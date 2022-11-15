package br.ufc.dspersist.interfaces;

import java.util.List;
import java.util.Optional;

import br.ufc.dspersist.entity.Ator;

public interface AtorInterfaceDAO extends GenericInterfaceDAO<Ator> {

    public Optional<Ator> findByIdWithFilmes(int atorId);

    public List<Ator> findAllAtoresByFilme(int filmeId);

    public List<Ator> findAllAtoresByAnoNascimento(int ano);
}
