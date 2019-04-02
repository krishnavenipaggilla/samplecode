package com.nimbus.pages;

import java.util.List;

import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections.map.MultiValueMap;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utility.ConfigReader;

public class ConditionsPage {
	ConfigReader config = new ConfigReader();

	WebDriver driver;
	Logger Log;

	public ConditionsPage(WebDriver ldriver) {
		this.driver = ldriver;
	}

	public ConditionsPage(Logger lLog) {
		this.Log = lLog;
	}

	public void verifyConditionsPage() throws Exception {
		Log = Logger.getLogger("ConditionsPage.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		Global_Method gm = new Global_Method(driver);
		LeftNavigationLink lnk = new LeftNavigationLink(driver);
		boolean passed = true;

		//lnk.clickHealthChart();
		try {
				addCondition();
		} catch (Exception e) {
			e.printStackTrace();
			Log.info(e);
			passed = false;
		}

		try {
				verifyAddedCondition();
		} catch (Exception e) {
			e.printStackTrace();
			Log.info(e);
			passed = false;
		}

		try {
				editAndVerifyConditions();
		} catch (Exception e) {
			e.printStackTrace();
			Log.info(e);
			passed = false;
		}
		
		try {
				numberOfColumnsInGridForpagination("Conditions", 25);
		} catch (Exception e) {
			e.printStackTrace();
			Log.info(e);
			passed = false;
		}
		
		try {
				this.verifyMainFocusYesPresence();
		} catch (Exception e) {
			e.printStackTrace();
			Log.info(e);
			passed = false;
		}

		try {

				columnLevelSortingForConditions();
				filterConditionsGrid();
					
		} catch (Exception e) {
			e.printStackTrace();
			Log.info(e);
			passed = false;
		}

		
		

		if (passed == true) {
			gm.Reports("NIM-11448", "Pass");
		} else {

			gm.Reports("NIM-11448", "Fail");
		}

	}

	public void addCondition() throws Exception {
		Log = Logger.getLogger("ConditionsPage.class");
		PropertyConfigurator.configure("log4j.properties");
		AnnualAssessment a = new AnnualAssessment(driver);
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		Global_Method gm = new Global_Method(driver);
		LeftNavigationLink lnk = new LeftNavigationLink(driver);

		int rowCount;
		int rowCountAfterAdd;
		String verifiedDateSystem;
		try {

			lnk.clickConditions();
			Thread.sleep(GlobalValues.Sleep_wait_time);

			WebElement conditionsGridHeaderElement = wait.until(ExpectedConditions
					.visibilityOf(driver.findElement(By.xpath(config.getConditionsGridHeaderXpath()))));
			String conditionsGridHeaderText = conditionsGridHeaderElement.getText().trim();
			Log.info(conditionsGridHeaderText);

			Assert.assertEquals(conditionsGridHeaderText, "Conditions", "AuthRepGrid Header is not matching");

			rowCount = gm.getNoOfRowsInGrid(conditionsGridHeaderText);
			Log.info("Row Count before adding a condition =  " + rowCount);

			WebElement addConditionLinkElement = wait.until(ExpectedConditions
					.elementToBeClickable(driver.findElement(By.xpath(config.getAddConditionLinkXpath()))));
			addConditionLinkElement.click();
			Thread.sleep(GlobalValues.Sleep_wait_time);

			// Verify conditions page header
			WebElement addEditConditionPageHeaderElement = wait.until(ExpectedConditions
					.visibilityOf(driver.findElement(By.xpath(config.getAddConditionPageHeaderXpath()))));
			String addEditConditionPageHeaderText = addEditConditionPageHeaderElement.getText().trim();
			Log.info("Conditions Page Header = " + addEditConditionPageHeaderText);

			Assert.assertEquals(addEditConditionPageHeaderText, "Add Condition",
					"Add Condition page header is not matching");

			// Fill in the Add Auth Rep form using Excel sheet
			a.annual_assessment("AnnualAssessments_CMDM", "AddCondition", 2);
			Thread.sleep(GlobalValues.Sleep_wait_time);

			rowCountAfterAdd = gm.getNoOfRowsInGrid(conditionsGridHeaderText);
			Log.info("Row Count after adding a condition =  " + rowCountAfterAdd);

			Assert.assertEquals(rowCountAfterAdd, rowCount + 1, "Record count after adding condition is not matching");
			Log.info("Successfully added Condition from Overview");
			Log.info("**********Verified Add Condition**********");
		} catch (Exception e) {

			Log.info(e + " - Couldn`t verify add Conditions");
			e.printStackTrace();
		}

		

	}

	public void verifyAddedCondition() throws Exception {
		Log = Logger.getLogger("ConditionsPage.class");
		PropertyConfigurator.configure("log4j.properties");
		AnnualAssessment a = new AnnualAssessment(driver);
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		Global_Method gm = new Global_Method(driver);
		String mainFocusOnGrid;

		try {
			String mainFocus = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getMainFocusXpath()))).getText();

			String condition = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getConditionXpath()))).getText();

			String diagnosisOnsetDate = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getDiagnosisOnsetDateXpath())))
					.getText();
			String clinicalStatus = driver.findElement(By.xpath(config.getClinicalStatusXpath())).getText();

			String resolvedDate = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getResolvedDateXpath())))
					.getText();
			String risk = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getRiskXpath())))
					.getText();
			String source = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getSourceXpathForCondition())))
					.getText();
			String verificationStatus = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getVerificationStatusXpath())))
					.getText();

			String mainFocusFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "AddCondition", 2, "Primary");
			String conditionFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "AddCondition", 2, "Condition");

			String diagnosisOnsetDateFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "AddCondition", 2,
					"Diagnosis/Onset Date");
			String clinicalStatusExcel = gm.readFromExcel("AnnualAssessments_CMDM", "AddCondition", 2,
					"Clinical Status");
			String resolvedDateFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "AddCondition", 2,
					"Resolved Date");
			String riskFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "AddCondition", 2, "Risk");
			String sourceFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "AddCondition", 2, "Source");
			String verificationStatusFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "AddCondition", 2,
					"Verification Status");
			if (mainFocusFromExcel.contains("click")) {
				mainFocusOnGrid = "true";
			} else {
				mainFocusOnGrid = " ";
			}

			Assert.assertEquals(mainFocus, mainFocusOnGrid,
					"Added values are not matching between excel and as shown on UI");
			Assert.assertEquals(condition, conditionFromExcel,
					"Added values are not matching between excel and as shown on UI");

			Assert.assertEquals(diagnosisOnsetDate, diagnosisOnsetDateFromExcel,
					"Added values are not matching between excel and as shown on UI");
			Assert.assertEquals(clinicalStatus, clinicalStatusExcel,
					"Added values are not matching between excel and as shown on UI");
			Assert.assertEquals(resolvedDate, resolvedDateFromExcel,
					"Added values are not matching between excel and as shown on UI");
			Assert.assertEquals(risk, riskFromExcel, "Added values are not matching between excel and as shown on UI ");
			Assert.assertEquals(source, sourceFromExcel,
					"Added values are not matching between excel and as shown on UI");
			Assert.assertEquals(verificationStatus, verificationStatusFromExcel,
					"Added values are not matching between excel and as shown on UI");

			Log.info("Successfully verified the Added Condition");
			Log.info("**********Verified Added Condition**********");

		} catch (Exception e) {

			Log.info(e + " -Couldn`t verify added condition");
			e.printStackTrace();
		}

	}

	public void editAndVerifyConditions() throws Exception {
		Log = Logger.getLogger("ConditionsPage.class");
		PropertyConfigurator.configure("log4j.properties");
		AnnualAssessment a = new AnnualAssessment(driver);
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		Global_Method gm = new Global_Method(driver);
		LeftNavigationLink lnk = new LeftNavigationLink(driver);
		int rowCountBeforeEdit;
		int rowCountAfterEdit;
		String mainFocusOnGrid = null;
		boolean isFocusClicked = false;

		try {

			WebElement conditionsGridHeaderElement = wait.until(ExpectedConditions
					.visibilityOf(driver.findElement(By.xpath(config.getConditionsGridHeaderXpath()))));
			String conditionsGridHeaderText = conditionsGridHeaderElement.getText().trim();
			Log.info(conditionsGridHeaderText);

			Assert.assertEquals(conditionsGridHeaderText, "Conditions", "Conditions Header is not matching");

			rowCountBeforeEdit = gm.getNoOfRowsInGrid(conditionsGridHeaderText);
			Log.info("Row Count before edit = " + rowCountBeforeEdit);

			WebElement editIconForAuthRepOnOverViewElement = driver.findElement(By.xpath(
					config.getEditIconForFirstConditionXpath()));
			editIconForAuthRepOnOverViewElement.click();
			Log.info("Clicked on the Edit Note+Pen icon on the First record in Conditions grid");

			// Verify conditions page header
			WebElement addEditConditionPageHeaderElement = wait.until(ExpectedConditions
					.visibilityOf(driver.findElement(By.xpath(config.getEditConditionPageHeaderXpath()))));
			String addEditConditionPageHeaderText = addEditConditionPageHeaderElement.getText().trim();
			Log.info("Conditions Page Header = " + addEditConditionPageHeaderText);

			Assert.assertEquals(addEditConditionPageHeaderText, "Edit Condition",
					"Edit Condition page header is not matching");

			// Fill in the Add Auth Rep form using Excel sheet
			a.annual_assessment("AnnualAssessments_CMDM", "EditCondition", 2);
			Thread.sleep(GlobalValues.Sleep_wait_time);
			int ConditionsGridCountAfterEdit = gm.getNoOfRowsInGrid(conditionsGridHeaderText);
			Log.info("Grid count after editing a record =  " + ConditionsGridCountAfterEdit);
			Assert.assertEquals(ConditionsGridCountAfterEdit, rowCountBeforeEdit,
					"Grid Count mismatch after editing a record");
			Thread.sleep(GlobalValues.Sleep_wait_time);

			String mainFocus = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getMainFocusXpath()))).getText();

			String condition = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getConditionXpath()))).getText();

			String diagnosisOnsetDate = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getDiagnosisOnsetDateXpath())))
					.getText();
			String clinicalStatus = driver.findElement(By.xpath(config.getClinicalStatusXpath())).getText();

			String resolvedDate = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getResolvedDateXpath())))
					.getText();
			String risk = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getRiskXpath())))
					.getText();
			String source = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getSourceXpathForCondition())))
					.getText();
			String verificationStatus = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getVerificationStatusXpath())))
					.getText();

			String mainFocusFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "EditCondition", 2, "Primary");
			String conditionFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "EditCondition", 2, "Condition");

			String diagnosisOnsetDateFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "EditCondition", 2,
					"Diagnosis/Onset Date");
			String clinicalStatusExcel = gm.readFromExcel("AnnualAssessments_CMDM", "EditCondition", 2,
					"Clinical Status");
			String resolvedDateFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "EditCondition", 2,
					"Resolved Date");
			String riskFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "EditCondition", 2, "Risk");
			String sourceFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "EditCondition", 2, "Source");
			String verificationStatusFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "EditCondition", 2,
					"Verification Status");
			
			if((mainFocusFromExcel != null) && mainFocusFromExcel.contains("Click")){
				isFocusClicked = true;
			}
			
			boolean isFocusClickedInGrid = false;
			String foucsFromAdd = gm.readFromExcel("AnnualAssessments_CMDM", "AddCondition", 2, "Primary");
			if(foucsFromAdd != null && foucsFromAdd.contains("click")){
				isFocusClickedInGrid = true;
			}
			
			String focusOld = isFocusClickedInGrid?"true":" ";
			String focusNew = focusOld;
			if(isFocusClicked){
				if(focusOld == "true"){
					focusNew = " ";
				}else {
					focusNew = "true";
				}
			}


			Log.info("Main Focus = " + mainFocusOnGrid);

			Assert.assertEquals(mainFocus, focusNew,
					"Added values are not matching between excel and as shown on UI");
			Assert.assertEquals(condition, conditionFromExcel,
					"Added values are not matching between excel and as shown on UI");

			Assert.assertEquals(diagnosisOnsetDate, diagnosisOnsetDateFromExcel,
					"Added values are not matching between excel and as shown on UI");
			Assert.assertEquals(clinicalStatus, clinicalStatusExcel,
					"Added values are not matching between excel and as shown on UI");
			Assert.assertEquals(resolvedDate, resolvedDateFromExcel,
					"Added values are not matching between excel and as shown on UI");
			Assert.assertEquals(risk, riskFromExcel, "Added values are not matching between excel and as shown on UI");
			Assert.assertEquals(source, sourceFromExcel,
					"Added values are not matching between excel and as shown on UI");
			Assert.assertEquals(verificationStatus, verificationStatusFromExcel,
					"Added values are not matching between excel and as shown on UI");

			Log.info("Successfully verified edit functionality for Conditions grid ");
			Log.info("**********verified Edit functionality for Conditions**********");

		} catch (Exception e) {

			Log.info(e + " Grid Title is not matching");
			e.printStackTrace();
		}

		
	}

	public void columnLevelSortingForConditions() throws Exception {
		Log = Logger.getLogger("ConditionsPage.class");
		PropertyConfigurator.configure("log4j.properties");
		DashboardFunctions df = new DashboardFunctions(driver);
		LeftNavigationLink lnk = new LeftNavigationLink(driver);
		try {
			lnk.clickConditions();
			WebElement gridTitleElement = driver.findElement(By.xpath(config.getConditionsGridHeaderXpath()));
			String gridTitle = gridTitleElement.getText();
			Log.info(gridTitle);
			Assert.assertEquals(gridTitle, "Conditions", "Grid Header is not matching");

			//df.columnSortingOnGrid(driver, 0, 1, 1);
			df.columnSorting(driver);
			Log.info("Successfully verified column level sorting for Conditions Grid ");
			Log.info("**********verified column level sorting for Conditions grid**********");

		} catch (Exception e) {

			Log.info(e + " - sorting Conditions grid failed");
		}

		
	}
	// No of columns in grid for pagination

	public void numberOfColumnsInGridForpagination(String gridName, int requiredRowCount) throws Exception {
		Log = Logger.getLogger("ConditionsPage.class");
		PropertyConfigurator.configure("log4j.properties");

		try {
			WebElement gridTitleElement = driver.findElement(By.xpath(config.getConditionsGridHeaderXpath()));
			String gridTitle = gridTitleElement.getText();
			Log.info(gridTitle);
			Assert.assertEquals(gridTitle, "Conditions", "Grid Header/Title is not matching");
			List<WebElement> gridRowsCount = driver.findElements(By.xpath(config.getGridRowCountElementXpath()));
			int rowCount = gridRowsCount.size();

			if (rowCount > requiredRowCount) {
				Log.info("Number of rows for pagination is more than required");
			} else {
				Log.info("Number of rows for pagination is as required");
				Log.info("**********verified pagination for Conditions Grid**********");
				
			}

		} catch (Exception e) {

			Log.info(e + " Grid Title is not matching");
		}

		
	}

	public void filterConditionsGrid() throws Exception {
		Log = Logger.getLogger("ConditionsPage.class");
		PropertyConfigurator.configure("log4j.properties");
		Global_Method gm = new Global_Method(driver);
		DashboardFunctions df = new DashboardFunctions(driver);

		MultiMap multiMapFilterDetails = new MultiValueMap();

		multiMapFilterDetails.put("Clinical Status",
				gm.readFromExcel("AnnualAssessments_CMDM", "FilterConditions", 1, "Clinical Status"));
		multiMapFilterDetails.put("Clinical Status", "column_1");
		multiMapFilterDetails.put("Clinical Status", "4");

		multiMapFilterDetails.put("Verification Status",
				gm.readFromExcel("AnnualAssessments_CMDM", "FilterConditions", 1, "Verification Status"));
		multiMapFilterDetails.put("Verification Status", "column_2");
		multiMapFilterDetails.put("Verification Status", "6");

		multiMapFilterDetails.put("Risk", gm.readFromExcel("AnnualAssessments_CMDM", "FilterConditions", 1, "Risk"));
		multiMapFilterDetails.put("Risk", "column_3");
		multiMapFilterDetails.put("Risk", "7");

		try {

			df.columnLevelFilteration(multiMapFilterDetails);
			Log.info("**********Verified the Column level Filters on Conditions Grid**********");

		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.info("Could not verify the Column level Filters on Conditions Grid");
		}
	}
	
	public void verifyMainFocusYesPresence() throws Exception {
		Log = Logger.getLogger("ConditionsPage.class");
		PropertyConfigurator.configure("log4j.properties");
		AnnualAssessment a = new AnnualAssessment(driver);
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		Global_Method gm = new Global_Method(driver);
		LeftNavigationLink lnk = new LeftNavigationLink(driver);
		
	try{
		for(int i=0; i<3; i++){
		
			WebElement addConditionLinkElement = wait.until(ExpectedConditions
					.elementToBeClickable(driver.findElement(By.xpath(config.getAddConditionLinkXpath()))));
			addConditionLinkElement.click();
			Thread.sleep(GlobalValues.Sleep_wait_time);

			// Verify conditions page header
			WebElement addEditConditionPageHeaderElement = wait.until(ExpectedConditions
					.visibilityOf(driver.findElement(By.xpath(config.getAddConditionPageHeaderXpath()))));
			String addEditConditionPageHeaderText = addEditConditionPageHeaderElement.getText().trim();
			Log.info("Conditions Page Header = " + addEditConditionPageHeaderText);

			Assert.assertEquals(addEditConditionPageHeaderText, "Add Condition",
					"Edit Condition page header is not matching");

			// Fill in the Add Auth Rep form using Excel sheet
			a.annual_assessment("AnnualAssessments_CMDM", "AddCondition", 2+i);
			Thread.sleep(GlobalValues.Sleep_wait_time);
		}
		
		List<WebElement> mainFocusYesElement = driver.findElements(By.xpath(config.getMainFocusYesElementsXpath()));
		int mainFocusYesElementsSize = mainFocusYesElement.size();
		
		Assert.assertEquals(mainFocusYesElementsSize, 1, "There should be only One Main Focus with Yes");
		Log.info("**********Verified Presence of One Main Focus with 'Yes'**********");
	} catch (Exception e) {

		Log.info(e + " -  Presence of 'One' Primary with 'true' failed");
		e.printStackTrace();
	}
	}
}
