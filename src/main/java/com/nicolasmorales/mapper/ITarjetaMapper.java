package com.nicolasmorales.mapper;

import com.nicolasmorales.dto.TarjetaDTO;
import com.nicolasmorales.entity.Tarjeta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Mapper(componentModel = "jakarta", uses = IEtiquetaMapper.class)
public interface ITarjetaMapper {

    @Mappings({
            @Mapping(target = "id", source = "tarjeta.id"),
            @Mapping(target = "titulo", source = "tarjeta.titulo"),
            @Mapping(target = "etiquetas", source = "tarjeta.etiquetas"),
            @Mapping(target = "descripcion", source = "tarjeta.descripcion"),
            @Mapping(target = "fechaDeCreacion", expression = "java(mapFechaToLocalDate(tarjeta.getFechaDeCreacion()))", dateFormat = "ddMMyyyy"),
            @Mapping(target = "borrado", source = "tarjeta.borrado"),
    })
    TarjetaDTO tarjetaToTarjetaDTO(Tarjeta tarjeta);

    @Mappings({
            @Mapping(target = "id", source = "tarjeta.id"),
            @Mapping(target = "titulo", source = "tarjeta.titulo"),
            @Mapping(target = "etiquetas", source = "tarjeta.etiquetas", ignore = true),
            @Mapping(target = "descripcion", source = "tarjeta.descripcion"),
            @Mapping(target = "fechaDeCreacion", source = "tarjeta.fechaDeCreacion", dateFormat = "ddMMyyyy", ignore = true),
            @Mapping(target = "borrado", source = "tarjeta.borrado"),
    })
    Tarjeta tarjetaDTOToTarjeta(TarjetaDTO tarjeta);

    default LocalDate mapFechaToLocalDate(LocalDateTime fechaCreacion) {
        return fechaCreacion.toLocalDate();
    }

}
