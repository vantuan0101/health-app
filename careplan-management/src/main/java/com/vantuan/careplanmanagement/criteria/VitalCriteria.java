package com.vantuan.careplanmanagement.criteria;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vantuan.careplanmanagement.model.entity.Patient;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VitalCriteria {
    private Long id;
    private Short currentHeight;
    private Short currentWeight;
    private Short heartRate;
    private Float temperature;
    private Short oxygenSaturation;
    private Short systolicBloodPressure;
    private Short diastolicBloodPressure;

    @JsonIgnore
    private Patient patient;
}
