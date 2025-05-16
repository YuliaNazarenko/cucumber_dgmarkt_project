Feature: Adding to Cart

  Scenario: User adds item to the shopping bag using search
    Given user clicks on Search field
    When user inputs <searching item> and clicks on Search icon
    Then user can find and choose the searching item on the page
    Then user goes to item cart and adds the item to the shopping bag
    Then message contains text <message>

  Scenario: User adds item to the shopping bag choosing by category
    Given user goes to Health and Beauty category and chooses it
    When user found the searching item on the page and click the Add to Cart button
    Then message contains text <message>