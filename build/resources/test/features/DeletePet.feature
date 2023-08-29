# encoding: UTF-8
# language: en

Feature: Добавление и удаление питомца
  @DeletePet @All
  Scenario: добавление нового питомца в магазин, удаление данных питомца и проверка результата
    And добавляем нового питомца
      | placeholder | value        |
      | status      | pending      |
      | id          | GENERATE: id |
    And получаем питомца по идентификатору
      | placeholder | value     |
      | id          | SAVED: id |
    And удаляем данные питомца
      | placeholder | value     |
      | id          | SAVED: id |
    And получаем питомца по идентификатору
      | placeholder | value     |
      | id          | SAVED: id |
    And проверяем результат удаления