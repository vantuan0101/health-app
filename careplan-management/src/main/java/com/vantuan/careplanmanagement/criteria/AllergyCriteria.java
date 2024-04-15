package com.vantuan.careplanmanagement.criteria;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vantuan.careplanmanagement.common.enums.Reaction;
import com.vantuan.careplanmanagement.common.enums.Severity;
import com.vantuan.careplanmanagement.model.entity.Patient;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import static com.vantuan.careplanmanagement.model.entity.Allergy.MAX_YEARS;
import static com.vantuan.careplanmanagement.model.entity.Allergy.MIN_YEARS;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class AllergyCriteria {
    @JsonIgnore
    private Patient patient;

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

    private Long id;
}
