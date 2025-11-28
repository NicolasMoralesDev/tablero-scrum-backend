package com.nicolasmoralesTest.bo;

import com.nicolasmorales.bo.impl.TableroBO;
import com.nicolasmorales.dto.TableroDTO;
import com.nicolasmorales.entity.Tablero;
import com.nicolasmorales.exception.BussinesException;
import com.nicolasmorales.mapper.ITableroMapper;
import com.nicolasmorales.repository.impl.TableroRepository;
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
public class TableroBOTest {

    @Mock
    private TableroRepository tableroRepository;

    @Mock
    private ITableroMapper tableroMapper;

    @InjectMocks
    private TableroBO tableroBO;

    private Tablero tablero;

    @BeforeEach
    void setUp() {
        tablero = new Tablero(1L, "Nuevo proyecto", "Proyecto en quarkus",
                null);
    }

    @Test
    @DisplayName(value = "getTablerosBO")
    void obtenerTablerosTest() {
        //Arrange
        when(tableroRepository.obtenerTodos()).thenReturn(List.of(tablero));
        //Act
        List<TableroDTO> tableroDTOS = tableroBO.obtenerTableros();
        //Assert
        assertEquals(7, tableroDTOS.size());
        assertFalse(tableroDTOS.isEmpty());
    }

    @Test
    @DisplayName(value = "postTableroExistenteBO")
    void crearTableroExistenteTest() {
        //Arrange
        TableroDTO tableroDTO = new TableroDTO(1L, "Nuevo proyecto", "Proyecto en quarkus",
                null, false);
        when(tableroRepository.obtenerPorTitulo("Nuevo proyecto")).thenReturn(tablero);
        //Assert
        BussinesException exception = assertThrows(BussinesException.class, () -> {
            tableroBO.crearTablero(tableroDTO);
        });
        assertEquals("El tablero con el titulo Nuevo proyecto ya existe",
                exception.getMessage());
    }

    @Test
    @DisplayName(value = "postTableroNoExistenteBO")
    void crearTableroNoExistenteTest() throws BussinesException {
        //Arrange
        TableroDTO tableroDTO = new TableroDTO(1L, "Nuevo proyecto", "Proyecto en quarkus",
                null, false);
        when(tableroRepository.obtenerPorTitulo("Nuevo proyecto")).thenReturn(null);
        //Assert
        TableroDTO tableroDTO1 = tableroBO.crearTablero(tableroDTO);
        assertEquals(tableroDTO.titulo(), tableroDTO1.titulo());
    }

    @Test
    @DisplayName(value = "deleteTableroBO")
    void borrarTableroTest() throws BussinesException {
        //Arrange
        when(tableroRepository.obtenerPorId(1L)).thenReturn(tablero);
        Tablero tablero1 = tableroRepository.obtenerPorId(1L);
        //Act
        tableroBO.borrarTableroPorId(1L);
        //Assert
        assertTrue(tablero1.isBorrado());
        BussinesException exception = assertThrows(BussinesException.class, () -> {
            tableroBO.borrarTableroPorId(2L);
        });
        assertEquals("Error al intentar borrar el tablero con id: 2 no existe!",
                exception.getMessage());
    }
}
