Feature: Обновление существующего питомца
  @UpdateExistPet @Debug
  Scenario: обновление данных существующего питомца и проверка результата
    And получаем список питомцев в статусе "available, pending, sold"
    And обновляем данные питомца и статус
      | placeholder | value     |
      | status      | sold      |
      | id          | EXIST: id |
    And получаем питомца по идентификатору
      | placeholder | value       |
      | id          | ALTERED: id |