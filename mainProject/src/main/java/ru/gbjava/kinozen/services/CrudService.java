package ru.gbjava.kinozen.services;

public interface CrudService<T, ID> {
    Iterable<T> getAll();
    T getById(ID id);
    void save(T object);
    void deleteBy(ID id);
}
