package com.vantuan.careplanmanagement.read;

import com.vantuan.careplanmanagement.common.enums.DoseUnit;
import com.vantuan.careplanmanagement.common.enums.Frequency;
import com.vantuan.careplanmanagement.common.enums.Unit;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
public final class MedicationDTOs {

    @Getter
    @Setter
    abstract static class Base {
        private Long id;

        private String medicineName;

        private String quantity;

        private Unit unit;

        @Schema(type = "string", format = "date")
        private LocalDate startDate;

        @Schema(type = "string", format = "date")
        private LocalDate estimatedEndDate;

        private DoseUnit doseUnit;

        private Frequency frequency;

        private String notes;

    }

    @Getter
    @Setter
    public static class Full extends Base {
    }

}
