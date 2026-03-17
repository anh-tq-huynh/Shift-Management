package com.example.shift_mgmt.controller;

import com.example.shift_mgmt.entity.Client;
import com.example.shift_mgmt.entity.Company;
import com.example.shift_mgmt.entity.Employee;
import com.example.shift_mgmt.entity.Shift;
import com.example.shift_mgmt.repository.EmployeeRepository;
import com.example.shift_mgmt.service.CompanyService;
import com.example.shift_mgmt.service.EmployeeService;
import com.example.shift_mgmt.service.ShiftService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController (EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id){
        return ResponseEntity.ok(employeeService.findById(id));
    }

    @GetMapping("/{employeeId}/shifts")
    public ResponseEntity<List<Shift>> getShifts (Long employeeId){
        return ResponseEntity.ok(employeeService.findById(employeeId).getEmployeeShifts());
    }

    @GetMapping("/{employeeId}/salary")
    public double getSalary (Long employeeId){
        return employeeService.calculateSalary(employeeId);
    }

    @PutMapping("/{employeeId}/book/{shiftID}")
    public ResponseEntity<List<Shift>> bookShift (Long employeeId, Long shiftId){
        employeeService.bookShift(employeeId,shiftId);
        return ResponseEntity.ok(employeeService.findById(employeeId).getEmployeeShifts());
    }

    @PostMapping
    public Employee createEmployee (@RequestBody Employee employee){
        return ResponseEntity.status(201).body(employeeService.save(employee)).getBody();
    }

}
