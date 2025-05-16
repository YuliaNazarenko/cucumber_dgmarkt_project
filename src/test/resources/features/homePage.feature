Feature: Home page features

  Scenario: User can change a currency
    Given user is on home page
    When user selects a target currency from drop down list
    Then the currency is as selected