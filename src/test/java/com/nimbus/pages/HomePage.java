package com.nimbus.pages;



import java.awt.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utility.ConfigReader;

/*
 * Global Class : HomePage

 * Description:	Validate HomePage
 */
public class HomePage {
	
	WebDriver driver;
	Logger Log;
	
	ConfigReader config = new ConfigReader();
	
	
	public HomePage(WebDriver ldriver){
		this.driver=ldriver;
	}
	
	public HomePage(Logger lLog){
		this.Log=lLog;
	}


	
	public void HomePageVerification()throws Exception
	{

		Log = Logger.getLogger("HomePage.class");
		PropertyConfigurator.configure("log4j.properties");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		
		//Get the Title of the page
		String title= driver.getTitle();
		Log.info("The title of the page is " + title);
		
		//Assert statement is used to check the condition
		Assert.assertTrue(driver.getTitle().contains(title));
		Log.info("Verify title of the page");
		
		//Wait time from OAuth page to Home Page navigation
		Log.info(dateFormat.format(date));
		
		WebDriverWait wait = new WebDriverWait(driver,20);
		
		Log.info("Verifying the buttons/elements on the Home page");
		
		Thread.sleep(3000);
		try {
			WebElement searchElement = driver.findElement(By.xpath("//i[@class = 'fa fa-search ng-star-inserted']"));
			Log.info("Verified the presence of search button on the page");
			Thread.sleep(GlobalValues.Sleep_wait_time);

		} catch (Exception e) {
			Log.info("Search Button is not present on the home page");
		}

		try {
			WebElement recentlyAccessedCasesElement = driver.findElement(By.xpath("//i[@class = 'fa fa-star ng-star-inserted']"));
			Log.info("Verified the presence of recently accessed cases button on the page");
			Thread.sleep(GlobalValues.Sleep_wait_time);
		} catch (Exception e) {
			Log.info("Recently Accessed cases Button is not present on the home page");
		}
		
//		for(int i=0; i<GlobalValues.buttonsOnHomePage.size();i++){
//			
//				driver.findElement(By.xpath("//button[@type = 'button' and text()= '"+GlobalValues.buttonsOnHomePage.get(i)+"']"));
//				Log.info("Verifying the "+ GlobalValues.buttonsOnHomePage.get(i) + "- Button on the page");
//			}
//			
		WebElement activityDashboard =  driver.findElement(By.xpath("//h2[contains(text(),'Activity Dashboard')]"));
		boolean isHeaderPresent = activityDashboard.isDisplayed();
		if(isHeaderPresent==true){
			Log.info("Activity Dashboard is present");
		}else{
			Log.info("Activity Dashboard is not present");
		}
				
		WebElement gridHeader =  driver.findElement(By.xpath("//p-header//caption[contains(text(),'Activity List')]"));
		boolean isGridHeaderPresent = gridHeader.isDisplayed();
		if(isGridHeaderPresent==true){
			Log.info("Activity List is present");
		}else{
			Log.info("Activity List is not present");
		}
		
	}
	
	public void openActivityDashboardAndSearchPage(){
		Log = Logger.getLogger("HomePage.class");
		PropertyConfigurator.configure("log4j.properties");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		By activityDashBoardBreadcrumb = By.xpath("//div[@class='breadcrumb-bar']//a[text()='Activity Dashboard']");
		if(driver.findElements(activityDashBoardBreadcrumb).size()>0){
			driver.findElement(activityDashBoardBreadcrumb).click();
		}
		Assert.assertFalse(driver.findElements(By.xpath("//h1[contains(@class, 'page-title') and contains(text(),'Activity Dashboard')]")).isEmpty());

		driver.findElement(By.xpath("//button[@title='Member Search']")).click();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
		
	}
	
}
