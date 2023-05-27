Feature: Проверка запроса списка питомцев по статусу в магазине
  @GetAvailablePets @Debug
  Scenario: получение списка доступных в магазине питомцев
    And получаем список питомцев в статусе "available"

  @GetPendingPets @Debug
  Scenario: получение списка питомцев ожидающих поступления
    And получаем список питомцев в статусе "pending"

  @GetSoldPets @Debug
  Scenario: получение списка проданных питомцев
    And получаем список питомцев в статусе "sold"

  @GetAllPets @Debug
  Scenario: получение списка всех питомцев в магазине
    And получаем список питомцев в статусе "available, pending, sold"

  @Negative @GetPetsUnknownStatus @Debug
  Scenario: получение списка питомцев в статусе отличном от доступных
    And получаем список питомцев в статусе "unknown"