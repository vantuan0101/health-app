package com.vantuan.patientmanagement.common.util;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import static java.time.temporal.ChronoUnit.DAYS;

@Component
public class TimeUtils {

    public static Instant currentTimestamp() {
        return Instant.now();
    }

    public static Instant currentTimestampMinusDays(final int daysToSubtract) {
        return Instant.now().minus(daysToSubtract, DAYS);
    }

    public static String currentTimestampAsString() {
        return Long.toString(Instant.now().getEpochSecond());
    }

    public static Instant instantFromString(String instant) {
        return Instant.ofEpochSecond(Long.parseLong(instant));
    }

    public static LocalDate currentDate() {
        return LocalDate.now();
    }

    public static LocalDate currentDateMinusDays(final int daysToSubtract) {
        return LocalDate.now().minusDays(daysToSubtract);
    }

    public static LocalDate currentDateInPatientTimeZoneMinusDays(String timeZone, final int daysToSubtract) {
        return LocalDate.now(ZoneId.of(timeZone)).minusDays(daysToSubtract);
    }

    public static LocalDate currentDateWithTimeZoneMinusDays(String timeZone, final int daysToSubtract) {
        return LocalDate.now(ZoneId.of(timeZone)).minusDays(daysToSubtract);
    }

    public static LocalDate currentDateWithTimeZone(String timeZone) {
        return LocalDate.now(ZoneId.of(timeZone));
    }
}
