package com.quironlabs.api.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;


@Configuration
// @PropertySource("file:${WORKSPACE}/api/application-${ENVIRONMENT}.properties")
@PropertySource("classpath:application-${ENVIRONMENT}.properties")
public class PropertiesConfig {
    private static final Logger logger = LoggerFactory.getLogger(PropertiesConfig.class);

    @Autowired
    private Environment env;

    @Bean
    public void testBean() {
        // String property = env.getProperty("jwt.secret");

        logger.info(" env: {}", env.getProperty("sistema.ambiente"));
        logger.info(" jwt: {}", env.getProperty("jwt.secret"));
        logger.info(" exp: {}", env.getProperty("jwt.expires.segs"));
        logger.info("port: {}", env.getProperty("server.port"));
        logger.info("path: {}", env.getProperty("server.servlet.context-path"));
    }
}
