package com.example.shift_mgmt.controller;

import com.example.shift_mgmt.entity.Client;
import com.example.shift_mgmt.entity.Company;
import com.example.shift_mgmt.entity.Shift;
import com.example.shift_mgmt.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;
    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClient(@PathVariable Long id){
        return ResponseEntity.ok(clientService.findById(id));
    }

    @GetMapping("/{clientId}/shifts")
    public ResponseEntity<List<Shift>> getShifts (@PathVariable Long clientId){
        Client client = clientService.findById(clientId);
        return ResponseEntity.ok(client.getClientShifts());
    }

    @GetMapping("/{clientId}/book/{shiftId}")
    public ResponseEntity<Client> registerShift(@PathVariable long clientId,
                                               @PathVariable long shiftId){
        clientService.registerShift(clientId,shiftId);
        return ResponseEntity.ok(clientService.findById(clientId));
    }

    @PostMapping
    public Client createClient (@RequestBody Client client){
        return ResponseEntity.status(201).body(clientService.save(client)).getBody();
    }
}
