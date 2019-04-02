package com.nimbus.pages;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.testng.Assert.assertEquals;

import utility.ConfigReader;
/*
 * Global Class : HeaderFooterValidation

 * Description:	Validate Header and Footer
 */
public class HeaderFooterValidation {

	ConfigReader config = new ConfigReader();

	WebDriver driver;
	Logger Log;

	public HeaderFooterValidation(WebDriver ldriver) {
		this.driver = ldriver;
	}

	public HeaderFooterValidation(Logger lLog) {
		this.Log = lLog;
	}

	public void footer() throws Exception {
		try {
			Log = Logger.getLogger("Footer.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			Global_Method GM = new Global_Method(driver);

			WebElement FootersiteMap = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getSiteMap())));
			String textFootersiteMap = FootersiteMap.getText();
			assertEquals(textFootersiteMap, GlobalValues.Footer_SiteMap.toString());
			Log.info("Verify Footer: Site Map");

			WebElement FooterTermsofUse = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getTermsofUse())));
			String textFooterTermsofUse = FooterTermsofUse.getText();
			assertEquals(textFooterTermsofUse, GlobalValues.Footer_TermsofUse.toString());
			Log.info("Verify Footer: Terms Of Use");

			WebElement ProtectedMarks = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getProctedMarks())));
			String textProtectedMarks = ProtectedMarks.getText();
			assertEquals(textProtectedMarks, GlobalValues.Footer_ProtectedMarks.toString());
			Log.info("Verify Footer: Protected Marks");

			WebElement PrivacyPolicy = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getPrivacyPolicy())));
			String textPrivacyPolicy = PrivacyPolicy.getText();
			assertEquals(textPrivacyPolicy, GlobalValues.Footer_PrivacyPolicy.toString());
			Log.info("Verify Footer: Privacy Policy");

			WebElement SSLCertification = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getSSLCertification())));
			String textSSLCertification = SSLCertification.getAttribute("src");
			if (GlobalValues.Environment.equalsIgnoreCase("SIT")) {
				assertEquals(textSSLCertification, GlobalValues.Footer_Image_src_Sit.toString());
				Log.info("Verify Footer: SSL Certificate from");
			} else if (GlobalValues.Environment.equalsIgnoreCase("DEV")) {
				assertEquals(textSSLCertification, GlobalValues.Footer_Image_src_Dev.toString());
				Log.info("Verify Footer: SSL Certificate from");
			} else if (GlobalValues.Environment.equalsIgnoreCase("SANDBOX")) {
				assertEquals(textSSLCertification, GlobalValues.Footer_Image_src_Sandbox.toString());
				Log.info("Verify Footer: SSL Certificate from");
			} else if (GlobalValues.Environment.equalsIgnoreCase("UAT")) {
				assertEquals(textSSLCertification, GlobalValues.Footer_Image_src_Uat.toString());
				Log.info("Verify Footer: SSL Certificate from");
			}
//			else if (GlobalValues.Environment.equalsIgnoreCase("IMPLEMENTATION DEV")) {
//				assertEquals(textSSLCertification, GlobalValues.Footer_Image_src_Uat.toString());
//				Log.info("Verify Footer: SSL Certificate from");
//				}
		} catch (Exception ex) {
			Log.info("Footer is not as expected" + ex.getMessage().toString());
		}

	}

	public void header() throws Exception {
		try {
			Log = Logger.getLogger("Header.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

			// Anthem Logo
			/*
			 * WebElement anthemLogo=wait.until(ExpectedConditions.
			 * visibilityOfElementLocated(By.xpath(config.getAnthemLogo())));
			 * String anthemLogoText=anthemLogo.getAttribute("alt"); String
			 * anthemurl=anthemLogo.getAttribute("src");
			 * Assert.assertEquals(anthemLogoText,
			 * GlobalValues.anthemText.toString());
			 * //Assert.assertEquals(anthemurl,
			 * GlobalValues.anthemUrl.toString());
			 * Log.info("Anthem Logo is present");
			 */
//			WebElement cmdm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getCMDM())));
//			String cmdmText = cmdm.getText();
//			Log.info("Nimbus CM/DM text is present on Header");

			WebElement userImage = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getUserImage())));
			Assert.assertTrue(userImage.isDisplayed());
			Log.info("Verify Header: User Image is displayed");

			WebElement userName = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getUserName())));
			// String userNameText=userName.getText();
			GlobalValues.UserNameOnUI = userName.getText();
			Assert.assertNotNull(GlobalValues.UserNameOnUI);
			Log.info("Verify Header: User Name is present on header");

			WebElement userRole = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getUserRole())));
			String userRoleText = userRole.getText();
			Assert.assertNotNull(userRoleText);
			Log.info("Verify Header: User Role is present on header");

			WebElement logout = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getLogOut())));
			Assert.assertTrue(logout.isDisplayed());
			Log.info("Verify Header: LogOut link is present on Header");

			
			WebElement quickLinksIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@class = 'fa quicklinks ng-star-inserted']")));
			Assert.assertTrue(quickLinksIcon.isDisplayed());
			Log.info("Verify Header: Quick Links Icon is present on Header");
			
			WebElement productNameText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='productName  d-inline-block']")));
			Assert.assertTrue(productNameText.isDisplayed());
			Log.info("Verify Header: Product Name Text is present on Header");
			
			/*
			 * WebElement floridaMarket=wait.until(ExpectedConditions.
			 * visibilityOfElementLocated(By.xpath(config.getFloridaMarket())));
			 * String floridaMarketText=floridaMarket.getText();
			 * Assert.assertEquals(floridaMarketText,
			 * GlobalValues.floridaMarket.toString());
			 * Log.info("Florida market text is present on header");
			 */
		} catch (Exception ex) {
			Log.info("Header is not as expected" + ex.getMessage().toString());
			ex.printStackTrace();
		}
	}

}
