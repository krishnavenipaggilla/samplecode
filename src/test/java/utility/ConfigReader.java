/**
 * 
 */
package utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * @author AF12066
 *
 */
public class ConfigReader {

	Properties pro;

	public ConfigReader() {

		try {
			File src = new File("./Configuration/config.property");
			File src1 = new File("./Configuration/Login.property");
			File src2 = new File("./Configuration/HeaderFooterValidation.property");
			File src3 = new File("./Configuration/LeftNRightLinksNIcons.property");
			File src4 = new File("./Configuration/AssessmentSummary.property");
			File src5 = new File("./Configuration/GlobalMethod.property");
			File src6 = new File("./Configuration/MemberSearch.property");
			File src7 = new File("./Configuration/LandiingPageCaseManager.property");
			File src8 = new File("./Configuration/Notes.property");
			File src9 = new File("./Configuration/MemberManagementReason.property");
			
			File src11 = new File("./Configuration/Activity.property");
			File src12 = new File("./Configuration/AuthorizedRepresentative.property");
			File src13 = new File("./Configuration/Immunization.property");
			File src14 = new File("./Configuration/TempMemberCreation.property");
			File src15 = new File("./Configuration/Medications.property");
			File src16 = new File("./Configuration/Interventions.property");
			File src17 = new File("./Configuration/CaseClosure.property");
			File src18 = new File("./Configuration/Referrals.property");
			File src19 = new File("./Configuration/RecordContact.property");
			File src20 = new File("./Configuration/Conditions.property");

			File src22 = new File("./Configuration/Admissions.property");
			File src23 = new File("./Configuration/ComplexityLevel.property");
			File src24 = new File("./Configuration/Documents.property");
			File src25 = new File("./Configuration/CarePlan.property");
			
			File src27 = new File("./Configuration/Barriers.property");
			File src28 = new File("./Configuration/EditMember.property");
			File src29 = new File("./Configuration/GeneralMedical.property");
			File src30 = new File("./Configuration/TempMemberProperty.property");
			File src31 = new File("./Configuration/LifestyleAndPrevention.property");
			
			File src32= new File ("./Configuration/MemberActionCenterIndividualProvider.property");
			File src33= new File ("./Configuration/CareTeamIndividualProvider.property");
			File src34= new File ("./Configuration/Activities.property");
			File src35= new File ("./Configuration/Clinical.property");
			File src36= new File ("./Configuration/ConditionSubsection.property");
			File src37= new File ("./Configuration/CareCoordination.property");
			File src38= new File ("./Configuration/ImmunizationSubsection.property");
			File src39 = new File("./Configuration/ChangeHistory.property");
			File src40 = new File("./Configuration/ClinicalIntervention.property");
			File src41 = new File("./Configuration/MgmtReasonSubSection.property");
			File src42 = new File("./Configuration/Questionnarie.property");
			File src43 = new File ("./Configuration/ActivitySubSectionMedications.property");
			File src44 = new File("./Configuration/RecordContact.property");
			File src45 = new File ("./Configuration/RecordContactSubActivity.property");
			File src46 = new File ("./Configuration/MemRepresentativeSubsection.property");
			File src47 = new File ("./Configuration/ProviderSubsection.property");
			File src48 = new File ("./Configuration/RecordContactSubActivity.property");

			

			FileInputStream fis = new FileInputStream(src);
			FileInputStream fis1 = new FileInputStream(src1);
			FileInputStream fis2 = new FileInputStream(src2);
			FileInputStream fis3 = new FileInputStream(src3);
			FileInputStream fis4 = new FileInputStream(src4);
			FileInputStream fis5 = new FileInputStream(src5);
			FileInputStream fis6 = new FileInputStream(src6);
			FileInputStream fis7 = new FileInputStream(src7);
			FileInputStream fis8 = new FileInputStream(src8);
			FileInputStream fis9 = new FileInputStream(src9);
			
			FileInputStream fis11 = new FileInputStream(src11);
			FileInputStream fis12 = new FileInputStream(src12);
			FileInputStream fis13 = new FileInputStream(src13);
			FileInputStream fis14 = new FileInputStream(src14);
			FileInputStream fis15 = new FileInputStream(src15);
			FileInputStream fis16 = new FileInputStream(src16);
			FileInputStream fis17 = new FileInputStream(src17);
			FileInputStream fis18 = new FileInputStream(src18);
			FileInputStream fis19 = new FileInputStream(src19);
			FileInputStream fis20 = new FileInputStream(src20);

			FileInputStream fis22 = new FileInputStream(src22);
			FileInputStream fis23 = new FileInputStream(src23);
			FileInputStream fis24 = new FileInputStream(src24);
			FileInputStream fis25 = new FileInputStream(src25);
		
			FileInputStream fis27 = new FileInputStream(src27);
			FileInputStream fis28 = new FileInputStream(src28);
			FileInputStream fis29 = new FileInputStream(src29);
			FileInputStream fis30 = new FileInputStream(src30);
			FileInputStream fis31 = new FileInputStream(src31);
			FileInputStream fis32 = new FileInputStream(src32);
			FileInputStream fis33 = new FileInputStream(src33);
			FileInputStream fis34 = new FileInputStream(src34);
			FileInputStream fis35 = new FileInputStream(src35);
			FileInputStream fis36 = new FileInputStream(src36);
			FileInputStream fis37 = new FileInputStream(src37);
			FileInputStream fis38 = new FileInputStream(src38);
			FileInputStream fis39 = new FileInputStream(src39);
			FileInputStream fis40 = new FileInputStream(src40);
			FileInputStream fis41 = new FileInputStream(src41);
			FileInputStream fis42 = new FileInputStream(src42);
			FileInputStream fis43 = new FileInputStream(src43);
			FileInputStream fis44 = new FileInputStream(src44);
			FileInputStream fis45 = new FileInputStream(src45);

			FileInputStream fis46 = new FileInputStream(src46);
			FileInputStream fis47 = new FileInputStream(src47);
			FileInputStream fis48= new FileInputStream(src48);


			pro = new Properties();

			pro.load(fis);
			pro.load(fis1);
			pro.load(fis2);
			pro.load(fis3);
			pro.load(fis4);
			pro.load(fis5);
			pro.load(fis6);
			pro.load(fis7);
			pro.load(fis8);
			pro.load(fis9);
		
			pro.load(fis11);
			pro.load(fis12);
			pro.load(fis13);
			pro.load(fis14);
			pro.load(fis15);
			pro.load(fis16);
			pro.load(fis17);
			pro.load(fis18);
			pro.load(fis19);
			pro.load(fis20);

			pro.load(fis22);
			pro.load(fis23);
			pro.load(fis24);
			pro.load(fis25);
		
			pro.load(fis27);
			pro.load(fis28);
			pro.load(fis29);
			pro.load(fis30);
			pro.load(fis31);
			pro.load(fis32);
			pro.load(fis33);
			pro.load(fis34);
			pro.load(fis35);
			pro.load(fis36);
			pro.load(fis37);
			pro.load(fis38);
			pro.load(fis39);
			pro.load(fis40);
			pro.load(fis41);
			pro.load(fis42);
			pro.load(fis43);
			pro.load(fis44);
			pro.load(fis45);

			pro.load(fis46);
			pro.load(fis47);
			pro.load(fis48);
			


			
		} catch (Exception e) {
			System.out.println("Exception is" + e.getMessage());
		}

	}

	// ---------------------------Utility class
	// file---------------------------//
	public String getURL() {
		String Link = pro.getProperty("URL");
		return Link;
	}

	public String getExecutiontype() {
		System.out.println(pro.getProperty("ExecutionType"));
		return pro.getProperty("ExecutionType");
	}

	// ---------------------------Login Page ---------------------------//

	public String getLoginUserNameWebElement() {
		return pro.getProperty("UserNamexpathProperty");
	}

	public String getLoginPasswordWebElement() {
		return pro.getProperty("PassWordxpathProperty");
	}

	public String getLoginButtonWebElement() {
		return pro.getProperty("LoginButtonxpathProperty");
	}

	// -------------------Member Search properties-------------------//

	public String getSearchButton() {
		return pro.getProperty("SearchButton");
	}

	public String getFirstName() {
		return pro.getProperty("FirstName");
	}

	public String getLastName() {
		return pro.getProperty("LastName");
	}

	public String getSearchMemberBtn() {
		return pro.getProperty("SearchMemberBtnXpath");
	}

	public String getDateofBirth() {
		return pro.getProperty("DateofBirth");
	}

	public String getMemberId() {
		return pro.getProperty("MemberId");
	}

	public String getCaseId() {
		return pro.getProperty("MemberCaseId");
	}

	public String getGender() {
		return pro.getProperty("Gender");
	}

	public String getPhoneNum() {
		return pro.getProperty("PhoneNum");
	}

	public String getState() {
		return pro.getProperty("State");
	}

	public String getZipCode() {
		return pro.getProperty("ZipCode");
	}

	public String getEligibilityStatus() {
		return pro.getProperty("EligibilityStatus");
	}

	public String getClearButton() {
		return pro.getProperty("ClearButton");
	}

	public String getBackButton() {
		return pro.getProperty("BackButton");
	}

	// -------------------Advance Member Search properties-------------------//

	public String getSearchResultFirstnamexpath() {
		return pro.getProperty("SearchResultFirstnamexpath");
	}

	public String getSearchResultCreateOpenCaseXpath() {
		return pro.getProperty("SearchResultCreateOpenCaseXpath");
	}

	public String getSelectCreateOpenCase() {
		return pro.getProperty("SelectCreateOpenCase");
	}

	// -----------------------BackToHome Page-----------------------//
	// (Under Login page property)
	public String getBackToHomexpath() {
		return pro.getProperty("BackToHome");
	}


	// -------------------Excel Path----------------------//


	public String getExcelPath() {

		return pro.getProperty("ExcelPath");
	}

	// --------------------------- Assessment summary
	// ---------------------------//
	public String getAssessmentPageColumnFilter() {
		return pro.getProperty("AssessmentPageColumnFilter");

	}

	public String getAssessmentPageLink() {
		return pro.getProperty("AssessmentPageLink");

	}

	public String getAssessmentTableXpath() {
		return pro.getProperty("AssessmentTableXpath");

	}

	public String getAssessmentStatusXpath() {
		return pro.getProperty("AssessmentStatusXpath");

	}

	public String getAssessmentDropDownTriggerXpath() {
		return pro.getProperty("AssessmentDropDownTriggerXpath");

	}

	public String getAssessmentViewAssessment() {
		return pro.getProperty("AssessmentViewAssessment");

	}

	public String getAssessmentEditAssessment() {
		return pro.getProperty("AssessmentEditAssessment");

	}

	public String getAssessmentGridHeaderColumnsXpath() {
		return pro.getProperty("AssessmentGridHeaderColumnsXpath");

	}

	public String getHomePageLink() {
		return pro.getProperty("HomePageLink");

	}

	public String getLastUpdated() {
		return pro.getProperty("LastUpdated");

	}

	public String getLastUpdatedColumnFilterButton() {
		return pro.getProperty("LastUpdatedColumnFilterButton");

	}

	public String getLastUpdatedBy() {
		return pro.getProperty("LastUpdatedBy");

	}

	public String getAssessmentNameFilter() {
		return pro.getProperty("AssessmentNameFilter");

	}

	public String getLastUpdatedDate() {
		return pro.getProperty("LastUpdatedDate");

	}

	public String getAssessmentDateList() {
		return pro.getProperty("AssessmentDateList");

	}

	public String getAssessmentNameSort() {
		return pro.getProperty("AssessmentNameSort");

	}

	public String getAssessmentStatusSort() {
		return pro.getProperty("AssessmentStatusSort");

	}

	public String getAssessmentTaskSort() {
		return pro.getProperty("AssessmentTaskSort");

	}

	public String getAssessmentDateSort() {
		return pro.getProperty("AssessmentDateSort");

	}

	public String getAssessmentUpdatedBySort() {
		return pro.getProperty("AssessmentUpdatedBySort");

	}

	public String getAssessmentNameList() {
		return pro.getProperty("AssessmentNameList");

	}

	public String getAssessmentStatusList() {
		return pro.getProperty("AssessmentStatusList");

	}

	public String getAssessmentTaskList() {
		return pro.getProperty("AssessmentTaskList");

	}

	public String getAssessmentUpdatedByList() {
		return pro.getProperty("AssessmentUpdatedByList");

	}

	public String getDocumentGridHeaderColumnsXpath() {
		return pro.getProperty("DocumentGridHeaderColumnsXpath");
	}

	// -------------------- Header&Footer Verification--------------//

	public String getSiteMap() {
		return pro.getProperty("SiteMap");

	}

	public String getTermsofUse() {
		return pro.getProperty("TermsofUse");

	}

	public String getProctedMarks() {
		return pro.getProperty("ProctedMarks");

	}

	public String getPrivacyPolicy() {
		return pro.getProperty("PrivacyPolicy");

	}

	public String getSSLCertification() {
		return pro.getProperty("SSLCertification");

	}

	public String getAnthemLogo() {
		return pro.getProperty("AnthemLogo");
	}

	public String getCMDM() {
		return pro.getProperty("CMDM");
	}

	public String getUserImage() {
		return pro.getProperty("UserImage");
	}

	public String getUserName() {
		return pro.getProperty("UserName");
	}

	public String getUserRole() {
		return pro.getProperty("UserRole");
	}

	public String getLogOut() {
		return pro.getProperty("LogOut");
	}

	public String getFloridaMarket() {
		return pro.getProperty("FloridaMarket");
	}

	// -------------------Member Search properties-------------------//

	public String getFirstNameTextBoxXpath() {
		return pro.getProperty("FirstNameTextBoxXpath");
	}

	public String getLastNameTextBoxXpath() {
		return pro.getProperty("LastNameTextBoxXpath");
	}

	public String getCaseSearchButtonXpath() {
		return pro.getProperty("CaseSearchButtonXpath");
	}

	public String getMedicaidId() {
		return pro.getProperty("MedicaidIdTextBox");
	}

	public String getMedicareId() {
		return pro.getProperty("MedicareIdTextBox");
	}

	public String getSubscriberId() {
		return pro.getProperty("SubscriberIdTextBox");
	}

	public String getDateOfBirth() {
		return pro.getProperty("DateofBirth");
	}

	public String getElementVisibility() {
		return pro.getProperty("ElementVisibility");
	}

	public String getViewCaseDropDown() {
		return pro.getProperty("ViewCaseDropDownImage");
	}

	public String getViewCaseClicked() {
		return pro.getProperty("ViewCaseClick");
	}

	////////// --------------LeftNRightLinksNIcons.property--------------------/////////////////
	//--------LeftNav Links-----//
	public String getOverviewTitle() {
		return pro.getProperty("Overview");
	}
	
	public String getOverviewLinkLN() {
		return pro.getProperty("OverviewLink");
	}
	
	public String getHealthChartLinkLN() {
		return pro.getProperty("HealthChartLink");
	}

	public String getSearchTitle() {
		return pro.getProperty("Search");
	}

	public String getAdmissionsTitle() {
		return pro.getProperty("Admissions");
	}
	public String getAdmissionsLinkLN() {
		return pro.getProperty("AdmissionsLink");
	}

	public String getConditionsTitle() {
		return pro.getProperty("Conditions");
	}
	public String getConditionsLinkLN() {
		return pro.getProperty("ConditionsLink");
	}

	public String getCareTeamTitle() {
		return pro.getProperty("CareTeam");
	}
	public String getCareTeamLinkLN() {
		return pro.getProperty("CareTeamLink");
	}

	public String getActivitiesTitle() {
		return pro.getProperty("Activities");
	}
	
	public String getActivitiesLinkLN() {
		return pro.getProperty("ActivitiesLink");
	}

	public String getImmunizationsTitle() {
		return pro.getProperty("Immunizations");
	}
	
	public String getImmunizationsLinkLN() {
		return pro.getProperty("ImmunizationsLink");
	}

	public String getChangetHistoryTitle() {

		return pro.getProperty("ChangeHistory");
	}
	
	public String getChangeHistoryLinkLN() {

		return pro.getProperty("ChangeHistoryLink");
	}

	public String getGeneralMedicalTitle() {
		return pro.getProperty("GeneralMedical");
	}
	
	public String getGeneralMedicalLinkLN() {
		return pro.getProperty("GeneralMedicalLink");
	}

	public String getManagementReasonTitle() {
		return pro.getProperty("ManagementReason");
	}
	public String getManagementReasonLinkLN() {
		return pro.getProperty("ManagementReasonLink");
	}

	public String getNotesTitle() {
		return pro.getProperty("Notes");
	}

	public String getNotesLinkLN() {
		return pro.getProperty("NotesLink");
	}
	public String getDocumentsTitle() {
		return pro.getProperty("Documents");
	}
	public String getDocumentsLinkLN() {
		return pro.getProperty("DocumentsLink");
	}

	

	public String getLifestyleAndPreventionTitle() {

		return pro.getProperty("LifestyleAndPrevention");
	}
	public String getLifestyleAndPreventionLinkLN() {

		return pro.getProperty("LifestyleAndPreventionLink");
	}

	public String getCarePlanTitle() {

		return pro.getProperty("CarePlan");
	}
	
	public String getCarePlanLinkLN() {

		return pro.getProperty("CarePlanLink");
	}

	public String getContactHistoryTitle() {

		return pro.getProperty("ContactHistory");
	}

	public String getBarriersTitle() {

		return pro.getProperty("Barriers");
	}
	public String getBarriersLinkLN() {

		return pro.getProperty("BarriersLink");
	}


	public String getMemberActionCenterLinkLN() {
		return pro.getProperty("MemberActionCenterLink");
	}

	
	public String getInterventionsTitle() {

		return pro.getProperty("Interventions");
	}
	
	public String getInterventionsLinkLN() {

		return pro.getProperty("InterventionsLink");
	}
	
	public String getMedicationsTitle() {
		return pro.getProperty("Medications");
	}
	
	public String getMedicationsLinkLN() {
		return pro.getProperty("MedicationsLink");
	}
	
	
	public String getQuestionnaireHistoryTitle() {
		return pro.getProperty("QuestionnaireHistory");
	}
	
	public String getQuestionnaireHistoryLinkLN() {
		return pro.getProperty("QuestionnaireHistoryLink");
	}
	
	public String getPrintTitle() {
		return pro.getProperty("Print");
	}
	
	public String getPrintLinkLN() {
		return pro.getProperty("PrintLink");
	}

	// -----RightActionTray----//
	public String getRecordAContactIconElement() {

		return pro.getProperty("RecordAContactIcon");
	}

	public String getRecordAContactBC() {

		return pro.getProperty("RecordAContact");
	}

	public String getAddActivityIconElement() {

		return pro.getProperty("AddActivityIcon");
	}

	public String getAddActivityBC() {

		return pro.getProperty("AddActivity");
	}

	public String getUploadFilesIconElement() {

		return pro.getProperty("UploadFilesIcon");
	}

	public String getUploadFilesBC() {

		return pro.getProperty("UploadFiles");
	}

	public String getAddNotesIconElement() {

		return pro.getProperty("AddNotesIcon");
	}

	public String getAddNotesBC() {

		return pro.getProperty("AddNotes");
	}

	public String getCaseClosureIconElement() {

		return pro.getProperty("CaseClosureIcon");
	}

	public String getCaseClosureBC() {

		return pro.getProperty("CaseClosure");
	}

	public String getRequestAuditPackageIconElement() {

		return pro.getProperty("RequestAuditPackageIcon");
	}

	public String getRequestAuditPackageBC() {

		return pro.getProperty("RequestAuditPackage");
	}

	// ----------Global Methods-------------------//
	public String getMemberBannerCaseStatusXpath() {
		return pro.getProperty("MemberBannerCaseStatusXpath");
	}

	public String getMemberBannerCaseStatusReasonXpath() {
		return pro.getProperty("MemberBannerCaseStatusReasonXpath");
	}

	public String getLoggedInUserXpath() {
		return pro.getProperty("LoggedInUserXpath");
	}

	// -----------LandiingPageCaseManager.property---------------//

	// ....................CaseManager and Supervisor
	// LandingDashboard...................//

	public String getColumnssOnGrid() {
		return pro.getProperty("ColumnsOnGrid");
	}

	public String getColumnFiltersOnGrid() {
		return pro.getProperty("TextAreasonGridForFilter");
	}

	public String getDateRangeToOnMembers() {
		return pro.getProperty("DateRangeToOnMembers");
	}

	public String getDateRangeFromOnMembers() {
		return pro.getProperty("DateRangeFromOnMembers");
	}

	public String getFilterOnMembers() {
		return pro.getProperty("FilterButtonOnMembers");
	}

	public String getNextTaskDueOnMembers() {
		return pro.getProperty("NextTaskDue");
	}

	public String getColumnAppend() {
		return pro.getProperty("AppendingCoulmn");
	}

	public String getColumnSort() {
		return pro.getProperty("ColumnSort");
	}

	public String getViewSelection() {
		return pro.getProperty("CaseSupervisorViewDropDown");
	}

	public String getMemberSearch() {
		return pro.getProperty("MemberSearchLink");
	}

	public String clearAllFiltersOnMembers() {
		return pro.getProperty("ClearAllFilters");
	}

	public String verifyNextTaskDueDates() {
		return pro.getProperty("VerifyNextTaskDue");
	}

	public String verifyCaseAssignment() {
		return pro.getProperty("CaseAssignmentLink");
	}

	public String caseLoadMyTeam() {
		return pro.getProperty("CaseLoadMyTeamDropdown");
	}

	public String caseLoadAllCM() {
		return pro.getProperty("CaseLoadALLCMDD");
	}

	public String memberViewMyTeam() {
		return pro.getProperty("MemberMyTeamDD");
	}

	public String memberViewAllCM() {
		return pro.getProperty("MemberAllCMDD");
	}

	public String myMembers() {
		return pro.getProperty("MyMembersDD");
	}

	public String caseLoadViewALLCMDefault() {
		return pro.getProperty("CaseLoadAllCM");
	}

	public String imageOnColumnToClick() {
		return pro.getProperty("ImageOnColumn");
	}

	public String columnsWithTextFilterOnGrid() {
		return pro.getProperty("ColumnswithTextFilters");

	}

	public String VerifyNoRecordsFound() {
		return pro.getProperty("NoRecordsFound");
	}

	public String VerifyNextTaskDueDropDown() {
		return pro.getProperty("NextTaskDueDropDown");
	}

	public String VerifyCheckBoxOnGrid() {
		return pro.getProperty("CheckBoxOnGrid");
	}

	public String VerifyArrowOnGrid() {
		return pro.getProperty("ArrowOnGrid");
	}

	// ---------------------Notes Property----------------//

	public String getBTN_AddaNote() {
		return pro.getProperty("BTN_AddaNote");
	}

	public String getTXT_CaseID() {
		return pro.getProperty("TXT_CaseID");
	}

	public String getBTN_Back() {
		return pro.getProperty("BTN_Back");
	}

	// --------------------------- Add notes
	// page----------------------------------//
	public String getNotesLinkXpath() {
		return pro.getProperty("NotesLinkXpath");
	}

	public String getAddNoteXpath() {
		return pro.getProperty("AddNoteXpath");
	}

	public String getNoteTypeXpath() {
		return pro.getProperty("NoteTypeXpath");
	}

	public String getSensitiveChkBoxXpath() {
		return pro.getProperty("SensitiveChkBoxXpath");
	}

	public String getNoteInfoXpath() {
		return pro.getProperty("NoteInfoXpath");
	}

	public String getSaveAndSubmitXpath() {
		return pro.getProperty("SaveAndSubmitXpath");
	}

	public String getCancelXpath() {
		return pro.getProperty("CancelXpath");
	}

	public String getActionDropDownXpath() {
		return pro.getProperty("ActionDropDownXpath");
	}

	public String getViewNotesXpath() {
		return pro.getProperty("ViewNotesXpath");
	}

	public String getModalWindowViewNotesXapth() {
		return pro.getProperty("ModalWindowViewNotesXapth");
	}

	public String getViewNoteCellXpath() {
		return pro.getProperty("ViewNoteCellXpath");
	}

	public String getModalWindowTitleXpath() {
		return pro.getProperty("ModalWindowTitleXpath");
	}

	public String getNotesTableXpath() {
		return pro.getProperty("NotesTableXpath");
	}

	public String getFromDateXpath() {
		return pro.getProperty("FromDateXpath");
	}

	public String getToDateXpath() {
		return pro.getProperty("ToDateXpath");
	}

	public String getStatusDropDownXpath() {
		return pro.getProperty("StatusDropDownXpath");
	}

	public String getGridXpath() {
		return pro.getProperty("GridXpath");
	}

	public String getFilterXpath() {
		return pro.getProperty("FilterXpath");
	}

	public String getCloseModalWindowXpath() {
		return pro.getProperty("CloseModalWindowXpath");
	}

	// --------------------------- Management Reason
	// page----------------------------------//

	public String getAddMgmtReasonLinkXpath() {
		return pro.getProperty("AddMgmtReasonLinkXpath");
	}

	public String getEditManagementReasonXpath() {
		return pro.getProperty("EditManagementReasonXpath");
	}

	public String getAddSupplementalInfoLinkXpath() {
		return pro.getProperty("AddSupplementalInfoLinkXpath");
	}

	public String getDropdownArrowForFilterTypeOnManagementReasonXpath() {
		return pro.getProperty("DropdownArrowForFilterTypeOnManagementReasonXpath");
	}

	public String getFilterButtonXpath() {
		return pro.getProperty("FilterButtonXpath");
	}

	public String getAddMgmtReasonMsgsXpath() {
		return pro.getProperty("AddMgmtReasonMsgsXpath");
	}

	public String getTextAreaLabelElementXpath() {
		return pro.getProperty("TextAreaLabelElementXpath");
	}

	public String getNoteAreaXpath() {
		return pro.getProperty("NoteAreaXpath");
	}

	public String getSupplementalGridHeaderElementXpath() {
		return pro.getProperty("SupplementalGridHeaderElementXpath");
	}

	public String getManagementReasonGridHeaderElementXpath() {
		return pro.getProperty("ManagementReasonGridHeaderElementXpath");
	}

	public String getProgramTypeWebElementsXpath() {
		return pro.getProperty("ProgramTypeWebElementsXpath");
	}

	// --------------------------- MemberSearch Property file - Method - Member Search to Left Nav---------------------------------//

	public String getMemberSearchLinkXpath() {
		return pro.getProperty("MemberSearchLinkXpath");
	}

	public String getCaseIdXpath() {
		return pro.getProperty("CaseIdXpath");
	}

	public String getSearchButtonXpath() {
		return pro.getProperty("SearchButtonXpath");
	}

	public String getInitialOutreachElementXpath() {
		return pro.getProperty("InitialOutreachElementXpath");
	}

	public String getSearchResultsXpath() {
		return pro.getProperty("SearchResultsXpath");
	}
	
	public String getClearButtonXpath() {
		return pro.getProperty("ClearButtonXpath");
	}
	public String getPaginationElement1Xpath() {
		return pro.getProperty("PaginationElement1Xpath");
	}
	public String getLastCaseIdFromGridXpath() {
		return pro.getProperty("LastCaseIdFromGridXpath");
	}

	// ----------------------------ActivityCheck---------------[Activity.propertyFile : Author: Praveen]---------------------//

	public String getChangeHistoryXpath() {
		return pro.getProperty("ChangeHistoryXpath");
	}

	public String getChangeHistoryInformationDropDown() {
		return pro.getProperty("ChangeHistoryInformationDropDown");
	}

	public String getChangeHistoryInformationDropDownValue() {
		return pro.getProperty("ChangeHistoryInformationDropDownValue");
	}

	public String getGridHeaderXpath() {
		return pro.getProperty("GridHeaderXpath");
	}

	public String getGridRecordsXpath() {
		return pro.getProperty("GridRecordsXpath");
	}
	
	 public String getEditActivity(){
         return pro.getProperty("lnkEditActivity");
      }
     
     public String getchkSubActivitycheckbox(){
         return pro.getProperty("chkSubActivitycheckbox");
      }
     
     
     public String getbtnAddSubActivity(){
         return pro.getProperty("btnAddSubActivity");
      }
     
     public String getlblSubActivityHeader(){
         return pro.getProperty("lblSubActivityHeader");
      }
     public String getbtnAdd(){
         return pro.getProperty("btnAdd");
      }
      
     public String getmsgLoading(){
         return pro.getProperty("msgLoading");
      }
     
     public String getbtnClose(){
         return pro.getProperty("btnClose");
      }
     
     
     public String getlblSubactivityheaders(){
         return pro.getProperty("lblSubactivityheaders");
      }

     public String getlblCaseinformationlabels(){
         return pro.getProperty("lblCaseinformationlabels");
      }
     public String getlblCaseinformationlabelvalues(){
         return pro.getProperty("lblCaseinformationlabelvalues");
      }
     
     public String gethdrcareplan(){
         return pro.getProperty("hdrcareplan");
      }
     
     
     public String getlblQuestionairelabels(){
         return pro.getProperty("lblQuestionairelabels");
      }
     public String getlblNorecordsQuestionaire(){
         return pro.getProperty("lblNorecordsQuestionaire");
      }
     
     public String getlblQuestionairelabelvalues(){
         return pro.getProperty("lblQuestionairelabelvalues");
      }
     public String getlblQuestionairegridrow(){
         return pro.getProperty("lblQuestionairegridrow");
      }
     public String getlblgoalsgridrow(){
         return pro.getProperty("lblgoalsgridrow");
      }
     public String getlblgoalslabelvalues(){
         return pro.getProperty("lblgoalslabelvalues");
      }
     public String getlblNorecords(){
         return pro.getProperty("lblNorecords");
      }
     
     public String getlblMECOsubactivitylabels(){
         return pro.getProperty("lblMECOsubactivitylabels");
      }
     public String getlblMECOsubactivitygridrow(){
         return pro.getProperty("lblMECOsubactivitygridrow");
      }
     public String getlblMECOsubactivitylabelvalues(){
         return pro.getProperty("lblMECOsubactivitylabelvalues");
      }
     
     public String getbtnAddQuestionnaires(){
         return pro.getProperty("btnAddQuestionnaires");
      }
     
     public String getlnkEditQuestionnaires(){
         return pro.getProperty("lnkEditQuestionnaires");
      }
     public String getbtnAddMedication(){
         return pro.getProperty("btnAddMedication");
      }
     public String getlnkEditMedication(){
         return pro.getProperty("lnkEditMedication");
      }
     public String getbtnAddCondition(){
         return pro.getProperty("btnAddCondition");
      }
     public String getlnkEditCondition(){
         return pro.getProperty("lnkEditCondition");
      }
     public String getbtnAddRepresentative(){
         return pro.getProperty("btnAddRepresentative");
      }
     public String getlnkEditRepresentative(){
         return pro.getProperty("lnkEditRepresentative");
      }
     public String getbtnSaveEndActivity(){
         return pro.getProperty("btnSaveEndActivity");
      }
     public String getbtnSaveForLater(){
         return pro.getProperty("btnSaveForLater");
      }
     public String getbtnBack(){
         return pro.getProperty("btnBack");
      }
     public String getlblgoalslabels(){
         return pro.getProperty("lblgoalslabels");
      }
     
     public String getlblInterventionsandbarrierslabels(){
         return pro.getProperty("lblInterventionsandbarrierslabels");
      }
     public String getlblNorecordsgoals(){
         return pro.getProperty("lblNorecordsgoals");
      }
     public String getlblNorecordsInterventionsandBarriers(){
         return pro.getProperty("lblNorecordsInterventionsandBarriers");
      }
     
     public String getlblInterventionsandbarriersgridrow(){
         return pro.getProperty("lblInterventionsandbarriersgridrow");
      }
     public String getlblInterventionsandbarrierslabelvalues(){
         return pro.getProperty("lblInterventionsandbarrierslabelvalues");
      }

	// ------------Authorized Representative------------//

	public String getInitialOutreachEyeElementXpath() {
		return pro.getProperty("initialOutreachEyeElementXpath");
	}

	public String getInitialOutreachBreadCrumbElementXpath() {
		return pro.getProperty("initialOutreachBreadCrumbElementXpath");
	}

	public String getaAuthRepAccordianElementXpath() {
		return pro.getProperty("authRepAccordianElementXpath");
	}

	public String getAddAuthRepGridHeaderElementXpath() {
		return pro.getProperty("addAuthRepGridHeaderElementXpath");
	}

	public String getAddAuthRepLinkElementXpath() {
		return pro.getProperty("addAuthRepLinkElementXpath");
	}

	public String getModalHeaderElementXpath() {
		return pro.getProperty("modalHeaderElementXpath");
	}

	public String getFirstNameXpath() {
		return pro.getProperty("firstNameXpath");
	}

	public String getLastNameXpath() {
		return pro.getProperty("lastNameXpath");
	}

	public String getPhoneXpath() {
		return pro.getProperty("phoneXpath");
	}

	public String getRelationshipToMemberXpath() {
		return pro.getProperty("relationshipToMemberXpath");
	}

	public String getVerifiedDateXpath() {
		return pro.getProperty("verifiedDateXpath");
	}

	public String getSourceXpath() {
		return pro.getProperty("sourceXpath");
	}

	public String getPhoneTypeXpath() {
		return pro.getProperty("phoneTypeXpath");
	}

	public String getNonVerifiedRecordsInGridElementXpath() {
		return pro.getProperty("nonVerifiedRecordsInGridElementXpath");
	}

	public String getGridHeaderCheckBoxElementXpath() {
		return pro.getProperty("gridHeaderCheckBoxElementXpath");
	}

	public String getSaveProgressBtnElementXpath() {
		return pro.getProperty("saveProgressBtnElementXpath");
	}

	public String getPaginationLastPageElementXpath() {
		return pro.getProperty("PaginationLastPageElementXpath");
	}

	public String getNameXpathFromOverview() {
		return pro.getProperty("NameXpathFromOverview");
	}

	public String getRelationshipToMemberXpathFromOverview() {
		return pro.getProperty("RelationshipToMemberXpathFromOverview");
	}

	public String getphoneXpathFromOverview() {
		return pro.getProperty("phoneXpathFromOverview");
	}

	public String getverifiedDateXpathFromOverview() {
		return pro.getProperty("verifiedDateXpathFromOverview");
	}

	public String getExpandCollapseLastRecordInGridElementXpath() {
		return pro.getProperty("ExpandCollapseLastRecordInGridElementXpath");
	}

	public String getSourceXpathFromOverview() {
		return pro.getProperty("SourceXpathFromOverview");
	}

	public String getPhoneTypeXpathFromOverview() {
		return pro.getProperty("PhoneTypeXpathFromOverview");
	}

	public String getGridRowCountElementXpath() {
		return pro.getProperty("GridRowCountElementXpath");
	}

	public String getNonVerifiedRecordsInGridElement() {
		return pro.getProperty("NonVerifiedRecordsInGridElementXpath");
	}

	public String getNonVerifiedRecordsInGridElementXpathClick() {
		return pro.getProperty("NonVerifiedRecordsInGridElementXpathClick");
	}

	public String getEditIconForAuthRepOnOverViewElementXpath() {
		return pro.getProperty("EditIconForAuthRepOnOverViewElementXpath");
	}

	public String getEditModalHeaderElementXpath() {
		return pro.getProperty("EditModalHeaderElementXpath");
	}

	// ------Immunization------

	public String getImmunizationLeftNav() {
		return pro.getProperty("ImmunizationLeftNav");
	}

	public String getImmunizationHeader() {
		return pro.getProperty("ImmunizationHeader");
	}

	public String getAddImmunizationbutton() {
		return pro.getProperty("AddImmunizationbutton");
	}

	public String getUpdateImmunization() {
		return pro.getProperty("UpdateImmunization");
	}

	public String getImmunizationnamedropId() {
		return pro.getProperty("ImmunizationnamedropId");
	}

	public String getRefusedId() {
		return pro.getProperty("RefusedId");
	}

	public String getReasonforRefusalID() {
		return pro.getProperty("ReasonforRefusalID");
	}

	public String getFacilityNameID() {
		return pro.getProperty("FacilityNameID");
	}

	public String getDateAdministered() {
		return pro.getProperty("DateAdministered");
	}

	// -------------------- Temp Member Creation-----------------------//
	public String getTempMemberbutton() {
		return pro.getProperty("TempMemberbutton");

	}

	public String getTempMemberYesbutton() {
		return pro.getProperty("TempMemberYesbutton");

	}

	public String getTempMemberNobutton() {
		return pro.getProperty("TempMemberNobutton");

	}

	public String getTempMemberCreatebutton() {
		return pro.getProperty("TempMemberCreatebutton");

	}

	public String getTempMemberCancelbutton() {
		return pro.getProperty("TempMemberCancelbutton");

	}

	public String getTempOverviewPage() {
		return pro.getProperty("TempOverviewPage");

	}

	public String getTempCaseID() {
		return pro.getProperty("TempCaseID");

	}

	public String getTempCaseStatus() {
		return pro.getProperty("TempCaseStatus");

	}

	public String getTempSearchPage() {
		return pro.getProperty("TempSearchPage");

	}

	

	// -------------------Medications------------------
	public String getGridHeader() {
		return pro.getProperty("gridHeader");
	}

	public String VerifyCheckBox() {
		return pro.getProperty("CheckBox");
	}

	public String VerifyReviewButton() {
		return pro.getProperty("ReviewButton");
	}

	public String AllMed() {
		return pro.getProperty("AllMedications");
	}

	public String AllMedHeader() {
		return pro.getProperty("AllMedHeader");
	}

	public String ClickMed() {
		return pro.getProperty("ClickMed");
	}

	public String AddMed() {
		return pro.getProperty("AddMed");
	}

	public String MedSearch() {
		return pro.getProperty("MedSearchTerm");
	}

	public String DrugName() {
		return pro.getProperty("drugName");
	}

	public String SearchMed() {
		return pro.getProperty("SearchButton");
	}

	public String getSearchResults() {
		return pro.getProperty("SearchResults");
	}

	public String OpenDrug() {
		return pro.getProperty("OpenDrug");
	}

	public String SelectDrug() {
		return pro.getProperty("Selectdrug");
	}

	public String ClickAddMed() {
		return pro.getProperty("AddMedSubmit");
	}

	public String VerifyMed() {
		return pro.getProperty("VerifyMed");
	}

	public String EditMed() {
		return pro.getProperty("EditMed");
	}

	public String ClickUpdateMed() {
		return pro.getProperty("UpdateMedSubmit");
	}

	public String VerifyUpdateButton() {
		return pro.getProperty("VerifyUpdate");
	}

	public String VerifyHistoryButton() {
		return pro.getProperty("VerifyHistory");
	}

	public String ClickMedView() {
		return pro.getProperty("MedView");
	}

	public String getViewHistory() {
		return pro.getProperty("ViewHistory");
	}

	public String getMedicationsHistoryGridHeader() {
		return pro.getProperty("MedicationsHistoryGridHeader");
	}

	public String getHistorySecRow() {
		return pro.getProperty("HistorySecRow");
	}

	public String getClickFirstCheckBox() {
		return pro.getProperty("ClickFirstCheckBox");
	}

	public String getReviewedDate() {
		return pro.getProperty("ReviewedDate");
	}

	// --------------Case Closure--------------------//
	public String getOverviewLeftNav() {
		return pro.getProperty("OverviewLeftNav");
	}

	public String getMgmtLeftNav() {
		return pro.getProperty("MgmtLeftNav");
	}

	public String getAddMgmtReason() {
		return pro.getProperty("AddMgmtReason");
	}

	public String getCaseClosurebutton() {
		return pro.getProperty("CaseClosurebutton");
	}

	public String getCaseClosureModalHeader() {
		return pro.getProperty("CaseClosureModalHeader");
	}

	public String getQuestiontext() {
		return pro.getProperty("Questiontext");
	}

	public String getMemberActionLeftNav() {
		return pro.getProperty("MemberActionLeftNav");
	}
	
	public String getActivities()
    {
         return pro.getProperty("Activities");
    }

	public String getMgmtReasonstatus() {
		return pro.getProperty("MgmtReasonstatus");
	}

	public String getActivityStatus() {
		return pro.getProperty("ActivityStatus");
	}

	public String getBannerCaseStatus() {
		return pro.getProperty("BannerCaseStatus");
	}

	public String getBannerCaseID() {
		return pro.getProperty("BannerCaseID");
	}

	// -----------Referrals--------------//
	public String getCarePlanleftnav() {
		return pro.getProperty("CarePlanleftnav");
	}

	public String getAddGoal() {
		return pro.getProperty("AddGoal");
	}

	public String getCarePlanLeftNav() {
		return pro.getProperty("CarePlanLeftNav");
	}

	public String getSecondaryRow() {
		return pro.getProperty("SecondaryRow");
	}

	public String getSecondaryRowEdit() {
		return pro.getProperty("SecondaryRowEdit");
	}

	public String getEditReferralHeader() {
		return pro.getProperty("EditReferralHeader");
	}

	public String getStatusValue() {
		return pro.getProperty("StatusValue");
	}

	public String getStatusReasonValue() {
		return pro.getProperty("StatusReasonValue");
	}

	public String getReferralHistoryGridHeader() {
		return pro.getProperty("ReferralHistoryGridHeader");
	}

	public String getHistorycolumn() {
		return pro.getProperty("Historycolumn");
	}

	public String getHistoryOwner() {
		return pro.getProperty("HistoryOwner");
	}

	public String getHistoryActionTaken() {
		return pro.getProperty("HistoryActionTaken");
	}

	public String getHistoryProposedvalue() {
		return pro.getProperty("HistoryProposedvalue");
	}

	public String getHistoryDateValue() {
		return pro.getProperty("HistoryDateValue");
	}

	public String getSecDescription() {
		return pro.getProperty("SecDescription");
	}

	public String getSecGridAssignedTo() {
		return pro.getProperty("SecGridAssignedTo");
	}

	public String getRefTypenonedit() {
		return pro.getProperty("RefTypenonedit");
	}

	public String getRefreasonNonedit() {
		return pro.getProperty("RefreasonNonedit");
	}

	public String getReferralsLeftNav() {
		return pro.getProperty("ReferralsLeftNav");
	}

	public String getAddIntervention() {
		return pro.getProperty("AddIntervention");
	}

	public String getInterventiondropdown() {
		return pro.getProperty("Interventiondropdown");
	}

	public String getReferralasIntervention() {
		return pro.getProperty("ReferralasIntervention");
	}

	public String getrow() {
		return pro.getProperty("row");
	}

	public String getAllReferralvalues() {
		return pro.getProperty("AllReferralvalues");
	}

	public String getReferralType() {
		return pro.getProperty("ReferralType");
	}

	public String getBehavioralHealth() {
		return pro.getProperty("BehavioralHealth");
	}

	public String getGoalCheckbox() {
		return pro.getProperty("GoalCheckbox");
	}

	public String getGoalValue() {
		return pro.getProperty("GoalValue");
	}

	public String getSubmit() {
		return pro.getProperty("Submit");
	}

	public String getReferralTypeValue() {
		return pro.getProperty("ReferralTypeValue");
	}

	public String getReferralReasonValue() {
		return pro.getProperty("ReferralReasonValue");
	}

	public String getActionTakenValue() {
		return pro.getProperty("ActionTakenValue");
	}

	public String getcolumnNames() {
		return pro.getProperty("columnNames");
	}

	public String getActionProposedValue() {
		return pro.getProperty("ActionProposedValue");
	}

	public String getDueDateValue() {
		return pro.getProperty("DueDateValue");
	}

	public String getNoteValue() {
		return pro.getProperty("NoteValue");
	}

	public String getPermissionValue() {
		return pro.getProperty("PermissionValue");
	}

	public String getAssignedTovalue() {
		return pro.getProperty("AssignedTovalue");
	}

	public String getDescription() {
		return pro.getProperty("Description");
	}

	public String getGridAssignedTo() {
		return pro.getProperty("GridAssignedTo");
	}

//	public String getEditReferral() {
//		return pro.getProperty("EditReferral");
//	}

	public String getNoteGridHeader() {
		return pro.getProperty("NoteGridHeader");
	}

	public String getAddNote() {
		return pro.getProperty("AddNote");
	}

	public String getAddnnoteHeader() {
		return pro.getProperty("AddnnoteHeader");
	}

	public String getBack() {
		return pro.getProperty("Back");
	}

	// Referral Activity

	public String getReferralActivity() {
		return pro.getProperty("ReferralActivity");
	}

	public String getRefDueDate() {
		return pro.getProperty("RefDueDate");
	}

	public String getRefAssignedTo() {
		return pro.getProperty("RefAssignedTo");
	}

	public String getRefStatus() {
		return pro.getProperty("RefStatus");
	}

	public String getEditRefActivity() {
		return pro.getProperty("EditRefActivity");
	}

	public String getActivityOpen() {
		return pro.getProperty("ActivityOpen");
	}

	public String getSaveforLaterButton() {
		return pro.getProperty("SaveforLaterButton");
	}

	public String getSaveActivityButton() {
		return pro.getProperty("SaveActivityButton");
	}

	public String getActivityHeader() {
		return pro.getProperty("ActivityHeader");
	}

	public String getActivityDashBaord() {
		return pro.getProperty("ActivityDashBaord");
	}

	public String getFFwdButton() {
		return pro.getProperty("FFwdButton");
	}

	public String getActivityListHeader() {
		return pro.getProperty("ActivityListHeader");
	}

	public String getRefActivityStatus() {
		return pro.getProperty("RefActivityStatus");
	}

	public String getRefActivGrid() {
		return pro.getProperty("RefActivGrid");
	}

	public String getCaseIDRef() {
		return pro.getProperty("CaseID");
	}

	// ------------------- Record a property---------------------//

	public String getRecordCotactPath() {
		return pro.getProperty("RecordCotactPath");

	}

	public String getSubmitandAddNewPath() {
		return pro.getProperty("SubmitandAddNewPath");

	}

	public String getContatDetailsPath() {
		return pro.getProperty("ContatDetailsPath");

	}

	public String getSubmitContactPath() {
		return pro.getProperty("SubmitContactPath");

	}

	public String getRecordContactDrop() {
		return pro.getProperty("RecordContactDrop");

	}

	public String getCaptureProgramName() {
		return pro.getProperty("CaptureProgramName");

	}

	// ------------------- Conditions property---------------------//

	public String getConditionsGridHeaderXpath() {
		return pro.getProperty("ConditionsGridHeaderXpath");

	}

	public String getAddConditionLinkXpath() {
		return pro.getProperty("AddConditionLinkXpath");

	}

	public String getAddConditionPageHeaderXpath() {
		return pro.getProperty("AddConditionPageHeaderXpath");

	}

	public String getEditConditionPageHeaderXpath() {
		return pro.getProperty("EditConditionPageHeaderXpath");

	}

	public String getMainFocusXpath() {
		return pro.getProperty("MainFocusXpath");

	}

	public String getConditionXpath() {
		return pro.getProperty("ConditionXpath");

	}

	public String getDiagnosisOnsetDateXpath() {
		return pro.getProperty("DiagnosisOnsetDateXpath");

	}

	public String getResolvedDateXpath() {
		return pro.getProperty("ResolvedDateXpath");

	}

	public String getClinicalStatusXpath() {
		return pro.getProperty("ClinicalStatusXpath");

	}

	public String getVerificationStatusXpath() {
		return pro.getProperty("VerificationStatusXpath");

	}

	public String getRiskXpath() {
		return pro.getProperty("RiskXpath");

	}

	public String getSourceXpathForCondition() {
		return pro.getProperty("SourceXpathForCondition");

	}

	public String getEditIconForFirstConditionXpath() {
		return pro.getProperty("EditIconForFirstConditionXpath");

	}

	public String getMainFocusYesElementsXpath() {
		return pro.getProperty("MainFocusYesElementsXpath");

	}

	
	// --------------ComplexityLevel----------------------------

	public String EditOverview2() {
		return pro.getProperty("OverviewEdit2");
	}

	public String ModelWindowHeader() {
		return pro.getProperty("ModelWindowHeader");
	}

	public String ComplexityDropdownValues() {
		return pro.getProperty("ComplexityDropdownValues");
	}

	public String ReasonForChangeDropdownValues() {
		return pro.getProperty("ReasonForChangeValues");
	}

	public String ComplexityLevelText() {
		return pro.getProperty("ComplexityLevelText");
	}

	public String ComplexityLevelValue() {
		return pro.getProperty("ComplexityLevelValue");
	}

	public String ChangeHistorydropdown() {
		return pro.getProperty("ChangeHistorydropdown");
	}

	public String SelectComplexityHistory() {
		return pro.getProperty("SelectComplexityHistory");
	}

	public String ComplexityLevelHistoryGrid() {
		return pro.getProperty("ComplexityLevelHistoryGrid");
	}

	public String Text_ComplexityLevel() {
		return pro.getProperty("Text_ComplexityLevel");
	}

	public String Text_ReasonForChange() {
		return pro.getProperty("Text_ReasonForChange");
	}

	public String Submit_ComplexityLevel() {
		return pro.getProperty("Submit_ComplexityLevel");
	}

	public String getProgramName() {
		return pro.getProperty("ProgramName");
	}

	public String getlblPerformedBy() {
		return pro.getProperty("lblPerformedBy");
	}

	public String getSelectedValue() {
		return pro.getProperty("Wait_SelectValue");
	}

	public String getSelectDropdown() {
		return pro.getProperty("SelectDropdown");
	}

	// -----------------Admissions-----------------

	public String AdmissionsHeader() {
		return pro.getProperty("GridHeader");
	}

	public String AddAdmissions() {
		return pro.getProperty("AddAdmissions");
	}

	public String GridHeaderVerify() {
		return pro.getProperty("GridHeaderVerify");
	}

	public String getTypeDropdown() {
		return pro.getProperty("TypeDropdown");
	}

	public String getNotificationDate() {
		return pro.getProperty("VerifyNotificationDate");
	}

	public String getProviderName() {
		return pro.getProperty("ProviderName");
	}

	public String ClickOpenImage() {
		return pro.getProperty("OpenImage");
	}

	public String ClickEditAdmission() {
		return pro.getProperty("EditAdmission");
	}

	public String EditGridHeader() {
		return pro.getProperty("EditGridHeader");
	}

	public String getAdmissionView() {
		return pro.getProperty("AdmissionView");
	}

	public String getAdmissionAdmitDate() {
		return pro.getProperty("AdmissionAdmitDate");
	}

	public String getAdmissionActualDischargeDate() {
		return pro.getProperty("AdmissionActualDischargeDate");
	}

	public String getDaysInHospital() {
		return pro.getProperty("DaysInHospital");
	}

	public String getEdit_ActualDischargeDate() {
		return pro.getProperty("Edit_ActualDischargeDate");
	}

	//-----Admissions SUBACTIVITY : admissions.property file-----//
	
	public String getAdmissionViewFromActivityXpath() {
		return pro.getProperty("AdmissionViewFromActivity");
	}
	
	public String getEditAdmissionFromActivityXpath() {
		return pro.getProperty("EditAdmissionFromActivity");
	}
	
	public String getAddAdmissionsFromActivityXpath() {
		return pro.getProperty("AddAdmissionsFromActivity");
	}
	
	
	public String getAdmissionAdmitDateFromActivityXpath() {
		return pro.getProperty("AdmissionAdmitDateFromActivity");
	}
	
	public String getAdmissionActualDischargeDateXpath() {
		return pro.getProperty("AdmissionActualDischargeDateFromActivity");
	}
	
	public String getDaysInHospitalFromActivityXpath() {
		return pro.getProperty("DaysInHospitalFromActivity");
	}
	
	public String getAdmissionAccordianFromActivityXpath() {
		return pro.getProperty("AdmissionAccordianFromActivity");
	}
	
	public String getAdmissionFromSAWindowXpath() {
		return pro.getProperty("AdmissionFromSAWindow");
	}
	
	// --------------------Documents page-----------------------//

	public String getViewCaseXpath() {
		return pro.getProperty("ViewCaseXpath");
	}

	public String getDocumentsXpath() {
		return pro.getProperty("DocumentsXpath");
	}

	public String getUploadFilesIconXpath() {
		return pro.getProperty("UploadFilesIconXpath");
	}

	public String getDocumentName_GridXpath() {
		return pro.getProperty("DocumentName_GridXpath");
	}

	public String getUploadedDocumentsTitleXpath() {
		return pro.getProperty("UploadedDocumentsTitleXpath");
	}

	public String getViewDocumentClickXpath() {
		return pro.getProperty("ViewDocumentClickXpath");
	}

	public String getViewDocumentTextXpath() {
		return pro.getProperty("ViewDocumentTextXpath");
	}

	public String getChooseFileXpath() {
		return pro.getProperty("ChooseFileXpath");
	}

	public String getDescription_GridXpath() {
		return pro.getProperty("Description_GridXpath");
	}

	public String getUploadedDate_GridXpath() {
		return pro.getProperty("UploadedDate_GridXpath");
	}

	public String getAttachedTo_GridXpath() {
		return pro.getProperty("AttachedTo_GridXpath");
	}

	public String getAddedBy_GridXpath() {
		return pro.getProperty("AddedBy_GridXpath");
	}

	public String getDocumentID_GridXpath() {
		return pro.getProperty("DocumentID_GridXpath");
	}

	public String getDocumentNameFilterXpath() {
		return pro.getProperty("DocumentID_GridXpath");
	}

	public String getUploadedDateFilterXpath() {
		return pro.getProperty("DocumentID_GridXpath");
	}

	public String getAddButtonXpath() {
		return pro.getProperty("AddButtonXpath");
	}

	public String getCancelButtonXpath() {
		return pro.getProperty("CancelButtonXpath");
	}

	public String getRecordsDisplayed() {
		return pro.getProperty("RecordsDisplayed");
	}

	public String getFileDescriptionXpath() {
		return pro.getProperty("FileDescriptionXpath");
	}

	public String getFileUrl() {
		return pro.getProperty("FileUrl");
	}

	public String getFileSample() {
		return pro.getProperty("FileSample");
	}


	// --------------Barriers---------------------//

	public String getMemberSerchClickXpath() {
		return pro.getProperty("MemberSerchClickXpath");
	}

	public String getCarePlanLink() {
		return pro.getProperty("CarePlanLink");
	}

	public String getCarePlanText() {
		return pro.getProperty("CarePlanText");
	}

	public String getGoalDropdownForAddGoal() {
		return pro.getProperty("GoalDropdownForAddGoal");
	}

	public String getFirstOptionInGoalDropDown() {
		return pro.getProperty("FirstOptionInGoalDropDown");
	}

	public String getPriorityDropdownForAddGoal() {
		return pro.getProperty("PriorityDropdownForAddGoal");
	}

	public String getFirstOptionInPriorityDropdown() {
		return pro.getProperty("FirstOptionInPriorityDropdown");
	}

	public String getdAddGoalStatusDropdown() {
		return pro.getProperty("AddGoalStatusDropdown");
	}

	public String getFirstOptionInStatusDropdown() {
		return pro.getProperty("FirstOptionInStatusDropdown");
	}

	public String getAddGoalStatusReasonDropdown() {
		return pro.getProperty("AddGoalStatusReasonDropdown");
	}

	public String getFirstOptionInStatusReasonDropdown() {
		return pro.getProperty("FirstOptionInStatusReasonDropdown");
	}

	public String getBarriersLinkXpath() {
		return pro.getProperty("BarriersLinkXpath");
	}

	public String getBarriersTitleXpath() {
		return pro.getProperty("BarriersTitleXpath");
	}

	public String getDateDropdown() {
		return pro.getProperty("DateDropdown");
	}

	public String getAddBarrierbuttonXpath() {
		return pro.getProperty("AddBarrierbuttonXpath");
	}

	public String getAddBarrierText() {
		return pro.getProperty("AddBarrierText");
	}

	public String getBarrierDropdown() {
		return pro.getProperty("BarrierDropdown");
	}

	public String getBarrierOption() {
		return pro.getProperty("BarrierOption");
	}

	public String getChooseGoal() {
		return pro.getProperty("ChooseGoal");
	}

	public String getBarriersGridHeader() {
		return pro.getProperty("BarriersGridHeader");
	}

	public String getBarriersGrid_DescriptionXpath() {
		return pro.getProperty("BarriersGrid_DescriptionXpath");
	}

	public String getBarriersGrid_LinkedGoalXpath() {
		return pro.getProperty("BarriersGrid_LinkedGoalXpath");
	}

	public String getBarriersGrid_IdentifiedDateXpath() {
		return pro.getProperty("BarriersGrid_IdentifiedDateXpath");
	}

	public String getBarriersGrid_ResolvedDateXpath() {
		return pro.getProperty("BarriersGrid_ResolvedDateXpath");
	}

	public String getBarriersGrid_StatusReasonXpath() {
		return pro.getProperty("BarriersGrid_StatusReasonXpath");
	}

	public String getBarriersGrid_StatusXpath() {
		return pro.getProperty("BarriersGrid_StatusXpath");
	}

	public String getAddBarrier_SubmitXpath() {
		return pro.getProperty("AddBarrier_SubmitXpath");

	}

	public String getAddBarrier_BackbuttonXpath() {
		return pro.getProperty("AddBarrier_BackbuttonXpath");
	}

	public String getEditBarrierIcon() {
		return pro.getProperty("EditBarrierIcon");
	}

	public String getEditBarrierText() {
		return pro.getProperty("EditBarrierText");
	}

	public String getEditBarrier_AssociatedGoal() {
		return pro.getProperty("EditBarrier_AssociatedGoal");
	}

	public String getEditBarrier_Description() {
		return pro.getProperty("EditBarrier_Description");
	}

	public String getEditBarrier_DescriptionField() {
		return pro.getProperty("EditBarrier_DescriptionField");
	}

	public String getEditBarrier_IdentifiedDate() {
		return pro.getProperty("EditBarrier_IdentifiedDate");
	}

	public String getEditBarrier_IdentifiedDateField() {
		return pro.getProperty("EditBarrier_IdentifiedDate");
	}

	public String getEditBarrier_Statusdropdown() {
		return pro.getProperty("EditBarrier_Statusdropdown");
	}

	public String getEditBarrier_StatusReasonDropdown() {
		return pro.getProperty("EditBarrier_StatusReasonDropdown");
	}

	public String getEditBarrier_ResolvedDateField() {
		return pro.getProperty("EditBarrier_ResolvedDateField");
	}

	public String getEditBarrier_SubmitXpath() {
		return pro.getProperty("EditBarrier_SubmitXpath");
	}

	public String getEditBarrier_BackbuttonXpath() {
		return pro.getProperty("EditBarrier_BackbuttonXpath");
	}

	public String getAddGoalXpath() {
		return pro.getProperty("AddGoalXpath");
	}

	public String getAddGoalTextXpath() {
		return pro.getProperty("AddGoalTextXpath");
	}

	public String getGoalsCheckboxXpath() {
		return pro.getProperty("GoalsCheckboxXpath");
	}

	public String getNotesText() {
		return pro.getProperty("NotesText");
	}

	public String getAddNotesHeader() {
		return pro.getProperty("AddNotesHeader");
	}

	public String getNotesDescriptiont() {
		return pro.getProperty("NotesDescription");
	}

	public String getAddNotesSubmitButton() {
		return pro.getProperty("AddNotesSubmitButton");
	}

	public String getAddNotesBackButton() {
		return pro.getProperty("AddNotesSubmitButton");
	}

	// ----------Edit Member--------------------//

	public String getOverview() {
		return pro.getProperty("Overview");
	}

	public String geteditMemberlink() {
		return pro.getProperty("editMemberlink");
	}

	public String getModalHeaderMember() {
		return pro.getProperty("ModalHeaderMember");
	}

	public String getMemberName() {
		return pro.getProperty("MemberName");
	}

	public String getGenderEditMember() {
		return pro.getProperty("GenderEditMember");
	}

	public String getDOBEditMember() {
		return pro.getProperty("DOBEditMember");
	}

	public String getNickName() {
		return pro.getProperty("NickName");
	}

	public String getEthnicOrigin() {
		return pro.getProperty("EthnicOrigin");
	}

	public String getmemberFN() {
		return pro.getProperty("memberFN");
	}

	public String getmemberLN() {
		return pro.getProperty("memberLN");
	}

	public String getmemberGender() {
		return pro.getProperty("memberGender");
	}

	public String getmemberDOB() {
		return pro.getProperty("memberDOB");
	}

	public String getAddress() {
		return pro.getProperty("Address");
	}

	public String getPhone() {
		return pro.getProperty("Phone");
	}

	public String getTimezone() {
		return pro.getProperty("Timezone");
	}

	public String getDay() {
		return pro.getProperty("Day");
	}

	public String getTime() {
		return pro.getProperty("Time");
	}

	public String getCommmethod() {
		return pro.getProperty("Commmethod");
	}

	public String getLanguage() {
		return pro.getProperty("Language");
	}

	public String getEmail() {
		return pro.getProperty("Email");
	}

	public String geteditConatctlink() {
		return pro.getProperty("editConatctlink");
	}

	public String getModalHeadercontact() {
		return pro.getProperty("ModalHeadercontact");
	}

	public String getChangeHistoryl() {
		return pro.getProperty("ChangeHistory");
	}

	public String getHistory() {
		return pro.getProperty("History");
	}

	public String getMemberUpdateHistory() {
		return pro.getProperty("MemberUpdateHistory");
	}

	public String getHistoryDOB() {
		return pro.getProperty("HistoryDOB");
	}

	public String getHistoryFN() {
		return pro.getProperty("HistoryFN");
	}

	public String getHistoryLN() {
		return pro.getProperty("HistoryLN");
	}

	public String getHistoryNN() {
		return pro.getProperty("HistoryNN");
	}

	public String getHistoryOrigin() {
		return pro.getProperty("HistoryOrigin");
	}

	public String getUpdatedBy() {
		return pro.getProperty("UpdatedBy");
	}

	public String getRoleValue() {
		return pro.getProperty("RoleValue");
	}

	// ----------General Medical--------------------//

	public String getCurrentWeightInGmEnteredFromUIXpath() {
		return pro.getProperty("CurrentWeightInGmEnteredFromUIXpath");
	}

	public String getbirthWeightInGmEnteredFromUIXpath() {
		return pro.getProperty("birthWeightInGmEnteredFromUIXpath");
	}

	public String getLbFromUIForBirthWeightElementXpath() {
		return pro.getProperty("LbFromUIForBirthWeightElementXpath");
	}

	public String getOZFromUIForBirthWeightElementXpath() {
		return pro.getProperty("OZFromUIForBirthWeightElementXpath");
	}

	public String getLbFromUIForCurrentWeightElementXpath() {
		return pro.getProperty("LbFromUIForCurrentWeightElementXpath");
	}

	public String getOZFromUIForCurrentWeightElementXpath() {
		return pro.getProperty("OZFromUIForCurrentWeightElementXpath");
	}

	public String getWeeksOfGestationalAgeOfBirthElementXpath() {
		return pro.getProperty("WeeksOfGestationalAgeOfBirthElementXpath");
	}

	public String getDaysOfGestationalAgeOfBirthElementXpath() {
		return pro.getProperty("DaysOfGestationalAgeOfBirthElementXpath");
	}

	public String getDobFromCaseBannerElementXpath() {
		return pro.getProperty("DobFromCaseBannerElementXpath");
	}

	public String getWeeksForCurrentGestAgeFromUIElementXpath() {
		return pro.getProperty("WeeksForCurrentGestAgeFromUIElementXpath");
	}

	public String getDaysForCurrentGestAgeFromUIElementXpath() {
		return pro.getProperty("DaysForCurrentGestAgeFromUIElementXpath");
	}

	public String getWarningMessageElementForChildGrowthTrackingXpath() {
		return pro.getProperty("WarningMessageElementForChildGrowthTrackingXpath");
	}

	public String getWarningMessageElementForERVisitSinceLastNICURNXpath() {
		return pro.getProperty("WarningMessageElementForERVisitSinceLastNICURNXpath");
	}

	public String getWarningMessageElementForHospReadmissionXpath() {
		return pro.getProperty("WarningMessageElementForHospReadmissionXpath");
	}

	public String getWarningMessageElementForChildCurrentHealthConcernsXpath() {
		return pro.getProperty("WarningMessageElementForChildCurrentHealthConcernsXpath");
	}

	public String getWarningMessageElementForChildVisionImpairmentXpath() {
		return pro.getProperty("WarningMessageElementForChildVisionImpairmentXpath");
	}

	public String getWarningMessageElementForChildAllergies() {
		return pro.getProperty("WarningMessageElementForChildAllergies");
	}

	public String getWarningMessageElementForChildHearingImpairmentXpath() {
		return pro.getProperty("WarningMessageElementForChildHearingImpairmentXpath");
	}

	public String getWarningMessageElementForChildHealthConditions() {
		return pro.getProperty("WarningMessageElementForChildHealthConditions");
	}

	public String getGMInputXpath() {
		return pro.getProperty("GMInputXpath");
	}

	public String getUpdatesSavedElementXpath() {
		return pro.getProperty("UpdatesSavedElementXpath");
	}

	public String getUpdatesCancelledElementXpath() {
		return pro.getProperty("UpdatesCancelledElementXpath");
	}

	public String getProgramNameFromBannerXpath() {
		return pro.getProperty("ProgramNameFromBannerXpath");
	}

	public String getshowHistoryElementsXpath() {
		return pro.getProperty("showHistoryElements");
	}

	public String gettableHeadersXpath() {
		return pro.getProperty("tableHeaders");
	}

	public String gethideHistoryElementXpath() {
		return pro.getProperty("hideHistoryElement");
	}

	public String getgridHeaderTextXpath() {
		return pro.getProperty("gridHeaderText");
	}

	public String getactualColumnHeadersXpath() {
		return pro.getProperty("actualColumnHeaders");
	}

	public String getlastModifiedElementsXpath() {
		return pro.getProperty("lastModifiedElements");
	}

	public String getquestionElementXpath() {
		return pro.getProperty("questionElement");
	}

	public String getanswersFromUIGridXpath() {
		return pro.getProperty("answersFromUIGrid");
	}

	public String getGMGridTitleElementXpath() {
		return pro.getProperty("gmgridTitleElement");
	}

	public String getGMGridRowsCountXpath() {
		return pro.getProperty("gmgridRowsCount");
	}

	// **********GeneralMedical & Lifestyle&Prevention - Common
	// xpaths**********//
	public String getGMQuestionLabelXpath() {
		return pro.getProperty("GMQuestionLabelXpath");
	}

	public String getGMQuestionLegendXpath() {
		return pro.getProperty("GMQuestionLegendXpath");
	}

	public String getshowHistoryElementXpath() {
		return pro.getProperty("showHistoryElement");
	}

	// **********LifeStyle N Prevention - Diet Nutrition n Exercise//
	public String getchildFeedingMethodElementXpath() {
		return pro.getProperty("childFeedingMethodElement");
	}

	public String getspecialDietFormulaNeedsElementXpath() {
		return pro.getProperty("specialDietFormulaNeedsElement");
	}
	
	
	//----------General Medical From Activity-----------//
	
	public String getGMAccordianClickFromActivityXpath() {
		return pro.getProperty("GMAccordianClickFromActivity");
	}
	
	public String getAddGMSubsectionFromActivityXpath() {
		return pro.getProperty("AddGMSubsectionFromActivity");
	}
	
	public String getGMtableHeadersFromActivityXpath() {
		return pro.getProperty("GMtableHeadersFromActivity");
	}
	
	public String getGMgridHeaderTextFromActivityXpath() {
		return pro.getProperty("GMgridHeaderTextFromActivity");
	}
	
	public String getGMactualColumnHeadersFromActivityXpath() {
		return pro.getProperty("GMactualColumnHeadersFromActivity");
	}
	
	public String getGMQuestionLabelXpathFromActivityXpath() {
		return pro.getProperty("GMQuestionLabelXpathFromActivity");
	}
	
	public String getGMQuestionLegendXpathFromActivityXpath() {
		return pro.getProperty("GMQuestionLegendXpathFromActivity");
	}
	
	public String getGMAnswersFromGridFromActivityXpath() {
		return pro.getProperty("GMAnswersFromGridFromActivity");
	}
	
	// ---Management reason Source - nivedida

	public String getTempMember() {
		return pro.getProperty("TempMember");
	}

	public String getSource() {
		return pro.getProperty("Source");
	}

	public String getManagementReasonleftNav() {
		return pro.getProperty("ManagementReasonleftNav");
	}

	public String getAddmgmt() {
		return pro.getProperty("Addmgmt");
	}

	public String getEditmgmt() {
		return pro.getProperty("Editmgmt");
	}

	// ----------Create Temp member manually----------------------//

	public String getWarningVerbiage() {
		return pro.getProperty("WarningVerbiage");

	}

	public String getCreateTempMemberText() {
		return pro.getProperty("CreateTempMemberText");
	}

	// -------------*Initial outreach activty UTC------------//

	public String getInitialOutreachActivitystatus() {
		return pro.getProperty("InitialOutreachActivitystatus");
	}

	public String getEditIntialoutreach() {
		return pro.getProperty("EditIntialoutreach");
	}

	public String getIntroandPermissionsaccordian() {
		return pro.getProperty("IntroandPermissionsaccordian");
	}

	public String getIntialOutreachSaveProgress() {
		return pro.getProperty("IntialOutreachSaveProgress");
	}

	public String getInitialOkbutton() {
		return pro.getProperty("InitialOkbutton");
	}

	public String getCaseStatus() {
		return pro.getProperty("CaseStatus");
	}

	public String getAddActivities() {
		return pro.getProperty("AddActivities");
	}

	public String getEditClinicalIntervention() {
		return pro.getProperty("EditClinicalIntervention");
	}

	public String getClinicalInterventionActivitystatus() {
		return pro.getProperty("ClinicalInterventionActivitystatus");
	}

	public String getGoalStatus() {
		return pro.getProperty("GoalStatus");
	}

	public String getGoalReason() {
		return pro.getProperty("GoalReason");
	}

	public String getInterventionsStatus() {
		return pro.getProperty("InterventionsStatus");
	}

	public String getInterventionsReason() {
		return pro.getProperty("InterventionsReason");
	}

	public String getBarriersStatus() {
		return pro.getProperty("InterventionsStatus");
	}

	public String getBarriersReason() {
		return pro.getProperty("InterventionsReason");
	}

	public String GoalCheckbox() {
		return pro.getProperty("GoalCheckbox");
	}

	// -------------*Initial outreach Hippa Maintained------------//

	public String getChangeHistroyDropdown() {
		return pro.getProperty("ChangeHistroyDropdown");

	}

	public String getChangeHistoryDropdownOption() {
		return pro.getProperty("ChangeHistoryDropdownOption");

	}

	public String getCHMemberReached() {
		return pro.getProperty("CHMemberReached");

	}

	public String getCHMemberReachedNot() {
		return pro.getProperty("CHMemberReachedNot");

	}

	public String getChangeHistroyDropdown1() {
		return pro.getProperty("ChangeHistroyDropdown1");

	}

	public String getChangeHistoryDropdownOption1() {
		return pro.getProperty("ChangeHistoryDropdownOption1");

	}

	

	//------------LifeStyle and Prevention-------------------------
	                     
	                       
	                       public String breadCrumb() {
	                           return pro.getProperty("LSPBreadCrumb");
	                       }
	                       
	                       public String LSPHeader() {
	                           return pro.getProperty("LSPHeader");
	                       }
	                       
	                       public String SourceDropDown() {
	                           return pro.getProperty("SourceDrop-down");
	                       }
	                       
	                       public String SourceValue() {
	                           return pro.getProperty("SourceValue");
	                       }
	                       
	                       public String LSPSubmit() {
	                           return pro.getProperty("LSPSubmit");
	                       }
	                       
	                       public String ShowHistoryClick() {
	                           return pro.getProperty("ShowHistoryClick");
	                       }
	                       
	                       public String ShowHistoryHeader() {
	                           return pro.getProperty("ShowHistoryHeader");
	                       } 
	                       
	                       public String SuccessMessage_Summary() {
	                           return pro.getProperty("SuccessMessage_Summary");
	                       }
	                       
	                       public String SuccsssMessage_Details() {
	                           return pro.getProperty("SuccsssMessage_Details");
	                       }
	                     
	                   	//************************************MemberActionCenterOrganizationProvider************************************[Author : Samba]//

	                   	public String getAddProvider2Xpath(){
	                   		return pro.getProperty("AddProvider2Xpath");
	                   	}
	                   	public String getOrganizationXpath(){
	                   		return pro.getProperty("OrganizationXpath");
	                   	}
	                   	public String getProviderIDOragnizationXpah(){
	                   		return pro.getProperty("ProviderIDOragnizationXpah");
	                   	}
	                   	public String getOrganizationNameFromOrganizationXpath(){
	                   		return pro.getProperty("OrganizationNameFromOrganizationXpath");
	                   	}
	                   	public String getProviderSpecialtyOrganizationDropdownXpath(){
	                   		return pro.getProperty("ProviderSpecialtyOrganizationDropdownXpath");
	                   	}
	                   	public String getProviderSpecialtyOrganizationChoiceXpath(){
	                   		return pro.getProperty("ProviderSpecialtyOrganizationChoiceXpath");
	                   	}
	                   	public String getNPIOrganizationXpath(){
	                   		return pro.getProperty("NPIOrganizationXpath");
	                   	}
	                   	public String getDEAOrganizationXpath(){
	                   		return pro.getProperty("DEAOrganizationXpath");
	                   	}
	                   	public String getTaxIDOrganizationXpath(){
	                   		return pro.getProperty("TaxIDOrganizationXpath");
	                   	}
	                   	public String getPhoneOrganizationXpath(){
	                   		return pro.getProperty("PhoneOrganizationXpath");
	                   	}
	                   	public String getFaxOrganizationXpath(){
	                   		return pro.getProperty("FaxOrganizationXpath");
	                   	}
	                   	public String getEmailOrganizationXpath(){
	                   		return pro.getProperty("EmailOrganizationXpath");
	                   	}
	                   	public String getAddressLine1OrganizationXpath(){
	                   		return pro.getProperty("AddressLine1OrganizationXpath");
	                   	}
	                   	public String getAddressLine2OrganizationXpath(){
	                   		return pro.getProperty("AddressLine2OrganizationXpath");
	                   	}
	                   	public String getCityOrganizationXpath(){
	                   		return pro.getProperty("CityOrganizationXpath");
	                   	}
	                   	public String getStateOrganizationDropDownXpath(){
	                   		return pro.getProperty("StateOrganizationDropDownXpath");
	                   	}
	                   	public String getStateOrganizationChoiceXpath(){
	                   		return pro.getProperty("StateOrganizationChoiceXpath");
	                   	}
	                   	public String getZipCodeOrganizationXpath(){
	                   		return pro.getProperty("ZipCodeOrganizationXpath");
	                   	}
	                   	public String getContactNameOrganizationXpath(){
	                   		return pro.getProperty("ContactNameOrganizationXpath");
	                   	}
	                   	public String getContactTitleOrganizationXpath(){
	                   		return pro.getProperty("ContactTitleOrganizationXpath");
	                   	}
	                   	public String getContactByOrganizationDropDownXpath(){
	                   		return pro.getProperty("ContactByOrganizationDropDownXpath");
	                   	}
	                   	public String getContactByOrganizationChoiceDropDown(){
	                   		return pro.getProperty("ContactByOrganizationChoiceDropDown");
	                   	}
	                   	public String getVerifiedOrganizationXpath(){
	                   		return pro.getProperty("VerifiedOrganizationXpath");
	                   	}
	                   	public String getPrimaryOrganizationXpath(){
	                   		return pro.getProperty("PrimaryOrganizationXpath");
	                   	}
	                   	public String getSubmitButtonOrganizationXpath(){
	                   		return pro.getProperty("SubmitButtonOrganizationXpath");
	                   	}
	                   	//*******************************CareTeamProvider*************************[Author : Samba]//
	                   	
	                   	public String getCareTeamXpath(){
	                   		return pro.getProperty("CareTeamXpath");
	                   	}
	                   	
	                   	public String getAddProviderFromCareTeamXpath(){
	                   		return pro.getProperty("AddProviderFromCareTeamXpath");
	                   	}
	                   	public String getCareTeamRoleFromCareTeam(){
	                   		return pro.getProperty("CareTeamRoleFromCareTeam");
	                   	}
	                   	public String getCareTeamChoice(){
	                   		return pro.getProperty("CareTeamChoice");
	                   	}
	                   	public String getCreateManualFromCareTeamRole(){
	                   		return pro.getProperty("CreateManualFromCareTeamRole");
	                   	}
	                   	public String getIndividualFromCareTeamXpath(){
	                   		return pro.getProperty("IndividualFromCareTeamXpath");
	                   	}
	                   	public String getOrganizationTypeFromCareTeamXpath(){
	                   		return pro.getProperty("OrganizationTypeFromCareTeamXpath");
	                   	}
	                   	public String getProviderIDFromCareTeamXpath(){
	                   	return pro.getProperty("ProviderIDFromCareTeamXpath");
	                   	
	                   	}
	                   	public String getOrganizationNameFromCareTeamXpath(){
	                   		return pro.getProperty("OrganizationNameFromCareTeamXpath");
	                   	}
	                   	
	                   	public String getFirstNameFromCareTeamXpath(){
	                   		return pro.getProperty("FirstNameFromCareTeamXpath");
	                   	}
	                   	public String getLastNameFromCareTeamXpath(){
	                   		return pro.getProperty("LastNameFromCareTeamXpath");
	                   	}
	                   	public String getProviderGenderFromCareTeamXpath(){
	                   		return pro.getProperty("ProviderGenderFromCareTeamXpath");
	                   	}
	                   	public String getLanguageDropDownFromCareTeamXpath(){
	                   		return pro.getProperty("LanguageDropDownFromCareTeamXpath");
	                   	}
	                   	public String getLanguageChoiceFromCareTeamXpath(){
	                   		return pro.getProperty("LanguageChoiceFromCareTeamXpath");
	                   	}
	                   	public String getTitleDropDownFromCareTeamXpath(){
	                   		return pro.getProperty("TitleDropDownFromCareTeamXpath");
	                   	}
	                   	public String getTitleChoiceFromCareTeamXpath(){
	                   		return pro.getProperty("TitleChoiceFromCareTeamXpath");
	                   	}
	                   	public String getProviderSpecialtyDropDownXpath(){
	                   		return pro.getProperty("ProviderSpecialtyDropDownXpath");
	                   	}
	                   	public String getNPIFromCareTeamXpath(){
	                   		return pro.getProperty("NPIFromCareTeamXpath");
	                   	}
	                   	public String getDEAFromCareTeamXpath(){
	                   		return pro.getProperty("DEAFromCareTeamXpath");
	                   	}
	                   	public String getTaxIDFromCareTeamXpath(){
	                   		return pro.getProperty("TaxIDFromCareTeamXpath");
	                   	}
	                   	public String getPhoneFromCareTeamXpath(){
	                   		return pro.getProperty("PhoneFromCareTeamXpath");
	                   	}
	                   	public String getFaxFromCareTeamXpath(){
	                   		return pro.getProperty("FaxFromCareTeamXpath");
	                   	}
	                   	public String getEmailFromCareTeamXpath(){
	                   		return pro.getProperty("EmailFromCareTeamXpath");
	                   	}
	                   	public String getAddressLine1FromCareTeamXpath(){
	                   		return pro.getProperty("AddressLine1FromCareTeamXpath");
	                   	}
	                   	public String getAddressLine2FromCareTeamXpath(){
	                   		return pro.getProperty("AddressLine2FromCareTeamXpath");
	                   	}
	                   	public String getCityFromCareTeamXpath(){
	                   		return pro.getProperty("CityFromCareTeamXpath");
	                   	}
	                   	public String getStateFromCareTeamDropDownXpath(){
	                   		return pro.getProperty("StateFromCareTeamDropDownXpath");
	                   	}
	                   	public String getStateFromCareTeamXpath(){
	                   		return pro.getProperty("StateFromCareTeamXpath");
	                   	}
	                   	public String getZipCodeFromCareTeamXpath(){
	                   		return pro.getProperty("ZipCodeFromCareTeamXpath");
	                   	}
	                   	public String getContactNameFromCareTeamXpath(){
	                   		return pro.getProperty("ContactNameFromCareTeamXpath");
	                   	}
	                   	public String getContactTitleFromCareTeamXpath(){
	                   		return pro.getProperty("ContactTitleFromCareTeamXpath");
	                   	}
	                   	public String getContactByFromCareTeamDropDownXpath(){
	                   		return pro.getProperty("ContactByFromCareTeamDropDownXpath");
	                   	}
	                   	public String getContactByFromCareTeamChoiceXpath(){
	                   		return pro.getProperty("ContactByFromCareTeamChoiceXpath");
	                   	}
	                   	public String getVerifiedFromCareTeamXpath(){
	                   		return pro.getProperty("VerifiedFromCareTeamXpath");
	                   	}
	                   	
	                   	public String getPrimaryFromCareTeamXpath(){
	                   		return pro.getProperty("PrimaryFromCareTeamXpath");
	                   	}
	                   	public String getSubmitFromCareTeamXpath(){
	                   		return pro.getProperty("SubmitFromCareTeamXpath");
	                   	}
	                   	public String getStateFromCareTeamDropDownOrganization(){
	                   		return pro.getProperty("StateFromCareTeamDropDownOrganization");
	                   	}
	                   	public String getStateChoiceFromCareTeamOrganizaion(){
	                   		return pro.getProperty("StateChoiceFromCareTeamOrganizaion");
	                   	}
	                   	public String getContactByFromCareTeamDropDownOrganization(){
	                   		return pro.getProperty("ContactByFromCareTeamDropDownOrganization");
	                   	}
	                   	public String getContactByChoiceFromCareTeamOrganization(){
	                   		return pro.getProperty("ContactByChoiceFromCareTeamOrganization");
	                   	}
	                   	public String getPrimaryFromCareTeamOrganization(){
	                   		return pro.getProperty("PrimaryFromCareTeamOrganization");
	                   	}
	                   	public String getSubmitButtonFromCareTeamXpath(){
	                   		return pro.getProperty("SubmitButtonFromCareTeamXpath");
	                   	}
	                   	public String getEditButtonFromCareTeamXpath(){
	                   		return pro.getProperty("EditButtonFromCareTeamXpath");
	                   	}
	                   	public String getExpandButtonFromCareTeamXpath(){
	                   		return pro.getProperty("ExpandButtonFromCareTeamXpath");
	                   	}

	                   	
	                  	//************************************MemberActionCenterIndividalProvider************************************[Author : Samba]//


	                	public String getMemberActionCenterClickXpath(){
	                		return pro.getProperty("MemberActionCenterClickXpath");
	                	}
	                	public String getActivitiesUnderMemberActionCenter(){
	                		return pro.getProperty("ActivitiesUnderMemberActionCenter");
	                	}
	                	public String getEditMemberActionCenter(){
	                		return pro.getProperty("EditMemberActionCenter");
	                	}
	                	public String getClickOnProvider(){
	                		return pro.getProperty("ClickOnProvider");
	                	}
	                	
	                	public String getExpandProvider(){
	                		return pro.getProperty("ExpandProvider");
	                	}
	                	public String getAddProvider(){
	                		return pro.getProperty("AddProvider");
	                	}
	                	public String getIndividualXpath(){
	                		return pro.getProperty("IndividualXpath");
	                	}
	                	public String getProviderIDXpath(){
	                		return pro.getProperty("ProviderIDXpath");
	                	}
	                	public String getOrganizationNameXpath(){
	                		return pro.getProperty("OrganizationNameXpath");
	                	}
	                	public String getFirstName1Xpath(){
	                		return pro.getProperty("FirstName1Xpath");
	                	}
	                	public String getLastName1Xpath(){
	                		return pro.getProperty("LastName1Xpath");
	                	}
	                	public String getProviderGenderXpath(){
	                		return pro.getProperty("ProviderGenderXpath");
	                	}
	                	public String getLanguageDropdownXpath(){
	                		return pro.getProperty("LanguageDropdownXpath");
	                	}
	                	public String getLanguageChoiceXpath(){
	                		return pro.getProperty("LanguageChoiceXpath");
	                	}
	                	public String getTitleDropdownXpath(){
	                		return pro.getProperty("TitleDropdownXpath");
	                	}
	                	public String getTitleChoiceXpath(){
	                		return pro.getProperty("TitleChoiceXpath");
	                	}
	                	public String getProviderSpecialtyDropdownXpath(){
	                		return pro.getProperty("ProviderSpecialtyDropdownXpath");
	                	}
	                	public String getProviderSpecialtyChoiceXpath(){
	                		return pro.getProperty("ProviderSpecialtyChoiceXpath");
	                	}
	                	public String getNPIXpath(){
	                		return pro.getProperty("NPIXpath");
	                	}
	                	public String getDEAXpath(){
	                		return pro.getProperty("DEAXpath");
	                	}
	                	public String getTaxIDXpath(){
	                		return pro.getProperty("TaxIDXpath");
	                	}
	                	public String getPhoneNumber1Xpath(){
	                		return pro.getProperty("PhoneNumber1Xpath");
	                	}
	                	public String getFaxXpath(){
	                		return pro.getProperty("FaxXpath");
	                	}
	                	public String getEmailXpath(){
	                		return pro.getProperty("EmailXpath");
	                	}
	                	public String getAddressLine1Xpath(){
	                		return pro.getProperty("AddressLine1Xpath");
	                	}
	                	public String getAddressLine2Xpath(){
	                		return pro.getProperty("AddressLine2Xpath");
	                	}
	                	public String getCityXpath(){
	                		return pro.getProperty("CityXpath");
	                	}
	                	public String getStateDropDown1Xpath(){
	                		return pro.getProperty("StateDropDown1Xpath");
	                	}
	                	public String getStateChoice1Xpath(){
	                		return pro.getProperty("StateChoice1Xpath");
	                	}
	                	public String getZipCodeXpath(){
	                		return pro.getProperty("ZipCodeXpath");
	                	}

	                	public String getContactNameXpath(){
	                		return pro.getProperty("ContactNameXpath");
	                	}
	                	public String getContactTitleXpath(){
	                		return pro.getProperty("ContactTitleXpath");
	                	}
	                	public String getContactByDropDownXapth(){
	                		return pro.getProperty("ContactByDropDownXapth");
	                	}
	                	public String getContactByChoiceXpath(){
	                		return pro.getProperty("ContactByChoiceXpath");
	                	}
	                	public String getVerifiedXpath(){
	                		return pro.getProperty("VerifiedXpath");
	                	}
	                	public String getPrimaryChoiceXpath(){
	                		return pro.getProperty("PrimaryChoiceXpath");
	                	}
	                	public String getSubmitButtonXpath(){
	                		return pro.getProperty("SubmitButtonXpath");
	                	}

	                	public String getEditButtonFromInitialOutreachXpath(){
	                		return pro.getProperty("EditButtonFromInitialOutreachXpath");
	                	}
	                	public String getExpandButtonFromInitialOutreachXpath(){
	                		return pro.getProperty("ExpandButtonFromInitialOutreachXpath");
	                	}
	   
	                   	
	                  //-------------------------CarePlan-----------------------------------------------[Author : Uma n Jaya]//  
	          	      
	         	       public String CarePlanBreadCrumb() {
	         	    	   return pro.getProperty("BreadCrumb");
	         	       }
	         	       
	         	       public String getStartDate() {
	         	    	   return pro.getProperty("StartDate");
	         	       }
	         	       
	         	       public String getFilterImage() {
	         	    	   return pro.getProperty("FilterImage");
	         	       }
	         	       
	         	       public String getAddGoalClick() {
	         	    	   return pro.getProperty("AddGoal");
	         	       }
	         	       
	         	       public String getVerifyAddGoalPage() {
	         	    	   return pro.getProperty("VerifyAddGoalPage");
	         	       }
	         	       
	         	       public String getPriorityValues() {
	         	    	   return pro.getProperty("PriorityValues");
	         	       }
	         	       
	         	       public String getCPStatusValue() {
	         	    	   return pro.getProperty("StatusValue");
	         	       }
	         	       
	         	       public String getCPStatusReasonValue() {
	         	    	   return pro.getProperty("StatusReasonValue");
	         	       }
	         	       
	         	       public String Submit_CarePlan() {
	         	    	   return pro.getProperty("Submit");
	         	       }
	         	       
	         	       public String getSecRow() {
	         	    	   return pro.getProperty("SecRow");
	         	       }
	         	       
	         	       public String GoalsDropdown() {
	         	    	   return pro.getProperty("GoalsDropdown");
	         	       }
	         	       
	         	       public String getWait_Goal() {
	         	    	   return pro.getProperty("Wait_Goal");
	         	       }
	         	       
	         	       public String getSelectGoal() {
	         	    	   return pro.getProperty("SelectGoal");
	         	       }
	         	       
	         	       public String PriorityDropdown() {
	         	    	   return pro.getProperty("PriorityDropdown");
	         	       }
	         	       
	         	       public String getWait_Priority() {
	         	    	   return pro.getProperty("Wait_Priority");
	         	       }
	         	       
	         	       public String getSelectPriority() {
	         	    	   return pro.getProperty("SelectPriority");
	         	       }
	         	       
	         	       public String getGridGoalValue() {
	         	    	   return pro.getProperty("GridGoalValue");
	         	       }
	         	       
	         	       public String getGridPriorityValue() {
	         	    	   return pro.getProperty("GridPriorityValue");
	         	       }
	         	       
	         	       public String getGridStartDateValue() {
	         	    	   return pro.getProperty("GridStartDateValue");
	         	       }
	         	       
	         	       public String getGridStatusValue() {
	         	    	   return pro.getProperty("GridStatusValue");
	         	       }
	         	       
	         	       public String getGridStatusReasonValue() {
	         	    	   return pro.getProperty("GridStatusReasonValue");
	         	       }
	         	       
	         	       public String getSelectedGoal() {
	         	    	   return pro.getProperty("SelectedGoal");
	         	       }
	         	       
	         	       public String getSelectedStartDate() {
	         	    	   return pro.getProperty("SelectedStartDate");
	         	       }
	         	       
	         	       public String getSelectedPriority() {
	         	    	   return pro.getProperty("SelectedPriority");
	         	       }
	         	       
	         	       public String ReasonForExtensionDropdown() {
	         	    	   return pro.getProperty("ReasonForExtensionDropdown");
	         	       }
	         	       
	         	       public String getWait_ReasonForExtension() {
	         	    	   return pro.getProperty("Wait_ReasonForExtension");
	         	       }
	         	       
	         	       public String getSelectReasonForExtension() {
	         	    	   return pro.getProperty("SelectReasonForExtension");
	         	       }
	         	       
	         	       public String getSelectedReasonForExtension() {
	         	    	   return pro.getProperty("SelectedReasonForExtension");
	         	       }
	         	       
	         	       public String getStatusText_NotStarted() {
	         	    	   return pro.getProperty("StatusText_NotStarted");
	         	       }
	         	       
	         	       public String getTargetEndDate()
	         	       {
	         	    	   return pro.getProperty("TargetEndDate");
	         	       }
	         	       
	         	       public String getClosedDate() {
	         	    	   return pro.getProperty("ClosedDate");
	         	       }
	         	       
	         	       public String getTargetEndDate_Value() {
	         	    	   return pro.getProperty("TargetEndDate_Value");
	         	       }
	         	       
	         	       public String clickEditGoal() {
	         	    	   return pro.getProperty("EditGoal");
	         	       }
	         	       
	         	       public String getPageHeader() {
	         	    	   return pro.getProperty("PageHeader");
	         	       }
	         	       
	         	       public String getStatusText_InProgress() {
	         	    	   return pro.getProperty("StatusText_InProgress");
	         	       }
	         	       
	         	       public String getReasonForExtension() {
	         	    	   return pro.getProperty("ReasonForExtension");
	         	       }
	         	       
	         	       public String getEditGoalSubmit() {
	         	    	   return pro.getProperty("EditGoalSUbmit");
	         	       }
	         	       
	         	       public String getStatusText_Closed() {
	         	    	   return pro.getProperty("StatusText_Closed");
	         	       }
	         	       
	         	       public String getWait_StatusReason() {
	         	    	   return pro.getProperty("Wait_StatusReason");
	         	       }
	         	       
	         	       public String getSelectStatusReason() {
	         	    	   return pro.getProperty("SelectStatusReason");
	         	       }
	         	       
	         //----------------Goal History page ---------------------------- [Author : UMA, JAYA]//

	         	     //------ Validating Goal history grid---------//
	          			public String ValidatingGOALHISTORYGrid()
	          			{
	          				return pro.getProperty("ValidatingGOALHISTORYGrid");
	          			}
	          			
	          			//----Goal History Link-------//
	          			public String getGoalHistoryIconLink()
	          			{
	          				
	          				return pro.getProperty("GoalHistoryIcon");
	          			}
	          			//-----Goal History Page Validation--------//
	          			public String GoalHistoryPageValidation()
	          			{
	          			return pro.getProperty("GoalHistoryValidation");
	          			}
	          			
	          			//------Goal Column Validation----------//
	          			public String GoalColumnValidate()
	          			{
	          				return pro.getProperty("GoalColumnValidation");
	          			}
	          			//--------TargetEndDateValidation-----//
	          			public String TargetEndDateValidation()
	          			{
	          				return pro.getProperty("TargetEndDateValidation");
	          			}
	          			//-----------ReasonForExtensionValidation--------------//
	          			public String ReasonForExtensionValidation()
	          			{
	          				return pro.getProperty("ReasonForExtensionValidation");
	          			}
	          			//------UpdatedByUserValidation-------------//
	          			public String UpdatedByUserValidation()
	          			{
	          				return pro.getProperty("UpdatedByUserValidation");
	          			}
	          			
	          			//BackbuttonInGoalHistoryPage
	          			public String  BackbuttonInGoalHistoryPage()
	          			{
	          				return pro.getProperty("BackbuttonInGoalHistoryPage");
	          			}
	          			
	          			public String getBackButtonOnGoalHistory()
	           			{
	           				return pro.getProperty("BackButton");
	           			}
	          			
	          			public String getFilterButton_Filter()
	           			{
	           				return pro.getProperty("Filter");
	           			}
	          			
	          			public String getClearButton_Filter()
	           			{
	           				return pro.getProperty("Clear");
	           			}
	          			
	          			public String getBackButton_Filter()
	           			{
	           				return pro.getProperty("Back");
	           			}
	          			 			
	          			
	          			//-------------------- Goal history columns data validation------------------------[Author :UMA JAYA]-//
	          			
	          			public String UpdatedDateValidation()
	           			{
	           				return pro.getProperty("UpdatedDateValidation");
	           			}
	          			
	          			//validatingdataunderGoalcolumn
	          			public String validatingdataunderGoalcolumn()
	          			{
	          				return pro.getProperty("validatingdataunderGoalcolumn");
	          			}
	          			
	          			//validatingdataunderTargetEndDatecolumn
	          			
	          			public String validatingdataunderTargetEndDatecolumn()
	          			{
	          				return pro.getProperty("validatingdataunderTargetEndDatecolumn");
	          			}
	          			
	          			//validatingdataunderReasonforExtensioncolumn
	          			
	          			public String validatingdataunderReasonforExtensioncolumn()
	          			{
	          				return pro.getProperty("validatingdataunderReasonforExtensioncolumn");
	          			}
	          			//validatingdataunderUpdatedbyUsercolumn
	          			
	          			public String validatingdataunderUpdatedbyUsercolumn()
	          			{
	          				return pro.getProperty("validatingdataunderUpdatedbyUsercolumn");
	          			}
	          			
	          			//validatingdataunderUpdatedDatecolumn
	          			public String validatingdataunderUpdatedDatecolumn()
	          			{
	          				return pro.getProperty("validatingdataunderUpdatedDatecolumn");
	          			}
	          			
	          			//validatingdataunderPrioritycolumn
	          			public String validatingdataunderPrioritycolumn()
	          			{
	          				return pro.getProperty("validatingdataunderPrioritycolumn");
	          			}

	          			
	          		//------------Interventions--------------------	[Author: UMA N JAYA]//  
	     		       
	         		   public String IntBreadCrumb() {
	         		   	   return pro.getProperty("BreadCrumb");
	         		   }
	         		       
	         	       public String getHeader() {
	         	    	   return pro.getProperty("Header");
	         	       }
	         	       
	         	       public String getShowingValue() {
	         	    	   return pro.getProperty("ShowingValue");
	         	       }
	         	       
	         	       public String AddIntervention() {
	         	    	   return pro.getProperty("AddIntervention1");
	         	       }
	         	       
	         	       public String AddInterventionHeader(){
	         	    	   return pro.getProperty("AddInterventionHeader");
	         	       }
	         	       
	         	       public String InterventionGoalGrid(){
	         	    	   return pro.getProperty("InterventionGoalGrid");
	         	       }
	         	       
	         	       public String InterventionCheckBox(){
	         	    	   return pro.getProperty("CheckBox");
	         	       }
	         	       
	         	       public String EditIntervention(){
	         	    	   return pro.getProperty("EditIntervention");
	         	       }
	         	       
	         	       public String HistoryButton(){
	         	    	   return pro.getProperty("HistoryButton");
	         	       }
	         	       
	         	       public String GridCount(){
	         	    	   return pro.getProperty("GridCount");
	         	       }
	         	       
	         	       public String getAssignedTo() {
	         	    	   return pro.getProperty("AssignedTo");
	         	       }
	         	       
	         	       public String clickSelectGoal() {
	         	    	   return pro.getProperty("SelectGoal");
	         	       }
	         	       
	         	       public String clickSubmit() {
	         	    	   return pro.getProperty("SubmitIntervention");
	         	       }
	         	       
	         	       public String getInterventionsdropdown() {
	         	    	   return pro.getProperty("InterventionsDropdown");
	         	       }
	         	       
	         	       public String getWaitInterventions() {
	         	    	   return pro.getProperty("Wait_Interventions");
	         	       }
	         	       
	         	       public String getSelectIntervention() {
	         	    	   return pro.getProperty("SelectIntervention");
	         	       }
	         	       
	         	       public String getAssignedToDropdown() {
	         	    	   return pro.getProperty("AssignedToDropdown");
	         	       }
	         	       
	         	       public String getWaitAssignedTo() {
	         	    	   return pro.getProperty("Wait_AssignedTo");
	         	       }
	         	       
	         	       public String getSelectAssignedTo() {
	         	    	   return pro.getProperty("SelectAssignedTo");
	         	       }
	         	       
	         	       public String getSelectedIntervention() {
	         	    	   return pro.getProperty("SelectedIntervention");
	         	       }
	         	       
	         	       public String getSelectedAssignedTo() {
	         	    	   return pro.getProperty("SelectedAssignedTo");
	         	       }
	         	       
	         	      public String getlblSelectIntervention() {
	         	    	   return pro.getProperty("lblSelectIntervention");
	         	       }
	         	     public String getdrpSelectInterventionvalue() {
	         	    	   return pro.getProperty("drpSelectInterventionvalue");
	         	       }
	         	     
	         	       
	         //-----------Int----------
	         	       public String InterventionHistoryLink()
	          			{
	          				return pro.getProperty("InterventionHistoryLink");
	          			}
	          			
	          			public String EditInterventionLink()
	          			{
	          				return pro.getProperty("EditInterventionLink");
	          			}
	          
	         //----------------Intervention History Grid column validation-------------//
	           			
	           			public String InterventionsValidationInGrid()
	           			{
	           			  return pro.getProperty("InterventionsValidationInGrid");
	           			}
	          
	           			public String AssignedToValidationInGrid()
	           			{
	           			  return pro.getProperty("AssignedToValidationInGrid");
	           			}
	           			
	            			public String StatusValidationInGrid()
	           			{
	           			  return pro.getProperty("StatusValidationInGrid");
	           			}
	           			 			public String StatusReasonValidationInGrid()
	           			{
	           			  return pro.getProperty("StatusReasonValidationInGrid");
	           			}

	           			public String UpdatedByUserValidationInGrid()
	           			{
	           			  return pro.getProperty("UpdatedByUserValidationInGrid");
	           			}
	           			 			
	           			public String UpdatedDateValidationInGrid()
	           			{
	           			  return pro.getProperty("UpdatedDateValidationInGrid");
	           			}
	         //-------------- Validating the data in interventions history page-----------------------//
	           			
	           			public String InterventionTextGoalHi()
	           			{
	           			  return pro.getProperty("InterventionTextGoalHi");
	           			}
	           			
	           			public String AssignedToTextGoalHi()
	           			{
	           			  return pro.getProperty("AssignedToTextGoalHi");
	           			}
	           			
	           			public String StatusTextGoalHi()
	           			{
	           			  return pro.getProperty("StatusTextGoalHi");
	           			}
	           			
	           			public String StatusReasonTextGoalHi()
	           			{
	           			  return pro.getProperty("StatusReasonTextGoalHi");
	           			}
	           			
	           			public String UpdatedByUserTextGoalHi()
	           			{
	           			  return pro.getProperty("UpdatedByUserTextGoalHi");
	           			}
	           		
	           			public String UpdatedDateGoalHi()
	           			{
	           			  return pro.getProperty("UpdatedDateGoalHi");
	           			}
	          //-------------- Validations in Edit Intervention page-------------------------------//
	           			
	           			//----AssignedToDrodown-------//
	           			public String AssignedToDropdown()
	           			{
	           			  return pro.getProperty("AssignedToDropdown");
	           			}
	           			
	           			//---- Associated Goal Description
	          			
	           			public String AssociatedGoalDescription()
	           			{
	           			  return pro.getProperty("AssociatedGoalDescription");
	           			}
	          
	           			//Intervention Description
	          			
	           			public String InterventionDescription()
	           			{
	           			  return pro.getProperty("InterventionDescription");
	           			}
	           					
	           			//Selecting Snow White In Assigned To Dropdown
	           			
	           			public String SelectingSnowWhiteInAssignedToDropdown()
	           			{
	           			  return pro.getProperty("SelectingSnowWhiteInAssignedToDropdown");
	           			}
	           			//---------Statusdropdown
	           			
	           			public String Statusdropdown()
	           			{
	           			  return pro.getProperty("Statusdropdown");
	           			}
	           			
	           			//----------SelectingNotStartedInStatusDropdown
	           			
	           			public String SelectingNotStartedInStatusDropdown()
	           			{
	           			  return pro.getProperty("SelectingNotStartedInStatusDropdown");
	           			}
	           			
	           			//-----Selecting In Progress In Status Dropdown
	           			
	           			public String SelectingInProgressInStatusDropdown()
	           			{
	           			  return pro.getProperty("SelectingInProgressInStatusDropdown");
	           			}
	           			
	           			//------Selecting Closed In Status Dropdown
	           			
	           			public String SelectingClosedInStatusDropdown()
	           			{
	           			  return pro.getProperty("SelectingClosedInStatusDropdown");
	           			}
	           			
	           			//-----------ValidatingStatusReasonWhenStatusIsNotStarted
	           			
	           			public String ValidatingStatusReasonWhenStatusIsNotStartedInEditInter()
	           			{
	           			  return pro.getProperty("ValidatingStatusReasonWhenStatusIsNotStartedInEditInter");
	           			}
	           			
	           			//--------------Validate Text In StatusReason dropdown-----//
	           			
	           			public String ValidateTextInStatusReason()
	           			{
	           			  return pro.getProperty("ValidateTextInStatusReason");
	           			}
	           			// Validations in status reason dropdown when status is closed
	           			
	           			//Met In Status Reason Drp
	           			public String MetInStatusReasonDrp()
	           			{
	           			  return pro.getProperty("MetInStatusReasonDrp");
	           			}
	           			//Partially Met In Status Reason Drp
	           			public String PartiallyMetInStatusReasonDrp()
	           			{
	           			  return pro.getProperty("PartiallyMetInStatusReasonDrp");
	           			}
	           			//Not Clinically Appropriate In Status Reason Drp
	           			public String NotClinicallyAppropriateInStatusReasonDrp()
	           			{
	           			  return pro.getProperty("NotClinicallyAppropriateInStatusReasonDrp");
	           			}
	           			//Not Compliant In Status Reason Drp
	           			public String NotCompliantInStatusReasonDrp()
	           			{
	           			  return pro.getProperty("NotCompliantInStatusReasonDrp");
	           			}
	           			//Progress Plateau In Status Reason Drp 
	           			public String ProgressPlateauInStatusReasonDrp()
	           			{
	           			  return pro.getProperty("ProgressPlateauInStatusReasonDrp");
	           			}
	             			
	           			//-----------SubmitButton
	           			
	           			public String SubmitButton()
	           			{
	           			  return pro.getProperty("SubmitButton");
	           			}
	           			
	           			// BackButton
	           			
	           			public String BackButton()
	           			{
	           			  return pro.getProperty("BackButton");
	           			}
	           			
	           			//---- ValidatingAssociatedGoal-------
	           			
	           			public String ValidatingAssociatedGoal()
	           			{
	           			  return pro.getProperty("ValidatingAssociatedGoal");
	           			}
	           			
	           			//------ValidatingIntervention
	           			
	           			public String ValidatingIntervention()
	           			{
	           			  return pro.getProperty("ValidatingIntervention");
	           			}
	           			
	           			//-------ValidatingAssignedTo
	           			
	           			public String ValidatingAssignedTo()
	           			{
	           			  return pro.getProperty("ValidatingAssignedTo");
	           			}
	           			
	           		    //-----------ValidatingStatus
	           			
	           			public String ValidatingStatus()
	           			{
	           			  return pro.getProperty("ValidatingStatus");
	           			}
	           			
	            			//-----------ValidatingStatusReason
	           			
	           			public String ValidatingStatusReason()
	           			{
	           			  return pro.getProperty("ValidatingStatusReason");
	           			}
	           			
	           		//----------activities sub sections-----------------[Property file : Activities :Author : KV]//
	           	       
	           	       public String getSubsectionsPath() {
	           	              return pro.getProperty("SubsectionsPath");

	           	       }
	           	       
	           	    public String getSubsectionsPathreferral() {
         	              return pro.getProperty("SubsectionsPathreferral");

         	       }
	           	       
	           	       public String getSubActivityIcon() {
	           	              return pro.getProperty("SubActivityIcon");

	           	       }
	           	       public String getSubActivityModelWindowHeader() {
	           	              return pro.getProperty("SubActivityModelWindowHeader");

	           	       }
	           	       public String getListOfSubactivities() {
	           	              return pro.getProperty("ListOfSubactivities");

	           	       }
	           	       public String getSubActivityAddbutton() {
	           	              return pro.getProperty("SubActivityAddbutton");

	           	       }
	           	    public String getEditCaseClosure1() {
         	              return pro.getProperty("EditCaseClosure");

         	       }
	           	 public String getCaseClosureActivitystatus() {
      	              return pro.getProperty("CaseClosureActivitystatus");

      	       }
	           	public String getEditReferral() {
     	              return pro.getProperty("EditReferral");

     	       }
	           	public String getReferralActivitystatus() {
     	              return pro.getProperty("ReferralActivitystatus");

     	       }
	        	public String getEditConsultation() {
   	              return pro.getProperty("EditConsultation");

   	       }
	           	public String getConsultationActivitystatus() {
   	              return pro.getProperty("ConsultationActivitystatus");

   	       }
	          	public String getEditCareCoordination() {
	   	              return pro.getProperty("EditCareCoordination");

	   	       }
		           	public String getCareCoordinationActivitystatus() {
	   	              return pro.getProperty("CareCoordinationActivitystatus");

	   	       }
		           	public String getEditCaseReview() {
		   	              return pro.getProperty("EditCaseReview");

		   	       }
			           	public String getCaseReviewActivitystatus() {
		   	              return pro.getProperty("CaseReviewActivitystatus");

		   	       }
	           	       
	           	       

	           	  //----------clinical Intervention-------------[Propertyfile : Clinical.property : Author : Nivedida]
	                   public String getActivityList()
	                   {
	                        return pro.getProperty("ActivityList");  
	                   }
	                   public String getEditInitial()
	                   {
	                        return pro.getProperty("EditInitial");  
	                   }
	                   public String getAlert()
	                   {
	                        return pro.getProperty("Alert");  
	                   }
	                   public String getClinicalActivity()
	                   {
	                        return pro.getProperty("ClinicalActivity");  
	                   }
	                   public String getInitialStatus()
	                   {
	                        return pro.getProperty("InitialStatus");  
	                   }
	                   
	                   public String getClinicalStatus()
	                   {
	                        return pro.getProperty("ClinicalStatus");  
	                   }
	                   public String getClinicalEdit()
	                   {
	                        return pro.getProperty("ClinicalEdit");  
	                   }
	                   public String getProgram()
	                   {
	                        return pro.getProperty("Program");  
	                   }
	                   public String getEditstatus()
	                   {
	                        return pro.getProperty("Editstatus");  
	                   }
	                   public String getassignedto()
	                   {
	                        return pro.getProperty("assignedto");  
	                   }
	                  
	                   public String getActivityname()
	                   {
	                        return pro.getProperty("Activityname");  
	                   }
	                   public String getStartdate()
	                   {
	                        return pro.getProperty("Startdate");  
	                   }
	                   public String getDuedate()
	                   {
	                        return pro.getProperty("Duedate");  
	                   }
	                   public String getintrosection()
	                   {
	                        return pro.getProperty("introsection");  
	                   }
	                   public String getmemberSection()
	                   {
	                        return pro.getProperty("memberSection");  
	                   }
	                   public String getCareplansection()
	                   {
	                        return pro.getProperty("Careplansection");  
	                   }
	                   public String getQuestionnairesection()
	                   {
	                        return pro.getProperty("Questionnairesection");  
	                   }
	                   public String getRecordsection()
	                   {
	                        return pro.getProperty("Recordsection");  
	                   }
	                   
	                   public String getAddActivity()
	                   {
	                        return pro.getProperty("AddActivity");  
	                   }
	                   
	                   public String getCliActivityGrid()
	                   {
	                        return pro.getProperty("CliActivityGrid");  
	                   }

	                 
	                  
	                   //**********Conditions sub section [Author : nivedida]**********//
	                   
	                       
	                            
//	                            public String getEditCaseClosure()
//	                            {
//	                                 return pro.getProperty("EditCaseClosure");  
//	                            }  
	                            public String getaddSubactivity()
	                            {
	                                 return pro.getProperty("addSubactivity");  
	                            }
	                            public String getConditionSub()
	                            {
	                                 return pro.getProperty("ConditionSub");  
	                            }
	                            public String getAddBtn()
	                            {
	                                 return pro.getProperty("AddBtn");  
	                            }
	                            public String getConditionrsection()
	                            {
	                                 return pro.getProperty("Conditionrsection");  
	                            }
	                            public String getConditionOpen()
	                            {
	                                 return pro.getProperty("ConditionOpen");  
	                            }
	                            public String getAddCondition()
	                            {
	                                 return pro.getProperty("AddCondition");  
	                            }
	                            public String getConditionAdded()
	                            {
	                                 return pro.getProperty("ConditionAdded");  
	                            }
	                            public String getEditCondition()
	                            {
	                                 return pro.getProperty("EditCondition");  
	                            }
	                            public String getAddHeader()
	                            {
	                                 return pro.getProperty("AddHeader");  
	                            }
	                            public String getEditHeader()
	                            {
	                                 return pro.getProperty("EditHeader");  
	                            }
	                            

	                          //---- Care Coordination [ Author : nivedida]------//
	                            public String getAddActivityBtn()
	                            {
	                                 return pro.getProperty("AddActivityBtn");  
	                            }
	                            public String getViewActivityimage()
	                            {
	                                 return pro.getProperty("ViewActivityimage");  
	                            }
	                            public String getEditActivityimage()
	                            {
	                                 return pro.getProperty("EditActivityimage");  
	                            }
	                            public String getCCActivity()
	                            {
	                                 return pro.getProperty("CCActivity");  
	                            }
	                            public String getCCStatus()
	                            {
	                                 return pro.getProperty("CCStatus");  
	                            }
	                            
	                            public String getQuestion()
	                            {
	                                 return pro.getProperty("Question");  
	                            }
	                            public String getCCactivityName()
	                            {
	                                 return pro.getProperty("CCactivityName");  
	                            }
	                            public String getPrimaryConditione()
	                            {
	                                 return pro.getProperty("PrimaryCondition");  
	                            }
	                            public String getRisk()
	                            {
	                                 return pro.getProperty("Risk");  
	                            }
	                            public String getProvidersection()
	                            {
	                                 return pro.getProperty("Providersection");  
	                            }
	                            public String getCorrespondencesection()
	                            {
	                                 return pro.getProperty("Correspondencesection");  
	                            }

	                            //----------Sandhya - Immunizations sub sections----------//
	                          //================Immunization Subsection=========================//
	                        	public String Editactivity() {
	                        		return pro.getProperty("Editactivity");
	                        	}
	                        	public String AddSubActivity() {
	                        		return pro.getProperty("AddSubActivity");
	                        	}
	                        	public String ImmunizationSubActivity() {
	                        		return pro.getProperty("ImmunizationSubActivity");
	                        	}
	                        	public String AddButtonForImmunization() {
	                        		return pro.getProperty("AddButtonForImmunization");
	                        	}
	                        	public String ImmunizationAccordian() {
	                        		return pro.getProperty("ImmunizationAccordian");
	                        	}
	                        	public String ReasonForRefusalText() {
	                        		return pro.getProperty("ReasonForRefusalText");
	                        	}
	                        	public String AddImmunizationText() {
	                        		return pro.getProperty("AddImmunizationText");
	                        	}
	                        	public String EducationProvidedCheckbox() {
	                        		return pro.getProperty("EducationProvidedCheckbox");
	                        	}
	                        	public String EditImmunization() {
	                        		return pro.getProperty("EditImmunization");
	                        	}
	                        	public String InitailOutreachText() {
	                        		return pro.getProperty("InitailOutreachText");
	                        	}
	                        	public String EditImmunizationText() {
	                        		return pro.getProperty("EditImmunizationText");
	                        	}
	                        	public String ImmunizationPagination() {
	                        		return pro.getProperty("ImmunizationPagination");
	                        	}
	                        
	                    //-----------Author ::UMA-----------//    	
	                        	//---- Add activity---------//
	                        	
	                        	public String ActivityTypeDropdown()
	                        	{
	                        		return pro.getProperty("ActivityTypeDropdown");
	                        	}
	                        	
	                        	
	                        	// Edit activity links in Activity page
	                        	public String QuestionnairesExpandButton()
	                        	{
	                        		return pro.getProperty("QuestionnairesExpandButton");
	                        	}
	                        	
	                        	public String QuestionnairesSaveProgress()
	                        	{
	                        		return pro.getProperty("QuestionnairesSaveProgress");
	                        	}
	                        	
	                        	
	                        	
	                        	
	                        	public String AddQuestionnairesLink()
	                        	{
	                        		return pro.getProperty("AddQuestionnairesLink");
	                        	}
	                        	
	                        	public String EditQuestionnairesButton()
	                        	{
	                        		return pro.getProperty("EditQuestionnairesButton");
	                        	}
	                        	
	                        	public String QuestionnaireNameDropDown()
	                        	{
	                        		return pro.getProperty("QuestionnaireNameDropDown");
	                        	}
	                        	
	                        	
	                        	public String SaveEndActivityButton()
	                        	{
	                        		return pro.getProperty("SaveEndActivityButton");
	                        	}
	                        	
	                        	public String InitialOutreachEditLink()
	                        	{
	                        		return pro.getProperty("InitialOutreachEditLink");
	                        	}
	                        	
	                        	public String ReferralEditLink()
	                        	{
	                        		return pro.getProperty("ReferralEditLink");
	                        	}
	                        	
	                        	public String ConsultationEditLink()
	                        	{
	                        		return pro.getProperty("ConsultationEditLink");
	                        	}
	                        	public String CareCoordinationEditLink()
	                        	{
	                        		return pro.getProperty("CaseCoordinationEditLink");
	                        	}
	                        	public String CaseClosureEditLink()
	                        	{
	                        		return pro.getProperty("CaseClosureEditLink");
	                        	}
	                        	public String HIPPAverfiedText()
	                        	{
	                        		return pro.getProperty("HIPPAverfiedText");
	                        	}

	                        	//------ Clinical Intervention-------//
	                        	
	                        	public String IntroAndPermissionsExpandButton()
	                        	{
	                        		return pro.getProperty("IntroAndPermissionsExpandButton");
	                        	}
	                        	public String YesOptionInDidYouReachTheMember(){
	                        		return pro.getProperty("YesOptionInDidYouReachTheMember");
	                        	}

	                        	public String ErrorMessageUnderFactorsVerifiedCheckBoxes(){
	                        		return pro.getProperty("ErrorMessageUnderFactorsVerifiedCheckBoxes");
	                        	}
	                        	
	                        	public String SaveProgressButton()
	                        	{
	                        		return pro.getProperty("SaveProgressButton");
	                        	}
	                        	
	                        	public String InterventionsGrid()
	                        	{
	                        		return pro.getProperty("InterventionsGrid");
	                        	}
	                        	
	                        	public String QuestionnairesGrid()
	                        	{
	                        		return pro.getProperty("QuestionnairesGrid");
	                        	}
	                        	
	                        	public String HandlingAlertMessageUponSubmission()
	                        	{
	                        		return pro.getProperty("HandlingAlertMessageUponSubmission");
	                        	}
	                        	

	                        	public String FilterButton()
	                        	{
	                        		return pro.getProperty("FilterButton");
	                        	}
	                        	
	                        	public String ClinicalInterventionLink()
	                        	{
	                        		return pro.getProperty("ClinicalInterventionLink");
	                        	}
	                        	
	                        	public String ReasonForUnableToReachText()
	                        	{
	                        		return pro.getProperty("ReasonForUnableToReachText");
	                        	}

	                        	//------- Validating updated Activity data in grid------------//
	                        	
	                        	public String ActivityTypedata()
	                        	{
	                        		return pro.getProperty("ActivityTypedata");
	                        	}
	                        	
	                        	public String StatusData()
	                        	{
	                        		return pro.getProperty("StatusData");
	                        	}
	                        	
	                        	public String StartDateData()
	                        	{
	                        		return pro.getProperty("StartDateData");
	                        	}
	                        	
	                        	public String DueDateData()
	                        	{
	                        		return pro.getProperty("DueDateData");
	                        	}
	                        	
	                        	public String CompletedDateData()
	                        	{
	                        		return pro.getProperty("CompletedDateData");
	                        	}
	                        	
	                        	public String AssignedToData()
	                        	{
	                        		return pro.getProperty("AssignedToData");
	                        	}
	                        	
	                        	
	                        	public String CreatedByData()
	                        	{
	                        		return pro.getProperty("CreatedByData");
	                        	}
	                        	
	                        	
	                        	public String LastUpdatedByData()
	                        	{
	                        		return pro.getProperty("LastUpdatedByData");
	                        	}
	                        	
	                        	//---------- Change History----------------//
	                        	
	                        	public String Dropdown()
	                        	{
	                        		return pro.getProperty("Dropdown");
	                        	}
	                        	
	                        	public String ContactHistoryInDropdown()
	                        	{
	                        		return pro.getProperty("ContactHistoryInDropdown");
	                        	}
	                        	
	                        	//------- Data validation in Contact History Grid--------------//
	                        	
	                        	public String Program()
	                        	{
	                        		return pro.getProperty("Program");
	                        	}
	                        	public String ActivityTypeColumn()
	                        	{
	                        		return pro.getProperty("ActivityTypeColumn");
	                        	}
	                        	public String MemberReached()
	                        	{
	                        		return pro.getProperty("MemberReached");
	                        	}
	                        	public String DateandTimeCompleted()
	                        	{
	                        		return pro.getProperty("DateandTimeCompleted");
	                        	}
	                        	public String PerformedBy()
	                        	{
	                        		return pro.getProperty("PerformedBy");
	                        	}
	                        	public String Role()
	                        	{
	                        		return pro.getProperty("Role");
	                        	}

	                        	//----- Add Activity Page------------//   
	                            
	                            
	                            
	                            public String SubmitButton1(){
	                                return pro.getProperty("SubmitButton1");
	                             } 
	                            
	                            public String SubmitANDAddNewButton()
	                            {
	                                return pro.getProperty("SubmitANDAddNewButton");
	                            } 
	                               
	                            public String IntroPermisionsExpandIcon(){
	                                    return pro.getProperty("IntroPermisionsExpandIcon");   
	                            }   
	                                
	                            public String SaveProgress(){
	                                return pro.getProperty("SaveProgress"); 
	                             }     
	                                
	                            public String AlertText(){
	                                return pro.getProperty("AlertText"); 
	                             }     
	                                
	                                
	                                
	                           
	                            //----- Interventions Grid------//
	                            public String InterventionsGridLabel(){
	                                return pro.getProperty("InterventionsGridLabel");
	                             }  
	                            
	                            public String InterventionDescription1(){
	                                return pro.getProperty("InterventionDescription1");
	                             }
	                            public String LinkedGoalsInGrid(){
	                                return pro.getProperty("LinkedGoalsInGrid");
	                             }
	                            public String CompletedDateInterventionGrid(){
	                                return pro.getProperty("CompletedDateInterventionGrid");
	                             }
	                            public String StatusInGrid(){
	                                return pro.getProperty("StatusInGrid");
	                             }
	                            public String StatusReasonInGrid(){
	                                return pro.getProperty("StatusReasonInGrid");
	                             }
	                            public String CompletedDateInGrid(){
	                                return pro.getProperty("CompletedDateInGrid");
	                             }
	                           public String ValidatingStatusInGrid(){
	                        	   return pro.getProperty("ValidatingStatusInGrid");
	                           }
	                           public String EditInterventionlink_AddActivitypage(){
	                        	   return pro.getProperty("EditInterventionlink_AddActivitypage");
	                           }
	                          
	                           
	                            //--- QuestionnairesGrid-----------//
	                            public String QuestionnairesGridLabel(){
	                                return pro.getProperty("QuestionnairesGridLabel");
	                             } 
	                            public String NameColumn(){
	                                return pro.getProperty("NameColumn");
	                             } 
	                            public String StatusColumn(){
	                                return pro.getProperty("StatusColumn");
	                             } 
	                            public String LastUpdatedDateColumn(){
	                                return pro.getProperty("LastUpdatedDateColumn");
	                             } 
	                            public String LastUpdatedByColumn(){
	                                return pro.getProperty("LastUpdatedByColumn");
	                             } 
	                          //----------------------------Activity Page------------------------------------//
	                            
	                            public String getChangeHistoryXpath1(){
	                               return pro.getProperty("ChangeHistoryXpath");
	                            }
	                            
	                            public String getChangeHistoryInformationDropDown1(){
	                               return pro.getProperty("ChangeHistoryInformationDropDown");
	                            }
	                            
	                            public String getChangeHistoryInformationDropDownValue1(){
	                               return pro.getProperty("ChangeHistoryInformationDropDownValue");
	                            }
	                            
	                            public String getGridHeaderXpath1(){
	                               return pro.getProperty("GridHeaderXpath");
	                            }
	                            public String getGridRecordsXpath1(){
	                               return pro.getProperty("GridRecordsXpath");
	                            }
	                            
	                            public String AddActivityLink(){
	                	               return pro.getProperty("AddActivityLink");
	                	            }
	                            
	                            public String EditActivityLink(){
	                	               return pro.getProperty("EditActivityLink");
	                	            }
	                            
	                            public String ValidatingUpdatedData(){
	                	               return pro.getProperty("ValidatingUpdatedData");
	                	            }
	                            
	                            // Subsections//
	                            
	                          //Add Management Reason subsection
	                            public String getManagemtLeftNav()
	                            {
	                                 return pro.getProperty("MgmtLeftNav");  
	                            }
	                            
	                            public String getMgmtSub()
	                            {
	                                 return pro.getProperty("MgmtSub");  
	                            }
	                            
	                            public String getMgmtsection()
	                            {
	                                 return pro.getProperty("Mgmtsection");  
	                            }
	                            
	                            public String getMgmtOpen()
	                            {
	                                 return pro.getProperty("MgmtOpen");  
	                            }
	                            
	                            public String getAddManagementgmtReason()
	                            {
	                                 return pro.getProperty("AddMgmtReason");  
	                            }
	                            public String getAddMHeader()
	                            {
	                                 return pro.getProperty("AddMHeader");  
	                            }
	                            
	                            public String getMgmtAdded()
	                            {
	                                 return pro.getProperty("MgmtAdded");  
	                            }
	                            
	                            public String getLeftNavReason()
	                            {
	                                 return pro.getProperty("LeftNavReason");  
	                            }
	                            
	                          //----------------------------Questionnarie Page------------------------------------//
                                
	                              
                                public String getMemberActionCenter(){
                                        return pro.getProperty("MemberActionCenter_xpath");
                                     }
                                public String getActivitiess(){
                                        return pro.getProperty("Activities_xpath");
                                     }
                                public String getInitialOutreach_edit(){
                                        return pro.getProperty("InitialOutreach_edit_xpath");
                                     }
                                public String getQuestionnaires(){
                                        return pro.getProperty("Questionnaires_xpath");
                                     }
                                public String getAddQuestionnaires(){
                                        return pro.getProperty("AddQuestionnaires_xpath");
                                     }
                                public String getAddQuestionnaires_dropdown(){
                                        return pro.getProperty("AddQuestionnaires_dropdown_xpath");
                                     }
                                public String getAddQuestionnaire_Save(){
                                        return pro.getProperty("AddQuestionnaire_Save_xpath");
                                     }
                                public String getNewborn(){
                                        return pro.getProperty("Newborn_xpath");
                                     }
                                
                                public String getToodler(){
                                        return pro.getProperty("Toodler_xpath");
                                     }
                                
                                public String getQuestionnaires_History(){
                                        return pro.getProperty("Questionnaires_History_xpath");
                                     }
                                
                                public String getQuestionnaires_Caption(){
                                        return pro.getProperty("Questionnaires_Caption_xpath");
                                     }
                                
                                public String getToddlerAssessment(){
                                        return pro.getProperty("ToddlerAssessment_xpath");
                                     }
                                
                                public String getSaveAsDraft(){
                                        return pro.getProperty("SaveAsDraft_xpath");
                                     }
                                
                                public String getDiscardDraft(){
                                        return pro.getProperty("DiscardDraft_xpath");
                                     }
                                
                                public String getWindowText(){
                                        return pro.getProperty("WindowText_xpath");
                                     }
                                
                                public String getPopUpWindowNo(){
                                        return pro.getProperty("PopUpWindowNo_xpath");
                                     }
                                
                                public String getPopUpWindowYes(){
                                        return pro.getProperty("PopUpWindowYes_xapth");
                                     }
                                
                                public String getNewbornAssessment(){
                                        return pro.getProperty("NewbornAssessment_xpath");
                                     }
                                
                                public String getSubmitasFinal(){
                                        return pro.getProperty("SubmitasFinal_xpath");
                                     }
                                
                              //*********************************************AcitivitySub-Section:Medications(17035)**********************
                            	
                            	public String getAddSubActivity(){
                            		return pro.getProperty("AddSubActivity");
                            	}
                            	public String getMedicationFromAddSubActivity(){
                            		return pro.getProperty("MedicationFromAddSubActivity");
                            	}
                            	public String getAddButtonFromAddSubActivity(){
                            		return pro.getProperty("AddButtonFromAddSubActivity");
                            	}
                            	public String getMedicationsFromSubSection(){
                            		return pro.getProperty("MedicationsFromSubSection");
                            	}
                            	public String getAddMedicationFromGrid(){
                            		return pro.getProperty("AddMedicationFromGrid");
                            	}
                            	public String getMedicationSeachTerm(){
                            		return pro.getProperty("MedicationSeachTerm");
                            	}
                            	public String getClickSearchButtonFromMedication(){
                            		return pro.getProperty("ClickSearchButton");
                            	}
                            	public String getAddCapsuleMedicationCym(){
                            		return pro.getProperty("AddCapsuleMedication");
                            	}
                            	public String getEditMedication(){
                            		return pro.getProperty("EditMedication");
                            	}
                            	public String getViewHistoryButton(){
                            		return pro.getProperty("ViewHistory");
                            	}
                            	public String getCheckBoxMedication(){
                            		return pro.getProperty("CheckBox");
                            	}
                            	public String getExpandButton(){
                            		return pro.getProperty("ExpandButton");
                            	}
                            	public String getReviewButton(){
                            		return pro.getProperty("ReviewButton");
                            	}
                            	public String getExpandHistoryButton(){
                            		return pro.getProperty("ExpandHistoryButton");
                            	}
                            	public String getBackButtonHistory(){
                            		return pro.getProperty("BackButtonHistory");
                            	}
                            	public String getAddCapsuleMedicationSTR(){
                            		return pro.getProperty("AddCapsuleMedicationSTR");
                            	}
                            	public String getQuinidineGluconate(){
                            		return pro.getProperty("QuinidineGluconate");
                            	}
                            	public String getAddZyprexa(){
                            		return pro.getProperty("AddZyprexa");
                            	}
                            	public String getAddVerzenio(){
                            		return pro.getProperty("AddVerzenio");
                            	}
                            	public String getDateAndTime(){
                            		return pro.getProperty("DateAndTime");
                            	}
                            	public String getClickOnFilter(){
                            		return pro.getProperty("ClickOnFilter");
                            	}
                            	public String getFilterBox1(){
                            		return pro.getProperty("FilterBox1");
                            	}
                            	public String getClearBox1(){
                            		return pro.getProperty("ClearBox1");
                            	}
                            	public String getFilterBox2(){
                            		return pro.getProperty("FilterBox2");
                            	}
                            	public String getClearBox2(){
                            		return pro.getProperty("ClearBox2");
                            			
                            		}
                            	public String getFilterBox3(){
                            		return pro.getProperty("FilterBox3");
                            	}
                            	public String getClearBox3(){
                            		return pro.getProperty("ClearBox3");
                            			
                            		}
                            	public String getFilterBox4(){
                            		return pro.getProperty("FilterBox4");
                            	}
                            	public String getClearBox4(){
                            		return pro.getProperty("ClearBox4");
                            			
                            		}
                            	public String getFilterBox5(){
                            		return pro.getProperty("FilterBox5");
                            	}
                            	public String getClearBox5(){
                            		return pro.getProperty("ClearBox5");
                            			
                            		}
                            	public String getAllColumnNames(){
                            		return pro.getProperty("AllColumnNames");
                            	}
                            	public String getColumnName(){
                            		return pro.getProperty("ColumnName");
                            	}
                            	public String getColumnValues(){
                            		return pro.getProperty("ColumnValues");
                            	}
                            	public String getColumnNumber(){
                            		return pro.getProperty("ColumnNumber");
                            	}
                            	public String getColumnSortIcon(){
                            		return pro.getProperty("ColumnSortIcon");
                            	}
                            	public String getAllColumnNamesMedicationHistory(){
                            		return pro.getProperty("AllColumnNamesMedicationHistory");
                            	}
                            	public String getColumnNameMedicationHistory(){
                            		return pro.getProperty("ColumnNameMedicationHistory");
                            	}
                            	public String getColumnValuesMedicationHistory(){
                            		return pro.getProperty("ColumnValuesMedicationHistory");
                            	}
                            	public String getColumnNumberPositionMedicationHistory(){
                            		return pro.getProperty("ColumnNumberPositionMedicationHistory");
                            	}
                            	public String getColumnSortIconMedicationHistory(){
                            		return pro.getProperty("ColumnSortIconMedicationHistory");
                            	}
                            	
                            	 // ------------- Questionnaire Subsection -----------//
                         		
                         		public String getQuestionnaireaccordian()
                         		{
                         			return pro.getProperty("Questionnaireaccordian");
                         			
                         		}
                         		public String getAddQuestionnaire()
                         		{
                         			return pro.getProperty("AddQuestionnaire");
                         			
                         		}
                         		public String getQuestionnaireSaveProgress()
                         		{
                         			return pro.getProperty("QuestionnaireSaveProgress");
                         			
                         		}
                         		public String getQuestionnaireEdit()
                         		{
                         			return pro.getProperty("QuestionnaireEdit");
                         			
                         		}
                         		public String getQuestionnaireName()
                         		{
                         			return pro.getProperty("QuestionnaireName");
                         			
                         		}
                         		public String getQuestionnaireSave()
                         		{
                         			return pro.getProperty("QuestionnaireSave");
                         			
                         		}
                         		public String getQuestionnaireCancel()
                         		{
                         			return pro.getProperty("QuestionnaireCancel");
                         			
                         		}
                         		public String getQuestionnaireColumnsOnGrid()
                         		{
                         			return pro.getProperty("QuestionnaireColumnsOnGrid");
                         			
                         		}
                         		public String getQuestionnaireAppendingCoulmn()
                         		{
                         			return pro.getProperty("QuestionnaireAppendingCoulmn");
                         			
                         		}
                         		public String getQuestionniareHeader()
                         		{
                         			return pro.getProperty("QuestionniareHeader");
                         			
                         		}
                         		public String getQuestionnaireDrop()
                         		{
                         			return pro.getProperty("QuestionnaireDrop");
                         			
                         		}
                         		public String getQuestionnaireDropOptions()
                         		{
                         			return pro.getProperty("QuestionnaireDropOptions");
                         			
                         		}
                         		public String getQuestionnairestatus()
                         		{
                         			return pro.getProperty("Questionnairestatus");
                         			
                         		}
                         		public String getQuestionnaireDelete()
                         		{
                         			return pro.getProperty("QuestionnaireDelete");
                         			
                         		}
                         		
                         		
                                // ------------------- Record a contact subsection---------------------//
                                
                                public String getActivitiesLink() {
                                       return pro.getProperty("ActivitiesLink");
                                }
                                
                                public String getActivitiesLeftNav() 
                                 {
                                              return pro.getProperty("ActivitiesLeftNav");  
                                   }
                              
                                public String getRecContAccordianElementXpath()
                                {
                                       return pro.getProperty("RecordContactAccordianXpath");
                                }

                                
                                public String getRecContacttextfieldsXpath() {
                                       return pro.getProperty("RecContacttextfieldsXpath");
                                }

                                public String getRecContacttextareafieldsXpath() {
                                       return pro.getProperty("RecContacttextareafieldsXpath");
                                }
                                
                                public String getRecContactdropdownfieldsXpath() {
                                       return pro.getProperty("RecContactdropdownfieldsXpath");
                                }
                                 
                                public String getRecContactlabelsXpath() {
                                       return pro.getProperty("RecContactlabelsXpath");
                                }
                                
                                       
                                public String getRecContactdropdownvaluesXpath() {
                                       return pro.getProperty("RecContactdropdownvaluesXpath");
                                }
                                
                                
                                public String getRecCntSectionxpath() {
                                       return pro.getProperty("RecCntSectionxpath");
                                }
                                
                                public String getbtnBackinViewActivity() {
                                       return pro.getProperty("btnBackinViewActivity");
                                }
                                
                                public String getbtnOkinAlert() {
                                       return pro.getProperty("btnOkinAlert");
                                }
                                
                                       
                                public String getlstbxRecContactXpath() {
                                       return pro.getProperty("lstbxRecContactXpath");
                                }
                                
                                 public String getViewActivity(){
                                      return pro.getProperty("lnkViewActivity");
                                   }
                                 
// NIM-17005 ---MemRepresentatives Subsection
                                 
                                 
                                 public String getMemRepresentative_xpath(){
                                     return pro.getProperty("MemRepresentative_xpath");
                                  }   
                                 public String getAddRepresentative(){
                                     return pro.getProperty("AddRepresentative");
                                  }
                                 public String getAddMemReprentativeTitle(){
                                     return pro.getProperty("AddMemReprentativeTitle");
                                  }
                                 public String getMemRepresentativeSave(){
                                     return pro.getProperty("MemRepresentativeSave");
                                  }
                                 public String getActivitystartlabel(){
                                     return pro.getProperty("Activitystartlabel");
                                  }
                                 public String getMemRepresntativeAddPhone(){
                                     return pro.getProperty("MemRepresntativeAddPhone");
                                  }
                                 public String getEditbutton(){
                                     return pro.getProperty("Editbutton");
                                  }
                                 // ------------------- Provider Subsections NIM-17004---------------------//


                                 public String getProvider_xpath() {
                                     return pro.getProperty("Provider_xpath");
                              }
                                 public String getAddProvider_xpath() {
                                     return pro.getProperty("AddProvider_xpath");
                              }
                                
                                 public String getCreateManualProvider() {
                                     return pro.getProperty("CreateManualProvider");
                              }
                                 public String getAddProvider_dropdown_xpath() {
                                     return pro.getProperty("AddProvider_dropdown_xpath");
                              } 
                                 public String getLicensedClinicalProfessional_xpath(){
                                     return pro.getProperty("LicensedClinicalProfessional_xpath");
                                  }
                                 public String getLicensedClinicalSocial_xpath(){
                                     return pro.getProperty("LicensedClinicalSocial_xpath");
                                  }
                                 public String getProviderSpeciality_dropdown(){
                                     return pro.getProperty("ProviderSpeciality_dropdown");
                                  }
                                 public String getPhysicianAssistant_xpath(){
                                     return pro.getProperty("PhysicianAssistant_xpath");
                                  }
                                 public String getLicensedMhCounselor(){
                                     return pro.getProperty("LicensedMhCounselor");
                                  }
                                 public String getProviderSpeciality_dropdownoptions(){
                                     return pro.getProperty("ProviderSpeciality_dropdownoptions");
                                  }
                                 public String getCareteampage(){
                                     return pro.getProperty("Careteampage");
                                  }
                                 public String getDefaultcareteamrole(){
                                     return pro.getProperty("Defaultcareteamrole");
                                  }
                                 public String getInitialoutreachpage(){
                                     return pro.getProperty("Initialoutreachpage");
                                  }
                                 public String getAddProviderTitle(){
                                     return pro.getProperty("AddProvider");
                                  }
                                 public String getEditProvider(){
                                     return pro.getProperty("EditProvider");
                                  }
                                 public String getProviderNametext(){
                                     return pro.getProperty("ProviderNametext");
                                  }
                                 public String getProviderExpand(){
                                     return pro.getProperty("ProviderExpand");
                                  } 
                                 public String getEditprovidertext(){
                                     return pro.getProperty("Editprovidertext");
                                  } 
                                 public String getCancelbutton(){
                                     return pro.getProperty("Cancelbutton");
                                  } 
                                 public String getOrganizationName(){
                                     return pro.getProperty("OrganizationName");
                                  } 
                                 public String getAddcareteamMem(){
                                     return pro.getProperty("AddcareteamMem");
                                  } 
                                 public String getcareteamroledrop(){
                                     return pro.getProperty("careteamroledrop");
                                  } 
                                 public String getcareteamroleprovider(){
                                     return pro.getProperty("careteamroleprovider");
                                  } 
                                 public String getsearchbutton(){
                                     return pro.getProperty("searchbutton");
                                  } 
                                 public String getprovidername(){
                                     return pro.getProperty("providername");
                                  } 
                                 public String getAddproviderbutton(){
                                     return pro.getProperty("Addproviderbutton");
                                  } 
                                 public String getSearchFirst(){
                                     return pro.getProperty("SearchFirst");
                                  } 
                                 public String getSearchLast(){
                                     return pro.getProperty("SearchLast");
                                  }
                                 public String getCareCoordinationPage(){
                                     return pro.getProperty("CareCoordinationPage");
                                  }
                                 public String getEditProviderLabel(){
                                     return pro.getProperty("EditProviderLabel");
                                  }
                                 // ------------------- Record Contact NIM-17018---------------------//
        
                                 public String getPorposeofCallLabel(){
                                     return pro.getProperty("PorposeofCallLabel");
                                  }
                                 public String getCallDirectionLabel(){
                                     return pro.getProperty("CallDirectionLabel");
                                  }
                                 public String getContactTypeLabel(){
                                     return pro.getProperty("ContactTypeLabel");
                                  }
                                 public String getCommTypeLabel(){
                                     return pro.getProperty("CommTypeLabel");
                                  }
                                 public String getContOutcomeLabel(){
                                     return pro.getProperty("ContOutcomeLabel");
                                  }
                                 public String getViewInitialoutreach(){
                                     return pro.getProperty("ViewInitialoutreach");
                                  }
}