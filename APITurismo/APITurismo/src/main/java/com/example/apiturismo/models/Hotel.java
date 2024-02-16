package com.example.apiturismo.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

/**
 * Clase que representa un hotel.
 */
@Data
@Entity
@Table(name = "hotel")
public class Hotel implements Serializable {

    /**
     * Identificador único del hotel.
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Nombre del hotel.
     */
    private String nombre;

    /**
     * Precio del hotel.
     */
    private Double precio;

    /**
     * Dirección del hotel.
     */
    private String direccion;

    /**
     * Estrellas del hotel.
     */
    private Integer estrellas;
}
