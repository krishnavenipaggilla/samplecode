package com.nimbus.pages;

import java.io.File;
import java.io.FileInputStream;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import excelReader.DataReaderExcel;
import utility.ConfigReader;

public class MgmtReasonSubSection {

	ConfigReader config = new ConfigReader();
	DataReaderExcel DataRead = new DataReaderExcel();
	
	WebDriver driver;
	Logger Log;
	
	public MgmtReasonSubSection(WebDriver ldriver){
		this.driver=ldriver;
	}
	
	public MgmtReasonSubSection(Logger lLog){
		this.Log=lLog;
	}
	
public void mgmtreason(String TaskName) throws Exception
	{
	
	Log = Logger.getLogger("MgmtReasonSubSection.class");
	PropertyConfigurator.configure("log4j.properties");
	  AnnualAssessment annual = new AnnualAssessment(driver);
	  Clinical cli=new Clinical(driver);
	  File sour = new File("./TestData/AddMgmtReason.xlsx");
	  FileInputStream fiss = new FileInputStream(sour);
	  XSSFWorkbook wb = new XSSFWorkbook(fiss);
	  ActivityPage addActivity = new ActivityPage(driver);
	  
	  XSSFSheet Sheetccl = wb.getSheet("CaseClosure");
	  XSSFSheet Sheet2 = wb.getSheet("EditMgmt");
	  ConditionSubsection condition = new ConditionSubsection(driver);

	  try
		 {
		  	WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		  	Thread.sleep(1000);
		  	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getManagemtLeftNav()))).click(); 
		  	Thread.sleep(3000);

		  	String LeftNavReason= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getLeftNavReason()))).getText().trim();
		  	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getMemberActionLeftNav()))).click(); 
			Thread.sleep(1000);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getActivityList()))).click(); 
			Thread.sleep(3000);
			
			addActivity.editInitialOutreachActivity(TaskName);
			Thread.sleep(5000);
			
		
			String mgmtSection= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getMgmtsection()))).getText().trim();
			try
			{
				Assert.assertNotNull(mgmtSection);
				Log.info("Passed - NIM-17612 : Display Management reasons subsection for Case closure Activity" + '\n' + "GIVEN: User Adds Case Closure Activity" + '\n' + "THEN: User should see 'Management reasons' subsection for that activity");
				
			}
			catch(Exception e)
			 {
			  Log.info("Failed - NIM-17612 : Display Management reasons subsection for Case closure Activity");
			 }
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getMgmtOpen()))).click();
			Thread.sleep(3000);
			String gridtAllText = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getMgmtAdded()))).getText().toString();
			Assert.assertTrue(gridtAllText.contains(LeftNavReason));
			Log.info("Management Reason Added from left nav");
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getAddManagementgmtReason()))).click();
			Thread.sleep(1000);
			
			annual.annual_assessment("AddMgmtReason", "CaseClosure", 2);
			Thread.sleep(3000);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[text()='Submit'])[2]"))).click();
			Thread.sleep(3000);
			String SheetM=Sheetccl.getRow(2).getCell(2).getStringCellValue().toString();
			gridtAllText = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getMgmtAdded()))).getText().toString();
			Assert.assertTrue(gridtAllText.contains(SheetM));
			Log.info("Management Reason Added");
			
			
			//Edit Managemnet Reason
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@title='"+SheetM+"']//parent::td//following-sibling::td//button"))).click();
			annual.annual_assessment("AddMgmtReason", "EditMgmt", 2);
			Thread.sleep(5000);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[text()='Submit'])[2]"))).click();
			Thread.sleep(5000);
						
			String SheetStatusReason=Sheet2.getRow(1).getCell(2).getStringCellValue().toString();
			String StatusReason=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@title='"+SheetM+"']//parent::td//following-sibling::td)[2]"))).getText().trim();
			Assert.assertEquals(StatusReason, SheetStatusReason);
			Log.info("Management Reason Updated Successfully");

						
			 //validate management reasons added in Left Nav
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getManagemtLeftNav()))).click(); 
		  	Thread.sleep(3000);
			 String SheetM1=Sheetccl.getRow(2).getCell(2).getStringCellValue().toString();
			 
			 gridtAllText = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getMgmtAdded()))).getText().toString();
			 	Log.info("gridtAllText::"+gridtAllText);
			 	Log.info("SheetM1::"+SheetM1);
			 	
			 	Assert.assertTrue(gridtAllText.contains(SheetM1));
				Log.info("Management Reason Added from subactivities");
				
				Log.info("Passed - NIM-18745 : Management Reason: Show management reason records in the case closure acvitity" + '\n' + "GIVEN: The case closure activity" + '\n' + "THEN: User shall see the newly added/updated record in the mgmt reason sub-section grid");
	
  
		 }
	  catch(Exception e)
		 {
		  Log.info("Failed - NIM-18745 : Management Reason: Show management reason records in the case closure acvitity");
		  Log.info("Management Reason Subsection failed"+e);
		 }
 }


}



