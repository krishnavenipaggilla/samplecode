package com.nimbus.pages;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

public class Clinical {
	ConfigReader config = new ConfigReader();
	DataReaderExcel DataRead = new DataReaderExcel();
	
	WebDriver driver;
	Logger Log;
	
	public Clinical(WebDriver ldriver){
		this.driver=ldriver;
	}
	
	public Clinical(Logger lLog){
		this.Log=lLog;
	}
	
public void clinicalTrigger() throws Exception
	{
	
  		Log = Logger.getLogger("Clinical.class");
		PropertyConfigurator.configure("log4j.properties");
		  AnnualAssessment annual = new AnnualAssessment(driver);
		
		 
		 try
		 {
			 WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getMemberActionLeftNav()))).click(); 
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getActivityList()))).click(); 
			Thread.sleep(3000);

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getEditInitial()))).click(); 
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getActivityOpen()))).click(); 
			annual.annual_assessment("ClinicalIntervention", "Initial", 2);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getSaveActivityButton()))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getAlert()))).click();
			Thread.sleep(3000);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getMemberActionLeftNav()))).click(); 
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getActivityList()))).click(); 
			Thread.sleep(3000);
		
			   String InitialStatus= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getInitialStatus()))).getText().trim();
			Assert.assertEquals("Completed", InitialStatus);
			Log.info("Initial Outreach is Completed");
			Thread.sleep(3000);
			String ClinicalActivity= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getClinicalActivity()))).getText().trim();
			 String Status= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getClinicalStatus()))).getText().trim();
			 Assert.assertNotNull(ClinicalActivity);
			 Assert.assertEquals("Not Started", Status);
			 Log.info("Clinical Interevention activity is triggered once Initial Outreach is completed in "+Status+" status");
			 wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getClinicalEdit()))).click(); 
			 String CCActivityname=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getCCactivityName()))).getText().trim();
			 String []activitybanner =CCActivityname.split("-");
			 String Activityname=activitybanner[0].trim();
			 String Assignedto=activitybanner[1].trim();
			 //String Assignedto=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getassignedto()))).getText().trim();
			String Program=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getProgram()))).getText().trim();
			String status=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getEditstatus()))).getText().trim();
			String Startdate=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getStartdate()))).getText().trim();
			String Duedate=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getDuedate()))).getText().trim();
			Assert.assertEquals("Clinical Intervention", Activityname);
			Log.info("Activity Name is "+Activityname);
			Assert.assertEquals("Clinical Intervention Team", Assignedto);
			Log.info("Activity is assigned to "+Assignedto);
			Assert.assertEquals("In Progress", status);
			Log.info("Status is "+status);
			if(Program.contains("NICU"))
			{
				DateFormat d = new SimpleDateFormat("MM/dd/yyyy");
				//ASystem.out.println(Startdate.toString());
				Date startDate = d.parse(Startdate);
				Calendar c = Calendar.getInstance();
				c.setTime(startDate); 
				c.add(Calendar.DATE, 10);
				Date duedate = c.getTime();
				String AddedDate = d.format(duedate);
				//System.out.println(AddedDate.toString());
				Assert.assertTrue(Duedate.contains(AddedDate));
				Log.info("Due Date is "+Duedate);		
			}
			String introSection= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getintrosection()))).getText().trim();
			Assert.assertNotNull(introSection);
			Log.info(introSection+" section is present");
			
			String memberSection= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getmemberSection()))).getText().trim();
			Assert.assertNotNull(memberSection);
			Log.info(memberSection+" section is present");
			
			String CareplanSetion= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getCareplansection()))).getText().trim();
			Assert.assertNotNull(CareplanSetion);
			Log.info(CareplanSetion+" section is present");
			
			String QuestionnaireSection= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getQuestionnairesection()))).getText().trim();
			Assert.assertNotNull(QuestionnaireSection);
			Log.info(QuestionnaireSection+" section is present");
			
			String recordCaontatSection= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getRecordsection()))).getText().trim();
			Assert.assertNotNull(recordCaontatSection);
			Log.info(recordCaontatSection+" section is present");
			
		
	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getActivityOpen()))).click(); 
			 annual.annual_assessment("ClinicalIntervention", "Clinical", 2);
			
			WebElement Backbutton=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getBackButton())));
		    Assert.assertTrue(Backbutton.isDisplayed());
		    Log.info("Back button is displayed");
		        
	       WebElement saveforlater=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getSaveforLaterButton())));
	        Assert.assertTrue(saveforlater.isDisplayed());
	        Log.info("Save for Later button is displayed");
	        
	        WebElement saveactivity=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getSaveActivityButton())));
	        Assert.assertTrue(saveactivity.isDisplayed());
	        Log.info("Save/End Activity button is displayed");
	        Thread.sleep(3000);
	       	
	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getSaveforLaterButton()))).click();
	 		Thread.sleep(3000);
	 		Assert.assertEquals("Activity", wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getActivityHeader()))).getText().trim());
			Log.info("Navigated to Previous page"); 
			
			String CaseID=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getCaseIDRef()))).getText().trim();
			Log.info("CaseID"+CaseID);
			 wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getActivityDashBaord()))).click(); 
			 //CaseID="17";
			  caseNavigation(CaseID,"ClinicalIntervention","ActivityType");
			  wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getBackButton()))).click(); 
			  Thread.sleep(3000);
			  Assert.assertEquals("Activity List", wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getActivityListHeader()))).getText().trim());
				Log.info("Navigated to Previous page"); 
				
				caseNavigation(CaseID,"ClinicalIntervention","ActivityType");
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getActivityOpen()))).click(); 
				annual.annual_assessment("ClinicalIntervention", "Clinical", 2);
				Thread.sleep(3000);
		 		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getSaveActivityButton()))).click(); 
		 		
		 		//commented for now due to defect 19199
		 		 Assert.assertEquals("Add Activity", wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getAddActivity()))).getText().trim());
		 		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getActivityDashBaord()))).click(); 
		 		caseNavigation(CaseID,"ClinicalIntervention","ActivityType");
		 		Assert.assertEquals("Completed", wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getEditstatus()))).getText().trim());
				Log.info("Activity status is Completed");
				 wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getMemberActionLeftNav()))).click(); 
				 wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getActivityList()))).click(); 
				 Thread.sleep(3000);
				 Assert.assertEquals("Completed", wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getClinicalStatus()))).getText().trim());
				 Log.info("Clinical Intervention Activity status is Completed in Grid too");
			 	
		 		 
		 }

		 catch(Exception e)
		 {
			 Log.info("Clinical Intervention Activity failed"+e);
		 }
		 }
public void caseNavigation(String caseid, String filename, String sheetname) throws Exception
{
	  AnnualAssessment annual = new AnnualAssessment(driver);
	  WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		try
	  {
		 annual.annual_assessment(filename, sheetname, 2); 
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

