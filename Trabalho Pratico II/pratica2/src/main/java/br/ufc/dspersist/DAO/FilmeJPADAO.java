package br.ufc.dspersist.DAO;

import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.ufc.dspersist.entity.Filme;
import br.ufc.dspersist.interfaces.FilmeInterfaceDAO;

public class FilmeJPADAO extends GenericJPADAO<Filme> implements FilmeInterfaceDAO {

    public FilmeJPADAO() {
        this.persistentClass = Filme.class;
    }

    public Optional<Filme> findByIdWithAtores(int filmeId) {
        try {
            Filme filme = getEm()
                    .createQuery("SELECT f FROM Filme f LEFT JOIN FETCH f.atores WHERE f.id = :_id", persistentClass)
                    .setParameter("_id", filmeId).getSingleResult();
            return Optional.of(filme);
        } catch (Exception e) {
            return Optional.empty();
        } finally {
            close();
        }
    }

    public List<Filme> findAllByAtor(int atorId) {
        List<Filme> filmes = getEm()
                .createQuery("SELECT f FROM Filme f LEFT JOIN FETCH f.atores a WHERE a.id = :_id", persistentClass)
                .setParameter("_id", atorId).getResultList();
        close();
        return filmes;
    }

    public List<Filme> findAllByAnoLancamento(int ano) {
        CriteriaBuilder cb = getEm().getCriteriaBuilder();
        CriteriaQuery<Filme> cQuery = cb.createQuery(persistentClass);
        Root<Filme> root = cQuery.from(persistentClass);

        cQuery.select(root).where(cb.equal(root.get("anoLancamento"), ano));

        List<Filme> filmes = getEm().createQuery(cQuery).getResultList();
        close();

        return filmes;
    }

    public List<Filme> findAllByTitulo(String titulo) {
        List<Filme> filmes = getEm().createNamedQuery("Filme.findByTitulo", persistentClass)
                .setParameter("titulo_", "%" + titulo.toUpperCase() + "%")
                .getResultList();
        close();
        return filmes;
    }
}
