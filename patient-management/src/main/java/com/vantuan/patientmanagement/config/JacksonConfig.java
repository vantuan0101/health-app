package com.vantuan.patientmanagement.config;

import com.fasterxml.jackson.datatype.jsr310.ser.InstantSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Configuration
public class JacksonConfig {

    public static final DateTimeFormatter UTC_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .withZone(ZoneOffset.UTC);

    public static final DateTimeFormatter UTC_LOCAL_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            .withZone(ZoneOffset.UTC);

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        return builder -> builder
                .serializers(new CustomInstantSerializer(), new LocalDateSerializer(UTC_LOCAL_DATE_FORMAT));
    }

    private static class CustomInstantSerializer extends InstantSerializer {
        private CustomInstantSerializer() {
            super(InstantSerializer.INSTANCE, null, UTC_DATE_FORMAT);
        }
    }

}
