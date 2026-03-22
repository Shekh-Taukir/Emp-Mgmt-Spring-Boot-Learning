package com.learnSpringBootCode.Hospital_Mgmt_SB.dto;

import lombok.ToString;

import java.time.LocalDate;


public interface IPatientInfo {
    Long getId();
    String getName();
    String getEmail();

}
