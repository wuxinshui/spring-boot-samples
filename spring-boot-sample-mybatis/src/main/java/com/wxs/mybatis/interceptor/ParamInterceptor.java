package com.wxs.mybatis.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Intercepts({
        @Signature(type = ParameterHandler.class, method = "setParameters", args = PreparedStatement.class),
})
@Slf4j
public class ParamInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        log.info("拦截器ParamInterceptor");
        //拦截 ParameterHandler 的 setParameters 方法 动态设置参数
        if (invocation.getTarget() instanceof ParameterHandler) {
            ParameterHandler parameterHandler = (ParameterHandler) invocation.getTarget();
            PreparedStatement ps = (PreparedStatement) invocation.getArgs()[0];

            // 反射获取 BoundSql 对象，此对象包含生成的sql和sql的参数map映射
			Field boundSqlField = parameterHandler.getClass().getDeclaredField("boundSql");
			boundSqlField.setAccessible(true);
			BoundSql boundSql = (BoundSql) boundSqlField.get(parameterHandler);

            Method method=invocation.getMethod();

            Object[] args=invocation.getArgs();

            // 反射获取 参数对像
            // Method parameterMethod =
            //         parameterHandler.getClass().getDeclaredMethod("parameterObject");

            // 反射获取 参数对像
            Field parameterField =
                    parameterHandler.getClass().getDeclaredField("parameterObject");
            parameterField.setAccessible(true);
            Object parameterObject = parameterField.get(parameterHandler);
            log.info("parameterObject:{}", parameterObject);
            handlerParameterObj(parameterObject);

        }
        log.info("拦截器ParamInterceptor...over");

        return invocation.proceed();
    }

    private void handlerParameterObj(Object parameterObject) {
        if (Objects.nonNull(parameterObject)) {
            if (parameterObject instanceof Map) {
                if (!((Map) parameterObject).containsKey("list")) {
                    return;
                }
                Object objValue = ((Map) parameterObject).get("list");
                if (null == objValue) {
                    return;
                }

                List list = (List) objValue;
                list.forEach(paraObj -> {
                    log.info("list parameterObject:{},paraObj:{}", parameterObject, paraObj);
                    handlerParameterObj(paraObj);
                });

            }


            Class<?> parameterObjectClass = parameterObject.getClass();

            log.info("parameterObject:{},parameterObjectClass:{}", parameterObject, parameterObjectClass);
            // EncryptField encryptDecryptClass = AnnotationUtils.findAnnotation(parameterObjectClass, EncryptField.class);
            // log.info("parameterObject:{},parameterObjectClass:{},encryptDecryptClass:{}", parameterObject, parameterObjectClass, encryptDecryptClass);
            // if (Objects.nonNull(encryptDecryptClass)) {
            Field[] declaredFields = parameterObjectClass.getDeclaredFields();

            List<Field> encryptFields = Stream.of(declaredFields).filter(o -> o.isAnnotationPresent(EncryptField.class)).collect(Collectors.toList());
            encryptFields.forEach(field -> {
                try {
                    field.setAccessible(true);
                    Object obj = field.get(parameterObject);
                    field.set(parameterObject, Base64Util.encryptBase64(obj.toString()));
                    field.setAccessible(false);
                } catch (IllegalAccessException e) {
                    log.error("IllegalAccessException", e);
                }
            });

            // log.info("parameterObject:{},parameterObjectClass:{},encryptDecryptClass:{},declaredFields:{}", parameterObject, parameterObjectClass, encryptDecryptClass, declaredFields);
            // Stream.of(declaredFields).forEach(field -> {
            //     try {
            //         Object obj = field.get(parameterObject);
            //         field.setAccessible(true);
            //         field.set(parameterObject, Base64Util.encryptBase64(obj.toString()));
            //     } catch (IllegalAccessException e) {
            //         log.error("IllegalAccessException", e);
            //     }
            // });

            // }
        }
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
