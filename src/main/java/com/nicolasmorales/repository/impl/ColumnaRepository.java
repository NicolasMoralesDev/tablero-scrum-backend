package com.nicolasmorales.repository.impl;

import com.nicolasmorales.entity.Columna;
import com.nicolasmorales.entity.Columna_;
import com.nicolasmorales.entity.Tablero_;
import com.nicolasmorales.entity.Tarjeta;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.criteria.*;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ColumnaRepository extends RepoGenerico<Columna> {

    /**
     * Entity Manager
     */
    @PersistenceContext
    private EntityManager entityManagerFactory;

    public List<Columna> obtenerColumnaPorTablero(Long tablero) throws PersistenceException {
        try {
            CriteriaBuilder cb = entityManagerFactory.getCriteriaBuilder();
            CriteriaQuery<Columna> cr = cb.createQuery(Columna.class);
            Root<Columna> root = cr.from(Columna.class);
            root.fetch("tarjetas", JoinType.LEFT);
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get(Columna_.BORRADO), false));
            predicates.add(cb.equal(root.get(Columna_.TABLERO).get(Tablero_.ID), tablero));

            cr.select(root)
                    .where(predicates.toArray(new Predicate[0]));
            return entityManagerFactory.createQuery(cr).getResultList();
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

}
