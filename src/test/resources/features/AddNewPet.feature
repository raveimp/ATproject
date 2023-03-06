Feature: Добавление нового питомца в магазин и запрос данных добавленного питомца
  @AddNewPet @Debug
  Scenario: добавление нового питомца в магазин и проверка результата
    And добавляем нового питомца в статусе "available"
      | placeholder | value        |
      | status      | available    |
      | id          | GENERATE: id |
    And получаем питомца по "id"
      | placeholder | value     |
      | id          | SAVED: id |