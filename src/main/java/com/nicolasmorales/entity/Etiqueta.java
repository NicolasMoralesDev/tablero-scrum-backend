package com.nicolasmorales.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "ETIQUETAS")
@NoArgsConstructor
public class Etiqueta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "BORRADO")
    private boolean borrado;

    public Etiqueta(String nombre) {
        this.nombre = nombre;
    }
}
