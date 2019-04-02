package com.nimbus.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import junit.framework.Assert;
import utility.ConfigReader;

/**
 * @author AF86867 Epic: 10279
 */

public class DocumentsPage {

	ConfigReader config = new ConfigReader();

	WebDriver driver;
	Logger Log;

	public DocumentsPage(WebDriver ldriver) {
		this.driver = ldriver;
	}

	public DocumentsPage(Logger lLog) {
		this.Log = lLog;
	}

	// public void clickDocuments() {
	// // Click Documents on LeftNavigation
	//
	// Log = Logger.getLogger("DocumentsPage.class");
	// PropertyConfigurator.configure("log4j.properties");
	// Log.info("Clicking on Document Link on Left navigation");
	//
	// WebDriverWait wait = new WebDriverWait(driver,
	// GlobalValues.Explicit_Wait_time);
	// Log.info("Entered \"Documents\" on PageCaseInfo");
	// try {
	// WebElement documents = wait
	// .until(ExpectedConditions.elementToBeClickable(By.xpath(config.getDocumentsXpath())));
	// documents.click();
	//
	// Log.info("Documents text is present");
	// Log.info("Clicked \"Documents\" on PageCaseInfo");
	// } catch (Exception e) {
	// e.printStackTrace();
	// Log.info("Document Link is visible");
	// }
	// }

	public void ViewDocument() {
		Log = Logger.getLogger("DocumentsPage.class");
		PropertyConfigurator.configure("log4j.properties");
		try {
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			WebElement DocumentDescription = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getViewDocumentClickXpath())));

		} catch (Exception e) {
			e.printStackTrace();
			Log.info("View document is not clikable");
		}
	}

	public void clickOnUploadDocumentsIcon() {

		// Clicking on Upload document Icon
		Log = Logger.getLogger("DocumentsPage.class");
		PropertyConfigurator.configure("log4j.properties");

		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		Log.info("Entered \"Documents\" on PageCaseInfo");
		try {
			WebElement UploadFilesIcon = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getUploadFilesIconXpath())));
			UploadFilesIcon.click();

			WebElement UploadFilesText = wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Upload Files')]")));
			String UploadFiles = UploadFilesText.getText();
			Assert.assertEquals(UploadFiles, "Upload Files");
			Log.info("Upload Files Text appeared");
			Log.info("Upload Files Page displayed");
		} catch (Exception e) {
			e.printStackTrace();
			Log.info("Upload Files Page not visible");
		}
	}

	public void ChooseFileAndUpload() throws AWTException, InterruptedException {
		// Upload file.
		Log = Logger.getLogger("DocumentsPage.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

		try {
			Log.info("clicked on AddFile");

			WebElement chooseFile = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getChooseFileXpath())));
			chooseFile.click();
			Log.info("clicked on Choose File button");

			
			String filepath = config.getFileUrl();
			StringSelection sel = new StringSelection(filepath);
			
			

			Clipboard filePathfromClip = Toolkit.getDefaultToolkit().getSystemClipboard();
			filePathfromClip.setContents(sel, sel);

			Log.info("filepath::" + filepath);

			Thread.sleep(6000);
			Robot robot = new Robot();
			robot.delay(250);
	        robot.keyPress(KeyEvent.VK_ENTER);
	        robot.keyPress(KeyEvent.VK_ENTER);
	        robot.keyRelease(KeyEvent.VK_ENTER);
	        robot.keyPress(KeyEvent.VK_CONTROL);
	        robot.keyPress(KeyEvent.VK_V);
	        robot.keyRelease(KeyEvent.VK_V);
	        robot.keyRelease(KeyEvent.VK_CONTROL);
	        robot.keyPress(KeyEvent.VK_ENTER);
	        robot.delay(150);
	        robot.keyRelease(KeyEvent.VK_ENTER);



			// Adding Description to the file
			WebElement DocumentDescription = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getFileDescriptionXpath())));
			DocumentDescription.clear();
			Thread.sleep(2000);
			DocumentDescription.sendKeys("Testing");
			Thread.sleep(3000);
			WebElement VerifyCancelButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getCancelButtonXpath())));
			Assert.assertTrue(VerifyCancelButton.isDisplayed());
			// checking Cancel and Add button
			Log.info("Cancel button is visible");
			WebElement Addbutton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getAddButtonXpath())));
			Assert.assertTrue(Addbutton.isDisplayed());
			Log.info("Add button is enabled");
			Addbutton.click();

			Log.info("Clicked on Add button");
			wait.until(ExpectedConditions
					.presenceOfAllElementsLocatedBy((By.xpath(config.getUploadedDocumentsTitleXpath()))));
			Log.info("Landed on Documents page");
		} catch (Exception e) {
			e.printStackTrace();
			Log.info("Couldn't upload the documents");
		}

	}

	public void GetDocumentNameAndId() {
		try {
			// Get Uploaded Document Name and ID
			Log.info("Getting uploaded Document and Document ID");
			Log = Logger.getLogger("DocumentsPage.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

			// Log.info("Verify Document Grid Started");
			Thread.sleep(2000);
			WebElement table = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table")));
			List<WebElement> allRowsOfTable = table.findElements(By.xpath("//table//tbody/tr"));
			int Row = allRowsOfTable.size();

			String DocumentId = driver.findElement(By.xpath("//p-table/div/div/table/tbody/tr[" + Row + "]/td[6]/span"))
					.getText();
			Log.info("Uploaded Document ID is:" + DocumentId);
			Thread.sleep(2000);
			String DocumentName = driver
					.findElement(By.xpath("//p-table/div/div/table/tbody/tr[" + Row + "]/td[1]/span")).getText();
			Log.info("Uploaded Document ID is:" + DocumentName);
		} catch (Exception e) {
			e.printStackTrace();
			Log.info("Document name and Document ID are not visible");
		}

	}

	public void verifyAddedDocument() throws Exception {
		// Verifying Added Document.
		Log = Logger.getLogger("DocumentsPage.class");
		PropertyConfigurator.configure("log4j.properties");
		Log.info("Verify Document Grid Started");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

		try {
			WebElement RecordsDisplayed = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getRecordsDisplayed())));
			// Log.info("records string is :: " + RecordsDisplayed);

			String RecDispStr = ((WebElement) RecordsDisplayed).getText().toString();
			Log.info("Number of Uploaded Document found :: " + RecDispStr);

			String[] totalRecords = RecDispStr.split(" ");
			String lastRecStr = totalRecords[totalRecords.length - 1];
			int totalRecord = Integer.parseInt(lastRecStr);
			System.out.println("total rec  " + totalRecord);
		} catch (Exception e) {
			e.printStackTrace();
			Log.info("No records Displayed");
		}

	}

	public void verifyUploadedDocumentsColumnGrid() throws Exception {
		// Verifying the uploaded document grid
		Log = Logger.getLogger("DocumentsPage.class");
		PropertyConfigurator.configure("log4j.properties");
		DashboardFunctions df = new DashboardFunctions(driver);
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

		WebElement gridHeaderElement = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getUploadedDocumentsTitleXpath())));
		String gridHeader = gridHeaderElement.getText();
		try {
			Log.info("Verifying Documents Column Grid ");

			if (gridHeader.contains("Uploaded Documents")) {
				List<String> actualColumnNames = df.getGridColumnNames();
				Log.info("Getting the list of Column names for Uploaded Documents Grid");
				Thread.sleep(3000);
				df.validateGridColumnNames(GlobalValues.DocumentsGrid, actualColumnNames);
				Log.info("Uploaded Documents Column Names has been verified");
			}
		} catch (Exception e) {
			Log.info(e + "Couldn't verify the  Uploaded Documents Columns");
		}
	}

	public void sortUploadedDocumentsGrid() throws Exception {
		// Sorting the Uploaded document grid
		Log = Logger.getLogger("DocumentsPage.class");
		PropertyConfigurator.configure("log4j.properties");
		DashboardFunctions df = new DashboardFunctions(driver);

		try {
			// df.columnSortingOnGrid(driver, 0);
			df.columnSorting(driver);
			Log.info("sucessfully sorted the Uploaded Document Name ");
		} catch (Exception e) {

			Log.info(e + " -sorting for Uploaded Documents grid failed");
		}
	}

	// public void sortUploadedDateGrid() throws Exception {
	// // Sorting the Uploaded date
	// Log = Logger.getLogger("DocumentsPage.class");
	// PropertyConfigurator.configure("log4j.properties");
	// DashboardFunctions df = new DashboardFunctions(driver);
	//
	// try {
	// //df.columnSortingOnGrid(driver, 1);
	// df.columnSorting(driver);
	// // df.verifySorting("Uploaded Date", 1, "ASC", 0);
	// Log.info("sucessfully sorted Uploaded date");
	// } catch (Exception e) {
	//
	// Log.info(e + " -sorting for Uploaded Date grid failed");
	// }
	// }

	public void ViewUploadedDocument() {

		// Viewing the document
		Log = Logger.getLogger("DocumentsPage.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

		try {
			WebElement ViewDocumentButton = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getViewDocumentClickXpath())));
			ViewDocumentButton.click();
			WebElement ViewDocText = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getViewDocumentTextXpath())));
			String Text = ViewDocText.getText();
			Thread.sleep(1000);			
			Log.info(Text);
			Assert.assertEquals(Text, "View Document");
			Log.info("**********Verified the presence of Discharge document checklist in case generated through payload**********");

//			ViewDocText.click();
//			Log.info("Clicked on view document");
		} catch (Exception e) {

			// Log.info(e + "Document is not visible");
		}

	}
	public int getNoOfRowsInUploadedDcumentsGrid() throws Exception {
		// Getting Documents count
		Log = Logger.getLogger("DocumentsPage.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

		int rowsInGrid = 0;
		try {

			WebElement gridHeader = wait.until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//p-header//caption[contains(text(),'Uploaded Documents')]")));
			String gridHeaderText = gridHeader.getText();
			Log.info("Grid Name is - " + gridHeaderText);

			WebElement table = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table")));

			List<WebElement> noRecordsElement = driver
					.findElements(By.xpath("//table/tbody/tr/td[contains(text(),'No records found')]"));
			if (noRecordsElement.isEmpty()) {
				// Now get all the TR elements from the table
				List<WebElement> allRowsOfTable = table.findElements(By.xpath("//table//tbody/tr"));

				rowsInGrid = allRowsOfTable.size();
				Log.info("No of uploaded documents on the grid is:  " + rowsInGrid);
			}

		} catch (Exception e) {
			e.printStackTrace();
			Log.info("Could not find the no of uploaded documents : " + e);
		}
		return rowsInGrid;
	}
}
