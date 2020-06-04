package ru.gbjava.kinozen.services;

import java.util.List;

public interface CrudService<T, ID> {
    List<T> findAll();
    T findById(ID id);
    void save(T object);
    void deleteById(ID id);
}
