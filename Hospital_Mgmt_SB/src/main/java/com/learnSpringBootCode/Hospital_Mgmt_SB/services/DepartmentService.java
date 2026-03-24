package com.learnSpringBootCode.Hospital_Mgmt_SB.services;

import com.learnSpringBootCode.Hospital_Mgmt_SB.entities.Department;

public interface DepartmentService {

    void deleteDepartment(Long departmentId);

    Department addDoctorInDepartment(Long departmentId, Long doctorId);
}
