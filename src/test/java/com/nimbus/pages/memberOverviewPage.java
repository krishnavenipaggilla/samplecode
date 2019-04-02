package com.nimbus.pages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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

import utility.ConfigReader;

public class memberOverviewPage {
	ConfigReader config = new ConfigReader();

	WebDriver driver;
	Logger Log;
	

	public memberOverviewPage(WebDriver ldriver) {
		this.driver = ldriver;
	}

	public memberOverviewPage(Logger lLog) {
		this.Log = lLog;
	}

	public void verifyMemberOverviewPage() throws Exception {
		Log = Logger.getLogger("AuthorizedRepresentativePage.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		Global_Method gm = new Global_Method(driver);
		LeftNavigationLink lnk = new LeftNavigationLink(driver);
		boolean passed = true;
		
		lnk.clickOverview();
		
		try {
			verifyMemberDetailsFields();
		} catch (Exception e) {
			Log.info(e);
			e.printStackTrace();
			passed = false;
		}

		try {
			verifyCaseDetailsFields();
		} catch (Exception e) {
			Log.info(e);
			e.printStackTrace();
			passed = false;
		}

		try {
			verifyContactDetailsFields();
		} catch (Exception e) {
			Log.info(e);
			e.printStackTrace();
			passed = false;
		}

		if (passed == true) {
			gm.Reports("NIM-14407", "Pass");

		} else {
			gm.Reports("NIM-14407", "Fail");

		}
		
	}
	

	public List<List<String>> getMemberDetailsFromMemberOverviewUI() throws InterruptedException{
		Log = Logger.getLogger("memberOverviewPage.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"navbarToggler\"]/nm-panelmenu/div/div[1]/div/a/span"))).click();
        
		Thread.sleep(GlobalValues.Sleep_wait_time);
		WebElement memberDetailsTileHeaderElement = driver.findElement(By.xpath("//div//h2[contains(text(),'Member Details')]"));
		String memberDetailsTileHeaderText = memberDetailsTileHeaderElement.getText().trim();
		Assert.assertEquals("Member Details", memberDetailsTileHeaderText, "Member Details Tile Header is not Matching");
		Thread.sleep(GlobalValues.Sleep_wait_time);
		
		
		List<String> memberDetailsLabel = new ArrayList<String>() ;
		List<WebElement> memberDetailsLabelElements = driver.findElements(By.xpath("//nm-section//div[@id = 'memberDemographicSection']//label"));
		for(WebElement memberDetailsLabelWebElement: memberDetailsLabelElements){
			memberDetailsLabel.add(memberDetailsLabelWebElement.getText().trim());
		}
		Log.info("Got the fields for Member Details from Member Overview of UI" + memberDetailsLabel);
		
		
		List<String> memberDetailsValues = new ArrayList<String>() ;
		List<WebElement> memberDetailsValuesElements = driver.findElements(By.xpath("//nm-section//div[@id = 'memberDemographicSection']//div[@class='form-group ng-star-inserted']//span"));
		for(WebElement memberDetailsValuesWebElement: memberDetailsValuesElements){
			memberDetailsValues.add(memberDetailsValuesWebElement.getText().trim());
		}
		Log.info("Got the Values for Member Details from Member Overview of UI" + memberDetailsValues);
		
		List<List<String>> memberDetailsLabelValues = new ArrayList<List<String>>();
		memberDetailsLabelValues.add(memberDetailsLabel);
		memberDetailsLabelValues.add(memberDetailsValues);
		
		return memberDetailsLabelValues;
	}
	
	public void verifyMemberDetailsFields()throws Exception{
		Log = Logger.getLogger("memberOverviewPage.class");
		PropertyConfigurator.configure("log4j.properties");
		Global_Method gm = new Global_Method(driver);
		Boolean pass = true;
		DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
			
		SimpleDateFormat myformat = new SimpleDateFormat(GlobalValues.StdDateformat);
		Date dob =new SimpleDateFormat("MM/dd/yyyy").parse(GlobalValues.DBDOB);
		
		//String dob = myformat.format(GlobalValues.DBDOBdate);
		

	/*	if(GlobalValues.DBExpressConsent.equals("Do Not Call")){
			GlobalValues.DBPhoneNumber = "xxx-xxx-xxxx";
		}
	*/	
		try{
		ArrayList<String> memberDetailsKeys = new ArrayList<String>(Arrays.asList("Member Name","Nickname","Member ID","DOB","Age","Gender","Ethnic Origin","Eligibility Status"));		
		
		ArrayList<String> memberDetailsValuesFromDB = new ArrayList<String>(Arrays.asList(GlobalValues.DBMemberFirstName+" "+GlobalValues.DBMemberLastName,GlobalValues.DBNickname,GlobalValues.DBMemberIdOfMember,GlobalValues.DBDOB,GlobalValues.calculateAge(dob)+"",GlobalValues.DBGender,GlobalValues.DBEthnicity,GlobalValues.DBEligibilityStatus));
		//+GlobalValues.DBCaseOwnerFirstName+GlobalValues.DBCaseOwnerLastName //
		Log.info("Member Details Keys "+ memberDetailsKeys);
		Log.info("Member Details Values from MongoDB = "+ memberDetailsValuesFromDB);
		Thread.sleep(GlobalValues.Sleep_wait_time);
		List<List<String>> memberDetailsList =  getMemberDetailsFromMemberOverviewUI();
		
		List <String> memberDetailFields = memberDetailsList.get(0);
		List <String> memberDetailValues = memberDetailsList.get(1);
		
		for(int i =0 ; i<memberDetailsKeys.size(); i++){
			if(!(memberDetailFields.get(i).contains(memberDetailsKeys.get(i)))){
				Log.info(memberDetailFields.get(i) +" - Member Details Field Labels mismatch = " + memberDetailsKeys.get(i));
				pass=false;				
			}
			if(!(memberDetailValues.get(i).contains(memberDetailsValuesFromDB.get(i)))){
				Log.info(memberDetailValues.get(i) +" - Member Details Field Values mismatch = " + memberDetailsValuesFromDB.get(i));
				pass=false;				
			}
			
		}
		
		if(pass==true){
			Log.info("**********Member Details Of Member Overview Passed********** " );
			
			
		}else {
			Log.info("!!!!!!!!!!Member Details Of Member Overview Failed!!!!!!!!!! " );
			
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public List<List<String>> getCaseDetailsFromMemberOverviewUI() throws InterruptedException{
		Log = Logger.getLogger("memberOverviewPage.class");
		PropertyConfigurator.configure("log4j.properties");
		Thread.sleep(GlobalValues.Sleep_wait_time);
		WebElement caseDetailsTileHeaderElement = driver.findElement(By.xpath("//div//h2[contains(text(),'Case Details')]"));
		String casememberDetailsTileHeaderText = caseDetailsTileHeaderElement.getText().trim();
		Assert.assertEquals("Case Details", casememberDetailsTileHeaderText, "Case Details Tile Header is not Matching");
		
		
		Thread.sleep(GlobalValues.Sleep_wait_time);
		List<String> caseDetailsLabels = new ArrayList<String>() ;
		List<WebElement> caseDetailsLabelElements = driver.findElements(By.xpath("//nm-section//div[@id = 'caseDetailSection']//label"));
		for(WebElement caseDetailsLabelWebElement: caseDetailsLabelElements){
			caseDetailsLabels.add(caseDetailsLabelWebElement.getText().trim());
		}
		Log.info("Got the fields for Case Details Labels from Member Overview of UI" + caseDetailsLabels);
		
		
		List<String> caseDetailsValues = new ArrayList<String>() ;
		List<WebElement> caseDetailsValuesElements = driver.findElements(By.xpath("//nm-section//div[@id = 'caseDetailSection']//div[@class='form-group ng-star-inserted']//span"));
		for(WebElement caseDetailsLabelWebElement: caseDetailsValuesElements){
			caseDetailsValues.add(caseDetailsLabelWebElement.getText().trim());
		}
		Log.info("Got the Values for Case Details Values from Member Overview of UI" + caseDetailsValues);
		
		List<List<String>> caseDetailsLabelsValues = new ArrayList<List<String>>();
		caseDetailsLabelsValues.add(caseDetailsLabels);
		caseDetailsLabelsValues.add(caseDetailsValues);
		
		return caseDetailsLabelsValues;
		
	}
	
	
	public void verifyCaseDetailsFields()throws Exception{
		Log = Logger.getLogger("memberOverviewPage.class");
		PropertyConfigurator.configure("log4j.properties");
		Global_Method gm = new Global_Method(driver);
		Boolean pass = true;
		
		try{
		ArrayList<String> caseDetailsKeys = new ArrayList<String>(Arrays.asList("Case ID","Case Owner","Case Status","Primary Condition","Management Reason","Program","Risk/Acuity"));		
		
		ArrayList<String> caseDetailsValuesFromDB = new ArrayList<String>(Arrays.asList(GlobalValues.caseId, GlobalValues.DBCaseOwnerDisplayName, GlobalValues.DBCaseStatus,"","",GlobalValues.DBProgramName,GlobalValues.DBRiskOrAcuity));
		//+GlobalValues.DBCaseOwnerFirstName+GlobalValues.DBCaseOwnerLastName //
		Log.info("Case Details Keys "+ caseDetailsKeys);
		Log.info("Case Details Values from MongoDB = "+ caseDetailsValuesFromDB);
		Thread.sleep(GlobalValues.Sleep_wait_time);
		List<List<String>> caseDetailsList =  getCaseDetailsFromMemberOverviewUI();
		
		List <String> caseDetailsLabels = caseDetailsList.get(0);
		List <String> caseDetailsValues = caseDetailsList.get(1);
				
				
		for(int i =0 ; i<caseDetailsKeys.size(); i++){
			if(!(caseDetailsLabels.get(i).contains(caseDetailsKeys.get(i)))){
				Log.info(caseDetailsLabels.get(i) +" - Case Details Field Labels mismatch = " + caseDetailsKeys.get(i));
				pass=false;				
			}
			if(!(caseDetailsValues.get(i).contains(caseDetailsValuesFromDB.get(i)))){
				Log.info(caseDetailsValues.get(i) +" - Case Details Field Values mismatch = " + caseDetailsValuesFromDB.get(i));
				pass=false;				
			}
			
		}
		
		if(pass==true){
			Log.info("**********Case Details Of Member Overview Passed********** " );
			
			
		}else {
			Log.info("!!!!!!!!!!Case Details Of Member Overview Failed!!!!!!!!!! " );
			
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public List<List<String>> getContactDetailsFromMemberOverviewUI() throws InterruptedException{
		Log = Logger.getLogger("memberOverviewPage.class");
		PropertyConfigurator.configure("log4j.properties");
		Thread.sleep(GlobalValues.Sleep_wait_time);
		WebElement contactDetailsTileHeaderElement = driver.findElement(By.xpath("//div//h2[contains(text(),'Contact Details')]"));
		String contactDetailsTileHeaderText = contactDetailsTileHeaderElement.getText().trim();
		Assert.assertEquals("Contact Details", contactDetailsTileHeaderText, "Contact Details Tile Header is not Matching");
		
		
		Thread.sleep(GlobalValues.Sleep_wait_time);
		List<String> contactDetailsLabels = new ArrayList<String>() ;
		List<WebElement> contactDetailsLabelElements = driver.findElements(By.xpath("//nm-section//div[@id = 'memberContactDetailsSection']//label"));
		for(WebElement contactDetailsLabelWebElement: contactDetailsLabelElements){
			contactDetailsLabels.add(contactDetailsLabelWebElement.getText().trim());
		}
		Log.info("Got the fields for Contact Details Labels from Member Overview of UI" + contactDetailsLabels);
	
			
		List<String> contactDetailsValues = new ArrayList<String>() ;
		List<WebElement> contactDetailsValueElements = driver.findElements(By.xpath("//nm-section//div[@id = 'memberContactDetailsSection']//div[@class='form-group ng-star-inserted']//span"));
		for(WebElement contactDetailsValueWebElement: contactDetailsValueElements){
			contactDetailsValues.add(contactDetailsValueWebElement.getText().trim());
		}
		Log.info("Got the Values for Case Details Values from Member Overview of UI" + contactDetailsValues);
		
		List<List<String>> contactDetailsLabelsValues = new ArrayList<List<String>>();
		contactDetailsLabelsValues.add(contactDetailsLabels);
		contactDetailsLabelsValues.add(contactDetailsValues);
		
		return contactDetailsLabelsValues;
	}


	
	
	public void verifyContactDetailsFields()throws Exception{
		Log = Logger.getLogger("memberOverviewPage.class");
		PropertyConfigurator.configure("log4j.properties");
		Global_Method gm = new Global_Method(driver);
		Boolean pass = true;
		DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
			
		try{
		ArrayList<String> contactDetailsKeys = new ArrayList<String>(Arrays.asList("Address","Phone","Time Zone","Best Day to Call","Best Time to Call","Preferred Communication Method","Preferred Language","Email"));		
		
		ArrayList<String> contactDetailsValuesFromDB = new ArrayList<String>(Arrays.asList(GlobalValues.DBAddress,GlobalValues.DBContactPhone,GlobalValues.DBTimeZone,"",GlobalValues.DBBestTimeToCall,GlobalValues.DBPreferdCommunicationMethod,GlobalValues.DBLanguage,GlobalValues.DBEmail));
		//+GlobalValues.DBCaseOwnerFirstName+GlobalValues.DBCaseOwnerLastName //
		Log.info("Contact Details Keys"+ contactDetailsKeys);
		Log.info("Contact Details Values from MongoDB = "+ contactDetailsValuesFromDB);
		Thread.sleep(GlobalValues.Sleep_wait_time);
		List<List<String>> contactDetailsList =  getContactDetailsFromMemberOverviewUI();
		List<String> contactDetailsLabels = contactDetailsList.get(0);
		List<String> contactDetailsValues = contactDetailsList.get(1);
		
		for(int i =0 ; i<contactDetailsKeys.size(); i++){
			if(!(contactDetailsLabels.get(i).contains(contactDetailsKeys.get(i)))){
				Log.info(contactDetailsLabels.get(i) +" - Contact Details Field Labels mismatch = " + contactDetailsKeys.get(i));
				pass=false;				
			}
			if(!(contactDetailsValues.get(i).contains(contactDetailsValuesFromDB.get(i)))){
				Log.info(contactDetailsValues.get(i) +" - Contact Details Field Values mismatch = " + contactDetailsValuesFromDB.get(i));
				pass=false;				
			}
			
		}
		
		if(pass==true){
			Log.info("**********Contact Details Of Member Overview Passed********** " );
			
			
		}else {
			Log.info("!!!!!!!!!!Contact Details Of Member Overview Failed!!!!!!!!!! " );
			
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}