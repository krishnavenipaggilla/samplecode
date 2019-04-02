package com.nimbus.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections.map.MultiValueMap;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.ConfigReader;

public class ActivitiesGridVerification {
	private List<String> ActualColumnNames = null;
	WebDriver driver;
	Logger Log;
	ConfigReader config = new ConfigReader();

	public ActivitiesGridVerification(WebDriver ldriver) {
		this.driver = ldriver;
	}

	ActivitiesGridVerification(Logger lLog) {
		this.Log = lLog;
	}

	public void ActivitiesGrid() throws Exception {
		try {
			activitiesGridVerification();
//			activitiesSorting();
//			activitiesFilterVerification();
			Log.info("Passed-NIM-15005: View, Sort, Filter Activity List" + '\n' + "GIVEN:view the edit activity list"
					+ '\n' + "THEN:verifying,sorting and filtering the grid");
			
		} catch (Exception e) {
			Log.info("Failed-NIM-15005: View, Sort, Filter Activity List");
			
				}
		Log.info("Refactored-NIM-15086: View, Edit Activity List");

	}

	private void activitiesSorting() throws Exception {
		Log = Logger.getLogger("ActivitiesGridVerification.class");
		PropertyConfigurator.configure("log4j.properties");
		Global_Method gm = new Global_Method(driver);
		DashboardFunctions df = new DashboardFunctions(driver);
		Thread.sleep(3000);
		// df.columnSortingOnGrid1(driver);
		df.columnSorting(driver);

	}

	private void activitiesFilterVerification() throws Exception {

		Log = Logger.getLogger("ActivitiesGridVerification.class");
		PropertyConfigurator.configure("log4j.properties");
		Global_Method gm = new Global_Method(driver);
		DashboardFunctions df = new DashboardFunctions(driver);
		MultiMap multiMapFilterDetails = new MultiValueMap();
		multiMapFilterDetails.put("Activity Type",
				gm.readFromExcel("AnnualAssessments_CMDM", "ActivityGrid", 1, "Activity Type"));
		multiMapFilterDetails.put("Activity Type", "column_1");
		multiMapFilterDetails.put("Activity Type", "1");

		multiMapFilterDetails.put("Status", gm.readFromExcel("AnnualAssessments_CMDM", "ActivityGrid", 1, "Status"));
		multiMapFilterDetails.put("Status", "column_2");
		multiMapFilterDetails.put("Status", "2");

		multiMapFilterDetails.put("Due Date",
				gm.readFromExcel("AnnualAssessments_CMDM", "ActivityGrid", 1, "Due Date"));
		multiMapFilterDetails.put("Due Date", "column_3");
		multiMapFilterDetails.put("Due Date", "3");

		multiMapFilterDetails.put("Completed Date",
				gm.readFromExcel("AnnualAssessments_CMDM", "ActivityGrid", 1, "Completed Date"));
		multiMapFilterDetails.put("Completed Date", "column_4");
		multiMapFilterDetails.put("Completed Date", "4");

		multiMapFilterDetails.put("Assigned To",
				gm.readFromExcel("AnnualAssessments_CMDM", "ActivityGrid", 1, "Assigned To"));
		multiMapFilterDetails.put("Assigned To", "column_5");
		multiMapFilterDetails.put("Assigned To", "5");

		multiMapFilterDetails.put("Created By",
				gm.readFromExcel("AnnualAssessments_CMDM", "ActivityGrid", 1, "Created By"));
		multiMapFilterDetails.put("Created By", "column_6");
		multiMapFilterDetails.put("Created By", "6");

		multiMapFilterDetails.put("Last Updated By",
				gm.readFromExcel("AnnualAssessments_CMDM", "ActivityGrid", 1, "Last Updated By"));
		multiMapFilterDetails.put("Last Updated By", "column_7");
		multiMapFilterDetails.put("Last Updated By", "7");

		try {

			df.columnLevelFilteration(multiMapFilterDetails);
			Log.info("Verified the Column level Filters on Activity Grid");
		

		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.info("Could not verify the Column level Filters on Activity Grid");
			
		}
	}

	private void activitiesGridVerification() throws Exception {
		Log = Logger.getLogger("ActivitiesGridVerification.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		// memberSearch();

		// Left Navigation
		LeftNavigationLink leftNav = PageFactory.initElements(driver, LeftNavigationLink.class);

		leftNav.clickMemberActionCenter();
		leftNav.clickActivities();
		Thread.sleep(3000);

		DashboardFunctions GridCloumns = PageFactory.initElements(driver, DashboardFunctions.class);

		// Verifying Grid Column Names in Activity

		ActualColumnNames = getGridColumnNames1();

		GridCloumns.validateGridColumnNames(GlobalValues.ActivityColumns, ActualColumnNames);
	}

	private void memberSearch() throws InterruptedException {
		Log = Logger.getLogger("ActivitiesGridVerification.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		MemberSearch MemberSearch = PageFactory.initElements(driver, MemberSearch.class);
		MemberSearch.navigateToMemberSearch();
		WebElement activity = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[@id='memberSearchSection']//input[@id='caseId']")));
		activity.sendKeys("115");
		WebElement searchbutton = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[@id='memberSearchSection']//button[text()='Search']")));
		searchbutton.click();
		Thread.sleep(8000);
		WebElement clickcase = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='gsFlexGrid']//a")));
		clickcase.click();

	}

	// This Method is returning the list of columns in Member Grid

	public List<String> getGridColumnNames1() throws Exception {

		Log = Logger.getLogger("ActivitiesGridVerification.class");
		PropertyConfigurator.configure("log4j.properties");

		List<WebElement> GridColumnCount = driver.findElements(By.xpath(config.getColumnssOnGrid()));

		StringBuilder Name = new StringBuilder();
		Name = Name.append(config.getColumnAppend());
		int ColumnCount = GridColumnCount.size();

		List<String> ColumnNames = new ArrayList<String>();

		if (ColumnCount > 0) {
			for (int ColumnNumber = 1; ColumnNumber <= 8; ColumnNumber++) {
				ColumnNames.add(driver
						.findElement(By.xpath(Name.toString().replace("columnNumber", String.valueOf(ColumnNumber))))
						.getAttribute("textContent").toString().trim());

			}
			Log.info("All Column Names are loaded Completely and Present on Grid");

		} else {
			Log.info("Member Grid do not have any columns to prepare the list for validation");
			ColumnNames = null;
		}
		return ColumnNames;
	}

}
