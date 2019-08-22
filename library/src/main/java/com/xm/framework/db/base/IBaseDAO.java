package com.xm.framework.db.base;

import java.util.List;

public interface IBaseDAO<T> {


    void deleteAll();

    boolean save(T t);

    boolean save(List<T> list);

    boolean replace(T t);

    boolean replace(List<T> list);

    List<T> findAll();

}
