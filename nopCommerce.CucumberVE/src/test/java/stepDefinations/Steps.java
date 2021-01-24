package stepDefinations;


import java.io.FileInputStream;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.cucumber.junit.Cucumber;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;




@RunWith(Cucumber.class)
public class Steps extends Base {

	//All the configuration remove their
@Before
public void setup() throws IOException
{
	//for reading config.properties file
	configProp = new Properties();
	FileInputStream configPropfile = new FileInputStream("config.properties");
	configProp.load(configPropfile);
	
	//Log4j for logging 
	logger=Logger.getLogger("nopCommerce");//Added logger
	PropertyConfigurator.configure("log4j.properties");
	
	String br=configProp.getProperty("browser");
	if(br.equals("chrome"))
	{
	//From config.properties file invoke any browser
	System.setProperty("webdriver.chrome.driver", configProp.getProperty("chromepath"));
	driver = new ChromeDriver();
	}
	else if (br.equals("firefox"))
	{
		System.setProperty("webdriver.gecko.driver", configProp.getProperty("firefoxpath"));
		driver = new FirefoxDriver();
	}
	else if (br.equals("ie"))
	{
		System.setProperty("webdriver.ie.driver", configProp.getProperty("iepath"));
		driver = new InternetExplorerDriver();
	}
	//Normal way invoke chrome browser
	//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/Drivers/chromedriver.exe");
	
	driver.manage().window().maximize();
	logger.info("*********Launching Browser**********");
}

	@Given("User Launch Chrome browser")
	public void user_launch_chrome_browser() {
		
		lp = new LoginPage(driver);
	}

	@When("User opens URL {string}")
	public void user_opens_url(String url) {
		logger.info("*********Opening URL**********");
		driver.get(url);
	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_email_as_and_password_as(String email, String password) {
		logger.info("*********Providing login details**********");
		lp.setUserName(email);
		lp.setPassword(password);
	}

	@When("Click on Login")
	public void click_on_login() {
		logger.info("*********Started Login pProcess**********");
		lp.clickLogin();
	}

	@Then("Page Title should be {string}")
	public void page_title_should_be(String title) {
		if (driver.getPageSource().contains("Login was unsuccessful.")) {
			driver.close();
			Assert.assertTrue("Login was unsuccessful", false);
			logger.info("*********Login failed**********");
		} else {
			Assert.assertEquals("Title is verify sucessfully", title, driver.getTitle());
			logger.info("*********Login Passed**********");
		}
	}

	@When("User click on Log out link")
	public void user_click_on_log_out_link() throws InterruptedException {
		lp.clickLogout();
		Thread.sleep(3000);
		logger.info("*********Click on logout link**********");
	}
	
	@Then("Close Browser")
	public void Close_Browser() {
		logger.info("*********Closing Browser**********");
		driver.quit();
	}

	//Add Customer Features 
		@Then("User can view Dashboad")
		public void user_can_view_dashboad() throws InterruptedException {
			logger.info("*********Dashboard Title Verified**********");
			addCust= new AddCustomerPage(driver);
			Assert.assertEquals("Dashboard / nopCommerce administration", addCust.getPageTitle());
			Thread.sleep(2000);
		}

		@When("User click on Customers Menu")
		public void user_click_on_customers_menu() throws InterruptedException {
			logger.info("*********Select Customer Menu**********");
			addCust.clickOnCustomerMenu();
			Thread.sleep(2000);
		}

		@When("Click on Customers Menu Item")
		public void click_on_customers_menu_item() throws InterruptedException {
			logger.info("*********Click on Customer Menu**********");
			addCust.clickOnCustomerMenuItem();
			Thread.sleep(2000);
		}

		@When("Click on Add New Button")
		public void click_on_add_new_button() throws InterruptedException {
			logger.info("*********Click on Add new Button**********");
			addCust.clickOnAddnew();
			Thread.sleep(2000);
		}

		@Then("User can view Add New Customer Page")
		public void user_can_view_add_new_customer_page() {
			logger.info("*********Verified Add new Customer page Title**********");
			Assert.assertEquals("Add a new customer / nopCommerce administration", addCust.getPageTitle());
		}

		@When("User enter customer info")
		public void user_enter_customer_info() throws InterruptedException {
			logger.info("*********Add new Customer**********");
			logger.info("*********Providing Customer Details**********");
			String email = randomstring()+ "@gmail.com";
			addCust.setEmail(email);
			addCust.setPassword("test123");
			addCust.setFName("Taaraz");
			addCust.setLName("Sena");
			addCust.setGender("Male");
			addCust.setDOB("10/22/2000");
			addCust.setCompanyName("Explorer");
			Thread.sleep(2000);
			addCust.setTaxExempt();
			Thread.sleep(3000);
			//addCust.setNewsletter("I need the news about Covid-19");
			
			/*Registered Default
			 * The customer cannot be in both 'Guests' and 'REgistered' customer roles
			 * Add the customer to 'Guests' or 'Registered' customer role
			 */
			Thread.sleep(4000);
			addCust.setCustomerRoles("Guests");
			Thread.sleep(2000);
			addCust.setActive();
			Thread.sleep(2000);
			addCust.setAdminContent("This is for testing ..........");
		}
		
		@When("Click on Save button")
		public void click_on_save_button() throws InterruptedException {
			logger.info("*********Save Customer information**********");
			addCust.clickOnSave();
			Thread.sleep(2000);
		}

		@Then("User can view confirmation message {string}")
		public void user_can_view_confirmation_message(String string) {
			logger.info("*********new Customer added successfully**********");
			Assert.assertTrue(driver.findElement(By.tagName("body")).getText()
					.contains("The new customer has been added successfully."));
		}
		
		//Search Customer By email Id
		@When("Enter Customer Email")
		public void enter_customer_email() {
			searchCust =new SearchCustomerPage(driver);
			searchCust.setEmail("james_pan@nopCommerce.com");
		}

		@When("Click on search button")
		public void click_on_search_button() throws InterruptedException {
		   searchCust.clickSearch();
		   Thread.sleep(2000);
		}

		@Then("User should found Email in the search table")
		public void user_should_found_email_in_the_search_table() {
		    boolean findStatus=searchCust.searchCustomerByEmail("james_pan@nopCommerce.com");
		    Assert.assertEquals(true,  findStatus);
		}
		
		//Steps for Search Customer By First Name and Last Name
		@When("Enter Customer FirstName")
		public void enter_customer_first_name() {
			searchCust = new SearchCustomerPage(driver);
			searchCust.setFirstName("James");
		}

		@When("Enter Customer LastName")
		public void enter_customer_last_name() {
			searchCust.setLastName("Pan");
		}

		@Then("User should found Name in the search table")
		public void user_should_found_name_in_the_search_table() {
			boolean findStatus=searchCust.searchCustomerByName("James Pan");
			Assert.assertEquals(true, findStatus);
		}


}
