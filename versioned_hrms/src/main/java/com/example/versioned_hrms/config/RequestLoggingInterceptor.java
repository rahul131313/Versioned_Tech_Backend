package com.example.versioned_hrms.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RequestLoggingInterceptor
        implements HandlerInterceptor {

    private static final Logger log =
            LoggerFactory.getLogger(
                    RequestLoggingInterceptor.class
            );

    private static final String START_TIME =
            "requestStartTime";

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) {

        request.setAttribute(
                START_TIME,
                System.currentTimeMillis()
        );

        log.info(
                "Incoming request method={} uri={}",
                request.getMethod(),
                request.getRequestURI()
        );

        return true;
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception ex) {

        Long startTime =
                (Long) request.getAttribute(START_TIME);

        long duration =
                System.currentTimeMillis() - startTime;

        log.info(
                "Request completed method={} uri={} status={} duration={}ms",
                request.getMethod(),
                request.getRequestURI(),
                response.getStatus(),
                duration
        );
    }
}
