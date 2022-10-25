package com.ufc.dspesist.lab9.dao;

import java.util.List;

import javax.persistence.NoResultException;

import com.ufc.dspesist.lab9.entity.Aluno;
import com.ufc.dspesist.lab9.interfaces.AlunoDAO;

public class AlunoJPADAO extends GenericJPADAO<Aluno> implements AlunoDAO {

    public AlunoJPADAO() {
        this.persistentClass = Aluno.class;
    }

    public List<Aluno> findByNome(String nome) {
        List<Aluno> alunos = getEm().createQuery("from Aluno where upper(nome) like :nome_",
                Aluno.class)
                .setParameter("nome_", "%" + nome.toUpperCase() + "%")
                .getResultList();

        close();
        return alunos;
    }

    public Aluno findByMatricula(int matricula) {
        try {
            Aluno aluno = getEm().createQuery("from Aluno where matricula = :matricula_",
                    Aluno.class)
                    .setParameter("matricula_", matricula)
                    .getSingleResult();
            close();
            return aluno;
        } catch (NoResultException ex) {
            return null;
        }
    }

    public Aluno findByCPF(String cpf) {
        Aluno aluno = getEm().createQuery("from Aluno where cpf = :cpf_",
                Aluno.class)
                .setParameter("cpf_", cpf)
                .getSingleResult();

        close();
        return aluno;
    }

}
