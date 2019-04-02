package com.nimbus.pages;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TimeZone;

import javax.print.attribute.standard.DateTimeAtCompleted;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.ss.formula.functions.Today;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.google.common.base.Verify;
import com.relevantcodes.extentreports.LogStatus;

import excelReader.DataReaderExcel;

import utility.ConfigReader;
import utility.UtilityClass;

/*
* Class Name: Notes
* MethodName:             CreateNote
* Description: common class to create note and view note
*/
public class ListOfNotesPage {

	ConfigReader config = new ConfigReader();
	DataReaderExcel DataRead = new DataReaderExcel();
	WebDriver driver;
	Logger Log;

	public ListOfNotesPage(WebDriver ldriver) {
		this.driver = ldriver;
	}

	public ListOfNotesPage(Logger lLog) {
		this.Log = lLog;
	}
	
	public void verifyNotesPage() throws InterruptedException, Throwable {
		Log = Logger.getLogger("ListOfNotesPage.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		boolean passed = true;
		Global_Method gm = new Global_Method(driver);
		LeftNavigationLink lnk = new LeftNavigationLink(driver);

		
		lnk.clickNotes();
		Thread.sleep(GlobalValues.Sleep_wait_time);

		try {
			Log.info("Verify Adding Note");
			createNote("true", "Clinical", "test-info");

		} catch (Exception e) {
			e.printStackTrace();
			passed = false;
		}

		try {
			Log.info("Verify Added Note");
			verifyNote();
		} catch (Exception e) {
			e.printStackTrace();
			passed = false;
		}

		try {
			Log.info("Verify View Note");
			viewNotes();
		} catch (Exception e) {
			e.printStackTrace();
			passed = false;
		}

		try {
			Log.info("Verify Notes Grid");
			verifyNotesGrid();
		} catch (Exception e) {
			e.printStackTrace();
			passed = false;
		}

		try {
				Log.info("Verify Column Level Sorting on Notes Grid");
				sortingOnColumns();
			
		} catch (Exception e) {
			e.printStackTrace();
			passed = false;
		}

		try {
			Log.info("Verify ToolTip on Note Details Column");
			toolTipOnNoteDetails();
		} catch (Exception e) {
			e.printStackTrace();
			passed = false;
		}

		// try {
		// Request4.verifyDefaultOrderByCriticalNote();
		// } catch
		// (Exception e) { // e.printStackTrace(); // passed = false; // }

		try {
			Log.info("Verify Filter by NoteType");
			verifyFilterBy("Clinical");
		} catch (Exception e) {
			e.printStackTrace();
			passed = false;
		}

		try {
			Log.info("Verify Cancel functionality of a Note");
			cancelNote(false, "Clinical", "test-info");
		} catch (Exception e) {
			e.printStackTrace();
			passed = false;
		}
		// Request4.verifySecondDefaultOrderByDate();

		if (passed == true) {
			gm.Reports("NIM-10345", "Pass");
		} else {

			gm.Reports("NIM-10345", "Fail");
		}
	}

	public void cancelNote(boolean criticalflag, String ntType, String ntInfo) {
		Log = Logger.getLogger("ListOfNotesPage.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

		try {
			// Click on Notes link
			LeftNavigationLink leftNavigationLink = PageFactory.initElements(driver, LeftNavigationLink.class);
			leftNavigationLink.clickNotes();
			Log.info("Clicked on notes");

			Log.info("Click on AddNote");
			Thread.sleep(GlobalValues.Sleep_wait_time);
			WebElement addNote = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getAddNoteXpath())));
			addNote.click();
			Thread.sleep(GlobalValues.Sleep_wait_time);

			Log.info("Select note type as " + ntType);
			GlobalValues.Note_Type = ntType;
			WebElement noteTypeDropDown = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getNoteTypeXpath())));

			noteTypeDropDown.click();
			Thread.sleep(GlobalValues.Sleep_wait_time);

			WebElement noteTypeDropDownType = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//label[contains(text(),'Note Type')]//parent::nm-input-label//following-sibling::p-dropdown//div//span[text()='"
							+ ntType + "']")));

			noteTypeDropDownType.click();
			Thread.sleep(GlobalValues.Sleep_wait_time);

			if (criticalflag) {
				Log.info("Select flag as critical checkbox ");
				WebElement flagAsCritical = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getSensitiveChkBoxXpath())));
				flagAsCritical.click();
			}
			Log.info("Enter note information ");
			GlobalValues.Note_Info = ntInfo;
			WebElement noteInfo = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getNoteInfoXpath())));
			noteInfo.sendKeys(GlobalValues.Note_Info);

			Log.info("Click on Cancel");
			WebElement cancel = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getCancelXpath())));
			cancel.click();

		} catch (Exception e) {
			e.printStackTrace();
			Log.info("Object not visible");
		}
	}

	public void createNote(String sensitiveFlag, String ntType, String ntInfo) {
		Log = Logger.getLogger("ListOfNotesPage.class");
		PropertyConfigurator.configure("log4j.properties");

		Log.info("createNote() started");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

		try {
			Log.info("Click on Notes ");

			Log.info("Click on AddNote");
			Thread.sleep(GlobalValues.Sleep_wait_time);
			WebElement addNote = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getAddNoteXpath())));
			addNote.click();

			Log.info("Select note type as " + ntType);
			Thread.sleep(GlobalValues.Sleep_wait_time);

			GlobalValues.Note_Type = ntType;
			WebElement noteTypeDropDown = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getNoteTypeXpath())));

			noteTypeDropDown.click();
			Thread.sleep(GlobalValues.Sleep_wait_time);

			WebElement noteTypeDropDownType = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//label[contains(text(),'Note Type')]//parent::nm-input-label//following-sibling::p-dropdown//div//span[text()='"
							+ ntType + "']")));
			noteTypeDropDownType.click();
			Thread.sleep(GlobalValues.Sleep_wait_time);

			if (sensitiveFlag.equals("true")) {
				Log.info("Select checkbox for sensitive flag ");
				WebElement flagAsSensitive = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getSensitiveChkBoxXpath())));
				flagAsSensitive.click();
			}

			GlobalValues.Note_Sensitive_Value_Assigned = sensitiveFlag;
			if(GlobalValues.Note_Sensitive_Value_Assigned.contains("true")){
				GlobalValues.Note_Sensitive_Value = "Yes";
			}

			Log.info("Enter note information");

			// WebElement subjectlinedropdown =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(),'Subject')]//following-sibling::p-dropdown//div//span")));
			// subjectlinedropdown.click();
			//
			// Log.info("click on the subject line drop down");

			// WebElement dropDownInpatientCareCoordination =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(),'Subject')]//following-sibling::p-dropdown//div//span[text()='Inpatient
			// Care Coordination']")));
			// dropDownInpatientCareCoordination.click();
			//
			// Log.info("select the Inpatient Care Coordination from the drop
			// down");

			GlobalValues.Note_Info = ntInfo;

			WebElement noteInfo = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getNoteInfoXpath())));
			noteInfo.sendKeys(GlobalValues.Note_Info);

			Log.info("Click on Save & Submit ");

			WebElement saveSubmit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getSaveAndSubmitXpath())));
			saveSubmit.click();
			Thread.sleep(GlobalValues.Sleep_wait_time);

		//	GlobalValues.Note_TakenBy = GlobalValues.DBCaseOwnerDisplayName.toLowerCase();
			
			GlobalValues.Note_TakenBy = driver.findElement(By.xpath("//div[@class ='userName']//nm-value")).getText().trim();

			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			Date date = new Date();
			df.setTimeZone(TimeZone.getTimeZone("UTC"));
			GlobalValues.Note_Date_time = df.format(date);
			Log.info(GlobalValues.Note_Date_time);
			Log.info("successfully note has created");

		} catch (Exception e) {
			e.printStackTrace();
			Log.info("Object not visible" + e.getMessage().toString());
		}
		Log.info("createNote() ended");
	}

	public void createAdditionalNote(boolean sensitiveFlag, String ntType, String ntInfo) {
		Log = Logger.getLogger("ListOfNotesPage.class");
		PropertyConfigurator.configure("log4j.properties");

		/*
		 * listOfNotesPage.createAdditionalNote(false, "Task",
		 * "example case two"); listOfNotesPage.createAdditionalNote(true,
		 * "Case", "sample case Three");
		 * listOfNotesPage.createAdditionalNote(true, "Task",
		 * "sample case Four");
		 */

		Log.info("createAdditionalNote() started");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

		try {

			Log.info("Click on AddNote");
			Thread.sleep(GlobalValues.Sleep_wait_time);
			WebElement addNote = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getAddNoteXpath())));
			addNote.click();

			Log.info("Select note type as " + ntType);
			GlobalValues.Note_Type = ntType;

			Thread.sleep(3000);

			WebElement noteTypeDropDown = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getNoteTypeXpath())));
			noteTypeDropDown.click();

			WebElement noteTypeDropDownType = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//label[contains(text(),'Note Type')]//following-sibling::p-dropdown//div//span[text()='"
							+ ntType + "']")));
			noteTypeDropDownType.click();

			if (sensitiveFlag) {
				Log.info("Select flag as sensitive checkbox ");
				WebElement flagAsSensitive = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getSensitiveChkBoxXpath())));
				flagAsSensitive.click();
			}

			Log.info("Enter note information ");

			GlobalValues.Note_Info = ntInfo;

			WebElement noteInfo = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getNoteInfoXpath())));
			noteInfo.sendKeys(GlobalValues.Note_Info);

			Log.info("Click on Save & Submit ");

			WebElement saveSubmit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getSaveAndSubmitXpath())));
			saveSubmit.click();

			/*
			 * DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); Date
			 * date = new Date(); df.setTimeZone(TimeZone.getTimeZone("UTC"));
			 * GlobalValues.Note_Date_time = df.format(date);
			 */

			Log.info("successfully another note has created ");

		} catch (Exception e) {
			e.printStackTrace();
			Log.info("Object not visible");
		}
		Log.info("createAdditionalNote() ended");
	}

	public void verifyNote() throws Exception {
		Log = Logger.getLogger("ListOfNotesPage.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

		Thread.sleep(GlobalValues.Sleep_wait_time);

		Log.info("Click on notes");
		// WebElement notes =
		// wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Notes")));
		// notes.click();

		
		Map<Integer, String> tableValueHashMap = new HashMap<Integer, String>();

		String cellValue = null;

		for (int r = 1; r <= 1; r++) {
			for (int c = 1; c <= 5; c++) {

				String tableXpathAfterRowReplace = config.getNotesTableXpath().replaceAll("replaceMeWithRow",
						String.valueOf(r));
				String tableXpathAfterColReplace = tableXpathAfterRowReplace.replaceAll("replaceMeWithCol",
						String.valueOf(c));
				// Capture cell value
				WebElement cell = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(tableXpathAfterColReplace)));
				cellValue = cell.getAttribute("textContent");
				tableValueHashMap.put(c, cellValue);
			}
		}

		Log.info("Verification of notes grid ");

		try {
			for (Integer key : tableValueHashMap.keySet()) {
				cellValue = tableValueHashMap.get(key);
				switch (key) {

				case 1:

					Assert.assertTrue(cellValue.equals(GlobalValues.Note_Sensitive_Value.toString()));
					Log.info("Validation of Sensitive cell value is done");
					break;
				case 3:

					Log.info("Validation of Critical cell case 2 started ");
					if (cellValue != null) {
						Date df = new SimpleDateFormat("MM/dd/yyyy").parse(cellValue);
						// Date date = new Date();
						SimpleDateFormat newCellValue = new SimpleDateFormat("MM/dd/yyyy");
						// df.setTimeZone(TimeZone.getTimeZone("UTC"));
						GlobalValues.Note_Date_time = newCellValue.format(df);
						Assert.assertTrue(newCellValue.format(df).equalsIgnoreCase(GlobalValues.Note_Date_time));
						Log.info("Validation of Date Entered value is done " + GlobalValues.Note_Date_time.toString());
						Log.info("cell value is done" + tableValueHashMap.get(2));
					}
					break;

				case 4:

					Assert.assertTrue(cellValue.toLowerCase().equals(GlobalValues.Note_TakenBy.toString().toLowerCase()));
					Log.info("Validation of EnteredBy cell value is done");
					break;

				case 2:

					Assert.assertTrue(cellValue.equals(GlobalValues.Note_Type.toString()));
					Log.info("Validation of Note Type cell value is done");
					break;

				case 5:

					Assert.assertTrue(cellValue.equals(GlobalValues.Note_Info.toString()));
					Log.info("Validation of Note Details cell value is done");
					break;
				}
			}
		} catch (Exception e) {
			Log.info("Verification failed during notes table verification");
			e.printStackTrace();
		}

	}

	public void viewNotes() throws Exception {
		Log = Logger.getLogger("ListOfNotesPage.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		Global_Method gm = new Global_Method(driver);

		Thread.sleep(GlobalValues.Sleep_wait_time);

		try {
			// Log.info("Click on viewNotes dropdown");
			// WebElement actionDropDown = wait
			// .until(ExpectedConditions.elementToBeClickable(By.xpath(config.getActionDropDownXpath())));
			// actionDropDown.click();

			Log.info("Click on viewNotes link");
			WebElement viewNotesLink = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getViewNotesXpath())));
			viewNotesLink.click();

			Log.info("Verify Modal window title");
			WebElement title = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getModalWindowTitleXpath())));
			String titleText = title.getText();
			Assert.assertEquals(titleText, GlobalValues.viewNoteTitle);

			String noteType = driver.findElement(By.xpath("//p-dropdown/div/label")).getText();
			Assert.assertEquals(noteType, GlobalValues.Note_Type);

			String sensitiveisCheck = driver.findElement(By.xpath("//nm-single-checkbox//input[@checked]"))
					.getAttribute("checked");
			String flag = "false";
			if (sensitiveisCheck.equals("true")) {
				flag = "true";
			}
			Assert.assertEquals(flag, GlobalValues.Note_Sensitive_Value_Assigned);

			String noteInfo = driver
					.findElement(By
							.xpath("//nm-input-textarea//textarea//following-sibling::pre"))
					.getAttribute("textContent");
			Assert.assertEquals(noteInfo, GlobalValues.Note_Info);

			Log.info("Click on Back button on view notes page");
			gm.clickBack(1);

			//
			// Log.info("Verify Modal window values");
			// for (int v = 2; v <= 6; v++) {
			// String xpathtoReplace = config.getViewNoteCellXpath();
			// String ViewNoteCellXpath = xpathtoReplace.replaceAll("replaceMe",
			// String.valueOf(v));
			// WebElement modalwindowCell =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ViewNoteCellXpath)));
			// String cellValue = modalwindowCell.getText();
			// System.out.println(cellValue);
			// switch (v) {
			// case 2:
			// String flag = "false";
			// if (GlobalValues.Note_Sensitive_Value.toString().equals("*")) {
			// flag = "true";
			// }
			// Assert.assertTrue(cellValue.contains(flag));
			// Log.info("Validation of Critical flag is done");
			// break;
			// case 3:
			// //
			// Assert.assertTrue(cellValue.contains(GlobalValues.Note_Date_time.toString()));
			// Log.info("Validation of date cell value is done");
			// break;
			// case 4:
			// Assert.assertTrue(cellValue.contains(GlobalValues.Note_TakenBy.toString()));
			// Log.info("Validation of takenBy cell value is done");
			// break;
			// case 5:
			// Assert.assertTrue(cellValue.contains(GlobalValues.Note_Type.toString()));
			// Log.info("Validation of note type cell value is done");
			// break;
			// case 6:
			// Assert.assertTrue(cellValue.contains(GlobalValues.Note_Info.toString()));
			// Log.info("Validation of note info cell value is done");
			// break;
			// }
			//
			// }
			//
			// Log.info("Click on Close button on modal window");
			// WebElement close = wait
			// .until(ExpectedConditions.elementToBeClickable(By.xpath(config.getCloseModalWindowXpath())));
			// close.click();
			

		} catch (Exception e) {
			Log.info("object disabled");
			e.printStackTrace();
			
		}

	}

	public void verifyFilterBy(String type) {

		Log = Logger.getLogger("ListOfNotesPage.class");
		PropertyConfigurator.configure("log4j.properties");
		Log.info("verifyFilterBy has started");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

		Date date = new Date();
		DateFormat dfFilter = new SimpleDateFormat("MM/dd/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		System.out.println(c.getTime());
		c.add(Calendar.DATE, -2);
		String frmDt = dfFilter.format(c.getTime());
		c.setTime(date);
		c.add(Calendar.DATE, 2);
		String toDt = dfFilter.format(c.getTime());

		try {

			/*
			 * Log.info("Click on notes"); LeftNavigationLink leftNavigationLink
			 * = PageFactory.initElements(driver, LeftNavigationLink.class);
			 * leftNavigationLink.clickNotes();
			 */

			Log.info("Enter fromDate  ");
			WebElement fromDate = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getFromDateXpath())));
			fromDate.clear();
			fromDate.sendKeys(frmDt);
			fromDate.sendKeys(Keys.TAB);

			Log.info("Enter toDate ");
			WebElement toDate = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getToDateXpath())));
			toDate.clear();
			toDate.sendKeys(toDt);
			toDate.sendKeys(Keys.TAB);

			// Log.info("Select note type as " + type);
			// GlobalValues.Note_Type = type;
			// WebElement statusDropDown = wait
			// .until(ExpectedConditions.elementToBeClickable(By.xpath(config.getStatusDropDownXpath())));
			//
			// statusDropDown.click();

			WebElement noteTypeDropDownTypeparent = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
					"//label[contains(text(),'Note Type')]//ancestor::label//parent::nm-input-label//following-sibling::p-dropdown//descendant::span")));

			noteTypeDropDownTypeparent.click();

			WebElement noteTypeDropDownType = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//label[contains(text(),'Note Type')]//parent::nm-input-label//following::p-dropdown//following-sibling::div//span[text()='"
							+ type + "']")));

			noteTypeDropDownType.click();

			Thread.sleep(GlobalValues.Sleep_wait_time);

			Log.info("click on Filter button ");
			WebElement filter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getFilterXpath())));
			filter.click();

			Thread.sleep(GlobalValues.Sleep_wait_time);

			Log.info("Capture values from Grid to verify filterBy");
			Map<Integer, String> gridValueHashMap = new HashMap<Integer, String>();

			String cellValue = null;

			for (int rw = 1; rw <= 1; rw++) {
				for (int cl = 1; cl <= 5; cl++) {

					String tableXpathAfterRowReplace = config.getNotesTableXpath().replaceAll("replaceMeWithRow",
							String.valueOf(rw));
					String tableXpathAfterColReplace = tableXpathAfterRowReplace.replaceAll("replaceMeWithCol",
							String.valueOf(cl));
					// Capture cell value
					WebElement cell = wait
							.until(ExpectedConditions.elementToBeClickable(By.xpath(tableXpathAfterColReplace)));
					cellValue = cell.getText().toString();
					gridValueHashMap.put(cl, cellValue);
				}
			}

			Log.info("Verification of date ");

			String NoteDate = gridValueHashMap.get(3).toString();
			NoteDate = NoteDate.substring(0, 10);
			Log.info("VNoteDate.toString()::" + NoteDate.toString());
			Log.info("GlobalValues.Note_Date_time::" + GlobalValues.Note_Date_time);

			Assert.assertEquals(NoteDate.toString(), GlobalValues.Note_Date_time);
			Assert.assertEquals(gridValueHashMap.get(2), GlobalValues.Note_Type);
			Log.info("Verification of note type::" + GlobalValues.Note_Type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Log.info("verifyFilterBy has ended");
	}

	public void verifyDefaultOrderByCriticalNote() {

		Log = Logger.getLogger("ListOfNotesPage.class");
		PropertyConfigurator.configure("log4j.properties");
		Log.info("verifyDefaultOrderByCriticalNote has started");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

		try {

			List<WebElement> sensitiveElementsList = driver
					.findElements(By.xpath("//table/tbody/tr/td[1]/span[contains(text(),'*')]"));
			int sensitiveElementsSize = sensitiveElementsList.size();

			for (int i = 0; i < sensitiveElementsSize; i++) {
				String sensitiveNote = driver.findElement(By.xpath("//table/tbody/tr[" + (i + 1) + "]/td[1]/span"))
						.getText();
				Assert.assertEquals(sensitiveNote, "*");
			}

			Log.info("verification of default order by Sensitive note based on * ");
			// Map<Integer, String> gridValueForSorting = new HashMap<Integer,
			// String>();
			//
			// String cellValue = null;

			// for (int rw = 1; rw <= 5; rw++) {
			// Thread.sleep(GlobalValues.Sleep_wait_time);
			// for (int cl = 1; cl <= 1; cl++) {
			// String tableXpathAfterRowReplace =
			// config.getNotesTableXpath().replaceAll("replaceMeWithRow",String.valueOf(rw));
			// String tableXpathAfterColReplace =
			// tableXpathAfterRowReplace.replaceAll("replaceMeWithCol",String.valueOf(cl));
			// // Capture cell value
			// WebElement cell =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath(tableXpathAfterColReplace)));
			// cellValue = cell.getText().toString();
			// gridValueForSorting.put(rw, cellValue);
			// }
			//
			// }
			//
			// //table/tbody/tr[i]/td[1]
			// Assert.assertEquals(gridValueForSorting.get(1).toString(), "*");
			// Assert.assertEquals(gridValueForSorting.get(2).toString(), "*");
			// Assert.assertEquals(gridValueForSorting.get(3).toString(), "*");
			// Assert.assertEquals(gridValueForSorting.get(4).toString(), "");
			// Assert.assertEquals(gridValueForSorting.get(5).toString(), "");
			//

			// Log.info("verification of default order by critical note 4");

		} catch (Exception e) {
			e.printStackTrace();
		}
		Log.info("verifyDefaultOrderByCriticalNote has ended");
	}

	public void verifySecondDefaultOrderByDate() {

		Log = Logger.getLogger("ListOfNotesPage.class");
		PropertyConfigurator.configure("log4j.properties");

		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

		try {

			Map<Integer, String> gridValueForSorting = new HashMap<Integer, String>();

			String cellValue = null;

			for (int rw = 1; rw <= 4; rw++) {
				for (int cl = 1; cl <= 1; cl++) {
					Thread.sleep(GlobalValues.Sleep_wait_time);

					String tableXpathAfterRowReplace = config.getNotesTableXpath().replaceAll("replaceMeWithRow",
							String.valueOf(rw));
					String tableXpathAfterColReplace = tableXpathAfterRowReplace.replaceAll("replaceMeWithCol",
							String.valueOf(cl));
					// Capture cell value
					WebElement cell = wait
							.until(ExpectedConditions.elementToBeClickable(By.xpath(tableXpathAfterColReplace)));
					cellValue = cell.getText().toString();

					gridValueForSorting.put(rw, cellValue);
				}
				System.out.println(gridValueForSorting.get(rw));
			}

			Assert.assertEquals(gridValueForSorting.get(1), "*");
			Assert.assertEquals(gridValueForSorting.get(2), "*");
			Assert.assertEquals(gridValueForSorting.get(3), "");
			Assert.assertEquals(gridValueForSorting.get(4), "");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void verifyNotesGrid() throws Exception {
		Log = Logger.getLogger("ListOfNotesPage.class");
		PropertyConfigurator.configure("log4j.properties");
		DashboardFunctions df = new DashboardFunctions(driver);
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

		WebElement gridHeaderElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p-header//caption[contains(text(),'Notes')]")));
		String gridHeader = gridHeaderElement.getText();
		Log.info("Grid Header is " + gridHeader);
		try {

			if (gridHeader.contains("Notes")) {
				List<String> actualColumnNames = df.getGridColumnNames();
				Log.info("Grid Header has been verified");
				Log.info("Getting the list of Column names for Notes Grid");
				Thread.sleep(GlobalValues.Sleep_wait_time);
				df.validateGridColumnNames(GlobalValues.NotesGrid, actualColumnNames);
				Log.info("Notes Grid Column Names has been verified");
			}
		} catch (Exception e) {
			Log.info(e + "  -This is not Notes Grid. Verify the Grid Name");
		}
	}

	public void sortingOnColumns() {

		Log = Logger.getLogger("ListOfNotesPage.class");
		PropertyConfigurator.configure("log4j.properties");
		LeftNavigationLink lnk = new LeftNavigationLink(driver);

		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		DashboardFunctions df = new DashboardFunctions(driver);

		try {
			lnk.clickNotes();
			Thread.sleep(GlobalValues.Sleep_wait_time);
			//df.columnSortingOnGrid(driver, 1);
			df.columnSorting(driver);
			Log.info("Column level sorting on -'Notes Grid' ");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void toolTipOnNoteDetails() throws Exception {

		Log = Logger.getLogger("ListOfNotesPage.class");
		PropertyConfigurator.configure("log4j.properties");
		LeftNavigationLink lnk = new LeftNavigationLink(driver);
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

		try {
			lnk.clickNotes();
			Thread.sleep(GlobalValues.Sleep_wait_time);
			WebElement noteDetailsElement = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tbody//tr[1]//td[5]/span")));

			Thread.sleep(GlobalValues.Sleep_wait_time);

			String noteDetailsText = noteDetailsElement.getAttribute("title");
			Log.info("ToolTip text is - " + noteDetailsText);

			Assert.assertEquals(noteDetailsText, GlobalValues.Note_Info);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void addNoteFromActionTray(String sensitiveFlag, String ntType, String ntInfo) throws InterruptedException {

		Log = Logger.getLogger("ListOfNotesPage.class");
		PropertyConfigurator.configure("log4j.properties");
		LeftNavigationLink lnk = new LeftNavigationLink(driver);
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);

		try {
			Log.info("Click on Note Icon from Action Tray");
			WebElement addNoteIconFromActionTrayElement = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@title ='Add Notes']")));
			addNoteIconFromActionTrayElement.click();

			Thread.sleep(GlobalValues.Sleep_wait_time);

			Log.info("Select note type as " + ntType);
			Thread.sleep(GlobalValues.Sleep_wait_time);

			GlobalValues.Note_Type = ntType;
			WebElement noteTypeDropDown = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getNoteTypeXpath())));

			noteTypeDropDown.click();
			Thread.sleep(GlobalValues.Sleep_wait_time);

			WebElement noteTypeDropDownType = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//label[contains(text(),'Note Type')]//parent::nm-input-label//following-sibling::p-dropdown//div//span[text()='"
							+ ntType + "']")));
			noteTypeDropDownType.click();
			Thread.sleep(GlobalValues.Sleep_wait_time);

			if (sensitiveFlag.equals("true")) {
				Log.info("Select checkbox for sensitive flag ");
				WebElement flagAsSensitive = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getSensitiveChkBoxXpath())));
				flagAsSensitive.click();
				Thread.sleep(2000);
			}

			GlobalValues.Note_Sensitive_Value_Assigned = sensitiveFlag;

			Log.info("Enter note information ");

			GlobalValues.Note_Info = ntInfo;

			WebElement noteInfo = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getNoteInfoXpath())));
			noteInfo.sendKeys(GlobalValues.Note_Info);

			Log.info("Click on Save & Submit ");

			WebElement saveSubmit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getSaveAndSubmitXpath())));
			saveSubmit.click();
			Thread.sleep(GlobalValues.Sleep_wait_time);

			GlobalValues.Note_TakenBy = GlobalValues.DBCaseOwnerDisplayName.toLowerCase();

			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			Date date = new Date();
			df.setTimeZone(TimeZone.getTimeZone("UTC"));
			GlobalValues.Note_Date_time = df.format(date);
			Log.info(GlobalValues.Note_Date_time);
			Log.info("**********successfully note has been created**********");

		} catch (Exception e) {
			e.printStackTrace();
			Log.info("Object not visible" + e.getMessage().toString());
		}

		Log.info("Added Note by clicking on Add Notes icon from action tray");
	}
}
