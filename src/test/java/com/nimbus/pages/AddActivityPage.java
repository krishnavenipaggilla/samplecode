package com.nimbus.pages;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utility.ConfigReader;

public class AddActivityPage {

	ConfigReader config = new ConfigReader();

	WebDriver driver;
	Logger Log;

	public AddActivityPage(WebDriver ldriver) {
		this.driver = ldriver;
	}

	public AddActivityPage(Logger lLog) {
		this.Log = lLog;
	}

	public void CreatingNewActivity() throws Exception{
		WebDriverWait wait =new WebDriverWait(driver, 20);	
		try
		{
			Log = Logger.getLogger("AddActivityPage.class");
			PropertyConfigurator.configure("log4j.properties");
			
			AnnualAssessment AnnualAssessment= PageFactory.initElements(driver, AnnualAssessment.class);
			AnnualAssessment.annual_assessment("AF86325","NewActivity",2);
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.SubmitButton1()))).click();
			Log.info("User Clicked on Submit button");
			
			// for the Submit and Add new Button 
	       /*wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.SubmitANDAddNewButton()))).click();
			//Log.info("User Clicked on Submit And Add new button");*/
			
		}
		
		catch(Exception e)
			{
			System.out.println( " : " +e);
			}
		
	}
	// Validating the Submit and Add new buttons
	
	public void SubmitAndAddNewButtons(){
		Log = Logger.getLogger("AddActivityPage.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait =new WebDriverWait(driver, 20);	
		try
		{
		Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.SubmitButton1()))).isDisplayed(), "Submit button is validated");	
		//Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.SubmitANDAddNewButton()))).isDisplayed(), "Submit and AddNew Button is validted");
		
		// Submit button
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.SubmitButton1()))).click();
		Log.info("User Clicked on Submit button");
		
		// Submit and Add new Button 
       /*wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.SubmitANDAddNewButton()))).click();
		//Log.info("User Clicked on Submit And Add new button");*/
		
		}
		
		catch(Exception e)
			{
			System.out.println( " : " +e);
			}
		
	}

// validating Grid	
	public void InterventionsGridValidation() throws Exception{
		Log = Logger.getLogger("AddActivityPage.class");
		PropertyConfigurator.configure("log4j.properties"); 
		WebDriverWait wait =new WebDriverWait(driver, 20);
		try{
		String ExpectedLabel= "Interventions";
		String ActualLabel=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.InterventionsGridLabel()))).getText();
		Assert.assertEquals(ActualLabel,ExpectedLabel,"Interventions grid Validation is succesful");
		
			ArrayList<String> expectedal1 = new ArrayList<String>();
            expectedal1.add("Intervention Description");
            expectedal1.add("Linked Goals");
            expectedal1.add("Status");
            expectedal1.add("Status Reason");
            expectedal1.add("Completed Date");
            
			ArrayList<String> actualal1 = new ArrayList<String>();
			actualal1.add(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.InterventionDescription1()))).getText());
			actualal1.add(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.LinkedGoalsInGrid()))).getText());
			actualal1.add(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.StatusInGrid()))).getText());
			actualal1.add(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.StatusReasonInGrid()))).getText());
			actualal1.add(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.CompletedDateInGrid()))).getText());
			
			System.out.println(expectedal1);
			System.out.println(actualal1);
			
			if(expectedal1.equals(actualal1))
			{
				Log.info("The Columns validation is successful");
			}
			else
			{
				Log.info("The Columns validation is failed");
			}	
		// validating InProgress interventions are displaying in the grid/not(Created intervention before perming the action)
		//System.out.println(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.ValidatingStatusText()))).getText());
			//Log.info("Validated the Status column value");
			
      // Validating Edit Icon in on the way to the right (Getting console errors)
			//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.ValidatingStatusText()))).click();	
			//Log.info("Validated the edit intervention button");
		}
		catch(Exception e)
		{
		System.out.println( " : " +e);
		}
			
		}
   
	
//	Validate 'Questionnaires' grid label and columns
	public void QuestionnairesGridValidation() throws Exception{
		
		try
		{
			Log = Logger.getLogger("AddActivityPage.class");
			PropertyConfigurator.configure("log4j.properties");
			
			WebDriverWait wait =new WebDriverWait(driver, 20);
			
			ArrayList<String> expectedal = new ArrayList<String>();
            expectedal.add("Questionnaires");
			ArrayList<String> actualal = new ArrayList<String>();
			actualal.add(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.QuestionnairesGridLabel()))).getText());
			System.out.println(expectedal);
			System.out.println(actualal);
			
			if(expectedal.equals(actualal))
			{
				Log.info("The Label validation is successful");
			}
			else
			{
				Log.info("The Label validation is failed");
			}
			
			ArrayList<String> expectedal1 = new ArrayList<String>();
            expectedal1.add("Name");
            expectedal1.add("Status");
            expectedal1.add("Last Updated Date");
            expectedal1.add("Last Updated By");
            
            
			ArrayList<String> actualal1 = new ArrayList<String>();
			actualal1.add(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.NameColumn()))).getText());
			actualal1.add(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.StatusColumn()))).getText());
			actualal1.add(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.LastUpdatedDateColumn()))).getText());
			actualal1.add(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.LastUpdatedByColumn()))).getText());
			
			System.out.println(expectedal1);
			System.out.println(actualal1);
			
			if(expectedal1.equals(actualal1))
			{
				Log.info("The Columns validation is successful");
			}
			else
			{
				Log.info("The Columns validation is failed");
			}	
			
			// validating Edit Intervention icon
	
}
		catch(Exception e)
		{
		System.out.println( " : " +e);
		}
	}		


public void SortingInterventionsGrid() throws Exception {
	Log = Logger.getLogger("ActivityPage.class");
	PropertyConfigurator.configure("log4j.properties");
	DashboardFunctions df=PageFactory.initElements(driver, DashboardFunctions.class);
	
	try {
		WebElement gridTitleElement = driver.findElement(By.xpath(config.InterventionsGridLabel()));
		String gridTitle = gridTitleElement.getText();
		Log.info(gridTitle);
		Assert.assertEquals(gridTitle, "Interventions", "Grid Header is not matching");

		//df.columnSortingOnGrid(driver, 0, 1, 1);
		df.columnSorting(driver);
		Log.info("Successfully verified column level sorting for Conditions Grid ");
		Log.info("**********verified column level sorting for Conditions grid**********");

	} catch (Exception e) {

		Log.info(e + " - sorting Conditions grid failed");
	}

	
}
}
