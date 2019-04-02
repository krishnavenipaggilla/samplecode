/**
 * 
 */
package com.nimbus.pages;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections.map.MultiValueMap;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

import excelReader.DataReaderExcel;
import utility.ConfigReader;
import utility.UtilityClass;



/*
 * Class Name : LoginPage 

 * Description:	Logging into application using client Admin role
 */
public class ClientAdminLogin {
	

	WebDriver driver;
	Logger Log;
	
	ConfigReader config = new ConfigReader();
	DataReaderExcel DataRead = new DataReaderExcel();
	
	public ClientAdminLogin(WebDriver ldriver){
		this.driver=ldriver;

	}
	
	public ClientAdminLogin(Logger lLog){
		this.Log=lLog;
	}

	
	public void navigateToClientAdminUrl() throws Exception 
	{
		driver.get("https://va33tlvnim301.wellpoint.com:8443/cmproductadmin/login?logout");
	}
	
	public void login_clientadmin() throws Exception 
	{
		
			Log = Logger.getLogger("ClientAdminLogin.class");
			PropertyConfigurator.configure("log4j.properties");
			
			Log.info("Given-The login screen");
			Log.info("When-A user successfully enters the user name and password");
			Log.info("Then-User should be able to login");
			    try
			    {
		    	//driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
				
				JavascriptExecutor js = (JavascriptExecutor)driver;
				
				WebDriverWait wait =new WebDriverWait(driver, 20);
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getLoginUserNameWebElement())));
				Boolean bool = js.executeScript("return document.readyState").equals("complete");
			    Log.info("Page completely loaded "+bool);
			    
			    //Enter UserName
			    WebElement LoginUserName = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getLoginUserNameWebElement())));
			    LoginUserName.sendKeys(DataRead.ClientAdmin_User);
				Log.info("Enter user name");
	
				//Enter Password
				WebElement LoginPassword = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getLoginPasswordWebElement())));
				LoginPassword.sendKeys(DataRead.ClientAdmin_Pass);
				Log.info("Enter password");
				
				//Click on Login Button 
				WebElement LoginSubmit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getLoginButtonWebElement())));
				LoginSubmit.click();
				Log.info("Click on login button");
				
			    }
			    catch(Exception ex)
			    {
			    	Log.info("Page Not loaded completely / Object is not Visible");
			    }
		    }
	
	public void HomePageVerification()throws Exception
	{
		HeaderFooterValidation headerFooter = new HeaderFooterValidation(driver);
		Log = Logger.getLogger("ClientAdminLogin.class");
		PropertyConfigurator.configure("log4j.properties");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		
		//Get the Title of the page
		String title= driver.getTitle();
		Log.info("The title of the page is " + title);
		
		//Assert statement is used to check the condition
		Assert.assertTrue(driver.getTitle().contains(title));
		Log.info("Verify title of the page");
		
		//Wait time from OAuth page to Home Page navigation
		Log.info(dateFormat.format(date));
		
		WebDriverWait wait = new WebDriverWait(driver,20);
		
		Log.info("Verifying the buttons/elements on the Home page");
		
		Thread.sleep(3000);
		
		List<WebElement> globalNav = driver.findElements(By.xpath("//nav[@class = 'globalNav']//button"));
		
		for(int i=0 ; i<globalNav.size();i++){
			globalNav.get(i).getText().trim();
		
			Assert.assertEquals(globalNav.get(i).getText().trim(), GlobalValues.ClientAdminGlobalNav.get(i));
			
			Log.info("Verifying the "+ GlobalValues.ClientAdminGlobalNav.get(i) + "- Button on the GlobalNav");
		}
		
		headerFooter.header();
		headerFooter.footer();
			
	}
	
	public void createUser()throws Exception
	{
	
		Log = Logger.getLogger("ClientAdminLogin.class");
		PropertyConfigurator.configure("log4j.properties");
		
		driver.findElement(By.xpath("//button[contains(text(),'User and Role Management')]")).click();
		driver.findElement(By.xpath("//a[contains(text(),' Admin - User Maintenance')]")).click();
		//navigates to user maintenance
		String urlFromUI = driver.getCurrentUrl();
		Assert.assertEquals(urlFromUI, "https://va33tlvnim301.wellpoint.com:8443/cmproductadmin/#/h/userrolemaintview/userRoleRefPage");
		
		Log.info("Verify the breadcrumb");
		String breadCrumbForUserMaintenance = driver.findElement(By.xpath("//a[text()='User Maintenance']")).getText();
		Assert.assertEquals(breadCrumbForUserMaintenance, "User Maintenance");
		
		String gridHeader = driver.findElement(By.xpath("//p-header//caption")).getText().trim();
		Assert.assertEquals(gridHeader, "User Roles");
	
		Log.info("Click Create User");
		driver.findElement(By.xpath("//i[@class = 'fa fa-plus-circle ng-star-inserted']")).click();
		
		Log.info("Verify the breadcrumb");
		String addUserBreadCrumb = driver.findElement(By.xpath("//a[text()='Add User']")).getText();
		Assert.assertEquals(addUserBreadCrumb, "Add User");
		
		AnnualAssessment a = new AnnualAssessment(driver);
		a.annual_assessment("AnnualAssessment_CMDM_ClientAdmin", "AddUser", 2);
		Log.info("Created a new User");
		
		
	}
	public void pagination()throws Exception{
		Log = Logger.getLogger("ClientAdminLogin.class");
		PropertyConfigurator.configure("log4j.properties");
		
		WebElement paginationElement = driver.findElement(By.xpath(config.getPaginationElement1Xpath()));
		boolean paginationElementStatus = paginationElement.isEnabled();
		if (paginationElementStatus = true) {
			paginationElement.click();
			Thread.sleep(GlobalValues.Sleep_wait_time);
		} else {
			List<WebElement> searchResultElements = driver
					.findElements(By.xpath(config.getSearchResultsXpath()));
			if (!(searchResultElements.size() > 0)) {
				Log.info("No Users found in the User Roles Grid");
				driver.findElement(By.xpath("//a[text()='Log Out']")).click();
			}
		}
	}
	
	public void verifyAddedEditedUser(String sheetname) throws Exception{
		Log = Logger.getLogger("ClientAdminLogin.class");
		PropertyConfigurator.configure("log4j.properties");
		
		Global_Method gm = new Global_Method(driver);
		List<WebElement> userInfoElement = driver.findElements(By.xpath("//p-header/caption[text() = 'User Roles']//ancestor::div[@class ='ui-datatable-header ui-widget-header ng-star-inserted']//following-sibling::p-table//tbody/tr[position()=1]"));
		String displayNameFromUI = userInfoElement.get(0).getText().trim();
		String loginIDfromUI = userInfoElement.get(1).getText().trim();
		String firstNameFromUI = userInfoElement.get(2).getText().trim();
		String lastNameFromUI = userInfoElement.get(3).getText().trim();
		String role = userInfoElement.get(4).getText().trim();
		String supervisorName = userInfoElement.get(5).getText().trim();
		String status = userInfoElement.get(6).getText().trim();
		String effectiveDate = userInfoElement.get(7).getText().trim();
		String terminationDate =userInfoElement.get(8).getText().trim();
		
		Assert.assertEquals(displayNameFromUI, gm.readFromExcel("AnnualAssessment_CMDM_ClientAdmin", sheetname, 2, "First Name")+ " "+gm.readFromExcel("AnnualAssessment_CMDM_ClientAdmin", "AddUser", 2, "Last Name"));
		Assert.assertEquals(loginIDfromUI, gm.readFromExcel("AnnualAssessment_CMDM_ClientAdmin", sheetname, 2, "Login ID"));
		Assert.assertEquals(firstNameFromUI, gm.readFromExcel("AnnualAssessment_CMDM_ClientAdmin", sheetname, 2, "First Name"));
		Assert.assertEquals(lastNameFromUI, gm.readFromExcel("AnnualAssessment_CMDM_ClientAdmin", sheetname, 2, "Last Name"));
		Assert.assertEquals(role, gm.readFromExcel("AnnualAssessment_CMDM_ClientAdmin", sheetname, 2, "Roles"));
	//	Assert.assertEquals(supervisorName, gm.readFromExcel("AnnualAssessment_CMDM_ClientAdmin", "AddUser", 2, "Roles"));
		Assert.assertEquals(status, gm.readFromExcel("AnnualAssessment_CMDM_ClientAdmin", sheetname, 2, "User Status"));
		Assert.assertEquals(effectiveDate, gm.readFromExcel("AnnualAssessment_CMDM_ClientAdmin", sheetname, 2, "Role Effective Date"));
		Assert.assertEquals(terminationDate, gm.readFromExcel("AnnualAssessment_CMDM_ClientAdmin", sheetname, 2, "Role Termination Date"));
	}
	
	public void editUser()throws Exception{
		Log = Logger.getLogger("ClientAdminLogin.class");
		PropertyConfigurator.configure("log4j.properties");
		
		AnnualAssessment a = new AnnualAssessment(driver);
		WebElement lastUserFromgrid = driver.findElement(By.xpath("//p-header/caption[text() = 'User Roles']//ancestor::div[@class ='ui-datatable-header ui-widget-header ng-star-inserted']//following-sibling::p-table//tbody/tr[position()=1]/td//button"));
		lastUserFromgrid.click();
		String editUserBreadCrumb = driver.findElement(By.xpath("//a[contains(text(),'Edit User Role')]")).getText().trim();
		
		Assert.assertEquals(editUserBreadCrumb, "Edit User Role");
		
		//driver.findElement(By.xpath("//button[@title ='Edit' and @type ='button']")).click();
		a.annual_assessment("AnnualAssessment_CMDM_ClientAdmin", "EditUser", 2);
		
	}
	

	public void verifyUserRolesGrid() throws Exception {
		Log = Logger.getLogger("ClientAdminLogin.class");
		PropertyConfigurator.configure("log4j.properties");
		DashboardFunctions df = new DashboardFunctions(driver);
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

		WebElement gridHeaderElement = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//p-header/caption[text() = 'User Roles']")));
		String gridHeader = gridHeaderElement.getText();
		try {

			if (gridHeader.contains("User Roles")) {
				List<String> actualColumnNames = df.getGridColumnNames();
				Log.info("Getting the list of Column names for User Roles grid");
				Thread.sleep(3000);
				df.validateGridColumnNames(GlobalValues.UserRolesGrid, actualColumnNames);
				Log.info("**********User Roles Grid Column Names has been verified**********");
			}
		} catch (Exception e) {
			Log.info(e + "  -This is not User Roles grid. Verify the Grid Name");
			e.printStackTrace();
		}
	}
	
	
	public void filterSupplementalInformationGrid() throws Exception {
		Log = Logger.getLogger("ClientAdminLogin.class");
		PropertyConfigurator.configure("log4j.properties");
		Global_Method gm = new Global_Method(driver);
		DashboardFunctions df = new DashboardFunctions(driver);

		MultiMap multiMapFilterDetails = new MultiValueMap();

		multiMapFilterDetails.put("Taken By",
				gm.readFromExcel("AnnualAssessments_CMDM", "FilterSupplementalInformation", 1, "Taken By"));
		multiMapFilterDetails.put("Taken By", "column_1");
		multiMapFilterDetails.put("Taken By", "2");

		multiMapFilterDetails.put("Note Type",
				gm.readFromExcel("AnnualAssessments_CMDM", "FilterSupplementalInformation", 1, "Note Type"));
		multiMapFilterDetails.put("Note Type", "column_2");
		multiMapFilterDetails.put("Note Type", "3");

		multiMapFilterDetails.put("Note Description",
				gm.readFromExcel("AnnualAssessments_CMDM", "FilterSupplementalInformation", 1, "Note Description"));
		multiMapFilterDetails.put("Note Description", "column_3");
		multiMapFilterDetails.put("Note Description", "4");

		try {

			df.columnLevelFilteration(multiMapFilterDetails);
			Log.info("**********Verified the Column level Filters on User Roles Grid **********");

		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.info("!!!!!!!!!!Could not verify the Column level Filters on User Roles Grid !!!!!!!!!!");
		}
	}
		
		
		public void sortUserRolesGrid() throws Exception {
			Log = Logger.getLogger("ClientAdminLogin.class");
			PropertyConfigurator.configure("log4j.properties");
			DashboardFunctions df = new DashboardFunctions(driver);

			try {
				//df.columnSortingOnGrid(driver, 0);
				df.columnSorting(driver);
			} catch (Exception e) {

				Log.info(e + " - sorting User Roles Grid failed");
				e.printStackTrace();
			}
			Log.info("**********Verified Sorting on User Roles Grid **********");
		}
	

}

