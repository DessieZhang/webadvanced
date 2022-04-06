# new feature
# Tags: optional

Feature: Create new repository

  Scenario: Login github website
    Given I navigate to GitHub website
    When I click Sign in button
    And I input account and click sign in button
      | email               | password             |
      | swlxm2002@gmail.com |U3V6aG91XzEh          |
    Then I see the organization home page

  Scenario: Create new repo
    Given I click New button
    When I give repository name
      | name             |
      | _Random          |
    And I give repository description
      | description                           |
      | This is a temp repo for demonstration |
    And I select repository permission
      | permission      |
      | Public         |
    And I click Create button
    Then I see the new repository

#  Scenario Outline: verify repo through API
#    Given test description: repo "<caseNo>" "<caseName>"
#    When send <method> request to <url> with <requestParams> <requestBodyFileName>
#    Then the response http code equals to <httpCode>
#    And the fields in response equal to <validations>
#    Examples:
#      |module|caseNo|caseName|url|method|requestParams|requestBodyFileName|httpCode|responseFileName|validations|variables|
#      |repo|001|get repository|https://api.github.com/repos/${owner}/${repo.name}|GET|||200||{'name':'${repo.name}'}||
