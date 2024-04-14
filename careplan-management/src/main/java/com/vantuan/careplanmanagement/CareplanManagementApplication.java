package com.vantuan.careplanmanagement;

import com.vantuan.crud.AbstractSpringBootServletInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.ComponentScan;

import java.util.TimeZone;

@SpringBootApplication
@ComponentScan(basePackages = { "com.vantuan.*" })
@EnableDiscoveryClient
public class CareplanManagementApplication extends AbstractSpringBootServletInitializer {

    private static final String TIME_ZONE = "UTC";

    public static void main(String[] args) {
        SpringApplication.run(CareplanManagementApplication.class, args);
    }

    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone(TIME_ZONE));
    }

    @Override
    protected Class<?> getApplicationClass() {
        return CareplanManagementApplication.class;
    }

}
