package com.vantuan.clinicmanagement;

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
public class ClinicManagementApplication extends AbstractSpringBootServletInitializer {

    private static final String TIME_ZONE = "UTC";

    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone(TIME_ZONE));
    }

    @Override
    protected Class<?> getApplicationClass() {
        return ClinicManagementApplication.class;
    }

    public static void main(String[] args) {
        SpringApplication.run(ClinicManagementApplication.class, args);
    }

}
