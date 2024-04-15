package com.vantuan.careplanmanagement.common.address.model.data;

import com.vantuan.careplanmanagement.common.enums.Country;
import com.vantuan.careplanmanagement.common.enums.Region;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
public final class UserAddressData {

    @Getter
    @Setter
    @SuperBuilder(toBuilder = true)
    @NoArgsConstructor
    public static class ClinicianCreate {

        @Schema(example = "East 64th Street")
        private String address;

        @Schema(example = "New York")
        private String city;

        private Country country;

        @Schema(example = "10122")
        private String zipCode;

        private Region region;
    }

    @Getter
    @Setter
    @SuperBuilder(toBuilder = true)
    @NoArgsConstructor
    public static class PatientCreate {

        @NotNull
        @Schema(example = "East 64th Street")
        private String address;

        @NotNull
        @Schema(example = "New York")
        private String city;

        @NotNull
        private Country country;

        @NotNull
        @Schema(example = "10122")
        private String zipCode;

        @NotNull
        private Region region;
    }

}
