Feature: Проверка запроса питомцев по идентификатору
  @GetPetsById @Debug
  Scenario: получение питомца по идентификатору
    And получаем питомца по идентификатору
      | placeholder | value     |
      | id          | SAVED: id |