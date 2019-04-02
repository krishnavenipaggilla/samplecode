package com.nimbus.pages;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utility.ConfigReader;

public class ActivitiesUTCFlow {
	WebDriver driver;
	Logger Log;
	ConfigReader config = new ConfigReader();
	String InitialOutreachStatus = null;
	WebElement InitialoutreachActivityStatus = null;
	WebElement ActivityStatus = null;
	LeftNavigationLink leftNav=null;
	

	public ActivitiesUTCFlow(WebDriver ldriver) {
		this.driver = ldriver;
	}

	public ActivitiesUTCFlow(Logger lLog) {
		this.Log = lLog;
	}

	public void initialOutreachUTC() throws Exception {
		Log = Logger.getLogger("ActivitiesUTCFlow.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		AnnualAssessment annual = new AnnualAssessment(driver);

		InitialoutreachActivityStatus = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getInitialOutreachActivitystatus())));
		InitialOutreachStatus = InitialoutreachActivityStatus.getText().toString().trim();
		Log.info("Initialoutreachstatus---" + InitialOutreachStatus);

		if (InitialOutreachStatus.equals(GlobalValues.Open_status.toString())
				|| InitialOutreachStatus.equals(GlobalValues.InProgress_status.toString()))

		{
			// adding goals,interventions and barriers
			BarriersPage barrierspage = PageFactory.initElements(driver, BarriersPage.class);
			barrierspage.clickCarePlan();
			barrierspage.AddGoal();
			barrierspage.clickBarriers();
			barrierspage.AddBarriersToGoal();
			
			// Left Navigation
		   leftNav = PageFactory.initElements(driver, LeftNavigationLink.class);
			leftNav.clickInterventions();
			Interventions interventionspage = PageFactory.initElements(driver, Interventions.class);
			interventionspage.AddInterventions_Verify();
			// Left Navigation
			 leftNav = PageFactory.initElements(driver, LeftNavigationLink.class);
//			leftNav.clickMemberActionCenter();
//			leftNav.clickMemberActionCenter();			
			leftNav.clickActivities();
			Thread.sleep(4000);

			Log.info("Click on Edit initialoutreach activity");
			WebElement editInitilaOutreach = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getEditIntialoutreach())));
			editInitilaOutreach.click();
			WebElement accordionIntroAndPermission = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getIntroandPermissionsaccordian())));
			accordionIntroAndPermission.click();
			annual.annual_assessment("AnnualAssessments_CMDM", "Intial_InroAndPermissions_UTC", 2);
			for (int i = 0; i <= 2; i++) {
				WebElement saveprogressIntialOutreach = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(config.getIntialOutreachSaveProgress())));
				saveprogressIntialOutreach.click();
				Log.info("Clicked on Save Progress");
				Thread.sleep(5000);
				WebElement IntialOutreachOkbutton = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getInitialOkbutton())));
				IntialOutreachOkbutton.click();
				Thread.sleep(3000);

			}
			// Left Navigation
			LeftNavigationLink leftNav = PageFactory.initElements(driver, LeftNavigationLink.class);
			leftNav.clickMemberActionCenter();
			leftNav.clickMemberActionCenter();
			leftNav.clickActivities();
			
			
			Thread.sleep(3000);
			WebElement InitialoutreachActivityStatus = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(config.getInitialOutreachActivitystatus())));
			InitialOutreachStatus = InitialoutreachActivityStatus.getText().toString().trim();
			Log.info("Initial outreach status---" + InitialOutreachStatus);
			if (InitialOutreachStatus.equalsIgnoreCase(GlobalValues.Completed_status)) {
				Log.info("Intial out reach UTC flow verified");
			}
			checkStatus();
			

			/*
			 * WebElement CaseStatus = wait
			 * .until(ExpectedConditions.elementToBeClickable(By.xpath(config.
			 * getCaseStatus()))); String CaseStatus1 =
			 * CaseStatus.getText().toString().trim();
			 * if(CaseStatus1.equalsIgnoreCase("Closed")){
			 * Log.info("Case status closed"); }
			 */
	
		}
	}
		public void checkStatus() throws InterruptedException {
			Log = Logger.getLogger("ActivitiesUTCFlow.class");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			BarriersPage barrierspage = PageFactory.initElements(driver, BarriersPage.class);
			barrierspage.clickCarePlan();
			WebElement Goalsstatus = wait.until(
					ExpectedConditions.presenceOfElementLocated(By.xpath(config.getGoalStatus())));
			String Goalsstatus1 = Goalsstatus.getText().toString().trim();
			Log.info("Goals status---" + Goalsstatus1);
			Assert.assertEquals(Goalsstatus1, "Cancelled");
			WebElement Goalsstatusreason = wait.until(
					ExpectedConditions.presenceOfElementLocated(By.xpath(config.getGoalReason())));
			String Goalsstatusreason1  = Goalsstatusreason.getText().toString().trim();
			
			Log.info("Goals status reason---" + Goalsstatusreason1);
		
			Assert.assertEquals(Goalsstatusreason1, "Unable to Contact");
		
			// Left Navigation
			   leftNav = PageFactory.initElements(driver, LeftNavigationLink.class);
				leftNav.clickInterventions();
				WebElement Interventionsstatus = wait.until(
						ExpectedConditions.presenceOfElementLocated(By.xpath(config.getInterventionsStatus())));
				String Interventionsstatus1 = Interventionsstatus.getText().toString().trim();
				Log.info("Interventions status---" + Interventionsstatus1);
				Assert.assertEquals(Interventionsstatus1, "Cancelled");
				WebElement Interventionsstatusreason = wait.until(
						ExpectedConditions.presenceOfElementLocated(By.xpath(config.getInterventionsReason())));
				String Interventionsstatusreason1 = Interventionsstatusreason.getText().toString().trim();
				
				Log.info("Inerventions status reason---" + Interventionsstatusreason1);
				Assert.assertEquals(Interventionsstatusreason1, "Unable to Contact");
				// Left Navigation
				   leftNav = PageFactory.initElements(driver, LeftNavigationLink.class);
				   barrierspage.clickBarriers();
					WebElement Barriersstatus = wait.until(
							ExpectedConditions.presenceOfElementLocated(By.xpath(config.getBarriersStatus())));
					String Barriersstatus1 = Barriersstatus.getText().toString().trim();
					Log.info("Barriers status---" + Barriersstatus1);
					Assert.assertEquals(Barriersstatus1, "Cancelled");
					WebElement Barriersstatusreason = wait.until(
							ExpectedConditions.presenceOfElementLocated(By.xpath(config.getBarriersReason())));
					String Barriersstatusreason1 = Barriersstatusreason.getText().toString().trim();
					Log.info("Barriers status reason---" + Barriersstatusreason1);
					Assert.assertEquals(Barriersstatusreason1, "Unable to Contact");
					
	}

		public void addActivityUTC() throws Exception {
			Log = Logger.getLogger("ActivitiesUTCFlow.class");
			PropertyConfigurator.configure("log4j.properties");
			AnnualAssessment annual = new AnnualAssessment(driver);
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			Log.info("Click on add activity");
			WebElement addactivity = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getAddActivities())));
			addactivity.click();
			ActivityPage activity = PageFactory.initElements(driver, ActivityPage.class);
			activity.addActivityFromActivitiesPage();
			ActivityStatus = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getClinicalInterventionActivitystatus())));
		String ActivityStatus1= ActivityStatus.getText().toString().trim();
			Log.info("Activity status---" + ActivityStatus1);
			if (ActivityStatus1.equals(GlobalValues.Open_status.toString())
					|| ActivityStatus1.equals(GlobalValues.InProgress_status.toString()))

			{
				
				// adding goals,interventions and barriers
				BarriersPage barrierspage = PageFactory.initElements(driver, BarriersPage.class);
				barrierspage.clickCarePlan();
				barrierspage.AddGoal();
				barrierspage.clickBarriers();
				barrierspage.AddBarriersToGoal();
				
				// Left Navigation
			   leftNav = PageFactory.initElements(driver, LeftNavigationLink.class);
				leftNav.clickInterventions();
				Interventions interventionspage = PageFactory.initElements(driver, Interventions.class);
				interventionspage.AddInterventions_Verify();
				// Left Navigation
				 leftNav = PageFactory.initElements(driver, LeftNavigationLink.class);
				leftNav.clickActivities();
				Thread.sleep(4000);
			
				Log.info("Click on Edit clinical intervention activity");
				WebElement editClinicalIntervention = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getEditClinicalIntervention())));
				editClinicalIntervention.click();
				WebElement accordionIntroAndPermission = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getIntroandPermissionsaccordian())));
				accordionIntroAndPermission.click();
				annual.annual_assessment("AnnualAssessments_CMDM", "Intial_InroAndPermissions_UTC", 2);
				for (int i = 0; i <= 2; i++) {
					WebElement saveprogressIntialOutreach = wait.until(
							ExpectedConditions.elementToBeClickable(By.xpath(config.getIntialOutreachSaveProgress())));
					saveprogressIntialOutreach.click();
					Log.info("Clicked on Save Progress");
					Thread.sleep(5000);
					WebElement IntialOutreachOkbutton = wait
							.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getInitialOkbutton())));
					IntialOutreachOkbutton.click();
					Thread.sleep(5000);

				}
			
			}
			// Left Navigation
						LeftNavigationLink leftNav = PageFactory.initElements(driver, LeftNavigationLink.class);
						leftNav.clickActivities();
						Thread.sleep(3000);
						WebElement ClinicalInterventionActivityStatus = wait.until(
								ExpectedConditions.elementToBeClickable(By.xpath(config.getClinicalInterventionActivitystatus())));
						String ClinicalInterventionStatus = ClinicalInterventionActivityStatus.getText().toString().trim();
						Log.info("ClinicalInterventionStatus---" + ClinicalInterventionStatus);
						if (ClinicalInterventionStatus.equalsIgnoreCase(GlobalValues.Completed_status)) {
							Log.info("ClinicalIntervention UTC flow verified");
						}
						checkStatus();

	
}
}
