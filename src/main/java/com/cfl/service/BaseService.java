package com.cfl.service;

import java.util.List;

public interface BaseService<T> {
    public T queryById(Long id);

    public void update(T t);

    public void add(T t);

    public void delete(Long id);

    public List<T> pagelist();

    public int count(String userCard);
}
