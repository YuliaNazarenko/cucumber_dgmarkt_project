Feature: Checkout

  Scenario: User can order as a guest
    Given user has the added item in the shopping bag
    When user clicks on shopping bag item the checkout form opens
    Then user fills up the guest checkout form
    Then message contains text <title>
