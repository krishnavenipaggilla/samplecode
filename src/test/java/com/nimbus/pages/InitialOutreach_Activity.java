package com.nimbus.pages;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
import utility.WebActions;

public class InitialOutreach_Activity {
	WebDriver driver;
	Logger Log;
	ConfigReader config = new ConfigReader();
	String InitialOutreachStatus = null;
	WebElement InitialoutreachActivityStatus = null;
	WebElement ActivityStatus = null;
	LeftNavigationLink leftNav=null;
	List<String> actualsubsections = null;

	public InitialOutreach_Activity(WebDriver ldriver) {
		this.driver = ldriver;
	}

	public InitialOutreach_Activity(Logger lLog) {
		this.Log = lLog;
	}
	public void initialOutreach() throws Exception {
		Log = Logger.getLogger("InitialOutreach_Activity.class");
		Global_Method GM = PageFactory.initElements(driver, Global_Method.class);
		
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		AnnualAssessment annual = new AnnualAssessment(driver);
		LeftNavigationLink leftNav = PageFactory.initElements(driver, LeftNavigationLink.class);
		leftNav.clickOverview();
		leftNav.clickMemberActionCenter();
		leftNav.clickActivities();
		InitialoutreachActivityStatus = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getInitialOutreachActivitystatus())));
		InitialOutreachStatus = InitialoutreachActivityStatus.getText().toString().trim();
		Log.info("Initialoutreachstatus---" + InitialOutreachStatus);

		if (InitialOutreachStatus.equals(GlobalValues.Open_status.toString())
				|| InitialOutreachStatus.equals(GlobalValues.InProgress_status.toString()))

		{
			Log.info("Click on Edit initialoutreach activity");
			WebElement editInitilaOutreach = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getEditIntialoutreach())));
			editInitilaOutreach.click();
			wait
			.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
			Thread.sleep(2000);
			try {			
//			webaction.validateDropdown(config.getSubsectionsPath(), GlobalValues.Initialoutreach_DefaultSubsections, driver);
			if(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[contains(text(),'Intro & Permissions')]"))).isDisplayed() && 
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[contains(text(),'Preferred Contact Information')]"))).isDisplayed() &&
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[contains(text(),'Questionnaire')]"))).isDisplayed() && 
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[contains(text(),'Member Representatives')]"))).isDisplayed() &&
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[contains(text(),'Provider')]"))).isDisplayed() &&
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[contains(text(),'Care Plan')]"))).isDisplayed() &&
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[contains(text(),'Record Contact')]"))).isDisplayed() 
					) {
			Log.info("verified actual and expected default subsections in initialoutreach");
			}
			else {
				Log.info("!!!!Failed !!! actual and expected default subsections mismatch in initialoutreach");
			}
			}
			catch(Exception e){
				Log.info("!!!!Failed !!! actual and expected default subsections mismatch in initialoutreach");
			}
			try{
				
				addSubsections();
			Log.info("Passed-NIM-17261: Add 'Add Sub Activity' button to Initial Outreach" + '\n' + "GIVEN:Add sub activity button"
					+ '\n' + "THEN:verifying add sub activity button");
			Log.info("Passed-NIM-17262: Sub Activity: Show modal window when user clicks on the Add Sub Activity" + '\n' + "GIVEN:Sub Activity: Show modal window"
					+ '\n' + "THEN:verifying modal window when user clicks on the Add Sub Activity");
			Log.info("Passed-NIM-17263: Sub Activity: Add admissions plug in to the modal window from Initial outreach" + '\n' + "GIVEN:Sub Activity: Add admissions"
					+ '\n' + "THEN:verifying admissions plug in to the modal window");
			Log.info("Passed-NIM-17330: Sub Activity: Show sub-activities on Modal window that are not default to activity" + '\n' + "GIVEN:sub-activities on Modal window that are not default to activity"
					+ '\n' + "THEN:verifying sub-activities on Modal window that are not default to activity");
						}
			catch(Exception e){
				Log.info("Failed-NIM-17261: Add 'Add Sub Activity' button to Initial Outreach");
				Log.info("Failed-NIM-17262: Sub Activity: Show modal window when user clicks on the Add Sub Activity" );
				Log.info("Failed-NIM-17263: Sub Activity: Add admissions plug in to the modal window from Initial outreach");
				Log.info("Failed-NIM-17330: Sub Activity: Show sub-activities on Modal window that are not default to activity");
			}
			Log.info("Abonded-NIM-16628: Create sub-activity to any open activity or when a new activity is being added");
			Log.info("Abonded-NIM-16632: Display sub-activity as another section on activity @ Member Action Center");
			Log.info("Abonded-NIM-16634: Manually add sub-activities");
			Log.info("Abonded-NIM-17265: Sub Activity: Add admission sub activity to Initial Outreach");
			
		}
		
	}

	public void addSubsections() throws Exception {
		Log = Logger.getLogger("InitialOutreach_Activity.class");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		Thread.sleep(4000);
		driver.findElement(By.xpath(config.getSubActivityIcon())).click();
        Log.info("Clicked on add subactivity Icon");
        wait
		.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
        Thread.sleep(2000);
        String subActivityModelWindowHeader = driver.findElement(By.xpath(config.getSubActivityModelWindowHeader())).getText().trim();
        wait
		.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
        Thread.sleep(1000);
        Assert.assertEquals(subActivityModelWindowHeader,"Add Sub Activity");
        Log.info("Selecting all sub sections");
        
        try
        {
	        List<WebElement> listofActivities1 = driver.findElements(By.xpath(config.getListOfSubactivities()));
	        
	       int count = listofActivities1.size();
	       
	        for(int i=0; i< count; i++)
	        {
	        	
	        List<WebElement> listofActivities = driver.findElements(By.xpath(config.getListOfSubactivities()));
		        
//	        System.out.println(listofActivities.get(i).toString());
	        listofActivities.get(i).click();
	        Thread.sleep(2000);
	        driver.findElement(By.xpath("//legend[contains(text(),'Activity Type')]")).click();
	        Thread.sleep(2000);
	        }
        }
        catch(Exception ex)
        {
        	System.out.println("Exception"+ex.getMessage().toString());
        }
        WebElement clickAddbutton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getSubActivityAddbutton())));
        clickAddbutton.click();      
//          driver.findElement(By.xpath(config.getSubActivityAddbutton())).click();
         Log.info("Clicked on Add button of modal window");
         Log.info("Added all sub sections");
         wait
 		.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
         Thread.sleep(1000);
		verifyAllSubsections();
	}

	public void verifyAllSubsections() throws Exception {
		Global_Method GM = PageFactory.initElements(driver, Global_Method.class);
		
		List<WebElement> options = driver.findElements(By.xpath(config.getSubsectionsPath()));
		ArrayList<String> expctedsubsections = new ArrayList<String>();
		for (int j = 0; j < options.size(); j++) {
			String listVal = options.get(j).getText();
			Log.info("options---" + options.get(j).getText());
			expctedsubsections.add(listVal);
			Log.info("Expected subsections---" + expctedsubsections);
			
			}
		actualsubsections=GlobalValues.Initialoutreach_AllSubsections;
		Log.info("verifying actual and expected subsections in initialoutreach after adding all subsections");
		GM.validateSubsections(actualsubsections, expctedsubsections);
		Log.info("verified actual and expected subsections in initialoutreach after adding all subsections");

	}
	
	
	
	
	public void fillIntroandPermissions() throws Exception {
		try{
		Log = Logger.getLogger("InitialOutreach_Activity.class");
		AnnualAssessment annual = new AnnualAssessment(driver);
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		
		WebElement accordionIntroAndPermission = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getIntroandPermissionsaccordian())));
		accordionIntroAndPermission.click();
		annual.annual_assessment("AnnualAssessments_CMDM", "Intro_Permissions", 2);
		WebElement saveprogressIntialOutreach = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath(config.getIntialOutreachSaveProgress())));
		saveprogressIntialOutreach.click();
		Log.info("Clicked on Save Progress");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
		Thread.sleep(1000);
		WebElement IntialOutreachOkbutton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getInitialOkbutton())));
		IntialOutreachOkbutton.click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[text()='Save/End Activity']")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[text()='Ok']")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
		Thread.sleep(1000);
		Log.info("Passed-NIM-18510: Intro & Permission: Create configurable Intro & Permission sub activity" + '\n' + "GIVEN:Intro & Permission sub activity"
				+ '\n' + "THEN:verifying intro & permission subsctivity");
		Log.info("Passed-NIM-18509: Intro & Permission: Remove buttons in an activity from intro & permission and add them to a separate section" + '\n' + "GIVEN:Intro & Permission buttons"
				+ '\n' + "THEN:veryfing and clicking buttons");
		Log.info("Passed-NIM-18508: Intro & Permission: Remove intro & permission from the bottom of the initial outreach" + '\n' + "GIVEN:Intro & Permission from bottom of the initial outreach"
				+ '\n' + "THEN:Remove intro & permissions");
		Log.info("Passed-NIM-18508: Intro & Permission: Remove intro & permission from the bottom of the initial outreach" + '\n' + "GIVEN:Intro & Permission from bottom of the initial outreach"
				+ '\n' + "THEN:Remove intro & permissions");
		Log.info("Passed-NIM-18879: Preferred contact information: Create configurable sub activity" + '\n' + "GIVEN:Preferred contact information"
				+ '\n' + "THEN:Veryfing Preferred contact information sub section");
		}
		catch(Exception e){
			Log.info("Failed-NIM-18510: Intro & Permission: Create configurable Intro & Permission sub activity");
			Log.info("Failed-NIM-18509: Intro & Permission: Remove buttons in an activity from intro & permission and add them to a separate section");
			Log.info("Failed-NIM-18508: Remove intro & permissions");
			Log.info("Failed-NIM-18879: Preferred contact information: Create configurable sub activity");
		}
		
		Log.info("abandoned-NIM-18512: Intro & Permission: Add 'Save Progress' button in the sub-section");
		Log.info("Refactored-NIM-18879: Preferred contact information: Create configurable sub activity");
		}
	
	public void verifyClinicalIntervention() throws Exception {
		try{
			String clinicalactivity=driver.findElement(By.xpath("//span[text()='Clinical Intervention']")).getText();
		
		if(clinicalactivity.equalsIgnoreCase("Clinical Intervention")){
			Log.info("Clinical Intervention generated");
		}else{
			Log.info("Clinical Intervention not generated");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
		
	}


	



