package com.example.shift_mgmt.service;

import com.example.shift_mgmt.entity.Company;
import com.example.shift_mgmt.repository.CompanyRepository;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentMap;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl extends GenericServiceImpl<Company, Long> implements CompanyService{

    public CompanyServiceImpl(CompanyRepository companyRepo){
        super(companyRepo);
    }

    @Override
    public Company updateCompany(Company companyInput, Long companyId) {
        //Find the company instance in the database
        Optional<Company> companyOptional = repository.findById(companyId);

        //Update information if the data is valid
        if (companyOptional.isPresent()){
            Company companyDB = companyOptional.get();
            if (Objects.nonNull(companyInput.getCompanyName())){
                companyDB.setCompanyName(companyInput.getCompanyName());
            }
            return repository.save(companyDB);
        }
        return null;
    }
}
