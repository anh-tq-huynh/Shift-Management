package com.example.shift_mgmt.service;

import com.example.shift_mgmt.entity.Client;
import com.example.shift_mgmt.entity.Shift;
import com.example.shift_mgmt.repository.ClientRepository;
import com.example.shift_mgmt.repository.CompanyRepository;
import com.example.shift_mgmt.repository.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class ClientServiceImpl extends GenericServiceImpl<Client,Long> implements ClientService{

    private final CompanyRepository companyRepository;
    private final ShiftRepository shiftRepository;
    private final ClientRepository clientRepository;
    public ClientServiceImpl(ClientRepository clientRepository,
                             ShiftRepository shiftRepository,
                             CompanyRepository companyRepository){
        super(clientRepository);
        this.shiftRepository = shiftRepository;
        this.companyRepository = companyRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public void registerShift(Long clientId, Long shiftId){
        Client client = clientRepository.findClientByClientId(clientId);
        Shift shift = shiftRepository.findShiftByShiftId(shiftId);
        shift.setClient(client);
        client.addShift(shift);
    }
}
