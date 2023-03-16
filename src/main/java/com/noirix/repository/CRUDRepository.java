package com.noirix.repository;

import java.util.List;

//    K - key, datatype of PK
//    T - type of object
public interface CRUDRepository <K, T> {
    //    CRUD - operations
    //    Create - Insert
    //    Read - Select (by id, all, filtered)
    //    Update
    //    Delete

    T findById(K id);

    List<T> findAll();

    T create(T object);

    T update(Long id, T object);

    void delete(K id);
}
