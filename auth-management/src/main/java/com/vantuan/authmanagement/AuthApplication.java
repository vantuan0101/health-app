package com.vantuan.authmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.ComponentScan;

import com.vantuan.crud.AbstractSpringBootServletInitializer;

import java.util.TimeZone;

@SpringBootApplication
@ComponentScan(basePackages = { "com.vantuan.*" })
 @EnableDiscoveryClient
public class AuthApplication extends AbstractSpringBootServletInitializer {

    private static final String TIME_ZONE = "UTC";

    @Override
    protected Class<?> getApplicationClass() {
        return AuthApplication.class;
    }

    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone(TIME_ZONE));
    }

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}