package com.nimbus.pages;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections.map.MultiValueMap;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.bson.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import excelReader.DataReaderExcel;
import utility.ConfigReader;

public class DashboardFunctions {

	WebDriver driver;
	Logger Log;
	WebElement element;
	WebElement element1;
	WebElement element2;
	String TestDataFilePath = "./TestData/TestDataFiles.xlsx";
	List<String> gridColumnNameList = new ArrayList<String>();
	List<String> gridFilterColumnNameList = new ArrayList<String>();
	ConfigReader config = new ConfigReader();
	List<WebElement> dataTable_StringFilter_ColumnCount;

	public DashboardFunctions(WebDriver ldriver) {
		this.driver = ldriver;

	}

	public DashboardFunctions(Logger lLog) {
		this.Log = lLog;
	}

	// Corresponding User story numbers are mentioned on the top of each
	// dashboard functionality methods in this class

	/*
	 * .............................User
	 * Story-7355,7586,9091,9276................
	 */



	// This Method is returning the list of columns in Member Grid

	public List<String> getGridColumnNames() throws Exception {

		Log = Logger.getLogger("DashboardFunctions.class");
		PropertyConfigurator.configure("log4j.properties");

		List<WebElement> GridColumnCount = driver.findElements(By.xpath(config.getColumnssOnGrid()));

		StringBuilder Name = new StringBuilder();
		Name = Name.append(config.getColumnAppend());
		int ColumnCount = GridColumnCount.size();

		List<String> ColumnNames = new ArrayList<String>();

		if (ColumnCount > 0) {
			for (int ColumnNumber = 1; ColumnNumber <= ColumnCount; ColumnNumber++) {
				ColumnNames.add(driver
						.findElement(By.xpath(Name.toString().replace("columnNumber", String.valueOf(ColumnNumber))))
						.getAttribute("textContent").toString().trim());

			//	Log.info("Column Name loaded is " + ColumnNames);
			}
			Log.info("Column Names loaded are " + ColumnNames);
			Log.info("All Column Names are loaded Completely and Present on Grid ");

		} else {
			Log.info("Member Grid do not have any columns to prepare the list for validation");
			ColumnNames = null;
		}
		return ColumnNames;
	}

	// This method is checking if actual columns which is returned from above
	// method and expected columns which we are setting in global
	// file are actually matching or not and returning a boolean value.
	public Boolean validateGridColumnNames(List<String> ExpectedColumnNames, List<String> ActualColumnNames)
			throws Exception {
		Boolean result = false;
		if (ExpectedColumnNames != null && ActualColumnNames != null) {
			try {

				ExpectedColumnNames.removeAll(ActualColumnNames);
				if (ExpectedColumnNames.isEmpty()) {
					result = true;
				} else {
					Log.info("column not matched" + ExpectedColumnNames);
				}
			} catch (Exception ex) {
				Log.info("exception : " + ex);

			}
		}
		return result;
	}

	// column level filter

	public Integer getGridFilterColumnNames() throws Exception {

		Log = Logger.getLogger("DashboardFunctions.class");
		PropertyConfigurator.configure("log4j.properties");
		int columnCount = 0;

		List<WebElement> GridColumnCount = driver
				.findElements(By.xpath("//div[@class='ng-star-inserted']//input[@type='text']"));
		columnCount = GridColumnCount.size();
		return columnCount;
	}

	// This method can be used to perform column level text based Filteration
	// and source of test data is Mongo DB

	public Boolean columnLevelFilteration(MultiMap multiMapFilterValues) throws Throwable {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,300)", "");

		Log = Logger.getLogger("DashboardFunctions.class");
		PropertyConfigurator.configure("log4j.properties");
		String filterString = "";
		String columnDataIndex = "";
		String itemToLookFor;
		Boolean doesExists = false;
		Boolean resultFlag = true;
		int filterColumnCount = 0;
		try {
			Boolean filterFlag;
			try{
				WebElement noRecordFoundElement = driver.findElement(By.xpath("//tbody//tr/td[contains(text(),'No records found')]"));
				if(noRecordFoundElement.isDisplayed()){
					String noRecordFoundText = noRecordFoundElement.getText();
					Log.info(noRecordFoundText + "- to perform Filtering on the grid");
					return true;
				} 
			}catch(Exception e){
				Log.info("Records are present for Filtering");
			}
			
			driver.findElement(By.xpath(config.imageOnColumnToClick())).click();
			filterColumnCount = getGridFilterColumnNames();
			Set<String> keys = multiMapFilterValues.keySet();
			for (int i = 1; i <= filterColumnCount; i++) {
				itemToLookFor = "column_" + i;
				filterString = "";
				columnDataIndex = "";
				for (String k : keys) {
					List<String> itemsWithKey = (List<String>) multiMapFilterValues.get(k);
					doesExists = itemsWithKey.contains(itemToLookFor);
					if (doesExists == true) {
						filterString = itemsWithKey.get(0);
						columnDataIndex = itemsWithKey.get(2);
						Log.info("The Test data to be filtered:" + filterString);
						break;
					}
				}
				if (filterString != null) {

					filterFlag = textBasedFilter(i, filterString, columnDataIndex);
					if (resultFlag != false)
						resultFlag = filterFlag;
				}
				
			}
		} catch (Exception e) {
			{
				Log.info("Exception  is" + e.getMessage());
				resultFlag = false;

			}
		}
		return resultFlag;
	}

	// public void columnLevelFilteration(String sheetName, int cellIndex)
	// throws Throwable {
	// JavascriptExecutor jse = (JavascriptExecutor) driver;
	// jse.executeScript("window.scrollBy(0,300)", "");
	//
	// Log = Logger.getLogger("DashboardFunctions.class");
	// PropertyConfigurator.configure("log4j.properties");
	//
	// File sour = new File(TestDataFilePath);
	//
	// FileInputStream fiss = new FileInputStream(sour);
	// XSSFWorkbook wb = new XSSFWorkbook(fiss);
	// XSSFSheet CoulmnSheet = wb.getSheet(sheetName);
	// int row = CoulmnSheet.getLastRowNum();
	//
	// String rowValue;
	//
	// XSSFRow xrow;
	//
	// gridColumnNameList = getGridColumnNames(); // Set the list of all the
	// // grid columns
	// try {
	// driver.findElement(By.xpath(config.imageOnColumnToClick())).click();
	// gridFilterColumnNameList = getGridFilterColumnNames();
	// for (int i = 1; i <= row; i++) {
	// xrow = CoulmnSheet.getRow(i);
	// if (xrow != null) {
	//
	// if (xrow.getCell(cellIndex) != null) {
	//
	// rowValue = xrow.getCell(cellIndex).getStringCellValue().toString();
	// Log.info("The Test data to be filtered:" + rowValue);
	// textBasedFilter(i - 1, rowValue);
	// }
	// }
	// }
	// } catch (Exception e) {
	// {
	// Log.info("Exception reading excel is" + e.getMessage());
	// }
	// }
	// }

	public Boolean VerifyColumnFilteration(String dataColumnIndex, String filterString) {
		Boolean verifyFlag = true;
		try {
			WebElement element;
			Log = Logger.getLogger("DashboardFunctions.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			Thread.sleep(3000);

			try {

				// element =
				// driver.findElement(By.xpath("(//span[normalize-space()='Subscriber
				// ID']//parent::th//following::tr//td[" + dataColumnIndex +
				// "])[1]"));
				element = driver.findElement(By.xpath("(//parent::th//following::tr//td[" + dataColumnIndex + "])[1]"));
			} catch (Exception ex) {
				element = driver.findElement(By.xpath(config.VerifyNoRecordsFound()));

			}
			String gridValue = element.getText();

			try {
				Assert.assertTrue(gridValue.toLowerCase().contains(filterString.toLowerCase()));
				Log.info("*******Record matched for search text : " + filterString);
			} catch (Error err1) {
				try {
					Assert.assertTrue(gridValue.toLowerCase().contains("no records found"));
					Log.info("!!!!!!!No Records found for verification");
				} catch (Error err2) {
					verifyFlag = false;
					Log.info("!!!!!!!!!Record does not match");
				}
			}

		} catch (Exception ex) {
			Log.info("Error in VerifyColumnFilteration() method :" + ex);
			verifyFlag = false;
		}
		return verifyFlag;

	}

	public Boolean textBasedFilter(int columNumber, String filterString, String columnIndex) throws Exception {
		Boolean filterFlag;
		dataTable_StringFilter_ColumnCount = driver
				.findElements(By.xpath("//div[@class='ng-star-inserted']//input[@type='text']"));
		Thread.sleep(GlobalValues.Sleep_wait_time);
		WebElement ColumnOnGrid = dataTable_StringFilter_ColumnCount.get(columNumber - 1);
		ColumnOnGrid.sendKeys(filterString);
		Thread.sleep(GlobalValues.Sleep_wait_time);
		filterFlag = VerifyColumnFilteration(columnIndex, filterString);
		ColumnOnGrid.sendKeys(Keys.CONTROL + "a");
		ColumnOnGrid.sendKeys(Keys.DELETE);
		return filterFlag;
	}

	/* .............User story-7586,9091............ */

	// This Method is doing Ascending and Descending Sorting on Grid Columns

public Boolean columnSorting(WebDriver ldriver) throws Exception {
		
		Boolean resultFlag=true;
		WebElement sortColumns = null;
		List<WebElement> checkBoxOnGrid =null;
		List<WebElement> arrowsOnGrid =null;
		
		int columnIndexVariable=0;
		try {

			Log = Logger.getLogger("DashboardFunctions.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

			Boolean sortingFlag = false;
			String columnName = "";
			
			try{
				WebElement noRecordFoundElement = driver.findElement(By.xpath("//tbody//tr/td[contains(text(),'No records found')]"));
				if(noRecordFoundElement.isDisplayed()){
					String noRecordFoundText = noRecordFoundElement.getText();
					Log.info(noRecordFoundText + "- to perform sorting in the grid");
					return true;
				} 
			}catch(Exception e){
				Log.info("Records are present for sorting");
			}
			
			// Calling getGridColumnNames Method from class DashboardLandingPage
			List<String> ColumnName = getGridColumnNames();
			
			try
			{
			checkBoxOnGrid = wait
						.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By.xpath(config.VerifyCheckBoxOnGrid()))));
			 columnIndexVariable=2;			 
			}
			catch(Exception e)
			{				
				columnIndexVariable=1;
			}
			
			try
			{
				if(columnIndexVariable!=2)
				{
				 arrowsOnGrid = wait
							.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By.xpath(config.VerifyArrowOnGrid()))));
				 columnIndexVariable=2;		
				}
			}
			catch(Exception e)
			{				
				columnIndexVariable=1;
			}
			
			
			for (int ColumnNumber = 0; ColumnNumber < ColumnName.size(); ColumnNumber++) {
				columnName = ColumnName.get(ColumnNumber);

				String sortXpath = "//th[ColumnNumber]//p-sorticon//i[@class]";

  				
				Integer columnIndex = new Integer(ColumnNumber);
				try{
//				if(columnIndex.equals(0))
//				{
					 sortColumns = wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath(sortXpath.toString().replace("ColumnNumber", String.valueOf(ColumnNumber+1))))));
//				}
//				else
//				{
//					 sortColumns = wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath(sortXpath.toString().replace("ColumnNumber", String.valueOf(ColumnNumber))))));
//				}
				}catch(Exception e){
					//e.printStackTrace();
					Log.info("Column Name :**********"+ columnName + "********** - Doesn`t have sorting");
					continue;
				}
				Thread.sleep(3000);
				sortColumns.click();
				
				Thread.sleep(3000);
				sortingFlag = verifySorting(columnName, ColumnNumber,"ASC",columnIndexVariable);
				if (sortingFlag == true)
					Log.info("********* Ascending order sorting executed sucessfully for column : " + columnName);
				else
					Log.info("!!!!!  Error in Ascending order sorting  for column : " + columnName);
				Thread.sleep(3000);
				if(resultFlag!=false)
					resultFlag = sortingFlag;
				sortColumns.click();
				Thread.sleep(3000);
				sortingFlag = verifySorting(columnName,ColumnNumber, "DESC",columnIndexVariable);
				if (sortingFlag == true)
					Log.info("********* Descending order sorting executed sucessfully for column : " + columnName);
				else
					Log.info("!!!!! Error in Descending order sorting for column : " + columnName);
				if(resultFlag!=false)
					resultFlag = sortingFlag;
				}

		} catch (Exception ex) {
			System.out.println("errors : " + ex);
			resultFlag =false;
			ex.printStackTrace();

		}
		return resultFlag;
	}
	
//Please use the above function- "columnSorting"-  for sorting not the below one
	public Boolean columnSortingOnGrid(WebDriver ldriver,int columnSortToStartIndex) throws Exception {
		int columnIndexVariable = 1;
		int columnHeaderIndex = 1;
		return columnSortingOnGrid(ldriver, columnSortToStartIndex, columnIndexVariable, columnHeaderIndex);
	}

	public Boolean columnSortingOnGrid(WebDriver ldriver,int columnSortToStartIndex, int columnIndexVariable, int columnHeaderIndex) throws Exception {
		Boolean resultFlag = true;
		WebElement sortColumns;		
		try {

			Log = Logger.getLogger("DashboardFunctions.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

			Boolean sortingFlag = false;
			String columnName = "";

			// Calling getGridColumnNames Method from class DashboardLandingPage
			List<String> ColumnName = getGridColumnNames();
			
			try{
				WebElement noRecordFoundElement = driver.findElement(By.xpath("//tbody//tr/td[contains(text(),'No records found')]"));
				if(noRecordFoundElement.isDisplayed()){
					String noRecordFoundText = noRecordFoundElement.getText();
					Log.info(noRecordFoundText + "- to perform sorting in the grid");
					return true;
				} 
			}catch(Exception e){
				Log.info("Records are present for sorting");
			}
			//Column index to start sort for Management reason is - 0 
			for (int ColumnNumber = columnSortToStartIndex; ColumnNumber < ColumnName.size(); ColumnNumber++) {
				// for (int ColumnNumber = 2; ColumnNumber < ColumnName.size();
				// ColumnNumber++) {
				columnName = ColumnName.get(ColumnNumber);
				
				String sortXpath = "//tr[@class=\"ng-star-inserted\"]//th[ColumnNumber]/p-sorticon//i";
				Integer columnIndex = new Integer(ColumnNumber);
				
				try{
					sortColumns = wait.until(ExpectedConditions.presenceOfElementLocated(
								(By.xpath(sortXpath.toString().replace("ColumnNumber", String.valueOf(ColumnNumber + columnHeaderIndex))))));
				} catch(Exception e){
					Log.info("Column Name :!!!!!!!!!!"+ columnName + "!!!!!!!!!! - Doesn`t have sorting");
					continue;
				}
		/*		if (columnIndex.equals(0)) {
					sortColumns = wait.until(ExpectedConditions.presenceOfElementLocated((By
							.xpath(sortXpath.toString().replace("ColumnNumber", String.valueOf(ColumnNumber + 1))))));

				} else {
					sortColumns = wait.until(ExpectedConditions.presenceOfElementLocated(
							(By.xpath(sortXpath.toString().replace("ColumnNumber", String.valueOf(ColumnNumber))))));
				}
		*/		
				Thread.sleep(3000);
				sortColumns.click();

				Thread.sleep(3000);
				sortingFlag = verifySorting(columnName, ColumnNumber, "ASC", columnIndexVariable);
				if (sortingFlag == true)
					Log.info("********* Ascending order sorting executed sucessfully for column : " + columnName);
				else
					Log.info("!!!!!  Error in Ascending order sorting  for column : " + columnName);
				Thread.sleep(3000);
				if (resultFlag != false)
					resultFlag = sortingFlag;
				sortColumns.click();
				Thread.sleep(3000);
				sortingFlag = verifySorting(columnName, ColumnNumber, "DESC", columnIndexVariable);
				if (sortingFlag == true)
					Log.info("********* Descending order sorting executed sucessfully for column : " + columnName);
				else
					Log.info("!!!!! Error in Descending order sorting for column : " + columnName);
				if (resultFlag != false)
					resultFlag = sortingFlag;
			}
			
		} catch (Exception ex) {
			System.out.println("errors : " + ex);
			ex.printStackTrace();
			resultFlag = false;

		}
		return resultFlag;
	}

	public Boolean verifySorting(String columnName, int ColumnNumber, String sortingAction, int columnIndexVariable) {
		Log = Logger.getLogger("DashboardFunctions.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		List<WebElement> sortColumnData = null;
		List<WebElement> sortColumnNoData = null;

		ArrayList<String> obtainedList = new ArrayList<String>();
		ArrayList<Integer> obtainedListIngeter = new ArrayList<Integer>();
		ArrayList<String> sortedList = new ArrayList<String>();
		ArrayList<Integer> sortedListInteger = new ArrayList<Integer>();
		Boolean isSortedFlag = false;
		String sortDataXpath = "//span[normalize-space()='" + columnName
				+ "']//parent::th//following::tr//td[columnNumber]";
		sortDataXpath = sortDataXpath.toString().replace("columnNumber",
				String.valueOf(ColumnNumber + columnIndexVariable));
		try {
			sortColumnData = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By.xpath(sortDataXpath))));
		} catch (Exception ex) {
//			sortColumnNoData = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By.xpath(
//					"//span[normalize-space()='" + columnName + "']//parent::th//following::tr//td[columnNumber]"))));
			sortColumnNoData = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By.xpath(sortDataXpath))));
			if (sortColumnNoData.size() > 0) {
				for (WebElement we : sortColumnNoData) {
					if (we.getText().toString().length() == 0) {
						Log.info("********* No data found for sorting on column : " + columnName);
						return true;
					}
				}

			}
		}
		for (WebElement we : sortColumnData) {
			if (we.getText().toString().length() > 0) {
				obtainedList.add(we.getText().toUpperCase());
			}
		}
		for (String s : obtainedList) {
			sortedList.add(s);
		}

		if (columnName.equals("LOC Date") || columnName.equals("Member Effective Date")
				|| columnName.equals("Next Quarterly") || columnName.equals("Next Annual")
				|| columnName.equals("Eligibility Start Date") || columnName.equals("Last Updated") || columnName.equals("Date Identified") || columnName.equals("Date Reidentified")|| columnName.equals("Date Closed") || columnName.equals("Verified Date")||columnName.equals("Updated Date & Time")||columnName.equals("Diagnosis/Onset Date")||columnName.equals("Resolved Date")) {
			Collections.sort(sortedList, new Comparator<String>() {
				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

				public int compare(String date1, String date2) {
					try {
						return dateFormat.parse(date1).compareTo(dateFormat.parse(date2));
					} catch (ParseException e) {
					}
					return 0;
				}
			});
		} else if (columnName.equals("Note Date")) {
			Collections.sort(sortedList, new Comparator<String>() {
				DateFormat dateFormat2 = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");

				public int compare(String date1, String date2) {
					try {
						return dateFormat2.parse(date1).compareTo(dateFormat2.parse(date2));
					} catch (ParseException e) {
					}
					return 0;
				}
			});
		} else if (columnName.equals("Caseload") || columnName.equals("Past Due Tasks")
				|| columnName.equals("Tasks Due in 2 Weeks") || columnName.equals("Member Age")) {
			for (String s : obtainedList) {
				sortedListInteger.add(Integer.parseInt(s));
				obtainedListIngeter.add(Integer.parseInt(s));
			}
			Collections.sort(sortedListInteger);
			if (sortingAction == "DESC") {
				Collections.reverse(sortedListInteger);
			}
			if (sortedListInteger.equals(obtainedListIngeter))
				isSortedFlag = true;
			return isSortedFlag;
		} else {
			Collections.sort(sortedList);
		}
		if (sortingAction == "DESC") {
			Collections.reverse(sortedList);
		}
		if (sortedList.equals(obtainedList))
			isSortedFlag = true;
		return isSortedFlag;
	}

	/*--------------------------User Story-7890,7586,7434,9091,9276................*/

	// This Method is Performing Dropdown Actions on NextTaskDue with "To" and
	// "From" Date Range,getting combinations of DD with dates from excel.

	public void nextTaskDueDropDown(String sheetname) throws Throwable {

		Log = Logger.getLogger("TaskFilters.class");
		PropertyConfigurator.configure("log4j.properties");

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,-150)", "");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

		File sour = new File(TestDataFilePath);
		FileInputStream fiss = new FileInputStream(sour);
		XSSFWorkbook wb = new XSSFWorkbook(fiss);
		XSSFSheet FilterSheet = wb.getSheet(sheetname);
		int row = FilterSheet.getLastRowNum();
		XSSFRow xrow;

		String dropDownValue = "";

		try {
			for (int i = 1; i <= row; i++) {

				xrow = FilterSheet.getRow(i);

				if (xrow != null) {

					String value = xrow.getCell(0).getStringCellValue().toString();
					Log.info("*** Pick the Next Task Due from Excel sheet : " + value);
					if (value.equals("All Members") || (value.equals("All Tasks"))) {
						if (dropDownValue != "") {

							driver.findElement(By.xpath(config.VerifyNextTaskDueDropDown())).click();
							Thread.sleep(2000);

							driver.findElement(By
									.xpath("//label[contains(text(),'Next Task Due:')]//following-sibling::p-dropdown//div//span[text()='"
											+ value + "']"))
									.click();
						}

						dropDownValue = value;
						Thread.sleep(5000);
						Log.info(value + " is selected from Next Task Due");
						if (xrow.getCell(1) != null) {

							DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
							Date date = FilterSheet.getRow(i).getCell(1).getDateCellValue();
							String fromDate = dateFormat.format(date).toString();
							WebElement dateFrom = wait.until(ExpectedConditions
									.elementToBeClickable(By.xpath(config.getDateRangeFromOnMembers())));
							dateFrom.sendKeys(Keys.CONTROL + "a");
							dateFrom.sendKeys(Keys.DELETE);
							Thread.sleep(2000);
							dateFrom.sendKeys(fromDate);

							Thread.sleep(GlobalValues.Sleep_wait_time);
						}
						if (xrow.getCell(2) != null) {

							DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
							Date date = FilterSheet.getRow(i).getCell(2).getDateCellValue();
							String toDate = dateFormat.format(date).toString();
							WebElement dateTo = wait.until(ExpectedConditions
									.elementToBeClickable(By.xpath(config.getDateRangeToOnMembers())));

							dateTo.sendKeys(Keys.CONTROL + "a");
							dateTo.sendKeys(Keys.DELETE);
							Thread.sleep(2000);
							dateTo.sendKeys(toDate);
							dateTo.sendKeys(Keys.TAB);
							Thread.sleep(GlobalValues.Sleep_wait_time);
						}

						clickFilterButton();

					} else if (value.equals("Past Due") || value.equals("Next 2 Weeks")
							|| value.equals("Next 30 Days")) {

						int rangeDays = 0;
						if (value.equals("Past Due"))
							rangeDays = 0;
						else if (value.equals("Next 2 Weeks"))
							rangeDays = 14;
						else if (value.equals("Next 30 Days"))
							rangeDays = 30;

						driver.findElement(By.xpath(config.VerifyNextTaskDueDropDown())).click();
						Thread.sleep(2000);

						driver.findElement(By
								.xpath("//label[contains(text(),'Next Task Due:')]//following-sibling::p-dropdown//div//span[text()='"
										+ value + "']"))
								.click();
						clickFilterButton();
						dropDownValue = value;
						Thread.sleep(3000);
						Boolean verifyFlag = verifyGridData(rangeDays);
						if (verifyFlag == true) {
							Log.info("*************Data verified sucessfully for task option : " + value);

						}

						else
							Log.info("!!!!!!!!!!!!!Data not verified for task option : " + value);

					}
				}
			}
		} catch (Exception e) {
			{
				Log.info("Error in nextTaskDueDropDown method" + e.getMessage());
			}
		}

	}

	public void clickFilterButton() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getFilterOnMembers()))).click();

		Log.info("Filter button is clicked");

	}

	public Boolean verifyGridData(int days) throws ParseException {
		Log = Logger.getLogger("DashboardFunctions.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		String dueDate = "";
		Boolean flag = false;
		Date currentDate = new Date();
		List<WebElement> VisitStatusXpath = driver.findElements(By.xpath(config.verifyNextTaskDueDates()));
		if (VisitStatusXpath.size() == 0) {
			Log.info("No Data Found");
			WebElement VisitStatusXpathNoData = driver.findElement(By.xpath(config.VerifyNoRecordsFound()));
			if (VisitStatusXpathNoData.getText().equals("No records found")) {
				return true;
			}
		}
		for (WebElement we : VisitStatusXpath) {
			if (we.getText() != "") {
				dueDate = we.getText();
				int index = dueDate.indexOf("(");
				if (index != -1) {
					dueDate = dueDate.substring(0, index);
					break;
				}
			}
		}
		if (dueDate.length() > 0) {
			// SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date formattedDueDate = formatter.parse(dueDate);

			Calendar c = Calendar.getInstance();
			c.add(Calendar.DATE, days);
			Date formattedEndDate = c.getTime();
			if (days > 0)
				flag = isWithinRange(formattedDueDate, currentDate, formattedEndDate, false);
			else
				flag = isWithinRange(formattedDueDate, currentDate, formattedEndDate, true);
		}
		return flag;
	}

	public boolean isWithinRange(Date dueDateValue, Date startDate, Date endDate, Boolean pastDueFlag) {
		if (pastDueFlag == true)
			return dueDateValue.getTime() < startDate.getTime();
		else
			return dueDateValue.getTime() >= startDate.getTime() && dueDateValue.getTime() <= endDate.getTime();
	}

	// This method is Clearing all Filters applied//
	// ******************9276**************//

	public void performClearAllFilters() throws Exception {
		Log = Logger.getLogger("DashboardFunctions.class");
		PropertyConfigurator.configure("log4j.properties");
		String startDate = "12/31/2017";
		String endDate = "12/31/2018";
		setClearFilterValues("All Members", startDate, endDate, true);
		setClearFilterValues("Past Due", startDate, endDate, false);

	}

	public void setClearFilterValues(String dropDownValue, String startDate, String endDate, Boolean otherValueFlag)
			throws Exception

	{
		Log = Logger.getLogger("DashboardFunctions.class");
		PropertyConfigurator.configure("log4j.properties");
		String expected = "";
		element1 = driver.findElement(By.xpath(config.getNextTaskDueOnMembers()));
		element1.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//li//span[text()='" + dropDownValue + "']")).click();
		if (otherValueFlag == true) {

			element = driver.findElement(By.xpath(config.getDateRangeFromOnMembers()));
			element.sendKeys(Keys.CONTROL + "a");
			element.sendKeys(Keys.DELETE);
			Thread.sleep(2000);
			element.sendKeys(startDate);
			element2 = driver.findElement(By.xpath(config.getDateRangeToOnMembers()));
			element2.sendKeys(Keys.CONTROL + "a");
			element2.sendKeys(Keys.DELETE);
			Thread.sleep(2000);
			element2.sendKeys(endDate);
		}
		// Click the filter button
		driver.findElement(By.xpath(config.clearAllFiltersOnMembers())).click();
		if (otherValueFlag == true) {
			String startDateValue = element.getText();

			Assert.assertEquals(startDateValue, expected);
			String endDateValue = element2.getText();

			Assert.assertEquals(endDateValue, expected);
		}

		Thread.sleep(3000);
		String dropDownValueActual = element1.getText();
		String dropDownValueExpected = "All Members";
		Assert.assertEquals(dropDownValueActual, dropDownValueExpected);

		Log.info("**********Clear filter executed sucessfully for option : " + dropDownValue);
	}
	
	
	//Activities Grid Verification

	public Boolean columnSortingOnGrid1(WebDriver ldriver) throws Exception {
		
		Boolean resultFlag=true;
		WebElement sortColumns;
		int columnIndexVariable=1;
		try {

			Log = Logger.getLogger("DashboardFunctions.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

			Boolean sortingFlag = false;
			String columnName = "";
			
			// Calling getGridColumnNames Method from class DashboardLandingPage

			ActivitiesGridVerification ActGrid= PageFactory.initElements(driver, ActivitiesGridVerification.class);
						
			List<String> ColumnName = ActGrid.getGridColumnNames1();
				
			for (int ColumnNumber = 0; ColumnNumber < ColumnName.size(); ColumnNumber++) {
				columnName = ColumnName.get(ColumnNumber);
				String sortXpath ="//th[@class='ui-sortable-column ng-star-inserted'][ColumnNumber]//p-sorticon[@class='ng-star-inserted']//span[@class]";
				Integer columnIndex = new Integer(ColumnNumber);
				if(columnIndex.equals(0))
				{
					 sortColumns = wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath(sortXpath.toString().replace("ColumnNumber", String.valueOf(ColumnNumber+1))))));
					 
				}
				else
				{
					 sortColumns = wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath(sortXpath.toString().replace("ColumnNumber", String.valueOf(ColumnNumber))))));
				}
				Thread.sleep(3000);
				sortColumns.click();
				
				Thread.sleep(3000);
				sortingFlag = verifySorting(columnName, ColumnNumber,"ASC",columnIndexVariable);
				if (sortingFlag == true)
					Log.info("********* Ascending order sorting executed sucessfully for column : " + columnName);
				else
					Log.info("!!!!!  Error in Ascending order sorting  for column : " + columnName);
				Thread.sleep(3000);
				if(resultFlag!=false)
					resultFlag = sortingFlag;
				sortColumns.click();
				Thread.sleep(3000);
				sortingFlag = verifySorting(columnName,ColumnNumber, "DESC",columnIndexVariable);
				if (sortingFlag == true)
					Log.info("********* Descending order sorting executed sucessfully for column : " + columnName);
				else
					Log.info("!!!!! Error in Descending order sorting for column : " + columnName);
				if(resultFlag!=false)
					resultFlag = sortingFlag;
				}

		} catch (Exception ex) {
			System.out.println("errors : " + ex);
			resultFlag =false;

		}
		return resultFlag;
	}


	}



