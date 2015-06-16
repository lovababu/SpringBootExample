Feature: Integration Tests for Project service.
  Scenario: Project create and then Get.
    Given Create Project with the below data
      |ID       |NAME     |DESCRIPTION |TEAMSIZE |PLATFORM|
      |PID100   |SomeName |Some Desc   |10       |JAVA    |
    And Project create success
    When Get the created project information PID100
    Then Get succeeded