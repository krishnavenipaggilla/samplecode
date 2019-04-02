package com.nimbus.pages;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import excelReader.DataReaderExcel;
import utility.ConfigReader;

public class SupervisorDashboard {
	WebDriver driver;
	Logger Log;
	
	ConfigReader config = new ConfigReader();
	DataReaderExcel DataRead = new DataReaderExcel();
	
	public SupervisorDashboard(WebDriver ldriver){
		this.driver=ldriver;

	}
	
	public SupervisorDashboard(Logger lLog){
		this.Log=lLog;
	
	}
	
	
	public void assignCase(String caseId, String assignee) throws Exception{
		Log = Logger.getLogger("ActivityPage.class");
		PropertyConfigurator.configure("log4j.properties");
		try{
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//label[text()='Case Assignment']/preceding-sibling::div[contains(@class,'radiobutton')]")))).click();
			Log.info("User clicked on Case Assignment radio");
			
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[@title='show grid filters']")))).click();
			Log.info("User opened the filter");
			
			driver.findElement(By.xpath("//div[@class='scrollGrid']//thead/tr[2]/th[2]//input")).sendKeys(caseId+Keys.TAB);
			Log.info("User filter with caseId as "+caseId);
			
			Thread.sleep(4000);
			
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("(//div[@class='scrollGrid']//tbody//div[contains(@class,'chkbox')])[1]")))).click();
			Log.info("User checks the checkbox of the case id");
			Thread.sleep(3000);
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[contains(text(),'Assign Case(s)')]")))).click();
			Log.info("User clicks on Assign case button");
			
			String assignOwnerBtnXpath = "//span[contains(text(),'"+assignee+"')]/../../following-sibling::td[@class=\"imageColumn ng-star-inserted\"]/span";
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(assignOwnerBtnXpath)))).click();
			Log.info("User assigs case to "+ assignee);
			Thread.sleep(5000);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
}
