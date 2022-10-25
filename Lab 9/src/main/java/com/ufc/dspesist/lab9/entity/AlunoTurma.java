package com.ufc.dspesist.lab9.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "aluno_turma")
@NoArgsConstructor
@AllArgsConstructor
public class AlunoTurma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false)
    @Getter
    @Setter
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "turma_id", nullable = false)
    @Getter
    @Setter
    private Turma turma;

    @Column(name = "nota_final")
    @Getter
    @Setter
    private float notaFinal;

    @Column(name = "qtd_faltas")
    @Getter
    @Setter
    private int qtdFaltas;

    @Override
    public String toString() {
        return "Aluno ID: " + getAluno().getId() + ", Turma: " + getTurma().getDisciplina() + ", Turma ID: "
                + getTurma().getId()
                + "\n Nota final: " + getNotaFinal() + ", Quantidade de faltas: " + getQtdFaltas();
    }
}