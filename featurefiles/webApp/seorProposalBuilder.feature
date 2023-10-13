@SEOR_AgencyTools_ProposalBuilder
Feature: SEOR > Agency Tools > Proposal Builder

  Background: 

  #Given User navigates to "https://account.seoreseller.com/login" using "chrome"
  #And User enter the username as "reuel+01@axadra.com"
  #And User enter the password as "asdasdasd"
  #And User click on the login button
  #Then User is successfully login
  #When User navigates to "https://account.seoreseller.com/pro/audits"
  #And User generates a WebAuditReport
  
  
  @CreateAProposalFromExistingContact
  Scenario Outline: Create a Proposal from existing Contact
    Given User navigates to "<loginUrl>" using "<browser>"
    And User enter the username as "reuel@axadra.com"
    And User enter the password as "asdasdasd"
    And User click on the login button
    Then User is successfully login
    When User navigates to "<proposalBuilderURL>"
    And User click on the createAProposal button
    And User add a title to the Proposal
    And User add an existing contact to the Proposal
    And User add a "<productName>" section from "<productCategory>" to the Proposal
    Then User see "<productName>" section is added to the Proposal
    When User click on the saveProposal button
    Then User see the proposal is created

    Examples: 
      | browser | loginUrl                              | proposalBuilderURL                            | productCategory | productName            |
      | chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/pro/proposals | SEO Services    | Link Clean Up - Medium |
