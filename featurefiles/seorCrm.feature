@SEOR_AgencyTools_Crm
Feature: SEOR > Agency Tools > WebAudit

  Background: 

  #Given User navigates to "https://account.seoreseller.com/login" using "chrome"
  #And User enter the username as "reuel+01@axadra.com"
  #And User enter the password as "asdasdasd"
  #And User click on the login button
  #Then User is successfully login
  #When User navigates to "https://account.seoreseller.com/pro/audits"
  #And User generates a WebAuditReport
  @DeleteSingleContactOverCRM
  Scenario Outline: Delete Single Contact over CRM
    Given User navigates to "<loginUrl>" using "<browser>"
    And User enter the username as "<username>"
    And User enter the password as "<password>"
    And User click on the login button
    Then User is successfully login
    When User navigates to "<crmURL>"
    And User delete single contact

    Examples: 
      | browser | loginUrl                              | crmURL                                   | username            | password  |
      | chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/pro/crm/ | reuel+01@axadra.com | asdasdasd |

  @DeleteSingleContactOverCRMContactDetails
  Scenario Outline: Delete Single Contact over CRM > Contact Details
    Given User navigates to "<loginUrl>" using "<browser>"
    And User enter the username as "<username>"
    And User enter the password as "<password>"
    And User click on the login button
    Then User is successfully login
    When User navigates to "<crmURL>"
    And User delete single contact over contact details

    Examples: 
      | browser | loginUrl                              | crmURL                                   | username            | password  |
      | chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/pro/crm/ | reuel+01@axadra.com | asdasdasd |

  @DeleteMultipleContactOverCRM
  Scenario Outline: Delete Multiple Contact over CRM
    Given User navigates to "<loginUrl>" using "<browser>"
    And User enter the username as "<username>"
    And User enter the password as "<password>"
    And User click on the login button
    Then User is successfully login
    When User navigates to "<crmURL>"
    And User delete "<multiple>" contact

    Examples: 
      | browser | loginUrl                              | crmURL                                   | username            | password  | multiple |
      | chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/pro/crm/ | reuel+01@axadra.com | asdasdasd |       10 |
      | chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/pro/crm/ | reuel+01@axadra.com | asdasdasd |       25 |
      | chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/pro/crm/ | reuel+01@axadra.com | asdasdasd |       50 |
      | chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/pro/crm/ | reuel+01@axadra.com | asdasdasd |      100 |

  @AddContactUsingForm
  Scenario Outline: Add Contact using Form
    Given User navigates to "<loginUrl>" using "<browser>"
    And User enter the username as "<username>"
    And User enter the password as "<password>"
    And User click on the login button
    Then User is successfully login
    When User navigates to "<crmURL>"
    And User clicks on the addContact button
    And User populates websiteURL textfield with "<websiteURL>"
    And User populates companyName textfield with "<companyName>"
    And User populates emailAddress textfield with "<emailAddress>"
    And User clicks on the createContact button
    And User clicks on the okaySuccess button
    Then User saves a contact with "<companyName>" "<emailAddress>" "<websiteURL>"

    Examples: 
      | browser | loginUrl                              | crmURL                                   | username            | password  | websiteURL | companyName  | emailAddress   |
      | chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/pro/crm/ | reuel+01@axadra.com | asdasdasd | test.com   | test company | test@gmail.com |

  @AddContactUsingUploadFile
  Scenario Outline: Add Contact using Upload File
    Given User navigates to "<loginUrl>" using "<browser>"
    And User enter the username as "<username>"
    And User enter the password as "<password>"
    And User click on the login button
    Then User is successfully login
    When User navigates to "<crmURL>"
    And User clicks on the addContact button
    And User uploads a file to the uploadFile textArea with "<uploadFile>"
    And User clicks on the proceedSuccess button
    Then User saves a contact from file with "<companyName>"

    Examples: 
      | browser | loginUrl                              | crmURL                                   | username            | password  | uploadFile               | companyName |
      | chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/pro/crm/ | reuel+01@axadra.com | asdasdasd | crm_exportfile_csv.csv   | _csv        |
      | chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/pro/crm/ | reuel+01@axadra.com | asdasdasd | crm_exportfile_txt.txt   | _txt        |
      | chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/pro/crm/ | reuel+01@axadra.com | asdasdasd | crm_exportfile_xls.xls   | _xls        |
      | chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/pro/crm/ | reuel+01@axadra.com | asdasdasd | crm_exportfile_xlsx.xlsx | _xlsx       |

  @UpdateContactWebSiteOverCRMContactDetailsBusinessInfo
  Scenario Outline: Update Contact WebSite over CRM > Contact Details > Business Info
    Given User navigates to "<loginUrl>" using "<browser>"
    And User enter the username as "<username>"
    And User enter the password as "<password>"
    And User click on the login button
    Then User is successfully login
    When User navigates to "<crmURL>"
    And User clicks on the addContact button
    And User populates websiteURL textfield with "<websiteURL>"
    And User populates companyName textfield with "<companyName>"
    And User populates emailAddress textfield with "<emailAddress>"
    And User clicks on the createContact button
    And User clicks on the okaySuccess button
    Then User saves a contact with "<companyName>" "<emailAddress>" "<websiteURL>"
    When User clicks on the contactDetails link
    And User clicks on the editContact button
    And User updates websiteURL textfield with "<websiteURL>"
    And User clicks on the saveChanges button
    Then User confirms "<websiteURL>" is updated successfully

    Examples: 
      | browser | loginUrl                              | crmURL                                   | username         | password  | websiteURL | companyName | emailAddress    |
      | chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/pro/crm/ | reuel@axadra.com | asdasdasd | url.com    | company     | email@gmail.com |

  @UpdateContactEmailAddressOverCRMContactDetailsBusinessInfo
  Scenario Outline: Update Contact Email Address over CRM > Contact Details > Business Info
    Given User navigates to "<loginUrl>" using "<browser>"
    And User enter the username as "<username>"
    And User enter the password as "<password>"
    And User click on the login button
    Then User is successfully login
    When User navigates to "<crmURL>"
    And User clicks on the addContact button
    And User populates websiteURL textfield with "<websiteURL>"
    And User populates companyName textfield with "<companyName>"
    And User populates emailAddress textfield with "<emailAddress>"
    And User clicks on the createContact button
    And User clicks on the okaySuccess button
    Then User saves a contact with "<companyName>" "<emailAddress>" "<websiteURL>"
    When User clicks on the contactDetails link
    And User clicks on the editContact button
    And User updates emailAddress textfield with "<emailAddress>"
    And User clicks on the saveChanges button
    Then User confirms "<emailAddress>" is updated successfully

    Examples: 
      | browser | loginUrl                              | crmURL                                   | username         | password  | websiteURL | companyName | emailAddress    |
      | chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/pro/crm/ | reuel@axadra.com | asdasdasd | url.com    | company     | email@gmail.com |

  @UpdateContactCompanyOverCRMContactDetailsBusinessInfo
  Scenario Outline: Update Contact Company over CRM > Contact Details > Business Info
    Given User navigates to "<loginUrl>" using "<browser>"
    And User enter the username as "<username>"
    And User enter the password as "<password>"
    And User click on the login button
    Then User is successfully login
    When User navigates to "<crmURL>"
    And User clicks on the addContact button
    And User populates websiteURL textfield with "<websiteURL>"
    And User populates companyName textfield with "<companyName>"
    And User populates emailAddress textfield with "<emailAddress>"
    And User clicks on the createContact button
    And User clicks on the okaySuccess button
    Then User saves a contact with "<companyName>" "<emailAddress>" "<websiteURL>"
    When User clicks on the contactDetails link
    And User clicks on the editContact button
    And User updates company textfield with "<companyName>"
    And User clicks on the saveChanges button
    Then User confirms "<companyName>" is updated successfully

    Examples: 
      | browser | loginUrl                              | crmURL                                   | username         | password  | websiteURL | companyName | emailAddress    |
      | chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/pro/crm/ | reuel@axadra.com | asdasdasd | url.com    | company     | email@gmail.com |

  @PopulateContactPhoneOverCRMContactDetailsBusinessInfo
  Scenario Outline: Update Contact Phone over CRM > Contact Details > Business Info
    Given User navigates to "<loginUrl>" using "<browser>"
    And User enter the username as "<username>"
    And User enter the password as "<password>"
    And User click on the login button
    Then User is successfully login
    When User navigates to "<crmURL>"
    And User clicks on the addContact button
    And User populates websiteURL textfield with "<websiteURL>"
    And User populates companyName textfield with "<companyName>"
    And User populates emailAddress textfield with "<emailAddress>"
    And User clicks on the createContact button
    And User clicks on the okaySuccess button
    Then User saves a contact with "<companyName>" "<emailAddress>" "<websiteURL>"
    When User clicks on the contactDetails link
    And User clicks on the editContact button
    And User populates phone textfield with "<phone>"
    And User clicks on the saveChanges button
    Then User confirms "<phone>" is updated successfully

    Examples: 
      | browser | loginUrl                              | crmURL                                   | username         | password  | phone          | websiteURL | companyName | emailAddress    |
      | chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/pro/crm/ | reuel@axadra.com | asdasdasd | (808) 123-4567 | url.com    | company     | email@gmail.com |

  @PopulateContactAddressOverCRMContactDetailsBusinessInfo
  Scenario Outline: Update Contact Address over CRM > Contact Details > Business Info
    Given User navigates to "<loginUrl>" using "<browser>"
    And User enter the username as "<username>"
    And User enter the password as "<password>"
    And User click on the login button
    Then User is successfully login
    When User navigates to "<crmURL>"
    And User clicks on the addContact button
    And User populates websiteURL textfield with "<websiteURL>"
    And User populates companyName textfield with "<companyName>"
    And User populates emailAddress textfield with "<emailAddress>"
    And User clicks on the createContact button
    And User clicks on the okaySuccess button
    Then User saves a contact with "<companyName>" "<emailAddress>" "<websiteURL>"
    When User clicks on the contactDetails link
    And User clicks on the editContact button
    And User populates address textfield with "<address>"
    And User clicks on the saveChanges button
    Then User confirms "<address>" is updated successfully

    Examples: 
      | browser | loginUrl                              | crmURL                                   | username         | password  | address                                                   | websiteURL | companyName | emailAddress    |
      | chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/pro/crm/ | reuel@axadra.com | asdasdasd | 3700 S Western Ave, Los Angeles, CA, 90018, United States | url.com    | company     | email@gmail.com |

  @PopulateContactSocialMediaOverCRMContactDetailsBusinessInfo
  Scenario Outline: Update Contact Social Media over CRM > Contact Details > Business Info
    Given User navigates to "<loginUrl>" using "<browser>"
    And User enter the username as "<username>"
    And User enter the password as "<password>"
    And User click on the login button
    Then User is successfully login
    When User navigates to "<crmURL>"
    And User clicks on the addContact button
    And User populates websiteURL textfield with "<websiteURL>"
    And User populates companyName textfield with "<companyName>"
    And User populates emailAddress textfield with "<emailAddress>"
    And User clicks on the createContact button
    And User clicks on the okaySuccess button
    Then User saves a contact with "<companyName>" "<emailAddress>" "<websiteURL>"
    When User clicks on the contactDetails link
    And User clicks on the editContact button
    And User populates facebook textfield with "<facebook>"
    And User populates instagram textfield with "<instagram>"
    And User populates twitter textfield with "<twitter>"
    And User populates linkedin textfield with "<linkedin>"
    And User clicks on the saveChanges button
    Then User confirms "<facebook>" is updated successfully
    And User confirms "<instagram>" is updated successfully
    And User confirms "<twitter>" is updated successfully
    And User confirms "<linkedin>" is updated successfully

    Examples: 
      | browser | loginUrl                              | crmURL                                   | username         | password  | facebook                                 | instagram                              | twitter                             | linkedin                              | websiteURL | companyName | emailAddress    |
      | chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/pro/crm/ | reuel@axadra.com | asdasdasd | https://www.facebook.com/bestseoreseller | https://www.instagram.com/seoreseller/ | https://twitter.com/bestseoreseller | https://www.linkedin.com/seoreseller/ | url.com    | company     | email@gmail.com |

  @AddContactOverCRMContactDetailsYourContact
  Scenario Outline: Add Contact over CRM > Contact Details > Business Info > Your Contacts
    Given User navigates to "<loginUrl>" using "<browser>"
    And User enter the username as "<username>"
    And User enter the password as "<password>"
    And User click on the login button
    Then User is successfully login
    When User navigates to "<crmURL>"
    And User clicks on the addContact button
    And User populates websiteURL textfield with "<websiteURL>"
    And User populates companyName textfield with "<companyName>"
    And User populates emailAddress textfield with "<emailAddress>"
    And User clicks on the createContact button
    And User clicks on the okaySuccess button
    Then User saves a contact with "<companyName>" "<emailAddress>" "<websiteURL>"
    When User clicks on the contactDetails link
    And User adds a contact over CRMContactDetailsBusinessInfoYourContact

    Examples: 
      | browser | loginUrl                              | crmURL                                   | username         | password  | facebook                                 | instagram                              | twitter                             | linkedin                              | websiteURL | companyName | emailAddress    |
      | chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/pro/crm/ | reuel@axadra.com | asdasdasd | https://www.facebook.com/bestseoreseller | https://www.instagram.com/seoreseller/ | https://twitter.com/bestseoreseller | https://www.linkedin.com/seoreseller/ | url.com    | company     | email@gmail.com |

  
