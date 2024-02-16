package com.example.apiturismo.repository;

import com.example.apiturismo.models.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Interfaz que proporciona métodos de acceso a datos para la entidad {@code Vuelo}.
 */
public interface VueloRepository extends JpaRepository<Vuelo, Integer> {
    /**
     * Obtiene un vuelo por su identificador único.
     *
     * @param id El identificador único del vuelo.
     * @return El vuelo con el identificador proporcionado, o {@code null} si no se encuentra.
     */
    public Vuelo getVueloById(Integer id);

    /**
     * Obtiene un vuelo por su nombre.
     *
     * @param nombre El nombre del vuelo.
     * @return El vuelo con el nombre proporcionado, o {@code null} si no se encuentra.
     */
    public Vuelo getVueloByNombre(String nombre);

    /**
     * Obtiene una lista de vuelos cuyo precio de entrada es anterior al valor proporcionado.
     *
     * @param precio El precio límite para la búsqueda.
     * @return Una lista de vuelos con precios de entrada inferiores al valor proporcionado.
     */
    public List<Vuelo> getVueloByPrecio(Double precio);

    /**
     * Obtiene una lista de vuelos por su compañía.
     *
     * @param compañia La compañía de los vuelos.
     * @return Una lista de vuelos que pertenecen a la compoañía proporcionada.
     */
    public Vuelo getVueloByCompañia(String compañia);
}
