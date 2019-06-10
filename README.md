# Rmm Services 

Store devices and services for a customer and calculates monthly cost operations.

Basic Authentication:

    user: admin
    password: password

Swagger UI : http://localhost:8080/swagger-ui.html
## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project.

### Prerequisites

- Java 11
- Lombok
- Postgresql (create this database **services** and **services_test**)

### Installing

**Note:** Before compilation, you might configure your IDE with Lombok plugin

Compilation

```
./gradlew build
```

Running locally

```
./gradlew bootRun
```

**Note:** DB migrations will be run using flywaydb project and will be executed when the project start


## Running the test cases

Run the test cases execute

```
./gradlew test
```

## Deployment

Compile the project and then:

```
java -jar build/libs/rmm-services-0.0.1-SNAPSHOT.jar
```

## Customization

For customization you can add environment variables according to:

| Name  | Description  | Default  |
|--------|-------------|----------|
| PG_HOST | Postgres Hostname | `localhost` |
| PG_DATABASE | Postgres Database Name | production: `services` test: `services_test` |
| PG_USERNAME | Postgres username | `postgres` |
| PG_PASSWORD | Postgres Password | `postgres` |





## Build With

- SpringBoot
- Spring Framework (Core, Data, MVC, JPA)
- Lombok
- Flywaydb (Manage db migrations)
- JUnit 5 Jupiter
- Mockito
- Gradle

## Author

- Daniel Ron - [edrb-profile](https://edrb.github.io)
