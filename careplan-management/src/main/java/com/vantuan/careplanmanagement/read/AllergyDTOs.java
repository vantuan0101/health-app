package com.vantuan.careplanmanagement.read;

import com.vantuan.careplanmanagement.common.enums.Reaction;
import com.vantuan.careplanmanagement.common.enums.Severity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public final class AllergyDTOs {

    @Getter
    @Setter
    abstract static class Base {

        private Long id;

        private String substance;

        private Reaction reaction;

        @Schema(example = "2018")
        private Short onset;

        private Severity severity;

        private String medication;

        private String notes;

    }

    @Getter
    @Setter
    public static class Full extends Base {
    }

}
