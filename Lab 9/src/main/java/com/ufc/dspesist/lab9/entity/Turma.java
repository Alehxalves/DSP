package com.ufc.dspesist.lab9.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "turmas")
@NoArgsConstructor
@AllArgsConstructor
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer id;

    @Column(name = "cod_turma")
    @Getter
    @Setter
    private int codTurma;

    @Getter
    @Setter
    private String periodo;

    @Getter
    @Setter
    private String disciplina;

    @OneToMany(mappedBy = "turma", cascade = CascadeType.REMOVE)
    @Getter
    @Setter
    private List<AlunoTurma> alunosTurma;

    @Override
    public String toString() {
        return "Turma ID: " + getId() + ", Código Turma: " + getCodTurma()
                + ", Disciplina: " + getDisciplina() + ", Período: " + getPeriodo();
    }
}