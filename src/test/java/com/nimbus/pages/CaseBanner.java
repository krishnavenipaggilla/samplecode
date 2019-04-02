package com.nimbus.pages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import utility.ConfigReader;
/*
 * Class Name : CaseBanner

 * Description:	Get details for case banner from MOngoDB
 */

public class CaseBanner{

	ConfigReader config = new ConfigReader();

	WebDriver driver;
	Logger Log;
	

	public CaseBanner(WebDriver ldriver) {
		this.driver = ldriver;
	}

	public CaseBanner(Logger lLog) {
		this.Log = lLog;
	}


	public List<List<String>> getElementsFromCaseBanner(){
		Log = Logger.getLogger("CaseBanner.class");
		PropertyConfigurator.configure("log4j.properties");
		
		List<String> caseBannerLabels = new ArrayList<String>() ;
		List<WebElement> caseBannerLabelElements = driver.findElements(By.xpath("//nm-card-details-field-group//nm-card-details-field//label"));
		for(WebElement caseBannerLabelWebElement: caseBannerLabelElements){
			caseBannerLabels.add(caseBannerLabelWebElement.getText().trim());
		}
		Log.info("Got the fields/Labels from UI for Case Banner" + caseBannerLabels);
		
		
		List<String> caseBannerValues = new ArrayList<String>() ;
		
		List<WebElement> caseBannerValueElements = driver.findElements(By.xpath("//nm-card-details-field-group//nm-card-details-field//span"));
		for(WebElement caseBannerValueWebElement: caseBannerValueElements){
			caseBannerValues.add(caseBannerValueWebElement.getText().trim());
		}
		Log.info("Got the Values from UI for Case Banner" + caseBannerValues);
		
		
		List<List<String>> caseBannerLabelsValues = new ArrayList<List<String>>();
		caseBannerLabelsValues.add(caseBannerLabels);
		caseBannerLabelsValues.add(caseBannerValues);
		
		return caseBannerLabelsValues;
	}
	
	public void verifyCaseBannerFields()throws Exception{
		Log = Logger.getLogger("CaseBanner.class");
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
			
		WebElement caseBannerHeaderElement = driver.findElement(By.xpath("//p-header/span"));
		String caseBannerHeaderText = caseBannerHeaderElement.getAttribute("textContent").trim();
		Assert.assertEquals(caseBannerHeaderText, GlobalValues.DBFirstNameFromCasesCollection+" "+GlobalValues.DBLastNameFromCasesCollection , "Case Banner Header Verification Failed");
		
			
		ArrayList<String> CaseBannerKeys = new ArrayList<String>(Arrays.asList("DOB","Age","Gender","Non-Member Permission","Preferred Phone #","","Member Contact Time","Case ID","Program Name","Co-Managed","Case Owner","Case","Case Status","Specialty Products"));		
		
		//ArrayList<String> CaseBannerValues = new ArrayList<String>(Arrays.asList(GlobalValues.DBMemberFirstName+" "+GlobalValues.DBMemberLastName,GlobalValues.DBDOB,GlobalValues.calculateAge(dob)+"",GlobalValues.DBGender,"Yes",GlobalValues.DBPhoneNumber/*GlobalValues.DBExpressConsent*/,"","",/*+GlobalValues.DBBestTimeToCall*/GlobalValues.caseId,GlobalValues.DBProgramName,"","Enrolled", GlobalValues.DBCaseStatus,"",GlobalValues.DBCaseOwnerDisplayName));
		
		ArrayList<String> CaseBannerValuesFromDB = new ArrayList<String>(Arrays.asList(GlobalValues.DBDOB,GlobalValues.calculateAge(dob)+"",GlobalValues.DBGender,"Yes",GlobalValues.DBPhoneNumber/*GlobalValues.DBExpressConsent*/,"",GlobalValues.DBBestTimeToCall,GlobalValues.caseId,GlobalValues.DBProgramName,"",GlobalValues.DBCaseOwnerDisplayName,"Enrolled", GlobalValues.DBCaseStatus,""));
		//+GlobalValues.DBCaseOwnerFirstName+GlobalValues.DBCaseOwnerLastName //
		Log.info("Case Banner Keys "+ CaseBannerKeys);
		Log.info("Case Banner Values from MongoDB = "+ CaseBannerValuesFromDB);
		Thread.sleep(GlobalValues.Sleep_wait_time);
		List<List<String>> bannerList =  getElementsFromCaseBanner();
		List<String> bannerLabels = bannerList.get(0);
		List<String> bannerValues = bannerList.get(1);
		
		for(int i =0 ; i<CaseBannerKeys.size(); i++){
			if(!(bannerLabels.get(i).contains(CaseBannerKeys.get(i)))){
				Log.info(bannerLabels.get(i) +" - Case Banner Field Labels mismatch = " + CaseBannerKeys.get(i));
				pass=false;				
			}
			if(!(bannerValues.get(i).contains(CaseBannerValuesFromDB.get(i)))){
				Log.info(bannerValues.get(i) +" - Case Banner Field Values mismatch = " + CaseBannerValuesFromDB.get(i));
				pass=false;				
			}
			
		}
		
		if(pass==true){
			Log.info("**********Banner Verification Passed**********" );
			gm.Reports("NIM-10333","Pass");
			
		}else {
			Log.info("!!!!!!!!!!Banner Verification Failed!!!!!!!!!!" );
			gm.Reports("NIM-10333","Fail");
		}
		}catch(Exception e){
			e.printStackTrace();
			Log.info("!!!!!!!!!!Banner Verification Failed!!!!!!!!!!" );
		}
	}
}
