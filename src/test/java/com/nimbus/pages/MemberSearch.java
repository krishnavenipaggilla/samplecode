package com.nimbus.pages;

import java.io.File;
import java.io.FileInputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import utility.ConfigReader;
/*
 * Class Name : MemberSearch

 * Description:	Search for member
 */
public class MemberSearch {

	WebDriver driver;
	Logger Log;

	ConfigReader config = new ConfigReader();
	public static String MemberSearchFirstname;
	public static String MemberSearchlastname;
	public static String MedicaidId;
	public static String MedicareId;
	public static String SubscriberId;
	public static String DateofBirth;
	public static String MemberId;
	public static String caseId;
	public static String gender;
	public static String phoneNo;
	public static String state;
	public static String zipCode;
	public static String eligibilityStatus;

	public static Boolean resultFlag = false;
	Map<String, String> map = new HashMap<String, String>();

	public MemberSearch(WebDriver ldriver) {
		this.driver = ldriver;
	}

	public MemberSearch(Logger lLog) {
		this.Log = lLog;
	}

	public void navigateToMemberSearch() {
		try {
			Log = Logger.getLogger("MemberSearch.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			Thread.sleep(GlobalValues.Sleep_wait_time);

			WebElement memberSearchLinkElement = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getMemberSearchLinkXpath())));
			memberSearchLinkElement.click();
			Log.info("Clicked on Member Search on Home Page");
			Thread.sleep(GlobalValues.Sleep_wait_time);

			WebElement actualElement = driver.findElement(By.xpath(config.getSearchTitle()));
			Thread.sleep(GlobalValues.Sleep_wait_time);
			String actualTitle = actualElement.getText();
			Log.info("Verify the Title of the page ");
			Assert.assertEquals("Search", actualTitle, "Page Title is not matching");

		} catch (Exception e) {
			Log.info("Not on Search Page - " + e);
			e.printStackTrace();
		}

	}

	public void setValues(String xPath, String Value) {
		try {
			Log = Logger.getLogger("MemberSearch.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

			WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
			element.sendKeys(Value);
			Log.info("The field value entered : " + Value);
			Thread.sleep(GlobalValues.Sleep_wait_time);

		} catch (Exception ex) {
			Log.info("Error in setValues() method :" + ex);
		}
	}

	public void verifyGridData(String searchText) {
		try {
			Log = Logger.getLogger("MemberSearch.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			String gridtAllText = wait.until(ExpectedConditions.elementToBeClickable(By.id("vsCMSMemberSearchResults")))
					.getText().toString();

			try {
				Assert.assertTrue(gridtAllText.toLowerCase().contains(searchText.toLowerCase()));
				Log.info("**********Record matched for search text : " + searchText);
				resultFlag = true;
			} catch (Error err1) {
				try {
					Assert.assertTrue(gridtAllText.toLowerCase().contains("no records found"));
					Log.info("!!!!!!!!!No Records found for verification");
				} catch (Error err2) {
					Log.info("!!!!!!!Record does not match");
				}

			}

		} catch (Exception ex) {
			Log.info("!!!!!!!!Error in verifyGridData() method :" + ex);
		}

	}

	// This Method search for member based on any field user wants to search
	// with//

	public void MemberSearchSingleField(Boolean clickViewFlag) throws Throwable {
		Log = Logger.getLogger("MemberSearch.class");
		String xPath = "";
		String filterText = "";
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

		Map<String, String> filterFields = GlobalValues.mapFilter;

		Map<String, String> map = new HashMap<String, String>();
		map.put("First_Name", config.getFirstName());
		map.put("Last_Name", config.getLastName());
		/*
		 * map.put("Medicaid_Id", config.getMedicaidId());
		 * map.put("Medicare_Id", config.getMedicareId());
		 * map.put("Subscriber_Id", config.getSubscriberId());
		 */
		map.put("Date_Of_Birth", config.getDateOfBirth());
		map.put("Member_Id", config.getMemberId());
		map.put("Case_Id", config.getCaseId());
		map.put("Gender", config.getGender());
		map.put("Phone_Num", config.getPhoneNum());
		map.put("State", config.getState());
		map.put("ZipCode", config.getZipCode());
		map.put("Eligibility_Status", config.getEligibilityStatus());

		/*
		 * WebElement MemberSearchLink = (new WebDriverWait(driver, 20))
		 * .until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.
		 * getSearchButton()))); MemberSearchLink.click();
		 * Log.info("Click on Member Search");
		 * 
		 * 
		 * for (String mapKey : map.keySet()) { WebElement element =
		 * driver.findElement(By.xpath(map.get(mapKey)));
		 * element.sendKeys(Keys.CONTROL + "a"); element.sendKeys(Keys.DELETE);
		 * }
		 */
		for (String item : filterFields.keySet()) {
			xPath = map.get(item);
			filterText = filterFields.get(item);
			if (filterText != "" && filterText != null)
				setValues(xPath, filterText);
		}

		// Click on MemberSearch button
		WebElement SearchMemberBtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getSearchButton())));
		SearchMemberBtn.click();
		Log.info("Click on member search button");
		Thread.sleep(GlobalValues.Sleep_wait_time);
		for (String item : filterFields.keySet()) {
			filterText = filterFields.get(item);
			if (filterText != "" && filterText != null)
				verifyGridData(filterText);
			Thread.sleep(GlobalValues.Sleep_wait_time);
		}

		if (resultFlag == true && clickViewFlag == true) {
			WebElement Visibilityofelement = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getElementVisibility())));

			WebElement SearchResultViewCase = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getViewCaseDropDown())));
			SearchResultViewCase.click();
			Log.info("Click on view case in Grid");

			WebElement SelectViewCase = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getViewCaseClicked())));
			SelectViewCase.click();
			Log.info("View Case is clicked");

		}

	}

	public void openFirstCaseDetail() {
		Log = Logger.getLogger("MemberSearch.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='scrollGrid']//tr)[2]//td[1]//a")))
				.click();
		Log.info("First case has been opened");
	}

	public void memberSearchToLeftNav() throws Exception {
		Log = Logger.getLogger("MemberSearch.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		Thread.sleep(GlobalValues.Sleep_wait_time);


		try {


			navigateToMemberSearch();

			if (GlobalValues.caseId != null) {

				setValues(config.getCaseIdXpath(), GlobalValues.caseId);
				// driver.findElement(By.xpath(config.getCaseIdXpath())).sendKeys(GlobalValues.caseId);
				Log.info("Entered the Case number from MongoDB in the CaseID textbox");
				WebElement searchButton = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getSearchButtonXpath())));
				Thread.sleep(GlobalValues.Sleep_wait_time);
				searchButton.click();
				searchButton.click();

				Log.info("Clicked on Search after entering the Case ID = " + GlobalValues.caseId);
				Thread.sleep(GlobalValues.Sleep_wait_time);

				WebElement searchResultsGridHeader = wait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p-header")));
				String searchResultsgridHeader = searchResultsGridHeader.getText();
				Assert.assertEquals(searchResultsgridHeader, "Search Results", "You are in Wrong grid");
				Log.info("Grid Name is " + searchResultsgridHeader);
				List<WebElement> searchResultElements = driver.findElements(By.xpath(config.getSearchResultsXpath()));
				Assert.assertTrue((searchResultElements.size() > 0), "No records found in the Search Results Grid");
				
				WebElement element = driver.findElement(By.xpath("//table/thead/tr/th/span[contains(text(),'Case ID #')]/ancestor::thead/following-sibling::tbody/tr/td//span//a [@title = '"
						+ GlobalValues.caseId + "'] "));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
				Thread.sleep(GlobalValues.Sleep_wait_time);

				Thread.sleep(GlobalValues.Sleep_wait_time);
				WebElement caseIdElementVisibility = wait.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//table/thead/tr/th/span[contains(text(),'Case ID #')]/ancestor::thead/following-sibling::tbody/tr/td//span//a [@title = '"
								+ GlobalValues.caseId + "'] ")));

				Thread.sleep(GlobalValues.Sleep_wait_time);
				caseIdElementVisibility.click();
				Log.info("Clicked on CaseID Link");

				Thread.sleep(GlobalValues.Sleep_wait_time);
				Log.info("Clicked on " + GlobalValues.caseId + " case ID");
				Thread.sleep(GlobalValues.Sleep_wait_time);
			} else {
				driver.findElement(By.xpath(config.getClearButton()));
				Log.info("Clicked on Clear button");
				Thread.sleep(GlobalValues.Sleep_wait_time);
				WebElement paginationElement = driver.findElement(By.xpath(config.getPaginationElement1Xpath()));
				boolean paginationElementStatus = paginationElement.isEnabled();
				if (paginationElementStatus = true) {
					paginationElement.click();
					Thread.sleep(GlobalValues.Sleep_wait_time);
				} else {
					List<WebElement> searchResultElements = driver
							.findElements(By.xpath(config.getSearchResultsXpath()));
					if (!(searchResultElements.size() > 0)) {
						Log.info("No records found in the Search Results Grid");
						driver.findElement(By.xpath("//a[text()='Log Out']")).click();
					}
				}
				WebElement lastCaseIdFromGrid = driver.findElement(By.xpath(config.getLastCaseIdFromGridXpath()));
				lastCaseIdFromGrid.click();
				String caseId = lastCaseIdFromGrid.getText();
				Thread.sleep(GlobalValues.Sleep_wait_time);

				Log.info("Clicking the case ID on the last row from the grid - " + caseId);
				GlobalValues.caseId = caseId;
				MongoDbConnection mongoconnection = new MongoDbConnection(driver);
				mongoconnection.getCaseDetails(false, false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.info(e);

		}

	}

}
