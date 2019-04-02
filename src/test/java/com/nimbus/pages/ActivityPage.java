package com.nimbus.pages;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import excelReader.DataReaderExcel;
import utility.ConfigReader;
import utility.WebActions;

public class ActivityPage {

	WebDriver driver;
	Logger Log;

	ConfigReader config = new ConfigReader();
	DataReaderExcel DataRead = new DataReaderExcel();

	public ActivityPage(WebDriver ldriver) {
		this.driver = ldriver;

	}

	public ActivityPage(Logger lLog) {
		this.Log = lLog;

	}

	public void addActivityFromActivitiesPage() throws InterruptedException {
		Log = Logger.getLogger("ActivityPage.class");
		PropertyConfigurator.configure("log4j.properties");
		AnnualAssessment a = new AnnualAssessment(driver);
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		Global_Method gm = new Global_Method(driver);
		LeftNavigationLink lnk = new LeftNavigationLink(driver);

		try {
			List<String> activityTypeExistingList = new ArrayList<String>() ;
			Log.info("Get the list of activities on the grid");
			List<WebElement> activityTypeList = driver.findElements(By.xpath("//tbody//td[1]/span"));
			int activityTypeListSize = activityTypeList.size();
			if(activityTypeListSize>0){
				for(WebElement activityTypeElement: activityTypeList){
					Log.info(activityTypeExistingList.add(activityTypeElement.getText().trim()));
				}
			}
			
			Log.info("user clicks on Add Activity from Activities page of Member Action Center");
			WebElement addActivityElement = driver.findElement(By.xpath("//button[contains(text(),'Add Activity')]"));
			addActivityElement.click();
			Thread.sleep(GlobalValues.Sleep_wait_time);
			a.annual_assessment("AnnualAssessment_CMDM_AuthRep", "AddActivity", 2);
			Thread.sleep(GlobalValues.Sleep_wait_time);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void editInitialOutreachActivity(String TaskName) throws InterruptedException {
		Log = Logger.getLogger("ActivityPage.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		Global_Method gm = new Global_Method(driver);
		LeftNavigationLink lnk = new LeftNavigationLink(driver);
		
		

		if(TaskName == "Initial Outreach" )
		{
		//Initial Outreach
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getEditIntialoutreach()))).click(); 
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
		Thread.sleep(1000);
		Log.info("Clicked on Edit of Initial Outreach Element");
		}
		if(TaskName == "Clinical Intervention")
		{
		//Clinical Intervention
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@title = 'Clinical Intervention']//parent::td//following-sibling::td//button[@title='Edit']"))).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
		Thread.sleep(1000);
		Log.info("Clicked on Edit Clinical Intervention");
		}
		if(TaskName == "Case Closure" )
		{
		//Case Closure
			WebElement editCaseClosure = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getEditCaseClosure1())));
			editCaseClosure.click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
			Thread.sleep(1000);
			Log.info("Clicked on Edit Case Closure");
		}
		if(TaskName == "Referral" )
		{
			Log.info("Click on Edit Referral activity");
			WebElement editreferral = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getEditReferral())));
			editreferral.click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
			Thread.sleep(1000);
			Log.info("Clicked on Edit Referral");
		}
		if(TaskName == "Consultation" )
		{
			Log.info("Click on Edit Consultation activity");
			WebElement editConsultation = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getEditConsultation())));
			editConsultation.click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
			Thread.sleep(1000);
			Log.info("Clicked on Edit Consultation");
		}
		if(TaskName == "Care Coordination" )
		{
			Log.info("Click on Edit care coordination activity");
			WebElement editCareCoordination = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getEditCareCoordination())));
			editCareCoordination.click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
			Thread.sleep(1000);
			Log.info("Clicked on Edit Care Coordination");
		}
		if(TaskName == "Case Review" )
		{
			Log.info("Click on Edit care coordination activity");
			WebElement editCareCoordination = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getEditCareCoordination())));
			editCareCoordination.click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
			Thread.sleep(1000);
			Log.info("Clicked on Edit Case Review");
		}
	
		Thread.sleep(GlobalValues.Sleep_wait_time);
		Thread.sleep(GlobalValues.Sleep_wait_time);
	
	}

	public void verifyChangeHistory() {
		Log = Logger.getLogger("ActivityPage.class");
		PropertyConfigurator.configure("log4j.properties");
		Log.info("user click on change history");

		driver.findElement(By.xpath(config.getChangeHistoryXpath())).click();

		Log.info("checking for Activity Status History");
		selectActivityDropDown("Activity Status History");
		if (driver.findElements(By.xpath(config.getGridRecordsXpath())).size() > 0) {
			Log.info("Record found for Activity Status History");
		} else {
			Log.info("No record found for Activity Status History");
		}

		Assert.assertTrue(verifyHeader("Program"), "Program header is not found for Activity Status History");
		Assert.assertTrue(verifyHeader("Activity Type"),
				"Activity Type header is not found for Activity Status History");

		Log.info("checking for Case Status History");
		selectActivityDropDown("Case Status History");
		if (driver.findElements(By.xpath(config.getGridRecordsXpath())).size() > 0) {
			Log.info("Record found for Case Status History");
		} else {
			Log.info("No record found for Case Status History");
		}
		Assert.assertTrue(verifyHeader("Program"), "Program header is not found for Case Status History");
		Assert.assertTrue(verifyHeader("Case Status"), "Case Status is not found for Case Status History");

		Log.info("cheching for Contact History");
		selectActivityDropDown("Contact History");
		if (driver.findElements(By.xpath(config.getGridRecordsXpath())).size() > 0) {
			Log.info("Record found for Contact History");
		} else {
			Log.info("No record found for Contact History");
		}
		Assert.assertTrue(verifyHeader("Program"), "Program header is not found for Case Status History");
		Assert.assertTrue(verifyHeader("Activity Type"), "Case Status is not found for Case Status History");

	}

	public void verifyActivityStatusHistory() throws InterruptedException {
		Log = Logger.getLogger("ActivityPage.class");
		PropertyConfigurator.configure("log4j.properties");
		Thread.sleep(5000);
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		wait.until(
				ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(config.getChangeHistoryXpath()))))
				.click();
		Thread.sleep(2000);
		wait.until(
				ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(config.getChangeHistoryXpath()))))
				.click();
		Log.info("user click on change history");

		Log.info("checking for Activity Status History");
		selectActivityDropDown("Activity Status History");

		Log.info("Number of row found : "
				+ driver.findElements(By.xpath("//div[@class='scrollGrid']//tbody/tr")).size());

	}

	public void verifyActivityStatusHistoryUpdated(String activityType) {
		Log = Logger.getLogger("ActivityPage.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		
		try{
			wait.until(		
				ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(config.getChangeHistoryXpath()))))
				.click();
		Log.info("user click on change history");

		Log.info("checking for Activity Status History");
		selectActivityDropDown("Activity Status History");

		Log.info("Number of row found after update : "
				+ driver.findElements(By.xpath("//div[@class='scrollGrid']//tbody/tr")).size());

		Assert.assertEquals(activityType,
				driver.findElement(By.xpath("//div[@class='scrollGrid']//tbody/tr[1]/td[2]")).getText().trim());
		Assert.assertEquals("Not Started",
				driver.findElement(By.xpath("//div[@class='scrollGrid']//tbody/tr[1]/td[3]")).getText().trim());
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		String currentDate = dateFormat.format(new Date());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='scrollGrid']//tbody/tr[1]/td[5]")).getText().trim()
				.contains(currentDate));
		Log.info("Verified all details of newly added activity");
		}catch(Exception e){
			driver.findElement(By.xpath("//button[contains(text(),'Ok')]"));
			Log.info("Clicked on OK");
		}
	}

	public void verifyCaseStatusHistory() {
		Log = Logger.getLogger("ActivityPage.class");
		PropertyConfigurator.configure("log4j.properties");

		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		wait.until(
				ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(config.getChangeHistoryXpath()))))
				.click();
		Log.info("user click on change history");

		Log.info("checking for Case Status History");
		selectActivityDropDown("Case Status History");

		Log.info("Number of row found : "
				+ driver.findElements(By.xpath("//div[@class='scrollGrid']//tbody/tr")).size());

	}

	public void verifyCaseStatusHistoryUpdated(String caseOwner) {
		Log = Logger.getLogger("ActivityPage.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		wait.until(
				ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(config.getChangeHistoryXpath()))))
				.click();
		Log.info("user click on change history");

		Log.info("checking for Case Status History");
		selectActivityDropDown("Case Status History");

		Log.info("Number of row found after update : "
				+ driver.findElements(By.xpath("//div[@class='scrollGrid']//tbody/tr")).size());

		Assert.assertEquals("Active",
				driver.findElement(By.xpath("//div[@class='scrollGrid']//tbody/tr[1]/td[2]")).getText().trim());
		Assert.assertEquals("Assigned",
				driver.findElement(By.xpath("//div[@class='scrollGrid']//tbody/tr[1]/td[3]")).getText().trim());
		Assert.assertEquals(caseOwner,
				driver.findElement(By.xpath("//div[@class='scrollGrid']//tbody/tr[1]/td[4]")).getText().trim());
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		String currentDate = dateFormat.format(new Date());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='scrollGrid']//tbody/tr[1]/td[5]")).getText().trim()
				.contains(currentDate));
		Log.info("Verified all details of newly added case activity");
	}

	public void verifyContactHistory() {
		Log = Logger.getLogger("ActivityPage.class");
		PropertyConfigurator.configure("log4j.properties");

		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		wait.until(
				ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(config.getChangeHistoryXpath()))))
				.click();
		Log.info("user click on change history");

		Log.info("cheching for Contact History");
		selectActivityDropDown("Contact History");

		Log.info("Number of row found : "
				+ driver.findElements(By.xpath("//div[@class='scrollGrid']//tbody/tr")).size());

	}

	public void verifyContactHistoryUpdated(String activityType, String contactOutcome) {
		Log = Logger.getLogger("ActivityPage.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		wait.until(
				ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(config.getChangeHistoryXpath()))))
				.click();
		Log.info("user click on change history");

		Log.info("cheching for Contact History");
		selectActivityDropDown("Contact History");

		Log.info("Number of row found after update : "
				+ driver.findElements(By.xpath("//div[@class='scrollGrid']//tbody/tr")).size());

		Assert.assertEquals(activityType,
				driver.findElement(By.xpath("//div[@class='scrollGrid']//tbody/tr[last()]/td[2]")).getText().trim());
		Assert.assertEquals(contactOutcome,
				driver.findElement(By.xpath("//div[@class='scrollGrid']//tbody/tr[last()]/td[4]")).getText().trim());
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		String currentDate = dateFormat.format(new Date());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='scrollGrid']//tbody/tr[last()]/td[4]")).getText()
				.trim().contains(currentDate));
		Log.info("Verified all details of newly added contact");
	}

	private void selectActivityDropDown(String activityName) {
		driver.findElement(By.xpath(config.getChangeHistoryInformationDropDown())).click();
		driver.findElement(
				By.xpath(config.getChangeHistoryInformationDropDownValue().replace("<DISPLAYED_TEXT>", activityName)))
				.click();
		try {
			Thread.sleep(GlobalValues.Sleep_wait_time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private boolean verifyHeader(String headerName) {
		return driver.findElements(By.xpath(config.getGridHeaderXpath().replace("<HEADER_NAME>", headerName)))
				.size() > 0;
	}
	
	/*
	 * verifylabelsinSubActivities - Method to verify the Subactivities in Case Review
	 * The following Subactivities -Case Information, Questionnaires, Care Plan, Medications, Conditions, Member Representatives have been implemented
	 * Validate Save and Back button
	 */

		public void verifylabelsinSubActivities(String ActivityName) throws InterruptedException {
			Log = Logger.getLogger("ActivityPage.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);


			try {
//				int a ;
				wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(config.getEditActivity().replace("<ActivityName>", ActivityName))))).click();
				wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath(config.getmsgLoading()))));
				
				WebActions WE = new WebActions();
							
				addAllSubActivities() ;
					
				if(driver.findElement(By.xpath(config.getHeader().replace("<ActivityName>", ActivityName))).getText().contains(ActivityName)){	
				
					Log.info("Activity header validated - " + ActivityName);
				}
				else{
					Log.info("Activity header validation failes - " + ActivityName);
				}
				if(ActivityName.equalsIgnoreCase("Case Review")){
				
					List<String> s1 = new ArrayList<String>();
					List<WebElement> l1= driver.findElements(By.xpath(config.getlblSubactivityheaders()));
					for(int i = 1;i<=l1.size();i++){
						String AllSubactivityheaders = "(" + config.getlblSubactivityheaders().concat(")["+ i+ "]");
						WebElement SA = driver.findElement(By.xpath(AllSubactivityheaders));
						s1.add(SA.getText());
						System.out.println("SubActivities present in "+ ActivityName+ "are" + s1);
					}
					
					for(int i=1;i<=(l1.size());i++){
						String AllSubactivityheaders = "(" + config.getlblSubactivityheaders().concat(")["+ i+ "]");
						WebElement SA = driver.findElement(By.xpath(AllSubactivityheaders));
						s1.add(SA.getText());
						System.out.println(SA.getText());
						Thread.sleep(2000);
						SA.click();
						Thread.sleep(2000);
						List<String> s2 = new ArrayList<String>();

						
						switch(SA.getText()){
						
						case "Case Information":
							
							validateCaseInformationGrid(SA, new ArrayList<String>(),ActivityName);
							
							break;
							
						case "Questionnaires":
							
							validateQuestionnaireGrid(SA, new ArrayList<String>(),ActivityName);
							
							break;
							
						case "Care Plan":
							
							
							validateGoalsGrid(SA, new ArrayList<String>(),ActivityName);
							
							
							validateInterventionsandbarriersGrid(SA, new ArrayList<String>(),ActivityName);
							break;
							
						case "Medications":	
							
							MedicationsConditionsMRGrid(SA, new ArrayList<String>(),ActivityName, GlobalValues.MedicationsGrid);
							break;
							
						case "Conditions":	
							
							MedicationsConditionsMRGrid(SA, new ArrayList<String>(),ActivityName, GlobalValues.ConditionsGrid);
							break;
							
						case "Member Representatives":	
							
							MedicationsConditionsMRGrid(SA, new ArrayList<String>(),ActivityName, GlobalValues.MemberRepresentativesGrid);
							break;
							
							default:
								Log.info("");
								break;
						
						}
						
						
					}
					System.out.println(s1);
					if(GlobalValues.CaseReviewAllSubActivities.containsAll(s1)){
						Log.info("All the Sub Activity headers validated in the Activity - " + ActivityName);
					}
					
					
					
				}
				
				validateobjectexists(config.getbtnSaveEndActivity(), "Save/End Activity");
				validateobjectexists(config.getbtnSaveForLater(), "Save For Later");
				validateobjectexists(config.getbtnBack(), "Back");
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getbtnBack().replace("<button>", "Back")))).click();
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
		
		
		public void validateCaseInformationGrid(WebElement SA, List<String> s2, String ActivityName) throws InterruptedException {	
		for(WebElement Wblabels:driver.findElements(By.xpath(config.getlblCaseinformationlabels().replace("<SubActivity Name>", SA.getText())))){
			s2.add(Wblabels.getText());
		}
		if(s2.containsAll(GlobalValues.CaseInformationGrid)){
			Log.info("All the labels are matching in the "+ SA.getText()+" Sub Activity in the Activity "+ActivityName);
			
		}
		else{
			Log.info("All the labels are not matching in the "+ SA.getText()+" Sub Activity in the Activity "+ActivityName);
			
		}
		s2.clear();
		List<WebElement> CI = driver.findElements(By.xpath(config.getlblCaseinformationlabelvalues().replace("<SubActivity Name>", SA.getText())));
		for(WebElement Wblabels: CI){
			s2.add(Wblabels.getText());
		}
		System.out.println("Case Information Values are : " +s2);
		
		SA.click();
		Log.info("Expand and collapse is working as expected for SubActivity " + SA.getText());
		s2.clear();
		}
		
		public void validateQuestionnaireGrid(WebElement SA, List<String> s2, String ActivityName) throws InterruptedException {	
			for(WebElement Wblabels:driver.findElements(By.xpath(config.getlblQuestionairelabels().replace("<SubActivity Name>", SA.getText())))){
				s2.add(Wblabels.getText());
			}
			if(s2.containsAll(GlobalValues.QuestionairesGrid)){
				Log.info("All the labels are matching in the "+ SA.getText()+" Sub Activity in the Activity "+ActivityName);
				
			}
			else{
				Log.info("All the labels are not matching in the "+ SA.getText()+" Sub Activity in the Activity "+ActivityName);
				
			}
			s2.clear();
			if(driver.findElement(By.xpath(config.getlblNorecordsQuestionaire().replace("<SubActivity Name>", SA.getText()))).getText().trim().equalsIgnoreCase("No records found")){
				Log.info("There are no "+SA.getText()+" added. No Records Available");
			}else{
//				int Qsize = ;
			for(int q=1;q<= driver.findElements(By.xpath(config.getlblQuestionairegridrow())).size() ;q++){
				for(WebElement Wblabels:driver.findElements(By.xpath(config.getlblQuestionairelabelvalues().replace("columnnumber", Integer.toString(q))))){
				s2.add(Wblabels.getText());
				}
				System.out.println(SA.getText()+ " Values in row "+q+" are : " +s2);
			}
			}
			validateobjectnotexists(config.getbtnAddQuestionnaires(), "Add Questionnaires","Add");
			validateobjectnotexists(config.getlnkEditQuestionnaires(), "Edit Questionnaires","Edit");
			SA.click();
			Log.info("Expand and collapse is working as expected for SubActivity " + SA.getText());
			s2.clear();
		}
		
		public void validateGoalsGrid(WebElement SA, List<String> s2, String ActivityName) throws InterruptedException {
		for(WebElement Wblabels:driver.findElements(By.xpath(config.getlblgoalslabels()))){
			s2.add(Wblabels.getText());
		}
		if(s2.containsAll(GlobalValues.GoalsGrid)){
			Log.info("All the labels " +s2+ "are matching in goals in the "+ SA.getText()+" Sub Activity in the Activity "+ActivityName);
			
		}
		else{
			Log.info("All the labels " +s2+ " are not matching in goals in the "+ SA.getText()+" Sub Activity in the Activity "+ActivityName);
			
		}
		s2.clear();
		if(driver.findElement(By.xpath(config.getlblNorecordsgoals().replace("<SubActivity Name>", SA.getText()))).getText().trim().equalsIgnoreCase("No records found")){
			Log.info("There are no goals added. No Records Available");
		}else{
		int gsize = driver.findElements(By.xpath(config.getlblgoalsgridrow())).size();
		for(int g=1;g<= gsize ;g++){
			for(WebElement Wblabels:driver.findElements(By.xpath(config.getlblgoalslabelvalues().replace("columnnumber", Integer.toString(g))))){
			s2.add(Wblabels.getText());
			}
			System.out.println(SA.getText()+ " Values in row "+g+" are : " +s2);
		}
		}
		s2.clear();
		}
		
	public void validateInterventionsandbarriersGrid(WebElement SA, List<String> s2, String ActivityName) throws InterruptedException {	
		for(WebElement Wblabels:driver.findElements(By.xpath(config.getlblInterventionsandbarrierslabels()))){
			s2.add(Wblabels.getText());
		}
		if(s2.containsAll(GlobalValues.InterventionbarrierGrid)){
			Log.info("All the labels are matching in Interventions and barriers in the "+ SA.getText()+" Sub Activity in the Activity "+ActivityName);
			
		}
		else{
			Log.info("All the labels are not matching in Interventions and barriers in the "+ SA.getText()+" Sub Activity in the Activity "+ActivityName);
			
		}
		s2.clear();
		
		if(driver.findElement(By.xpath(config.getlblNorecordsInterventionsandBarriers().replace("<SubActivity Name>", SA.getText()))).getText().trim().equalsIgnoreCase("No records found")){
			Log.info("There are no Interventions and barriers added. No Records Available");
		}else{
			int bsize = driver.findElements(By.xpath(config.getlblInterventionsandbarriersgridrow())).size();
		for(int b=1;b<=bsize;b++){
			for(WebElement Wblabels:driver.findElements(By.xpath(config.getlblInterventionsandbarrierslabelvalues().replace("columnnumber", Integer.toString(b))))){
			s2.add(Wblabels.getText());
			}
			System.out.println(SA.getText() + " Values in row "+b+" are : " +s2);
		}
		}
		SA.click();
		Log.info("Expand and collapse is working as expected for SubActivity " + SA.getText());
		s2.clear();
		}
		
		
		
	public void MedicationsConditionsMRGrid(WebElement SA, List<String> s2, String ActivityName,List<String> Expectedvalues ) throws InterruptedException {		
		for(WebElement Wblabels:driver.findElements(By.xpath(config.getlblMECOsubactivitylabels().replace("<SubActivity Name>", SA.getText())))){
			s2.add(Wblabels.getText());
		}
		if(s2.containsAll(Expectedvalues)){
			Log.info("All the labels are matching in the "+ SA.getText()+" Sub Activity in the Activity "+ActivityName);
			
		}
		else{
			Log.info("All the labels are not matching in the "+ SA.getText()+" Sub Activity in the Activity "+ActivityName);
			
		}
		s2.clear();
		
		if(driver.findElement(By.xpath(config.getlblNorecords().replace("<SubActivity Name>", SA.getText()))).getText().trim().equalsIgnoreCase("No records found")){
			Log.info("There are no "+SA.getText()+" added. No Records Available");
		}else{
			
			int msize = driver.findElements(By.xpath(config.getlblMECOsubactivitygridrow().replaceAll("<SubActivity Name>", SA.getText()))).size();
		for(int m=1;m<=msize;m++){
//			System.out.println(config.getlblMECOsubactivitylabelvalues().replace("columnnumber", Integer.toString(m)).replace("<SubActivity Name>", SA.getText()));
			for(WebElement Wblabels:driver.findElements(By.xpath(config.getlblMECOsubactivitylabelvalues().replace("columnnumber", Integer.toString(m)).replace("<SubActivity Name>", SA.getText())))){
			s2.add(Wblabels.getText());
			}
			System.out.println(SA.getText() +" Values in row "+m+" are : " +s2);
		}
		
		}
		if(SA.getText().equalsIgnoreCase("Medications")){
			validateobjectnotexists(config.getbtnAddMedication(), "Add Medication","Add");
			validateobjectnotexists(config.getlnkEditMedication(), "Edit Medication","Edit");
		}
		else if(SA.getText().equalsIgnoreCase("Conditions")){
			validateobjectnotexists(config.getbtnAddCondition(), "Add Condition","Add");
			validateobjectnotexists(config.getlnkEditCondition(), "Edit Condition","Edit");
		}
		else if(SA.getText().equalsIgnoreCase("Member Representatives")){
			validateobjectnotexists(config.getbtnAddRepresentative(), "Add Representative()","Add");
			validateobjectnotexists(config.getlnkEditRepresentative(), "Edit Representative()","Edit");
		}
		SA.click();
		Log.info("Expand and collapse is working as expected for SubActivity " + SA.getText());
		s2.clear();
	}

	/* Method to check if the Add / Edit link of each sub activity is not present in a given page
	 * ActionType options - Add/Edit
	 * buttonname - As defined by html Text
	 * xpath - Gives the xpath of the element
	 */
	public void validateobjectnotexists(String Sxpath, String buttonname, String Actiontype ) throws InterruptedException {	
		if(Actiontype.equalsIgnoreCase("Add")){
			System.out.println(Sxpath);
		if((driver.findElements(By.xpath(Sxpath)).size()) == 0){
			Log.info(buttonname + " is not available which is as expected");
		}
		else{
			Log.info(buttonname + " is available which is not as expected");
		}
		}
		else if(Actiontype.equalsIgnoreCase("Edit")){
			if((driver.findElements(By.xpath(Sxpath)).size()) == 0){
				Log.info(buttonname + " is not available which is as expected");
			}
		}
		else{
			Log.info(Actiontype + " needs to be Add/Edit");
		}
	}


	/* Method to check if the button is present in a given page
	 * 
	 * buttonname - As defined by html Text
	 * xpath - Gives the xpath of the element
	 */

	public void validateobjectexists(String xpath, String buttonname) throws InterruptedException {	
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
	if((driver.findElements(By.xpath(xpath.replace("<button>", buttonname))).size()) != 0){
		
			Log.info(buttonname + " is available which is as expected");
		}
		else{
			Log.info(buttonname + " is not available which is not as expected");
		}
		
	}
	
	public void addAllSubActivities() throws InterruptedException {	
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
	Thread.sleep(4000);
	wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(config.getbtnAddSubActivity())))).click();
	Assert.assertEquals(wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(config.getlblSubActivityHeader())))).getText().trim(), "Add Sub Activity");
	List<WebElement> We = driver.findElements(By.xpath(config.getchkSubActivitycheckbox()));
//	System.out.println(We);
	Thread.sleep(3000);
	if(!(We.size()== 0)){
	for(int i=1;i<= We.size();i++){
		driver.findElement(By.xpath(config.getchkSubActivitycheckbox().concat("["+ i+ "]//div" ))).click();
		Thread.sleep(2000);
	}
	wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(config.getbtnAdd())))).click();
	wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath(config.getmsgLoading()))));
	Log.info("All the Sub Activities have been added");
	}
	else{
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(config.getbtnClose())))).click();
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath(config.getmsgLoading()))));
	}
	
	}
	
	public void clickcareplanSubActivity() throws InterruptedException {	
		
		WebActions wb = new WebActions();
		wb.waitforPageLoad(driver, 15);	
		Thread.sleep(2000);
		WebElement SA = driver.findElement(By.xpath(config.gethdrcareplan()));
		
		SA.click();
		System.out.println("Clicked on " +SA.getText());
		Thread.sleep(2000);
		
	}
}
