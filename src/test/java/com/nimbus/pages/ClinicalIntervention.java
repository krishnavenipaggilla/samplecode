package com.nimbus.pages;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utility.ConfigReader;

public class ClinicalIntervention {
		WebDriver driver;
		Logger Log;
		
		ConfigReader config = new ConfigReader();

		public ClinicalIntervention(WebDriver ldriver) {
			this.driver = ldriver;
		}

		public ClinicalIntervention(Logger lLog) {
			this.Log = lLog;
		}
	
public void IntroAndPermissions() throws Exception {
	
	try{
		Log = Logger.getLogger("ClinicalIntervention.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		
	     wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(config.IntroAndPermissionsExpandButton())))).click();
	  Log.info("Clicked on Intro&Permissions Expand button");
	  AnnualAssessment Annual_Assessment=PageFactory.initElements(driver, AnnualAssessment.class);
	Annual_Assessment.annual_assessment("AF86325","Intro&Permissions",5);
	Log.info("User successfully answered the Q&A");
	
	// Submit button
	wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(config.SaveProgressButton())))).click();
	Log.info("User clicked on submit progress button");
	
	// Clicking OK for the Alert message
	
	wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(config.HandlingAlertMessageUponSubmission())))).click();
	Log.info("User clicked ok for the alert box");
	
	
}

catch(Exception e)
{
System.out.println( " : " +e);
}

}
public void InterventionsAndQuestionnairesGridValidation(){
	Log = Logger.getLogger("ClinicalIntervention.class");
	PropertyConfigurator.configure("log4j.properties");
	WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
	ArrayList<String> expectedal = new ArrayList<String>();
	expectedal.add("Questionnaires");
	expectedal.add("Interventions");
	ArrayList<String> actualal = new ArrayList<String>();
	System.out.println(actualal.add(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.QuestionnairesGrid()))).getText()));
	System.out.println(actualal.add(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.InterventionsGrid()))).getText()));
	if(expectedal.equals(actualal))
	{
		Log.info("The Grids validation is successful");
	}
	else
	{
		Log.info("The Grids validation is failed");
	}	
}
public void DidYouReachTheMemberOrMemberRepresentative (){
	Log = Logger.getLogger("ClinicalIntervention.class");
	PropertyConfigurator.configure("log4j.properties");
	WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);	
	
	//wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(config.IntroAndPermissionsExpandButton())))).click();
	  //Log.info("Clicked on Intro&Permissions Expand button");
	 
	try {
		
		AnnualAssessment Annual_Assessment=PageFactory.initElements(driver, AnnualAssessment.class);
		//use this for YES checkbox
		Annual_Assessment.annual_assessment("AF86325","Intro&Permissions",7);
		Log.info("User selected Yes radiobutton");
		
		// User this for No
		/*Annual_Assessment.annual_assessment("AF86325","Intro&Permissions",6);
		Log.info("User selected  No radiobutton");*/
		
		
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	 
	  
	  // User this for No
	 /* AnnualAssessment Annual_Assessment=PageFactory.initElements(driver, AnnualAssessment.class);
		try {
			Annual_Assessment.annual_assessment("AF86325","Intro&Permissions",6);
			Log.info("Selected No checkbox");*/
}

public void VerifyingErrorMessageForFactorsVerified(){
	Log = Logger.getLogger("ClinicalIntervention.class");
	PropertyConfigurator.configure("log4j.properties");
	WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
	ArrayList<String> expectedal = new ArrayList<String>();
	expectedal.add("* Verify minimum of three factors with name as mandatory");
	ArrayList<String> actualal = new ArrayList<String>();
	actualal.add(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.ErrorMessageUnderFactorsVerifiedCheckBoxes()))).getText());
	if(expectedal.equals(actualal))
	{
		Log.info("The Error message validation is successful");
	}
	else
	{
		Log.info("The Error message validation is failed");
	}	
}

// Validating the error message upon Selecting two check boxes except name
public void SelectingTwoCheckBoxesForFactorsVerified(){
	Log = Logger.getLogger("ClinicalIntervention.class");
	PropertyConfigurator.configure("log4j.properties");
	WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
	AnnualAssessment Annual_Assessment=PageFactory.initElements(driver, AnnualAssessment.class);
	try {
		Annual_Assessment.annual_assessment("AF86325","Intro&Permissions",3);
		Log.info("Selected 2 checkboxes except Name");
		WebElement Error_valid= driver.findElement(By.xpath(config.ErrorMessageUnderFactorsVerifiedCheckBoxes()));
		if ((Error_valid.getText()).equals("* Verify minimum of three factors with name as mandatory"))
		{	
			Log.info("Error message validation is Success");
		}
		else
		{
			Log.info("Error message validation is Failed");
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
}

//  Validating the error message upon selecting 3 options including name
public void SelectingThreeCheckboxesIncludingName(){
	Log = Logger.getLogger("ClinicalIntervention.class");
	PropertyConfigurator.configure("log4j.properties");
	WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
	AnnualAssessment Annual_Assessment=PageFactory.initElements(driver, AnnualAssessment.class);
	try {
		Annual_Assessment.annual_assessment("AF86325","Intro&Permissions",4);
		Log.info("Selected 3 checkboxes Including Name");
		WebElement Error_valid= driver.findElement(By.xpath(config.ErrorMessageUnderFactorsVerifiedCheckBoxes()));
		if ((Error_valid.getText()).equals("* Verify minimum of three factors with name as mandatory"))
		{	
			Log.info("Error message validation is Success");
		}
		else
		{
			Log.info("Error message validation is Failed");
		}
		
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


// Intro and permission negative scenario
public void IntroPermissionsNegativeScenario() {
	Log = Logger.getLogger("ClinicalIntervention.class");
	PropertyConfigurator.configure("log4j.properties");
	WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
	AnnualAssessment Annual_Assessment=PageFactory.initElements(driver, AnnualAssessment.class);
	try {
		Annual_Assessment.annual_assessment("AF86325","Intro&Permissions NS",2);
		Log.info("Selected NO for Did you reach the member/member representative?");
		Log.info("Selected Unavailable in Reason for unable to reach dropdown");
		Log.info("Selected NO for Was HIPPA maintained?");
		
		
		// Verifying the reason for failure question (Should be Reason for Unable to Reach)
		WebElement Failure_Reason=wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.ReasonForUnableToReachText())));
		if((Failure_Reason.getText()).equals("Reason for Unable to Reach"))
		{
			Log.info("Label validation is successful");
		}
		else
		{
			Log.info("Label validation is failed");
		}
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
}

}






	
	

