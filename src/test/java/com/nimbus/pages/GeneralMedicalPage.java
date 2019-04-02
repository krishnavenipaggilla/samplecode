package com.nimbus.pages;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utility.ConfigReader;

public class GeneralMedicalPage {
	ConfigReader config = new ConfigReader();
	WebDriver driver;
	Logger Log;

	public GeneralMedicalPage(WebDriver ldriver) {
		this.driver = ldriver;
	}

	public GeneralMedicalPage(Logger lLog) {
		this.Log = lLog;
	}

	public void verifyGeneralMedicalPage() throws Exception {
		Log = Logger.getLogger("GeneralMedicalPage.class");
		PropertyConfigurator.configure("log4j.properties");
		Global_Method gm = new Global_Method(driver);
		boolean passed = true;
		
//		GlobalValues.UserNameOnUI = driver.findElement(By.xpath("//label[contains(text(),'Case Owner')]//parent::div//following-sibling::div//span")).getText().trim();
		
//		try {
//			fillGeneralMedicalPage();
//		} catch (Exception e) {
//			e.printStackTrace();
//			passed = false;
//		}

		try {
			verifyGramsToLbOzConversion("Birth Weight");
		} catch (Exception e) {
			e.printStackTrace();
			passed = false;
		}

		 try{
			 verifyGramsToLbOzConversion("Current Weight");
		 } catch (Exception e) {
			 e.printStackTrace();
			 passed = false;
		 }

//		try {
//			verifyCurrentGestationalAge();
//		} catch (Exception e) {
//			e.printStackTrace();
//			passed = false;
//		}
		try {
			highlightFieldBasedOnValues();
		} catch (Exception e) {
			e.printStackTrace();
			passed = false;
		}

		try {
			verifyDisplayConfirmationOnSubmit();
		} catch (Exception e) {
			e.printStackTrace();
			passed = false;
		}

		try {
			verifyDisplayConfirmationOnBack();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			viewMetadataForChartUpdates2();

		} catch (Exception e) {
			e.printStackTrace();
			passed = false;
		}
		
		try {
			verifyDisplayConfirmationOnSubmit();
		} catch (Exception e) {
			e.printStackTrace();
			passed = false;
		}

		try {
			verifyDisplayConfirmationOnBack();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (passed == true) {
			gm.Reports("NIM-11366", "Pass");
		} else {
			gm.Reports("NIM-11366", "Fail");
		}
	}

	public void verifyGeneralMedicalPageHistory() throws Exception {
		Log = Logger.getLogger("GeneralMedicalPage.class");
		PropertyConfigurator.configure("log4j.properties");
		Global_Method gm = new Global_Method(driver);
		LeftNavigationLink lnk = new LeftNavigationLink(driver);
		boolean passed = true;
		//lnk.clickHealthChart();
		
		try {
			fillGeneralMedicalPage();
		} catch (Exception e) {
			e.printStackTrace();
			passed = false;
		}
		
		Thread.sleep(GlobalValues.Sleep_wait_time);
//		try{
//			verifyShowHideHistory();
//		} catch (Exception e) {
//			e.printStackTrace();
//			Log.info("Verified the History grid for DOS field questions");
//		}
		try{
			viewHistoryGrid();
			Log.info("**********VERIFIED THE HISTORY GRID FOR ALL APPLICABLE QUESTIONS IN GENERAL MEDICAL**********");
		} catch (Exception e) {
			e.printStackTrace();
			passed = false;
		}
		if (passed == true) {
			gm.Reports("NIM-12059", "Pass");
			Log.info("**********Verified the History for General Medical**********");
		} else {
			gm.Reports("NIM-12059", "Fail");
			Log.info("!!!!!!!!!!General Medical History verification Failed!!!!!!!!!!");
		}
	}

	public void fillGeneralMedicalPage() throws Exception {
		Log = Logger.getLogger("GeneralMedicalPage.class");
		PropertyConfigurator.configure("log4j.properties");
		LeftNavigationLink lnk = new LeftNavigationLink(driver);
		AnnualAssessment a = new AnnualAssessment(driver);

		GlobalValues.UserNameOnUI = driver.findElement(By.xpath("//div[@class ='userName']//child::nm-value")).getText().trim();
		
		try {
			
			lnk.clickGeneralMedical();
			Thread.sleep(GlobalValues.Sleep_wait_time);
			// Fill in the HealthChart form using Excel sheet
			a.annual_assessment("AnnualAssessments_CMDM", "AddGeneralMedical", 3);
			Log.info("Filled and Submitted the General Medical Form");

			//DateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			Date date = new Date();
			// df.setTimeZone(TimeZone.getTimeZone("UTC"));
			GlobalValues.GeneralMedicalSubmit_Date = df.format(date);
			Log.info(GlobalValues.GeneralMedicalSubmit_Date);

		} catch (Exception e) {
			e.printStackTrace();
			Log.info(e);
		}
	}

	public void verifyGramsToLbOzConversion(String fieldInGrams) throws Exception {
		Log = Logger.getLogger("GeneralMedicalPage.class");
		PropertyConfigurator.configure("log4j.properties");

		LeftNavigationLink lnk = new LeftNavigationLink(driver);

		double weightInKgs = 0.0;
		double weightInLbFromUI = 0.0;
		double weightInOzFromUI = 0.0;

		try {
			lnk.clickGeneralMedical();
			// 1 gram = 0 lbs 0.035274 oz

			String currentWeightInGmEnteredFromUI = driver
					.findElement(By.xpath(config.getCurrentWeightInGmEnteredFromUIXpath())).getAttribute("textContent");
			Double currentWeightInGmEnteredFromUIInDouble = Double.parseDouble(currentWeightInGmEnteredFromUI);

			String birthWeightInGmEnteredFromUI = driver
					.findElement(By.xpath(config.getbirthWeightInGmEnteredFromUIXpath())).getAttribute("textContent");
			Double birthWeightInGmEnteredFromUIInDouble = Double.parseDouble(birthWeightInGmEnteredFromUI);
			if (fieldInGrams.equals("Current Weight")) {

				weightInKgs = currentWeightInGmEnteredFromUIInDouble / 1000;
			} else if (fieldInGrams.equals("Birth Weight")) {

				weightInKgs = birthWeightInGmEnteredFromUIInDouble / 1000;
			}
			double totaleWightInPounds = weightInKgs * 2.2046226218;
			double weightInPounds = (double) ((int) (totaleWightInPounds));

			double weightInOunces = (totaleWightInPounds - weightInPounds) * 16;
			// Set the weight only if the rounded of previous weight and new
			// weight is same
			Double newRoundedWtInPounds = roundDecimalToPlaces(weightInPounds, 2).doubleValue();
			Double newRoundedWightInOz = roundDecimalToPlaces(weightInOunces, 2).doubleValue();

			WebElement lbFromUIForBirthWeightElement = driver
					.findElement(By.xpath(config.getLbFromUIForBirthWeightElementXpath()));
			String lbFromUIForBirthWeightText = lbFromUIForBirthWeightElement.getText().toString().trim();

			WebElement oZFromUIForBirthWeightElement = driver
					.findElement(By.xpath(config.getOZFromUIForBirthWeightElementXpath()));
			String oZFromUIForBirthWeightText = oZFromUIForBirthWeightElement.getText().toString().trim();

			WebElement lbFromUIForCurrentWeightElement = driver
					.findElement(By.xpath(config.getLbFromUIForCurrentWeightElementXpath()));
			String lbFromUIForCurrentWeightText = lbFromUIForCurrentWeightElement.getText().toString().trim();

			WebElement oZFromUIForCurrentWeightElement = driver
					.findElement(By.xpath(config.getOZFromUIForCurrentWeightElementXpath()));
			String oZFromUIForCurrentWeightText = oZFromUIForCurrentWeightElement.getText().toString().trim();

			if (fieldInGrams.equals("Current Weight")) {

				weightInLbFromUI = Double.parseDouble(lbFromUIForCurrentWeightText);
				weightInOzFromUI = Double.parseDouble(oZFromUIForCurrentWeightText);
			} else if (fieldInGrams.equals("Birth Weight")) {

				weightInLbFromUI = Double.parseDouble(lbFromUIForBirthWeightText);
				weightInOzFromUI = Double.parseDouble(oZFromUIForBirthWeightText);
			}

			Assert.assertEquals(weightInLbFromUI, newRoundedWtInPounds,
					"Conversion to Weight in Pounds/Lbs is not matching");
			Assert.assertEquals(weightInOzFromUI, newRoundedWightInOz, "Conversion to Weight in Oz is not matching");
			Log.info("Verified the Conversion of Birth/Current Weight from Grams to Lb and OZ");

		} catch (Exception e) {
			e.printStackTrace();
			Log.info(e);
		}

	}

	public BigDecimal roundDecimalToPlaces(double value, int places) {
		BigDecimal bd = new BigDecimal(Double.toString(value));
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		return bd;
	}

	public void verifyCurrentGestationalAge() throws Exception {
		Log = Logger.getLogger("GeneralMedicalPage.class");
		PropertyConfigurator.configure("log4j.properties");

		LeftNavigationLink lnk = new LeftNavigationLink(driver);

		try {
			
			lnk.clickGeneralMedical();

			// Current gestational age = gestational age at birth + (Systemdate-DOB)+1

			WebElement weeksOfGestationalAgeOfBirthElement = driver
					.findElement(By.xpath(config.getWeeksOfGestationalAgeOfBirthElementXpath()));
			String weeksOfGestationalAgeOfBirthText = weeksOfGestationalAgeOfBirthElement.getAttribute("textContent");

			WebElement daysOfGestationalAgeOfBirthElement = driver
					.findElement(By.xpath(config.getDaysOfGestationalAgeOfBirthElementXpath()));
			String daysOfGestationalAgeOfBirthText = daysOfGestationalAgeOfBirthElement.getAttribute("textContent");

			WebElement dobFromCaseBannerElement = driver
					.findElement(By.xpath(config.getDobFromCaseBannerElementXpath()));
			String dobFromCaseBannerElementText = dobFromCaseBannerElement.getText().toString().trim();

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			LocalDate dob = LocalDate.parse(dobFromCaseBannerElementText, dtf);

			int totalGestationalAgeInDays = Integer.parseInt(weeksOfGestationalAgeOfBirthText) * 7
					+ Integer.parseInt(daysOfGestationalAgeOfBirthText);
			int totalAgeDifferenceInDays = (int) ChronoUnit.DAYS.between(dob, LocalDate.now());
			// Converting to weeks and Days
			int ageDifferenceInWeeks = (int) totalAgeDifferenceInDays / 7;
			int ageDifferenceInDays = totalAgeDifferenceInDays - (ageDifferenceInWeeks * 7);
			int totalWeeks = ageDifferenceInWeeks + Integer.parseInt(weeksOfGestationalAgeOfBirthText);
			int totalDays = ageDifferenceInDays + Integer.parseInt(daysOfGestationalAgeOfBirthText) + 1;
			// 1 is added to account for current Day
			// If @totalDays > 7 then convert it to weeks and Days and add to
			// @totalWeeks and @totalDays
			if (totalDays > 7) {
				int additionalWeeks = (int) (totalDays / 7);
				totalDays = totalDays - (additionalWeeks * 7);
				totalWeeks = totalWeeks + additionalWeeks;

			}
			int totalCurrentAgeInDays = totalWeeks * 7 + totalDays;

			WebElement weeksForCurrentGestAgeFromUIElement = driver
					.findElement(By.xpath(config.getWeeksForCurrentGestAgeFromUIElementXpath()));
			String weeksForCurrentGestAgeFromUIText = weeksForCurrentGestAgeFromUIElement.getText().trim().toString();

			WebElement DaysForCurrentGestAgeFromUIElement = driver
					.findElement(By.xpath(config.getDaysForCurrentGestAgeFromUIElementXpath()));
			String DaysForCurrentGestAgeFromUIText = DaysForCurrentGestAgeFromUIElement.getText().trim().toString();

			Assert.assertEquals(Integer.parseInt(weeksForCurrentGestAgeFromUIText), totalWeeks,
					"Conversion to Weeks is not matching");
			Assert.assertEquals(Integer.parseInt(DaysForCurrentGestAgeFromUIText), totalDays,
					"Conversion to Days is not matching");

			Log.info(
					"Verified the Current Gestational Age Calculation from Gestation Age at Birth - [Current gestational age = gestational age at birth + (System date-DOB)+1]");
		} catch (Exception e) {

			e.printStackTrace();
			Log.info(e);
		}

	}

	public void editGeneralMedicalPage() throws Exception {
		Log = Logger.getLogger("GeneralMedicalPage.class");
		PropertyConfigurator.configure("log4j.properties");

		LeftNavigationLink lnk = new LeftNavigationLink(driver);
		AnnualAssessment a = new AnnualAssessment(driver);

		try {
			lnk.clickGeneralMedical();
			Thread.sleep(GlobalValues.Sleep_wait_time);
			// Fill in the HealthChart form using Excel sheet
			a.annual_assessment("AnnualAssessments_CMDM", "EditGeneralMedical", 2);
			Log.info("Edited and Submitted the General Medical Form");

		} catch (Exception e) {
			e.printStackTrace();
			Log.info(e);
		}

	}

	public void highlightFieldBasedOnValues() throws Exception {
		Log = Logger.getLogger("GeneralMedicalPage.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		Global_Method gm = new Global_Method(driver);
		LeftNavigationLink lnk = new LeftNavigationLink(driver);

		String reqWarningMessage = "CM: Attention Required";

		try {
			
			lnk.clickGeneralMedical();
			Thread.sleep(GlobalValues.Sleep_wait_time);
			// get the value from UI instead of excel
			String childGrowthTrackingValueFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical",
					3, "Child's Growth Tracking (According to CDC Growth Charts)");

			String erVisitRNOutreachValueFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 3,
					"ER visits since last NICU RN outreach");

			String hospitalReadmissionsRNOutreachValueFromExcel = gm.readFromExcel("AnnualAssessments_CMDM",
					"AddGeneralMedical", 3, "Hospital Readmissions since last NICU RN outreach");

			String childCurrentHealthValueFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 3,
					"Child's Current Health Concerns");

			String goodUnderstandingOfChildHealthConditionValueFromExcel = gm.readFromExcel("AnnualAssessments_CMDM",
					"AddGeneralMedical", 3, "Good Understanding of Child's Health Conditions");

			String childVisionImpairementValueFromExcel = gm.readFromExcel("AnnualAssessments_CMDM",
					"AddGeneralMedical", 3, "Child's Vision Impairement");

			String childHearingImpairementValueFromExcel = gm.readFromExcel("AnnualAssessments_CMDM",
					"AddGeneralMedical", 3, "Child's Hearing Impairement");

			String childAllergiesValueFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 3,
					"Child's Allergies");

			if (childGrowthTrackingValueFromExcel.contains("No")) {

				WebElement warningMessageElementForChildGrowthTracking = wait
						.until(ExpectedConditions.visibilityOfElementLocated(
								By.xpath(config.getWarningMessageElementForChildGrowthTrackingXpath())));
				warningMessageElementForChildGrowthTracking.getText();
				Assert.assertEquals(warningMessageElementForChildGrowthTracking.getText(), reqWarningMessage,
						"Warning messages are not getting displayed");
				Log.info(
						"Verified for the presence of the Warning Message for Child's Growth Tracking (According to CDC Growth Charts) When 'No' is selected- "
								+ warningMessageElementForChildGrowthTracking.getText());
			}

			if (erVisitRNOutreachValueFromExcel.contains("Yes")) {
				WebElement warningMessageElementForERVisitSinceLastNICURN = wait
						.until(ExpectedConditions.visibilityOfElementLocated(
								By.xpath(config.getWarningMessageElementForERVisitSinceLastNICURNXpath())));
				warningMessageElementForERVisitSinceLastNICURN.getText();
				Assert.assertEquals(warningMessageElementForERVisitSinceLastNICURN.getText(), reqWarningMessage,
						"Warning messages are not getting displayed");
				Log.info(
						"Verified for the presence of the Warning Message for ER visit(s) since last NICU RN outreach When 'Yes' is selected- "
								+ warningMessageElementForERVisitSinceLastNICURN.getText());
			}

			if (hospitalReadmissionsRNOutreachValueFromExcel.contains("Yes")) {
				WebElement warningMessageElementForHospReadmission = wait
						.until(ExpectedConditions.visibilityOfElementLocated(
								By.xpath(config.getWarningMessageElementForHospReadmissionXpath())));
				warningMessageElementForHospReadmission.getText();
				Assert.assertEquals(warningMessageElementForHospReadmission.getText(), reqWarningMessage,
						"Warning messages are not getting displayed");

				Log.info(
						"Verified for the presence of the Warning Message for Hospital Readmissions since last NICU RN outreach When 'Yes' is selected - "
								+ warningMessageElementForHospReadmission.getText());
			}
			if (childCurrentHealthValueFromExcel.equals("Medical/Condition")
					|| childCurrentHealthValueFromExcel.equals("Medication")
					|| childCurrentHealthValueFromExcel.equals("Utilization/Monitoring")
					|| childCurrentHealthValueFromExcel.equals("Psychosocial")
					|| childCurrentHealthValueFromExcel.equals("Lifestyle")) {
				WebElement warningMessageElementForChildCurrentHealthConcerns = wait
						.until(ExpectedConditions.visibilityOfElementLocated(
								By.xpath(config.getWarningMessageElementForChildCurrentHealthConcernsXpath())));
				warningMessageElementForChildCurrentHealthConcerns.getText();
				Assert.assertEquals(warningMessageElementForChildCurrentHealthConcerns.getText(), reqWarningMessage,
						"Warning messages are not getting displayed");
				Log.info(
						"Verified for the presence of the Warning Message for Child's Current Health Concerns When Medical/Condition,Medication,Utilization/Monitoring,Psychosocial or Lifestyle is selected - "
								+ warningMessageElementForChildCurrentHealthConcerns.getText());
			}
			if (childVisionImpairementValueFromExcel.contains("Vision Correction (glasses, surgery)")
					|| childVisionImpairementValueFromExcel.contains("Legally blind")
					|| childVisionImpairementValueFromExcel.contains("Impaired Vision - Not Corrected")) {
				WebElement warningMessageElementForChildVisionImpairment = wait
						.until(ExpectedConditions.visibilityOfElementLocated(
								By.xpath(config.getWarningMessageElementForChildVisionImpairmentXpath())));
				warningMessageElementForChildVisionImpairment.getText();
				Assert.assertEquals(warningMessageElementForChildVisionImpairment.getText(), reqWarningMessage,
						"Warning messages are not getting displayed");
				Log.info(
						"Verified for the presence of the Warning Message for Child's Vision Impairement When  Vision Correction (glasses, surgery),Legally blind,Impaired Vision - Not Corrected is selected - "
								+ warningMessageElementForChildVisionImpairment.getText());
			}

			if (childHearingImpairementValueFromExcel.contains("Hearing Corrected (hearing aids)")
					|| childHearingImpairementValueFromExcel.contains("Deafness Diagnosed")
					|| childHearingImpairementValueFromExcel.contains("Hearing Impairment - Not Corrected")) {
				WebElement warningMessageElementForChildHearingImpairment = wait
						.until(ExpectedConditions.visibilityOfElementLocated(
								By.xpath(config.getWarningMessageElementForChildHearingImpairmentXpath())));
				warningMessageElementForChildHearingImpairment.getText();
				Assert.assertEquals(warningMessageElementForChildHearingImpairment.getText(), reqWarningMessage,
						"Warning messages are not getting displayed");
				Log.info(
						"Verified for the presence of the Warning Message for Child's Hearing Impairement When  Hearing Corrected (hearing aids),Deafness Diagnosed,Hearing Impairment - Not Corrected is selected - "
								+ warningMessageElementForChildHearingImpairment.getText());
			}

			if (childAllergiesValueFromExcel.contains("Yes")) {
				WebElement warningMessageElementForChildAllergies = wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(config.getWarningMessageElementForChildAllergies())));
				warningMessageElementForChildAllergies.getText();
				Assert.assertEquals(warningMessageElementForChildAllergies.getText(), reqWarningMessage,
						"Warning messages are not getting displayed");
				Log.info(
						"Verified for the presence of the Warning Message for Child's Allergies When 'Yes' is selected - "
								+ warningMessageElementForChildAllergies.getText());
			}

			if (goodUnderstandingOfChildHealthConditionValueFromExcel.contains("No")
					|| goodUnderstandingOfChildHealthConditionValueFromExcel.contains("Unsure")) {
				WebElement warningMessageElementForChildHealthConditions = wait
						.until(ExpectedConditions.visibilityOfElementLocated(
								By.xpath(config.getWarningMessageElementForChildHealthConditions())));
				warningMessageElementForChildHealthConditions.getText();
				Assert.assertEquals(warningMessageElementForChildHealthConditions.getText(), reqWarningMessage,
						"Warning messages are not getting displayed");
				Log.info(
						"Verified for the presence of the Warning Message for Good Understanding of Child's Health Conditions When 'No' or 'Unsure' is selected - "
								+ warningMessageElementForChildHealthConditions.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.info(e);
		}
	}

	public void verifyDisplayConfirmationOnSubmit() throws Exception {
		Log = Logger.getLogger("GeneralMedicalPage.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		Global_Method gm = new Global_Method(driver);
		LeftNavigationLink lnk = new LeftNavigationLink(driver);

	
		lnk.clickGeneralMedical();
		driver.findElement(By.xpath(config.getGMInputXpath())).clear();
		Thread.sleep(2000);
		driver.findElement(By.xpath(config.getGMInputXpath())).sendKeys("210");
		Thread.sleep(2000);

		gm.clickSubmit(1);
		Thread.sleep(3000);
		WebElement updatesSavedElement = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getUpdatesSavedElementXpath())));
		String updatesSavedText = updatesSavedElement.getText();
		Thread.sleep(2000);

		Assert.assertEquals("Updates Saved", updatesSavedText, "Confirmation Message differs");
		Log.info("**********Verified the display of Confirmation - " + updatesSavedText);
	}

	public void verifyDisplayConfirmationOnBack() throws Exception {
		Log = Logger.getLogger("GeneralMedicalPage.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		Global_Method gm = new Global_Method(driver);
		LeftNavigationLink lnk = new LeftNavigationLink(driver);

		
		lnk.clickGeneralMedical();
		driver.findElement(By.xpath(config.getGMInputXpath())).clear();
		driver.findElement(By.xpath(config.getGMInputXpath())).sendKeys("210");

		gm.clickBack(1);
		Thread.sleep(2000);
		WebElement updatesCancelledElement = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getUpdatesCancelledElementXpath())));
		String updatesCancelledText = updatesCancelledElement.getText();
		Thread.sleep(2000);
		Assert.assertEquals("Updates Cancelled", updatesCancelledText, "Confirmation Message differs");
		Log.info("**********Verified the display of Confirmation - " + updatesCancelledText);
	}

	
	public void verifyShowHideHistory() throws Exception {
		Log = Logger.getLogger("GeneralMedicalPage.class");
		PropertyConfigurator.configure("log4j.properties");

		List<WebElement> showHistoryElements = driver
				.findElements(By.xpath(config.getshowHistoryElementsXpath()));
		for (WebElement showHistoryElement : showHistoryElements) {
			showHistoryElement.click();
			Thread.sleep(1000);

			List<WebElement> tableHeaders = driver.findElements(By.xpath(config.gettableHeadersXpath()));
			if (tableHeaders.size() == 6) {
				viewHistoryGridWithDOSLoop();
				numberOfRowsInGridForpagination("History", 5);
			}
//			} else {
//				viewHistoryGridWithoutDOS();
//				numberOfRowsInGridForpagination("History", 5);
//			}
			Thread.sleep(5000);
			WebElement hideHistoryElement = driver.findElement(By.xpath(config.gethideHistoryElementXpath()));
			hideHistoryElement.click();
			Thread.sleep(3000);
		}
	}

	public void viewHistoryGridWithDOSLoop() throws Exception {
		Log = Logger.getLogger("GeneralMedicalPage.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		Global_Method gm = new Global_Method(driver);

		try {

			
			String enteredBy = GlobalValues.UserNameOnUI;

			String programName = "";
			if(GlobalValues.DBProgramNameActiveStatus.contains("true") && GlobalValues.DBProgramNamePrimaryStatus.contains("true")){
				programName = GlobalValues.DBProgramName;
			}
			String programNameFromBanner = driver
					.findElement(By.xpath(config.getProgramNameFromBannerXpath()))
					.getText().toString().trim();
			String sourceFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 3,
					"If applicable, please select source of current changes").trim().toString();

			String gridHeaderText = wait
					.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath(config.getgridHeaderTextXpath())))
					.getText();
			Log.info("Grid Header is " + gridHeaderText);
			Assert.assertEquals(gridHeaderText.trim(), "History", "Grid header not matching");
			List<WebElement> actualColumnHeaders = driver.findElements(By.xpath(
					config.getactualColumnHeadersXpath()));
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

			List<WebElement> lastModifiedElements = driver.findElements(By.xpath(
					config.getlastModifiedElementsXpath()));
			WebElement lastModifiedElement = lastModifiedElements.get(lastModifiedElements.size() - 1);
			WebElement questionElement = lastModifiedElement.findElement(By.xpath(
					config.getquestionElementXpath()));

			String questionFromUI = questionElement.getText();
			List<WebElement> sameQuestionList = driver
					.findElements(By.xpath("//label[contains(text(),'" + questionFromUI + "')]"));
			if (sameQuestionList.size() > 1) {
				for (int k = 0; k < sameQuestionList.size(); k++) {
					if (sameQuestionList.get(k).equals(questionElement)) {
						questionFromUI = questionFromUI.trim() + "|" + (k + 1);
						break;
					}
				}
			}
			String answerFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 3, questionFromUI);
			int dateOfServiceFromExcelRowNum = gm.getRowNumFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 3,
					questionFromUI);
			String dateOfServiceFromExcel = null;
			for (int i = dateOfServiceFromExcelRowNum + 1; i < dateOfServiceFromExcelRowNum + 5; i++) {
				String cellValueFromExcel = gm.getCellValueFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", i,
						0);
				if (cellValueFromExcel.contains("Date of Service")) {
					dateOfServiceFromExcel = gm.getCellValueFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", i,
							3);
					break;
				}
			}

			List<WebElement> answersFromUIGrid = driver
					.findElements(By.xpath(config.getanswersFromUIGridXpath()));

			Assert.assertEquals(answersFromUIGrid.get(0).getText().trim().contains(answerFromExcel), true);
			Assert.assertEquals(answersFromUIGrid.get(1).getText().trim(), GlobalValues.GeneralMedicalSubmit_Date);
			Assert.assertEquals(answersFromUIGrid.get(2).getText().trim(), enteredBy);
			Assert.assertEquals(answersFromUIGrid.get(3).getText().trim(), dateOfServiceFromExcel);
			Assert.assertEquals(answersFromUIGrid.get(4).getText().trim(), programName);
			Assert.assertEquals(answersFromUIGrid.get(5).getText().trim(), sourceFromExcel);

			Log.info("**********Verified the data  in the history grid**********" + questionFromUI);

		} catch (Exception e) {
			e.printStackTrace();
			Log.info(e);
		}
	}

	
	public void numberOfRowsInGridForpagination(String gridName, int requiredRowCount) throws Exception {
		Log = Logger.getLogger("GeneralMedicalPage.class");
		PropertyConfigurator.configure("log4j.properties");

		try {
			WebElement gridTitleElement = driver
					.findElement(By.xpath(config.getGMGridTitleElementXpath()));
			String gridTitle = gridTitleElement.getText();
			Log.info(gridTitle);
			Assert.assertEquals(gridTitle, gridName, "Grid Header/Title is not matching");
			List<WebElement> gridRowsCount = driver
					.findElements(By.xpath(config.getGMGridRowsCountXpath()));
			int rowCount = gridRowsCount.size();

			if (rowCount > requiredRowCount) {
				Log.info("Number of rows for pagination is more than required");
			} else {
				Log.info("Number of rows for pagination is as required");
				Log.info("**********Verified pagination for History Grid in General Medical**********");
			}

		} catch (Exception e) {

			Log.info(e + " Grid Title is not matching");
		}

	}

	public void viewMetadataForChartUpdates2() throws Exception {
		Log = Logger.getLogger("GeneralMedicalPage.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		LeftNavigationLink lnk = new LeftNavigationLink(driver);

		try {
			
			lnk.clickGeneralMedical();
			editGeneralMedicalPage();
			Log.info("**********successfully Edited the form**********");

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			// LocalDate localDate = LocalDate.parse(date, formatter);
			LocalDate date1 = LocalDate.now();
			String dateFromSystem = date1.format(formatter);

			lnk.clickGeneralMedical();
			
			String programName = "";
			
			if(GlobalValues.DBProgramNameActiveStatus.contains("true") && GlobalValues.DBProgramNamePrimaryStatus.contains("true")){
				programName = GlobalValues.DBProgramName;
			}
			String programNameFromBanner = driver
					.findElement(By.xpath(config.getProgramNameFromBannerXpath()))
					.getText().toString().trim();

			File sour = new File("./TestData/AnnualAssessments_CMDM.xlsx");
			FileInputStream fiss = new FileInputStream(sour);
			XSSFWorkbook wb = new XSSFWorkbook(fiss);
			XSSFSheet Sheet1 = wb.getSheet("EditGeneralMedical2");
			int row = Sheet1.getLastRowNum();
			for (int i = 1; i <= row; i++) {
				String metadataFromUI = "";
				String metadataToVerify = "";
				boolean hasDateOfService = false;
				Cell cellAnswer = Sheet1.getRow(i).getCell(2);
				if (cellAnswer == null)
					continue;
				else if (Sheet1.getRow(i).getCell(0).getStringCellValue().contains("Date of Service"))
					continue;

				cellAnswer.setCellType(Cell.CELL_TYPE_STRING);

				if (Sheet1.getRow(i).getCell(0).getStringCellValue()
						.contains("If applicable, please select source of current changes")) {
					break;
				}

				if (cellAnswer != null && StringUtils.isNotBlank(cellAnswer.getStringCellValue())) {
					String QuestionText = Sheet1.getRow(i).getCell(0).getStringCellValue().toString();
					String Question = null;
					String instance = null;

					if (QuestionText.contains("|")) {
						Question = QuestionText.split("\\|")[0];
						instance = QuestionText.split("\\|")[1];
					} else {
						Question = QuestionText;
						instance = "1";
					}

					if (Sheet1.getRow(i).getCell(1).getStringCellValue().toString().contains("radio")) {
						metadataFromUI = wait
								.until(ExpectedConditions.presenceOfElementLocated(
										By.xpath("(//legend[contains(text(),\"" + Question + "\")])[" + instance
												+ "]//ancestor::nm-input-radio//following-sibling::nm-message//span[contains(text(),'Last Modified:')]")))
								.getText();
					}else if (Sheet1.getRow(i).getCell(1).getStringCellValue().toString().contains("drop")) {
						metadataFromUI = wait
								.until(ExpectedConditions.presenceOfElementLocated(
										By.xpath("(//label[contains(text(),\"" + Question + "\")])[" + instance
												+ "]//ancestor::nm-combobox//following-sibling::nm-message//span[contains(text(),'Last Modified:')]")))
								.getText();
					}
					else {

						metadataFromUI = wait
								.until(ExpectedConditions.presenceOfElementLocated(
										By.xpath("(//label[contains(text(),\"" + Question + "\")])[" + instance
												+ "]//ancestor::nm-input//following-sibling::nm-message//span[contains(text(),'Last Modified:')]")))
								.getText();

						// metadataFromUI = wait
						// .until(ExpectedConditions.presenceOfElementLocated(
						// By.xpath("(//*[contains(text(),\"" + Question +
						// "\")])[" + instance
						// +
						// "]//ancestor::nm-element//following-sibling::nm-message//span[@class
						// = 'ui-messages-detail ng-star-inserted' and
						// contains(text(),'Last Modified:')]")))
						// .getText();
					}
					if (Sheet1.getRow(i).getCell(0).getStringCellValue().equals("Gm|1")
							|| Sheet1.getRow(i).getCell(0).getStringCellValue().equals("Gm|2")
							|| Sheet1.getRow(i).getCell(0).getStringCellValue().equals("Cm|2")
							|| Sheet1.getRow(i).getCell(0).getStringCellValue().equals("Cm|3")) {
						hasDateOfService = true;
					}

					if (hasDateOfService) {

						if (Sheet1.getRow(i + 1).getCell(0).getStringCellValue().contains("Date of Service")) {
							metadataToVerify = "Date of Service: "
									+ Sheet1.getRow(i + 1).getCell(2).getStringCellValue() + " ";
						} else if (Sheet1.getRow(i + 2).getCell(0).getStringCellValue().contains("Date of Service")) {
							metadataToVerify = "Date of Service: "
									+ Sheet1.getRow(i + 2).getCell(2).getStringCellValue() + " ";
						} else if (Sheet1.getRow(i + 3).getCell(0).getStringCellValue().contains("Date of Service")) {
							metadataToVerify = "Date of Service: "
									+ Sheet1.getRow(i + 3).getCell(2).getStringCellValue() + " ";
						}

					}

					metadataToVerify += ("Last Modified: " + dateFromSystem + " " + GlobalValues.UserNameOnUI + " "
							+ Sheet1.getRow(43).getCell(2).getStringCellValue() + " " + programName).trim();

					Assert.assertEquals(metadataFromUI, metadataToVerify, "Metadata Values are not matching");

					Log.info("Verified the metadata for - " + QuestionText);
				}

			}
			Log.info("**********Verified the metadata**********");

		} catch (Exception e) {
			e.printStackTrace();
			Log.info(e);
		}
	}

	public void viewHistoryGrid() throws Exception {
		Log = Logger.getLogger("GeneralMedicalPage.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		Global_Method gm = new Global_Method(driver);
		DashboardFunctions df = new DashboardFunctions(driver);

		try {

			String enteredBy = GlobalValues.UserNameOnUI;

			String programName = "";
			if(GlobalValues.DBProgramNameActiveStatus.contains("true") && GlobalValues.DBProgramNamePrimaryStatus.contains("true")){
				programName = GlobalValues.DBProgramName;
			}
			String programNameFromBanner = driver
					.findElement(By.xpath(config.getProgramNameFromBannerXpath()))
					.getText().toString().trim();
			String sourceFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 3,
					"If applicable, please select source of current changes").trim().toString();
			
			
			
			//********** Validate Question - Current Weight**********
			driver.findElement(By.xpath(config.getshowHistoryElementXpath().replace("instanceNumber", "1"))).click();
			Thread.sleep(GlobalValues.Sleep_wait_time);
			List<WebElement> tableHeaders = driver.findElements(By.xpath(config.gettableHeadersXpath()));
			int numberOfColumnsInHistoryGrid = tableHeaders.size();
			Log.info(numberOfColumnsInHistoryGrid);

			validateColumnsOfWithDOSGrid();
		

			WebElement currentWeightElement = driver.findElement(By.xpath(
					config.getGMQuestionLabelXpath().replace("instance", "6")));
			String currentWeightQuestionText = currentWeightElement.getText().trim();
			String answerFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 3,
					currentWeightQuestionText+"|2");
			String isHistoryPresent = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 2,
					currentWeightQuestionText);
			
			String dateOfServiceFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 3,
					"Date of Service|2");
			
				List<WebElement> answersFromUIGrid = driver
						.findElements(By.xpath(config.getanswersFromUIGridXpath()));

				Assert.assertEquals(answersFromUIGrid.get(0).getText().trim().contains(answerFromExcel), true);
				Assert.assertEquals(answersFromUIGrid.get(1).getText().trim().substring(0, 10), GlobalValues.GeneralMedicalSubmit_Date);
				Assert.assertEquals(answersFromUIGrid.get(2).getText().trim(), enteredBy);
				Assert.assertEquals(answersFromUIGrid.get(3).getText().trim(), dateOfServiceFromExcel);
				Assert.assertEquals(answersFromUIGrid.get(4).getText().trim(), programName);
				Assert.assertEquals(answersFromUIGrid.get(5).getText().trim(), sourceFromExcel);

				Log.info(
						"**********Verified the data  in the history grid **********" + currentWeightQuestionText);
			Thread.sleep(1000);
			WebElement hideHistoryElement = driver.findElement(By.xpath(config.gethideHistoryElementXpath()));
			hideHistoryElement.click();
			Thread.sleep(1000);

			//**********Validate Question - Current Length/Height**********//
			
			driver.findElement(By.xpath(config.getshowHistoryElementXpath().replace("instanceNumber", "2"))).click();
			Thread.sleep(GlobalValues.Sleep_wait_time);
			tableHeaders = driver.findElements(By.xpath(config.gettableHeadersXpath()));
			numberOfColumnsInHistoryGrid = tableHeaders.size();
			Log.info("Number of columns in the grid = " + numberOfColumnsInHistoryGrid);

			validateColumnsOfWithDOSGrid();
		

			WebElement currentLengthHeightElement = driver.findElement(By.xpath(
					config.getGMQuestionLabelXpath().replace("instance", "5")));
			String currentLengthHeightQuestionText = currentLengthHeightElement.getText().trim();
			answerFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 3,
					currentLengthHeightQuestionText+"|3");
			isHistoryPresent = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 2,
					currentLengthHeightQuestionText);
			
			dateOfServiceFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 3,
					"Date of Service|4");
			
				answersFromUIGrid = driver
						.findElements(By.xpath(config.getanswersFromUIGridXpath()));

				Assert.assertEquals(answersFromUIGrid.get(0).getText().trim().contains(answerFromExcel), true);
				Assert.assertEquals(answersFromUIGrid.get(1).getText().trim().substring(0, 10), GlobalValues.GeneralMedicalSubmit_Date);
				Assert.assertEquals(answersFromUIGrid.get(2).getText().trim(), enteredBy);
				Assert.assertEquals(answersFromUIGrid.get(3).getText().trim(), dateOfServiceFromExcel);
				Assert.assertEquals(answersFromUIGrid.get(4).getText().trim(), programName);
				Assert.assertEquals(answersFromUIGrid.get(5).getText().trim(), sourceFromExcel);

				Log.info(
						"**********Verified the data  in the history grid***********" + currentLengthHeightQuestionText);
			Thread.sleep(1000);
			hideHistoryElement = driver.findElement(By.xpath(config.gethideHistoryElementXpath()));
			hideHistoryElement.click();
			Thread.sleep(1000);

			
			//********** Validate Question - Child Growth Tracking**********
			driver.findElement(By.xpath(config.getshowHistoryElementXpath().replace("instanceNumber", "3"))).click();
			Thread.sleep(GlobalValues.Sleep_wait_time);
			 tableHeaders = driver.findElements(By.xpath(config.gettableHeadersXpath()));
			 numberOfColumnsInHistoryGrid = tableHeaders.size();
			Log.info(numberOfColumnsInHistoryGrid);

			validateColumnsOfWithoutDOSGrid();

			WebElement childGrowthTrackingElement = driver.findElement(By.xpath(
					config.getGMQuestionLegendXpath().replace("instance", "6")));
			String childGrowthTrackingQuestionText = childGrowthTrackingElement.getText().trim();
			answerFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 3,
					childGrowthTrackingQuestionText);
			isHistoryPresent = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 2,
					childGrowthTrackingQuestionText);

			if (answerFromExcel.contains("Yes") || answerFromExcel.contains("No")) {

				answersFromUIGrid = driver
						.findElements(By.xpath(config.getanswersFromUIGridXpath()));

				Assert.assertEquals(answersFromUIGrid.get(0).getText().trim().contains(answerFromExcel), true);
				Assert.assertEquals(answersFromUIGrid.get(1).getText().trim().substring(0, 10), GlobalValues.GeneralMedicalSubmit_Date);
				Assert.assertEquals(answersFromUIGrid.get(2).getText().trim(), enteredBy);
				Assert.assertEquals(answersFromUIGrid.get(3).getText().trim(), programName);
				Assert.assertEquals(answersFromUIGrid.get(4).getText().trim(), sourceFromExcel);

				Log.info(
						"**********Verified the data  in the history grid**********" + childGrowthTrackingQuestionText);
			}
			hideHistoryElement = driver.findElement(By.xpath(config.gethideHistoryElementXpath()));
			hideHistoryElement.click();
			Thread.sleep(1000);

			//********** Validate Question - ER visits since last NICU RN outreach**********
			driver.findElement(By.xpath(config.getshowHistoryElementXpath().replace("instanceNumber", "4"))).click();
			Thread.sleep(GlobalValues.Sleep_wait_time);

			validateColumnsOfWithoutDOSGrid();

			WebElement ERVisitRadioElement = driver.findElement(By.xpath(
					config.getGMQuestionLegendXpath().replace("instance", "5")));
			String ERVisitRadioElementQuestionText = ERVisitRadioElement.getText().trim();
			answerFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 3,
					ERVisitRadioElementQuestionText);
			isHistoryPresent = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 2,
					ERVisitRadioElementQuestionText);

			if (answerFromExcel.contains("Yes")) {

				answerFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 3,
						"Date of ER visit");
				answersFromUIGrid = driver
						.findElements(By.xpath(config.getanswersFromUIGridXpath()));

				Assert.assertEquals(answersFromUIGrid.get(0).getText().trim().contains(answerFromExcel), true);
				Assert.assertEquals(answersFromUIGrid.get(1).getText().trim().substring(0, 10), GlobalValues.GeneralMedicalSubmit_Date);
				Assert.assertEquals(answersFromUIGrid.get(2).getText().trim(), enteredBy);
				Assert.assertEquals(answersFromUIGrid.get(3).getText().trim(), programName);
				Assert.assertEquals(answersFromUIGrid.get(4).getText().trim(), sourceFromExcel);

				Log.info(
						"**********Verified the data  in the history grid**********" + ERVisitRadioElementQuestionText);
			} else if (answerFromExcel.contains("No")) {
				Log.info("History is not captured for 'No'. It`s captured only for ER visits since last NICU RN outreach date");
			}
			hideHistoryElement = driver.findElement(By.xpath(config.gethideHistoryElementXpath()));
			hideHistoryElement.click();
			Thread.sleep(1000);

			//********** Validate Question - Hospital Readmissions since last NICU RN outreach**********
			driver.findElement(By.xpath(config.getshowHistoryElementXpath().replace("instanceNumber", "5"))).click();
			Thread.sleep(GlobalValues.Sleep_wait_time);

			validateColumnsOfWithoutDOSGrid();

			WebElement hospitalReadmissionElement = driver.findElement(By.xpath(
					config.getGMQuestionLegendXpath().replace("instance", "5")));
			String hospitalReadmissionElementQuestionText = hospitalReadmissionElement.getText().trim();
			answerFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 3,
					hospitalReadmissionElementQuestionText);
			isHistoryPresent = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 2,
					hospitalReadmissionElementQuestionText);

			if (answerFromExcel.contains("Yes")) {
				answerFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 3,
						"Date of Hospital readmission");
				answersFromUIGrid = driver
						.findElements(By.xpath(config.getanswersFromUIGridXpath()));

				Assert.assertEquals(answersFromUIGrid.get(0).getText().trim().contains(answerFromExcel), true);
				Assert.assertEquals(answersFromUIGrid.get(1).getText().trim().substring(0, 10), GlobalValues.GeneralMedicalSubmit_Date);
				Assert.assertEquals(answersFromUIGrid.get(2).getText().trim(), enteredBy);
				Assert.assertEquals(answersFromUIGrid.get(3).getText().trim(), programName);
				Assert.assertEquals(answersFromUIGrid.get(4).getText().trim(), sourceFromExcel);

				Log.info("**********Verified the data  in the history grid**********"
						+ hospitalReadmissionElementQuestionText);
			} else if (answerFromExcel.contains("No")) {
				Log.info("History is not captured for 'No'. It`s captured only for Hospital Readmissions since last NICU RN outreach date");
			}
			hideHistoryElement = driver.findElement(By.xpath(config.gethideHistoryElementXpath()));
			hideHistoryElement.click();
			Thread.sleep(1000);

			//********** Validate Question - Importance Assessment Level- Child**********
			driver.findElement(By.xpath(config.getshowHistoryElementXpath().replace("instanceNumber", "6"))).click();
			Thread.sleep(GlobalValues.Sleep_wait_time);

			validateColumnsOfWithoutDOSGrid();

			WebElement impAssessmentLevelChildElement = driver.findElement(By.xpath(
					config.getGMQuestionLabelXpath().replace("instance", "3")));
			String impAssessmentLevelChildElementQuestionText = impAssessmentLevelChildElement.getText().trim();
			answerFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 3,
					impAssessmentLevelChildElementQuestionText);
			isHistoryPresent = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 2,
					impAssessmentLevelChildElementQuestionText);

			answersFromUIGrid = driver
					.findElements(By.xpath(config.getanswersFromUIGridXpath()));

			Assert.assertEquals(answersFromUIGrid.get(0).getText().trim().contains(answerFromExcel), true);
			Assert.assertEquals(answersFromUIGrid.get(1).getText().trim().substring(0, 10), GlobalValues.GeneralMedicalSubmit_Date);
			Assert.assertEquals(answersFromUIGrid.get(2).getText().trim(), enteredBy);
			Assert.assertEquals(answersFromUIGrid.get(3).getText().trim(), programName);
			Assert.assertEquals(answersFromUIGrid.get(4).getText().trim(), sourceFromExcel);

			Log.info("**********Verified the data  in the history grid**********"
					+ impAssessmentLevelChildElementQuestionText);

			hideHistoryElement = driver.findElement(By.xpath(config.gethideHistoryElementXpath()));
			hideHistoryElement.click();
			Thread.sleep(1000);

			//********** Validate Question - Confidence Level to Manage Child's Health Concerns**********
			driver.findElement(By.xpath(config.getshowHistoryElementXpath().replace("instanceNumber", "7"))).click();
			Thread.sleep(GlobalValues.Sleep_wait_time);

			validateColumnsOfWithoutDOSGrid();

			WebElement confidenceLevelToManageChildHealthElement = driver.findElement(By.xpath(
					config.getGMQuestionLabelXpath().replace("instance", "6")));
			String confidenceLevelToManageChildHealthElementQuestionText = confidenceLevelToManageChildHealthElement
					.getText().trim();
			answerFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 3,
					confidenceLevelToManageChildHealthElementQuestionText);
			isHistoryPresent = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 2,
					confidenceLevelToManageChildHealthElementQuestionText);

			answersFromUIGrid = driver
					.findElements(By.xpath(config.getanswersFromUIGridXpath()));

			Assert.assertEquals(answersFromUIGrid.get(0).getText().trim().contains(answerFromExcel), true);
			Assert.assertEquals(answersFromUIGrid.get(1).getText().trim().substring(0, 10), GlobalValues.GeneralMedicalSubmit_Date);
			Assert.assertEquals(answersFromUIGrid.get(2).getText().trim(), enteredBy);
			Assert.assertEquals(answersFromUIGrid.get(3).getText().trim(), programName);
			Assert.assertEquals(answersFromUIGrid.get(4).getText().trim(), sourceFromExcel);

			Log.info("**********Verified the data  in the history grid**********"
					+ confidenceLevelToManageChildHealthElementQuestionText);

			hideHistoryElement = driver.findElement(By.xpath(config.gethideHistoryElementXpath()));
			hideHistoryElement.click();
			Thread.sleep(1000);

			//********** Validate Question - Child's Current Health Concerns**********
			driver.findElement(By.xpath(config.getshowHistoryElementXpath().replace("instanceNumber", "8"))).click();
			Thread.sleep(GlobalValues.Sleep_wait_time);

			validateColumnsOfWithoutDOSGrid();

			WebElement childCurrentHealthConcernsElement = driver.findElement(By.xpath(
					config.getGMQuestionLabelXpath().replace("instance", "6")));
			String childCurrentHealthConcernsElementQuestionText = childCurrentHealthConcernsElement.getText().trim();
			answerFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 3,
					childCurrentHealthConcernsElementQuestionText);
			isHistoryPresent = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 2,
					childCurrentHealthConcernsElementQuestionText);
			Log.info(answerFromExcel+ "-answer from excel");
			answersFromUIGrid = driver
					.findElements(By.xpath(config.getanswersFromUIGridXpath()));

			Assert.assertEquals(answersFromUIGrid.get(0).getText().trim().contains(answerFromExcel), true);
			Assert.assertEquals(answersFromUIGrid.get(1).getText().trim().substring(0, 10), GlobalValues.GeneralMedicalSubmit_Date);
			Assert.assertEquals(answersFromUIGrid.get(2).getText().trim(), enteredBy);
			Assert.assertEquals(answersFromUIGrid.get(3).getText().trim(), programName);
			Assert.assertEquals(answersFromUIGrid.get(4).getText().trim(), sourceFromExcel);

			Log.info("**********Verified the data  in the history grid**********"
					+ childCurrentHealthConcernsElementQuestionText);

			hideHistoryElement = driver.findElement(By.xpath(config.gethideHistoryElementXpath()));
			hideHistoryElement.click();
			Thread.sleep(1000);

			//********** Validate Question - Good Understanding of Child's Health Conditions ********** 
			driver.findElement(By.xpath(config.getshowHistoryElementXpath().replace("instanceNumber", "9"))).click();
			Thread.sleep(GlobalValues.Sleep_wait_time);

			validateColumnsOfWithoutDOSGrid();

			WebElement goodUnderstandingOfChildHealthConditionsElement = driver.findElement(By.xpath(
					config.getGMQuestionLabelXpath().replace("instance", "5")));
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
				answersFromUIGrid = driver
						.findElements(By.xpath(config.getanswersFromUIGridXpath()));

				Assert.assertEquals(answersFromUIGrid.get(0).getText().trim().contains(answerFromExcel), true);
				Assert.assertEquals(answersFromUIGrid.get(1).getText().trim().substring(0, 10), GlobalValues.GeneralMedicalSubmit_Date);
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

			//********** Validate Question - Child Vision Impairment***********
			driver.findElement(By.xpath(config.getshowHistoryElementXpath().replace("instanceNumber", "10"))).click();
			Thread.sleep(GlobalValues.Sleep_wait_time);

			validateColumnsOfWithoutDOSGrid();

			WebElement childVisionImpairmentElement = driver.findElement(By.xpath(
					config.getGMQuestionLabelXpath().replace("instance", "4")));
			String childVisionImpairmentElementQuestionText = childVisionImpairmentElement.getText().trim();
			answerFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 3,
					childVisionImpairmentElementQuestionText);
			isHistoryPresent = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 2,
					childVisionImpairmentElementQuestionText);

			answersFromUIGrid = driver
					.findElements(By.xpath(config.getanswersFromUIGridXpath()));

			Assert.assertEquals(answersFromUIGrid.get(0).getText().trim().contains(answerFromExcel), true);
			Assert.assertEquals(answersFromUIGrid.get(1).getText().trim().substring(0, 10), GlobalValues.GeneralMedicalSubmit_Date);
			Assert.assertEquals(answersFromUIGrid.get(2).getText().trim(), enteredBy);
			Assert.assertEquals(answersFromUIGrid.get(3).getText().trim(), programName);
			Assert.assertEquals(answersFromUIGrid.get(4).getText().trim(), sourceFromExcel);

			Log.info("**********Verified the data  in the history grid**********"
					+ childVisionImpairmentElementQuestionText);

			hideHistoryElement = driver.findElement(By.xpath(config.gethideHistoryElementXpath()));
			hideHistoryElement.click();
			Thread.sleep(1000);

			//*********** Validate Question - Child's Hearing Impairment **********
			driver.findElement(By.xpath(config.getshowHistoryElementXpath().replace("instanceNumber", "11"))).click();
			Thread.sleep(GlobalValues.Sleep_wait_time);

			validateColumnsOfWithoutDOSGrid();

			WebElement childHearingImpairmentElement = driver.findElement(By.xpath(
					config.getGMQuestionLabelXpath().replace("instance", "5")));
			String childHearingImpairmentQuestionText = childHearingImpairmentElement.getText().trim();
			answerFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 3,
					childHearingImpairmentQuestionText);
			isHistoryPresent = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 2,
					childHearingImpairmentQuestionText);

			answersFromUIGrid = driver
					.findElements(By.xpath(config.getanswersFromUIGridXpath()));

			Assert.assertEquals(answersFromUIGrid.get(0).getText().trim().contains(answerFromExcel), true);
			Assert.assertEquals(answersFromUIGrid.get(1).getText().trim().substring(0, 10), GlobalValues.GeneralMedicalSubmit_Date);
			Assert.assertEquals(answersFromUIGrid.get(2).getText().trim(), enteredBy);
			Assert.assertEquals(answersFromUIGrid.get(3).getText().trim(), programName);
			Assert.assertEquals(answersFromUIGrid.get(4).getText().trim(), sourceFromExcel);

			Log.info("**********Verified the data  in the history grid**********"
					+ childHearingImpairmentQuestionText);

			hideHistoryElement = driver.findElement(By.xpath(config.gethideHistoryElementXpath()));
			hideHistoryElement.click();
			Thread.sleep(1000);

			//********** Validate Question - Child's Allergies **********
			driver.findElement(By.xpath(config.getshowHistoryElementXpath().replace("instanceNumber", "12"))).click();
			Thread.sleep(GlobalValues.Sleep_wait_time);

			validateColumnsOfWithoutDOSGrid();

			WebElement childAllergiesElement = driver.findElement(By.xpath(
					config.getGMQuestionLabelXpath().replace("instance", "1")));
			String childAllergiesElementQuestionText = childAllergiesElement.getText().trim();
			answerFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 3,
					childAllergiesElementQuestionText);
			isHistoryPresent = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 2,
					childAllergiesElementQuestionText);

			if (answerFromExcel.contains("Yes")) {
				answerFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 3,
						"Please Explain/List the Allergy(ies)");
				answersFromUIGrid = driver
						.findElements(By.xpath(config.getanswersFromUIGridXpath()));

				Assert.assertEquals(answersFromUIGrid.get(0).getText().trim().contains(answerFromExcel), true);
				Assert.assertEquals(answersFromUIGrid.get(1).getText().trim().substring(0, 10), GlobalValues.GeneralMedicalSubmit_Date);
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
		Log = Logger.getLogger("GeneralMedicalPage.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		Global_Method gm = new Global_Method(driver);
		DashboardFunctions df = new DashboardFunctions(driver);

		try {
			String gridHeaderText = wait
					.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath(config.getgridHeaderTextXpath())))
					.getText();
			Log.info("Grid Header is " + gridHeaderText);
			Assert.assertEquals(gridHeaderText.trim(), "History", "Grid header not matching");

			List<WebElement> actualColumnHeaders = driver.findElements(By.xpath(
					config.getactualColumnHeadersXpath()));
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
		Log = Logger.getLogger("GeneralMedicalPage.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		Global_Method gm = new Global_Method(driver);
		

		try {

			String gridHeaderText = wait
					.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath(config.getgridHeaderTextXpath())))
					.getText();
			Log.info("Grid Header is " + gridHeaderText);
			Assert.assertEquals(gridHeaderText.trim(), "History", "Grid header not matching");
			List<WebElement> actualColumnHeaders = driver.findElements(By.xpath(
					config.getactualColumnHeadersXpath()));
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

	
	
	
	
	
	
	public void viewHistoryGridWithoutDOSLoop() throws Exception {
		Log = Logger.getLogger("GeneralMedicalPage.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		Global_Method gm = new Global_Method(driver);
		

		try {

			String enteredBy = GlobalValues.UserNameOnUI;

			String programName = "";
			if(GlobalValues.DBProgramNameActiveStatus.contains("true") && GlobalValues.DBProgramNamePrimaryStatus.contains("true")){
				programName = GlobalValues.DBProgramName;
			}
			
			String programNameFromBanner = driver
					.findElement(By.xpath(config.getProgramNameFromBannerXpath()))
					.getText().toString().trim();
			String sourceFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 3,
					"If applicable, please select source of current changes").trim().toString();

			Thread.sleep(2000);
			String gridHeaderText = wait
					.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath(config.getgridHeaderTextXpath())))
					.getText();
			Log.info("Grid Header is " + gridHeaderText);
			Assert.assertEquals(gridHeaderText.trim(), "History", "Grid header not matching");
			List<WebElement> actualColumnHeaders = driver.findElements(By.xpath(
					config.getactualColumnHeadersXpath()));
			Assert.assertEquals(actualColumnHeaders.get(0).getText().trim(), "Answer");
			Assert.assertEquals(actualColumnHeaders.get(1).getText().trim(), "Date Entered");
			Assert.assertEquals(actualColumnHeaders.get(2).getText().trim(), "Entered By");
			Assert.assertEquals(actualColumnHeaders.get(3).getText().trim(), "Program");
			Assert.assertEquals(actualColumnHeaders.get(4).getText().trim(), "Source");
			Log.info("Grid Header has been verified");
			Log.info("Getting the list of Column names for History Grid");
			Thread.sleep(GlobalValues.Sleep_wait_time);
			Log.info("**********History Grid Column Names has been verified for without DOS**********");

			List<WebElement> lastModifiedElements = driver.findElements(By.xpath(
					config.getlastModifiedElementsXpath()));
			WebElement lastModifiedElement = lastModifiedElements.get(lastModifiedElements.size() - 1);

			WebElement questionElement;
			try {
				questionElement = lastModifiedElement

						.findElement(By.xpath(
								"//ancestor::nm-frm-grp//child::nm-input//labelbli"));
			} catch (Exception e) {
				// (//button[contains(text(), 'Hide
				// History')]//ancestor::nm-button//preceding-sibling::nm-element//span[contains(text(),
				// 'Modified')])[8]//parent::li/parent::ul/parent::div/parent::p-messages/parent::nm-message/preceding-sibling::nm-input-radio/descendant::legend
				questionElement = lastModifiedElement

						.findElement(By.xpath(
								"parent::li/parent::ul/parent::div/parent::p-messages/parent::nm-message/preceding-sibling::nm-input-radio/descendant::legend"));

			}
			String questionFromUI = questionElement.getText();

			List<WebElement> sameQuestionList;
			try {
				sameQuestionList = driver
						.findElements(By.xpath("//label[contains(text(),\"" + questionFromUI + "\")]"));
			} catch (Exception e) {
				sameQuestionList = driver
						.findElements(By.xpath("//legend[contains(text(),\"" + questionFromUI + "\")]"));
			}
			if (sameQuestionList.size() > 1) {
				for (int k = 0; k < sameQuestionList.size(); k++) {
					if (sameQuestionList.get(k).equals(questionElement)) {
						questionFromUI = questionFromUI.trim() + "|" + (k + 1);
						break;
					}
				}
			}
			String answerFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 3, questionFromUI);

			String isHistoryPresent = gm.readFromExcel("AnnualAssessments_CMDM", "AddGeneralMedical", 2,
					questionFromUI);

			if (!(isHistoryPresent.contains("TRUE") || isHistoryPresent.contains(answerFromExcel))) {
				return;
			}

			List<WebElement> answersFromUIGrid = driver
					.findElements(By.xpath(config.getanswersFromUIGridXpath()));

			Assert.assertEquals(answersFromUIGrid.get(0).getText().trim().contains(answerFromExcel), true);
			Assert.assertEquals(answersFromUIGrid.get(1).getText().trim().substring(0, 10), GlobalValues.GeneralMedicalSubmit_Date);
			Assert.assertEquals(answersFromUIGrid.get(2).getText().trim(), enteredBy);
			Assert.assertEquals(answersFromUIGrid.get(3).getText().trim(), programName);
			Assert.assertEquals(answersFromUIGrid.get(4).getText().trim(), sourceFromExcel);

			Log.info("**********Verified the data  in the history grid**********" + questionFromUI);
		} catch (Exception e) {
			e.printStackTrace();
			Log.info(e);
		}
	}


	
}
