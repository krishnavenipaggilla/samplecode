package com.nimbus.pages;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.ConfigReader;
/* Author : Samba
 * Class : Provider
 * Description :Add Provider, Edit provider from both Individual and Org Provider
 */
public class AddProviderAction {
	
	WebDriver driver;
	Logger Log;
	ConfigReader config = new ConfigReader();

	public AddProviderAction(WebDriver ldriver) {
		this.driver = ldriver;
	}

	public AddProviderAction(Logger lLog) {
		this.Log = lLog;
	}
	
	public void addIndividualProviderFromCareTeamPage1() throws Exception{
		Log = Logger.getLogger("AddProviderAction.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		Global_Method gm = new Global_Method(driver);
		LeftNavigationLink lnk = new LeftNavigationLink(driver);
		lnk.clickCareTeam();
		
		Log.info("creating Individual Provider from care team");
		
		AnnualAssessment annual = new AnnualAssessment(driver);
		
		driver.findElement(By.xpath(config.getAddProviderFromCareTeamXpath())).click();
		driver.findElement(By.xpath(config.getCareTeamRoleFromCareTeam())).click();
		driver.findElement(By.xpath(config.getCareTeamChoice())).click();
		driver.findElement(By.xpath(config. getCreateManualFromCareTeamRole())).click();
		
		annual.annual_assessment("AnnualAssessments_CMDM", "CareTeamProviderIndividual", 2);
		driver.findElement(By.xpath(config.getSubmitButtonFromCareTeamXpath())).click();
		
		Log.info("IndividualProvider Care team is created");
		
		//Edit Individual Provider
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getEditButtonFromCareTeamXpath()))).click();
		annual.annual_assessment("AnnualAssessments_CMDM", "CareTeamProviderIndividual", 3);
		driver.findElement(By.xpath(config.getSubmitButtonFromCareTeamXpath())).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getExpandButtonFromCareTeamXpath()))).click();
		
		Thread.sleep(4000);
		
		//Verify Individual Provider
		FormVerification verification = new FormVerification(driver);
		verification.verifyFromExcel("AnnualAssessments_CMDM", "CareTeamProviderIndividual", 3);
		
		//Edit Individual Provider
		
		      // LeftNavigationLink lnk1 = new LeftNavigationLink(driver);
		      // lnk1.clickCareTeam();
//				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getEditButtonFromCareTeamXpath()))).click();
//				driver.findElement(By.xpath(config.getProviderIDFromCareTeamXpath())).clear();
//				driver.findElement(By.xpath(config.getProviderIDFromCareTeamXpath())).sendKeys(gm.readFromExcel("AnnualAssessments_CMDM", "CareTeamProviderIndividual", 3, "Provider ID"));
//				driver.findElement(By.xpath(config.getOrganizationNameFromCareTeamXpath())).clear();
//				driver.findElement(By.xpath(config.getOrganizationNameFromCareTeamXpath())).sendKeys(gm.readFromExcel("AnnualAssessments_CMDM", "CareTeamProviderIndividual", 3, "Organization Name"));
//				driver.findElement(By.xpath(config.getTitleDropDownFromCareTeamXpath())).click();
//				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getTitleChoiceFromCareTeamXpath().replace("<DISPLAYED_TEXT>", gm.readFromExcel("AnnualAssessments_CMDM", "CareTeamProviderIndividual", 3, "Title"))))).click();
//				driver.findElement(By.xpath(config.getContactNameFromCareTeamXpath())).clear();
//				driver.findElement(By.xpath(config.getContactNameFromCareTeamXpath())).sendKeys(gm.readFromExcel("AnnualAssessments_CMDM", "CareTeamProviderIndividual", 3, "Contact Name"));
//				driver.findElement(By.xpath(config.getContactTitleFromCareTeamXpath())).clear();
//				driver.findElement(By.xpath(config.getContactTitleFromCareTeamXpath())).sendKeys(gm.readFromExcel("AnnualAssessments_CMDM", "CareTeamProviderIndividual", 3, "Contact Title"));
//				driver.findElement(By.xpath(config.getContactByFromCareTeamDropDownOrganization())).click();
//				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getContactByChoiceFromCareTeamOrganization().replace("<DISPLAYED_TEXT>", gm.readFromExcel("AnnualAssessments_CMDM", "CareTeamProviderIndividual", 3, "Contact By"))))).click();
				//driver.findElement(By.xpath(config.getSubmitButtonFromCareTeamXpath())).click();
		
		
	}
	
	 public void addOrganizationProviderFromCareTeamPage() throws Exception{
		 Log = Logger.getLogger("AddProviderAction.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			Global_Method gm = new Global_Method(driver);

			Log.info("creating Organization Provider from care team");
			
			AnnualAssessment annual = new AnnualAssessment(driver);
			
			driver.findElement(By.xpath(config.getAddProviderFromCareTeamXpath())).click();
			driver.findElement(By.xpath(config.getCareTeamRoleFromCareTeam())).click();
			driver.findElement(By.xpath(config.getCareTeamChoice())).click();
			driver.findElement(By.xpath(config. getCreateManualFromCareTeamRole())).click();
			
			annual.annual_assessment("AnnualAssessments_CMDM", "CareTeamProviderOrganization", 2);
			driver.findElement(By.xpath(config.getSubmitButtonFromCareTeamXpath())).click();
			
			Log.info("OrganizationProvider Care team is created");
			
			//Edit Organization Provider
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getEditButtonFromCareTeamXpath()))).click();
			annual.annual_assessment("AnnualAssessments_CMDM", "CareTeamProviderOrganization", 3);
			driver.findElement(By.xpath(config.getSubmitButtonFromCareTeamXpath())).click();
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getExpandButtonFromCareTeamXpath()))).click();
			
			Thread.sleep(4000);
			
			//Verify Organization Provider
			
			FormVerification verification = new FormVerification(driver);
			verification.verifyFromExcel("AnnualAssessments_CMDM", "CareTeamProviderOrganization", 3);
			
			/*//Edit Organization Provider
		
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getEditButtonFromCareTeamXpath()))).click();
				driver.findElement(By.xpath(config.getProviderIDFromCareTeamXpath())).clear();
				driver.findElement(By.xpath(config.getProviderIDFromCareTeamXpath())).sendKeys(gm.readFromExcel("AnnualAssessments_CMDM", "CareTeamProviderOrganization", 3, "Provider ID"));
				driver.findElement(By.xpath(config.getOrganizationNameFromCareTeamXpath())).clear();
				driver.findElement(By.xpath(config.getOrganizationNameFromCareTeamXpath())).sendKeys(gm.readFromExcel("AnnualAssessments_CMDM", "CareTeamProviderOrganization", 3, "Organization Name"));
				driver.findElement(By.xpath(config.getCityFromCareTeamXpath())).clear();
				driver.findElement(By.xpath(config.getCityFromCareTeamXpath())).sendKeys(gm.readFromExcel("AnnualAssessments_CMDM", "CareTeamProviderOrganization", 3, "Contact Name"));
				driver.findElement(By.xpath(config.getContactNameFromCareTeamXpath())).clear();
				driver.findElement(By.xpath(config.getContactNameFromCareTeamXpath())).sendKeys(gm.readFromExcel("AnnualAssessments_CMDM", "CareTeamProviderOrganization", 3, "Contact Name"));
				driver.findElement(By.xpath(config.getContactTitleFromCareTeamXpath())).clear();
				driver.findElement(By.xpath(config.getContactTitleFromCareTeamXpath())).sendKeys(gm.readFromExcel("AnnualAssessments_CMDM", "CareTeamProviderOrganization", 3, "Contact Title"));
				driver.findElement(By.xpath(config.getContactByFromCareTeamDropDownOrganization())).click();
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getContactByChoiceFromCareTeamOrganization().replace("<DISPLAYED_TEXT>", gm.readFromExcel("AnnualAssessments_CMDM", "CareTeamProviderOrganization", 3, "Contact By"))))).click();
				driver.findElement(By.xpath(config.getSubmitButtonFromCareTeamXpath())).click();*/
				
			
}
	 
	 public void addIndividualProviderInitialOutreach() throws Exception{
			Log = Logger.getLogger("AddProviderAction.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			Global_Method gm = new Global_Method(driver);
			LeftNavigationLink lnk = new LeftNavigationLink(driver);
			lnk.clickMemberActionCenter();
			driver.findElement(By.xpath(config.getActivitiesUnderMemberActionCenter())).click();
			Log.info("creating Individual Provider from Initial Outreach");
			JavascriptExecutor js = (JavascriptExecutor)driver;
			AnnualAssessment annual = new AnnualAssessment(driver);
			//driver.findElement(By.xpath(config.getActivitiesUnderMemberActionCenter())).click();
			driver.findElement(By.xpath(config.getEditMemberActionCenter())).click();
			js.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.xpath(config.getClickOnProvider())));
			//driver.findElement(By.xpath(config.getClickOnProvider())).click();
			if(!driver.findElements(By.xpath(config.getExpandProvider())).isEmpty()){
				driver.findElement(By.xpath(config.getExpandProvider())).click();
			}
			
			Thread.sleep(10000);
			
			js.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.xpath(config.getAddProvider())));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getAddProvider()))).click();
			driver.findElement(By.xpath(config. getCreateManualFromCareTeamRole())).click();
			annual.annual_assessment("AnnualAssessments_CMDM", "InitialOutProviderIndividual", 2);
			driver.findElement(By.xpath(config.getSubmitButtonFromCareTeamXpath())).click();
			
			Log.info("IndividualProvider from Initial Outreach  is created");
		
			//Edit Individual Provider
			
			//driver.findElement(By.xpath(config.getEditMemberActionCenter())).click();
			js.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.xpath(config.getClickOnProvider())));
			//driver.findElement(By.xpath(config.getClickOnProvider())).click();
			if(!driver.findElements(By.xpath(config.getExpandProvider())).isEmpty()){
				driver.findElement(By.xpath(config.getExpandProvider())).click();
			}
			js.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.xpath(config.getEditButtonFromInitialOutreachXpath())));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getEditButtonFromInitialOutreachXpath()))).click();
			annual.annual_assessment("AnnualAssessments_CMDM", "InitialOutProviderIndividual", 3);
			driver.findElement(By.xpath(config.getSubmitButtonFromCareTeamXpath())).click();
			
			//Verify Individual Provider
			
			//driver.findElement(By.xpath(config.getEditMemberActionCenter())).click();
			js.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.xpath(config.getClickOnProvider())));
			//driver.findElement(By.xpath(config.getClickOnProvider())).click();
			if(!driver.findElements(By.xpath(config.getExpandProvider())).isEmpty()){
				driver.findElement(By.xpath(config.getExpandProvider())).click();
			}
			js.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.xpath(config.getExpandButtonFromInitialOutreachXpath())));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getExpandButtonFromInitialOutreachXpath()))).click();
			
			Thread.sleep(4000);
			
			
			FormVerification verification = new FormVerification(driver);
			verification.verifyFromExcel("AnnualAssessments_CMDM", "InitialOutProviderIndividual", 3);
	 }
	 
	 public void addOrganisationProviderInitialOutreach() throws Exception{
			Log = Logger.getLogger("AddProviderAction.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			
			JavascriptExecutor js = (JavascriptExecutor)driver;
			AnnualAssessment annual = new AnnualAssessment(driver);
			/*Global_Method gm = new Global_Method(driver);
			LeftNavigationLink lnk = new LeftNavigationLink(driver);
			lnk.clickMemberActionCenter();
			driver.findElement(By.xpath(config.getActivitiesUnderMemberActionCenter())).click();
			Log.info("creating Organisation Provider from Initial Outreach");
			
			
			//driver.findElement(By.xpath(config.getActivitiesUnderMemberActionCenter())).click();
			driver.findElement(By.xpath(config.getEditMemberActionCenter())).click();*/
			
			js.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.xpath(config.getClickOnProvider())));
			//driver.findElement(By.xpath(config.getClickOnProvider())).click();
			if(!driver.findElements(By.xpath(config.getExpandProvider())).isEmpty()){
				driver.findElement(By.xpath(config.getExpandProvider())).click();
			}
			
			
			js.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.xpath(config.getAddProvider())));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getAddProvider()))).click();
			driver.findElement(By.xpath(config. getCreateManualFromCareTeamRole())).click();
			annual.annual_assessment("AnnualAssessments_CMDM", "InitialOutProviderOrganization", 2);
			driver.findElement(By.xpath(config.getSubmitButtonFromCareTeamXpath())).click();
			
			Log.info("Organisation Provider from Initial Outreach  is created");
		
			//Edit Organisation Provider
			
			//driver.findElement(By.xpath(config.getEditMemberActionCenter())).click();
			js.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.xpath(config.getClickOnProvider())));
			//driver.findElement(By.xpath(config.getClickOnProvider())).click();
			if(!driver.findElements(By.xpath(config.getExpandProvider())).isEmpty()){
				driver.findElement(By.xpath(config.getExpandProvider())).click();
				Thread.sleep(1000);
			}
			js.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.xpath(config.getEditButtonFromInitialOutreachXpath())));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getEditButtonFromInitialOutreachXpath()))).click();
			annual.annual_assessment("AnnualAssessments_CMDM", "InitialOutProviderOrganization", 3);
			driver.findElement(By.xpath(config.getSubmitButtonFromCareTeamXpath())).click();
			
			//Verify Organisation Provider
			
			//driver.findElement(By.xpath(config.getEditMemberActionCenter())).click();
			js.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.xpath(config.getClickOnProvider())));
			//driver.findElement(By.xpath(config.getClickOnProvider())).click();
			if(!driver.findElements(By.xpath(config.getExpandProvider())).isEmpty()){
				driver.findElement(By.xpath(config.getExpandProvider())).click();
			}
			js.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.xpath(config.getExpandButtonFromInitialOutreachXpath())));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getExpandButtonFromInitialOutreachXpath()))).click();
			
			Thread.sleep(4000);
			
			
			FormVerification verification = new FormVerification(driver);
			verification.verifyFromExcel("AnnualAssessments_CMDM", "InitialOutProviderOrganization", 3);
	 }
	 
}

