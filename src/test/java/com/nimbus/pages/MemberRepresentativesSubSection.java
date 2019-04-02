package com.nimbus.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.ConfigReader;

public class MemberRepresentativesSubSection {
	WebDriver driver;
	Logger Log;
	ConfigReader config = new ConfigReader();
	WebElement accordionMemRepresntative;
	WebElement addMemReprentativesave;
	
	public MemberRepresentativesSubSection(WebDriver ldriver) {
		this.driver = ldriver;
	}

	public MemberRepresentativesSubSection(Logger lLog) {
		this.Log = lLog;
	}
	public void memRepresentativeSection(String TaskName) throws Exception{
		try{
		Log = Logger.getLogger("QuestionnairesSection.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		// Left Navigation
		Log.info("Click on Edit initialoutreach activity");
			
		ActivityPage addActivity = new ActivityPage(driver);
		addActivity.editInitialOutreachActivity(TaskName);
		List<WebElement> defaultSubActivitiesElementsFromUI = driver.findElements(By.xpath("//nm-section//a[@role = 'tab']//p-header//h2"));
		boolean isFound = false;
		
		for(int i=0; i<defaultSubActivitiesElementsFromUI.size(); i ++){
			if(defaultSubActivitiesElementsFromUI.get(i).getText().contains("Member Representatives")){
				isFound = true;
				break;
			}
		}
		
		if(isFound){
			Log.info("Verified Member Representatives sub-section displayed as a default Sub section in Activities");
			 accordionMemRepresntative = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getMemRepresentative_xpath())));
			accordionMemRepresntative.click();
			Log.info("Clicked on Member Representatives Accordian");
			Thread.sleep(GlobalValues.Sleep_wait_time);
		}
		WebElement addMemReprentative = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getAddRepresentative())));
		addMemReprentative.isDisplayed();
		Log.info("Add Representatives button displayed");
		Log.info("Passed-NIM-17531: Add Member Rep. sub-section in 4 Activities pages" + '\n' + "GIVEN:Add Member Rep. sub-section in 4 Activities pages"
				+ '\n' + "THEN:verifying Add Member Rep. sub-section in 4 Activities pages");
	
	}
			
	catch (Exception e) {
		Log.info("Failed-NIM-17531: Add Member Rep. sub-section in 4 Activities pages");
	}
}
	public void addMemberRepresentative() throws Exception{
		try{
		Log = Logger.getLogger("QuestionnairesSection.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		
		WebElement addMemReprentative = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getAddRepresentative())));
		addMemReprentative.click();
		WebElement memRepresentativeTitle = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getAddMemReprentativeTitle())));
		String actMemRepresentativeTitle=memRepresentativeTitle.getText();
		if(actMemRepresentativeTitle.equalsIgnoreCase("Add Member Representative")){
			Log.info("Add Member Representative page verified");
		}
		AnnualAssessment annual=new AnnualAssessment(driver);
		annual.annual_assessment("AnnualAssessments_CMDM", "AddMemRepresentative", 2);
//		Thread.sleep(2000);
		WebElement addphonetype = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getMemRepresntativeAddPhone())));
		addphonetype.click();
		Thread.sleep(GlobalValues.Sleep_wait_time);
		annual.annual_assessment("AnnualAssessments_CMDM", "AddMemRepresentativePhoneType", 2);
		
		 addMemReprentativesave = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getMemRepresentativeSave())));
		addMemReprentativesave.click();
		Thread.sleep(GlobalValues.Sleep_wait_time);
		WebElement activitylabel = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getActivitystartlabel())));
		activitylabel.isDisplayed();
		Log.info("Navigated to respective activity after adding Member Representative");
		Log.info("Passed-NIM-17737: Add Member Rep. in Activity" + '\n' + "GIVEN:Add Member Rep. in Activity"
				+ '\n' + "THEN:verifying Add Member Rep. in Activity");
		}
		
		catch (Exception e) {
			Log.info("Failed-NIM-17737:Add Member Rep. in Activity");
		}
				
	}
	public void editMemberRepresentative() throws Exception{
		try{
		Log = Logger.getLogger("QuestionnairesSection.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);	
		 accordionMemRepresntative = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getMemRepresentative_xpath())));
		accordionMemRepresntative.click();
		WebElement editbutton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getEditbutton())));
		editbutton.click();
		AnnualAssessment annual=new AnnualAssessment(driver);
		annual.annual_assessment("AnnualAssessments_CMDM", "AddMemRepresentative", 3);
		 addMemReprentativesave = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getMemRepresentativeSave())));
		addMemReprentativesave.click();
		Thread.sleep(GlobalValues.Sleep_wait_time);
		WebElement activitylabel = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getActivitystartlabel())));
		activitylabel.isDisplayed();
		Log.info("Navigated to respective activity after editing Member Representative");
		Log.info("Passed-NIM-17740: Edit Member Rep. within Sub Section" + '\n' + "GIVEN:Edit Member Rep. within Sub Section"
				+ '\n' + "THEN:verifying Edit Member Rep. within Sub Section");
		}
		
		catch (Exception e) {
			Log.info("Failed-NIM-17740:Edit Member Rep. within Sub Section");
		}
		
	}
}
