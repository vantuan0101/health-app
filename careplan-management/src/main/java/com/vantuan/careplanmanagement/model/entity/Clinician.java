package com.vantuan.careplanmanagement.model.entity;

import com.vantuan.careplanmanagement.common.address.model.entity.UserAddress;
import com.vantuan.careplanmanagement.common.enums.Country;
import com.vantuan.careplanmanagement.common.enums.Gender;
import com.vantuan.careplanmanagement.common.enums.Region;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

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

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_user_address")
    private UserAddress userAddress;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Country countryOfLicense;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Region stateOfPrimaryLicense;

    @NotNull
    private String licenseNumber;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull
    private LocalDate birthDate;

    @OneToMany(mappedBy = "clinician")
    private List<Patient> patients;

    public String getFullName() {
        return firstName + SPACE + lastName;
    }
}
