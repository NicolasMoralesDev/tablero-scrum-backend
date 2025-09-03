package com.nicolasmorales.bo.impl;

import com.nicolasmorales.bo.IColumnaBO;
import com.nicolasmorales.dto.ColumnaDTO;
import com.nicolasmorales.entity.Columna;
import com.nicolasmorales.exception.BussinesException;
import com.nicolasmorales.mapper.IColumnaMapper;
import com.nicolasmorales.repository.impl.ColumnaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ColumnaBO implements IColumnaBO {

    private static final Logger LOG = Logger.getLogger(ColumnaBO.class);

    @Inject
    ColumnaRepository columnaRepository;

    @Inject
    IColumnaMapper columnaMapper;

    @Override
    public List<ColumnaDTO> obtenerColumnas() {
        return columnaRepository.obtenerTodos()
                .stream().map(columna ->
                        columnaMapper.columnaToColumnaDTO(columna)
                ).collect(Collectors.toList());
    }

    @Override
    public List<ColumnaDTO> obtenerColumnasPorTablero(Long tablero) {
        return columnaRepository.obtenerColumnaPorTablero(tablero).stream().map(
                columna -> columnaMapper.columnaToColumnaDTO(columna)
        ).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void borrarColumnaPorTitulo(String titulo) throws BussinesException {
        try {
            Columna columna = columnaRepository.obtenerPorTitulo(titulo);
            if (columna != null) {
                columna.setBorrado(true);
            } else {
                throw new BussinesException("Error al borrar, no se encontro la columna ");
            }
        } catch (PersistenceException e) {
            LOG.error(e.getMessage());
            throw new BussinesException(e.getMessage());
        }
    }

    @Override
    public ColumnaDTO crearColumna(ColumnaDTO columna) throws BussinesException {
       try {
           if (columnaRepository.obtenerPorTitulo(columna.titulo()) == null) {
               columnaRepository.guardar(columnaMapper.columnaDTOToColumna(columna));
               return columna;
           } else {
               throw new BussinesException(String.format("La columna con el nombre %s ya existe", columna.titulo()));
           }
       } catch (PersistenceException e) {
           throw new BussinesException("Error al intentar registrar la columna");
       }
    }
}
