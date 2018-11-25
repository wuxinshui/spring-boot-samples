/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wxs.jersey.common;

import com.wxs.jersey.resource.LoginResource;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class JerseyConfig extends ResourceConfig {

	@Value("${spring.jersey.application-path}")
	private String apiPath;


	public JerseyConfig() {
		register(LoginResource.class);
		register(io.swagger.jaxrs.listing.ApiListingResource.class);
		register(io.swagger.jaxrs.listing.AcceptHeaderApiListingResource.class);
		register(io.swagger.jaxrs.listing.SwaggerSerializers.class);
	}

	@PostConstruct
	public void init() {
		// Register components where DI is needed
		this.configureSwagger();
	}

	private void configureSwagger() {
		// Available at localhost:port/swagger.json
		this.register(ApiListingResource.class);
		this.register(SwaggerSerializers.class);
		BeanConfig config = new BeanConfig();
		config.setConfigId("springboot-jersey-swagger-docker-example");
		config.setTitle("Spring Boot + Jersey + Swagger");
		config.setVersion("v1");
		config.setContact("yoyo");
		config.setSchemes(new String[] { "http", "https" });
		config.setBasePath(this.apiPath);
		config.setResourcePackage("com.wxs.jersey.resource");
		config.setPrettyPrint(true);
		config.setScan(true);
	}

}
