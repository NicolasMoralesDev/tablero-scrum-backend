package com.nicolasmorales.bo;

import com.nicolasmorales.dto.ColumnaDTO;
import com.nicolasmorales.exception.BussinesException;

import java.util.List;

public interface IColumnaBO {

    List<ColumnaDTO> obtenerColumnas();

    List<ColumnaDTO> obtenerColumnasPorTablero(Long tablero);

    void borrarColumnaPorTitulo(String titulo) throws BussinesException;

    ColumnaDTO crearColumna(ColumnaDTO columna) throws BussinesException;
}
