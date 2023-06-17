# encoding: UTF-8
# language: en

Feature: Добавление нового питомца в магазин и обновляем его данные
  @UpdatePet @All
  Scenario: добавление нового питомца в магазин, обновление данных питомца и проверка результата
    And добавляем нового питомца
      | placeholder | value        |
      | status      | available    |
      | id          | GENERATE: id |
    And получаем питомца по идентификатору
      | placeholder | value     |
      | id          | SAVED: id |
    And обновляем данные питомца и статус
      | placeholder | value     |
      | status      | sold      |
      | id          | SAVED: id |
    And получаем питомца по идентификатору
      | placeholder | value     |
      | id          | SAVED: id |
    And проверяем результат