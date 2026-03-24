package com.learnSpringBootCode.Hospital_Mgmt_SB.services.impl;

import com.learnSpringBootCode.Hospital_Mgmt_SB.entities.Doctor;
import com.learnSpringBootCode.Hospital_Mgmt_SB.repositories.DoctorRepository;
import com.learnSpringBootCode.Hospital_Mgmt_SB.services.DoctorService;
import com.learnSpringBootCode.Hospital_Mgmt_SB.services.helper.EntityFinder;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private DoctorRepository doctorRepository;
    private EntityFinder entityFinder;

    @Override
//    @Transactional
    public void deleteDoctorById(Long doctorId) {
        Doctor doctor = entityFinder.findDoctorOrThrowException(doctorId);
        doctorRepository.delete(doctor);
    }
}
