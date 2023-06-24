# encoding: UTF-8
# language: en

Feature: Обновление существующего питомца
  @UpdateExistPet @All
  Scenario: обновление данных существующего питомца и проверка результата
    And получаем список питомцев в статусе "available"
    And обновляем данные питомца и статус
      | placeholder | value     |
      | status      | sold      |
      | id          | EXIST: id |
    And получаем питомца по идентификатору
      | placeholder | value     |
      | id          | SAVED: id |