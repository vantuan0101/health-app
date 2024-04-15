package com.vantuan.clinicmanagement.read;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vantuan.clinicmanagement.common.address.read.UserAddressDTOs;
import com.vantuan.clinicmanagement.common.enums.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@NoArgsConstructor
public final class ClinicianDTOs {

    @Getter
    @Setter
    abstract static class Base {

        private Boolean active;

        @Schema(example = "John")
        private String firstName;

        @Schema(example = "Kowalski")
        private String lastName;

        private Boolean isVerified;

        private Gender gender;

        @Schema(type = "string", format = "date")
        private LocalDate birthDate;
    }

    @Getter
    @Setter
    public static class Full extends Base {
        @JsonInclude(NON_EMPTY)
        private UserAddressDTOs.Full userAddress;
    }

    @Getter
    @Setter
    public static class Short extends Base {
    }

}
