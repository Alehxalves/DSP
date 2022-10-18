package com.ufc.dspesist.lab8.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.ufc.dspesist.lab8.entity.Aluno;
import com.ufc.dspesist.lab8.util.JPAUtil;

public class AlunoJPADAO implements AlunoDAO {

    public AlunoJPADAO() {
    }

    public void save(Aluno entity) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            JPAUtil.beginTransaction();

            if (entity.getId() == null) {
                em.persist(entity);
            } else {
                em.merge(entity);
            }

            JPAUtil.commit();
        } catch (Exception ex) {
            JPAUtil.rollback();
            ex.printStackTrace();
        } finally {
            JPAUtil.closeEntityManager();
        }

    }

    public void delete(int id) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            JPAUtil.beginTransaction();

            em.createQuery("delete from Aluno where id = :id")
                    .setParameter("id", id)
                    .executeUpdate();

            JPAUtil.commit();
        } catch (Exception ex) {
            JPAUtil.rollback();
            ex.printStackTrace();
        } finally {
            JPAUtil.closeEntityManager();
        }
    }

    public Aluno find(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        Aluno aluno = em.find(Aluno.class, id);

        JPAUtil.closeEntityManager();
        return aluno;
    }

    public List<Aluno> find() {
        EntityManager em = JPAUtil.getEntityManager();

        List<Aluno> alunos = em.createQuery("from Aluno", Aluno.class).getResultList();

        JPAUtil.closeEntityManager();
        return alunos;
    }

    public List<Aluno> findByNome(String nome) {
        EntityManager em = JPAUtil.getEntityManager();

        List<Aluno> alunos = em.createQuery("from Aluno where upper(nome) like :nome_", Aluno.class)
                .setParameter("nome_", "%" + nome.toUpperCase() + "%")
                .getResultList();

        JPAUtil.closeEntityManager();
        return alunos;
    }

    public Aluno findByMatricula(int matricula) {
        EntityManager em = JPAUtil.getEntityManager();

        Aluno aluno = em.createQuery("from Aluno where matricula = :matricula_", Aluno.class)
                .setParameter("matricula_", matricula)
                .getSingleResult();

        JPAUtil.closeEntityManager();

        return aluno;
    }

}
