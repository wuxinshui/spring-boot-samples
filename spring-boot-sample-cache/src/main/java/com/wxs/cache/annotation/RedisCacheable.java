package com.wxs.cache.annotation;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;
import java.time.Duration;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Import(Duration.class)
public @interface RedisCacheable {
    @AliasFor(annotation = Cacheable.class)
    String[] value() default {};

    @AliasFor(annotation = Cacheable.class)
    String[] cacheNames() default {};

    @AliasFor(annotation = Cacheable.class)
    String key() default "";

    @AliasFor(annotation = Cacheable.class)
    String keyGenerator() default "";

    @AliasFor(annotation = Cacheable.class)
    String cacheManager() default "";

    @AliasFor(annotation = Cacheable.class)
    String cacheResolver() default "";

    @AliasFor(annotation = Cacheable.class)
    String condition() default "";

    @AliasFor(annotation = Cacheable.class)
    String unless() default "";

    @AliasFor(annotation = Cacheable.class)
    boolean sync() default false;

    /**
     * 0-ofSeconds
     * 1-ofMinutes
     * 2-ofHours
     * 3-ofDays
     *
     * @return
     */
    int duration() default 2;

    int ttl() default 2;
}
