package com.nimbus.pages;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import excelReader.DataReaderExcel;
import utility.ConfigReader;

public class EditActivityPage {

	
	WebDriver driver;
	Logger Log;
	
	ConfigReader config = new ConfigReader();
	DataReaderExcel DataRead = new DataReaderExcel();
	
	public EditActivityPage(WebDriver ldriver){
		this.driver=ldriver;

	}
	
	public EditActivityPage(Logger lLog){
		this.Log=lLog;
	
	}
	
public void IntroPermissionsQALink(){
		
		Log = Logger.getLogger("EditActivityPage.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(config.IntroPermisionsExpandIcon())))).click();
		Log.info("User clicked on Intro&Permissions Link");
	
	}

	public void CompletingEditActivity(){
		Log = Logger.getLogger("EditActivityPage.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		
		try{
			
			
			AnnualAssessment Annual_Assessment= PageFactory.initElements(driver, AnnualAssessment.class);
			Annual_Assessment.annual_assessment("AF86325","Intro&Permissions",2);
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(config.SaveProgress())))).click();
		 Log.info("clicked on SaveProgress");
		 
		 // Clicking Ok in the Alert text 
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.AlertText()))).click();
		 Log.info("User clicked OK in Alert model window");
	
		}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	}	

	
	// Save/End Activity
	
	public void SaveEndActivity(){
		Log = Logger.getLogger("EditActivityPage.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		
		try{
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(config.SaveEndActivityButton())))).click();
			Log.info("User clicked on Save/End Activity button");
			
		}
		catch(Exception e){
			
		}
		
	}
	
	
	// Adding Questionnaires
	
	public void AddingQuestionnaires(){
		
		Log = Logger.getLogger("EditActivityPage.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		
       try{
    	   // Expand Button
    	   
    	   wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(config.QuestionnairesExpandButton())))).click();
			Log.info("User clicked on Questionnaires Expand Button");
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(config.AddQuestionnairesLink())))).click();
			Log.info("User clicked on Add Questionnaires link");
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(config.QuestionnaireNameDropDown())))).click();
			Log.info("User clicked on Questionnaire Name DropDown");
			AnnualAssessment Annual_Assessment=PageFactory.initElements(driver, AnnualAssessment.class);
			Annual_Assessment.annual_assessment("AF86325","Questionnaires",2);
			// Save
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(config.QuestionnairesSaveProgress())))).click();
			Log.info("User clicked on Questionnaires SaveProgress Button");
			Log.info("User succesfully created new Questionnaires");
		}
		catch(Exception e){
			
		}
		
	}
	
	// Validating and clicking Edit button in Questionnaires grid
	
public void EditQuestionnaires(){
		
		Log = Logger.getLogger("EditActivityPage.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		
       try{
			
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(config.EditQuestionnairesButton())))).isDisplayed();
			Log.info("User succesfully validated the Edit Questionnaires Button");
			
			/*wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(config.EditQuestionnairesButton())))).click();
			Log.info("User clicked on the Edit Questionnaires Button");*/
		}
		catch(Exception e){
			
		}
		
	}

	// Clicking Ok for the confirmation message window
	
	public void HandlingConfirmationMessage(){
		Log = Logger.getLogger("EditActivityPage.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		
		try{
			
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(config.AlertText())))).click();
			Log.info("User clicked OK Confirmation message");
			
		}
		catch(Exception e){
			
		}
		
	}

	
	public void VerifiyingHIPPAVerifiedPresented(){
		Log = Logger.getLogger("EditActivityPage.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);	
		//Thread.sleep(GlobalValues.Sleep_wait_time);
		WebElement HippaVerified_Text = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.HIPPAverfiedText())));
		if((HippaVerified_Text.getText()).equals("HIPAA Verified?"))
		{
			Log.info("Hippa verified text Validation is successful");
		}
		else
		{
			Log.info("Hippa verified text Validation is failed");
		}
	}
	
// Validating the Checkboxes @Factors Verified (minimum of three factors with name as mandatory) *
	
	public void ValidatingFactorsVerifiedCheckboxes(){
          Log = Logger.getLogger("ActivityPage.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait =new WebDriverWait(driver, 20);
		
		
		// Validating the Factor verified Hippa Checkboxes
		
		/*for (int i=1; i<=5; i++)
		{
			Assert.assertTrue(driver.findElement(By.xpath("//div/nm-input-checkbox/fieldset/div/div[i]/p-checkbox/div/div[2]")).isDisplayed(),"Checkbox " + i +" is failed");
			
		}
		
		Assert.assertTrue(driver.findElement(By.xpath(xpathExpression)).isDisplayed(),"Name text faied to display");*/
		
		
		
		
		
		ArrayList<String> expectedal = new ArrayList<String>();
        expectedal.add("Name:");
        expectedal.add("Address:");
        expectedal.add("Preferred Phone Number:");
        expectedal.add("DOB/Year of Birth:");
        expectedal.add("Subscriber HCID:");
        ArrayList<String> actualal = new ArrayList<String>();
		
		if(expectedal.equals(actualal))
		{
			Log.info("The validation is successful");
		}
		else
		{
			Log.info("The validation is failed");
		}
       		
	}
}
	
	
	
	
	
		



		
		
	
		
		
		
	

