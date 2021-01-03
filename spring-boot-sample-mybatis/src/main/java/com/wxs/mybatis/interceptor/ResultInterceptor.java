package com.wxs.mybatis.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Intercepts({
        @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class})
})
@Component
@Slf4j
public class ResultInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        log.info("拦截器ResultInterceptor");
        Object result = invocation.proceed();
        if (Objects.isNull(result)) {
            return null;
        }

        if (result instanceof ArrayList) {
            ArrayList resultList = (ArrayList) result;
            if (!CollectionUtils.isEmpty(resultList) && needToDecrypt(resultList.get(0))) {
                for (int i = 0; i < resultList.size(); i++) {
                    Object parameterObject = resultList.get(i);
                    List<Field> encryptFields = Stream.of(parameterObject.getClass().getDeclaredFields()).filter(o -> o.isAnnotationPresent(EncryptField.class)).collect(Collectors.toList());
                    encryptFields.forEach(field -> {
                        try {
                            field.setAccessible(true);
                            Object obj = field.get(parameterObject);
                            field.set(parameterObject, Base64Util.decryptBase64(obj.toString()));
                            field.setAccessible(false);
                        } catch (IllegalAccessException e) {
                            log.error("IllegalAccessException", e);
                        }
                    });
                }
            }
        } else {
            if (needToDecrypt(result)) {
                Base64Util.decryptBase64(result.toString());
            }
        }
        return result;
    }

    public boolean needToDecrypt(Object object) {
        Class<?> objectClass = object.getClass();
        if (Stream.of(objectClass.getDeclaredFields()).filter(o -> o.isAnnotationPresent(EncryptField.class)).findAny().isPresent()) {
            return true;
        }
        // EncryptField encryptDecryptClass = AnnotationUtils.findAnnotation(objectClass, EncryptField.class);
        // if (Objects.nonNull(encryptDecryptClass)) {
        //     return true;
        // }
        return false;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
