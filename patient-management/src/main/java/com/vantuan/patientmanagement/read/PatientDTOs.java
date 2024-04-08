package com.vantuan.patientmanagement.model.read;

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

        private Boolean active;

        @Schema(example = "John")
        private String firstName;

        @Schema(example = "Kowalski")
        private String lastName;

        @Schema(type = "string", format = "date")
        private LocalDate birthDate;

        private String profilePicture;

        private UserAddressDTOs.Full userAddress;

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
}

