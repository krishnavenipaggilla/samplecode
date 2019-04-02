package com.nimbus.pages;

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

public class InitialOutreach_Hippa {
	WebDriver driver;
	Logger Log;
	ConfigReader config = new ConfigReader();
	public InitialOutreach_Hippa(WebDriver ldriver) {
		this.driver = ldriver;
	}

	public InitialOutreach_Hippa(Logger lLog) {
		this.Log = lLog;
	}
	
	public void initialOutreachHIPPAMaintained(String s) throws Exception{
		Log = Logger.getLogger("InitialOutreach_Hippa.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		AnnualAssessment annual = new AnnualAssessment(driver);
		// Left Navigation
					LeftNavigationLink leftNav = PageFactory.initElements(driver, LeftNavigationLink.class);
					leftNav.clickMemberActionCenter();
					leftNav.clickMemberActionCenter();
					leftNav.clickActivities();
					Log.info("Click on Edit initialoutreach activity");
					WebElement editInitilaOutreach = wait
							.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getEditIntialoutreach())));
					editInitilaOutreach.click();
					WebElement accordionIntroAndPermission = wait
							.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getIntroandPermissionsaccordian())));
					accordionIntroAndPermission.click();
					if(s.equalsIgnoreCase("Yes")){
					annual.annual_assessment("AnnualAssessments_CMDM", "Intial_InroAndPermissions_UTC", 3);
					WebElement saveprogressIntialOutreach = wait.until(
							ExpectedConditions.elementToBeClickable(By.xpath(config.getIntialOutreachSaveProgress())));
					saveprogressIntialOutreach.click();
					Log.info("Clicked on Save Progress");
					Thread.sleep(3000);
					WebElement IntialOutreachOkbutton = wait
							.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getInitialOkbutton())));
					IntialOutreachOkbutton.click();
					Thread.sleep(3000);
					changeHistroy("Yes");
					}
					else{
						annual.annual_assessment("AnnualAssessments_CMDM", "Intial_InroAndPermissions_UTC", 4);
						WebElement saveprogressIntialOutreach = wait.until(
								ExpectedConditions.elementToBeClickable(By.xpath(config.getIntialOutreachSaveProgress())));
						saveprogressIntialOutreach.click();
						Log.info("Clicked on Save Progress");
						Thread.sleep(3000);
						WebElement IntialOutreachOkbutton = wait
								.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getInitialOkbutton())));
						IntialOutreachOkbutton.click();
						Thread.sleep(3000);
						changeHistroy("No");
					}
					
					
				
	}

	public void changeHistroy(String txt) {
		Log = Logger.getLogger("InitialOutreach_Hippa.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		LeftNavigationLink leftNav = PageFactory.initElements(driver, LeftNavigationLink.class);
		leftNav.clickChangeHistory();
		if(txt.equalsIgnoreCase("Yes")){
		WebElement changeHistorydropdown = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getChangeHistroyDropdown())));
		changeHistorydropdown.click();
		WebElement changeHistorydropdownOption = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getChangeHistoryDropdownOption())));
		changeHistorydropdownOption.click();
		Log.info("verifying contact history");
		WebElement memberReached = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getCHMemberReached())));
		String hippaMaintainedText=memberReached.getText().toString();
		Log.info("hippa Maintained text---" + hippaMaintainedText);
		Assert.assertEquals(hippaMaintainedText, "No - HIPAA Maintained");
		}
		else{
//			WebElement changeHistorydropdown1 = wait
//					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getChangeHistroyDropdown1())));
//			changeHistorydropdown1.click();
//			WebElement changeHistorydropdownOption1 = wait
//					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getChangeHistoryDropdownOption1())));
//			changeHistorydropdownOption1.click();
			Log.info("verifying contact history");
			WebElement memberReached = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getCHMemberReachedNot())));
			String hippaMaintainedText=memberReached.getText().toString();
			Log.info("hippa Maintained text---" + hippaMaintainedText);
			Assert.assertEquals(hippaMaintainedText, "No - HIPAA Not Maintained");
			
		}
		
	
		
	}

}
