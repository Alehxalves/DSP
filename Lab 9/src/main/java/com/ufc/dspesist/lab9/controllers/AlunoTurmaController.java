package com.ufc.dspesist.lab9.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.JOptionPane;

import com.ufc.dspesist.lab9.dao.AlunoTurmaJPADAO;
import com.ufc.dspesist.lab9.entity.Aluno;
import com.ufc.dspesist.lab9.entity.AlunoTurma;
import com.ufc.dspesist.lab9.entity.Turma;
import com.ufc.dspesist.lab9.interfaces.AlunoTurmaDAO;

public class AlunoTurmaController {
    private AlunoTurmaDAO baseAlunoTurmas;

    private AlunoController alunoController;
    private TurmaController turmaController;

    public AlunoTurmaController() {
        baseAlunoTurmas = new AlunoTurmaJPADAO();
        alunoController = new AlunoController();
        turmaController = new TurmaController();
    }

    public boolean isMatriculado(Aluno aluno, Turma turma) {
        return Objects.nonNull(baseAlunoTurmas.isAlunoMatriculado(aluno, turma))
                ? true
                : false;
    }

    public void matricularAluno(AlunoTurma alunoTurma) throws Exception {
        Aluno aluno = alunoTurma.getAluno();
        Turma turma = alunoTurma.getTurma();
        if (isMatriculado(aluno, turma)) {
            throw new Exception("Este Aluno(a) já está matriculado nesta turma.");
        } else {
            baseAlunoTurmas.save(alunoTurma);
            JOptionPane.showMessageDialog(null, aluno.getNome() + " matriculado em " + turma.getDisciplina());
        }
    }

    public void atualizarMatricula(AlunoTurma alunoTurma) {
        baseAlunoTurmas.save(alunoTurma);
        JOptionPane.showMessageDialog(null, "Matrícula atualizada.");
    }

    public void cancelarMatricula(Aluno aluno, Turma turma) throws Exception {
        if (isMatriculado(aluno, turma)) {
            baseAlunoTurmas.cancelarMatricula(aluno, turma);
            JOptionPane.showMessageDialog(null, "Matrícula cancelada.");
        } else {
            throw new Exception("Este Aluno(a) não está matriculado nesta turma.");
        }
    }

    public AlunoTurma findByAlunoTurma(Aluno aluno, Turma turma) throws Exception {
        if (!isMatriculado(aluno, turma)) {
            throw new Exception("Este aluno(a) não está matriculado nesta turma.");
        }
        return baseAlunoTurmas.findByAlunoTurma(aluno, turma);
    }

    public List<String> turmasDoAluno(Aluno aluno) throws Exception {
        List<AlunoTurma> alunoTurmas = baseAlunoTurmas.findTurmasDoAluno(aluno);
        if (alunoTurmas.size() == 0) {
            throw new Exception("Este aluno(a) não está matriculado em nenhuma turma.");
        }
        List<String> turmasAluno = new ArrayList<>();

        String descricaoAluno = "Nome: " + aluno.getNome() + ", Matricula: " + aluno.getMatricula();
        String notasEFaltas;
        String descricaoTurma;
        turmasAluno.add(descricaoAluno + "\n");
        for (AlunoTurma alunoTurma : alunoTurmas) {
            descricaoTurma = turmaController.findById(alunoTurma.getTurma().getId()).toString();
            notasEFaltas = "Nota final: " + alunoTurma.getNotaFinal() + ", Quantidade de faltas: "
                    + alunoTurma.getQtdFaltas();

            turmasAluno.add(descricaoTurma + "\n" + notasEFaltas + ".\n");
        }
        return turmasAluno;
    }

    public List<String> alunosDaTurma(Turma turma) throws Exception {
        List<AlunoTurma> alunoTurmas = baseAlunoTurmas.findAlunosDaTurma(turma);
        if (alunoTurmas.size() == 0) {
            throw new Exception("Esta turma não possui alunos matriculados.");
        }
        List<String> alunosTurma = new ArrayList<>();

        String descricaoTurma = "ID Turma: " + turma.getId() + ", Cod Turma: " + turma.getCodTurma()
                + ", Disciplina: " + turma.getDisciplina();

        String descricaoAluno;
        alunosTurma.add(descricaoTurma + "\n");
        for (AlunoTurma alunoTurma : alunoTurmas) {
            descricaoAluno = alunoController.findByMatricula(alunoTurma.getAluno().getMatricula()).toString();

            alunosTurma.add(descricaoAluno + ".\n");
        }
        return alunosTurma;
    }
}
