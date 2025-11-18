package com.nicolasmoralesTest.repository;

import com.nicolasmorales.entity.Tarjeta;
import com.nicolasmorales.repository.impl.TarjetaRepository;
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
public class TarjetaRepositoryTest {

    @Inject
    TarjetaRepository tarjetaRepository;

    @BeforeEach
    public void setUp() {
        Tarjeta tarjeta = new Tarjeta(null, "AGREGAR ENPOINTS", null, "agregar endpoints para la api rest",
                null, false);
        tarjetaRepository.persist(tarjeta);
    }

    @Test
    public void obtenerTodas() {
       List<Tarjeta> tarjetas = tarjetaRepository.obtenerTodos();
       assertNotNull(tarjetas);
       assertEquals(2, tarjetas.size());
    }

    @Test
    public void obtenerTarjetasPorId() {
        Tarjeta tarjeta = tarjetaRepository.obtenerPorId(1L);
        assertNotNull(tarjeta);
        assertEquals(1L, tarjeta.getId());
    }

    @Test
    public void obtenerTarjetaPorTitulo() {
        Tarjeta tarjeta = tarjetaRepository.obtenerPorTitulo("AGREGAR ENPOINTS");
        assertNotNull(tarjeta);
        assertEquals("AGREGAR ENPOINTS", tarjeta.getTitulo());
    }

    @Test
    public void guardarTarjetasTest() {
        Tarjeta tarjeta = new Tarjeta(null, "ARMAR LOS MANIFIESTOS DE KUBERNETES", null, "agregar los manifiestos de kubernetes",
                null, false);
        tarjetaRepository.guardar(tarjeta);
        Tarjeta tarjeta1 = tarjetaRepository.obtenerPorTitulo(tarjeta.getTitulo());
        assertNotNull(tarjeta1);
        assertEquals(tarjeta.getTitulo(), tarjeta1.getTitulo());
    }
}
