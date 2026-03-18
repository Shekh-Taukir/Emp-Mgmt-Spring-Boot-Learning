package com.learnSpringBootCode.Hospital_Mgmt_SB.services;

import com.learnSpringBootCode.Hospital_Mgmt_SB.dto.EmployeeDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface EmployeeService {

    Optional<EmployeeDTO> getEmployeeById(Long empId);

    List<EmployeeDTO> findAllEmployees();

    EmployeeDTO createNewEmployee(EmployeeDTO inputEmployeedto);

    EmployeeDTO updateEmployeeById(Long empId, EmployeeDTO newEmployeeDto);

    boolean deleteEmployeeById(Long empId);

    EmployeeDTO updatePartialEmployeeById(Long empId, Map<String, Object>  updates);
}
