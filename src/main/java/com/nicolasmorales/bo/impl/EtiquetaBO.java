package com.nicolasmorales.bo.impl;

import com.nicolasmorales.bo.IEtiquetaBO;
import com.nicolasmorales.dto.EtiquetaDTO;
import com.nicolasmorales.entity.Etiqueta;
import com.nicolasmorales.exception.BussinesException;
import com.nicolasmorales.mapper.IEtiquetaMapper;
import com.nicolasmorales.repository.impl.EtiquetaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class EtiquetaBO implements IEtiquetaBO  {

    private static final Logger LOG = Logger.getLogger(EtiquetaBO.class);

    @Inject
    EtiquetaRepository etiquetaRepository;

    @Inject
    IEtiquetaMapper etiquetaMapper;

    @Override
    public List<EtiquetaDTO> obtenerEtiquetas() {
        return etiquetaRepository.obtenerTodos().stream()
                .map(etiqueta -> etiquetaMapper.etiquetaToEtiquetaDTO(etiqueta))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void borrarEtiquetaPorId(Long id) throws BussinesException {
        try {
            Etiqueta etiqueta = etiquetaRepository.obtenerPorId(id);
            if (etiqueta != null) {
                etiqueta.setBorrado(true);
            } else {
                throw new BussinesException("Error al intentar borrar la etiqueta con id: "+ id + " no existe!");
            }
        } catch (PersistenceException e) {
            LOG.error(e);
            throw new BussinesException(e.getMessage());
        }
    }

    @Override
    public EtiquetaDTO crearEtiqueta(EtiquetaDTO etiquetaDTO) throws BussinesException {
        try {
            if (etiquetaRepository.obtenerEtiquetaPorNombre(etiquetaDTO.nombre()) == null) {
                etiquetaRepository.guardar(etiquetaMapper.etiquetaDTOToEtiqueta(etiquetaDTO));
                return etiquetaDTO;
            } else {
                throw new BussinesException(String.format("La etiqueta con el nombre %s ya existe", etiquetaDTO.nombre()));
            }
        } catch (PersistenceException e) {
            throw new BussinesException("Error al intentar crear la etiqueta");
        }
    }
}
