/**
 * 
 */
package com.nimbus.pages;


import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;

import excelReader.DataReaderExcel;
import utility.ConfigReader;
import utility.UtilityClass;



/*
 * Class Name : LoginPage

 * Description:	Logging into application using diff roles
 */
public class LoginPage {
	

	WebDriver driver;
	Logger Log;
	
	ConfigReader config = new ConfigReader();
	DataReaderExcel DataRead = new DataReaderExcel();
	
	public LoginPage(WebDriver ldriver){
		this.driver=ldriver;

	}
	
	public LoginPage(Logger lLog){
		this.Log=lLog;
	}

	
	public void login_Supervisor() throws Exception 
	{
		
			Log = Logger.getLogger("LoginPage.class");
			PropertyConfigurator.configure("log4j.properties");
			
			Log.info("Given-The login screen");
			Log.info("When-A user successfully enters the user name and password");
			Log.info("Then-User should be able to login");
			    try
			    {
		    	//driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
				
				JavascriptExecutor js = (JavascriptExecutor)driver;
				
				WebDriverWait wait =new WebDriverWait(driver, 20);
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getLoginUserNameWebElement())));
				Boolean bool = js.executeScript("return document.readyState").equals("complete");
			    Log.info("Page completely loaded "+bool);
			    
			    //Enter UserName
			    WebElement LoginUserName = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getLoginUserNameWebElement())));
			    LoginUserName.sendKeys(DataRead.Supervisor_User);
				Log.info("Enter user name");
	
				//Enter Password
				WebElement LoginPassword = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getLoginPasswordWebElement())));
				LoginPassword.sendKeys(DataRead.Supervisor_Pass);
				Log.info("Enter password");
				
				//Click on Login Button 
				WebElement LoginSubmit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getLoginButtonWebElement())));
				LoginSubmit.click();
				Log.info("Click on login button");
				
			    }
			    catch(Exception ex)
			    {
			    	Log.info("Page Not loaded completely / Object is not Visible");
			    }
		    }
	
	public void login_CaseManager() throws Exception 
	{
		
			Log = Logger.getLogger("LoginPage.class");
			PropertyConfigurator.configure("log4j.properties");
			
			Log.info("Given - The login screen");
			Log.info("When - A user successfully enters the user name and password");
			Log.info("Then - User should be able to login");
			    try
			    {
		    	//driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
				
				JavascriptExecutor js = (JavascriptExecutor)driver;
				
				WebDriverWait wait =new WebDriverWait(driver, 20);
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getLoginUserNameWebElement())));
				Boolean bool = js.executeScript("return document.readyState").equals("complete");
			    Log.info("Page completely loaded "+bool);
			    
			    //Enter UserName
			    WebElement LoginUserName = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getLoginUserNameWebElement())));
			    LoginUserName.sendKeys(DataRead.CaseManager_User);
				Log.info("Enter user name");
	
				//Enter Password
				WebElement LoginPassword = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getLoginPasswordWebElement())));
				LoginPassword.sendKeys(DataRead.CaseManager_Pass);
				Log.info("Enter password");
				
				//Click on Login Button 
				WebElement LoginSubmit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getLoginButtonWebElement())));
				LoginSubmit.click();
				Log.info("Click on login button");
				
			    }
			    catch(Exception ex)
			    {
			    	Log.info("Page Not loaded completely / Object is not Visible");
			    }
		    }
	
	public void login_CaseSpecialist() throws Exception 
	{
		
			Log = Logger.getLogger("LoginPage.class");
			PropertyConfigurator.configure("log4j.properties");
			
			Log.info("User Story - 456"+ " Give-The login screen");
			Log.info("When-A user successfully enters the user name and password");
			Log.info("Then-User should be able to login");
			    try
			    {
		    	//driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
				
				JavascriptExecutor js = (JavascriptExecutor)driver;
				
				WebDriverWait wait =new WebDriverWait(driver, 20);
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getLoginUserNameWebElement())));
				Boolean bool = js.executeScript("return document.readyState").equals("complete");
			    Log.info("Page completely loaded "+bool);
			    
			    //Enter UserName
			    WebElement LoginUserName = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getLoginUserNameWebElement())));
			    LoginUserName.sendKeys(DataRead.CaseSpecialist_User);
				Log.info("Enter user name");
	
				//Enter Password
				WebElement LoginPassword = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getLoginPasswordWebElement())));
				LoginPassword.sendKeys(DataRead.CaseSpecialist_Pass);
				Log.info("Enter password");
				
				//Click on Login Button 
				WebElement LoginSubmit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getLoginButtonWebElement())));
				LoginSubmit.click();
				Log.info("Click on login button");
				
			    }
			    catch(Exception ex)
			    {
			    	Log.info("Page Not loaded completely / Object is not Visible");
			    }
		    }
	
	public void login_EnrollmentSpecialist() throws Exception 
	{
		
			Log = Logger.getLogger("LoginPage.class");
			PropertyConfigurator.configure("log4j.properties");
			
			Log.info("User Story - 456"+ " Give-The login screen");
			Log.info("When-A user successfully enters the user name and password");
			Log.info("Then-User should be able to login");
			    try
			    {
		    	//driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
				
				JavascriptExecutor js = (JavascriptExecutor)driver;
				
				WebDriverWait wait =new WebDriverWait(driver, 20);
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getLoginUserNameWebElement())));
				Boolean bool = js.executeScript("return document.readyState").equals("complete");
			    Log.info("Page completely loaded "+bool);
			    
			    //Enter UserName
			    WebElement LoginUserName = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getLoginUserNameWebElement())));
			    LoginUserName.sendKeys(DataRead.EnrollmentSpecialist_User);
				Log.info("Enter user name");
	
				//Enter Password
				WebElement LoginPassword = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getLoginPasswordWebElement())));
				LoginPassword.sendKeys(DataRead.EnrollmentSpecialist_Pass);
				Log.info("Enter password");
				
				//Click on Login Button 
				WebElement LoginSubmit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getLoginButtonWebElement())));
				LoginSubmit.click();
				Log.info("Click on login button");
				
			    }
			    catch(Exception ex)
			    {
			    	Log.info("Page Not loaded completely / Object is not Visible");
			    }
		    }
	}

	

