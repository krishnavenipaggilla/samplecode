package com.nimbus.pages;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;

import utility.ConfigReader;
import utility.UtilityClass;

public class AssessmentSummaryPage {

	ConfigReader config = new ConfigReader();

	WebDriver driver;
	Logger Log;

	public AssessmentSummaryPage(WebDriver Idriver) {
		this.driver = Idriver;
	}

	public AssessmentSummaryPage(Logger lLog) {
		this.Log = lLog;
	}


	Map<String, String> hm;

	public Map<String, String> getPDFName() {
		hm = new HashMap();
		hm.put("Freedom of Choice", "FL_LTSS_Freedom_of_Choice");
		hm.put("PDO Consent", "FL_LTSS_Participiant_Directed_Option");
		hm.put("Caregiver Assessment", "FL_Caregiver");
		hm.put("SMMC LTC Consent", "FL_LTSS_SMMC");
		hm.put("701T Comprehensive Assessment", "FL_LTSS_701t");
			
		return hm;

	}

	public List<String> getListOfAssessment() {
		//List<String> assessments = GlobalValues.PrintAssessmentList;
		List<String> assessments = new ArrayList<String>();
		//assessments.add("701B Comprehensive Assessment,visittask");
		assessments.add("Freedom of Choice,Orientation Visit"); 
////		assessments.add("PDO Consent,Orientation Visit");
		assessments.add("SMMC LTC Consent,Orientation Visit");
//		assessments.add("Advanced Directives,Orientation Visit");
////		//assessments.add("Caregiver Assessment,Orientation Visit");
		 assessments.add("701T Comprehensive Assessment,Orientation Visit");
		System.out.println("Assessments available for download "+assessments);
		return assessments;
		
		
	}
	/*
	 * Method types in names of assessments into Assessments Summary page grid's
	 * Assessment Name search box (one Assessment at a time) and validates they
	 * are present in the list/table(just checking the size of returned records
	 * is >0 and clicks on viewAssessment to download and verifies download ).
	 */
	public void validatePrintAssessment() throws Exception {
		try {
			Log = Logger.getLogger("AnnualAssessment.class");
		PropertyConfigurator.configure("log4j.properties");
		List<String> assessments = getListOfAssessment();
		
		String status = null,lastUpdatedUser;
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		// WebDriverWait wait = new WebDriverWait(driver, 20);
		/*List<WebElement> nameSearch = wait.until(
				ExpectedConditions.presenceOfAllElementsLocatedBy((By.xpath(config.getAssessmentPageColumnFilter()))));*/
		WebElement nameSearch = wait
				.until(ExpectedConditions.presenceOfElementLocated((By.xpath(config.getAssessmentNameFilter()))));
		
		for (String ele : assessments) {
			Thread.sleep(1000);
			nameSearch.clear();
			nameSearch.sendKeys(ele.split("[,]")[0]);
			String xpathButton = "//span[@title='" + ele.split("[,]")[0]+ "']//ancestor::tr//following-sibling::td//span[@title='" + ele.split("[,]")[1]+ "']//ancestor::td//following-sibling::td//button";
			// String xpathButton = "//tbody/tr/td//span[@title='" +
			// ele.split("[,]")[0]+
			// "']/ancestor::td/following-sibling::td//span[@title='" +
			// ele.split("[,]")[1]+
			// "']/ancestor::td/following::td[last()]//button";
			Log.info("entered the assessment name " + ele.split("[,]")[0]);
			List<WebElement> dropDown = null;
			
		
			try{

				 dropDown = wait
						.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By.xpath(xpathButton))));
				 WebElement statusOfAssessments = wait
							.until(ExpectedConditions.presenceOfElementLocated((By.xpath(config.getAssessmentStatusXpath()))));
					status = statusOfAssessments.getText();
					
					WebElement lastUpdatedBy = wait
							.until(ExpectedConditions.presenceOfElementLocated((By.xpath(config.getLastUpdatedBy()))));
					lastUpdatedUser = lastUpdatedBy.getText();
					
			}catch(Exception e)
			{
				dropDown = new ArrayList(0);
			}
			System.out.println("The dropdownsize is "+dropDown.size());
			
			if (dropDown.size() > 0) {

				Log.info(ele.split("[,]")[0] + " " + ele.split("[,]")[1] + " - Record found");

				dropDown.get(0).click();
			} else {
				
				Log.info(ele.split("[,]")[0] + " " + ele.split("[,]")[1] + " - Record not found");
				continue;
				//throw new Exception("Assessment unavailable or drowpdown link is disabled");
			}

			WebElement viewAssessment = wait.until(
					ExpectedConditions.presenceOfElementLocated((By.linkText(config.getAssessmentViewAssessment()))));
			if (status.equals(GlobalValues.Completed_status)) {
				if (viewAssessment.isEnabled()) {
					viewAssessment.click();
					Log.info("Click on View Assessment to Download");
					Boolean download = validateAssessmentDownload(ele.split(",")[0]);
					Log.info(download);
					if (download.equals(true)) {
						Log.info("File downloaded successfully");
					} else {
						Log.info("File download failed");
					}
				} else {
					//throw new Exception("status is completed but link is disabled");
				}

			} else if (status.trim().equals(GlobalValues.InProgress_status)) {
				Log.info("Assessment is InProgress");
				if (viewAssessment.isEnabled()) {
					Log.info("View Assessment link enabled for InProgress");
				}
			}

		}
		
		}
		catch (Exception e) {
			Log.info("Exception in validate Print Assessment");
			e.getStackTrace();
			
		}
		

	}
	
	
	public void validateLastUpdated() throws Exception {
		Log = Logger.getLogger("AnnualAssessment.class");
		PropertyConfigurator.configure("log4j.properties");
		Log.info("Entered validateLastUpdated");
		WebDriverWait wait = new WebDriverWait(driver,GlobalValues.Explicit_Wait_time );
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		String currentDate = dateFormat.format(date);
		
		WebElement lastUpdatedFilterButton = wait
				.until(ExpectedConditions.presenceOfElementLocated((By.xpath(config.getLastUpdatedColumnFilterButton()))));
		lastUpdatedFilterButton.click();
		Log.info("Clicked on Last Updated Filter");
		
				WebElement lastUpdated = wait
				.until(ExpectedConditions.presenceOfElementLocated((By.xpath(config.getLastUpdated()))));
		lastUpdated.sendKeys(currentDate);
		Log.info("Entered date in the Last updated field");
		
		List<WebElement> lastUpdatedDates = wait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By.xpath(config.getLastUpdatedDate()))));	
		
		/*Iterator<WebElement> itr = lastUpdatedDates.iterator();
		while(itr.hasNext()) {
		    Log.info(itr.next().getText());
		}*/
		Log.info("No of records: " + lastUpdatedDates.size());
		for(WebElement lastUpdatedDate:lastUpdatedDates)
		{
			if(!lastUpdatedDate.getText().trim().equals(currentDate))
			{
				
				Log.info("Date validation failed");
				
			}else{
				Log.info("Date validation passed");
			}
		}
		
		lastUpdated.clear();
		
		
		
	}

	// Method gets list of files available in system downloads page and
	// validates if the PDF is downloaded into the user's system when
	// ViewAssessment Link is clicked
	public boolean validateAssessmentDownload(String pdfName) {

		//String dir = System.getProperty("user.home") + "//Downloads"; // C:/......
		String dir = System.getProperty("user.home");
		Log.info("Downloaded in folder "+dir);
		boolean isDownloaded = false;
		File downloadedfolder = new File(dir);

		File[] listOfFiles = downloadedfolder.listFiles();
		
		
		for (int i = 0; i < listOfFiles.length; i++) {

			if (listOfFiles[i].isFile()) {
				String fileName = listOfFiles[i].getName();
				//Log.info(fileName);

				if (fileName.endsWith(".pdf") && fileName.contains(getPDFName().get(pdfName))) {

					isDownloaded = true;
				}
			}
		}
		return isDownloaded;

	}

	public List<String> getAssessmentColumnNames()
	{
		List<String> columnNames = new ArrayList<String>();
		try{
			
			columnNames.add("Name");
			columnNames.add("Status");
			columnNames.add("Task");
			columnNames.add("Last Updated");
			columnNames.add("Last Updated By");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return columnNames;
	}
	
	// Method validates the column names of the Assessments Page grid
	public void validateAssessmentGridColumns() {

		try {
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

			Log = Logger.getLogger("AnnualAssessment.class");
			PropertyConfigurator.configure("log4j.properties");
			List<WebElement> assessmentColumns = wait.until(ExpectedConditions
					.presenceOfAllElementsLocatedBy((By.xpath(config.getDocumentGridHeaderColumnsXpath()))));
			List<String> columnNames = getAssessmentColumnNames();
			
			for (int i = 0; i < columnNames.size(); i++) {
				String actualColumnName = assessmentColumns.get(i).getText().trim();
				if (columnNames.get(i).equals(actualColumnName)) {
					Log.info("The column " + actualColumnName + " found");
				} else {
					System.out.println("Actual:" + actualColumnName + "-" + columnNames.get(i));
					Log.info("Column "+ actualColumnName + " is not found!");
					throw new Exception("column not found");
				}
			}

		
		} catch (Exception e) {
			Log.info("Exception in validateAssessmentGridColumns");
			e.getStackTrace();
			
		
		}

	}
	
	
	public void validateAssessmentsDefaultSort(){
		try {
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			List<String> actualDateList = new ArrayList();
			Log = Logger.getLogger("AnnualAssessment.class");
			PropertyConfigurator.configure("log4j.properties");
			List<WebElement> assessmentDates = wait.until(ExpectedConditions
					.presenceOfAllElementsLocatedBy((By.xpath(config.getAssessmentDateList()))));
			for(WebElement assessmentDate:assessmentDates)
			{
				actualDateList.add(assessmentDate.getText());
			}
			
			List<String> expectedDateList = new ArrayList<String>();
			for(String dateList:actualDateList)
			{
				expectedDateList.add(dateList);
			}
			
			Collections.sort(expectedDateList,new Comparator<String>(){
				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				public int compare(String date1,String date2){
					try {
						 return dateFormat.parse(date1).compareTo(dateFormat.parse(date2));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//return 0;
					return 0;
				}
			});
			
			Collections.reverse(expectedDateList);
			/*Iterator<WebElement> itr = lastUpdatedDates.iterator();
			while(itr.hasNext()) {
			    Log.info(itr.next().getText());
			}*/
			System.out.println("Actual List");
			for(String a:actualDateList)
				System.out.println(a);
			
			System.out.println("Expected List");
			for(String e:expectedDateList)
				System.out.println(e);
			if(actualDateList.equals(expectedDateList))
			{
				Log.info("Assessments are sorted by date by default");
			}else
			{
				Log.info("Assessments are NOT sorted by date by default");
			}
			
			
		}catch (Exception e) {
			Log.info("Exception in validate Assessment default sort");
			e.getStackTrace();
		}
		
	}
	
	public void validateAssessmentGridColumnSorting(){
		try {
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			List<String> actualList = new ArrayList<String>();
			Log = Logger.getLogger("AnnualAssessment.class");
			PropertyConfigurator.configure("log4j.properties");
			
			List<String> columnNames = getAssessmentColumnNames();
			
			for(String columnName:columnNames)
			{
				String sortXpath ="//span[text()='"+columnName+"']/parent::span/following-sibling::span[@class]";
				String columnListXpath ="//span[text()='"+columnName+"']/parent::td/span[contains(@class,'ui-cell')]/span[@title]";
				WebElement assessmentNameSort = wait.until(ExpectedConditions
						.presenceOfElementLocated((By.xpath(sortXpath))));
				assessmentNameSort.click();
				List<WebElement> assessmentNameList = wait.until(ExpectedConditions
						.presenceOfAllElementsLocatedBy((By.xpath(columnListXpath))));
				
				if(verifySorting(assessmentNameList,columnName))
				{
					Log.info(columnName+" sorted successfully");
				}else
				{
					Log.info(columnName+" sort failed!");
				}
			}
		
			
			
			
		}catch (Exception e) {
			Log.info("validate Assessment sort failed ");
			e.getStackTrace();
		}
		
		
	}
	
	public boolean verifySorting(List<WebElement> items,String columnName)
	{
		boolean isSorted = false;
		List<String> actualList = new ArrayList();
		List<String> expectedList = new ArrayList();
		
		for(WebElement listOfItems:items)
		{
			actualList.add(listOfItems.getText());
		}
		
		
		for(String list:actualList)
		{
			expectedList.add(list);
		}
		
		if(columnName.equals("Last Updated"))
		{
			Collections.sort(expectedList,new Comparator<String>(){
				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				public int compare(String date1,String date2){
					try {
						 return dateFormat.parse(date1).compareTo(dateFormat.parse(date2));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//return 0;
					return 0;
				}
			});
		}else{
		Collections.sort(expectedList);
		}
		//Collections.reverse(expectedList);
		
		if(actualList.equals(expectedList))
		{
			 isSorted = true;

		}
		return isSorted;
	}

}
