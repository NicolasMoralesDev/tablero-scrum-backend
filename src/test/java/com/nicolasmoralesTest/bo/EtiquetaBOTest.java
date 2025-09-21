package com.nicolasmoralesTest.bo;

import com.nicolasmorales.bo.impl.EtiquetaBO;
import com.nicolasmorales.entity.Etiqueta;
import com.nicolasmorales.repository.impl.EtiquetaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EtiquetaBOTest {

    @Mock
    private EtiquetaRepository etiquetaRepository;

    @InjectMocks
    private EtiquetaBO etiquetaBO;

    @Captor
    private ArgumentCaptor<Etiqueta> clientCaptor;

    @Test
    @DisplayName(value = "getEtiqueta")
    public void obtenerEtiquetaTest() {
        //Arrange
        when(etiquetaRepository.obtenerEtiquetaPorNombre("DONE")).thenReturn(new Etiqueta("DONE"));
        //Act
        Etiqueta etiqueta = etiquetaRepository.obtenerEtiquetaPorNombre("DONE");
        //Assert
        assertEquals("DONE", etiqueta.getNombre());

    }

    @Test
    @DisplayName(value = "postEtiqueta")
    public void crearEtiquetaTest() {
        //Arrange
        etiquetaRepository.guardar(new Etiqueta("DONE"));
        verify(etiquetaRepository).guardar((Etiqueta) clientCaptor.capture());
        //Act
        Etiqueta etiqueta = clientCaptor.getValue();
        //Assert
        assertEquals("DONE", etiqueta.getNombre());
        verifyNoMoreInteractions(etiquetaRepository);
    }


}
