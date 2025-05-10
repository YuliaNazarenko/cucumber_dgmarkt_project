Feature: Login

  @positive
  Scenario Outline: Login with two valid parameters
    Given user opens login page
    When user enters login '<login>' and password '<password>'
    Then message contains text '<successMessage>'

    Examples:
      | login          | password | successMessage                        |
      | test2@mail.com | 1234     | Congratulation! Login Successfully\n× |
      | test3@mail.com | 1234     | Congratulation! Login Successfully\n× |

  @negative
  Scenario Outline: Login with incorrect data
    Given user opens login page
    When user enters invalid login '<login>' or invalid password '<password>'
    Then alert message contains text '<Warning message>' or '<Alternative message>'

    Examples:
      | login                 | password | Warning message                                          | Alternative message                                                                                 |
      | test1@mail.com        | 4321     | Warning: No match for E-Mail Address and/or Password.\n× | Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.\n× |
      | invalidEmail@mail.com | 1234     | Warning: No match for E-Mail Address and/or Password.\n× | Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.\n× |
      | test1@mail.com        |          | Warning: No match for E-Mail Address and/or Password.\n× | Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.\n× |
      |                       | 1234     | Warning: No match for E-Mail Address and/or Password.\n× | Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.\n× |
      |                       |          | Warning: No match for E-Mail Address and/or Password.\n× | Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.\n× |


  @positive
  Scenario: Sending email to update forgotten password
    Given user opens login page
    When user click Forgotten Password link
    Then user enters valid email and confirms
    Then alert message contains text <message>

  @negative
  Scenario: Sending Invalid email to update forgotten password
    Given user opens login page
    When user click Forgotten Password link
    Then user enters invalid email and confirms
    Then warning message contains text <message>






