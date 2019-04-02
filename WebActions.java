package utility;

import java.util.ArrayList;
import java.util.List;



import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.nimbus.pages.GlobalValues;

public class WebActions {
	
	ConfigReader config = new ConfigReader();
	
	/* Author: Praveen Rego
	 * Validate if all the expected values are present in the Dropdown
	 * rowxpath - Create an xpath to math all the values in the dropdown
	 * Expectedvalues - Expected Values to be passed as List
	 * driver - Driver instance
	 */
	
    public boolean validateDropdown(String rowxpath, List<String> Expectedvalues, WebDriver driver){
    	List<WebElement> ListWE = new ArrayList<WebElement>();
    	List<String> ListStr = new ArrayList<String>();
    	ListWE = driver.findElements(By.xpath(rowxpath));
    	for(int i=1;i<=ListWE.size();i++){
    		ListStr.add(driver.findElement(By.xpath("("+ rowxpath +")[" +i+ "]" )).getText());
    	}
    	
    	if(ListStr.containsAll(Expectedvalues)){
    		
    		return true;
    	}
    	else{
		return false;
    	}
    }
    
    /* Author: Praveen Rego
	 * Wait till the loading circle dissappears
	 * waittime - wait time for the page to load
	 * driver - Driver instance
	 */
    
    public void waitforPageLoad(WebDriver driver, int waittime){
    	WebDriverWait wait = new WebDriverWait(driver, waittime);
    	
    	wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath(config.getmsgLoading()))));
    }
    
    /* Author: Praveen Rego
   	 * Validate if the object is present in the webpage 
   	 * strxpath - xpath of the field to be validated
   	 * driver - Driver instance
   	 */
    
    public boolean validateobjectexists(String strxpath, WebDriver driver) {	
    	
		if((driver.findElements(By.xpath(strxpath))).size() !=0){
			if(driver.findElement(By.xpath(strxpath)).isDisplayed()){
				return true;
			}
			else
				return false;
		}
		else
		{
			return false ;
		}
		
	} 
    
   /*Author: Praveen Rego
   	* Validate if a particular element is readonly
    * strxpath - Create a xpath for the element where there is attribute "disabled" 
    * For example: <input class="ng-tns-c10-91" readonly="" role="listbox" type="text" aria-label=" " disabled="">
    *  driver - Driver instance
    */
    
    public boolean validateobjectreadonly(String strxpath, WebDriver driver){   
    	WebElement readOnly = driver.findElement(By.xpath(strxpath));
    	
    	
    	if((readOnly.getAttribute("disabled")) != null){
    		return true;
    	}
    	else
    		return false;
    	
    
    }
    
    /*
     *  To check whether field is mandatory
     */
     public boolean validateobjectmandatory(String strxpath, WebDriver driver){   
       	WebElement requiredfield = driver.findElement(By.xpath(strxpath));
        	
    	    	if((requiredfield.getAttribute("class")).equalsIgnoreCase("required")){
    	    		return true;
    	    	}
    	    	else
    	    		return false;
   	    } 
}
