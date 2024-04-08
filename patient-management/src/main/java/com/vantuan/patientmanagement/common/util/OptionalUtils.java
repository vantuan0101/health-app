package com.vantuan.patientmanagementh.common.util;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OptionalUtils {

    public static <T> T fromOptional(Optional<T> optional) {
        return optional.orElse(null);
    }

}
