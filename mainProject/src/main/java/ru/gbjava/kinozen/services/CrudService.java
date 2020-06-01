package ru.gbjava.kinozen.services;

public interface CrudService<T, ID> {
    Iterable<T> findAll();
    T findById(ID id);
    void save(T object);
    void deleteBy(ID id);
}
