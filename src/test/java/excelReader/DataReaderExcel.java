/**
 * 
 */
package excelReader;

import java.io.File;
import java.io.FileInputStream;


import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * @author AF12066
 *
 */
public class DataReaderExcel {
	
	
	public static String Supervisor_User;
	public static String Supervisor_Pass;
	public static String CaseManager_User;
	public static String CaseManager_Pass;
	public static String CaseSpecialist_User;
	public static String CaseSpecialist_Pass;
	public static String EnrollmentSpecialist_User;
	public static String EnrollmentSpecialist_Pass;
	public static String ClientAdmin_User;
	public static String ClientAdmin_Pass;
	
	
	
	
	public DataReaderExcel() {
		
		
		try {
			File sour = new File("./TestData/Credentials.xlsx");
			FileInputStream fiss = new FileInputStream(sour);
			XSSFWorkbook wb = new XSSFWorkbook(fiss);
			XSSFSheet Sheet1 = wb.getSheetAt(0);
		    Supervisor_User=Sheet1.getRow(0).getCell(0).getStringCellValue();
		    Supervisor_Pass=Sheet1.getRow(0).getCell(1).getStringCellValue();
			
		    CaseManager_User=Sheet1.getRow(1).getCell(0).getStringCellValue();
		    CaseManager_Pass=Sheet1.getRow(1).getCell(1).getStringCellValue();
		    
		    CaseSpecialist_User=Sheet1.getRow(2).getCell(0).getStringCellValue();
		    CaseSpecialist_Pass=Sheet1.getRow(2).getCell(1).getStringCellValue();
		    
		    EnrollmentSpecialist_User=Sheet1.getRow(3).getCell(0).getStringCellValue();
		    EnrollmentSpecialist_Pass=Sheet1.getRow(3).getCell(1).getStringCellValue();
		
		    ClientAdmin_User = Sheet1.getRow(4).getCell(0).getStringCellValue();
		    ClientAdmin_Pass = Sheet1.getRow(4).getCell(0).getStringCellValue();
		} catch (Exception e) {
			
			System.out.println("Exception reading excel is"+e.getMessage());

		}
		
	}

}
