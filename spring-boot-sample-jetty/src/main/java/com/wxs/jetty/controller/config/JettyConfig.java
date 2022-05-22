package com.wxs.jetty.controller.config;

import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.springframework.boot.web.embedded.jetty.JettyServerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JettyConfig {
    // @Bean
    // public JettyEmbeddedServletContainerFactory jettyEmbeddedServletContainerFactory(
    //         JettyServerCustomizer jettyServerCustomizer) {
    //     JettyEmbeddedServletContainerFactory factory = new JettyEmbeddedServletContainerFactory();
    //     factory.addServerCustomizers(jettyServerCustomizer);
    //     return factory;
    // }


    @Bean
    public JettyServerCustomizer jettyServerCustomizer() {
        return server -> {
            // Tweak the connection config used by Jetty to handle incoming HTTP
            // connections
            final QueuedThreadPool threadPool = server.getBean(QueuedThreadPool.class);
            threadPool.setMaxThreads(500);
            threadPool.setMinThreads(30);
        };
    }
}
