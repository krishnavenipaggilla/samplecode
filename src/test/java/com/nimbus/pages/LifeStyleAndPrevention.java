package com.nimbus.pages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utility.ConfigReader;

public class LifeStyleAndPrevention {

	WebDriver driver;
	Logger Log;

	ConfigReader config = new ConfigReader();

	public LifeStyleAndPrevention(WebDriver ldriver) {
		this.driver = ldriver;
	}

	public LifeStyleAndPrevention(Logger lLog) {
		this.Log = lLog;
	}

	String sourceValueSelected;

	public void LifestyleAndPrevention() throws Exception {

		Log = Logger.getLogger("LifestyleAndPrevention.class");
		PropertyConfigurator.configure("log4j.properties");
		AnnualAssessment annual = new AnnualAssessment(driver);

		// verifying bread crumb
		WebElement LSPBreadCrumb = driver.findElement(By.xpath(config.breadCrumb()));
		Assert.assertEquals(LSPBreadCrumb.getText(), "Lifestyle and Prevention",
				"Bread Crumb of the Lifestyle and Prevention page is not matching");
		Log.info("Verified Lifestyle and Prevention page breadcrumb");

		// verifying the page header
		WebElement PageHeader = driver.findElement(By.xpath(config.LSPHeader()));
		Assert.assertEquals(PageHeader.getText(), "Lifestyle and Prevention",
				"Lifestyle and Prevention page header is not matching");
		Log.info("Successfully verifed the Lifestyle and Prevention page header");
		Thread.sleep(3000);

		annual.annual_assessment("AnnualAssessments_CMDM", "LSP_Add", 2);
		Thread.sleep(3000);

		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		// df.setTimeZone(TimeZone.getTimeZone("UTC"));
		GlobalValues.LifestylePreventionSubmit_Date = df.format(date);
		Log.info(GlobalValues.LifestylePreventionSubmit_Date);

		WebElement Source_dropdown = driver.findElement(By.xpath(config.SourceDropDown())); // capturing
																							// label
		String text = " If applicable, please select source of current changes ";

		if (Source_dropdown.getText().equals(text.trim())) {

			WebElement SourceValue = driver.findElement(By.xpath(config.SourceValue()));
			sourceValueSelected = SourceValue.getText();
			Log.info("Capturing selected Source value while submitting in Lifestyle and Prevention");
		} else {
			System.out.println("Both are not same");
			Log.info("Not captured the Souce drop dwon value while submitting");
		}

		WebElement Submitclick = driver.findElement(By.xpath(config.LSPSubmit()));
		Submitclick.click();

		Log.info("LifestyleAndPrevention is Submitted");

	}

	public void VerifySuccessMsg() throws Exception {
		Log = Logger.getLogger("LifestyleAndPrevention.class");
		PropertyConfigurator.configure("log4j.properties");
		try {
			String SuccessMessage_Summary = driver.findElement(By.xpath(config.SuccessMessage_Summary())).getText();
			String SuccsssMessage_Details = driver.findElement(By.xpath(config.SuccsssMessage_Details())).getText();

			Assert.assertEquals(SuccessMessage_Summary, "Success Message",
					"Not showing the Updated Success Message as 'Success Message' after click on SUbmit in LSP");
			Assert.assertEquals(SuccsssMessage_Details, "Updates Saved",
					"Not showing the Updated Success Message as 'Updates Saved' after click on SUbmit in LSP");

			Thread.sleep(2000);

			Log.info("Verified the after Submit Success Message In LSP");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Log.info("Success Mesage not Captured");
		}
	}

	/*
	 * public void ShowHistory() throws InterruptedException { Log =
	 * Logger.getLogger("LifestyleAndPrevention.class");
	 * PropertyConfigurator.configure("log4j.properties");
	 * 
	 * //Verifying Show History grid WebElement ShowHistory =
	 * driver.findElement(By.xpath(config.ShowHistoryClick()));
	 * ShowHistory.click(); Thread.sleep(3000); WebElement ShowHistoryHeader =
	 * driver.findElement(By.xpath(config.ShowHistoryHeader()));
	 * if(ShowHistoryHeader.getText().equals("History")) {
	 * Log.info("History is the Grid header"); } else {
	 * Log.info("Grid Header is not displaying"); } for(int
	 * i=0;i<GlobalValues.ShowHistoryGrid.size();i++) {
	 * driver.findElement(By.xpath("//p-table/div//tr/th/span[text()='"+
	 * GlobalValues.ShowHistoryGrid.get(i) +"']")); Log.info("Verifying the "+
	 * GlobalValues.ShowHistoryGrid.get(i) +
	 * "- columns in the Show History grid"); } }
	 */
	public void verifyHistoryForLSP() throws Exception {
		Log = Logger.getLogger("LifestyleandPrevention.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		Global_Method gm = new Global_Method(driver);
		DashboardFunctions df = new DashboardFunctions(driver);
		LeftNavigationLink lnk = new LeftNavigationLink(driver);
		lnk.clickLifestyleAndPrevention();
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
			String sourceFromExcel = sourceValueSelected;

			// Validate Question - Child's feeding method?
			driver.findElement(By.xpath("(//button[contains(text(), 'Show History')])[1]")).click();
			Thread.sleep(GlobalValues.Sleep_wait_time);
			List<WebElement> tableHeaders = driver.findElements(By.xpath(config.gettableHeadersXpath()));
			int numberOfColumnsInHistoryGrid = tableHeaders.size();
			Log.info(numberOfColumnsInHistoryGrid);

			validateColumnsOfWithoutDOSGrid();

			WebElement childFeedingMethodElement = driver
					.findElement(By.xpath(config.getGMQuestionLabelXpath().replace("instance", "3")));
			String childFeedingMethodElementQuestionText = childFeedingMethodElement.getText().trim();
			String answerFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "LSP_Add", 2,
					childFeedingMethodElementQuestionText);
			

			if (answerFromExcel.contains("Other")) {
				answerFromExcel = "Other - "
						+ gm.readFromExcel("AnnualAssessments_CMDM", "LSP_Add", 2, "If Other, please explain");
			}
			List<WebElement> answersFromUIGrid = driver.findElements(By.xpath(config.getanswersFromUIGridXpath()));

			Assert.assertEquals(answersFromUIGrid.get(0).getText().trim().contains(answerFromExcel), true);
			Assert.assertEquals(answersFromUIGrid.get(1).getText().trim().substring(0, 10), GlobalValues.LifestylePreventionSubmit_Date);
			Assert.assertEquals(answersFromUIGrid.get(2).getText().trim(), enteredBy);
			Assert.assertEquals(answersFromUIGrid.get(3).getText().trim(), programName);
			Assert.assertEquals(answersFromUIGrid.get(4).getText().trim(), sourceFromExcel);

			Log.info("**********Verified the data  in the history grid**********"
					+ childFeedingMethodElementQuestionText);

			Thread.sleep(GlobalValues.Sleep_wait_time);
			WebElement hideHistoryElement = driver.findElement(By.xpath(config.gethideHistoryElementXpath()));
			hideHistoryElement.click();
			Thread.sleep(GlobalValues.Sleep_wait_time);

			// Validate Question - Special Diet or Formula needs?
			driver.findElement(By.xpath("(//button[contains(text(), 'Show History')])[2]")).click();
			Thread.sleep(GlobalValues.Sleep_wait_time);

			validateColumnsOfWithoutDOSGrid();

			WebElement specialDietFormulaNeedsElement = driver
					.findElement(By.xpath(config.getGMQuestionLegendXpath().replace("instance", "3")));
			String specialDietFormulaNeedsElementQuestionText = specialDietFormulaNeedsElement.getText().trim();
			answerFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "LSP_Add", 2,
					specialDietFormulaNeedsElementQuestionText);
			

			if (answerFromExcel.contains("Yes")) {
				answerFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "LSP_Add", 2,
						"If Yes, what is the formula");
				answersFromUIGrid = driver.findElements(By.xpath(config.getanswersFromUIGridXpath()));

				Assert.assertEquals(answersFromUIGrid.get(0).getText().trim().contains(answerFromExcel), true);
				Assert.assertEquals(answersFromUIGrid.get(1).getText().trim().substring(0, 10),
						GlobalValues.LifestylePreventionSubmit_Date);
				Assert.assertEquals(answersFromUIGrid.get(2).getText().trim(), enteredBy);
				Assert.assertEquals(answersFromUIGrid.get(3).getText().trim(), programName);
				Assert.assertEquals(answersFromUIGrid.get(4).getText().trim(), sourceFromExcel);

				Log.info("**********Verified the data  in the history grid**********"
						+ specialDietFormulaNeedsElementQuestionText);
			} else if (answerFromExcel.contains("No")) {
				Log.info(
						"History is not captured for 'No'.History is captured for if answered 'Yes' for 'Special Diet or Formula needs'");
			}

			Thread.sleep(GlobalValues.Sleep_wait_time);
			hideHistoryElement = driver.findElement(By.xpath(config.gethideHistoryElementXpath()));
			hideHistoryElement.click();
			Thread.sleep(GlobalValues.Sleep_wait_time);

			// Validate Question - Substances currently used by parent/caregiver

			driver.findElement(By.xpath("(//button[contains(text(), 'Show History')])[3]")).click();
			Thread.sleep(GlobalValues.Sleep_wait_time);

			validateColumnsOfWithoutDOSGrid();

			WebElement substancesUsedElement = driver
					.findElement(By.xpath(config.getGMQuestionLegendXpath().replace("instance", "6")));
			String substancesUsedElementElementQuestionText = substancesUsedElement.getText().trim();
			answerFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "LSP_Add", 3,
					substancesUsedElementElementQuestionText);
			

			answersFromUIGrid = driver.findElements(By.xpath(config.getanswersFromUIGridXpath()));

			Assert.assertEquals(answersFromUIGrid.get(0).getText().trim().contains(answerFromExcel), true);
			Assert.assertEquals(answersFromUIGrid.get(1).getText().trim().substring(0, 10), GlobalValues.LifestylePreventionSubmit_Date);
			Assert.assertEquals(answersFromUIGrid.get(2).getText().trim(), enteredBy);
			Assert.assertEquals(answersFromUIGrid.get(3).getText().trim(), programName);
			Assert.assertEquals(answersFromUIGrid.get(4).getText().trim(), sourceFromExcel);

			Log.info("**********Verified the data  in the history grid**********"
					+ substancesUsedElementElementQuestionText);

			Thread.sleep(GlobalValues.Sleep_wait_time);
			hideHistoryElement = driver.findElement(By.xpath(config.gethideHistoryElementXpath()));
			hideHistoryElement.click();
			Thread.sleep(GlobalValues.Sleep_wait_time);
			
			
			//Validate Question - Others living in home using tobacco products:
			
			driver.findElement(By.xpath("(//button[contains(text(), 'Show History')])[4]")).click();
			Thread.sleep(GlobalValues.Sleep_wait_time);

			validateColumnsOfWithoutDOSGrid();

			WebElement usingTobaccoElement = driver
					.findElement(By.xpath(config.getGMQuestionLegendXpath().replace("instance", "2")));
			String usingTobaccoElementQuestionText = usingTobaccoElement.getText().trim();
			answerFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "LSP_Add", 3,
					usingTobaccoElementQuestionText);
			

			answersFromUIGrid = driver.findElements(By.xpath(config.getanswersFromUIGridXpath()));

			Assert.assertEquals(answersFromUIGrid.get(0).getText().trim().contains(answerFromExcel), true);
			Assert.assertEquals(answersFromUIGrid.get(1).getText().trim().substring(0, 10), GlobalValues.LifestylePreventionSubmit_Date);
			Assert.assertEquals(answersFromUIGrid.get(2).getText().trim(), enteredBy);
			Assert.assertEquals(answersFromUIGrid.get(3).getText().trim(), programName);
			Assert.assertEquals(answersFromUIGrid.get(4).getText().trim(), sourceFromExcel);

			Log.info("**********Verified the data  in the history grid**********"
					+ usingTobaccoElementQuestionText);

			Thread.sleep(GlobalValues.Sleep_wait_time);
			hideHistoryElement = driver.findElement(By.xpath(config.gethideHistoryElementXpath()));
			hideHistoryElement.click();
			Thread.sleep(GlobalValues.Sleep_wait_time);
			
			//Validate Question - Education/Resources provided:
			
			driver.findElement(By.xpath("(//button[contains(text(), 'Show History')])[5]")).click();
			Thread.sleep(GlobalValues.Sleep_wait_time);

			validateColumnsOfWithoutDOSGrid();

			WebElement educationProvidedElement = driver
					.findElement(By.xpath(config.getGMQuestionLegendXpath().replace("instance", "8")));
			String educationProvidedElementQuestionText = educationProvidedElement.getText().trim();
			answerFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "LSP_Add", 3,
					educationProvidedElementQuestionText);
			
			answersFromUIGrid = driver.findElements(By.xpath(config.getanswersFromUIGridXpath()));

			Assert.assertEquals(answersFromUIGrid.get(0).getText().trim().contains(answerFromExcel), true);
			Assert.assertEquals(answersFromUIGrid.get(1).getText().trim().substring(0, 10), GlobalValues.LifestylePreventionSubmit_Date);
			Assert.assertEquals(answersFromUIGrid.get(2).getText().trim(), enteredBy);
			Assert.assertEquals(answersFromUIGrid.get(3).getText().trim(), programName);
			Assert.assertEquals(answersFromUIGrid.get(4).getText().trim(), sourceFromExcel);

			Log.info("**********Verified the data  in the history grid**********"
					+ educationProvidedElementQuestionText);

			Thread.sleep(GlobalValues.Sleep_wait_time);
			hideHistoryElement = driver.findElement(By.xpath(config.gethideHistoryElementXpath()));
			hideHistoryElement.click();
			Thread.sleep(GlobalValues.Sleep_wait_time);

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
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getgridHeaderTextXpath())))
					.getText();
			Log.info("Grid Header is " + gridHeaderText);
			Assert.assertEquals(gridHeaderText.trim(), "History", "Grid header not matching");

			List<WebElement> actualColumnHeaders = driver.findElements(By.xpath(config.getactualColumnHeadersXpath()));
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

	public void LSP_VerifyLastModified() {
		Log = Logger.getLogger("LifestyleAndPrevention.class");
		PropertyConfigurator.configure("log4j.properties");

		for (int i = 1; i <= 9; i++) {

			WebElement VerifyLastModified = driver
					.findElement(By.xpath("//nm-frm-grp['" + i + 1 + "']//p-messages/div//li/span"));
			Log.info("Verifying Last Modified value " + i + 1 + " displying as " + VerifyLastModified.getText());
		}

		Log.info("Successfully verified Last Modifeied values for all the labels in LSP after submit");
	}

	public void LSP_Edit() throws Exception {
		Log = Logger.getLogger("LifestyleAndPrevention.class");
		PropertyConfigurator.configure("log4j.properties");
		AnnualAssessment annual = new AnnualAssessment(driver);

		annual.annual_assessment("AnnualAssessments_CMDM", "LSP_Edit", 2);
		Thread.sleep(3000);

		Log.info("Successfully Edited the LSP ");
	}

} // LifeStyleAnd Prevention method
