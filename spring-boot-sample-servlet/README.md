# spring-boot-sample-cache
## 技术点

### 1. Spring Boot
### 2. Servlet3.x
1. `@WebServlet(name = "userServlet",urlPatterns = "/web-async-servlet",asyncSupported = true)`
2. `new ServletRegistrationBean(new UserRegistrationServlet(), "/register-servlet")`
