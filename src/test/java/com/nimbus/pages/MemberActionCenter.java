package com.nimbus.pages;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import excelReader.DataReaderExcel;
import utility.ConfigReader;

public class MemberActionCenter {
	WebDriver driver;
	Logger Log;
	
	ConfigReader config = new ConfigReader();
	DataReaderExcel DataRead = new DataReaderExcel();
	
	public MemberActionCenter(WebDriver ldriver){
		this.driver=ldriver;
	}
	public MemberActionCenter(Logger lLog){
		this.Log=lLog;
	}
	public MemberActionCenter(WebDriver ldriver, Logger lLog){
		this.driver=ldriver;
		this.Log=lLog;
	}
	
	public void addActivity(String activityType) throws Exception{
		Log = Logger.getLogger("ActivityPage.class");
		PropertyConfigurator.configure("log4j.properties");
		try{
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			Log.info("user is now in the activity page");
			
			driver.findElement(By.xpath("//*[@id=\"actionTray\"]/nm-button[2]/button")).click();
			Log.info("user click on add activity");
			
			Log.info("checking for Activity Status History");
			
			driver.findElement(By.xpath("//label[contains(text(),'Activity Type')]/../following-sibling::p-dropdown")).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(),'Activity Type')]/../following-sibling::p-dropdown//span[text()='"+activityType+"']"))).click();
			Log.info("Activity type is selected with name as "+activityType);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//label[contains(text(),'Assigned To')]/../following-sibling::p-dropdown")).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(),'Assigned To')]/../following-sibling::p-dropdown//div[@class='ui-dropdown-items-wrapper']//li[16]//span"))).click();
			Log.info("Assigned To dropdown is selected");
			
			driver.findElement(By.xpath("//p-calendar/span/button/span[1]")).click();
	// adding 	
			
			if(activityType.equalsIgnoreCase("Clinical Intervention")){
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(),'Activity Date')]//parent::nm-input-label//following-sibling::p-calendar/span/input"))).sendKeys("11/10/2019");
			Log.info("Activity Date is selected as current date");
			}
			
			else if(activityType.equalsIgnoreCase("Case Closure")){
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(),'Activity Date')]//parent::nm-input-label//following-sibling::p-calendar/span/input"))).sendKeys("11/11/2019");
				Log.info("Activity Date is selected as current date");
			}
			
			else if(activityType.equalsIgnoreCase("Referral")){
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(),'Activity Date')]//parent::nm-input-label//following-sibling::p-calendar/span/input"))).sendKeys("11/12/2019");
				Log.info("Activity Date is selected as current date");
			}
			
			else if(activityType.equalsIgnoreCase("Consultation")){
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(),'Activity Date')]//parent::nm-input-label//following-sibling::p-calendar/span/input"))).sendKeys("11/13/2019");
				Log.info("Activity Date is selected as current date");
			}
			
			else if(activityType.equalsIgnoreCase("Care Coordination")){
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(),'Activity Date')]//parent::nm-input-label//following-sibling::p-calendar/span/input"))).sendKeys("11/14/2019");
				Log.info("Activity Date is selected as current date");
			}
			
			else if(activityType.equalsIgnoreCase("Case Review")){
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(),'Activity Date')]//parent::nm-input-label//following-sibling::p-calendar/span/input"))).sendKeys("11/15/2019");
				Log.info("Activity Date is selected as current date");
			}
			
					
			
			if(!(activityType.equalsIgnoreCase("Case Review"))){
				
				driver.findElement(By.xpath("//label[contains(text(),'Activity Time')]/../following-sibling::p-dropdown")).click();
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p-dropdown//span[contains(text(),'01:00 AM')]"))).click();
				Log.info("Activity Time is selected as current time");
				}
//			driver.findElement(By.xpath("//label[contains(text(),'Best Time To Call')]//following-sibling::p-dropdown")).click();
//			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(),'Best Time To Call')]//following-sibling::p-dropdown//div[@class='ui-dropdown-items-wrapper']//li[1]//span"))).click();
//			Log.info("Best Time To Call dropdown is selected");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
			Thread.sleep(4000);
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Submit']"))).click();
			Log.info("Activity has been submitted");
			
			Thread.sleep(6000);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
}
