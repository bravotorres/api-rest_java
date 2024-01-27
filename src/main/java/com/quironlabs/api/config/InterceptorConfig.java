package com.quironlabs.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.quironlabs.api.config.interceptors.CypherInterceptor;
import com.quironlabs.api.config.interceptors.LoggerInterceptor;

@Configuration
@EnableWebMvc
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggerInterceptor());
        registry.addInterceptor(new CypherInterceptor());
            // .addPathPatterns("/**")
            // .excludePathPatterns("/admin/**");
    }
}
