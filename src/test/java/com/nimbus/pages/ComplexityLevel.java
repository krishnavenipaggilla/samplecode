package com.nimbus.pages;

import static org.testng.Assert.assertEquals;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.eclipse.jetty.util.log.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.ConfigReader;

public class ComplexityLevel {
	
	WebDriver driver;
	Logger Log;
	
	ConfigReader config = new ConfigReader();

	public ComplexityLevel(WebDriver ldriver) {
		this.driver = ldriver;
	}

	public ComplexityLevel(Logger lLog) {
		this.Log = lLog;
	}
	
	public void Complexity() throws Exception {
	
	
	try{		
		Log = Logger.getLogger("Complexity.class");
		PropertyConfigurator.configure("log4j.properties");
		AnnualAssessment annual = new AnnualAssessment(driver);
		
		//getting displaying Complexity Value
		WebElement ComplexityLevelText = driver.findElement(By.xpath(config.ComplexityLevelText()));
		WebElement ComplexityLevelValue = driver.findElement(By.xpath(config.ComplexityLevelValue()));
		Log.info("Dispalying the " + ComplexityLevelText.getText() + " with the value" + ComplexityLevelValue.getText() + "in the Overview page");
		
		WebElement BlockEdit = driver.findElement(By.xpath(config.EditOverview2()));
		BlockEdit.click();
		Thread.sleep(3000);
		
		WebElement ModelWindowHeader = driver.findElement(By.xpath(config.ModelWindowHeader()));
		assertEquals(ModelWindowHeader.getAttribute("textContent").trim(), "Edit");
		Log.info("Complexity Level Window opened and verified");
		
		//verifying fields in Complexity Model window
		for(int i=0;i<GlobalValues.ComplexityWindowlabels.size();i++) {
			driver.findElement(By.xpath("//label[contains(text(),'"+ GlobalValues.ComplexityWindowlabels.get(i) +"')]"));
			Log.info("Verifying the " + GlobalValues.ComplexityWindowlabels.get(i) + "field in the window");
		}
		
		//verifying Complexity Level dropdown values
		WebElement ComplexityLevelDropdown = driver.findElement(By.xpath(config.ComplexityDropdownValues()));
		ComplexityLevelDropdown.click();
		for(int i=0;i<GlobalValues.ComplexityLevelValues.size();i++) {
			driver.findElement(By.xpath("//li//span[text()='" + GlobalValues.ComplexityLevelValues.get(i) + "']"));
			Log.info("Verifying the "+ GlobalValues.ComplexityLevelValues.get(i) +" dropdown values in Complexity Level");
		}
		
		//verifying Reason For Change dropdwon values
		WebElement ReasonForChangeDropdown = driver.findElement(By.xpath(config.ReasonForChangeDropdownValues()));
		ReasonForChangeDropdown.click();
		for(int i=0;i<GlobalValues.ReasonForChangelValues.size();i++) {
			driver.findElement(By.xpath("//li//span[text()='" + GlobalValues.ReasonForChangelValues.get(i) + "']"));
			Log.info("Verifying the "+ GlobalValues.ReasonForChangelValues.get(i) +" dropdwon value in Reason For Change");
			Thread.sleep(3000);
		}
		Thread.sleep(5000);
		driver.findElement(By.xpath("//li//span[text()='Decrease Case Management Needs']")).click();
		WebElement WindowClose = driver.findElement(By.xpath("(//div[1]/div[1]/a/span)[2]"));
		WindowClose.click();
		Thread.sleep(1000);
		BlockEdit.click();
	
//		annual.annual_assessment("AnnualAssessments_CMDM", "ComplexityLevel", 3);
		
		//Selecting random value from the Complexity Level
		WebElement dropdown =  driver.findElement(By.xpath(config.ComplexityDropdownValues()));
		dropdown.click();
		Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(config.getSelectedValue()))));
		List<WebElement> itemsInDropdown = driver.findElements(By.xpath(config.getSelectDropdown()));
		Thread.sleep(3000);
		int size = itemsInDropdown.size();
		int randNumber = ThreadLocalRandom.current().nextInt(0, size);
		itemsInDropdown.get(randNumber).click();
		Thread.sleep(3000);

		//Selecting random value from the Reason for Change
		WebElement dropdown1 = driver.findElement(By.xpath(config.ReasonForChangeDropdownValues()));
		dropdown1.click();
	
//		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(config.getSelectedValue()))));
//		List<WebElement> itemsInDropdown1 = null;
//		itemsInDropdown1 = driver.findElements(By.xpath(config.getSelectDropdown()));
		Thread.sleep(3000);
		
//		int size1 = itemsInDropdown1.size();
//		int randNumber1 = ThreadLocalRandom.current().nextInt(0, size1);
//		itemsInDropdown1.get(randNumber1).click();
		driver.findElement(By.xpath("//li//span[text()='Decrease Case Management Needs']")).click();
		Thread.sleep(3000);	
		//Capturing Complexity Level and Reason for change given values
		WebElement Text_ComplexityLevel = driver.findElement(By.xpath(config.Text_ComplexityLevel()));
		String Value_ComplexityLevel = Text_ComplexityLevel.getText();
		
		WebElement Text_ReasonForChange = driver.findElement(By.xpath(config.Text_ReasonForChange()));
		String Value_ReasonForChange = Text_ReasonForChange.getText();
		
		//click Submit button
		WebElement Submit_ComplexityLevel = driver.findElement(By.xpath(config.Submit_ComplexityLevel()));
		Submit_ComplexityLevel.click();
		Thread.sleep(3000);	
		//Capturing Program Name from the banner
		WebElement Program = driver.findElement(By.xpath(config.getProgramName()));
		String Value_Program = Program.getText();
		Thread.sleep(3000);
		Log.info("Program name = "+ Value_Program);
			
		//Capturing Date and Time from the system when submitted
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
		Date date = new Date();
		String date1= dateFormat.format(date);
		Log.info("Date = " + date1 );
		
		//Capturing Performed By value
		WebElement PerformedBy = driver.findElement(By.xpath(config.getlblPerformedBy()));
		String Value_PerformedBy = PerformedBy.getText();
		Thread.sleep(3000);
		Log.info("Performed by = " + Value_PerformedBy );
		
		Log.info("Complexity Level added from the overview ");
		
		//getting displaying Complexity Value
		Log.info("Veriying the changed " + ComplexityLevelText.getText() + " with the value " + ComplexityLevelValue.getText() + "in the Overview page");
		
		//click on Change History in left tab
		LeftNavigationLink LeftNav = PageFactory.initElements(driver, LeftNavigationLink.class);
		LeftNav.clickChangeHistory();
		Thread.sleep(3000);
		
		//navigate to Complexity level grid page
		WebElement ChangeHistorydropdown = driver.findElement(By.xpath(config.ChangeHistorydropdown()));
		ChangeHistorydropdown.click();
		
		WebElement SelectComplexityHistory = driver.findElement(By.xpath(config.SelectComplexityHistory()));
		SelectComplexityHistory.click();
		Thread.sleep(1000);
		
		//verify grid Header
		WebElement ComplexityLevelHistoryGrid = driver.findElement(By.xpath(config.ComplexityLevelHistoryGrid()));
		if((ComplexityLevelHistoryGrid.getText()).equals("Risk/Acuity History")) {
			Log.info("Risk/Acuity HistoryGrid is present");
		} else {
			Log.info("Risk/Acuity History Grid is not present");
		}
		
		//verifying grid columns and comparing data in the column with the given ones
		for(int i=0;i<GlobalValues.ComplexityLevelHistoryGrid.size();i++) {
			driver.findElement(By.xpath("//p-table/div//tr/th["+(i+1) +"]//span[text()='"+ GlobalValues.ComplexityLevelHistoryGrid.get(i) +"']"));
			Log.info("Complexity Level History Grid column - " + GlobalValues.ComplexityLevelHistoryGrid.get(i));
			
			if(GlobalValues.ComplexityLevelHistoryGrid.get(i) == "Program") {
				WebElement Program_Text = driver.findElement(By.xpath("//p-table//tr/td["+(i+1) +"]//span[@class ='fieldValue']"));
				Thread.sleep(1000);
				assertEquals(Program_Text.getText(), Value_Program.trim());
				Log.info("Program Value verified");
			}else if(GlobalValues.ComplexityLevelHistoryGrid.get(i) == "Risk/Acuity Level") {
				WebElement ComplexityLevel_Text = driver.findElement(By.xpath("//p-table//tr/td["+(i+1) +"]//span[@class ='fieldValue']"));
				Thread.sleep(1000);
				assertEquals(ComplexityLevel_Text.getText(), Value_ComplexityLevel);
				Log.info("Complexity Value verified");
			}else if(GlobalValues.ComplexityLevelHistoryGrid.get(i) == "Reason for Change") {
				WebElement ReasonForChange_Text = driver.findElement(By.xpath("//p-table//tr/td["+(i+1) +"]//span[@class ='fieldValue']"));
				Thread.sleep(1000);
				assertEquals(ReasonForChange_Text.getText(), Value_ReasonForChange);
				Log.info("Reason For Change verified");
			}else if(GlobalValues.ComplexityLevelHistoryGrid.get(i) == "Date and Time Completed") {
				WebElement DateandTimeCompleted_Text = driver.findElement(By.xpath("//p-table//tr/td["+(i+1) +"]//span[@class ='fieldValue']"));
				Thread.sleep(1000);
				assertEquals(DateandTimeCompleted_Text.getText(), date1);
				Log.info("Date and Time Completed verified");
			}else if(GlobalValues.ComplexityLevelHistoryGrid.get(i) == "Performed By") {
				WebElement PerformedBy_Text = driver.findElement(By.xpath("//p-table//tr/td["+(i+1) +"]//span[@class ='fieldValue']"));
				Thread.sleep(1000);
				assertEquals(PerformedBy_Text.getText(), Value_PerformedBy);
				Log.info("Performed By Verified ");
			}
		}
		Thread.sleep(3000);
		
	}catch(Exception e){
		e.printStackTrace();
		Global_Method gm = new Global_Method(driver);
		gm.clickClose(1);
	}
}
}
