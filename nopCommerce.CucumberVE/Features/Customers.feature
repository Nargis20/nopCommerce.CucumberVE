Feature: nopCommerce Customers

 Background: Below are the common steps for each scenario
	Given User Launch Chrome browser
	When User opens URL "http://admin-demo.nopcommerce.com/login"
	And User enters Email as "admin@yourstore.com" and Password as "admin"
	And Click on Login
	Then User can view Dashboad
	When User click on Customers Menu
	And Click on Customers Menu Item

@SanityTest	
Scenario: Add a New Customer
	And Click on Add New Button
	Then User can view Add New Customer Page
	When User enter customer info
	When Click on Save button
	Then User can view confirmation message "The new customer has been added successfully."
	And Close Browser

@Regression		
Scenario: Search Customer by EmailID
	And Enter Customer Email
	When Click on search button
	Then User should found Email in the search table
	And Close Browser

@Regression	
Scenario: Search Customer by Name

	And Enter Customer FirstName
	And Enter Customer LastName
	When Click on search button
	Then User should found Name in the search table
	And Close Browser
	