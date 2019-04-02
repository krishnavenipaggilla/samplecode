package com.nimbus.pages;

import org.testng.Assert;
import org.testng.AssertJUnit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;

import utility.ConfigReader;
import utility.UtilityClass;

public class Global_Method {

	WebDriver driver;
	Logger Log;

	ConfigReader config = new ConfigReader();

	public Global_Method(WebDriver ldriver) {
		this.driver = ldriver;
	}

	public Global_Method(Logger lLog) {
		this.Log = lLog;
	}

	public void verifyGrid(ArrayList<String> list, boolean searchByContains, String gridName) {
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		Log = Logger.getLogger("Global_Method.class");
		PropertyConfigurator.configure("log4j.properties");

		for (int i = 0; i < list.size(); i++) {

			String[] str = list.get(i).split("\\|");

			String xpath = "";
			int flag = 0;
			for (String ret : str) {
				try {
					if (flag == 0) {
						if (searchByContains) {

							//xpath = xpath + "//th[" + (i + 1) + "]/span[contains(text(),'" + ret + "')]";
							xpath = xpath + "//th/span[contains(text(),'"+ret+"')]";
							
							flag++;

							WebElement TablePath = wait
									.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
							String TablePathText = TablePath.getText();
							AssertJUnit.assertEquals(TablePathText, ret);

						} else {
							xpath = xpath + "//span[text()='" + ret + "']";
							flag++;

							WebElement TablePath = wait
									.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
							String TablePathText = TablePath.getText();
							AssertJUnit.assertEquals(TablePathText, ret);
						}
					} else {
						// xpath = xpath +
						// "//parent::span//ancestor::thead//following-sibling::tbody//child::td//span[text()='"+
						// ret + "']";
						xpath = xpath
								+ "//parent::span//ancestor::thead//following-sibling::tbody//child::td//span[contains(text(),'"
								+ ret + "')]";
						WebElement TablePath = wait
								.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
						String TablePathText = TablePath.getText();
						AssertJUnit.assertEquals(TablePathText, ret);
					}
				} catch (Exception ex)

				{
					Log.info(ex.getMessage().toString());
				}
			}

		}

		Log.info(gridName + "-" + "Grid is Verified Successfully");
		list.clear();

	}

	public void Reports(String str, String Results) throws IOException {
		try {
			File sour = new File("./TestData/ReportsUpdated.xlsx");
			FileInputStream fiss = new FileInputStream(sour);
			XSSFWorkbook wb = new XSSFWorkbook(fiss);
			XSSFSheet Sheet1 = wb.getSheet("Reports");
			String Description = "";

			Log = Logger.getLogger("Global_Method.class");
			PropertyConfigurator.configure("log4j.properties");

			int row = Sheet1.getLastRowNum();

			for (int i = 1; i <= row; i++) {
				if (str.equals(Sheet1.getRow(i).getCell(0).getStringCellValue().toString())) {
					Description = Sheet1.getRow(i).getCell(1).getStringCellValue().toString();
					break;
				}
			}

			if (Results == "Pass" && Description.length() > 0) {
				UtilityClass.logger.log(LogStatus.PASS, str, Description);
			} else if (Results == "Fail" && Description.length() > 0) {
				UtilityClass.logger.log(LogStatus.FAIL, str, Description);
			} else {
				Log.info("User Story Desciption is Not Available in Database" + str);
				
			}
		}	 catch (Exception ex) {
			Log.info("Reports" + ex.getMessage().toString());
		}

	}

	public void TaskStatus_BeforeSubmit(String taskName) {
		Log = Logger.getLogger("Global_Method.class");
		PropertyConfigurator.configure("log4j.properties");

		try {
			Log.info("taskname--" + taskName);
			String xpath = "";
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

			WebElement task = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='" + taskName + "']")));
			String taskText = task.getText().toString();
			Log.info("task name:" + taskText);
			xpath = "//span[text()='" + taskName
					+ "']//ancestor::td//following-sibling::td//span[text()='Status']//following-sibling::span";
			WebElement status = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='"
					+ taskName
					+ "']//ancestor::td//following-sibling::td//span[text()='Status']//following-sibling::span")));
			String statusText = status.getText().toString();
			Log.info(taskText + "::" + statusText);
			GlobalValues.TaskStatus.add(taskText + "|" + statusText);
		} catch (Exception ex) {
			Log.info(ex.getMessage().toString());
		}

	}

	public void TaskStatus_AfterSubmit(String taskName) {
		Log = Logger.getLogger("Global_Method.class");
		PropertyConfigurator.configure("log4j.properties");

		try {
			Thread.sleep(GlobalValues.Sleep_wait_time);

			String xpath = "";
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			WebElement task = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='" + taskName + "']")));
			String taskText = task.getText().toString();
			xpath = "//span[text()='" + taskName
					+ "']//ancestor::td//following-sibling::td//span[text()='Status']//following-sibling::span";
			WebElement status = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='"
					+ taskName
					+ "']//ancestor::td//following-sibling::td//span[text()='Status']//following-sibling::span")));
			String statusText = status.getText().toString();
			Log.info(taskText + "::" + statusText);
			GlobalValues.TaskStatus.add(taskText + "|" + statusText);
		} catch (Exception ex) {
			Log.info(ex.getMessage().toString());
		}

	}
	// Case Status History

	public void fetchMemberCaseStatusDetails() {

		Log = Logger.getLogger("Global_Method.class");
		PropertyConfigurator.configure("log4j.properties");
		String expectedUserName = "";
		try {

			Log.info("verifying case status history");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			String expectedCaseStatus = wait
					.until(ExpectedConditions
							.presenceOfElementLocated((By.xpath(config.getMemberBannerCaseStatusXpath()))))
					.getText().trim();
			Log.info("expectedCaseStatus---" + expectedCaseStatus);

			String expectedCaseStatusReason = wait
					.until(ExpectedConditions
							.presenceOfElementLocated((By.xpath(config.getMemberBannerCaseStatusReasonXpath()))))
					.getText().trim();

			Log.info("expectedCaseStatusReason---" + expectedCaseStatusReason);

			if (expectedCaseStatus.equals("New") && expectedCaseStatusReason.equals("Unassigned")) {
				expectedUserName = "System";
				GlobalValues.CaseStatus
						.add(expectedCaseStatus + "|" + expectedCaseStatusReason + "|" + expectedUserName);
			} else {
				expectedUserName = wait
						.until(ExpectedConditions.presenceOfElementLocated((By.xpath(config.getLoggedInUserXpath()))))
						.getText().trim();
				Log.info("expectedUserName---" + expectedUserName);
				GlobalValues.CaseStatus
						.add(expectedCaseStatus + "|" + expectedCaseStatusReason + "|" + expectedUserName);
			}
		} catch (Exception ex) {
			Log.info(ex.getMessage().toString());
		}

	}

	// Get text given xpath

	public String getText(String xpath) {
		Log = Logger.getLogger("Global_Method.class");
		PropertyConfigurator.configure("log4j.properties");

		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		Log = Logger.getLogger("Global_Method.class");

		WebElement xpathText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		String text = xpathText.getText();
		return xpath;

	}

	public void clickSubmit(int instance) {
		Log = Logger.getLogger("Global_Method.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		
		WebElement submit = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[text() ='Submit'])["+instance+"]")));
				try {
			if (submit.isEnabled()) {
				submit.click();
				Log.info("Clicked on Submit button");
			}
		} catch (Exception e) {
			Log.info(e.getStackTrace() + "Submit is not enabled or clicked");

		}
	}

	public void clickCancel(int instance) {
		Log = Logger.getLogger("Global_Method.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		
		WebElement cancel = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text() ='Cancel'])["+instance+"]")));
		cancel.click();
		Log.info("Clicked on Cancel button");
	}

	public void clickBack(int instance) {
		Log = Logger.getLogger("Global_Method.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		
		WebElement back = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[text() ='Back'])["+instance +"]")));
		back.click();
		Log.info("Clicked on Back button");
	}
	
	public void clickClose(int instance) {
		Log = Logger.getLogger("Global_Method.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		
		WebElement close = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[text() ='Close'])["+instance +"]")));
		close.click();
		Log.info("Clicked on Close button");
	}

	public String readFromExcel(String FileName, String sheetName, int col, String text) throws Exception {

		Log = Logger.getLogger("Global_Method.class");
		PropertyConfigurator.configure("log4j.properties");

		File sour = new File("./TestData/" + FileName + ".xlsx");
		FileInputStream fiss = new FileInputStream(sour);
		XSSFWorkbook wb = new XSSFWorkbook(fiss);
		XSSFSheet Sheet1 = wb.getSheet(sheetName);

		WebDriverWait wait = new WebDriverWait(driver, 15);
		int row = Sheet1.getLastRowNum();
		int j = col;

		try {

			for (int i = 1; i <= Sheet1.getLastRowNum(); i++) {
				String QuestionText = Sheet1.getRow(i).getCell(0).getStringCellValue().toString();
				String Question = null;
				String instance = null;
				String Question1 = null;
				String AssessmentUItext = null;

				if (QuestionText.contains("|")) {
					Question = QuestionText.split("\\|")[0];
					instance = QuestionText.split("\\|")[1];
				} else {
					Question = QuestionText;
					instance = "1";
				}
				Cell cellData = Sheet1.getRow(i).getCell(j);
				if (cellData == null)
					continue;
				cellData.setCellType(Cell.CELL_TYPE_STRING);
				String Answer = cellData.toString();

				if (StringUtils.isEmpty(Answer))
					continue;
				if (text.equalsIgnoreCase(Sheet1.getRow(i).getCell(0).getStringCellValue().toString())) {
					return Sheet1.getRow(i).getCell(j).getStringCellValue().toString();
				}
			}
		} catch (Exception Ex) {
			Log.info(text + " is not found in the " + FileName);
			Log.info(Ex.getMessage().toString());

			if (fiss != null)
				fiss.close();
		} finally {
			fiss.close();
		}
		return null;
	}

	public void getEachCellDataFromAnyGrid() throws Exception {
		Log = Logger.getLogger("Global_Method.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		

		try {

			WebElement gridHeader = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p-header")));
			gridHeader.getText();
			Log.info("Grid Name is - "+ gridHeader);
			
			WebElement table = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table")));


			// Now get all the TR elements from the table
			List<WebElement> allRowsOfTable = table.findElements(By.xpath("//table//tbody/tr"));

			// And iterate over them, getting the cells
			for (WebElement row : allRowsOfTable) {
				List<WebElement> allCellsOfTable = row.findElements(By.xpath("//table//tbody/tr/td"));
				//// table/tbody/tr/td/span [contains(text(),'test2')]
				for (WebElement cell : allCellsOfTable) {
					Log.info((cell.getText()));
				}
			}
		} catch (Exception e) {
			Log.info("Could not display the info " + e);
		}
	}

	public int getNoOfRowsInGrid(String gridHeader) throws Exception {
		Log = Logger.getLogger("Global_Method.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		
		int rowsInGrid = 0;
		try {

						
			WebElement recordsDisplayedElement = driver.findElement(By.xpath("//nm-table//div//p-header//caption[contains(text(),'"+gridHeader+"')]//parent::p-header//following-sibling::div[@class = 'recordsDisplayed']"));
			String recordsDisplayedText = recordsDisplayedElement.getText();

			String pattern="of (\\d+)";
			
			Pattern p=Pattern.compile(pattern);
			Matcher m=p.matcher(recordsDisplayedText);
			if (m.find()) {
			   rowsInGrid = Integer.parseInt(m.group(1));
			   Log.info("Rows in grid = "+rowsInGrid);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			Log.info("Could not find the no of rows " + e);
		}
		return rowsInGrid;
	}
	
	
	
	// This method for drop down options verification
    public void validateDropDownOptions(List<String> actualoptions, List<String> expctedoptions) {
    	Log = Logger.getLogger("Global_Method.class");
		PropertyConfigurator.configure("log4j.properties");
           Log = Logger.getLogger("Global_Method.class");
           Log.info("Expected Options---" + expctedoptions);
           Log.info("Actual Options---" + actualoptions);
           Set<String> intersect = new HashSet<String>(actualoptions);
           Log.info("Values before intersect---" + intersect);
           intersect.removeAll(expctedoptions);
           Log.info("Values after intersect---" + intersect);
           int matchValue = intersect.size();
           Log.info("size after comparsion---" + matchValue);

           if (matchValue == 0) {
                  Log.info("Drop down options are verified");

           } else {
                  Log.info("Drop down options are not verfied");

           }

    }
    
    public void verifySmokeTest() throws InterruptedException{
    	Log = Logger.getLogger("Global_Method.class");
		PropertyConfigurator.configure("log4j.properties");
    	WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		LeftNavigationLink Request1 = PageFactory.initElements(driver, LeftNavigationLink.class);
		
    	
			Request1.clickMemberActionCenter();
			Request1.clickActivities();
			Thread.sleep(GlobalValues.Sleep_wait_time);

			WebElement activityGridHeaderElement = wait.until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class ='scrollGrid']//p-header")));
			String activityGridHeader = activityGridHeaderElement.getText();
			Assert.assertEquals(activityGridHeader, "Activity", "You are in Wrong grid");
			Log.info("Grid Name is " + activityGridHeader);
			Thread.sleep(GlobalValues.Sleep_wait_time);
			List<WebElement> initialOutreachElements = driver
					.findElements(By.xpath(config.getInitialOutreachElementXpath()));
			Assert.assertTrue((initialOutreachElements.size() > 0),
					"Initial Outreach Web Element is not found in the Activity grid");
			WebElement initialOutreachElementPresence = driver.findElement(By.xpath(
					"//table/thead/tr/th/span[contains(text(),'Activity Type')]/ancestor::thead/following-sibling::tbody/tr/td//span[@title = 'Initial Outreach']"));
			if (initialOutreachElementPresence.isDisplayed()) {
				Log.info("**********Verified the presence of Initial Outreach Task in Member Action Centre**********");
			
		}
    }
    
    public int getRowNumFromExcel(String FileName, String sheetName, int col, String text) throws Exception {

		Log = Logger.getLogger("Global_Method.class");
		PropertyConfigurator.configure("log4j.properties");

		File sour = new File("./TestData/" + FileName + ".xlsx");
		FileInputStream fiss = new FileInputStream(sour);
		XSSFWorkbook wb = new XSSFWorkbook(fiss);
		XSSFSheet Sheet1 = wb.getSheet(sheetName);

		WebDriverWait wait = new WebDriverWait(driver, 15);
		int row = Sheet1.getLastRowNum();
		int j = col;

		try {

			for (int i = 1; i <= Sheet1.getLastRowNum(); i++) {
				String QuestionText = Sheet1.getRow(i).getCell(0).getStringCellValue().toString();
				String Question = null;
				String instance = null;
				String Question1 = null;
				String AssessmentUItext = null;

				if (QuestionText.contains("|")) {
					Question = QuestionText.split("\\|")[0];
					instance = QuestionText.split("\\|")[1];
				} else {
					Question = QuestionText;
					instance = "1";
				}
				Cell cellData = Sheet1.getRow(i).getCell(j);
				if (cellData == null)
					continue;
				cellData.setCellType(Cell.CELL_TYPE_STRING);
				String Answer = cellData.toString();

				if (StringUtils.isEmpty(Answer))
					continue;
				if (text.equalsIgnoreCase(Sheet1.getRow(i).getCell(0).getStringCellValue().toString())) {
					return i;
				}
			}
		} catch (Exception Ex) {
			Log.info(text + " is not found in the " + FileName);
			Log.info(Ex.getMessage().toString());

			if (fiss != null)
				fiss.close();
		} finally {
			fiss.close();
		}
		return -1;
	}

    
    public String getCellValueFromExcel(String FileName, String sheetName, int row, int col) throws Exception {

		Log = Logger.getLogger("Global_Method.class");
		PropertyConfigurator.configure("log4j.properties");

		File sour = new File("./TestData/" + FileName + ".xlsx");
		FileInputStream fiss = new FileInputStream(sour);
		XSSFWorkbook wb = new XSSFWorkbook(fiss);
		XSSFSheet Sheet1 = wb.getSheet(sheetName);

		WebDriverWait wait = new WebDriverWait(driver, 15);
		
		try {

				Cell cellData = Sheet1.getRow(row).getCell(col);
				if (cellData == null)
					return null;
				cellData.setCellType(Cell.CELL_TYPE_STRING);
				String cellValue = cellData.toString();
					return cellValue;
				
			
		} catch (Exception Ex) {
			
			Log.info(Ex.getMessage().toString());

			if (fiss != null)
				fiss.close();
		} finally {
			fiss.close();
		}
		return null;
	}

    // This method for subsections verification
    public void validateSubsections(List<String> actualsubsections, List<String> expctedsubsections) {
       Log = Logger.getLogger("Global_Method.class");
              PropertyConfigurator.configure("log4j.properties");
           Log = Logger.getLogger("Global_Method.class");
           Log.info("Expected sub sections---" + expctedsubsections);
           Log.info("Actual sub sections---" + actualsubsections);
           Set<String> intersect = new HashSet<String>(actualsubsections);
           Log.info("Values before intersect---" + intersect);
           intersect.removeAll(expctedsubsections);
           Log.info("Values after intersect---" + intersect);
           int matchValue = intersect.size();
           Log.info("size after comparsion---" + matchValue);

           if (matchValue == 0) {
                  Log.info("All Sub Sections are verified");

           } else {
                  Log.info("All Sub Sections are not verfied");

           }

    }

    
}