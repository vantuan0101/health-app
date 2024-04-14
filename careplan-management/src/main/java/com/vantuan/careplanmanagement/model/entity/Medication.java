package com.vantuan.careplanmanagement.model.entity;

import com.vantuan.careplanmanagement.common.enums.*;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@Table(name = "medications")
@SuperBuilder(toBuilder = true)
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_patient")
    private Patient patient;

    @NotNull
    private String medicineName;

    @NotNull
    private String quantity;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Unit unit;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate estimatedEndDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private DoseUnit doseUnit;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Frequency frequency;

    private String notes;

}
