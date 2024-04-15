package com.vantuan.patientmanagement.config;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.SecureRandom;

@Configuration
public class GlobalConfig {

    @Bean
    @SneakyThrows
    public SecureRandom secureRandom() {
        return SecureRandom.getInstanceStrong();
    }

}
