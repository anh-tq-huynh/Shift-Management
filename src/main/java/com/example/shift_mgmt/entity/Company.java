package com.example.shift_mgmt.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.*;
import org.apache.logging.log4j.util.PropertySource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Company {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long companyId;
    private String companyName;

    @ToString.Exclude
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Client> clients = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Employee> employees = new ArrayList<>();

    public void addClient(Client client){
        this.clients.add(client);
        client.setCompany(this);
    }

    public void hireEmployee(Employee employee){
        this.employees.add(employee);
        employee.setCompany(this);
    }


    public void viewEmployees(List<Employee> employeeList){
        sortEmp(employeeList);
        for ( Employee emp : employeeList){
            System.out.println("Name: " + emp.getEmployeeName());
            System.out.println("ID: " + emp.getEmployeeId());
            System.out.println("Job Title: " + emp.getEmployeeJobTitle());
            System.out.println("Total work hours: " + emp.getEmployeeTotalHours());
        }
    }

    public void viewClients(){
        sortCli(clients);
        for (Client cli : clients){
            System.out.println("Name: " + cli.getClientName());
            System.out.println("ID: " + cli.getClientId());
            System.out.println("Hours left: " + cli.getHourQuota());
        }
    }


    public static void sortEmp (List<Employee> employeeList){
        employeeList.sort(Comparator.comparing(Employee::getEmployeeId));
    }
    public static void sortCli (List<Client> clientList){
        clientList.sort(Comparator.comparing(Client::getClientId));
    }

}
