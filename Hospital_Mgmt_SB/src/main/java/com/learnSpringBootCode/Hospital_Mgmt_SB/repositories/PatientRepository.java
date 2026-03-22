package com.learnSpringBootCode.Hospital_Mgmt_SB.repositories;

import com.learnSpringBootCode.Hospital_Mgmt_SB.dto.CPatientInfo;
import com.learnSpringBootCode.Hospital_Mgmt_SB.dto.IPatientInfo;
import com.learnSpringBootCode.Hospital_Mgmt_SB.dto.PatientBloodGroupStats;
import com.learnSpringBootCode.Hospital_Mgmt_SB.entities.Patient;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    List<Patient> findByEmailContaining(String email);

    @Query("select p.id as id, p.name as name, p.email as email from Patient p order by p.id asc")
    List<IPatientInfo> getAllPatientInfo();

    @Query("select new com.learnSpringBootCode.Hospital_Mgmt_SB.dto.CPatientInfo(p.id, p.name) from Patient p order by p.id asc")
    List<CPatientInfo> getAllPatientInfoConcrete();

    @Query("select new com.learnSpringBootCode.Hospital_Mgmt_SB.dto.PatientBloodGroupStats(p.bloodGroup, count(1)) from Patient p group by p.bloodGroup order by p.bloodGroup ")
    List<PatientBloodGroupStats> getBloodGroupStatsList();

    @Transactional
    @Modifying
    @Query("update Patient p set p.name = :name where p.id = :id")
    int updatePatientNameWithId(@Param("name") String name, @Param("id") Long id);
}
