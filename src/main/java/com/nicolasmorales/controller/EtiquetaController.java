package com.nicolasmorales.controller;

import com.nicolasmorales.bo.IEtiquetaBO;
import com.nicolasmorales.dto.ColumnaDTO;
import com.nicolasmorales.dto.EtiquetaDTO;
import com.nicolasmorales.exception.BussinesException;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/etiquetas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EtiquetaController {

    @Inject
    IEtiquetaBO etiquetaBO;

    @GET
    public Response obtenerEtiqueta() {
        return Response.ok(etiquetaBO.obtenerEtiquetas()).build() ;
    }

    @POST
    public Response guardarEtiqueta(EtiquetaDTO etiquetaDTO) throws BussinesException {
        return Response.ok(etiquetaBO.crearEtiqueta(etiquetaDTO)).build() ;
    }

    @DELETE
    public Response borrarEtiqueta(@QueryParam(value = "id") Long id) throws BussinesException {
        etiquetaBO.borrarEtiquetaPorId(id);
        return Response.ok().build() ;
    }
}
