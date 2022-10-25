package com.ufc.dspesist.lab9.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import com.ufc.dspesist.lab9.interfaces.GenericDAO;
import com.ufc.dspesist.lab9.util.JPAUtil;

public abstract class GenericJPADAO<T> implements GenericDAO<T> {
    protected Class<T> persistentClass;

    public void save(T entity) {
        try {
            beginTransaction();
            getEm().merge(entity);
            commit();
        } catch (Exception ex) {
            rollback();
            ex.printStackTrace();
        } finally {
            close();
        }
    }

    public void delete(T entity) {
        try {
            beginTransaction();
            getEm().remove(getEm().merge(entity));
            commit();
        } catch (Exception ex) {
            rollback();
            ex.printStackTrace();
        } finally {
            close();
        }
    }

    public T findById(Object id) {
        return getEm().find(persistentClass, id);

    }

    public List<T> findAll() {
        CriteriaQuery<T> cQuery = getEm().getCriteriaBuilder().createQuery(persistentClass);
        cQuery.from(persistentClass);

        return getEm().createQuery(cQuery).getResultList();
    }

    public EntityManager getEm() {
        return JPAUtil.getEntityManager();
    }

    public void beginTransaction() {
        JPAUtil.beginTransaction();
    }

    public void commit() {
        JPAUtil.commit();
    }

    public void rollback() {
        JPAUtil.rollback();
    }

    public void close() {
        JPAUtil.closeEntityManager();
    }
}