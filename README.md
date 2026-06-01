# Performance Testing: REST vs gRPC

Comparative performance analysis of REST (HTTP/1.1) and gRPC (HTTP/2) communication protocols.

## Technologies
- Java 17, Spring Boot 3.5.14, Maven
- Grafana k6 (load testing)
- JSON (REST) / Protocol Buffers (gRPC)

## Project Structure
- `rest-service/` - Spring Boot REST API (port 8080)
- `grpc-service/` - Spring Boot gRPC service (port 9090)
- `k6-tests/` - k6 load testing scripts

## Running the services
Start REST service:
```
cd rest-service
./mvnw spring-boot:run
```
Start gRPC service:
```
cd grpc-service
./mvnw spring-boot:run
```

## Running the tests
```
cd k6-tests
k6 run baseline.js
k6 run concurrency.js
k6 run payload.js
k6 run baseline_grpc.js
k6 run concurrency_grpc.js
k6 run payload_grpc.js
```

## Test Scenarios
- **Baseline** - 1 VU, 100 iterations
- **Concurrency** - up to 1000 VUs, 2 minutes
- **Payload** - 10 VUs, small/medium/large data sizes
