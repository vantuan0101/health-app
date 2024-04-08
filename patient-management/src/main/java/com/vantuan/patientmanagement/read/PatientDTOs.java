package com.vantuan.patientmanagement.read;

import com.vantuan.patientmanagement.clinician.read.ClinicianDTOs;
import com.vantuan.patientmanagement.common.address.read.UserAddressDTOs;
import com.vantuan.patientmanagement.enums.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
public final class PatientDTOs {

    @Getter
    @Setter
    abstract static class Base {

        @Schema(example = "John")
        private String firstName;

        @Schema(example = "Kowalski")
        private String lastName;

        private String photo;

        @Schema(type = "string", format = "date")
        private LocalDate birthDate;

        private UserAddressDTOs.Full userAddress;

        private Country country;

        private Gender gender;


        @Schema(example = "180")
        private Short height;

        @Schema(example = "80")
        private Float weight;
    }

    @Getter
    @Setter
    public static class Simple extends Base {
    }

    @Getter
    @Setter
    public static class Full  {
        private ClinicianDTOs.Short clinician;
    }
}

