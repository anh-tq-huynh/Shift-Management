package com.example.shift_mgmt.service;

import com.example.shift_mgmt.entity.*;
import com.example.shift_mgmt.repository.ClientRepository;
import com.example.shift_mgmt.repository.CompanyRepository;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentMap;

import com.example.shift_mgmt.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl extends GenericServiceImpl<Company, Long> implements CompanyService{

    private final EmployeeRepository employeeRepository;
    private  final ClientRepository clientRepository;
    private final CompanyRepository companyRepository;
    public CompanyServiceImpl(CompanyRepository companyRepo,
                              EmployeeRepository employeeRepository,
                              ClientRepository clientRepository){
        super(companyRepo);
        this.employeeRepository = employeeRepository;
        this.clientRepository = clientRepository;
        this.companyRepository = companyRepo;
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

    @Override
    public void hireEmployee(Long companyId, Long employeeId){
        Employee newEmployee = employeeRepository.findEmployeeByEmployeeId(employeeId);
        Company company = companyRepository.findCompaniesByCompanyId(companyId);

        company.hireEmployee(newEmployee);
        companyRepository.save(company);
        employeeRepository.save(newEmployee);
    }

    @Override
    public void addClient (Long companyId, Long clientId){
        Client client = clientRepository.findClientByClientId(clientId);
        Company company = companyRepository.findCompaniesByCompanyId(companyId);

        company.addClient(client);
        companyRepository.save(company);
        clientRepository.save(client);
    }

}
