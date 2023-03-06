Feature: Добавление и удаление питомца
  @DeletePet @Debug
  Scenario: добавление нового питомца в магазин, удаление данных питомца и проверка результата
    And добавляем нового питомца в статусе "pending"
      | placeholder | value        |
      | status      | pending    |
      | id          | GENERATE: id |
    And получаем питомца по "id"
      | placeholder | value     |
      | id          | SAVED: id |
    And удаляем данные питомца по "id"
      | placeholder | value     |
      | id          | SAVED: id |
    And получаем питомца по "id"
      | placeholder | value     |
      | id          | SAVED: id |