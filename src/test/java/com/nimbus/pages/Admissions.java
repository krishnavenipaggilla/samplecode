package com.nimbus.pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertSame;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utility.ConfigReader;

public class Admissions {

	WebDriver driver;
	Logger Log;
	
	ConfigReader config = new ConfigReader();

	public Admissions(WebDriver ldriver) {
		this.driver = ldriver;
	}

	public Admissions(Logger lLog) {
		this.Log = lLog;
	}

	public void AdmissionsGridVerification() throws InterruptedException
	{
		try {
		Log = Logger.getLogger("Admissions.class");
		PropertyConfigurator.configure("log4j.properties");
		
		//Verify Grid Header and columns
		WebElement gridHeader = driver.findElement(By.xpath(config.AdmissionsHeader()));
		assertEquals(gridHeader.getText(), "Admissions");
		Log.info("The lable of the page is - " + gridHeader.getText());
		
		//Verifying Admissions Grid
		Log.info("Verifying the elements on the Admissions page grid");
		for(int i=0; i<GlobalValues.AdmissionsGrid.size();i++)
		{
				driver.findElement(By.xpath("//span[text()='"+GlobalValues.AdmissionsGrid.get(i)+"']"));
				Log.info("Verifying the "+ GlobalValues.AdmissionsGrid.get(i) + "- Grid columns");
		}
	
		boolean isGridHeaderPresent = gridHeader.isDisplayed();
		if(isGridHeaderPresent==true){
			Log.info("Admissions Grid is present");
		}else{
			Log.info("Admissions Grid is not present");
		}
		}
		catch (Exception e)
		{
			Log.info("Failed - " +e);
		}
	}
	
	public void AddAdmissions() throws Exception
	{
		try {
		Log = Logger.getLogger("Admissions.class");
		PropertyConfigurator.configure("log4j.properties");
		AnnualAssessment annual = new AnnualAssessment(driver);
	
		//Click Add Admission button
		WebElement addAdmission = driver.findElement(By.xpath(config.AddAdmissions()));
		if(addAdmission.getText().equals("Add Admission")) {
			Log.info("Click on Add Admission button in Admission page");
		} else {
			Log.info("Add Admission button is not present in Admission page");
		}
		addAdmission.click();
		Thread.sleep(3000);
		
		//Add Admission grid header verification
		WebElement GridHeaderVerifcation = driver.findElement(By.xpath(config.GridHeaderVerify()));
		assertEquals(GridHeaderVerifcation.getText(),"Add Admission");
		Log.info("Add Admissions page header is present");
		
		//verifying "Type" dropdown values
		WebElement TypeDropdown = driver.findElement(By.xpath(config.getTypeDropdown()));
		TypeDropdown.click();
		for(int i=0;i<GlobalValues.TypedropdownValues.size();i++)
		{
			driver.findElement(By.xpath("//p-dropdown//div[3]//li[" + (i+1) + "]//span"));
			Log.info("Verifying the "+ GlobalValues.TypedropdownValues.get(i) + "- value in the TYPE dropdown");
		}
		
		//verifying "Notification Date" value
		WebElement NotificationDateVerify = driver.findElement(By.xpath(config.getNotificationDate()));
		String NotificatioDateValue = NotificationDateVerify.getAttribute("value");
		
		// Getting System date value
		SimpleDateFormat dateFormat = new SimpleDateFormat(GlobalValues.StdDateformat.toString());
		DateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
        Date date1 = new Date();
		String date = DATE_FORMAT.format(date1);     
        assertEquals(NotificatioDateValue, date);
       if(NotificatioDateValue.equals(date)) {
    	   Log.info(" Notification Date is matching with System Date");
       }
       else {
    	   Log.info(" Notification Date is not matching with System Date");
       }
       driver.findElement(By.xpath(config.getProviderName())).click(); //cursor moving from "Type" field to "Provider" field
		
       //adding values to the Add Admission page fields
       Thread.sleep(3000);
		annual.annual_assessment("AnnualAssessments_CMDM", "Admissions", 2);
		Log.info("Admission added to the Grid");
		 driver.findElement(By.xpath(config.getProviderName())).click();
		 driver.findElement(By.xpath("//button[text()='Submit']")).click();
		
		WebElement ShowingValue = driver.findElement(By.xpath(config.getShowingValue()));
		Log.info("Admissions Grid - Showing values displaying as : " + ShowingValue.getText());
		
		}
		catch (Exception e)
		{
			Log.info("Failed - " +e);
		}
	}
	
	public void EditAdmission() throws Exception
	{
		Log = Logger.getLogger("Admission.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		
		Log.info("Edit/Update Admissions Grid"); 
		WebElement ClickEditAdmission = driver.findElement(By.xpath(config.ClickEditAdmission()));
		ClickEditAdmission.click(); //click Edit Admission button
		
		// Edit Admission Grid header verification
		WebElement gridHeader = driver.findElement(By.xpath(config.EditGridHeader()));
		assertEquals(gridHeader.getText(), "Edit Admission");
		if(gridHeader.isDisplayed())
		{
			Robot robot = new Robot();
			robot.delay(3000);
			WebElement Edit_ActualDischargeDate = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getEdit_ActualDischargeDate())));
			Edit_ActualDischargeDate.click(); //editing Actual Discharge Date to calculate days in hospital with system date
			robot.keyPress(KeyEvent.VK_CONTROL);    
            robot.keyPress(KeyEvent.VK_A);
            robot.keyRelease(KeyEvent.VK_A);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            Thread.sleep(1000);
            robot.keyPress(KeyEvent.VK_DELETE);
            robot.keyRelease(KeyEvent.VK_DELETE);
            driver.findElement(By.xpath("//label[contains(text(),'Provider Phone')]")).click();
            Thread.sleep(3000);
            WebElement Edit_Submit = driver.findElement(By.xpath("//button[text()='Submit']"));
            Edit_Submit.click();
            Thread.sleep(3000);
	//		WebElement DaysInHospital = driver.findElement(By.xpath("//ancestor::label[contains(text(),'Days in Hospital')]//following-sibling::p"));
	//		System.out.println(DaysInHospital.getText());
			
			Log.info("Admission values Updated");
		}
		else
		{
			Log.info("Admission Grid not updated");
		}
		
	}
	
	public void AdmissionSecondRowVerification() throws Exception 
	{
		try {
		Log = Logger.getLogger("Admission.class");
		PropertyConfigurator.configure("log4j.properties");
		AnnualAssessment annual = new AnnualAssessment(driver);
				
		Log.info("Secondary Row verification in Admissions");
		WebElement AdmissionView = driver.findElement(By.xpath(config.getAdmissionView()));
		AdmissionView.click();
		
		// verifying secondary row label name
		for(int i=0;i<GlobalValues.AdmissionsSecondaryRowLabels.size();i++)
		{
			
			driver.findElement(By.xpath("//nm-card-details/div//span//div//label[text()='"+GlobalValues.AdmissionsSecondaryRowLabels.get(i)+"']"));
			Log.info("Verifying the "+ GlobalValues.AdmissionsSecondaryRowLabels.get(i) + "- label in Admission Second row");
		}
		}
		catch (Exception e)
		{
			Log.info("Failed - " +e);
		}
			
	}
	
	public void AdmissionDaysInHospital() throws ParseException
	{
		try {
		Log = Logger.getLogger("Admission.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver,20);
		
		// Getting System date value
		SimpleDateFormat dateFormat = new SimpleDateFormat(GlobalValues.StdDateformat.toString());
		DateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
        Date date1 = new Date();
		String date = DATE_FORMAT.format(date1);
  
		Log.info("Calculate Days in Hospital - Admissions");
		
		WebElement Admission_AdmitDate = driver.findElement((By.xpath(config.getAdmissionAdmitDate())));
		WebElement Admission_ActualDischargeDate = driver.findElement((By.xpath(config.getAdmissionActualDischargeDate())));

		String AdmitDateText = Admission_AdmitDate.getText().toString();
		String ActualDischargeDateText = Admission_ActualDischargeDate.getText().toString();
		
		WebElement DaysInHospital = driver.findElement(By.xpath(config.getDaysInHospital()));
		String DaysInHospitalText = DaysInHospital.getText();
		
		if( (Admission_ActualDischargeDate.getText()).isEmpty() )
		{
			Log.info("Actual Discharge Date is not Present");
			try {
				   java.util.Date dateBefore = dateFormat.parse(AdmitDateText);
				   java.util.Date dateAfter = dateFormat.parse(date);
	
		       	   String DateBefore = DATE_FORMAT.format(dateBefore);
		       	   String DateAfter = DATE_FORMAT.format(dateAfter);
		       	   Date dateA = dateFormat.parse(DateAfter);
		       	   Date dateB = dateFormat.parse(DateBefore);
		       	   long difference = dateA.getTime() - dateB.getTime();
			       int daysBetween = (int) ((difference / (1000*60*60*24))+1);
			       String Str = String.valueOf(daysBetween);
			       Assert.assertEquals(DaysInHospitalText, Str);
			       
			       Log.info("Days in Hospital matched without Actual Discharge Date");
			     
			     } catch (Exception e) {
			       e.printStackTrace();
			 }	
		} 
		else 
		{
			Log.info("Actual Discharge Date is Present");
			try {
				   java.util.Date dateBefore = dateFormat.parse(AdmitDateText);
			       java.util.Date dateAfter = dateFormat.parse(ActualDischargeDateText);
			       long difference = dateAfter.getTime() - dateBefore.getTime();
			       int daysBetween = (int) ((difference / (1000*60*60*24))+1);
		     
			       String Str = String.valueOf(daysBetween);
			       Assert.assertEquals(DaysInHospitalText, Str);
			       Log.info("Days in Hospital matched With Actual Discharge Date");
			     
			     } catch (Exception e) {
			       e.printStackTrace();
			 }
		}
		}
		catch (Exception e)
		{
			Log.info("Failed - " +e);
			e.printStackTrace();
		}
	}
} //Admissions method









