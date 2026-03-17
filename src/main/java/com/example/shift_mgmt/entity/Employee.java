package com.example.shift_mgmt.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.*;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonIgnore
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "employee")
    private List<Shift> employeeShifts = new ArrayList<>();

    public void addShift(Shift newShift){
        employeeShifts.add(newShift);
    }
}
