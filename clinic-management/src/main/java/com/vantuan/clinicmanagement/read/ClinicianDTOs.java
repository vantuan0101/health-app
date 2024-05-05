package com.vantuan.clinicmanagement.read;

import com.vantuan.clinicmanagement.common.enums.Country;
import com.vantuan.clinicmanagement.common.enums.Gender;
import com.vantuan.clinicmanagement.common.enums.Region;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
public final class ClinicianDTOs {

    @Getter
    @Setter
    abstract static class Base {

        private Long id;

        private Boolean isVerified;

        private Boolean active;

        @Schema(example = "John")
        private String firstName;

        @Schema(example = "Kowalski")
        private String lastName;

        private String address;

        private String city;

        private Country country;

        private String zipCode;

        private Region region;

        private Gender gender;

        @Schema(type = "string", format = "date")
        private LocalDate birthDate;
    }

    @Getter
    @Setter
    public static class Full extends Base {

        private Country countryOfLicense;

        private Region stateOfPrimaryLicense;

        @Schema(example = "35007")
        private String licenseNumber;

    }

    @Getter
    @Setter
    public static class Short extends Base {
    }

}
