package com.example.shift_mgmt.service;

import java.util.List;

public interface GenericService<T, ID> {
    T save(T entity);
    List<T> fetchAll();
    void deleteById(ID id);
    T update(T newEntity, ID id);
    T changeName(String newName, ID id);
    T findById(ID id);
}
