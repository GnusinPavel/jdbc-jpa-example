package org.gnusinpavel.itlab.service;

import java.util.List;

/**
 * Стандартные операции над объектом БД
 */
public interface CrudService<T> {
    T create(T model);

    int update(T model);

    int delete(long id);

    T get(long id);

    List<T> list();
}
