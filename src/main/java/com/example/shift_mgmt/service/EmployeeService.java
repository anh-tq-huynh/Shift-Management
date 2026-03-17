package com.example.shift_mgmt.service;
import com.example.shift_mgmt.entity.Employee;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public interface EmployeeService extends GenericService<Employee, Long> {
     void bookShift(Long empId, Long shiftID);
     public double calculateSalary(Long empID);

}
