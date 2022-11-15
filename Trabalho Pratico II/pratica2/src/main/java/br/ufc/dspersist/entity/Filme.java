package br.ufc.dspersist.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NamedQuery(name = "Filme.findByTitulo", query = "from Filme where upper(titulo) like :titulo_")

/**
 * Filme
 */
@Entity
@Table(name = "filmes")
@NoArgsConstructor
@AllArgsConstructor
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String titulo;

    @Column(name = "ano_lancamento")
    @Getter
    @Setter
    private int anoLancamento;

    @ManyToMany
    @JoinTable(name = "filmes_atores", joinColumns = { @JoinColumn(name = "filme_id") }, inverseJoinColumns = {
            @JoinColumn(name = "ator_id") })
    @Getter
    @Setter
    private Set<Ator> atores = new HashSet<Ator>();

    public void addAtor(Ator ator) {
        this.atores.add(ator);
        ator.getFilmes().add(this);
    }

    public void removeFilme(Ator ator) {
        this.atores.remove(ator);
        ator.getFilmes().remove(this);
    }

    public String toString() {
        return "ID: " + getId() + ", Título: " + getTitulo() + ", Ano de Lançamento: " + getAnoLancamento();
    }
}
