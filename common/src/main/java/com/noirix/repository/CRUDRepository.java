package com.noirix.repository;

import java.util.List;

//K - key, datatype of PK
//T - type of object
public interface CRUDRepository <K, T> {
    //    CRUD - operations
//    Create - Insert
//    Read - Select (by id, all, filtered)
//    Update
//    Delete

//    Optional<T> findOne(K id); //
//
//    T findById(K id); //throw new EntityNotFoundException("User with id " + id + " ")

    T findOne(K id); //

    List<T> findAll();

    T create(T object);

    T update(T object);

    void delete(K id);
}
