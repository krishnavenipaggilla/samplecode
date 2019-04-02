package com.nimbus.pages;

import java.awt.Robot;
import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.ConfigReader;

public class ValidateAnnualAssessment {

	ConfigReader config = new ConfigReader();

	WebDriver driver;
	Logger Log;

	public ValidateAnnualAssessment(WebDriver ldriver) {
		this.driver = ldriver;
	}

	public ValidateAnnualAssessment(Logger lLog) {
		this.Log = lLog;
	}

	public void validate_annual_assessment(String FileName, String sheetName, int col) throws Exception {

		Global_Method gm = new Global_Method(driver);

		File sour = new File("./TestData/" + FileName + ".xlsx");
		FileInputStream fiss = new FileInputStream(sour);
		XSSFWorkbook wb = new XSSFWorkbook(fiss);
		XSSFSheet Sheet1 = wb.getSheet(sheetName);
		Robot r = new Robot();

		Log = Logger.getLogger("AnnualAssessment.class");
		PropertyConfigurator.configure("log4j.properties");

		WebDriverWait wait = new WebDriverWait(driver, 15);

		int row = Sheet1.getLastRowNum();
		int j = col;

		Log.info(Sheet1.getRow(0).getCell(j).getStringCellValue().toString() + "for " + sheetName);

		try {
			for (int i = 1; i <= row; i++) {
				String QuestionText = Sheet1.getRow(i).getCell(0).getStringCellValue().toString();
				String Question = null;
				String instance = null;
				String Question1 = null;
				String AssessmentUItext = null;

				if (QuestionText.contains("|")) {
					Question = QuestionText.split("\\|")[0];
					instance = QuestionText.split("\\|")[1];
				} else {
					Question = QuestionText;
					instance = "1";
				}

				// String Answer =
				// String.valueOf(Sheet1.getRow(i).getCell(j).getStringCellValue().toString());
				Cell cellData = Sheet1.getRow(i).getCell(j);
				if (cellData == null)
					continue;
				cellData.setCellType(Cell.CELL_TYPE_STRING);
				String Answer = cellData.toString();

				if (StringUtils.isEmpty(Answer))
					continue;
				if (Sheet1.getRow(i).getCell(1).getStringCellValue().toString().equals("drop") && Answer.length() > 0) {
					try {
						Thread.sleep(6000);
						String Labeltext = "(//label[contains(text(),\"" + Question + "\")])[" + instance + "]";
						String dropAnsSelected = "(//label[contains(text(),\"" + Question + "\")])[" + instance
								+ "]//parent::nm-input-label//following-sibling::p-dropdown//div//label";

						WebElement AssessmentText = (new WebDriverWait(driver, 30))
								.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Labeltext)));
						// code change for text with multiple space while
						// gettext() or getattribute automatically converts it.
						Question1 = Question.replaceAll(" +", "");
						AssessmentUItext = AssessmentText.getAttribute("innerText").toString();
						AssessmentUItext = AssessmentUItext.replaceAll(" +", "");
						Assert.assertEquals(Question1, AssessmentUItext);
						Log.info("Verify " + AssessmentText.getAttribute("innerText") + " label");

						WebElement AssessmentTextAnsFromUI = (new WebDriverWait(driver, 30))
								.until(ExpectedConditions.presenceOfElementLocated(By.xpath(dropAnsSelected)));
						String AnswerFromUI = AssessmentTextAnsFromUI.getAttribute("innerText").toString();
						Log.info("Captured Answer " + AssessmentTextAnsFromUI.getAttribute("innerText").toString());
						Thread.sleep(GlobalValues.Sleep_wait_time);
						Assert.assertEquals(Answer, AnswerFromUI);
						Log.info("Verify " + AnswerFromUI + " ValueFromExcel"+ ":" + Answer);
						
					} catch (Exception ex) {
						Log.info("Question/Answer is not available / changed" + ex.getMessage().toString());
					} 
				}else if (Sheet1.getRow(i).getCell(1).getStringCellValue().toString().equals("verify")
						&& Answer.length() > 0) {

					try {
						String VerifyText = "(//*[contains(text(),\"" + Question + "\")])[" + instance + "]";
						WebElement AssessmentText = (new WebDriverWait(driver, 30))
								.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(VerifyText)));
						// code change for text with multiple space while
						// gettext() or getattribute automatically converts it.
						Question1 = Question.replaceAll(" +", "");
						AssessmentUItext = AssessmentText.getAttribute("innerText").toString();
						AssessmentUItext = AssessmentUItext.replaceAll(" +", "");
						Thread.sleep(GlobalValues.Sleep_wait_time);
						Assert.assertEquals(Question1, AssessmentUItext);
						Thread.sleep(GlobalValues.Sleep_wait_time);
						Log.info("Verify " + AssessmentText.getAttribute("innerText") + " label"+ ":" + Answer);
					} catch (Exception ex) {
						Log.info("Question is not available / changed" + ex.getMessage().toString());
					}
				}else if (Sheet1.getRow(i).getCell(1).getStringCellValue().toString().equals("radio")
						&& Answer.length() > 0) {
					try {
						String Legendtext = "(//legend[contains(text(),\"" + Question + "\")])[" + instance + "]";
						String RadioOptionSelected = "(//legend[contains(text(),\"" + Question + "\")])[" + instance
								+ "]//..//child::label[@class ='ng-star-inserted ui-radiobutton-label ui-label-active']";

						WebElement Questiononexpath = wait
								.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Legendtext)));
						// code change for text with multiple space while
						// gettext() or getattribute automatically converts it.

						Question1 = Question.replaceAll(" +", "");
						AssessmentUItext = Questiononexpath.getAttribute("innerText").toString();
						AssessmentUItext = AssessmentUItext.replaceAll(" +", "");
						Assert.assertEquals(Question1, AssessmentUItext);
						Log.info("Verify " + Questiononexpath.getAttribute("innerText").toString());
						
						Thread.sleep(GlobalValues.Sleep_wait_time);
						WebElement AssessmentTextAnsFromUI = (new WebDriverWait(driver, 30))
								.until(ExpectedConditions.presenceOfElementLocated(By.xpath(RadioOptionSelected)));
						String AnswerFromUI = AssessmentTextAnsFromUI.getAttribute("innerText").toString();
						Log.info("Captured Answer " + AssessmentTextAnsFromUI.getAttribute("innerText").toString());
						Thread.sleep(GlobalValues.Sleep_wait_time);
						Assert.assertEquals(Answer, AnswerFromUI);
						Log.info("Verify " + AnswerFromUI + " ValueFromExcel" + ":" + Answer );

						
					} catch (Exception ex) {
						Log.info("Question is not available / changed" + ex.getMessage().toString());
					}
				}else if (Sheet1.getRow(i).getCell(1).getStringCellValue().toString().equals("text")
						&& Answer.length() > 0) {
					try {
						String Labeltext = "(//label[contains(text(),\"" + Question + "\")])[" + instance + "]";
						String TextboxAnsEntered = "(//label[contains(text(),\"" + Question + "\")])[" + instance
								+ "]//parent::nm-input-label//following-sibling::pre";

						WebElement AssessmentText = wait
								.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Labeltext)));
						// code change for text with multiple space while
						// gettext() or getattribute automatically converts it.
						Question1 = Question.replaceAll(" +", "");
						AssessmentUItext = AssessmentText.getAttribute("innerText").toString();
						AssessmentUItext = AssessmentUItext.replaceAll(" +", "");
						Assert.assertEquals(Question1, AssessmentUItext);
						Log.info("Verify " + AssessmentText.getAttribute("innerText") + " label");

						
						Thread.sleep(GlobalValues.Sleep_wait_time);
						WebElement AssessmentTextAnsFromUI = (new WebDriverWait(driver, 30))
								.until(ExpectedConditions.presenceOfElementLocated(By.xpath(TextboxAnsEntered)));
						String AnswerFromUI = AssessmentTextAnsFromUI.getAttribute("innerText").toString();
						Log.info("Captured Answer " + AssessmentTextAnsFromUI.getAttribute("innerText").toString());
						Thread.sleep(GlobalValues.Sleep_wait_time);
						Assert.assertEquals(Answer, AnswerFromUI);
						Log.info("Verify " + AnswerFromUI + " ValueFromExcel"+ ":" + Answer);
														
					} catch (Exception ex) {
						Log.info("Question is not available / changed " + ex.getMessage().toString());
						ex.printStackTrace();
					}
				}else if (Sheet1.getRow(i).getCell(1).getStringCellValue().toString().equals("date")
						&& Answer.length() > 0) {
					try {
						String Labeltext = "(//label[contains(text(),\"" + Question + "\")])[" + instance + "]";
						String TextboxAnsEntered = "(//label[contains(text(),\"" + Question + "\")])[" + instance
								+ "]/parent::nm-input-label//following-sibling::p-calendar//input";

						WebElement AssessmentText = wait
								.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Labeltext)));
						// code change for text with multiple space while
						// gettext() or getattribute automatically converts it.
						Question1 = Question.replaceAll(" +", "");
						AssessmentUItext = AssessmentText.getAttribute("innerText").toString();
						AssessmentUItext = AssessmentUItext.replaceAll(" +", "");
						Assert.assertEquals(Question1, AssessmentUItext);
						Log.info("Verify " + AssessmentText.getAttribute("innerText") + " label");

						
						Thread.sleep(GlobalValues.Sleep_wait_time);
						WebElement AssessmentTextAnsFromUI = (new WebDriverWait(driver, 30))
								.until(ExpectedConditions.presenceOfElementLocated(By.xpath(TextboxAnsEntered)));
						String AnswerFromUI = AssessmentTextAnsFromUI.getAttribute("value").toString();
						Log.info("Captured Answer " + AssessmentTextAnsFromUI.getAttribute("value").toString());
						Thread.sleep(GlobalValues.Sleep_wait_time);
						Assert.assertEquals(Answer, AnswerFromUI);
						Log.info("Verify " + AnswerFromUI + " ValueFromExcel"+ ":" + Answer);
														
					} catch (Exception ex) {
						Log.info("Question is not available / changed " + ex.getMessage().toString());
						ex.printStackTrace();
					}
					}else if (Sheet1.getRow(i).getCell(1).getStringCellValue().toString().equals("textarea")
						&& Answer.length() > 0) {
					try {
						String Labeltext = "(//label[contains(text(),\"" + Question + "\")])[" + instance + "]";
						String TextareaAnsEntered = "(//label[contains(text(),\"" + Question + "\")])[" + instance
								+ "]//parent::nm-input-label//following-sibling::pre";

						WebElement AssessmentText = wait
								.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Labeltext)));
						// code change for text with multiple space while
						// gettext() or getattribute automatically converts it.
						Question1 = Question.replaceAll(" +", "");
						AssessmentUItext = AssessmentText.getAttribute("innerText").toString();
						AssessmentUItext = AssessmentUItext.replaceAll(" +", "");
						Assert.assertEquals(Question1, AssessmentUItext);
						Log.info("Verify " + AssessmentText.getAttribute("innerText") + " label");

						Thread.sleep(GlobalValues.Sleep_wait_time);
						WebElement AssessmentTextAnsFromUI = (new WebDriverWait(driver, 30))
								.until(ExpectedConditions.presenceOfElementLocated(By.xpath(TextareaAnsEntered)));
						String AnswerFromUI = AssessmentTextAnsFromUI.getAttribute("innerText").toString();
						Log.info("Captured Answer " + AssessmentTextAnsFromUI.getAttribute("innerText").toString());
						Thread.sleep(GlobalValues.Sleep_wait_time);
						Assert.assertEquals(Answer, AnswerFromUI);
						Log.info("Verify " + AnswerFromUI + " ValueFromExcel"+ ":" + Answer);
														
						
					} catch (Exception ex) {
						Log.info("Question is not available / changed" + ex.getMessage().toString());
					}
				}
			}
		} catch (Exception Ex) {
			Log.info(Sheet1.getRow(0).getCell(j).getStringCellValue().toString() + " is failed");
			Log.info("object is not visible in Assessment " + Ex.getMessage().toString());
			Ex.printStackTrace();

			if (fiss != null)
				fiss.close();
		} finally {
			fiss.close();
		}

	}
}