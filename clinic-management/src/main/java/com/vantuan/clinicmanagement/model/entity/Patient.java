package com.vantuan.clinicmanagement.model.entity;

import com.vantuan.clinicmanagement.common.enums.*;
import jakarta.persistence.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

import lombok.*;
import lombok.experimental.SuperBuilder;

import static org.apache.commons.lang3.StringUtils.SPACE;

@Data
@Entity
@NoArgsConstructor
@Table(name = "patients")
@SuperBuilder(toBuilder = true)
public class Patient {
    public static final int FIRST_NAME_MAX_SIZE = 150;
    public static final int LAST_NAME_MAX_SIZE = 150;
    public static final int HEIGHT_MIN = 0;
    public static final int WEIGHT_MIN = 0;
    public static final int WEIGHT_MAX = 300;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    @NotNull
    @Size(max = FIRST_NAME_MAX_SIZE)
    private String firstName;

    @NotNull
    @Size(max = LAST_NAME_MAX_SIZE)
    private String lastName;

    private String photo;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull
    private LocalDate birthDate;

    private String address;

    private String city;

    @Enumerated(EnumType.STRING)
    private Country country;

    private String zipCode;

    @Enumerated(EnumType.STRING)
    private Region region;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_clinician")
    private Clinician clinician;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull
    @Min(HEIGHT_MIN)
    private Short height;

    @NotNull
    @Min(WEIGHT_MIN)
    @Max(WEIGHT_MAX)
    private Float weight;

    public String getFullName() {
        return firstName + SPACE + lastName;
    }
}
