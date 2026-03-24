package com.learnSpringBootCode.Hospital_Mgmt_SB;

import com.learnSpringBootCode.Hospital_Mgmt_SB.entities.Appointment;
import com.learnSpringBootCode.Hospital_Mgmt_SB.entities.PatientInsurance;
import com.learnSpringBootCode.Hospital_Mgmt_SB.services.AppointmentService;
import com.learnSpringBootCode.Hospital_Mgmt_SB.services.PatientInsuranceService;
import com.learnSpringBootCode.Hospital_Mgmt_SB.services.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
public class PatientInsuranceTest {

    @Autowired
    private PatientService patientService;
    @Autowired
    private PatientInsuranceService patientInsuranceService;



    @Test
    public void testAssignInsuranceToPatient(){
        PatientInsurance patientInsurance = PatientInsurance.builder()
                .provider("HDFC Group")
                .policyNumber("HDFC_3289")
                .validUntil(LocalDate.of(2028,3,9))
                .build();

        PatientInsurance patientInsuranceUpdated = patientInsuranceService.assignInsuranceToPatient(patientInsurance,1L);

        System.out.println(patientInsuranceUpdated);

        patientService.deletePatient(1L);
    }

}
