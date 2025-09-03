package com.nicolasmorales.repository.impl;

import com.nicolasmorales.entity.Etiqueta;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.PersistenceException;

@ApplicationScoped
public class EtiquetaRepository extends RepoGenerico<Etiqueta> {

    public Etiqueta obtenerEtiquetaPorNombre(String titulo) throws PersistenceException {
        try {
            return find("WHERE nombre=:nombre AND borrado=false",
                    Parameters.with("nombre", titulo))
                    .firstResult();
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

}
