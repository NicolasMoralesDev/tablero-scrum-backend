package com.nicolasmorales.repository.impl;

import com.nicolasmorales.entity.Tablero;
import com.nicolasmorales.entity.Tablero_;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

@ApplicationScoped
public class TableroRepository extends RepoGenerico<Tablero> {

    /**
     * Entity Manager
     */
    @PersistenceContext
    private EntityManager entityManagerFactory;

    public Tablero obtenerPorTitulo(String titulo) throws PersistenceException {
        return find("titulo=:titulo AND borrado=false",
                Parameters.with("titulo", titulo))
                .firstResult();
    }

    @Override
    public List<Tablero> obtenerTodos() throws PersistenceException {
        try {
            CriteriaBuilder cb = entityManagerFactory.getCriteriaBuilder();
            CriteriaQuery<Tablero> cr = cb.createQuery(Tablero.class);
            Root<Tablero> root = cr.from(Tablero.class);
            cr.multiselect(
                            root.get(Tablero_.ID),
                            root.get(Tablero_.TITULO),
                            root.get(Tablero_.DESCRIPCION),
                            root.get(Tablero_.FECHA_DE_CREACION)
            )
                    .where(cb.equal(root.get(Tablero_.BORRADO), false));
            return entityManagerFactory.createQuery(cr).getResultList();
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }
}
