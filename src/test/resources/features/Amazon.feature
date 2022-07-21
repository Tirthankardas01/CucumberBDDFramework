@Amazon
Feature: Amazon.in main page validation

Scenario Outline: Validation of Amazon.in basic functionalities
Given user is on amazon main page
Given page title is "<pagetitle>"
When user clicks on "<option>" in All dropdown
And user clicks on search option
Then watches option should be displayed with all options in the left panel

Examples:
|pagetitle|option|
|Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in|Watches|





