package com.nicolasmorales.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "TABLEROS")
public class Tablero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "TITULO")
    private String titulo;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany
    private List<Columna> columnas;
    @CreationTimestamp
    @Column(name = "FECHA_DE_CREACION")
    private LocalDate fechaDeCreacion;
    @Column(name = "BORRADO")
    private boolean borrado;

    public Tablero() {
    }

    public Tablero(Long id, String titulo, String descripcion, LocalDate fechaDeCreacion) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaDeCreacion = fechaDeCreacion;
    }
}
