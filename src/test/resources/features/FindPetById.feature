Feature: Проверка запроса питомцев по идентификатору
  @GetPetsById @Debug
  Scenario: получение питомца по идентификатору
    And получаем питомца по "id"
      | placeholder | value     |
      | id          | SAVED: id |