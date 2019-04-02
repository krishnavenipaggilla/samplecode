/**
 * 
 */
package com.nimbus.screenshotandReports;



import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;




/**
 * @author AF12066
 *
 */
public class ScreenShotandReports {
	
	
	WebDriver driver;
	static Logger Log;
	
	public ScreenShotandReports(WebDriver ldriver) {
		this.driver=ldriver;

	}
	
//	public ScreenShotandReports(Logger lLog){
//		this.Log=lLog;
//	}

	

	public static String takeScreenshots(WebDriver driver,String screenshotsName){
		
		Log = Logger.getLogger("ScreenShotandReports.class");
		PropertyConfigurator.configure("log4j.properties");
		
		try {
						
			TakesScreenshot ts=(TakesScreenshot)driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			String dest="./"+screenshotsName+".png";
			File destination=new File(dest);
			FileUtils.copyFile(source, destination);
			//System.out.println("Screenshot was taken FYI");
			Log.info("Screenshot was taken");
			
			return dest;
			
			
		} catch (Exception e) {
			
			System.out.println("Screenshot exception is "+e.getMessage());
			return e.getMessage();

		} 
		
		
	}


}
