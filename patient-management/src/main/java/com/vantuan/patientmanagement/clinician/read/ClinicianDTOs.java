package com.vantuan.patientmanagement.clinician.read;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vantuan.patientmanagement.common.address.read.UserAddressDTOs;
import com.vantuan.patientmanagement.enums.Country;
import com.vantuan.patientmanagement.enums.Gender;
import com.vantuan.patientmanagement.enums.Region;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mapstruct.Mapper;

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

