package com.example.shift_mgmt.controller;

import com.example.shift_mgmt.entity.Client;
import com.example.shift_mgmt.entity.Shift;
import com.example.shift_mgmt.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class ClientController {

    private final ClientService clientService;
    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @GetMapping("/clients/{shift_id}")
    public ResponseEntity<List<Shift>> getShifts (@PathVariable Long shift_id){
        Client client = clientService.findById(shift_id);
        return ResponseEntity.ok(client.getClientShifts());
    }

    @PostMapping("/clients/{name}")
}
