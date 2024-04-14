package com.vantuan.careplanmanagement.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "vitals")
@SuperBuilder(toBuilder = true)
public class Vital {

    public static final int VITALS_MIN = 0;
    public static final int VITALS_MAX = 300;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_patient")
    private Patient patient;

    @NotNull
    @Min(VITALS_MIN)
    @Max(VITALS_MAX)
    private Short currentHeight;

    @NotNull
    @Min(VITALS_MIN)
    @Max(VITALS_MAX)
    private Short currentWeight;

    @NotNull
    @Min(VITALS_MIN)
    @Max(VITALS_MAX)
    private Short heartRate;

    @NotNull
    @Min(VITALS_MIN)
    @Max(VITALS_MAX)
    private Float temperature;

    @NotNull
    @Min(VITALS_MIN)
    @Max(VITALS_MAX)
    private Short oxygenSaturation;

    @NotNull
    @Min(VITALS_MIN)
    @Max(VITALS_MAX)
    private Short systolicBloodPressure;

    @NotNull
    @Min(VITALS_MIN)
    @Max(VITALS_MAX)
    private Short diastolicBloodPressure;

    @CreationTimestamp
    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Instant createdAt;

    @UpdateTimestamp
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Instant updatedAt;
}
