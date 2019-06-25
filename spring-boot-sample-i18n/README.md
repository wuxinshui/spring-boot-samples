# spring-boot-sample-i18n
## 工程所用技术点


### 1. Spring Boot
### 2. Mybatis
### 3. PageHelper
### 4. No mapper.xml
### 5. il8n
### 6. org.springframework.context.MessageSource
### 7. java.util.Locale

[Spring Boot MessageSourceAutoConfiguration](https://stackoverflow.com/questions/30663513/spring-boot-messagesourceautoconfiguration)

[Spring boot 国际化自动加载资源文件问题](https://segmentfault.com/a/1190000010757338)

# blog


## 简介

In computing, internationalization and localization are means of adapting computer software to different languages, regional peculiarities and technical requirements of a target locale.


术语

- i18n 

	internationalization `英 [ˌɪntəˌnæʃnəlaɪ'zeɪʃn]` ,国际化。
	
	由于首字母"i"和末尾字母"n"间有18个字符，所以简称i18n。
	
	internationalization指为了使应用程序能适应不同的语言和地区间的变化而不作系统性的变化所采取的设计措施。
- l10n

	localization, 本地化。
	
	由于首字母"l"和末尾字母"n"间有10个字母，所以简称l10n。
	localization指为了使应用软件能够在某一特定语言环境或地区使用而加入本地特殊化部件和翻译后文本的过程。
- locale: 指语言和区域进行特殊组合的一个标志

一般`语言_地区`可以确定一个特定类型的本地化信息。


`基名_语言_地区.properties`

- 语言由两个小写字母表示，具体代码是由[ISO-639](https://incubator.wikimedia.org/wiki/Incubator:ISO_639/zh)标准定义。
- 地区由两个大写字母表示，由[ISO-3166标准](http://kirste.userpage.fu-berlin.de/diverse/doc/ISO_3166.html)定义的。
- 基名，basename,一般是业务代码。例如：`ValidationLogin.properties`

常用配置：
- i18n_zh_CN.properties：中国大陆的，中文语言的资源

- i18n_en_US.properties：美国地区，英文语言的资源

- i18n.properties：默认资源文件，如果请求相应的资源文件不存在，将使用此资源文件

JDK的支持
- `java.util.Local`

SpringBoot的支持
- `org.springframework.context.MessageSource`
	```
	public interface MessageSource {
		String getMessage(String var1, Object[] var2, String var3, Locale var4);

		String getMessage(String var1, Object[] var2, Locale var3) throws NoSuchMessageException;

		String getMessage(MessageSourceResolvable var1, Locale var2) throws NoSuchMessageException;
	}
	```

- `org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration`

- `org.springframework.context.support.AbstractApplicationContext#initMessageSource`
- `ResourceBundleMessageSource`
- `ReloadableResourceBundleMessageSource`

	了解这些类，对问题排查会有帮助。

## 实战

`SpringBoot`提供两种配置方式。

### Java配置

重新定义`org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration#messageSource`中的bean。

```
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("i18n");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
```

### application配置


```
spring.messages.basename=i18n
```

`org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration#messageSource` 根据配置文件，自动创建bean`messageSource`.

![](https://oscimg.oschina.net/oscnet/f472f465224cfc50cdee46be295c22b4c4b.jpg)

![](https://oscimg.oschina.net/oscnet/95d704b3f41ae6bc3d2002b905ea9b65c5d.jpg)


### 代码演示

文件配置：
`email.server=mail.163.com`

![](https://oscimg.oschina.net/oscnet/5964c6c99e1bb039934e4a4ca9cd220a58a.jpg)

RestController
```
@RestController
public class MessageSourceTestController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/mail")
    public String getUsers() {
        return messageSource.getMessage("email.server", null, Locale.CHINA);
    }

}
```

请求接口,获取配置文件中的值。

![](https://oscimg.oschina.net/oscnet/bde73d3a27d85615be76cb33b0f1ac8cf4c.jpg)

## 遇到的问题

接手一个老项目，`restful`框架还是自研的，切换为springboot框架后，国际化配置遇到些问题。

整理如下。

### `MessageSource` null
 `org.springframework.context.support.AbstractApplicationContext#initMessageSource`，初始化时，没有检测到`messageSource`Bean，会默认提供一个空的实现`DelegatingMessageSource`。

![](https://oscimg.oschina.net/oscnet/35a11e1025c7ac42fef6d9c6a32ed648375.jpg)

 解决办法，直接实例化，一个bean。参考上文`java配置`

### `@ConditionalOnMissingBean (types: org.springframework.context.MessageSource; SearchStrategy: current) did not find any beans (OnBeanCondition)`

应该是个bug，参考[Spring Boot MessageSourceAutoConfiguration](https://stackoverflow.com/questions/30663513/spring-boot-messagesourceautoconfiguration)，实际开发中，可忽略。

### `debug=true`
`application.properties`中添加`debug=true`，在应用启动的时候，会把自动化配置、bean等信息打印出来。

```
2019-06-13 14:03:42.740 DEBUG 18680 --- [           main] ationConfigEmbeddedWebApplicationContext : Using MessageSource [org.springframework.context.support.ResourceBundleMessageSource: basenames=[messages/messages]]

```

```
 MessageSourceAutoConfiguration matched:
      - ResourceBundle found bundle URL [file:/D:/git/github/spring-boot-samples/spring-boot-sample-il8n/target/classes/messages/messages.properties] (MessageSourceAutoConfiguration.ResourceBundleCondition)
      - @ConditionalOnMissingBean (types: org.springframework.context.MessageSource; SearchStrategy: current) did not find any beans (OnBeanCondition)

```
没有检测到`messageSource`Bean，会默认提供一个空的实现
```
[2019-06-13 14:19:43.453] [main] [DEBUG] [igServletWebServerApplicationContext:739 ] - Unable to locate MessageSource with name 'messageSource': using default [org.springframework.context.support.DelegatingMessageSource@3f2ab6ec]
```

## 参考

[Java 程序的国际化和本地化介绍
](https://www.ibm.com/developerworks/cn/java/joy-i18n/index.html)

[国际化资源文件命名规范](https://zhuanlan.zhihu.com/p/50620931)



