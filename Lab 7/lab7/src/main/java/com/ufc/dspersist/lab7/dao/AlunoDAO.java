package com.ufc.dspersist.lab7.dao;

import java.util.List;

import com.ufc.dspersist.lab7.entity.Aluno;

public interface AlunoDAO {

    public void save(Aluno entity);

    public void delete(int id);

    public Aluno find(int id);

    public List<Aluno> find();

    public List<Aluno> findByNome(String str);

    public Aluno findByMatricula(int matricula);
}
