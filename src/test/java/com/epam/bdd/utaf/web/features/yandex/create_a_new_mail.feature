Feature: Create a new mail test
  Description: The purpose of this feature is to test the mail creation function(save as draft, send email)

  Background:
    Given I navigate to the yandex mail home page
    When I click Log in button to open login page
    And I login yandex mail as existing user
      | ID                 | Password             |
      | dessie.zhang16     |QmFpZHUwMDEh          |

  @positive
  Scenario:  Create a new email and save as a draft
    Given I open the new mail page
    When I new a email with recipient,subject, body fields filled
    And I close the new email
    And I open Drafts mail folder
    And I should see the mail in "Drafts" folder
    Then I open this draft mail
    And I see the draft content is correct
    And I send the email
    Then I see the mail disappeared from ‘Drafts’ folder
    And I see the mail is in ‘Sent’ folder
    And I log off Yandex

