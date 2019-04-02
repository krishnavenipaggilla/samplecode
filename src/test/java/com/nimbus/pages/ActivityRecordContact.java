package com.nimbus.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utility.ConfigReader;
import utility.WebActions;

public class ActivityRecordContact {
	WebDriver driver;
	Logger Log;
	ConfigReader config = new ConfigReader();

	public ActivityRecordContact(WebDriver ldriver) {
		this.driver = ldriver;
	}

	public ActivityRecordContact(Logger lLog) {
		this.Log = lLog;
	}
	
	public void activityRecordContact(String TaskName) throws Exception{
		try{
		Log = Logger.getLogger("ActivityProviderSubSection.class");
		PropertyConfigurator.configure("log4j.properties");
		AnnualAssessment annual = new AnnualAssessment(driver);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebActions webact=new WebActions();
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		LeftNavigationLink leftNav = PageFactory.initElements(driver, LeftNavigationLink.class);
		Log.info("Click on Edit initialoutreach activity");
		ActivityPage addActivity = new ActivityPage(driver);
		addActivity.editInitialOutreachActivity(TaskName);
		List<WebElement> defaultSubActivitiesElementsFromUI = driver
				.findElements(By.xpath("//nm-section//a[@role = 'tab']//p-header//h2"));
		boolean isFound = false;

		for (int i = 0; i < defaultSubActivitiesElementsFromUI.size(); i++) {
			if (defaultSubActivitiesElementsFromUI.get(i).getText().contains("Record Contact")) {
				isFound = true;
				break;
			}
		}
		if (isFound) {
			Log.info("Verified Record Contact subsection displayed as a default Sub section in Activities");
			Log.info("Click on Record contact accordion");
			WebElement recContactAccordianElement = wait.until(ExpectedConditions
					.elementToBeClickable(driver.findElement(By.xpath(config.getRecContAccordianElementXpath()))));
				recContactAccordianElement.click();
			Log.info("Click on record contact");
				Thread.sleep(GlobalValues.Sleep_wait_time);
		} else {

			driver.findElement(By.xpath("//button[@title = 'Add Sub Activity' and @type ='button']")).click();
			Log.info("Clicked on add subactivity Icon");
			String subActivityModelWindowHeader = driver
					.findElement(By.xpath("//p-header[contains(text(),'Add Sub Activity')]")).getText().trim();
			Assert.assertEquals(subActivityModelWindowHeader, "Add Sub Activity");

			driver.findElement(By.xpath("//label[contains(text(),'Record Contact')]//..//label")).click();
			Log.info("Adding Record Contact sub section from the modal window");
			Thread.sleep(GlobalValues.Sleep_wait_time);

			driver.findElement(By.xpath("//button[text()='Add']")).click();
			Log.info("Clicked on Add button of modal window");

			Thread.sleep(GlobalValues.Sleep_wait_time);

			Log.info("Click on Record Contact accordion");
			WebElement recContactAccordianElement = wait.until(ExpectedConditions
					.elementToBeClickable(driver.findElement(By.xpath(config.getRecContAccordianElementXpath()))));
				recContactAccordianElement.click();
			Log.info("Click on Record Contact");
			
			Thread.sleep(GlobalValues.Sleep_wait_time);

		}
		Log.info("Verifying required fields ");
		js.executeScript("window.scrollBy(0,1000)");
		if(webact.validateobjectmandatory(config.getPorposeofCallLabel(),driver)){
				Log.info("PorposeofCall field is mandatory has passed");
		 	}
		 	else{
		 
		 		Log.info("PorposeofCall field is not mandatory has failed");
		 		}
		
		if(webact.validateobjectmandatory(config.getCallDirectionLabel(),driver)){
			Log.info("CallDirection field is mandatory has passed");
	 	}
	 	else{
	 
	 		Log.info("CallDirection field is not mandatory has failed");
	 		}
		if(webact.validateobjectmandatory(config.getContactTypeLabel(),driver)){
			Log.info("ContactType field is mandatory has passed");
	 	}
	 	else{
	 
	 		Log.info("ContactType field is not mandatory has failed");
	 		}
		if(webact.validateobjectmandatory(config.getCommTypeLabel(),driver)){
			Log.info("Communication Type field is mandatory has passed");
	 	}
	 	else{
	 
	 		Log.info("Communication Type is not mandatory has failed");
	 		}
		if(webact.validateobjectmandatory(config.getContOutcomeLabel(),driver)){
			Log.info("Contact Outcome field is mandatory has passed");
	 	}
	 	else{
	 
	 		Log.info("Contact Outcome is not mandatory has failed");
	 		}
		
		
		
		if(TaskName.equalsIgnoreCase("Initial Outreach")){
		annual.annual_assessment("AnnualAssessments_CMDM", "RecordContact", 2);
		}
		else if(TaskName.equalsIgnoreCase("Case Closure")){
			annual.annual_assessment("AnnualAssessments_CMDM", "RecordContact", 6);
		}
		if(TaskName.equalsIgnoreCase("Initial Outreach")){
		js.executeScript("window.scrollBy(0,-800)");
				
		WebElement accordionIntroAndPermission = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getIntroandPermissionsaccordian())));
		accordionIntroAndPermission.click();
		annual.annual_assessment("AnnualAssessments_CMDM", "Intro_Permissions", 2);
		WebElement saveprogressIntialOutreach = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath(config.getIntialOutreachSaveProgress())));
		saveprogressIntialOutreach.click();
		Log.info("Clicked on Save Progress");
		Thread.sleep(3000);
		WebElement IntialOutreachOkbutton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getInitialOkbutton())));
		IntialOutreachOkbutton.click();
		js.executeScript("window.scrollBy(0,1000)");
		WebElement saveandendactivityIntialOutreach = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getSaveActivityButton())));
		saveandendactivityIntialOutreach.click();
		Thread.sleep(8000);
		WebElement okbutton=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getInitialOkbutton())));
		okbutton.click();
		Thread.sleep(GlobalValues.Sleep_wait_time);
		
			WebElement initialviewbutton=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getViewInitialoutreach())));	
			initialviewbutton.click();
			js.executeScript("window.scrollBy(0,1000)");
			WebElement recContactAccordianElement = wait.until(ExpectedConditions
					.elementToBeClickable(driver.findElement(By.xpath(config.getRecContAccordianElementXpath()))));
				recContactAccordianElement.click();
			Log.info("Click on record contact");
				Thread.sleep(GlobalValues.Sleep_wait_time);
				//To check whether the fields are read-only in the Activity View after Save/End Activity
				for(int i=0;i<GlobalValues.RecordContactSectionfields.size();i++)
				{
				 String fieldname = GlobalValues.RecordContactSectionfields.get(i);	
				
				 if(fieldname.contains("Purpose of Call") || fieldname.contains("Call Direction") || fieldname.contains("Contact Type") 
						 || fieldname.contains("Communication Type") || fieldname.contains("Contact Outcome")){
					 
					 	 if((webact.validateobjectreadonly(config.getlstbxRecContactXpath().replace("<fieldName>", fieldname), driver))==true){
					 		 Log.info(fieldname+ " is read-only");
					 		 Log.info("Test case to check that " +fieldname+ " is read-only has passed");
					 	 }
					 	 else{
					 		 Log.info(fieldname+ " is not read-only");
					 		 Log.info("Test case to check that " +fieldname+ " is not read-only has failed");
					 	 }
				 	}
				 else if(fieldname.contains("Contact Name") || fieldname.contains("Contact Phone")){
					 if((webact.validateobjectreadonly(config.getRecContacttextfieldsXpath().replace("<fieldName>", fieldname), driver))==true){
				 		 Log.info(fieldname+ " is read-only");
				 		 Log.info("Test case to check that " +fieldname+ " is read-only has passed");
				 	 }
				 	 else{
				 		 Log.info(fieldname+ " is not read-only");
				 		 Log.info("Test case to check that " +fieldname+ " is not read-only has failed");
				 	 }
				 	}
				 
				 else if(fieldname.contains("Member to Accomplish Prior to Next Contact")){
					 if(webact.validateobjectreadonly(config.getRecContacttextareafieldsXpath().replace("<fieldName>", fieldname), driver)==true){
				 		 Log.info(fieldname+ " is read-only");
				 		 Log.info("Test case to check that " +fieldname+ " is read-only has passed");
				 	 }
				 	 else{
				 		 Log.info(fieldname+ " is not read-only");
				 		 Log.info("Test case to check that " +fieldname+ " is not read-only has failed");
				 	 }
				 	}
				 	 
				}
		}
		Log.info("Passed-NIM-17361: Activity Sub-section: Create View for Record a Contact"
				+ '\n' + "GIVEN:Activity Sub-section: Create View for Record a Contact" + '\n'
				+ "THEN:Activity Sub-section: Create View for Record a Contact");
	
		}
		catch(Exception e){
			Log.info("Failed-NIM-17361: Activity Sub-section: Create View for Record a Contact");
				}
			
	}
	
	
	
	}
	


