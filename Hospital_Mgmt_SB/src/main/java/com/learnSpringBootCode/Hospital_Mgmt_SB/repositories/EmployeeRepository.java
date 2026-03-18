package com.learnSpringBootCode.Hospital_Mgmt_SB.repositories;

import com.learnSpringBootCode.Hospital_Mgmt_SB.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

//    List<EmployeeRepository> findByName(String name);
}
