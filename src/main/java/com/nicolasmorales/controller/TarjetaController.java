package com.nicolasmorales.controller;

import com.nicolasmorales.bo.ITarjetaBO;
import com.nicolasmorales.dto.ColumnaDTO;
import com.nicolasmorales.dto.TarjetaDTO;
import com.nicolasmorales.exception.BussinesException;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/tarjetas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TarjetaController {

    @Inject
    ITarjetaBO tarjetaBO;

    @GET
    public Response obtenerTarjetas() {
        return Response.ok(tarjetaBO.obtenerTarjetas()).build() ;
    }

    @GET
    @Path("/tablero")
    public Response obtenerTarjetasPorTablero(@QueryParam(value = "tablero") Long tablero) {
        return Response.ok(tarjetaBO.obtenerTarjetasPorTablero(tablero)).build() ;
    }

    @POST
    public Response guardarTarjeta(TarjetaDTO tarjeta) throws BussinesException {
        return Response.ok(tarjetaBO.crearTarjeta(tarjeta)).build() ;
    }

    @DELETE
    public Response borrarTarjeta(@QueryParam(value = "id") Long id) throws BussinesException {
        tarjetaBO.borrarTarjetaPorId(id);
        return Response.ok().build() ;
    }
}
