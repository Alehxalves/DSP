package com.ufc.dspesist.lab8.dao;

import java.util.List;

import com.ufc.dspesist.lab8.entity.Aluno;

public interface AlunoDAO {
    public void save(Aluno entity);

    public void delete(int id);

    public Aluno find(int id);

    public List<Aluno> find();

    public List<Aluno> findByNome(String nome);

    public Aluno findByMatricula(int matricula);
}
