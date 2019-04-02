package com.nimbus.pages;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utility.ConfigReader;

public class RecordContact {

	WebDriver driver;
	Logger Log;
	ConfigReader config = new ConfigReader();

	public RecordContact(WebDriver ldriver) {
		this.driver = ldriver;
	}

	public RecordContact(Logger lLog) {
		this.Log = lLog;
	}

	public void recordCreation(String submit) throws Exception {
		Log = Logger.getLogger("RecordContact.class");
		PropertyConfigurator.configure("log4j.properties");
		AnnualAssessment annual = new AnnualAssessment(driver);
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		try {
			Log.info("click on record contact");
			WebElement recordcontacticon = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getRecordCotactPath())));
			recordcontacticon.click();
			Thread.sleep(3000);
			WebElement Captureprogram = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getCaptureProgramName())));
			String programname = Captureprogram.getText();
			Log.info("Program name---" + programname);
			if (programname.isEmpty()) {
				Log.info("Program name not verified");
			} else {
				Log.info("Program name verified");
			}

			annual.annual_assessment("AnnualAssessments_CMDM", "RecordContact", 2);
			if (submit.equals("SubmitandNew")) {
				WebElement submitandnew = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getSubmitandAddNewPath())));
				submitandnew.click();
				Log.info("Record added after SubmitandNew ");
				Thread.sleep(3000);
				verifyPageAfterRecord();
			} else if (submit.equals("SubmitContact")) {
				WebElement submitcontact = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getSubmitContactPath())));
				submitcontact.click();
				Log.info("Record added after SubmitContact");

			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.info(e);
		}
	}

	public void verifyPageAfterRecord() {
		Log = Logger.getLogger("RecordContact.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		try {
			Log.info("Verifying Contact Details Page");
			WebElement contactdetails = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getContatDetailsPath())));
			String contactdetails1 = contactdetails.getText();
			Assert.assertEquals(contactdetails1, "Contact Details");
			Log.info("Contact Details Page verified after clicking submit and new button");
			Log.info("Verifying Record Contact Form");
			WebElement submitandnewbutton = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getSubmitandAddNewPath())));

			if (submitandnewbutton.isEnabled()) {
				Log.info("Contact Record form is not cleared");
			} else {
				Log.info("Contact Record form is cleared");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.info(e);
		}

	}

	public void verifydropdownOptions() throws Exception {
		Global_Method GM = PageFactory.initElements(driver, Global_Method.class);
		List<String> actualoptions = null;
		String listVal = null;
		try {
			for (int i = 1; i <= 5; i++) {
				WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

				WebElement ContactRecordDrop = wait.until(ExpectedConditions.elementToBeClickable(
						By.xpath("(//label[contains(text(),'Please Select...')])[" + i + "]//following-sibling::div")));
				ContactRecordDrop.click();
				Thread.sleep(2000);
				List<WebElement> options = driver.findElements(By.xpath(
						"(//label[contains(text(),'Please Select...')])[" + i + "]//following-sibling::div//li//span"));
				ArrayList<String> expctedoptions = new ArrayList<String>();
				for (int j = 0; j < options.size(); j++) {
					listVal = options.get(j).getText();
					Log.info("options---" + options.get(j).getText());
					expctedoptions.add(listVal);
					Log.info("Expected Options---" + expctedoptions);

				}
				if (i == 1) {
					actualoptions = GlobalValues.RecordContactPorposeofCallOptions;
				} else if (i == 2) {
					actualoptions = GlobalValues.RecordContactCallDirectionOptions;
				} else if (i == 3) {
					actualoptions = GlobalValues.RecordContactContactTypeOptions;
				} else if (i == 4) {
					actualoptions = GlobalValues.RecordContactCommunicationTypeOptions;
				} else {
					actualoptions = GlobalValues.RecordContactContactOutcomeOptions;
				}
				Log.info("Atual options---" + actualoptions);
				GM.validateDropDownOptions(actualoptions, expctedoptions);
				WebElement ContactRecordDrop1 = wait.until(ExpectedConditions.elementToBeClickable(
						By.xpath("(//label[contains(text(),'Please Select...')])[" + i + "]//following-sibling::div")));
				ContactRecordDrop1.click();

			}

		} catch (Exception e) {
			e.printStackTrace();
			Log.info(e);
		}

	}
}
