package com.nimbus.pages;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import excelReader.DataReaderExcel;
import utility.ConfigReader;

public class ContactDetailsPage {

	WebDriver driver;
	Logger Log;
	
	ConfigReader config = new ConfigReader();
	DataReaderExcel DataRead = new DataReaderExcel();
	
	public ContactDetailsPage(WebDriver ldriver){
		this.driver=ldriver;
	}
	public ContactDetailsPage(Logger lLog){
		this.Log=lLog;
	}
	public ContactDetailsPage(WebDriver ldriver, Logger lLog){
		this.driver=ldriver;
		this.Log=lLog;
	}
	public void addAContact(String activityType, String contactOutcome) throws Exception{
		Log = Logger.getLogger("ActivityPage.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		driver.findElement(By.xpath("//button[@title='Record a Contact']")).click();
		Log.info("user click on add contact");
		
		
		driver.findElement(By.xpath("//label[contains(text(),'Purpose of Call')]/../following-sibling::p-dropdown")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(),'Purpose of Call')]/../following-sibling::p-dropdown//span[text()='"+activityType+"']"))).click();
		Log.info("Activity type is selected with name as "+activityType);
		
		String callDirection = "Incoming";
		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(),'Call Direction')]//following-sibling::p-dropdown"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(),'Call Direction')]/../following-sibling::p-dropdown//label"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(),'Call Direction')]/../following-sibling::p-dropdown//span[text()='"+callDirection+"']"))).click();
		Log.info("Call Direction is selected with name as "+callDirection);
		
		/*String callDirection = "Incoming";
		WebElement element = driver.findElement(By.xpath("//label[contains(text(),'Call Direction')]//following-sibling::p-dropdown"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(),'Call Direction')]//following-sibling::p-dropdown//span[text()='"+callDirection+"']"))).click();
		Log.info("Call Direction is selected with name as "+callDirection);*/
		
		String contactType = "Caregiver";
		driver.findElement(By.xpath("//label[contains(text(),'Contact Type')]/../following-sibling::p-dropdown")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(),'Contact Type')]/../following-sibling::p-dropdown//span[text()='"+contactType+"']"))).click();
		Log.info("Contact Type is selected with name as "+contactType);
		
		driver.findElement(By.xpath("//input[@id='contactName']")).sendKeys("Abcd");
		Log.info("contact name is entered");
		
		driver.findElement(By.id("contactPhone")).sendKeys("913-456-7890");
		Log.info("Contact Phone is entered");
		
		String communicationType = "Dialer";
		driver.findElement(By.xpath("//label[contains(text(),'Communication Type')]/../following-sibling::p-dropdown")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(),'Communication Type')]/../following-sibling::p-dropdown//span[text()='"+communicationType+"']"))).click();
		Log.info("Communication Type is selected with name as "+communicationType);
		
		driver.findElement(By.xpath("//label[contains(text(),'Contact Outcome')]/../following-sibling::p-dropdown")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(),'Contact Outcome')]/../following-sibling::p-dropdown//span[text()='"+contactOutcome+"']"))).click();
		Log.info("Contact Outcome is selected with name as "+contactOutcome);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Submit Contact']"))).click();
		Log.info("Contact details has been submitted");
		Thread.sleep(3000);
		
	}
	
	
}
