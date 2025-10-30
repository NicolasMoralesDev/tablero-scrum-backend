package com.nicolasmoralesTest.controller;

import com.nicolasmorales.bo.IEtiquetaBO;
import com.nicolasmorales.dto.EtiquetaDTO;
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
public class EtiquetaControllerTest {

    @InjectMock
    IEtiquetaBO etiquetaBO;

    @Test
    @DisplayName(value = "obtenerEtiquetasController")
    public void obtenerEtiqueta() {
        given()
                .when().get("/etiquetas")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName(value = "guardarEtiquetaController")
    public void guardarEtiquetaTest() throws BussinesException {
        EtiquetaDTO input = new EtiquetaDTO(null,"DONE", false);
        when(etiquetaBO.crearEtiqueta(input)).thenReturn(input);
        given()
                .header("Content-Type", "application/json")
                .body(input)
                .when().post("/etiquetas")
                .then()
                .statusCode(200)
                .body("nombre", is("DONE"));
    }

    @Test
    @DisplayName("borrarEtiquetaController")
    public void borrarEtiquetaTest() throws BussinesException {
        EtiquetaDTO input = new EtiquetaDTO(null,"DONE", false);
        EtiquetaDTO mockResponse = new EtiquetaDTO(1L,"DONE", false);
        when(etiquetaBO.crearEtiqueta(input)).thenReturn(mockResponse);

        given()
                .queryParam("id", mockResponse.id())
                .when().delete("/etiquetas")
                .then()
                .statusCode(200)
                .body("msg", is("Etiqueta borrada con éxito!"));
    }
}
