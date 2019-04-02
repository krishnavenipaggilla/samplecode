package com.nimbus.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
/*
 * Class Name : LogoutPage

 * Description:	LogoutPage of application
 */
public class LogoutPage {
	
	

	WebDriver driver;
	Logger Log;
	
	
	
	public LogoutPage(WebDriver ldriver){
		this.driver=ldriver;

	}
	
	public LogoutPage(Logger lLog){
		this.Log=lLog;
	}

	
	public void Logout() throws Exception{
		
		try
		{
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
		Thread.sleep(5000);
		}
		catch(Exception ex)
		{
			driver.quit();
		}
	}
	
	public void HardStop() throws Exception
	{
			driver.quit();
	}

}
