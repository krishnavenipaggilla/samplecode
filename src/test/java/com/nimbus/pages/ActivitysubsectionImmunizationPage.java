package com.nimbus.pages;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import excelReader.DataReaderExcel;
import utility.ConfigReader;
import utility.UtilityClass;

//Author:- AF86867
//Epic-NIM-17036
public class ActivitysubsectionImmunizationPage extends UtilityClass {

	ConfigReader config = new ConfigReader();
	DataReaderExcel DataRead = new DataReaderExcel();
	WebDriver driver;
	Logger Log;

	public ActivitysubsectionImmunizationPage(WebDriver ldriver) {
		this.driver = ldriver;
	}

	public ActivitysubsectionImmunizationPage(Logger lLog) {
		this.Log = lLog;
	}

	public void clickMemberActionCenter() throws Exception {
		try { // Click
			Log = Logger.getLogger("LeftNavigationLink.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

			WebElement memberActionCenter = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Member Action Center']")));
			memberActionCenter.click();
			Log.info("Clicked \"Member Action Center\" on PageCaseInfo");
			Thread.sleep(3000);

		} catch (Exception e) {
			e.printStackTrace();
			Log.info("Object not visible");
		}

	}

	public void clickActivity() throws Exception {
		try { // Click
			Log = Logger.getLogger("LeftNavigationLink.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

			//wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.ActivitiesLink()))).click();
			LeftNavigationLink lnk = new LeftNavigationLink(driver);
			lnk.clickActivities();
			Log.info("Clicked \"Activity\" on leftNavigation");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
			Thread.sleep(2000);

		} catch (Exception e) {
			e.printStackTrace();
			Log.info("Activity link is not Visible");
		}

	}

	public void ClickEditActivity() throws Exception {
		try {

			Log = Logger.getLogger("LeftNavigationLink.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.Editactivity()))).click();
			Log.info("Clicked on Edit Activity Link");

		} catch (Exception e) {
			e.printStackTrace();
			Log.info("Edit Activity link is not Visible");
		}

	}

	public void AddSubActivity() throws Exception {
		try {

			Log = Logger.getLogger("LeftNavigationLink.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Initial Outreach')]")));

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.AddSubActivity()))).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath(config.ImmunizationSubActivity())).click();
			Log.info("Clicked on Immunization Sud Activity");
			Thread.sleep(3000);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.AddButtonForImmunization()))).click();
			Log.info("Added Immunization");
			Log.info("Immunization Subsection Added");

		} catch (Exception e) {
			e.printStackTrace();
			Log.info("Immunization Subsection is not available");
		}

	}

	public void AddImmunization(String TaskName) throws Exception {
		try {

			Log = Logger.getLogger("LeftNavigationLink.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			ActivityPage addActivity = new ActivityPage(driver);
			addActivity.editInitialOutreachActivity(TaskName);
			driver.findElement(By.xpath(config.ImmunizationAccordian())).click();
			Log.info("Clicked on Immunization");
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getAddImmunizationbutton()))).click();
			Log.info("Clicked on Add Immunization button");
			Thread.sleep(2000);
			String Actual = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.AddImmunizationText())))
					.getText();
			Assert.assertEquals(Actual, "Add Immunization");
			Log.info("Add Immunization Page got displayed");

			AnnualAssessment annual = new AnnualAssessment(driver);
			annual.annual_assessment("AddImmunizations_Subsections", "CheckDropdown", 4);
			WebElement CheckBox = driver.findElement(By.xpath(config.EducationProvidedCheckbox()));
			if (!(CheckBox.isSelected())) {
				Log.info("Checkbox is not selected as expected");
			} else {
				Assert.assertTrue(CheckBox.isSelected());
			}
			annual.annual_assessment("AddImmunizations_Subsections", "CheckDropdown", 3);
			WebElement Text = driver.findElement(By.xpath(config.ReasonForRefusalText()));
			Assert.assertFalse(Text.isDisplayed());
			Log.info("Reason For Refusal dropdown not displayed when clicked NO on Refused? ");
			Log.info("Passed-NIM-17720: Activity Sub-section: Immunizations create Immunizations subsection" + '\n' + "GIVEN:create Immunizations subsection"
					+ '\n' + "THEN:verifying Immunizations subsection");
			Log.info("Passed-NIM-17729: Activity Sub-section: Immunizations Add Immunization" + '\n' + "GIVEN:Add Immunization"
					+ '\n' + "THEN:verifying add Immunizations ");

		} catch (Exception e) {
			e.printStackTrace();
			Log.info("Not able to add Immunization ");
			Log.info("Failed-NIM-17720: Activity Sub-section: Immunizations create Immunizations subsection");
			Log.info("Failed-NIM-17729: Activity Sub-section: Immunizations Add Immunization");
		}

	}

	public void ClickUpdateImmunization() throws Exception {
		try {

			Log = Logger.getLogger("LeftNavigationLink.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			AnnualAssessment annual = new AnnualAssessment(driver);
			annual.annual_assessment("AddImmunizations_Subsections", "CheckDropdown", 4);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Submit')]")))
					.click();
			// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.InitailOutreachText()))).getText();
			Log.info("Landed back to Activity Page");
			Thread.sleep(7000);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.ImmunizationAccordian()))).click();
			Thread.sleep(2000);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.EditImmunization()))).click();
			Log.info("Clicked on Update Immunization Link");
			String Actual = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.EditImmunizationText())))
					.getText();
			Assert.assertEquals(Actual, "Edit Immunization");
			Log.info("Edit Immunation page got displayed");

		} catch (Exception e) {
			e.printStackTrace();
			Log.info("Not able to add Immunization ");
		}

	}

	public void UpdateImmunization() throws Exception {
		try {

			Log = Logger.getLogger("LeftNavigationLink.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			AnnualAssessment annual = new AnnualAssessment(driver);

			WebElement immunizationname = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//p-dropdown/div/label)[1]")));
			Assert.assertFalse(immunizationname.isSelected());
			Log.info("Immunization Name is non editable in update Immunization page");

			WebElement Refused = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//p-dropdown/div/label)[2]")));
			Assert.assertFalse(Refused.isSelected());
			Log.info("Refused Yes/No is non editable in update Immunization page");

			WebElement ReasonforRefusal = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//p-dropdown/div/label)[3]")));
			Assert.assertFalse(ReasonforRefusal.isSelected());
			Log.info("Reason for Refusal is non editable in update Immunization page");

			Assert.assertNotNull(wait
					.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("//div/nm-section/div/nm-form/form/nm-frm-grp[5]/span/nm-element/div/nm-input/p")))
					.getText());
			Log.info("Facility Name is non editable in update Immunization page");
			Robot robot = new Robot();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()=' Date Administered ']//parent::nm-input-label//following-sibling::p-calendar/span/input"))).sendKeys("01/10/2019");
    		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']"))).click();
			Log.info("Passed-NIM-17724: Activity Sub-section: Immunizations Navigation for Add & Update page" + '\n' + "GIVEN:Immunizations Navigation for Add & Update page"
					+ '\n' + "THEN:verifying Immunizations Add & Update page");
			Log.info("Passed-NIM-17733: Activity Sub-section: Immunizations Update Immunization" + '\n' + "GIVEN:Update Immunization"
					+ '\n' + "THEN:verifying Update Immunization");


		} catch (Exception e) {
			e.printStackTrace();
			Log.info("Update Immunization verification failed");
			Log.info("Failed-NIM-17724: Activity Sub-section: Immunizations Navigation for Add & Update page");
			Log.info("Failed-NIM-17733: Activity Sub-section: Immunizations Update Immunization");
		}

	}

	public void CheckUpdateButtonDisabled() throws Exception {
		try {

			Log = Logger.getLogger("LeftNavigationLink.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			AnnualAssessment annual = new AnnualAssessment(driver);

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.ImmunizationAccordian()))).click();
			Thread.sleep(2000);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getAddImmunizationbutton()))).click();
			Log.info("Clicked on Add Immunization button");
			Thread.sleep(2000);
			annual.annual_assessment("AddImmunizations_Subsections", "CheckDropdown", 5);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Submit')]")))
					.click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.ImmunizationAccordian()))).click();
			Thread.sleep(5000);
			
			List<WebElement> result = driver.findElements(By.xpath(config.EditImmunization()));

			if (result.size() == 0) {
				Assert.assertTrue(true);
				
				Log.info("Edit Immunization button is Disabled when Refused");
			} else {
				Assert.assertTrue(false);
				Log.info("Failed:Edit Immunization button is present when Refused");
			}

		} catch (Exception e) {
			e.printStackTrace();
			Log.info("Update Button is not disabled");
		}

	}

	public void CheckImmunizationPagination() throws Exception {
		try {

			Log = Logger.getLogger("LeftNavigationLink.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			AnnualAssessment annual = new AnnualAssessment(driver);
			// checking the pagination(pagination should start after 15th row)
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
			Thread.sleep(1000);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.ImmunizationAccordian()))).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
			Thread.sleep(1000);
			for (int i = 0; i <= 16; i++) {
				Thread.sleep(3000);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.ImmunizationAccordian()))).click();
				Thread.sleep(7000);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getAddImmunizationbutton())))
						.click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
				Thread.sleep(1000);
				annual.annual_assessment("AddImmunizations_Subsections", "CheckDropdown", 4);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Submit')]")))
						.click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
				Thread.sleep(1000);

			}
			Log.info("Checking Pagination after adding records");
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.ImmunizationAccordian()))).click();

			int rowsInGrid = 0;

			WebElement gridHeader = wait.until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//p-header//caption[contains(text(),'Immunizations')]")));
			String gridHeaderText = gridHeader.getText();
			Log.info("Grid Name is - " + gridHeaderText);

			WebElement table = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table")));

			List<WebElement> allRowsOfTable = table.findElements(By.xpath(config.ImmunizationPagination()));

			rowsInGrid = allRowsOfTable.size();
			Log.info("No of uploaded documents on the grid is:  " + rowsInGrid);

			if (rowsInGrid == 15) {
				Assert.assertTrue(true);
				Log.info("Pagination is as per AC---15 rows per page");
			} else {
				Assert.assertTrue(false);
				Log.info("Pagination is failed");
			}

		} catch (Exception e) {
			e.printStackTrace();
			Log.info("pagination failed");
		}

	}
}