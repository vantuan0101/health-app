package com.vantuan.patientmanagement.clinician.criteria;

import com.vantuan.crud.model.criteria.BaseCriteria;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.mapstruct.Mapper;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ClinicianCriteria extends BaseCriteria {

    private Long clinicianId;
    private Boolean active;
    private Boolean isVerified;
    private String firstName;
    private String lastName;
}
