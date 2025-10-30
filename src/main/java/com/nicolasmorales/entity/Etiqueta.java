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

    /**
      Constructor para pruebas unitarias
     */
    public Etiqueta(Long id, String nombre, boolean borrado) {
        this.id = id;
        this.nombre = nombre;
        this.borrado = borrado;
    }
}
