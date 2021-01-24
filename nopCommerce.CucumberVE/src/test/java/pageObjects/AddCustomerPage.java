package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddCustomerPage {

	public WebDriver driver;
	
	public AddCustomerPage(WebDriver driver) {
		this.driver =driver;
		PageFactory.initElements(driver, this);
	}
	
	By lnkCustomers_menu = By.cssSelector("i[class*='fa fa-user']");
	By lnkCustomers_menuItem = By.xpath("(//*[contains(text(),'Customers')])[2]");
	By btnAddNew = By.cssSelector("[class='btn bg-blue']");
	
	By txtEmail = By.id("Email");
	By txtPassword = By.id("Password");
	By txtFName = By.id("FirstName");
	By txtLName = By.id("LastName");
	By rbtnGenderMale = By.id("Gender_Male");
	By rbtnGenderFemale = By.id("Gender_Female");
	
	By txtDOB = By.xpath("//input[@id='DateOfBirth']");
	By txtCompanyName = By.xpath("//input[@id='Company']");
	By chkTaxExempt = By.xpath("//input[@id='IsTaxExempt']");
	By txtNewsletter = By.xpath("(//*[@class='k-multiselect-wrap k-floatwrap'])[1]");
	
	By lstCustomerRoles = By.xpath("(//div[@class='k-multiselect-wrap k-floatwrap'])[2]");
	By lstCRAdminstrators = By.xpath("//li[contains(text(),'Administrators')]");
	By lstCRForumModerators = By.xpath("//li[contains(text(),'Forum Moderators')]");
	By lstCRGuests = By.xpath("//li[contains(text(),'Guests')]");
	By lstCRRegistered = By.xpath("//li[contains(text(),'Registered')]");
	By lstCRVendors = By.xpath("//li[contains(text(),'Vendors')]");
	
	By drpMgofVendor = By.xpath("//select[@id='VendorId']");
	By chkActive = By.xpath("//input[@id='Active']");
	
	By txtAdminComment = By.cssSelector("#AdminComment");
	
	By btnSave = By.name("save");
	By btnSavenContinue = By.name("save-continue");
	
	//Actions Methods
	
	public String getPageTitle()
	{
		return driver.getTitle();
	}
	public void clickOnCustomerMenu()
	{
		driver.findElement(lnkCustomers_menu).click();
	}

	public void clickOnCustomerMenuItem()
	{
		driver.findElement(lnkCustomers_menuItem).click();
	}
	
	public void clickOnAddnew()
	{ 
		driver.findElement(btnAddNew).click();
	}
	
	public void setEmail(String email)
	{
		driver.findElement(txtEmail).sendKeys(email);
	}
	
	public void setPassword(String password)
	{
		driver.findElement(txtPassword).sendKeys(password);
	}
	
	public void setFName (String fname)
	{
		driver.findElement(txtFName).sendKeys(fname);
	}
	
	public void setLName (String lname)
	{
		driver.findElement(txtLName).sendKeys(lname);
	}
	
	public void setGender(String gender) {
		if(gender.equals("Male"))
		{
			driver.findElement(rbtnGenderMale).click();
		}
		else if (gender.equals("Female"))
		{
			driver.findElement(rbtnGenderFemale).click();
		}
		else
		{
			driver.findElement(rbtnGenderMale).click();//default
		}
	}
	
	public void setDOB(String dob) {
		driver.findElement(txtDOB).sendKeys(dob);
	}
	
	public void setCompanyName(String comname) {
		driver.findElement(txtCompanyName).sendKeys(comname);
	}
	
	public void setTaxExempt() {
		driver.findElement(chkTaxExempt).click();
	}
	
	public void setNewsletter(String newslet) {
		driver.findElement(txtNewsletter).sendKeys(newslet);
	}
	
	public void setCustomerRoles(String role) throws InterruptedException {
		//If role is vendors should not delete register as per pre-selection
		if(!role.equals("Vendors"))
		{
			driver.findElement(By.xpath("//*[@id='SelectedCustomerRoleIds_taglist']/li/span[2]")).click();
		}
		
		driver.findElement(lstCustomerRoles).click();
		Thread.sleep(3000);
		
	WebElement listitem = null;
		
		if(role.equals("Administrators"))
		{
			listitem = driver.findElement(lstCRAdminstrators);
		} 
		else if (role.equals("Forum Moderators"))
		{
			listitem = driver.findElement(lstCRForumModerators);
		}
		else if (role.equals("Guests"))
		{
			listitem = driver.findElement(lstCRGuests);
		}
		else if (role.equals("Registered"))
		{
			listitem = driver.findElement(lstCRRegistered);
		}
		else if (role.equals("Vendors"))
		{
			listitem = driver.findElement(lstCRVendors);
		}
		
		/*or if click option noot work use JSExecutor
		listitem.click();
		Thread.sleep(2000);
		 */
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", listitem);
		
	}
	
	public void setManagerOfVendor(String value)
	{
		Select drp = new Select(driver.findElement(drpMgofVendor));
		drp.selectByVisibleText(value);
	}
	
	public void setActive() {
		if (!driver.findElement(chkActive).isSelected())
		{
		driver.findElement(chkActive).click();
		}
	}
	
	public void setAdminContent(String content) {
		driver.findElement(txtAdminComment).sendKeys(content);
	}
	
	public void clickOnSave() {
		driver.findElement(btnSave).click();
	}
}
