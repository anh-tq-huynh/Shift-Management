package com.example.shift_mgmt.controller;

import com.example.shift_mgmt.entity.Client;
import com.example.shift_mgmt.entity.Company;
import com.example.shift_mgmt.entity.Employee;
import com.example.shift_mgmt.service.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController (CompanyService companyService){
        this.companyService = companyService;
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable Long id){
        return ResponseEntity.ok(companyService.findById(id));
    }

    @GetMapping("/company/{id}/employees") //Fetch all employees
    public ResponseEntity<List<Employee>> getEmployees(@PathVariable Long id){
        Company company = companyService.findById(id);
        return ResponseEntity.ok(company.getEmployees());
    }

    @GetMapping("/company/{id}/clients") //Fetch all clients
    public ResponseEntity<List<Client>> getClients(@PathVariable Long id){
        Company company = companyService.findById(id);
        return ResponseEntity.ok(company.getClients());
    }

    @PostMapping("/company/{companyName}") //Create new company
    public Company addCompany ( @PathVariable String companyName){
        Company newCompany = new Company();
        newCompany.setCompanyName(companyName);
        return companyService.save(newCompany);
    }
}
