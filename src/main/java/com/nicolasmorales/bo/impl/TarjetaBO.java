package com.nicolasmorales.bo.impl;

import com.nicolasmorales.bo.ITarjetaBO;
import com.nicolasmorales.dto.TarjetaDTO;
import com.nicolasmorales.entity.Tarjeta;
import com.nicolasmorales.exception.BussinesException;
import com.nicolasmorales.mapper.ITarjetaMapper;
import com.nicolasmorales.repository.impl.TarjetaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class TarjetaBO implements ITarjetaBO {

    private static final Logger LOG = Logger.getLogger(TarjetaBO.class);

    @Inject
    TarjetaRepository tarjetaRepository;

    @Inject
    ITarjetaMapper tarjetaMapper;


    @Override
    public List<TarjetaDTO> obtenerTarjetas() {
        return tarjetaRepository.obtenerTodos().stream().map(
                tarjeta -> tarjetaMapper.tarjetaToTarjetaDTO(tarjeta)
        ).collect(Collectors.toList());
    }

    @Override
    public List<TarjetaDTO> obtenerTarjetasPorTablero(Long id) {
        return tarjetaRepository.obtenerTarjetasPorColumna(id).stream()
                .map(tarjeta -> tarjetaMapper.tarjetaToTarjetaDTO(tarjeta)
        ).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void borrarTarjetaPorId(Long id) throws BussinesException {
        try {
            Tarjeta tarjeta = tarjetaRepository.obtenerPorId(id);
            if (tarjeta != null) {
                tarjeta.setBorrado(true);
            } else {
                throw new BussinesException("Error al intentar borrar la tarjeta con id: "+ id + " no existe!");
            }
        } catch (PersistenceException e) {
            LOG.error(e);
            throw new BussinesException("Error al intentar borrar la tarjeta con id: "+ id);
        }
    }

    @Override
    public TarjetaDTO crearTarjeta(TarjetaDTO tarjetaDTO) throws BussinesException {
        try {
            if (tarjetaRepository.obtenerPorTitulo(tarjetaDTO.titulo()) == null) {
                tarjetaRepository.guardar(tarjetaMapper.tarjetaDTOToTarjeta(tarjetaDTO));
                return tarjetaDTO;
            } else {
                throw new BussinesException(String.format("La tarjeta con el titulo %s ya existe", tarjetaDTO.titulo()));
            }
        } catch (PersistenceException e) {
            throw new BussinesException("Error al intentar crear la tarjeta");
        }
    }
}
