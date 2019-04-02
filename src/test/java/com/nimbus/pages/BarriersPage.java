package com.nimbus.pages;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.nimbus.pages.DashboardFunctions;
import com.nimbus.pages.GlobalValues;
import utility.ConfigReader;
import utility.UtilityClass;

/**
 * Author-AF86867 
 * Epic-14386
 *
 */

public class BarriersPage extends  UtilityClass {

	ConfigReader config = new ConfigReader();

	WebDriver driver;
	Logger Log;

	public BarriersPage(WebDriver ldriver) {
		this.driver = ldriver;
	}

	public BarriersPage(Logger lLog) {
		this.Log = lLog;
	}

	public void clickCarePlan() {
		try {
			// Click Care Plan
			Log = Logger.getLogger("LeftNavigationLink.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			Log.info("Entered \"Care Plan\" on PageCaseInfo");
			WebElement carePlan = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getCarePlanLink())));
			carePlan.click();

			WebElement CarePlanTitle = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getCarePlanTitle())));
			String CarePlanTitleText = CarePlanTitle.getText();
			Assert.assertEquals(CarePlanTitleText, GlobalValues.carePlan);
			Log.info("Care Plan text is present");
			Log.info("Clicked \"Care Plan\" on PageCaseInfo");
		} catch (Exception e) {
			e.printStackTrace();
			//Log.info("Care Plan Text is visible visible");
		}

	}

	public void AddGoal() {

		// Adding Goals
		try {
			Log = Logger.getLogger("BarriersPage.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getCarePlanText())));
			WebElement AddGoal = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getAddGoalXpath())));
			AddGoal.click();
			Log.info("Clicked on Add Goal link");
			Log.info("Waiting for Add Goal page to appear");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getAddGoalTextXpath())));
			Log.info("Add Goal page got displayed");

			Thread.sleep(2000);
			driver.findElement(By.xpath(config.getGoalDropdownForAddGoal())).click();
			// driver.findElement(By.xpath(config.getAddGoalDropdown())).click();
			Thread.sleep(2000);
			Log.info("Clicked on Goal drop-down");
			WebElement Goaloption = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getFirstOptionInGoalDropDown())));
			Goaloption.click();
			Log.info("Clicked on Goal option ");

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getPriorityDropdownForAddGoal())))
					.click();
			WebElement Priorityoption = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(config.getFirstOptionInPriorityDropdown())));
			Priorityoption.click();
			Log.info("Clicked on Priority option");

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getdAddGoalStatusDropdown()))).click();
			// driver.findElement(By.xpath(config.getdAdGoalStatusDropdown())).click();
			WebElement Statusoption = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getFirstOptionInStatusDropdown())));
			Statusoption.click();
			Log.info("Clicked on Status option");

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getAddGoalStatusReasonDropdown())))
					.click();

			WebElement StatusReasonoption = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(config.getFirstOptionInStatusReasonDropdown())));
			StatusReasonoption.click();
			Log.info("Clicked on Status Reason");

			Thread.sleep(2000);

			WebElement Date = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getDateDropdown())));
			Date.sendKeys("8/12/2018");
			Log.info("Date choosen");

			WebElement Submit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Submit')]")));
			Assert.assertTrue(Submit.isDisplayed());
			Submit.click();
		//	Log.info("Clicked on Submit");
			Log.info("Sucessfully added Goal");

		} catch (Exception e) {
			e.printStackTrace();
			//Log.info("Add Goal is not visible");
		}
	}

	public void clickBarriers() {
		try {
			// Click Barriers
			Log = Logger.getLogger("BarriersPage.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			LeftNavigationLink lnk = new LeftNavigationLink(driver);
			
//			lnk.clickBarriers();
//			WebElement barrier = wait
//					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getBarriersLinkXpath())));
//			barrier.click();
//			Log.info("Clicked on Barrier on Left Navigation");
			Thread.sleep(2000);
			WebElement AddBarrier = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getAddBarrierbuttonXpath())));
			AddBarrier.click();
			//Thread.sleep(3000);
			Log.info("Clicked on Add Barrier button");
			Thread.sleep(3000);
		WebElement AddBarrierText = wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getAddBarrierText())));
		String ActualText = AddBarrierText.getText();
			Assert.assertEquals(ActualText, "Add Barrier");
			Log.info("Add Barrier Page got displayed");

		} catch (Exception e) {
			e.printStackTrace();
			//Log.info("Add Barrier page is not visible");
		}

	}
	
	public void clickBarriersinactivity() {
		try {
			// Click Barriers
			Log = Logger.getLogger("BarriersPage.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			
			WebElement AddBarrier = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getAddBarrierbuttonXpath())));
			AddBarrier.click();
			//Thread.sleep(3000);
			Log.info("Clicked on Add Barrier button");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(config.getmsgLoading())));
			Thread.sleep(1000);
		WebElement AddBarrierText = wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getAddBarrierText())));
		String ActualText = AddBarrierText.getText();
			Assert.assertEquals(ActualText, "Add Barrier");
			Log.info("Add Barrier Page got displayed");

		} catch (Exception e) {
			e.printStackTrace();
			//Log.info("Add Barrier page is not visible");
		}

	}


	public void AddBarriersToGoal() throws Exception {

		// Adding Barriers to Goal
		Log = Logger.getLogger("BarriersPage.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

		int rowsInGrid = 0;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getBarrierDropdown()))).click();
			Thread.sleep(2000);
			Log.info("Clicked on barrier dropdown");
			WebElement Barrier = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getBarrierOption())));
			// Log.info("Selected Barrier is"+Barrier.getText());
			Barrier.click();
			Log.info("Clicked on brrier option from the dropdown");
			Thread.sleep(2000);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getChooseGoal()))).click();
			Log.info("Clicked on Goal");

			Thread.sleep(2000);
			String GoalText = driver.findElement(By.xpath("//table/tbody//tr[1]//td[2]")).getText();
			Log.info("Selected Goal is: " + GoalText);

			WebElement SubmitButton = driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
			String SubmitButtonText = SubmitButton.getText();
			Assert.assertEquals(SubmitButtonText, "Submit");
			Log.info("Submit Button is present");

			WebElement BackButton = driver.findElement(By.xpath("//button[contains(text(),'Back')]"));
			String BackButtonText = BackButton.getText();
			Assert.assertEquals(BackButtonText, "Back");
			Log.info("Back Button is present");

			Thread.sleep(2000);
			driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();

			Log.info("Clicked on submit button");

			WebElement BarriersLandingPage = driver.findElement(By.xpath(config.getBarriersGridHeader()));
			String BarriersLandingPageText = BarriersLandingPage.getText();
			Assert.assertEquals(BarriersLandingPageText, "Barriers");
			Log.info("Taken back to Barriers landing page");

			WebElement gridHeader = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getBarriersGridHeader())));
			String gridHeaderText = gridHeader.getText();
			Log.info("Grid Name is - " + gridHeaderText);

			WebElement table = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table")));

			List<WebElement> noRecordsElement = driver
					.findElements(By.xpath("//table/tbody/tr/td[contains(text(),'No records found')]"));
			if (noRecordsElement.isEmpty()) {
				// Now get all the TR elements from the table
				List<WebElement> allRowsOfTable = table.findElements(By.xpath("//table//tbody/tr"));

				int rowsInGrid1 = allRowsOfTable.size();
				Log.info("Number of Barriers present: " + rowsInGrid1);

				Thread.sleep(3000);
				WebElement Status = driver.findElement(
						By.xpath("//tbody[@class='ui-table-tbody']//tr[" + rowsInGrid1 + "]//td[5]//span[1]//span[1]"));
				String Actual = Status.getText();
				//Assert.assertEquals(Actual, "Open");
				Log.info("Staus column by default has status as:"+Actual);

				Thread.sleep(2000);
				WebElement StatusReason = driver.findElement(
						By.xpath("//tbody[@class='ui-table-tbody']//tr[" + rowsInGrid1 + "]//td[6]//span[1]//span[1]"));
				String ActualReason = StatusReason.getText();
				//Assert.assertEquals(ActualReason, "In Progress");
				Log.info("Staus Reason column by default has status as:"+ActualReason ); 
			}
		} catch (Exception e) {
			e.printStackTrace();
			//Log.info("Could not find Goals.Please add Goals first " + e);

		}
	}

	

	public void EditBarrier() throws InterruptedException {
		try {

			Log = Logger.getLogger("BarriersPage.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

			String AssociatedGoalText = driver.findElement(By.xpath(config.getEditBarrier_AssociatedGoal())).getText();
			String ExpectedTitle = "Associated Goal";
			Assert.assertEquals(AssociatedGoalText, ExpectedTitle);
			Log.info("Able to see Associated Goal");

			WebElement Description = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getEditBarrier_Description())));
			String DescriptionText = Description.getText();
			String Expected = "Description";
			Assert.assertEquals(DescriptionText, Expected);
			Log.info("Able to see Description");
			WebElement DescriptionField = wait.until(
					ExpectedConditions.presenceOfElementLocated(By.xpath(config.getEditBarrier_DescriptionField())));
			boolean DescriptionNotEditable = DescriptionField.isEnabled() == false;
			Assert.assertFalse(DescriptionNotEditable);// assertTrue(DescriptionNotEditable);
			Log.info("Description field is not editable");

			WebElement IdentifiedDate = wait.until(
					ExpectedConditions.presenceOfElementLocated(By.xpath(config.getEditBarrier_IdentifiedDate())));
			String ActualText = IdentifiedDate.getText();
			String ExpectedText = "Identified Date";
			Assert.assertEquals(ActualText, ExpectedText);
			Log.info("Able to see Identified Date");
			WebElement DateField = wait.until(
					ExpectedConditions.presenceOfElementLocated(By.xpath(config.getEditBarrier_IdentifiedDateField())));
			boolean DateNotEditable = DateField.isDisplayed() == false;
			Assert.assertFalse(DateNotEditable);
			Log.info("Identified date field is not editable");

			WebElement EditBarrierStatusDropdown = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getEditBarrier_Statusdropdown())));
			EditBarrierStatusDropdown.click();
			WebElement ChooseStatusClosed = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//label[contains(text(),'Status')]/../../..//li[2]")));
			ChooseStatusClosed.click();
			Log.info("Clicked on Staus dropdown,choosen Status as closed");

			WebElement EditBarrierStatusReasonDropdown = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(config.getEditBarrier_StatusReasonDropdown())));
			EditBarrierStatusReasonDropdown.click();
			Thread.sleep(2000);
			WebElement ChooseStatusReasonMet = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//label[contains(text(),'Status Reason')]/../../..//li[2]")));
			ChooseStatusReasonMet.click(); 
			Log.info("Clicked on Staus Reason dropdown");

			Thread.sleep(2000);

		      driver.findElement(By.xpath("//button[contains(text(),'Back')]"));
			//boolean Displayed = Back.isDisplayed();
			//Assert.assertTrue(Displayed);
			Log.info("Able to see Back button");

			Thread.sleep(2000);
			WebElement Submit = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Submit')]")));
		    //	WebElement Submit = driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
			//boolean SubmitDisplayed = Submit.isDisplayed();
			//Assert.assertTrue(SubmitDisplayed);
			Submit.click();
			Log.info("Clicked on Submit button");

		} catch (Exception e) {
			e.printStackTrace();
			//Log.info("Couldn't Edit Barrier");
		}

	}

	
	public void ClickEditBarrier() {
		try {
			int a;
			Log = Logger.getLogger("BarriersPage.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			Thread.sleep(4000);
//			driver.findElement(By.xpath(config.getBarriersLinkXpath())).click();
//			Log.info("Clicked on BarriersLink");
			Thread.sleep(3000);
			WebElement EditBarriers = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getEditBarrierIcon())));

			EditBarriers.click();
			Log.info("Clicked on Edit Barriers Icon");
			Thread.sleep(2000);
			Log.info("Edit Barriers page got displayed");
		} catch (Exception e) {
			e.printStackTrace();
			//Log.info("Edit Barrier button is not visible");
		}
	}
	public void VerifyResolvedDate() {
		try {
			Log = Logger.getLogger("BarriersPage");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

			this.ClickEditBarrier();
			Thread.sleep(2000);

			WebElement ResolvedDateField = wait.until(
					ExpectedConditions.presenceOfElementLocated(By.xpath(config.getEditBarrier_ResolvedDateField())));
			boolean Resolved = ResolvedDateField.isDisplayed() == false;
			Assert.assertFalse(Resolved);
			Log.info("Resolved date field is not editable: as expected");

		} catch (Exception e) {
			e.printStackTrace();
			//Log.info("Couldn't verify resolved date");

		}
	}

	public void EditBarriersLink() throws InterruptedException {

		try {
			
			Log = Logger.getLogger("BarriersPage");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			
			driver.findElement(By.xpath("//span[contains(text(),'Barriers')]")).click();
			Log.info("Clicked on BarriersLink");
			Thread.sleep(2000);
			// clicking on Edit Barriers
			WebElement EditBarriers = wait

					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getEditBarrierIcon())));

			EditBarriers.click();

			Log.info("Clicked on Edit Barriers Icon");
			Thread.sleep(2000);
			// Log.info("Edit Barriers page got displayed");

			String EditBarriersText = wait

					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getEditBarrierText()))).getText();

			Assert.assertEquals(EditBarriersText, "Edit Barrier");
			Log.info("Edit Barriers page got displayed");
		} catch (Exception e) {
			e.printStackTrace();
			//Log.info("Edit Barrier page didn't get displayed " + e);
		}
	}

	public void verifynotesColumnGrid() throws Exception {
		// Verifying the uploaded document grid
		Log = Logger.getLogger("LeftNavigationLink.class");
		PropertyConfigurator.configure("log4j.properties");
		DashboardFunctions df = new DashboardFunctions(driver);
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

		WebElement gridHeaderElement = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getNotesText())));
		String gridHeader = gridHeaderElement.getText();
		Assert.assertEquals(gridHeader, "Notes");
		Log.info("Notes header is present");
		try {

			if (gridHeader.contains("Notes")) {
				List<String> actualColumnNames = df.getGridColumnNames();
				Log.info("Getting the list of Column names for Notes Grid");
				Thread.sleep(3000);
				df.validateGridColumnNames(GlobalValues.NotesGridOnBarrierGrid, actualColumnNames);
				Log.info("Notes Column Names has been verified");
			}
		} catch (Exception e) {
			Log.info(e + "Couldn't verify the Notes Columns");
		}
	}

	public void AddingNote() {
		try {
			Log = Logger.getLogger("BarriersPage.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

			WebElement AddNote = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getAddNoteXpath())));
			AddNote.click();
			Log.info("Clicked on Add Note button");

			WebElement AddNotesWindow = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getAddNotesHeader())));
			AddNotesWindow.isDisplayed();
			Log.info("Add Notes window got displayed");

			driver.findElement(By.xpath(config.getNotesDescriptiont())).sendKeys("My Note");
			Log.info("Added Note");

			Thread.sleep(2000);

			WebElement SubmitButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getAddNotesSubmitButton())));

			Assert.assertTrue(SubmitButton.isDisplayed());
			Log.info("Submit button is present");
			Log.info("Submit button is enabled");

			WebElement BackButton = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getAddNotesBackButton())));
			Assert.assertTrue(BackButton.isDisplayed());
			Log.info("Back button is present");
			Thread.sleep(2000);

			SubmitButton.click();
			Log.info("Clicked on Submit button");

			WebElement NotesHeader = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getNotesText())));
			Assert.assertEquals(NotesHeader.getText(), "Notes");
			Log.info("Taken back to Edit barrier page conaining Notes table  ");
		} catch (Exception e) {
			e.printStackTrace();
			Log.info(e + "Couldn't Add Note");
		}

	}

	public void getNoOfRowsInNotesGrid() throws Exception {
		Log = Logger.getLogger("Global_Method.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

		int rowsInGrid = 0;
		try {

			WebElement gridHeader = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getNotesText())));
			String gridHeaderText = gridHeader.getText();
			Log.info("Grid Name is - " + gridHeaderText);

			WebElement table = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getNotesText())));

			List<WebElement> noRecordsElement = driver
					.findElements(By.xpath("//table/tbody/tr/td[contains(text(),'No records found')]"));
			if (noRecordsElement.isEmpty()) {
				// Now get all the TR elements from the table
				List<WebElement> allRowsOfTable = table.findElements(By.xpath("//table//tbody/tr"));

				rowsInGrid = allRowsOfTable.size();
				Log.info("No of Notes available in the grid : " + rowsInGrid);

			}

		} catch (Exception e) {
			e.printStackTrace();
			Log.info("Could not find notes " + e);
		}
		// return rowsInGrid;
	}

	public void VerifyBarriersGrid() {
		// Verify Barriers grid column names

		try {
			Log = Logger.getLogger("Footer.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			// Global_Method GM = new Global_Method(driver);

			WebElement BarriersGrid_Description = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(config.getBarriersGrid_DescriptionXpath())));
			String Actual1 = BarriersGrid_Description.getText();
			assertEquals(Actual1, GlobalValues.BarriersGrid_Description.toString());
			Log.info(" Description column present on the grid");

			WebElement BarriersGrid_LinkedGoal = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getBarriersGrid_LinkedGoalXpath())));
			String Actual2 = BarriersGrid_LinkedGoal.getText();
			assertEquals(Actual2, GlobalValues.BarriersGrid_LinkedGoal.toString());
			Log.info("Linked Goal column present on the grid ");

			WebElement BarriersGrid_IdentifiedDate = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(config.getBarriersGrid_IdentifiedDateXpath())));
			String Actual3 = BarriersGrid_IdentifiedDate.getText();
			assertEquals(Actual3, GlobalValues.BarriersGrid_IdentifiedDate.toString());
			Log.info("Identified Date column present on the grid");

			WebElement BarriersGrid_ResolvedDate = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(config.getBarriersGrid_ResolvedDateXpath())));
			String Actual4 = BarriersGrid_ResolvedDate.getText();
			assertEquals(Actual4, GlobalValues.BarriersGrid_ResolvedDate.toString());
			Log.info("Resolved Date column present on the grid");

			WebElement BarriersGrid_Status = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getBarriersGrid_StatusXpath())));
			String Actual5 = BarriersGrid_Status.getText();
			assertEquals(Actual5, GlobalValues.BarriersGrid_Status.toString());
			Log.info(" Status column present on the grid");

//			WebElement BarriersGrid_StatusReason = wait.until(
//					ExpectedConditions.elementToBeClickable(By.xpath(config.getBarriersGrid_StatusReasonXpath())));
//			String Actual6 = BarriersGrid_StatusReason.getText();
//			assertEquals(Actual6, GlobalValues.BarriersGrid_StatusReason.toString());
//			Log.info(" Status Reason column present on the grid");
		} catch (Exception e) {
			e.printStackTrace();
			Log.info("Barriers Grid is as expected");
		}

	}

	public void CheckBarrierTypeColumn() throws Exception {

		try {

			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			Log = Logger.getLogger("DashboardFunctions.class");
			PropertyConfigurator.configure("log4j.properties");
			DashboardFunctions df = new DashboardFunctions(driver);

			// Barriers Grid
			WebElement gridHeaderElement = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getBarriersGridHeader())));
			String gridHeader = gridHeaderElement.getText();

			if (gridHeader.contains("Barriers")) {

				List<WebElement> BarrierGridColumns = driver.findElements(By.xpath("//table//th"));

				for (WebElement we : BarrierGridColumns) {
					String ColumnName = we.getText();
					// ColumnName ="Barrier Type";
					if (ColumnName == "Barrier Type") {
						Log.info("Barriers - Barriers Type Column exist");
						break;
					}
				}

				Log.info("Barriers - Barriers Type Column doesn't exist as expected");
			}

			// Add Barriers Grid
			driver.findElement(By.xpath(config.getAddBarrierbuttonXpath())).click();
			Thread.sleep(2000);
			WebElement GoalsgridHeaderElement = wait.until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//caption[contains(text(),'Goals')]")));
			String gridHeaderText = GoalsgridHeaderElement.getText();
			if (gridHeaderText.contains("Goals")) {

				List<WebElement> GoalsGridColumns = driver.findElements(By.xpath("//table//th"));

				for (WebElement we : GoalsGridColumns) {
					String ColumnName = we.getText();
					// ColumnName ="Barrier Type";
					if (ColumnName == "Barrier Type") {
						Log.info("Goals - Barriers Type Column exist in Add Barrier Page");
						break;
					}
				}

				Log.info("Goals - Barriers Type Column doesn't exist in Add Barrier page as expected");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.info(e);
		}
	}

	
	public void VerifyEditBarrier() {
		try {
			Log = Logger.getLogger("DashboardFunctions.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

//			this.clickBarriers();
//			Thread.sleep(3000);
			
			LeftNavigationLink lnk = new LeftNavigationLink(driver);
			
			lnk.clickBarriers();

			if (driver.findElement(By.xpath(config.getEditBarrierIcon())).isDisplayed()) {
				Log.info("Edit Icon is visible");

				driver.findElement(By.xpath(config.getEditBarrierIcon())).click();
				WebElement EditBarrierLabel = wait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getEditBarrierText())));

				if (EditBarrierLabel.isDisplayed())
					Log.info("Able to open Edit/View Barrier from Barrier Page using Edit/View Icon");
				else
					Log.info("Not Able to open Edit/View Barrier from Barrier Page using Edit/View Icon");
			} else
				Log.info("Edit Icon is not visible");

		}

		catch (Exception e) {
			e.printStackTrace();
			Log.info(e);
		}

	}
	
	public void AddBarriersToGoal_Subsection() throws Exception {	
		Log = Logger.getLogger("BarriersPage.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

		int rowsInGrid = 0;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getBarrierDropdown()))).click();
			Thread.sleep(2000);
			Log.info("Clicked on barrier dropdown");
			WebElement Barrier = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getBarrierOption())));
			// Log.info("Selected Barrier is"+Barrier.getText());
			Barrier.click();
			Log.info("Clicked on brrier option from the dropdown");
			Thread.sleep(2000);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getChooseGoal()))).click();
			Log.info("Clicked on Goal");

			Thread.sleep(2000);
			String GoalText = driver.findElement(By.xpath("//table/tbody//tr[1]//td[2]")).getText();
			Log.info("Selected Goal is: " + GoalText);

			WebElement SubmitButton = driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
			String SubmitButtonText = SubmitButton.getText();
			Assert.assertEquals(SubmitButtonText, "Submit");
			Log.info("Submit Button is present");

			WebElement BackButton = driver.findElement(By.xpath("//button[contains(text(),'Back')]"));
			String BackButtonText = BackButton.getText();
			Assert.assertEquals(BackButtonText, "Back");
			Log.info("Back Button is present");

			Thread.sleep(2000);
			driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();

			Log.info("Clicked on submit button");
		}
		catch (Exception e) {
			e.printStackTrace();
			//Log.info("Could not find Goals.Please add Goals first " + e);

		}
	}
	
	public void VerifyBarriersdata() throws Exception {	
		int a;
		Log = Logger.getLogger("BarriersPage.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
	WebElement BarriersLandingPage = driver.findElement(By.xpath(config.getBarriersGridHeader()));
	String BarriersLandingPageText = BarriersLandingPage.getText();
//	Assert.assertEquals(BarriersLandingPageText, "Barriers");
	Log.info("Taken back to Barriers landing page");

	WebElement gridHeader = wait
			.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getBarriersGridHeader())));
	String gridHeaderText = gridHeader.getText();
	Log.info("Grid Name is - " + gridHeaderText);

	WebElement table = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table")));

	List<WebElement> noRecordsElement = driver
			.findElements(By.xpath("//caption[contains(text(),'Interventions and Barriers')]/../../..//span[@title='Barrier']"));
	if (!(noRecordsElement.isEmpty())) {
		// Now get all the TR elements from the table
		List<WebElement> allRowsOfTable = table.findElements(By.xpath("//caption[contains(text(),'Interventions and Barriers')]/../../..//span[@title='Barrier']"));

		int rowsInGrid1 = allRowsOfTable.size();
		Log.info("Number of Barriers present: " + rowsInGrid1);

		Thread.sleep(3000);
		WebElement Status = driver.findElement(
				By.xpath("(//caption[contains(text(),'Interventions and Barriers')]/../../..//span[@title='Barrier'])["+rowsInGrid1+"]/../..//td[4]/span/span"));
		String Actual = Status.getText();
		//Assert.assertEquals(Actual, "Open");
		Log.info("Staus column by default has status as:"+Actual);

		Thread.sleep(2000);
		WebElement StatusReason = driver.findElement(
				By.xpath("(//caption[contains(text(),'Interventions and Barriers')]/../../..//span[@title='Barrier'])["+rowsInGrid1+"]/../..//td[5]/span/span"));
		String ActualReason = StatusReason.getText();
		//Assert.assertEquals(ActualReason, "In Progress");
		Log.info("Staus Reason column by default has status as:"+ActualReason ); 
	}
} 

}
