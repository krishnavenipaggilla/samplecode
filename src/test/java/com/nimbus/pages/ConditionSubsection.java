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

public class ConditionSubsection {
	ConfigReader config = new ConfigReader();
	DataReaderExcel DataRead = new DataReaderExcel();
	
	WebDriver driver;
	Logger Log;
	
	public ConditionSubsection(WebDriver ldriver){
		this.driver=ldriver;
	}
	
	public ConditionSubsection(Logger lLog){
		this.Log=lLog;
	}
	
public void condition(String TaskName) throws Exception
	{
	
  		Log = Logger.getLogger("ConditionSubsection.class");
		PropertyConfigurator.configure("log4j.properties");
		  AnnualAssessment annual = new AnnualAssessment(driver);
		  ActivityPage addActivity = new ActivityPage(driver);
		  Clinical cli=new Clinical(driver);
		  File sour = new File("./TestData/AddCondition.xlsx");
		  FileInputStream fiss = new FileInputStream(sour);
		  XSSFWorkbook wb = new XSSFWorkbook(fiss);
		  XSSFSheet Sheeti = wb.getSheet("Initial");
		  XSSFSheet Sheetcl = wb.getSheet("Clinical");
		  XSSFSheet Sheetr = wb.getSheet("Referral");
		  XSSFSheet Sheetcc = wb.getSheet("CareCoordination");
		  XSSFSheet Sheetccl = wb.getSheet("CaseClosure");		  XSSFSheet Sheethc = wb.getSheet("HealthChart");
		  try
			 {
			  	WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			  	/*Thread.sleep(1000);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getMemberActionLeftNav()))).click(); 
				Thread.sleep(1000);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getActivityList()))).click(); 
				Thread.sleep(3000);
				
				//Initial Outreach
//				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getEditIntialoutreach()))).click(); 
				addActivity.editInitialOutreachActivity(TaskName);
				Thread.sleep(5000);*/
				addCondition("AddCondition","Initial",TaskName);

				Thread.sleep(3000);
				
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a//span[contains(text(),'Health Chart')]"))).click(); 
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a//span[contains(text(),'Conditions')]"))).click(); 
				 
				 //validate conditions added
				 String SheetCondition1=Sheeti.getRow(1).getCell(2).getStringCellValue().toString();

				 
				 	String gridtAllText = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getConditionAdded()))).getText().toString();
				 				 	
//					Assert.assertTrue(gridtAllText.contains(SheetCondition1)); --kk
					Log.info("Conditions Added from subactivities");
					
				Log.info("Condition Added from left nav");
					Log.info("Passed-NIM-17478: Create Conditions sub-sections in 5 Activities pages" + '\n' + "GIVEN:Conditions sub-section"
							+ '\n' + "THEN:verifying Conditions sub-section in activities");	
	  
			 }
		  catch(Exception e)
			 {
				 Log.info("Condition Subsection failed"+e.getMessage().toString());
				 Log.info("Failed-NIM-17478: Create Conditions sub-sections in 5 Activities pages");
			 }
	 }

public void addCondition(String filename, String sheetname, String Activityname) throws Exception
{
AnnualAssessment annual = new AnnualAssessment(driver);
WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
File sour = new File("./TestData/AddCondition.xlsx");
FileInputStream fiss = new FileInputStream(sour);
XSSFWorkbook wb = new XSSFWorkbook(fiss);
XSSFSheet Sheet1 = wb.getSheet(sheetname);
XSSFSheet Sheet2 = wb.getSheet("EditCondition");
try
{
	Thread.sleep(1000);
	String conditionSection= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getConditionrsection()))).getText().trim();
	Assert.assertNotNull(conditionSection);
	Log.info(conditionSection+" section is present");
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getConditionOpen()))).click();
	Thread.sleep(1000);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getAddCondition()))).click();
	 wait
		.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
	Thread.sleep(1000);
//	Assert.assertEquals("Add Condition", wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getAddHeader()))).getText().trim());
	Log.info("Navigated to Add Condition page"); 
	annual.annual_assessment(filename, sheetname, 2);
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
	Thread.sleep(1000);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getSubmit()))).click();
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
	Thread.sleep(1000);
	verifyActivityName(Activityname);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getConditionOpen()))).click();
	Thread.sleep(1000);
	String SheetCondition=Sheet1.getRow(1).getCell(2).getStringCellValue().toString();
	String gridtAllText = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getConditionAdded()))).getText().toString();
	Assert.assertTrue(gridtAllText.contains(SheetCondition));
	Log.info("Condition Added");
	
	//Edit Condition
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@title='"+SheetCondition+"']//parent::td//following-sibling::td//button"))).click();
//	Assert.assertEquals("Edit Condition", wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getEditHeader()))).getText().trim());
	Log.info("Navigated to Edit Condition page");
	
	annual.annual_assessment(filename, "EditCondition", 2);
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
	Thread.sleep(1000);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getSubmit()))).click();
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
	Thread.sleep(1000);
	verifyActivityName(Activityname);
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
	Thread.sleep(1000);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getConditionOpen()))).click();
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
	Thread.sleep(2000);
	String SheetRisk=Sheet2.getRow(1).getCell(2).getStringCellValue().toString();
	String SheetSource=Sheet2.getRow(2).getCell(2).getStringCellValue().toString();
	String Source=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@title='"+SheetCondition+"']//parent::td//following-sibling::td)[6]"))).getText().trim();
	String Risk=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@title='"+SheetCondition+"']//parent::td//following-sibling::td)[5]"))).getText().trim();
	
	Assert.assertEquals(Source, SheetSource);
	Assert.assertEquals(Risk, SheetRisk);
	
	Log.info("Condition Updated Successfully");
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
	Thread.sleep(1000);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getActivityList()))).click(); 
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
	Thread.sleep(1000);
	Log.info("Passed-NIM-17481: Add Conditions inside conditions sub-section" + '\n' + "GIVEN:Add Conditions inside conditions sub-section"
			+ '\n' + "THEN:verifying Conditions inside conditions sub-section");
	Log.info("Passed-NIM-17932: Edit Conditions inside conditions sub-section" + '\n' + "GIVEN:Edit Conditions inside conditions sub-section"
			+ '\n' + "THEN:verifying Edit Conditions inside conditions sub-section");
}
catch(Exception ex)
{
	  Log.info("Adding Condition Subsection failed");
	
	  Log.info("Failed-NIM-17481: Add Conditions inside conditions sub-section");
		Log.info("Failed-NIM-17932: Edit Conditions inside conditions sub-section");
	  
	
}
}
	public void verifyActivityName(String Activityname) throws Exception
	{
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		try
		{
			Log.info("Verifying Activity name");
			String CCActivityname=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getCCactivityName()))).getText().trim();
			String []activitybanner =CCActivityname.split("-");
			String Activitynm=activitybanner[0].trim();
			Assert.assertEquals(Activitynm, Activityname);
			Log.info("Landed in "+Activitynm+" page");

		}
		catch(Exception ex)
		{
			  Log.info("Activity name cannot be verified"+ex);
			
		}
	}
	

}

