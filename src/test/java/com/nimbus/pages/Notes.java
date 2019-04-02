package com.nimbus.pages;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.ConfigReader;

/*
 * Class Name: Notes
 * MethodName:	CreateNote
 * Description: common class to create note and view note
 */
public class Notes {
	
ConfigReader config = new ConfigReader();
	
	WebDriver driver;
	Logger Log;
	public Notes(WebDriver ldriver){
		this.driver=ldriver;
	}
	
	public Notes(Logger lLog){
		this.Log=lLog;
	}
	
	public void createNote()
	{
		Log = Logger.getLogger("Notes.class");
		PropertyConfigurator.configure("log4j.properties");
		//click on Notes
		LeftNavigationLink LNL = PageFactory.initElements(driver, LeftNavigationLink.class);
		LNL.clickNotes();
		
		WebDriverWait wait = new WebDriverWait(driver,GlobalValues.Explicit_Wait_time);
		
		WebElement AddNote = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getBTN_AddaNote())));
		AddNote.click();
        Log.info("Click on AddNote");
        
//        WebElement CaseNumber = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getTXT_CaseID())));
//        GlobalValues.Case_Number = CaseNumber.getAttribute("value").toString();
//        Log.info("Assign CaseNumber");
//        Log.info("CaseNumber"+GlobalValues.Case_Number.toString());
        
        WebElement backButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getBTN_Back())));
        backButton.click();
        Log.info("click on Back");
 	}
}