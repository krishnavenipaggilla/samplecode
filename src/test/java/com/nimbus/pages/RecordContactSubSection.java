package com.nimbus.pages;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import excelReader.DataReaderExcel;
import utility.ConfigReader;
import utility.WebActions;

public class RecordContactSubSection {
	

	WebDriver driver;
	Logger Log;

	ConfigReader config = new ConfigReader();
	DataReaderExcel DataRead = new DataReaderExcel();

	public RecordContactSubSection(WebDriver ldriver) {
		this.driver = ldriver;

	}

	public RecordContactSubSection(Logger lLog) {
		this.Log = lLog;

	}

	//Record Contact section in Edit Activity of Initial Outreach, Care Coordination, Referral, Clinical Intervention and Case Closure
	public void verifyRecordContactSubActivity(String activityName) throws InterruptedException{
			LeftNavigationLink leftNavigation = PageFactory.initElements(driver, LeftNavigationLink.class);
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			AnnualAssessment ann = PageFactory.initElements(driver, AnnualAssessment.class);
			MemberActionCenter memberActionCenter = PageFactory.initElements(driver, MemberActionCenter.class);
			
			if(((driver.findElement(By.xpath(config.getActivitiesLink()))).isDisplayed())==false){
				leftNavigation.clickMemberActionCenter();
			}
			if(((driver.findElement(By.xpath(config.getActivitiesLink()))).isSelected())==false){
				leftNavigation.clickActivities();
			}

			try {	
			//for(int i=0;i<GlobalValues.RecordContactActivityType.size();i++){
			//	String activityName = GlobalValues.RecordContactActivityType.get(i);

				switch(activityName){
				
				case "Initial Outreach":
					String MemtoAccompInOut = verifyEditActivity(activityName);
					Thread.sleep(GlobalValues.Sleep_wait_time);
					Log.info("Successfully added Record Contact details from " +activityName+ " page");
					Log.info("Verify added Record Contact from " + activityName + " page");
					verifyRecordContactfromActivityPage(activityName,MemtoAccompInOut);
					break;
				
				case "Clinical Intervention":
					String MemtoAccompClinInt = verifyEditActivity(activityName);
					Thread.sleep(GlobalValues.Sleep_wait_time);
					Log.info("Successfully added Record Contact details from " +activityName+ " page");
					Log.info("Verify added Record Contact from " + activityName + " page");
					verifyRecordContactfromActivityPage(activityName,MemtoAccompClinInt);
					break;
					
				
				case "Referral":
					String MemtoAccompReferral = verifyEditActivity(activityName);
					Thread.sleep(GlobalValues.Sleep_wait_time);
					Log.info("Successfully added Record Contact details from " +activityName+ " page");
					Log.info("Verify added Record Contact from " + activityName + " page");
					verifyRecordContactfromActivityPage(activityName,MemtoAccompReferral);
					break;
					
				
				case "Care Coordination":
					String MemtoAccompCareCoord = verifyEditActivity(activityName);
					Thread.sleep(GlobalValues.Sleep_wait_time);
					Log.info("Successfully added Record Contact details from " +activityName+ " page");
					Log.info("Verify added Record Contact from " + activityName + " page");
					verifyRecordContactfromActivityPage(activityName,MemtoAccompCareCoord);
					break;	
					
//				default:
//					memberActionCenter.addActivity(activityName);
//					Thread.sleep(3000);
//					String MemtoAccompOther = verifyEditActivity(activityName);
//					Thread.sleep(GlobalValues.Sleep_wait_time);
//					Log.info("Successfully added Record Contact details from " +activityName+ " page");
//					Log.info("Verify added Record Contact from " + activityName + " page");
//					verifyRecordContactfromActivityPage(activityName,MemtoAccompOther);
//					
				}
				
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
				Log.info("abandoned-NIM-18512: Intro & Permission: Add 'Save Progress' button in the sub-section");
				Log.info("Refactored-NIM-18879: Preferred contact information: Create configurable sub activity");
			//}
			
		} catch (Exception e) {
			e.printStackTrace();
			Log.info("Failed-NIM-18510: Intro & Permission: Create configurable Intro & Permission sub activity");
			Log.info("Failed-NIM-18509: Intro & Permission: Remove buttons in an activity from intro & permission and add them to a separate section");
			Log.info("Failed-NIM-18508: Remove intro & permissions");
			Log.info("Failed-NIM-18879: Preferred contact information: Create configurable sub activity");
			}
	}	
			

		public String verifyEditActivity(String activityName) throws Exception {
				Log = Logger.getLogger("ActivityPage.class");
				PropertyConfigurator.configure("log4j.properties");
				WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
				Global_Method gm = new Global_Method(driver);
				LeftNavigationLink lnk = new LeftNavigationLink(driver);
				InitialOutreach_Activity hipa = new InitialOutreach_Activity(driver);
				WebActions webact = new WebActions();
				JavascriptExecutor js = (JavascriptExecutor)driver;
				AnnualAssessment annual = new AnnualAssessment(driver);
				ActivityPage activity = new ActivityPage(driver);
						
				String MemtoAccomp = "Collect Reports";
				
				boolean passed = true;
				
				Thread.sleep(2000);
				
				Log.info("Click on Edit of " + activityName);
				wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(config.getEditActivity().replace("<ActivityName>", activityName))))).click(); 
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
				Thread.sleep(1000);
				
	
					//------------------- Verify Record Contact Section is present ---------------//
				js.executeScript("window.scrollBy(0,1000)");	
				if(webact.validateobjectexists("//h2[contains(text(),'Record Contact')]", driver)==true){
						Log.info("Record contact section exists in "+ activityName + " activity");
						Log.info("Test case to check whether Record Contact section exists in the "+activityName+ " has passed");
					}
				
					else{
					Log.info("Record contact section does not exist!");
					Log.info("Test case to check whether Record Contact section exists in the "+activityName+ " has failed");
					}
							
					//Add details in Record Contact section
					try {
						Log.info("Add Record Contact from " + activityName + " page");
						addRecordContactfromActivityPage(activityName);
						driver.findElement(By.xpath("//label[contains(text(),'Member to Accomplish Prior to Next Contact')]/../../..//textarea")).sendKeys(MemtoAccomp);
//						hipa.initialOutreachHIPPAapproval("Member",activityName);
						if(activityName.equalsIgnoreCase("Initial Outreach")){
						hipa.fillIntroandPermissions();
						}
						else{
							//activity.editInitialOutreachActivity("Clinical Intervention");
							js.executeScript("window.scrollBy(0,-800)");
							WebElement accordionIntroAndPermission = wait
									.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getIntroandPermissionsaccordian())));
							accordionIntroAndPermission.click();
							Thread.sleep(2000);
							annual.annual_assessment("AnnualAssessments_CMDM", "Intro_Permissions", 5);
									
							Thread.sleep(2000);
									WebElement saveandendactivityIntialOutreach = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getSaveActivityButton())));
									saveandendactivityIntialOutreach.click();
									
									if((webact.validateobjectexists(config.getbtnOkinAlert(), driver))==true){
										wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getbtnOkinAlert()))).click();
										}	
								}
						
						
						} catch (Exception e) {
						e.printStackTrace();
						passed = false;
					}
					return MemtoAccomp;
				}
				
			
		public void addRecordContactfromActivityPage(java.lang.String activityName) throws Exception {
		Log = Logger.getLogger("ActivityPage.class");
		PropertyConfigurator.configure("log4j.properties");
		AnnualAssessment a = new AnnualAssessment(driver);
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		Global_Method gm = new Global_Method(driver);
		LeftNavigationLink lnk = new LeftNavigationLink(driver);
		WebActions webact = new WebActions();

			try {
				WebElement recContactAccordianElement = wait.until(ExpectedConditions
						.elementToBeClickable(driver.findElement(By.xpath(config.getRecContAccordianElementXpath()))));
					recContactAccordianElement.click();

				Thread.sleep(2000);
					
					//Verify that text area where user can document what member will achieve prior to next contact is present 
					
				if(webact.validateobjectexists(config.getRecContacttextareafieldsXpath().replace("<fieldName>", "Member to Accomplish"), driver)==true){
					Log.info("Text Area to document what member will achieve prior to next contact exists in the record contact section" );
					Log.info("Test Case to check whether Text Area to document what member will achieve prior to next contact exists in the record contact section has passed" );
					}
				else{
					Log.info("Text Area to document what member will achieve prior to next contact does not exist in the record contact section");
					Log.info("Test Case to check whether Text Area to document what member will achieve prior to next contact exists in the record contact section has failed" );
					}
					
				for(int i=0;i<GlobalValues.RecordContactSectionfields.size();i++)
					{
					 String fieldname = GlobalValues.RecordContactSectionfields.get(i);	
								 		
				//-----	Check the mandatory fields in Record Contact section-----//
					 	if(fieldname.contains("Purpose of Call") || fieldname.contains("Call Direction") || fieldname.contains("Contact Type") 
							 || fieldname.contains("Communication Type") || fieldname.contains("Contact Outcome")){

					 	if(webact.validateobjectmandatory((config.getRecContactlabelsXpath().replace("<fieldName>", fieldname)), driver)==true){
							Log.info( fieldname+ " is mandatory!");
							Log.info("Test case to check whether " +fieldname+ " is mandatory has passed");
						 	}
						 	else{
						 	Log.info( fieldname+ " is not mandatory!");
						 	Log.info("Test case to check whether " +fieldname+ " is mandatory has failed");
						 	}
					 	}
						else if(fieldname.contains("Contact Name") || fieldname.contains("Contact Phone")){
							 if(webact.validateobjectmandatory((config.getRecContactlabelsXpath().replace("<fieldName>", fieldname)), driver)==false){
									Log.info( fieldname+ " is not mandatory!");
									Log.info("Test case to check whether " +fieldname+ " is not mandatory has passed");
								 	}
								 	else{
								 		Log.info( fieldname+ " is mandatory!");
								 		Log.info("Test case to check whether " +fieldname+ " is not mandatory has failed");
								 		}
						}
						 
						else if(fieldname.contains("Member to Accomplish Prior to Next Contact")){
							 if(webact.validateobjectmandatory((config.getRecContactlabelsXpath().replace("<fieldName>", fieldname)), driver)==false){
									Log.info( fieldname+ " is not mandatory!");
									Log.info("Test case to check whether " +fieldname+ " is not mandatory has passed");
								 	}
								 	else{
								 		Log.info( fieldname+ " is mandatory!");
								 		Log.info("Test case to check whether " +fieldname+ " is not mandatory has failed");
								 		}
						}
						 
						//Check drop down values in Record Contact section 
					 		
						 	if(fieldname.contains("Purpose of Call")){
						 		Log.info("Checking values in drop down fields");
						 		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(config.getRecContactdropdownfieldsXpath().replace("<fieldName>", fieldname))))).click();
						 					 			 		
						 		if(webact.validateDropdown((config.getRecContactdropdownvaluesXpath().replace("<fieldname>", "purpose")), GlobalValues.RecordContactPorposeofCallOptions, driver)==true){
						 			Log.info( fieldname+ " dropdown contains the required values");
							 		Log.info("Test case to check whether the values contained in the dropdown " +fieldname+ " has passed");
						 			}
						 		
						 		else{
						 			Log.info( fieldname+ " dropdown does not contain the required values");
							 		Log.info("Test case to check whether the values contained in the dropdown " +fieldname+ " has failed");
						 			}
						 		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(config.getRecContactdropdownfieldsXpath().replace("<fieldName>", fieldname))))).click();
						 	}
						 		
						 	else if(fieldname.contains("Call Direction")){
						 		Log.info("Checking values in drop down fields");
						 		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(config.getRecContactdropdownfieldsXpath().replace("<fieldName>", fieldname))))).click();
								if(webact.validateDropdown((config.getRecContactdropdownvaluesXpath().replace("<fieldname>", "callDirection")), GlobalValues.RecordContactCallDirectionOptions, driver)==true){
									Log.info( fieldname+ " dropdown contains the required values");
							 		Log.info("Test case to check whether the values contained in the dropdown " +fieldname+ " has passed");
									}
								else{
									Log.info( fieldname+ " dropdown does not contain the required values");
							 		Log.info("Test case to check whether the values contained in the dropdown " +fieldname+ " has failed");
							 		}
								wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(config.getRecContactdropdownfieldsXpath().replace("<fieldName>", fieldname))))).click();
						 	}
								
						 	else if(fieldname.contains("Contact Type")){
						 		Log.info("Checking values in drop down fields");
						 		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(config.getRecContactdropdownfieldsXpath().replace("<fieldName>", fieldname))))).click();
								if(webact.validateDropdown((config.getRecContactdropdownvaluesXpath().replace("<fieldname>", "contactType")), GlobalValues.RecordContactContactTypeOptions, driver)==true){
									Log.info( fieldname+ " dropdown contains the required values");
							 		Log.info("Test case to check whether the values contained in the dropdown " +fieldname+ " has passed");
									}
								else{
									Log.info( fieldname+ " dropdown does not contain the required values");
									Log.info("Test case to check whether the values contained in the dropdown " +fieldname+ " has failed");
								 	}
								wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(config.getRecContactdropdownfieldsXpath().replace("<fieldName>", fieldname))))).click();
							}
						 	else if(fieldname.contains("Communication Type")){
						 		Log.info("Checking values in drop down fields");
						 		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(config.getRecContactdropdownfieldsXpath().replace("<fieldName>", fieldname))))).click();
								if(webact.validateDropdown((config.getRecContactdropdownvaluesXpath().replace("<fieldname>", "communicationType")), GlobalValues.RecordContactCommunicationTypeOptions, driver)==true){
									Log.info( fieldname+ " dropdown contains the required values");
									Log.info("Test case to check whether the values contained in the dropdown " +fieldname+ " has passed");
									}
								else{
									Log.info( fieldname+ " dropdown does not contain the required values");
									Log.info("Test case to check whether the values contained in the dropdown " +fieldname+ " has failed");
								 	}
								wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(config.getRecContactdropdownfieldsXpath().replace("<fieldName>", fieldname))))).click();
							}	
						 	else if(fieldname.contains("Contact Outcome")){
						 		Log.info("Checking values in drop down fields");
						 		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(config.getRecContactdropdownfieldsXpath().replace("<fieldName>", fieldname))))).click();
								if(webact.validateDropdown((config.getRecContactdropdownvaluesXpath().replace("<fieldname>", "contactOutcome")), GlobalValues.RecordContactContactOutcomeOptions, driver)==true){
									Log.info( fieldname+ " dropdown contains the required values");
									Log.info("Test case to check whether the values contained in the dropdown " +fieldname+ " has passed");
									}
								else{
									Log.info( fieldname+ " dropdown does not contain the required values");
									Log.info("Test case to check whether the values contained in the dropdown " +fieldname+ " has failed");
									}
								wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(config.getRecContactdropdownfieldsXpath().replace("<fieldName>", fieldname))))).click();
							}	
					}
					
					Log.info("Add Record Contact Details in the "+ activityName + " activity");	
					
					switch(activityName){
					
					case "Initial Outreach":
						
						a.annual_assessment("AnnualAssessments_CMDM", "RecordContact", 2);
						break;
					
					case "Clinical Intervention":
						a.annual_assessment("AnnualAssessments_CMDM", "RecordContact", 3);
						break;
						
					case "Referral":
						a.annual_assessment("AnnualAssessments_CMDM", "RecordContact", 4);
						break;
						
					case "Care Coordination":
						a.annual_assessment("AnnualAssessments_CMDM", "RecordContact", 5);
						break;
											
					}
					
					Thread.sleep(2000);
					Log.info("Passed-NIM-17361: Activity Sub-section: Create View for Record a Contact" + '\n' + "GIVEN:Create View for Record a Contact"
							+ '\n' + "THEN:Activity Sub-section: Create View for Record a Contact");
								
				} catch (Exception e) {

					Log.info(e + " -Couldn`t add details in the Record Contact Sub Activity of a " +activityName+ " page");
					e.printStackTrace();
					
					Log.info("Failed-NIM-17361: Activity Sub-section: Create View for Record a Contact");
				}
				
			}
			
			
		public void verifyRecordContactfromActivityPage(java.lang.String activityName,String MemtoAccomp ) throws Exception {
			Log = Logger.getLogger("ActivityPage.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			WebDriverWait wait1 = new WebDriverWait(driver, 200);
			Global_Method gm = new Global_Method(driver);
			LeftNavigationLink lnk = new LeftNavigationLink(driver);
			WebActions webact = new WebActions();
			JavascriptExecutor js = (JavascriptExecutor)driver;
			String purposeOfCallfromExcel,callDirectionfromExcel,contactTypefromExcel,contactNamefromExcel,contactPhonefromExcel,communicationTypefromExcel,contactOutcomefromExcel;
			
			Thread.sleep(6000);
			//To check whether details added in Record Contact section is displayed and is in read-only mode

			if(((driver.findElement(By.xpath(config.getActivitiesLink()))).isDisplayed())==false){
				lnk.clickMemberActionCenter();
			}
			if(((driver.findElement(By.xpath(config.getActivitiesLink()))).isSelected())==false){
				lnk.clickActivities();
			}
			
				
			try{
			Log.info("Click on View of "+activityName+ "  Activity");
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(config.getViewActivity().replace("<ActivityName>", activityName))))).click(); 
			wait1.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
			Thread.sleep(1000);
			
			js.executeScript("window.scrollBy(0,1000)");
			WebElement recContactAccordianElement = wait.until(ExpectedConditions
					.elementToBeClickable(driver.findElement(By.xpath(config.getRecContAccordianElementXpath()))));
			recContactAccordianElement.click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
			Thread.sleep(1000);
			
			//To check whether the values entered from excel are displayed in the Activity View after Save/End Activity
		
			switch(activityName){
			
			case "Initial Outreach":
				 
				 purposeOfCallfromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "RecordContact", 2, "Purpose of Call");
				 callDirectionfromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "RecordContact", 2, "Call Direction");
				 contactTypefromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "RecordContact", 2, "Contact Type");
				 contactNamefromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "RecordContact", 2, "Contact Name");
				 contactPhonefromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "RecordContact", 2, "Contact Phone");
				 communicationTypefromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "RecordContact", 2, "Communication Type");
				 contactOutcomefromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "RecordContact", 2, "Contact Outcome");
				 checkvalues(purposeOfCallfromExcel,callDirectionfromExcel,contactTypefromExcel,contactNamefromExcel,contactPhonefromExcel,communicationTypefromExcel,contactOutcomefromExcel,MemtoAccomp);
				 break;
				
			case "Clinical Intervention":
				 purposeOfCallfromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "RecordContact", 3, "Purpose of Call");
				 callDirectionfromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "RecordContact", 3, "Call Direction");
				 contactTypefromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "RecordContact", 3, "Contact Type");
				 contactNamefromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "RecordContact", 3, "Contact Name");
				 contactPhonefromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "RecordContact", 3, "Contact Phone");
				 communicationTypefromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "RecordContact", 3, "Communication Type");
				 contactOutcomefromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "RecordContact", 3, "Contact Outcome");
				 checkvalues(purposeOfCallfromExcel,callDirectionfromExcel,contactTypefromExcel,contactNamefromExcel,contactPhonefromExcel,communicationTypefromExcel,contactOutcomefromExcel,MemtoAccomp);
				 break;
				
				
			case "Referral":
				 purposeOfCallfromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "RecordContact", 4, "Purpose of Call");
				 callDirectionfromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "RecordContact", 4, "Call Direction");
				 contactTypefromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "RecordContact", 4, "Contact Type");
				 contactNamefromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "RecordContact", 4, "Contact Name");
				 contactPhonefromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "RecordContact", 4, "Contact Phone");
				 communicationTypefromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "RecordContact", 4, "Communication Type");
				 contactOutcomefromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "RecordContact", 4, "Contact Outcome");
				 checkvalues(purposeOfCallfromExcel,callDirectionfromExcel,contactTypefromExcel,contactNamefromExcel,contactPhonefromExcel,communicationTypefromExcel,contactOutcomefromExcel,MemtoAccomp);
				 break;	
			
			case "Care Coordination":
				 purposeOfCallfromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "RecordContact", 5, "Purpose of Call");
				 callDirectionfromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "RecordContact", 5, "Call Direction");
				 contactTypefromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "RecordContact", 5, "Contact Type");
				 contactNamefromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "RecordContact", 5, "Contact Name");
				 contactPhonefromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "RecordContact", 5, "Contact Phone");
				 communicationTypefromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "RecordContact", 5, "Communication Type");
				 contactOutcomefromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "RecordContact", 5, "Contact Outcome");
				 checkvalues(purposeOfCallfromExcel,callDirectionfromExcel,contactTypefromExcel,contactNamefromExcel,contactPhonefromExcel,communicationTypefromExcel,contactOutcomefromExcel,MemtoAccomp);
				 break;	 
				
			}
			
			
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
			 	Log.info("Click on Back button from View "+activityName+ "  Activity");
			 	Thread.sleep(2000);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getbtnBackinViewActivity()))).click(); 
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
				Thread.sleep(1000);
			
			
			Log.info("Successfully verified the Record Contact subsection is read-only for " +activityName+ " Activity");
			Log.info("**********Verified Record Contact subsection from " +activityName+ " Activity Page**********");

						
			} catch (Exception e) {
				Log.info(e + " -Couldn`t verify added record in Record Contacts Sub-section of "+activityName+ " Activity page");
				}

			}


public void checkvalues(String purposeOfCallfromExcel,String callDirectionfromExcel,String contactTypefromExcel,String contactNamefromExcel,String contactPhonefromExcel,String communicationTypefromExcel,String contactOutcomefromExcel,String MemtoAccomp){
	if(purposeOfCallfromExcel.equalsIgnoreCase(driver.findElement(By.xpath(config.getRecContactdropdownfieldsXpath().replace("<fieldName>", "Purpose of Call"))).getText())){
		Log.info("Purpose of Call value verified");
		}
	else{
		Log.info("Purpose of Call value not matching");
	}

	if(callDirectionfromExcel.equalsIgnoreCase(driver.findElement(By.xpath(config.getRecContactdropdownfieldsXpath().replace("<fieldName>", "Call Direction"))).getText())){
		Log.info("Call Direction value verified");
		}
	else{
		Log.info("Call Direction value not matching");
	}
	
	if(contactTypefromExcel.equalsIgnoreCase(driver.findElement(By.xpath(config.getRecContactdropdownfieldsXpath().replace("<fieldName>", "Contact Type"))).getText())){
		Log.info("Contact Type value verified");
		}
	else{
		Log.info("Contact Type value not matching");
	}
	
				
	if(contactNamefromExcel.equalsIgnoreCase(driver.findElement(By.xpath(config.getRecContacttextfieldsXpath().replace("<fieldName>", "Contact Name"))).getAttribute("value"))){
		Log.info("Contact Name value verified");
		}
	else{
		Log.info("Contact Name value not matching");
	}
	if(contactPhonefromExcel.equalsIgnoreCase((driver.findElement(By.xpath(config.getRecContacttextfieldsXpath().replace("<fieldName>", "Contact Phone")))).getAttribute("value"))){
		Log.info("Contact Phone value verified");
		}
	else{
		Log.info("Contact Phone value not matching");
	}
	if(communicationTypefromExcel.equalsIgnoreCase(driver.findElement(By.xpath(config.getRecContactdropdownfieldsXpath().replace("<fieldName>", "Communication Type"))).getText())){
		Log.info("Communication Type value verified");
		}
	else{
		Log.info("Communication Type not matching");
	}
	if(contactOutcomefromExcel.equalsIgnoreCase(driver.findElement(By.xpath(config.getRecContactdropdownfieldsXpath().replace("<fieldName>", "Contact Outcome"))).getText())){
		Log.info("Contact Outcome value verified");
		}
	else{
		Log.info("Contact Outcome not matching");
	}

				
	if(MemtoAccomp.equalsIgnoreCase((driver.findElement(By.xpath("//label[contains(text(),'Member to Accomplish Prior to Next Contact')]/../../..//textarea"))).getAttribute("value"))){
	Log.info("Member to Accomplish Prior to Next Contact value verified");
		}
	else{
		Log.info("Member to Accomplish Prior to Next Contact not matching");
	}
	
}
}

	
