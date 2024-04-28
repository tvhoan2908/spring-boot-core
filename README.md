## Descriptions
- Project Spring Boot for RnD API

## Requirements
- Spring boot 3
- Postgresql
- Liquibase

## Getting Started
```sh
$ Copy application-local.properties.example to application-local.properties
$ ./mvnw spring-boot:run -Plocal
```

## Run migrations
```sh
$ Update file resources/liquibase.properties
$ Create new migration in resources/liquibase/migrations
$ Run: ./mvnw liquibase:update
$ Rollback: ./mvnw liquibase:rollback -Dliquibase.rollbackTag=vxxxx
```