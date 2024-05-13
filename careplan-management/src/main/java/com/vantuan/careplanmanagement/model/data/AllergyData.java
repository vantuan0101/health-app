package com.vantuan.careplanmanagement.model.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vantuan.careplanmanagement.common.enums.Reaction;
import com.vantuan.careplanmanagement.common.enums.Severity;
import com.vantuan.careplanmanagement.model.entity.Patient;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import static com.vantuan.careplanmanagement.model.entity.Allergy.MAX_YEARS;
import static com.vantuan.careplanmanagement.model.entity.Allergy.MIN_YEARS;

@NoArgsConstructor
public final class AllergyData {

    @Getter
    @Setter
    @SuperBuilder(toBuilder = true)
    @NoArgsConstructor
    public abstract static class Base {

        @NotNull
        private String substance;

        @NotNull
        private Reaction reaction;

        @NotNull
        @Min(MIN_YEARS)
        @Max(MAX_YEARS)
        @Schema(example = "2018")
        private Short onset;

        @NotNull
        private Severity severity;

        private String medication;

        private String notes;
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
