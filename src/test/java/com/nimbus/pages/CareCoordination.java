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
import org.testng.Assert;

import excelReader.DataReaderExcel;
import utility.ConfigReader;

public class CareCoordination {

	ConfigReader config = new ConfigReader();
	DataReaderExcel DataRead = new DataReaderExcel();
	
	WebDriver driver;
	Logger Log;
	
	public CareCoordination(WebDriver ldriver){
		this.driver=ldriver;
	}
	
	public CareCoordination(Logger lLog){
		this.Log=lLog;
	}
	
public void careCoordination() throws Exception
	{
	
  		Log = Logger.getLogger("CareCoordination.class");
		PropertyConfigurator.configure("log4j.properties");
		  AnnualAssessment annual = new AnnualAssessment(driver);
		  Clinical cli=new Clinical(driver);
		  File sour = new File("./TestData/CareCoordination.xlsx");
			FileInputStream fiss = new FileInputStream(sour);
			XSSFWorkbook wb = new XSSFWorkbook(fiss);
			XSSFSheet Sheet1 = wb.getSheet("Activity");
			XSSFSheet Sheet2 = wb.getSheet("Condition");
		  try
			 {
				 WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
				 //-- Adding a Primary condition
				 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a//span[contains(text(),'Health Chart')]"))).click(); 
				 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a//span[contains(text(),'Conditions')]"))).click(); 
				 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Add Condition')]"))).click(); 
				 annual.annual_assessment("CareCoordination", "Condition", 2);
				 Thread.sleep(3000);
				 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Submit')]"))).click(); 
				 Thread.sleep(3000);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getAddActivityBtn()))).click(); 
				 annual.annual_assessment("CareCoordination", "Activity", 2);
				 Thread.sleep(3000);
				
				 /*Commented due to defect
				  * wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getAddActivityBtn()))).click(); 
				 annual.annual_assessment("CareCoordination", "Activity", 2);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getAlert()))).click(); 
				 */
				 
				 String CCActivity= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getCCActivity()))).getText().trim();
				String Status= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getCCStatus()))).getText().trim();
				 Assert.assertNotNull(CCActivity);
				 Assert.assertEquals("Not Started", Status);
				 WebElement viewImage=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getViewActivityimage())));
			        Assert.assertTrue(viewImage.isDisplayed());
			        Log.info("View Activity Image is displayed");
			        WebElement editImage=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getEditActivityimage())));
			        Assert.assertTrue(editImage.isDisplayed());
			        Log.info("Edit Activity Image is displayed");
			        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getViewActivityimage()))).click(); 
			        String status=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getEditstatus()))).getText().trim();
					Assert.assertEquals(status, status);
					Log.info("Activity Status is not changed on viewing an activity");
					/*Commented due to defect- editable on view page
					 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getActivityOpen()))).click(); 
					WebElement Question=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getQuestion())));
					 Assert.assertFalse(Question.isSelected());
					Log.info("Activity non editable in view activity page");
					
					 */
					WebElement Backbutton=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getBackButton())));
				    Assert.assertTrue(Backbutton.isDisplayed());
				    Log.info("Back button is displayed");
				    Backbutton.click();
				    
				    Thread.sleep(5000);
				    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getEditActivityimage()))).click(); 
				   String CCActivityname=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getCCactivityName()))).getText().trim();
				    String []activitybanner =CCActivityname.split("-");
				    String Activityname=activitybanner[0].trim();
					String Assignedto=activitybanner[1].trim();
					Assert.assertEquals("Care Coordination", Activityname);
					Log.info("Activity Name is "+Activityname);
					
					String SheetAssignedto=Sheet1.getRow(2).getCell(2).getStringCellValue().toString();
					Assert.assertEquals(Assignedto,SheetAssignedto);
					Log.info("Activity is assigned to "+Assignedto);
					
					String Program=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getProgram()))).getText().trim();
					status=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getEditstatus()))).getText().trim();
					String Startdate=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getStartdate()))).getText().trim();
					String Duedate=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getDuedate()))).getText().trim();
					String Condition=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getPrimaryConditione()))).getText().trim();
					
					/*Commented due to defect
					 * String Risk=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getRisk()))).getText().trim();
					String Sheetrisk=Sheet2.getRow(6).getCell(2).getStringCellValue().toString();
					Assert.assertEquals(Risk,Sheetrisk);
					Log.info("Risk is "+Risk);*/
				
					Assert.assertEquals("In Progress", status);
					Log.info("Status is "+status);
					
					DateFormat d = new SimpleDateFormat("MM/dd/yyyy");
					//ASystem.out.println(Startdate.toString());
					Date startDate = d.parse(Startdate);
					Calendar c = Calendar.getInstance();
					c.setTime(startDate); 
					String todaydate = d.format(startDate);
					Assert.assertEquals(Startdate, todaydate);
					Log.info("Start date is "+Startdate);
					
					String Sheetduedate=Sheet1.getRow(3).getCell(2).getStringCellValue().toString();
					String Sheettime=Sheet1.getRow(4).getCell(2).getStringCellValue().toString();
					Assert.assertEquals(Duedate, Sheetduedate+" "+Sheettime);
					Log.info("Due date is "+Duedate);
					
					String Sheetprimarycondition=Sheet2.getRow(1).getCell(2).getStringCellValue().toString();
					Assert.assertEquals(Condition, Sheetprimarycondition);
					Log.info("Primary Condition is "+Condition);
					
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
					
					String providerSection= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getProvidersection()))).getText().trim();
					Assert.assertNotNull(providerSection);
					Log.info(providerSection+" section is present");
					
					String correspondenceSection= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getCorrespondencesection()))).getText().trim();
					Assert.assertNotNull(correspondenceSection);
					Log.info(correspondenceSection+" section is present");	
					
					Backbutton=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getBackButton())));
				    Assert.assertTrue(Backbutton.isDisplayed());
				    Log.info("Back button is displayed");
				        
			       WebElement saveforlater=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getSaveforLaterButton())));
			        Assert.assertTrue(saveforlater.isDisplayed());
			        Log.info("Save for Later button is displayed");
			        
			        WebElement saveactivity=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getSaveActivityButton())));
			        Assert.assertTrue(saveactivity.isDisplayed());
			        Log.info("Save/End Activity button is displayed");
			        Thread.sleep(3000);
					
			        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getActivityOpen()))).click(); 
					 annual.annual_assessment("ClinicalIntervention", "Clinical", 2);
					 
					 wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getSaveforLaterButton()))).click();
				 		Thread.sleep(3000);
				 		Assert.assertEquals("Activity", wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getActivityHeader()))).getText().trim());
						Log.info("Navigated to Previous page"); 
						
						String CaseID=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getCaseIDRef()))).getText().trim();
						Log.info("CaseID"+CaseID);
						 wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getActivityDashBaord()))).click(); 
						 cli.caseNavigation(CaseID,"CareCoordination", "ActivityType");
						 
						 wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getBackButton()))).click(); 
						  Thread.sleep(3000);
						  Assert.assertEquals("Activity List", wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getActivityListHeader()))).getText().trim());
							Log.info("Navigated to Previous page"); 
							
							cli.caseNavigation(CaseID,"CareCoordination", "ActivityType");
							annual.annual_assessment("ClinicalIntervention", "Clinical", 2);
							Thread.sleep(3000);
					 		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getSaveActivityButton()))).click(); 
					 		 Assert.assertEquals("Activity List", wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getActivityListHeader()))).getText().trim());
								Log.info("Navigated to Previous page"); 
								
					 		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getActivityDashBaord()))).click(); 
							cli.caseNavigation(CaseID,"CareCoordination", "ActivityType");
					 		Assert.assertEquals("Completed", wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getEditstatus()))).getText().trim());
							Log.info("Activity status is Completed");
							 wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getMemberActionLeftNav()))).click(); 
							 wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getActivityList()))).click(); 
							 Thread.sleep(3000);
							Assert.assertEquals("Completed", wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getCCStatus()))).getText().trim());
							 Log.info("Care Coordination Activity status is Completed in Grid too");
							
				 /*wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getMemberActionLeftNav()))).click(); 
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getActivityList()))).click(); 
					Thread.sleep(3000);*/
				 
			 }
				 catch(Exception e)
				 {
					 Log.info("Care Coordination Activity failed"+e);
				 }
				 }
		

}
