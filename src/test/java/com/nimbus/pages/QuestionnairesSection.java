package com.nimbus.pages;



import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utility.ConfigReader;

public class QuestionnairesSection {
	private List<String> ActualColumnNames = null;
	WebDriver driver;
	Logger Log;
	ConfigReader config = new ConfigReader();
	public QuestionnairesSection(WebDriver ldriver) {
		this.driver = ldriver;
	}

	public QuestionnairesSection(Logger lLog) {
		this.Log = lLog;
	}
	
	public void questionnairesSection(String TaskName) throws Exception{
		try{
		Log = Logger.getLogger("QuestionnairesSection.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		// Left Navigation
		LeftNavigationLink leftNav = PageFactory.initElements(driver, LeftNavigationLink.class);
//		leftNav.clickMemberActionCenter();
//		leftNav.clickActivities();
		Log.info("Click on Edit initialoutreach activity");
			
		ActivityPage addActivity = new ActivityPage(driver);
		addActivity.editInitialOutreachActivity(TaskName);
		WebElement accordionQuestionnaire = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getQuestionnaires())));
		accordionQuestionnaire.click();
		DashboardFunctions GridCloumns = PageFactory.initElements(driver, DashboardFunctions.class);

		// Verifying Grid Column Names in Questionnaire

		ActualColumnNames =getQuestionnaireGridColumnNames();
		Thread.sleep(1000);
		GridCloumns.validateGridColumnNames(GlobalValues.QuestionnaireColumns, ActualColumnNames);
		Log.info("verifying save progress button");
		WebElement QuestionnaireSaveProgress = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getQuestionnaireSaveProgress())));
		Thread.sleep(1000);
		WebElement AddQuestionnaire = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getAddQuestionnaire())));
		if(QuestionnaireSaveProgress.isEnabled() && AddQuestionnaire.isEnabled()){
			Log.info("verified save progress and add questionnaire options");
		}
		
		Log.info("Passed-NIM-17224: Layout of the Questionnaire Subsection" + '\n' + "GIVEN:Questionnaire Subsection"
				+ '\n' + "THEN:verifying Layout of the Questionnaire Subsection");
		
		}
		catch (Exception e) {
			Log.info("Failed-NIM-17224: Layout of the Questionnaire Subsection");
		}
					
	}
	
	public void addQuestionnaires() {
		try{
		WebElement AddQuestionnaire=null;
		WebElement QuestionniareHeader=null;
		String QuestionniareHeader1=null;
		Log = Logger.getLogger("QuestionnairesSection.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		Log.info("adding questionnaire");
		 AddQuestionnaire = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getAddQuestionnaire())));
		AddQuestionnaire.click();
		Log.info("verifying questionnaire name");
		WebElement QuestionnaireName = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getQuestionnaireName())));
		String QuestionnaireName1= QuestionnaireName.getText();
		Assert.assertEquals(QuestionnaireName1, "Questionnaire Name");
		Log.info("verified Questionnaire Name");
		Log.info("verifying questionnaire subsection after clicking cancel button");
		WebElement QuestionnaireCancelbutton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getQuestionnaireCancel())));
		QuestionnaireCancelbutton.click();
		Thread.sleep(3000);
		 QuestionniareHeader = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getQuestionniareHeader())));
		 QuestionniareHeader1= QuestionniareHeader.getText();
		 Assert.assertEquals(QuestionniareHeader1, "Questionnaire");
		Log.info("verified Questionnaire subsection after clicking on cancel button");
		Thread.sleep(4000);
		 AddQuestionnaire = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getAddQuestionnaire())));
		AddQuestionnaire.click();
//		try{
//		Select QuestionnaireDrop =new  Select(driver.findElement(By.xpath(config.getQuestionnaireDropOptions())));
//		QuestionnaireDrop.selectByIndex(1);
//		}
//	 catch (Exception e) {
//		e.printStackTrace();
//		}
//		WebElement QuestionnaireDrop = wait
//				.until(ExpectedConditions.elementToBeClickable(By.xpath(("(//label[contains(text(),'Please Select...')])[15]//following-sibling::div"))));
//		QuestionnaireDrop.click();
//		
//		WebElement Questionnairenewborn = wait
//				.until(ExpectedConditions.elementToBeClickable(By.xpath(("//li//span[contains(text(),'Newborn Assessment')]"))));
//		Questionnairenewborn.click();
		
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
		
		Thread.sleep(4000);
		
		WebElement QuestionnaireSave = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getQuestionnaireSave())));
		QuestionnaireSave.click();
		Thread.sleep(2000);
		 QuestionniareHeader = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getQuestionniareHeader())));
		 QuestionniareHeader1= QuestionniareHeader.getText();
		 Assert.assertEquals(QuestionniareHeader1, "Questionnaire");
		Log.info("verified Questionnaire subsection after clicking on save button");
		Log.info("verifying Questionnaire status");
		WebElement QuestionnaireStatus = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getQuestionnairestatus())));
		String QuestionnaireStatus1=QuestionnaireStatus.getText();
		Assert.assertEquals(QuestionnaireStatus1, "Not Started");
		Log.info("verified Questionnaire status");
		Log.info("verifying Newly added Questionnaires should be allowed to be deleted before save progress ");
		WebElement QuestionnaireDelete = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getQuestionnaireDelete())));
		QuestionnaireDelete.click();
		
		Log.info("Passed-NIM-17225: Ability to Add a Questionnaire & Display" + '\n' + "GIVEN:Add a Questionnaire & Display"
				+ '\n' + "THEN:Ability to Add a Questionnaire & Display");
		}
		catch (Exception e) {
			Log.info("Failed-NIM-17225: Ability to Add a Questionnaire & Display");
		}
		
		
		
		
		
	}

	// This Method is returning the list of columns in Questionnaire Grid

			public List<String> getQuestionnaireGridColumnNames() throws Exception {

				Log = Logger.getLogger("DashboardFunctions.class");
				PropertyConfigurator.configure("log4j.properties");

				List<WebElement> GridColumnCount = driver.findElements(By.xpath(config.getQuestionnaireColumnsOnGrid()));
			

				StringBuilder Name = new StringBuilder();
				Name = Name.append(config.getQuestionnaireAppendingCoulmn());
				int ColumnCount = GridColumnCount.size();

				List<String> ColumnNames = new ArrayList<String>();

				if (ColumnCount > 0) {
					for (int ColumnNumber = 1; ColumnNumber <= ColumnCount; ColumnNumber++) {
						ColumnNames.add(driver
								.findElement(By.xpath(Name.toString().replace("columnNumber", String.valueOf(ColumnNumber))))
								.getAttribute("textContent").toString().trim());


					}
					Log.info("All Column Names are loaded Completely and Present on Grid");

				} else {
					Log.info("Questionnaire Grid do not have any columns to prepare the list for validation");
					ColumnNames = null;
				}
				return ColumnNames;
			}

}
