package com.example.shift_mgmt.controller;

import com.example.shift_mgmt.entity.Client;
import com.example.shift_mgmt.entity.Company;
import com.example.shift_mgmt.entity.Employee;
import com.example.shift_mgmt.service.CompanyService;
import com.example.shift_mgmt.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController (CompanyService companyService, EmployeeService employeeService){
        this.companyService = companyService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable Long id){
        return ResponseEntity.ok(companyService.findById(id));
    }

    @GetMapping("/{id}/employees") //Fetch all employees
    public ResponseEntity<List<Employee>> getEmployees(@PathVariable Long id){
        Company company = companyService.findById(id);
        return ResponseEntity.ok(company.getEmployees());
    }

    @GetMapping("/{id}/clients") //Fetch all clients
    public ResponseEntity<List<Client>> getClients(@PathVariable Long id){
        Company company = companyService.findById(id);
        return ResponseEntity.ok(company.getClients());
    }

    @PutMapping("/{companyId}/hire/{employeeId}")
    public ResponseEntity<List<Employee>> hireEmployee (@PathVariable long companyId,
                                                        @PathVariable long employeeId){
        companyService.hireEmployee(companyId, employeeId);
        return getEmployees(companyId);
    }

    @PutMapping("/{companyId}/add_client/{clientId}")
    public ResponseEntity<List<Client>> addClient (@PathVariable long companyId,
                                                   @PathVariable long clientId){
        companyService.addClient(companyId,clientId);
        return getClients(companyId);
    }

    @PostMapping //Create new company
    public Company addCompany ( @RequestBody Company company){
        return ResponseEntity.status(201).body(companyService.save(company)).getBody();
    }
}
