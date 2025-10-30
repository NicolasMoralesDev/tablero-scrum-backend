package com.nicolasmoralesTest.bo;

import com.nicolasmorales.bo.impl.TarjetaBO;
import com.nicolasmorales.dto.TarjetaDTO;
import com.nicolasmorales.entity.Tarjeta;
import com.nicolasmorales.exception.BussinesException;
import com.nicolasmorales.mapper.ITarjetaMapper;
import com.nicolasmorales.repository.impl.TarjetaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TarjeraBOTest {

    @Mock
    private TarjetaRepository tarjetaRepository;

    @Mock
    ITarjetaMapper tarjetaMapper;

    @InjectMocks
    private TarjetaBO tarjetaBO;

    private Tarjeta tarjeta;

    @BeforeEach
    public void setUp() {
        tarjeta = new Tarjeta(1L, "FIX COLUMNAS", null, "Se agreglan columnas",
                null, false);
    }

    @Test
    @DisplayName(value = "getTarjetasBO")
    public void obtenerTarjetasTest() {
        //Arrange
        when(tarjetaRepository.obtenerTodos()).thenReturn(List.of(tarjeta));
        //Act
        List<TarjetaDTO> tarjetas = tarjetaBO.obtenerTarjetas();
        //Assert
        assertEquals(1, tarjetas.size());
        assertFalse(tarjetas.isEmpty());
    }

    @Test
    @DisplayName(value = "deleteTarjetasBO")
    public void borrarTarjetaPorIdTest() throws BussinesException {
        //Arrange
        when(tarjetaRepository.obtenerPorId(1L)).thenReturn(tarjeta);
        when(tarjetaRepository.findById(1L)).thenReturn(tarjeta);
        Tarjeta tarjeta1 = tarjetaRepository.findById(1L);
        //Act
        tarjetaBO.borrarTarjetaPorId(1L);
        //Assert
        assertTrue(tarjeta1.isBorrado());
        BussinesException exception = assertThrows(BussinesException.class, () -> {
            tarjetaBO.borrarTarjetaPorId(2L);
        });
        assertEquals("Error al intentar borrar la tarjeta con id: 2 no existe!",
                exception.getMessage());
    }

    @Test
    @DisplayName(value = "postTarjetaExistenteBO")
    public void crearTarjetaExistenteTest() {
        //Arrange
        TarjetaDTO tarjetaDTO = new TarjetaDTO(1L, "FIX COLUMNAS", "Se agreglan columnas",null ,
                null, false);
        when(tarjetaRepository.obtenerPorTitulo("FIX COLUMNAS")).thenReturn(tarjeta);
        //Assert
        BussinesException exception = assertThrows(BussinesException.class, () -> {
            tarjetaBO.crearTarjeta(tarjetaDTO);
        });
        assertEquals("La tarjeta con el titulo FIX COLUMNAS ya existe",
                exception.getMessage());
    }

    @Test
    @DisplayName(value = "postTarjetaNoExistenteBO")
    public void crearTarjetaNoExistenteTest() throws BussinesException {
        //Arrange
        TarjetaDTO tarjetaDTO = new TarjetaDTO(1L, "FIX COLUMNAS", "Se agreglan columnas",null ,
                null, false);
        when(tarjetaRepository.obtenerPorTitulo("FIX COLUMNAS")).thenReturn(null);
        //Act
        TarjetaDTO tarjetaDTO1 = tarjetaBO.crearTarjeta(tarjetaDTO);
        //Assert
        assertEquals(tarjetaDTO1.titulo(), tarjetaDTO.titulo());
    }
}
