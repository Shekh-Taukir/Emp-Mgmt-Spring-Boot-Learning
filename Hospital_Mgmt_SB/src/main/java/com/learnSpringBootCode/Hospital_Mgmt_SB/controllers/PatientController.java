package com.learnSpringBootCode.Hospital_Mgmt_SB.controllers;

import com.learnSpringBootCode.Hospital_Mgmt_SB.dto.PatientDTO;
import com.learnSpringBootCode.Hospital_Mgmt_SB.services.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/admin/patient")
@AllArgsConstructor
public class PatientController {
    private final PatientService patientService;

    @GetMapping("/list")
    public ResponseEntity<List<PatientDTO>> getPatientList(){
        return ResponseEntity.ok(patientService.getPatientList());
    }

    @PostMapping
    public ResponseEntity<PatientDTO> createPatient(@RequestBody PatientDTO patientDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.createPatient(patientDTO));
    }

}
