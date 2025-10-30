package com.nicolasmoralesTest.controller;

import com.nicolasmorales.bo.ITableroBO;
import com.nicolasmorales.dto.EtiquetaDTO;
import com.nicolasmorales.dto.TableroDTO;
import com.nicolasmorales.exception.BussinesException;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@QuarkusTest
@ExtendWith(MockitoExtension.class)
public class TableroControllerTest {

    @InjectMock
    ITableroBO tableroBO;

    @Test
    @DisplayName(value = "obtenerTablerosTestController")
    public void obtenerTablerosTest() {
        given()
                .when().get("/tableros")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName(value = "guardarTableroTestController")
    public void guardarTablero() throws BussinesException {
        TableroDTO input = new TableroDTO(
                null,"Proyecto Quarkus", "Quarkus proyect", null, false);
        when(tableroBO.crearTablero(input)).thenReturn(input);

        given()
                .header("Content-Type", "application/json")
                .body(input)
                .when().post("/tableros")
                .then()
                .statusCode(200)
                .body("titulo", is("Proyecto Quarkus"));
    }

    @Test
    @DisplayName(value = "borrarTableroTestController")
    public void borrarTablero() throws BussinesException {
        TableroDTO input = new TableroDTO(
                1L,"Proyecto Quarkus", "Quarkus proyect", null, false);
        when(tableroBO.crearTablero(input)).thenReturn(input);

        given()
                .queryParam("id", input.id())
                .when().delete("/tableros")
                .then()
                .statusCode(200)
                .body("msg", is("Tablero borrada con éxito!"));
    }
}
