package br.ufc.dspersist.DAO;

import java.util.List;
import java.util.Optional;

import javax.persistence.Query;

import br.ufc.dspersist.entity.Ator;
import br.ufc.dspersist.interfaces.AtorInterfaceDAO;

public class AtorJPADAO extends GenericJPADAO<Ator> implements AtorInterfaceDAO {

    public AtorJPADAO() {
        this.persistentClass = Ator.class;
    }

    public Optional<Ator> findByIdWithFilmes(int atorId) {
        try {
            Ator ator = getEm()
                    .createQuery("SELECT a FROM Ator a LEFT JOIN FETCH a.filmes WHERE a.id = :_id", persistentClass)
                    .setParameter("_id", atorId).getSingleResult();
            return Optional.of(ator);
        } catch (Exception e) {
            return Optional.empty();
        } finally {
            close();
        }
    }

    public List<Ator> findAllAtoresByFilme(int filmeId) {
        List<Ator> atores = getEm()
                .createQuery("SELECT a FROM Ator a LEFT JOIN FETCH a.filmes f WHERE f.id = :_id", persistentClass)
                .setParameter("_id", filmeId).getResultList();
        close();
        return atores;
    }

    public List<Ator> findAllAtoresByAnoNascimento(int ano) {
        Query query = getEm()
                .createNativeQuery(
                        "SELECT id , data_nascimento, nome FROM atores WHERE EXTRACT(YEAR FROM data_nascimento) = :ano_",
                        persistentClass);
        query.setParameter("ano_", ano);

        @SuppressWarnings("unchecked")
        List<Ator> atores = query.getResultList();
        close();

        return atores;
    }
}
