package com.ufc.dspesist.lab9.controllers;

import java.util.List;
import java.util.Objects;

import javax.swing.JOptionPane;

import com.ufc.dspesist.lab9.dao.AlunoJPADAO;
import com.ufc.dspesist.lab9.entity.Aluno;
import com.ufc.dspesist.lab9.interfaces.AlunoDAO;

public class AlunoController {
    private AlunoDAO baseAlunos;

    public AlunoController() {
        baseAlunos = new AlunoJPADAO();
    }

    public boolean existsAluno(Aluno aluno) throws Exception {
        return Objects.nonNull(baseAlunos.findByMatricula(aluno.getMatricula()))
                ? true
                : false;
    }

    public void saveAluno(Aluno aluno) throws Exception {
        if (existsAluno(aluno))
            throw new Exception("Aluno " + aluno.getNome() + " já está salvo no sistema.");
        baseAlunos.save(aluno);
        JOptionPane.showMessageDialog(null, "Aluno(a) " + aluno.getNome() + " Salvo.");

    }

    public void updateAluno(Aluno aluno) throws Exception {
        if (!existsAluno(aluno))
            throw new Exception("Aluno " + aluno.getNome() + " não encontrado.");
        JOptionPane.showMessageDialog(null, "Aluno(a) " + aluno.getNome() + " Atualizado.");
    }

    public void deleteAlunoByMatricula(int matricula) throws Exception {
        Aluno aluno = findByMatricula(matricula);
        baseAlunos.delete(aluno);
        JOptionPane.showMessageDialog(null, "Aluno(a) deletado.");
    }

    public Aluno findByMatricula(int matricula) throws Exception {
        Aluno aluno = new Aluno();
        try {
            aluno = baseAlunos.findByMatricula(matricula);
        } catch (Exception e) {
            throw new Exception("Aluno(a) não encontrado.");
        }
        return aluno;
    }

    public List<Aluno> findAll() {
        return baseAlunos.findAll();
    }

    public List<Aluno> findByNome(String nome) {
        return baseAlunos.findByNome(nome);
    }

}
