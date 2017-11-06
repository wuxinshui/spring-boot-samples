package com.wxs.tkmybatis.conf;

import com.github.pagehelper.autoconfigure.MapperProperties;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;

import javax.annotation.PostConstruct;
import java.util.List;


@Configuration
@EnableConfigurationProperties(MapperProperties.class)
@Order(Ordered.LOWEST_PRECEDENCE)
public class MapperAutoConfiguration {

    private final List<SqlSessionFactory> SqlSessionFactories;
    @Autowired
    private       MapperProperties  properties;

    public MapperAutoConfiguration(List<SqlSessionFactory> sqlSessionFactories) {
        this.SqlSessionFactories = sqlSessionFactories;
    }

    @PostConstruct
    public void addPageInterceptor() {
        MapperHelper mapperHelper = new MapperHelper();
        mapperHelper.setConfig(properties);
        if (properties.getMappers().size() > 0) {
            for (Class mapper : properties.getMappers()) {
                mapperHelper.registerMapper(mapper);
            }
        } else {
            mapperHelper.registerMapper(Mapper.class);
        }
        for (SqlSessionFactory sqlSessionFactory : SqlSessionFactories) {
        	mapperHelper.processConfiguration(sqlSessionFactory.getConfiguration());
        }
    }
}
