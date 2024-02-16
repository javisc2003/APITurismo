package com.example.apiturismo.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

/**
 * Clase que representa un vuelo.
 */
@Data
@Entity
@Table(name = "vuelo")
public class Vuelo implements Serializable {

    /**
     * Identificador único del vuelo.
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Nombre del vuelo.
     */

    private String nombre;

    /**
     * Precio del vuelo.
     */

    private Double precio;

    /**
     * Compañía del vuelo.
     */

    private String compañia;


    /**
     * Descuento aplicable a los vuelos.
     */
}
