package com.vantuan.careplanmanagement.model.entity;

import com.vantuan.careplanmanagement.common.enums.*;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
@Table(name = "allergies")
@SuperBuilder(toBuilder = true)
public class Allergy {

    public static final int MIN_YEARS = 1900;
    public static final int MAX_YEARS = 2100;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_patient")
    private Patient patient;

    @NotNull
    private String substance;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Reaction reaction;

    @NotNull
    @Min(MIN_YEARS)
    @Max(MAX_YEARS)
    private Short onset;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Severity severity;

    private String medication;

    private String notes;

}
