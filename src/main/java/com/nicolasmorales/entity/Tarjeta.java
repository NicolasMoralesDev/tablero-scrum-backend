package com.nicolasmorales.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "TARJETAS")
public class Tarjeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "TITULO")
    private String titulo;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Etiqueta> etiquetas;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @CreationTimestamp
    @Column(name = "FECHA_DE_CREACION")
    private LocalDateTime fechaDeCreacion;
    @Column(name = "BORRADO")
    private boolean borrado;

    public Tarjeta() {
    }

    /**
     * Constructor para pruebas unitarias
     */
    public Tarjeta(Long id, String titulo, List<Etiqueta> etiquetas,
                   String descripcion,
                   LocalDateTime fechaDeCreacion, boolean borrado) {
        this.id = id;
        this.titulo = titulo;
        this.etiquetas = etiquetas;
        this.descripcion = descripcion;
        this.fechaDeCreacion = fechaDeCreacion;
        this.borrado = borrado;
    }

}
