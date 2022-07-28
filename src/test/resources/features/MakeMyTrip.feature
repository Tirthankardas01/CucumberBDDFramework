Feature: Make My Trip application booking functionalities

@MMT
Scenario: Make My Trip Hotel booking functionality
Given browser is open and application is launched
When user enters city name and selects checkin date and selects checkout date
And user clicks on apply and clicks on search button
Then List of hotels is displayed for the city

