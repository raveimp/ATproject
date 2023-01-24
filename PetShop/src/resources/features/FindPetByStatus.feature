Feature: Проверка запроса списка питомцев по статусу в магазине
  @GetAvailablePets
  Scenario: получение списка доступных в магазине питомцев
    And получаем список питомцев в статусе "available"
      | placeholder | value     |
      | status      | available |

  @GetPendingPets
  Scenario: получение списка питомцев ожидающих поступления
    And получаем список питомцев в статусе "pending"
      | placeholder | value   |
      | status      | pending |

  @GetSoldPets
  Scenario: получение списка проданных питомцев
    And получаем список питомцев в статусе "sold"
      | placeholder | value |
      | status      | sold  |

  @GetAllPets
  Scenario: получение списка всех питомцев в магазине
    And получаем список питомцев в статусе "available"
      | placeholder | value     |
      | status      | available |
    And получаем список питомцев в статусе "pending"
      | placeholder | value   |
      | status      | pending |
    And получаем список питомцев в статусе "sold"
      | placeholder | value |
      | status      | sold  |

  @Negative @GetPetsUnknownStatus
  Scenario: получение списка питомцев в недоступном статусе
    And получаем список питомцев в статусе отличном от доступных
      | placeholder | value            |
      | status      | GENERATE: status |