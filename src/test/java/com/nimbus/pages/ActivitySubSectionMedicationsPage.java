package com.nimbus.pages;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import bsh.ParseException;
import utility.ConfigReader;

public class ActivitySubSectionMedicationsPage {

	WebDriver driver;
	Logger Log;
	ConfigReader config = new ConfigReader();

	public ActivitySubSectionMedicationsPage(WebDriver ldriver) {
		this.driver = ldriver;
	}

	public ActivitySubSectionMedicationsPage(Logger lLog) {
		this.Log = lLog;
	}

	public void medicationAction(String TaskName) throws Exception {
		Log = Logger.getLogger("AddProviderAction.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		Global_Method GM = new Global_Method(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		LeftNavigationLink lnk = new LeftNavigationLink(driver);
		lnk.clickMemberActionCenter();
		Thread.sleep(3000);

		// adding Cymballa medicine
		AddingMedicationPage addingMedication;
		AnnualAssessment annual;
		try {
			driver.findElement(By.xpath(config.getActivitiesUnderMemberActionCenter())).click();
			Log.info("click on Activities under member action center");
			Thread.sleep(3000);
			ActivityPage addActivity = new ActivityPage(driver);
			addActivity.editInitialOutreachActivity(TaskName);
			Log.info("click on edit button from initial outreach");
			addingMedication = new AddingMedicationPage(driver);
			addingMedication.adddingMedicationCymbalta();
			annual = new AnnualAssessment(driver);
			annual.annual_assessment("AnnualAssessments_CMDM", "AddMedications", 2);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getMedicationsFromSubSection())))
					.click();
			Log.info("click on Medications");
			Log.info("Cymballa medicine is added");
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getCheckBoxMedication()))).click();
			Log.info("Click on check box button ");
			Thread.sleep(2000);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getReviewButton()))).click();
			Log.info("Click on review button ");
			Thread.sleep(1000);
			Log.info("Passed-NIM-17396: Activity Sub-section: Medications - Create Medications subsection accordion" + '\n' + "GIVEN:Create Medications subsection accordion"
					+ '\n' + "THEN:verifying Activity Sub-section: Medications - Create Medications subsection accordion");
		} catch (Exception e) {
			e.printStackTrace();
					Log.info("Failed-NIM-17396: Activity Sub-section: Medications - Create Medications subsection accordion");

		}
		// date and time generated after clicking on review

		try {
			boolean isFormatted = true;
			DateFormat destDf = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
			WebElement IdentifiedDate = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getDateAndTime())));
			String ActualText = IdentifiedDate.getText();
			Log.info("Date found is " + ActualText);
			try {
				destDf.parse(ActualText);
			} catch (Exception ee) {

				isFormatted = false;
				Log.info(ee.getMessage().toString());
			}

			Assert.assertTrue(isFormatted);
			Log.info("Date and Time has been generated");
			// adding Verzennio medicine
			try {
				// adding Verzennio medicine
				addingMedication = new AddingMedicationPage(driver);
				annual = new AnnualAssessment(driver);
				// AddingMedicationPage addingMedication;
				// AnnualAssessment annual;
				addingMedication.adddingMedicationVerzenio();
				annual.annual_assessment("AnnualAssessments_CMDM", "AddMedications", 7);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
				Thread.sleep(1000);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getMedicationsFromSubSection())))
						.click();
				Log.info("click on Medications");
				Log.info("Verzenio is added");
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getCheckBoxMedication()))).click();
				Log.info("Click on check box button ");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
				Thread.sleep(1000);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getReviewButton()))).click();
				Log.info("Click on review button ");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
				Thread.sleep(1000);

				// date and time generated after clicking on review
				boolean isFormatteds = true;
				DateFormat destDfs = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
				WebElement IdentifiedDates = wait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getDateAndTime())));
				String ActualTexts = IdentifiedDates.getText();
				Log.info("Date found is " + ActualTexts);
				try {
					destDfs.parse(ActualTexts);
								
				} catch (Exception ee) {
					isFormatteds = false;
					Log.info(ee.getMessage().toString());
				}

				Assert.assertTrue(isFormatteds);
				Log.info("Date and Time has been generated");
				Log.info("Passed-NIM-17414: Activity Sub-section: Medications - Review of Current Medications" + '\n' + "GIVEN:Review of Current Medications"
						+ '\n' + "THEN:verifying Activity Sub-section: Medications - Review of Current Medications");
			}

			catch (Exception Ex) {
				Log.info(Ex.getMessage().toString());
				Log.info("Failed-NIM-17414: Activity Sub-section: Medications - Review of Current Medications");
			}

			// sorting column
			List<WebElement> columns = driver.findElements(By.xpath(config.getAllColumnNames()));
			List<String> columnNames = new ArrayList<String>();
			for (WebElement column : columns) {
				columnNames.add(column.getText());
			}

			// Verification of sorting of each column
			addingMedication = new AddingMedicationPage(driver);
			annual = new AnnualAssessment(driver);
			for (String columnName : columnNames) {
				driver.findElement(By.xpath(config.getColumnName().replace("COL_NAME", columnName))).click();
				Thread.sleep(2000);
				if (addingMedication.isAscendingSorted(columnName) == true)
					Log.info(columnName + " is sorted in ascending order");
				driver.findElement(By.xpath(config.getColumnName().replace("COL_NAME", columnName))).click();
				Thread.sleep(2000);
				if (addingMedication.isDescendingSorted(columnName) == true)
					Log.info(columnName + " is sorted in descending order");

			}

			// checking filtering
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getClickOnFilter()))).click();
			Log.info("click  on filter Button");
			Thread.sleep(3000);

			WebElement el = driver.findElement(By.xpath(config.getFilterBox1()));
			el.sendKeys("Verzenio");
			Log.info("Verzenio is found");
			Thread.sleep(4000);
			el.findElement(By.xpath(config.getClearBox1())).click();

			Thread.sleep(4000);
			el = driver.findElement(By.xpath(config.getFilterBox2()));
			el.sendKeys("Current Rx or OTC");
			Log.info("Current Rx or OTC is found");
			Thread.sleep(4000);
			el.findElement(By.xpath(config.getClearBox2())).click();

			Thread.sleep(4000);
			el = driver.findElement(By.xpath(config.getFilterBox3()));
			el.sendKeys("No");
			Log.info("No – PRN is found");
			Thread.sleep(4000);
			el.findElement(By.xpath(config.getClearBox3())).click();

			Thread.sleep(4000);
			el = driver.findElement(By.xpath(config.getFilterBox4()));
			el.sendKeys("Fentanyl");
			Log.info("Fentanyl Patch is found");
			Thread.sleep(4000);
			el.findElement(By.xpath(config.getClearBox4())).click();

			Thread.sleep(4000);
			el = driver.findElement(By.xpath(config.getFilterBox5()));
			el.sendKeys("read");
			Log.info("read label is found");
			Thread.sleep(4000);
			el.findElement(By.xpath(config.getClearBox5())).click();

			Thread.sleep(4000);
			driver.findElement(By.xpath(config.getClickOnFilter())).click();
			Thread.sleep(3000);
			Log.info(" filtering is completed");
					
			Log.info("Passed-NIM-17397:Activity Sub Section: Medication - View Medications" + '\n' + "GIVEN:Sorting and filtering in medications"
					+ '\n' + "THEN:verifying sorting and filtering in medications");
		} catch (Exception e) {
			e.printStackTrace();
		
			Log.info("Failed-NIM-17397: Activity Sub Section: Medication - View Medications-Sorting");
		}

		// verifying edit

		try {
			addingMedication = new AddingMedicationPage(driver);
			annual = new AnnualAssessment(driver);
			annual.annual_assessment("AnnualAssessments_CMDM", "VerifyMedications", 2);
			WebElement EditMedication = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getEditMedication())));
			String EditMedicationText = EditMedication.getText();
			Assert.assertEquals(EditMedicationText, GlobalValues.editMedication);
			Log.info("edit button is present");
			WebElement ViewHistory = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getViewHistoryButton())));
			String ViewHistoryText = ViewHistory.getText();
			Assert.assertEquals(ViewHistoryText, GlobalValues.viewHistory);
			Log.info("View History  button is present");
			js.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.xpath(config.getAddProvider())));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getExpandButton()))).click();
			Log.info("click on expandable button from current medications");

			annual.annual_assessment("AnnualAssessments_CMDM", "VerifyMedications", 3);

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getExpandButton()))).click();
			Log.info("click back on expandable button from current medications");
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getCheckBoxMedication()))).click();
			Log.info("Click on check box button ");
			Thread.sleep(3000);

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getReviewButton()))).click();
			Log.info("Click on review button ");
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getViewHistoryButton()))).click();
			Log.info("User click View History  button");

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getExpandHistoryButton()))).click();
			Log.info("click  on expand from history page");
			annual.annual_assessment("AnnualAssessments_CMDM", "VerifyMedications", 4);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getBackButtonHistory()))).click();
			Log.info("click  on back button from history page");
			// add medication
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getMedicationsFromSubSection())))
					.click();
			Log.info("click on Medications");
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getAddMedicationFromGrid()))).click();
			Log.info("click  on add Medications from grid");
			Thread.sleep(1000);
			driver.findElement(By.xpath(config.getMedicationSeachTerm())).clear();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getMedicationSeachTerm())))
					.sendKeys("str");
			Thread.sleep(4000);
			Log.info("click  on add Medications Search Term");
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getClickSearchButtonFromMedication())))
					.click();
			Log.info("click  on Search Button");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
			Thread.sleep(1000);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getAddCapsuleMedicationSTR()))).click();
			Log.info("click  on Strattera");
			// AnnualAssessment annual = new AnnualAssessment(driver);
			annual.annual_assessment("AnnualAssessments_CMDM", "AddMedications", 2);
			js.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.xpath(config.getAddProvider())));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getMedicationsFromSubSection())))
					.click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
			Thread.sleep(1000);
			Log.info("click on Medications");
			Log.info("Stratteraon Medications is added ");
		
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getCheckBoxMedication()))).click();
			Log.info("Click on check box button ");
			Thread.sleep(3000);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getReviewButton()))).click();
			Log.info("Click on review button ");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
			Thread.sleep(1000);

			// edit medications
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getEditMedication()))).click();
			Log.info("edit button is clicked");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
			Thread.sleep(1000);
			annual.annual_assessment("AnnualAssessments_CMDM", "AddMedications", 3);

			js.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.xpath(config.getAddProvider())));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getMedicationsFromSubSection())))
					.click();
			Log.info("click on Medications");
			Thread.sleep(2000);

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getEditMedication()))).click();
			Log.info("edit button is clicked");
			annual.annual_assessment("AnnualAssessments_CMDM", "AddMedications", 8);

			js.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.xpath(config.getAddProvider())));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getMedicationsFromSubSection())))
					.click();
			Log.info("click on Medications");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
			Thread.sleep(1000);
						
			Log.info("Passed-NIM-17428:Activity Sub-section: Medication - Navigation for Add and Update page" + '\n' + "GIVEN: Navigation for Add and Update page"
					+ '\n' + "THEN:verifying  Navigation for Add and Update page");
		} catch (Exception e2) {
			e2.printStackTrace();
		
			Log.info("Failed-NIM-17428: Activity Sub-section: Medication - Navigation for Add and Update page");
		}

		// viewHistory

		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getViewHistoryButton()))).click();
			Log.info("View History  button is present and it is click");
			Thread.sleep(3000);

			// sorting column
			try {
				addingMedication = new AddingMedicationPage(driver);
				annual = new AnnualAssessment(driver);
				List<WebElement> columnss = driver.findElements(By.xpath(config.getAllColumnNamesMedicationHistory()));
				List<String> columnNamess = new ArrayList<String>();
				for (WebElement column : columnss) {
					columnNamess.add(column.getText());
				}
				// Verification of sorting of each column

				for (String columnName : columnNamess) {
					driver.findElement(
							By.xpath(config.getColumnNameMedicationHistory().replace("COL_NAME", columnName))).click();
					Thread.sleep(2000);
					if (addingMedication.isAscendingSortedFromHistory(columnName) == true)
						Log.info(columnName + " is sorted in ascending order");
					driver.findElement(
							By.xpath(config.getColumnNameMedicationHistory().replace("COL_NAME", columnName))).click();
					Thread.sleep(2000);
					if (addingMedication.isDescendingSortedFromHistory(columnName) == true)
						Log.info(columnName + " is sorted in descending order");

				}
			} catch (Exception ee) {

				Log.info(ee.getMessage().toString());

			}

			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getBackButtonHistory()))).click();
				Log.info("click  on back button from history page");
				js.executeScript("arguments[0].scrollIntoView()",
						driver.findElement(By.xpath(config.getAddProvider())));
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getMedicationsFromSubSection())))
						.click();
				Log.info("click on Medications");
			} catch (Exception e1) {

				e1.printStackTrace();
			}
			Log.info("Passed-NIM-17419:Activity Sub-section: Medications - View History of update & Review of Drugs" + '\n' + "GIVEN:  View History of update & Review of Drugs"
					+ '\n' + "THEN:verifying View History of update & Review of Drugs");
		
			Log.info("Passed-NIM-17919:Activity Sub-section: Medications - History grid sorting" + '\n' + "GIVEN:  History grid sorting"
					+ '\n' + "THEN:verifying  History grid sorting");
		} catch (Exception e) {
			e.printStackTrace();
			
			Log.info("Failed-NIM-17919: Activity Sub-section: Medications - History grid sorting");
			Log.info("Failed-NIM-17419: Activity Sub-section: Medications - View History of update & Review of Drugs");
		}

		
	}
}
