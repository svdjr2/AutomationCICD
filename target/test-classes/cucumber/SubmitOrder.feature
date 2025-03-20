
@tag
Feature: Purchase the order from Ecommerce Website

	Background: 
	Given I landed on Ecommerce Page	

  @Regression
  Scenario Outline: Positive Test of Submitting the order
    Given Logged in username <email> and password <password>
    When I add product <productName> from Cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
    | email								| password      		| productName       |
    | sdt74201@gmail.com    | Abcd!234     | ADIDAS ORIGINAL |
