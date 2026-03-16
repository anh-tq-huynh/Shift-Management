package com.example.shift_mgmt.service;

import com.example.shift_mgmt.entity.Company;
import com.example.shift_mgmt.repository.CompanyRepository;
import java.util.ArrayList;
import java.util.Objects;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService{
    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepo){
        this.companyRepository = companyRepo;
    }

    @Override
    public Company saveCompany (Company company){
       return companyRepository.save(company);
    }

    @Override
    public ArrayList<Company> fetchCompanyList() {
        return (ArrayList<Company>) companyRepository.findAll();
    }

    @Override
    public Company updateCompany(Company company, Long companyId) {
        Company findCompany = companyRepository.findById(companyId);
    }

    @Override
    public void deleteCompanyById(Long CompanyId) {

    }

}
