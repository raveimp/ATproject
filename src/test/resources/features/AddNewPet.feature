Feature: Добавление нового питомца в магазин и запрос данных добавленного питомца
  @AddNewPet @Debug
  Scenario: добавление нового питомца в магазин и проверка результата
    And добавляем нового питомца
      | placeholder | value        |
      | status      | available    |
      | id          | GENERATE: id |
    And получаем питомца по идентификатору
      | placeholder | value     |
      | id          | SAVED: id |