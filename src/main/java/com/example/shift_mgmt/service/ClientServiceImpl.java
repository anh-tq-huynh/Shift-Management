package com.example.shift_mgmt.service;

import com.example.shift_mgmt.entity.Client;
import com.example.shift_mgmt.entity.Shift;
import com.example.shift_mgmt.repository.ClientRepository;
import com.example.shift_mgmt.repository.CompanyRepository;
import com.example.shift_mgmt.repository.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
    public void registerShift(Long clientId, LocalDate date, LocalTime start, LocalTime end){
        Client cli = clientRepository.findClientByClientId(clientId);


        LocalDateTime startTime = LocalDateTime.of(date,start);
        LocalDateTime endTime = LocalDateTime.of(date, end);
        Duration duration = Duration.between(startTime, endTime);

        double durationInHour = duration.toMinutes() /60.0;
        if (cli.getHourQuota() < durationInHour){
            throw new RuntimeException("Client doesn't have enough hour quota");
        }
        else{
            Shift newShift = new Shift();
            newShift.setStartDateTime(startTime);
            newShift.setEndDateTime(endTime);
            newShift.setClient(cli);
            cli.addShift(newShift);
        }
    }
}
