Feature: Adding to Cart

  Scenario: User adds item to the shopping bag using search
    Given user clicks on Search field
    When user inputs <searching item> and clicks on Search icon
    Then user can find and choose the searching item on the page
    Then user goes to item cart and adds the item to the shopping bag
    Then message contains text <message>

