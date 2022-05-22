# spring-boot-sample-mybatis-plus

了解一个框架，需要了解以下内容：
- 怎么加载数据库配置
- 怎么支持springboot集成
- 怎么支持mapper interface、xml
- 怎么支持注解SQL
- 怎么支持Java SQL


## 技术点

[基于官方sample](https://github.com/baomidou/dynamic-datasource-spring-boot-starter)，解决了包依赖的问题。

### 1. Spring Boot
### 2. Mybatis-plus
### 3. Mysql
### 4. 动态数据源 

​	- 主从数据库，实现读写分离

    
### 5.api

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