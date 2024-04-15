package com.vantuan.patientmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.ComponentScan;

import com.vantuan.crud.AbstractSpringBootServletInitializer;

import java.util.TimeZone;

@SpringBootApplication
@ComponentScan(basePackages = { "com.vantuan.*" })
@EnableDiscoveryClient
public class PatientManagementApplication extends AbstractSpringBootServletInitializer {

    private static final String TIME_ZONE = "UTC";

    @Override
    protected Class<?> getApplicationClass() {
        return PatientManagementApplication.class;
    }

    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone(TIME_ZONE));
    }

    public static void main(String[] args) {
        SpringApplication.run(PatientManagementApplication.class, args);
    }
}
