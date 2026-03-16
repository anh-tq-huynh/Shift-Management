package com.example.shift_mgmt.service;

import com.example.shift_mgmt.entity.Company;
import java.util.ArrayList;

public interface CompanyService {
    Company saveCompany (Company company);
    ArrayList<Company> fetchCompanyList();
    Company updateCompany(Company company, Long companyId);
    void deleteCompanyById(Long CompanyId);
}
