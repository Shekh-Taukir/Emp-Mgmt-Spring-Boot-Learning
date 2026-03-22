package com.learnSpringBootCode.Hospital_Mgmt_SB.controllers;

import com.learnSpringBootCode.Hospital_Mgmt_SB.services.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/patient")
@AllArgsConstructor
public class PatientController {
    private final PatientService patientService;
}
