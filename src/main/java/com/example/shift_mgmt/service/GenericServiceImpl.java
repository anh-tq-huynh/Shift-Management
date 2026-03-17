package com.example.shift_mgmt.service;

import com.example.shift_mgmt.entity.Client;
import com.example.shift_mgmt.entity.Employee;
import com.example.shift_mgmt.entity.Shift;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public abstract class GenericServiceImpl <T,ID> implements GenericService <T,ID>{

    protected final JpaRepository<T, ID> repository;

    public GenericServiceImpl(JpaRepository<T,ID> repository){
        this.repository = repository;
    }

    @Override
    public T save(T entity){
        return repository.save(entity);
    }

    @Override
    public List<T> fetchAll(){
        return repository.findAll();
    }

    @Override
    public T findById(ID id){
        Optional<T> entityTemp = repository.findById(id);
        if (entityTemp.isPresent()){
            return entityTemp.get();
        }
        else{
            throw new EntityNotFoundException("ID" + id + "not found");
        }
    }

    @Override
    public void deleteById(ID id){
        repository.deleteById(id);
    }

    @Override
    public T update(T newEntity, ID id) {
        return null;
    }

    @Override
    public  T changeName(String newName, ID id){
        Optional<T> entityOpt =  repository.findById(id);
        if (entityOpt.isPresent()){
            if (!newName.isEmpty()){
                T entityDB = entityOpt.get();
                if (entityDB instanceof Employee emp){
                    emp.setEmployeeName(newName);
                } else if (entityDB instanceof  Client cli){
                    cli.setClientName(newName);
                }
                return repository.save(entityDB);
            }
        }
        return null;
    }

}
