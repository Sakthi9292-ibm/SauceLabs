Feature: Validate the functionality of Shopping page

Background: Login as a valid user
Given User opens the sauceLab website "https://www.saucedemo.com/"
When  User enters login information with "standard_user" and "secret_sauce"
And User click the login button

@shopping
Scenario Outline: Validate product details in the shopping screen
When User is in the shopping page
Then User should see below <products> along with the <price>

Examples:
|products|price|
|"Sauce Labs Backpack"|"$29.99"|
|"Sauce Labs Bike Light"|"$9.99"|
|"Sauce Labs Bolt T-Shirt"|"$15.99"|
|"Sauce Labs Fleece Jacket"|"$49.99"|
|"Sauce Labs Onesie"|"$7.99"|
|"Test.allTheThings() T-Shirt (Red)"|"$15.99"|


@shopping
Scenario: Validate Whether products are added to the cart
When User is in the shopping page
And  User clicks "Add to cart" button for products
|product|
|"Sauce Labs Onesie"|
|"Sauce Labs Backpack"|
Then User should see "2" items added to cart in shopping page

@shopping
Scenario: Validate Whether products are Removed from the cart
When User is in the shopping page
And  User clicks "Add to cart" button for products
|product|
|"Sauce Labs Onesie"|
|"Sauce Labs Backpack"|
Then User should see "2" items added to cart in shopping page
When User clicks "Remove from cart" button for products
|product|
|"sauce Labs Onesie"|
Then User should see "1" items added to cart in shopping page

@shopping
Scenario: Validate clickable function when hover over the products
When User is in the shopping page
And  User moves over the product name
Then User should be allowed to click on it

@shopping
Scenario: Validate whether prices are editable or not
When User is in the shopping page
Then User should not be allowed to edit the prices
