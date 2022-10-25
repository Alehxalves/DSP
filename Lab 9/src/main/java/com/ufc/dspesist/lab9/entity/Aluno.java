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
@Table(name = "alunos")
@NoArgsConstructor
@AllArgsConstructor
public class Aluno {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Getter
    @Setter
    private String nome;

    @Column(nullable = false, unique = true)
    @Getter
    @Setter
    private String cpf;

    @Column(nullable = false, unique = true)
    @Getter
    @Setter
    private int matricula;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String telefone;

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.REMOVE)
    @Getter
    @Setter
    private List<AlunoTurma> turmasAluno;

    @Override
    public String toString() {
        return "Nome: " + getNome() + ", Matricula: " + getMatricula() + ", CPF: " + getCpf()
                + "\n Email: " + getEmail() + ", Telefone: " + getTelefone();
    }
}
