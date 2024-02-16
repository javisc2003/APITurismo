package com.example.apiturismo.controllers;

import com.example.apiturismo.models.Museo;
import com.example.apiturismo.repository.MuseoRepository;
import com.example.apiturismo.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turismo")
public class MuseoController {

    /**
     * Enlace al repositorio de museo.
     */
    @Autowired
    private MuseoRepository museoRepository;

    /**
     * Enlace al servicio de seguridad.
     */
    @Autowired
    private SecurityService securityService;

    /**
     * Obtiene todos los museos disponibles.
     *
     * @return Lista de museos.
     */
    @GetMapping("/museo")
    public List<Museo> getAllMuseos() {
        return museoRepository.findAll();
    }

    /**
     * Obtiene un museo por su identificador único.
     *
     * @param id El identificador único del museo.
     * @return El museo con el identificador proporcionado.
     */
    @GetMapping("/museo/id/{id}")
    public Museo getMuseobyId(@PathVariable Integer id) {
        return museoRepository.getMuseoById(id);
    }

    /**
     * Obtiene un museo por su nombre.
     *
     * @param nombre El nombre del museo.
     * @return El museo con el nombre proporcionado.
     */
    @GetMapping("/museo/nombre/{nombre}")
    public Museo getMuseobyNombre(@PathVariable String nombre) {
        return museoRepository.getMuseoByNombre(nombre);
    }

    /**
     * Obtiene una lista de museos cuyo precio de entrada es inferior al valor proporcionado.
     *
     * @param precio El precio límite para la búsqueda.
     * @return Lista de museos con precios de entrada inferiores al valor proporcionado.
     */
    @GetMapping("/museo/precio/{precio}")
    public List<Museo> getMuseobyPrecio(@PathVariable Double precio) {
        return museoRepository.getMuseoByPrecioBefore(precio);
    }

    /**
     * Obtiene una lista de museos por su temática.
     *
     * @param direccion La temática o categoría de los museos.
     * @return Lista de museos que pertenecen a la temática proporcionada.
     */
    @GetMapping("/museo/direccion/{direccion}")
    public List<Museo> getMuseobyDireccion(@PathVariable String direccion) {
        return museoRepository.getMuseoByDireccion(direccion);
    }

    /**
     * Crea un nuevo museo.
     *
     * @param museo El museo a crear.
     * @param token El token de autenticación.
     * @return ResponseEntity con el resultado de la operación.
     */
    @PostMapping("/museo/post")
    public ResponseEntity<Museo> nuevo(@RequestBody Museo museo, @RequestParam String token) {
        if (securityService.tokenDeValidacion(token)) {
            return new ResponseEntity<Museo>(museoRepository.save(museo), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Actualiza un museo existente.
     *
     * @param id El identificador único del museo a actualizar.
     * @param nuevoMuseo Los nuevos datos del museo.
     * @param token El token de autenticación.
     * @return ResponseEntity con el resultado de la operación.
     */
    @PutMapping("/museo/put/{id}")
    public ResponseEntity<Museo> put(@PathVariable Integer id, @RequestBody Museo nuevoMuseo, @RequestParam String token){
        if (!securityService.tokenDeValidacion(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            Museo museo = new Museo();
            var museoOpcional = museoRepository.findById(id);
            if (museoOpcional.isEmpty()) {
                museo = nuevoMuseo;
            } else {
                museo = museoOpcional.get();
                museo.setNombre(nuevoMuseo.getNombre());
                museo.setPrecio(nuevoMuseo.getPrecio());
                museo.setDireccion(nuevoMuseo.getDireccion());
                museo.setDescripcion(nuevoMuseo.getDescripcion());
            }
            return new ResponseEntity<Museo>(museoRepository.save(museo), HttpStatus.OK);
        }
    }

    /**
     * Elimina un museo por su identificador único.
     *
     * @param id El identificador único del museo a eliminar.
     * @param token El token de autenticación.
     * @return ResponseEntity con el resultado de la operación.
     */
    @DeleteMapping("/museo/delete/{id}")
    public ResponseEntity<Museo> delete(@PathVariable Integer id,  @RequestParam String token){
        ResponseEntity<Museo> respuesta = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        if( securityService.tokenDeValidacion(token) ){
            Museo salida = new Museo();
            if (museoRepository.existsById(id)) {
                salida = museoRepository.findById(id).get();
                museoRepository.deleteById(id);
                respuesta = new ResponseEntity<Museo>(salida, HttpStatus.OK);
            } else {
                respuesta = new ResponseEntity<Museo>(salida, HttpStatus.NOT_FOUND);
            }
        }
        return respuesta;
    }
}
