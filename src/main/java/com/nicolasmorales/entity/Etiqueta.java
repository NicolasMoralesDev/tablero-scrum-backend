package com.nicolasmorales.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ETIQUETAS")
public class Etiqueta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "BORRADO")
    private boolean borrado;

}
