package com.nimbus.pages;

import static org.testng.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;

import excelReader.DataReaderExcel;
import junit.framework.Assert;
import utility.ConfigReader;

public class Medication {
	
	WebDriver driver;
	Logger Log;
	
	ConfigReader config = new ConfigReader();
	DataReaderExcel DataRead = new DataReaderExcel();

	public Medication(WebDriver ldriver) {
		this.driver = ldriver;
	}

	public Medication(Logger lLog) {
		this.Log = lLog;
	}
	
	public void MedicationPage_Grid() throws InterruptedException
	{
		try {
		Log = Logger.getLogger("Medication.class");
		PropertyConfigurator.configure("log4j.properties");
		
			//verifying Medication Page
			for(int i=0; i<GlobalValues.linksOnMedicationPage.size();i++)
			{
				driver.findElement(By.xpath("//button[contains(.,'"+GlobalValues.linksOnMedicationPage.get(i)+"')]"));
				Log.info("Verifying the "+ GlobalValues.linksOnMedicationPage.get(i) + "- link on the page");
			}
		
			//verify grid header 
			WebElement gridHeader =  driver.findElement(By.xpath(config.getGridHeader()));
			boolean isGridHeaderPresent = gridHeader.isDisplayed();
		
			assertEquals(gridHeader.getText(), "Current Medications");
			Log.info("Label of the grid is - Current Medications");
			
			//verify grid columns- Current Medication
			for(int i=0; i<GlobalValues.MedicationGrid_Current.size();i++)
			{
				driver.findElement(By.xpath("//span[text()= '"+GlobalValues.MedicationGrid_Current.get(i)+"']"));
				Log.info("Verifying the "+ GlobalValues.MedicationGrid_Current.get(i) + "- column on the grid");
			}
			Log.info("Verified Current Medication Grid");
			
			//verifying Checkbox at header level
			WebElement Checkbox = driver.findElement(By.xpath(config.VerifyCheckBox()));
			if(Checkbox.isDisplayed()) {
				Log.info("Checkbox is present in the Current Medication page grid");
			} else {
				Log.info("Checkbox is not present in the Current Medication page grid");
			}
			
			//verifying "Review" button
			WebElement ReviewButton = driver.findElement(By.xpath(config.VerifyReviewButton()));
			if(ReviewButton.isDisplayed()) {
				Log.info("Review button is present in the Current Medication page");
			} else {
				Log.info("Review button is not present in the Current Medication page");
			}
			Thread.sleep(3000);
			//navigating and verifying All Medications
			WebElement AllMedications_button = driver.findElement(By.xpath(config.AllMed()));
			AllMedications_button.click();
			Thread.sleep(5000);
			
			/*Revert back after the defect is fixed */
			
//			WebElement gridHeader1 =  driver.findElement(By.xpath(config.AllMedHeader()));
//			assertEquals(gridHeader1.getText(), "All Medications" );
//			Thread.sleep(5000);
//			Log.info("Label of the grid is - All Medications");
			
			//verify grid columns - All Medication
//			for(int i=0; i<GlobalValues.MedicationGrid_All.size();i++)
//			{
//				driver.findElement(By.xpath("//span[text()= '"+GlobalValues.MedicationGrid_All.get(i)+"']"));
//				Log.info("Verifying the "+ GlobalValues.MedicationGrid_All.get(i) + "- column on the grid");
//			}
//			Log.info("Verified All Medication Grid");
//			Log.info("No Medications Grid is present");
			
			/*Revert back after the defect is fixed */
			
		} 
		catch (Exception e)
		{
			Log.info("Failed - " +e);
			e.printStackTrace();
		}
	}

	// click on Medications link in Health Chart
	public void clickMedications()
	{
		try {
		Log = Logger.getLogger("Medication.class");
		PropertyConfigurator.configure("log4j.properties");
		LeftNavigationLink lnk = new LeftNavigationLink(driver);
		
		lnk.clickHealthChart();
		Log.info("Click Medication Tab");
		WebElement MedicationButton = driver.findElement(By.xpath(config.ClickMed()));
		assertEquals(MedicationButton.getText(), "Medications");
		Log.info("Lable of the page is - " + MedicationButton.getText());
		MedicationButton.click();
		}
		catch (Exception e)
		{
			Log.info("Failed - " +e);
		}
	}
	
	//Add Medication
	public void AddMedication() throws Exception
	{
		try {
		Log = Logger.getLogger("Medication.class");
		PropertyConfigurator.configure("log4j.properties");
		AnnualAssessment annual = new AnnualAssessment(driver);
		
		WebElement AddMedication = driver.findElement(By.xpath(config.AddMed()));
		AddMedication.click();
		Log.info("Clicked on Add Medication link");
	
		//Medication Search Term field and search
		WebElement MedSearchTerm = driver.findElement(By.xpath(config.MedSearch()));
		assertEquals(MedSearchTerm.getText().trim(), "Medication Search Term");	
		System.out.println(MedSearchTerm.getText().trim());
		Thread.sleep(4000);
		WebElement SendMedName = driver.findElement(By.xpath(config.DrugName()));
		SendMedName.sendKeys("Pravachol");
		System.out.println("Drug Name Entered = Pravachol");
		
		//click search button
		WebElement SearchButton = driver.findElement(By.xpath(config.SearchMed()));
		SearchButton.click();
		Thread.sleep(3000);
		
		//Verify Search results grid
		WebElement SearchResults = driver.findElement(By.xpath(config.getSearchResults()));
		assertEquals(SearchResults.getText(), "Search Results");
		
		
		for(int i=0; i<GlobalValues.AddMedicationSearchResults.size();i++)
		{
			driver.findElement(By.xpath("//span[text()= '"+GlobalValues.AddMedicationSearchResults.get(i)+"']"));
			Log.info("Verifying the "+ GlobalValues.AddMedicationSearchResults.get(i) + " - column on the grid");
		}
		Log.info("Verified Medication Search Results Grid");
		{
//			WebElement openDrugDropdown = driver.findElement(By.xpath(config.OpenDrug()));
//			openDrugDropdown.click();
		Thread.sleep(3000);
			WebElement drugSelect = driver.findElement(By.xpath(config.SelectDrug()));
			drugSelect.click();
			Thread.sleep(3000);
			annual.annual_assessment("AnnualAssessments_CMDM", "Medications", 2);
			
			WebElement AddMed = driver.findElement(By.xpath(config.ClickAddMed()));
			AddMed.click();
			Thread.sleep(5000);
			Log.info("Added Medication values to the grid");
		}
		}
		catch (Exception e)
		{
			Log.info("Failed - " +e);
			e.printStackTrace();
		}
	}
	
	public void UpdateMedication() throws Exception
	{
		try {
		Log = Logger.getLogger("Medication.class");
		PropertyConfigurator.configure("log4j.properties");
		AnnualAssessment annual = new AnnualAssessment(driver);
		
		//Verify value
		WebElement verifyMed = driver.findElement(By.xpath(config.VerifyMed()));
		Assert.assertEquals((verifyMed.getText()), "PRAVACHOL");
		Log.info("Click on Edit Medication");
		Thread.sleep(5000);
		//Click on Edit Medication
		WebElement EditMed = driver.findElement(By.xpath(config.EditMed()));
		EditMed.click();
		
		annual.annual_assessment("AnnualAssessments_CMDM", "Medications", 3);
		
		//Click on Update button
		WebElement UpdateMed = driver.findElement(By.xpath(config.ClickUpdateMed()));
		UpdateMed.click();
		
		Log.info("Updated Medication values");
		}
		catch (Exception e)
		{
			Log.info("Failed - " +e);
		}
	}
	
	public void MedicationSecondaryRowVerification() throws InterruptedException 
	{
		Log = Logger.getLogger("Medication.class");
		PropertyConfigurator.configure("log4j.properties");
		
		//verifying Edit and History buttons of each Medications
		Thread.sleep(3000);
		WebElement EditButton = driver.findElement(By.xpath(config.VerifyUpdateButton()));
		WebElement HistoryButton = driver.findElement(By.xpath(config.VerifyHistoryButton()));
		if (EditButton.isDisplayed() && HistoryButton.isDisplayed()) {
			Log.info("Edit and History images displayed for each Medication");	
		}
		else {
			Log.info("Edit and History images not displayed for each Medication");
		}
		
		Log.info("Secondary Row verification in Medications");
		Thread.sleep(5000);
		WebElement MedicationView = driver.findElement(By.xpath(config.ClickMedView()));
		MedicationView.click();
		
		for(int i=0; i<GlobalValues.MedicationsSecondaryRowLabels.size();i++)
		{
			driver.findElement(By.xpath("//nm-card-details/div//nm-card-details-field/div//label[text()= '"+GlobalValues.MedicationsSecondaryRowLabels.get(i)+"']"));
			Log.info("Verifying the "+ GlobalValues.MedicationsSecondaryRowLabels.get(i) + " - label on Medication Secondary Row");	
			WebElement Values = driver.findElement(By.xpath("//nm-card-details/div//nm-card-details-field/div//ancestor::label[text()= '"+GlobalValues.MedicationsSecondaryRowLabels.get(i)+"']//following-sibling::div"));
			Log.info("Values of the Secondary row - " + Values.getText());
		}
	//	MedicationView.click();
		Log.info("Verified Medications Secondary Row");
	}
	
	public void ViewHistory() throws InterruptedException
	{
		try {
		Log = Logger.getLogger("Medication.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, 30);
		//Click on View History button
		WebElement ViewHistory = driver.findElement(By.xpath(config.getViewHistory()));
		Thread.sleep(3000);
		ViewHistory.click();
		Log.info("View History of Medication");

		WebElement gridHeader = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getMedicationsHistoryGridHeader())));
		Thread.sleep(7000);
		Assert.assertEquals((gridHeader.getText()), "Medication History");
		
		for(int i=0; i<GlobalValues.MedicationHistoryGrid.size();i++)
		{
			Thread.sleep(2000);
			driver.findElement(By.xpath("//span[text()= '"+GlobalValues.MedicationHistoryGrid.get(i)+"']"));
			Log.info("Verifying the "+ GlobalValues.MedicationHistoryGrid.get(i) + " - column on the grid");
		}
		
		//verifying History page secondary row
		WebElement HistorySecondaryRow = driver.findElement(By.xpath(config.getHistorySecRow()));
		HistorySecondaryRow.click();
		
		for(int i=0; i<GlobalValues.MedicationsHistorySecondaryrowLabels.size();i++)
		{
			driver.findElement(By.xpath("//label[text()= '"+GlobalValues.MedicationsHistorySecondaryrowLabels.get(i)+"']"));
			Log.info("Verifying the "+ GlobalValues.MedicationsHistorySecondaryrowLabels.get(i) + " - field in the Medications History Secondary row");
		}
		}
		catch (Exception e)
		{
			Log.info("Failed  - " +e);
		}
	}

	public void ReviewDate() throws InterruptedException
	{
		Log = Logger.getLogger("Medication.class");
		PropertyConfigurator.configure("log4j.properties");
		
		Log.info("Review button verification and validate");
		
		WebElement ClickCheckBox = driver.findElement(By.xpath(config.getClickFirstCheckBox())); //selecting first value in the grid
		Thread.sleep(3000);
		ClickCheckBox.click();
		
		//verifying "Review" button
		WebElement ReviewButton = driver.findElement(By.xpath(config.VerifyReviewButton()));
		if(ReviewButton.isDisplayed()) {
			Log.info("Review button is present in the Current Medication page");
		} else {
			Log.info("Review button is not present in the Current Medication page");
		}
		ReviewButton.click();
		WebElement ReviewedDateText = driver.findElement(By.xpath(config.getReviewedDate()));
		String ReviewDateText_Data = ReviewedDateText.getAttribute("value");
	
	}
}

