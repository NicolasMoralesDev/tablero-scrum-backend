package com.nicolasmoralesTest.repository;

import com.nicolasmorales.entity.Tablero;
import com.nicolasmorales.repository.impl.TableroRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.smallrye.common.constraint.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
@QuarkusTest
public class TableroRepositoryTest {

    @Inject
    TableroRepository tableroRepository;

    Tablero tablero;

    @BeforeEach
    public void setUp() {
        tablero = new Tablero(null, "PROYECTO QUARKUS", "Proyecto con quarkus y test", null);
        tableroRepository.persist(tablero);
    }

    @Test
    public void obtenerTodosTest() {
        List<Tablero> tableros = tableroRepository.obtenerTodos();
        assertNotNull(tableros);
        assertEquals(2, tableros.size());
    }

    @Test
    public void obtenerTableroPorIdTest() {
        Tablero tablero = tableroRepository.obtenerPorId(1L);
        assertNotNull(tablero);
        assertEquals(1L, tablero.getId());
    }

    @Test
    public void obtenerTableroPorTitulo() {
        Tablero tablero = tableroRepository.obtenerPorTitulo("PROYECTO QUARKUS");
        assertNotNull(tablero);
    }

    @Test
    public void guardarTableroTest() {
        Tablero tablero = new Tablero(null, "PROYECTO NEXT", "Proyecto con quarkus y test", null);
        tableroRepository.guardar(tablero);
        Tablero tablero1 = tableroRepository.obtenerPorTitulo(tablero.getTitulo());
        assertNotNull(tablero1);
        assertEquals(tablero.getTitulo(), tablero1.getTitulo());
    }
}
