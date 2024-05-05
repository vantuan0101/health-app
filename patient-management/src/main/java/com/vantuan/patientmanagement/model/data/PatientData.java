package com.vantuan.patientmanagement.model.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vantuan.patientmanagement.common.enums.*;
import com.vantuan.patientmanagement.model.entity.Clinician;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

import static com.vantuan.patientmanagement.model.entity.Clinician.FIRST_NAME_MAX_SIZE;
import static com.vantuan.patientmanagement.model.entity.Clinician.LAST_NAME_MAX_SIZE;

@NoArgsConstructor
public final class PatientData {

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

    private String photo;

    private Status status;

    @NotNull
    private String email;

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

    private String clinicianId;

    @JsonIgnore
    private Clinician clinician;
  }
}
