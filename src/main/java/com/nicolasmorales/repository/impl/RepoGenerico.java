package com.nicolasmorales.repository.impl;

import com.nicolasmorales.repository.IRepoGenerico;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;

import java.util.List;

/**
 * Repo generico
 * @param <T>
 */
public abstract class RepoGenerico<T> implements IRepoGenerico<T>, PanacheRepository<T> {

    /**
     * Entity Manager
     */
    @PersistenceContext
    private EntityManager entityManagerFactory;

    @Override
    @Transactional
    public List<T> obtenerTodos() throws PersistenceException {
        try {
            return find("borrado=false").stream().toList();
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void guardar(T entidad) throws PersistenceException {
        try {
            entityManagerFactory.persist(entidad);
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public T obtenerPorId(Long id) throws PersistenceException {
        try {
            return find("WHERE id=:id AND borrado=false",
                    Parameters.with("id", id))
                    .firstResult();
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public T obtenerPorTitulo(String titulo) throws PersistenceException {
        try {
            return find("titulo=:titulo AND borrado=false",
                    Parameters.with("titulo", titulo))
                    .firstResult();
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }
}
