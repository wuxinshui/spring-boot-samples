# spring-boot-sample-data-mongo
## 技术点
### 1. Spring Boot
### 2. spring-boot-starter-data-mongodb

## command


`docker pull mongo`

`docker run --name test-mongo -p 27017:27017 -d mongo:latest`


`docker pull mongo-express`


```docker run --link test-mongo:mongo -p 8081:8081 mongo-express```

