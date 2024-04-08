package com.vantuan.patientmanagement.config;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public abstract class AbstractObjectMapperConfiguration {

    private final ObjectMapper mapper;
    private final EntityBaseMapperModuleFactory entityBaseMapperModuleFactory;

    @PostConstruct
    public void configure() {
        mapper.registerModule(entityBaseMapperModuleFactory.create());
        mapper.configOverride(List.class).setSetterInfo(JsonSetter.Value.forValueNulls(Nulls.AS_EMPTY));
        mapper.configOverride(Set.class).setSetterInfo(JsonSetter.Value.forValueNulls(Nulls.AS_EMPTY));

        configure(mapper);
    }

    protected abstract void configure(final ObjectMapper mapper);
}
