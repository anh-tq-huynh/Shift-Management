package com.example.shift_mgmt.repository;

import com.example.shift_mgmt.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    Employee findEmployeeByEmployeeId(long employeeId);
}

