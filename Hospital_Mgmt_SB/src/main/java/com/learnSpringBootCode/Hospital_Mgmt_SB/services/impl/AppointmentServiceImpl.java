package com.learnSpringBootCode.Hospital_Mgmt_SB.services.impl;

import com.learnSpringBootCode.Hospital_Mgmt_SB.entities.Appointment;
import com.learnSpringBootCode.Hospital_Mgmt_SB.entities.Doctor;
import com.learnSpringBootCode.Hospital_Mgmt_SB.entities.Patient;
import com.learnSpringBootCode.Hospital_Mgmt_SB.repositories.AppointmentRepository;
import com.learnSpringBootCode.Hospital_Mgmt_SB.services.AppointmentService;
import com.learnSpringBootCode.Hospital_Mgmt_SB.services.helper.EntityFinder;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private AppointmentRepository appointmentRepository;
    private EntityFinder entityFinder;

    @Override
    @Transactional
    public Appointment createNewAppointment(Appointment appointment, Long patientId, Long doctorId) {
        Patient patient = entityFinder.findPatientOrThrowException(patientId);
        Doctor doctor = entityFinder.findDoctorOrThrowException(doctorId);

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        return appointmentRepository.save(appointment);
    }
}
