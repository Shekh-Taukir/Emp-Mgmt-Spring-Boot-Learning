package com.learnSpringBootCode.Hospital_Mgmt_SB.services.impl;

import com.learnSpringBootCode.Hospital_Mgmt_SB.dto.EmployeeDTO;
import com.learnSpringBootCode.Hospital_Mgmt_SB.entities.Employee;
import com.learnSpringBootCode.Hospital_Mgmt_SB.repositories.EmployeeRepository;
import com.learnSpringBootCode.Hospital_Mgmt_SB.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    //Internal Methods
    public boolean isEmployeeExistsById(Long empId){
        if(employeeRepository.existsById(empId))
            return true;
        return false;
    }

    @Override
    public Optional<EmployeeDTO> getEmployeeById(Long empId) {
//        Employee employee = employeeRepository.findById(empId).orElseThrow(()->new IllegalArgumentException("Employee not found on id : "+empId));
//        return employeeRepository.findById(empId).map(employee -> modelMapper.map(employee,EmployeeDTO.class));

//        return  modelMapper.map(employee, EmployeeDTO.class);

        return employeeRepository.findById(empId).map(employee -> modelMapper.map(employee,EmployeeDTO.class));
    }

    @Override
    public List<EmployeeDTO> findAllEmployees() {
        List<Employee> employees =  employeeRepository.findAll();

        return employees
                .stream()
                .map(employee -> modelMapper.map(employee, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO createNewEmployee(EmployeeDTO inputEmployeeDto) {
        Employee newEmployee = modelMapper.map(inputEmployeeDto, Employee.class);
        newEmployee =  employeeRepository.save(newEmployee);
        return modelMapper.map(newEmployee, EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO updateEmployeeById(Long empId, EmployeeDTO newEmployeeDto) {
        Employee employee = modelMapper.map(newEmployeeDto, Employee.class);
        employee.setId(empId);
        employee = employeeRepository.save(employee);
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    @Override
    public boolean deleteEmployeeById(Long empId) {
        boolean empExists =  isEmployeeExistsById(empId);
        if (empExists){
            employeeRepository.deleteById(empId);
        }

        return empExists;
    }

    @Override
    public EmployeeDTO updatePartialEmployeeById(Long empId, Map<String, Object> updates) {
        boolean empExists =  isEmployeeExistsById(empId);
        if (!empExists)
            return null;

        Employee employee = employeeRepository.findById(empId).get();

        updates.forEach((key, value)->{
            Field fieldToBeUpdated = ReflectionUtils.getRequiredField(Employee.class,key);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated,employee,value);
        });

        return modelMapper.map(employeeRepository.save(employee), EmployeeDTO.class);
    }
}

/*
//            switch (key){
//                case "name":
//                    employee.setName((String)value);
//                    break;
//
//                case "email":
//                    employee.setEmail((String)value);
//                    break;
//
//                case "age":
//                    employee.setAge((Integer) value);
//                    break;
//
//                case "dateOfJoining":
//                    employee.setDateOfJoining((LocalDateTime) value);
//                    break;
//
//                case "isActive":
//                    employee.setName((String)value);
//                    break;
* */