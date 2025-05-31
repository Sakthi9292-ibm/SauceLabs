Feature: login


Background: go to URL
Given User opens the sauceLab website "https://www.saucedemo.com/"


@login @sanity @positive
Scenario Outline: Login Successful login
When  User enters login information with <userid> and <password>
And User click the login button
Then User should be redirected to the shopping page
Examples:
|userid|password|
|"standard_user"|"secret_sauce"|
|"locked_out_user"|"secret_sauce"|
|"problem_user"|"secret_sauce"|
|"performance_glitch_user"|"secret_sauce"|
|"error_user"|"secret_sauce"|
|"visual_user"|"secret_sauce"|


@login @sanity @negative
Scenario Outline: Login Invalid User 
When  User enters login information with <userid> and <password>
And User click the login button
Then website should throw <message>
Examples:
|userid|password|message|
|"user123"|"pass123"|"Username and password do not match any user in this service"|

@login @sanity @negative
Scenario Outline: Login Password Required 
When  User enters login information with <userid> and <password>
And User click the login button
Then website should throw <message>
Examples:
|userid|password|message|
|"user123"|""|"Password is required"|

@login @sanity @negative
Scenario Outline: Login Username Required
When  User enters login information with <userid> and <password>
And User click the login button
Then website should throw <message>
Examples:
|userid|password|message|
|""|"pass123"|"Username is required"|
