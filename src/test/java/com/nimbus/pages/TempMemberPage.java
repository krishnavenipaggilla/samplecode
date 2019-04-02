package com.nimbus.pages;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.nimbus.pages.AnnualAssessment;
import com.nimbus.pages.GlobalValues;
import junit.framework.Assert;
import utility.ConfigReader;
import utility.UtilityClass;

//Author:- AF86867
//Epic-NIM-17181
public class TempMemberPage extends UtilityClass {
	ConfigReader config = new ConfigReader();

	WebDriver driver;
	Logger Log;

	public TempMemberPage(WebDriver ldriver) {
		this.driver = ldriver;
	}

	public TempMemberPage(Logger lLog) {
		this.Log = lLog;
	}

	public void ClickMemberSearch() {
		try {
			Log = Logger.getLogger("MemberActionCenterPage.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			WebElement memberSearchLink = wait

					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getMemberSerchClickXpath())));
			memberSearchLink.click();
			Log.info("Clicked on Member Search on Home Page");
			Thread.sleep(GlobalValues.Sleep_wait_time);

		} catch (Exception e) {
			Log.info(e);

		}
	}

	public void ClickCreateTempMemberBtn() {

		try {
			Log = Logger.getLogger("MemberActionCenterPage.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

			WebElement tempMemberbutton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getTempMemberbutton())));
			tempMemberbutton.click();
			Log.info("Clicked on Create Temp Member button");

			WebElement WarningMessage = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getWarningVerbiage())));

			String Message = WarningMessage.getText();
			Log.info("Warning message displayed is:" + Message);
			Assert.assertEquals(Message, "Temp Member Confirmation");
			Log.info("Warning Verbiage got displayed");

		} catch (Exception e) {
			Log.info(e);

		}

	}

	public void ClickNoOnTempMemberConfirmation() {

		try {
			Log = Logger.getLogger("MemberActionCenterPage.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

			WebElement tempMembernobutton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getTempMemberNobutton())));
			tempMembernobutton.click();
			Thread.sleep(3000);

			WebElement tempSearch = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getTempSearchPage())));
			String tempSearch1 = tempSearch.getText();
			Assert.assertEquals(tempSearch1, "Search");
			Log.info("Upon clicking No button, Navigated back to Member & Case Search screen");

		} catch (Exception e) {
			Log.info(e);

		}

	}

	public void ClickYesOnTempMemberConfirmation() {
		try {

			Log = Logger.getLogger("MemberActionCenterPage.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

			WebElement tempMemberyesbutton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getTempMemberYesbutton())));
			Thread.sleep(GlobalValues.Sleep_wait_time);
			tempMemberyesbutton.click();
			Thread.sleep(3000);

			WebElement CreateTempMember = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getCreateTempMemberText())));
			Assert.assertEquals(CreateTempMember.getText(), "Create Temp Member");
			Log.info("Create Temp Member modal window got displayed");

		} catch (Exception e) {
			Log.info(e);

		}

	}

	public void ClickCancelOnCreateTempMember() {
		try {

			Log = Logger.getLogger("MemberActionCenterPage.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

			WebElement tempMembercancelbutton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getTempMemberCancelbutton())));
			tempMembercancelbutton.click();
			Thread.sleep(GlobalValues.Sleep_wait_time);
			Log.info("Clicked on Cancel button after entering the data on Temp member model window");

			WebElement tempSearch = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getTempSearchPage())));
			String tempSearch1 = tempSearch.getText();
			Assert.assertEquals(tempSearch1, "Search");
			Log.info("Landed back on back to Member & Case Search page");

		}

		catch (Exception e) {
			Log.info(e);

		}

	}

	public void CreateTempMember() {
		try {

			Log = Logger.getLogger("MemberActionCenterPage.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			AnnualAssessment annual = new AnnualAssessment(driver);

			annual.annual_assessment("AnnualAssessments_CMDM", "TempMemberCreation", 3);
			Log.info("Scenario 2 is passed");
			annual.annual_assessment("AnnualAssessments_CMDM", "TempMemberCreation", 4);
			Log.info("Scenario 3 is passed");
			annual.annual_assessment("AnnualAssessments_CMDM", "TempMemberCreation", 5);
			Log.info("Scenario 4 is passed");
			annual.annual_assessment("AnnualAssessments_CMDM", "TempMemberCreation", 2);
			Log.info("Scenario 1 is passed");

			Log.info("Entered all the mandatory fields......Create button is Enabled.");
			Thread.sleep(GlobalValues.Sleep_wait_time);
			WebElement tempMembercreate = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getTempMemberCreatebutton())));
			tempMembercreate.click();
			Log.info("Temp member created");
			
			WebElement tempcaseId = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getTempCaseID())));
			String tempcaseID = tempcaseId.getText().trim();
			GlobalValues.caseIdFromTempMember = tempcaseID;

		} catch (Exception e) {
			Log.info(e);
			e.printStackTrace();

		}

	}
}
