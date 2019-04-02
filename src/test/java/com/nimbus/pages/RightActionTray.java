package com.nimbus.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.ConfigReader;

/*Global Class: Right Action Tray Icons
	Description: common class to click on Right Action Tray Icons
*/
public class RightActionTray {

	ConfigReader config = new ConfigReader();

	WebDriver driver;
	Logger Log;

	public RightActionTray(WebDriver ldriver) {
		this.driver = ldriver;
	}

	public RightActionTray(Logger lLog) {
		this.Log = lLog;
	}

	public void getElementsFromRightActionTray(){
		List<WebElement> elementsFromRightActionTray = driver.findElements(By.xpath("//div[@id='actionTray']//nm-button"));
		
	}
	public void clickRecordAContact() throws InterruptedException {
		try {
			
			Log = Logger.getLogger("RightActionTray.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

			WebElement recordAcontactIcon = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getRecordAContactIconElement())));
			recordAcontactIcon.click();
			Log.info("Clicked Record a contact on the right action tray");

			WebElement recordAcontactBreadcrumb = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getRecordAContactBC())));
			String recordAcontactBreadcrumbText = recordAcontactBreadcrumb.getText();
			Assert.assertEquals(recordAcontactBreadcrumbText, GlobalValues.ContactDetails);
			Log.info("Contact Details Bread Crumb is present");

		} catch (Exception e) {
			e.printStackTrace();
			Log.info("Object not visible");
		}
	}

	public void clickAddActivity() {
		try {
			
			Log = Logger.getLogger("RightActionTray.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

			WebElement addActivityIcon = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getAddActivityIconElement())));
			addActivityIcon.click();
			Thread.sleep(1000);
			Log.info("Clicked Add Activity on Right Action Tray");
			Thread.sleep(1000);
			
			WebElement addActivityBreadCrumb = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getAddActivityBC())));
			String addActivityBreadCrumbText = addActivityBreadCrumb.getText();
			Assert.assertEquals(addActivityBreadCrumbText,GlobalValues.AddActivity);
			Log.info("Add Activity Bread Crumb is present");

		} catch (Exception e) {
			e.printStackTrace();
			Log.info("Object not visible");
		}
	}

	public void clickUploadFiles() {
		try {
			
			Log = Logger.getLogger("RightActionTray.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

			Thread.sleep(GlobalValues.Sleep_wait_time);
		

			WebElement uploadFilesIcon = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getUploadFilesIconElement())));
			uploadFilesIcon.click();
			Log.info("Clicked Upload Files Icon on Right Action Tray");

			WebElement documentUploadBreadCrumb = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getUploadFilesBC())));
			String documentUploadBreadCrumbText = documentUploadBreadCrumb.getText();
			Assert.assertEquals(documentUploadBreadCrumbText, GlobalValues.DocumentUpload);
			Log.info("Document Upload bread crumb is present");

		} catch (Exception e) {
			e.printStackTrace();
			Log.info("Object not visible");
		}
	}

	
	public void clickAddNotes() {
		try {
			
			Log = Logger.getLogger("RightActionTray.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

			Thread.sleep(GlobalValues.Sleep_wait_time);

			WebElement addNotesIcon = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getAddNotesIconElement())));
			addNotesIcon.click();
			Log.info("Clicked \" Add Notes\" on Right Action Tray ");
			Thread.sleep(GlobalValues.Sleep_wait_time);
			WebElement notesbreadcrumb = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getAddNotesBC())));
			String notesbreadcrumbtext = notesbreadcrumb.getText();
			Assert.assertEquals(notesbreadcrumbtext, GlobalValues.AddNote);
			Log.info("Add Note Bread Crumb is present");

		} catch (Exception e) {
			e.printStackTrace();
			Log.info("Object not visible");
		}
	}

	public void clickCaseClosure() {
		try {
			
			Log = Logger.getLogger("RightActionTray.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

			WebElement caseClosureIcon = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getCaseClosureIconElement())));
			caseClosureIcon.click();
			Log.info("Clicked \"Case Closure\" on Right Action Tray");
			Thread.sleep(GlobalValues.Sleep_wait_time);
			WebElement caseClosureTitle = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getCaseClosureBC())));
			String caseClosureTitleText = caseClosureTitle.getText();
			Assert.assertEquals(caseClosureTitleText, GlobalValues.CaseClosure);
			Log.info("Case Closure Confirmation Header is present");
			

		} catch (Exception e) {
			e.printStackTrace();
			Log.info("Object not visible");
		}
	}

	public void clickRequestAuditPackage() {
		try { 
			Log = Logger.getLogger("RightActionTray.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			WebElement requestAuditPackageIcon = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getRequestAuditPackageIconElement())));
			requestAuditPackageIcon.click();
			Log.info("Clicked \" Request Audit Package\" on Right Action Tray ");
			Thread.sleep(GlobalValues.Sleep_wait_time);
			WebElement requestAuditPackageHeader = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getRequestAuditPackageBC())));
			String requestAuditPackageHeaderext = requestAuditPackageHeader.getText();
			Assert.assertEquals(requestAuditPackageHeaderext, GlobalValues.RequestAuditPackage);
			Log.info("Request Audit Package header is present");
			
			

		} catch (Exception e) {
			e.printStackTrace();
			Log.info("Object not visible");
		}
	}

	


}
