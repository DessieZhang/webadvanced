## new feature
## Tags: optional
#
#Feature: Delete repository
#
#  Scenario Outline: delete repo through API
#    Given test description: repo "<caseNo>" "<caseName>"
#    When send <method> request to <url> with <requestParams> <requestBodyFileName>
#    Then the response http code equals to <httpCode>
#    And the fields in response equal to <validations>
#    Examples:
#      |module|caseNo|caseName|url|method|requestParams|requestBodyFileName|httpCode|responseFileName|validations|variables|
#      |repo|001|delete repository|https://api.github.com/repos/${owner}/${repo.name}|DELETE|||204||||
#
#  Scenario: Verify deleted repo on github website
#    Given I click Your repositories button
#    When I search repository name
#      | name             |
#      | ${repo.name}          |
#    Then I see 0 result in search summary
#
