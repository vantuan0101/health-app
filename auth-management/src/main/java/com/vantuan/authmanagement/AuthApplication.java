package com.vantuan.authmanagement;

import com.vantuan.framework.crud.AbstractSpringBootServletInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.ComponentScan;
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