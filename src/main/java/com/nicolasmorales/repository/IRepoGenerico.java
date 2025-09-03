package com.nicolasmorales.repository;

import jakarta.persistence.PersistenceException;

import java.util.List;

public interface IRepoGenerico<T> {

    List<T> obtenerTodos();

    void guardar(T entidad);

    T obtenerPorId(Long id);

    T obtenerPorTitulo(String titulo) throws PersistenceException;

}
