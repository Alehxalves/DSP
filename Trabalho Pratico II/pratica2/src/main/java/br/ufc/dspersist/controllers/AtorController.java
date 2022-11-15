package br.ufc.dspersist.controllers;

import java.util.List;

import br.ufc.dspersist.DAO.AtorJPADAO;
import br.ufc.dspersist.entity.Ator;

public class AtorController {
    private AtorJPADAO baseAtores;

    public AtorController() {
        this.baseAtores = new AtorJPADAO();
    }

    public void saveAtor(Ator ator) {
        baseAtores.save(ator);
    }

    public void removerAtor(Ator ator) {
        baseAtores.delete(ator);
    }

    public Ator findAtorByIdWithFilmes(int atorId) throws Exception {
        Ator ator = baseAtores.findByIdWithFilmes(atorId)
                .orElseThrow(() -> new Exception("Ator não encontrado"));

        return ator;
    }

    public Ator findById(int id) {
        return baseAtores.findById(id);
    }

    public List<Ator> listAtoresByFilme(int filmeId) throws Exception {
        List<Ator> atores = baseAtores.findAllAtoresByFilme(filmeId);

        if (atores.isEmpty()) {
            throw new Exception("Nenhum ator encontrado");
        } else {
            return atores;
        }
    }

    public List<Ator> listAtoresByAnoNascimento(int anoNascimento) throws Exception {
        List<Ator> atores = baseAtores.findAllAtoresByAnoNascimento(anoNascimento);

        if (atores.isEmpty()) {
            throw new Exception("Nenhum ator encontrado");
        } else {
            return atores;
        }
    }

    public List<Ator> listAllAtores() throws Exception {
        List<Ator> atores = baseAtores.findAll();

        if (atores.isEmpty()) {
            throw new Exception("Nenhum ator encontrado");
        } else {
            return atores;
        }
    }
}
