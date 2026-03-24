package com.learnSpringBootCode.Hospital_Mgmt_SB.services.impl;

import com.learnSpringBootCode.Hospital_Mgmt_SB.entities.Patient;
import com.learnSpringBootCode.Hospital_Mgmt_SB.entities.PatientInsurance;
import com.learnSpringBootCode.Hospital_Mgmt_SB.exceptions.ResourceNotFoundException;
import com.learnSpringBootCode.Hospital_Mgmt_SB.repositories.PatientInsuranceRepository;
import com.learnSpringBootCode.Hospital_Mgmt_SB.repositories.PatientRepository;
import com.learnSpringBootCode.Hospital_Mgmt_SB.services.PatientInsuranceService;
import com.learnSpringBootCode.Hospital_Mgmt_SB.services.helper.EntityFinder;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PatientInsuranceServiceImpl implements PatientInsuranceService {
    private PatientRepository patientRepository;
    private PatientInsuranceRepository patientInsuranceRepository;
    private EntityFinder entityFinder;

    @Transactional
    public PatientInsurance assignInsuranceToPatient(PatientInsurance patientInsurance, Long patientId){

        Patient patient = entityFinder.findPatientOrThrowException(patientId);

        //as patient is owning the insurance, then have to set in patient object.
        patient.setInsurance(patientInsurance);

        //optional
        patientInsurance.setPatient(patient);
        return patientInsurance;
    }
}
