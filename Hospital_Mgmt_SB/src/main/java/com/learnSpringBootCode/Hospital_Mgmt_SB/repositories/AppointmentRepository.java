package com.learnSpringBootCode.Hospital_Mgmt_SB.repositories;

import com.learnSpringBootCode.Hospital_Mgmt_SB.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}