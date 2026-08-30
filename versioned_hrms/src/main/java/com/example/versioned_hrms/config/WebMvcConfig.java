package com.example.versioned_hrms.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig
        implements WebMvcConfigurer {

    private final RequestLoggingInterceptor interceptor;

    public WebMvcConfig(
            RequestLoggingInterceptor interceptor) {

        this.interceptor = interceptor;
    }

    @Override
    public void addInterceptors(
            InterceptorRegistry registry) {

        registry.addInterceptor(interceptor);
    }
}
