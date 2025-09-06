package com.nicolasmorales.bo;

import com.nicolasmorales.dto.TableroDTO;
import com.nicolasmorales.exception.BussinesException;

import java.util.List;

public interface ITableroBO {

    List<TableroDTO> obtenerTableros();

    void borrarTableroPorId(Long id) throws BussinesException;

    TableroDTO crearTablero(TableroDTO tableroDTO) throws BussinesException;
}
