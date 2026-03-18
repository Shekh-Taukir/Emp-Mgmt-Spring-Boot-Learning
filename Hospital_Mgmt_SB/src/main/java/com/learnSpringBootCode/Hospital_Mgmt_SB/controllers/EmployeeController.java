package com.learnSpringBootCode.Hospital_Mgmt_SB.controllers;

import com.learnSpringBootCode.Hospital_Mgmt_SB.dto.EmployeeDTO;
import com.learnSpringBootCode.Hospital_Mgmt_SB.entities.Employee;
import com.learnSpringBootCode.Hospital_Mgmt_SB.repositories.EmployeeRepository;
import com.learnSpringBootCode.Hospital_Mgmt_SB.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
@AllArgsConstructor
public class EmployeeController {

//    @GetMapping("/getSecretMsg")
//    public String getMySuperSecretMapping(){
//        return "Secret mapping for the code";
//    }

    private final EmployeeService employeeService;

    //--------------API Definations

    @GetMapping("/list")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@RequestParam(required = false) Integer age,
                                                             @RequestParam(required = false) String sortBy){
//        return "This is the age value from param : "+age + " | sort by field : "+sortBy;
//        return employeeService.findAllEmployees();
        return ResponseEntity.ok(employeeService.findAllEmployees());
    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable("id") Long empId){
        Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeById(empId);

        return employeeDTO
                .map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
                .orElse(ResponseEntity.notFound().build());

//        if (employeeDTO==null)
//            return ResponseEntity.notFound().build();
//
//        return ResponseEntity.ok(employeeDTO);
//        return new EmployeeDTO(empId, "Taukir", "taukir@gmail.com", 23, LocalDate.of(2020, 3, 3).atStartOfDay(), true);
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody EmployeeDTO inputEmployeeDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.createNewEmployee(inputEmployeeDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@PathVariable("id") Long empId,
                                                          @RequestBody EmployeeDTO newEmployeeDto){
        return ResponseEntity.ok(employeeService.updateEmployeeById(empId, newEmployeeDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployeeBYId(@PathVariable("id") Long empId){
        Boolean lbResult =  employeeService.deleteEmployeeById(empId);
        if (lbResult)
            return ResponseEntity.ok("Employee Data deleted");

        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@PathVariable("id")Long empId,
                                                                 @RequestBody Map<String, Object> updates){
        EmployeeDTO employeeDTO =  employeeService.updatePartialEmployeeById(empId, updates);
        if (employeeDTO==null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(employeeDTO);
    }

}
