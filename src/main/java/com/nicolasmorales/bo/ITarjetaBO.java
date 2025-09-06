package com.nicolasmorales.bo;

import com.nicolasmorales.dto.TarjetaDTO;
import com.nicolasmorales.exception.BussinesException;

import java.util.List;

public interface ITarjetaBO {

    List<TarjetaDTO> obtenerTarjetas();

    List<TarjetaDTO> obtenerTarjetasPorTablero(Long id);

    void borrarTarjetaPorId(Long id) throws BussinesException;

    TarjetaDTO crearTarjeta(TarjetaDTO tarjetaDTO) throws BussinesException;
}
