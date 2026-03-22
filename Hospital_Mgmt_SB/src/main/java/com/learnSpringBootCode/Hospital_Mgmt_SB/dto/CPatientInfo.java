package com.learnSpringBootCode.Hospital_Mgmt_SB.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CPatientInfo {

    private Long id;
    private String name;
}
