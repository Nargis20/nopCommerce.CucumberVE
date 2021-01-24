package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utilities.WaitHelper;

public class SearchCustomerPage {

	public WebDriver driver;
	WaitHelper waithelper;
	
	public SearchCustomerPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		waithelper=new WaitHelper(driver);
	}
	
	@FindBy(how = How.ID, using = "SearchEmail")
	WebElement txtEmail;
	
	@FindBy(how = How.ID, using = "SearchFirstName")
	WebElement txtFName;
	
	@FindBy(how = How.ID, using = "SearchLastName")
	WebElement txtLName;
	
	@FindBy(how = How.XPATH, using = "//button[@id='search-customers']")
	WebElement btnSearch;
	
	@FindBy(how = How.XPATH, using = "//table[@id='customers-grid']")
	WebElement table;
	
	@FindBy(how = How.XPATH, using ="//table[@id='customers-grid']//tbody/tr")
	List<WebElement> tableRows;
	
	@FindBy(how = How.XPATH, using ="//table[@id='customers-grid']//tbody/tr/td")
	List<WebElement> tableColumns;
	
	public void setEmail(String email)
	{
		waithelper.WaitForElement(txtEmail, 30);
		txtEmail.clear();
		txtEmail.sendKeys(email);
	}
	
	public void setFirstName (String fname)
	{
		waithelper.WaitForElement(txtFName, 30);
		txtFName.clear();
		txtFName.sendKeys(fname);
	}
	
	public void setLastName (String lname)
	{
		waithelper.WaitForElement(txtLName, 30);
		txtLName.clear();
		txtLName.sendKeys(lname);
	}
	
	public void clickSearch()
	{
		btnSearch.click();
		waithelper.WaitForElement(btnSearch, 30);
	}
	
	public int getNoOfRows() 
	{
		return(tableRows.size());
	}
	
	public int getNoOfColums()
	{
		return(tableColumns.size());
	}
	
	public boolean searchCustomerByEmail(String email)
	{
		boolean flag = false;
		for(int i = 1; i<=getNoOfRows(); i++)
		{
			String emailId = table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr["+i+"]/td[2]")).getText();
			System.out.println(emailId);
			
			if(emailId.equals(email))
			{
				flag =true;
			}
		}
		return flag;
	}
	
	public boolean searchCustomerByName(String Name)
	{
		boolean flag = false;
		for(int i = 1; i<=getNoOfRows(); i++)
		{
			String name = table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr["+i+"]/td[3]")).getText();
			String names[] = name.split(" ");//where have space split/separate fname and lname
			if(names[0].equals("James") && names[1].equals("Pan"))
			{
				flag = true;
			}
		}
		return flag;
	}
}
