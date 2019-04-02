package com.nimbus.pages;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import excelReader.DataReaderExcel;
import junit.framework.Assert;
import utility.ConfigReader;

public class Referrals {

	ConfigReader config = new ConfigReader();
	DataReaderExcel DataRead = new DataReaderExcel();
	
	WebDriver driver;
	Logger Log;
	
	public Referrals(WebDriver ldriver){
		this.driver=ldriver;
	}
	
	public Referrals(Logger lLog){
		this.Log=lLog;
	}
	
public void referrals() throws Exception
	{
	
  		Log = Logger.getLogger("Referrals.class");
		PropertyConfigurator.configure("log4j.properties");
		  AnnualAssessment annual = new AnnualAssessment(driver);
		 DashboardFunctions df = new DashboardFunctions(driver);
		 File sour = new File("./TestData/Referral.xlsx");
			FileInputStream fiss = new FileInputStream(sour);
			XSSFWorkbook wb = new XSSFWorkbook(fiss);
			XSSFSheet Sheet1 = wb.getSheet("EditReferral");
			LeftNavigationLink lnk = new LeftNavigationLink(driver);
			lnk.clickMemberActionCenter();
		 try
		 {
			 WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			 wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getCarePlanleftnav()))).click();
		     Log.info("Clicked on Care Paln");
		     wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getAddGoal()))).click();
		     Log.info("Clicked Add Goal");
		     wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class='ui-clickable fa fa-fw fa-caret-down'])[1]"))).click();
		     wait.until(ExpectedConditions.elementToBeClickable(By.xpath("((//label[contains(text(),'Goal')])[1]//following-sibling::p-dropdown//div//li//span)[1]"))).click();
		     annual.annual_assessment("Referral", "Goal", 2);
			 wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getReferralsLeftNav()))).click();
		     Log.info("Clicked on Interventions");
		     Thread.sleep(2000);
		     wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getAddIntervention()))).click();
		     Log.info("Clicked on Add Interventions");
		     Thread.sleep(2000);
		     wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getInterventiondropdown()))).click();
		     Log.info("Clicked on Intervention dropdown");
		     Thread.sleep(2000);
		     wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getReferralasIntervention()))).click();
		    Thread.sleep(2000);
		     Log.info("Selected Referral as Intervention");
		     List <WebElement> ReferralValues=driver.findElements(By.xpath(config.getAllReferralvalues()));
		     Assert.assertTrue(ReferralValues.get(2).getText().toString().contains(GlobalValues.ReferralFields.get(0)));
		     Assert.assertTrue(ReferralValues.get(3).getText().toString().contains(GlobalValues.ReferralFields.get(1)));
		     Assert.assertTrue(ReferralValues.get(4).getText().toString().contains(GlobalValues.ReferralFields.get(2)));
		     Assert.assertTrue(ReferralValues.get(5).getText().toString().contains(GlobalValues.ReferralFields.get(3)));
		     Assert.assertTrue(ReferralValues.get(6).getText().toString().contains(GlobalValues.ReferralFields.get(4)));
		     Assert.assertTrue(ReferralValues.get(7).getText().toString().contains(GlobalValues.ReferralFields.get(5)));
		     
		    
		   Log.info("Referral Attributes are as expected");
		   wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getReferralType()))).click();
		   wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getBehavioralHealth()))).click();
		   Log.info("Behavioral Health Referral Type is selected");
		   Thread.sleep(2000);
		   ReferralValues=driver.findElements(By.xpath(config.getAllReferralvalues()));
		   Assert.assertTrue(ReferralValues.get(8).getText().toString().contains("Permission"));
		   Log.info("Permission Attributes is present for Behavioral Health Referral Type");
	    	annual.annual_assessment("Referral", "Add", 2);
	    	String Assignedto=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getAssignedTovalue()))).getText().trim();
	    	String RefTypeText=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getReferralTypeValue()))).getText().trim();
	    	String RefReasnText=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getReferralReasonValue()))).getText().trim();
	    	String ActionTaken=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getActionTakenValue()))).getText().trim();
	    	String ActionProposed=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getActionProposedValue()))).getText().trim();
	    	String PermissionValue=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getPermissionValue()))).getText().trim();
	    	String NoteValue= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getNoteValue()))).getAttribute("value");
	    	String DueDateValue=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getDueDateValue()))).getAttribute("value");
	    	
	    		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config. getGoalCheckbox()))).click();
			     String GoalValue= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getGoalValue()))).getText().trim();
			     Log.info("Selected Goal"+GoalValue);
			     wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config. getSubmit()))).click();
			     			
	    	Thread.sleep(3000);
	    	String GridDescription= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getDescription()))).getText().trim();
	    	String GridAssignedTo=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getGridAssignedTo()))).getText().trim();
	    	Assert.assertEquals(RefTypeText+" - "+RefReasnText, GridDescription);
	    	Log.info("Description contains Referral Type and Referral Reason");
	    	Assert.assertEquals(Assignedto, GridAssignedTo);
	    	Log.info("'Assigned To' value matches");
	    	Thread.sleep(2000);
	    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getEditReferral()))).click();
	    	Thread.sleep(1000);
	    	String NoteGridHeader=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getNoteGridHeader()))).getText().trim();
	    	Assert.assertEquals("Notes",NoteGridHeader );
	    	Log.info("Grid Header is "+NoteGridHeader);
	    	List <WebElement> columnname=driver.findElements(By.xpath(config.getcolumnNames()));
	    	for (int j=0;j<columnname.size();j++)
	    	{
	    		Assert.assertEquals(GlobalValues.NoteGridColumn.get(j), columnname.get(j).getText().toString().trim());
	    		Log.info("The Expected Column name "+GlobalValues.NoteGridColumn.get(j)+" and Actual Column name "+columnname.get(j).getText().toString().trim()+" of Grid have matched");
	    	}
	    	  	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getAddNote()))).click();
	    	Log.info("Clicked Add note");
	    	Thread.sleep(1000);
	    	String NoteHeader=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getAddnnoteHeader()))).getText().trim();
	    	Assert.assertEquals("Add Notes",NoteHeader );
	    	Log.info("Modal Header is "+NoteHeader);
	    	annual.annual_assessment("Referral", "Note", 2);
	    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getBack()))).click();
	    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getAddIntervention()))).click();
		     wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getInterventiondropdown()))).click();
		     wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getReferralasIntervention()))).click();
		     Thread.sleep(1000);
		     annual.annual_assessment("Referral", "AddSecond", 2);
	    	Thread.sleep(2000);
	    	 List <WebElement> row=driver.findElements(By.xpath(config.getrow()));
		     int rowcount= row.size();
		     String [] goaltext= new String[10];
		     boolean flag=false;
		     String gridtAllText = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='scrollGrid']")))
	                    .getText().toString();
		     if(!(gridtAllText.toLowerCase().contains("no records found")) )
				{
		     for(int i=1; i<rowcount;i++)
		     {
		    	// Log.info("rowcount::"+rowcount);
		    	goaltext[i-1]= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getrow()+"["+i+"]//td[2]"))).getText().trim();
			    if(! (goaltext[i-1].toLowerCase().equals(GoalValue.toLowerCase())))
			    {
			    	
			    	flag=true;
			    	
			    }
			    else{
			    	flag =false;
			    	break;
			    }
		     }
		     
		     }
		     else
		     {
		    	 flag=true;
		     }
		    
		     Assert.assertTrue(flag);
		     Log.info("Verified Same Goal cannot be selected for same referral reason and referral type");
		     wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getBack()))).click();
		     Thread.sleep(2000);
		     
		     //Referral Activity
		     wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getMemberActionLeftNav()))).click(); 
		     String RefActivity= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getReferralActivity()))).getText().trim();
		     String RefdueDate= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getRefDueDate()))).getText().trim();
			 String RefAssignedTo= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getRefAssignedTo()))).getText().trim();
			 Assert.assertNotNull(RefActivity);  
			 Assert.assertEquals(DueDateValue, RefdueDate);
			 Assert.assertEquals(Assignedto, RefAssignedTo);
			 Log.info("Referral Activity created with due Date as "+RefdueDate+"and assigned to "+RefAssignedTo);
			 
		     //Edit Referral and close
			 Thread.sleep(3000);
			 wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getReferralsLeftNav()))).click();
		     wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getEditReferral()))).click();
		     annual.annual_assessment("Referral", "Edit", 2);
		     Log.info("Closed Referral");
		     Thread.sleep(10000);
		     //Check if Referral Activity is completed
		     wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getMemberActionLeftNav()))).click(); 
		     Thread.sleep(3000);
			 String RefStatus=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getRefStatus()))).getText().trim();
			  Assert.assertEquals("Completed", RefStatus);
			  Log.info("Referral activity is "+RefStatus);
			  wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getReferralsLeftNav()))).click();
		     wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getAddIntervention()))).click();
		     wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getInterventiondropdown()))).click();
		     wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getReferralasIntervention()))).click();
		     annual.annual_assessment("Referral", "Add", 2);
		     row=driver.findElements(By.xpath(config.getrow()));
		      rowcount= row.size();
		     goaltext= new String[10];
		     flag=true;
		     for(int i=1; i<rowcount;i++)
		     {
		    	goaltext[i-1]= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getrow()+"["+i+"]//td[2]"))).getText().trim();
		    	if (goaltext[i-1].toLowerCase().equals(GoalValue.toLowerCase()))
			    {
			    	
		    		Log.info(goaltext[i-1].toLowerCase());
		    		flag=true;
			    	break;
			    	
			    }
			    else{
			    	flag =false;
			    	
			    }
		     }
		     Assert.assertTrue(flag);
		     Log.info("Verified Once Referral is closed, Goal is visible for same Referral reason and Referral type can be added");
		     Thread.sleep(2000);
		     wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config. getGoalCheckbox()))).click();
		     wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config. getSubmit()))).click();
		     //Check # of Referral activity
		     wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getMemberActionLeftNav()))).click(); 
		     Thread.sleep(3000);
		     List <WebElement> activities=driver.findElements(By.xpath(config.getReferralActivity()));
		     int ct=activities.size();
			   Integer.toString(ct);
			   Log.info(Integer.toString(ct));
			   //Go to Careplan
			   Thread.sleep(3000);
			   wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getCarePlanLeftNav()))).click();
		     Log.info("Clicked on CarePlan"); 
		     wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getSecondaryRow()))).click();
		     Log.info("Clicked on secondary row"); 
		     Thread.sleep(3000);
		    String SecGridDescription= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getSecDescription()))).getText().trim();
		    String SecGridAssignedTo=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getSecGridAssignedTo()))).getText().trim();
		    	Assert.assertEquals("(R) "+RefTypeText+" - "+RefReasnText, SecGridDescription);
		    	
		    	Log.info("Description contains Referral Type and Referral Reason");
		    	Assert.assertEquals(Assignedto, SecGridAssignedTo);
		    	Log.info("'Assigned To' value matches");
		    	Thread.sleep(2000);
		    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getSecondaryRowEdit()))).click();
		    	String EditHeader= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getEditReferralHeader()))).getText().trim();
		    	Assert.assertEquals("Edit Referral", EditHeader);
		    	Log.info(EditHeader+" is header");
		    	
		    	Assert.assertNotNull(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getRefTypenonedit()))).getText());
		    	Log.info("Referral Type is non editable in Edit Referral page");
				
		    	Assert.assertNotNull(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getRefreasonNonedit()))).getText());
		    	Log.info("Referral Reason is non editable in Edit Referral page");
				 annual.annual_assessment("Referral", "EditReferral", 2);
				 Assignedto=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getAssignedTovalue()))).getText().trim();
				 String StatusText=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getStatusValue()))).getText().trim();
				 String StatusReasnText=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getStatusReasonValue()))).getText().trim();
				 ActionTaken=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getActionTakenValue()))).getText().trim();
			    ActionProposed=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getActionProposedValue()))).getText().trim();
			    DueDateValue=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getDueDateValue()))).getAttribute("value");
			    		
			    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config. getSubmit()))).click();
				    	
			    Thread.sleep(3000);
				 wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getCarePlanLeftNav()))).click();
				 wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getSecondaryRow()))).click();
				 String Statusgrid=Sheet1.getRow(4).getCell(2).getStringCellValue().toString();
				 String StatusReasonGrid=Sheet1.getRow(5).getCell(2).getStringCellValue().toString();
				 //String ActionTakengrid=Sheet1.getRow(6).getCell(2).getStringCellValue().toString();
				 //String ActionproposedGrid=Sheet1.getRow(7).getCell(2).getStringCellValue().toString();
				 Assert.assertEquals(Statusgrid, StatusText);
				 Assert.assertEquals(StatusReasonGrid, StatusReasnText);
				 Log.info("Values Edited are updated");
				 wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getSecondaryRowEdit()))).click();
				 Thread.sleep(3000);
				 String HistoryHeader=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getReferralHistoryGridHeader()))).getText().trim();
			    	Assert.assertEquals("History",HistoryHeader );
			    	Log.info("Grid Header is "+HistoryHeader);
			    	List <WebElement> historycolumnname=driver.findElements(By.xpath(config.getHistorycolumn()));
			    	for (int j=0;j<historycolumnname.size();j++)
			    	{
			    		Assert.assertEquals(GlobalValues.ReferralHistoryGrid.get(j), historycolumnname.get(j).getText().toString().trim());
			    		Log.info("The Expected Column name "+GlobalValues.ReferralHistoryGrid.get(j)+" and Actual Column name "+historycolumnname.get(j).getText().toString().trim()+" of Grid have matched");
			    	}	
			   String HistoryOwner=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getHistoryOwner()))).getText().trim();
			   String HistoryactionTaken=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getHistoryActionTaken()))).getText().trim();
			   String HistoryactionProposed=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getHistoryProposedvalue()))).getText().trim();
			   String Historyduedate=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getHistoryDateValue()))).getText().trim();
			   //Assert.assertEquals(Assignedto, HistoryOwner);
			   Assert.assertEquals(ActionTaken, HistoryactionTaken);
			   Assert.assertEquals(ActionProposed, HistoryactionProposed);
			   Assert.assertEquals(DueDateValue, Historyduedate);
			   Log.info("Values in grid matches");
			   
			   //check no change in Referral activity count
			   Thread.sleep(3000);
			   wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getMemberActionLeftNav()))).click(); 
			   Thread.sleep(3000);
			   activities=driver.findElements(By.xpath(config.getReferralActivity()));
			   int ct1=activities.size();
			   Integer.toString(ct1);
			   Log.info(Integer.toString(ct1));
			   Assert.assertEquals(ct, ct1);
			   Log.info("Same Referral Activity on Activity List Dashboard after creating Referral with same due date and Same Assigned To");
			    	
		 }
		 catch(Exception e)
		 {
			 Log.info("Referrals failed"+e);
			 e.printStackTrace();
		 }
}

public void editDueAssigned() throws Exception
{

		Log = Logger.getLogger("Referrals.class");
	PropertyConfigurator.configure("log4j.properties");
	  AnnualAssessment annual = new AnnualAssessment(driver);
	 DashboardFunctions df = new DashboardFunctions(driver);
	 File sour = new File("./TestData/Referral.xlsx");
		FileInputStream fiss = new FileInputStream(sour);
		XSSFWorkbook wb = new XSSFWorkbook(fiss);
		XSSFSheet Sheet1 = wb.getSheet("EditReferral");
	 try
	 {
		 WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		 Thread.sleep(3000);
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getReferralsLeftNav()))).click();
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getEditReferral()))).click();
		 annual.annual_assessment("Referral", "EditDue", 2);
		 Thread.sleep(3000);
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getMemberActionLeftNav()))).click(); 
		 Thread.sleep(3000);
		 List <WebElement>  activities=driver.findElements(By.xpath(config.getReferralActivity()));
		   int ct1=activities.size();
		   Integer.toString(ct1);
		   Log.info(Integer.toString(ct1));
		   Assert.assertEquals(3, ct1);
		   Log.info("A new referral activity is created once due dtae is changed");
		   Thread.sleep(3000);
		   wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getReferralsLeftNav()))).click();
		   Thread.sleep(3000);
			 wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getEditReferral()))).click();
		   annual.annual_assessment("Referral", "EditAssignedTo", 2);
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getMemberActionLeftNav()))).click(); 
		 Thread.sleep(3000);
		activities=driver.findElements(By.xpath(config.getReferralActivity()));
		  ct1=activities.size();
		   Integer.toString(ct1);
		   Log.info(Integer.toString(ct1));
		   Assert.assertEquals(4, ct1);
		   Log.info("A new referral activity is created once Assigned To is changed");	
	 }
	 catch(Exception e)
	 {
		 Log.info("Referral Activity Edit failed"+e);
		 e.printStackTrace();
	 }
	 }


public void BackSaveforLater() throws Exception
{

		Log = Logger.getLogger("Referrals.class");
	PropertyConfigurator.configure("log4j.properties");
	  AnnualAssessment annual = new AnnualAssessment(driver);
	 DashboardFunctions df = new DashboardFunctions(driver);
	 File sour = new File("./TestData/Referral.xlsx");
		FileInputStream fiss = new FileInputStream(sour);
		XSSFWorkbook wb = new XSSFWorkbook(fiss);
		XSSFSheet Sheet1 = wb.getSheet("EditReferral");
	 try
	 {
		 WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getEditRefActivity()))).click(); 
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getActivityOpen()))).click(); 
		 annual.annual_assessment("Referral", "Activity", 2);
 		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getSaveforLaterButton()))).click();
 		Thread.sleep(3000);
		Assert.assertEquals("Activity", wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getActivityHeader()))).getText().trim());
		Log.info("Navigated to Previous page"); 
		String CaseID=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getCaseIDRef()))).getText().trim();
		Log.info("CaseID"+CaseID);
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getActivityDashBaord()))).click(); 
		 //CaseID="17";
		  caseNavigation(CaseID);
		  wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getBackButton()))).click(); 
		  Thread.sleep(3000);
		  Assert.assertEquals("Activity List", wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getActivityListHeader()))).getText().trim());
			Log.info("Navigated to Previous page"); 
			caseNavigation(CaseID);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getActivityOpen()))).click(); 
			annual.annual_assessment("Referral", "Activity", 2);
			Thread.sleep(3000);
	 		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getSaveActivityButton()))).click(); 
	 		Assert.assertEquals("Activity List", wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getActivityListHeader()))).getText().trim());
			caseNavigation(CaseID);
	 		Assert.assertEquals("Completed", wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getRefActivityStatus()))).getText().trim());
			Log.info("Referral Activity status is Completed");
			 wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getMemberActionLeftNav()))).click(); 
			 Thread.sleep(3000);
			 Assert.assertEquals("Completed", wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getRefActivGrid()))).getText().trim());
			 Log.info("Referral Activity status is Completed in Grid too");
		 	
	 }
	 catch(Exception e)
	 {
		 Log.info("Referral Activity Edit failed"+e);
	 }
	 }
	public void caseNavigation(String caseid) throws Exception
	{
		  AnnualAssessment annual = new AnnualAssessment(driver);
		  WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			try
		  {
			 annual.annual_assessment("Referral", "ActivityType", 2); 
			 Thread.sleep(3000);
			  wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getFFwdButton()))).click();
			  Log.info("Click on  FForward button");
			  List <WebElement>  Refactivities=driver.findElements(By.xpath("//tr//td[1]//span[contains(text(),'"+caseid+"')]"));
			   int ct1=Refactivities.size();
			   Thread.sleep(3000);
			   WebElement activity=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("((//tr//td[1]//span[contains(text(),'"+caseid+"')])["+ct1+"]//following::td)[3]//span//a")));
			   Thread.sleep(3000);
			   activity.click(); 
			 Log.info("Clicked the Activity");
		  }
		  catch(Exception ex)
		  {
			  Log.info("No further Pages");
			  List <WebElement>  Refactivities=driver.findElements(By.xpath("//span[contains(text(),'"+caseid+"')]"));
			   int ct1=Refactivities.size();
			   wait.until(ExpectedConditions.elementToBeClickable(By.xpath("((//span[contains(text(),'"+caseid+"')])["+ct1+"]//following::td)[3]"))).click(); 
			   
			}
		
	}
	
}