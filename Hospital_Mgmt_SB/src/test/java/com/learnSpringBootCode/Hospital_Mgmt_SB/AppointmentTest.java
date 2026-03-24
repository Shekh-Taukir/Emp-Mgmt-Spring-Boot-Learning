package com.learnSpringBootCode.Hospital_Mgmt_SB;

import com.learnSpringBootCode.Hospital_Mgmt_SB.entities.Appointment;
import com.learnSpringBootCode.Hospital_Mgmt_SB.entities.Department;
import com.learnSpringBootCode.Hospital_Mgmt_SB.services.AppointmentService;
import com.learnSpringBootCode.Hospital_Mgmt_SB.services.DepartmentService;
import com.learnSpringBootCode.Hospital_Mgmt_SB.services.DoctorService;
import com.learnSpringBootCode.Hospital_Mgmt_SB.services.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class AppointmentTest {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private DepartmentService departmentService;

    @Test
    public void testAppointmentCreation(){
        Appointment appointment = Appointment.builder()
                .reason("Cold thyroid")
                .appointmentTime(LocalDateTime.now())
                .build();

        System.out.println(appointmentService.createNewAppointment(appointment,16L, 1L));

//        patientService.deletePatient(18L);
    }

    @Test
    public void deleteDoctor(){
        doctorService.deleteDoctorById(1L);
    }

    @Test
    public void addDoctorInDepartment(){
        Department department = departmentService.addDoctorInDepartment(1L, 2L);

        System.out.println(department);
    }

    @Test
    public void deleteDepartment(){
        departmentService.deleteDepartment(1L);
    }

}
