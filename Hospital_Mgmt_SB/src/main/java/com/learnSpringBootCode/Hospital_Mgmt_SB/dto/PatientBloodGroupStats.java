package com.learnSpringBootCode.Hospital_Mgmt_SB.dto;

import com.learnSpringBootCode.Hospital_Mgmt_SB.entities.type.UserBloodGroupEnum;
import lombok.Data;

@Data
public class PatientBloodGroupStats {
    private final UserBloodGroupEnum bloodGroupType;
    private final Long count;
}
