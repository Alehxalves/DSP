package com.ufc.dspesist.lab9.interfaces;

import java.util.List;

import com.ufc.dspesist.lab9.entity.Aluno;

public interface AlunoDAO extends GenericDAO<Aluno> {

    public List<Aluno> findByNome(String nome);

    public Aluno findByMatricula(int matricula);

    public Aluno findByCPF(String cpf);
}
