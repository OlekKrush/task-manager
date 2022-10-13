package com.pet.taskmanager;

import java.util.List;

public interface CRUD<T, U> {

    T create(T t);
    T update(T t);
    T findOneById(U id);
    void delete(U id);
    List<T> findAll();
}
