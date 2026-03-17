package com.example.shift_mgmt.service;

import com.example.shift_mgmt.entity.Client;
import com.example.shift_mgmt.entity.Company;
import com.example.shift_mgmt.entity.Employee;

import java.util.ArrayList;

public interface CompanyService extends GenericService<Company, Long> {
    Company updateCompany(Company company, Long companyId);
    void hireEmployee(Long companyId, Long employeeId);
    void addClient(Long companyId, Long clientId);
}
