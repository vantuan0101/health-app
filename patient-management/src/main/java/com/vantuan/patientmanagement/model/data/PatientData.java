package com.vantuan.patientmanagement.model;

import com.vantuan.patientmanagement.common.address.model.data.UserAddressData;
import com.vantuan.patientmanagement.enums.Gender;
import com.vantuan.patientmanagement.repo.common.StructMapper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

import static com.vantuan.patientmanagement.model.Patient.*;

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

        @NotNull
        @Schema(type = "string", format = "date")
        private LocalDate birthDate;

        @Valid
        @NotNull
        private UserAddressData.PatientCreate userAddress;

        @NotNull
        private Gender gender;

        @NotNull
        @Min(HEIGHT_MIN)
        @Schema(example = "180")
        private Short height;

        @NotNull
        @Min(WEIGHT_MIN)
        @Max(WEIGHT_MAX)
        @Schema(example = "80")
        private Float weight;

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

//        @JsonIgnore
//        private Clinician clinician;

    }

    @Mapper(builder = @Builder(disableBuilder = true))
    public abstract static class PatientCreateMapper extends StructMapper<Create, Patient> {
    }

    @Mapper(builder = @Builder(disableBuilder = true))
    public abstract static class PatientEditMapper extends StructMapper<Edit, Patient> {
    }

}

