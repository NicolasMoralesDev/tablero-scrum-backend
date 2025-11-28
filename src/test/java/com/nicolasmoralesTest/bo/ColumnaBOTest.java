package com.nicolasmoralesTest.bo;

import com.nicolasmorales.bo.impl.ColumnaBO;
import com.nicolasmorales.dto.ColumnaDTO;
import com.nicolasmorales.entity.Columna;
import com.nicolasmorales.exception.BussinesException;
import com.nicolasmorales.mapper.IColumnaMapper;
import com.nicolasmorales.repository.impl.ColumnaRepository;
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
public class ColumnaBOTest {

    @Mock
    ColumnaRepository columnaRepository;

    @Mock
    IColumnaMapper columnaMapper;

    @InjectMocks
    ColumnaBO columnaBO;

    private Columna columna;

    @BeforeEach
    void setUp() {
//        Tablero tablero = new Tablero(1L, "Proyecto Quarkus", "Proyecto completo con quarkus",
//                LocalDate.now());
        columna = new Columna(1L, null,
                null, "DONE", false);
    }

    @Test
    @DisplayName(value = "postColumnaExistenteBO")
    void crearColumnaExistenteTest() {
        //Arrange
        ColumnaDTO columnaDTO1 = new ColumnaDTO(1L, null,
                null, "DONE", false);
        when(columnaRepository.obtenerPorTitulo("DONE")).thenReturn(columna);
        //Assert
        BussinesException exception = assertThrows(BussinesException.class, () -> {
            columnaBO.crearColumna(columnaDTO1);
        });
        assertEquals("La columna con el nombre DONE ya existe",
                exception.getMessage());
    }

    @Test
    @DisplayName(value = "postColumnaNoExistenteBO")
    void crearColumnaNoExistenteTest() throws BussinesException {
        //Arrange
        ColumnaDTO columnaDTO1 = new ColumnaDTO(1L, null,
                null, "DONE", false);
        when(columnaRepository.obtenerPorTitulo("DONE")).thenReturn(null);
        when(columnaMapper.columnaDTOToColumna(columnaDTO1)).thenReturn(columna);
        //Act
        ColumnaDTO columnaDTO = columnaBO.crearColumna(columnaDTO1);
        //Assert
        assertEquals(columnaDTO1.titulo(), columnaDTO.titulo());
    }

    @Test
    @DisplayName(value = "getColumnasBO")
    void obtenerColumnasTest() {
        //Arrange
        when(columnaRepository.obtenerTodos()).thenReturn(List.of(columna));
        //Act
        List<ColumnaDTO> columnas = columnaBO.obtenerColumnas();
        //Assert
        assertEquals(1, columnas.size());
        assertFalse(columnas.isEmpty());
    }

    @Test
    @DisplayName(value = "borrarColumnaPorTituloBO")
    void borrarColumnaPorTituloTest() throws BussinesException {
        //Arrange
        when(columnaRepository.obtenerPorTitulo("Proyecto Quarkus")).thenReturn(columna);
        when(columnaRepository.findById(1L)).thenReturn(columna);
        Columna columna1 = columnaRepository.findById(1L);
        //Act
        columnaBO.borrarColumnaPorTitulo("Proyecto Quarkus");
        //Assert
        assertTrue(columna1.isBorrado());
        BussinesException exception = assertThrows(BussinesException.class, () -> {
            columnaBO.borrarColumnaPorTitulo("Proyectos");
        });
        assertEquals("Error al borrar, no se encontro la columna ",
                exception.getMessage());
    }
}
