package com.nicolasmoralesTest.repository;

import com.nicolasmorales.entity.Etiqueta;
import com.nicolasmorales.repository.impl.EtiquetaRepository;
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
public class EtiquetaRepositoryTest {

    @Inject
    EtiquetaRepository etiquetaRepository;

    @BeforeEach
    public void setUp() {
        etiquetaRepository.persist(new Etiqueta("EN TESTING"));
    }

    @Test
    public void obtenerTodasTest() {
        List<Etiqueta> etiquetas = etiquetaRepository.obtenerTodos();
        assertNotNull(etiquetas);
        assertEquals(1, etiquetas.size());
    }

    @Test
    public void obtenerEtiquetaPorNombreTest() {
        Etiqueta etiqueta = etiquetaRepository.obtenerEtiquetaPorNombre("EN TESTING");
        assertNotNull(etiqueta);
        assertEquals("EN TESTING", etiqueta.getNombre());
    }

    @Test
    public void obtenerEtiquetaPorIdTest() {
        Etiqueta etiqueta = etiquetaRepository.obtenerPorId(1L);
        assertNotNull(etiqueta);
        assertEquals(1L, etiqueta.getId());
    }

    @Test
    public void guardarEtiquetaTest() {
        etiquetaRepository.guardar(new Etiqueta("EN DESARROLLO"));
        Etiqueta etiqueta = etiquetaRepository.obtenerEtiquetaPorNombre("EN DESARROLLO");
        assertNotNull(etiqueta);
        assertEquals("EN DESARROLLO", etiqueta.getNombre());
    }

}
