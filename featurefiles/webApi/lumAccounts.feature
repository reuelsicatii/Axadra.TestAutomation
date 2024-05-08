@LumenAccounts
Feature: Lumen > API > Accounts

  Background: 

  #Given I set a request
  #And I set the baseURL to "<BaseURL>"
  #And I reset the baseURL
  #And I set the baseURL to "<BaseURL>"
  #And I set the basePath to "<BasePath>"
  #And I reset the basePath
  #And I set the basePath to "<BasePath>"
  #And I add parameter Key as "page" and Value as "<page>"
  #And I remove parameter Key as "page" and Value as "<page>"
  #And I add parameter Key as "page" and Value as "<page>"
  #And I build a "<method>" request
  #Then I validate response status against "<expectedStatus>"
  #And I validate response body against schema "<expectedSchemaBody>"
  #And I validate response body against raw "<expectedBody>"
  @postCredentialStatus200
  Scenario Outline: postCredentialStatus - 200 - /account-email-server/credentials-status
    Given I set a request
    And I set the baseURL to "<BaseURL>"
    And I set the basePath to "<BasePath>"
    And I add the JSON "<requestBody>" to the request
    And I build a "<method>" request
    Then I validate response status against "<expectedStatus>"
    And I validate response body against raw "<expectedBody>"
    And I validate response body against schema "<expectedSchemaBody>"

    Examples: 
      | BaseURL                     | BasePath                                     | method | expectedStatus | requestBody                                                             | expectedBody                                                             | expectedSchemaBody                                                             |
      | http://dev.api.lumen.io.aws | /api/account-email-server/credentials-status | POST   |            200 | \\data\\webApi.LUM.Accounts\\postCredentialStatus_Req200_TS01_TC01.json | \\data\\webApi.LUM.Accounts\\postCredentialStatus_Resp200_TS01_TC01.json | \\data\\webApi.LUM.Accounts\\postCredentialStatus_RespSchema200_TS01_TC01.json |

  @postCredentialStatus422
  Scenario Outline: postCredentialStatus - 422 - /account-email-server/credentials-status
    Given I set a request
    And I set the baseURL to "<BaseURL>"
    And I set the basePath to "<BasePath>"
    And I add the JSON "<requestBody>" to the request
    And I build a "<method>" request
    Then I validate response status against "<expectedStatus>"
    And I validate response body against raw "<expectedBody>"
    And I validate response body against schema "<expectedSchemaBody>"

    Examples: 
      | BaseURL                     | BasePath                                     | method | expectedStatus | requestBody                                                             | expectedBody                                                             | expectedSchemaBody                                                             |
      | http://dev.api.lumen.io.aws | /api/account-email-server/credentials-status | POST   |            422 | \\data\\webApi.LUM.Accounts\\postCredentialStatus_Req422_TS02_TC01.json | \\data\\webApi.LUM.Accounts\\postCredentialStatus_Resp422_TS02_TC01.json | \\data\\webApi.LUM.Accounts\\postCredentialStatus_RespSchema422_TS02_TC01.json |

  @getAccountWorkflow200
  Scenario Outline: getAccountWorkflow - 200 - /account-workflow
    Given I set a request
    And I set the baseURL to "<BaseURL>"
    And I set the basePath to "<BasePath>"
    And I add parameter Key as "account_id" and Value as "<account_id>"
    And I build a "<method>" request
    Then I validate response status against "<expectedStatus>"
    And I validate response body against raw "<expectedBody>"
    And I validate response body against schema "<expectedSchemaBody>"

    Examples: 
      | BaseURL                     | BasePath              | account_id | method | expectedStatus | expectedBody                                                           | expectedSchemaBody                                                           |
      | http://dev.api.lumen.io.aws | /api/account-workflow |       7140 | GET    |            200 | \\data\\webApi.LUM.Accounts\\getAccountWorkflow_Resp200_TS01_TC01.json | \\data\\webApi.LUM.Accounts\\getAccountWorkflow_RespSchema200_TS01_TC01.json |

  @getAccountWorkflow422
  Scenario Outline: getAccountWorkflow - 422 - /account-workflow
    Given I set a request
    And I set the baseURL to "<BaseURL>"
    And I set the basePath to "<BasePath>"
    And I add parameter Key as "accountt_id" and Value as "<account_id>"
    And I build a "<method>" request
    Then I validate response status against "<expectedStatus>"
    And I validate response body against raw "<expectedBody>"
    And I validate response body against schema "<expectedSchemaBody>"

    Examples: 
      | BaseURL                     | BasePath              | account_id | method | expectedStatus | expectedBody                                                           | expectedSchemaBody                                                           |
      | http://dev.api.lumen.io.aws | /api/account-workflow |       7140 | GET    |            422 | \\data\\webApi.LUM.Accounts\\getAccountWorkflow_Resp422_TS02_TC01.json | \\data\\webApi.LUM.Accounts\\getAccountWorkflow_RespSchema422_TS02_TC01.json |

  @getEmailOverview200
  Scenario Outline: getEmailOverview - 200 - /account-workflow
    Given I set a request
    And I set the baseURL to "<BaseURL>"
    And I set the basePath to "<BasePath>"
    And I add parameter Key as "account_id" and Value as "<account_id>"
    And I add parameter Key as "site_id" and Value as "<site_id>"
    And I add parameter Key as "workflow_type_code" and Value as "<workflow_type_code>"
    And I build a "<method>" request
    Then I validate response status against "<expectedStatus>"
    And I validate response body against raw "<expectedBody>"
    And I validate response body against schema "<expectedSchemaBody>"

    Examples: 
      | BaseURL                     | BasePath                             | account_id | site_id | workflow_type_code | method | expectedStatus | expectedBody                                                         | expectedSchemaBody                                                         |
      | http://dev.api.lumen.io.aws | /api/account-workflow/email-overview |       7140 |       1 | email_automation   | GET    |            200 | \\data\\webApi.LUM.Accounts\\getEmailOverview_Resp200_TS01_TC01.json | \\data\\webApi.LUM.Accounts\\getEmailOverview_RespSchema200_TS01_TC01.json |

  @getEmailOverview422
  Scenario Outline: getEmailOverview - 422 - /account-workflow
    Given I set a request
    And I set the baseURL to "<BaseURL>"
    And I set the basePath to "<BasePath>"
    And I add parameter Key as "account_id1" and Value as "<account_id>"
    And I add parameter Key as "site_id1" and Value as "<site_id>"
    And I add parameter Key as "workflow_type_code1" and Value as "<workflow_type_code>"
    And I build a "<method>" request
    Then I validate response status against "<expectedStatus>"
    And I validate response body against raw "<expectedBody>"
    And I validate response body against schema "<expectedSchemaBody>"

    Examples: 
      | BaseURL                     | BasePath                             | account_id | site_id | workflow_type_code | method | expectedStatus | expectedBody                                                         | expectedSchemaBody                                                         |
      | http://dev.api.lumen.io.aws | /api/account-workflow/email-overview |       7140 |       1 | email_automation   | GET    |            422 | \\data\\webApi.LUM.Accounts\\getEmailOverview_Resp422_TS02_TC01.json | \\data\\webApi.LUM.Accounts\\getEmailOverview_RespSchema422_TS02_TC01.json |

  @postSignUp200
  Scenario Outline: postSignUp - 200 - /signup
    Given I set a request
    And I set the baseURL to "<BaseURL>"
    And I set the basePath to "<BasePath>"
    And I add formBody Key as "password" and Value as "asdasdasd"
    And I add formBody Key as "confirmPassword" and Value as "asdasdasd"
    And I add formBody Key as "site_id" and Value as "1"
    And I add formBody Key as "email" and Value as "dynamicEmail"
    And I add the formBody to the request
    And I build a "<method>" request
    Then I validate response status against "<expectedStatus>"
    And I validate response body against raw "<expectedBody>"
    And I validate response body against schema "<expectedSchemaBody>"

    Examples: 
      | BaseURL                     | BasePath    | method | expectedStatus | expectedBody                                                   | expectedSchemaBody                                                   |
      | http://dev.api.lumen.io.aws | /api/signup | POST   |            200 | \\data\\webApi.LUM.Accounts\\postSignUp_Resp200_TS01_TC01.json | \\data\\webApi.LUM.Accounts\\postSignUp_RespSchema200_TS01_TC01.json |

  @postSignUp422
  Scenario Outline: postSignUp - 422 - /signup
    Given I set a request
    And I set the baseURL to "<BaseURL>"
    And I set the basePath to "<BasePath>"
    And I add formBody Key as "password1" and Value as "asdasdasd"
    And I add formBody Key as "confirmPassword1" and Value as "asdasdasd"
    And I add formBody Key as "site_id1" and Value as "1"
    And I add formBody Key as "email1" and Value as "dynamicEmail"
    And I add the formBody to the request
    And I build a "<method>" request
    Then I validate response status against "<expectedStatus>"
    And I validate response body against raw "<expectedBody>"
    And I validate response body against schema "<expectedSchemaBody>"

    Examples: 
      | BaseURL                     | BasePath    | method | expectedStatus | expectedBody                                                   | expectedSchemaBody                                                   |
      | http://dev.api.lumen.io.aws | /api/signup | POST   |            422 | \\data\\webApi.LUM.Accounts\\postSignUp_Resp422_TS02_TC01.json | \\data\\webApi.LUM.Accounts\\postSignUp_RespSchema422_TS02_TC01.json |

  @putAccount200
  Scenario Outline: putAccount - 200 - /account
    Given I set a request
    And I set the baseURL to "<BaseURL>"
    And I set the basePath to "<BasePath>"
    And I add formBody Key as "id" and Value as "7831"
    And I add formBody Key as "email" and Value as "dynamicEmail"
    And I add formBody Key as "username" and Value as "apitest-username"
    And I add the formBody to the request
    And I build a "<method>" request
    Then I validate response status against "<expectedStatus>"
    And I validate response body against raw "<expectedBody>"
    And I validate response body against schema "<expectedSchemaBody>"

    Examples: 
      | BaseURL                     | BasePath     | method | expectedStatus | expectedBody                                                   | expectedSchemaBody                                                   |
      | http://dev.api.lumen.io.aws | /api/account | PUT    |            200 | \\data\\webApi.LUM.Accounts\\putAccount_Resp200_TS01_TC01.json | \\data\\webApi.LUM.Accounts\\putAccount_RespSchema200_TS01_TC01.json |

  @getAccount200
  Scenario Outline: getAccount - 200 - /account
    Given I set a request
    And I set the baseURL to "<BaseURL>"
    And I set the basePath to "<BasePath>"
    And I add parameter Key as "<parName>" and Value as "<parValue>"
    And I build a "<method>" request
    Then I validate response status against "<expectedStatus>"
    And I validate response body against raw "<expectedBody>"
    And I validate response body against schema "<expectedSchemaBody>"

    Examples: 
      | BaseURL                     | BasePath     | parName | parValue              | method | expectedStatus | expectedBody                                                   | expectedSchemaBody                                                   |
      | http://dev.api.lumen.io.aws | /api/account | id      |                  7140 | GET    |            200 | \\data\\webApi.LUM.Accounts\\getAccount_Resp200_TS01_TC01.json | \\data\\webApi.LUM.Accounts\\getAccount_RespSchema200_TS01_TC01.json |
      | http://dev.api.lumen.io.aws | /api/account | email   | chew@truelogic.com.ph | GET    |            200 | \\data\\webApi.LUM.Accounts\\getAccount_Resp200_TS01_TC02.json | \\data\\webApi.LUM.Accounts\\getAccount_RespSchema200_TS01_TC02.json |

  @getAccount422
  Scenario Outline: getAccount - 422 - /account
    Given I set a request
    And I set the baseURL to "<BaseURL>"
    And I set the basePath to "<BasePath>"
    And I add parameter Key as "<parName>" and Value as "<parValue>"
    And I build a "<method>" request
    Then I validate response status against "<expectedStatus>"
    And I validate response body against raw "<expectedBody>"
    And I validate response body against schema "<expectedSchemaBody>"

    Examples: 
      | BaseURL                     | BasePath     | parName | parValue              | method | expectedStatus | expectedBody                                                   | expectedSchemaBody                                                   |
      | http://dev.api.lumen.io.aws | /api/account | id1     |                  7140 | GET    |            422 | \\data\\webApi.LUM.Accounts\\getAccount_Resp422_TS02_TC01.json | \\data\\webApi.LUM.Accounts\\getAccount_RespSchema422_TS02_TC01.json |
      | http://dev.api.lumen.io.aws | /api/account | email1  | chew@truelogic.com.ph | GET    |            422 | \\data\\webApi.LUM.Accounts\\getAccount_Resp422_TS02_TC02.json | \\data\\webApi.LUM.Accounts\\getAccount_RespSchema422_TS02_TC02.json |

  @getAccountDetails200
  Scenario Outline: getAccountDetails - 200 - /account/details
    Given I set a request
    And I set the baseURL to "<BaseURL>"
    And I set the basePath to "<BasePath>"
    And I build a "<method>" request
    Then I validate response status against "<expectedStatus>"
    # error due to special chracter --> "?page=3"
    #And I validate response body against raw "<expectedBody>"
    And I validate response body against schema "<expectedSchemaBody>"

    Examples: 
      | BaseURL                     | BasePath             | method | expectedStatus | expectedBody                                                          | expectedSchemaBody                                                          |
      | http://dev.api.lumen.io.aws | /api/account/details | GET    |            200 | \\data\\webApi.LUM.Accounts\\getAccountDetails_Resp200_TS01_TC01.json | \\data\\webApi.LUM.Accounts\\getAccountDetails_RespSchema200_TS01_TC01.json |

  @putAccountDashboardProfileInfo200
  Scenario Outline: putAccountDashboardProfileInfo - 200 - /account/dashboard-profile-info
    Given I set a request
    And I set the baseURL to "<BaseURL>"
    And I set the basePath to "<BasePath>"
    And I add formBody Key as "use_id" and Value as "<use_id>"
    And I add formBody Key as "site_id" and Value as "1"
    And I add formBody Key as "firstname" and Value as "ApiTest"
    And I add formBody Key as "lastname" and Value as "Automation"
    And I add the formBody to the request
    And I build a "<method>" request
    Then I validate response status against "<expectedStatus>"
    And I validate response body against raw "<expectedBody>"
    And I validate response body against schema "<expectedSchemaBody>"

    Examples: 
      | BaseURL                     | BasePath                            | use_id | method | expectedStatus | expectedBody                                                                       | expectedSchemaBody                                                                       |
      | http://dev.api.lumen.io.aws | /api/account/dashboard-profile-info |   7628 | PUT    |            200 | \\data\\webApi.LUM.Accounts\\putAccountDashboardProfileInfo_Resp200_TS01_TC01.json | \\data\\webApi.LUM.Accounts\\putAccountDashboardProfileInfo_RespSchema200_TS01_TC01.json |

  @putAccountDashboardProfileInfo422
  Scenario Outline: putAccountDashboardProfileInfo - 422 - /account/dashboard-profile-info
    Given I set a request
    And I set the baseURL to "<BaseURL>"
    And I set the basePath to "<BasePath>"
    And I add formBody Key as "use_id1" and Value as "<use_id>"
    And I add formBody Key as "site_id1" and Value as "1"
    And I add formBody Key as "firstname" and Value as "ApiTest"
    And I add formBody Key as "lastname" and Value as "Automation"
    And I add the formBody to the request
    And I build a "<method>" request
    Then I validate response status against "<expectedStatus>"
    And I validate response body against raw "<expectedBody>"
    And I validate response body against schema "<expectedSchemaBody>"

    Examples: 
      | BaseURL                     | BasePath                            | use_id | method | expectedStatus | expectedBody                                                                       | expectedSchemaBody                                                                       |
      | http://dev.api.lumen.io.aws | /api/account/dashboard-profile-info |   7628 | PUT    |            422 | \\data\\webApi.LUM.Accounts\\putAccountDashboardProfileInfo_Resp422_TS01_TC01.json | \\data\\webApi.LUM.Accounts\\putAccountDashboardProfileInfo_RespSchema422_TS01_TC01.json |

  @getAccountLastLogin200
  Scenario Outline: getAccountLastLogin - 200 - account/last-login
    Given I set a request
    And I set the baseURL to "<BaseURL>"
    And I set the basePath to "<BasePath>"
    And I build a "<method>" request
    Then I validate response status against "<expectedStatus>"
    And I validate response body against raw "<expectedBody>"
    And I validate response body against schema "<expectedSchemaBody>"

    Examples: 
      | BaseURL                     | BasePath                | method | expectedStatus | expectedBody                                                            | expectedSchemaBody                                                            |
      | http://dev.api.lumen.io.aws | /api/account/last-login | GET    |            200 | \\data\\webApi.LUM.Accounts\\getAccountLastLogin_Resp200_TS01_TC01.json | \\data\\webApi.LUM.Accounts\\getAccountLastLogin_RespSchema200_TS01_TC01.json |
