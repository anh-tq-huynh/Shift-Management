package com.example.shift_mgmt.repository;

import com.example.shift_mgmt.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{
    Client findClientByClientId(Long clientId);
}
