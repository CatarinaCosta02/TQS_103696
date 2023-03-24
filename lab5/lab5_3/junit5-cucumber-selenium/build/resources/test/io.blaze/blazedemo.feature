Feature: Buy a flight
  Allow a user to buy a flight
  Scenario: Buy a flight in BlazeDemo
    Given I navigate to "https://blazedemo.com/"
    When I selected the departure city "Boston" and the destination city "New York"
    And Click find flights
    And I choose the flight 234
    And I fill the form
    And I should see the message of confirmation
    Then Check purchase information in the database