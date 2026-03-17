package com.example.shift_mgmt.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long employeeId;

    String employeeName;
    String employeeJobTitle;
    double employeeTotalHours;
    double salaryRate;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonIgnore
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "employee")
    private List<Shift> employeeShifts = new ArrayList<>();

    public void addShift(Shift newShift){
        LocalDateTime newStart = newShift.getStartDateTime();
        LocalDateTime newEnd = newShift.getEndDateTime();
        for (Shift shift : employeeShifts){
            LocalDateTime existingStart = shift.getStartDateTime();
            LocalDateTime exisitingEnd = shift.getEndDateTime();
            if (newStart.isBefore(exisitingEnd) && existingStart.isBefore(newEnd)){
                throw new RuntimeException("Shift overlap!");
            }
        }
        employeeShifts.add(newShift);
        newShift.setEmployee(this);
    }
}
