package com.learnSpringBootCode.Hospital_Mgmt_SB.services;

import com.learnSpringBootCode.Hospital_Mgmt_SB.entities.Appointment;

public interface AppointmentService {
    Appointment createNewAppointment(Appointment appointment, Long patientId, Long doctorId);
}
