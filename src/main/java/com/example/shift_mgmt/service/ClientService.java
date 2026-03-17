package com.example.shift_mgmt.service;

import com.example.shift_mgmt.entity.Client;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public interface ClientService extends GenericService<Client, Long>{
    void registerShift(Long clientId, LocalDate date, LocalTime start, LocalTime end);
}
