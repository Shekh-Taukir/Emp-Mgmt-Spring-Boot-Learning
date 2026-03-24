package com.learnSpringBootCode.Hospital_Mgmt_SB.services.impl;

import com.learnSpringBootCode.Hospital_Mgmt_SB.entities.Department;
import com.learnSpringBootCode.Hospital_Mgmt_SB.entities.Doctor;
import com.learnSpringBootCode.Hospital_Mgmt_SB.repositories.DepartmentRepository;
import com.learnSpringBootCode.Hospital_Mgmt_SB.services.DepartmentService;
import com.learnSpringBootCode.Hospital_Mgmt_SB.services.helper.EntityFinder;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;
    private EntityFinder entityFinder;


    @Override
    @Transactional
    public void deleteDepartment(Long departmentId) {
        Department department = entityFinder.findDepartmentOrThrowException(departmentId);

        departmentRepository.delete(department);
    }

    @Override
    @Transactional
    public Department addDoctorInDepartment(Long departmentId, Long doctorId) {
        Department department = entityFinder.findDepartmentOrThrowException(departmentId);
        Doctor doctor = entityFinder.findDoctorOrThrowException(doctorId);

        department.getDoctors().add(doctor);
        doctor.getDepartments().add(department);

        return department;
    }
}
