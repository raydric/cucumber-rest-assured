Feature: REST API testing using RestAssured
  Demo scenarios

  Scenario: GET list of TODOs
    Given Todo rest endpoint exists
    When I get list of todos
    Then Response status code should be 200
    And Response contains list of todos

  Scenario Outline: Get single TODO by ID
    Given Todo rest endpoint exists
    When I get todo by id <id>
    Then Response status code should be 200
    And Response contains todo with id <id>

    Examples:
      | id |
      | 1  |
      | 10 |

  Scenario: Create TODO
    Given Todo rest endpoint exists
    When I create todo
    Then Response status code should be 201
    And Response contains created todo

  Scenario: Delete TODO
    Given Todo rest endpoint exists
    When I delete todo with id 1
    Then Response status code should be 200

  Scenario: Patch TODO
    Given Todo rest endpoint exists
    When I change todo title with id 1 to Title
    Then Response status code should be 200

  Scenario: Put TODO
    Given Todo rest endpoint exists
    When I replace todo with id 1 with another
    Then Response status code should be 200