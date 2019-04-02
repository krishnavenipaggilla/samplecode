package com.nimbus.pages;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections.map.MultiValueMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utility.ConfigReader;

public class ManagementReasonPage {

	ConfigReader config = new ConfigReader();

	WebDriver driver;
	Logger Log;

	public ManagementReasonPage(WebDriver ldriver) {
		this.driver = ldriver;
	}

	public ManagementReasonPage(Logger lLog) {
		this.Log = lLog;
	}
	
	
	public void verifyManagementReasonPage() throws Exception {
		Log = Logger.getLogger("ManagementReasonPage.class");
		PropertyConfigurator.configure("log4j.properties");
		AnnualAssessment a = new AnnualAssessment(driver);
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		Global_Method gm = new Global_Method(driver);
		LeftNavigationLink lnk = new LeftNavigationLink(driver);
		
		boolean passed = true;
		boolean passed1 = true;
		lnk.clickManagementReason();

		// Adding Management Reason 
		
		Thread.sleep(GlobalValues.Sleep_wait_time);

		try {
			addManagementReason();
			Thread.sleep(GlobalValues.Sleep_wait_time);

		} catch (Exception e) {
			e.printStackTrace();
			passed = false;
			passed1 = false;
		}

		
		  try { 
			  	Thread.sleep(GlobalValues.Sleep_wait_time);
		  		editReasonsForManagement();
		  		Thread.sleep(GlobalValues.Sleep_wait_time);
		  
		  	} catch (Exception e) {
		  		e.printStackTrace(); 
		  		passed = false; 
		  		passed1 = false;
		   
		   }
		  
		  if (passed1 == true) {
			  	gm.Reports("NIM-17119","Pass");
			} else {
				gm.Reports("NIM-17119","Fail");
			}
		  
		/* //Filter removed from the grid try
		 * 
		 * { 
		 * 	Request2.filterManagementReasonGrid(); 
		 * }catch(Exception e) {
		 * 	e.printStackTrace(); 
		 * 	passed = false; 
		 * }
		 * Thread.sleep(GlobalValues.Sleep_wait_time);
		 */

		try {
			lnk.clickManagementReason();
			sortManagementReasonGrid();
		} catch (Exception e) {
			e.printStackTrace();
			passed = false;
		}
		Thread.sleep(GlobalValues.Sleep_wait_time);
//		try {
//			lnk.clickManagementReason();
//			filterByProgramTypeForManagementReason("NICU");
//		} catch (Exception e) {
//			e.printStackTrace();
//			passed = false;
//		}

		Thread.sleep(GlobalValues.Sleep_wait_time);
		lnk.clickManagementReason();

		Thread.sleep(GlobalValues.Sleep_wait_time);
		if (passed == true) {
			gm.Reports("NIM-12523", "Pass");
			gm.Reports("NIM-11409", "Pass");
		} else {
			gm.Reports("NIM-12523", "Fail");
			gm.Reports("NIM-11409", "Fail");
		}
		
	}
	
	public void verifySupplementalInfo() throws Exception {
		Log = Logger.getLogger("ManagementReasonPage.class");
		PropertyConfigurator.configure("log4j.properties");
		AnnualAssessment a = new AnnualAssessment(driver);
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		Global_Method gm = new Global_Method(driver);
		LeftNavigationLink lnk = new LeftNavigationLink(driver);
		
		boolean passed = true;
		// try { 
		//the below functionality has been removed from grid
		// Request1.addSupplementalInformationFromGrid("test-123-info", 1);
		// Thread.sleep(GlobalValues.Sleep_wait_time);
		// } catch (Exception e) {
		// e.printStackTrace(); // passed = false; // }
		try {
			
			try{
				WebElement noRecordsFoundElement = driver.findElement(By.xpath("//p-header[contains(text(),'Management Reason')]//ancestor::nm-table//tbody//tr//td[contains(text(),'No records found')]"));
						
				if(noRecordsFoundElement.isDisplayed()){
					gm.Reports("NIM-11409", "Pass");
					return;
				}
				
			}catch(Exception e){
				Log.info("Record is present to check the Supplemental Info Functionality");
			}
			
			WebElement editMgmtReasonElement = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getEditManagementReasonXpath())));
			editMgmtReasonElement.click();
			Log.info("Clicked on edit for 1st Management Reason from Grid");
			Thread.sleep(GlobalValues.Sleep_wait_time);

			verifySupplementalInfoGrid();
			Thread.sleep(GlobalValues.Sleep_wait_time);
			Log.info("Verified Supplemental Info grid");

			addSupplementalInformationFromViewReasonsForManagement("Test-Note-Info123", 2);
			Thread.sleep(GlobalValues.Sleep_wait_time);
			Log.info("Added Supplemental Info");

			// click Management Reason
			lnk.clickManagementReason();
			// Click on Edit for Management Reason
			WebElement editMgmtReasonElement1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getEditManagementReasonXpath())));
			editMgmtReasonElement1.click();
			Log.info("Clicked on edit for 1st Management Reason from Grid");
			Thread.sleep(GlobalValues.Sleep_wait_time);

			
				sortSupplementalInformationGrid();
				Thread.sleep(GlobalValues.Sleep_wait_time);
				Log.info("Column Level Sorting on Supplemental Info");
						
		} catch (Exception e) {
			e.printStackTrace();
			passed = false;
		}

		if (passed == true) {
			gm.Reports("NIM-11409", "Pass");
		} else {

			gm.Reports("NIM-11409", "Fail");
		}
		
	}

	public void addManagementReason() throws Exception {
		Log = Logger.getLogger("ManagementReasonPage.class");
		PropertyConfigurator.configure("log4j.properties");
		AnnualAssessment a = new AnnualAssessment(driver);
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		Global_Method gm = new Global_Method(driver);
		int rowCount = gm.getNoOfRowsInGrid("Management Reason");
		int rowCountAfterAdd;

		try {
			Thread.sleep(GlobalValues.Sleep_wait_time);

			WebElement addMgmtReasonElement = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getAddMgmtReasonLinkXpath())));
			addMgmtReasonElement.click();
			Log.info("Clicked on Add Management Reason link");

			Thread.sleep(GlobalValues.Sleep_wait_time);
			Thread.sleep(GlobalValues.Sleep_wait_time);
			a.annual_assessment("AnnualAssessments_CMDM", "AddManagementReason", 2);
			Log.info("verifying the field labels and passing the values from excel for Add management reason");
			/*
			String programTypeAdded = driver.findElement(By.xpath("//table//thead//tr//th//span[contains(text(),'Status')]//ancestor::thead//following-sibling::tbody//tr[1]//td[3]")).getText();
			String managementReason = driver.findElement(By.xpath("//table//thead//tr//th//span[contains(text(),'Status')]//ancestor::thead//following-sibling::tbody//tr[1]//td[3]")).getText();
			String communicationMethods = driver.findElement(By.xpath("//table//thead//tr//th//span[contains(text(),'Status')]//ancestor::thead//following-sibling::tbody//tr[1]//td[4]")).getText();
			String managementReasonFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "EditManagementReason",0,"Management Status");
			String managementStatusReasonFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "EditManagementReason",0,"Management Status Reason");
			
			Assert.assertEquals(statuseditedto, managementReasonFromExcel, "Added values from excel are not matching with the edited values on UI for status in the grid");
			Assert.assertEquals(caseStatuseditedto, managementStatusReasonFromExcel, "Added values from excel are not matching with the edited values on UI for casestatus in the grid");
			 */
			List<WebElement> addMgmtReasonMsgs = driver.findElements(By.xpath(config.getAddMgmtReasonMsgsXpath()));
			if (!addMgmtReasonMsgs.isEmpty()) {
				Log.info("**********Record already exists**********");
				driver.findElement(By.xpath("//button[text()='Ok']")).click();

			} else {
				Log.info("**********Added Management reason********** ");
				rowCountAfterAdd = gm.getNoOfRowsInGrid("Management Reason");
				Assert.assertTrue((rowCountAfterAdd == rowCount + 1),
						"Row Count has not been increased after adding a record");

			}

		} catch (Exception e) {

			Log.info(e + " -Couldn`t add a record in Management Reason");
			e.printStackTrace();
		}
	}

	public void editReasonsForManagement() throws Exception {
		Log = Logger.getLogger("ManagementReasonPage.class");
		PropertyConfigurator.configure("log4j.properties");
		AnnualAssessment a = new AnnualAssessment(driver);
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		Global_Method gm = new Global_Method(driver);
		int rowCount = gm.getNoOfRowsInGrid("Management Reason");
		int rowCountAfterEdit;

		try {
			WebElement editManagementReasonIconElement = wait.until(ExpectedConditions
					.presenceOfElementLocated(By.xpath(config.getEditManagementReasonXpath())));
			editManagementReasonIconElement.click();
			Thread.sleep(GlobalValues.Sleep_wait_time);
			Log.info("Clicked on the Edit for the first record from Management Reason Grid");

			a.annual_assessment("AnnualAssessments_CMDM", "EditManagementReason", 2);
			Log.info("verifying the field labels and passing the values from excel for edit management reason");

			rowCountAfterEdit = gm.getNoOfRowsInGrid("Management Reason");
			Thread.sleep(GlobalValues.Sleep_wait_time);
			Assert.assertTrue((rowCountAfterEdit == rowCount), "Row count has been increased after editing a record");
			
			String statusEditedto = driver.findElement(By.xpath("//table//thead//tr//th//span[contains(text(),'Status')]//ancestor::thead//following-sibling::tbody//tr[1]//td[4]")).getText().trim();
			String managementStatusReasonEditedto = driver.findElement(By.xpath("//table//thead//tr//th//span[contains(text(),'Status Reason')]//ancestor::thead//following-sibling::tbody//tr[1]//td[5]")).getText().trim();
			String managementStatusFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "EditManagementReason",2,"Management Status|1");
			String managementStatusReasonFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "EditManagementReason",2,"Management Status Reason|1");
			
			Assert.assertEquals(statusEditedto, managementStatusFromExcel, "Edited values from excel are not matching with the edited values on UI for status in the grid");
			Assert.assertEquals(managementStatusReasonEditedto, managementStatusReasonFromExcel, "Edited values from excel are not matching with the edited values on UI for casestatus in the grid");
			 
			Log.info("**********Verified Edit Management Reason**********");
		} catch (Exception e) {

			Log.info(e + " -Edit Management Reason failed");
			e.printStackTrace();
		}
	}

	public void addSupplementalInformationFromGrid(String ntInfo, int instanceForSubmit) throws Exception {
		Log = Logger.getLogger("ManagementReasonPage.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		Map<String, Integer> beforeCount = new HashMap<String, Integer>();
		Map<String, Integer> afterCount = new HashMap<String, Integer>();

		try {
			beforeCount = this.getRowCountForNotesNSupplementalInfo();
			
			Thread.sleep(GlobalValues.Sleep_wait_time);
			WebElement DropdownArrowOnManagementReasonGridElement = wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(config.getEditManagementReasonXpath())));
			DropdownArrowOnManagementReasonGridElement.click();
			Thread.sleep(GlobalValues.Sleep_wait_time);
			Log.info("Clicked on Dropdown arrow on the Management Reason Grid");

			WebElement addSupInfo = wait
					.until(ExpectedConditions.elementToBeClickable(By.linkText("Add Supplemental Information")));
			addSupInfo.click();
			Log.info("Clicked on Add Supplemental Information from the dropdown");
			Thread.sleep(GlobalValues.Sleep_wait_time);
			this.addSupplementalInformation(ntInfo, instanceForSubmit);

			afterCount= this.getRowCountForNotesNSupplementalInfo();
			
			int beforCountOfNotes = beforeCount.get("NotesGrid");
			int beforeCountOfSupplementalInfo= beforeCount.get("SupplementalInfoGrid");
			
			int afterCountOfNotes = afterCount.get("NotesGrid");
			int afterCountOfSupplementalInfo= afterCount.get("SupplementalInfoGrid");
			
			Assert.assertEquals(beforCountOfNotes +1, afterCountOfNotes, "Note has been added to notes grid");
			Assert.assertEquals(beforeCountOfSupplementalInfo +1, afterCountOfSupplementalInfo, "Note has been added to supplemental Info grid");
			
			Log.info("**********Verified Add Supplemental Info**********");
		} catch (Exception e) {

			Log.info(e + " Add Supplemental Info failed");
			e.printStackTrace();
		}
	}

	public void addSupplementalInformationFromViewReasonsForManagement(String ntInfo, int instanceForSubmit)
			throws Exception {
		Log = Logger.getLogger("ManagementReasonPage.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		Map<String, Integer> beforeCount = new HashMap<String, Integer>();
		Map<String, Integer> afterCount = new HashMap<String, Integer>();
		try {

			beforeCount = getRowCountForNotesNSupplementalInfo();
			
			WebElement EditManagementReasonXpathElement = wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(config.getEditManagementReasonXpath())));
			EditManagementReasonXpathElement.click();
			Thread.sleep(GlobalValues.Sleep_wait_time);
			Log.info("Clicked on the first record Dropdown arrow on the Management Reason Grid");

			
			WebElement addSupplementalInfoLinkElement = wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getAddSupplementalInfoLinkXpath())));
			addSupplementalInfoLinkElement.click();
			Thread.sleep(GlobalValues.Sleep_wait_time);
			Log.info("Clicked on Add Supplemental Info on View Reason For Management Page");

			addSupplementalInformation(ntInfo, instanceForSubmit);
			Thread.sleep(GlobalValues.Sleep_wait_time);
			afterCount = getRowCountForNotesNSupplementalInfo();
			Thread.sleep(GlobalValues.Sleep_wait_time);
			
			int beforCountOfNotes = beforeCount.get("NotesGrid");
			int beforeCountOfSupplementalInfo= beforeCount.get("SupplementalInfoGrid");
			
			int afterCountOfNotes = afterCount.get("NotesGrid");
			int afterCountOfSupplementalInfo= afterCount.get("SupplementalInfoGrid");
			
			Assert.assertEquals(beforCountOfNotes +1, afterCountOfNotes, "Note has been added to notes grid");
			Assert.assertEquals(beforeCountOfSupplementalInfo +1, afterCountOfSupplementalInfo, "Note has been added to supplemental Info grid");
			
			Log.info("**********Verified Add Supplemental Info**********");

		} catch (Exception e) {

			Log.info(e + " -Add supplemental info failed");
			e.printStackTrace();
		}
	}

	public void addSupplementalInformation(String ntInfo, int instanceForSubmit) throws Exception {
		Log = Logger.getLogger("ManagementReasonPage.class");
		PropertyConfigurator.configure("log4j.properties");
		Global_Method gm = new Global_Method(driver);
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

		try {
			// verify modal window header
			WebElement modalHeaderElement = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div//span//p-header[contains(text(),'Add Supplemental Information')]")));
			String modalHeaderText = modalHeaderElement.getText();
			Thread.sleep(GlobalValues.Sleep_wait_time);
			Assert.assertEquals(modalHeaderText, "Add Supplemental Information",
					"Add Supplemental Information modal Window header is not matching");
			Log.info("Add Supplemental Information modal Window header has been verified");

			// verify label of the text area present
			WebElement textAreaLabelElement = wait.until(
					ExpectedConditions.presenceOfElementLocated(By.xpath(config.getTextAreaLabelElementXpath())));
			String textAreaLabel = textAreaLabelElement.getText();
			Thread.sleep(GlobalValues.Sleep_wait_time);
			Assert.assertEquals(textAreaLabel, "Note Description", "Note Description Label is not matching");
			Log.info("Note Description Label text has been verified");
			// Enter notes

			WebElement noteArea = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getNoteAreaXpath())));
			noteArea.sendKeys(ntInfo);
			Thread.sleep(GlobalValues.Sleep_wait_time);

			// verify buttons

			WebElement submit = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("(//button[text() ='Submit'])[" + instanceForSubmit + "]")));
			try {
				if (submit.isEnabled()) {
					submit.click();
					Thread.sleep(GlobalValues.Sleep_wait_time);
					Log.info("Clicked on Submit for the Note Modal Window");
				} else {
					driver.findElement(By.xpath("//button[text() ='Back']")).click();
				}
			} catch (Exception e) {
				Log.info(e.getStackTrace() + "Submit is not enabled or clicked");

			}
		} catch (Exception e) {

			Log.info(e + " -add supplemental info failed");
			e.printStackTrace();
		}

		// verify the landing page

	}

	public void verifySupplementalInfoGrid() throws Exception {
		Log = Logger.getLogger("ManagementReasonPage.class");
		PropertyConfigurator.configure("log4j.properties");
		DashboardFunctions df = new DashboardFunctions(driver);
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

		WebElement gridHeaderElement = wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath(config.getSupplementalGridHeaderElementXpath())));
		String gridHeader = gridHeaderElement.getText();
		Log.info("Grid Header is " + gridHeader);
		try {

			if (gridHeader.contains("Supplemental Info")) {
				List<String> actualColumnNames = df.getGridColumnNames();
				Log.info("Grid Header has been verified");
				Log.info("Getting the list of Column names for Supplemental Info Grid");
				Thread.sleep(GlobalValues.Sleep_wait_time);
				df.validateGridColumnNames(GlobalValues.SupplementalInformationGrid, actualColumnNames);
				Log.info("**********Supplemental Info Grid Column Names has been verified**********");
			}
		} catch (Exception e) {
			Log.info(e + "  -This is not Supplemental Info Grid. Verify the Grid Name");
			e.printStackTrace();
		}
	}

	public void verifyManagementReasonsGrid() throws Exception {
		Log = Logger.getLogger("ManagementReasonPage.class");
		PropertyConfigurator.configure("log4j.properties");
		DashboardFunctions df = new DashboardFunctions(driver);
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

		WebElement gridHeaderElement = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath(config.getManagementReasonGridHeaderElementXpath())));
		String gridHeader = gridHeaderElement.getText();
		try {

			if (gridHeader.contains("Management Reason")) {
				List<String> actualColumnNames = df.getGridColumnNames();
				Log.info("Getting the list of Column names for Management Reason Grid");
				Thread.sleep(3000);
				df.validateGridColumnNames(GlobalValues.ManagementReasonGrid, actualColumnNames);
				Log.info("**********Management Reason Grid Column Names has been verified**********");
			}
		} catch (Exception e) {
			Log.info(e + "  -This is not Management Reason Grid. Verify the Grid Name");
			e.printStackTrace();
		}
	}

	public void filterSupplementalInformationGrid() throws Exception {
		Log = Logger.getLogger("ManagementReasonPage.class");
		PropertyConfigurator.configure("log4j.properties");
		Global_Method gm = new Global_Method(driver);
		DashboardFunctions df = new DashboardFunctions(driver);

		MultiMap multiMapFilterDetails = new MultiValueMap();

		multiMapFilterDetails.put("Taken By",
				gm.readFromExcel("AnnualAssessments_CMDM", "FilterSupplementalInformation", 1, "Taken By"));
		multiMapFilterDetails.put("Taken By", "column_1");
		multiMapFilterDetails.put("Taken By", "2");

		multiMapFilterDetails.put("Note Type",
				gm.readFromExcel("AnnualAssessments_CMDM", "FilterSupplementalInformation", 1, "Note Type"));
		multiMapFilterDetails.put("Note Type", "column_2");
		multiMapFilterDetails.put("Note Type", "3");

		multiMapFilterDetails.put("Note Description",
				gm.readFromExcel("AnnualAssessments_CMDM", "FilterSupplementalInformation", 1, "Note Description"));
		multiMapFilterDetails.put("Note Description", "column_3");
		multiMapFilterDetails.put("Note Description", "4");

		try {

			df.columnLevelFilteration(multiMapFilterDetails);
			Log.info("**********Verified the Column level Filters on Supplemental Information Grid**********");

		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.info("!!!!!!!!!!Could not verify the Column level Filters on Supplemental Information Grid!!!!!!!!!!");
		}
	}

	public void filterManagementReasonGrid() throws Exception {
		Log = Logger.getLogger("ManagementReasonPage.class");
		PropertyConfigurator.configure("log4j.properties");
		Global_Method gm = new Global_Method(driver);
		DashboardFunctions df = new DashboardFunctions(driver);

		MultiMap multiMapFilterDetails = new MultiValueMap();

		multiMapFilterDetails.put("Program Type",
				gm.readFromExcel("AnnualAssessments_CMDM", "FilterManagementReason", 1, "Program Type"));
		multiMapFilterDetails.put("Program Type", "column_1");
		multiMapFilterDetails.put("Program Type", "1");
		Log.info("Added Program Type to the Multimapfilterdetails");

		multiMapFilterDetails.put("Reason",
				gm.readFromExcel("AnnualAssessments_CMDM", "FilterManagementReason", 1, "Reason"));
		multiMapFilterDetails.put("Reason", "column_2");
		multiMapFilterDetails.put("Reason", "2");
		Log.info("Added Reason to the Multimapfilterdetails");

		multiMapFilterDetails.put("Status",
				gm.readFromExcel("AnnualAssessments_CMDM", "FilterManagementReason", 1, "Status"));
		multiMapFilterDetails.put("Status", "column_3");
		multiMapFilterDetails.put("Status", "3");
		Log.info("Added Status to the Multimapfilterdetails");

		multiMapFilterDetails.put("Status Reason",
				gm.readFromExcel("AnnualAssessments_CMDM", "FilterManagementReason", 1, "Status Reason"));
		multiMapFilterDetails.put("Status Reason", "column_4");
		multiMapFilterDetails.put("Status Reason", "4");
		Log.info("Added Status Reason to the Multimapfilterdetails");

		multiMapFilterDetails.put("Priority",
				gm.readFromExcel("AnnualAssessments_CMDM", "FilterManagementReason", 1, "Priority"));
		multiMapFilterDetails.put("Priority", "column_4");
		multiMapFilterDetails.put("Priority", "5");
		Log.info("Added Priority to the Multimapfilterdetails");

		multiMapFilterDetails.put("Date Identified",
				gm.readFromExcel("AnnualAssessments_CMDM", "FilterManagementReason", 1, "Date Identified"));
		multiMapFilterDetails.put("Date Identified", "column_5");
		multiMapFilterDetails.put("Date Identified", "6");
		Log.info("Added Date Identified to the Multimapfilterdetails");

		multiMapFilterDetails.put("Date Re-identified",
				gm.readFromExcel("AnnualAssessments_CMDM", "FilterManagementReason", 1, "Date Identified"));
		multiMapFilterDetails.put("Date Re-identified", "column_6");
		multiMapFilterDetails.put("Date Re-identified", "7");
		Log.info("Added Date Re-identified to the Multimapfilterdetails");

		multiMapFilterDetails.put("Date Closed",
				gm.readFromExcel("AnnualAssessments_CMDM", "FilterManagementReason", 1, "Date Closed"));
		multiMapFilterDetails.put("Date Closed", "column_7");
		multiMapFilterDetails.put("Date Closed", "8");
		Log.info("Added Date Closed to the Multimapfilterdetails");

		try {

			df.columnLevelFilteration(multiMapFilterDetails);
			Log.info("**********Verified the Column level Filters on Management Reason Grid**********");

		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.info("!!!!!!!!!!Could not verify the Column level Filters on Supplemental Information Grid!!!!!!!!!!");
		}
	}

	public void filterByProgramTypeForManagementReason(String programType) throws Exception {
		Log = Logger.getLogger("ManagementReasonPage.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

		try {
			WebElement dropdownArrowForFilterTypeOnManagementReasonElement = wait
					.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath(config.getDropdownArrowForFilterTypeOnManagementReasonXpath())));
			dropdownArrowForFilterTypeOnManagementReasonElement.click();
			Log.info("Clicked on Dropdown arrow for Program Type");

			WebElement programTypeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By
					.xpath("//label[contains(text(),'Program Type')]//ancestor::div//parent::li//span[contains(text(),'"
							+ programType + "')]")));
			programTypeElement.click();
			Log.info("Selected a Program Type from dropdown");

			WebElement filterButtonElement = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getFilterButtonXpath())));
			filterButtonElement.click();
			Log.info("Clicked on Filter button after selecting the program type from dropdown");

			try {
				List<WebElement> programTypeWebElements = driver
						.findElements(By.xpath(config.getProgramTypeWebElementsXpath()));
				/// th[span= Program
				/// Type]//ancestor::thead//following-sibling::tbody//tr/span[@title
				/// = 'No records found']
				// String programTypeWebElement;

				if (programTypeWebElements != null) {
					for (WebElement programTypeWebElement : programTypeWebElements) {
						String actualResult = programTypeWebElement.getText();
						Assert.assertEquals(actualResult, programType, "Filter by Program Type is not working");
					}
					Log.info("**********Program Type Filter is working properly**********");
				} else {
					Log.info("**********Program Type Filter is working properly but No records Found**********");
				}
			} catch (Exception e) {
				Log.info("!!!!!!!!!!!Program Type Filter is giving error - " + e.getStackTrace());

			}
		} catch (Exception e) {

			Log.info(e + " -Filter by program type failed");
			e.printStackTrace();
		}
	}

	public void sortManagementReasonGrid() throws Exception {
		Log = Logger.getLogger("ManagementReasonPage.class");
		PropertyConfigurator.configure("log4j.properties");
		DashboardFunctions df = new DashboardFunctions(driver);

		try {
			df.columnSortingOnGrid(driver, 1,2,2);
			//df.columnSorting(driver);
		} catch (Exception e) {

			Log.info(e + " -sorting management reasons grid failed");
			e.printStackTrace();
		}
		
	}

	public void sortSupplementalInformationGrid() throws Exception {
		Log = Logger.getLogger("ManagementReasonPage.class");
		PropertyConfigurator.configure("log4j.properties");
		DashboardFunctions df = new DashboardFunctions(driver);

		try {
			//df.columnSortingOnGrid(driver, 0);
			df.columnSorting(driver);
		} catch (Exception e) {

			Log.info(e + " - sorting supplementalgrid failed");
			e.printStackTrace();
		}
		Log.info("**********Verified Sorting on Supplemental Info grid**********");
	}

	/*
	 * public void verifyPresenceOfProgramTypeNReason(String programType, String
	 * reason) throws Exception { Log =
	 * Logger.getLogger("ManagementReasonPage.class");
	 * PropertyConfigurator.configure("log4j.properties");
	 * 
	 * // List<WebElement> programTypeWebelements = driver //
	 * .findElements(By.xpath("//div[@class ='ui-table-wrapper //
	 * ng-star-inserted']//tbody/tr")); // List<WebElement>
	 * programTypeNICUWebelements = // driver.findElements(By.xpath( //
	 * "//div[@class ='ui-table-wrapper //
	 * ng-star-inserted']//tbody/tr/td/span[@title = " + programType + // "]"));
	 * // List<WebElement> reasonWebelements = driver.findElements( //
	 * By.xpath("//div[@class ='ui-table-wrapper //
	 * ng-star-inserted']//tbody/tr/td[2]/span[@title=" // + reason + "]"));
	 * List<WebElement> nicuProgramTypeWithReason = driver .findElements(By.
	 * xpath("//div[@class ='ui-table-wrapper ng-star-inserted']//tbody/tr/td/span[@title ="
	 * + programType +
	 * "]/parent::td//following-sibling::td/span[contains(text(),'Reason')]"));
	 * 
	 * for (int i = 0; i < nicuProgramTypeWithReason.size(); i++) { if
	 * ((nicuProgramTypeWithReason.get(i).getText().contains("NICU")) &&
	 * ((nicuProgramTypeWithReason.get(i).getText().contains("Reason 1")))) {
	 * Log.info("Program Type NICU with Reason 1 is already present in the list"
	 * ); addVerifyManagementReasonGrid(2); } else if
	 * ((nicuProgramTypeWithReason.get(i).getText().contains("NICU")) &&
	 * (nicuProgramTypeWithReason.get(i).getText().contains("Reason 2"))) {
	 * Log.info("Program Type NICU with Reason 2 is already present in the list"
	 * ); addVerifyManagementReasonGrid(1); } else {
	 * Log.info("Program Type NICU with Reason is not present in the list");
	 * addVerifyManagementReasonGrid(1); } } }
	 */

	public Map<String, Integer> getRowCountForNotesNSupplementalInfo() throws Exception {
		Log = Logger.getLogger("ManagementReasonPage.class");
		PropertyConfigurator.configure("log4j.properties");
		LeftNavigationLink lnk = new LeftNavigationLink(driver);
		Global_Method gm = new Global_Method(driver);
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		Map<String, Integer> notescountfromgrid = new HashMap<String, Integer>();
		
		int rowCountofNotesGrid;
		int rowCountofSupplementalInfoGrid;
		try {
			lnk.clickNotes();
			Thread.sleep(GlobalValues.Sleep_wait_time);
			rowCountofNotesGrid = gm.getNoOfRowsInGrid("Notes");
			Thread.sleep(GlobalValues.Sleep_wait_time);
			notescountfromgrid.put("NotesGrid", rowCountofNotesGrid);
			Log.info("Number of rows in Notes Grid - "+ rowCountofNotesGrid);

			lnk.clickManagementReason();
			Thread.sleep(GlobalValues.Sleep_wait_time);
			WebElement editManagementReasonIconElement = wait.until(ExpectedConditions
					.presenceOfElementLocated(By.xpath(config.getEditManagementReasonXpath())));
			editManagementReasonIconElement.click();
			Thread.sleep(GlobalValues.Sleep_wait_time);
			Log.info("Clicked on Edit Management Reason Icon on the Grid");
			Thread.sleep(GlobalValues.Sleep_wait_time);
			
			WebElement editManagementReasonPageHeader =wait.until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//h3[contains(text(),'Edit Management Reason')]")));
			String editManagementReasonPageHeaderText = editManagementReasonPageHeader.getText();
			Log.info("Edit Management Reason Page Header = " + editManagementReasonPageHeaderText);
			
			rowCountofSupplementalInfoGrid = gm.getNoOfRowsInGrid("Supplemental Info");
			Thread.sleep(GlobalValues.Sleep_wait_time);
			Log.info("Number of rows in SupplementalInfo Grid - "+ rowCountofSupplementalInfoGrid);
			notescountfromgrid.put("SupplementalInfoGrid", rowCountofSupplementalInfoGrid);
			Thread.sleep(GlobalValues.Sleep_wait_time);
			
			lnk.clickManagementReason();
			Thread.sleep(GlobalValues.Sleep_wait_time);
			
			return notescountfromgrid;

		} catch (Exception e) {

			Log.info(e + " get count failed");
			e.printStackTrace();
			
		}return null;
	}

}
