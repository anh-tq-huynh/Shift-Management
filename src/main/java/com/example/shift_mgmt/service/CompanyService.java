package com.example.shift_mgmt.service;

import com.example.shift_mgmt.entity.Company;
import java.util.ArrayList;

public interface CompanyService extends GenericService<Company, Long> {
    Company updateCompany(Company company, Long companyId);
}
