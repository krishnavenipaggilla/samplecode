package com.nimbus.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utility.ConfigReader;
import utility.WebActions;

public class ActivityProviderSubSection {

	WebDriver driver;
	Logger Log;
	ConfigReader config = new ConfigReader();

	public ActivityProviderSubSection(WebDriver ldriver) {
		this.driver = ldriver;
	}

	public ActivityProviderSubSection(Logger lLog) {
		this.Log = lLog;
	}

	public void ProviderManagement(String TaskName) throws Exception {
		try {
			String listVal = null;
			List<String> actualoptions = null;
			Global_Method GM = PageFactory.initElements(driver, Global_Method.class);
			WebActions webact = new WebActions();

			Log = Logger.getLogger("ActivityProviderSubSection.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			LeftNavigationLink leftNav = PageFactory.initElements(driver, LeftNavigationLink.class);
			Log.info("Click on Edit initialoutreach activity");
			ActivityPage addActivity = new ActivityPage(driver);
			addActivity.editInitialOutreachActivity(TaskName);

			List<WebElement> defaultSubActivitiesElementsFromUI = driver
					.findElements(By.xpath("//nm-section//a[@role = 'tab']//p-header//h2"));
			boolean isFound = false;

			for (int i = 0; i < defaultSubActivitiesElementsFromUI.size(); i++) {
				if (defaultSubActivitiesElementsFromUI.get(i).getText().contains("Member Representatives")) {
					isFound = true;
					break;
				}
			}

			if (isFound) {
				Log.info("Verified Provider sub-section displayed as a default Sub section in Activities");
				Log.info("Click on Provider accordion");
				WebElement accordionProvider = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getProvider_xpath())));
				accordionProvider.click();
				Log.info("Click on add provider");
				;
				Thread.sleep(GlobalValues.Sleep_wait_time);
			} else {

				driver.findElement(By.xpath("//button[@title = 'Add Sub Activity' and @type ='button']")).click();
				Log.info("Clicked on add subactivity Icon");
				String subActivityModelWindowHeader = driver
						.findElement(By.xpath("//p-header[contains(text(),'Add Sub Activity')]")).getText().trim();
				Assert.assertEquals(subActivityModelWindowHeader, "Add Sub Activity");

				driver.findElement(By.xpath("//label[contains(text(),'Provider')]//..//label")).click();
				Log.info("Adding provider sub section from the modal window");
				Thread.sleep(GlobalValues.Sleep_wait_time);

				driver.findElement(By.xpath("//button[text()='Add']")).click();
				Log.info("Clicked on Add button of modal window");

				Thread.sleep(GlobalValues.Sleep_wait_time);

				Log.info("Click on Provider accordion");
				WebElement accordionProvider = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getProvider_xpath())));
				accordionProvider.click();
				Log.info("Click on add provider");
				;
				Thread.sleep(GlobalValues.Sleep_wait_time);

			}

			WebElement addProvider = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getAddProvider_xpath())));
			addProvider.click();
			WebElement careteampage = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getCareteampage())));
			String actcareteampage = careteampage.getText();
			if (actcareteampage.equalsIgnoreCase("Care Team")) {
				Log.info("Navigated to care team page after clicking on add provider");
			}
			WebElement defaultcareteamrole = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getDefaultcareteamrole())));
			String defaultcareteamrole1 = defaultcareteamrole.getText();
			if (defaultcareteamrole1.equalsIgnoreCase("Provider")) {
				Log.info("Default care team role displayed as Provider");
			}
			
			Log.info(
					"Passed-NIM-18439: Provider: Make role type read only when adding/editing provider from an activity"
							+ '\n'
							+ "GIVEN:Provider: Make role type read only when adding/editing provider from an activity"
							+ '\n'
							+ "THEN:verifying Provider: Make role type read only when adding/editing provider from an activity");

			WebElement createmanual = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getCreateManualProvider())));
			createmanual.click();
			if (TaskName.equalsIgnoreCase("Initial Outreach")) {
			WebElement addprovider = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getAddProviderTitle())));
			String addprovider1 = addprovider.getText();
			if (addprovider1.equalsIgnoreCase("Add Provider")) {
				Log.info("Navigated to add provider after clicking on create manual");
			}
			}
			else if (TaskName.equalsIgnoreCase("Care Coordination")) {
				WebElement addprovider = wait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getEditProviderLabel())));
				String addprovider1 = addprovider.getText();
				if (addprovider1.equalsIgnoreCase("Edit Provider")) {
					Log.info("Navigated to edit Provider after clicking on create manual");
				}
			}

			// verifying Title and provider speciality options

			for (int i = 2; i <= 3; i++) {

				WebElement TitleDrop = wait.until(ExpectedConditions.elementToBeClickable(
						By.xpath("(//label[contains(text(),'Please Select...')])[" + i + "]//following-sibling::div")));
				TitleDrop.click();
				Thread.sleep(2000);
				List<WebElement> options = driver.findElements(By.xpath(
						"(//label[contains(text(),'Please Select...')])[" + i + "]//following-sibling::div//li//span"));
				ArrayList<String> expctedoptions = new ArrayList<String>();
				for (int j = 0; j < options.size(); j++) {
					listVal = options.get(j).getText();
					Log.info("options---" + options.get(j).getText());
					expctedoptions.add(listVal);
					Log.info("Expected Options---" + expctedoptions);

				}

				if (i == 2) {
					actualoptions = GlobalValues.ProviderTitleOptions;
					GM.validateDropDownOptions(actualoptions, expctedoptions);
				} else if (i == 3) {
					if (expctedoptions.contains("Licensed Mh Counselor")) {
						Log.info("drop down options verified");
					}
				}
			}
			AnnualAssessment annual = new AnnualAssessment(driver);
			annual.annual_assessment("AnnualAssessments_CMDM", "CreateManual_Provider", 2);
			Thread.sleep(GlobalValues.Sleep_wait_time);
						
			if (TaskName.equalsIgnoreCase("Initial Outreach")) {
				WebElement initalpagesubmit = wait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getInitialoutreachpage())));
				String initalpagesubmit1 = initalpagesubmit.getText();
				if (initalpagesubmit1.equalsIgnoreCase("Initial Outreach")) {
					Log.info("Landed on Initial outreach page after clicking submit button");
				}
			} else if (TaskName.equalsIgnoreCase("Care Coordination")) {
				WebElement coordinationpage = wait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getCareCoordinationPage())));
				String coordinationpage1= coordinationpage.getText();
				if (coordinationpage1.equalsIgnoreCase("Care Coordination")) {
					Log.info("Landed on Care Coordination page after clicking submit button");
				}
			}
				
			if (TaskName.equalsIgnoreCase("Initial Outreach")) {
				Log.info(
						"Passed-NIM-17430: Provider management: Clean up of drop down options when adding an Individual as Provider on member's case"
								+ '\n'
								+ "GIVEN:Provider management: Clean up of drop down options when adding an Individual as Provider on member's case"
								+ '\n'
								+ "THEN:verifying Provider management: Clean up of drop down options when adding an Individual as Provider on member's case");
				Log.info("Passed-NIM-17581: Provider: Add provider by clicking create manual (from Initial Outreach)"
						+ '\n' + "GIVEN:Provider: Add provider by clicking create manual (from Initial Outreach)" + '\n'
						+ "THEN:verifying Provider: Add provider by clicking create manual (from Initial Outreach)");
			} else if (TaskName.equalsIgnoreCase("Care Coordination")) {
				Log.info(
						"Passed-NIM-17284: Provider: Add Provider sub section to Care Coordination (Grid and the Add function))"
								+ '\n'
								+ "GIVEN:Provider: Add Provider sub section to Care Coordination (Grid and the Add function)"
								+ '\n'
								+ "THEN:verifying Provider: Add Provider sub section to Care Coordination (Grid and the Add function)");
			}
		}

		catch (Exception e) {

			if (TaskName.equalsIgnoreCase("Initial Outreach")) {
				Log.info(
						"Failed-NIM-17430: Provider management: Clean up of drop down options when adding an Individual as Provider on member's case");
				Log.info("Failed-NIM-17581: Provider: Add provider by clicking create manual (from Initial Outreach)");
			} else if (TaskName.equalsIgnoreCase("Care Coordination")) {
				Log.info(
						"Failed-NIM-17284: Provider: Add Provider sub section to Care Coordination (Grid and the Add function)");
			}
			Log.info(
					"Failed-NIM-18439: Provider: Make role type read only when adding/editing provider from an activity");

		}
	}

	public void editProvider(String TaskName) throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			Log = Logger.getLogger("ActivityProviderSubSection.class");
			PropertyConfigurator.configure("log4j.properties");
			WebElement editbutton;
			WebElement accordionProvider;
			File sour = new File("./TestData/AnnualAssessments_CMDM.xlsx");
			FileInputStream fiss = new FileInputStream(sour);
			XSSFWorkbook wb = new XSSFWorkbook(fiss);
			XSSFSheet Sheet1 = wb.getSheet("CreateManual_Provider");
			accordionProvider = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getProvider_xpath())));
			accordionProvider.click();
			Log.info("Click on add provider");
			Thread.sleep(GlobalValues.Sleep_wait_time);
			String expectedprovidername = Sheet1.getRow(4).getCell(2).getStringCellValue().toString();
			String exppectedorgname = Sheet1.getRow(3).getCell(3).getStringCellValue().toString();
			WebElement actprovidername = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getProviderNametext())));
			String actprovidername1 = actprovidername.getText();
			if (actprovidername1.contains(expectedprovidername)) {
				Log.info("Most recent record displayed on top");
			}
			editbutton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getEditProvider())));
			editbutton.click();
			WebElement editprovider = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getEditprovidertext())));
			String editprovider1 = editprovider.getText();
			if (editprovider1.equalsIgnoreCase("Edit Provider")) {
				Log.info("Navigated to edit provider after clicking on edit button");
			}
			WebElement cancelbutton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getCancelbutton())));
			cancelbutton.click();
			Thread.sleep(GlobalValues.Sleep_wait_time);
			if (TaskName.equalsIgnoreCase("Initial Outreach")) {
				WebElement initalpage = wait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getInitialoutreachpage())));
				String initalpage1 = initalpage.getText();
				if (initalpage1.equalsIgnoreCase("Initial Outreach")) {
					Log.info("Landed on Initial outreach page after clicking cancel button");
				}
			} else if (TaskName.equalsIgnoreCase("Care Coordination")) {
				WebElement coordinationpage = wait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getCareCoordinationPage())));
				String coordinationpage1= coordinationpage.getText();
				if (coordinationpage1.equalsIgnoreCase("Care Coordination")) {
					Log.info("Landed on Care Coordination page after clicking cancel button");
				}
 
			}
			accordionProvider = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getProvider_xpath())));
			accordionProvider.click();
			editbutton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getEditProvider())));
			editbutton.click();
			AnnualAssessment annual = new AnnualAssessment(driver);
			annual.annual_assessment("AnnualAssessments_CMDM", "CreateManual_Provider", 3);
			Thread.sleep(GlobalValues.Sleep_wait_time);
			if (TaskName.equalsIgnoreCase("Initial Outreach")) {
				WebElement initalpagesubmit = wait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getInitialoutreachpage())));
				String initalpagesubmit1 = initalpagesubmit.getText();
				if (initalpagesubmit1.equalsIgnoreCase("Initial Outreach")) {
					Log.info("Landed on Initial outreach page after clicking submit button");
				}
			} else if (TaskName.equalsIgnoreCase("Care Coordination")) {
				WebElement coordinationpage = wait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getCareCoordinationPage())));
				String coordinationpage1= coordinationpage.getText();
				if (coordinationpage1.equalsIgnoreCase("Care Coordination")) {
					Log.info("Landed on Care Coordination page after clicking submit button");
				}
			}
			accordionProvider = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getProvider_xpath())));
			accordionProvider.click();

			WebElement actualorgname = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getOrganizationName())));
			String actualorgname1 = actualorgname.getText();
			if (exppectedorgname.equalsIgnoreCase(actualorgname1)) {
				Log.info("Organization name displayed after edit");
			}
			if (TaskName.equalsIgnoreCase("Initial Outreach")) {
				WebElement expandbutton = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getProviderExpand())));
				expandbutton.click();

				annual.annual_assessment("AnnualAssessments_CMDM", "Provider_Grid", 1);
				Log.info("All grid labels verified");
				Log.info("Passed-NIM-18249: Provider: Change the Provider grid layout on Initial Outreach" + '\n'
						+ "GIVEN:Provider: Change the Provider grid layout on Initial Outreach" + '\n'
						+ "THEN:verifying Provider: Change the Provider grid layout on Initial Outreach");

			}
			Log.info("Passed-NIM-17283: Provider: Edit a provider from Initial Outreach" + '\n'
					+ "GIVEN:Provider: Edit a provider from Initial Outreach" + '\n'
					+ "THEN:verifying Provider: Edit a provider from Initial Outreach");
			Log.info("Passed-NIM-18250: Provider: Edit a Provider from Initial Outreach and Care Coordination" + '\n'
					+ "GIVEN:Provider: Edit a Provider from Initial Outreach and Care Coordination" + '\n'
					+ "THEN:verifying Provider: Edit a Provider from Initial Outreach and Care Coordination");
			Log.info("Passed-NIM-18333: Care Team: Navigate to activity after adding/cancelling a provider record"
					+ '\n' + "GIVEN:Care Team: Navigate to activity after adding/cancelling a provider record" + '\n'
					+ "THEN:verifying Care Team: Navigate to activity after adding/cancelling a provider record");
		}

		catch (Exception e) {

			Log.info("Failed-NIM-17283: Provider: Edit a provider from Initial Outreach");
			Log.info("Failed-NIM-18249: Provider: Change the Provider grid layout on Initial Outreach");
			Log.info("Failed-NIM-18250: Provider: Edit a Provider from Initial Outreach and Care Coordination");
			Log.info("Failed-NIM-18333: Care Team: Navigate to activity after adding/cancelling a provider record");

		}

	}

	public void searchProvider() throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			Log = Logger.getLogger("ActivityProviderSubSection.class");
			PropertyConfigurator.configure("log4j.properties");
			File sour = new File("./TestData/AnnualAssessments_CMDM.xlsx");
			FileInputStream fiss = new FileInputStream(sour);
			XSSFWorkbook wb = new XSSFWorkbook(fiss);
			XSSFSheet Sheet1 = wb.getSheet("CreateManual_Provider");
			String providerFirstname = Sheet1.getRow(4).getCell(3).getStringCellValue().toString();
			String providerLastname = Sheet1.getRow(5).getCell(3).getStringCellValue().toString();
			LeftNavigationLink leftNav = PageFactory.initElements(driver, LeftNavigationLink.class);
			leftNav.clickCareTeam();
			WebElement careteammem = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getAddcareteamMem())));
			careteammem.click();
			WebElement careteamroledrop = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getcareteamroledrop())));
			careteamroledrop.click();
			WebElement careteamroledropoption = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getcareteamroleprovider())));
			careteamroledropoption.click();
			WebElement firstname = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getSearchFirst())));
			firstname.sendKeys(providerFirstname);
			WebElement lastname = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getSearchLast())));
			lastname.sendKeys(providerLastname);
			WebElement search = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getsearchbutton())));
			search.click();
			Thread.sleep(GlobalValues.Sleep_wait_time);
			WebElement providername = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getprovidername())));
			String Actprovidername = providername.getText();
			if (Actprovidername.contains(providerFirstname)) {
				Log.info("Search provider results displayed in grid");
			}
			WebElement addprovider = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getAddproviderbutton())));
			addprovider.click();
			WebElement careteampage = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getCareteampage())));
			String actcareteampage = careteampage.getText();
			if (actcareteampage.equalsIgnoreCase("Care Team")) {
				Log.info("Navigated to care team page after clicking on add provider button");
			}
			Log.info("Passed-NIM-17580: Provider: Search a provider and add it to member's case (From care team page)"
					+ '\n' + "GIVEN:Provider: Search a provider and add it to member's case (From care team page)"
					+ '\n' + "THEN:verifying Provider: Search a provider and add it to member's case (From care team page)");
		}

		catch (Exception e) {

			Log.info("Failed-NIM-17580: Provider: Search a provider and add it to member's case (From care team page)");
		}
	}
}
