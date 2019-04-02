package com.nimbus.pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utility.ConfigReader;

public class Interventions {

	WebDriver driver;
	Logger Log;
	
	ConfigReader config = new ConfigReader();

	public Interventions(WebDriver ldriver) {
		this.driver = ldriver;
	}

	public Interventions(Logger lLog) {
		this.Log = lLog;
	}
	
	public void Interventions_VerifyGrid() throws InterruptedException 
	{
//		try 	{
		Log = Logger.getLogger("Interventions.class");
		PropertyConfigurator.configure("log4j.properties");
	
		//Verify Grid header
		WebElement gridHeader = driver.findElement(By.xpath(config.getHeader()));
		Assert.assertEquals((gridHeader.getText()), GlobalValues.interventions);
			
		WebElement IntBreadCrumb = driver.findElement(By.xpath(config.IntBreadCrumb()));
		Assert.assertEquals(IntBreadCrumb.getText(), "Interventions", "Bread Crumb of the Interventions is not showing");
		Log.info("Verified the Interventions Bread Crumb");
		
		WebElement ShowingValue = driver.findElement(By.xpath(config.getShowingValue()));
		Log.info("Interventions Grid - Showing values displaying as : " + ShowingValue.getText());
		
		//verifying Interventions Page Grid
			for(int i=0; i<GlobalValues.InterventionsGrid.size();i++)
			{
				driver.findElement(By.xpath("//span[text()= '"+GlobalValues.InterventionsGrid.get(i)+"']"));
				Log.info("Verifying the "+ GlobalValues.InterventionsGrid.get(i) + "- column on the page");
				Thread.sleep(3000);
			}
//		}
//		catch (Exception e)
//		{
//			Log.info("Failed - " + e);
//		}
	}
	
	public void AddInterventions_Verify() throws Exception
	{
		Log = Logger.getLogger("Interventions.class");
		PropertyConfigurator.configure("log4j.properties");
		AnnualAssessment annual = new AnnualAssessment(driver);
		WebDriverWait wait = new WebDriverWait(driver, 15);
		
		WebElement AddIntervention = driver.findElement(By.xpath(config.AddIntervention()));
		AddIntervention.click();
		
		//Add Interventions page Header
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
		annual.annual_assessment("AnnualAssessments", "Intervention", 2);
		
		// Selecting random Interventions values in the Select Intervention dropdown
		WebElement dropdown = driver.findElement(By.xpath(config.getInterventionsdropdown()));
		dropdown.click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(config.getWaitInterventions()))));
		List<WebElement> itemsInDropdown = driver.findElements(By.xpath(config.getSelectIntervention()));
		Thread.sleep(3000);
		int size = itemsInDropdown.size();
		int randNumber = ThreadLocalRandom.current().nextInt(1, size);
		itemsInDropdown.get(randNumber).click();
	
		if(randNumber!=0) {
		WebElement SelectedIntervention = driver.findElement(By.xpath(config.getSelectedIntervention()));
	
		} 
				
		// Selecting random Assigned To values in the Assigned To dropdown
		WebElement dropdown1 = (driver.findElement(By.xpath("//label[contains(text(),'Assigned To')]//ancestor::nm-combobox//following-sibling::p-dropdown//label")));
		dropdown1.click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(config.getWaitAssignedTo()))));
		List<WebElement> itemsInDropdown1 = driver.findElements(By.xpath(config.getSelectAssignedTo()));
		Thread.sleep(3000);
		int size1 = itemsInDropdown1.size();
		int randNumber1 = ThreadLocalRandom.current().nextInt(0, size1);
		itemsInDropdown1.get(randNumber1).click();
		WebElement SelectedAssignedTo = driver.findElement(By.xpath(config.getSelectedAssignedTo()));
		
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
		
		WebElement SelectedGoal = driver.findElement(By.xpath("//p-table//div//tr/td[2]//span")); //getting Selected Goal values
		
//		WebElement SubmitIntervention = driver.findElement(By.xpath(config.clickSubmit()));
//		SubmitIntervention.click(); //click on Submit in Interventions page
		driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();

		Thread.sleep(7000);
		WebElement Intervention = driver.findElement(By.xpath("//p-table//div//tr/td[1]//span"));
		String Intervention_Text = Intervention.getText();
		WebElement AssignedTo = driver.findElement(By.xpath("//p-table//div//tr/td[3]//span"));
		WebElement Goal = driver.findElement(By.xpath("//p-table//div//tr/td[2]//span"));
		Thread.sleep(7000);
		
/*		if(SelectedIntervention.getText().equals(Intervention.getText())) {
			Log.info("Selected Intervention,Goal and AssignedTo is displaying in the Interventions page grid");
		} else {
			Log.info("Selected Intervention,Goal and AssignedTo is not displaying in the Interventions page grid");
		}
		if( SelectedAssignedTo.getText().equals(AssignedTo.getText())) {
			System.out.println("ABC");
		}
		if(SelectedGoal.getText().equals(Goal.getText())) {
			System.out.println("123");
		}*/
	
		List<WebElement> RowsCount = driver.findElements(By.tagName("tr")); 
		
		//getting the values of the interventions grids values
		for(int j=1;j<(RowsCount.size());j++) 
		{
			WebElement InterventionName = driver.findElement(By.xpath("//p-table//div//following-sibling::tr[" + j + "]"));
			String InterventionNameText = InterventionName.getText();
			Thread.sleep(5000);
			if(!Intervention_Text.isEmpty()) 
			{

			//	assertEquals(SelectedIntervention.getText(), Intervention_Text);
				for(int i=1;i<=(GlobalValues.Intervention_GoalsGrid.size());i++) {
					WebElement GridRowsColumns = driver.findElement(By.xpath("//p-table//div//following-sibling::tr[" + j + "]/td[" + i + "]"));
					Log.info("Grid rows and columns verified - " +  GridRowsColumns.getText());	
					Thread.sleep(3000);
				}
			} else {
					Log.info("Cannot Verifed the GRid Count");
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
		}
		
		//calculating the count of interventions in the grid
//		List<WebElement> GridRowsColumns = driver.findElements(By.xpath(config.GridCount()));
//		wait.until(ExpectedConditions.visibilityOfAllElements(GridRowsColumns));
		
		/*if((GridRowsColumns.size()) != 0) {
			int i= GridRowsColumns.size();
			WebElement GridCount = driver.findElement(By.xpath("//div/div['Showing 1-"+ i +" of "+ i +"']"));
			GridCount.getText();
	
			WebElement Count = driver.findElement(By.xpath("//*[@id='gsFlexGrid']/div/div[text()]"));
			System.out.println(Count.getText());
			Assert.assertEquals( GridRowsColumns.size(),Count.getText());
		} else {
			System.out.println("error");
		}*/
	}
	
	public void EditIntervetnion() throws Exception {
		Log = Logger.getLogger("Interventions.class");
		PropertyConfigurator.configure("log4j.properties");		
		
		//Edit Intervention page
		WebElement EditButton = driver.findElement(By.xpath("//button[@title='Edit Intervention']"));
		EditButton.click();
		
		WebElement PageHeader = driver.findElement(By.xpath("//nm-label/h2"));
		if(PageHeader.getText().equals("Edit Intervention")) {
			Log.info("Edit Intervention is the header of the page");
		} else {
			Log.info("Edit Intervention is not the header of the page");
		}

		ArrayList<String> expectedal = new ArrayList<String>();
		expectedal.add("Associated Goal");
		expectedal.add("Intervention");
		expectedal.add("Assigned To");
		expectedal.add("Status");
		expectedal.add("Status Reason");
		
		ArrayList<String> actualal = new ArrayList<String>();
		actualal.add(driver.findElement(By.xpath(config.ValidatingAssociatedGoal())).getText());
		actualal.add(driver.findElement(By.xpath(config.ValidatingIntervention())).getText());
		actualal.add(driver.findElement(By.xpath(config.ValidatingAssignedTo())).getText());
		actualal.add(driver.findElement(By.xpath(config.ValidatingStatus())).getText());
		actualal.add(driver.findElement(By.xpath(config.ValidatingStatusReason())).getText());
		
		if(expectedal.equals(actualal))
		{
			Log.info("The column validation is successful");
		}
		else
		{
			Log.info("The column validation is failed");
		}

		Thread.sleep(5000);
		
		driver.findElement(By.xpath("//button[text()='Back']")).click(); //Clicking Back button
		
		//History Intervention
		WebElement HistoryButton = driver.findElement(By.xpath("//button[@title='Intervention history']"));
		HistoryButton.click();
		
		PageHeader = driver.findElement(By.xpath("//nm-table//p-header"));
		if(PageHeader.getText().equals("Intervention History")) 
		{
			Log.info("Intervention History is the header of the page");
		} else {
			Log.info("Intervention History is not the header of the page");
		}
		for(int i=0; i<GlobalValues.HistoryIntervention.size();i++)
		{
				driver.findElement(By.xpath("//span[text()='"+GlobalValues.HistoryIntervention.get(i)+"']"));
				Log.info("Verifying the "+ GlobalValues.HistoryIntervention.get(i) + "- grid columns in Intervention History page");
		}
		
		driver.findElement(By.xpath("//button[text()='Back']")).click(); //Clicking Back button
		
	}
	
	public void EditIntervention_UpdatingData() throws InterruptedException{	
		Log = Logger.getLogger("Interventions.class");
		PropertyConfigurator.configure("log4j.properties");
		
		 //Clicking on Interventions edit link
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		WebElement Intervention_EL =driver.findElement(By.xpath("//button[@title='Edit Intervention']"));
		Intervention_EL.click();
		
		// Clicked on Assigned to dropdown
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//label[contains(text(),'Assigned To')]//ancestor::nm-combobox//following-sibling::p-dropdown//label")).click();
		Log.info("Clicked on Assigned to dropdown");
			
		// Selecting Manager(snow white) in order to submit				
		Thread.sleep(2000);
		driver.findElement(By.xpath(config.SelectingSnowWhiteInAssignedToDropdown())).click();
		Log.info("Selected Snow White in Assigned to dropdown");
			
		// Clicking on status dropdown
		Thread.sleep(5000);
		WebElement StatusDrpd =driver.findElement(By.xpath(config.Statusdropdown()));
		StatusDrpd.click();
		Log.info("Clicked on Status dropdown ");
							
		//Selecting not started in status dropdown
		Thread.sleep(5000);
		driver.findElement(By.xpath(config.SelectingNotStartedInStatusDropdown())).click();
		Log.info("Selecting Not Started In Status Dropdown ");
			
		//Validating the status reason is pre-populated/not.			
		Thread.sleep(5000);
        WebElement ValidateStReason =driver.findElement(By.xpath(config.ValidatingStatusReasonWhenStatusIsNotStartedInEditInter()));
	    
	    //Validating cancel button			    
		Thread.sleep(2000);
		String Back = driver.findElement(By.xpath("//button[text()='Back']")).getText();
		Assert.assertEquals(Back, "Back", "Back button is not present in Edit Intervention page");
		Log.info("BackButton is presented in the page");
					
		// Click on submit button				
		Thread.sleep(5000);
		driver.findElement(By.xpath(config.SubmitButton())).click();
		Log.info("Clicked on SubmitButton ");

}
		
	public void EditIntervention_ValidatingStatusandReason() throws InterruptedException 
	{
		
		Log = Logger.getLogger("Interventions.class");
		PropertyConfigurator.configure("log4j.properties");
		
	 //Clicking on Interventions edit link
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		WebElement Intervention_EL =driver.findElement(By.xpath("//button[@title='Edit Intervention']"));
		Intervention_EL.click();
	
		//-------Validating and clicking on  status dropdown -------------//
		
		Thread.sleep(5000);
		WebElement StatusDrpd =driver.findElement(By.xpath(config.Statusdropdown()));
		StatusDrpd.click();
		Log.info("Clicked on Status dropdown ");	
			
		//----Selecting not started in status dropdown 
		
		Thread.sleep(5000);
		driver.findElement(By.xpath(config.SelectingNotStartedInStatusDropdown())).click();
		Log.info("Selecting Not Started In Status Dropdown ");
				
				
		//---validating status reason-------//
		
		Thread.sleep(5000);
		WebElement ValidateStReason =driver.findElement(By.xpath(config.ValidatingStatusReasonWhenStatusIsNotStartedInEditInter()));
		Assert.assertEquals(ValidateStReason.getText(), "Initial Action Required", "Status Reason not in Not Started status when Status is in Not Started status");
	
		//-------clicking on status dropdown -------------//
		
		Thread.sleep(5000);
		driver.findElement(By.xpath(config.Statusdropdown())).click();
		Log.info("Clicked on Status dropdown ");
			
		//----Selecting In Progress in status dropdown------//
	
		Thread.sleep(5000);
		driver.findElement(By.xpath(config.SelectingInProgressInStatusDropdown())).click();
		Log.info("Selected InProgress In Status Dropdown ");
		
		// ---------validating status reason-------//
    
		Thread.sleep(5000);
		String SRText = driver.findElement(By.xpath(config.ValidateTextInStatusReason())).getText();
		Assert.assertEquals(SRText, "In Progress", "Status Reason not in In-progress status when Status is in In-progress status");
	
		//-------clicking on  status dropdown -------------//
	
		Thread.sleep(5000);
		driver.findElement(By.xpath(config.Statusdropdown())).click();
		Log.info("Clicked on Status dropdown ");
		
		//----------Selecting Closed in status dropdown------------------//
		
		Thread.sleep(5000);
		driver.findElement(By.xpath(config.SelectingClosedInStatusDropdown())).click();
		Log.info("Selected Closed In Status Dropdown ");
		
		//----------------Clicking on status reason--------------------//	
		Thread.sleep(5000);
		driver.findElement(By.xpath(config.ValidateTextInStatusReason())).click();
      
	    // validating the dropdown values when staus is closed
	    
	    ArrayList<String> expectedal1 = new ArrayList<String>();
		expectedal1.add("Met");
		expectedal1.add("Partially Met");
		expectedal1.add("Not Clinically Appropriate");
		expectedal1.add("Not Compliant");
		expectedal1.add("Progress Plateau");
		
		ArrayList<String> actualal1 = new ArrayList<String>();
	    actualal1.add(driver.findElement(By.xpath(config.MetInStatusReasonDrp())).getText());
	    actualal1.add(driver.findElement(By.xpath(config.PartiallyMetInStatusReasonDrp())).getText());
	    actualal1.add(driver.findElement(By.xpath(config.NotClinicallyAppropriateInStatusReasonDrp())).getText());
	    actualal1.add(driver.findElement(By.xpath(config.NotCompliantInStatusReasonDrp())).getText());
	    actualal1.add(driver.findElement(By.xpath(config.ProgressPlateauInStatusReasonDrp())).getText());
		
		if(expectedal1.equals(actualal1))
		{
			Log.info("The column validation is successful");
		}
		else
		{
			Log.info("The column validation is failed");
		}	
		
		/*//----------------Clicking on status reason--------------------//	
	    Thread.sleep(5000);
	    driver.findElement(By.xpath(config.ValidateTextInStatusReason())).click();*/
	    
	    // Selecting Met in status reason dropdown
	    Thread.sleep(5000);
	    driver.findElement(By.xpath(config.MetInStatusReasonDrp())).click();
	    Log.info("Clicked on Met");
	    
		// Submit button
	    driver.findElement(By.xpath(config.SubmitButton())).click();
	    Log.info("Clicked on submit button");
	
	}	
	
	public void clickonInterventionsHistory() throws InterruptedException
	{
	    //Clicking on Interventions history link
		Log = Logger.getLogger("Interventions.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		WebElement Intervention_HI =driver.findElement(By.xpath(config.InterventionHistoryLink()));
		Intervention_HI.click();
      
	}

	public void EditInterventionLink() throws InterruptedException
	{
	
    //Clicking on Interventions history link
		Log = Logger.getLogger("Interventions.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		WebElement Intervention_EL =driver.findElement(By.xpath(config.EditInterventionLink()));
		Intervention_EL.click();
	}
	
	public void ValidatingInterventionHistoryGrid() {
		
		Log = Logger.getLogger("Interventions.class");
		PropertyConfigurator.configure("log4j.properties");
		
		 //Clicking on Interventions history link
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		WebElement Intervention_HI =driver.findElement(By.xpath("//p-table//div//tr/td[9]"));
		Intervention_HI.click();
		
		// Validating the InterventionHistory Grid columns 
		
			ArrayList<String> expectedal = new ArrayList<String>();
			expectedal.add("Interventions");
			expectedal.add("Assigned To");
			expectedal.add("Status");
			expectedal.add("Status Reason");
			expectedal.add("Updated by User");
			expectedal.add("Updated Date");
			
			ArrayList<String> actualal = new ArrayList<String>();
			
			actualal.add(driver.findElement(By.xpath(config.InterventionsValidationInGrid())).getText());
			actualal.add(driver.findElement(By.xpath(config.AssignedToValidationInGrid())).getText());
			actualal.add(driver.findElement(By.xpath(config.StatusValidationInGrid())).getText());
			actualal.add(driver.findElement(By.xpath(config.StatusReasonValidationInGrid())).getText());
			actualal.add(driver.findElement(By.xpath(config.UpdatedByUserValidationInGrid())).getText());
			actualal.add(driver.findElement(By.xpath(config.UpdatedDateValidationInGrid())).getText());
			
			if(expectedal.equals(actualal))
		     {
			Log.info("The column validation is successful");
			}
			else
			{
				Log.info("The column validation is failed");
			}
			
	}

	public void ValidatingGridData_IntervetnionHistoryPage() {
		try {
				Log = Logger.getLogger("Interventions.class");
				PropertyConfigurator.configure("log4j.properties");
				
				WebElement ShowingValue = driver.findElement(By.xpath(config.getShowingValue()));
				Log.info("Intervention Histroy Grid - Showing values displaying as : " + ShowingValue.getText());
						
		// Validating Intervention Text 
			Thread.sleep(8000);
			driver.findElement(By.xpath(config.InterventionTextGoalHi())).getText();
	
		//validating Assigned to text
			driver.findElement(By.xpath(config.AssignedToTextGoalHi())).getText();	
				
		// validating status text
			driver.findElement(By.xpath(config.StatusTextGoalHi())).getText();
				
				
		// validating status reason text
			driver.findElement(By.xpath(config.StatusReasonTextGoalHi())).getText();
			
		// Validating Updated By User text
			driver.findElement(By.xpath(config.UpdatedByUserTextGoalHi())).getText();

		// Validating UpdateDate date text
			driver.findElement(By.xpath(config.UpdatedDateGoalHi())).getText();

			Log.info("Validated the Intervention History Grid value updating");
		}
		catch(Exception e)
		{
			Log.info("Element is not in the page");
		}

	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
