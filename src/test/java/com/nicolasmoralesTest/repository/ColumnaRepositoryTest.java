package com.nicolasmoralesTest.repository;

import com.nicolasmorales.entity.Columna;
import com.nicolasmorales.entity.Tablero;
import com.nicolasmorales.repository.impl.ColumnaRepository;
import com.nicolasmorales.repository.impl.TableroRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
@Transactional
public class ColumnaRepositoryTest {

    @Inject
    ColumnaRepository columnaRepository;

    @Inject
    TableroRepository tableroRepository;

    @BeforeEach
    public void setUp() {
        Tablero tablero = new Tablero(null, "PROYTECTO SCRUM", "API CON QUARKUS", null);
        Columna columna = new Columna(null, null, null, "DONE", false);
        columnaRepository.persist(columna);
        tableroRepository.persist(tablero);
    }

    @Test
    public void obtenerColumnasPorTableroTest() {
       List<Columna> columnas = columnaRepository.obtenerColumnaPorTablero(1L);
       assertNotNull(columnas);
       assertEquals(0, columnas.size());
    }

    @Test
    public void obtenerColumnasPorNombreTest() {
        Columna columna = columnaRepository.obtenerPorTitulo("DONE");
        assertNotNull(columna);
        assertEquals("DONE", columna.getTitulo());
    }

    @Test
    public void obtenerColumnaPorIdTest() {
        Columna columna = columnaRepository.obtenerPorId(1L);
        assertEquals(1L, columna.getId());
        assertNotNull(columna);
    }

    @Test
    public void guardarColumnaTest() {
        Columna columna = new Columna(null, null , null, "DONE", false);
        columnaRepository.guardar(columna);
        Columna columna1 = columnaRepository.obtenerPorTitulo("DONE");
        assertEquals("DONE", columna1.getTitulo());
    }

    @Test
    public void obtenerColumnasTest() {
        List<Columna> columnas = columnaRepository.obtenerTodos();
        assertNotNull(columnas);
    }
}
