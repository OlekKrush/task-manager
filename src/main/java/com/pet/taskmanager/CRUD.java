package com.pet.taskmanager;

import java.util.List;

public interface CRUD<T> {

    T create(T t);
    T update(T t);
    T findOneById(Long id);
    void delete(Long id);
    List<T> findAll();
}
