package com.nimbus.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utility.ConfigReader;

public class Referral_Activity {
	WebDriver driver;
	Logger Log;
	ConfigReader config = new ConfigReader();
	WebElement ReferralActivityStatus = null;
	String ReferralStatus = null;
	List<String> actualsubsections = null;
	LeftNavigationLink leftNav=null;
	public Referral_Activity(WebDriver ldriver) {
		this.driver = ldriver;
	}

	public Referral_Activity(Logger lLog) {
		this.Log = lLog;
	}
	public void referral() throws Exception {
		Log = Logger.getLogger("Referral_Activity.class");
		Global_Method GM = PageFactory.initElements(driver, Global_Method.class);
		PropertyConfigurator.configure("log4j.properties");
		LeftNavigationLink leftNav = PageFactory.initElements(driver, LeftNavigationLink.class);
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
//		leftNav.clickMemberActionCenter();
//		leftNav.clickActivities();
		MemberActionCenter mac = PageFactory.initElements(driver, MemberActionCenter.class);
		mac.addActivity("Referral");
		ReferralActivityStatus =wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getReferralActivitystatus())));
		ReferralStatus = ReferralActivityStatus.getText().toString().trim();
		Log.info("ReferralStatus---" + ReferralStatus);
		if (ReferralStatus.equals(GlobalValues.Open_status.toString())
				|| ReferralStatus.equals(GlobalValues.InProgress_status.toString()))

		{
			Log.info("Click on Edit Referral activity");
			WebElement editreferral = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getEditReferral())));
			editreferral.click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
			Thread.sleep(1000);
			ArrayList<WebElement> options = new ArrayList<WebElement>();
			
			System.out.println(config.getSubsectionsPathreferral());
			options=(ArrayList<WebElement>) driver.findElements(By.xpath(config.getSubsectionsPathreferral()));
			ArrayList<String> expctedsubsections = new ArrayList<String>();
			System.out.println(options.size());
			
			for (int j = 0; j < options.size(); j++) {
				Thread.sleep(2000);
//				String listVal = options.get(j).getText();
				Log.info("options---" + options.get(j).getText());
				expctedsubsections.add(options.get(j).getText());
				Log.info("Expected subsections---" + expctedsubsections);
//				actualsubsections=GlobalValues.Referral_DefaultSubsections;
				}
			Log.info("verifying actual and expected default subsections in referral");
			GM.validateSubsections(GlobalValues.Referral_DefaultSubsections, expctedsubsections);
			Log.info("verified actual and expected default subsections in referral");
		
				
		}
	}
	
	public void addSubsections() throws InterruptedException {
		Log = Logger.getLogger("Referral_Activity.class");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		driver.findElement(By.xpath(config.getSubActivityIcon())).click();
        Log.info("Clicked on add subactivity Icon");
        Thread.sleep(3000);
        String subActivityModelWindowHeader = driver.findElement(By.xpath(config.getSubActivityModelWindowHeader())).getText().trim();
        Assert.assertEquals(subActivityModelWindowHeader,"Add Sub Activity");
        Log.info("Selecting all sub sections");
        
        try
        {
	        List<WebElement> listofActivities1 = driver.findElements(By.xpath(config.getListOfSubactivities()));
	        
	       int count = listofActivities1.size();
	       
	        for(int i=0; i< count; i++)
	        {
	        	
	        List<WebElement> listofActivities = driver.findElements(By.xpath(config.getListOfSubactivities()));
		        
	        System.out.println(listofActivities.get(i).toString());
	        listofActivities.get(i).click();
	        Thread.sleep(2000);
	        driver.findElement(By.xpath("//legend[contains(text(),'Activity Type')]")).click();
	        Thread.sleep(2000);
	        }
        }
        catch(Exception ex)
        {
        	System.out.println("Exception"+ex.getMessage().toString());
        }
        WebElement clickAddbutton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getSubActivityAddbutton())));
        clickAddbutton.click();      
          Log.info("Clicked on Add button of modal window");
         Log.info("Added all sub sections");
         Thread.sleep(8000);
		verifyAllSubsections();
	}
	public void verifyAllSubsections() {
		Global_Method GM = PageFactory.initElements(driver, Global_Method.class);
		
		List<WebElement> options = driver.findElements(By.xpath(config.getSubsectionsPath()));
		ArrayList<String> expctedsubsections = new ArrayList<String>();
		for (int j = 0; j < options.size(); j++) {
			String listVal = options.get(j).getText();
			Log.info("options---" + options.get(j).getText());
			expctedsubsections.add(listVal);
			Log.info("Expected subsections---" + expctedsubsections);
			
			}
		actualsubsections=GlobalValues.Referral_AllSubsections;
		Log.info("verifying actual and expected subsections in referral after adding all subsections");
		GM.validateSubsections(actualsubsections, expctedsubsections);
		Log.info("verified actual and expected subsections in referral after adding all subsections");
//		fillSubsections();
	}


}
