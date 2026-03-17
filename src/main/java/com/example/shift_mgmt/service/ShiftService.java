package com.example.shift_mgmt.service;

import com.example.shift_mgmt.entity.Shift;

import java.time.LocalDate;
import java.time.LocalTime;

public interface ShiftService extends GenericService<Shift,Long>{
    Shift createShift(Long clientId, LocalDate date, LocalTime start, LocalTime end);
    void markAsDone(Long empId, Long shiftID);
}
