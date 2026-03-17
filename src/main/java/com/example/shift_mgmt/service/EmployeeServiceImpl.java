package com.example.shift_mgmt.service;

import com.example.shift_mgmt.entity.Employee;
import com.example.shift_mgmt.entity.Shift;
import com.example.shift_mgmt.entity.ShiftStatus;
import com.example.shift_mgmt.repository.EmployeeRepository;
import com.example.shift_mgmt.repository.ShiftRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class EmployeeServiceImpl extends GenericServiceImpl <Employee, Long> implements EmployeeService {
    private final ShiftRepository shiftRepository;
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl (EmployeeRepository employee, ShiftRepository shiftRepository){
        super(employee);
        this.shiftRepository = shiftRepository;
        this.employeeRepository = employee;
    }

    @Override
    public void bookShift(Long empId, Long shiftID) {
        Employee emp = employeeRepository.findEmployeeByEmployeeId(empId);
        Shift shift = shiftRepository.findShiftByShiftId(shiftID);

        shift.setEmployee(emp);
        shift.setStatus(ShiftStatus.BOOKED);

        shiftRepository.save(shift);
        emp.addShift(shift);
    }



    @Override
    public double calculateSalary(Long empID) {
        Employee employee = employeeRepository.findEmployeeByEmployeeId(empID);
        return employee.getSalaryRate() * employee.getEmployeeTotalHours();
    }
}
