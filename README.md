
### Hexlet tests and linter status:

[![hexlet-check](https://github.com/AlenaMende/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/AlenaMende/java-project-78/actions)
[![Java CI](https://github.com/AlenaMende/java-project-78/actions/workflows/main.yml/badge.svg)](https://github.com/AlenaMende/java-project-78/actions/workflows/main.yml)
[![Quality gate status](https://sonarcloud.io/api/project_badges/measure?project=AlenaMende_java-project-78&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=AlenaMende_java-project-78)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=AlenaMende_java-project-78&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=AlenaMende_java-project-78)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=AlenaMende_java-project-78&metric=coverage)](https://sonarcloud.io/summary/new_code?id=AlenaMende_java-project-78)

### Data Validator 
A simple Java data validation library for creating schemas and validating different types of data.


## Features 

- String validation
- Number validation
- Map validation


## Technologies

- Java
- Gradle
- JUnit

## Installation

<!-- Опишите установку: клонирование, зависимости, переменные окружения -->

```bash
git clone https://github.com/AlenaMende/java-project-78.git
cd java-project-78
```

## Usage

<!-- Добавьте примеры запуска и запись asciinema — именно это смотрит работодатель -->
```bash
Validator v = new Validator();
 
var schema = v.string() 
              .required() 
              .minLength(3); 

schema.isValid("Hello"); // true 
schema.isValid("Hi"); // false
```

## Testing

Run the test with:

```bash
./gradlew  test
```

## About Hexlet

[Хекслет](https://ru.hexlet.io/) — A programming school offering proprietary training programs that include hands-on practice, mentor support, and real-world projects you can add to your resume. This repository is one such project.
