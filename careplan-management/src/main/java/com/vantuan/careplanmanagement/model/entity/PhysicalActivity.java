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
@Table(name = "physical_activities")
@SuperBuilder(toBuilder = true)
public class PhysicalActivity {

    public static final int PHYSICAL_ACTIVITY_MIN_DAYS = 0;
    public static final int PHYSICAL_ACTIVITY_MAX_DAYS = 7;
    public static final int PHYSICAL_ACTIVITY_MIN_MINUTES = 0;
    public static final int PHYSICAL_ACTIVITY_MAX_MINUTES = 60;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_patient")
    private Patient patient;

    @NotNull
    @Min(PHYSICAL_ACTIVITY_MIN_DAYS)
    @Max(PHYSICAL_ACTIVITY_MAX_DAYS)
    private Short daysOfModerateActivity;

    @NotNull
    @Min(PHYSICAL_ACTIVITY_MIN_MINUTES)
    @Max(PHYSICAL_ACTIVITY_MAX_MINUTES)
    private Short minutesOfModerateActivity;

    @NotNull
    @Min(PHYSICAL_ACTIVITY_MIN_DAYS)
    @Max(PHYSICAL_ACTIVITY_MAX_DAYS)
    private Short daysOfVigorousActivity;

    @NotNull
    @Min(PHYSICAL_ACTIVITY_MIN_MINUTES)
    @Max(PHYSICAL_ACTIVITY_MAX_MINUTES)
    private Short minutesOfVigorousActivity;

    @CreationTimestamp
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Instant createdAt;

    @UpdateTimestamp
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Instant updatedAt;

}
