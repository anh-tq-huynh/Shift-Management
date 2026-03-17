package com.example.shift_mgmt.controller;

import com.example.shift_mgmt.entity.Company;
import com.example.shift_mgmt.entity.Shift;
import com.example.shift_mgmt.entity.ShiftRequestDTO;
import com.example.shift_mgmt.service.ShiftService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/shift")
public class ShiftController {
    private final ShiftService shiftService;
    public ShiftController(ShiftService shiftService){
        this.shiftService = shiftService;
    }

    @GetMapping("{id}/start")
    public LocalDateTime getStart(@PathVariable long id){
        return shiftService.findById(id).getStartDateTime();
    }

    @GetMapping("{id}/end")
    public LocalDateTime getEnd(@PathVariable long id){
        return shiftService.findById(id).getEndDateTime();
    }

    @GetMapping("{employeeId}/finish/{shiftID}")
    public ResponseEntity<Shift> markAsDone (@PathVariable long employeeId,
                                             @PathVariable long shiftID){
        shiftService.markAsDone(employeeId,shiftID);
        return ResponseEntity.ok(shiftService.findById(shiftID));
    }

    @PostMapping
    public ResponseEntity<Shift> addCompany (@RequestBody ShiftRequestDTO input){
        Shift saved = shiftService.createShift(input.getClientId(), input.getDate(), input.getStart(), input.getEnd());
        return ResponseEntity.ok(saved);
    }
}
