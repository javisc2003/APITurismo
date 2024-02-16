package com.example.apiturismo.controllers;

import com.example.apiturismo.models.Hotel;
import com.example.apiturismo.repository.HotelRepository;
import com.example.apiturismo.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turismo")
public class HotelController {
    /**
     * Enlace al repositorio de hoteles.
     */
    @Autowired
    private HotelRepository hotelRepository;

    /**
     * Enlace al servicio de seguridad.
     */
    @Autowired
    private SecurityService securityService;

    /**
     * Obtiene todos los hoteles disponibles.
     *
     * @return Lista de hoteles.
     */
    @GetMapping("/hotel")
    public List<Hotel> getAllHoteles() {
        return hotelRepository.findAll();
    }

    /**
     * Obtiene un hotel por su identificador único.
     *
     * @param id El identificador único del hotel.
     * @return El hotel con el identificador proporcionado.
     */
    @GetMapping("/hotel/id/{id}")
    public Hotel getById(@PathVariable Integer id) {
        return hotelRepository.getHotelById(id);
    }

    /**
     * Obtiene un hotel por su nombre.
     *
     * @param nombre El nombre del hotel.
     * @return El hotel con el nombre proporcionado.
     */
    @GetMapping("/hotel/nombre/{nombre}")
    public Hotel getHotelbyNombre(@PathVariable String nombre) {
        return hotelRepository.getHotelByNombre(nombre);
    }

    /**
     * Obtiene hoteles con un precio inferior al proporcionado.
     *
     * @param precio El precio máximo de los hoteles a buscar.
     * @return Lista de hoteles con precio inferior al proporcionado.
     */
    @GetMapping("/hotel/precio/{precio}")
    public List<Hotel> getHotelbyPrecio(@PathVariable Double precio) {
        return hotelRepository.getHotelByPrecioBefore(precio);
    }

    /**
     * Obtiene hoteles por la dirección.
     *
     * @param direccion La dirección de los hoteles.
     * @return Lista de hoteles con su dirección.
     */
    @GetMapping("/hotel/direccion/{direccion}")
    public List<Hotel> getHotelByDireccion(@PathVariable String direccion) {
        return hotelRepository.getHotelByDireccion(direccion);
    }


    /**
     * Obtiene hoteles por su calificación.
     *
     * @param estrellas Las estrellas del hotel.
     * @return Lista de hoteles con su calificación.
     */

    @GetMapping("/hotel/estrellas/{estrellas}")
    public List<Hotel> getHotelByEstrellas(@PathVariable Integer estrellas) {
        return hotelRepository.getHotelByEstrellas(estrellas);
    }

    /**
     * Crea un nuevo hotel.
     *
     * @param hotel El hotel a crear.
     * @param token El token de autenticación.
     * @return ResponseEntity con el resultado de la operación.
     */
    @PostMapping("/hotel/post")
    public ResponseEntity<Hotel> nuevo(@RequestBody Hotel hotel, @RequestParam String token) {
        if (securityService.tokenDeValidacion(token)) {
            return new ResponseEntity<Hotel>(hotelRepository.save(hotel), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Actualiza un hotel existente.
     *
     * @param id El identificador único del hotel a actualizar.
     * @param nuevoHotel Los nuevos datos del hotel.
     * @param token El token de autenticación.
     * @return ResponseEntity con el resultado de la operación.
     */
    @PutMapping("/hotel/put/{id}")
    public ResponseEntity<Hotel> put(@PathVariable Integer id, @RequestBody Hotel nuevoHotel, @RequestParam String token){
        if (!securityService.tokenDeValidacion(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            Hotel hotel = new Hotel();
            var hotelOpcional = hotelRepository.findById(id);
            if (hotelOpcional.isEmpty()) {
                hotel = nuevoHotel;
            } else {
                hotel = hotelOpcional.get();
                hotel.setNombre(nuevoHotel.getNombre());
                hotel.setPrecio(nuevoHotel.getPrecio());
                hotel.setDireccion(nuevoHotel.getDireccion());
                hotel.setEstrellas(nuevoHotel.getEstrellas());
            }
            return new ResponseEntity<Hotel>(hotelRepository.save(hotel), HttpStatus.OK);
        }
    }

    /**
     * Elimina un evento por su identificador único.
     *
     * @param id El identificador único del hotel a eliminar.
     * @param token El token de autenticación.
     * @return ResponseEntity con el resultado de la operación.
     */
    @DeleteMapping("/hotel/delete/{id}")
    public ResponseEntity<Hotel> delete(@PathVariable Integer id,  @RequestParam String token){
        ResponseEntity<Hotel> respuesta = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        if( securityService.tokenDeValidacion(token) ){
            Hotel salida = new Hotel();
            if (hotelRepository.existsById(id)) {
                salida = hotelRepository.findById(id).get();
                hotelRepository.deleteById(id);
                respuesta = new ResponseEntity<Hotel>(salida, HttpStatus.OK);
            } else {
                respuesta = new ResponseEntity<Hotel>(salida, HttpStatus.NOT_FOUND);
            }
        }
        return respuesta;
    }
}
