package com.example.shift_mgmt.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShiftRequestDTO{
    public Long clientId;
    public LocalDate date;
    public LocalTime start;
    public LocalTime end;
}
