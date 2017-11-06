package com.wxs.tkmybatis.conf;

import javax.sql.DataSource;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@ConditionalOnClass(DruidDataSource.class)
@EnableConfigurationProperties(MybatisProperties.class)
public class DataSourceConfig {

	@Autowired
	private MybatisProperties properties;

	@Autowired
	private ResourceLoader resourceLoader = new DefaultResourceLoader();

	@Bean(name = "primary-datasource")
	@Primary
	@ConfigurationProperties(prefix = "druid")
	public DataSource primaryDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(@Qualifier("primary-datasource") DataSource dataSource)
			throws Exception {
		SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
		factory.setDataSource(dataSource);
		factory.setVfs(SpringBootVFS.class);
		if (StringUtils.hasText(this.properties.getConfigLocation())) {
			factory.setConfigLocation(this.resourceLoader.getResource(this.properties.getConfigLocation()));
		}
		factory.setConfiguration(properties.getConfiguration());

		if (StringUtils.hasLength(this.properties.getTypeAliasesPackage())) {
			factory.setTypeAliasesPackage(this.properties.getTypeAliasesPackage());
		}
		if (StringUtils.hasLength(this.properties.getTypeHandlersPackage())) {
			factory.setTypeHandlersPackage(this.properties.getTypeHandlersPackage());
		}
		if (!ObjectUtils.isEmpty(this.properties.resolveMapperLocations())) {
			factory.setMapperLocations(this.properties.resolveMapperLocations());
		}

		return factory.getObject();
	}
	
	@Bean(name = "sqlSessionTemplate")
	public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
		ExecutorType executorType = this.properties.getExecutorType();
		if (executorType != null) {
			return new SqlSessionTemplate(sqlSessionFactory, executorType);
		} else {
			return new SqlSessionTemplate(sqlSessionFactory);
		}
	}

	// @Bean(name = "mq-datasource")
	// @ConfigurationProperties(prefix = "spring.mq-datasource")
	// public DataSource secondaryDataSource() {
	// 	return DataSourceBuilder.create().build();
	// }
    //
	// @Bean(name = "mqSqlSessionFactory")
	// public SqlSessionFactory mqSqlSessionFactory(@Qualifier("mq-datasource") DataSource dataSource) throws Exception {
	// 	SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
	// 	factory.setDataSource(dataSource);
	// 	factory.setVfs(SpringBootVFS.class);
	// 	if (StringUtils.hasText(this.properties.getConfigLocation())) {
	// 		factory.setConfigLocation(this.resourceLoader.getResource(this.properties.getConfigLocation()));
	// 	}
	// 	factory.setConfiguration(properties.getConfiguration());
    //
	// 	if (StringUtils.hasLength(this.properties.getTypeAliasesPackage())) {
	// 		factory.setTypeAliasesPackage(this.properties.getTypeAliasesPackage());
	// 	}
	// 	if (StringUtils.hasLength(this.properties.getTypeHandlersPackage())) {
	// 		factory.setTypeHandlersPackage(this.properties.getTypeHandlersPackage());
	// 	}
	// 	if (!ObjectUtils.isEmpty(this.properties.resolveMapperLocations())) {
	// 		factory.setMapperLocations(this.properties.resolveMapperLocations());
	// 	}
    //
	// 	return factory.getObject();
	// }
    //
	// @Bean(name = "mqSqlSessionTemplate")
	// public SqlSessionTemplate mqSqlSessionTemplate(@Qualifier("mqSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
	// 	ExecutorType executorType = this.properties.getExecutorType();
	// 	if (executorType != null) {
	// 		return new SqlSessionTemplate(sqlSessionFactory, executorType);
	// 	} else {
	// 		return new SqlSessionTemplate(sqlSessionFactory);
	// 	}
	// }
    //
	// @Bean(name = "roi-datasource")
	// @ConfigurationProperties(prefix = "spring.roi-datasource")
	// public DataSource thirdDataSource() {
	// 	return DataSourceBuilder.create().build();
	// }
    //
	// @Bean(name = "roiSqlSessionFactory")
	// public SqlSessionFactory roiSqlSessionFactory(@Qualifier("roi-datasource") DataSource dataSource) throws Exception {
	// 	SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
	// 	factory.setDataSource(dataSource);
	// 	factory.setVfs(SpringBootVFS.class);
	// 	if (StringUtils.hasText(this.properties.getConfigLocation())) {
	// 		factory.setConfigLocation(this.resourceLoader.getResource(this.properties.getConfigLocation()));
	// 	}
	// 	factory.setConfiguration(properties.getConfiguration());
    //
	// 	if (StringUtils.hasLength(this.properties.getTypeAliasesPackage())) {
	// 		factory.setTypeAliasesPackage(this.properties.getTypeAliasesPackage());
	// 	}
	// 	if (StringUtils.hasLength(this.properties.getTypeHandlersPackage())) {
	// 		factory.setTypeHandlersPackage(this.properties.getTypeHandlersPackage());
	// 	}
	// 	if (!ObjectUtils.isEmpty(this.properties.resolveMapperLocations())) {
	// 		factory.setMapperLocations(this.properties.resolveMapperLocations());
	// 	}
    //
	// 	return factory.getObject();
	// }
    //
	// @Bean(name = "roiSqlSessionTemplate")
	// public SqlSessionTemplate roiSqlSessionTemplate(@Qualifier("roiSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
	// 	ExecutorType executorType = this.properties.getExecutorType();
	// 	if (executorType != null) {
	// 		return new SqlSessionTemplate(sqlSessionFactory, executorType);
	// 	} else {
	// 		return new SqlSessionTemplate(sqlSessionFactory);
	// 	}
	// }
	//
	// @Bean(name = "rule-datasource")
	// @ConfigurationProperties(prefix = "spring.rule-datasource")
	// public DataSource ruleDataSource() {
	// 	return DataSourceBuilder.create().build();
	// }
    //
	// @Bean(name = "ruleSqlSessionFactory")
	// public SqlSessionFactory ruleSqlSessionFactory(@Qualifier("rule-datasource") DataSource dataSource) throws Exception {
	// 	SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
	// 	factory.setDataSource(dataSource);
	// 	factory.setVfs(SpringBootVFS.class);
	// 	if (StringUtils.hasText(this.properties.getConfigLocation())) {
	// 		factory.setConfigLocation(this.resourceLoader.getResource(this.properties.getConfigLocation()));
	// 	}
	// 	factory.setConfiguration(properties.getConfiguration());
    //
	// 	if (StringUtils.hasLength(this.properties.getTypeAliasesPackage())) {
	// 		factory.setTypeAliasesPackage(this.properties.getTypeAliasesPackage());
	// 	}
	// 	if (StringUtils.hasLength(this.properties.getTypeHandlersPackage())) {
	// 		factory.setTypeHandlersPackage(this.properties.getTypeHandlersPackage());
	// 	}
	// 	if (!ObjectUtils.isEmpty(this.properties.resolveMapperLocations())) {
	// 		factory.setMapperLocations(this.properties.resolveMapperLocations());
	// 	}
    //
	// 	return factory.getObject();
	// }
    //
	// @Bean(name = "ruleSqlSessionTemplate")
	// public SqlSessionTemplate ruleSqlSessionTemplate(@Qualifier("ruleSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
	// 	ExecutorType executorType = this.properties.getExecutorType();
	// 	if (executorType != null) {
	// 		return new SqlSessionTemplate(sqlSessionFactory, executorType);
	// 	} else {
	// 		return new SqlSessionTemplate(sqlSessionFactory);
	// 	}
	// }
	
}
