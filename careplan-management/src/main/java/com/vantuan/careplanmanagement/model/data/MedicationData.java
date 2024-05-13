package com.vantuan.careplanmanagement.model.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vantuan.careplanmanagement.common.enums.DoseUnit;
import com.vantuan.careplanmanagement.common.enums.Frequency;
import com.vantuan.careplanmanagement.common.enums.Unit;
import com.vantuan.careplanmanagement.model.entity.Patient;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
public final class MedicationData {

    @Getter
    @Setter
    @SuperBuilder(toBuilder = true)
    @NoArgsConstructor
    public abstract static class Base {

        @NotNull
        private String medicineName;

        @NotNull
        private String quantity;

        @NotNull
        private Unit unit;

        @NotNull
        @Schema(type = "string", format = "date")
        private LocalDate startDate;

        @NotNull
        @Schema(type = "string", format = "date")
        private LocalDate estimatedEndDate;

        @NotNull
        private DoseUnit doseUnit;

        @NotNull
        private Frequency frequency;

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
