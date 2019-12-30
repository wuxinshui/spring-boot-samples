# spring-boot-sample-data-mongo
## 技术点
### 1. Spring Boot
### 2. spring-boot-starter-data-mongodb

## command

### 下载mongo镜像

`docker pull mongo`

### 启动mongo

`docker run --name test-mongo -p 27017:27017 -d mongo:latest`

### 下载mongo-express镜像
`docker pull mongo-express`

### 启动mongo-express

`docker run --link test-mongo:mongo -p 8081:8081 mongo-express`

### web访问

```java
http://localhost:9091/customer/add


{
  "id": "1",
  "firstName": "Wuxinshui",
  "lastName": "wuxinshui"
}

http://localhost:9091/customer/query?name=Wuxinshui



```

