package com.vantuan.patientmanagement.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vantuan.patientmanagement.common.enums.*;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.SPACE;

@Data
@Entity
@NoArgsConstructor
@Table(name = "clinicians")
@SuperBuilder(toBuilder = true)
public class Clinician {

    public static final int FIRST_NAME_MAX_SIZE = 150;
    public static final int LAST_NAME_MAX_SIZE = 150;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Builder.Default
    private Boolean isVerified = false;

    @NotNull
    @Builder.Default
    private Boolean active = false;

    @NotNull
    @Size(max = FIRST_NAME_MAX_SIZE)
    private String firstName;

    @NotNull
    @Size(max = LAST_NAME_MAX_SIZE)
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Country countryOfLicense;

    @Enumerated(EnumType.STRING)
    private Region stateOfPrimaryLicense;

    private String licenseNumber;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull
    private LocalDate birthDate;

    private String address;

    private String city;

    private Country country;

    private String zipCode;

    private Region region;

    @OneToMany(mappedBy = "clinician")
    private List<Patient> patients;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private User user;

    public String getFullName() {
        return firstName + SPACE + lastName;
    }
}
