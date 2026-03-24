package com.learnSpringBootCode.Hospital_Mgmt_SB.repositories;

import com.learnSpringBootCode.Hospital_Mgmt_SB.entities.PatientInsurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientInsuranceRepository extends JpaRepository<PatientInsurance, Long> {
}