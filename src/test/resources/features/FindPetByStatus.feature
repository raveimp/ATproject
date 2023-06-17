# encoding: UTF-8
# language: en

Feature: Проверка запроса списка питомцев по статусу в магазине
  @GetAvailablePets @All
  Scenario: получение списка доступных в магазине питомцев
    And получаем список питомцев в статусе "available"

  @GetPendingPets @All
  Scenario: получение списка питомцев ожидающих поступления
    And получаем список питомцев в статусе "pending"

  @GetSoldPets @All
  Scenario: получение списка проданных питомцев
    And получаем список питомцев в статусе "sold"

  @GetAllPets @All
  Scenario: получение списка всех питомцев в магазине
    And получаем список питомцев в статусе "available, pending, sold"

  @Negative @GetPetsUnknownStatus @All
  Scenario: получение списка питомцев в статусе отличном от доступных
    And получаем список питомцев в статусе "unknown"