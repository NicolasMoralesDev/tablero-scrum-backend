package com.nicolasmorales.dto;

import java.time.LocalDate;

public record TableroDTO(
         Long id,
         String titulo,
         String descripcion,
         LocalDate fechaDeCreacion,
         boolean borrado
) {
}
