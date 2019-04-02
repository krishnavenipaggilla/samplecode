package com.nimbus.pages;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import excelReader.DataReaderExcel;
import utility.ConfigReader;

public class LifeStylePreventionSubSection {

	WebDriver driver;
	Logger Log;

	ConfigReader config = new ConfigReader();
	DataReaderExcel DataRead = new DataReaderExcel();

	public LifeStylePreventionSubSection(WebDriver ldriver) {
		this.driver = ldriver;

	}

	public LifeStylePreventionSubSection(Logger lLog) {
		this.Log = lLog;

	}
	
	String sourceValueSelected;
	
	public void verifyLSPFromActivity(String TaskName) throws Exception {
		Log = Logger.getLogger("LifeStylePreventionSubSection.class");
		PropertyConfigurator.configure("log4j.properties");
		LeftNavigationLink lnk = new LeftNavigationLink(driver);
		 new Global_Method(driver);
		boolean passed = true;
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		try{
			addLSPSubActivity(TaskName);
			Thread.sleep(GlobalValues.Sleep_wait_time);
			Log.info("Verified Filling LSP Form from Activity");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
			Thread.sleep(1000);
//			driver.findElement(By.xpath("//button[contains(text(),'Ok')]")).click();
			Log.info("Clicked on Ok for Alert : Progress has been saved");
			Thread.sleep(GlobalValues.Sleep_wait_time);
			Log.info("Passed-NIM-17840: Activity sub section: Lifestyle & Prevention Create subsection (accordion)" + '\n' + "GIVEN:Lifestyle & Prevention Create subsection"
					+ '\n' + "THEN:verifying Lifestyle & Prevention Create subsection");
		}catch (Exception e) {
			e.printStackTrace();
			Log.info(e);
			passed = false;
			Log.info("Failed-NIM-17840: Activity sub section: Lifestyle & Prevention Create subsection (accordion)");
		}
		
		try{
			verifyHistoryForLSPFromActivity();
			Log.info("Verified the History for respective questions in the LSP form from Activity");
			Log.info("Passed-NIM-17842: Activity sub section: L&P display Audit info at each question" + '\n' + "GIVEN:L&P display Audit info at each question"
					+ '\n' + "THEN:verifying L&P display Audit info at each question");
			Log.info("Passed-NIM-17843: Activity sub section: Lifestyle & Prevention: View history of update" + '\n' + "GIVEN:Lifestyle & Prevention: View history of update"
					+ '\n' + "THEN:verifying  View history of update for Lifestyle and prevention");
			
			Thread.sleep(GlobalValues.Sleep_wait_time);
		}catch (Exception e) {
			e.printStackTrace();
			Log.info(e);
			passed = false;
			Log.info("Failed-NIM-17842: Activity sub section: L&P display Audit info at each question");
			Log.info("Failed-NIM-17843: Activity sub section: Lifestyle & Prevention: View history of update");
		}
		
		try{
			editLSPFromActivity();
			Log.info("Verified the editing LSP from Activity");
			driver.findElement(By.xpath("//button[contains(text(),'Ok')]")).click();
			Log.info("Clicked on Ok for Alert : Progress has been saved");
		}catch (Exception e) {
			e.printStackTrace();
			Log.info(e);
			passed = false;
		}
		
		try{
			lnk.clickHealthChart();
			lnk.clickLifestyleAndPrevention();
			verifyLSP_LN_AfterEditingFromActivity();
			Log.info("Verified the LSP Left Nav after editing LSP from Activity");
		}catch (Exception e) {
			e.printStackTrace();
			Log.info(e);
			passed = false;
		}
		
		if (passed == true) {
//			 GM.Reports("NIM-17060", "Pass");
		} else {

//			GM.Reports("NIM-17060", "Fail");
		}
		
	}
	

	public void addLSPSubActivity(String TaskName) throws Exception {
		Log = Logger.getLogger("LifeStylePreventionSubSection.class");
		PropertyConfigurator.configure("log4j.properties");
		ActivityPage addActivity = new ActivityPage(driver);
//		addActivity.editInitialOutreachActivity(TaskName);
		
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		try {
			driver.findElement(By.xpath("//h2[contains(text(),'Lifestyle & Prevention')]")).click();
			Log.info("Clicked on LSP Accordian");
			Thread.sleep(GlobalValues.Sleep_wait_time);
		}catch(Exception e){
			driver.findElement(By.xpath("//button[@title = 'Add Sub Activity' and @type ='button']")).click();
			Log.info("Clicked on add subactivity Icon");
			String subActivityModelWindowHeader = driver.findElement(By.xpath("//p-header[contains(text(),'Add Sub Activity')]")).getText().trim();
			Assert.assertEquals(subActivityModelWindowHeader,"Add Sub Activity");
			
			driver.findElement(By.xpath("//label[contains(text(),'Lifestyle & Prevention')]//..//label")).click();
			Log.info("Adding LSP sub section from the modal window");
			Thread.sleep(GlobalValues.Sleep_wait_time);
			
			driver.findElement(By.xpath("//button[text()='Add']")).click();
			Log.info("Clicked on Add button of modal window");
			
			Thread.sleep(GlobalValues.Sleep_wait_time);
			
			driver.findElement(By.xpath("//h2[contains(text(),'Lifestyle & Prevention')]")).click();
			Log.info("Clicked on LSP Accordian");
			Thread.sleep(GlobalValues.Sleep_wait_time);
		}
		
		AnnualAssessment annual = new AnnualAssessment(driver);
		
		// verifying the page header
				WebElement PageHeader = driver.findElement(By.xpath("//legend[contains(text(),'Lifestyle and Prevention')]"));
				Assert.assertEquals(PageHeader.getText(), "Lifestyle and Prevention",
						"Lifestyle and Prevention page header is not matching");
				Log.info("Successfully verifed the Lifestyle and Prevention page header");
				Thread.sleep(3000);

				annual.annual_assessment("AnnualAssessments_CMDM", "LSP_Add", 2);
				Thread.sleep(3000);

				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				Date date = new Date();
				// df.setTimeZone(TimeZone.getTimeZone("UTC"));
				GlobalValues.LifestylePreventionSubmit_Date = df.format(date);
				Log.info(GlobalValues.LifestylePreventionSubmit_Date);

				WebElement Source_dropdown = driver.findElement(By.xpath(config.SourceDropDown())); // capturing
																									// label
				String text = " If applicable, please select source of current changes ";

				if (Source_dropdown.getText().equals(text.trim())) {

					WebElement SourceValue = driver.findElement(By.xpath(config.SourceValue()));
					Thread.sleep(2000);
					sourceValueSelected = SourceValue.getText();
					Thread.sleep(2000);
					Log.info("Capturing selected Source value while submitting in Lifestyle and Prevention");
				} else {
					System.out.println("Both are not same");
					Log.info("Not captured the Souce drop dwon value while submitting");
				}

				WebElement Submitclick = driver.findElement(By.xpath("//h2[contains(text(),'Lifestyle & Prevention')]//ancestor::div[@class ='ui-accordion-header ui-state-default ui-corner-all ui-state-active']//following::div//button[contains(text(),'Save Progress')]"));
				Submitclick.click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
				Thread.sleep(1000);
				Log.info("LifestyleAndPrevention is Submitted");
				
				driver.findElement(By.xpath("//button[text()='Ok']")).click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
				Thread.sleep(1000);
		
	}
	

	
	public void editLSPFromActivity() throws Exception {
		Log = Logger.getLogger("LifeStylePreventionSubSection.class");
		PropertyConfigurator.configure("log4j.properties");
		LifeStyleAndPrevention lsp = new LifeStyleAndPrevention(driver);
		lsp.LSP_Edit() ;
		Log.info("Edited the LSP Form");
		driver.findElement(By.xpath("//div[@class ='ui-accordion-header ui-state-default ui-corner-all ui-state-active']//ancestor::nm-form//button[contains(text(),'Save Progress')]")).click();
		Log.info("Clicked on Save Progress after editing the values");
	}
	
	public void verifyLSP_LN_AfterEditingFromActivity() throws Exception {
		
		Log = Logger.getLogger("LifeStylePreventionSubSection.class");
		PropertyConfigurator.configure("log4j.properties");
		
		ValidateAnnualAssessment validateEditGM = new ValidateAnnualAssessment(driver);
		validateEditGM.validate_annual_assessment("AnnualAssessments_CMDM", "LSP_Edit", 2);
		
		Log.info("Validated that the edited values are reflecting in the LeftNav");
		Thread.sleep(GlobalValues.Sleep_wait_time);
	}
	
	public void verifyHistoryForLSPFromActivity() throws Exception {
		Log = Logger.getLogger("LifeStylePreventionSubSection.class");
		PropertyConfigurator.configure("log4j.properties");
		new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		Global_Method gm = new Global_Method(driver);
		new DashboardFunctions(driver);
		new LeftNavigationLink(driver);
		
		try {
			GlobalValues.UserNameOnUI = driver.findElement(By.xpath("//div[@class ='userName']//child::nm-value"))
					.getText().trim();
			String enteredBy = GlobalValues.UserNameOnUI;

			String programName = "";
			if (GlobalValues.DBProgramNameActiveStatus.contains("true")
					&& GlobalValues.DBProgramNamePrimaryStatus.contains("true")) {
				programName = GlobalValues.DBProgramName;
			}

//			String programName = driver.findElement(By.xpath("//label[contains(text(),'Program Name')]//..//parent::div//child::div//span"))
//					.getText().toString().trim();
//			
//			String programNameFromBanner = driver.findElement(By.xpath(config.getProgramNameFromBannerXpath()))
//					.getText().toString().trim();
			Log.info("program form banner--"+programName );
			String sourceFromExcel = sourceValueSelected;

			// Validate Question - Child's feeding method?
			driver.findElement(By.xpath("(//button[contains(text(), 'Show History')])[1]")).click();
			Thread.sleep(GlobalValues.Sleep_wait_time);
			List<WebElement> tableHeaders = driver.findElements(By.xpath("//div[@class ='ui-accordion-header ui-state-default ui-corner-all ui-state-active']//ancestor::nm-form//div[not(@hidden)]/parent::nm-table//th"));
			int numberOfColumnsInHistoryGrid = tableHeaders.size();
			Log.info(numberOfColumnsInHistoryGrid);

			validateColumnsOfWithoutDOSGrid();
			Thread.sleep(3000);
			WebElement childFeedingMethodElement = driver
					.findElement(By.xpath(config.getGMQuestionLabelXpathFromActivityXpath().replace("instance", "6")));
			
			
			
			String childFeedingMethodElementQuestionText = childFeedingMethodElement.getText().trim();
			String answerFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "LSP_Add", 2,
					childFeedingMethodElementQuestionText);
			

			if (answerFromExcel.contains("Other")) {
				answerFromExcel = "Other - "
						+ gm.readFromExcel("AnnualAssessments_CMDM", "LSP_Add", 2, "If Other, please explain");
			}
			List<WebElement> answersFromUIGrid = driver.findElements(By.xpath("//div[@class ='ui-accordion-header ui-state-default ui-corner-all ui-state-active']//ancestor::nm-form//div[not(@hidden)]/parent::nm-table//tbody//tr[position()=1]/td"));

try{
			Assert.assertEquals(answersFromUIGrid.get(0).getText().trim().contains(answerFromExcel), true);
			Assert.assertEquals(answersFromUIGrid.get(1).getText().trim().substring(0, 10), GlobalValues.LifestylePreventionSubmit_Date);
			Assert.assertEquals(answersFromUIGrid.get(2).getText().trim(), enteredBy);
			Assert.assertEquals(answersFromUIGrid.get(3).getText().trim(), programName);
			Assert.assertEquals(answersFromUIGrid.get(4).getText().trim(), sourceFromExcel);
}
catch(AssertionError e){
	Log.info("Assertion--"+e);	
}

			Log.info("**********Verified the data  in the history grid **********"
					+ childFeedingMethodElementQuestionText);

			Thread.sleep(GlobalValues.Sleep_wait_time);
			WebElement hideHistoryElement = driver.findElement(By.xpath(config.gethideHistoryElementXpath()));
			hideHistoryElement.click();
			Thread.sleep(GlobalValues.Sleep_wait_time);

			// Validate Question - Special Diet or Formula needs?
			driver.findElement(By.xpath("(//button[contains(text(), 'Show History')])[2]")).click();
			Thread.sleep(GlobalValues.Sleep_wait_time);

			validateColumnsOfWithoutDOSGrid();

			WebElement specialDietFormulaNeedsElement = driver
					.findElement(By.xpath(config.getGMQuestionLegendXpathFromActivityXpath().replace("instance", "5")));
			String specialDietFormulaNeedsElementQuestionText = specialDietFormulaNeedsElement.getText().trim();
			answerFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "LSP_Add", 2,
					specialDietFormulaNeedsElementQuestionText);
			

			if (answerFromExcel.contains("Yes")) {
				answerFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "LSP_Add", 2,
						"If Yes, what is the formula");
				answersFromUIGrid = driver.findElements(By.xpath("//div[@class ='ui-accordion-header ui-state-default ui-corner-all ui-state-active']//ancestor::nm-form//div[not(@hidden)]/parent::nm-table//tbody//tr[position()=1]/td"));
				try{
				Assert.assertEquals(answersFromUIGrid.get(0).getText().trim().contains(answerFromExcel), true);
				Assert.assertEquals(answersFromUIGrid.get(1).getText().trim().substring(0, 10),
						GlobalValues.LifestylePreventionSubmit_Date);
				Assert.assertEquals(answersFromUIGrid.get(2).getText().trim(), enteredBy);
				Assert.assertEquals(answersFromUIGrid.get(3).getText().trim(), programName);
				Assert.assertEquals(answersFromUIGrid.get(4).getText().trim(), sourceFromExcel);
				}
				catch(AssertionError e){
					Log.info("Assertion--"+e);	
				}

				Log.info("********** Verified the data  in the history grid  **********"
						+ specialDietFormulaNeedsElementQuestionText);
			} else if (answerFromExcel.contains("No")) {
				Log.info(
						"History is not captured for 'No'.History is captured for if answered 'Yes' for 'Special Diet or Formula needs'");
			}

			Thread.sleep(GlobalValues.Sleep_wait_time);
			hideHistoryElement = driver.findElement(By.xpath(config.gethideHistoryElementXpath()));
			hideHistoryElement.click();
			Thread.sleep(GlobalValues.Sleep_wait_time);

			// Validate Question - Substances currently used by parent/caregiver

			driver.findElement(By.xpath("(//button[contains(text(), 'Show History')])[3]")).click();
			Thread.sleep(GlobalValues.Sleep_wait_time);

			validateColumnsOfWithoutDOSGrid();

			WebElement substancesUsedElement = driver
					.findElement(By.xpath(config.getGMQuestionLegendXpathFromActivityXpath().replace("instance", "5")));
			String substancesUsedElementElementQuestionText = substancesUsedElement.getText().trim();
			answerFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "LSP_Add", 2,
					substancesUsedElementElementQuestionText);
			

			answersFromUIGrid = driver.findElements(By.xpath("//div[@class ='ui-accordion-header ui-state-default ui-corner-all ui-state-active']//ancestor::nm-form//div[not(@hidden)]/parent::nm-table//tbody//tr[position()=1]/td"));

			//answersFromUIGrid = driver.findElements(By.xpath(config.getanswersFromUIGridXpath()));
			String[] answersFromExcel = answerFromExcel.split(",");
			for(String ans: answersFromExcel)
			{
			Assert.assertEquals(answersFromUIGrid.get(0).getText().trim().contains(ans), true);
			}
			try{
//			Assert.assertEquals(answersFromUIGrid.get(0).getText().trim().contains(answerFromExcel), true);
			Assert.assertEquals(answersFromUIGrid.get(1).getText().trim().substring(0, 10), GlobalValues.LifestylePreventionSubmit_Date);
			Assert.assertEquals(answersFromUIGrid.get(2).getText().trim(), enteredBy);
			Assert.assertEquals(answersFromUIGrid.get(3).getText().trim(), programName);
			Assert.assertEquals(answersFromUIGrid.get(4).getText().trim(), sourceFromExcel);
			}
			catch(AssertionError e){
				Log.info("Assertion--"+e);	
			}

			Log.info("**********Verified the data  in the history grid**********"
					+ substancesUsedElementElementQuestionText);

			Thread.sleep(GlobalValues.Sleep_wait_time);
			hideHistoryElement = driver.findElement(By.xpath(config.gethideHistoryElementXpath()));
			hideHistoryElement.click();
			Thread.sleep(GlobalValues.Sleep_wait_time);
			
			
			//Validate Question - Others living in home using tobacco products:
			
			driver.findElement(By.xpath("(//button[contains(text(), 'Show History')])[4]")).click();
			Thread.sleep(GlobalValues.Sleep_wait_time);

			validateColumnsOfWithoutDOSGrid();

			WebElement usingTobaccoElement = driver
					.findElement(By.xpath(config.getGMQuestionLegendXpathFromActivityXpath().replace("instance", "5")));
			String usingTobaccoElementQuestionText = usingTobaccoElement.getText().trim();
			answerFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "LSP_Add", 2,
					usingTobaccoElementQuestionText);
			

			answersFromUIGrid = driver.findElements(By.xpath("//div[@class ='ui-accordion-header ui-state-default ui-corner-all ui-state-active']//ancestor::nm-form//div[not(@hidden)]/parent::nm-table//tbody//tr[position()=1]/td"));
try{
			Assert.assertEquals(answersFromUIGrid.get(0).getText().trim().contains(answerFromExcel), true);
			Assert.assertEquals(answersFromUIGrid.get(1).getText().trim().substring(0, 10), GlobalValues.LifestylePreventionSubmit_Date);
			Assert.assertEquals(answersFromUIGrid.get(2).getText().trim(), enteredBy);
			Assert.assertEquals(answersFromUIGrid.get(3).getText().trim(), programName);
			Assert.assertEquals(answersFromUIGrid.get(4).getText().trim(), sourceFromExcel);
}
catch(AssertionError e){
	Log.info("Assertion--"+e);	
}

			Log.info("**********Verified the data  in the history grid**********"
					+ usingTobaccoElementQuestionText);

			Thread.sleep(GlobalValues.Sleep_wait_time);
			hideHistoryElement = driver.findElement(By.xpath(config.gethideHistoryElementXpath()));
			hideHistoryElement.click();
			Thread.sleep(GlobalValues.Sleep_wait_time);
			
			//Validate Question - Education/Resources provided:
			
			driver.findElement(By.xpath("(//button[contains(text(), 'Show History')])[5]")).click();
			Thread.sleep(GlobalValues.Sleep_wait_time);

			validateColumnsOfWithoutDOSGrid();

			WebElement educationProvidedElement = driver
					.findElement(By.xpath(config.getGMQuestionLegendXpathFromActivityXpath().replace("instance", "8")));
			String educationProvidedElementQuestionText = educationProvidedElement.getText().trim();
			answerFromExcel = gm.readFromExcel("AnnualAssessments_CMDM", "LSP_Add", 2,
					educationProvidedElementQuestionText);
			
			answersFromUIGrid = driver.findElements(By.xpath("//div[@class ='ui-accordion-header ui-state-default ui-corner-all ui-state-active']//ancestor::nm-form//div[not(@hidden)]/parent::nm-table//tbody//tr[position()=1]/td"));
try{
			Assert.assertEquals(answersFromUIGrid.get(0).getText().trim().contains(answerFromExcel), true);
			Assert.assertEquals(answersFromUIGrid.get(1).getText().trim().substring(0, 10), GlobalValues.LifestylePreventionSubmit_Date);
			Assert.assertEquals(answersFromUIGrid.get(2).getText().trim(), enteredBy);
			Assert.assertEquals(answersFromUIGrid.get(3).getText().trim(), programName);
			Assert.assertEquals(answersFromUIGrid.get(4).getText().trim(), sourceFromExcel);
}
catch(AssertionError e){
	Log.info("Assertion--"+e);	
}

			Log.info("**********Verified the data  in the history grid **********"
					+ educationProvidedElementQuestionText);

			Thread.sleep(GlobalValues.Sleep_wait_time);
			hideHistoryElement = driver.findElement(By.xpath(config.gethideHistoryElementXpath()));
			hideHistoryElement.click();
			Thread.sleep(GlobalValues.Sleep_wait_time);

		} catch (Exception e) {
			
			e.printStackTrace();
			Log.info(e);
			throw e;
		}
	}
	
	public void validateColumnsOfWithoutDOSGrid() throws Exception {
		Log = Logger.getLogger("LifeStylePreventionSubSection.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		new Global_Method(driver);
		new DashboardFunctions(driver);

		try {
			String gridHeaderText = wait
					.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath(config.getGMgridHeaderTextFromActivityXpath())))
					.getText();
			Log.info("Grid Header is " + gridHeaderText);
			Assert.assertEquals(gridHeaderText.trim(), "History", "Grid header not matching");

			List<WebElement> actualColumnHeaders = driver.findElements(By.xpath(
					config.getGMactualColumnHeadersFromActivityXpath()));
			try{
			Assert.assertEquals(actualColumnHeaders.get(0).getText().trim(), "Answer");
			Assert.assertEquals(actualColumnHeaders.get(1).getText().trim(), "Date Entered");
			Assert.assertEquals(actualColumnHeaders.get(2).getText().trim(), "Entered By");
			Assert.assertEquals(actualColumnHeaders.get(3).getText().trim(), "Program");
			Assert.assertEquals(actualColumnHeaders.get(4).getText().trim(), "Source");
			}
			catch(AssertionError e){
				Log.info("Assertion--"+e);	
			}
			Log.info("Grid Header has been verified");
			Log.info("Getting the list of Column names for History Grid");
			Thread.sleep(GlobalValues.Sleep_wait_time);
			Log.info("**********History Grid Column Names has been verified for NON DOS**********");

		} catch (Exception e) {
			e.printStackTrace();
			Log.info(e);
		}
	}
	
	public void validateColumnsOfWithDOSGrid() throws Exception {
		Log = Logger.getLogger("LifeStylePreventionSubSection.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		new Global_Method(driver);
		

		try {

			String gridHeaderText = wait
					.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath(config.getGMgridHeaderTextFromActivityXpath())))
					.getText();
			Log.info("Grid Header is " + gridHeaderText);
			Assert.assertEquals(gridHeaderText.trim(), "History", "Grid header not matching");
			List<WebElement> actualColumnHeaders = driver.findElements(By.xpath(
					config.getGMactualColumnHeadersFromActivityXpath()));
			try{
			Assert.assertEquals(actualColumnHeaders.get(0).getText().trim(), "Answer");
			Assert.assertEquals(actualColumnHeaders.get(1).getText().trim(), "Date Entered");
			Assert.assertEquals(actualColumnHeaders.get(2).getText().trim(), "Entered By");
			Assert.assertEquals(actualColumnHeaders.get(3).getText().trim(), "Date of Service");
			Assert.assertEquals(actualColumnHeaders.get(4).getText().trim(), "Program");
			Assert.assertEquals(actualColumnHeaders.get(5).getText().trim(), "Source");
			}
			catch(AssertionError e){
				Log.info("Assertion--"+e);	
			}
			Log.info("Grid Header has been verified");
			Log.info("Getting the list of Column names for History Grid");
			Thread.sleep(GlobalValues.Sleep_wait_time);
			Log.info("**********History Grid Column Names has been verified for DOS**********");


		} catch (Exception e) {
			e.printStackTrace();
			Log.info(e);
		}
	}

	
	public void viewMetadataForLSPFromActivity() throws Exception {
		Log = Logger.getLogger("LifeStylePreventionSubSection.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		new LeftNavigationLink(driver);

		try {
			
			String programName = "";
			
			if(GlobalValues.DBProgramNameActiveStatus.contains("true") && GlobalValues.DBProgramNamePrimaryStatus.contains("true")){
				programName = GlobalValues.DBProgramName;
			}
			driver
					.findElement(By.xpath(config.getProgramNameFromBannerXpath()))
					.getText().toString().trim();

			File sour = new File("./TestData/AnnualAssessments_CMDM.xlsx");
			FileInputStream fiss = new FileInputStream(sour);
			XSSFWorkbook wb = new XSSFWorkbook(fiss);
			XSSFSheet Sheet1 = wb.getSheet("LSP_Add");
			int row = Sheet1.getLastRowNum();
			for (int i = 1; i <= row; i++) {
				String metadataFromUI = "";
				String metadataToVerify = "";
				
				Cell cellAnswer = Sheet1.getRow(i).getCell(2);
				if (cellAnswer == null)
					continue;
				else if (Sheet1.getRow(i).getCell(0).getStringCellValue().contains("Show History"))
					continue;

				cellAnswer.setCellType(Cell.CELL_TYPE_STRING);

				if (Sheet1.getRow(i).getCell(0).getStringCellValue()
						.contains("If applicable, please select source of current changes")) {
					break;
				}

				if (cellAnswer != null && StringUtils.isNotBlank(cellAnswer.getStringCellValue())) {
					String QuestionText = Sheet1.getRow(i).getCell(0).getStringCellValue().toString();
					String Question = null;
					String instance = null;

					if (QuestionText.contains("|")) {
						Question = QuestionText.split("\\|")[0];
						instance = QuestionText.split("\\|")[1];
					} else {
						Question = QuestionText;
						instance = "1";
					}

					if (Sheet1.getRow(i).getCell(1).getStringCellValue().toString().contains("radio")) {
						metadataFromUI = wait
								.until(ExpectedConditions.presenceOfElementLocated(
										By.xpath("(//legend[contains(text(),\"" + Question + "\")])[" + instance
												+ "]//ancestor::div[@class='ng-valid ng-star-inserted ng-touched ng-dirty form-group']//following-sibling::nm-message//span[contains(text(),'Last Modified:')]")))
								.getText();
					
					}
					else {

						metadataFromUI = wait
								.until(ExpectedConditions.presenceOfElementLocated(
										By.xpath("(//label[contains(text(),\"" + Question + "\")])[" + instance
												+ "]//ancestor::div[@class='ng-valid ng-star-inserted ng-touched ng-dirty form-group threeColumn']//following-sibling::nm-message//span[contains(text(),'Last Modified:')]")))
								.getText();

						// metadataFromUI = wait
						// .until(ExpectedConditions.presenceOfElementLocated(
						// By.xpath("(//*[contains(text(),\"" + Question +
						// "\")])[" + instance
						// +
						// "]//ancestor::nm-element//following-sibling::nm-message//span[@class
						// = 'ui-messages-detail ng-star-inserted' and
						// contains(text(),'Last Modified:')]")))
						// .getText();
					}
					
					

					metadataToVerify += ("Last Modified: " + GlobalValues.LifestylePreventionSubmit_Date + " " + GlobalValues.UserNameOnUI + " "
							+ sourceValueSelected + " " + programName).trim();

					Assert.assertEquals(metadataFromUI, metadataToVerify, "Metadata Values are not matching");

					Log.info("Verified the metadata for - " + QuestionText);
				}

			}
			Log.info("**********Verified the metadata**********");

		} catch (Exception e) {
			e.printStackTrace();
			Log.info(e);
		}
	}
	
}
