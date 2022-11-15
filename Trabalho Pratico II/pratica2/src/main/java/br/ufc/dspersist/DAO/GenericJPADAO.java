package br.ufc.dspersist.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import br.ufc.dspersist.interfaces.GenericInterfaceDAO;
import br.ufc.dspersist.util.JPAUtil;

public abstract class GenericJPADAO<T> implements GenericInterfaceDAO<T> {
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
