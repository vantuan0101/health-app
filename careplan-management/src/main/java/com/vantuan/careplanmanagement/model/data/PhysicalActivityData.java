package com.vantuan.careplanmanagement.model.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vantuan.careplanmanagement.model.entity.Patient;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import static com.vantuan.careplanmanagement.model.entity.PhysicalActivity.*;

@NoArgsConstructor
public final class PhysicalActivityData {

    @Getter
    @Setter
    @SuperBuilder(toBuilder = true)
    @NoArgsConstructor
    public abstract static class Base {

        @NotNull
        @Schema(example = "2")
        @Min(PHYSICAL_ACTIVITY_MIN_DAYS)
        @Max(PHYSICAL_ACTIVITY_MAX_DAYS)
        private Short daysOfModerateActivity;

        @NotNull
        @Schema(example = "30")
        @Min(PHYSICAL_ACTIVITY_MIN_MINUTES)
        @Max(PHYSICAL_ACTIVITY_MAX_MINUTES)
        private Short minutesOfModerateActivity;

        @NotNull
        @Schema(example = "2")
        @Min(PHYSICAL_ACTIVITY_MIN_DAYS)
        @Max(PHYSICAL_ACTIVITY_MAX_DAYS)
        private Short daysOfVigorousActivity;

        @NotNull
        @Schema(example = "30")
        @Min(PHYSICAL_ACTIVITY_MIN_MINUTES)
        @Max(PHYSICAL_ACTIVITY_MAX_MINUTES)
        private Short minutesOfVigorousActivity;
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
