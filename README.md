Вот аккуратная, структурированная и “показываемая ментору” финальная версия README.md.
Можешь просто заменить текущий файл этим содержимым.

---

```markdown
# gRPC CRUD Project

Учебный микросервисный проект, демонстрирующий CRUD-операции,
взаимодействие сервисов через **gRPC** и обмен событиями через **Apache Kafka**.

---

## 🧩 Описание проекта

Проект демонстрирует построение распределённой системы на базе:

- Spring Boot 3
- gRPC + Protobuf
- Apache Kafka
- PostgreSQL
- Docker Compose

Система состоит из нескольких микросервисов, взаимодействующих:

User ↔ Post ↔ Order ↔ Notification

Цель проекта — реализовать:

- gRPC взаимодействие между сервисами
- Асинхронное событийное взаимодействие через Kafka
- Оркестрацию через Saga
- Запуск всей системы через Docker Compose
- Подготовку инфраструктуры, близкой к production

---

## 🏗️ Структура проекта

```

grpc-crud-project/
├── common-proto/              # Общие .proto контракты и сгенерированные gRPC классы
├── user-service/              # CRUD пользователей (PostgreSQL + gRPC)
├── post-service/              # CRUD постов + резервирование
├── order-service/             # Оркестратор (gRPC + Kafka)
├── notification-service/      # Kafka consumer
├── docker/                    # Docker Compose, .env, вспомогательные скрипты
└── README.md

````

---

## ⚙️ Технологический стек

| Компонент | Технология |
|------------|-------------|
| Язык | Java 21 |
| Фреймворк | Spring Boot 3.5.x |
| RPC | gRPC |
| Контракты | Protobuf |
| gRPC Starter | net.devh grpc-spring-boot-starter |
| Сообщения | Apache Kafka |
| База данных | PostgreSQL + Flyway |
| Сборка | Gradle (Groovy DSL) |
| Контейнеризация | Docker Compose |
| Тестирование | JUnit 5, Testcontainers |
| Мониторинг | Spring Boot Actuator |

---

## 🚀 Сборка проекта

```bash
git clone https://github.com/<your-username>/grpc-crud-project.git
cd grpc-crud-project
./gradlew clean build
````

---

## 🚀 Запуск всех сервисов (Docker)

```bash
docker compose up --build
```

---

# 🧪 Локальная разработка

Каждый сервис можно запускать отдельно.

---

## ▶ Пример: user-service

```bash
cd user-service
./gradlew bootRun
```

### Порты

* gRPC: **9090**
* REST (если включён): **8081**

---

## 🧪 gRPC Smoke Test (UserService)

### 1️⃣ Запуск

```bash
./gradlew :user-service:bootRun --args='--spring.profiles.active=grpc-local'
```

### 2️⃣ Проверка доступных сервисов

```bash
grpcurl -plaintext localhost:9090 list
```

Ожидается:

```
grpc.health.v1.Health
grpc.reflection.v1alpha.ServerReflection
ru.itwizardry.grpc.user.UserService
```

### 3️⃣ Успешный вызов

```bash
grpcurl -plaintext -d '{"id":1}' \
localhost:9090 \
ru.itwizardry.grpc.user.UserService/GetUser
```

Ответ:

```json
{
  "id": "1",
  "username": "mikhail",
  "email": "mikhail@example.com"
}
```

### 4️⃣ Негативные сценарии

```bash
# NOT_FOUND
grpcurl -plaintext -d '{"id":2}' \
localhost:9090 \
ru.itwizardry.grpc.user.UserService/GetUser

# INVALID_ARGUMENT
grpcurl -plaintext -d '{"id":0}' \
localhost:9090 \
ru.itwizardry.grpc.user.UserService/GetUser
```

---

## 📦 Порты сервисов

| Сервис               | REST | gRPC | PostgreSQL | Kafka |
| -------------------- | ---- | ---- | ---------- | ----- |
| user-service         | 8081 | 9090 | 5433       | –     |
| post-service         | 8082 | 9091 | 5434       | –     |
| order-service        | 8083 | 9092 | 5435       | 9094  |
| notification-service | 8084 | –    | –          | 9094  |

---

## 📊 Текущий прогресс

| Модуль               | Статус                 | Описание                         |
| -------------------- | ---------------------- | -------------------------------- |
| common-proto         | 🟢 готов               | gRPC контракты User/Post         |
| user-service         | 🟡 gRPC API реализован | Метод GetUser + обработка ошибок |
| post-service         | ⚪ в планах             | CRUD + резервирование            |
| order-service        | ⚪ в планах             | Оркестрация + Kafka producer     |
| notification-service | ⚪ в планах             | Kafka consumer                   |

---

## 📋 Roadmap

* [x] Структура проекта
* [x] common-proto и генерация gRPC стаба
* [x] gRPC сервер в user-service
* [x] Реализация unary метода GetUser
* [ ] CRUD для user-service
* [ ] CRUD + резерв для post-service
* [ ] Оркестрация через OrderService
* [ ] События Kafka
* [ ] Docker Compose стек
* [ ] Интеграционные тесты (Testcontainers)
* [ ] Метрики и наблюдаемость

---

## 📚 Полезные ссылки

* [https://spring.io/projects/spring-boot](https://spring.io/projects/spring-boot)
* [https://grpc.io/docs/languages/java/](https://grpc.io/docs/languages/java/)
* [https://spring.io/projects/spring-kafka](https://spring.io/projects/spring-kafka)
* [https://testcontainers.com/](https://testcontainers.com/)
* [https://microservices.io/patterns/data/saga.html](https://microservices.io/patterns/data/saga.html)

---

## 🧠 Примечание

Проект создаётся в учебных целях для закрепления:

* микросервисной архитектуры
* gRPC
* Kafka
* оркестрации (Saga)
* инфраструктуры и DevOps-подхода

Дальнейшие шаги — развитие бизнес-логики и усложнение взаимодействия сервисов.

```
