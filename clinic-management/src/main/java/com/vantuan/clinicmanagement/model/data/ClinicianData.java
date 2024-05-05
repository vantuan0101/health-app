package com.vantuan.clinicmanagement.model.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vantuan.clinicmanagement.common.enums.Country;
import com.vantuan.clinicmanagement.common.enums.Gender;
import com.vantuan.clinicmanagement.common.enums.Region;

import com.vantuan.clinicmanagement.model.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

import static com.vantuan.clinicmanagement.model.entity.Clinician.FIRST_NAME_MAX_SIZE;
import static com.vantuan.clinicmanagement.model.entity.Clinician.LAST_NAME_MAX_SIZE;

@NoArgsConstructor
public final class ClinicianData {

  @Getter
  @Setter
  @SuperBuilder(toBuilder = true)
  @NoArgsConstructor
  public abstract static class Base {

    @NotNull
    @Size(max = FIRST_NAME_MAX_SIZE)
    @Schema(example = "John")
    private String firstName;

    @NotNull
    @Size(max = LAST_NAME_MAX_SIZE)
    @Schema(example = "Kowalski")
    private String lastName;

    @NotNull
    private Boolean isVerified;

    @NotNull
    private Boolean active;

    @NotNull
    private Gender gender;

    @NotNull
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
  @SuperBuilder(toBuilder = true)
  @NoArgsConstructor
  public static class Edit extends Base {
  }

  @Getter
  @Setter
  @SuperBuilder(toBuilder = true)
  @NoArgsConstructor
  public static class Create extends Base {

    private String userId;

    @JsonIgnore
    private User user;

    private Country countryOfLicense;

    private Region stateOfPrimaryLicense;

    @Schema(example = "35007")
    private String licenseNumber;
  }
}
