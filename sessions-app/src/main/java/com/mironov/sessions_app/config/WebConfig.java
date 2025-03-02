package com.mironov.sessions_app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


public class WebConfig implements WebMvcConfigurer {

        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**") // Разрешить все пути
                    .allowedOrigins("*") // Разрешить все источники
                    .allowedMethods("GET", "POST", "PUT", "DELETE"); // Разрешенные методы
        }
    }
