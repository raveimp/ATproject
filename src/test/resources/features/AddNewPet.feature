# encoding: UTF-8
# language: en

Feature: Добавление нового питомца в магазин и запрос данных добавленного питомца
  @AddNewPet @All
  Scenario: добавление нового питомца в магазин и проверка результата
    And добавляем нового питомца
      | placeholder | value        |
      | status      | available    |
      | id          | GENERATE: id |
    And получаем питомца по идентификатору
      | placeholder | value     |
      | id          | SAVED: id |
    And сравниваем запрос и ответ