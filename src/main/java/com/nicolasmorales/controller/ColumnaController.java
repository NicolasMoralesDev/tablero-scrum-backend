package com.nicolasmorales.controller;

import com.nicolasmorales.bo.IColumnaBO;
import com.nicolasmorales.dto.ColumnaDTO;
import com.nicolasmorales.dto.RESTResponse;
import com.nicolasmorales.entity.Columna;
import com.nicolasmorales.exception.BussinesException;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/columnas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ColumnaController {

    @Inject
    IColumnaBO columnaBO;

    @GET
    public Response obtenerColumnas() {
        return Response.ok(columnaBO.obtenerColumnas()).build();
    }

    @GET
    @Path("/tablero")
    public Response obtenerColumnasPorTablero(@QueryParam(value = "tablero") Long tablero) {
        return Response.ok(columnaBO.obtenerColumnasPorTablero(tablero)).build();
    }

    @POST
    public Response guardarColumna(ColumnaDTO columna) throws BussinesException {
        return Response.ok(columnaBO.crearColumna(columna)).build() ;
    }

    @DELETE
    public Response borrarColumna(@QueryParam(value = "titulo") String titulo) throws BussinesException {
        columnaBO.borrarColumnaPorTitulo(titulo);
        return Response.ok(new RESTResponse("Columna borrada con éxito!")).build();
    }

}
