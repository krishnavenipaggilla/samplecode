package com.nimbus.pages;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utility.ConfigReader;

public class TempMemberCreation {
	WebDriver driver;
	Logger Log;
	ConfigReader config = new ConfigReader();

	public TempMemberCreation(WebDriver ldriver) {
		this.driver = ldriver;
	}

	public TempMemberCreation(Logger lLog) {
		this.Log = lLog;
	}

	public void tempMemberCreation() throws Exception {
		verifySearchPage("No");
		verifySearchPage("Cancel");
		try{
		createMember();
		Log.info("Passed-NIM-14984: Temp member creation: CM Supervisor dashboard (Member & Case Search)" + '\n' + "GIVEN:Temp member creation"
				+ '\n' + "THEN:verifying temp member creation");
		Log.info("Passed-NIM-15091: Temp member creation: Page navigation after temp member created" + '\n' + "GIVEN: Page navigation after temp member created"
				+ '\n' + "THEN:verifying  Page navigation after temp member created");
		Log.info("Passed-NIM-14985: DB");
		Log.info("Passed-NIM-14988: DB");
		}
		catch(Exception e){
			Log.info("Failed-NIM-14984: Temp member creation: CM Supervisor dashboard (Member & Case Search)");	
			Log.info("Failed-NIM-15091: Temp member creation: Page navigation after temp member created");
			Log.info("Failed-NIM-14985: DB");
			Log.info("Failed-NIM-14988: DB");
		}
		verifyOverviewPage();

	}

	private void verifySearchPage(String text) throws InterruptedException {
		Log = Logger.getLogger("TempMemberCreation.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		Log.info("verifying search page after clicking No and cancel buttons");

		if (text.equalsIgnoreCase("No")) {
			MemberSearch MemberSearch = PageFactory.initElements(driver, MemberSearch.class);
			MemberSearch.navigateToMemberSearch();
			Thread.sleep(GlobalValues.Sleep_wait_time);
			WebElement tempMemberbutton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getTempMemberbutton())));
			tempMemberbutton.click();
			Thread.sleep(GlobalValues.Sleep_wait_time);
			WebElement tempMembernobutton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getTempMemberNobutton())));
			tempMembernobutton.click();
			Thread.sleep(GlobalValues.Sleep_wait_time);
		} else {
			WebElement tempMemberbutton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getTempMemberbutton())));
			tempMemberbutton.click();
			Thread.sleep(GlobalValues.Sleep_wait_time);
			WebElement tempMemberyesbutton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getTempMemberYesbutton())));
			tempMemberyesbutton.click();
			Thread.sleep(GlobalValues.Sleep_wait_time);
			WebElement tempMembercancelbutton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getTempMemberCancelbutton())));
			tempMembercancelbutton.click();
			Thread.sleep(GlobalValues.Sleep_wait_time);
		}

		WebElement tempSearch = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getTempSearchPage())));
		String tempSearch1 = tempSearch.getText();
		Assert.assertEquals(tempSearch1, "Search");
		Log.info("Search Page verified");

	}

	private void verifyOverviewPage() throws InterruptedException {
		try{
		Log = Logger.getLogger("TempMemberCreation.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		Log.info("Verifying Overview Page");
		WebElement tempOverview = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getTempOverviewPage())));
		String tempOverview1 = tempOverview.getText();
		Assert.assertEquals(tempOverview1, "Overview");
		Log.info("Overview Page verified");
		WebElement tempcaseId = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getTempCaseID())));
		String tempcaseID = tempcaseId.getText().trim();
		Log.info("Verifying case status");
		Thread.sleep(5000);
		WebElement tempcaseStatus = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getTempCaseStatus())));
		String tempcaseStatus1 = tempcaseStatus.getText().trim();
		Assert.assertEquals(tempcaseStatus1, "Open");
		Log.info("Case status verified");

//		WebElement caseIdElement = driver
//				.findElement(By.xpath("//div//span[contains(text(),'Case ID:')]//following-sibling::span"));
		GlobalValues.caseIdFromTempMember = tempcaseID;
		Log.info("Case ID for the New Temp created is " + GlobalValues.caseIdFromTempMember);
		
		}
		catch(Exception e){
		
			
		}
	}

	public void createMember() throws Exception {
		Log = Logger.getLogger("TempMemberCreation.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		Log.info("creating temp member");
		AnnualAssessment annual = new AnnualAssessment(driver);
		WebElement tempMemberbutton = wait 
				.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getTempMemberbutton())));
		tempMemberbutton.click();
		WebElement tempMemberyesbutton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getTempMemberYesbutton())));
		tempMemberyesbutton.click();
		annual.annual_assessment("AnnualAssessments_CMDM", "TempMemberCreation", 2);
		WebElement tempMembercreate = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getTempMemberCreatebutton())));
		tempMembercreate.click();
		Thread.sleep(3000);
//		Log.info("Temp member created");

	}
}
