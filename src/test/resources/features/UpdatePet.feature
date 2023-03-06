Feature: Добавление нового питомца в магазин и обновляем его данные
  @UpdatePet @Debug
  Scenario: добавление нового питомца в магазин, обновление данных питомца и проверка результата
    And добавляем нового питомца в статусе "available"
      | placeholder | value        |
      | status      | available    |
      | id          | GENERATE: id |
    And получаем питомца по "id"
      | placeholder | value     |
      | id          | SAVED: id |
    And обновляем данные питомца и статус на "sold" по "id"
      | placeholder | value     |
      | status      | sold      |
      | id          | SAVED: id |
    And получаем питомца по "id"
      | placeholder | value     |
      | id          | SAVED: id |