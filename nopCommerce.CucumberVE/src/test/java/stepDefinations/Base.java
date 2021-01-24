package stepDefinations;

import java.util.Properties;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import pageObjects.AddCustomerPage;

import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;


public class Base {
	public WebDriver driver;
	public LoginPage lp;
	public AddCustomerPage addCust;
	public SearchCustomerPage searchCust;
	public Logger logger;
	
	//to read config.properties file, by that we can take input our browser 
	public Properties configProp;
	
	//Created for generating random String for Unique email
	public static String randomstring() {
		String generatedString1 = RandomStringUtils.randomAlphabetic(5);
		return (generatedString1);
	}
}
