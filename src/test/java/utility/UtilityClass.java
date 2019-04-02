/**
 * 
 */
package utility;



import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.nimbus.pages.GlobalValues;
import com.nimbus.screenshotandReports.ScreenShotandReports;
import com.nimbus.testcase.TestCases;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


/**
 * @author AF12066
 *
 */


public class UtilityClass {


	public static ExtentReports reports;
	public static ExtentTest logger;

	public String reportFileName = "NimbusReport.html";

	
	public WebDriver driver;
	public Logger Log;


	
		@BeforeTest
		public void setupDriver() throws Exception{
			//The following code is to get rid of log4 error message.
			System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.Jdk14Logger");
			
			ConfigReader config = new ConfigReader();
			
			Log = Logger.getLogger(UtilityClass.class.getName());
			PropertyConfigurator.configure("log4j.properties");

			Reporter.log("======++++ Browser Session Started ++++======",true);

		

			String Env = System.getProperty("target.env"); 
			String Grid = System.getProperty("target.grid");

			 
			 
			if(Env == null && Grid == null)
			                     {
			                           System.setProperty("webdriver.chrome.driver", ".//Drivers//chromedriver.exe");
			                           
			                           ChromeOptions options2 = new ChromeOptions();
			                           options2.setExperimentalOption("useAutomationExtension", false); 
			                           driver = new ChromeDriver(options2);    //enable to run locally    
			                           GlobalValues.URL = "https://va33tlvnim303.wellpoint.com:8443/cmproduct/login";
			                           
			                           //Implementation Dev
//			                           GlobalValues.URL = "https://va33dlvnim300.wellpoint.com:10443/cm-hip/login#/h/view_CMLandingPage/cmLandingPage";
			                            //enable to run locally
			                            driver.get(config.getURL());
//				
//										System.setProperty("webdriver.gecko.driver",".//Drivers//geckodriver.exe");
//										DesiredCapabilities capabilities = DesiredCapabilities.firefox();
//										capabilities.setCapability("marionette", true);
//										driver = new FirefoxDriver(capabilities);
//										driver.get(config.getURL());
			                     }
			                     else if (Env != null && Grid != null)
			                     {
			                     
			                     DesiredCapabilities capability = DesiredCapabilities.chrome();
			                    ChromeOptions options2 = new ChromeOptions();
//			                    options2.setExperimentalOption("useAutomationExtension", false);                
			                    options2.addArguments("start-maximized"); // open Browser in maximized mode
			                    options2.addArguments("disable-infobars"); // disabling infobars
			                    options2.addArguments("--disable-extensions"); // disabling extensions
			                    options2.addArguments("--disable-gpu"); // applicable to windows os only
			                    options2.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
			                    options2.addArguments("--no-sandbox"); // Bypass OS security model
			                    
			                    capability.setCapability(ChromeOptions.CAPABILITY, options2);
			                    driver = new RemoteWebDriver(new URL(Grid),capability);
			                    GlobalValues.URL = Env;
			                    driver.get(Env);
			            }
			            
					driver.manage().window().maximize();
			   
					driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
	             
					
						
					



             reports = new ExtentReports("./" + reportFileName);
             //logger=reports.startTest("feature/sprint21");
             logger=reports.startTest(TestCases.class.getName());
             Reporter.log("======++++ Application Started ++++======",true);
			
		}

		
		@AfterTest
		public void closeApp(){
			
			reports.endTest(logger);
			reports.flush();
			
			driver.quit();
			Reporter.log("======++++ Brwoser Session Ended ++++======",true);
		}
		


	    

		
		//Take screenshot method.
		

		
		
		@AfterMethod
		public void captureScreenshot(ITestResult result) {
			
			if(ITestResult.FAILURE==result.getStatus()){
				
				String screenshot_path = 
					ScreenShotandReports.takeScreenshots(driver, result.getName());
				String image = logger.addScreenCapture(screenshot_path);

//                logger.log(LogStatus.FAIL,result.getMethod().getDescription(),image);

       

//				
			}
			else if (ITestResult.SKIP==result.getStatus()) {

//				logger.log(LogStatus.SKIP,result.getMethod().getDescription());
			}
			else
			{
//				logger.log(LogStatus.PASS,result.getMethod().getDescription());

			
			}

//			driver.quit();
			//driver.get("./Reports/NimbusReport.html");
		//-Taking screenshot of the steps.
			
			
		}
		


}