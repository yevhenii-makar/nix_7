package com.yevheniimakar.dao;

import java.util.List;


public interface BaseDao<E> {

    E getByID();

    List<E> getAll();

    void create(E e);

    void delete(Integer id);

    void update(E e);



}
