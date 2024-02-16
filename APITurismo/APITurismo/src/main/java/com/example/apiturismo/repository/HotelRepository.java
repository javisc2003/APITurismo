package com.example.apiturismo.repository;

import com.example.apiturismo.models.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Interfaz que proporciona métodos de acceso a datos para la entidad {@code Hotel}.
 */
public interface HotelRepository extends JpaRepository<Hotel, Integer> {
    /**
     * Obtiene un hotel por su identificador único.
     *
     * @param id El identificador único del hotel.
     * @return El hotel con el identificador proporcionado, o {@code null} si no se encuentra.
     */
    public Hotel getHotelById(Integer id);

    /**
     * Obtiene un hotel por su nombre.
     *
     * @param nombre El nombre del hotel.
     * @return El hotel con el nombre proporcionado, o {@code null} si no se encuentra.
     */
    public Hotel getHotelByNombre(String nombre);

    /**
     * Obtiene una lista de hoteles cuyo precio es anterior al valor proporcionado.
     *
     * @param precio El precio límite para la búsqueda.
     * @return Una lista de hoteles con precios de entrada inferiores al valor proporcionado.
     */
    public List<Hotel> getHotelByPrecioBefore(Double precio);

    /**
     * Obtiene una lista de hoteles por su dirección.
     *
     * @param direccion La dirección de los hoteles.
     * @return Una lista de hoteles que pertenecen a la dirección proporcionada.
     */
    public List<Hotel> getHotelByDireccion(String direccion);

    /**
     * Obtiene una lista de hoteles por su calificación.
     *
     * @param estrellas Las estrellas de los hoteles.
     * @return Una lista de hoteles que pertenecen a la calificación proporcionada.
     */
    public List<Hotel> getHotelByEstrellas(Integer estrellas);
}
