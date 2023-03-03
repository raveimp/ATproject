Feature: Проверка запроса питомцев по уникальному идентификатору
  @GetPetsById @Debug
  Scenario: получение питомца по уникальному идентификатору
    And получаем питомца по "id"
      | placeholder | value        |
      | status      | GENERATE: id |