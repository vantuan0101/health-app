package com.vantuan.careplanmanagement.criteria;

import com.vantuan.careplanmanagement.model.entity.Patient;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import static com.vantuan.careplanmanagement.model.entity.PhysicalActivity.*;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class PhysicalActivityCriteria {
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

    private Long id;
    private Patient patient;
}
