Feature: Login

  @negative

  Scenario: Empty fields login
    Given user opens login page
    When user click on login button
    Then warning massage contains text <alertMassageWarningText>

