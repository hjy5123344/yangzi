# AGENTS.md

## Cursor Cloud specific instructions

This is a Spring Cloud microservices project ("yyy/yangzi") demonstrating reliable message delivery using RabbitMQ. It uses Maven, JDK 8, Spring Boot 2.1.8, and Spring Cloud Greenwich.SR2.

### Architecture

| Service | Port | Description |
|---|---|---|
| yyy-eureka-server | 8761 | Service registry (start first) |
| yyy-producer | 8763 | Creates orders, sends to RabbitMQ |
| yyy-consumer | 8764 | Receives and ACKs order messages |
| yyy-eureka-client | 8762 | Optional demo/utility module |

### Infrastructure dependencies (Docker)

Three containers are required: MySQL 5.7 (port 3306), RabbitMQ 3 (port 5672), Redis 7 (port 6379). Start them before running any services:

```bash
sudo dockerd > /tmp/dockerd.log 2>&1 &
sleep 3
sudo docker start mysql rabbitmq redis
```

If containers don't exist yet, create them:

```bash
sudo docker run -d --name mysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=test -p 3306:3306 mysql:5.7
sudo docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
sudo docker run -d --name redis -p 6379:6379 redis:7 --requirepass 123456
```

MySQL tables required (`test` database):
- `t_order` (id VARCHAR(128) PK, name VARCHAR(128), message_id VARCHAR(128))
- `broker_message_log` (message_id VARCHAR(128) PK, message TEXT, try_count INT, status VARCHAR(10), next_retry BIGINT, create_time BIGINT, update_time BIGINT)

### Key gotchas

- The `common` module is a library (no main class). Its `pom.xml` must have `spring-boot-maven-plugin` with `<skip>true</skip>` to avoid repackage errors.
- The `broker_message_log` table must use `BIGINT` for timestamp columns (not DATETIME), because the Java entity stores epoch millis as `Long`.
- JDK 8 is required. Spring Boot 2.1.x is not compatible with JDK 21. Use `JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64`.
- The `testCreateOrder` test in `yyy-producer` uses a hardcoded order ID (`2018080400000005`). Clean the `t_order` table before re-running.

### Build & Test

```bash
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
mvn clean install -DskipTests
mvn test -pl yyy-producer -Dtest=YyyProducerApplicationTests#testCreateOrder
```

### Running services

Start in order: Eureka Server -> Producer -> Consumer.

```bash
java -jar yyy-eureka-server/target/yyy-eureka-server-0.0.1-SNAPSHOT.jar &
# Wait for port 8761
java -jar yyy-producer/target/yyy-producer-0.0.1-SNAPSHOT.jar &
java -jar yyy-consumer/target/yyy-consumer-0.0.1-SNAPSHOT.jar &
```
