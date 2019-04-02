package com.nimbus.pages;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.ConfigReader;

public class ChangeHistoryPage {

	
	
	ConfigReader config = new ConfigReader();

	WebDriver driver;
	Logger Log;

	public ChangeHistoryPage(WebDriver ldriver) {
		this.driver = ldriver;
	}

	public ChangeHistoryPage(Logger lLog) {
		this.Log = lLog;
	}

	public void Dropdown() throws Exception{
	
		try
		{
			Log = Logger.getLogger("ChangeHistoryPage.class");
			PropertyConfigurator.configure("log4j.properties");
			
			WebDriverWait wait =new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.Dropdown()))).click();
			Log.info("User clicked on dropdown");
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.ContactHistoryInDropdown()))).click();
			Log.info("User Selected contact History in dropdown");
}
		catch(Exception e)
			{
			System.out.println( " : " +e);
			}
		
	}
	
	public void ContactHistoryData() throws Exception{
		
		try
		{
			Log = Logger.getLogger("ChangeHistoryPage.class");
			PropertyConfigurator.configure("log4j.properties");
			
			ArrayList<String> expectedal = new ArrayList<String>();
			expectedal.add("NICU");
			expectedal.add("Initial Outreach");
			expectedal.add("Yes - HIPAA Verified");
			//expectedal.add("Date and Time");
			expectedal.add("Alabaster Snowball");
			expectedal.add("Case Manager");
			
			ArrayList<String> actualal = new ArrayList<String>();
			
			actualal.add(driver.findElement(By.xpath(config.Program())).getText());
			actualal.add(driver.findElement(By.xpath(config.ActivityTypeColumn())).getText());
			actualal.add(driver.findElement(By.xpath(config.MemberReached())).getText());
			//actualal.add(driver.findElement(By.xpath(config.DateandTimeCompleted())).getText());
			actualal.add(driver.findElement(By.xpath(config.PerformedBy())).getText());
			actualal.add(driver.findElement(By.xpath(config.Role())).getText());
			
			System.out.println(expectedal);
			System.out.println(actualal);
	

}
		
		catch(Exception e)
			{
			System.out.println( " : " +e);
			}
	}	
}	

