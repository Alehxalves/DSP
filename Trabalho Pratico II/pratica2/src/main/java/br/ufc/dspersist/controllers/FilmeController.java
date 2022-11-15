package br.ufc.dspersist.controllers;

import java.util.List;

import br.ufc.dspersist.DAO.FilmeJPADAO;
import br.ufc.dspersist.entity.Filme;

public class FilmeController {
    private FilmeJPADAO baseFilmes;

    public FilmeController() {
        this.baseFilmes = new FilmeJPADAO();
    }

    public void saveFilme(Filme filme) {
        baseFilmes.save(filme);
    }

    public void removerFilme(Filme filme) {
        baseFilmes.delete(filme);
    }

    public Filme findFilmeById(int filmeId) {
        return baseFilmes.findById(filmeId);
    }

    public Filme findFilmeByIdWithAtores(int filmeId) throws Exception {
        Filme filme = baseFilmes.findByIdWithAtores(filmeId)
                .orElseThrow(() -> new Exception("Filme não encontrado"));

        return filme;
    }

    public List<Filme> listFilmesByTitulo(String titulo) throws Exception {
        List<Filme> filmes = baseFilmes.findAllByTitulo(titulo);

        if (filmes.isEmpty()) {
            throw new Exception("Nenhum filme encontrado");
        } else {
            return filmes;
        }
    }

    public List<Filme> listFilmesByAtor(int atorId) throws Exception {
        List<Filme> filmes = baseFilmes.findAllByAtor(atorId);

        if (filmes.isEmpty()) {
            throw new Exception("Nenhum filme encontrado");
        } else {
            return filmes;
        }
    }

    public List<Filme> listFilmesByAnoLancamento(int ano) throws Exception {
        List<Filme> filmes = baseFilmes.findAllByAnoLancamento(ano);

        if (filmes.isEmpty()) {
            throw new Exception("Nenhum filme encontrado");
        } else {
            return filmes;
        }
    }

    public List<Filme> listAllFilmes() throws Exception {
        List<Filme> filmes = baseFilmes.findAll();

        if (filmes.isEmpty()) {
            throw new Exception("Nenhum filme encontrado");
        } else {
            return filmes;
        }
    }
}
