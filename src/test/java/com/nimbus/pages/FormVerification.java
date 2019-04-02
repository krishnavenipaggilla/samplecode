package com.nimbus.pages;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

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

public class FormVerification {
	ConfigReader config = new ConfigReader();

	WebDriver driver;
	Logger Log;

	public FormVerification(WebDriver ldriver) {
		this.driver = ldriver;
	}

	public FormVerification(Logger lLog) {
		this.Log = lLog;
	}

	public void verifyFromExcel(String fileName, String sheetName, int col) throws Exception {

		Global_Method gm = new Global_Method(driver);

		File sour = new File("./TestData/" + fileName + ".xlsx");
		FileInputStream fiss = new FileInputStream(sour);
		XSSFWorkbook wb = new XSSFWorkbook(fiss);
		XSSFSheet Sheet1 = wb.getSheet(sheetName);

		Log = Logger.getLogger("AnnualAssessment.class");
		PropertyConfigurator.configure("log4j.properties");

		WebDriverWait wait = new WebDriverWait(driver, 15);

		int row = Sheet1.getLastRowNum();
		int j = col;

		Log.info("Verification is "+ Sheet1.getRow(0).getCell(j).getStringCellValue().toString() + " for " + sheetName);

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

				Cell cellData = Sheet1.getRow(i).getCell(j);
				if (cellData == null)
					continue;
				cellData.setCellType(Cell.CELL_TYPE_STRING);
				String Answer = cellData.toString();

				if (StringUtils.isEmpty(Answer))
					continue;
				
				try{
					String Labeltext = "(//label[contains(text(),\"" + Question + "\")])[" + instance + "]";
					String TextboxAns = "(//label[contains(text(),\"" + Question + "\")])[" + instance
							+ "]//following-sibling::*[1]";
					WebElement AssessmentText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Labeltext)));
					WebElement answerText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TextboxAns)));
					Question1 = Question.replaceAll(" +", "");
					AssessmentUItext = AssessmentText.getAttribute("innerText").toString().replaceAll(" +", "");
					String actualAnswer = answerText.getAttribute("innerText").toString().replaceAll(" +", "").replaceAll("\u00A0","").trim();
					Answer = Answer.replaceAll(" ", "");
					System.out.println("Expected answer is : "+Answer);
					System.out.println("Actual answer is : "+actualAnswer);
					/*for(int a = 0;a<actualAnswer.length();a++){
						char ch = actualAnswer.charAt(a);
						System.out.println(ch+":"+(int)ch);
					}
					char ch = ' ';
					System.out.println(ch+":"+(int)ch);*/
					if(Question1.trim().equals(AssessmentUItext.trim())){
						Assert.assertEquals(Answer.trim(), actualAnswer.trim());
						Log.info("Verifyed " + AssessmentText.getAttribute("innerText") + " label");
					}
					
					
				}catch(Exception e){
					e.printStackTrace();
				}

			}
	}
		
}
