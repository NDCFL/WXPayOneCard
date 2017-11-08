package com.cfl.dao;

import java.util.List;

public interface BaseDAO<T>  {
    public T queryById(Long id);

    public void update(T t);

    public void add(T t);

    public void delete(Long id);

    public List<T> pagelist();

    public int count(String userCard);
}
