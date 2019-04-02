package com.nimbus.pages;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.ConfigReader;


 /*Global Class: Left panel links
 	Description: common class to click on Left panel links.
 */
public class LeftNavigationLink {

	ConfigReader config = new ConfigReader();

	WebDriver driver;
	Logger Log;

	public LeftNavigationLink(WebDriver ldriver) {
		this.driver = ldriver;
	}

	public LeftNavigationLink(Logger lLog) {
		this.Log = lLog;
	}

	public void clickOverview() throws InterruptedException {
		try {
			// Click on Overview
			Log = Logger.getLogger("LeftNavigationLink.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

			WebElement MemberOverview = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getOverviewLinkLN())));
			MemberOverview.click();
			Log.info("Clicked Overview");

			WebElement Overview = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getOverviewTitle())));
			String OverviewText = Overview.getText().trim();
			Assert.assertEquals(OverviewText, GlobalValues.overview);
			Log.info("OverviewText is present");
			Thread.sleep(2000);

		} catch (Exception e) {
			e.printStackTrace();
			Log.info("Object not visible");
		}
	}

	public void clickHealthChart() {
		try {
			// Click on Health Chart
			Log = Logger.getLogger("LeftNavigationLink.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

			WebElement healthChart = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getHealthChartLinkLN())));
			healthChart.click();
			Thread.sleep(GlobalValues.Sleep_wait_time);
			Log.info("Clicked Health Chart on Left Nav");
			Thread.sleep(1000);

		} catch (Exception e) {
			e.printStackTrace();
			Log.info("Object not visible");
		}
	}

	public void clickGeneralMedical() {
		try {
			// Click on General Medical
			Log = Logger.getLogger("LeftNavigationLink.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

			Thread.sleep(GlobalValues.Sleep_wait_time);
		//	clickHealthChart();

			WebElement generalMedical = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getGeneralMedicalLinkLN())));
			generalMedical.click();
			Log.info("Clicked \"General Medical\" on PageCaseInfo");

			WebElement generalMedicalTitle = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getGeneralMedicalTitle())));
			String generalMedicalTitleText = generalMedicalTitle.getText().trim();
			Assert.assertEquals(generalMedicalTitleText, GlobalValues.generalMedical);
			Log.info("General Medical text is present");

		} catch (Exception e) {
			e.printStackTrace();
			Log.info("Object not visible");
		}
	}

	
	public void clickNotes() {
		try {
			// Click on NOTES
			Log = Logger.getLogger("LeftNavigationLink.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

			Thread.sleep(GlobalValues.Sleep_wait_time);

			WebElement notes = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getNotesLinkLN())));
			notes.click();
			Log.info("Clicked \"Notes\" on PageCaseInfo");
			Thread.sleep(GlobalValues.Sleep_wait_time);
			WebElement notestitle = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getNotesTitle())));
			String notesText = notestitle.getText().trim();
			Assert.assertEquals(notesText, GlobalValues.notes);
			Log.info("Notes text is present");

		} catch (Exception e) {
			e.printStackTrace();
			Log.info("Object not visible");
		}
	}

	public void clickManagementReason() {
		try {
			// Click on Management Reason
			Log = Logger.getLogger("LeftNavigationLink.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

			WebElement managementReason = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getManagementReasonLinkLN())));
			managementReason.click();
			Log.info("Clicked \"Management Reason\" on PageCaseInfo");
			Thread.sleep(GlobalValues.Sleep_wait_time);
			WebElement managementReasonTitle = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getManagementReasonTitle())));
			String managementReasonTitleText = managementReasonTitle.getText().trim();
			Assert.assertEquals(managementReasonTitleText, GlobalValues.managementReasons);
			Log.info("Management Reason text is present");

		} catch (Exception e) {
			e.printStackTrace();
			Log.info("Object not visible");
		}
	}

	public void clickDocuments() {
		try { // Click on Assessments
			Log = Logger.getLogger("LeftNavigationLink.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			Log.info("Entered \"Documents\" on PageCaseInfo");
			WebElement documents = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getDocumentsLinkLN())));
			documents.click();
			Thread.sleep(GlobalValues.Sleep_wait_time);
			WebElement documentstitles = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getDocumentsTitle())));
			String documentsText = documentstitles.getText().trim();
			Assert.assertEquals(documentsText, GlobalValues.documents);
			Log.info("Documents text is present");
			Log.info("Clicked \"Documents\" on PageCaseInfo");
		} catch (Exception e) {
			e.printStackTrace();
			Log.info("Object not visible");
		}
	}

	public void clickAdmissions() {
		try {
			// Click Admissions
			Log = Logger.getLogger("LeftNavigationLink.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			
		//	clickHealthChart();
			
			Log.info("Entered \"Admissions\" on PageCaseInfo");
			WebElement admissions = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getAdmissionsLinkLN())));
			admissions.click();
			Thread.sleep(GlobalValues.Sleep_wait_time);
			WebElement admissionsTitle = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getAdmissionsTitle())));
			String admissionsTitleText = admissionsTitle.getText().trim();
			Assert.assertEquals(admissionsTitleText, GlobalValues.admissions);
			Log.info("Admissions text is present");
			Log.info("Clicked \"Admissions\" on PageCaseInfo");
		} catch (Exception e) {
			e.printStackTrace();
			Log.info("Object not visible");
		}
	}

	public void clickLifestyleAndPrevention() {
		try {
			// Click Lifestyle and Prevention
			Log = Logger.getLogger("LeftNavigationLink.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			
		//	clickHealthChart();
			
			Log.info("Entered \"Lifestyle and Prevention\" on PageCaseInfo");
			WebElement lifestyleAndPrevention = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getLifestyleAndPreventionLinkLN())));
			lifestyleAndPrevention.click();
			Thread.sleep(GlobalValues.Sleep_wait_time);

			WebElement LifestyleAndPreventionTitle = wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getLifestyleAndPreventionTitle())));
			String LifestyleAndPreventionTitleText = LifestyleAndPreventionTitle.getText().trim();
			Assert.assertEquals(LifestyleAndPreventionTitleText, GlobalValues.lifestyleAndPrevention);
			Log.info("Lifestyle and Prevention text is present");
			Log.info("Clicked \"Lifestyle and Prevention\" on PageCaseInfo");
		} catch (Exception e) {
			e.printStackTrace();
			Log.info("Object not visible");
		}
	}

	public void clickCarePlan() {
		try {
			// Click Care Plan
			Log = Logger.getLogger("LeftNavigationLink.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			
//			clickMemberActionCenter();
			
			Log.info("Entered \"Care Plan\" on PageCaseInfo");

			WebElement carePlan = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getCarePlanLinkLN())));

			carePlan.click();
			Thread.sleep(GlobalValues.Sleep_wait_time);

			WebElement CarePlanTitle = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getCarePlanTitle())));
			String CarePlanTitleText = CarePlanTitle.getText().trim();
			Assert.assertEquals(CarePlanTitleText, GlobalValues.carePlan);
			Log.info("Care Plan text is present");
			Log.info("Clicked \"Care Plan\" on PageCaseInfo");
		} catch (Exception e) {
			e.printStackTrace();
			Log.info("Object not visible");
		}

	}

	public void clickContactHistory() {
		try {
			// Click Care Plan
			Log = Logger.getLogger("LeftNavigationLink.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			Log.info("Entered \"Contact History\" on PageCaseInfo");
			WebElement contactHistory = wait
					.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Contact History")));
			contactHistory.click();
			Thread.sleep(GlobalValues.Sleep_wait_time);
			
			WebElement ContactHistoryTitle = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getContactHistoryTitle())));
			String ContactHistoryTitleText = ContactHistoryTitle.getText().trim();
			Assert.assertEquals(ContactHistoryTitleText, GlobalValues.contactHistory);
			Log.info("Contact History text is present");
			Log.info("Clicked \"Contact History\" on PageCaseInfo");
		} catch (Exception e) {
			e.printStackTrace();
			Log.info("Object not visible");
		}

	}

	public void clickBarriers() {
		try {
			// Click Barriers
			Log = Logger.getLogger("LeftNavigationLink.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			
	//		clickMemberActionCenter();
			Thread.sleep(4000);
			Log.info("Entered \"Barriers\" on PageCaseInfo");
			WebElement barriers = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getBarriersLinkLN())));
			barriers.click();
			Thread.sleep(GlobalValues.Sleep_wait_time);
			WebElement barriersTitle = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getBarriersTitle())));
			String barriersTitleText = barriersTitle.getText().trim();
			Assert.assertEquals(barriersTitleText, GlobalValues.barriers);
			Log.info("Barriers text is present");
			Log.info("Clicked \"Barriers\" on PageCaseInfo");
		} catch (Exception e) {
			e.printStackTrace();
			Log.info("Object not visible");
		}

	}

	public void clickMemberActionCenter() {
		try {
			// Click Member Action Center
			Log = Logger.getLogger("LeftNavigationLink.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

			if(!(driver.findElement(By.xpath(config.getActivitiesLinkLN()))).isDisplayed()){
				WebElement memberActionCenter = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getMemberActionCenterLinkLN())));
				memberActionCenter.click();
				Log.info("Clicked \"Member Action Center\" on PageCaseInfo");
				Thread.sleep(2000);
		}

		} catch (Exception e) {
			e.printStackTrace();
			Log.info("Object not visible");
		}

	}

	public void clickMedications() {
		try {
			// Click on Medications
			Log = Logger.getLogger("LeftNavigationLink.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			
//			clickHealthChart();
			Log.info("Clicked Health Chart");
			Thread.sleep(1000);
			WebElement medications = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getMedicationsLinkLN())));
			medications.click();
			Log.info("Clicked Medications");
			Thread.sleep(GlobalValues.Sleep_wait_time);
			
			WebElement medicationsTitle = wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getMedicationsTitle())));
			String medicationsTitleText = medicationsTitle.getText().trim();
			Assert.assertEquals(medicationsTitleText, GlobalValues.medications);
			Log.info("Medication text is present");

		} catch (Exception e) {
			e.printStackTrace();
			Log.info("Object not visible");
		}
	}

	public void clickInterventions() {

		try {
			// Click Interventions
			Log = Logger.getLogger("LeftNavigationLink.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			
	//		clickMemberActionCenter();
			
			Log.info("Entered \"Member Action Center\" on PageCaseInfo");
			Thread.sleep(1000);
			WebElement interventions = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getInterventionsLinkLN())));
			interventions.click();
			Thread.sleep(GlobalValues.Sleep_wait_time);
			WebElement InterventionsTitle = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getInterventionsTitle())));
			String InterventionsTitleText = InterventionsTitle.getText().trim();
			Assert.assertEquals(InterventionsTitleText, GlobalValues.interventions);
			Log.info("Interventions text is present");
			Log.info("Clicked \"Interventions\" on PageCaseInfo");
		} catch (Exception e) {
			e.printStackTrace();
			Log.info("Object not visible");
		}
	}

	
	public void clickConditions() {
		try {
			// Click Conditions
			Log = Logger.getLogger("LeftNavigationLink.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			
	//		clickHealthChart();
						
	//		Log.info("Entered \"Health Chart\" on PageCaseInfo");
			Thread.sleep(1000);
			WebElement conditions = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getConditionsLinkLN())));
			conditions.click();
			Thread.sleep(GlobalValues.Sleep_wait_time);
			Log.info("Clicked \"Conditions\" on PageCaseInfo");

			WebElement conditionsTitle = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getConditionsTitle())));
			String conditionsTitleText = conditionsTitle.getText().trim();
			Assert.assertEquals(conditionsTitleText, GlobalValues.conditions);
			Log.info("Conditions text is present");
	

		} catch (Exception e) {
			e.printStackTrace();
			Log.info("Object not visible");
		}
	}
	
	public void clickImmunizations() {
		try {
			// Click Immunizations
			Log = Logger.getLogger("LeftNavigationLink.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			//clickHealthChart();
			
			Log.info("Entered \"Immunizations\" on PageCaseInfo");
			WebElement immunizations = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getImmunizationsLinkLN())));
			immunizations.click();
			Thread.sleep(GlobalValues.Sleep_wait_time);
			WebElement ImmunizationsTitle = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getImmunizationsTitle())));
			String immunizationsTitleText = ImmunizationsTitle.getText().trim();
			Assert.assertEquals(immunizationsTitleText, GlobalValues.immunizations);
			Log.info("Immunizations text is present");
			Log.info("Clicked \"Immunizations\" on PageCaseInfo");
		} catch (Exception e) {
			e.printStackTrace();
			Log.info("Object not visible");
		}
	}
	
    public void clickChangeHistory() {
        try { 
               // Click Care Plan
               Log = Logger.getLogger("LeftNavigationLink.class");
               PropertyConfigurator.configure("log4j.properties");
               WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
               Log.info("Entered \"Change History\" on PageCaseInfo");
               Thread.sleep(2000);
               WebElement contactHistory = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getChangeHistoryLinkLN())));
               contactHistory.click();
               Thread.sleep(GlobalValues.Sleep_wait_time);       
               WebElement ChangeHistoryTitle=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getChangetHistoryTitle())));
            String ChangeHistoryTitleText=ChangeHistoryTitle.getText().trim();
            Assert.assertEquals(ChangeHistoryTitleText, GlobalValues.changeHistory);
            Log.info("Change History text is present");
            Log.info("Clicked \"Change History\" on PageCaseInfo");
        } catch (Exception e) {
               e.printStackTrace();
               Log.info("Object not visible");
        }
        
 }
    
    public void clickCareTeam() {
        try { 
               // Click Care Team
               Log = Logger.getLogger("LeftNavigationLink.class");
               PropertyConfigurator.configure("log4j.properties");
               WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
               Log.info("Entered \"Care Team\" on PageCaseInfo");
               WebElement careTeam = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getCareTeamLinkLN())));
               careTeam.click();
               Thread.sleep(GlobalValues.Sleep_wait_time);
               WebElement careTeamTitleElement =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getCareTeamTitle())));
               String careTeamText=careTeamTitleElement.getText().trim();
               Assert.assertEquals(careTeamText, GlobalValues.careTeam);
               Log.info("Care Team text is present");
               Log.info("Clicked \"Care Team\" on PageCaseInfo");
        } catch (Exception e) {
               e.printStackTrace();
               Log.info("Object not visible");
        }
    }
        
        public void clickActivities() {
            try { 
                   // Click Activities
                   Log = Logger.getLogger("LeftNavigationLink.class");
                   PropertyConfigurator.configure("log4j.properties");
                   WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
                   
                 //  clickMemberActionCenter();
                   
                   WebElement activitiesWebElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getActivitiesLinkLN())));
                   activitiesWebElement.click();
                   Log.info("Entered \"Activities\" on PageCaseInfo");
                   Thread.sleep(GlobalValues.Sleep_wait_time);
                   WebElement activitiesTitleElement =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getActivitiesTitle())));
                   String activitiesText=activitiesTitleElement.getText().trim();
                   Assert.assertEquals(activitiesText, GlobalValues.activities);
                   Log.info("Activities text is present");
                   Log.info("Clicked \"Activities\" on PageCaseInfo");
                   Thread.sleep(3000);
            } catch (Exception e) {
                   e.printStackTrace();
                   Log.info("Object not visible");
            }
        
 }
        
        public void clickQuestionnairesHistory() {
    		try {
    			// Click Questionnaire History
    			Log = Logger.getLogger("LeftNavigationLink.class");
    			PropertyConfigurator.configure("log4j.properties");
    			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
    	
    			
    			Log.info("Entered \"Questionnaire History \" on PageCaseInfo");
    			WebElement questionnaireHistoryElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getQuestionnaireHistoryLinkLN())));
    			questionnaireHistoryElement.click();
    			Thread.sleep(GlobalValues.Sleep_wait_time);
    			WebElement questionnaireHistoryBreadCrumb = wait
    					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getQuestionnaireHistoryTitle())));
    			String questionnaireHistoryBreadCrumbText = questionnaireHistoryBreadCrumb.getText().trim();
    			Assert.assertEquals(questionnaireHistoryBreadCrumbText, GlobalValues.questionnairesHistory);
    			Log.info("Questionnaires History text is present");
    			Log.info("Clicked \"Questionnaires History\" on PageCaseInfo");
    		} catch (Exception e) {
    			e.printStackTrace();
    			Log.info("Object not visible");
    		}
    	}
        
        public void clickPrint() {
    		try {
    			// Click Print
    			Log = Logger.getLogger("LeftNavigationLink.class");
    			PropertyConfigurator.configure("log4j.properties");
    			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
    	
    			
    			Log.info("Entered \"Print \" on PageCaseInfo");
    			WebElement printElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getPrintLinkLN())));
    			printElement.click();
    			Thread.sleep(GlobalValues.Sleep_wait_time);
    			WebElement printBreadCrumb = wait
    					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getPrintTitle())));
    			String printBreadCrumbText = printBreadCrumb.getText();
    			Assert.assertEquals(printBreadCrumbText, GlobalValues.print);
    			Log.info("Print Bread Crumb text is present");
    			
    		} catch (Exception e) {
    			e.printStackTrace();
    			Log.info("Object not visible");
    		}
    	}


}
