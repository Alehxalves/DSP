package com.ufc.dspesist.lab9.interfaces;

import java.util.List;

import com.ufc.dspesist.lab9.entity.Aluno;
import com.ufc.dspesist.lab9.entity.AlunoTurma;
import com.ufc.dspesist.lab9.entity.Turma;

public interface AlunoTurmaDAO extends GenericDAO<AlunoTurma> {

    public AlunoTurma isAlunoMatriculado(Aluno aluno, Turma turma);

    public void cancelarMatricula(Aluno aluno, Turma turma);

    public AlunoTurma findByAlunoTurma(Aluno aluno, Turma turma);

    public List<AlunoTurma> findTurmasDoAluno(Aluno aluno);

    public List<AlunoTurma> findAlunosDaTurma(Turma turma);
}
