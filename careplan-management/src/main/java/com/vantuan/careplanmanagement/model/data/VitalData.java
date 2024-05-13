package com.vantuan.careplanmanagement.model.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vantuan.careplanmanagement.model.entity.Patient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import static com.vantuan.careplanmanagement.model.entity.Vital.VITALS_MAX;
import static com.vantuan.careplanmanagement.model.entity.Vital.VITALS_MIN;

@NoArgsConstructor
public final class VitalData {

    @Getter
    @Setter
    @SuperBuilder(toBuilder = true)
    @NoArgsConstructor
    public abstract static class Base {

        @NotNull
        @Min(VITALS_MIN)
        @Max(VITALS_MAX)
        private Short currentHeight;

        @NotNull
        @Min(VITALS_MIN)
        @Max(VITALS_MAX)
        private Short currentWeight;

        @NotNull
        @Min(VITALS_MIN)
        @Max(VITALS_MAX)
        private Short heartRate;

        @NotNull
        @Min(VITALS_MIN)
        @Max(VITALS_MAX)
        private Float temperature;

        @NotNull
        @Min(VITALS_MIN)
        @Max(VITALS_MAX)
        private Short oxygenSaturation;

        @NotNull
        @Min(VITALS_MIN)
        @Max(VITALS_MAX)
        private Short systolicBloodPressure;

        @NotNull
        @Min(VITALS_MIN)
        @Max(VITALS_MAX)
        private Short diastolicBloodPressure;

    }

    @Getter
    @Setter
    @SuperBuilder(toBuilder = true)
    @NoArgsConstructor
    public static class Edit extends Base {
    }

    @Getter
    @Setter
    @SuperBuilder(toBuilder = true)
    @NoArgsConstructor
    public static class Create extends Base {

        private String patientId;

        @JsonIgnore
        private Patient patient;
    }

}
