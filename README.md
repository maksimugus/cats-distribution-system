# Система распределения котиков

---

Микросервисное Spring Boot приложение для управления котиками и их владельцами.

## Используемые фреймворки

---

- JPA
- Spring Boot
  - Web
  - Security
  - Validation
- Spring Kafka

## Окружение

---

- PostgreSQL - для хранения информации о котиках и хозяевах

- Apache Kafka - для обеспечения отказоустойчивости

- Docker - для запуска PostgreSQL и Apache Kafka

Сборка приложения и управление зависимостями осуществляется с помощью Maven.