	package com.nimbus.pages;

	import java.util.List;

import org.apache.log4j.Logger;
	import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import excelReader.DataReaderExcel;
	import utility.ConfigReader;

	public class CaseClosure {
		ConfigReader config = new ConfigReader();
		DataReaderExcel DataRead = new DataReaderExcel();
		
		WebDriver driver;
		Logger Log;
		
		public CaseClosure(WebDriver ldriver){
			this.driver=ldriver;
		}
		
		public CaseClosure(Logger lLog){
			this.Log=lLog;
		}
		
	public void caseClosure() throws Exception
		{
		
	  		Log = Logger.getLogger("CreateCase.class");
			PropertyConfigurator.configure("log4j.properties");
			AnnualAssessment annual = new AnnualAssessment(driver);
			MemberSearch ms = new MemberSearch(driver);
			Log.info("Case Closure Started");
			
			 try
			 {
				 WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
				 String CaseIdtext= driver.findElement(By.xpath(config.getBannerCaseID())).getText().toString().trim();
			     Log.info("CaseIdtext::"+CaseIdtext);
			    /* wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getMgmtLeftNav()))).click();
			     Log.info("Clicked on Management Reason");   
			     wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getAddMgmtReason()))).click();
			     Log.info("Clicked on Add Management Reason");  
			     Thread.sleep(3000);
			     try
			     {
			     annual.annual_assessment("AddMgmt_caseClosuer","Add",2);
			     }
			     catch(Exception e)
			     {
			    	 Log.info("Not added already");
			     }
			     wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getOverviewLeftNav()))).click();
			     Log.info("Clicked on Overview"); */
			     wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getCaseClosurebutton()))).click();
			     Log.info("Clicked on Case Closure");
			     try
			      {
			    	 Thread.sleep(3000);
			    	 WebElement element = driver.findElement(By.xpath(config.getCaseClosureModalHeader()));
					String headertext = element.getText().toString().trim();
					Assert.assertEquals("Case Closure Confirmation", headertext);
					Log.info("Modal Header is- "+headertext);
					Thread.sleep(1000);
					WebElement questionelement = driver.findElement(By.xpath(config.getQuestiontext()));
					String questiontext = questionelement.getText().toString().trim();
					Log.info(questiontext);
					Log.info(GlobalValues.caseclosurequestion);
					Assert.assertEquals(questiontext,GlobalValues.caseclosurequestion);
					Log.info("Question is as expected");
					Thread.sleep(10000);
					annual.annual_assessment("AnnualAssessments_CMDM","Contact",2);
			      }
				catch(Exception Ex)
		      
				{
					Log.info("Question and Modal header is not expected ");
					Ex.printStackTrace();
				}
			     Thread.sleep(3000);
			     
			    
			     ms.memberSearchToLeftNav();
			     
			     
//			     wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='button' and @title='Member Search']"))).click();
//			     Thread.sleep(3000);
//			     wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getCaseIdXpath()))).clear();
//			     wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getCaseIdXpath()))).sendKeys(CaseIdtext);
//			     wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getSearchButtonXpath()))).click();
//			     wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getSearchButtonXpath()))).click();
//			     Thread.sleep(3000);
			     
//			     wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='fa-search fa']"))).click();
//                 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[normalize-space()='Case ID #']//following-sibling::input"))).clear();
//                 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[normalize-space()='Case ID #']//following-sibling::input"))).sendKeys(CaseIdtext);
//                 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Search']"))).click();
//                 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table/thead/tr/th/span[contains(text(),'Case ID #')]/ancestor::thead/following-sibling::tbody/tr/td//span//a[@title = '" +CaseIdtext+ "']"))).click();
//                 Thread.sleep(3000);
	     
			     String CaseStatustext= driver.findElement(By.xpath(config.getBannerCaseStatus())).getText().toString().trim();
			     Assert.assertEquals(CaseStatustext, "Closed");
			     Log.info("Case Status is "+CaseStatustext);
			     
			     wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getMgmtLeftNav()))).click();
			     //Log.info("Clicked on Management Reason");  
			     Thread.sleep(3000);
			     try
			     {
			     List <WebElement> MgmtStatus=driver.findElements(By.xpath(config.getMgmtReasonstatus()));
			     for (int i=0;i<MgmtStatus.size();i++)
			     {
			    	 try{
			    		//Log.info("Mgmt status of "+i+"::"+MgmtStatus.get(i).getText()); 
			    		 Assert.assertEquals("Closed", MgmtStatus.get(i).getText());
					     Log.info("Management Reason Status is Closed");
					     }
			    	 catch(Exception e)
				     {
				    	 Log.info("Management Reason Status is not Closed"+e);
				     }
			   	 
			     }
			     }
			     catch(Exception e )
			     {
			    	 Log.info("No added management to check status");
			     }
			     
			     
			     wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getMemberActionLeftNav()))).click();
			     wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getActivities()))).click();
			     //Log.info("Clicked on Member Action Center	");  
			    Thread.sleep(3000);
			     List <WebElement> ActivityStatus=driver.findElements(By.xpath(config.getActivityStatus()));
			     for (int i=0;i<ActivityStatus.size();i++)
			     {
			    	 try{
			    		// Log.info("Activity status of "+i+"::"+ActivityStatus.get(i).getText()); 
			    		 Assert.assertEquals("Cancelled", ActivityStatus.get(i).getText());
					     Log.info("Activity Status is Cancelled");
					     }
			    	 catch(Exception e)
				     {
				    	 Log.info("Activity Status is not Cancelled"+e);
				     }
			    	 	 
			     }
			      
			     Log.info("Verified Case Closure ");
			    
			 }
			catch(Exception ex)
			 {
				Log.info("Case closure failed"+ex);
				ex.printStackTrace();
			 }

	}
	}

