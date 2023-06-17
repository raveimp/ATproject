# encoding: UTF-8
# language: en

Feature: Удаление существующего питомца
  @DeleteExistPet @All
  Scenario: удаление данных существующего питомца и проверка результата
    And получаем список питомцев в статусе "available, pending, sold"
    And удаляем данные питомца
      | placeholder | value     |
      | id          | EXIST: id |
    And получаем питомца по идентификатору
      | placeholder | value       |
      | id          | ALTERED: id |