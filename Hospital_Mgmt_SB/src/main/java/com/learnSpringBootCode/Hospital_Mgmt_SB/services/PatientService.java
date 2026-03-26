package com.learnSpringBootCode.Hospital_Mgmt_SB.services;

import com.learnSpringBootCode.Hospital_Mgmt_SB.dto.PatientDTO;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface PatientService {
    public void testPatientTransaction();

    void deletePatient(Long patientId);

    List<PatientDTO> getPatientList();

    PatientDTO createPatient(PatientDTO patientDTO);
}
