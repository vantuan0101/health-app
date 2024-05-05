package com.vantuan.patientmanagement.read;

import com.vantuan.patientmanagement.common.enums.Country;
import com.vantuan.patientmanagement.common.enums.Gender;
import com.vantuan.patientmanagement.common.enums.Region;
import com.vantuan.patientmanagement.common.enums.Status;
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

        private Long id;

        @Schema(example = "John")
        private String firstName;

        @Schema(example = "Kowalski")
        private String lastName;

        private String photo;

        private Status status;

        private String email;

        private Gender gender;

        @Schema(type = "string", format = "date")
        private LocalDate birthDate;

        private String address;

        private String city;

        private Country country;

        private String zipCode;

        private Region region;
    }

    @Getter
    @Setter
    public static class Full extends Base {

    }

    @Getter
    @Setter
    public static class Short extends Base {
    }

}
