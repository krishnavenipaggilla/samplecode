/**
 * 
 */
package com.nimbus.testcase;

import org.testng.annotations.Test;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.nimbus.pages.BarriersPage;
import com.nimbus.pages.CareCoordination_Activity;
import com.nimbus.pages.ActivitiesGridVerification;
import com.nimbus.pages.ActivityPage;
import com.nimbus.pages.ActivityProviderSubSection;
import com.nimbus.pages.ActivityRecordContact;
import com.nimbus.pages.ActivitySubSectionMedicationsPage;
import com.nimbus.pages.ActivitysubsectionImmunizationPage;
import com.nimbus.pages.AdmissionSubActivity;
import com.nimbus.pages.CarePlan;
import com.nimbus.pages.GlobalValues;
import com.nimbus.pages.Global_Method;
import com.nimbus.pages.InitialOutreach_Activity;
import com.nimbus.pages.LeftNavigationLink;
import com.nimbus.pages.LifeStylePreventionSubSection;
import com.nimbus.pages.LoginPage;
import com.nimbus.pages.LogoutPage;
import com.nimbus.pages.MemberRepresentativesSubSection;
import com.nimbus.pages.MemberSearch;
import com.nimbus.pages.MongoDbConnection;
import com.nimbus.pages.Payload;
import com.nimbus.pages.Questionnaires;
import com.nimbus.pages.QuestionnairesSection;
import com.nimbus.pages.RecordContactSubSection;
import com.nimbus.pages.Referral_Activity;
import com.nimbus.pages.TempMemberCreation;
import com.nimbus.pages.CaseClosure_Activity;
import com.nimbus.pages.CaseReview_Activity;
import com.nimbus.pages.ConditionSubsection;
import com.nimbus.pages.Consultation_Activity;
import com.nimbus.pages.ClinicalIntervention_Activity;
import com.nimbus.pages.MgmtReasonSubSection;

import utility.ConfigReader;
import utility.UtilityClass;

/**
 * 
 *
 */

public class TestCases extends UtilityClass {

	ConfigReader config = new ConfigReader();

	// Environment can be - SIT, DEV, SANDBOX,UAT

	// Set the env in GlobalValues.Environment to run on diff env and uncomment
	// the URL in config file to pick that URL.

	// @BeforeSuite
	// public void setReportFileName(){
	// reportFileName = "TestCases2Report.html";
	// }

	// @Test(priority = 1, enabled = false)
	public void payloadCase() throws Exception {
		Global_Method gm = new Global_Method(driver);

		Payload Request1 = PageFactory.initElements(driver, Payload.class);

		int statusCode = Request1.payloadCase();
		if (statusCode == 200) {
			gm.Reports("NIM-8422", "Pass");
			gm.Reports("NIM-14382", "Pass");
			Log.info("Case got generated using payload");
		} else {
			gm.Reports("NIM-8422", "Fail");
			gm.Reports("NIM-14382", "Fail");
		}

	}

	@Test(priority = 1, enabled = true)
	public void tempmemCreation() throws Exception {
		Global_Method GM = PageFactory.initElements(driver, Global_Method.class);
		TempMemberCreation Request1 = PageFactory.initElements(driver, TempMemberCreation.class);

		LoginPage login_page = PageFactory.initElements(driver, LoginPage.class);
		login_page.login_Supervisor();
		Log.info("Logged in as Supervisor");

		try {
			Request1.tempMemberCreation();
			GM.Reports("NIM-13182", "Pass");
		} catch (Exception e) {
			e.printStackTrace();
			GM.Reports("NIM-13182", "Fail");
		}

	
	}

 
	 @Test(priority = 3, enabled = true)
	public void ActivitiesPage1() throws Exception {
		Global_Method GM = PageFactory.initElements(driver, Global_Method.class);
		ActivitiesGridVerification Request1 = PageFactory.initElements(driver, ActivitiesGridVerification.class);
		LeftNavigationLink leftNav = PageFactory.initElements(driver, LeftNavigationLink.class);

		try {
	
			Request1.ActivitiesGrid();
			GM.Reports("NIM-8218", "Pass");
		} catch (Exception e) {
			e.printStackTrace();
			GM.Reports("NIM-8218", "Fail");

		}
	}
	

	  // Initial outreach
	@Test(priority = 4, enabled = true)
	public void validateInitialOutreachActivitySubsections() throws Exception {
		new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		PageFactory.initElements(driver, MemberSearch.class);
		Global_Method gm = new Global_Method(driver);
		PageFactory.initElements(driver, LeftNavigationLink.class);
		InitialOutreach_Activity InitialOutreach_page = PageFactory.initElements(driver,
				InitialOutreach_Activity.class);
		try {

			InitialOutreach_page.initialOutreach();

			gm.Reports("NIM-18441", "Pass");
			gm.Reports("NIM-16320", "Pass");
		} catch (Exception e) {
			gm.Reports("NIM-16320", "Fail");
			gm.Reports("NIM-18441", "Fail");
		}

	}

	@Test(priority = 5, enabled = true)
	public void ConditionSubsection() throws Throwable {
		Global_Method GM = new Global_Method(driver);
		LeftNavigationLink leftNavigation = PageFactory.initElements(driver, LeftNavigationLink.class);

		try {
			Thread.sleep(3000);
			com.nimbus.pages.ConditionSubsection c = PageFactory.initElements(driver, ConditionSubsection.class);
			c.condition("Initial Outreach");
			GM.Reports("NIM-17033", "Pass");

		} catch (Exception e) {
			GM.Reports("NIM-17033", "Fail");

		}

		leftNavigation.clickOverview();

	}

	@Test(priority = 6, enabled = true)
	public void verifyAdmissions() throws Exception {
		Global_Method GM = PageFactory.initElements(driver, Global_Method.class);
		AdmissionSubActivity admission = PageFactory.initElements(driver, AdmissionSubActivity.class);
		new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		LeftNavigationLink leftNav = PageFactory.initElements(driver, LeftNavigationLink.class);

		try {
			leftNav.clickMemberActionCenter();
			leftNav.clickActivities();
			admission.verifyAdmissionsFromActivity("Initial Outreach");
			GM.Reports("NIM-17032", "Pass");

		} catch (Exception e) {
			GM.Reports("NIM-17032", "Fail");

		}

		leftNav.clickOverview();

	}

	
	@Test(priority = 7, enabled = true)
	public void ActivitySubsectionForImmunization() throws Throwable {
		Global_Method GM = new Global_Method(driver);
		PageFactory.initElements(driver, LeftNavigationLink.class);
		CarePlan CP = PageFactory.initElements(driver, CarePlan.class);
		ActivityPage ap = PageFactory.initElements(driver, ActivityPage.class);
		BarriersPage Barrier = PageFactory.initElements(driver, BarriersPage.class);
		boolean passed = true;

		try {
			ActivitysubsectionImmunizationPage ActivitySubsection = PageFactory.initElements(driver,
					ActivitysubsectionImmunizationPage.class);

			ActivitySubsection.clickMemberActionCenter();
			ActivitySubsection.clickActivity();
	
			ActivitySubsection.AddImmunization("Initial Outreach");

		} catch (Exception e) {
			Log.info(e);
			passed = false;

		}

		try {
			ActivitysubsectionImmunizationPage ActivitySubsection = PageFactory.initElements(driver,
					ActivitysubsectionImmunizationPage.class);
			ActivitySubsection.ClickUpdateImmunization();
			ActivitySubsection.UpdateImmunization();
			ActivitySubsection.CheckUpdateButtonDisabled();
			ActivitySubsection.CheckImmunizationPagination();

		} catch (Exception e) {
			e.printStackTrace();
			Log.info(e);
			passed = false;

		}

		if (passed == true) {
			GM.Reports("NIM-17036", "Pass");
		} else {

			GM.Reports("NIM-17036", "Fail");
		}
		
		
		try {
//
			ap.clickcareplanSubActivity();
			CP.CarePlan_AddGoal();
			CP.CarePlan_clicksubmit();
			
			ap.clickcareplanSubActivity();
			CP.CP_AddInterventions();
			
			ap.clickcareplanSubActivity();
			CP.Editgoalinprogress();
			
			ap.clickcareplanSubActivity();
			CP.Editgoalclose();
			
			ap.clickcareplanSubActivity();
			CP.validateGoalHistoryColumns();
			CP.verifyGoalHistoryData();
			CP.clickBackButton();
			 
			ap.clickcareplanSubActivity();
			Barrier.clickBarriersinactivity();
			Barrier.AddBarriersToGoal_Subsection();
			 
			ap.clickcareplanSubActivity();
			Barrier.VerifyBarriersdata();
			Barrier.ClickEditBarrier();
			Barrier.EditBarrier();
//			CP.clickBackButton();
			

		} catch (Exception e) {
			e.printStackTrace();
			Log.info(e);
			

		}
		
		

//		leftNavigation.clickOverview();

	}
	

	@Test(priority = 8, enabled = true)
	public void ValidateLSPSubSection() throws Exception {
		Global_Method GM = PageFactory.initElements(driver, Global_Method.class);
		LeftNavigationLink leftNavigation = PageFactory.initElements(driver, LeftNavigationLink.class);

		LifeStylePreventionSubSection request3 = PageFactory.initElements(driver, LifeStylePreventionSubSection.class);

		try {
//			leftNavigation.clickMemberActionCenter();
//			leftNavigation.clickActivities();
			request3.verifyLSPFromActivity("Initial Outreach");     //Show History not displaying
			GM.Reports("NIM-17060", "Pass");

		} catch (Exception e) {
			e.printStackTrace();
			GM.Reports("NIM-17060", "Fail");

		}

		leftNavigation.clickOverview();
	}

	//Quesionnaire
	@Test(priority = 9, enabled = true)
	public void questionnairesSection() throws Exception {
		Global_Method GM = PageFactory.initElements(driver, Global_Method.class);
		LeftNavigationLink leftNavigation = PageFactory.initElements(driver, LeftNavigationLink.class);
		try {
			leftNavigation.clickMemberActionCenter();
			leftNavigation.clickActivities();			
			QuestionnairesSection Request1 = PageFactory.initElements(driver, QuestionnairesSection.class);
			Request1.questionnairesSection("Initial Outreach");
			Request1.addQuestionnaires();
			GM.Reports("NIM-17006", "Pass");
		} catch (Exception e) {
			e.printStackTrace();
			GM.Reports("NIM-17006", "Fail");

		}
		leftNavigation.clickOverview();

	}

	//Questionnare1
	@Test(priority = 10, enabled = true)
	public void Questionnaire() throws Exception {
		new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		PageFactory.initElements(driver, LeftNavigationLink.class);
		Questionnaires Ques_naire = PageFactory.initElements(driver, Questionnaires.class);
		LeftNavigationLink leftNavigation = PageFactory.initElements(driver, LeftNavigationLink.class);
		Global_Method GM = PageFactory.initElements(driver, Global_Method.class);
		try {
			Ques_naire.AddAssessments("Initial Outreach");
			Ques_naire.QuestionnairesHistory();
			Ques_naire.SaveAsDraft();
			Ques_naire.DiscardDraft();
			Ques_naire.SubmitFinal();

			GM.Reports("NIM-14389", "Pass");
			GM.Reports("NIM-11391", "Pass");
		} catch (Exception e) {
			e.printStackTrace();
			GM.Reports("NIM-14389", "Fail");
			GM.Reports("NIM-11391", "Fail");
		}

//		leftNavigation.clickOverview();
	}

	//Medications
	@Test(priority = 11, enabled = true)
	public void ActivitySubSectionMedications() throws Exception {
		Global_Method GM = PageFactory.initElements(driver, Global_Method.class);
		LeftNavigationLink leftNavigation = PageFactory.initElements(driver, LeftNavigationLink.class);

		try {
			ActivitySubSectionMedicationsPage activitySubSectionMedPage = PageFactory.initElements(driver,
					ActivitySubSectionMedicationsPage.class);
			activitySubSectionMedPage.medicationAction("Initial Outreach");

			GM.Reports("NIM-17035", "Pass");
		} catch (Exception e) {
			e.printStackTrace();
			GM.Reports("NIM-17035", "Fail");
		}
		leftNavigation.clickOverview();
	}

	@Test(priority = 12, enabled = true)
	public void ValidateRecordContactSubSection_InitialOutreach() throws Exception {
		Global_Method GM = PageFactory.initElements(driver, Global_Method.class);
		LeftNavigationLink leftNavigation = PageFactory.initElements(driver, LeftNavigationLink.class);

		RecordContactSubSection request4 = PageFactory.initElements(driver, RecordContactSubSection.class);

		try {
			leftNavigation.clickMemberActionCenter();
			leftNavigation.clickActivities();
			request4.verifyRecordContactSubActivity("Initial Outreach");
			GM.Reports("NIM-17018", "Pass");

		} catch (Exception e) {
			e.printStackTrace();
			GM.Reports("NIM-17018", "Fail");

		}
		leftNavigation.clickOverview();
	} 
	

	//Member Representatives for Initial outreach
	@Test(priority = 13, enabled = true)
	public void ActivitySubSectionMemRepresentatives() throws Exception {
		Global_Method GM = PageFactory.initElements(driver, Global_Method.class);
		LeftNavigationLink leftNavigation = PageFactory.initElements(driver, LeftNavigationLink.class);

		try {
			MemberRepresentativesSubSection subSectionMemRepresentative = PageFactory.initElements(driver,
					MemberRepresentativesSubSection.class);
			leftNavigation.clickMemberActionCenter();
			leftNavigation.clickActivities();
			subSectionMemRepresentative.memRepresentativeSection("Initial Outreach");
			subSectionMemRepresentative.addMemberRepresentative();
			subSectionMemRepresentative.editMemberRepresentative();
			GM.Reports("NIM-17005", "Pass");
		} catch (Exception e) {
			e.printStackTrace();
			GM.Reports("NIM-17005", "Fail");
		}
		leftNavigation.clickOverview();
	}
	
	//Provider
			@Test(priority = 14, enabled = true)
			public void ActivitySubSectionProvider() throws Exception {
				Global_Method GM = PageFactory.initElements(driver, Global_Method.class);
				LeftNavigationLink leftNavigation = PageFactory.initElements(driver, LeftNavigationLink.class);

				try {
					
					leftNavigation.clickMemberActionCenter();
					leftNavigation.clickActivities();
					ActivityProviderSubSection providersubsection = PageFactory.initElements(driver,
							ActivityProviderSubSection.class);
					providersubsection.ProviderManagement("Initial Outreach");
					providersubsection.editProvider("Initial Outreach");
					providersubsection.searchProvider();

					GM.Reports("NIM-17004", "Pass");
				} catch (Exception e) {
					e.printStackTrace();
					GM.Reports("NIM-17004", "Fail");
				}
				leftNavigation.clickOverview();
			}
			
			//Record contact
			@Test(priority = 15, enabled = true)
			public void ActivitySubSectionRecordContact() throws Exception {
				Global_Method GM = PageFactory.initElements(driver, Global_Method.class);
				LeftNavigationLink leftNavigation = PageFactory.initElements(driver, LeftNavigationLink.class);

				try {
					
					leftNavigation.clickMemberActionCenter();
					leftNavigation.clickActivities();
					ActivityRecordContact recordcontact = PageFactory.initElements(driver,
							ActivityRecordContact.class);
					recordcontact.activityRecordContact("Initial Outreach");
					
					GM.Reports("NIM-17018", "Pass");
				} catch (Exception e) {
					e.printStackTrace();
					GM.Reports("NIM-17018", "Fail");
				}
				leftNavigation.clickOverview();
			}

	

	 @Test(priority = 16, enabled = true)
	public void verifyClinicalInterventionGenerated() throws Exception {
		PageFactory.initElements(driver, Global_Method.class);
		new Global_Method(driver);
		new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		LeftNavigationLink leftNav = PageFactory.initElements(driver, LeftNavigationLink.class);
		InitialOutreach_Activity InitialOutreach_page = PageFactory.initElements(driver,
				InitialOutreach_Activity.class);

		try {
			leftNav.clickMemberActionCenter();
			leftNav.clickActivities();
			ActivityPage addActivity = new ActivityPage(driver);
			addActivity.editInitialOutreachActivity("Initial Outreach");
			InitialOutreach_page.fillIntroandPermissions();
		}

		catch (Exception e) {

		}

		try {
			Thread.sleep(3000);
			InitialOutreach_page.verifyClinicalIntervention();
			Log.info("Initial outreach completed");

		} catch (Exception e) {
			e.printStackTrace();
			Log.info("Initial outreach not completed");

		}
		leftNav.clickOverview();
	} 

	

	 
	// -------------------------------------Starting Clinical intervention activity ------------//

    // Clinical intervention activity
	@Test(priority = 15, enabled = true)
	public void validateClinicalInterventionActivitySubsections() throws Exception {
		new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		PageFactory.initElements(driver, MemberSearch.class);
		Global_Method gm = new Global_Method(driver);
		
		
		try {
		
			ClinicalIntervention_Activity ClinicalIntervention_page = PageFactory.initElements(driver,
					ClinicalIntervention_Activity.class);

//			leftNav.clickMemberActionCenter();
//			leftNav.clickActivities();
			Log.info("-------Starting Clinical intervention activity------------");
			ClinicalIntervention_page.clinicalIntervention();
			ClinicalIntervention_page.addSubsections();

			gm.Reports("Notes-1", "Pass");
		} catch (Exception e) {
			gm.Reports("Notes-1", "Fail");
		}


//		leftNav.clickOverview();
	}

	@Test(priority = 16, enabled = true)
	public void ConditionSubsection_Clinical() throws Throwable {
		Global_Method GM = new Global_Method(driver);
		LeftNavigationLink leftNavigation = PageFactory.initElements(driver, LeftNavigationLink.class);

			try {
			Thread.sleep(3000);
			com.nimbus.pages.ConditionSubsection c = PageFactory.initElements(driver, ConditionSubsection.class);
			c.condition("Clinical Intervention");
			GM.Reports("NIM-17033", "Pass");

		} catch (Exception e) {
			GM.Reports("NIM-17033", "Fail");

		}

		leftNavigation.clickOverview();

	}

	@Test(priority = 17, enabled = true)
	public void verifyAdmissions_Clinical() throws Exception {
		Global_Method GM = PageFactory.initElements(driver, Global_Method.class);
		AdmissionSubActivity admission = PageFactory.initElements(driver, AdmissionSubActivity.class);
		new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		LeftNavigationLink leftNav = PageFactory.initElements(driver, LeftNavigationLink.class);

	
		try {
			leftNav.clickMemberActionCenter();
			leftNav.clickActivities();
			admission.verifyAdmissionsFromActivity("Clinical Intervention");
			GM.Reports("NIM-17032", "Pass");

		} catch (Exception e) {
			GM.Reports("NIM-17032", "Fail");

		}
	
//		leftNav.clickOverview();

	}
	

	//Questionnaire
	@Test(priority = 18, enabled = true)
	public void questionnairesSection_clinical() throws Exception {
		Global_Method GM = PageFactory.initElements(driver, Global_Method.class);
		LeftNavigationLink leftNavigation = PageFactory.initElements(driver, LeftNavigationLink.class);
		CarePlan CP = PageFactory.initElements(driver, CarePlan.class);
		ActivityPage ap = PageFactory.initElements(driver, ActivityPage.class);
		BarriersPage Barrier = PageFactory.initElements(driver, BarriersPage.class);
		try {
			leftNavigation.clickMemberActionCenter();
			leftNavigation.clickActivities();			
			QuestionnairesSection Request1 = PageFactory.initElements(driver, QuestionnairesSection.class);
			
			
			Request1.questionnairesSection("Clinical Intervention");
			Request1.addQuestionnaires();
			GM.Reports("NIM-17006", "Pass");
		} catch (Exception e) {
			e.printStackTrace();
			GM.Reports("NIM-17006", "Fail");

		}
		
		try {

			ap.clickcareplanSubActivity();
			CP.CarePlan_AddGoal();
			CP.CarePlan_clicksubmit();
			
			ap.clickcareplanSubActivity();
			CP.CP_AddInterventions();
			
			ap.clickcareplanSubActivity();
			CP.Editgoalinprogress();
			
			ap.clickcareplanSubActivity();
			CP.Editgoalclose();
			
			ap.clickcareplanSubActivity();
			CP.validateGoalHistoryColumns();
			CP.verifyGoalHistoryData();
			CP.clickBackButton();
			 
			ap.clickcareplanSubActivity();
			Barrier.clickBarriers();
			Barrier.AddBarriersToGoal_Subsection();
			 
			ap.clickcareplanSubActivity();
			Barrier.VerifyBarriersdata();
			Barrier.ClickEditBarrier();
			Barrier.EditBarrier();
//			CP.clickBackButton();
			

		} catch (Exception e) {
			e.printStackTrace();
			Log.info(e);
			

		}
		
		
//		leftNavigation.clickOverview();

	}


	// medications
	@Test(priority = 19, enabled = true)
	public void ActivitySubSectionMedications_Clinical() throws Exception {
		Global_Method GM = PageFactory.initElements(driver, Global_Method.class);
		LeftNavigationLink leftNavigation = PageFactory.initElements(driver, LeftNavigationLink.class);

		try {
			ActivitySubSectionMedicationsPage activitySubSectionMedPage = PageFactory.initElements(driver,
					ActivitySubSectionMedicationsPage.class);
			activitySubSectionMedPage.medicationAction("Clinical Intervention");

			GM.Reports("NIM-17035", "Pass");
		} catch (Exception e) {
			e.printStackTrace();
			GM.Reports("NIM-17035", "Fail");
		}
		leftNavigation.clickOverview();
	}

	
	@Test(priority = 20, enabled = true)
	public void ValidateRecordContactSubSection_Clinical() throws Exception {
		Global_Method GM = PageFactory.initElements(driver, Global_Method.class);
		LeftNavigationLink leftNavigation = PageFactory.initElements(driver, LeftNavigationLink.class);

		RecordContactSubSection request4 = PageFactory.initElements(driver, RecordContactSubSection.class);

		try {
			leftNavigation.clickMemberActionCenter();
			leftNavigation.clickActivities();
			request4.verifyRecordContactSubActivity("Clinical Intervention");
			GM.Reports("NIM-17018", "Pass");

		} catch (Exception e) {
			e.printStackTrace();
			GM.Reports("NIM-17018", "Fail");

		}
		
	} 
	
	//Member Representatives for Clinical Intervention
	@Test(priority = 21, enabled = true)
	public void ActivitySubSectionMemRepresentatives_Clinical() throws Exception {
		Global_Method GM = PageFactory.initElements(driver, Global_Method.class);
		LeftNavigationLink leftNavigation = PageFactory.initElements(driver, LeftNavigationLink.class);

		try {
			MemberRepresentativesSubSection subSectionMemRepresentative = PageFactory.initElements(driver,
					MemberRepresentativesSubSection.class);
			leftNavigation.clickMemberActionCenter();
			leftNavigation.clickActivities();
			subSectionMemRepresentative.memRepresentativeSection("Clinical Intervention");
			subSectionMemRepresentative.addMemberRepresentative();
			subSectionMemRepresentative.editMemberRepresentative();
			GM.Reports("NIM-17005", "Pass");
		} catch (Exception e) {
			e.printStackTrace();
			GM.Reports("NIM-17005", "Fail");
		}
		leftNavigation.clickOverview();
	}
	

	
// ----------------------- Starting Referral activity----------------------//
	// Referral
	@Test(priority = 20, enabled = true)
	public void validateReferralActivitySubsections() throws Exception {
		new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		PageFactory.initElements(driver, MemberSearch.class);
		Global_Method gm = new Global_Method(driver);
		LeftNavigationLink leftNav = PageFactory.initElements(driver, LeftNavigationLink.class);
		PageFactory.initElements(driver, CarePlan.class);
		PageFactory.initElements(driver, ActivityPage.class);
		PageFactory.initElements(driver, BarriersPage.class);
		try {

			leftNav.clickMemberActionCenter();
			leftNav.clickActivities();

			Referral_Activity Referral_page = PageFactory.initElements(driver, Referral_Activity.class);
			Log.info("-------Starting Referral activity------------");
			Referral_page.referral();
			Referral_page.addSubsections();

			gm.Reports("Notes-3", "Pass");
		} catch (Exception e) {
			gm.Reports("Notes-3", "Fail");
		}
		
		
		

//		leftNav.clickOverview();
	}
	
//Conditions
	@Test(priority = 21, enabled = true)
	public void ConditionSubsection_Referral() throws Throwable {
		Global_Method GM = new Global_Method(driver);
		LeftNavigationLink leftNavigation = PageFactory.initElements(driver, LeftNavigationLink.class);

	
		try {
			Thread.sleep(3000);
			com.nimbus.pages.ConditionSubsection c = PageFactory.initElements(driver, ConditionSubsection.class);
			c.condition("Referral");
			GM.Reports("NIM-17033", "Pass");

		} catch (Exception e) {
			GM.Reports("NIM-17033", "Fail");

		}

		leftNavigation.clickOverview();

	}

	//Admissions
	@Test(priority = 22, enabled = true)
	public void verifyAdmissions_Referral() throws Exception {
		Global_Method GM = PageFactory.initElements(driver, Global_Method.class);
		AdmissionSubActivity admission = PageFactory.initElements(driver, AdmissionSubActivity.class);
		new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		LeftNavigationLink leftNav = PageFactory.initElements(driver, LeftNavigationLink.class);

	
		try {
			leftNav.clickMemberActionCenter();
			leftNav.clickActivities();
			admission.verifyAdmissionsFromActivity("Referral");
			GM.Reports("NIM-17032", "Pass");

		} catch (Exception e) {
			GM.Reports("NIM-17032", "Fail");

		}
		leftNav.clickOverview();

	}

	
	// medications
		@Test(priority = 23, enabled = true)
	public void ActivitySubSectionMedications_Referral() throws Exception {
		Global_Method GM = PageFactory.initElements(driver, Global_Method.class);
		LeftNavigationLink leftNav = PageFactory.initElements(driver, LeftNavigationLink.class);
		CarePlan CP = PageFactory.initElements(driver, CarePlan.class);
		ActivityPage ap = PageFactory.initElements(driver, ActivityPage.class);
		BarriersPage Barrier = PageFactory.initElements(driver, BarriersPage.class);
		
		try {
			ActivitySubSectionMedicationsPage activitySubSectionMedPage = PageFactory.initElements(driver,
					ActivitySubSectionMedicationsPage.class);
			leftNav.clickMemberActionCenter();
			leftNav.clickActivities();
			activitySubSectionMedPage.medicationAction("Referral");

			GM.Reports("NIM-17035", "Pass");
		} catch (Exception e) {
			e.printStackTrace();
			GM.Reports("NIM-17035", "Fail");
		}
		
		try {

			ap.clickcareplanSubActivity();
			CP.CarePlan_AddGoal();
			CP.CarePlan_clicksubmit();
			
			ap.clickcareplanSubActivity();
			CP.CP_AddInterventions();
			
			ap.clickcareplanSubActivity();
			CP.Editgoalinprogress();
			
			ap.clickcareplanSubActivity();
			CP.Editgoalclose();
			
			ap.clickcareplanSubActivity();
			CP.validateGoalHistoryColumns();
			CP.verifyGoalHistoryData();
			CP.clickBackButton();
			 
			ap.clickcareplanSubActivity();
			Barrier.clickBarriers();
			Barrier.AddBarriersToGoal_Subsection();
			 
			ap.clickcareplanSubActivity();
			Barrier.VerifyBarriersdata();
			Barrier.ClickEditBarrier();
			Barrier.EditBarrier();
			CP.clickBackButton();
			

		} catch (Exception e) {
			e.printStackTrace();
			Log.info(e);
			

		}
		
		
		leftNav.clickOverview();
	} 
	
	
	//Record Contact subsection for Referral
		@Test(priority = 24, enabled = true)
		public void ValidateRecordContactSubSection_Referral() throws Exception {
			Global_Method GM = PageFactory.initElements(driver, Global_Method.class);
			LeftNavigationLink leftNavigation = PageFactory.initElements(driver, LeftNavigationLink.class);

			RecordContactSubSection request4 = PageFactory.initElements(driver, RecordContactSubSection.class);

			try {
				leftNavigation.clickMemberActionCenter();
				leftNavigation.clickActivities();
				request4.verifyRecordContactSubActivity("Referral");
				GM.Reports("NIM-17018", "Pass");

			} catch (Exception e) {
				e.printStackTrace();
				GM.Reports("NIM-17018", "Fail");

			}
			
		}	
		
		
		//Member Representatives for Referral Intervention
		//@Test(priority = 25, enabled = true)
		public void ActivitySubSectionMemRepresentatives_Referral() throws Exception {
			Global_Method GM = PageFactory.initElements(driver, Global_Method.class);
			LeftNavigationLink leftNavigation = PageFactory.initElements(driver, LeftNavigationLink.class);

			try {
				MemberRepresentativesSubSection subSectionMemRepresentative = PageFactory.initElements(driver,
						MemberRepresentativesSubSection.class);
				leftNavigation.clickMemberActionCenter();
				leftNavigation.clickActivities();
				subSectionMemRepresentative.memRepresentativeSection("Referral");
				subSectionMemRepresentative.addMemberRepresentative();
				subSectionMemRepresentative.editMemberRepresentative();
				GM.Reports("NIM-17005", "Pass");
			} catch (Exception e) {
				e.printStackTrace();
				GM.Reports("NIM-17005", "Fail");
			}
			leftNavigation.clickOverview();
		}
	
		// -------------------------------- Starting consultation activity ----------------------------//

	// Consultation
	@Test(priority = 25, enabled = true)
	public void validateConsultationActivitySubsections() throws Exception {
		new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		PageFactory.initElements(driver, MemberSearch.class);
		Global_Method gm = new Global_Method(driver);
		LeftNavigationLink leftNav = PageFactory.initElements(driver, LeftNavigationLink.class);
		CarePlan CP = PageFactory.initElements(driver, CarePlan.class);
		ActivityPage ap = PageFactory.initElements(driver, ActivityPage.class);
		BarriersPage Barrier = PageFactory.initElements(driver, BarriersPage.class);
		try {

//			leftNav.clickMemberActionCenter();
			leftNav.clickActivities();
			Consultation_Activity Consultation_page = PageFactory.initElements(driver, Consultation_Activity.class);
			Log.info("-------Starting Consultation activity------------");
			Consultation_page.consultation();
			Consultation_page.addSubsections();

			gm.Reports("Notes-4", "Pass");
		} catch (Exception e) {
			gm.Reports("Notes-4", "Fail");
		}
		
		try {

			ap.clickcareplanSubActivity();
			CP.CarePlan_AddGoal();
			CP.CarePlan_clicksubmit();
			
			ap.clickcareplanSubActivity();
			CP.CP_AddInterventions();
			
			ap.clickcareplanSubActivity();
			CP.Editgoalinprogress();
			
			ap.clickcareplanSubActivity();
			CP.Editgoalclose();
			
			ap.clickcareplanSubActivity();
			CP.validateGoalHistoryColumns();
			CP.verifyGoalHistoryData();
			CP.clickBackButton();
			 
			ap.clickcareplanSubActivity();
			Barrier.clickBarriers();
			Barrier.AddBarriersToGoal_Subsection();
			 
			ap.clickcareplanSubActivity();
			Barrier.VerifyBarriersdata();
			Barrier.ClickEditBarrier();
			Barrier.EditBarrier();
			CP.clickBackButton();
			

		} catch (Exception e) {
			e.printStackTrace();
			Log.info(e);
			

		}

			leftNav.clickOverview();
	}


	// -------------------------------- Starting Care Coordination activity ----------------------------//
	// Care Coordination
	@Test(priority = 26, enabled = true)
	public void validateCareCoordinationActivitySubsections() throws Exception {
		new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		PageFactory.initElements(driver, MemberSearch.class);
		Global_Method gm = new Global_Method(driver);
		LeftNavigationLink leftNav = PageFactory.initElements(driver, LeftNavigationLink.class);
		try {
	

			leftNav.clickMemberActionCenter();
			leftNav.clickActivities();

			CareCoordination_Activity CareCoordination_page = PageFactory.initElements(driver,
					CareCoordination_Activity.class);
			Log.info("-------Starting care coordination activity------------");
			CareCoordination_page.carecoordination();
			CareCoordination_page.addSubsections();

			gm.Reports("Notes-5", "Pass");
		} catch (Exception e) {
			gm.Reports("Notes-5", "Fail");
		}

//			leftNav.clickOverview();
	}

	
	//conditions
	@Test(priority = 27, enabled = true)
	public void ConditionSubsection_Carecoordination() throws Throwable {
		Global_Method GM = new Global_Method(driver);
		LeftNavigationLink leftNavigation = PageFactory.initElements(driver, LeftNavigationLink.class);
		CarePlan CP = PageFactory.initElements(driver, CarePlan.class);
		ActivityPage ap = PageFactory.initElements(driver, ActivityPage.class);
		BarriersPage Barrier = PageFactory.initElements(driver, BarriersPage.class);
		
		try {
			Thread.sleep(3000);
			com.nimbus.pages.ConditionSubsection c = PageFactory.initElements(driver, ConditionSubsection.class);
			c.condition("Care Coordination");
			GM.Reports("NIM-17033", "Pass");

		} catch (Exception e) {
			GM.Reports("NIM-17033", "Fail");

		}

		try {

			ap.clickcareplanSubActivity();
			CP.CarePlan_AddGoal();
			CP.CarePlan_clicksubmit();
			
			ap.clickcareplanSubActivity();
			CP.CP_AddInterventions();
			
			ap.clickcareplanSubActivity();
			CP.Editgoalinprogress();
			
			ap.clickcareplanSubActivity();
			CP.Editgoalclose();
			
			ap.clickcareplanSubActivity();
			CP.validateGoalHistoryColumns();
			CP.verifyGoalHistoryData();
			CP.clickBackButton();
			 
			ap.clickcareplanSubActivity();
			Barrier.clickBarriers();
			Barrier.AddBarriersToGoal_Subsection();
			 
			ap.clickcareplanSubActivity();
			Barrier.VerifyBarriersdata();
			Barrier.ClickEditBarrier();
			Barrier.EditBarrier();
			CP.clickBackButton();
			

		} catch (Exception e) {
			e.printStackTrace();
			Log.info(e);
			

		}
		leftNavigation.clickOverview();

	}

	//admissions
	@Test(priority = 28, enabled = true)
	public void verifyAdmissions_Carecoordination() throws Exception {
		Global_Method GM = PageFactory.initElements(driver, Global_Method.class);
		AdmissionSubActivity admission = PageFactory.initElements(driver, AdmissionSubActivity.class);
		new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		LeftNavigationLink leftNav = PageFactory.initElements(driver, LeftNavigationLink.class);

				try {
			leftNav.clickMemberActionCenter();
			leftNav.clickActivities();
			admission.verifyAdmissionsFromActivity("Care Coordination");
			GM.Reports("NIM-17032", "Pass");

		} catch (Exception e) {
			GM.Reports("NIM-17032", "Fail");

		}
		
		leftNav.clickOverview();

	}
	//Questionnaire
		@Test(priority = 29, enabled = true)
		public void questionnairesSection_Carecoordination() throws Exception {
			Global_Method GM = PageFactory.initElements(driver, Global_Method.class);
			LeftNavigationLink leftNavigation = PageFactory.initElements(driver, LeftNavigationLink.class);
			try {
				leftNavigation.clickMemberActionCenter();
				leftNavigation.clickActivities();			
				QuestionnairesSection Request1 = PageFactory.initElements(driver, QuestionnairesSection.class);
				Request1.questionnairesSection("Care Coordination");
				Request1.addQuestionnaires();
				GM.Reports("NIM-17006", "Pass");
			} catch (Exception e) {
				e.printStackTrace();
				GM.Reports("NIM-17006", "Fail");

			}
			leftNavigation.clickOverview();

		}
	// medications
	@Test(priority = 30, enabled = true)
	public void ActivitySubSectionMedications_Carecoordination() throws Exception {
		Global_Method GM = PageFactory.initElements(driver, Global_Method.class);
		LeftNavigationLink leftNavigation = PageFactory.initElements(driver, LeftNavigationLink.class);

		try {
			ActivitySubSectionMedicationsPage activitySubSectionMedPage = PageFactory.initElements(driver,
					ActivitySubSectionMedicationsPage.class);
			activitySubSectionMedPage.medicationAction("Care Coordination");

			GM.Reports("NIM-17035", "Pass");
		} catch (Exception e) {
			e.printStackTrace();
			GM.Reports("NIM-17035", "Fail");
		}
		leftNavigation.clickOverview();
	}

	
	//Record Contact subsection for Care Coordination
		@Test(priority = 31, enabled = true)
		public void ValidateRecordContactSubSection_CareCoordination() throws Exception {
			Global_Method GM = PageFactory.initElements(driver, Global_Method.class);
			LeftNavigationLink leftNavigation = PageFactory.initElements(driver, LeftNavigationLink.class);

			RecordContactSubSection request4 = PageFactory.initElements(driver, RecordContactSubSection.class);

			try {
				leftNavigation.clickMemberActionCenter();
				leftNavigation.clickActivities();
				request4.verifyRecordContactSubActivity("Care Coordination");
				GM.Reports("NIM-17018", "Pass");

			} catch (Exception e) {
				e.printStackTrace();
				GM.Reports("NIM-17018", "Fail");

			}
			
		}
		//Member Representatives for CareCoordination Activity
		@Test(priority = 32, enabled = true)
		public void ActivitySubSectionMemRepresentatives_CareCoordination() throws Exception {
			Global_Method GM = PageFactory.initElements(driver, Global_Method.class);
			LeftNavigationLink leftNavigation = PageFactory.initElements(driver, LeftNavigationLink.class);

			try {
				MemberRepresentativesSubSection subSectionMemRepresentative = PageFactory.initElements(driver,
						MemberRepresentativesSubSection.class);
				leftNavigation.clickMemberActionCenter();
				leftNavigation.clickActivities();
				subSectionMemRepresentative.memRepresentativeSection("Care Coordination");
				subSectionMemRepresentative.addMemberRepresentative();
				subSectionMemRepresentative.editMemberRepresentative();
				GM.Reports("NIM-17005", "Pass");
			} catch (Exception e) {
				e.printStackTrace();
				GM.Reports("NIM-17005", "Fail");
			}
			leftNavigation.clickOverview();
		}
		
		//Provider
		@Test(priority = 33, enabled = true)
		public void ActivitySubSectionProvider_CareCoordination() throws Exception {
			Global_Method GM = PageFactory.initElements(driver, Global_Method.class);
			LeftNavigationLink leftNavigation = PageFactory.initElements(driver, LeftNavigationLink.class);

			try {
				
				leftNavigation.clickMemberActionCenter();
				leftNavigation.clickActivities();
				ActivityProviderSubSection providersubsection = PageFactory.initElements(driver,
						ActivityProviderSubSection.class);
				providersubsection.ProviderManagement("Care Coordination");
				providersubsection.editProvider("Care Coordination");
				providersubsection.searchProvider();

				GM.Reports("NIM-17004", "Pass");
			} catch (Exception e) {
				e.printStackTrace();
				GM.Reports("NIM-17004", "Fail");
			}
			leftNavigation.clickOverview();
		}
	
	
	// -------------------------------- Starting Case Review activity ----------------------------//
	// Case Review
	@Test(priority = 32, enabled = true)
	public void validateCaseReviewActivitySubsections() throws Exception {
		new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		PageFactory.initElements(driver, MemberSearch.class);
		Global_Method gm = new Global_Method(driver);
		LeftNavigationLink leftNav = PageFactory.initElements(driver, LeftNavigationLink.class);
		try {

			leftNav.clickMemberActionCenter();
			leftNav.clickActivities();

			CaseReview_Activity CaseReview_page = PageFactory.initElements(driver, CaseReview_Activity.class);
			Log.info("-------Starting case review activity------------");
			CaseReview_page.casereview();
			CaseReview_page.addSubsections();

			gm.Reports("Notes-6", "Pass");
		} catch (Exception e) {
			gm.Reports("Notes-6", "Fail");
		}

			leftNav.clickOverview();
	}
	// -----------------------------  Starting Case closure activity -----------------------------//

	// Case closure
	@Test(priority = 33, enabled = true)
	public void validateCaseClosureActivitySubsections() throws Exception {
		new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		PageFactory.initElements(driver, MemberSearch.class);
		Global_Method gm = new Global_Method(driver);
		LeftNavigationLink leftNav = PageFactory.initElements(driver, LeftNavigationLink.class);
		try {

			leftNav.clickMemberActionCenter();
			leftNav.clickActivities();

			CaseClosure_Activity CaseClosure_page = PageFactory.initElements(driver, CaseClosure_Activity.class);
			Log.info("-------Starting case closure activity------------");
			CaseClosure_page.caseClosure();
			CaseClosure_page.addSubsections();

			gm.Reports("Notes-2", "Pass");
		} catch (Exception e) {
			gm.Reports("Notes-2", "Fail");
		}
		leftNav.clickOverview();
//
	}

	// subsection management reason for case closure
	@Test(priority = 34, enabled = true)
	public void MgmtReasonSubSection() throws Throwable {
		PageFactory.initElements(driver, LeftNavigationLink.class);
		Global_Method GM = new Global_Method(driver);
		Log.info("-----------Management reason subsection for Case closure activity---------");
		try {
			Thread.sleep(3000);
			MgmtReasonSubSection m = PageFactory.initElements(driver, MgmtReasonSubSection.class);
			m.mgmtreason("Case Closure");
			GM.Reports("NIM-17031", "Pass");

		} catch (Exception e) {
			GM.Reports("NIM-17031", "Fail");

		}
//		leftNav.clickOverview();
	}
	
	
	// medications
		@Test(priority = 35, enabled = true)
		public void ActivitySubSectionMedications_CaseClosure() throws Exception {
			Global_Method GM = PageFactory.initElements(driver, Global_Method.class);
			PageFactory.initElements(driver, LeftNavigationLink.class);
			CarePlan CP = PageFactory.initElements(driver, CarePlan.class);
			ActivityPage ap = PageFactory.initElements(driver, ActivityPage.class);
			BarriersPage Barrier = PageFactory.initElements(driver, BarriersPage.class);
			
			try {
				ActivitySubSectionMedicationsPage activitySubSectionMedPage = PageFactory.initElements(driver,
						ActivitySubSectionMedicationsPage.class);
				activitySubSectionMedPage.medicationAction("Case Closure");

				GM.Reports("NIM-17035", "Pass");
			} catch (Exception e) {
				e.printStackTrace();
				GM.Reports("NIM-17035", "Fail");
			}
			
			try {

				ap.clickcareplanSubActivity();
				CP.CarePlan_AddGoal();
				CP.CarePlan_clicksubmit();
				
				ap.clickcareplanSubActivity();
				CP.CP_AddInterventions();
				
				ap.clickcareplanSubActivity();
				CP.Editgoalinprogress();
				
				ap.clickcareplanSubActivity();
				CP.Editgoalclose();
				
				ap.clickcareplanSubActivity();
				CP.validateGoalHistoryColumns();
				CP.verifyGoalHistoryData();
				CP.clickBackButton();
				 
				ap.clickcareplanSubActivity();
				Barrier.clickBarriers();
				Barrier.AddBarriersToGoal_Subsection();
				 
				ap.clickcareplanSubActivity();
				Barrier.VerifyBarriersdata();
				Barrier.ClickEditBarrier();
				Barrier.EditBarrier();
				CP.clickBackButton();
				

			} catch (Exception e) {
				e.printStackTrace();
				Log.info(e);
				

			}
			
			
//			leftNavigation.clickOverview();
		} 


	//Conditions
	@Test(priority = 36, enabled = true)
	public void ConditionSubsection_Caseclosure() throws Throwable {
		Global_Method GM = new Global_Method(driver);
		LeftNavigationLink leftNav = PageFactory.initElements(driver, LeftNavigationLink.class);
		try {
			Thread.sleep(3000);
			com.nimbus.pages.ConditionSubsection c = PageFactory.initElements(driver, ConditionSubsection.class);
			c.condition("Case Closure");
			GM.Reports("NIM-17033", "Pass");

		} catch (Exception e) {
			GM.Reports("NIM-17033", "Fail");

		}

		leftNav.clickOverview();

	}

	//Admissions
	@Test(priority = 37, enabled = true)
	public void verifyAdmissions_caseclosure() throws Exception {
		Global_Method GM = PageFactory.initElements(driver, Global_Method.class);
		AdmissionSubActivity admission = PageFactory.initElements(driver, AdmissionSubActivity.class);
		new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		LeftNavigationLink leftNav = PageFactory.initElements(driver, LeftNavigationLink.class);

		try {
			leftNav.clickMemberActionCenter();
			leftNav.clickActivities();
			admission.verifyAdmissionsFromActivity("Case Closure");
			GM.Reports("NIM-17032", "Pass");

		} catch (Exception e) {
			GM.Reports("NIM-17032", "Fail");

		}

		leftNav.clickOverview();

	}

	//Immunization
	@Test(priority = 38, enabled = true)
	public void ActivitySubsectionForImmunization_Caseclosure() throws Throwable {
		Global_Method GM = new Global_Method(driver);
		LeftNavigationLink leftNav = PageFactory.initElements(driver, LeftNavigationLink.class);

		boolean passed = true;

		PageFactory.initElements(driver, LoginPage.class);

		try {
			ActivitysubsectionImmunizationPage ActivitySubsection = PageFactory.initElements(driver,
					ActivitysubsectionImmunizationPage.class);

			ActivitySubsection.clickMemberActionCenter();
			ActivitySubsection.clickActivity();
			Thread.sleep(4000);
			ActivitySubsection.AddImmunization("Case Closure");

		} catch (Exception e) {
			Log.info(e);
			passed = false;

		}

		try {
			ActivitysubsectionImmunizationPage ActivitySubsection = PageFactory.initElements(driver,
					ActivitysubsectionImmunizationPage.class);
			ActivitySubsection.ClickUpdateImmunization();
			ActivitySubsection.UpdateImmunization();
			ActivitySubsection.CheckUpdateButtonDisabled();
			Thread.sleep(3000);
			ActivitySubsection.CheckImmunizationPagination();

		} catch (Exception e) {
			e.printStackTrace();
			Log.info(e);
			passed = false;

		}

		if (passed == true) {
			GM.Reports("NIM-17036", "Pass");
		} else {

			GM.Reports("NIM-17036", "Fail");
		}

		leftNav.clickOverview();

	}

	//LSP
	@Test(priority = 39, enabled = true)
	public void ValidateLSPSubSection_CaseClosure() throws Exception {
		Global_Method GM = PageFactory.initElements(driver, Global_Method.class);
		LeftNavigationLink leftNavigation = PageFactory.initElements(driver, LeftNavigationLink.class);

		LifeStylePreventionSubSection request3 = PageFactory.initElements(driver, LifeStylePreventionSubSection.class);

		try {
			leftNavigation.clickMemberActionCenter();
			leftNavigation.clickActivities();
			request3.verifyLSPFromActivity("Case Closure");
			GM.Reports("NIM-17060", "Pass");

		} catch (Exception e) {
			e.printStackTrace();
			GM.Reports("NIM-17060", "Fail");

		}

		leftNavigation.clickOverview();
	}
		
		//Record contact
		@Test(priority = 40, enabled = true)
		public void ActivitySubSectionRecordContact_CaseClosure() throws Exception {
			Global_Method GM = PageFactory.initElements(driver, Global_Method.class);
			LeftNavigationLink leftNavigation = PageFactory.initElements(driver, LeftNavigationLink.class);

			try {
				
				leftNavigation.clickMemberActionCenter();
				leftNavigation.clickActivities();
				ActivityRecordContact recordcontact = PageFactory.initElements(driver,
						ActivityRecordContact.class);
				recordcontact.activityRecordContact("Case Closure");
				
				GM.Reports("NIM-17018", "Pass");
			} catch (Exception e) {
				e.printStackTrace();
				GM.Reports("NIM-17018", "Fail");
			}
			leftNavigation.clickOverview();
		}



}