package com.learnSpringBootCode.Hospital_Mgmt_SB.services.helper;

import com.learnSpringBootCode.Hospital_Mgmt_SB.entities.Department;
import com.learnSpringBootCode.Hospital_Mgmt_SB.entities.Doctor;
import com.learnSpringBootCode.Hospital_Mgmt_SB.entities.Patient;
import com.learnSpringBootCode.Hospital_Mgmt_SB.exceptions.ResourceNotFoundException;
import com.learnSpringBootCode.Hospital_Mgmt_SB.repositories.DepartmentRepository;
import com.learnSpringBootCode.Hospital_Mgmt_SB.repositories.DoctorRepository;
import com.learnSpringBootCode.Hospital_Mgmt_SB.repositories.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EntityFinder {


    private PatientRepository patientRepository;
    private DoctorRepository doctorRepository;
    private DepartmentRepository departmentRepository;

    public Patient findPatientOrThrowException(Long patientId){
        return patientRepository
                .findById(patientId)
                .orElseThrow(()->new ResourceNotFoundException("Patient not found in ID: "+ patientId));
    }

    public Doctor findDoctorOrThrowException(Long doctorId) {
        return doctorRepository
                .findById(doctorId)
                .orElseThrow(()->new ResourceNotFoundException(("Doctor not found for Id: "+doctorId)));
    }

    public Department findDepartmentOrThrowException(Long departmentId) {
        return departmentRepository
                .findById(departmentId)
                .orElseThrow(()->new ResourceNotFoundException(("Department not found for Id: "+departmentId)));
    }
}
