package org.gnusinpavel.itlab.service;

import java.util.List;

/**
 * Стандартные операции над объектом БД
 */
public interface CrudService<T> {
    T create(T t);

    T update(T t);

    void delete(long id);

    T get(long id);

    List<T> list();
}
