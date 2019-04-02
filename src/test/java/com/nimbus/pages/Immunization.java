package com.nimbus.pages;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import excelReader.DataReaderExcel;
import utility.ConfigReader;

public class Immunization {

	ConfigReader config = new ConfigReader();
	DataReaderExcel DataRead = new DataReaderExcel();
	
	WebDriver driver;
	Logger Log;
	
	public Immunization(WebDriver ldriver){
		this.driver=ldriver;
	}
	
	public Immunization(Logger lLog){
		this.Log=lLog;
	}
	
public void immunizationLeftnav() throws Exception
	{
	
  		Log = Logger.getLogger("Immunization.class");
		PropertyConfigurator.configure("log4j.properties");
		 Log.info("Immunization Assignment Started");
		 Robot robot = new Robot();
		 
		 DashboardFunctions df = new DashboardFunctions(driver);
		 LeftNavigationLink lnk = new LeftNavigationLink(driver);
		// lnk.clickHealthChart();
		 lnk.clickImmunizations();
		 try
			{  
	        WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
//	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getImmunizationLeftNav()))).click();
//	        Log.info("Clicked on Immunizations");
	        AnnualAssessment annual = new AnnualAssessment(driver);
	        
	        try
		      {
				WebElement element = driver.findElement(By.xpath(config.getImmunizationHeader()));
				String headertext = element.getText().toString();
				Assert.assertEquals("Immunizations", headertext);
				Log.info("Grid Header is Immunizations");
		      }
			catch(Exception Ex)
	      
			{
				Log.info("Grid Header is not Immunizations");
			}
	        	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getAddImmunizationbutton()))).click();
	        	Log.info("Clicked on Add Immunization");
	        	/*annual.annual_assessment("AddImmunizations","Back",2);
				Log.info("Clicked on Back");*/
				
	        	Log.info("Facility Name is non editable");
				annual.annual_assessment("AddImmunizations","Submit",2);
				Log.info("Clicked on Submit");
				List<String> gridColumnNames = df.getGridColumnNames();
				Boolean result= df.validateGridColumnNames(GlobalValues.Immunizationgrid_headers,gridColumnNames);
				
				if (result) {
					Assert.assertTrue(true);
					
					Log.info("The Expected Column names and Actual Column names of Grid have matched");
				}
				else {
					Assert.assertTrue(false);
					
					Log.info("The Expected Column names and Actual Column names of Grid did not match");
				}
				
				try{
					Thread.sleep(3000);
					Log.info("Verifying grid data is present or not");
					WebElement Visibilityofelement = wait.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath("(//span[normalize-space()='Immunization Name']//parent::th//following::td//following::td)[1]")));
					Log.info("Grid is having members to Validate");
				}
				catch(Exception e)
				{
					Log.info("No Data in Grid to validate");
					return;
				}
				
				try
				{
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getUpdateImmunization()))).click();
		        	Log.info("Clicked on Update Immunization");
		        	
		        	WebElement immunizationname=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getImmunizationnamedropId())));
					Assert.assertFalse(immunizationname.isSelected());
					//Assert.assertFalse(immunizationname.isEnabled());
					Log.info("Immunization Name is non editable in update Immunization page");
					
					WebElement Refused=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getRefusedId())));
					 Assert.assertFalse(Refused.isSelected());
					Log.info("Refused Yes/No is non editable in update Immunization page");
					
					WebElement ReasonforRefusal=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getReasonforRefusalID())));
					Assert.assertFalse(ReasonforRefusal.isSelected());
					Log.info("Reason for Refusal is non editable in update Immunization page");
					
					Assert.assertNotNull(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getFacilityNameID()))).getText());
					Log.info("Facility Name is non editable in update Immunization page");
					
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getDateAdministered()))).click();
		        	 robot.keyPress(KeyEvent.VK_CONTROL);	 
					 robot.keyPress(KeyEvent.VK_A);
					 robot.keyRelease(KeyEvent.VK_A);
					 robot.keyRelease(KeyEvent.VK_CONTROL);
					 Thread.sleep(1000);
					 robot.keyPress(KeyEvent.VK_DELETE);
					 robot.keyRelease(KeyEvent.VK_DELETE);
		        	annual.annual_assessment("AddImmunizations","Update",2);
		        	Log.info("Verified Immunization Update");
				}
				catch(Exception e)
				{
					Log.info("No Immunization to update ");
					e.printStackTrace();
				}
				
	        }
		 catch(Exception ex)
		 {
			 Log.info("Immunization failed"+ex);
			 ex.printStackTrace();
		 }
}
}
