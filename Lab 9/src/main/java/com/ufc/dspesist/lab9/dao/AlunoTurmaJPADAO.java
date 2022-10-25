package com.ufc.dspesist.lab9.dao;

import java.util.List;

import javax.persistence.NoResultException;

import com.ufc.dspesist.lab9.entity.Aluno;
import com.ufc.dspesist.lab9.entity.AlunoTurma;
import com.ufc.dspesist.lab9.entity.Turma;
import com.ufc.dspesist.lab9.interfaces.AlunoTurmaDAO;

public class AlunoTurmaJPADAO extends GenericJPADAO<AlunoTurma> implements AlunoTurmaDAO {

    public AlunoTurmaJPADAO() {
        this.persistentClass = AlunoTurma.class;
    }

    public void cancelarMatricula(Aluno aluno, Turma turma) {
        try {
            AlunoTurma alunoTurma = getEm()
                    .createQuery("from AlunoTurma where aluno_id = :aluno_id_ and turma_id = :turma_id_",
                            persistentClass)
                    .setParameter("aluno_id_", aluno.getId())
                    .setParameter("turma_id_", turma.getId())
                    .getSingleResult();
            beginTransaction();
            getEm().remove(alunoTurma);
            commit();
        } catch (Exception e) {
            rollback();
        } finally {
            close();
        }

    }

    public AlunoTurma isAlunoMatriculado(Aluno aluno, Turma turma) {
        try {
            AlunoTurma alunoTurma = getEm()
                    .createQuery("from AlunoTurma where aluno_id = :aluno_id_ and turma_id = :turma_id_",
                            persistentClass)
                    .setParameter("aluno_id_", aluno.getId())
                    .setParameter("turma_id_", turma.getId())
                    .getSingleResult();
            return alunoTurma;
        } catch (NoResultException ex) {
            return null;
        }
    }

    public AlunoTurma findByAlunoTurma(Aluno aluno, Turma turma) {
        return getEm()
                .createQuery("from AlunoTurma where aluno_id = :aluno_id_ and turma_id = :turma_id_",
                        persistentClass)
                .setParameter("aluno_id_", aluno.getId())
                .setParameter("turma_id_", turma.getId())
                .getSingleResult();
    }

    public List<AlunoTurma> findTurmasDoAluno(Aluno aluno) {
        return getEm()
                .createQuery("from AlunoTurma where aluno_id = :aluno_id_", persistentClass)
                .setParameter("aluno_id_", aluno.getId())
                .getResultList();

    }

    public List<AlunoTurma> findAlunosDaTurma(Turma turma) {
        return getEm()
                .createQuery("from AlunoTurma where turma_id = :turma_id_", persistentClass)
                .setParameter("turma_id_", turma.getId())
                .getResultList();
    }
}
