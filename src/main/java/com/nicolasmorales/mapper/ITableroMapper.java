package com.nicolasmorales.mapper;

import com.nicolasmorales.dto.TableroDTO;
import com.nicolasmorales.entity.Tablero;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "jakarta", uses = { IColumnaMapper.class })
public interface ITableroMapper {

    @Mappings({
            @Mapping(target = "id", source = "tablero.id"),
            @Mapping(target = "titulo", source = "tablero.titulo"),
            @Mapping(target = "descripcion", source = "tablero.descripcion"),
            @Mapping(target = "fechaDeCreacion", source = "tablero.fechaDeCreacion", dateFormat = "ddMMyyyy"),
            @Mapping(target = "borrado", source = "tablero.borrado"),
    })
    TableroDTO tableroToTableroDTO(Tablero tablero);

    @Mappings({
            @Mapping(target = "id", source = "tablero.id", ignore = true),
            @Mapping(target = "titulo", source = "tablero.titulo"),
            @Mapping(target = "descripcion", source = "tablero.descripcion"),
            @Mapping(target = "fechaDeCreacion", source = "tablero.fechaDeCreacion", dateFormat = "ddMMyyyy"),
            @Mapping(target = "borrado", source = "tablero.borrado"),
    })
    Tablero tableroDTOToTablero(TableroDTO tablero);
}
