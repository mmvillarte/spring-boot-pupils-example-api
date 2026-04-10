# Pupil Courses Microservice

- Microservice developed con **Spring Boot 4.x** and **Java 21**
- The main purpose of this microservice is to provide an API to get pupils courses information, which can be consumed by the angular-pupils-example frontend application
- Another purpose is loading data into the database using flyway plugin, which is a database migration tool that allows to manage and version control the database schema and data
- Expected to be called from angular-pupils-example frontend application, but can be used as standalone microservice to get pupils courses information

---

## 🔖 Badges

[![Build Status](https://img.shields.io/badge/build-passing-brightgreen)](#)
[![Java](https://img.shields.io/badge/Java-21-blue)](#)
[![Spring Boot](https://img.shields.io/badge/SpringBoot-4.x-green)](#)

> ⚠️ Now it is locally analyzed using **SonarLint**

---

## 🏗️ Architecture

### Business Flow (High-Level)

#### Get Pupil Courses (all and filtered)
[[Pupil Courses](./docs/architecture/pupil-courses-angular-schema.svg)](./docs/architecture/pupil-courses-angular-schema.svg)

## 🚀 Ejecución

### Requisitos

- Java 21
- Maven 3.9+
- IDE with SonarLint (opcional)

### Ejecutar aplicación

```bash
# Usando Maven
mvn spring-boot:run

# Usando aplication class
mvn compile exec:java -Dexec.mainClass=org.pupils.example.PupilsApiApplication

# Ejecutar tests
mvn test
```

## 📡 Main Endpoints

Swagger is available at http://localhost:8080/swagger-ui/index.html

| Método | Endpoint                                    | Descripción        | Response                                                                                                                                                                                                                                                                                                                                                         |
|--------|---------------------------------------------|--------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| GET    | /api/courses/all                            | Get all courses    | `[{"courseName":"Java Fundamentals","courseStatus":"IN_PROGRESS","pupil":"Doe, John"},{"courseName":"Spring Boot Essentials","courseStatus":"COMPLETED","pupil":"Doe, John"},{"courseName":"Java Fundamentals","courseStatus":"NOT_STARTED","pupil":"Johnson, Jane"},{"courseName":"PostgreSQL Basics","courseStatus":"IN_PROGRESS","pupil":"Jackson, Howard"}]` |
| GET    | /api/courses?pupilName=john&courseName=java | Actualizar usuario | `[{"courseName":"Java Fundamentals","courseStatus":"IN_PROGRESS","pupil":"Doe, John"}]`                                                                                                                                                                                                                                                                          |
