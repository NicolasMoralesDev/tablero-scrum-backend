package com.nicolasmorales.repository.impl;

import com.nicolasmorales.entity.Columna;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.PersistenceException;

import java.util.List;

@ApplicationScoped
public class ColumnaRepository extends RepoGenerico<Columna> {

    public List<Columna> obtenerColumnaPorTablero(Long tablero) throws PersistenceException {
        try {
            return find("FROM Columna c JOIN c.tablero t WHERE " +
                            " t.id=:id AND " +
                            " c.borrado=false",
                    Parameters.with("id", tablero))
                    .stream().toList();
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

}
