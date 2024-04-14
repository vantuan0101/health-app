package com.vantuan.careplanmanagement.criteria;

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
    private Patient patient;
}
