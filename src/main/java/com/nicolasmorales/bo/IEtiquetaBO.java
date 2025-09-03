package com.nicolasmorales.bo;

import com.nicolasmorales.dto.EtiquetaDTO;
import com.nicolasmorales.exception.BussinesException;

import java.util.List;

public interface IEtiquetaBO {

    List<EtiquetaDTO> obtenerEtiquetas();

    void borrarEtiquetaPorId(Long id) throws BussinesException;

    EtiquetaDTO crearEtiqueta(EtiquetaDTO etiquetaDTO) throws BussinesException;

}
