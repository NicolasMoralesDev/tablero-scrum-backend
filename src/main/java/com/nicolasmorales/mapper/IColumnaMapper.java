package com.nicolasmorales.mapper;

import com.nicolasmorales.dto.ColumnaDTO;
import com.nicolasmorales.entity.Columna;
import org.mapstruct.*;

@Mapper(componentModel = "jakarta",
        uses = {
          ITarjetaMapper.class,
        }
)
public interface IColumnaMapper {

    @Mappings({
            @Mapping(target = "id", source = "columna.id"),
            @Mapping(target = "tarjetas", source = "columna.tarjetas"),
            @Mapping(target = "tablero", source = "columna.tablero", ignore = true),
            @Mapping(target = "titulo", source = "columna.titulo"),
            @Mapping(target = "borrado", source = "columna.borrado"),
    })
    ColumnaDTO columnaToColumnaDTO(Columna columna);

    @Mappings({
            @Mapping(target = "id", source = "columna.id"),
            @Mapping(target = "tarjetas", source = "columna.tarjetas", ignore = true),
            @Mapping(target = "tablero", source = "columna.tablero", ignore = true),
            @Mapping(target = "titulo", source = "columna.titulo"),
            @Mapping(target = "borrado", source = "columna.borrado"),
    })
    Columna columnaDTOToColumna(ColumnaDTO columna);

}
