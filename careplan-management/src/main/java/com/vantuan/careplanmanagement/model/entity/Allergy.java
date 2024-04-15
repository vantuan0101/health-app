package com.vantuan.careplanmanagement.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vantuan.careplanmanagement.common.enums.*;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.Instant;

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
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
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

    @CreationTimestamp
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Instant createdAt;

    @UpdateTimestamp
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Instant updatedAt;

}
