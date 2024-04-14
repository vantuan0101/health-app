package com.vantuan.careplanmanagement.criteria;

import com.vantuan.careplanmanagement.common.enums.*;
import com.vantuan.careplanmanagement.model.entity.Patient;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class MedicationCriteria {
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

    private Long id;
    private Patient patient;
}
