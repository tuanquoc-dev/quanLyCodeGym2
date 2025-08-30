package com.management;

import java.util.List;

public interface IManagerment<T> {
    void add(T t) throws Exception;
    void delete(Long id) throws Exception;
    void update(Long id, T t) throws Exception;
    T findById(Long id) throws Exception;
    List<T> findAll();
    int findIndexById(Long id) throws Exception;
}
