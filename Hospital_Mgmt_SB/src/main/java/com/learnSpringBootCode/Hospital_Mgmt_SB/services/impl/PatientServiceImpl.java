package com.learnSpringBootCode.Hospital_Mgmt_SB.services.impl;

import com.learnSpringBootCode.Hospital_Mgmt_SB.entities.Patient;
import com.learnSpringBootCode.Hospital_Mgmt_SB.repositories.PatientRepository;
import com.learnSpringBootCode.Hospital_Mgmt_SB.services.PatientService;
import com.learnSpringBootCode.Hospital_Mgmt_SB.services.helper.EntityFinder;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private EntityFinder entityFinder;

    @Transactional
    public void testPatientTransaction(){
        Patient p1 = patientRepository.findById(2L).orElseThrow();

        Patient p2 = patientRepository.findById(2L).orElseThrow();

        System.out.println(p1);

        System.out.println(p2);

        System.out.println(p1 == p2);
    }

    @Transactional
    public void deletePatient(Long patientId){
        Patient patient = entityFinder.findPatientOrThrowException(patientId);
        patientRepository.delete(patient);
    }
}
