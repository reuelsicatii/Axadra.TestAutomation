@SEOR_AgencyTools_WebAudit_SocialActivity
Feature: SEOR > Agency Tools > WebAudit > Social Activity

  Background: 

  #Given User navigates to "https://account.seoreseller.com/login" using "chrome"
  #And User enter the username as "reuel+01@axadra.com"
  #And User enter the password as "asdasdasd"
  #And User click on the login button
  #Then User is successfully login
  #When User navigates to "https://account.seoreseller.com/pro/audits"
  #And User generates a WebAuditReport
  @WebAuditReportSocialActivityFacebookData
  Scenario Outline: Generate WebAudit Report and validate Social Activity > Facebook Data between "<fromDate>" and "<toDate>"
    Given User setup a "<browser>"
    And User validates the Social Activity > Facebook SubSection > Data is pulled between "<fromDate>" and "<toDate>"

    Examples: 
      | browser | fromDate            | toDate |
      | chrome  | 2024-02-26 00:00:00 | NOW    |

		  #| chrome  | 2023-10-01 00:00:00 | NOW                 |
		  #| chrome  | 2023-07-01 00:00:00 | 2023-09-30 23:59:59 |
		  #| chrome  | 2023-04-01 00:00:00 | 2023-06-30 23:59:59 |
		  #| chrome  | 2023-01-01 00:00:00 | 2023-03-31 23:59:59 |
  
  @WebAuditReportSocialActivityTwitterData
  Scenario Outline: Generate WebAudit Report and validate Social Activity > Twitter Data between "<fromDate>" and "<toDate>"
    Given User setup a "<browser>"
    And User validates the Social Activity > Twitter SubSection > Data is pulled between "<fromDate>" and "<toDate>"

    Examples: 
      | browser | fromDate            | toDate |
      | chrome  | 2024-02-26 00:00:00 | NOW    |
      
	    #| chrome  | 2023-10-01 00:00:00 | NOW                 |
	    #| chrome  | 2023-07-01 00:00:00 | 2023-09-30 23:59:59 |
	    #| chrome  | 2023-04-01 00:00:00 | 2023-06-30 23:59:59 |
	    #| chrome  | 2023-01-01 00:00:00 | 2023-03-31 23:59:59 |
