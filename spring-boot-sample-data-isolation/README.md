# spring-boot-sample-isolation
## 技术点

[参考](https://www.jianshu.com/p/a1d4cce7af53)

### 1. Spring Boot
### 2. Mybatis-plus
### 3. H2
### 4. flyway 数据库版本管理
### 5. 数据隔离方案
 -  @RequestScope
 -  ThreadLocal

### 6.api

## 数据隔离术

### RequestScope

#### 基本应用



设置bean

![requestscope-bean](D:\git\github\spring-boot-samples\spring-boot-sample-data-isolation\src\main\resources\doc\requestscope-bean.png)

过滤器



![Filter](D:\git\github\spring-boot-samples\spring-boot-sample-data-isolation\src\main\resources\doc\Filter.png)



创建接口

![requestscope-random](D:\git\github\spring-boot-samples\spring-boot-sample-data-isolation\src\main\resources\doc\requestscope-random.png)



postman测试，每次返回的id都不一样



![requestscope](D:\git\github\spring-boot-samples\spring-boot-sample-data-isolation\src\main\resources\doc\requestscope.png)

#### 基本原理

### ThreadLocal

#### 基本应用



![inheritableThreadLocal](D:\git\github\spring-boot-samples\spring-boot-sample-data-isolation\src\main\resources\doc\inheritableThreadLocal.png)



过滤器



![Filter](D:\git\github\spring-boot-samples\spring-boot-sample-data-isolation\src\main\resources\doc\Filter.png)



创建rest接口



![local-controller](D:\git\github\spring-boot-samples\spring-boot-sample-data-isolation\src\main\resources\doc\local-controller.png)

![local](D:\git\github\spring-boot-samples\spring-boot-sample-data-isolation\src\main\resources\doc\local.png)



#### 基本原理