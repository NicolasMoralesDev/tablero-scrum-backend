package com.nicolasmoralesTest.controller;

import com.nicolasmorales.bo.IColumnaBO;
import com.nicolasmorales.dto.ColumnaDTO;
import com.nicolasmorales.exception.BussinesException;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@QuarkusTest
@ExtendWith(MockitoExtension.class)
public class ColumnaControllerTest {

    @InjectMock
    IColumnaBO columnaBO;


    @BeforeEach
    void setup() throws BussinesException {
        ColumnaDTO input = new ColumnaDTO(null,null,null, "SPRINT 1", false);
        when(columnaBO.crearColumna(input)).thenReturn(input);
    }

    @Test
    @DisplayName(value = "obtenerColumnasController")
    void obtenerColumnas() {
        given()
                .when().get("/columnas")
                .then()
                .statusCode(200);
    }

//    @Test
//    @DisplayName(value = "obtenerColumnasPorTableroController")
//    public void obtenerColumnasPorTablero() throws BussinesException {
//
//        ColumnaDTO input = new ColumnaDTO(null,null,null, "SPRINT 1", false);
//        ColumnaDTO mockResponse = new ColumnaDTO(1L,null,null,"SPRINT 1", false);
//        when(columnaBO.crearColumna(input)).thenReturn(mockResponse);
//
//        ColumnaDTO input = new ColumnaDTO(null,null,null, "SPRINT 1", false);
//        ColumnaDTO mockResponse = new ColumnaDTO(1L,null,null,"SPRINT 1", false);
//        when(columnaBO.crearColumna(input)).thenReturn(mockResponse);
//
//        given()
//                .queryParam("tablero", 1)
//                .when().get("/columnas")
//                .then()
//                .statusCode(200);
//    }

    @Test
    @DisplayName(value = "guardarColumnaController")
    void guardarColumna() throws BussinesException {
        ColumnaDTO input = new ColumnaDTO(null,null,null, "SPRINT 1", false);
        when(columnaBO.crearColumna(input)).thenReturn(input);
          given()
                  .header("Content-Type", "application/json")
                  .body(input)
                  .when().post("/columnas")
                  .then()
                  .statusCode(200)
                  .body("titulo", is("SPRINT 1"));
    }

    @Test
    @DisplayName(value = "borrarColumnaController")
    void borrarColumna() throws BussinesException {
        given()
                .queryParam("titulo", "SPRINT 1")
                .when().delete("/columnas")
                .then()
                .statusCode(200)
                .body("msg", is("Columna borrada con éxito!"));
    }
}
