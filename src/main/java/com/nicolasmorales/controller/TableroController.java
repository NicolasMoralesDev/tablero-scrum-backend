package com.nicolasmorales.controller;

import com.nicolasmorales.bo.ITableroBO;
import com.nicolasmorales.dto.RESTResponse;
import com.nicolasmorales.dto.TableroDTO;
import com.nicolasmorales.exception.BussinesException;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/tableros")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TableroController {

    @Inject
    ITableroBO tableroBO;

    @GET
    public Response obtenerTableros() {
        return Response.ok(tableroBO.obtenerTableros()).build() ;
    }

    @POST
    public Response guardarTablero(TableroDTO tableroDTO) throws BussinesException {
        return Response.ok(tableroBO.crearTablero(tableroDTO)).build() ;
    }

    @DELETE
    public Response borrarTablero(@QueryParam(value = "id") Long id) throws BussinesException {
        tableroBO.borrarTableroPorId(id);
        return Response.ok(new RESTResponse("Tablero borrada con éxito!")).build();
    }
}
