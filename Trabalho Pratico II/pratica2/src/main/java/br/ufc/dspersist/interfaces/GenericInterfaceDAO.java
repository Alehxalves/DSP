package br.ufc.dspersist.interfaces;

import java.util.List;

public interface GenericInterfaceDAO<T> {
    public void save(T entity);

    public void delete(T entity);

    public T findById(Object id);

    public List<T> findAll();

    public void beginTransaction();

    public void commit();

    public void rollback();

    public void close();
}
