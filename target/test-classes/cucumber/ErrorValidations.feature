
@tag
Feature: Error validation
	
  @ErrorValidation
  Scenario Outline: Title of your scenario outline
    Given I landed on Ecommerce Page   
    When Logged in username <email> and password <password>
    Then "Incorrect email or p2assword." message is displayed

    Examples: 
    | email								| password      |
    | sdt74201@gmail.com    | Abcd!2345     | 