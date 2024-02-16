package com.example.apiturismo.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

/**
 * Clase que representa un museo.
 */
@Data
@Entity
@Table(name = "museo")
public class Museo implements Serializable {

    /**
     * Identificador único del museo.
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Nombre del museo.
     */

    private String nombre;

    /**
     * Precio del museo.
     */

    private Double precio;

    /**
     * Dirección del museo.
     */

    private String direccion;

    /**
     * Descripción del museo.
     */

    private String descripcion;

}
