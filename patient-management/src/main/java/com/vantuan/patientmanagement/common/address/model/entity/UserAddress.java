package com.vantuan.patientmanagement.common.address.model.entity;

import com.vantuan.patientmanagement.clinician.model.entity.Clinician;
import com.vantuan.patientmanagement.enums.*;
import com.vantuan.patientmanagement.model.entity.Patient;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;


@Data
@Entity
@NoArgsConstructor
@Table(name = "users_addresses")
@SuperBuilder(toBuilder = true)
public class UserAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    private String city;

    @Enumerated(EnumType.STRING)
    private Country country;

    private String zipCode;

    @Enumerated(EnumType.STRING)
    private Region region;

    @ToString.Exclude
    @OneToOne(mappedBy = "userAddress")
    private Clinician clinician;

    @ToString.Exclude
    @OneToOne(mappedBy = "userAddress")
    private Patient patient;

}
