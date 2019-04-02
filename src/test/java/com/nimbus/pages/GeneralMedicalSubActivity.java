package com.nimbus.pages;

import java.text.DateFormat;
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

public class GeneralMedicalSubActivity {

	WebDriver driver;
	Logger Log;

	ConfigReader config = new ConfigReader();
	DataReaderExcel DataRead = new DataReaderExcel();

	public GeneralMedicalSubActivity(WebDriver ldriver) {
		this.driver = ldriver;

	}

	public GeneralMedicalSubActivity(Logger lLog) {
		this.Log = lLog;

	}

	public void verifyGMFromActivity(String TaskName) throws Exception {
		Log = Logger.getLogger("GeneralMedicalSubActivity.class");
		PropertyConfigurator.configure("log4j.properties");
		LeftNavigationLink lnk = new LeftNavigationLink(driver);
		Global_Method GM = new Global_Method(driver);
		boolean passed = true;

		try {
			addGeneralMedicalSubActivity(TaskName);
			Log.info("Verified Filling General Medical Form from Activity");
			Log.info("Passed-NIM-18396: Create a Layout of General Medical in Sub-Section of Activities" + '\n'
					+ "GIVEN:General Medical in Sub-Section of Activities" + '\n'
					+ "THEN:verifying General Medical in Sub-Section of Activities");
			Log.info("Passed-NIM-18410: Add and Edit the Data on the General Medical Sub-Section" + '\n'
					+ "GIVEN:Add and Edit the Data on the General Medical" + '\n'
					+ "THEN:verifying General Medical in Sub-Section of Activities");
		} catch (Exception e) {
			e.printStackTrace();
			Log.info(e);
			passed = false;
			Log.info("Failed-NIM-18396: Create a Layout of General Medical in Sub-Section of Activities");
			Log.info("Failed-NIM-18410: Add and Edit the Data on the General Medical Sub-Section");
		}

		try {
			verifyHistoryForGMFromActivity();
			Log.info("Verified the History for respective questions in the General Medical form from Activity");
			Thread.sleep(GlobalValues.Sleep_wait_time);
			Log.info("Passed-NIM-18398: NIM-18398 Update the General Medical Sub-Section with  History icons" + '\n' + "GIVEN:Update the General Medical Sub-Section with  History icons"
					+ '\n' + "THEN:verifying Update the General Medical Sub-Section with  History icons");

		} catch (Exception e) {
			e.printStackTrace();
			Log.info(e);
			passed = false;
			Log.info("Failed-NIM-18398: NIM-18398 Update the General Medical Sub-Section with  History icons");
		}

		try {
			editGMFromActivity();
			Log.info("Verified the editing GM from Activity");
			Log.info("Passed-NIM-18397: Populating Data of General Medical in Sub-Section of Activities" + '\n' + "GIVEN:Populating Data of General Medical in Sub-Section"
					+ '\n' + "THEN:veryfing data ");
		} catch (Exception e) {
			e.printStackTrace();
			Log.info(e);
			passed = false;
			Log.info("Failed-NIM-18397: Populating Data of General Medical in Sub-Section of Activities");
		}

		try {
			lnk.clickHealthChart();
			lnk.clickGeneralMedical();
			verifyGMLNAfterEditingFromActivity();
			Log.info("Verified the General Medical Left Nav after editing GM from Activity");
		} catch (Exception e) {
			e.printStackTrace();
			Log.info(e);
			passed = false;
		}

		if (passed == true) {
			GM.Reports("NIM-17059", "Pass");
		} else {

			GM.Reports("NIM-17059", "Fail");
		}

	}

	public void addGeneralMedicalSubActivity(String TaskName) throws Exception {
		Log = Logger.getLogger("GeneralMedicalSubActivity.class");
		PropertyConfigurator.configure("log4j.properties");
		ActivityPage addActivity = new ActivityPage(driver);
		addActivity.editInitialOutreachActivity(TaskName);
		List<WebElement> defaultSubActivitiesElementsFromUI = driver
				.findElements(By.xpath("//nm-section//a[@role = 'tab']//p-header//h2"));

		boolean isFound = false;

		for (int i = 0; i < defaultSubActivitiesElementsFromUI.size(); i++) {
			if (defaultSubActivitiesElementsFromUI.get(i).getText().contains("General Medical")) {
				isFound = true;
				break;
			}
		}

		if (isFound) {
			driver.findElement(By.xpath(config.getGMAccordianClickFromActivityXpath())).click();
			Log.info("Clicked on General Medical Accordian");
			Thread.sleep(GlobalValues.Sleep_wait_time);
		} else {
			driver.findElement(By.xpath("//i[@class = 'fa addsubactivity ng-star-inserted']")).click();
			Log.info("Clicked on add subactivity Icon");
			String subActivityModelWindowHeader = driver
					.findElement(By.xpath("//p-header[contains(text(),'Add Sub Activity')]")).getText().trim();
			Assert.assertEquals(subActivityModelWindowHeader, "Add Sub Activity");
			Thread.sleep(GlobalValues.Sleep_wait_time);
			driver.findElement(By.xpath(config.getAddGMSubsectionFromActivityXpath())).click();
			Log.info("Adding General Medical sub section from the modal window");
			Thread.sleep(GlobalValues.Sleep_wait_time);
			Thread.sleep(GlobalValues.Sleep_wait_time);

			driver.findElement(By.xpath("//button[text()='Add']")).click();
			Log.info("Clicked on Add button of modal window");

			Thread.sleep(GlobalValues.Sleep_wait_time);

			driver.findElement(By.xpath(config.getGMAccordianClickFromActivityXpath())).click();
			Log.info("Clicked on General Medical Accordian");
			Thread.sleep(GlobalValues.Sleep_wait_time);
		}

		GeneralMedicalPage gm = new GeneralMedicalPage(driver);
		AnnualAssessment a = new AnnualAssessment(driver);

		a.annual_assessment("AnnualAssessments_CMDM", "AddGeneralMedical", 3);
		Log.info("Filled and Submitted the General Medical Form");

		// DateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		GlobalValues.GeneralMedicalSubmit_Date = df.format(date);
		Log.info(GlobalValues.GeneralMedicalSubmit_Date);
		Thread.sleep(GlobalValues.Sleep_wait_time);

	}

	public void editGMFromActivity() throws Exception {
		Log = Logger.getLogger("GeneralMedicalSubActivity.class");
		PropertyConfigurator.configure("log4j.properties");
		AnnualAssessment a = new AnnualAssessment(driver);
		a.annual_assessment("AnnualAssessments_CMDM", "EditGeneralMedical", 2);
		Log.info("Edited and Submitted the General Medical Form");

		DateFormat df1 = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
		Date date1 = new Date();
		GlobalValues.GeneralMedicalSubmit_Date = df1.format(date1);
		GlobalValues.GeneralMedicalSubmit_Date = "10/17/2018 06:00 PM";
		Log.info(GlobalValues.GeneralMedicalSubmit_Date);
		Thread.sleep(GlobalValues.Sleep_wait_time);
	}

	public void verifyGMLNAfterEditingFromActivity() throws Exception {
		Log = Logger.getLogger("GeneralMedicalSubActivity.class");
		PropertyConfigurator.configure("log4j.properties");
		ValidateAnnualAssessment validateEditGM = new ValidateAnnualAssessment(driver);
		validateEditGM.validate_annual_assessment("AnnualAssessments_CMDM", "EditGeneralMedical", 2);
		Log.info("Validated that the edited values are reflecting in the form");
		Thread.sleep(GlobalValues.Sleep_wait_time);
	}

	public void verifyHistoryForGMFromActivity() throws Exception {
		Log = Logger.getLogger("GeneralMedicalSubActivity.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		Global_Method gm = new Global_Method(driver);
		DashboardFunctions df = new DashboardFunctions(driver);

		try {
			GlobalValues.UserNameOnUI = driver.findElement(By.xpath("//div[@class ='userName']//child::nm-value"))
					.getText().trim();
			String enteredBy = GlobalValues.UserNameOnUI;

			String programName = "";
			if (GlobalValues.DBProgramNameActiveStatus.contains("true")
					&& GlobalValues.DBProgramNamePrimaryStatus.contains("true")) {
				programName = GlobalValues.DBProgramName;
			}
			String programNameFromBanner = driver.findElement(By.xpath(config.getProgramNameFromBannerXpath()))
					.getText().toString().trim();
			String sourceFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 3,
					"If applicable, please select source of current changes").trim().toString();

			// ********** Validate Question - Current Weight**********
			driver.findElement(By.xpath(config.getshowHistoryElementXpath().replace("instanceNumber", "1"))).click();
			Thread.sleep(GlobalValues.Sleep_wait_time);
			List<WebElement> tableHeaders = driver.findElements(By.xpath(config.getGMtableHeadersFromActivityXpath()));
			int numberOfColumnsInHistoryGrid = tableHeaders.size();
			Log.info(numberOfColumnsInHistoryGrid);

			validateColumnsOfWithDOSGrid();

			WebElement currentWeightElement = driver
					.findElement(By.xpath(config.getGMQuestionLabelXpathFromActivityXpath().replace("instance", "8")));
			String currentWeightQuestionText = currentWeightElement.getText().trim();
			String answerFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 3,
					currentWeightQuestionText + "|2");
			String isHistoryPresent = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 2,
					currentWeightQuestionText);

			String dateOfServiceFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 3,
					"Date of Service|2");

			List<WebElement> answersFromUIGrid = driver
					.findElements(By.xpath(config.getGMAnswersFromGridFromActivityXpath()));

			Assert.assertEquals(answersFromUIGrid.get(0).getText().trim().contains(answerFromExcel), true);
			Assert.assertEquals(answersFromUIGrid.get(1).getText().trim().substring(0, 10),
					GlobalValues.GeneralMedicalSubmit_Date);
			Assert.assertEquals(answersFromUIGrid.get(2).getText().trim(), enteredBy);
			Assert.assertEquals(answersFromUIGrid.get(3).getText().trim(), dateOfServiceFromExcel);
			Assert.assertEquals(answersFromUIGrid.get(4).getText().trim(), programName);
			Assert.assertEquals(answersFromUIGrid.get(5).getText().trim(), sourceFromExcel);

			Log.info("**********Verified the data in the history grid **********" + currentWeightQuestionText);
			Thread.sleep(1000);
			WebElement hideHistoryElement = driver.findElement(By.xpath(config.gethideHistoryElementXpath()));
			hideHistoryElement.click();
			Thread.sleep(1000);

			// **********Validate Question - Current Length/Height**********//

			driver.findElement(By.xpath(config.getshowHistoryElementXpath().replace("instanceNumber", "2"))).click();
			Thread.sleep(GlobalValues.Sleep_wait_time);
			tableHeaders = driver.findElements(By.xpath(config.getGMtableHeadersFromActivityXpath()));
			numberOfColumnsInHistoryGrid = tableHeaders.size();
			Log.info("Number of columns in the grid = " + numberOfColumnsInHistoryGrid);

			validateColumnsOfWithDOSGrid();

			WebElement currentLengthHeightElement = driver
					.findElement(By.xpath(config.getGMQuestionLabelXpathFromActivityXpath().replace("instance", "7")));
			String currentLengthHeightQuestionText = currentLengthHeightElement.getText().trim();
			answerFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 3,
					currentLengthHeightQuestionText + "|3");
			isHistoryPresent = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 2,
					currentLengthHeightQuestionText);

			dateOfServiceFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 3,
					"Date of Service|4");

			answersFromUIGrid = driver.findElements(By.xpath(config.getGMAnswersFromGridFromActivityXpath()));

			Assert.assertEquals(answersFromUIGrid.get(0).getText().trim().contains(answerFromExcel), true);
			Assert.assertEquals(answersFromUIGrid.get(1).getText().trim().substring(0, 10),
					GlobalValues.GeneralMedicalSubmit_Date);
			Assert.assertEquals(answersFromUIGrid.get(2).getText().trim(), enteredBy);
			Assert.assertEquals(answersFromUIGrid.get(3).getText().trim(), dateOfServiceFromExcel);
			Assert.assertEquals(answersFromUIGrid.get(4).getText().trim(), programName);
			Assert.assertEquals(answersFromUIGrid.get(5).getText().trim(), sourceFromExcel);

			Log.info("**********Verified the data  in the history grid***********" + currentLengthHeightQuestionText);
			Thread.sleep(1000);
			hideHistoryElement = driver.findElement(By.xpath(config.gethideHistoryElementXpath()));
			hideHistoryElement.click();
			Thread.sleep(1000);

			// ********** Validate Question - Child Growth Tracking**********
			driver.findElement(By.xpath(config.getshowHistoryElementXpath().replace("instanceNumber", "3"))).click();
			Thread.sleep(GlobalValues.Sleep_wait_time);
			tableHeaders = driver.findElements(By.xpath(config.getGMtableHeadersFromActivityXpath()));
			numberOfColumnsInHistoryGrid = tableHeaders.size();
			Log.info(numberOfColumnsInHistoryGrid);

			validateColumnsOfWithoutDOSGrid();

			WebElement childGrowthTrackingElement = driver
					.findElement(By.xpath(config.getGMQuestionLegendXpathFromActivityXpath().replace("instance", "7")));
			String childGrowthTrackingQuestionText = childGrowthTrackingElement.getText().trim();
			answerFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 3,
					childGrowthTrackingQuestionText);
			isHistoryPresent = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 2,
					childGrowthTrackingQuestionText);

			if (answerFromExcel.contains("Yes") || answerFromExcel.contains("No")) {

				answersFromUIGrid = driver.findElements(By.xpath(config.getGMAnswersFromGridFromActivityXpath()));

				Assert.assertEquals(answersFromUIGrid.get(0).getText().trim().contains(answerFromExcel), true);
				Assert.assertEquals(answersFromUIGrid.get(1).getText().trim().substring(0, 10),
						GlobalValues.GeneralMedicalSubmit_Date);
				Assert.assertEquals(answersFromUIGrid.get(2).getText().trim(), enteredBy);
				Assert.assertEquals(answersFromUIGrid.get(3).getText().trim(), programName);
				Assert.assertEquals(answersFromUIGrid.get(4).getText().trim(), sourceFromExcel);

				Log.info(
						"**********Verified the data  in the history grid**********" + childGrowthTrackingQuestionText);
			}
			hideHistoryElement = driver.findElement(By.xpath(config.gethideHistoryElementXpath()));
			hideHistoryElement.click();
			Thread.sleep(1000);

			// ********** Validate Question - ER visits since last NICU RN
			// outreach**********
			driver.findElement(By.xpath(config.getshowHistoryElementXpath().replace("instanceNumber", "4"))).click();
			Thread.sleep(GlobalValues.Sleep_wait_time);

			validateColumnsOfWithoutDOSGrid();

			WebElement ERVisitRadioElement = driver
					.findElement(By.xpath(config.getGMQuestionLegendXpathFromActivityXpath().replace("instance", "6")));
			String ERVisitRadioElementQuestionText = ERVisitRadioElement.getText().trim();
			answerFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 3,
					ERVisitRadioElementQuestionText);
			isHistoryPresent = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 2,
					ERVisitRadioElementQuestionText);

			if (answerFromExcel.contains("Yes")) {

				answerFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 3,
						"Date of ER visit");
				answersFromUIGrid = driver.findElements(By.xpath(config.getGMAnswersFromGridFromActivityXpath()));

				Assert.assertEquals(answersFromUIGrid.get(0).getText().trim().contains(answerFromExcel), true);
				Assert.assertEquals(answersFromUIGrid.get(1).getText().trim().substring(0, 10),
						GlobalValues.GeneralMedicalSubmit_Date);
				Assert.assertEquals(answersFromUIGrid.get(2).getText().trim(), enteredBy);
				Assert.assertEquals(answersFromUIGrid.get(3).getText().trim(), programName);
				Assert.assertEquals(answersFromUIGrid.get(4).getText().trim(), sourceFromExcel);

				Log.info(
						"**********Verified the data  in the history grid**********" + ERVisitRadioElementQuestionText);
			} else if (answerFromExcel.contains("No")) {
				Log.info(
						"History is not captured for 'No'. It`s captured only for ER visits since last NICU RN outreach date");
			}
			hideHistoryElement = driver.findElement(By.xpath(config.gethideHistoryElementXpath()));
			hideHistoryElement.click();
			Thread.sleep(1000);

			// ********** Validate Question - Hospital Readmissions since last
			// NICU RN outreach**********
			driver.findElement(By.xpath(config.getshowHistoryElementXpath().replace("instanceNumber", "5"))).click();
			Thread.sleep(GlobalValues.Sleep_wait_time);

			validateColumnsOfWithoutDOSGrid();

			WebElement hospitalReadmissionElement = driver
					.findElement(By.xpath(config.getGMQuestionLegendXpathFromActivityXpath().replace("instance", "5")));
			String hospitalReadmissionElementQuestionText = hospitalReadmissionElement.getText().trim();
			answerFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 3,
					hospitalReadmissionElementQuestionText);
			isHistoryPresent = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 2,
					hospitalReadmissionElementQuestionText);

			if (answerFromExcel.contains("Yes")) {
				answerFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 3,
						"Date of Hospital readmission");
				answersFromUIGrid = driver.findElements(By.xpath(config.getGMAnswersFromGridFromActivityXpath()));

				Assert.assertEquals(answersFromUIGrid.get(0).getText().trim().contains(answerFromExcel), true);
				Assert.assertEquals(answersFromUIGrid.get(1).getText().trim().substring(0, 10),
						GlobalValues.GeneralMedicalSubmit_Date);
				Assert.assertEquals(answersFromUIGrid.get(2).getText().trim(), enteredBy);
				Assert.assertEquals(answersFromUIGrid.get(3).getText().trim(), programName);
				Assert.assertEquals(answersFromUIGrid.get(4).getText().trim(), sourceFromExcel);

				Log.info("**********Verified the data  in the history grid**********"
						+ hospitalReadmissionElementQuestionText);
			} else if (answerFromExcel.contains("No")) {
				Log.info(
						"History is not captured for 'No'. It`s captured only for Hospital Readmissions since last NICU RN outreach date");
			}
			hideHistoryElement = driver.findElement(By.xpath(config.gethideHistoryElementXpath()));
			hideHistoryElement.click();
			Thread.sleep(1000);

			// ********** Validate Question - Importance Assessment Level-
			// Child**********
			driver.findElement(By.xpath(config.getshowHistoryElementXpath().replace("instanceNumber", "6"))).click();
			Thread.sleep(GlobalValues.Sleep_wait_time);

			validateColumnsOfWithoutDOSGrid();

			WebElement impAssessmentLevelChildElement = driver
					.findElement(By.xpath(config.getGMQuestionLabelXpathFromActivityXpath().replace("instance", "5")));
			String impAssessmentLevelChildElementQuestionText = impAssessmentLevelChildElement.getText().trim();
			answerFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 3,
					impAssessmentLevelChildElementQuestionText);
			isHistoryPresent = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 2,
					impAssessmentLevelChildElementQuestionText);

			answersFromUIGrid = driver.findElements(By.xpath(config.getGMAnswersFromGridFromActivityXpath()));

			Assert.assertEquals(answersFromUIGrid.get(0).getText().trim().contains(answerFromExcel), true);
			Assert.assertEquals(answersFromUIGrid.get(1).getText().trim().substring(0, 10),
					GlobalValues.GeneralMedicalSubmit_Date);
			Assert.assertEquals(answersFromUIGrid.get(2).getText().trim(), enteredBy);
			Assert.assertEquals(answersFromUIGrid.get(3).getText().trim(), programName);
			Assert.assertEquals(answersFromUIGrid.get(4).getText().trim(), sourceFromExcel);

			Log.info("**********Verified the data  in the history grid**********"
					+ impAssessmentLevelChildElementQuestionText);

			hideHistoryElement = driver.findElement(By.xpath(config.gethideHistoryElementXpath()));
			hideHistoryElement.click();
			Thread.sleep(1000);

			// ********** Validate Question - Confidence Level to Manage Child's
			// Health Concerns**********
			driver.findElement(By.xpath(config.getshowHistoryElementXpath().replace("instanceNumber", "7"))).click();
			Thread.sleep(GlobalValues.Sleep_wait_time);

			validateColumnsOfWithoutDOSGrid();

			WebElement confidenceLevelToManageChildHealthElement = driver
					.findElement(By.xpath(config.getGMQuestionLabelXpathFromActivityXpath().replace("instance", "5")));
			String confidenceLevelToManageChildHealthElementQuestionText = confidenceLevelToManageChildHealthElement
					.getText().trim();
			answerFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 3,
					confidenceLevelToManageChildHealthElementQuestionText);
			isHistoryPresent = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 2,
					confidenceLevelToManageChildHealthElementQuestionText);

			answersFromUIGrid = driver.findElements(By.xpath(config.getGMAnswersFromGridFromActivityXpath()));

			Assert.assertEquals(answersFromUIGrid.get(0).getText().trim().contains(answerFromExcel), true);
			Assert.assertEquals(answersFromUIGrid.get(1).getText().trim().substring(0, 10),
					GlobalValues.GeneralMedicalSubmit_Date);
			Assert.assertEquals(answersFromUIGrid.get(2).getText().trim(), enteredBy);
			Assert.assertEquals(answersFromUIGrid.get(3).getText().trim(), programName);
			Assert.assertEquals(answersFromUIGrid.get(4).getText().trim(), sourceFromExcel);

			Log.info("**********Verified the data  in the history grid**********"
					+ confidenceLevelToManageChildHealthElementQuestionText);

			hideHistoryElement = driver.findElement(By.xpath(config.gethideHistoryElementXpath()));
			hideHistoryElement.click();
			Thread.sleep(1000);

			// ********** Validate Question - Child's Current Health
			// Concerns**********
			driver.findElement(By.xpath(config.getshowHistoryElementXpath().replace("instanceNumber", "8"))).click();
			Thread.sleep(GlobalValues.Sleep_wait_time);

			validateColumnsOfWithoutDOSGrid();

			WebElement childCurrentHealthConcernsElement = driver
					.findElement(By.xpath(config.getGMQuestionLabelXpathFromActivityXpath().replace("instance", "5")));
			String childCurrentHealthConcernsElementQuestionText = childCurrentHealthConcernsElement.getText().trim();
			answerFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 3,
					childCurrentHealthConcernsElementQuestionText);
			isHistoryPresent = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 2,
					childCurrentHealthConcernsElementQuestionText);
			Log.info(answerFromExcel + "-answer from excel");
			answersFromUIGrid = driver.findElements(By.xpath(config.getGMAnswersFromGridFromActivityXpath()));

			Assert.assertEquals(answersFromUIGrid.get(0).getText().trim().contains(answerFromExcel), true);
			Assert.assertEquals(answersFromUIGrid.get(1).getText().trim().substring(0, 10),
					GlobalValues.GeneralMedicalSubmit_Date);
			Assert.assertEquals(answersFromUIGrid.get(2).getText().trim(), enteredBy);
			Assert.assertEquals(answersFromUIGrid.get(3).getText().trim(), programName);
			Assert.assertEquals(answersFromUIGrid.get(4).getText().trim(), sourceFromExcel);

			Log.info("**********Verified the data  in the history grid**********"
					+ childCurrentHealthConcernsElementQuestionText);

			hideHistoryElement = driver.findElement(By.xpath(config.gethideHistoryElementXpath()));
			hideHistoryElement.click();
			Thread.sleep(1000);

			// ********** Validate Question - Good Understanding of Child's
			// Health Conditions **********
			driver.findElement(By.xpath(config.getshowHistoryElementXpath().replace("instanceNumber", "9"))).click();
			Thread.sleep(GlobalValues.Sleep_wait_time);

			validateColumnsOfWithoutDOSGrid();

			WebElement goodUnderstandingOfChildHealthConditionsElement = driver
					.findElement(By.xpath(config.getGMQuestionLabelXpathFromActivityXpath().replace("instance", "5")));
			String goodUnderstandingOfChildHealthConditionsElementQuestionText = goodUnderstandingOfChildHealthConditionsElement
					.getText().trim();
			answerFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 3,
					goodUnderstandingOfChildHealthConditionsElementQuestionText);
			isHistoryPresent = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 2,
					goodUnderstandingOfChildHealthConditionsElementQuestionText);

			if (answerFromExcel.contains("No") || answerFromExcel.contains("Unsure")
					|| answerFromExcel.contains("Other")) {
				answerFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 3,
						"Please explain health condition(s)");
				answersFromUIGrid = driver.findElements(By.xpath(config.getGMAnswersFromGridFromActivityXpath()));

				Assert.assertEquals(answersFromUIGrid.get(0).getText().trim().contains(answerFromExcel), true);
				Assert.assertEquals(answersFromUIGrid.get(1).getText().trim().substring(0, 10),
						GlobalValues.GeneralMedicalSubmit_Date);
				Assert.assertEquals(answersFromUIGrid.get(2).getText().trim(), enteredBy);
				Assert.assertEquals(answersFromUIGrid.get(3).getText().trim(), programName);
				Assert.assertEquals(answersFromUIGrid.get(4).getText().trim(), sourceFromExcel);

				Log.info("**********Verified the data  in the history grid**********"
						+ goodUnderstandingOfChildHealthConditionsElementQuestionText);
			} else if (answerFromExcel.contains("Yes")) {
				Log.info("History is not captured for 'Yes'. It`s captured for health conditions");
			}
			hideHistoryElement = driver.findElement(By.xpath(config.gethideHistoryElementXpath()));
			hideHistoryElement.click();
			Thread.sleep(1000);

			// ********** Validate Question - Child Vision Impairment***********
			driver.findElement(By.xpath(config.getshowHistoryElementXpath().replace("instanceNumber", "10"))).click();
			Thread.sleep(GlobalValues.Sleep_wait_time);

			validateColumnsOfWithoutDOSGrid();

			WebElement childVisionImpairmentElement = driver
					.findElement(By.xpath(config.getGMQuestionLabelXpathFromActivityXpath().replace("instance", "5")));
			String childVisionImpairmentElementQuestionText = childVisionImpairmentElement.getText().trim();
			answerFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 3,
					childVisionImpairmentElementQuestionText);
			isHistoryPresent = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 2,
					childVisionImpairmentElementQuestionText);

			answersFromUIGrid = driver.findElements(By.xpath(config.getGMAnswersFromGridFromActivityXpath()));

			Assert.assertEquals(answersFromUIGrid.get(0).getText().trim().contains(answerFromExcel), true);
			Assert.assertEquals(answersFromUIGrid.get(1).getText().trim().substring(0, 10),
					GlobalValues.GeneralMedicalSubmit_Date);
			Assert.assertEquals(answersFromUIGrid.get(2).getText().trim(), enteredBy);
			Assert.assertEquals(answersFromUIGrid.get(3).getText().trim(), programName);
			Assert.assertEquals(answersFromUIGrid.get(4).getText().trim(), sourceFromExcel);

			Log.info("**********Verified the data  in the history grid**********"
					+ childVisionImpairmentElementQuestionText);

			hideHistoryElement = driver.findElement(By.xpath(config.gethideHistoryElementXpath()));
			hideHistoryElement.click();
			Thread.sleep(1000);

			// *********** Validate Question - Child's Hearing Impairment
			// **********
			driver.findElement(By.xpath(config.getshowHistoryElementXpath().replace("instanceNumber", "11"))).click();
			Thread.sleep(GlobalValues.Sleep_wait_time);

			validateColumnsOfWithoutDOSGrid();

			WebElement childHearingImpairmentElement = driver
					.findElement(By.xpath(config.getGMQuestionLabelXpathFromActivityXpath().replace("instance", "5")));
			String childHearingImpairmentQuestionText = childHearingImpairmentElement.getText().trim();
			answerFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 3,
					childHearingImpairmentQuestionText);
			isHistoryPresent = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 2,
					childHearingImpairmentQuestionText);

			answersFromUIGrid = driver.findElements(By.xpath(config.getGMAnswersFromGridFromActivityXpath()));

			Assert.assertEquals(answersFromUIGrid.get(0).getText().trim().contains(answerFromExcel), true);
			Assert.assertEquals(answersFromUIGrid.get(1).getText().trim().substring(0, 10),
					GlobalValues.GeneralMedicalSubmit_Date);
			Assert.assertEquals(answersFromUIGrid.get(2).getText().trim(), enteredBy);
			Assert.assertEquals(answersFromUIGrid.get(3).getText().trim(), programName);
			Assert.assertEquals(answersFromUIGrid.get(4).getText().trim(), sourceFromExcel);

			Log.info("**********Verified the data  in the history grid**********" + childHearingImpairmentQuestionText);

			hideHistoryElement = driver.findElement(By.xpath(config.gethideHistoryElementXpath()));
			hideHistoryElement.click();
			Thread.sleep(1000);

			// ********** Validate Question - Child's Allergies **********
			driver.findElement(By.xpath(config.getshowHistoryElementXpath().replace("instanceNumber", "12"))).click();
			Thread.sleep(GlobalValues.Sleep_wait_time);

			validateColumnsOfWithoutDOSGrid();

			WebElement childAllergiesElement = driver
					.findElement(By.xpath(config.getGMQuestionLabelXpathFromActivityXpath().replace("instance", "5")));
			String childAllergiesElementQuestionText = childAllergiesElement.getText().trim();
			answerFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 3,
					childAllergiesElementQuestionText);
			isHistoryPresent = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 2,
					childAllergiesElementQuestionText);

			if (answerFromExcel.contains("Yes")) {
				answerFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 3,
						"Please Explain/List the Allergy(ies)");
				answersFromUIGrid = driver.findElements(By.xpath(config.getGMAnswersFromGridFromActivityXpath()));

				Assert.assertEquals(answersFromUIGrid.get(0).getText().trim().contains(answerFromExcel), true);
				Assert.assertEquals(answersFromUIGrid.get(1).getText().trim().substring(0, 10),
						GlobalValues.GeneralMedicalSubmit_Date);
				Assert.assertEquals(answersFromUIGrid.get(2).getText().trim(), enteredBy);
				Assert.assertEquals(answersFromUIGrid.get(3).getText().trim(), programName);
				Assert.assertEquals(answersFromUIGrid.get(4).getText().trim(), sourceFromExcel);

				Log.info("**********Verified the data  in the history grid**********"
						+ childAllergiesElementQuestionText);
			} else if (answerFromExcel.contains("No")) {
				Log.info("History is not captured for 'No'.History is captured for Allergies");
			}
			hideHistoryElement = driver.findElement(By.xpath(config.gethideHistoryElementXpath()));
			hideHistoryElement.click();
			Thread.sleep(1000);

		} catch (Exception e) {
			e.printStackTrace();
			Log.info(e);
		}
	}

	public void validateColumnsOfWithoutDOSGrid() throws Exception {
		Log = Logger.getLogger("GeneralMedicalSubActivity.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		Global_Method gm = new Global_Method(driver);
		DashboardFunctions df = new DashboardFunctions(driver);

		try {
			String gridHeaderText = wait
					.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath(config.getGMgridHeaderTextFromActivityXpath())))
					.getText();
			Log.info("Grid Header is " + gridHeaderText);
			Assert.assertEquals(gridHeaderText.trim(), "History", "Grid header not matching");

			List<WebElement> actualColumnHeaders = driver
					.findElements(By.xpath(config.getGMactualColumnHeadersFromActivityXpath()));
			Assert.assertEquals(actualColumnHeaders.get(0).getText().trim(), "Answer");
			Assert.assertEquals(actualColumnHeaders.get(1).getText().trim(), "Date Entered");
			Assert.assertEquals(actualColumnHeaders.get(2).getText().trim(), "Entered By");
			Assert.assertEquals(actualColumnHeaders.get(3).getText().trim(), "Program");
			Assert.assertEquals(actualColumnHeaders.get(4).getText().trim(), "Source");
			Log.info("Grid Header has been verified");
			Log.info("Getting the list of Column names for History Grid");
			Thread.sleep(GlobalValues.Sleep_wait_time);
			Log.info("**********History Grid Column Names has been verified for NON DOS**********");

		} catch (Exception e) {
			e.printStackTrace();
			Log.info(e);
		}
	}

	public void validateColumnsOfWithDOSGrid() throws Exception {
		Log = Logger.getLogger("GeneralMedicalSubActivity.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		Global_Method gm = new Global_Method(driver);

		try {

			String gridHeaderText = wait
					.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath(config.getGMgridHeaderTextFromActivityXpath())))
					.getText();
			Log.info("Grid Header is " + gridHeaderText);
			Assert.assertEquals(gridHeaderText.trim(), "History", "Grid header not matching");
			List<WebElement> actualColumnHeaders = driver
					.findElements(By.xpath(config.getGMactualColumnHeadersFromActivityXpath()));
			Assert.assertEquals(actualColumnHeaders.get(0).getText().trim(), "Answer");
			Assert.assertEquals(actualColumnHeaders.get(1).getText().trim(), "Date Entered");
			Assert.assertEquals(actualColumnHeaders.get(2).getText().trim(), "Entered By");
			Assert.assertEquals(actualColumnHeaders.get(3).getText().trim(), "Date of Service");
			Assert.assertEquals(actualColumnHeaders.get(4).getText().trim(), "Program");
			Assert.assertEquals(actualColumnHeaders.get(5).getText().trim(), "Source");
			Log.info("Grid Header has been verified");
			Log.info("Getting the list of Column names for History Grid");
			Thread.sleep(GlobalValues.Sleep_wait_time);
			Log.info("**********History Grid Column Names has been verified for DOS**********");

		} catch (Exception e) {
			e.printStackTrace();
			Log.info(e);
		}
	}

}
