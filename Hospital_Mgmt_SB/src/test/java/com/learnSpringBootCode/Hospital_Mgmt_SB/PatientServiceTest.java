package com.learnSpringBootCode.Hospital_Mgmt_SB;

import com.learnSpringBootCode.Hospital_Mgmt_SB.dto.CPatientInfo;
import com.learnSpringBootCode.Hospital_Mgmt_SB.dto.IPatientInfo;
import com.learnSpringBootCode.Hospital_Mgmt_SB.dto.PatientBloodGroupStats;
import com.learnSpringBootCode.Hospital_Mgmt_SB.entities.Patient;
import com.learnSpringBootCode.Hospital_Mgmt_SB.repositories.PatientRepository;
import com.learnSpringBootCode.Hospital_Mgmt_SB.services.PatientService;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Modifying;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class PatientServiceTest {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PatientService patientService;

    @Test
    public void testPatient(){
        /*List<IPatientInfo> patientList = patientRepository.getAllPatientInfo();
        List<CPatientInfo> patientList = patientRepository.getAllPatientInfoConcrete();

        for(CPatientInfo patientInfo : patientList){
           System.out.println(patientInfo);
        }
         */

        /*
        List<PatientBloodGroupStats> patientList = patientRepository.getBloodGroupStatsList();

        patientList.forEach(patient->{
            System.out.println(patient);
        });

         */

//        int patientsUpdated = patientRepository.updatePatientNameWithId("Anuj Sharma", 5L);
//        System.out.println(patientsUpdated);

        List<IPatientInfo> patientInfoList = patientRepository.getAllPatientInfo();

//        patientInfoList.forEach(patient -> {
//            ObjectMapper objectMapper = new ObjectMapper();
////            Map<String, Object> map = objectMapper.convertValue(patient, Map.class);
//            Map<String, Object> map = objectMapper.convertValue(patient, Map.class);
//            System.out.println(map);
//        });

//        patientInfoList.forEach(patient->{
//
//        });
//
//        System.out.println();

        //checking Persistant context
        patientService.testPatientTransaction();

    }
}
