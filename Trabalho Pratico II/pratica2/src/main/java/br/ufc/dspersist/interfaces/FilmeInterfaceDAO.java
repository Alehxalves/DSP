package br.ufc.dspersist.interfaces;

import java.util.List;
import java.util.Optional;

import br.ufc.dspersist.entity.Filme;

public interface FilmeInterfaceDAO extends GenericInterfaceDAO<Filme> {

    public Optional<Filme> findByIdWithAtores(int filmeId);

    public List<Filme> findAllByAtor(int atorId);

    public List<Filme> findAllByAnoLancamento(int ano);

    public List<Filme> findAllByTitulo(String titulo);
}
