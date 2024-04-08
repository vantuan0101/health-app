package com.vantuan.patientmanagement.common.util;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Supplier;

@Component
public class TransactionUtil {

    @Transactional
    public <T> T inTransaction(final Supplier<T> action){
        return action.get();
    }
}
