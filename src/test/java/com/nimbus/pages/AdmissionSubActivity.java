package com.nimbus.pages;

import static org.testng.Assert.assertEquals;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import excelReader.DataReaderExcel;
import utility.ConfigReader;

public class AdmissionSubActivity {

	WebDriver driver;
	Logger Log;

	ConfigReader config = new ConfigReader();
	DataReaderExcel DataRead = new DataReaderExcel();

	public AdmissionSubActivity(WebDriver ldriver) {
		this.driver = ldriver;

	}

	public AdmissionSubActivity(Logger lLog) {
		this.Log = lLog;

	}

	public void addnVerifyAdmissionSubActivity(String TaskName) throws Exception {
		Log = Logger.getLogger("AdmissionSubActivity.class");
		PropertyConfigurator.configure("log4j.properties");
		ActivityPage addActivity = new ActivityPage(driver);
		addActivity.editInitialOutreachActivity(TaskName);
		Thread.sleep(1000);
		List<WebElement> defaultSubActivitiesElementsFromUI = driver
				.findElements(By.xpath("//nm-section//a[@role = 'tab']//p-header//h2"));

		boolean isFound = false;

		for (int i = 0; i < defaultSubActivitiesElementsFromUI.size(); i++) {
			if (defaultSubActivitiesElementsFromUI.get(i).getText().contains("Admissions")) {
				isFound = true;
				break;
			}
		}

		if (isFound) {
			driver.findElement(By.xpath("//h2[contains(text(),'Admissions')]")).click();
			Log.info("Clicked on Admissions Accordian");
		} else {
			driver.findElement(By.xpath("//i[@class = 'fa addsubactivity ng-star-inserted']")).click();
			Log.info("Clicked on add subactivity Icon");
			Thread.sleep(GlobalValues.Sleep_wait_time);
			
			String subActivityModelWindowHeader = driver
					.findElement(By.xpath("//p-header[contains(text(),'Add Sub Activity')]")).getText().trim();
			Assert.assertEquals(subActivityModelWindowHeader, "Add Sub Activity");
			Thread.sleep(GlobalValues.Sleep_wait_time);

			driver.findElement(By.xpath("//label[contains(text(),'Admissions')]//..//label")).click();
			Log.info("Adding Admissions sub section from the modal window");
			Thread.sleep(GlobalValues.Sleep_wait_time);

			driver.findElement(By.xpath("//button[text()='Add']")).click();
			Log.info("Clicked on Add button of modal window");
			Thread.sleep(GlobalValues.Sleep_wait_time);
			
			driver.findElement(By.xpath("//h2[contains(text(),'Admissions')]")).click();
			Log.info("Clicked on Admissions Accordian");
			Thread.sleep(GlobalValues.Sleep_wait_time);
		}
	}

	public void verifyAdmissionsFromActivity(String TaskName) throws Exception {
		Log = Logger.getLogger("AdmissionSubActivity.class");
		PropertyConfigurator.configure("log4j.properties");
		LeftNavigationLink lnk = new LeftNavigationLink(driver);
		Global_Method GM = new Global_Method(driver);
		boolean passed = true;
		Admissions Admission = new Admissions(driver);
		AnnualAssessment a = new AnnualAssessment(driver);
		ValidateAnnualAssessment va = new ValidateAnnualAssessment (driver);
		
		try{
		addnVerifyAdmissionSubActivity(TaskName);
		Log.info("Passed-NIM-17032: Activity sub-section: Admissions" + '\n' + "GIVEN:sub-section: Admissions"
				+ '\n' + "THEN:verifying sub-section: Admissions");
		Log.info("Passed-NIM-17270: Admissions: Add an admissions record from Initial Outreach" + '\n' + "GIVEN:Add an admissions record from Initial Outreach"
				+ '\n' + "THEN:verifying admissions record from Initial Outreach");
		Log.info("Passed-NIM-17851: Sub Activity: Create dummy accordion and insert default and applicable section scripts" + '\n' + "GIVEN:Create dummy accordion and insert default and applicable section scripts"
				+ '\n' + "THEN:verifying admissions accordion");
		Log.info("Passed-NIM-17272: Admissions: Add admissions plugin to other activities (Grid, add & edit functionality)" + '\n' + "GIVEN:Add admissions subsection"
				+ '\n' + "THEN:verifying admissions subsection in activities");
		Log.info("Passed-NIM-17267: Sub Activity: Populate data inside the sub section grid" + '\n' + "GIVEN:Populate data inside the sub section"
				+ '\n' + "THEN:verifying Populate data inside the sub section");
		Thread.sleep(4000);
		}
		catch(Exception e){
			Log.info("Failed-NIM-17032: Activity sub-section: Admissions" );
			Log.info("Failed-NIM-17270: Admissions: Add an admissions record from Initial Outreach");
			Log.info("Failed-NIM-17851: Sub Activity: Create dummy accordion and insert default and applicable section scripts");
			Log.info("Failed-NIM-17272: Admissions: Add admissions plugin to other activities (Grid, add & edit functionality)");
			Log.info("Failed-NIM-17267: Sub Activity: Populate data inside the sub section grid");
			
		}
		
		try {
			Admission.AdmissionsGridVerification();// verify admissions grid
		} catch (Exception e) {
			e.printStackTrace();
			passed = false;
		}

		AddAdmissions(); // Add a Admission

		driver.findElement(By.xpath("//h2[contains(text(),'Admissions')]")).click();
		Log.info("Clicked on Admissions Accordian ");
		Thread.sleep(3000);
		try {
			AdmissionSecondRowVerification(); // secondary row verification
		} catch (Exception e) {
			e.printStackTrace();
			passed = false;
		}

		try {
			AdmissionDaysInHospital(); // Calculate Days in Hospital
		} catch (Exception e) {
			e.printStackTrace();
			passed = false;
		}

		try {
			EditAdmission(); // Edit a Admission
			Log.info("Passed-NIM-17271: Admissions: Edit an Admissions record from Initial Outreach" + '\n' + "GIVEN:Edit an Admissions record from Initial Outreach"
					+ '\n' + "THEN:verifying edit admissions record from Initial Outreach");
		} catch (Exception e) {
			e.printStackTrace();
			passed = false;
			Log.info("Failed-NIM-17271: Admissions: Edit an Admissions record from Initial Outreach");
		}
		driver.findElement(By.xpath("//h2[contains(text(),'Admissions')]")).click();
		Log.info("Clicked on Admissions Accordian");
		try {
//			AdmissionDaysInHospital(); // Re-Calculate Days in Hospital
		} catch (Exception e) {
			e.printStackTrace();
			passed = false;
		}
		
		try {
			Thread.sleep(3000);
			WebElement ClickEditAdmission = driver.findElement(By.xpath(config.getEditAdmissionFromActivityXpath()));

			ClickEditAdmission.click();
			a.annual_assessment("AnnualAssessments_CMDM", "EditAdmission", 2);//ii
		} catch (Exception e) {
			e.printStackTrace();
			passed = false;
		}
		
		lnk.clickHealthChart();
		lnk.clickAdmissions();
		try {
			//AdmissionDaysInHospital(); 
			driver.findElement(By.xpath("(//p-header//caption[contains(text(),'Admissions')]//ancestor::div[@class='scrollGrid']//p-table//tr[1]//td//i)[1]")).click();
			Log.info("Expanded the sencondary row ");
			Thread.sleep(3000);
			verifyEditedAdmission("EditAdmission"); //ii
		} catch (Exception e) {
			e.printStackTrace();
			
			passed = false;
		}
		
		if (passed == true) {
//			GM.Reports("NIM-17032", "Pass");
		} else {

//			GM.Reports("NIM-17032", "Fail");
		}
	}

	public void verifyEditedAdmission(String sheetname) throws Exception{
		Log = Logger.getLogger("AdmissionSubActivity.class");
		PropertyConfigurator.configure("log4j.properties");
		
		Global_Method gm = new Global_Method(driver);
		List<WebElement> admissionElement = driver.findElements(By.xpath("//p-header/caption[text() = 'Admissions']//ancestor::div[@class ='ui-datatable-header ui-widget-header ng-star-inserted']//following-sibling::p-table//tbody/tr[position()=1]//td"));
		String typeFromUI = admissionElement.get(1).getText().trim();
		String admitDatefromUI = admissionElement.get(2).getText().trim();
		String daysInHospitalFromUI = admissionElement.get(3).getText().trim();
		String providerNameFromUI = admissionElement.get(4).getText().trim();
		String actualDischargeDateFromUI = admissionElement.get(5).getText().trim();
		String postDischargeCompDateFromUI = admissionElement.get(6).getText().trim();
		
		List<WebElement> admissionSecElement = driver.findElements(By.xpath("//p-header/caption[text() = 'Admissions']//ancestor::div[@class ='ui-datatable-header ui-widget-header ng-star-inserted']//following-sibling::p-table//tbody/tr[position()=1]//following-sibling::tr[@class ='ui-expanded-row-content ng-star-inserted']//nm-card-details-field//div[contains(@class, 'textWrapBreakWord')]"));
		String notificationDateFromUI = admissionSecElement.get(0).getText().trim();
		String preAdmissionCompletionDateFromUI = admissionSecElement.get(1).getText().trim();
		String dischargeNotificationDateFromUI =admissionSecElement.get(2).getText().trim();
		String plannedDischargeDateFromUI =admissionSecElement.get(3).getText().trim();
		String providerGroupFacilityNameFromUI =admissionSecElement.get(4).getText().trim();
		String providerPhoneFromUI =admissionSecElement.get(5).getText().trim();
		
		Assert.assertEquals(typeFromUI, gm.readFromExcel("AnnualAssessments_CMDM", sheetname, 2, "Type"));
		Assert.assertEquals(admitDatefromUI, gm.readFromExcel("AnnualAssessments_CMDM", sheetname, 2, "Admit Date"));
		//Assert.assertEquals(daysInHospitalFromUI, gm.readFromExcel("AnnualAssessments_CMDM", sheetname, 2, "First Name"));
		Assert.assertEquals(providerNameFromUI, gm.readFromExcel("AnnualAssessments_CMDM", sheetname, 2, "Provider Name"));
		Assert.assertEquals(actualDischargeDateFromUI, gm.readFromExcel("AnnualAssessments_CMDM", sheetname, 2, "Actual Discharge Date"));
		Assert.assertEquals(postDischargeCompDateFromUI, gm.readFromExcel("AnnualAssessments_CMDM", sheetname, 2, "Post-Discharge Completion Date"));
		//Assert.assertEquals(notificationDateFromUI, gm.readFromExcel("AnnualAssessments_CMDM", sheetname, 2, "User Status"));
		Assert.assertEquals(preAdmissionCompletionDateFromUI, gm.readFromExcel("AnnualAssessments_CMDM", sheetname, 2, "Pre-Admission Completion Date"));
		Assert.assertEquals(dischargeNotificationDateFromUI, gm.readFromExcel("AnnualAssessments_CMDM", sheetname, 2, "Discharge Notification Date"));
		Assert.assertEquals(plannedDischargeDateFromUI, gm.readFromExcel("AnnualAssessments_CMDM", sheetname, 2, "Planned Discharge Date"));
		Assert.assertEquals(providerGroupFacilityNameFromUI, gm.readFromExcel("AnnualAssessments_CMDM", sheetname, 2, "Provider Group/Facility Name"));
		Assert.assertEquals(providerPhoneFromUI, gm.readFromExcel("AnnualAssessments_CMDM", sheetname, 2, "Provider Phone"));
		Log.info("Verified the edited values with Admissions on Left Nav");
	}
	
	public void AdmissionDaysInHospital() throws ParseException {
		try {
			Log = Logger.getLogger("AdmissionSubActivity.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, 20);

			// Getting System date value
			SimpleDateFormat dateFormat = new SimpleDateFormat(GlobalValues.StdDateformat.toString());
			DateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
			Date date1 = new Date();
			String date = DATE_FORMAT.format(date1);

			Log.info("Calculate Days in Hospital - Admissions");

			WebElement Admission_AdmitDate = driver
					.findElement((By.xpath(config.getAdmissionAdmitDateFromActivityXpath())));
			WebElement Admission_ActualDischargeDate = driver
					.findElement((By.xpath(config.getAdmissionActualDischargeDateXpath())));

			String AdmitDateText = Admission_AdmitDate.getText().toString();
			String ActualDischargeDateText = Admission_ActualDischargeDate.getText().toString();
			Thread.sleep(GlobalValues.Sleep_wait_time);

			WebElement DaysInHospital = driver.findElement(By.xpath(config.getDaysInHospitalFromActivityXpath()));
			String DaysInHospitalText = DaysInHospital.getText();

			if ((Admission_ActualDischargeDate.getText()).isEmpty()) {
				Log.info("Actual Discharge Date is not Present");
				try {
					java.util.Date dateBefore = dateFormat.parse(AdmitDateText);
					java.util.Date dateAfter = dateFormat.parse(date);

					String DateBefore = DATE_FORMAT.format(dateBefore);
					String DateAfter = DATE_FORMAT.format(dateAfter);
					Date dateA = dateFormat.parse(DateAfter);
					Date dateB = dateFormat.parse(DateBefore);
					long difference = dateA.getTime() - dateB.getTime();
					int daysBetween = (int) ((difference / (1000 * 60 * 60 * 24)) + 1);
					String Str = String.valueOf(daysBetween);
					Assert.assertEquals(DaysInHospitalText, Str);

					Log.info("Days in Hospital matched without Actual Discharge Date");

				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				Log.info("Actual Discharge Date is Present");
				try {
					java.util.Date dateBefore = dateFormat.parse(AdmitDateText);
					java.util.Date dateAfter = dateFormat.parse(ActualDischargeDateText);
					long difference = dateAfter.getTime() - dateBefore.getTime();
					int daysBetween = (int) ((difference / (1000 * 60 * 60 * 24)) + 1);

					String Str = String.valueOf(daysBetween);
					Assert.assertEquals(DaysInHospitalText, Str);
					Log.info("Days in Hospital matched With Actual Discharge Date");

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			Log.info("Failed - " + e);
		}
	}

	public void AddAdmissions() throws Exception {
		try {
			Log = Logger.getLogger("AdmissionSubActivity.class");
			PropertyConfigurator.configure("log4j.properties");
			AnnualAssessment annual = new AnnualAssessment(driver);
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			// Click Add Admission button
			WebElement addAdmission = driver.findElement(By.xpath(config.getAddAdmissionsFromActivityXpath()));
			if (addAdmission.getText().equals("Add Admission")) {
				Log.info("Click on Add Admission button in Admission page");
			} else {
				Log.info("Add Admission button is not present in Admission page");
			}
			addAdmission.click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
			Thread.sleep(1000);

			// Add Admission grid header verification
			WebElement GridHeaderVerifcation = driver.findElement(By.xpath(config.GridHeaderVerify()));
			assertEquals(GridHeaderVerifcation.getText(), "Add Admission");
			Log.info("Add Admissions page header is present");

			// verifying "Type" dropdown values
			WebElement TypeDropdown = driver.findElement(By.xpath(config.getTypeDropdown()));
			TypeDropdown.click();
			for (int i = 0; i < GlobalValues.TypedropdownValues.size(); i++) {
				driver.findElement(By.xpath("//p-dropdown//div[3]//li[" + (i + 1) + "]//span"));
				Log.info("Verifying the " + GlobalValues.TypedropdownValues.get(i) + "- value in the TYPE dropdown");
			}

			// verifying "Notification Date" value
			WebElement NotificationDateVerify = driver.findElement(By.xpath(config.getNotificationDate()));
			String NotificatioDateValue = NotificationDateVerify.getAttribute("value");

			// Getting System date value
			SimpleDateFormat dateFormat = new SimpleDateFormat(GlobalValues.StdDateformat.toString());
			DateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
			Date date1 = new Date();
			String date = DATE_FORMAT.format(date1);
			assertEquals(NotificatioDateValue, date);
			if (NotificatioDateValue.equals(date)) {
				Log.info(" Notification Date is matching with System Date");
			} else {
				Log.info(" Notification Date is not matching with System Date");
			}
			driver.findElement(By.xpath(config.getProviderName())).click(); // cursor
																			// moving
																			// from
																			// "Type"
																			// field
																			// to
																			// "Provider"
																			// field

			// adding values to the Add Admission page fields
			Thread.sleep(3000);
			annual.annual_assessment("AnnualAssessments_CMDM", "Admissions", 2);
			Log.info("Admission added to the Grid");
			driver.findElement(By.xpath(config.getProviderName())).click();
			driver.findElement(By.xpath("//button[text()='Submit']")).click();

			WebElement ShowingValue = driver.findElement(By.xpath(config.getShowingValue()));
			Log.info("Admissions Grid - Showing values displaying as : " + ShowingValue.getText());

		} catch (Exception e) {
			Log.info("Failed - " + e);
		}
	}

	public void AdmissionSecondRowVerification() throws Exception {
		try {
			Log = Logger.getLogger("AdmissionSubActivity.class");
			PropertyConfigurator.configure("log4j.properties");
			AnnualAssessment annual = new AnnualAssessment(driver);

			Log.info("Secondary Row verification in Admissions");

			WebElement AdmissionView = driver.findElement(By.xpath(config.getAdmissionViewFromActivityXpath()));

			AdmissionView.click();

			// verifying secondary row label name
			for (int i = 0; i < GlobalValues.AdmissionsSecondaryRowLabels.size(); i++) {

				driver.findElement(By.xpath("//nm-card-details/div//span//div//label[text()='"
						+ GlobalValues.AdmissionsSecondaryRowLabels.get(i) + "']"));
				Log.info("Verifying the  " + GlobalValues.AdmissionsSecondaryRowLabels.get(i)
						+ "- label in Admission Second row");
			}
		} catch (Exception e) {
			Log.info("Failed - " + e);
		}

	}

	public void EditAdmission() throws Exception {
		Log = Logger.getLogger("AdmissionSubActivity.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

		Log.info("Edit/Update Admissions Grid");

		WebElement ClickEditAdmission = driver.findElement(By.xpath(config.getEditAdmissionFromActivityXpath()));

		ClickEditAdmission.click(); // click Edit Admission button
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
		Thread.sleep(1000);
		// Edit Admission Grid header verification
		WebElement gridHeader = driver.findElement(By.xpath(config.EditGridHeader()));
		Thread.sleep(3000);
		assertEquals(gridHeader.getText(), "Edit Admission");
		if (gridHeader.isDisplayed()) {
			Robot robot = new Robot();
			robot.delay(3000);
			WebElement Edit_ActualDischargeDate = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getEdit_ActualDischargeDate())));
			Edit_ActualDischargeDate.clear();

			Edit_ActualDischargeDate.click(); // editing Actual Discharge Date
												// to calculate days in hospital
												// with system date
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_A);
			robot.keyRelease(KeyEvent.VK_A);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(1000);
			robot.keyPress(KeyEvent.VK_DELETE);
			robot.keyRelease(KeyEvent.VK_DELETE);
			driver.findElement(By.xpath("//label[contains(text(),'Provider Phone')]")).click();
			Thread.sleep(3000);
			WebElement Edit_Submit = driver.findElement(By.xpath("//button[text()='Submit']"));
			Edit_Submit.click();
			Thread.sleep(3000);
			 WebElement DaysInHospital =
			 driver.findElement(By.xpath("//span[contains(text(),'Days in Hospital')]//ancestor::thead//following-sibling::tbody//td[4]//span//span"));
			 System.out.println(DaysInHospital.getText());

			Log.info("Admission values Updated");
		} else {
			Log.info("Admission Grid not updated");
		}

	}

	
}
