package com.nimbus.pages;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GlobalValues {


	public static int Explicit_Wait_time = 20;
	public static int Sleep_wait_time = 3000;
	
	public static String Completed_status = "Completed";
	public static String Open_status = "Not Started";
	public static String Pending_status = "Pending";
	public static String Unassigned_status = "Unassigned";
	public static String StdDateformat = "MM/dd/yyyy";
	
	public static String Case_Number = null;
	public static String Note = "sample note";
	public static String Cancelled_status = "Cancelled";
	public static String SubscriberId = null;
	public static String livingArrangement = null;
	public static String caseId = "1062";
	public static String viewNoteTitle = "View Note";
	public static String caseIdFromTempMember = null;

	public static String Footer_SiteMap = "Site Map";
	public static String Footer_TermsofUse = "Terms Of Use";
	public static String Footer_ProtectedMarks = "Protected Marks";
	public static String Footer_PrivacyPolicy = "Privacy Policy";
	public static String Footer_Image_src_Sit = "https://va33tlvnim301.wellpoint.com:8443/cmproductverisign.png";
	public static String Footer_Image_src_Dev = "https://va33dlvnim301.wellpoint.com:8443/cmproductverisign.png";
	public static String Footer_Image_src_Sandbox = "https://va33dlvnim301.wellpoint.com:9443/cmproductverisign.png";
	public static String Footer_Image_src_Uat = "https://va33tlvnim303.wellpoint.com:8443/cmproductverisign.png";

//	public static String Environment = "SIT";
	public static String DBUserName = "";
	public static String DBPassword = "";
	public static String DBAddress = "";
	public static String DBName = "";
	public static String URL = "";
	public static String DBURL = "";
	public static String DBMemberFirstName = "";
	public static String DBMemberLastName = "";
	public static String DBMemberFullName = "";
	public static String DBMemberId = "";
	public static String DBDOB = "";
	public static String DBMemberId2 = "";
	public static String DBGender = "";
	public static String DBPhoneNumber = "";
	public static String DBCaseOwnerFirstName = "";
	public static String DBCaseOwnerLastName = "";
	public static String DBRiskOrAcuity = "";
	public static String DBProgramName = "";
	public static String DBCaseStatus = "";
	public static String DBEthnicity = "";
	public static String DBPrimaryRsnForMgmt = "";
	public static String DBBestTimeToCall = "";
	public static String DBExpressConsent = "";
	public static String DBCaseOwnerDisplayName = "";
	public static String DBTimeZone = "";
	public static String DBEmail = "";
	public static String DBPreferdCommunicationMethod = "";
	public static String DBMemberIdOfMember = "";
	public static String DBContactPhone = "";
	public static String DBLanguage = "";
	public static String DBNickname = "";
	public static String DBEligibilityStatus = "";
	public static String DBBestDayToCall = "";
	public static String DBFirstNameFromCasesCollection = "";
	public static String DBLastNameFromCasesCollection = "";
	public static String DBProgramNameActiveStatus = "";
	public static String DBProgramNamePrimaryStatus = "";
	
	public static String GeneralMedicalSubmit_Date = null;
	public static String LifestylePreventionSubmit_Date = null;
	public static String UserNameOnUI = null;
//	public static String Environment = "IMPLEMENTATION DEV";
	public static String Environment = "SIT";
//	public static String Environment = "SANDBOX";
//	public static String Environment = "IMPLEMENTATION SIT";
	public static String DBPhoneType = "";
	public static String DBPhoneConsent = "";

	public static List<String> AddedDocumentList = new ArrayList<String>();
	public static List<String> PrintAssessmentList = new ArrayList<String>();

	public static ArrayList<String> Case_History_Grid = new ArrayList<String>();
	public static ArrayList<String> TaskStatus = new ArrayList<String>();
	public static ArrayList<String> CaseStatus = new ArrayList<String>();
	public static String projectPath = System.getProperty("user.dir");
	public static String casehistory = "Case History";

	public static String anthemText = "Anthem";
	// public static String anthemUrl = "";
	public static String floridaMarket = "Florida Market";

	
	public static String Note_Sensitive_Value_Assigned = "";
	public static String Note_Sensitive_Value = "";
	public static String Note_TakenBy = "Alabaster Snowball";
	public static String Note_Type = null;
	public static String Note_Info = null;
	public static String Note_Date_time = null;
	public static String InProgress_status = "In Progress";
	public static String UTC_1 = "UTC-1";
	public static String UTC_2 = "UTC-2";

	public static String[] Task_Location = new String[] { "Orientation Visit", "Annual Visit" };
	public static Map<String, String> mapFilter = new HashMap<String, String>();
	public static ArrayList<String> notesAdded = new ArrayList<String>();

	public static String overview = "Overview";
	public static String tasks = "Tasks";
	public static String assessments = "Assessments/Forms";
	public static String notes = "Notes";
	public static String documents = "Documents";
	public static String admissions = "Admissions";
	
	public static String managementReasons = "Management Reasons";
	public static String generalMedical = "General Medical";
	public static String healthChart = "Health Chart";
	public static String changeHistory= "Change History";
	public static String careTeam= "Care Team";
	public static String lifestyleAndPrevention = "Lifestyle and Prevention";
	public static String carePlan = "Care Plan";
	public static String contactHistory = "Contact History";
	public static String barriers = "Barriers";
	public static String memberActionCenter = "Activities";
	
	public static String medications = "Medications";
	public static String interventions = "Interventions";
	public static String conditions = "Conditions";
	public static String immunizations = "Immunizations";
	public static String activities = "Activities";
	public static String questionnairesHistory = "Questionnnaires History";
	public static String print = "Print";
	
	public static String RequestAuditPackage = "Request Audit Package";
	public static String CaseClosure = "Case Closure Confirmation";
	public static String AddNote = "Add Note";
	public static String DocumentUpload = "Document Upload";
	public static String AddActivity = "Add Activity";
	public static String ContactDetails = "Contact Details";
	
	//Document
    public static String UploadFiles = "Upload Files";
    public static String UploadedDocuments = "Uploaded Documents";
    public static String DocumentName_Grid = "Document Name";
    public static String Description_Grid = "Description";
    public static String UploadedDate_Grid = "Uploaded Date";
    public static String AttachedTo_Grid = "Attached To";
    public static String AddedBy_Grid = "Added By";
    public static String DocumentID_Grid = "Document ID";
    public static String BarriersGrid_Description = "Description";
    public static String BarriersGrid_LinkedGoal = "Linked Goal";
    public static String BarriersGrid_IdentifiedDate = "Identified Date";
    public static String BarriersGrid_ResolvedDate = "Resolved Date";
    public static String BarriersGrid_Status = "Status";
    public static String BarriersGrid_StatusReason = "Status Reason";
    
    public static String editMedication="";
	public static String viewHistory="";
    
   
    
    
   //Client Admin
    public static ArrayList<String> ClientAdminGlobalNav = new ArrayList<String>(Arrays.asList("Organization Management","TeamLibrary Management","User and Role Management","User Notification","Program Management","Template Management"));
    
    public static ArrayList<String> UserRolesGrid = new ArrayList<String>(Arrays.asList("Display Name","Login ID","First Name","Last Name","Role","Supervisor Name","Status","Effective Date","Termination Date"));
  //care plan and interventions
    
   //CarePlan   
      public static ArrayList<String> LinksOnCarePlanPage = new ArrayList<String>(Arrays.asList("Add Barrier","Add Intervention","Add Goal"));
      
      public static ArrayList<String> CarePlanGrid = new ArrayList<String>(Arrays.asList("Priority","Goal","Program","Status","Status Reason","Start Date","Target End Date","Closed Date"));
      
      public static ArrayList<String> CarePlanPriorityValues = new ArrayList<String>(Arrays.asList("High","Medium","Low"));
      
      public static ArrayList<String> CarePlanStatusDropdown = new ArrayList<String>(Arrays.asList("Not Started","In Progress","Closed"));
      
      public static ArrayList<String> FilterPage_fields = new ArrayList<String>(Arrays.asList(" Priority "," Target Due Date "," Status "));
      
      public static ArrayList<String> StatusReasonClosedValues = new ArrayList<String>(Arrays.asList("Met","Partially Met","Not Compliant","Unable to Contact","Member Deceased","In Eligible","Not Clinically Appropriate","Member / Caregiver Refuses","Referred to Another Program"));

      public static ArrayList<String> SecondaryRowInterventionsGrid = new ArrayList<String>(Arrays.asList("Description","Status","Status Reason","Assigned To","Start Date","Closed Date"));
      
      public static ArrayList<String> CarePlanHistoryGrid = new ArrayList<String>(Arrays.asList("Goal","Target End Date","Reason for Extension","Updated by User","Updated Date","Priority"));
   
     

    //Interventions  
        public static ArrayList<String> InterventionsGrid = new ArrayList<String>(Arrays.asList("Description","Linked Goals","Assigned To","Status","Status Reason","Closed Date"));
        
        public static ArrayList<String> Intervention_GoalsGrid = new ArrayList<String>(Arrays.asList("Goal","Program","Status","Status Reason","Start Date","Due Date"));
        
        public static ArrayList<String> TypedropdownValues = new ArrayList<String>(Arrays.asList("Behavioral","Medical","Rehab","Skilled Nursing Facility","NICU"));

        public static ArrayList<String> EditIntervention = new ArrayList<String>(Arrays.asList("Associated Goal","Intervention","Assigned To","Status","Status Reason"));
        
        public static ArrayList<String> HistoryIntervention = new ArrayList<String>(Arrays.asList("Intervention","Assigned To","Status","Status Reason","Updated by User","Updated Date"));

        //activity sub sections
        public static List<String> Initialoutreach_DefaultSubsections = new ArrayList<String>(Arrays.asList("Intro & Permissions","Member Representatives", "Provider", "Questionnaire", "Care Plan", "Record Contact"));
        public static List<String> Initialoutreach_AllSubsections = new ArrayList<String>(Arrays.asList("Intro & Permissions","Case Information","Member Representatives", "Provider", "Questionnaire", "Care Plan","Management Reason","Admissions","Conditions","General Medical","Immunizations","Lifestyle & Prevention","Medications","Correspondence","Print","Record Contact"));
        public static List<String> ClinicalIntervention_DefaultSubsections = new ArrayList<String>(Arrays.asList("Intro & Permissions","Member Representatives","Questionnaire", "Care Plan", "Record Contact"));
        public static List<String> ClinicalIntervention_AllSubsections = new ArrayList<String>(Arrays.asList("Intro & Permissions","Case Information","Preferred Contact Information","Member Representatives", "Provider", "Questionnaire", "Care Plan","Management Reason","Admissions","Conditions","General Medical","Immunizations","Lifestyle & Prevention","Medications","Correspondence","Print","Record Contact"));
        
        public static List<String> CaseClosure_DefaultSubsections = new ArrayList<String>(Arrays.asList("Intro & Permissions","Member Representatives","Questionnaire", "Care Plan","Management Reason", "Record Contact"));
        public static List<String> CaseClosure_AllSubsections = new ArrayList<String>(Arrays.asList("Intro & Permissions","Case Information","Preferred Contact Information","Member Representatives", "Provider", "Questionnaire", "Care Plan","Management Reason","Admissions","Conditions","General Medical","Immunizations","Lifestyle & Prevention","Medications","Correspondence","Print","Record Contact"));
       
        public static List<String> Referral_DefaultSubsections = new ArrayList<String>(Arrays.asList("Intro & Permissions","Member Representatives","Care Plan", "Record Contact"));
        public static List<String> Referral_AllSubsections = new ArrayList<String>(Arrays.asList("Intro & Permissions","Case Information","Preferred Contact Information","Member Representatives", "Provider", "Questionnaire", "Care Plan","Management Reason","Admissions","Conditions","General Medical","Immunizations","Lifestyle & Prevention","Medications","Correspondence","Print","Record Contact"));
       
        public static List<String> Consultation_DefaultSubsections = new ArrayList<String>(Arrays.asList("Intro & Permissions","Member Representatives","Questionnaire","Care Plan", "Record Contact"));
        public static List<String> Consultation_AllSubsections = new ArrayList<String>(Arrays.asList("Intro & Permissions","Case Information","Preferred Contact Information","Member Representatives", "Provider", "Questionnaire", "Care Plan","Management Reason","Admissions","Conditions","General Medical","Immunizations","Lifestyle & Prevention","Medications","Correspondence","Print","Record Contact"));
        
        public static List<String> CareCoordination_DefaultSubsections = new ArrayList<String>(Arrays.asList("Intro & Permissions","Member Representatives","Provider","Questionnaire","Care Plan","Correspondence","Record Contact"));
        public static List<String> CareCoordination_AllSubsections = new ArrayList<String>(Arrays.asList("Intro & Permissions","Case Information","Preferred Contact Information","Member Representatives", "Provider", "Questionnaire", "Care Plan","Management Reason","Admissions","Conditions","General Medical","Immunizations","Lifestyle & Prevention","Medications","Correspondence","Print","Record Contact"));
        
        public static List<String> CaseReview_DefaultSubsections = new ArrayList<String>(Arrays.asList("Case Information","Member Representatives","Questionnaire","Care Plan","Management Reason","Conditions","Medications", "Record Contact","Correspondence","Print"));
        public static List<String> CaseReview_AllSubsections = new ArrayList<String>(Arrays.asList("Case Information","Preferred Contact Information","Member Representatives", "Provider", "Questionnaire", "Care Plan","Management Reason","Admissions","Conditions","General Medical","Immunizations","Lifestyle & Prevention","Medications","Correspondence","Print","Record Contact"));
        
        //Activity Type
    public static ArrayList<String> ActivityType = new ArrayList<String>(Arrays.asList("Initial Outreach","Clinical Intervention","Case Closure","Referral","Consultation","Care Coordination","Case Review"));
   
    public static ArrayList<String> InitialOutreachDefaultSubActivities = new ArrayList<String>(Arrays.asList("Intro & Permissions","Preferred Contact Information","Member Representatives","Provider","Questionnaires","Care Plan","Record Contact"));
    public static ArrayList<String> InitialOutreachUserAddedSubActivities = new ArrayList<String>(Arrays.asList("Medications","Immunization","Conditions","Admissions","Lifestyle & Prevention","General Medical"));

    public static ArrayList<String> CareCoordinationDefaultSubActivities = new ArrayList<String>(Arrays.asList("Intro & Permissions","Member Representatives","Provider","Questionnaires","Care Plan","Record Contact","Correspondence"));
    public static ArrayList<String> CareCoordinationUserAddedSubActivities = new ArrayList<String>(Arrays.asList("Conditions","Admissions","General Medical","Immunization"));

    public static ArrayList<String> CaseClosureDefaultSubActivities = new ArrayList<String>(Arrays.asList("Intro & Permissions","Member Representatives","Questionnaires","Care Plan","Record Contact","Management Reason"));
    public static ArrayList<String> CaseClosureUserAddedSubActivities = new ArrayList<String>(Arrays.asList("Medications","Immunization","Conditions","Admissions","Lifestyle & Prevention","General Medical"));

    public static ArrayList<String> ClinicalInterventionDefaultSubActivities = new ArrayList<String>(Arrays.asList("Intro & Permissions","Member Representatives","Questionnaires","Care Plan","Record Contact"));
    public static ArrayList<String> ClinicalInterventionUserAddedSubActivities = new ArrayList<String>(Arrays.asList("Conditions","Admissions","General Medical","Immunization"));

   
    public static ArrayList<String> ReferralDefaultSubActivities = new ArrayList<String>(Arrays.asList("Intro & Permissions","Member Representatives","Questionnaires","Care Plan","Record Contact"));
    public static ArrayList<String> ReferralUserAddedSubActivities = new ArrayList<String>(Arrays.asList("Conditions","Admissions","General Medical","Immunization"));


    public static ArrayList<String> CaseReviewDefaultSubActivities = new ArrayList<String>(Arrays.asList("Member Representatives","Questionnaires","Care Plan","Conditions","Correspondence","Management Reason","Case Information","Print"));
    public static ArrayList<String> CaseReviewUserAddedSubActivities = new ArrayList<String>(Arrays.asList("Immunization"));

//documents
public static ArrayList<String> DocumentsGrid = new ArrayList<String>(Arrays.asList("Document Name","Description","Uploaded Date","Attached To","Added By","Added By"));

//Barriers

public static ArrayList<String> BarriersPageGrid = new ArrayList<String>(Arrays.asList("Description","Linked Goal","Identified Date","Resolved Date","Status","Status Reason",""));

public static ArrayList<String> NotesGridOnBarrierGrid = new ArrayList<String>(Arrays.asList("Description","Taken Date","Taken By"));

//Member Uodate History
public static List<String> MemberUpdateHistoryGrid = new ArrayList<String>(Arrays.asList("Data Attribute","Updated Entry","Updated Date & Time" ,"Updated By"));


	// public static ArrayList<String> buttonsOnHomePage = new
	// ArrayList<String>(Arrays.asList("Member Search","Recently Accessed
	// Cases"));
	public static List<String> Case_Manger = new ArrayList<String>(
			Arrays.asList("Subscriber ID", "Last Name", "First Name", "Next Task", "Next Task Due", "Next Task Status",
					"Next Quarterly", "Next Annual", "LOC Date", "County", "Facility Name", "Address"));

	public static List<String> Case_Sup_CaseLoad = new ArrayList<String>(
			Arrays.asList("Last Name", "First Name", "Caseload", "Past Due Tasks", "Tasks Due in 2 Weeks"));

	public static List<String> Case_Sup_MemberLoad = new ArrayList<String>(
			Arrays.asList("Case Owner", "Subscriber ID", "Member Effective Date", "Member Last Name",
					"Member First Name", "Living Arrangement", "County", "City", "Facility Name", "Address"));

	public static List<String> Case_Sup_MyMembers = new ArrayList<String>(
			Arrays.asList("Subscriber ID", "Last Name", "First Name", "Next Task", "Next Task Due", "Next Task Status",
					"Next Quarterly", "Next Annual", "LOC Date", "County", "Facility Name", "Address"));

	public static ArrayList<String> PocGrid = new ArrayList<String>(
			Arrays.asList("Name", "Status", "Task", "Last Updated", "Last Updated By"));

	public static ArrayList<String> MemberBanner = new ArrayList<String>(
			Arrays.asList("DOB", "Age", "Gender", "Non-Member Premission", "Preferred Phone #", "Member Contact Time",
					"Case ID", "Program Name", "Co-Managed", "Case Owner", "Case Status", "Speciality Products"));

	public static ArrayList<String> ManagementReasonsGrid = new ArrayList<String>(Arrays.asList("Primary","Program Type",
			"Reason", "Status", "Status Reason", "Priority", "Date Identified", "Date Re-identified", "Date Closed"));

	public static ArrayList<String> SupplementalInformationGrid = new ArrayList<String>(
			Arrays.asList("Note Date", "Taken By", "Note Type", "Note Description"));

	public static ArrayList<String> NotesGrid = new ArrayList<String>(
			Arrays.asList("", "Note Type", "Date Entered", "Entered By", "Note Details"));

	public static ArrayList<String> ManagementReasonGrid = new ArrayList<String>(Arrays.asList("Program Type", "Reason",
			"Status", "Status Reason", "Priority", "Date Identified", "Date Re-identified", "Date Closed"));

	public static ArrayList<String> AuthRepGridFromOverview = new ArrayList<String>(
			Arrays.asList("Name","Relationship to Member", "Phone Number","Permission To Share"));

	public static List<String> ActivityColumns = new ArrayList<String>(Arrays.asList("Activity Type", "Status",
			"Start Date", "Due Date", "Completed Date", "Assigned To", "Created By", "Last Updated By"));

	public static List<String> Immunizationgrid_headers = new ArrayList<String>(
			Arrays.asList("Immunization Name", "Date Administered", "Provider/Facility", "Entered By",
					"Date & Time Entered", "Reason for Refusal", "Education Provided"));

	public static ArrayList<String> ImmunizationGrid = new ArrayList<String>(
			Arrays.asList("Immunization Name", "Date Administered", "Provider/Facility"));

	public static ArrayList<String> ImmunizationUpdateGrid = new ArrayList<String>(Arrays.asList("Date Administered"));

	public static ArrayList<String> buttonsOnHomePage = new ArrayList<String>(
			Arrays.asList("Member Search", "Recently Accessed Cases"));

	public static ArrayList<String> AdmissionsGrid = new ArrayList<String>(Arrays.asList("Type", "Admit Date",
			"Days in Hospital", "Provider Name", "Actual Discharge Date", "Post-Discharge Completion Date"));

	public static ArrayList<String> AdmissionsSecondRow = new ArrayList<String>(
			Arrays.asList("Notification Date", "Pre-Admission Completion Date", "Discharge Notification Date",
					"Planned Discharge Date", "Provider Group/Facility Name", "Provider Phone"));

	public static ArrayList<String> linksOnMedicationPage = new ArrayList<String>(
			Arrays.asList("Add Medication"));

	public static ArrayList<String> MedicationGrid_Current = new ArrayList<String>(Arrays.asList("Reviewed Date",
			"Drug Name", "Drug Class", "Status", "Adherence", "Frequency", "Special Instructions"));

	public static ArrayList<String> MedicationGrid_All = new ArrayList<String>(Arrays.asList("Reviewed Date",
			"Drug Name", "Drug Class", "Status", "Adherence", "Frequency", "Special Instructions", "Termed Date"));

	public static ArrayList<String> AddMedicationSearchResults = new ArrayList<String>(
			Arrays.asList("Drug Name", "Label Name", "NDC"));

	public static ArrayList<String> MedicationHistoryGrid = new ArrayList<String>(Arrays.asList("Drug Name",
			"Label Name", "RX #", "Prescribing Provider", "Date Filled", "Pharmacy Name", "Strength"));

	

	public static ArrayList<String> AdmissionsSecondaryRowLabels = new ArrayList<String>(
			Arrays.asList(" Notification Date ", " Pre-Admission Completion Date ", " Discharge Notification Date ",
					" Planned Discharge Date ", " Provider Group/Facility Name ", " Provider Phone "));

	public static ArrayList<String> MedicationsSecondaryRowLabels = new ArrayList<String>(
			Arrays.asList("Rx #", "Date Filled", "Strength", "Doses/Day", "Days Supply", "Quantity", "Source",
					"Label Name", "Pharmacy Name", "Prescribing Provider"));

	public static ArrayList<String> MedicationsHistorySecondaryrowLabels = new ArrayList<String>(
			Arrays.asList("Adherence", "Quantity", "Source", "Status", "Frequency", "Special Instructions",
					"Date Reviewed", "Modified By", "Date Modified"));
	
	public static List<String> ReferralHistoryGrid = new ArrayList<String>(Arrays.asList("Date","Owner","Today's Action Taken" ,"Next Action Proposed", "Next Action Due Date"));

	
	public static ArrayList<String> CaseReviewAllSubActivities = new ArrayList<String>(Arrays.asList("Case Information","Preferred Contact Information","Member Representatives","Provider","Questionnaires","Care Plan","Conditions","Correspondence",
    		"Management Reason","Admissions","Conditions","General Medical","Immunization","Lifestyle & Prevention","Medications","Correspondence","Print","Record Contact"));

    public static ArrayList<String> QuestionairesGrid = new ArrayList<String>(
			Arrays.asList("Name", "Status","Last Updated Date", "Updated By","Score","Result / Recommendation"));

	public static ArrayList<String> MedicationsGrid = new ArrayList<String>(
			Arrays.asList("Reviewed Date", "Drug Name","Drug Class", "Status","Adherence","Frequency","Special Instructions"));
	
	public static ArrayList<String> GoalsGrid = new ArrayList<String>(
			Arrays.asList("Goal", "Program","Status", "Status Reason","Start Date","Due Date"));
	
	
	
	public static ArrayList<String> ConditionsGrid = new ArrayList<String>(
			Arrays.asList("Primary", "Condition","Diagnosis/Onset Date", "Clinical Status","Resolved Date","Verification Status","Risk","Source"));
	public static ArrayList<String> CaseInformationGrid = new ArrayList<String>(Arrays.asList("Program",
			"Case ID", "Case Status", "Status Reason", "Case Status Date", "Enrollment Status", 
			"Enrollment Date", "Risk/Acuity"));
	
	public static ArrayList<String> MemberRepresentativesGrid = new ArrayList<String>(
			Arrays.asList("Name", "Relationship","Phone Number", "Permission to Share"));
	
	public static ArrayList<String> InterventionbarrierGrid = new ArrayList<String>(
			Arrays.asList("Description", "Linked Goals", "Assigned To", "Status", "Status Reason"));
	
	//Added for Referral
	    public static ArrayList<String> ReferralFields = new ArrayList<String>(Arrays.asList("Referral Type","Referral Reason","Today's Action Taken","Next Action Proposed","Next Action Due Date","Note"));
	    
	    public static ArrayList<String> Notegrid = new ArrayList<String>(Arrays.asList("Description"));
	    
	    public static List<String> NoteGridColumn = new ArrayList<String>(Arrays.asList("Description","Taken Date","Taken By"));
	    

//added for case closure
    
    public static String caseclosurequestion="Are you sure you want to close this case? Upon clicking “Yes” the case will be closed.";
//added for record contact
    
    public static List<String> RecordContactPorposeofCallOptions = new ArrayList<String>(Arrays.asList("Initial Outreach","Clinical Intervention", "Consult", "Referral Work", "Case Closure", "Case Review","Provider Outreach"));
    public static List<String> RecordContactCallDirectionOptions = new ArrayList<String>(Arrays.asList("Incoming","Outgoing", "Not Applicable"));
    public static List<String> RecordContactContactTypeOptions = new ArrayList<String>(Arrays.asList("Caregiver","Facility","Community Resource", "Guardian","Member","Member Representative","Parent","Provider"));
    public static List<String> RecordContactCommunicationTypeOptions = new ArrayList<String>(Arrays.asList("Chat","Dialer","E-Mail", "Face-to-Face","Fax","Letter","Link w/Client System (Pre-Cert, Membership)","Onsite Visit","Telephone","Telephone-Language Line","Telephone-TTY/TDD","Video Chat","Not Applicable"));
    public static List<String> RecordContactContactOutcomeOptions = new ArrayList<String>(Arrays.asList("Busy","Call Rescheduled","Consultation", "Disconnected","Follow Up Call Completed","Immediate Hang-up","Initial Call Completed","Invalid Phone Number","Left Voicemail","Member Expired","Member Termed","Message Left with Non-Member","No Action Taken","No Answer","Not Applicable","Unsuccessful Transfer","Other"));
    
    
    //added for Record Contact Sub-Activity
    public static ArrayList<String> RecordContactActivityType = new ArrayList<String>(Arrays.asList("Initial Outreach","Clinical Intervention","Referral","Care Coordination"));
    public static ArrayList<String> RecordContactSectionfields = new ArrayList<String>(Arrays.asList("Purpose of Call","Call Direction","Contact Type","Contact Name","Contact Phone","Communication Type","Contact Outcome","Member to Accomplish Prior to Next Contact"));  
    
    public static List<String> QuestionnaireColumns = new ArrayList<String>(Arrays.asList("Questionnaire Name","Status", "Last Updated Date", "Last Updated By"));

    //Admissions and complexity level
    
    //Admissions
    
//    public static ArrayList<String> AdmissionsGrid = new ArrayList<String>(Arrays.asList("Type","Admit Date","Days in Hospital","Provider Name","Actual Discharge Date","Post-Discharge Completion Date"));
//    
//    public static ArrayList<String> AdmissionsSecondRow = new ArrayList<String>(Arrays.asList("Notification Date","Pre-Admission Completion Date","Discharge Notification Date","Planned Discharge Date","Provider Group/Facility Name","Provider Phone"));
//    
//    public static ArrayList<String> AdmissionsSecondaryRowLabels = new ArrayList<String>(Arrays.asList("Notification Date","Pre-Admission Completion Date","Discharge Notification Date","Planned Discharge Date","Provider Group/Facility Name","Provider Phone"));
     //ComplexityLevel   
    
    public static ArrayList<String> ComplexityWindowlabels = new ArrayList<String>(Arrays.asList("Risk/Acuity","Reason For Change"));
    
    public static ArrayList<String> ComplexityLevelValues = new ArrayList<String>(Arrays.asList("Low","Moderate","High"));
    
    public static ArrayList<String> ReasonForChangelValues = new ArrayList<String>(Arrays.asList("Increase Case Management Needs","Decrease Case Management Needs","Move to Care Coordination"));
    
    public static ArrayList<String> ComplexityLevelHistoryGrid = new ArrayList<String>(Arrays.asList("Program","Risk/Acuity Level","Reason for Change","Date and Time Completed","Performed By"));

    public static ArrayList<String> GeneralHealthDOSHistoryGrid = new ArrayList<String>(Arrays.asList("Answer","Date Entered","Entered By","Date of Service","Program","Source")); 

    public static ArrayList<String> GeneralHealthNoDOSHistoryGrid = new ArrayList<String>(Arrays.asList("Answer","Date Entered","Entered By","Program","Source"));
	
  //provider title options
    public static List<String> ProviderTitleOptions = new ArrayList<String>(Arrays.asList("Licensed Clinical Professional Counselor","Licensed Clinical Social Worker", "Physician's Assistant"));
    
    //Record contact
    
//    public static List<String> RecordContactPorposeofCallOptions = new ArrayList<String>(Arrays.asList("Initial Outreach","Clinical Intervention", "Consult", "Referral Work", "Case Closure", "Case Review","Provider Outreach"));
//    public static List<String> RecordContactCallDirectionOptions = new ArrayList<String>(Arrays.asList("Incoming","Outgoing", "Not Applicable"));
//    public static List<String> RecordContactContactTypeOptions = new ArrayList<String>(Arrays.asList("Caregiver","Facility","Community Resource", "Guardian","Member","Member Representative","Parent","Provider"));
//    public static List<String> RecordContactCommunicationTypeOptions = new ArrayList<String>(Arrays.asList("Chat","Dialer","E-Mail", "Face-to-Face","Fax","Letter","Link w/Client System (Pre-Cert, Membership)","Onsite Visit","Telephone","Telephone-Language Line","Telephone-TTY/TDD","Video Chat","Not Applicable"));
//    public static List<String> RecordContactContactOutcomeOptions = new ArrayList<String>(Arrays.asList("Busy","Call Rescheduled","Consultation", "Disconnected","Follow Up Call Completed","Immediate Hang-up","Initial Call Completed","Invalid Phone Number","Left Voicemail","Member Expired","Member Termed","Message Left with Non-Member","No Action Taken","No Answer","Not Applicable","Unsuccessful Transfer","Other"));
    
    
    public static long dateValidation(String currentDate1, String startDateordueDate) {
		String resDate = startDateordueDate;

		String currentDate = currentDate1;
		SimpleDateFormat myformat = new SimpleDateFormat(StdDateformat);
		TimeZone obj = TimeZone.getTimeZone("EST");
		long days = 0;
		try {
			myformat.setTimeZone(obj);
			Date curdate1 = myformat.parse(currentDate);
			Date date2 = myformat.parse(resDate);
			long diff = date2.getTime() - curdate1.getTime();
			days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
			System.out.println(days);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return days;
	}

	public static String AddDate(String currentDate) throws ParseException {

		DateFormat dateFormat = new SimpleDateFormat(GlobalValues.StdDateformat.toString());
		Date date1 = new SimpleDateFormat(GlobalValues.StdDateformat.toString()).parse(currentDate);
		Calendar c = Calendar.getInstance();
		c.setTime(date1);
		c.add(Calendar.DATE, 15);
		date1 = c.getTime();
		currentDate = dateFormat.format(date1);

		return currentDate;

	}

	public static String GetCurrentDateTime() {
		LocalDate localDate = LocalDate.now();
		String onlyDate = DateTimeFormatter.ofPattern("MM/dd/yyyy").format(localDate);
		return onlyDate;
	}

	// public static int CalculateAgeFromSysDateNDob()throws ParseException{

	// String dateStr = GlobalValues.DBDOB;
	// DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
	// Date date = (Date)formatter.parse(dateStr);
	//
	//
	// int age = (Integer.parseInt(GlobalValues.GetCurrentDateTime()) - date);
	// return age;

	// }

	public static int calculateAge(Date birthDate) {
		int years = 0;
		int months = 0;
		int days = 0;
		// create calendar object for birth day
		Calendar birthDay = Calendar.getInstance();
		birthDay.setTimeInMillis(birthDate.getTime());
		// create calendar object for current day
		long currentTime = System.currentTimeMillis();
		Calendar now = Calendar.getInstance();
		now.setTimeInMillis(currentTime);
		// Get difference between years
		years = now.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
		int currMonth = now.get(Calendar.MONTH) + 1;
		int birthMonth = birthDay.get(Calendar.MONTH) + 1;
		// Get difference between months
		months = currMonth - birthMonth;
		// if month difference is in negative then reduce years by one and
		// calculate the number of months.
		if (months < 0) {
			years--;
			months = 12 - birthMonth + currMonth;
			if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
				months--;
		} else if (months == 0 && now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
			years--;
			months = 11;
		}
		// Calculate the days
		if (now.get(Calendar.DATE) > birthDay.get(Calendar.DATE))
			days = now.get(Calendar.DATE) - birthDay.get(Calendar.DATE);
		else if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
			int today = now.get(Calendar.DAY_OF_MONTH);
			now.add(Calendar.MONTH, -1);
			days = now.getActualMaximum(Calendar.DAY_OF_MONTH) - birthDay.get(Calendar.DAY_OF_MONTH) + today;
		} else {
			days = 0;
			if (months == 12) {
				years++;
				months = 0;
			}
		}
		// Create new Age object
		return years;
	}

	// Place holder for all common Message , Variables etc
}
