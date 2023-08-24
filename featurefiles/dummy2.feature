Feature: Dummy

  Background: #Precondition

  @TestCode
  Scenario Outline: Add and Remove Single Keyword from Delete button
    Given User navigates to "<loginUrl>" using "<browser>"
    And User enter the username as "<username>"
    And User enter the password as "<password>"
    And User click on the login button
    Then User is successfully login
    When User navigates to "<crmURL>"
    And User adds contacts through file upload

    Examples: 
      | browser | loginUrl                              | crmURL                                   | username            | password  |
      | chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/pro/crm/ | reuel+01@axadra.com | asdasdasd |
