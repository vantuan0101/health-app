package com.vantuan.patientmanagement.config;

import com.vantuan.common.mapper.MappingUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfiguration {

    @Bean
    public MappingUtil mappingUtil() {
        return new MappingUtil();
    }
}

