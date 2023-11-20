@LinksBuilt
Feature: API > LinksBuilt

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
  @getAccount200
  Scenario Outline: getAccount - 200 - /api/v1/semify/account/details?accountId=
    Given I set a request
    And I set the baseURL to "<BaseURL>"
    And I set the basePath to "<BasePath>"
    And I add parameter Key as "accountId" and Value as "<accountId>"
    And I build a "<method>" request
    Then I validate response status against "<expectedStatus>"
    And I validate response body against raw "<expectedBody>"
    And I validate response body against schema "<expectedSchemaBody>"

    Examples: 
      | BaseURL                      | BasePath                       | accountId | method | expectedStatus | expectedSchemaBody                                                 | expectedBody                                                 |
      | http://dev.sync.accesshub.co | /api/v1/semify/account/details |      7140 | GET    |            200 | \\data\\webApi.SEM.linksBuilt\\getAccountRespSchema_TS01_TC01.json | \\data\\webApi.SEM.linksBuilt\\getAccountResp_TS01_TC01.json |

  @getAccount422
  Scenario Outline: getAccount - 422 - /api/v1/semify/account/details?accountId=
    Given I set a request
    And I set the baseURL to "<BaseURL>"
    And I set the basePath to "<BasePath>"
    And I add parameter Key as "accountId" and Value as "<accountId>"
    And I build a "<method>" request
    Then I validate response status against "<expectedStatus>"
    And I validate response body against raw "<expectedBody>"
    And I validate response body against schema "<expectedSchemaBody>"

    Examples: 
      | BaseURL                      | BasePath                       | accountId | method | expectedStatus | expectedSchemaBody                                                 | expectedBody                                                 |
      | http://dev.sync.accesshub.co | /api/v1/semify/account/details |           | GET    |            422 | \\data\\webApi.SEM.linksBuilt\\getAccountRespSchema_TS02_TC01.json | \\data\\webApi.SEM.linksBuilt\\getAccountResp_TS02_TC01.json |

  @getCampaign200
  Scenario Outline: getCampaign - 200 - /api/v1/semify/campaign/details?campaignId=
    Given I set a request
    And I set the baseURL to "<BaseURL>"
    And I set the basePath to "<BasePath>"
    And I add parameter Key as "campaignId" and Value as "<campaignId>"
    And I build a "<method>" request
    Then I validate response status against "<expectedStatus>"
    And I validate response body against raw "<expectedBody>"
    And I validate response body against schema "<expectedSchemaBody>"

    Examples: 
      | BaseURL                      | BasePath                        | campaignId | method | expectedStatus | expectedSchemaBody                                                  | expectedBody                                                  |
      | http://dev.sync.accesshub.co | /api/v1/semify/campaign/details |          2 | GET    |            200 | \\data\\webApi.SEM.linksBuilt\\getCampaignRespSchema_TS03_TC01.json | \\data\\webApi.SEM.linksBuilt\\getCampaignResp_TS03_TC01.json |

  @getCampaign422
  Scenario Outline: getCampaign - 422 - /api/v1/semify/campaign/details?campaignId=
    Given I set a request
    And I set the baseURL to "<BaseURL>"
    And I set the basePath to "<BasePath>"
    And I add parameter Key as "campaignId" and Value as "<campaignId>"
    And I build a "<method>" request
    Then I validate response status against "<expectedStatus>"
    And I validate response body against raw "<expectedBody>"
    And I validate response body against schema "<expectedSchemaBody>"

    Examples: 
      | BaseURL                      | BasePath                        | campaignId | method | expectedStatus | expectedSchemaBody                                                  | expectedBody                                                  |
      | http://dev.sync.accesshub.co | /api/v1/semify/campaign/details |            | GET    |            422 | \\data\\webApi.SEM.linksBuilt\\getCampaignRespSchema_TS04_TC01.json | \\data\\webApi.SEM.linksBuilt\\getCampaignResp_TS04_TC01.json |

  @addLink200
  Scenario Outline: addLink - 200 - /api/v1/semify/campaigns/links-built
    Given I set a request
    And I set the baseURL to "<BaseURL>"
    And I set the basePath to "<BasePath>"
    And I update the "<requestBody>" whose "links.url" field is "dynamicURL"
    And I add the "<requestBody>" to the request
    And I build a "<method>" request
    Then I validate response status against "<expectedStatus>"
    And I validate response body against raw "<expectedBody>"
    And I validate response body against schema "<expectedSchemaBody>"

    Examples: 
      | BaseURL                      | BasePath                             | requestBody                                              | method | expectedStatus | expectedSchemaBody                                              | expectedBody                                              |
      | http://dev.sync.accesshub.co | /api/v1/semify/campaigns/links-built | \\data\\webApi.SEM.linksBuilt\\addLinkReq_TS05_TC01.json | POST   |            200 | \\data\\webApi.SEM.linksBuilt\\addLinkRespSchema_TS05_TC01.json | \\data\\webApi.SEM.linksBuilt\\addLinkResp_TS05_TC01.json |
      | http://dev.sync.accesshub.co | /api/v1/semify/campaigns/links-built | \\data\\webApi.SEM.linksBuilt\\addLinkReq_TS05_TC02.json | POST   |            200 | \\data\\webApi.SEM.linksBuilt\\addLinkRespSchema_TS05_TC01.json | \\data\\webApi.SEM.linksBuilt\\addLinkResp_TS05_TC01.json |

  @addLink422
  Scenario Outline: addLink - 422 - /api/v1/semify/campaigns/links-built
    Given I set a request
    And I set the baseURL to "<BaseURL>"
    And I set the basePath to "<BasePath>"
    And I add the "<requestBody>" to the request
    And I build a "<method>" request
    Then I validate response status against "<expectedStatus>"
    And I validate response body against raw "<expectedBody>"
    And I validate response body against schema "<expectedSchemaBody>"

    Examples: 
      | BaseURL                      | BasePath                             | requestBody                                              | method | expectedStatus | expectedSchemaBody                                              | expectedBody                                              |
      | http://dev.sync.accesshub.co | /api/v1/semify/campaigns/links-built | \\data\\webApi.SEM.linksBuilt\\addLinkReq_TS06_TC01.json | POST   |            422 | \\data\\webApi.SEM.linksBuilt\\addLinkRespSchema_TS06_TC01.json | \\data\\webApi.SEM.linksBuilt\\addLinkResp_TS06_TC01.json |
      | http://dev.sync.accesshub.co | /api/v1/semify/campaigns/links-built | \\data\\webApi.SEM.linksBuilt\\addLinkReq_TS06_TC02.json | POST   |            422 | \\data\\webApi.SEM.linksBuilt\\addLinkRespSchema_TS06_TC02.json | \\data\\webApi.SEM.linksBuilt\\addLinkResp_TS06_TC02.json |
