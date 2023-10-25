@SEM_AWS_Delete_Buckets
Feature: Dummy

  Background: #Precondition

  @DeleteFiles
  Scenario Outline: Delete Files between <from> and <to> links
    Given User navigates to "<loginUrl>" using "<browser>"
    And I copy the the href between <from> and <to> links
    When User navigates to "<awsURL>"
    And User enter the AWS accountID as "<accountID>"
    And User enter the AWS username as "<username>"
    And User enter the AWS password as "<password>"
    And User click the AWS SignIn button
    And User delete the AWS Buckets

    Examples: 
      | browser | loginUrl                         | awsURL                                             | username | password     | accountID    | from | to |
      | chrome  | https://dev.semify.com/ztemp.cfm | https://324263547202.signin.aws.amazon.com/console | seor     | Cl3v3l@nd123 | 324263547202 |    1 |  3 |
      | chrome  | https://dev.semify.com/ztemp.cfm | https://324263547202.signin.aws.amazon.com/console | seor     | Cl3v3l@nd123 | 324263547202 |    4 |  6 |
