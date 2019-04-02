package com.nimbus.pages;
	import java.util.List;
	import org.apache.log4j.Logger;
	import org.apache.log4j.PropertyConfigurator;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.Assert;

	import utility.ConfigReader;

	public class ProviderPage {
		ConfigReader config = new ConfigReader();

		WebDriver driver;
		Logger Log;

		public ProviderPage(WebDriver ldriver) {
			this.driver = ldriver;
		}

		public ProviderPage(Logger lLog) {
			this.Log = lLog;
		}

		public void verifyProviderFunctionalityFromCareTeamPage() throws Exception {
			Log = Logger.getLogger("ProviderPage.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			Global_Method gm = new Global_Method(driver);
			LeftNavigationLink lnk = new LeftNavigationLink(driver);
			boolean passed = true;

			
			  lnk.clickCareTeam(); 
			  try {
			  this.addIndividualProviderFromCareTeamPage();
			  Log.info("Added the Individual Provider from Care Team Page");
			  
			  } catch (Exception e) { 
				  e.printStackTrace(); 
				  passed = false; 
				  }
			 
			  try {
				  this.addOrganizationProviderFromCareTeamPage();
				  Log.info("Added the Organization Provider from Care Team Page");
				  
				  } catch (Exception e) { 
					  e.printStackTrace(); 
					  passed = false; 
					  }
			  
			  try { 
				  this.verifyAddedIndividualProviderFromCareTeamPage();
			  
				  Log.info("Verified the added Individual provider from care team page"); 
			  } catch (Exception e) { 
				  e.printStackTrace(); 
				  passed = false; 
				  }
			  
			  try { 
				  this.verifyAddedOrganizationProviderFromCareTeamPage();
			  
				  Log.info("Verified the added Organization provider from care team page"); 
			  } catch (Exception e) { 
				  e.printStackTrace(); 
				  passed = false; 
				  }
			  
			  try { 
				  this.editAndVerifyIndividualProviderFromCareTeamPage();
				  Log.info("Edit and Verify Individual provider from care team page"); 
			  } catch (Exception e) { 
				  e.printStackTrace(); 
				  passed = false; 
				  }
			  
			  try { 
				  this.editAndVerifyOrganizationProviderFromCareTeamPage();
				  Log.info("Edit and Verify Organization provider from care team page"); 
			  } catch (Exception e) { 
				  e.printStackTrace(); 
				  passed = false; 
				  }
			  
			  try {
					addAndVerifyDateFromGridForSingleProviderFromCareTeamPage();
					Log.info("Verified the single provider verify date columns");
				} catch (Exception e) {
					e.printStackTrace();
					passed = false;
				}

				try {
					addAndVerifyDateFromGridForMultipleProvidersFromCareTeamPage();
					Log.info("Verified the multiple provider verify date columns");
				} catch (Exception e) {
					e.printStackTrace();
					passed = false;
				}
				

				try {
					lnk.clickOverview();
					Log.info("Verify pagination for auth rep grid");
					numberOfColumnsInGridForpagination("Care Team", 25);

				} catch (Exception e) {
					e.printStackTrace();
					passed = false;
				}
				
				if (passed == true) {
					gm.Reports("NIM-1069", "Pass");
				} else {

					gm.Reports("NIM-1069", "Fail");
				}
			}
				
		

	
	public void verifyProviderFunctionalityFromInitialOutreachPage() throws Exception {
		Log = Logger.getLogger("ProviderPage.class");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
		Global_Method gm = new Global_Method(driver);
		LeftNavigationLink lnk = new LeftNavigationLink(driver);
		boolean passed = true;


		  try {
			  this.addIndividualProviderFromInitialOutreachPage();
			  Log.info("Added the Individual Provider from Initial Outreach Page");
			  
			  } catch (Exception e) { 
				  e.printStackTrace(); 
				  passed = false; 
				  }
			 
			  try {
				  this.addOrganizationProviderFromInitialOutreachPage();
				  Log.info("Added the Organization Provider from Initial Outreach Page");
				  
				  } catch (Exception e) { 
					  e.printStackTrace(); 
					  passed = false; 
					  }
			  
			  try { 
				  this.verifyAddedIndividualProviderFromInitialOutreachPage();
			  
				  Log.info("Verified the added Individual provider from Initial Outreach page"); 
			  } catch (Exception e) { 
				  e.printStackTrace(); 
				  passed = false; 
				  }
			  
			  try { 
				  this.verifyAddedOrganizationProviderFromInitialOutreachPage();
			  
				  Log.info("Verified the added Organization provider from Initial Outreach page"); 
			  } catch (Exception e) { 
				  e.printStackTrace(); 
				  passed = false; 
				  }
			  
			  try { 
				  this.editAndVerifyIndividualProviderFromInitialOutreachPage();
				  Log.info("Edit and Verify Individual provider from Initial Outreach page"); 
			  } catch (Exception e) { 
				  e.printStackTrace(); 
				  passed = false; 
				  }
			  
			  try { 
				  this.editAndVerifyOrganizationProviderFromInitialOutreachPage();
				  Log.info("Edit and Verify Organization provider from Initial Outreach page"); 
			  } catch (Exception e) { 
				  e.printStackTrace(); 
				  passed = false; 
				  }
			  
			  try {
					addAndVerifyDateFromGridForSingleProviderFromInitialOutreachPage();
					Log.info("Verified the single provider verify date columns");
				} catch (Exception e) {
					e.printStackTrace();
					passed = false;
				}

				try {
					addAndVerifyDateFromGridForMultipleProvidersFromInitialOutreachPage();
					Log.info("Verified the multiple provider verify date columns");
				} catch (Exception e) {
					e.printStackTrace();
					passed = false;
				}
				

				try {
					lnk.clickOverview();
					Log.info("Verify pagination for auth rep grid");
					numberOfColumnsInGridForpagination("Care Team", 25);

				} catch (Exception e) {
					e.printStackTrace();
					passed = false;
				}
				
				if (passed == true) {
					gm.Reports("NIM-1069", "Pass");
				} else {

					gm.Reports("NIM-1069", "Fail");
				}
			}

		public void addIndividualProviderFromCareTeamPage() throws Exception {
			Log = Logger.getLogger("ProviderPage.class");
			PropertyConfigurator.configure("log4j.properties");
			AnnualAssessment a = new AnnualAssessment(driver);
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			Global_Method gm = new Global_Method(driver);
			LeftNavigationLink lnk = new LeftNavigationLink(driver);

			int AddRepGridCountBeforeAdd;
			int AddRepGridCountAfterAdd;

			try {
				Thread.sleep(GlobalValues.Sleep_wait_time);

				lnk.clickOverview();

				WebElement addAuthRepGridHeaderWebelement = wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(config.getAddAuthRepGridHeaderElementXpath())));
				String addAuthRepGridHeaderText = addAuthRepGridHeaderWebelement.getText();
				Assert.assertEquals(addAuthRepGridHeaderText, "Authorized Rep(s)", "Grid Header is not matching");
				AddRepGridCountBeforeAdd = gm.getNoOfRowsInGrid("Authorized Rep(s)");

				WebElement addAuthRepLinkElement = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getAddAuthRepLinkElementXpath())));
				addAuthRepLinkElement.click();

				// Verify Modal Window Header
				// WebElement modalHeader =
				// driver.findElement(By.xpath("(//div//span[@id='ui-dialog-9-label']/p-header/text())[1]"));
				// String modalHeaderText = modalHeader.getText();
				// Assert.assertEquals(modalHeaderText, "Add Authorized
				// Representative",
				// "Add Modal Window Header is not matching");

				// Fill in the Add Auth Rep form using Excel sheet
				a.annual_assessment("AnnualAssessment_CMDM_AuthRep", "AddAuthRepFromOverview", 2);
				Thread.sleep(GlobalValues.Sleep_wait_time);
				AddRepGridCountAfterAdd = gm.getNoOfRowsInGrid("Authorized Rep(s)");
				int gridCountAfterAdd = AddRepGridCountBeforeAdd + 1;
				Log.info("Grid count after adding records =  " + gridCountAfterAdd);
				Assert.assertEquals(AddRepGridCountAfterAdd, gridCountAfterAdd, "Grid Count mismatch ");
				
				Log.info("Successfully added Authorized Representative from Overview");
				Log.info("**********Added Authorized Representative from overview**********");

			} catch (Exception e) {

				Log.info(e + " -Couldn`t add a record in Auth Rep");
			}
			
		}
		
		public void addOrganizationProviderFromCareTeamPage() throws Exception {
			Log = Logger.getLogger("ProviderPage.class");
			PropertyConfigurator.configure("log4j.properties");
			AnnualAssessment a = new AnnualAssessment(driver);
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			Global_Method gm = new Global_Method(driver);
			LeftNavigationLink lnk = new LeftNavigationLink(driver);

			int AddRepGridCountBeforeAdd;
			int AddRepGridCountAfterAdd;

			try {
				Thread.sleep(GlobalValues.Sleep_wait_time);

				lnk.clickOverview();

				WebElement addAuthRepGridHeaderWebelement = wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(config.getAddAuthRepGridHeaderElementXpath())));
				String addAuthRepGridHeaderText = addAuthRepGridHeaderWebelement.getText();
				Assert.assertEquals(addAuthRepGridHeaderText, "Authorized Rep(s)", "Grid Header is not matching");
				AddRepGridCountBeforeAdd = gm.getNoOfRowsInGrid("Authorized Rep(s)");

				WebElement addAuthRepLinkElement = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getAddAuthRepLinkElementXpath())));
				addAuthRepLinkElement.click();

				// Verify Modal Window Header
				// WebElement modalHeader =
				// driver.findElement(By.xpath("(//div//span[@id='ui-dialog-9-label']/p-header/text())[1]"));
				// String modalHeaderText = modalHeader.getText();
				// Assert.assertEquals(modalHeaderText, "Add Authorized
				// Representative",
				// "Add Modal Window Header is not matching");

				// Fill in the Add Auth Rep form using Excel sheet
				a.annual_assessment("AnnualAssessment_CMDM_AuthRep", "AddAuthRepFromOverview", 2);
				Thread.sleep(GlobalValues.Sleep_wait_time);
				AddRepGridCountAfterAdd = gm.getNoOfRowsInGrid("Authorized Rep(s)");
				int gridCountAfterAdd = AddRepGridCountBeforeAdd + 1;
				Log.info("Grid count after adding records =  " + gridCountAfterAdd);
				Assert.assertEquals(AddRepGridCountAfterAdd, gridCountAfterAdd, "Grid Count mismatch ");
				
				Log.info("Successfully added Authorized Representative from Overview");
				Log.info("**********Added Authorized Representative from overview**********");

			} catch (Exception e) {

				Log.info(e + " -Couldn`t add a record in Auth Rep");
			}
			
		}

		public void verifyAddedIndividualProviderFromCareTeamPage() throws Exception {
			Log = Logger.getLogger("ProviderPage.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			Global_Method gm = new Global_Method(driver);
			LeftNavigationLink lnk = new LeftNavigationLink(driver);
			String verifiedDateSystem;

			try {
				List<WebElement> PaginationLastPageElement = driver
						.findElements(By.xpath(config.getPaginationLastPageElementXpath()));

				if (!(PaginationLastPageElement.isEmpty())) {
					PaginationLastPageElement.get(0).click();
					Thread.sleep(GlobalValues.Sleep_wait_time);
					List<WebElement> listOfRecordsInThePageElement = driver
							.findElements(By.xpath(config.getGridRowCountElementXpath()));
					int rowCountOnPage = listOfRecordsInThePageElement.size();
					Log.info("Count of rows in the page = " + rowCountOnPage);
					// driver.findElement(By.xpath("//table//tbody//tr[last()]"));

				}

				String name = wait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getNameXpathFromOverview())))
						.getText().trim();
				String relationshipToMember = wait
						.until(ExpectedConditions
								.presenceOfElementLocated(By.xpath(config.getRelationshipToMemberXpathFromOverview())))
						.getText().trim();
				String phone = wait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getphoneXpathFromOverview())))
						.getText().trim();

				String verifiedDate = wait
						.until(ExpectedConditions
								.presenceOfElementLocated(By.xpath(config.getverifiedDateXpathFromOverview())))
						.getText().trim();

				// click on expand on the grid for last record
				wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(config.getExpandCollapseLastRecordInGridElementXpath()))).click();
				Thread.sleep(GlobalValues.Sleep_wait_time);

				String source = wait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getSourceXpathFromOverview())))
						.getText().trim();
				String phoneType = wait
						.until(ExpectedConditions
								.presenceOfElementLocated(By.xpath(config.getPhoneTypeXpathFromOverview())))
						.getText().trim();

				String firstNameFromExcel = gm.readFromExcel("AnnualAssessment_CMDM_AuthRep", "AddAuthRepFromOverview", 2,
						"First Name");
				String lastNameFromExcel = gm.readFromExcel("AnnualAssessment_CMDM_AuthRep", "AddAuthRepFromOverview", 2,
						"Last Name");
				String nameFromExcel = (lastNameFromExcel + ", " + firstNameFromExcel);

				String relationshipToMemberFromExcel = gm.readFromExcel("AnnualAssessment_CMDM_AuthRep",
						"AddAuthRepFromOverview", 2, "Relationship to Member");
				String phoneTypeFromExcel = gm.readFromExcel("AnnualAssessment_CMDM_AuthRep", "AddAuthRepFromOverview", 2,
						"Phone Type|1");
				String phoneFromExcel = gm.readFromExcel("AnnualAssessment_CMDM_AuthRep", "AddAuthRepFromOverview", 2,
						"Phone|3");
				String sourceFromExcel = gm.readFromExcel("AnnualAssessment_CMDM_AuthRep", "AddAuthRepFromOverview", 2,
						"Source");
				String verifiedFromExcel = gm.readFromExcel("AnnualAssessment_CMDM_AuthRep", "AddAuthRepFromOverview", 2,
						"Verified|1");

				if (verifiedFromExcel.contains("click")) {
					verifiedDateSystem = GlobalValues.GetCurrentDateTime();
				} else {
					verifiedDateSystem = null;
				}

				Assert.assertEquals(name, nameFromExcel, "Added values are not matching between excel and as shown on UI");
				Assert.assertEquals(relationshipToMember, relationshipToMemberFromExcel,
						"Added values are not matching between excel and as shown on UI");
				Assert.assertEquals(phone, phoneFromExcel,
						"Added values are not matching between excel and as shown on UI");
				Assert.assertEquals(verifiedDate, verifiedDateSystem,
						"Added values are not matching between excel and as shown on UI");
				Assert.assertEquals(source, sourceFromExcel,
						"Added values are not matching between excel and as shown on UI");
				Assert.assertEquals(phoneType, phoneTypeFromExcel,
						"Added values are not matching between excel and as shown on UI");

				Log.info("Added authorized rep from overview has been verified");

				wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(config.getExpandCollapseLastRecordInGridElementXpath()))).click();
				Thread.sleep(GlobalValues.Sleep_wait_time);
				Log.info("Collapsed the expanded last row on overview page for Auth Rep grid");
				Log.info("Successfully verified Added Authorized Representative from overview");
				Log.info("**********Verified added Authorized Representative from overview**********");

			} catch (Exception e) {

				Log.info(e + " -Couldn`t add a record in Auth Rep");
			}

			
		}
		
		public void verifyAddedOrganizationProviderFromCareTeamPage() throws Exception {
			Log = Logger.getLogger("ProviderPage.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			Global_Method gm = new Global_Method(driver);
			LeftNavigationLink lnk = new LeftNavigationLink(driver);
			String verifiedDateSystem;

			try {
				List<WebElement> PaginationLastPageElement = driver
						.findElements(By.xpath(config.getPaginationLastPageElementXpath()));

				if (!(PaginationLastPageElement.isEmpty())) {
					PaginationLastPageElement.get(0).click();
					Thread.sleep(GlobalValues.Sleep_wait_time);
					List<WebElement> listOfRecordsInThePageElement = driver
							.findElements(By.xpath(config.getGridRowCountElementXpath()));
					int rowCountOnPage = listOfRecordsInThePageElement.size();
					Log.info("Count of rows in the page = " + rowCountOnPage);
					// driver.findElement(By.xpath("//table//tbody//tr[last()]"));

				}

				String name = wait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getNameXpathFromOverview())))
						.getText().trim();
				String relationshipToMember = wait
						.until(ExpectedConditions
								.presenceOfElementLocated(By.xpath(config.getRelationshipToMemberXpathFromOverview())))
						.getText().trim();
				String phone = wait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getphoneXpathFromOverview())))
						.getText().trim();

				String verifiedDate = wait
						.until(ExpectedConditions
								.presenceOfElementLocated(By.xpath(config.getverifiedDateXpathFromOverview())))
						.getText().trim();

				// click on expand on the grid for last record
				wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(config.getExpandCollapseLastRecordInGridElementXpath()))).click();
				Thread.sleep(GlobalValues.Sleep_wait_time);

				String source = wait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getSourceXpathFromOverview())))
						.getText().trim();
				String phoneType = wait
						.until(ExpectedConditions
								.presenceOfElementLocated(By.xpath(config.getPhoneTypeXpathFromOverview())))
						.getText().trim();

				String firstNameFromExcel = gm.readFromExcel("AnnualAssessment_CMDM_AuthRep", "AddAuthRepFromOverview", 2,
						"First Name");
				String lastNameFromExcel = gm.readFromExcel("AnnualAssessment_CMDM_AuthRep", "AddAuthRepFromOverview", 2,
						"Last Name");
				String nameFromExcel = (lastNameFromExcel + ", " + firstNameFromExcel);

				String relationshipToMemberFromExcel = gm.readFromExcel("AnnualAssessment_CMDM_AuthRep",
						"AddAuthRepFromOverview", 2, "Relationship to Member");
				String phoneTypeFromExcel = gm.readFromExcel("AnnualAssessment_CMDM_AuthRep", "AddAuthRepFromOverview", 2,
						"Phone Type|1");
				String phoneFromExcel = gm.readFromExcel("AnnualAssessment_CMDM_AuthRep", "AddAuthRepFromOverview", 2,
						"Phone|3");
				String sourceFromExcel = gm.readFromExcel("AnnualAssessment_CMDM_AuthRep", "AddAuthRepFromOverview", 2,
						"Source");
				String verifiedFromExcel = gm.readFromExcel("AnnualAssessment_CMDM_AuthRep", "AddAuthRepFromOverview", 2,
						"Verified|1");

				if (verifiedFromExcel.contains("click")) {
					verifiedDateSystem = GlobalValues.GetCurrentDateTime();
				} else {
					verifiedDateSystem = null;
				}

				Assert.assertEquals(name, nameFromExcel, "Added values are not matching between excel and as shown on UI");
				Assert.assertEquals(relationshipToMember, relationshipToMemberFromExcel,
						"Added values are not matching between excel and as shown on UI");
				Assert.assertEquals(phone, phoneFromExcel,
						"Added values are not matching between excel and as shown on UI");
				Assert.assertEquals(verifiedDate, verifiedDateSystem,
						"Added values are not matching between excel and as shown on UI");
				Assert.assertEquals(source, sourceFromExcel,
						"Added values are not matching between excel and as shown on UI");
				Assert.assertEquals(phoneType, phoneTypeFromExcel,
						"Added values are not matching between excel and as shown on UI");

				Log.info("Added authorized rep from overview has been verified");

				wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(config.getExpandCollapseLastRecordInGridElementXpath()))).click();
				Thread.sleep(GlobalValues.Sleep_wait_time);
				Log.info("Collapsed the expanded last row on overview page for Auth Rep grid");
				Log.info("Successfully verified Added Authorized Representative from overview");
				Log.info("**********Verified added Authorized Representative from overview**********");

			} catch (Exception e) {

				Log.info(e + " -Couldn`t add a record in Auth Rep");
			}

			
		}

		public void addIndividualProviderFromInitialOutreachPage() throws Exception {
			Log = Logger.getLogger("ProviderPage.class");
			PropertyConfigurator.configure("log4j.properties");
			AnnualAssessment a = new AnnualAssessment(driver);
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			Global_Method gm = new Global_Method(driver);
			LeftNavigationLink lnk = new LeftNavigationLink(driver);

			int rowCount;
			int rowCountAfterAdd;
			String verifiedDateSystem;
			try {

				lnk.clickMemberActionCenter();
				Thread.sleep(GlobalValues.Sleep_wait_time);
				WebElement initialOutreachEyeElement = wait.until(ExpectedConditions
						.elementToBeClickable(driver.findElement(By.xpath(config.getInitialOutreachEyeElementXpath()))));
				initialOutreachEyeElement.click();
				Thread.sleep(GlobalValues.Sleep_wait_time);

				WebElement initialOutreachBreadCrumbElement = wait.until(ExpectedConditions
						.visibilityOf(driver.findElement(By.xpath(config.getInitialOutreachBreadCrumbElementXpath()))));
				String initialOutreachBreadCrumbText = initialOutreachBreadCrumbElement.getText();
				Log.info("Breadcrumb text = " + initialOutreachBreadCrumbText);

				WebElement authRepAccordianElement = wait.until(ExpectedConditions
						.elementToBeClickable(driver.findElement(By.xpath(config.getaAuthRepAccordianElementXpath()))));
				authRepAccordianElement.click();

				WebElement addAuthRepGridHeaderElement = wait.until(ExpectedConditions
						.visibilityOf(driver.findElement(By.xpath(config.getAddAuthRepGridHeaderElementXpath()))));
				String addAuthRepGridHeaderText = addAuthRepGridHeaderElement.getText();
				Log.info(addAuthRepGridHeaderText);

				Assert.assertEquals(addAuthRepGridHeaderText, "Authorized Rep(s)", "AuthRepGrid Header is not matching");

				int AddRepGridCountBeforeAdd = gm.getNoOfRowsInGrid("Authorized Rep(s)");

				WebElement addAuthRepLinkElement = wait.until(ExpectedConditions
						.elementToBeClickable(driver.findElement(By.xpath(config.getAddAuthRepLinkElementXpath()))));
				addAuthRepLinkElement.click();

				Thread.sleep(GlobalValues.Sleep_wait_time);

				// Verify Modal Window Header
				WebElement modalHeaderElement = wait.until(
						ExpectedConditions.visibilityOf(driver.findElement(By.xpath(config.getModalHeaderElementXpath()))));
				String modalHeaderText = modalHeaderElement.getText();
				Log.info("Modal Window Header = " + modalHeaderText);

				Assert.assertEquals(modalHeaderText, "Add Authorized Representative",
						"Add Modal Window Header is not matching");

				// Fill in the Add Auth Rep form using Excel sheet
				a.annual_assessment("AnnualAssessment_CMDM_AuthRep", "AddAuthRepFromInitialOutreach", 2);
				Thread.sleep(GlobalValues.Sleep_wait_time);
				int AddRepGridCountAfterAdd = gm.getNoOfRowsInGrid("Authorized Rep(s)");

				int gridCountAfterAdd = AddRepGridCountBeforeAdd + 1;
				Assert.assertEquals(AddRepGridCountAfterAdd, gridCountAfterAdd, "Grid Count mismatch");

				Thread.sleep(GlobalValues.Sleep_wait_time);
				Log.info("Successfully added Authorized Representative from Initial Outreach");
				Log.info("**********Added Authorized Representative from Initial Outreach**********");
				
			} catch (Exception e) {

				Log.info(e + " -Couldn`t add a record in Auth Rep");
			}

			
		}

		public void addOrganizationProviderFromInitialOutreachPage() throws Exception {
			Log = Logger.getLogger("ProviderPage.class");
			PropertyConfigurator.configure("log4j.properties");
			AnnualAssessment a = new AnnualAssessment(driver);
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			Global_Method gm = new Global_Method(driver);
			LeftNavigationLink lnk = new LeftNavigationLink(driver);

			int rowCount;
			int rowCountAfterAdd;
			String verifiedDateSystem;
			try {

				lnk.clickMemberActionCenter();
				Thread.sleep(GlobalValues.Sleep_wait_time);
				WebElement initialOutreachEyeElement = wait.until(ExpectedConditions
						.elementToBeClickable(driver.findElement(By.xpath(config.getInitialOutreachEyeElementXpath()))));
				initialOutreachEyeElement.click();
				Thread.sleep(GlobalValues.Sleep_wait_time);

				WebElement initialOutreachBreadCrumbElement = wait.until(ExpectedConditions
						.visibilityOf(driver.findElement(By.xpath(config.getInitialOutreachBreadCrumbElementXpath()))));
				String initialOutreachBreadCrumbText = initialOutreachBreadCrumbElement.getText();
				Log.info("Breadcrumb text = " + initialOutreachBreadCrumbText);

				WebElement authRepAccordianElement = wait.until(ExpectedConditions
						.elementToBeClickable(driver.findElement(By.xpath(config.getaAuthRepAccordianElementXpath()))));
				authRepAccordianElement.click();

				WebElement addAuthRepGridHeaderElement = wait.until(ExpectedConditions
						.visibilityOf(driver.findElement(By.xpath(config.getAddAuthRepGridHeaderElementXpath()))));
				String addAuthRepGridHeaderText = addAuthRepGridHeaderElement.getText();
				Log.info(addAuthRepGridHeaderText);

				Assert.assertEquals(addAuthRepGridHeaderText, "Authorized Rep(s)", "AuthRepGrid Header is not matching");

				int AddRepGridCountBeforeAdd = gm.getNoOfRowsInGrid("Authorized Rep(s)");

				WebElement addAuthRepLinkElement = wait.until(ExpectedConditions
						.elementToBeClickable(driver.findElement(By.xpath(config.getAddAuthRepLinkElementXpath()))));
				addAuthRepLinkElement.click();

				Thread.sleep(GlobalValues.Sleep_wait_time);

				// Verify Modal Window Header
				WebElement modalHeaderElement = wait.until(
						ExpectedConditions.visibilityOf(driver.findElement(By.xpath(config.getModalHeaderElementXpath()))));
				String modalHeaderText = modalHeaderElement.getText();
				Log.info("Modal Window Header = " + modalHeaderText);

				Assert.assertEquals(modalHeaderText, "Add Authorized Representative",
						"Add Modal Window Header is not matching");

				// Fill in the Add Auth Rep form using Excel sheet
				a.annual_assessment("AnnualAssessment_CMDM_AuthRep", "AddAuthRepFromInitialOutreach", 2);
				Thread.sleep(GlobalValues.Sleep_wait_time);
				int AddRepGridCountAfterAdd = gm.getNoOfRowsInGrid("Authorized Rep(s)");

				int gridCountAfterAdd = AddRepGridCountBeforeAdd + 1;
				Assert.assertEquals(AddRepGridCountAfterAdd, gridCountAfterAdd, "Grid Count mismatch");

				Thread.sleep(GlobalValues.Sleep_wait_time);
				Log.info("Successfully added Authorized Representative from Initial Outreach");
				Log.info("**********Added Authorized Representative from Initial Outreach**********");
				
			} catch (Exception e) {

				Log.info(e + " -Couldn`t add a record in Auth Rep");
			}

			
		}

		public void verifyAddedIndividualProviderFromInitialOutreachPage() throws Exception {
			Log = Logger.getLogger("ProviderPage.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			Global_Method gm = new Global_Method(driver);

			String verifiedDateSystem;

			// now verify the added record on the grid
			try {
				List<WebElement> paginationForwardArrowElement = driver
						.findElements(By.xpath(config.getPaginationLastPageElementXpath()));

				if (!(paginationForwardArrowElement.isEmpty())) {
					paginationForwardArrowElement.get(0).click();
					Thread.sleep(GlobalValues.Sleep_wait_time);
					List<WebElement> listOfRecordsInThePageElement = driver
							.findElements(By.xpath(config.getGridRowCountElementXpath()));
					int rowCountOnPage = listOfRecordsInThePageElement.size();
					Log.info("Count of rows in the page = " + rowCountOnPage);
					// return rowCountOnPage;
					// driver.findElement(By.xpath("//table//tbody//tr[last()]"));

				}
				String firstName = wait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getFirstNameXpath()))).getText();

				String lastName = wait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getLastNameXpath()))).getText();

				String relationshipToMember = wait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getRelationshipToMemberXpath())))
						.getText();
				String phone = driver.findElement(By.xpath(config.getPhoneXpath())).getText();

				String verifiedDate = wait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getVerifiedDateXpath())))
						.getText();
				String source = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getSourceXpath())))
						.getText();
				String phoneType = wait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getPhoneTypeXpath()))).getText();

				String firstNameFromExcel = gm.readFromExcel("AnnualAssessment_CMDM_AuthRep",
						"AddAuthRepFromInitialOutreach", 2, "First Name");
				String lastNameFromExcel = gm.readFromExcel("AnnualAssessment_CMDM_AuthRep",
						"AddAuthRepFromInitialOutreach", 2, "Last Name");

				String relationshipToMemberFromExcel = gm.readFromExcel("AnnualAssessment_CMDM_AuthRep",
						"AddAuthRepFromInitialOutreach", 2, "Relationship to Member");
				String phoneTypeFromExcel = gm.readFromExcel("AnnualAssessment_CMDM_AuthRep",
						"AddAuthRepFromInitialOutreach", 2, "Phone Type|1");
				String phoneFromExcel = gm.readFromExcel("AnnualAssessment_CMDM_AuthRep", "AddAuthRepFromInitialOutreach",
						2, "Phone|4");
				String sourceFromExcel = gm.readFromExcel("AnnualAssessment_CMDM_AuthRep", "AddAuthRepFromInitialOutreach",
						2, "Source");
				String verifiedFromExcel = gm.readFromExcel("AnnualAssessment_CMDM_AuthRep",
						"AddAuthRepFromInitialOutreach", 2, "Verified|1");

				if (verifiedFromExcel.contains("click")) {
					verifiedDateSystem = GlobalValues.GetCurrentDateTime();
				} else {
					verifiedDateSystem = null;
				}

				Assert.assertEquals(firstName, firstNameFromExcel,
						"Added values are not matching between excel and as shown on UI");
				Assert.assertEquals(lastName, lastNameFromExcel,
						"Added values are not matching between excel and as shown on UI");
				Assert.assertEquals(relationshipToMember, relationshipToMemberFromExcel,
						"Added values are not matching between excel and as shown on UI");
				Assert.assertEquals(phone, phoneFromExcel,
						"Added values are not matching between excel and as shown on UI");
				Assert.assertEquals(verifiedDate, verifiedDateSystem,
						"Added values are not matching between excel and as shown on UI");
				Assert.assertEquals(source, sourceFromExcel,
						"Added values are not matching between excel and as shown on UI");
				Assert.assertEquals(phoneType, phoneTypeFromExcel,
						"Added values are not matching between excel and as shown on UI");

				Log.info("Successfully verified the added auth rep from Initial Outreach");
				Log.info("**********Verified Authorized Representative from Initial Outreach Page**********");

			} catch (Exception e) {

				Log.info(e + " -Couldn`t verify added record in Auth Rep");
			}

			
		}
		
		public void verifyAddedOrganizationProviderFromInitialOutreachPage() throws Exception {
			Log = Logger.getLogger("ProviderPage.class");
			PropertyConfigurator.configure("log4j.properties");
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			Global_Method gm = new Global_Method(driver);

			String verifiedDateSystem;

			// now verify the added record on the grid
			try {
				List<WebElement> paginationForwardArrowElement = driver
						.findElements(By.xpath(config.getPaginationLastPageElementXpath()));

				if (!(paginationForwardArrowElement.isEmpty())) {
					paginationForwardArrowElement.get(0).click();
					Thread.sleep(GlobalValues.Sleep_wait_time);
					List<WebElement> listOfRecordsInThePageElement = driver
							.findElements(By.xpath(config.getGridRowCountElementXpath()));
					int rowCountOnPage = listOfRecordsInThePageElement.size();
					Log.info("Count of rows in the page = " + rowCountOnPage);
					// return rowCountOnPage;
					// driver.findElement(By.xpath("//table//tbody//tr[last()]"));

				}
				String firstName = wait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getFirstNameXpath()))).getText();

				String lastName = wait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getLastNameXpath()))).getText();

				String relationshipToMember = wait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getRelationshipToMemberXpath())))
						.getText();
				String phone = driver.findElement(By.xpath(config.getPhoneXpath())).getText();

				String verifiedDate = wait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getVerifiedDateXpath())))
						.getText();
				String source = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getSourceXpath())))
						.getText();
				String phoneType = wait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getPhoneTypeXpath()))).getText();

				String firstNameFromExcel = gm.readFromExcel("AnnualAssessment_CMDM_AuthRep",
						"AddAuthRepFromInitialOutreach", 2, "First Name");
				String lastNameFromExcel = gm.readFromExcel("AnnualAssessment_CMDM_AuthRep",
						"AddAuthRepFromInitialOutreach", 2, "Last Name");

				String relationshipToMemberFromExcel = gm.readFromExcel("AnnualAssessment_CMDM_AuthRep",
						"AddAuthRepFromInitialOutreach", 2, "Relationship to Member");
				String phoneTypeFromExcel = gm.readFromExcel("AnnualAssessment_CMDM_AuthRep",
						"AddAuthRepFromInitialOutreach", 2, "Phone Type|1");
				String phoneFromExcel = gm.readFromExcel("AnnualAssessment_CMDM_AuthRep", "AddAuthRepFromInitialOutreach",
						2, "Phone|4");
				String sourceFromExcel = gm.readFromExcel("AnnualAssessment_CMDM_AuthRep", "AddAuthRepFromInitialOutreach",
						2, "Source");
				String verifiedFromExcel = gm.readFromExcel("AnnualAssessment_CMDM_AuthRep",
						"AddAuthRepFromInitialOutreach", 2, "Verified|1");

				if (verifiedFromExcel.contains("click")) {
					verifiedDateSystem = GlobalValues.GetCurrentDateTime();
				} else {
					verifiedDateSystem = null;
				}

				Assert.assertEquals(firstName, firstNameFromExcel,
						"Added values are not matching between excel and as shown on UI");
				Assert.assertEquals(lastName, lastNameFromExcel,
						"Added values are not matching between excel and as shown on UI");
				Assert.assertEquals(relationshipToMember, relationshipToMemberFromExcel,
						"Added values are not matching between excel and as shown on UI");
				Assert.assertEquals(phone, phoneFromExcel,
						"Added values are not matching between excel and as shown on UI");
				Assert.assertEquals(verifiedDate, verifiedDateSystem,
						"Added values are not matching between excel and as shown on UI");
				Assert.assertEquals(source, sourceFromExcel,
						"Added values are not matching between excel and as shown on UI");
				Assert.assertEquals(phoneType, phoneTypeFromExcel,
						"Added values are not matching between excel and as shown on UI");

				Log.info("Successfully verified the added auth rep from Initial Outreach");
				Log.info("**********Verified Authorized Representative from Initial Outreach Page**********");

			} catch (Exception e) {

				Log.info(e + " -Couldn`t verify added record in Auth Rep");
			}

			
		}

		public void addAndVerifyDateFromGridForMultipleProvidersFromCareTeamPage() throws Exception {
			Log = Logger.getLogger("ProviderPage.class");
			PropertyConfigurator.configure("log4j.properties");
			AnnualAssessment a = new AnnualAssessment(driver);
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			Global_Method gm = new Global_Method(driver);
			LeftNavigationLink lnk = new LeftNavigationLink(driver);

			try {

				lnk.clickMemberActionCenter();
				Thread.sleep(GlobalValues.Sleep_wait_time);
				WebElement initialOutreachEyeElement = wait.until(ExpectedConditions
						.elementToBeClickable(driver.findElement(By.xpath(config.getInitialOutreachEyeElementXpath()))));
				initialOutreachEyeElement.click();
				Thread.sleep(GlobalValues.Sleep_wait_time);

				WebElement initialOutreachBreadCrumbElement = wait.until(ExpectedConditions
						.visibilityOf(driver.findElement(By.xpath(config.getInitialOutreachBreadCrumbElementXpath()))));
				String initialOutreachBreadCrumbText = initialOutreachBreadCrumbElement.getText();
				Log.info("Breadcrumb text = " + initialOutreachBreadCrumbText);

				WebElement authRepAccordianElement = wait.until(ExpectedConditions
						.elementToBeClickable(driver.findElement(By.xpath(config.getaAuthRepAccordianElementXpath()))));
				authRepAccordianElement.click();

				WebElement addAuthRepGridHeaderElement = wait.until(ExpectedConditions
						.visibilityOf(driver.findElement(By.xpath(config.getAddAuthRepGridHeaderElementXpath()))));
				String addAuthRepGridHeaderText = addAuthRepGridHeaderElement.getText();
				Log.info(addAuthRepGridHeaderText);

				Assert.assertEquals(addAuthRepGridHeaderText, "Authorized Rep(s)", "AuthRepGrid Header is not matching");

				// int AddRepGridCountBeforeAdd = gm.getNoOfRowsInGrid();
				for (int i = 0; i <= 3; i++) {

					WebElement addAuthRepLinkElement = wait.until(ExpectedConditions
							.elementToBeClickable(driver.findElement(By.xpath(config.getAddAuthRepLinkElementXpath()))));
					addAuthRepLinkElement.click();

					Thread.sleep(GlobalValues.Sleep_wait_time);

					// Verify Modal Window Header
					WebElement modalHeaderElement = wait.until(ExpectedConditions
							.visibilityOf(driver.findElement(By.xpath(config.getModalHeaderElementXpath()))));
					String modalHeaderText = modalHeaderElement.getText();
					Log.info("Modal Window Header = " + modalHeaderText);

					Assert.assertEquals(modalHeaderText, "Add Authorized Representative",
							"Add Modal Window Header is not matching");

					// Fill in the Add Auth Rep form using Excel sheet
					a.annual_assessment("AnnualAssessment_CMDM_AuthRep", "AddAuthRepFromInitialOutreach", 3 + i);
					Thread.sleep(GlobalValues.Sleep_wait_time);
				}

				List<WebElement> paginationForwardArrowElement = driver
						.findElements(By.xpath(config.getPaginationLastPageElementXpath()));

				if (!(paginationForwardArrowElement.isEmpty())) {
					paginationForwardArrowElement.get(0).click();
					Thread.sleep(GlobalValues.Sleep_wait_time);
					List<WebElement> listOfRecordsInThePageElement = driver
							.findElements(By.xpath(config.getGridRowCountElementXpath()));
					int rowCountOnPage = listOfRecordsInThePageElement.size();
					Log.info("Count of rows in the page = " + rowCountOnPage);
					Thread.sleep(GlobalValues.Sleep_wait_time);

				}
				List<WebElement> nonVerifiedRecordsInGridElement = driver
						.findElements(By.xpath(config.getNonVerifiedRecordsInGridElementXpath()));
				int nonVerifiedRecordsCount = nonVerifiedRecordsInGridElement.size();
				Log.info("Non verified records count for this page = " + nonVerifiedRecordsCount);
				Thread.sleep(GlobalValues.Sleep_wait_time);

				WebElement gridHeaderCheckBoxElement = wait.until(ExpectedConditions
						.elementToBeClickable(driver.findElement(By.xpath(config.getGridHeaderCheckBoxElementXpath()))));
				gridHeaderCheckBoxElement.click();
				Thread.sleep(GlobalValues.Sleep_wait_time);

				WebElement saveProgressBtnElement = wait.until(ExpectedConditions
						.elementToBeClickable(driver.findElement(By.xpath(config.getSaveProgressBtnElementXpath()))));
				saveProgressBtnElement.click();
				Thread.sleep(GlobalValues.Sleep_wait_time);

				List<WebElement> nonVerifiedRecordsInGridElement2 = driver
						.findElements(By.xpath(config.getNonVerifiedRecordsInGridElementXpath()));
				int nonVerifiedRecordsCountAfterVerification = nonVerifiedRecordsInGridElement2.size();
				Log.info("Non verified records count for this page = " + nonVerifiedRecordsCountAfterVerification);

				Assert.assertEquals(nonVerifiedRecordsCountAfterVerification, 0,
						"Record count after verifying multiple records is not matching");

				Log.info("Successfully verified date for Multiple auth rep records by clicking on grid header checkbox");
				Log.info("**********Verified date for Multiple auth rep records by clicking on grid header checkbox**********");
				
			} catch (Exception e) {

				Log.info(e + " - Couldn`t verify multiple records in Auth Rep");
				e.printStackTrace();
			}

			
		}
		
		
		public void addAndVerifyDateFromGridForMultipleProvidersFromInitialOutreachPage() throws Exception {
			Log = Logger.getLogger("ProviderPage.class");
			PropertyConfigurator.configure("log4j.properties");
			AnnualAssessment a = new AnnualAssessment(driver);
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			Global_Method gm = new Global_Method(driver);
			LeftNavigationLink lnk = new LeftNavigationLink(driver);

			try {

				lnk.clickMemberActionCenter();
				Thread.sleep(GlobalValues.Sleep_wait_time);
				WebElement initialOutreachEyeElement = wait.until(ExpectedConditions
						.elementToBeClickable(driver.findElement(By.xpath(config.getInitialOutreachEyeElementXpath()))));
				initialOutreachEyeElement.click();
				Thread.sleep(GlobalValues.Sleep_wait_time);

				WebElement initialOutreachBreadCrumbElement = wait.until(ExpectedConditions
						.visibilityOf(driver.findElement(By.xpath(config.getInitialOutreachBreadCrumbElementXpath()))));
				String initialOutreachBreadCrumbText = initialOutreachBreadCrumbElement.getText();
				Log.info("Breadcrumb text = " + initialOutreachBreadCrumbText);

				WebElement authRepAccordianElement = wait.until(ExpectedConditions
						.elementToBeClickable(driver.findElement(By.xpath(config.getaAuthRepAccordianElementXpath()))));
				authRepAccordianElement.click();

				WebElement addAuthRepGridHeaderElement = wait.until(ExpectedConditions
						.visibilityOf(driver.findElement(By.xpath(config.getAddAuthRepGridHeaderElementXpath()))));
				String addAuthRepGridHeaderText = addAuthRepGridHeaderElement.getText();
				Log.info(addAuthRepGridHeaderText);

				Assert.assertEquals(addAuthRepGridHeaderText, "Authorized Rep(s)", "AuthRepGrid Header is not matching");

				// int AddRepGridCountBeforeAdd = gm.getNoOfRowsInGrid();
				for (int i = 0; i <= 3; i++) {

					WebElement addAuthRepLinkElement = wait.until(ExpectedConditions
							.elementToBeClickable(driver.findElement(By.xpath(config.getAddAuthRepLinkElementXpath()))));
					addAuthRepLinkElement.click();

					Thread.sleep(GlobalValues.Sleep_wait_time);

					// Verify Modal Window Header
					WebElement modalHeaderElement = wait.until(ExpectedConditions
							.visibilityOf(driver.findElement(By.xpath(config.getModalHeaderElementXpath()))));
					String modalHeaderText = modalHeaderElement.getText();
					Log.info("Modal Window Header = " + modalHeaderText);

					Assert.assertEquals(modalHeaderText, "Add Authorized Representative",
							"Add Modal Window Header is not matching");

					// Fill in the Add Auth Rep form using Excel sheet
					a.annual_assessment("AnnualAssessment_CMDM_AuthRep", "AddAuthRepFromInitialOutreach", 3 + i);
					Thread.sleep(GlobalValues.Sleep_wait_time);
				}

				List<WebElement> paginationForwardArrowElement = driver
						.findElements(By.xpath(config.getPaginationLastPageElementXpath()));

				if (!(paginationForwardArrowElement.isEmpty())) {
					paginationForwardArrowElement.get(0).click();
					Thread.sleep(GlobalValues.Sleep_wait_time);
					List<WebElement> listOfRecordsInThePageElement = driver
							.findElements(By.xpath(config.getGridRowCountElementXpath()));
					int rowCountOnPage = listOfRecordsInThePageElement.size();
					Log.info("Count of rows in the page = " + rowCountOnPage);
					Thread.sleep(GlobalValues.Sleep_wait_time);

				}
				List<WebElement> nonVerifiedRecordsInGridElement = driver
						.findElements(By.xpath(config.getNonVerifiedRecordsInGridElementXpath()));
				int nonVerifiedRecordsCount = nonVerifiedRecordsInGridElement.size();
				Log.info("Non verified records count for this page = " + nonVerifiedRecordsCount);
				Thread.sleep(GlobalValues.Sleep_wait_time);

				WebElement gridHeaderCheckBoxElement = wait.until(ExpectedConditions
						.elementToBeClickable(driver.findElement(By.xpath(config.getGridHeaderCheckBoxElementXpath()))));
				gridHeaderCheckBoxElement.click();
				Thread.sleep(GlobalValues.Sleep_wait_time);

				WebElement saveProgressBtnElement = wait.until(ExpectedConditions
						.elementToBeClickable(driver.findElement(By.xpath(config.getSaveProgressBtnElementXpath()))));
				saveProgressBtnElement.click();
				Thread.sleep(GlobalValues.Sleep_wait_time);

				List<WebElement> nonVerifiedRecordsInGridElement2 = driver
						.findElements(By.xpath(config.getNonVerifiedRecordsInGridElementXpath()));
				int nonVerifiedRecordsCountAfterVerification = nonVerifiedRecordsInGridElement2.size();
				Log.info("Non verified records count for this page = " + nonVerifiedRecordsCountAfterVerification);

				Assert.assertEquals(nonVerifiedRecordsCountAfterVerification, 0,
						"Record count after verifying multiple records is not matching");

				Log.info("Successfully verified date for Multiple auth rep records by clicking on grid header checkbox");
				Log.info("**********Verified date for Multiple auth rep records by clicking on grid header checkbox**********");
				
			} catch (Exception e) {

				Log.info(e + " - Couldn`t verify multiple records in Auth Rep");
				e.printStackTrace();
			}

			
		}

		public void addAndVerifyDateFromGridForSingleProviderFromCareTeamPage() throws Exception {
			Log = Logger.getLogger("ProviderPage.class");
			PropertyConfigurator.configure("log4j.properties");
			AnnualAssessment a = new AnnualAssessment(driver);
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			Global_Method gm = new Global_Method(driver);
			LeftNavigationLink lnk = new LeftNavigationLink(driver);

			try {

				lnk.clickMemberActionCenter();
				Thread.sleep(GlobalValues.Sleep_wait_time);
				WebElement initialOutreachEyeElement = driver
						.findElement(By.xpath(config.getInitialOutreachEyeElementXpath()));
				initialOutreachEyeElement.click();
				Thread.sleep(GlobalValues.Sleep_wait_time);

				WebElement initialOutreachBreadCrumbElement = wait.until(ExpectedConditions
						.visibilityOf(driver.findElement(By.xpath(config.getInitialOutreachBreadCrumbElementXpath()))));
				String initialOutreachBreadCrumbText = initialOutreachBreadCrumbElement.getText();
				Log.info("Breadcrumb text = " + initialOutreachBreadCrumbText);

				WebElement authRepAccordianElement = wait.until(ExpectedConditions
						.elementToBeClickable(driver.findElement(By.xpath(config.getaAuthRepAccordianElementXpath()))));
				authRepAccordianElement.click();

				WebElement addAuthRepGridHeaderElement = wait.until(ExpectedConditions
						.visibilityOf(driver.findElement(By.xpath(config.getAddAuthRepGridHeaderElementXpath()))));
				String addAuthRepGridHeaderText = addAuthRepGridHeaderElement.getText();
				Log.info(addAuthRepGridHeaderText);

				Assert.assertEquals(addAuthRepGridHeaderText, "Authorized Rep(s)", "AuthRepGrid Header is not matching");

				// int AddRepGridCountBeforeAdd = gm.getNoOfRowsInGrid();
				for (int i = 0; i <= 3; i++) {

					WebElement addAuthRepLinkElement = wait.until(ExpectedConditions
							.elementToBeClickable(driver.findElement(By.xpath(config.getAddAuthRepLinkElementXpath()))));
					addAuthRepLinkElement.click();

					Thread.sleep(GlobalValues.Sleep_wait_time);

					// Verify Modal Window Header
					WebElement modalHeaderElement = wait.until(ExpectedConditions
							.visibilityOf(driver.findElement(By.xpath(config.getModalHeaderElementXpath()))));
					String modalHeaderText = modalHeaderElement.getText();
					Log.info("Modal Window Header = " + modalHeaderText);

					Assert.assertEquals(modalHeaderText, "Add Authorized Representative",
							"Add Modal Window Header is not matching");

					// Fill in the Add Auth Rep form using Excel sheet
					a.annual_assessment("AnnualAssessment_CMDM_AuthRep", "AddAuthRepFromInitialOutreach", 3 + i);
					Thread.sleep(GlobalValues.Sleep_wait_time);

				}

				List<WebElement> paginationForwardArrowElement = driver
						.findElements(By.xpath(config.getPaginationLastPageElementXpath()));

				if (!(paginationForwardArrowElement.isEmpty())) {
					paginationForwardArrowElement.get(0).click();
					Thread.sleep(GlobalValues.Sleep_wait_time);
					List<WebElement> listOfRecordsInThePageElement = driver
							.findElements(By.xpath(config.getGridRowCountElementXpath()));
					int rowCountOnPage = listOfRecordsInThePageElement.size();
					Log.info("Count of rows in the page = " + rowCountOnPage);
					Thread.sleep(GlobalValues.Sleep_wait_time);

				}
				List<WebElement> nonVerifiedRecordsInGridElement = driver
						.findElements(By.xpath(config.getNonVerifiedRecordsInGridElementXpath()));
				int nonVerifiedRecordsCount = nonVerifiedRecordsInGridElement.size();
				Assert.assertEquals(nonVerifiedRecordsCount > 0, true);

				List<WebElement> nonVerifiedElementClick = driver
						.findElements(By.xpath(config.getNonVerifiedRecordsInGridElementXpathClick()));
				nonVerifiedElementClick.get(0).click();

				Thread.sleep(GlobalValues.Sleep_wait_time);
				// table//tbody//tr//td[1]
				WebElement saveProgressBtnElement = wait.until(ExpectedConditions
						.visibilityOf(driver.findElement(By.xpath(config.getSaveProgressBtnElementXpath()))));
				saveProgressBtnElement.click();

				List<WebElement> paginationForwardArrowElement1 = driver
						.findElements(By.xpath(config.getPaginationLastPageElementXpath()));

				if (!(paginationForwardArrowElement1.isEmpty())) {
					paginationForwardArrowElement1.get(0).click();
					Thread.sleep(GlobalValues.Sleep_wait_time);
					List<WebElement> listOfRecordsInThePageElement = driver
							.findElements(By.xpath(config.getGridRowCountElementXpath()));
					int rowCountOnPage = listOfRecordsInThePageElement.size();
					Log.info("Count of rows in the page = " + rowCountOnPage);
					Thread.sleep(GlobalValues.Sleep_wait_time);

				}

				List<WebElement> nonVerifiedRecordsInGridElementAfter = driver
						.findElements(By.xpath(config.getNonVerifiedRecordsInGridElementXpath()));
				int nonVerifiedRecordsCountAfter = nonVerifiedRecordsInGridElementAfter.size();
				Assert.assertEquals(nonVerifiedRecordsCountAfter, nonVerifiedRecordsCount - 1,
						"Record verifed after single verifcation is not matching");

				Log.info("Successfully verified date for Single auth rep record by clicking on single checkbox ");
				Log.info("**********verified date for Single auth rep record by clicking on single checkbox********** ");

			} catch (Exception e) {

				e.printStackTrace();
				Log.info(e + " -Couldn`t verify single records in Auth  Rep");
			}

			
		}
		
		public void addAndVerifyDateFromGridForSingleProviderFromInitialOutreachPage() throws Exception {
			Log = Logger.getLogger("ProviderPage.class");
			PropertyConfigurator.configure("log4j.properties");
			AnnualAssessment a = new AnnualAssessment(driver);
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			Global_Method gm = new Global_Method(driver);
			LeftNavigationLink lnk = new LeftNavigationLink(driver);

			try {

				lnk.clickMemberActionCenter();
				Thread.sleep(GlobalValues.Sleep_wait_time);
				WebElement initialOutreachEyeElement = driver
						.findElement(By.xpath(config.getInitialOutreachEyeElementXpath()));
				initialOutreachEyeElement.click();
				Thread.sleep(GlobalValues.Sleep_wait_time);

				WebElement initialOutreachBreadCrumbElement = wait.until(ExpectedConditions
						.visibilityOf(driver.findElement(By.xpath(config.getInitialOutreachBreadCrumbElementXpath()))));
				String initialOutreachBreadCrumbText = initialOutreachBreadCrumbElement.getText();
				Log.info("Breadcrumb text = " + initialOutreachBreadCrumbText);

				WebElement authRepAccordianElement = wait.until(ExpectedConditions
						.elementToBeClickable(driver.findElement(By.xpath(config.getaAuthRepAccordianElementXpath()))));
				authRepAccordianElement.click();

				WebElement addAuthRepGridHeaderElement = wait.until(ExpectedConditions
						.visibilityOf(driver.findElement(By.xpath(config.getAddAuthRepGridHeaderElementXpath()))));
				String addAuthRepGridHeaderText = addAuthRepGridHeaderElement.getText();
				Log.info(addAuthRepGridHeaderText);

				Assert.assertEquals(addAuthRepGridHeaderText, "Authorized Rep(s)", "AuthRepGrid Header is not matching");

				// int AddRepGridCountBeforeAdd = gm.getNoOfRowsInGrid();
				for (int i = 0; i <= 3; i++) {

					WebElement addAuthRepLinkElement = wait.until(ExpectedConditions
							.elementToBeClickable(driver.findElement(By.xpath(config.getAddAuthRepLinkElementXpath()))));
					addAuthRepLinkElement.click();

					Thread.sleep(GlobalValues.Sleep_wait_time);

					// Verify Modal Window Header
					WebElement modalHeaderElement = wait.until(ExpectedConditions
							.visibilityOf(driver.findElement(By.xpath(config.getModalHeaderElementXpath()))));
					String modalHeaderText = modalHeaderElement.getText();
					Log.info("Modal Window Header = " + modalHeaderText);

					Assert.assertEquals(modalHeaderText, "Add Authorized Representative",
							"Add Modal Window Header is not matching");

					// Fill in the Add Auth Rep form using Excel sheet
					a.annual_assessment("AnnualAssessment_CMDM_AuthRep", "AddAuthRepFromInitialOutreach", 3 + i);
					Thread.sleep(GlobalValues.Sleep_wait_time);

				}

				List<WebElement> paginationForwardArrowElement = driver
						.findElements(By.xpath(config.getPaginationLastPageElementXpath()));

				if (!(paginationForwardArrowElement.isEmpty())) {
					paginationForwardArrowElement.get(0).click();
					Thread.sleep(GlobalValues.Sleep_wait_time);
					List<WebElement> listOfRecordsInThePageElement = driver
							.findElements(By.xpath(config.getGridRowCountElementXpath()));
					int rowCountOnPage = listOfRecordsInThePageElement.size();
					Log.info("Count of rows in the page = " + rowCountOnPage);
					Thread.sleep(GlobalValues.Sleep_wait_time);

				}
				List<WebElement> nonVerifiedRecordsInGridElement = driver
						.findElements(By.xpath(config.getNonVerifiedRecordsInGridElementXpath()));
				int nonVerifiedRecordsCount = nonVerifiedRecordsInGridElement.size();
				Assert.assertEquals(nonVerifiedRecordsCount > 0, true);

				List<WebElement> nonVerifiedElementClick = driver
						.findElements(By.xpath(config.getNonVerifiedRecordsInGridElementXpathClick()));
				nonVerifiedElementClick.get(0).click();

				Thread.sleep(GlobalValues.Sleep_wait_time);
				// table//tbody//tr//td[1]
				WebElement saveProgressBtnElement = wait.until(ExpectedConditions
						.visibilityOf(driver.findElement(By.xpath(config.getSaveProgressBtnElementXpath()))));
				saveProgressBtnElement.click();

				List<WebElement> paginationForwardArrowElement1 = driver
						.findElements(By.xpath(config.getPaginationLastPageElementXpath()));

				if (!(paginationForwardArrowElement1.isEmpty())) {
					paginationForwardArrowElement1.get(0).click();
					Thread.sleep(GlobalValues.Sleep_wait_time);
					List<WebElement> listOfRecordsInThePageElement = driver
							.findElements(By.xpath(config.getGridRowCountElementXpath()));
					int rowCountOnPage = listOfRecordsInThePageElement.size();
					Log.info("Count of rows in the page = " + rowCountOnPage);
					Thread.sleep(GlobalValues.Sleep_wait_time);

				}

				List<WebElement> nonVerifiedRecordsInGridElementAfter = driver
						.findElements(By.xpath(config.getNonVerifiedRecordsInGridElementXpath()));
				int nonVerifiedRecordsCountAfter = nonVerifiedRecordsInGridElementAfter.size();
				Assert.assertEquals(nonVerifiedRecordsCountAfter, nonVerifiedRecordsCount - 1,
						"Record verifed after single verifcation is not matching");

				Log.info("Successfully verified date for Single auth rep record by clicking on single checkbox ");
				Log.info("**********verified date for Single auth rep record by clicking on single checkbox********** ");

			} catch (Exception e) {

				e.printStackTrace();
				Log.info(e + " -Couldn`t verify single records in Auth  Rep");
			}

			
		}


		// sorting on column level in overview

		public void columnLevelSortingForAuthRepFromOverview() throws Exception {
			Log = Logger.getLogger("ProviderPage.class");
			PropertyConfigurator.configure("log4j.properties");
			DashboardFunctions df = new DashboardFunctions(driver);
			LeftNavigationLink lnk = new LeftNavigationLink(driver);
			try {
				lnk.clickOverview();
				WebElement gridTitleElement = driver.findElement(By.xpath(config.getAddAuthRepGridHeaderElementXpath()));
				String gridTitle = gridTitleElement.getText();
				Log.info(gridTitle);
				Assert.assertEquals(gridTitle, "Authorized Rep(s)", "Grid Header is not matching");

				// df.columnSortingOnGrid(driver, 0, 2, 2);
				df.columnSorting(driver);
				Log.info("Successfully verified column level sorting for Auth rep from overview ");
				Log.info("**********verified column level sorting for Auth rep from overview********** ");

			} catch (Exception e) {

				Log.info(e + " - sorting Authorized Rep(S) grid failed");
			}

			
		}
		// No of columns in grid for pagination

		public void numberOfColumnsInGridForpagination(String gridName, int requiredRowCount) throws Exception {
			Log = Logger.getLogger("ProviderPage.class");
			PropertyConfigurator.configure("log4j.properties");

			try {
				WebElement gridTitleElement = driver.findElement(By.xpath(config.getAddAuthRepGridHeaderElementXpath()));
				String gridTitle = gridTitleElement.getText();
				Log.info(gridTitle);
				Assert.assertEquals(gridTitle, gridName , "Grid Header/Title is not matching");
				List<WebElement> gridRowsCount = driver.findElements(By.xpath(config.getGridRowCountElementXpath()));
				int rowCount = gridRowsCount.size();

				if (rowCount > requiredRowCount) {
					Log.info("Number of rows for pagination is more than required");
				} else {
					Log.info("Number of rows for pagination is as required");
					Log.info("**********verified pagination for Auth rep**********");
				}

			} catch (Exception e) {

				Log.info(e + " Grid Title is not matching");
			}

			
		}

		// Default sort order - latest record on the top

		public void editAndVerifyIndividualProviderFromCareTeamPage() throws Exception {
			Log = Logger.getLogger("ProviderPage.class");
			PropertyConfigurator.configure("log4j.properties");
			AnnualAssessment a = new AnnualAssessment(driver);
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			Global_Method gm = new Global_Method(driver);
			LeftNavigationLink lnk = new LeftNavigationLink(driver);
			int rowCountBeforeEdit;
			int rowCountAfterEdit;
			String verifiedDateSystem;

			try {
				rowCountBeforeEdit = gm.getNoOfRowsInGrid("Authorized Rep(s)");
				Log.info("Row Count before edit = " + rowCountBeforeEdit);

				List<WebElement> paginationForwardArrowElement = driver
						.findElements(By.xpath("(//a//span[@class ='fa fa-step-forward'])[1]"));

				if (!(paginationForwardArrowElement.isEmpty())) {
					paginationForwardArrowElement.get(0).click();
					Thread.sleep(GlobalValues.Sleep_wait_time);
					List<WebElement> listOfRecordsInThePageElement = driver
							.findElements(By.xpath(config.getGridRowCountElementXpath()));
					int rowCountOnPage = listOfRecordsInThePageElement.size();
					Log.info("Count of rows in the page = " + rowCountOnPage);
					Thread.sleep(GlobalValues.Sleep_wait_time);

				}

				WebElement editIconForAuthRepOnOverViewElement = driver
						.findElement(By.xpath(config.getEditIconForAuthRepOnOverViewElementXpath()));
				editIconForAuthRepOnOverViewElement.click();
				Log.info("Clicked on the Edit Note+Pen icon on the last record for Auth rep on Member Overview");

				// Verify Modal Window Header
				WebElement modalHeaderElement = wait.until(
						ExpectedConditions.presenceOfElementLocated(By.xpath(config.getEditModalHeaderElementXpath())));
				String modalHeaderText = modalHeaderElement.getText();
				Log.info("Modal Window Header = " + modalHeaderText);
				// Assert.assertEquals(modalHeaderText, "Edit Authorized
				// Representative",
				// "Edit Modal Window Header is not matching");

				// Fill in the Add Auth Rep form using Excel sheet
				a.annual_assessment("AnnualAssessment_CMDM_AuthRep", "EditAuthRepFromOverview", 2);
				Thread.sleep(GlobalValues.Sleep_wait_time);
				int AddRepGridCountAfterEdit = gm.getNoOfRowsInGrid("Authorized Rep(s)");
				Log.info("Grid count after editing a record =  " + AddRepGridCountAfterEdit);
				Assert.assertEquals(AddRepGridCountAfterEdit, rowCountBeforeEdit,
						"Grid Count mismatch after editing a record");
				Thread.sleep(GlobalValues.Sleep_wait_time);

				if (!(paginationForwardArrowElement.isEmpty())) {
					paginationForwardArrowElement.get(0).click();
					Thread.sleep(GlobalValues.Sleep_wait_time);
					List<WebElement> listOfRecordsInThePageElement = driver
							.findElements(By.xpath(config.getGridRowCountElementXpath()));
					int rowCountOnPage = listOfRecordsInThePageElement.size();
					Log.info("Count of rows in the page = " + rowCountOnPage);
					Thread.sleep(GlobalValues.Sleep_wait_time);

				}

				String name = wait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getNameXpathFromOverview())))
						.getText().trim();
				String relationshipToMember = wait
						.until(ExpectedConditions
								.presenceOfElementLocated(By.xpath(config.getRelationshipToMemberXpathFromOverview())))
						.getText().trim();
				String phone = wait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getphoneXpathFromOverview())))
						.getText().trim();

				String verifiedDate = wait
						.until(ExpectedConditions
								.presenceOfElementLocated(By.xpath(config.getverifiedDateXpathFromOverview())))
						.getText().trim();

				// click on expand on the grid for last record
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(config.getExpandCollapseLastRecordInGridElementXpath())))
						.click();
				Thread.sleep(GlobalValues.Sleep_wait_time);

				String source = wait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getSourceXpathFromOverview())))
						.getText().trim();
				String phoneType = wait
						.until(ExpectedConditions
								.presenceOfElementLocated(By.xpath(config.getPhoneTypeXpathFromOverview())))
						.getText().trim();

				String firstNameFromExcel = gm.readFromExcel("AnnualAssessment_CMDM_AuthRep", "EditAuthRepFromOverview", 2,
						"First Name");
				String lastNameFromExcel = gm.readFromExcel("AnnualAssessment_CMDM_AuthRep", "EditAuthRepFromOverview", 2,
						"Last Name");
				String nameFromExcel = (lastNameFromExcel + ", " + firstNameFromExcel);

				String relationshipToMemberFromExcel = gm.readFromExcel("AnnualAssessment_CMDM_AuthRep",
						"EditAuthRepFromOverview", 2, "Relationship to Member");
				String phoneTypeFromExcel = gm.readFromExcel("AnnualAssessment_CMDM_AuthRep", "EditAuthRepFromOverview", 2,
						"Phone Type|1");
				String phoneFromExcel = gm.readFromExcel("AnnualAssessment_CMDM_AuthRep", "EditAuthRepFromOverview", 2,
						"Phone|3");
				String sourceFromExcel = gm.readFromExcel("AnnualAssessment_CMDM_AuthRep", "EditAuthRepFromOverview", 2,
						"Source");
				String verifiedFromExcel = gm.readFromExcel("AnnualAssessment_CMDM_AuthRep", "EditAuthRepFromOverview", 2,
						"Verified|1");

				if (verifiedFromExcel.contains("click")) {
					verifiedDateSystem = GlobalValues.GetCurrentDateTime();
				} else {
					verifiedDateSystem = null;
				}

				Assert.assertEquals(name, nameFromExcel, "Added values are not matching between excel and as shown on UI");
				Assert.assertEquals(relationshipToMember, relationshipToMemberFromExcel,
						"Added values are not matching between excel and as shown on UI");
				Assert.assertEquals(phone, phoneFromExcel,
						"Added values are not matching between excel and as shown on UI");
				Assert.assertEquals(verifiedDate, verifiedDateSystem,
						"Added values are not matching between excel and as shown on UI");

				Assert.assertEquals(source, sourceFromExcel,
						"Added values are not matching between excel and as shown on UI");
				Assert.assertEquals(phoneType, phoneTypeFromExcel,
						"Added values are not matching between excel and as shown on UI");

				Log.info("Added authorized rep from overview has been verified");

				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(config.getExpandCollapseLastRecordInGridElementXpath())))
						.click();
				Thread.sleep(GlobalValues.Sleep_wait_time);
				Log.info("Collapsed the expanded last row on overview page for Auth Rep grid");

				Log.info("Successfully verified edit functionality for Auth rep from overview ");
				Log.info("**********Verified edit functionality for Auth rep from overview********** ");

			} catch (Exception e) {

				Log.info(e + " Grid Title is not matching");
			}

			
		}
		
		public void editAndVerifyOrganizationProviderFromCareTeamPage() throws Exception {
			Log = Logger.getLogger("ProviderPage.class");
			PropertyConfigurator.configure("log4j.properties");
			AnnualAssessment a = new AnnualAssessment(driver);
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			Global_Method gm = new Global_Method(driver);
			LeftNavigationLink lnk = new LeftNavigationLink(driver);
			int rowCountBeforeEdit;
			int rowCountAfterEdit;
			String verifiedDateSystem;

			try {
				rowCountBeforeEdit = gm.getNoOfRowsInGrid("Authorized Rep(s)");
				Log.info("Row Count before edit = " + rowCountBeforeEdit);

				List<WebElement> paginationForwardArrowElement = driver
						.findElements(By.xpath("(//a//span[@class ='fa fa-step-forward'])[1]"));

				if (!(paginationForwardArrowElement.isEmpty())) {
					paginationForwardArrowElement.get(0).click();
					Thread.sleep(GlobalValues.Sleep_wait_time);
					List<WebElement> listOfRecordsInThePageElement = driver
							.findElements(By.xpath(config.getGridRowCountElementXpath()));
					int rowCountOnPage = listOfRecordsInThePageElement.size();
					Log.info("Count of rows in the page = " + rowCountOnPage);
					Thread.sleep(GlobalValues.Sleep_wait_time);

				}

				WebElement editIconForAuthRepOnOverViewElement = driver
						.findElement(By.xpath(config.getEditIconForAuthRepOnOverViewElementXpath()));
				editIconForAuthRepOnOverViewElement.click();
				Log.info("Clicked on the Edit Note+Pen icon on the last record for Auth rep on Member Overview");

				// Verify Modal Window Header
				WebElement modalHeaderElement = wait.until(
						ExpectedConditions.presenceOfElementLocated(By.xpath(config.getEditModalHeaderElementXpath())));
				String modalHeaderText = modalHeaderElement.getText();
				Log.info("Modal Window Header = " + modalHeaderText);
				// Assert.assertEquals(modalHeaderText, "Edit Authorized
				// Representative",
				// "Edit Modal Window Header is not matching");

				// Fill in the Add Auth Rep form using Excel sheet
				a.annual_assessment("AnnualAssessment_CMDM_AuthRep", "EditAuthRepFromOverview", 2);
				Thread.sleep(GlobalValues.Sleep_wait_time);
				int AddRepGridCountAfterEdit = gm.getNoOfRowsInGrid("Authorized Rep(s)");
				Log.info("Grid count after editing a record =  " + AddRepGridCountAfterEdit);
				Assert.assertEquals(AddRepGridCountAfterEdit, rowCountBeforeEdit,
						"Grid Count mismatch after editing a record");
				Thread.sleep(GlobalValues.Sleep_wait_time);

				if (!(paginationForwardArrowElement.isEmpty())) {
					paginationForwardArrowElement.get(0).click();
					Thread.sleep(GlobalValues.Sleep_wait_time);
					List<WebElement> listOfRecordsInThePageElement = driver
							.findElements(By.xpath(config.getGridRowCountElementXpath()));
					int rowCountOnPage = listOfRecordsInThePageElement.size();
					Log.info("Count of rows in the page = " + rowCountOnPage);
					Thread.sleep(GlobalValues.Sleep_wait_time);

				}

				String name = wait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getNameXpathFromOverview())))
						.getText().trim();
				String relationshipToMember = wait
						.until(ExpectedConditions
								.presenceOfElementLocated(By.xpath(config.getRelationshipToMemberXpathFromOverview())))
						.getText().trim();
				String phone = wait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getphoneXpathFromOverview())))
						.getText().trim();

				String verifiedDate = wait
						.until(ExpectedConditions
								.presenceOfElementLocated(By.xpath(config.getverifiedDateXpathFromOverview())))
						.getText().trim();

				// click on expand on the grid for last record
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(config.getExpandCollapseLastRecordInGridElementXpath())))
						.click();
				Thread.sleep(GlobalValues.Sleep_wait_time);

				String source = wait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getSourceXpathFromOverview())))
						.getText().trim();
				String phoneType = wait
						.until(ExpectedConditions
								.presenceOfElementLocated(By.xpath(config.getPhoneTypeXpathFromOverview())))
						.getText().trim();

				String firstNameFromExcel = gm.readFromExcel("AnnualAssessment_CMDM_AuthRep", "EditAuthRepFromOverview", 2,
						"First Name");
				String lastNameFromExcel = gm.readFromExcel("AnnualAssessment_CMDM_AuthRep", "EditAuthRepFromOverview", 2,
						"Last Name");
				String nameFromExcel = (lastNameFromExcel + ", " + firstNameFromExcel);

				String relationshipToMemberFromExcel = gm.readFromExcel("AnnualAssessment_CMDM_AuthRep",
						"EditAuthRepFromOverview", 2, "Relationship to Member");
				String phoneTypeFromExcel = gm.readFromExcel("AnnualAssessment_CMDM_AuthRep", "EditAuthRepFromOverview", 2,
						"Phone Type|1");
				String phoneFromExcel = gm.readFromExcel("AnnualAssessment_CMDM_AuthRep", "EditAuthRepFromOverview", 2,
						"Phone|3");
				String sourceFromExcel = gm.readFromExcel("AnnualAssessment_CMDM_AuthRep", "EditAuthRepFromOverview", 2,
						"Source");
				String verifiedFromExcel = gm.readFromExcel("AnnualAssessment_CMDM_AuthRep", "EditAuthRepFromOverview", 2,
						"Verified|1");

				if (verifiedFromExcel.contains("click")) {
					verifiedDateSystem = GlobalValues.GetCurrentDateTime();
				} else {
					verifiedDateSystem = null;
				}

				Assert.assertEquals(name, nameFromExcel, "Added values are not matching between excel and as shown on UI");
				Assert.assertEquals(relationshipToMember, relationshipToMemberFromExcel,
						"Added values are not matching between excel and as shown on UI");
				Assert.assertEquals(phone, phoneFromExcel,
						"Added values are not matching between excel and as shown on UI");
				Assert.assertEquals(verifiedDate, verifiedDateSystem,
						"Added values are not matching between excel and as shown on UI");

				Assert.assertEquals(source, sourceFromExcel,
						"Added values are not matching between excel and as shown on UI");
				Assert.assertEquals(phoneType, phoneTypeFromExcel,
						"Added values are not matching between excel and as shown on UI");

				Log.info("Added authorized rep from overview has been verified");

				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(config.getExpandCollapseLastRecordInGridElementXpath())))
						.click();
				Thread.sleep(GlobalValues.Sleep_wait_time);
				Log.info("Collapsed the expanded last row on overview page for Auth Rep grid");

				Log.info("Successfully verified edit functionality for Auth rep from overview ");
				Log.info("**********Verified edit functionality for Auth rep from overview********** ");

			} catch (Exception e) {

				Log.info(e + " Grid Title is not matching");
			}

			
		}
		
		
		public void editAndVerifyIndividualProviderFromInitialOutreachPage() throws Exception {
			Log = Logger.getLogger("ProviderPage.class");
			PropertyConfigurator.configure("log4j.properties");
			AnnualAssessment a = new AnnualAssessment(driver);
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			Global_Method gm = new Global_Method(driver);
			LeftNavigationLink lnk = new LeftNavigationLink(driver);
			int rowCountBeforeEdit;
			int rowCountAfterEdit;
			String verifiedDateSystem;

			try {
				rowCountBeforeEdit = gm.getNoOfRowsInGrid("Authorized Rep(s)");
				Log.info("Row Count before edit = " + rowCountBeforeEdit);

				List<WebElement> paginationForwardArrowElement = driver
						.findElements(By.xpath("(//a//span[@class ='fa fa-step-forward'])[1]"));

				if (!(paginationForwardArrowElement.isEmpty())) {
					paginationForwardArrowElement.get(0).click();
					Thread.sleep(GlobalValues.Sleep_wait_time);
					List<WebElement> listOfRecordsInThePageElement = driver
							.findElements(By.xpath(config.getGridRowCountElementXpath()));
					int rowCountOnPage = listOfRecordsInThePageElement.size();
					Log.info("Count of rows in the page = " + rowCountOnPage);
					Thread.sleep(GlobalValues.Sleep_wait_time);

				}

				WebElement editIconForAuthRepOnOverViewElement = driver
						.findElement(By.xpath(config.getEditIconForAuthRepOnOverViewElementXpath()));
				editIconForAuthRepOnOverViewElement.click();
				Log.info("Clicked on the Edit Note+Pen icon on the last record for Auth rep on Member Overview");

				// Verify Modal Window Header
				WebElement modalHeaderElement = wait.until(
						ExpectedConditions.presenceOfElementLocated(By.xpath(config.getEditModalHeaderElementXpath())));
				String modalHeaderText = modalHeaderElement.getText();
				Log.info("Modal Window Header = " + modalHeaderText);
				// Assert.assertEquals(modalHeaderText, "Edit Authorized
				// Representative",
				// "Edit Modal Window Header is not matching");

				// Fill in the Add Auth Rep form using Excel sheet
				a.annual_assessment("AnnualAssessment_CMDM_AuthRep", "EditAuthRepFromOverview", 2);
				Thread.sleep(GlobalValues.Sleep_wait_time);
				int AddRepGridCountAfterEdit = gm.getNoOfRowsInGrid("Authorized Rep(s)");
				Log.info("Grid count after editing a record =  " + AddRepGridCountAfterEdit);
				Assert.assertEquals(AddRepGridCountAfterEdit, rowCountBeforeEdit,
						"Grid Count mismatch after editing a record");
				Thread.sleep(GlobalValues.Sleep_wait_time);

				if (!(paginationForwardArrowElement.isEmpty())) {
					paginationForwardArrowElement.get(0).click();
					Thread.sleep(GlobalValues.Sleep_wait_time);
					List<WebElement> listOfRecordsInThePageElement = driver
							.findElements(By.xpath(config.getGridRowCountElementXpath()));
					int rowCountOnPage = listOfRecordsInThePageElement.size();
					Log.info("Count of rows in the page = " + rowCountOnPage);
					Thread.sleep(GlobalValues.Sleep_wait_time);

				}

				String name = wait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getNameXpathFromOverview())))
						.getText().trim();
				String relationshipToMember = wait
						.until(ExpectedConditions
								.presenceOfElementLocated(By.xpath(config.getRelationshipToMemberXpathFromOverview())))
						.getText().trim();
				String phone = wait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getphoneXpathFromOverview())))
						.getText().trim();

				String verifiedDate = wait
						.until(ExpectedConditions
								.presenceOfElementLocated(By.xpath(config.getverifiedDateXpathFromOverview())))
						.getText().trim();

				// click on expand on the grid for last record
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(config.getExpandCollapseLastRecordInGridElementXpath())))
						.click();
				Thread.sleep(GlobalValues.Sleep_wait_time);

				String source = wait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getSourceXpathFromOverview())))
						.getText().trim();
				String phoneType = wait
						.until(ExpectedConditions
								.presenceOfElementLocated(By.xpath(config.getPhoneTypeXpathFromOverview())))
						.getText().trim();

				String firstNameFromExcel = gm.readFromExcel("AnnualAssessment_CMDM_AuthRep", "EditAuthRepFromOverview", 2,
						"First Name");
				String lastNameFromExcel = gm.readFromExcel("AnnualAssessment_CMDM_AuthRep", "EditAuthRepFromOverview", 2,
						"Last Name");
				String nameFromExcel = (lastNameFromExcel + ", " + firstNameFromExcel);

				String relationshipToMemberFromExcel = gm.readFromExcel("AnnualAssessment_CMDM_AuthRep",
						"EditAuthRepFromOverview", 2, "Relationship to Member");
				String phoneTypeFromExcel = gm.readFromExcel("AnnualAssessment_CMDM_AuthRep", "EditAuthRepFromOverview", 2,
						"Phone Type|1");
				String phoneFromExcel = gm.readFromExcel("AnnualAssessment_CMDM_AuthRep", "EditAuthRepFromOverview", 2,
						"Phone|3");
				String sourceFromExcel = gm.readFromExcel("AnnualAssessment_CMDM_AuthRep", "EditAuthRepFromOverview", 2,
						"Source");
				String verifiedFromExcel = gm.readFromExcel("AnnualAssessment_CMDM_AuthRep", "EditAuthRepFromOverview", 2,
						"Verified|1");

				if (verifiedFromExcel.contains("click")) {
					verifiedDateSystem = GlobalValues.GetCurrentDateTime();
				} else {
					verifiedDateSystem = null;
				}

				Assert.assertEquals(name, nameFromExcel, "Added values are not matching between excel and as shown on UI");
				Assert.assertEquals(relationshipToMember, relationshipToMemberFromExcel,
						"Added values are not matching between excel and as shown on UI");
				Assert.assertEquals(phone, phoneFromExcel,
						"Added values are not matching between excel and as shown on UI");
				Assert.assertEquals(verifiedDate, verifiedDateSystem,
						"Added values are not matching between excel and as shown on UI");

				Assert.assertEquals(source, sourceFromExcel,
						"Added values are not matching between excel and as shown on UI");
				Assert.assertEquals(phoneType, phoneTypeFromExcel,
						"Added values are not matching between excel and as shown on UI");

				Log.info("Added authorized rep from overview has been verified");

				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(config.getExpandCollapseLastRecordInGridElementXpath())))
						.click();
				Thread.sleep(GlobalValues.Sleep_wait_time);
				Log.info("Collapsed the expanded last row on overview page for Auth Rep grid");

				Log.info("Successfully verified edit functionality for Auth rep from overview ");
				Log.info("**********Verified edit functionality for Auth rep from overview********** ");

			} catch (Exception e) {

				Log.info(e + " Grid Title is not matching");
			}

			
		}
		
		public void editAndVerifyOrganizationProviderFromInitialOutreachPage() throws Exception {
			Log = Logger.getLogger("ProviderPage.class");
			PropertyConfigurator.configure("log4j.properties");
			AnnualAssessment a = new AnnualAssessment(driver);
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			Global_Method gm = new Global_Method(driver);
			LeftNavigationLink lnk = new LeftNavigationLink(driver);
			int rowCountBeforeEdit;
			int rowCountAfterEdit;
			String verifiedDateSystem;

			try {
				rowCountBeforeEdit = gm.getNoOfRowsInGrid("Authorized Rep(s)");
				Log.info("Row Count before edit = " + rowCountBeforeEdit);

				List<WebElement> paginationForwardArrowElement = driver
						.findElements(By.xpath("(//a//span[@class ='fa fa-step-forward'])[1]"));

				if (!(paginationForwardArrowElement.isEmpty())) {
					paginationForwardArrowElement.get(0).click();
					Thread.sleep(GlobalValues.Sleep_wait_time);
					List<WebElement> listOfRecordsInThePageElement = driver
							.findElements(By.xpath(config.getGridRowCountElementXpath()));
					int rowCountOnPage = listOfRecordsInThePageElement.size();
					Log.info("Count of rows in the page = " + rowCountOnPage);
					Thread.sleep(GlobalValues.Sleep_wait_time);

				}

				WebElement editIconForAuthRepOnOverViewElement = driver
						.findElement(By.xpath(config.getEditIconForAuthRepOnOverViewElementXpath()));
				editIconForAuthRepOnOverViewElement.click();
				Log.info("Clicked on the Edit Note+Pen icon on the last record for Auth rep on Member Overview");

				// Verify Modal Window Header
				WebElement modalHeaderElement = wait.until(
						ExpectedConditions.presenceOfElementLocated(By.xpath(config.getEditModalHeaderElementXpath())));
				String modalHeaderText = modalHeaderElement.getText();
				Log.info("Modal Window Header = " + modalHeaderText);
				// Assert.assertEquals(modalHeaderText, "Edit Authorized
				// Representative",
				// "Edit Modal Window Header is not matching");

				// Fill in the Add Auth Rep form using Excel sheet
				a.annual_assessment("AnnualAssessment_CMDM_AuthRep", "EditAuthRepFromOverview", 2);
				Thread.sleep(GlobalValues.Sleep_wait_time);
				int AddRepGridCountAfterEdit = gm.getNoOfRowsInGrid("Authorized Rep(s)");
				Log.info("Grid count after editing a record =  " + AddRepGridCountAfterEdit);
				Assert.assertEquals(AddRepGridCountAfterEdit, rowCountBeforeEdit,
						"Grid Count mismatch after editing a record");
				Thread.sleep(GlobalValues.Sleep_wait_time);

				if (!(paginationForwardArrowElement.isEmpty())) {
					paginationForwardArrowElement.get(0).click();
					Thread.sleep(GlobalValues.Sleep_wait_time);
					List<WebElement> listOfRecordsInThePageElement = driver
							.findElements(By.xpath(config.getGridRowCountElementXpath()));
					int rowCountOnPage = listOfRecordsInThePageElement.size();
					Log.info("Count of rows in the page = " + rowCountOnPage);
					Thread.sleep(GlobalValues.Sleep_wait_time);

				}

				String name = wait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getNameXpathFromOverview())))
						.getText().trim();
				String relationshipToMember = wait
						.until(ExpectedConditions
								.presenceOfElementLocated(By.xpath(config.getRelationshipToMemberXpathFromOverview())))
						.getText().trim();
				String phone = wait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getphoneXpathFromOverview())))
						.getText().trim();

				String verifiedDate = wait
						.until(ExpectedConditions
								.presenceOfElementLocated(By.xpath(config.getverifiedDateXpathFromOverview())))
						.getText().trim();

				// click on expand on the grid for last record
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(config.getExpandCollapseLastRecordInGridElementXpath())))
						.click();
				Thread.sleep(GlobalValues.Sleep_wait_time);

				String source = wait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getSourceXpathFromOverview())))
						.getText().trim();
				String phoneType = wait
						.until(ExpectedConditions
								.presenceOfElementLocated(By.xpath(config.getPhoneTypeXpathFromOverview())))
						.getText().trim();

				String firstNameFromExcel = gm.readFromExcel("AnnualAssessment_CMDM_AuthRep", "EditAuthRepFromOverview", 2,
						"First Name");
				String lastNameFromExcel = gm.readFromExcel("AnnualAssessment_CMDM_AuthRep", "EditAuthRepFromOverview", 2,
						"Last Name");
				String nameFromExcel = (lastNameFromExcel + ", " + firstNameFromExcel);

				String relationshipToMemberFromExcel = gm.readFromExcel("AnnualAssessment_CMDM_AuthRep",
						"EditAuthRepFromOverview", 2, "Relationship to Member");
				String phoneTypeFromExcel = gm.readFromExcel("AnnualAssessment_CMDM_AuthRep", "EditAuthRepFromOverview", 2,
						"Phone Type|1");
				String phoneFromExcel = gm.readFromExcel("AnnualAssessment_CMDM_AuthRep", "EditAuthRepFromOverview", 2,
						"Phone|3");
				String sourceFromExcel = gm.readFromExcel("AnnualAssessment_CMDM_AuthRep", "EditAuthRepFromOverview", 2,
						"Source");
				String verifiedFromExcel = gm.readFromExcel("AnnualAssessment_CMDM_AuthRep", "EditAuthRepFromOverview", 2,
						"Verified|1");

				if (verifiedFromExcel.contains("click")) {
					verifiedDateSystem = GlobalValues.GetCurrentDateTime();
				} else {
					verifiedDateSystem = null;
				}

				Assert.assertEquals(name, nameFromExcel, "Added values are not matching between excel and as shown on UI");
				Assert.assertEquals(relationshipToMember, relationshipToMemberFromExcel,
						"Added values are not matching between excel and as shown on UI");
				Assert.assertEquals(phone, phoneFromExcel,
						"Added values are not matching between excel and as shown on UI");
				Assert.assertEquals(verifiedDate, verifiedDateSystem,
						"Added values are not matching between excel and as shown on UI");

				Assert.assertEquals(source, sourceFromExcel,
						"Added values are not matching between excel and as shown on UI");
				Assert.assertEquals(phoneType, phoneTypeFromExcel,
						"Added values are not matching between excel and as shown on UI");

				Log.info("Added authorized rep from overview has been verified");

				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(config.getExpandCollapseLastRecordInGridElementXpath())))
						.click();
				Thread.sleep(GlobalValues.Sleep_wait_time);
				Log.info("Collapsed the expanded last row on overview page for Auth Rep grid");

				Log.info("Successfully verified edit functionality for Auth rep from overview ");
				Log.info("**********Verified edit functionality for Auth rep from overview********** ");

			} catch (Exception e) {

				Log.info(e + " Grid Title is not matching");
			}

			
		}
		
		public void verifyPrimaryProviderFromCareTeam() throws Exception {
			Log = Logger.getLogger("ProviderPage.class");
			PropertyConfigurator.configure("log4j.properties");
			AnnualAssessment a = new AnnualAssessment(driver);
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			Global_Method gm = new Global_Method(driver);
			LeftNavigationLink lnk = new LeftNavigationLink(driver);
			

			try{
				
			} catch (Exception e) {

				Log.info(e + " Grid Title is not matching");
			}

			
		}
		
		
		public void verifyPrimaryProviderFromInitialOutreachPage() throws Exception {
			Log = Logger.getLogger("ProviderPage.class");
			PropertyConfigurator.configure("log4j.properties");
			AnnualAssessment a = new AnnualAssessment(driver);
			WebDriverWait wait = new WebDriverWait(driver, GlobalValues.Explicit_Wait_time);
			Global_Method gm = new Global_Method(driver);
			LeftNavigationLink lnk = new LeftNavigationLink(driver);
			

			try{
				
			} catch (Exception e) {

				Log.info(e + " Grid Title is not matching");
			}

			
		}
	}


