package com.nimbus.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.ConfigReader;

public class AddingMedicationPage {
	
	WebDriver driver;
	Logger Log;
	ConfigReader config = new ConfigReader();

	public AddingMedicationPage(WebDriver ldriver) {
		this.driver = ldriver;
	}

	public AddingMedicationPage(Logger lLog) {
		this.Log = lLog;
	}
	
	public void adddingMedicationCymbalta() throws Exception{
		Log = Logger.getLogger("AddProviderAction.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getMedicationsFromSubSection()))).click();
		Log.info("click on Medications");
		//Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getAddMedicationFromGrid()))).click();
		Log.info("click  on add Medications from grid");
		Thread.sleep(3000);
		driver.findElement(By.xpath(config.getMedicationSeachTerm())).clear();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getMedicationSeachTerm()))).sendKeys("cym");
		Log.info("click  on add Medications Search Term"); 
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getClickSearchButtonFromMedication()))).click();
		Log.info("click  on Search Button"); 
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getAddCapsuleMedicationCym()))).click();
		Log.info("click  on Cymbalta");
	}
	public void adddingMedicationStrattera() throws Exception{
		Log = Logger.getLogger("AddProviderAction.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		
		try
		{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		/*wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getMedicationsFromSubSection()))).click();
		Log.info("click on Medications");*/
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getAddMedicationFromGrid()))).click();
		Log.info("click  on add Medications from grid");
		Thread.sleep(1000);
		driver.findElement(By.xpath(config.getMedicationSeachTerm())).clear();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getMedicationSeachTerm()))).sendKeys("str");
		Log.info("click  on add Medications Search Term"); 
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getClickSearchButtonFromMedication()))).click();
		Log.info("click  on Search Button"); 
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getAddCapsuleMedicationSTR()))).click();
		Log.info("click  on Strattera");
		}
		catch(Exception Ex)
		{
			Log.info(Ex.getMessage().toString());
		
		}
}
	public void adddingMedicationQuinidine() throws Exception{
		Log = Logger.getLogger("AddProviderAction.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		/*wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getMedicationsFromSubSection()))).click();
		Log.info("click on Medications");*/
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getAddMedicationFromGrid()))).click();
		Log.info("click  on add Medications from grid");
		Thread.sleep(1000);
		driver.findElement(By.xpath(config.getMedicationSeachTerm())).clear();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getMedicationSeachTerm()))).sendKeys("qui");
		Log.info("click  on add Medications Search Term"); 
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getClickSearchButtonFromMedication()))).click();
		Log.info("click  on Search Button"); 
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getQuinidineGluconate()))).click();
		Log.info("click  on Quinidine Gluconate");
}
	public void adddingMedicationZyprexa() throws Exception{
		Log = Logger.getLogger("AddProviderAction.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getMedicationsFromSubSection()))).click();
		Log.info("click on Medications");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getAddMedicationFromGrid()))).click();
		Log.info("click  on add Medications from grid");
		Thread.sleep(1000);
		driver.findElement(By.xpath(config.getMedicationSeachTerm())).clear();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getMedicationSeachTerm()))).sendKeys("zyp");
		Log.info("click  on add Medications Search Term"); 
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getClickSearchButtonFromMedication()))).click();
		Log.info("click  on Search Button"); 
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getAddZyprexa()))).click();
		Log.info("click  on Zyprexa");
}
	public void adddingMedicationVerzenio() throws Exception{
		Log = Logger.getLogger("AddProviderAction.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		/*wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getMedicationsFromSubSection()))).click();
		Log.info("click on Medications");*/
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getAddMedicationFromGrid()))).click();
		Log.info("click  on add Medications from grid");
		Thread.sleep(2000);
		driver.findElement(By.xpath(config.getMedicationSeachTerm())).clear();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getMedicationSeachTerm()))).sendKeys("ver");
		Log.info("click  on add Medications Search Term"); 
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getClickSearchButtonFromMedication()))).click();
		Log.info("click  on Search Button"); 
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getAddVerzenio()))).click();
		Log.info("click  on Verzenio");
		
	}
	
	public boolean isAscendingSorted(String columnName){
		String columnSortIconStatusXpath = config.getColumnSortIcon().replace("COL_NAME", columnName);
		String columnSortIconStatus = driver.findElement(By.xpath(columnSortIconStatusXpath)).getAttribute("class");
		if(!columnSortIconStatus.contains("pi-sort-up")){
			Log.info("Sort icon has not been changed for "+columnName);
			return false;
		}
		String columnNumXpath = config.getColumnNumber().replace("COL_NAME", columnName);
		int size = driver.findElements(By.xpath(columnNumXpath)).size();
		String columnNum = String.valueOf(size + 1);
		String columnValuesXpath = config.getColumnValues().replace("COL_NUM", columnNum);
		List<WebElement> eleArr = driver.findElements(By.xpath(columnValuesXpath));
		
		List<String> colValues = new ArrayList<String>();
		List<String> sortedValues = new ArrayList<String>();
		for(WebElement ele: eleArr){
			String str = ele.getText();
			colValues.add(str);
			sortedValues.add(str);
		}
		Collections.sort(sortedValues);
		
		boolean isEqual = true;
		for(int i=0;i<colValues.size();i++){
			if(!colValues.get(i).equals(sortedValues.get(i))){
				isEqual = false;
				break;
			}
		}
		return isEqual;
	}
	
	public boolean isDescendingSorted(String columnName){
		String columnSortIconStatusXpath = config.getColumnSortIcon().replace("COL_NAME", columnName);
		String columnSortIconStatus = driver.findElement(By.xpath(columnSortIconStatusXpath)).getAttribute("class");
		if(!columnSortIconStatus.contains("pi-sort-down")){
			Log.info("Sort icon has not been changed for "+columnName);
			return false;
		}
		
		String columnNumXpath = config.getColumnNumber().replace("COL_NAME", columnName);
		int size = driver.findElements(By.xpath(columnNumXpath)).size();
		String columnNum = String.valueOf(size + 1);
		String columnValuesXpath = config.getColumnValues().replace("COL_NUM", columnNum);
		List<WebElement> eleArr = driver.findElements(By.xpath(columnValuesXpath));
		
		List<String> colValues = new ArrayList<String>();
		List<String> sortedValues = new ArrayList<String>();
		for(WebElement ele: eleArr){
			String str = ele.getText();
			colValues.add(str);
			sortedValues.add(str);
		}
		Collections.sort(sortedValues, Collections.reverseOrder());
		
		boolean isEqual = true;
		for(int i=0;i<colValues.size();i++){
			if(!colValues.get(i).equals(sortedValues.get(i))){
				isEqual = false;
				break;
			}
		}
		return isEqual;
	}
	
	public boolean isAscendingSortedFromHistory(String columnName){
		String columnSortIconStatusXpath = config.getColumnSortIconMedicationHistory().replace("COL_NAME", columnName);
		String columnSortIconStatus = driver.findElement(By.xpath(columnSortIconStatusXpath)).getAttribute("class");
		if(!columnSortIconStatus.contains("pi-sort-up")){
			Log.info("Sort icon has not been changed for "+columnName);
			return false;
		}
		String columnNumXpath = config.getColumnNumberPositionMedicationHistory().replace("COL_NAME", columnName);
		int size = driver.findElements(By.xpath(columnNumXpath)).size();
		String columnNum = String.valueOf(size + 1);
		String columnValuesXpath = config.getColumnValuesMedicationHistory().replace("COL_NUM", columnNum);
		List<WebElement> eleArr = driver.findElements(By.xpath(columnValuesXpath));
		
		List<String> colValues = new ArrayList<String>();
		List<String> sortedValues = new ArrayList<String>();
		for(WebElement ele: eleArr){
			String str = ele.getText();
			colValues.add(str);
			sortedValues.add(str);
		}
		Collections.sort(sortedValues);
		
		boolean isEqual = true;
		for(int i=0;i<colValues.size();i++){
			if(!colValues.get(i).equals(sortedValues.get(i))){
				isEqual = false;
				break;
			}
		}
		return isEqual;
	}
	
	public boolean isDescendingSortedFromHistory(String columnName){
		String columnSortIconStatusXpath = config.getColumnSortIconMedicationHistory().replace("COL_NAME", columnName);
		String columnSortIconStatus = driver.findElement(By.xpath(columnSortIconStatusXpath)).getAttribute("class");
		if(!columnSortIconStatus.contains("pi-sort-down")){
			Log.info("Sort icon has not been changed for "+columnName);
			return false;
		}
		
		String columnNumXpath = config.getColumnNumberPositionMedicationHistory().replace("COL_NAME", columnName);
		int size = driver.findElements(By.xpath(columnNumXpath)).size();
		String columnNum = String.valueOf(size + 1);
		String columnValuesXpath = config.getColumnValuesMedicationHistory().replace("COL_NUM", columnNum);
		List<WebElement> eleArr = driver.findElements(By.xpath(columnValuesXpath));
		
		List<String> colValues = new ArrayList<String>();
		List<String> sortedValues = new ArrayList<String>();
		for(WebElement ele: eleArr){
			String str = ele.getText();
			colValues.add(str);
			sortedValues.add(str);
		}
		Collections.sort(sortedValues, Collections.reverseOrder());
		
		boolean isEqual = true;
		for(int i=0;i<colValues.size();i++){
			if(!colValues.get(i).equals(sortedValues.get(i))){
				isEqual = false;
				break;
			}
		}
		return isEqual;
	}
	
}