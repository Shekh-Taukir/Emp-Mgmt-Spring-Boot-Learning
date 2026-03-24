package com.learnSpringBootCode.Hospital_Mgmt_SB.services;

import com.learnSpringBootCode.Hospital_Mgmt_SB.entities.PatientInsurance;

public interface PatientInsuranceService {
    PatientInsurance assignInsuranceToPatient(PatientInsurance patientInsurance, Long patientId);


}
