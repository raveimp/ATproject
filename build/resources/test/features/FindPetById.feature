# encoding: UTF-8
# language: en

Feature: Проверка запроса питомцев по идентификатору
  @GetPetsById @All
  Scenario: получение питомца по идентификатору
    And получаем питомца по идентификатору
      | placeholder | value     |
      | id          | SAVED: id |
    And проверяем результат запроса