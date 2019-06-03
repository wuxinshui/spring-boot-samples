# spring-boot-sample-flyway
## 工程所用技术点

[参考](http://blog.didispace.com/spring-boot-flyway-db-version/)

[官网](https://flywaydb.org/)

### 1. Spring Boot
### 2. Mybatis-plus
### 3. Mysql
### 4. flyway 数据库版本管理
### 5.ehcache [参考](https://raychase.iteye.com/blog/1545906)
### 6.guava
### 7.@Cacheable
### 8.Caffeine [深入解密来自未来的缓存-Caffeine](https://juejin.im/post/5b8df63c6fb9a019e04ebaf4)

path:localhost:8080/mp/users/1
response:
```$java
{
    "id": 1,
    "name": "Hi",
    "age": 12
}
```
path:localhost:8080/mp/users/add
 request:
 ```$java
{
	"name":"mp1",
	"age":26
}
 ```
````java

````