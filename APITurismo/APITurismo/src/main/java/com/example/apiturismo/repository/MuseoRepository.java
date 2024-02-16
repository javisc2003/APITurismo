package com.example.apiturismo.repository;

import com.example.apiturismo.models.Museo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Interfaz que proporciona métodos de acceso a datos para la entidad {@code Museo}.
 */
public interface MuseoRepository extends JpaRepository<Museo, Integer> {
    /**
     * Obtiene un museo por su identificador único.
     *
     * @param id El identificador único del museo.
     * @return El museo con el identificador proporcionado, o {@code null} si no se encuentra.
     */
    public Museo getMuseoById(Integer id);

    /**
     * Obtiene un museo por su nombre.
     *
     * @param nombre El nombre del museo.
     * @return El museo con el nombre proporcionado, o {@code null} si no se encuentra.
     */
    public Museo getMuseoByNombre(String nombre);

    /**
     * Obtiene una lista de museos cuyo precio de entrada es anterior al valor proporcionado.
     *
     * @param precio El precio límite para la búsqueda.
     * @return Una lista de museos con precios de entrada inferiores al valor proporcionado.
     */
    public List<Museo> getMuseoByPrecioBefore(Double precio);

    /**
     * Obtiene una lista de museos por su dirección.
     *
     * @param direccion La dirección de los museos.
     * @return Una lista de museos que pertenecen a la dirección proporcionada.
     */
    public List<Museo> getMuseoByDireccion(String direccion);
}
