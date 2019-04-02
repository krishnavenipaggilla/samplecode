package com.nimbus.pages;

import static org.testng.Assert.assertEquals;


import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

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
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Verify;

import utility.ConfigReader;

/*
 * ClassName : Annual Assessment
 * Description : Fill Assessment Forms in loop for multiple scenario
 * Data Source : Excel sheet in Test Data
 * Feature     : Support Normal Assessment and Assessment with Section
 */

public class AnnualAssessment {
	ConfigReader config = new ConfigReader();

	WebDriver driver;
	Logger Log;

	public AnnualAssessment(WebDriver ldriver) {
		this.driver = ldriver;
	}

	public AnnualAssessment(Logger lLog) {
		this.Log = lLog;
	}

	public void annual_assessment(String FileName, String sheetName, int col) throws Exception {

		Global_Method gm = new Global_Method(driver);

		File sour = new File("./TestData/" + FileName + ".xlsx");
		FileInputStream fiss = new FileInputStream(sour);
		XSSFWorkbook wb = new XSSFWorkbook(fiss);
		XSSFSheet Sheet1 = wb.getSheet(sheetName);
		
		

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

				if (Sheet1.getRow(i).getCell(1).getStringCellValue().toString().equalsIgnoreCase("URL")) {
					driver.get(Answer);
					Log.info("Welcome to assessment page");
				}

				else if (Sheet1.getRow(i).getCell(1).getStringCellValue().toString().equalsIgnoreCase("Link")
						|| Sheet1.getRow(i).getCell(1).getStringCellValue().toString().equalsIgnoreCase("Modal")) {
					WebElement Assessmentlink;
					if (Sheet1.getRow(i).getCell(1).getStringCellValue().toString().equalsIgnoreCase("Link")) {
						Assessmentlink = wait.until(ExpectedConditions
								.elementToBeClickable(By.xpath("//button[contains(text(),'" + Question + "')]")));
					} else {
						Assessmentlink = wait.until(ExpectedConditions
								.elementToBeClickable(By.xpath("//button[text() ='" + Question + "']")));
					}

					Log.info("Clicked on " + Assessmentlink.getAttribute("innerText").toString());
					Assessmentlink.click();
					Log.info("Welcome to Assessment link");

				}

				else if (Sheet1.getRow(i).getCell(1).getStringCellValue().toString().equalsIgnoreCase("Section")) {
					WebElement SectionLink = wait.until(
							ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='" + Question + "']")));
					Log.info("Clicked on " + SectionLink.getAttribute("innerText").toString());
					SectionLink.click();
				}else if (Sheet1.getRow(i).getCell(1).getStringCellValue().toString().equals("date")
						&& Answer.length() > 0) {
					try {
						String Labeltext = "(//label[contains(text(),\"" + Question + "\")])[" + instance + "]";
						String TextboxAns = "(//label[contains(text(),\"" + Question + "\")])[" + instance
								+ "]//following::input";

						WebElement AssessmentText = wait
								.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Labeltext)));
						// code change for text with multiple space while
						// gettext() or getattribute automatically converts it.
						Question1 = Question.replaceAll(" +", "");
						AssessmentUItext = AssessmentText.getAttribute("innerText").toString();
						AssessmentUItext = AssessmentUItext.replaceAll(" +", "");
						Assert.assertEquals(Question1, AssessmentUItext);
						Log.info("Verify " + AssessmentText.getAttribute("innerText") + " label");

						WebElement AssessmentTextAns = wait
								.until(ExpectedConditions.elementToBeClickable(By.xpath(TextboxAns)));
						AssessmentTextAns.clear();
						AssessmentTextAns.sendKeys(Answer);
						AssessmentTextAns.sendKeys(Keys.TAB);
						Log.info("Entered " + AssessmentText.getAttribute("innerText"));
					} catch (Exception ex) {
						Log.info("Question is not available / changed " + ex.getMessage().toString());
					}
				}

				else if (Sheet1.getRow(i).getCell(1).getStringCellValue().toString().equals("text")
						&& Answer.length() > 0) {
					try {
						String Labeltext = "(//label[contains(text(),\"" + Question + "\")])[" + instance + "]";
						String TextboxAns = "(//label[contains(text(),\"" + Question + "\")])[" + instance
								+ "]//following::input";

						WebElement AssessmentText = wait
								.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Labeltext)));
						// code change for text with multiple space while
						// gettext() or getattribute automatically converts it.
						Question1 = Question.replaceAll(" +", "");
						AssessmentUItext = AssessmentText.getAttribute("innerText").toString();
						AssessmentUItext = AssessmentUItext.replaceAll(" +", "");
						Assert.assertEquals(Question1, AssessmentUItext);
						Log.info("Verify " + AssessmentText.getAttribute("innerText") + " label");

						WebElement AssessmentTextAns = wait
								.until(ExpectedConditions.elementToBeClickable(By.xpath(TextboxAns)));
						AssessmentTextAns.clear();
						
					

						Thread.sleep(1000);
						
						
						AssessmentTextAns.sendKeys(Answer);
						AssessmentTextAns.sendKeys(Keys.TAB);
						Log.info("Entered " + AssessmentText.getAttribute("innerText"));
					} catch (Exception ex) {
						Log.info("Question is not available / changed " + ex.getMessage().toString());
					}
				}

				else if (Sheet1.getRow(i).getCell(1).getStringCellValue().toString().equals("textarea")
						&& Answer.length() > 0) {
					try {
						String Labeltext = "(//label[contains(text(),\"" + Question + "\")])[" + instance + "]";
						String TextareaAns = "(//label[contains(text(),\"" + Question + "\")])[" + instance
								+ "]//parent::nm-input-label//following-sibling::textarea";

						WebElement AssessmentText = wait
								.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Labeltext)));
						// code change for text with multiple space while
						// gettext() or getattribute automatically converts it.
						Question1 = Question.replaceAll(" +", "");
						AssessmentUItext = AssessmentText.getAttribute("innerText").toString();
						AssessmentUItext = AssessmentUItext.replaceAll(" +", "");
						Assert.assertEquals(Question1, AssessmentUItext);
						Log.info("Verify " + AssessmentText.getAttribute("innerText") + " label");

						WebElement AssessmentTextAns = wait
								.until(ExpectedConditions.elementToBeClickable(By.xpath(TextareaAns)));
						AssessmentTextAns.sendKeys(Answer);
						Log.info("Entered " + AssessmentText.getAttribute("innerText"));
					} catch (Exception ex) {
						Log.info("Question is not available / changed" + ex.getMessage().toString());
					}
				}

				else if (Sheet1.getRow(i).getCell(1).getStringCellValue().toString().equals("radio")
						&& Answer.length() > 0) {
					try {
						String Legendtext = "(//legend[contains(text(),\"" + Question + "\")])[" + instance + "]";
						String RadioOption = "((//legend[contains(text(),\"" + Question + "\")])[" + instance
								+ "]//following::label[contains(text(),'" + Answer + "')])[1]";

						WebElement Questiononexpath = wait
								.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Legendtext)));
						// code change for text with multiple space while
						// gettext() or getattribute automatically converts it.

						Question1 = Question.replaceAll(" +", "");
						AssessmentUItext = Questiononexpath.getAttribute("innerText").toString();
						AssessmentUItext = AssessmentUItext.replaceAll(" +", "");
						Assert.assertEquals(Question1, AssessmentUItext);
						Log.info("Verify " + Questiononexpath.getAttribute("innerText").toString());

						WebElement AnsoneXpath = wait
								.until(ExpectedConditions.elementToBeClickable(By.xpath(RadioOption)));
						AnsoneXpath.click();
						Log.info("Clicked on option for " + Question.toString());
					} catch (Exception ex) {
						Log.info("Question is not available / changed" + ex.getMessage().toString());
					}
				}

				else if (Sheet1.getRow(i).getCell(1).getStringCellValue().toString().equals("check")
						&& Answer.length() > 0) {
					try {
						String Legendtext = "(//legend[contains(text(),\"" + Question + "\")])[" + instance + "]";
						

//						String Legendtext = "//label[contains(text(),\"" + Question + "\")]";
						WebElement Questiononexpath = (new WebDriverWait(driver, 30))
								.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Legendtext)));
						// code change for text with multiple space while
						// gettext() or getattribute automatically converts it.
						Question1 = Question.replaceAll(" +", "");
						AssessmentUItext = Questiononexpath.getAttribute("innerText").toString();
						AssessmentUItext = AssessmentUItext.replaceAll(" +", "");
						Assert.assertEquals(Question1, AssessmentUItext);
						Log.info("Verify " + Questiononexpath.getAttribute("innerText").toString());

						String[] Option = Answer.split(",");
						for (String w : Option) {
//							String CheckOption = "(//legend[contains(text(),\"" + Question + "\")])[" + instance
//									+ "]//parent::fieldset//label[contains(text(),'" + w + "')]";
//							Thread.sleep(1000);
							
							String CheckOption = "(//legend[contains(text(),\"" + Question + "\")])[" + instance

	                                + "]//../parent::fieldset//label[contains(text(),'" + w + "')]";
	                  Thread.sleep(1000);

		
							WebElement AnsoneXpath = wait
									.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CheckOption)));
							AnsoneXpath.click();

						}
						Log.info("Clicked on option for " + Question1.toString());
					} catch (Exception ex) {
						Log.info("Question is not available / changed" + ex.getMessage().toString());
					}
				}

				else if (Sheet1.getRow(i).getCell(1).getStringCellValue().toString().equals("drop")
						&& Answer.length() > 0) {
					try {
						Thread.sleep(6000);
						String Labeltext = "(//label[contains(text(),\"" + Question + "\")])[" + instance + "]";
						String SelectAns = "(//label[contains(text(),\"" + Question + "\")])[" + instance
								+ "]//parent::nm-input-label//following-sibling::p-dropdown//div";

						WebElement AssessmentText = (new WebDriverWait(driver, 30))
								.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Labeltext)));
						// code change for text with multiple space while
						// gettext() or getattribute automatically converts it.
						Question1 = Question.replaceAll(" +", "");
						AssessmentUItext = AssessmentText.getAttribute("innerText").toString();
						AssessmentUItext = AssessmentUItext.replaceAll(" +", "");
						Assert.assertEquals(Question1, AssessmentUItext);
						Log.info("Verify " + AssessmentText.getAttribute("innerText") + " label");

						WebElement AssessmentTextAns = (new WebDriverWait(driver, 30))
								.until(ExpectedConditions.elementToBeClickable(By.xpath(SelectAns)));
						AssessmentTextAns.click();
						Thread.sleep(GlobalValues.Sleep_wait_time);
						String SelectAnswer = SelectAns + "//span[text()='" + Answer + "']";

						WebElement AssessmentTextAnswer = (new WebDriverWait(driver, 30))
								.until(ExpectedConditions.elementToBeClickable(By.xpath(SelectAnswer)));
						AssessmentTextAnswer.click();
						Thread.sleep(GlobalValues.Sleep_wait_time);

						Log.info("Entered " + AssessmentText.getAttribute("innerText").toString());
					} catch (Exception ex) {
						Log.info("Question is not available / changed" + ex.getMessage().toString());
					}
				}

				else if (Sheet1.getRow(i).getCell(1).getStringCellValue().toString().equals("verify")
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
						Log.info("Verify " + AssessmentText.getAttribute("innerText") + " label");
					} catch (Exception ex) {
						Log.info("Question is not available / changed" + ex.getMessage().toString());
					}
				}

				else if (Sheet1.getRow(i).getCell(1).getStringCellValue().toString().equals("buttonclick")
						&& Answer.length() > 0) {

					try {
						String VerifyText = "(//button[contains(text(),\"" + Question + "\")])[" + instance + "]";
						WebElement AssessmentText = (new WebDriverWait(driver, 30))
								.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(VerifyText)));
						// code change for text with multiple space while
						// gettext() or getattribute automatically converts it.
						Question1 = Question.replaceAll(" +", "");
						AssessmentUItext = AssessmentText.getAttribute("innerText").toString();
						AssessmentUItext = AssessmentUItext.replaceAll(" +", "");
						Assert.assertEquals(Question1, AssessmentUItext);
						Log.info("Clicked on " + AssessmentText.getAttribute("innerText") + " label");
						AssessmentText.click();
					} catch (Exception ex) {
						Log.info("Question is not available / changed" + ex.getMessage().toString());
					}
				}

				else if (Sheet1.getRow(i).getCell(1).getStringCellValue().toString().equals("click")
						&& Answer.length() > 0) {

					try {
						Thread.sleep(2000);
						String VerifyText = "(//*[contains(text(),\"" + Question + "\")])[" + instance + "]";
						WebElement AssessmentText = (new WebDriverWait(driver, 30))
								.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(VerifyText)));
						// code change for text with multiple space while
						// gettext() or getattribute automatically converts it.
						Question1 = Question.replaceAll(" +", "");
						AssessmentUItext = AssessmentText.getAttribute("innerText").toString();
						AssessmentUItext = AssessmentUItext.replaceAll(" +", "");
						Assert.assertEquals(Question1, AssessmentUItext);
						Log.info("Clicked on " + AssessmentText.getAttribute("innerText") + " label");
						AssessmentText.click();
					} catch (Exception ex) {
						Log.info("Question is not available / changed" + ex.getMessage().toString());
					}
				}

				else if (Sheet1.getRow(i).getCell(1).getStringCellValue().toString().equals("tab")) {
					try {
						String Labeltext = "(//label[contains(text(),\"" + Question + "\")])[" + instance + "]";
						String TextboxAns = "(//label[contains(text(),\"" + Question + "\")])[" + instance
								+ "]//following-sibling::input";

						WebElement AssessmentText = wait
								.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Labeltext)));
						// code change for text with multiple space while
						// gettext() or getattribute automatically converts it.
						Question1 = Question.replaceAll(" +", "");
						AssessmentUItext = AssessmentText.getAttribute("innerText").toString();
						AssessmentUItext = AssessmentUItext.replaceAll(" +", "");
						Assert.assertEquals(Question1, AssessmentUItext);
						Log.info("Verify " + AssessmentText.getAttribute("innerText") + " label");

						WebElement AssessmentTextAns = wait
								.until(ExpectedConditions.elementToBeClickable(By.xpath(TextboxAns)));
						AssessmentTextAns.sendKeys(Keys.TAB);

						Log.info("Entered Tab");
					} catch (Exception ex) {
						Log.info("Question is not available / changed" + ex.getMessage().toString());
					}

				}

				// Added for Immunization
				else if (Sheet1.getRow(i).getCell(1).getStringCellValue().toString().equals("checkself")
						&& Answer.length() > 0) {
					try {
						String Legendtext = "(//*[contains(text(),\"" + Question + "\")])[" + instance + "]";
						WebElement Questiononexpath = (new WebDriverWait(driver, 30))
								.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Legendtext)));
						// code change for text with multiple space while
						// gettext() or getattribute automatically converts it.
						Question1 = Question.replaceAll(" +", "");
						AssessmentUItext = Questiononexpath.getAttribute("innerText").toString();
						AssessmentUItext = AssessmentUItext.replaceAll(" +", "");
						Assert.assertEquals(Question1, AssessmentUItext);
						Log.info("Verify " + Questiononexpath.getAttribute("innerText").toString());

						String[] Option = Answer.split(",");
						for (String w : Option) {
							String CheckOption = "(//*[contains(text(),\"" + Question + "\")])[" + instance
									+ "]//ancestor::label";
							Thread.sleep(1000);
							WebElement AnsoneXpath = wait
									.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CheckOption)));
							AnsoneXpath.click();

						}
						Log.info("Clicked on option for " + Question1.toString());
					} catch (Exception ex) {
						Log.info("Question is not available / changed" + ex.getMessage().toString());
					}
				}

				// else
				// if(Sheet1.getRow(i).getCell(1).getStringCellValue().toString().equals("modal"))
				// {
				// try {
				//
				// WebElement Assessmentlink =
				// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()
				// ='"+Question+"']")));
				// Log.info("Clicked on
				// "+Assessmentlink.getAttribute("innerText").toString());
				// Thread.sleep(3000);
				// Assessmentlink.click();
				// Log.info("Clicked on modal link");
				//
				// }
				// catch (Exception ex)
				// {
				// Log.info("Question is not available /
				// changed"+ex.getMessage().toString());
				// }
				// }
				else if (Sheet1.getRow(i).getCell(1).getStringCellValue().toString().equals("check_span")
						&& Answer.length() > 0) {
					try {
						String Legendtext = "(//label/span[contains(text(),\"" + Question + "\")])[" + instance + "]";
						WebElement Questiononexpath = (new WebDriverWait(driver, 30))
								.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Legendtext)));
						// code change for text with multiple space while
						// gettext() or getattribute automatically converts it.
						Question1 = Question.replaceAll(" +", "");
						AssessmentUItext = Questiononexpath.getAttribute("innerText").toString();
						AssessmentUItext = AssessmentUItext.replaceAll(" +", "");
						Assert.assertEquals(Question1, AssessmentUItext);
						Log.info("Verify " + Questiononexpath.getAttribute("innerText").toString());

						// String[] Option = Answer.split(",");
						// for (String w : Option) {
						// String CheckOption = "(//legend[contains(text(),\"" +
						// Question + "\")])[" + instance
						// + "]//parent::fieldset//label[contains(text(),'" + w
						// + "')]";
						Thread.sleep(1000);
						WebElement AnsoneXpath = wait
								.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Legendtext)));
						AnsoneXpath.click();

						Log.info("Clicked on option for " + Question1.toString());
					} catch (Exception ex) {
						Log.info("Question is not available / changed" + ex.getMessage().toString());
					}
				} else if (Sheet1.getRow(i).getCell(1).getStringCellValue().toString().equals("modalgridsearch")) {
					try {

						String VerifyText = "(//span[contains(text(),'" + Question + "')])[" + instance + "]";

						WebElement AssessmentText = (new WebDriverWait(driver, 30))
								.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(VerifyText)));
						// code change for text with multiple space while
						// gettext() or getattribute automatically converts it.
						Question1 = Question.replaceAll(" +", "");
						AssessmentUItext = AssessmentText.getAttribute("innerText").toString();
						AssessmentUItext = AssessmentUItext.replaceAll(" +", "");
						Assert.assertEquals(Question1, AssessmentUItext);
						Log.info("Verify " + AssessmentText.getAttribute("innerText") + " label");
						String clickButtonFromGrid = "((//span[contains(text(),'" + Question + "')])[" + instance
								+ "]//ancestor::table//following-sibling::td//button)[1]";

						WebElement button = (new WebDriverWait(driver, 30))
								.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(clickButtonFromGrid)));
						button.click();
						driver.findElement(By.xpath("//a[contains(text(),'Select')]")).click();
						Log.info("Clicked on One medication/condition");

					} catch (Exception ex) {
						Log.info("Question is not available / changed" + ex.getMessage().toString());
					}
				} else if (Sheet1.getRow(i).getCell(1).getStringCellValue().toString().equals("xPathClick")
						&& Answer.length() > 0) {
					try {

						WebElement AssessmentTextAnswer = (new WebDriverWait(driver, 30))
								.until(ExpectedConditions.elementToBeClickable(By.xpath(Answer)));
						AssessmentTextAnswer.click();

						Log.info("Clicked on " + Question);
					} catch (Exception ex) {
						Log.info("Question is not available / changed" + ex.getMessage().toString());
					}
				} else if (Sheet1.getRow(i).getCell(1).getStringCellValue().toString()
						.equals("gridsearchforClinialcondition")) {
					try {

						String VerifyText = "(//span[contains(text(),'" + Question + "')])[" + instance + "]";

						WebElement AssessmentText = (new WebDriverWait(driver, 30))
								.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(VerifyText)));
						// code change for text with multiple space while
						// gettext() or getattribute automatically converts it.
						Question1 = Question.replaceAll(" +", "");
						AssessmentUItext = AssessmentText.getAttribute("innerText").toString();
						AssessmentUItext = AssessmentUItext.replaceAll(" +", "");
						Assert.assertEquals(Question1, AssessmentUItext);
						Log.info("Verify " + AssessmentText.getAttribute("innerText") + " label");
						String clickButtonFromGrid = "(//span[contains(text(),'" + Question + "')])[" + instance
								+ "]//ancestor::table//following-sibling::td//button";

						WebElement button = (new WebDriverWait(driver, 30))
								.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(clickButtonFromGrid)));
						button.click();
						driver.findElement(By.xpath("//a[contains(text(),'Select')]")).click();
						Log.info("Clicked on One medication/condition");

					} catch (Exception ex) {
						Log.info("Question is not available / changed" + ex.getMessage().toString());
					}
				}

				else if (Sheet1.getRow(i).getCell(1).getStringCellValue().toString().equals("pocgridheader")) {
					try {
						Log.info("Poc Grid Header");

						String VerifyText = "(//div/p-header)[" + instance + "]";
						WebElement AssessmentText = (new WebDriverWait(driver, 30))
								.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(VerifyText)));
						// code change for text with multiple space while
						// gettext() or getattribute automatically converts it.
						Question1 = Question.replaceAll(" +", "");
						AssessmentUItext = AssessmentText.getAttribute("innerText").toString();
						AssessmentUItext = AssessmentUItext.replaceAll(" +", "");
						Assert.assertEquals(Question1, AssessmentUItext);
						Log.info("Verify " + AssessmentText.getAttribute("innerText") + " label");
						Log.info(Answer);

					} catch (Exception ex) {
						Log.info("Question is not available / changed" + ex.getMessage().toString());
					}
				} else if (Sheet1.getRow(i).getCell(1).getStringCellValue().toString().equals("pocgridcount")) {
					try {
						// Log.info("Poc Grid Count");
						Thread.sleep(3000);
						String VerifyText = "(//div/p-header)[" + instance + "]";
						WebElement AssessmentText = (new WebDriverWait(driver, 30))
								.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(VerifyText)));
						// code change for text with multiple space while
						// gettext() or getattribute automatically converts it.
						Question1 = Question.replaceAll(" +", "");
						AssessmentUItext = AssessmentText.getAttribute("innerText").toString();
						AssessmentUItext = AssessmentUItext.replaceAll(" +", "");
						Assert.assertEquals(Question1, AssessmentUItext);

						String getGridCount = driver.findElement(By.xpath("//div/p-header//caption[contains(text(),'" + Question
								+ "')]//parent::p-header//following-sibling::div")).getText();
						Thread.sleep(1000);

						// div/p-header[contains(text(),'Allergies')]//parent::div//following-sibling::div
						Log.info("Verify gridcount for " + AssessmentText.getAttribute("innerText"));
						Log.info(Answer);
						Log.info(getGridCount);

					} catch (Exception ex) {
						Log.info("Question is not available / changed" + ex.getMessage().toString());
					}
				} else if (Sheet1.getRow(i).getCell(1).getStringCellValue().toString().equals("pocverifygrid")) {
					try {
						
						Log.info("scenario - pocverifygrid");
						String[] verifyItems = Sheet1.getRow(i).getCell(0).getStringCellValue().toString().split("\\|");
						ArrayList<String> gridHeaders = new ArrayList<String>();
						String gridName = "";
						Log.info("Verifying: " + verifyItems[0]);
						if (verifyItems[0].equals("ManagementReasons")) {
							gridName = "ManagementReasonsGrid";
							for (int n = 0; n < GlobalValues.ManagementReasonsGrid.size(); n++) {
								gridHeaders.add(n, GlobalValues.ManagementReasonsGrid.get(n));
								Log.info("Adding the columns into loop to verify Management Reasons Grid  -"
										+ GlobalValues.ManagementReasonsGrid.get(n));
							}
						} else if (verifyItems[0].equals("ImmunizationGrid")) {
							gridName = "ImmunizationGrid";
							for (int n = 0; n < GlobalValues.ImmunizationGrid.size(); n++) {
								gridHeaders.add(n, GlobalValues.ImmunizationGrid.get(n));
								Log.info("Adding the columns into loop to verify Activity Grid  -"
										+ GlobalValues.ImmunizationGrid.get(n));

							}
						} else if (verifyItems[0].equals("ImmunizationUpdateGrid")) {
							gridName = "ImmunizationUpdateGrid";
							for (int n = 0; n < GlobalValues.ImmunizationUpdateGrid.size(); n++) {
								gridHeaders.add(n, GlobalValues.ImmunizationUpdateGrid.get(n));
								Log.info("Adding the columns into loop to verify Activity Grid  -"
										+ GlobalValues.ImmunizationUpdateGrid.get(n));

							}
						} else if (verifyItems[0].equals("Notegrid")) {
							gridName = "Notegrid";
							for (int n = 0; n < GlobalValues.Notegrid.size(); n++) {
								gridHeaders.add(n, GlobalValues.Notegrid.get(n));
								Log.info("Adding the columns into loop to verify Activity Grid  -"
										+ GlobalValues.Notegrid.get(n));

							}
						}

						if (verifyItems.length == 3) {
							ArrayList<String> answers = new ArrayList<String>();
							int ansStart = Integer.parseInt(verifyItems[1]);
							int ansEnd = Integer.parseInt(verifyItems[2]);
							for (int k = ansStart - 1, l = 0; k < ansEnd; k++, l++) {
								String gridAnswer = "";
								if (Sheet1.getRow(k).getCell(2) != null)
									gridAnswer = Sheet1.getRow(k).getCell(2).getStringCellValue().toString();
								answers.add(l, gridAnswer);
								Log.info(gridAnswer);
							}
							ArrayList<String> gridHeadersWithAnswers = new ArrayList<String>();
							for (int m = 0; m < gridHeaders.size(); m++) {
								gridHeadersWithAnswers.add(m, gridHeaders.get(m) + "|" + answers.get(m));
							}
							gm.verifyGrid(gridHeadersWithAnswers, true, gridName);

						} else {
							gm.verifyGrid(gridHeaders, true, gridName);
							Log.info(Answer);
						}

					} catch (Exception ex) {
						Log.info("Question is not available / changed" + ex.getMessage().toString());
					}
				}

				else if (Sheet1.getRow(i).getCell(1).getStringCellValue().toString().equals("servicesexpandverify")) {
					try {
						driver.findElement(By.xpath("//p-table/div/div/table/tbody/tr[1]/td[1]/a/i")).click();
						Log.info("Services Grid Expanded");
						// gm.verifyGrid(GlobalValues.AddAuthorization, true,
						// "AddAuthorization");

					} catch (Exception ex) {
						Log.info("Question is not available / changed" + ex.getMessage().toString());
					}
				} else {
					Log.info("Please Include Some Type in your Excel Sheet");
				}
			}

			Log.info(Sheet1.getRow(0).getCell(j).getStringCellValue().toString() + " for " + sheetName + " is passed");
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

	public void signature() {

		int x = 10;
		int y = 10;
		WebElement signatureWebElement = driver.findElement(By.id("memberSignatureData"));
		Actions builder = new Actions(driver);
		Action drawAction = builder.moveToElement(signatureWebElement, x, y).clickAndHold().moveByOffset(15, 12)
				.moveByOffset(30, 20).release().build();
		drawAction.perform();
		Log.info("Entered signature for memberSignatureData");
		driver.findElement(By.xpath("(//div/button)[1]")).click();

		signatureWebElement = driver.findElement(By.id("userSignatureData"));
		drawAction = builder.moveToElement(signatureWebElement, x, y).clickAndHold().moveByOffset(15, 12)
				.moveByOffset(30, 20).release().build();
		drawAction.perform();
		driver.findElement(By.xpath("(//div/button)[2]")).click();

	}
}
