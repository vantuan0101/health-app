package com.vantuan.patientmanagement.config;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.vantuan.patientmanagement.model.entity.Patient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EntityBaseMapperModuleFactory {

    public SimpleModule create(){
        final SimpleModule module = new SimpleModule();
        return module;
    }
}
