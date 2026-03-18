package com.example.shift_mgmt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;

    private String clientName;
    private Double hourQuota = 50.00;

    @ManyToOne(fetch = FetchType.EAGER)
    @ToString.Exclude
    @JsonIgnore
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany (mappedBy = "client", fetch = FetchType.EAGER)
    private List<Shift> clientShifts = new ArrayList<>();

    public void addShift(Shift newShift){
        clientShifts.add(newShift);
    }
}
