package com.nimbus.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import excelReader.DataReaderExcel;
import utility.ConfigReader;



public class Questionnaires 
{
	WebDriver driver;
	Logger Log;
	
	ConfigReader config = new ConfigReader();
	DataReaderExcel DataRead = new DataReaderExcel();
	
	public Questionnaires(WebDriver ldriver){
		this.driver=ldriver;

	}
	
	public Questionnaires(Logger lLog){
		this.Log=lLog;
	}

	
	public void AddAssessments(String TaskName) throws Exception 
	{
		
			Log = Logger.getLogger("LoginPage.class");
			PropertyConfigurator.configure("log4j.properties");
			
			WebDriverWait wait =new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			
			try
			{			
				
			//"//span[contains(text(),'Member Action Center')]"	
			WebElement MemberActionCenter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getMemberActionCenter())));
			MemberActionCenter.isDisplayed();
			MemberActionCenter.click();
			Log.info("Click on Member Action Center");
		
			//"//span[contains(text(),'Activities')]"
			WebElement Activities = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getActivitiess())));
			Activities.isDisplayed();
			Activities.click();
			Log.info("Click on Activities");
			
//			//"//span[contains(text(),'Initial Outreach')]//ancestor::td//following-sibling::td//nm-link/a[@class='fa fa-pencil-square-o pull-right ng-star-inserted']"
//			WebElement InitialOutreach_edit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getInitialOutreach_edit())));
//			InitialOutreach_edit.isDisplayed();
//			InitialOutreach_edit.click();
//			Log.info("Click on Edit Initial outreach");
			
			ActivityPage addActivity = new ActivityPage(driver);
			addActivity.editInitialOutreachActivity(TaskName);
			
			//"//h2[contains(text(),'Questionnaires')]"			
			WebElement Questionnaires = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getQuestionnaires())));
			Questionnaires.isDisplayed();
			Questionnaires.click();
			Log.info("Click on Questionnaires");
					
			//"//button[contains(text(),'Add Questionnaires')]"			
			WebElement AddQuestionnaires = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getAddQuestionnaires())));
			AddQuestionnaires.isDisplayed();
			AddQuestionnaires.click();
			Log.info("Click on AddQuestionnaires");
			
			//"//p-header[contains(text(),'Add Questionnaires')]//parent::span/parent::div//following-sibling::div//p-dropdown/div"			
			WebElement AddQuestionnaires_dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getAddQuestionnaires_dropdown())));
			AddQuestionnaires_dropdown.isDisplayed();
			AddQuestionnaires_dropdown.click();
			Log.info("Click on AddQuestionnaires_dropdown");
			
			//"//li//span[contains(text(),'Newborn Assessment')]"			
			WebElement Newborn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getNewborn())));
			Newborn.isDisplayed();
			Newborn.click();
			Log.info("Click on Newborn");
			
			Thread.sleep(2000);
			
			//"//p-header[contains(text(),'Add Questionnaires')]//parent::span/parent::div//following-sibling::div//button[contains(text(),'Save')]"
			WebElement AddQuestionnaire_Save = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getAddQuestionnaire_Save())));
			AddQuestionnaire_Save.isDisplayed();
			AddQuestionnaire_Save.click();
			Log.info("Click on Save");
			
			Thread.sleep(2000);
			
			//"//button[contains(text(),'Add Questionnaires')]"
			WebElement AddQuestionnaires_1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getAddQuestionnaires())));
			AddQuestionnaires_1.isDisplayed();
			AddQuestionnaires_1.click();
			Log.info("Click on AddQuestionnaires");
			
			//"//p-header[contains(text(),'Add Questionnaires')]//parent::span/parent::div//following-sibling::div//p-dropdown/div"			
			WebElement AddQuestionnaires_dropdown_1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getAddQuestionnaires_dropdown())));
			AddQuestionnaires_dropdown_1.isDisplayed();
			AddQuestionnaires_dropdown_1.click();
			Log.info("Click on AddQuestionnaires_dropdown");
			
			//"//li//span[contains(text(),'Toddler Assessment')]"
			WebElement Toodler = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getToodler())));
			Toodler.isDisplayed();
			Toodler.click();
			Log.info("Click on Toddler");
			
			Thread.sleep(2000);
			
			//"//p-header[contains(text(),'Add Questionnaires')]//parent::span/parent::div//following-sibling::div//button[contains(text(),'Save')]"
			WebElement AddQuestionnaire_Save_1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getAddQuestionnaire_Save())));
			AddQuestionnaire_Save_1.isDisplayed();
			AddQuestionnaire_Save_1.click();
			Log.info("Click on Save");

			}
			catch(Exception ex)
			{
				Log.info("Message"+ex.getMessage().toString());
			}
	}
	
	public void QuestionnairesHistory() throws Exception 
	{
		try{
		Log = Logger.getLogger("LoginPage.class");
		PropertyConfigurator.configure("log4j.properties");
		
		WebDriverWait wait =new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
		Thread.sleep(1000);
		//"//span[contains(text(),'Questionnaires History')]"
		WebElement Questionnaires_History = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getQuestionnaires_History())));
		Questionnaires_History.isDisplayed();
		Questionnaires_History.click();
		Log.info("Click on Questionnaires_History");

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
		Thread.sleep(1000);
		//"//p-header/caption"
		WebElement Questionnaires = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getQuestionnaires_Caption())));
		String Expected = Questionnaires.getText().toString();
		Assert.assertEquals("Questionnaire", Expected);
		
		List<WebElement> tableHeader = driver.findElements(By.xpath("//p-table//div//table//tr/th"));
		
		try
		{
		for(int i = 1;i <= 7;i++)
		{
			WebElement Headertext = driver.findElement(By.xpath("//p-table//div//table//tr/th["+i+"]/span"));
			Log.info(Headertext.getText());
		}
		}
		catch(Exception ex)
		{
			Log.info("Message"+ex.getMessage().toString());
		}
		
		Log.info("Passed-NIM-14880: View Questionnaire/Assessment History" + '\n' + "GIVEN:Questionnaire History page"
				+ '\n' + "THEN:verifying Questionnaire History");
		}
		catch (Exception e) {
			e.printStackTrace();
			Log.info("Failed-NIM-14880: View Questionnaire/Assessment History");
				
			}
		
		
	}
	
	
	public void SaveAsDraft() throws Exception
		{
		try{
		Log = Logger.getLogger("LoginPage.class");
		PropertyConfigurator.configure("log4j.properties");
		
		WebDriverWait wait =new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
	
		
		//"//span[@title='Toddler Assessment']//parent::td//following-sibling::td//a"
		WebElement ToddlerAssessment = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getToddlerAssessment())));
		ToddlerAssessment.isDisplayed();
		ToddlerAssessment.click();
		Log.info("Click on ToddlerAssessment");
		
		AnnualAssessment AnnualAssessment= PageFactory.initElements(driver, AnnualAssessment.class);
		AnnualAssessment.annual_assessment("Questionnaires","Toddler",2);
		
		Thread.sleep(1000);
		
		//"//button[text()='Save as Draft']"
		WebElement SaveAsDraft = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getSaveAsDraft())));
		SaveAsDraft.isDisplayed();
		SaveAsDraft.click();
		Log.info("Click on SaveAsDraft");
		
		Thread.sleep(1000);
		
		//"//p-header/caption"
		WebElement Questionnaires = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getQuestionnaires_Caption())));
		String Expected = Questionnaires.getText().toString();
		Assert.assertEquals("Questionnaire", Expected);
		Log.info("Passed-NIM-14881: Start on Available Assessment" + '\n' + "GIVEN:clicking Save as Draft button"
				+ '\n' + "THEN:verifying Save as Draft button landing page");
				
		}
		catch (Exception e) {
			e.printStackTrace();
			Log.info("Failed-NIM-14881: Start on Available Assessment");
		}
		
	}
	
	public void DiscardDraft() throws Exception
	{
		try{
		Log = Logger.getLogger("LoginPage.class");
		PropertyConfigurator.configure("log4j.properties");
		
		WebDriverWait wait =new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		
		//"//span[@title='Toddler Assessment']//parent::td//following-sibling::td//a"
		WebElement ToddlerAssessment = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getToddlerAssessment())));
		ToddlerAssessment.isDisplayed();
		ToddlerAssessment.click();
		Log.info("Click on ToddlerAssessment");
		
		AnnualAssessment AnnualAssessment= PageFactory.initElements(driver, AnnualAssessment.class);
		AnnualAssessment.annual_assessment("Questionnaires","Toddler",2);
		
		Thread.sleep(1000);
		
		//"//button[text()='Discard Draft']"
		WebElement DiscardDraft = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getDiscardDraft())));
		DiscardDraft.isDisplayed();
		DiscardDraft.click();
		Log.info("Click on Discard Draft");

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
		Thread.sleep(1000);
		//"//label[contains(text(),'Are you sure you want to discard this Assessment?')]"
		WebElement WindowText = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getWindowText())));
		WindowText.isDisplayed();
		
		Log.info("Click on WindowText");

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
		Thread.sleep(1000);
		//"//button[text()='No']"
		WebElement PopUpWindowNo = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getPopUpWindowNo())));
		PopUpWindowNo.isDisplayed();
		PopUpWindowNo.click();
		Log.info("Click on PopUpWindowNo");

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
		Thread.sleep(1000);
		//"//button[text()='Discard Draft']"
		WebElement DiscardDraft1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getDiscardDraft())));
		DiscardDraft1.isDisplayed();
		DiscardDraft1.click();
		Log.info("Click on Discard Draft1");
		

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
		Thread.sleep(1000);
		//"//label[contains(text(),'Are you sure you want to discard this Assessment?')]"
		WebElement WindowTextyes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getWindowText())));
		WindowTextyes.isDisplayed();
		
		Log.info("Click on WindowTextyes");
		
		//"//button[text()='Yes']"
		WebElement PopUpWindowYes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getPopUpWindowYes())));
		PopUpWindowYes.isDisplayed();
		PopUpWindowYes.click();
		Log.info("Click on PopUpWindowYes");
		

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
		Thread.sleep(1000);
		
		//"//p-header/caption"
		WebElement Questionnaires = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getQuestionnaires_Caption())));
		String Expected = Questionnaires.getText().toString();
		Assert.assertEquals("Questionnaire", Expected);

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
		Thread.sleep(1000);
		int size = driver.findElements(By.xpath("//span[@title='Toddler Assessment']//parent::td//following-sibling::td//a")).size();
		if(size == 0)
		{
			Log.info("Toddler Assessment is discarded");
		}
		
		Log.info("Passed-NIM-14883: Discard an Assessment" + '\n' + "GIVEN:clicking Discard an Assessment"
				+ '\n' + "THEN:verifying Discard an Assessment");
		}
		catch (Exception e) {
			e.printStackTrace();
			Log.info("Failed-NIM-14883: Discard an Assessment");
		}
	}
	
	
	public void SubmitFinal() throws Exception
	{
		try{
		Log = Logger.getLogger("LoginPage.class");
		PropertyConfigurator.configure("log4j.properties");
		
		WebDriverWait wait =new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
	
		//"//span[@title='Newborn Assessment']//parent::td//following-sibling::td//a"
		WebElement NewbornAssessment = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getNewbornAssessment())));
		NewbornAssessment.isDisplayed();
		NewbornAssessment.click();
		Log.info("Click on NewbornAssessment");
		
		AnnualAssessment AnnualAssessment= PageFactory.initElements(driver, AnnualAssessment.class);
		AnnualAssessment.annual_assessment("Questionnaires","Newborn",2);
		
		Thread.sleep(1000);
		
		//"//button[text()='Submit as Final']"
		WebElement SubmitasFinal = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getSubmitasFinal())));
		SubmitasFinal.isDisplayed();
		SubmitasFinal.click();
		Log.info("Click on SubmitasFinal");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
		Thread.sleep(1000);
		
		//"//p-header/caption"
		WebElement Questionnaires = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getQuestionnaires_Caption())));
		String Expected = Questionnaires.getText().toString();
		Assert.assertEquals("Questionnaire", Expected);
		Log.info("Passed-NIM-14882:Complete/Partially Complete Assessment" + '\n' + "GIVEN:Complete/Partially Complete Assessment"
				+ '\n' + "THEN:verifying Complete/Partially Complete Assessment");
	}
		catch (Exception e) {
			e.printStackTrace();
			Log.info("Failed-NIM-14882: Complete/Partially Complete Assessment");
		}
	
	
	}

}
