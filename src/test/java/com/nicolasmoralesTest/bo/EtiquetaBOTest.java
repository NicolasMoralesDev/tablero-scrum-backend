package com.nicolasmoralesTest.bo;

import com.nicolasmorales.bo.impl.EtiquetaBO;
import com.nicolasmorales.dto.EtiquetaDTO;
import com.nicolasmorales.entity.Etiqueta;
import com.nicolasmorales.exception.BussinesException;
import com.nicolasmorales.mapper.IEtiquetaMapper;
import com.nicolasmorales.repository.impl.EtiquetaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EtiquetaBOTest {

    @Mock // Objeto falso
    private EtiquetaRepository etiquetaRepository;

    @Mock
    private IEtiquetaMapper etiquetaMapper;

    @InjectMocks // Objeto verdadero
    private EtiquetaBO etiquetaBO;

    @Captor
    private ArgumentCaptor<Etiqueta> clientCaptor;

    Etiqueta etiquetaInput;

    @BeforeEach
    void setUp() {
        etiquetaInput = new Etiqueta(1L, "DONE", false);
    }

    @Test
    @DisplayName(value = "getEtiquetasBO")
    void obtenerEtiquetasTest() {
        //Arrange
        EtiquetaDTO etiquetaDTO = new EtiquetaDTO(1L, "DONE", false);
        when(etiquetaRepository.obtenerTodos()).thenReturn(List.of(etiquetaInput));
        when(etiquetaMapper.etiquetaToEtiquetaDTO(etiquetaInput)).thenReturn(etiquetaDTO);
        //ACT
        List<EtiquetaDTO> etiquetas = etiquetaBO.obtenerEtiquetas();
        //ASSERT
        assertFalse(etiquetas.isEmpty(), "La lista de etiquetas no debería estar vacía");
    }

    @Test
    @DisplayName(value = "deleteEtiquetaBO")
    void borrarEtiquetaTest() throws BussinesException {
        //Arrange
        when(etiquetaRepository.obtenerPorId(1L)).thenReturn(etiquetaInput);
        when(etiquetaRepository.findById(1L)).thenReturn(etiquetaInput);
        //Act
        etiquetaBO.borrarEtiquetaPorId(1L);
        Etiqueta etiqueta = etiquetaRepository.findById(1L);
        //Assert
        assertTrue(etiqueta.isBorrado());
        BussinesException exception = assertThrows(BussinesException.class, () -> {
            etiquetaBO.borrarEtiquetaPorId(2L);
        });
        assertEquals("Error al intentar borrar la etiqueta con id: 2 no existe!",
                exception.getMessage());
    }

    @Test
    @DisplayName(value = "postEtiquetaNoExistenteBO")
    void crearEtiquetaNoExistenteTest() throws BussinesException {
        //Arrange
        EtiquetaDTO etiquetaDTO = new EtiquetaDTO(null, "DONE", false);
        when(etiquetaMapper.etiquetaDTOToEtiqueta(etiquetaDTO)).thenReturn(etiquetaInput);
        when(etiquetaRepository.obtenerEtiquetaPorNombre("DONE")).thenReturn(null);
        //Act
        EtiquetaDTO etiqueta = etiquetaBO.crearEtiqueta(etiquetaDTO);
        //Assert
        assertEquals("DONE", etiqueta.nombre());
        verify(etiquetaRepository).guardar(etiquetaInput);
    }

    @Test
    @DisplayName(value = "postColumnaExistenteBO")
    void crearColumnaExistenteTest() {
        //Arrange
        EtiquetaDTO etiquetaDTO1 = new EtiquetaDTO(1L, "DONE",
                false);
        when(etiquetaRepository.obtenerEtiquetaPorNombre("DONE")).thenReturn(etiquetaInput);
        //Assert
        BussinesException exception = assertThrows(BussinesException.class, () -> {
            etiquetaBO.crearEtiqueta(etiquetaDTO1);
        });
        assertEquals("La etiqueta con el nombre DONE ya existe",
                exception.getMessage());
    }
}
