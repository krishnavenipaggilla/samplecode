package com.nimbus.pages;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import excelReader.DataReaderExcel;
import utility.ConfigReader;

public class ActivityPage1{
	
	WebDriver driver;
	Logger Log;
	
	ConfigReader config = new ConfigReader();
	DataReaderExcel DataRead = new DataReaderExcel();
	
	public ActivityPage1(WebDriver ldriver){
		this.driver=ldriver;

	}
	
	public ActivityPage1(Logger lLog){
		this.Log=lLog;
	
	}

	public void AddActivityLink() {
		Log = Logger.getLogger("ActivityPage.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(config.AddActivityLink())))).click();
		Log.info("User clicked on Add Activity Link");
		
	} 
	
	public void ValidatingActivityDropdownValues(){
		Log = Logger.getLogger("ActivityPage.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		List<String> Expectedlist = new ArrayList<String>();
		Expectedlist.add("Clinical Intervention");
		Expectedlist.add("Case Closure");
		Expectedlist.add("Referral");
		Expectedlist.add("Consultation");
		Expectedlist.add("Care Coordination");
		Expectedlist.add("Case Review");
	    List<WebElement> Activitydropdown = driver.findElements(By.xpath(config.ActivityTypeDropdown()));
	    List<String> Actual_List= new ArrayList<String>();
	   for(WebElement e:Activitydropdown){
	    	Actual_List.add(e.getText());	
	    }
	    Global_Method Global_Me=PageFactory.initElements(driver, Global_Method.class);
		Global_Me.validateDropDownOptions(Actual_List, Expectedlist);
	}	
	
//--------------------------------------------Edit activity Links-----------------------------------------------------------------//
	
	// Edit Activity link for Initial outreach
	
	public void InitialOutreachEditLink(){
		Log = Logger.getLogger("ActivityPage.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(config.InitialOutreachEditLink())))).click();
		Log.info("User clicked on Edit Activity Link");
	}
	
	
	// Edit Activity link for Referral
	
	public void ReferralEditLink(){
		Log = Logger.getLogger("ActivityPage.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(config.ReferralEditLink())))).click();
		Log.info("User clicked on Edit Referral Activity Link");
	}
	
	
	// Edit Activity link for Care coordination
	
	public void CareCoordinationEditLink(){
		Log = Logger.getLogger("ActivityPage.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(config.CareCoordinationEditLink())))).click();
		Log.info("User clicked on Edit Activity Link");
	}
	
	
	
	//Edit Activity link for consultation
	
	public void ConsultationEditLink(){
		Log = Logger.getLogger("ActivityPage.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(config.ConsultationEditLink())))).click();
		Log.info("User clicked on Edit Activity Link");
	}
	
	
	
	//Case closure Edit activity link
		public void CaseClosureEditLink() {
			Log = Logger.getLogger("ActivityPage.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(config.EditActivityLink())))).click();
			Log.info("User clicked on Edit Activity Link");
		}	
		
//----------------------//		
		
		// Validating the Activity Type(Initial Outreach) Status 
		public void ValidateFirstRowData() {
			Log = Logger.getLogger("ActivityPage.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			System.out.println(wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(config.EditActivityLink())))).getText());
		}	
	
		
// Validating the Updated data(the updated activity is in the second row, We are validating the second row)
		
		public void ValidatingUpdatedData() {
			Log = Logger.getLogger("ActivityPage.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait =new WebDriverWait(driver, 20);
			ArrayList<String> expectedal = new ArrayList<String>();
            expectedal.add("Consultation");
            expectedal.add("Not Started");
            expectedal.add("08/26/2018");
            expectedal.add("01/01/2019");
            expectedal.add("");
            expectedal.add("Snow White");
            expectedal.add("Alabaster Snowball");
            expectedal.add("Alabaster Snowball");
            
			ArrayList<String> actualal = new ArrayList<String>();
			actualal.add(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.ActivityTypedata()))).getText());
			actualal.add(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.StatusData()))).getText());
			actualal.add(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.StartDateData()))).getText());
			actualal.add(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.DueDateData()))).getText());
			actualal.add(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.CompletedDateData()))).getText());
			actualal.add(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.AssignedToData()))).getText());
			actualal.add(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.CreatedByData()))).getText());
			actualal.add(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.LastUpdatedByData()))).getText());
			System.out.println(actualal);
			System.out.println(expectedal);
			
			if(expectedal.equals(actualal))
			{
				Log.info("The validation is successful");
			}
			else
			{
				Log.info("The validation is failed");
			}
			
			
		}	
		
	public void verifyChangeHistory(){
		Log = Logger.getLogger("ActivityPage.class");
		PropertyConfigurator.configure("log4j.properties");
		Log.info("user click on change history");
		
		driver.findElement(By.xpath(config.getChangeHistoryXpath1())).click();
		
		Log.info("checking for Activity Status History");
		selectActivityDropDown("Activity Status History");
		if(driver.findElements(By.xpath(config.getGridRecordsXpath1())).size() > 0){
			Log.info("Record found for Activity Status History");
		}else{
			Log.info("No record found for Activity Status History");
		}
			
		Assert.assertTrue(verifyHeader("Program"), "Program header is not found for Activity Status History");
		Assert.assertTrue(verifyHeader("Activity Type"), "Activity Type header is not found for Activity Status History");
		
		
		Log.info("checking for Case Status History");
		selectActivityDropDown("Case Status History");
		if(driver.findElements(By.xpath(config.getGridRecordsXpath1())).size() > 0){
			Log.info("Record found for Case Status History");
		}else{
			Log.info("No record found for Case Status History");
		}
		Assert.assertTrue(verifyHeader("Program"), "Program header is not found for Case Status History");
		Assert.assertTrue(verifyHeader("Case Status"), "Case Status is not found for Case Status History");
		
		Log.info("cheching for Contact History");
		selectActivityDropDown("Contact History");
		if(driver.findElements(By.xpath(config.getGridRecordsXpath1())).size() > 0){
			Log.info("Record found for Contact History");
		}else{
			Log.info("No record found for Contact History");
		}
		Assert.assertTrue(verifyHeader("Program"), "Program header is not found for Case Status History");
		Assert.assertTrue(verifyHeader("Activity Type"), "Case Status is not found for Case Status History");

	}
	
	public void verifyActivityStatusHistory(){
		Log = Logger.getLogger("ActivityPage.class");
		PropertyConfigurator.configure("log4j.properties");
		
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(config.getChangeHistoryXpath1())))).click();
		Log.info("user click on change history");
		
		Log.info("checking for Activity Status History");
		selectActivityDropDown("Activity Status History");
		
		Log.info("Number of row found : "+driver.findElements(By.xpath("//div[@class='scrollGrid']//tbody/tr")).size());

	}
	public void verifyActivityStatusHistoryUpdated(String activityType){
		Log = Logger.getLogger("ActivityPage.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(config.getChangeHistoryXpath())))).click();
		Log.info("user click on change history");
		
		Log.info("checking for Activity Status History");
		selectActivityDropDown("Activity Status History");
		
		Log.info("Number of row found after update : "+driver.findElements(By.xpath("//div[@class='scrollGrid']//tbody/tr")).size());
		
		Assert.assertEquals(activityType, driver.findElement(By.xpath("//div[@class='scrollGrid']//tbody/tr[1]/td[2]")).getText().trim());
		Assert.assertEquals("Not Started", driver.findElement(By.xpath("//div[@class='scrollGrid']//tbody/tr[1]/td[3]")).getText().trim());
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		String currentDate = dateFormat.format(new Date());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='scrollGrid']//tbody/tr[1]/td[5]")).getText().trim().contains(currentDate));
		Log.info("Verified all details of newly added activity");
	}
	
	public void verifyCaseStatusHistory(){
		Log = Logger.getLogger("ActivityPage.class");
		PropertyConfigurator.configure("log4j.properties");
		
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(config.getChangeHistoryXpath())))).click();
		Log.info("user click on change history");
		
		Log.info("checking for Case Status History");
		selectActivityDropDown("Case Status History");
		
		Log.info("Number of row found : "+driver.findElements(By.xpath("//div[@class='scrollGrid']//tbody/tr")).size());
	}
	
	public void verifyCaseStatusHistoryUpdated(String caseOwner){
		Log = Logger.getLogger("ActivityPage.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(config.getChangeHistoryXpath())))).click();
		Log.info("user click on change history");
		
		Log.info("checking for Case Status History");
		selectActivityDropDown("Case Status History");
		
		Log.info("Number of row found after update : "+driver.findElements(By.xpath("//div[@class='scrollGrid']//tbody/tr")).size());
		
		Assert.assertEquals("Active", driver.findElement(By.xpath("//div[@class='scrollGrid']//tbody/tr[1]/td[2]")).getText().trim());
		Assert.assertEquals("Assigned", driver.findElement(By.xpath("//div[@class='scrollGrid']//tbody/tr[1]/td[3]")).getText().trim());
		Assert.assertEquals(caseOwner, driver.findElement(By.xpath("//div[@class='scrollGrid']//tbody/tr[1]/td[4]")).getText().trim());
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		String currentDate = dateFormat.format(new Date());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='scrollGrid']//tbody/tr[1]/td[5]")).getText().trim().contains(currentDate));
		Log.info("Verified all details of newly added case activity");
	}
	
	
	public void verifyContactHistory(){
		Log = Logger.getLogger("ActivityPage.class");
		PropertyConfigurator.configure("log4j.properties");
		try{
		
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(config.getChangeHistoryXpath())))).click();
		Log.info("user click on change history");
		
		Log.info("cheching for Contact History");
		selectActivityDropDown("Contact History");
		
		Log.info("Number of row found : "+driver.findElements(By.xpath("//div[@class='scrollGrid']//tbody/tr")).size());
		}
		catch(Exception e){
			
		}
		
	}
	
	public void verifyContactHistoryUpdated(String activityType, String contactOutcome){
		Log = Logger.getLogger("ActivityPage.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		try{
			
		
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(config.getChangeHistoryXpath1())))).click();
		Log.info("user click on change history");
		
		Log.info("cheching for Contact History");
		selectActivityDropDown("Contact History");
		
		Log.info("Number of row found after update : "+driver.findElements(By.xpath("//div[@class='scrollGrid']//tbody/tr")).size());
		
		Assert.assertEquals(activityType, driver.findElement(By.xpath("//div[@class='scrollGrid']//tbody/tr[last()]/td[2]")).getText().trim());
		Assert.assertEquals(contactOutcome, driver.findElement(By.xpath("//div[@class='scrollGrid']//tbody/tr[last()]/td[3]")).getText().trim());
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		String currentDate = dateFormat.format(new Date());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='scrollGrid']//tbody/tr[last()]/td[4]")).getText().trim().contains(currentDate));
		Log.info("Verified all details of newly added contact");
		}
		catch(Exception e){
			
		}
		
	}
	
	private void selectActivityDropDown(String activityName){
		driver.findElement(By.xpath(config.getChangeHistoryInformationDropDown1())).click();
		driver.findElement(By.xpath(config.getChangeHistoryInformationDropDownValue1().replace("<DISPLAYED_TEXT>", activityName))).click();
		try {
			Thread.sleep(GlobalValues.Sleep_wait_time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private boolean verifyHeader(String headerName){
		return driver.findElements(By.xpath(config.getGridHeaderXpath1().replace("<HEADER_NAME>", headerName))).size()>0;
	}
}
