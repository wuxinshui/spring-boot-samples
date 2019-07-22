package com.wxs.auth.starter.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "auth.filter.url")
@Data
public class AuthFilterProperties {
    private String[] excludeUrls;

    private String[] includeUrls;
}
