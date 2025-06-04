# Environment Variables

## PostgreSQL (Local)
| Variable             | Description                             | Example       |
|----------------------|-----------------------------------------|---------------|
| POSTGRES_DB          | Name of the local database              | localydb      |
| POSTGRES_USER        | Database user                           | localyuser    |
| POSTGRES_PASSWORD    | Password for the user                   | localysecret  |

## PostgreSQL (Auth)
| Variable             | Description                             | Example       |
|----------------------|-----------------------------------------|---------------|
| POSTGRES_DB          | Name of the auth database               | authdb        |
| POSTGRES_USER        | Database user for auth                  | authuser      |
| POSTGRES_PASSWORD    | Password for the auth user              | authsecret    |

## Localy Service
| Variable                   | Description                                          | Example                                                 |
|----------------------------|------------------------------------------------------|---------------------------------------------------------|
| SPRING_APPLICATION_NAME    | Name of the Spring Boot application                  | Localy                                                  |
| SPRING_DATASOURCE_URL      | JDBC URL to the Localy database                      | `jdbc:postgresql://localy_postgres:5432/localydb`       |
| SPRING_DATASOURCE_USERNAME | Database user                                        | localyuser                                              |
| SPRING_DATASOURCE_PASSWORD | Database password                                    | localysecret                                            |
| SPRING_FLYWAY_ENABLED      | Enable Flyway migrations (true/false)                | true                                                    |
| JWT_SECRET                 | Secret key for JWT signing                           | `<secure secret>`                                       |
| JWT_EXPIRATION_MILLIS      | Token expiration in milliseconds                     | 3600000                                                 |
| MQTT_BROKER_URL            | URL of the MQTT broker                               | `tcp://mqtt_broker:1883`                                |
| MQTT_CLIENT_ID             | Client ID (random)                                   | localy-client-123                                       |
| MQTT_TOPIC_USER_UPDATE     | Topic for user updates                               | user/update                                             |
| MQTT_TOPIC_USER_INFO       | Topic for user information                           | user/info                                               |

## Authentication Service
| Variable                       | Description                                          | Example                                                       |
|--------------------------------|------------------------------------------------------|---------------------------------------------------------------|
| SPRING_APPLICATION_NAME        | Name of the Spring Boot application                  | AuthService                                                   |
| SPRING_DATASOURCE_URL          | JDBC URL to the auth database                        | `jdbc:postgresql://auth_postgres:5432/authdb`                 |
| SPRING_DATASOURCE_USERNAME     | Database user for auth                               | authuser                                                      |
| SPRING_DATASOURCE_PASSWORD     | Database password for auth                           | authsecret                                                    |
| SPRING_FLYWAY_ENABLED          | Enable Flyway migrations (true/false)                | true                                                          |
| SPRING_FLYWAY_LOCATIONS        | Paths to Flyway migrations                           | `classpath:db/migration`                                      |
| SERVER_PORT                    | Port on which the service runs                       | 8090                                                          |
| SPRING_SECURITY_USER_NAME      | Internal Spring Security username (unused)           | unused                                                        |
| SPRING_SECURITY_USER_PASSWORD  | Internal Spring Security password (unused)           | unused                                                        |
| JWT_SECRET                     | Secret key for JWT signing                           | `<secure secret>`                                             |
| JWT_EXPIRATION_MILLIS          | Token expiration in milliseconds                     | 3600000                                                       |
| MQTT_BROKER_URL                | URL of the MQTT broker                               | `tcp://mqtt_broker:1883`                                      |
| MQTT_CLIENT_ID                 | Client ID (random)                                   | auth-service-456                                              |
| MQTT_TOPIC_USER_UPDATE         | Topic for user updates                               | user/update                                                   |
