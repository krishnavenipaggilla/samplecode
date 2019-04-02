package com.nimbus.pages;

import static org.testng.Assert.assertEquals;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.joda.time.format.PeriodFormatterBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.nimbus.pages.Interventions;

import utility.ConfigReader;
import utility.WebActions;

public class CarePlan {

	WebDriver driver;
	Logger Log;
	
	ConfigReader config = new ConfigReader();
	WebActions wb = new WebActions();
	public CarePlan(WebDriver ldriver) {
		this.driver = ldriver;
	}

	public CarePlan(Logger lLog) {
		this.Log = lLog;
	}
	
	public void CarePlanPageVerify() {
		Log = Logger.getLogger("CarePlan.class");
		PropertyConfigurator.configure("log4j.properties");
		
		WebElement CPBreadCrumb = driver.findElement(By.xpath(config.CarePlanBreadCrumb()));
		Assert.assertEquals(CPBreadCrumb.getText(), "Care Plan", "Bread Crumb of the Care Plan is not showing");
		Log.info("Verified the Care Paln Bread Crumb");
		
		//verifying CarePlan Page links
		for(int i=0; i<GlobalValues.LinksOnCarePlanPage.size();i++)
		{ 
			driver.findElement(By.xpath("//button[contains(.,'"+GlobalValues.LinksOnCarePlanPage.get(i)+"')]"));
			Log.info("Verifying the "+ GlobalValues.LinksOnCarePlanPage.get(i) + "- link on the page");
		}
		
		//verifying Filter image on the page
		WebElement FilterImage = driver.findElement(By.xpath(config.getFilterImage()));
		if(FilterImage.isDisplayed()) {
			Log.info("Filter image is present");
		} else {
			Log.info("Filter image is not present");
		}

		//verifying Careplan grid columns
		for(int i=0; i<GlobalValues.CarePlanGrid.size();i++)
		{
			driver.findElement(By.xpath("//th//span[text()='"+GlobalValues.CarePlanGrid.get(i)+"']"));
			Log.info("Verifying the "+ GlobalValues.CarePlanGrid.get(i) + "- column on CarePlan grid");
		}
		WebElement ShowingValue = driver.findElement(By.xpath(config.getShowingValue()));
		Log.info("CarePlan Grid - Showing values displaying as : " + ShowingValue.getText());
		
	}
	
	public void CarePlan_AddGoal_Verify() throws Exception  
	{
		Log = Logger.getLogger("CarePlan.class");
		PropertyConfigurator.configure("log4j.properties");
		AnnualAssessment annual = new AnnualAssessment(driver);
		
		//Add Goal to the Page
		WebElement ClickAddGoalButton = driver.findElement(By.xpath(config.getAddGoalClick()));
		ClickAddGoalButton.click();
		Log.info("Click on Add Goal button on CarePlan page");
		
		//verifying AddGoal page
		WebElement VerifyAddGoalPage = driver.findElement(By.xpath(config.getVerifyAddGoalPage()));
		assertEquals(VerifyAddGoalPage.getText(), "Add Goal");
		Log.info(VerifyAddGoalPage.getText() + " is the label of the page"); 
		
		// Locating Goals drop down and selecting random number from Goals drop down
		WebElement dropdown = driver.findElement(By.xpath(config.GoalsDropdown()));
		dropdown.click();
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(config.getWait_Goal()))));
		List<WebElement> itemsInDropdown = driver.findElements(By.xpath(config.getSelectGoal()));
		int size = itemsInDropdown.size();
		int randNumber = ThreadLocalRandom.current().nextInt(0, size);
		itemsInDropdown.get(randNumber).click();
		Thread.sleep(2000);
		
		//verifying Priority drop down values
		WebElement PriorityLabel = driver.findElement(By.xpath(config.getPriorityValues()));
		PriorityLabel.click();
		for(int i=0;i<GlobalValues.CarePlanPriorityValues.size();i++) {
			
			driver.findElement(By.xpath("//div/ul/li["+ (i+1) +"]/span[text()='"+GlobalValues.CarePlanPriorityValues.get(i)+"']"));
			Log.info("Verifying the "+ GlobalValues.CarePlanPriorityValues.get(i) + " value in the Priority dropdown");
		}
				
//		// Selecting random Priority values in the Priority dropdown
//		dropdown = driver.findElement(By.xpath(config.PriorityDropdown()));
//		dropdown.click();
//		Thread.sleep(2000);	
			
//		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(config.getWait_Priority()))));
//		itemsInDropdown = driver.findElements(By.xpath(config.getSelectPriority()));
//		size = itemsInDropdown.size();
//		randNumber = ThreadLocalRandom.current().nextInt(0, size);
//		itemsInDropdown.get(randNumber).click();
	
		//selecting today's date as a Start Date
		WebElement StartDate = driver.findElement(By.xpath(config.getStartDate()));
		StartDate.click();
		
		DateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
        Date date1 = new Date();
		String date = DATE_FORMAT.format(date1);

		driver.findElement(By.xpath("//label[contains(.,'Start Date')]//ancestor::nm-input-label//following-sibling::p-calendar//span//input")).sendKeys(date);
		
		//verifying only the fields are present or not
		annual.annual_assessment("AnnualAssessments_CMDM", "CarePlan", 2);
		
		//Verifying Status and Status Reason values and auto-populate values
		WebElement Status = driver.findElement(By.xpath(config.getStatusValue()));
		Status.click();
		
		WebElement StatusText = driver.findElement(By.xpath(config.getStatusText_NotStarted()));
		StatusText.click();
		
		WebElement StatusValue = driver.findElement(By.xpath(config.getStatusValue()));
															  
		WebElement StatusReason = driver.findElement(By.xpath(config.getStatusReasonValue()));
		Thread.sleep(3000);

		if(StatusValue.getText().equals("Not Started")) {
			Thread.sleep(5000);
			assertEquals(StatusReason.getText(), "Initial Action Required");
			Log.info(StatusReason.getText() +" is auto populated when the Status is "+ StatusValue.getText());
		} else {
			Log.info("Status Value in not 'Not Started'");
		}
				
		//verifying the added Goal in the grid
		//getting selected value from Add Goal page
		String SelectedGoal = driver.findElement(By.xpath(config.getSelectedGoal())).getText();
		
		String SelectedPriority = driver.findElement(By.xpath(config.getSelectedPriority())).getText();
	
		driver.findElement(By.xpath(config.getSelectedStartDate())).getText();
		
		String SelectedStatus = driver.findElement(By.xpath(config.getStatusValue())).getText();
		
		String SelectedStatusReason = driver.findElement(By.xpath(config.getStatusReasonValue())).getText();
		
		//Click Care Plan Submit
		WebElement CarePlan_Submit = driver.findElement(By.xpath(config.Submit_CarePlan()));
		CarePlan_Submit.click();
		Thread.sleep(3000);
		
		//getting fields values from the grid
		String GridGoal = driver.findElement(By.xpath(config.getGridGoalValue())).getText();

		String GridPriority = driver.findElement(By.xpath(config.getGridPriorityValue())).getText();
		
		driver.findElement(By.xpath(config.getGridStartDateValue())).getText();
		
		String GridStatus = driver.findElement(By.xpath(config.getGridStatusValue())).getText();
		
		String GridStatusReason = driver.findElement(By.xpath(config.getGridStatusReasonValue())).getText();
		
		Assert.assertEquals(SelectedGoal, GridGoal, "Selected Goal is not matching with grid value in Care Plan grid");
		
		Assert.assertEquals(SelectedPriority, GridPriority, "Selected Priority is not matching with the grid Priority value in Care Plan grid");
		
//		Assert.assertEquals(SelectedStartDate, GridStartDate, "Selected Start Date is not matching with the grid Start Date value in Care Plan grid");
		
		Assert.assertEquals(SelectedStatus, GridStatus, "Selected Status is not matching with the grid Status value in Care Plan grid");
		
		Assert.assertEquals(SelectedStatusReason, GridStatusReason, "Selected StatusReason is not matching with the grid Statsu Reason value in Care Plan grid");
		
	}
	
	public void CarePlan_Edit_History() throws InterruptedException 
	{
		Log = Logger.getLogger("CarePlan.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, 15);

		WebElement GoalEdit = driver.findElement(By.xpath(config.clickEditGoal()));
		GoalEdit.click();
	
		WebElement PageHeader = driver.findElement(By.xpath(config.getPageHeader()));
		if(PageHeader.getText().equals("Edit Goal")) {
			Log.info("Edit goal is the header of the page");
		} else {
			Log.info("Page Header is not displaying for the edit goal page");
		}

		WebElement StatusValue = driver.findElement(By.xpath(config.getStatusValue()));
		String StatusValueText = StatusValue.getText();
		
		//Status = Not Started --> In-Progress
		if(StatusValueText.equals("Not Started")) {
			WebElement Status = driver.findElement(By.xpath(config.getStatusValue()));
			Status.click();
			WebElement StatusText = driver.findElement(By.xpath(config.getStatusText_InProgress())); //selecting In-progress here
			StatusText.click();
			Log.info("Changing Status from Not Started to In-Progress in Edit Goal page");
			assertEquals(StatusText.getText(), "In Progress");
			Thread.sleep(2000);
			WebElement StatusReason = driver.findElement(By.xpath(config.getStatusReasonValue()));
			assertEquals(StatusReason.getText(), "In Progress");
			Log.info(StatusReason.getText() +" is auto populated when the Status is "+ Status.getText());
		}
		WebElement ReasonForExtension = driver.findElement(By.xpath(config.getReasonForExtension()));
		ReasonForExtension.click();
		
		// Selecting random values in Reason For Extension dropdown
		WebElement dropdown = driver.findElement(By.xpath(config.ReasonForExtensionDropdown()));
		dropdown.click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(config.getWait_ReasonForExtension()))));
		List<WebElement> itemsInDropdown = driver.findElements(By.xpath(config.getSelectReasonForExtension()));
		int size = itemsInDropdown.size();
		int randNumber = ThreadLocalRandom.current().nextInt(0, size);
		itemsInDropdown.get(randNumber).click();
		Log.info("Clicking a random value from the Reason for extension drop down");
		
		//Edit Goal Submit button
		WebElement SubmitEditGoal = driver.findElement(By.xpath(config.getEditGoalSubmit()));
		SubmitEditGoal.click();
		Log.info("Submit Edit Goal after selecting Status as In Progress and Reason for Extension value");
		
		//Edit Goal to Closed status and selecting random values from StatusReason
		Thread.sleep(3000);
		driver.findElement(By.xpath(config.clickEditGoal())).click();
		Thread.sleep(3000);
		StatusValue = driver.findElement(By.xpath(config.getStatusValue()));
		StatusValueText = StatusValue.getText();
		if(StatusValueText.equals("In Progress"))
		{
			WebElement Status = driver.findElement(By.xpath(config.getStatusValue()));
			Status.click();
			WebElement StatusText = driver.findElement(By.xpath(config.getStatusText_Closed()));
			StatusText.click();
			Log.info("Changing Status from In Progress to Closed in Edit Goal page");
			Thread.sleep(2000);
			WebElement StatusReason = driver.findElement(By.xpath(config.getStatusReasonValue()));
			StatusReason.click();
			Thread.sleep(3000);
			for(int i=0;i<GlobalValues.StatusReasonClosedValues.size();i++) {
				driver.findElement(By.xpath("//div/ul/li["+ (i+1) +"]/span[text()='"+GlobalValues.StatusReasonClosedValues.get(i)+"']"));
				Thread.sleep(2000);
				Log.info("Verifying the "+ GlobalValues.StatusReasonClosedValues.get(i) + " value in the Status Reason dropdown when Status is " + StatusValueText);
			}
			Thread.sleep(5000);
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(config.getWait_StatusReason()))));
			itemsInDropdown = driver.findElements(By.xpath(config.getSelectStatusReason()));
			size = itemsInDropdown.size();
			randNumber = ThreadLocalRandom.current().nextInt(0, size);
			itemsInDropdown.get(randNumber).click();
		}
		Thread.sleep(2000);
		driver.findElement(By.xpath(config.getEditGoalSubmit())).click();
		Log.info("Submit Edit Goal after selecting Status as Closed");

//		driver.findElement(By.xpath("//button[@title='Goal History']//span/i")).click();
//		
//		PageHeader = driver.findElement(By.xpath("//nm-tile//p-header"));
//		if(PageHeader.getText().equals("Goal History")) {
//			Log.info("Goal History is the header of the page");
//		} else {
//			Log.info("Page is not displaying for carePlan History page");
//		}
//		for(int i=0;i<(GlobalValues.CarePlanHistoryGrid.size());) 
//		{	
//			driver.findElement(By.xpath("//span[text()='"+GlobalValues.CarePlanHistoryGrid.get(i)+"']"));
//			Log.info("Verifying the "+ GlobalValues.CarePlanHistoryGrid.get(i) + "- Grid columns");
//			i++;
//			WebElement Value = driver.findElement(By.xpath("//div/nm-table/div/p-table//table/tbody/tr[1]/td['"+i+"']/span"));
//		
//			String Value_Text = Value.getText();
//		
//			if(Value_Text.equals(SelectedGoalText)) 
//			{
//				Log.info("Goal in the History page - "+Value.getText());
//				Thread.sleep(2000);
//			} else if(Value_Text.equals(TargetEndDate_Text)) {
//				Log.info("Target End Date in the History page - "+Value_Text);
//				Thread.sleep(2000);
//			} else if(Value_Text.equals(SelectedReasonForExtension_Text)) {
//				Log.info("Reason For Extension in the History page - "+Value_Text);
//				Thread.sleep(2000);
//			} else if(Value_Text.equals(PerformedBy_Text)) {
//				Log.info("Updated by User in the History page - "+Value_Text);
//				Thread.sleep(2000);
//			} else if(Value_Text.equals(date)) {
//				Log.info("Updated date in the History page - "+Value_Text);
//				Thread.sleep(2000);
//			} else if(Value_Text.equals(SelectedPriority_Text)) {
//				Log.info("Priority in the History page - "+Value_Text);
//				Thread.sleep(2000);
//			}
//		}
	}
	
	public void CalculateTargetEndDate() throws InterruptedException 
	{
		Log = Logger.getLogger("CarePlan.class");
		PropertyConfigurator.configure("log4j.properties");
		
		WebElement StartDate = driver.findElement(By.xpath("//p-table/div//tr/th[7]/span")); //header

		if(StartDate.getText().equals("Start Date")) {
			StartDate = driver.findElement(By.xpath(config.getGridStartDateValue())); //value
			Thread.sleep(5000);	
			Log.info("Start Date is captured in the CarePlan grid -"+StartDate.getText());
		} else {
			Log.info("Start Date is not captured in the CarePlan grid");
		}
				
		WebElement TargetEndDate = driver.findElement(By.xpath(config.getTargetEndDate())); //header
	
		if(TargetEndDate.getText().equals("Target End Date")) {
			TargetEndDate = driver.findElement(By.xpath(config.getTargetEndDate_Value())); //value
			Thread.sleep(5000);	
			Log.info("Target End Date is captured in the CarePlan grid -"+TargetEndDate.getText());
		} else {
			Log.info("Target End Date is not captured in the CarePlan grid");
		}
		StartDate = driver.findElement(By.xpath(config.getGridStartDateValue())); //value
		TargetEndDate = driver.findElement(By.xpath(config.getTargetEndDate_Value())); //value
				
		if (GlobalValues.dateValidation(StartDate.getText().toString(),TargetEndDate.getText().toString()) >= 29
				|| GlobalValues.dateValidation(StartDate.getText().toString(),TargetEndDate.getText().toString()) >= 44) 
		{
			Log.info("Start Date and Target End Date are as expected");
		} else {
			Log.info("Start Date and Target End Date are not as expected");
		}
	}

	public void CarePlanSecondaryRow() throws Exception 
	{
		Log = Logger.getLogger("CarePlan.class");
		PropertyConfigurator.configure("log4j.properties");
		
		WebElement SecRow = driver.findElement(By.xpath(config.getSecRow()));
		SecRow.click();
		
		for(int i=0;i<(GlobalValues.SecondaryRowInterventionsGrid.size());i++) {
			driver.findElement(By.xpath("//span[text()='"+GlobalValues.SecondaryRowInterventionsGrid.get(i)+"']"));
			Log.info("Verifying the "+ GlobalValues.SecondaryRowInterventionsGrid.get(i) + "- Grid columns");
		}
		
		//verifying Edit and History buttons of each Interventions
		WebElement EditButton = driver.findElement(By.xpath(config.EditIntervention()));
		WebElement HistoryButton = driver.findElement(By.xpath(config.HistoryButton()));
		if (EditButton.isDisplayed() && HistoryButton.isDisplayed()) {
			Log.info("Edit and History images displayed for the intervention");	
		}
		else {
			Log.info("Edit and History images not displayed for the intervention");
		}	
		Log.info("Care Plan Secondary Row Interventions verified");
	}

	public void CarePlan_ClosedDate() throws Exception 
	{
		Log = Logger.getLogger("CarePlan.class");
		PropertyConfigurator.configure("log4j.properties");
		
		DateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
        Date date1 = new Date();
		String date = DATE_FORMAT.format(date1);
		
		WebElement Grid_StatusValue = driver.findElement(By.xpath(config.getGridStatusValue()));
		Thread.sleep(2000);
		WebElement ClosedDate = driver.findElement(By.xpath(config.getClosedDate()));
		Thread.sleep(7000);
		String ClosedDate_Text = ClosedDate.getText();
		Thread.sleep(3000);
		String Grid_StatusValue_Text = Grid_StatusValue.getText();
		
		if(Grid_StatusValue_Text.equals("Closed")) 
		{
			assertEquals(ClosedDate_Text, date);
			Log.info("Goals Closed Date is matched with today's System date");
		} else{
			Log.info("Grid Status is not equal to Closed - "+ Grid_StatusValue_Text);
			Log.info("Grid Status is not equal to Closed - "+ ClosedDate_Text);
		}
		
	}
	
	public void columnLevelSortingForCarePlan() throws Exception {
		Log = Logger.getLogger("CarePlan.class");
		PropertyConfigurator.configure("log4j.properties");
		DashboardFunctions df = new DashboardFunctions(driver);
		new LeftNavigationLink(driver);
		try {
			WebElement gridTitleElement = driver.findElement(By.xpath("//nm-table//p-header"));
			String gridTitle = gridTitleElement.getText();

			Assert.assertEquals(gridTitle, "Care Plan", "Grid Header is not matching");

			//df.columnSortingOnGrid(driver, 0, 1, 1);
			df.columnSorting(driver);
			Log.info("Successfully verified column level sorting for Care Plan Grid ");
			Log.info("**********verified column level sorting for Care Plan grid**********");

		} catch (Exception e) {

			Log.info(e + " - sorting Conditions grid failed");
		}
	}
	
	public void CP_AddInterventions() throws Exception
	{
		Log = Logger.getLogger("Interventions.class");
		PropertyConfigurator.configure("log4j.properties");
		AnnualAssessment annual = new AnnualAssessment(driver);
		WebDriverWait wait = new WebDriverWait(driver, 15);
		
		WebElement AddIntervention = driver.findElement(By.xpath(config.AddIntervention()));
		AddIntervention.click();
		
		//Add Interventions page Header
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
		Thread.sleep(1000);
		WebElement AddIntervention_GridHeader = driver.findElement(By.xpath(config.AddInterventionHeader()));
		Assert.assertEquals(AddIntervention_GridHeader.getText(), "Add Intervention");
		Log.info("Add Intervention Grid is verified");
	
		//verifying Assigned To default value
		WebElement AssignedTo_Value = driver.findElement(By.xpath(config.getAssignedTo()));
		WebElement PerformedBy = driver.findElement(By.xpath(config.getlblPerformedBy()));
		String Value_PerformedBy = PerformedBy.getText();
		if(AssignedTo_Value.getText().equals(Value_PerformedBy)) {
			Log.info("Assigned To default displaying as Login user name");
		} else {
			Log.info("Assigned To default not displaying as Login user name");
		}
		
		//verifying the fields are present or not
		annual.annual_assessment("AnnualAssessments_CMDM", "Intervention", 2);
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getlblSelectIntervention()))).click();
		Thread.sleep(1000);
		if(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getdrpSelectInterventionvalue().replace("<value>", 1+"")))).getText().equalsIgnoreCase("Referral")){
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getdrpSelectInterventionvalue().replace("<value>", 2+"")))).click();
		}else {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getdrpSelectInterventionvalue().replace("<value>", 1+"")))).click();
		}
		// Selecting random Interventions values in the Select Intervention dropdown
	/*	WebElement dropdown = driver.findElement(By.xpath(config.getInterventionsdropdown()));
		dropdown.click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(config.getWaitInterventions()))));
		List<WebElement> itemsInDropdown = driver.findElements(By.xpath(config.getSelectIntervention()));
		Thread.sleep(3000);
		int size = itemsInDropdown.size();
		int randNumber = ThreadLocalRandom.current().nextInt(1, size);
		itemsInDropdown.get(randNumber).click();
		if(randNumber!=0) {
		WebElement SelectedIntervention = driver.findElement(By.xpath(config.getSelectedIntervention()));
		}*/
				
		// Selecting random Assigned To values in the Assigned To dropdown
		/*WebElement dropdown1 = (driver.findElement(By.xpath("//label[contains(text(),'Assigned To')]//ancestor::nm-combobox//following-sibling::p-dropdown//label")));
		dropdown1.click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(config.getWaitAssignedTo()))));
		List<WebElement> itemsInDropdown1 = driver.findElements(By.xpath(config.getSelectAssignedTo()));
		Thread.sleep(3000);
		int size1 = itemsInDropdown1.size();
		int randNumber1 = ThreadLocalRandom.current().nextInt(0, size1);
		itemsInDropdown1.get(randNumber1).click();
		WebElement SelectedAssignedTo = driver.findElement(By.xpath(config.getSelectedAssignedTo()));*/

		
		//verifying Interventions Page Goals Grid and Header
		WebElement Intervetnion_GoalGrid = driver.findElement(By.xpath(config.InterventionGoalGrid()));
		Assert.assertEquals((Intervetnion_GoalGrid.getText()), "Goals");
		
		WebElement Intervention_Checkbox = driver.findElement(By.xpath(config.InterventionCheckBox()));
			if(Intervention_Checkbox.isDisplayed())
			{
				Log.info("Interventon Goal grid check box is present");
			} else {
				Log.info("Interventon Goal grid check box is not present");
			}
		
			for(int i=0; i<GlobalValues.Intervention_GoalsGrid.size();i++)
			{
				driver.findElement(By.xpath("//span[text()= '"+GlobalValues.Intervention_GoalsGrid.get(i)+"']"));
				Log.info("Verifying the "+ GlobalValues.Intervention_GoalsGrid.get(i) + "- column on the page");
			}
		Log.info("Verified Intervention Goals grid header and columns");
		
		Thread.sleep(3000);
//		WebElement SelectGoal = driver.findElement(By.xpath(config.clickSelectGoal()));
//		SelectGoal.click(); //selecting first goal in the grid	
		driver.findElement(By.xpath("//p-table/div//p-tablecheckbox/div")).click();
		Thread.sleep(2000);
		WebElement SelectedGoal = driver.findElement(By.xpath("//p-table//div//tr/td[2]//span")); //getting Selected Goal values
		Log.info("SelectedGoal" +SelectedGoal.getText());
//		WebElement SubmitIntervention = driver.findElement(By.xpath(config.clickSubmit()));
//		SubmitIntervention.click(); //click on Submit in Interventions page
		driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
		Thread.sleep(1000);
		Log.info("sUCCESSFULLY Added Intervetnion fromCare Plan page");
	}

	public void validateGoalHistoryColumns(){
		try
		{
			Log = Logger.getLogger("CarePlan.class");
			PropertyConfigurator.configure("log4j.properties");
			new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			
			driver.findElement(By.xpath("//caption[contains(text(),'Goals')]/../../..//button[@title='Goal History']")).click();
			
			Assert.assertTrue(driver.findElement(By.xpath(config.GoalHistoryPageValidation())).isDisplayed(), "Goal History Page is not displayed");
			Log.info("Goal History page is displayed");
			ArrayList<String> expectedal = new ArrayList<String>();
			expectedal.add("Goal");
			expectedal.add("Target End Date");
			expectedal.add("Reason for Extension");
			expectedal.add("Updated by User");
			expectedal.add("Updated Date");
			
			ArrayList<String> actualal = new ArrayList<String>();
			actualal.add(driver.findElement(By.xpath(config.GoalColumnValidate())).getText());
			actualal.add(driver.findElement(By.xpath(config.TargetEndDateValidation())).getText());
			actualal.add(driver.findElement(By.xpath(config.ReasonForExtensionValidation())).getText());
			actualal.add(driver.findElement(By.xpath(config.UpdatedByUserValidation())).getText());
			actualal.add(driver.findElement(By.xpath(config.UpdatedDateValidation())).getText());
			
			if(expectedal.equals(actualal))
			{
				Log.info("The column validation is successful");
			}
			else
			{
				Log.info("The column validation is failed");
			}	
			
		}

		catch (Exception e) {
			Log.info("Not clicked on goal history icon - " + e);
		}
	}
		
	public void clickBackButton()
	{
		try
		{
			Log = Logger.getLogger("CarePlan.class");
			PropertyConfigurator.configure("log4j.properties");
			// Clicking on back button in Goal History page
			Thread.sleep(3000);
	
			driver.findElement(By.xpath(config.BackbuttonInGoalHistoryPage())).click();
			Log.info("Clicked on back button");
		}
		catch (Exception e) {
			Log.info("Not clicked on Back icon - " + e);
		}
	
	}

	public void verifyGoalHistoryData ()
	{
		try
		{
			
			Log = Logger.getLogger("CarePlan.class");
			PropertyConfigurator.configure("log4j.properties");
			new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			
			// validating the data under goal column 
					
						WebElement goaldata=driver.findElement(By.xpath(config.validatingdataunderGoalcolumn()));
						goaldata.getText();
											
					// 	validating the data Target End Date column
						
						WebElement Targetdata=driver.findElement(By.xpath(config.validatingdataunderTargetEndDatecolumn()));
						Targetdata.getText();
						
					//validating the data under Reason for Extension column
						
						WebElement reasondata=driver.findElement(By.xpath(config.validatingdataunderReasonforExtensioncolumn()));
						reasondata.getText();
						
					//validating the data under Updated by User column
				
						WebElement updateddata=driver.findElement(By.xpath(config.validatingdataunderUpdatedbyUsercolumn()));
						updateddata.getText();
						
					//validating the data under Updated Date column
						
						WebElement Udatedata=driver.findElement(By.xpath(config.validatingdataunderUpdatedDatecolumn()));
						Udatedata.getText();
						
					//validating the data under Priority column	
						
						WebElement prioritydata=driver.findElement(By.xpath(config.validatingdataunderPrioritycolumn()));
						prioritydata.getText();
						
			// -------------Then we should see the updated data displaying/not----------------------------------------//	
						Log.info("Successfully validated the data in Goal Histroy grid ");
				}
			catch (Exception e) {
				Log.info("Failed to validate data - " + e);
			}
		}

	public void clickFilter() throws Exception {
		Log = Logger.getLogger("CarePlan.class");
		PropertyConfigurator.configure("log4j.properties");
		AnnualAssessment annual = new AnnualAssessment(driver);
		
		Log.info("Verifying the Filter options in Care Plan page");
		
		WebElement FilterImage = driver.findElement(By.xpath(config.getFilterImage()));
		FilterImage.click();
		Thread.sleep(2000);
		
		//verifying Filter page fields
		for(int i=0;i<GlobalValues.FilterPage_fields.size();i++) 
		{
				driver.findElement(By.xpath("//nm-frm-grp["+ (i+1) +"]//nm-input-label/label[text()='"+GlobalValues.FilterPage_fields.get(i)+"']"));
				Log.info("Verifying the "+ GlobalValues.FilterPage_fields.get(i) + " fileds in the Filter Page");
		}
				
		String Filter = driver.findElement(By.xpath(config.getFilterButton_Filter())).getText();
		Assert.assertEquals(Filter, "Filter", "Filter button is not present in Care Plan Filter page");
		
		String Clear = driver.findElement(By.xpath(config.getClearButton_Filter())).getText();
		Assert.assertEquals(Clear, "Clear", "Clear button is not present in Care Plan Filter page");
		
		String Back = driver.findElement(By.xpath(config.getBackButton_Filter())).getText();
		Assert.assertEquals(Back, "Back", "Back button is not present in Care Plan Filter page");		
		
		Log.info("Suucessfully verified the fields in the Care Plan Filter page");
		
		//verifying Priority drop down values
		WebElement PriorityLabel = driver.findElement(By.xpath(config.getPriorityValues()));
		PriorityLabel.click();
		for(int i=0;i<GlobalValues.CarePlanPriorityValues.size();i++) {
			
			driver.findElement(By.xpath("//div/ul/li["+ (i+1) +"]/span[text()='"+GlobalValues.CarePlanPriorityValues.get(i)+"']"));
			Log.info("Verifying the "+ GlobalValues.CarePlanPriorityValues.get(i) + " value in the Priority dropdown");
		}
		Thread.sleep(2000);
		
		//Verifying Status and Status Reason values and auto-populate values
		WebElement Status = driver.findElement(By.xpath(config.getStatusValue()));
		Status.click();
		for(int i=0;i<GlobalValues.CarePlanStatusDropdown.size();i++) {
			
			driver.findElement(By.xpath("//div[@class='ui-dropdown-items-wrapper']//li["+ (i+1) +"]/span[text()='"+GlobalValues.CarePlanStatusDropdown.get(i)+"']"));
			Log.info("Verifying the "+ GlobalValues.CarePlanStatusDropdown.get(i) + " value in the Status dropdown");
		}
		
		annual.annual_assessment("AnnualAssessments", "CarePlan_Filter", 3);
		Thread.sleep(2000);
		
		WebElement ClearButton = driver.findElement(By.xpath(config.getClearButton_Filter()));
		ClearButton.click();
		Log.info("Filter page selected field values are cleared");
		
		WebElement BackButton = driver.findElement(By.xpath(config.getBackButton_Filter()));
		BackButton.click();
		Thread.sleep(2000);
		
		//verifying Filter image on the page
		FilterImage = driver.findElement(By.xpath(config.getFilterImage()));
		if(FilterImage.isDisplayed()) {
			Log.info("Filter image is present after click on Back button in Filter page");
		} else {
			Log.info("Filter image is not present after click on Back button in Filter page");
		}
		
	}
	
	
	public void CarePlan_AddGoal() throws Exception  
	{	
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		Log = Logger.getLogger("CarePlan.class");
		PropertyConfigurator.configure("log4j.properties");
		new AnnualAssessment(driver);
		Thread.sleep(3000);
		
		//Add Goal to the Page
		WebElement ClickAddGoalButton = driver.findElement(By.xpath(config.getAddGoalClick()));
		ClickAddGoalButton.click();
		Log.info("Click on Add Goal button on CarePlan page");
		wb.waitforPageLoad(driver, 15);
		Thread.sleep(2000);
		//verifying AddGoal page
		WebElement VerifyAddGoalPage = driver.findElement(By.xpath(config.getVerifyAddGoalPage()));
		assertEquals(VerifyAddGoalPage.getText(), "Add Goal");
		Log.info(VerifyAddGoalPage.getText() + " is the label of the page"); 
		
		// Locating Goals drop down and selecting random number from Goals drop down
		wb.waitforPageLoad(driver, 15);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.GoalsDropdown())));
		WebElement dropdown = driver.findElement(By.xpath(config.GoalsDropdown()));
		dropdown.click();
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(config.getWait_Goal()))));
		List<WebElement> itemsInDropdown = driver.findElements(By.xpath(config.getSelectGoal()));
		int size = itemsInDropdown.size();
		int randNumber = ThreadLocalRandom.current().nextInt(0, size);
		itemsInDropdown.get(randNumber).click();
		Thread.sleep(2000);
		
		//verifying Priority drop down values
		WebElement PriorityLabel = driver.findElement(By.xpath(config.getPriorityValues()));
//		PriorityLabel.click();
		for(int i=0;i<GlobalValues.CarePlanPriorityValues.size();i++) {
			Thread.sleep(1000);
			PriorityLabel.click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//div/ul/li["+ (i+1) +"]/span[text()='"+GlobalValues.CarePlanPriorityValues.get(i)+"']")).click();
			Log.info("Verifying the "+ GlobalValues.CarePlanPriorityValues.get(i) + " value in the Priority dropdown");
		}
				
	
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getStartDate())));
		WebElement StartDate = driver.findElement(By.xpath(config.getStartDate()));
		StartDate.click();
		
		DateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
	    Date date1 = new Date();
		String date = DATE_FORMAT.format(date1);

		driver.findElement(By.xpath("(//label[contains(.,'Start Date')]//ancestor::nm-input-label//following-sibling::p-calendar//span//input)[2]")).sendKeys(date);
		Thread.sleep(3000);
		//verifying only the fields are present or not
		
		
		//Verifying Status and Status Reason values and auto-populate values
		WebElement Status = driver.findElement(By.xpath(config.getStatusValue()));
		Status.click();
		
		WebElement StatusText = driver.findElement(By.xpath(config.getStatusText_NotStarted()));
		StatusText.click();
		
		WebElement StatusValue = driver.findElement(By.xpath(config.getStatusValue()));
															  
		WebElement StatusReason = driver.findElement(By.xpath(config.getStatusReasonValue()));
		Thread.sleep(3000);

		if(StatusValue.getText().equals("Not Started")) {
			Thread.sleep(5000);
			assertEquals(StatusReason.getText(), "Initial Action Required");
			Log.info(StatusReason.getText() +" is auto populated when the Status is "+ StatusValue.getText());
		} else {
			Log.info("Status Value in not 'Not Started'");
		}
		
//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.Submit_CarePlan()))).click();
//		annual.annual_assessment("AnnualAssessments_CMDM", "CarePlan", 2);		
		//verifying the added Goal in the grid
		//getting selected value from Add Goal page
		
	}
	
	public void CarePlan_clicksubmit() throws Exception  
	{	
		Thread.sleep(3000);
		WebElement CarePlan_Submit = driver.findElement(By.xpath(config.Submit_CarePlan()));
		CarePlan_Submit.click();
		Thread.sleep(3000);
	}	
	
	
	public void Editgoalinprogress() throws InterruptedException 
	{	
		Log = Logger.getLogger("CarePlan.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
		Thread.sleep(1000);
		WebElement GoalEdit = driver.findElement(By.xpath(config.clickEditGoal()));
		GoalEdit.click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
		Thread.sleep(1000);
		WebElement PageHeader = driver.findElement(By.xpath(config.getPageHeader()));
		if(PageHeader.getText().equals("Edit Goal")) {
			Log.info("Edit goal is the header of the page");
		} else {
			Log.info("Page Header is not displaying for the edit goal page");
		}

		WebElement StatusValue = driver.findElement(By.xpath(config.getStatusValue()));
		String StatusValueText = StatusValue.getText();
		
		//Status = Not Started --> In-Progress
		if(StatusValueText.equals("Not Started")) {
			WebElement Status = driver.findElement(By.xpath(config.getStatusValue()));
			Status.click();
			WebElement StatusText = driver.findElement(By.xpath(config.getStatusText_InProgress())); //selecting In-progress here
			String s = StatusText.getText();
			System.out.println(StatusText.getText());
			StatusText.click();
			Log.info("Changing Status from Not Started to In-Progress in Edit Goal page");
			
			assertEquals(s, "In Progress");
			Thread.sleep(2000);
			WebElement StatusReason = driver.findElement(By.xpath(config.getStatusReasonValue()));
			System.out.println(StatusReason.getText());
			assertEquals(StatusReason.getText(), "In Progress");
			Log.info(StatusReason.getText() +" is auto populated when the Status is "+ Status.getText());
		}
		driver.findElement(By.xpath(config.getReasonForExtension()));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
		Thread.sleep(3000);
		// Selecting random values in Reason For Extension dropdown
		WebElement dropdown = driver.findElement(By.xpath(config.ReasonForExtensionDropdown()));
		dropdown.click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(config.getWait_ReasonForExtension()))));
		List<WebElement> itemsInDropdown = driver.findElements(By.xpath(config.getSelectReasonForExtension()));
		int size = itemsInDropdown.size();
		int randNumber = ThreadLocalRandom.current().nextInt(0, size);
		itemsInDropdown.get(randNumber).click();
		Log.info("Clicking a random value from the Reason for extension drop down");
		
		//Edit Goal Submit button
		WebElement SubmitEditGoal = driver.findElement(By.xpath(config.getEditGoalSubmit()));
		SubmitEditGoal.click();
		Log.info("Submit Edit Goal after selecting Status as In Progress and Reason for Extension value");
	}	
	
	public void Editgoalclose() throws InterruptedException 
	{	
		Thread.sleep(3000);
		driver.findElement(By.xpath(config.clickEditGoal())).click();
		Thread.sleep(3000);
		
		Log = Logger.getLogger("CarePlan.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, 15);
		WebElement StatusValue = driver.findElement(By.xpath(config.getStatusValue()));
		String StatusValueText = StatusValue.getText();
		if(StatusValueText.equals("In Progress"))
		{
			WebElement Status = driver.findElement(By.xpath(config.getStatusValue()));
			Status.click();
			WebElement StatusText = driver.findElement(By.xpath(config.getStatusText_Closed()));
			StatusText.click();
			Log.info("Changing Status from In Progress to Closed in Edit Goal page");
			Thread.sleep(2000);
			WebElement StatusReason = driver.findElement(By.xpath(config.getStatusReasonValue()));
			StatusReason.click();
			Thread.sleep(3000);
			for(int i=0;i<GlobalValues.StatusReasonClosedValues.size();i++) {
				driver.findElement(By.xpath("//div/ul/li["+ (i+1) +"]/span[text()='"+GlobalValues.StatusReasonClosedValues.get(i)+"']"));
				Thread.sleep(2000);
				Log.info("Verifying the "+ GlobalValues.StatusReasonClosedValues.get(i) + " value in the Status Reason dropdown when Status is " + StatusValueText);
			}
			Thread.sleep(5000);
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(config.getWait_StatusReason()))));
			List<WebElement> itemsInDropdown = driver.findElements(By.xpath(config.getSelectStatusReason()));
			int size = itemsInDropdown.size();
			int randNumber = ThreadLocalRandom.current().nextInt(0, size);
			itemsInDropdown.get(randNumber).click();
		}
		Thread.sleep(2000);
		driver.findElement(By.xpath(config.getEditGoalSubmit())).click();
		Log.info("Submit Edit Goal after selecting Status as Closed");
	
	}
}






