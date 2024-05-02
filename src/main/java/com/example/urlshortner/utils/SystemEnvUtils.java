package com.example.urlshortner.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Base64;
import java.util.Objects;

@Slf4j
public class SystemEnvUtils {

    public static String getFromEnvOrDefault(String envVarName, String defaultValue) {
        String value = getFromEnv(envVarName);

        if (Objects.isNull(value)) {
            log.warn("env var {} value is null; using {} as default", envVarName, defaultValue);
            return defaultValue;
        }

        log.info("env var {} value is {}", envVarName, value);
        return value;
    }

    public static String getFromEnv(String envVarName) {
        return System.getenv(envVarName);
    }

    public static String getBase64DecodedFromEnv(String envVarName) {
        String encodedValue = System.getenv(envVarName);
        log.info("env var {}, encoded value {}", envVarName, encodedValue);
        byte[] decodedBytes = Base64.getDecoder().decode(encodedValue);
        return new String(decodedBytes);
    }

}
