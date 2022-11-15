package br.ufc.dspersist.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Ator
 */
@Entity
@Table(name = "atores")
@NoArgsConstructor
@AllArgsConstructor
public class Ator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String nome;

    @Column(name = "data_nascimento")
    @Getter
    @Setter
    private LocalDate dataNascimento;

    @Getter
    @Setter
    @ManyToMany(mappedBy = "atores")
    private Set<Filme> filmes = new HashSet<Filme>();

    public void addFilme(Filme filme) {
        this.filmes.add(filme);
        filme.getAtores().add(this);
    }

    public void removeFilme(Filme filme) {
        this.filmes.remove(filme);
        filme.getAtores().remove(this);
    }

    public String toString() {
        return "ID: " + getId() + ", Nome: " + getNome() + ", Data Nascimento: " + getDataNascimento();
    }
}
