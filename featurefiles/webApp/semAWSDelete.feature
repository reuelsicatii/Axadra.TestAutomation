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
      | browser | loginUrl                         | awsURL                                             | username | password     | accountID    | from | to   |
      | chrome  | https://dev.semify.com/ztemp.cfm | https://324263547202.signin.aws.amazon.com/console | seor     | Cl3v3l@nd123 | 324263547202 |    1 | 1000 |
      | chrome  | https://dev.semify.com/ztemp.cfm | https://324263547202.signin.aws.amazon.com/console | seor     | Cl3v3l@nd123 | 324263547202 | 1001 | 2000 |
      | chrome  | https://dev.semify.com/ztemp.cfm | https://324263547202.signin.aws.amazon.com/console | seor     | Cl3v3l@nd123 | 324263547202 | 2001 | 3142 |
      #| chrome  | https://dev.semify.com/ztemp.cfm | https://324263547202.signin.aws.amazon.com/console | seor     | Cl3v3l@nd123 | 324263547202 |  301 |  400 |
      #| chrome  | https://dev.semify.com/ztemp.cfm | https://324263547202.signin.aws.amazon.com/console | seor     | Cl3v3l@nd123 | 324263547202 |  401 |  500 |
      #| chrome  | https://dev.semify.com/ztemp.cfm | https://324263547202.signin.aws.amazon.com/console | seor     | Cl3v3l@nd123 | 324263547202 |  501 |  600 |
      #| chrome  | https://dev.semify.com/ztemp.cfm | https://324263547202.signin.aws.amazon.com/console | seor     | Cl3v3l@nd123 | 324263547202 |  601 |  700 |
      #| chrome  | https://dev.semify.com/ztemp.cfm | https://324263547202.signin.aws.amazon.com/console | seor     | Cl3v3l@nd123 | 324263547202 |  701 |  800 |
      #| chrome  | https://dev.semify.com/ztemp.cfm | https://324263547202.signin.aws.amazon.com/console | seor     | Cl3v3l@nd123 | 324263547202 |  801 |  900 |
      #| chrome  | https://dev.semify.com/ztemp.cfm | https://324263547202.signin.aws.amazon.com/console | seor     | Cl3v3l@nd123 | 324263547202 |  901 | 1000 |
      #| chrome  | https://dev.semify.com/ztemp.cfm | https://324263547202.signin.aws.amazon.com/console | seor     | Cl3v3l@nd123 | 324263547202 | 1001 | 1100 |
      #| chrome  | https://dev.semify.com/ztemp.cfm | https://324263547202.signin.aws.amazon.com/console | seor     | Cl3v3l@nd123 | 324263547202 | 1101 | 1200 |
      #| chrome  | https://dev.semify.com/ztemp.cfm | https://324263547202.signin.aws.amazon.com/console | seor     | Cl3v3l@nd123 | 324263547202 | 1201 | 1300 |
      #| chrome  | https://dev.semify.com/ztemp.cfm | https://324263547202.signin.aws.amazon.com/console | seor     | Cl3v3l@nd123 | 324263547202 | 1301 | 1400 |
      #| chrome  | https://dev.semify.com/ztemp.cfm | https://324263547202.signin.aws.amazon.com/console | seor     | Cl3v3l@nd123 | 324263547202 | 1401 | 1500 |
      #| chrome  | https://dev.semify.com/ztemp.cfm | https://324263547202.signin.aws.amazon.com/console | seor     | Cl3v3l@nd123 | 324263547202 | 1501 | 1600 |
      #| chrome  | https://dev.semify.com/ztemp.cfm | https://324263547202.signin.aws.amazon.com/console | seor     | Cl3v3l@nd123 | 324263547202 | 1601 | 1700 |
      #| chrome  | https://dev.semify.com/ztemp.cfm | https://324263547202.signin.aws.amazon.com/console | seor     | Cl3v3l@nd123 | 324263547202 | 1701 | 1800 |
      #| chrome  | https://dev.semify.com/ztemp.cfm | https://324263547202.signin.aws.amazon.com/console | seor     | Cl3v3l@nd123 | 324263547202 | 1801 | 1900 |
      #| chrome  | https://dev.semify.com/ztemp.cfm | https://324263547202.signin.aws.amazon.com/console | seor     | Cl3v3l@nd123 | 324263547202 | 1901 | 2000 |
      #| chrome  | https://dev.semify.com/ztemp.cfm | https://324263547202.signin.aws.amazon.com/console | seor     | Cl3v3l@nd123 | 324263547202 | 2001 | 2100 |
      #| chrome  | https://dev.semify.com/ztemp.cfm | https://324263547202.signin.aws.amazon.com/console | seor     | Cl3v3l@nd123 | 324263547202 | 2101 | 2200 |
      #| chrome  | https://dev.semify.com/ztemp.cfm | https://324263547202.signin.aws.amazon.com/console | seor     | Cl3v3l@nd123 | 324263547202 | 2201 | 2300 |
      #| chrome  | https://dev.semify.com/ztemp.cfm | https://324263547202.signin.aws.amazon.com/console | seor     | Cl3v3l@nd123 | 324263547202 | 2301 | 2400 |
      #| chrome  | https://dev.semify.com/ztemp.cfm | https://324263547202.signin.aws.amazon.com/console | seor     | Cl3v3l@nd123 | 324263547202 | 2401 | 2500 |
      #| chrome  | https://dev.semify.com/ztemp.cfm | https://324263547202.signin.aws.amazon.com/console | seor     | Cl3v3l@nd123 | 324263547202 | 2501 | 2600 |
      #| chrome  | https://dev.semify.com/ztemp.cfm | https://324263547202.signin.aws.amazon.com/console | seor     | Cl3v3l@nd123 | 324263547202 | 2601 | 2700 |
      #| chrome  | https://dev.semify.com/ztemp.cfm | https://324263547202.signin.aws.amazon.com/console | seor     | Cl3v3l@nd123 | 324263547202 | 2701 | 2800 |
      #| chrome  | https://dev.semify.com/ztemp.cfm | https://324263547202.signin.aws.amazon.com/console | seor     | Cl3v3l@nd123 | 324263547202 | 2801 | 2900 |
      #| chrome  | https://dev.semify.com/ztemp.cfm | https://324263547202.signin.aws.amazon.com/console | seor     | Cl3v3l@nd123 | 324263547202 | 2901 | 3000 |
      #| chrome  | https://dev.semify.com/ztemp.cfm | https://324263547202.signin.aws.amazon.com/console | seor     | Cl3v3l@nd123 | 324263547202 | 3001 | 3100 |
      #| chrome  | https://dev.semify.com/ztemp.cfm | https://324263547202.signin.aws.amazon.com/console | seor     | Cl3v3l@nd123 | 324263547202 | 3101 | 3142 |
