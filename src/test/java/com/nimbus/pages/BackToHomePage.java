package com.nimbus.pages;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.ConfigReader;


/*
 * Class Name : BackToHomePage
 * Method Name: backToHome
 * Description:	Navigates to Dashboard Page from anywhere in the application
 */
public class BackToHomePage {
	ConfigReader config = new ConfigReader();
	
	WebDriver driver;
	Logger Log;
	
	public BackToHomePage (WebDriver ldriver){
		this.driver=ldriver;
	}
	
	public BackToHomePage (Logger lLog){
		this.Log=lLog;
	}
	
	public void backToHome() throws Exception {
		
		Log = Logger.getLogger("BackToHomePage.class");
		PropertyConfigurator.configure("log4j.properties");
		
		Thread.sleep(5000);
		
		driver.findElement(By.xpath("//a[text()='Activity Dashboard']")).click();
		Log.info("Back to Dashboard");
		WebElement activityDashboard = driver.findElement(By.xpath("//h2[contains(text(),'Activity Dashboard')]"));
		boolean isHeaderPresent = activityDashboard.isDisplayed();
		if (isHeaderPresent == true) {
			Log.info("Activity Dashboard is present");
		} else {
			Log.info("Activity Dashboard is not present");
		}

		
		
		
	}

}
