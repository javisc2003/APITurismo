package com.example.apiturismo.controllers;

import com.example.apiturismo.models.Vuelo;
import com.example.apiturismo.repository.VueloRepository;
import com.example.apiturismo.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turismo")
public class VueloController {

    /**
     * Enlace al repositorio de platos gastronomicos.
     */
    @Autowired
    private VueloRepository vueloRepository;

    /**
     * Enlace al servicio de seguridad.
     */
    @Autowired
    private SecurityService securityService;

    /**
     * Obtiene todos los platos gastronómicos disponibles.
     *
     * @return Lista de platos gastronómicos.
     */
    @GetMapping("/vuelo")
    public List<Vuelo> getAllVuelos() {
        return vueloRepository.findAll();
    }

    /**
     * Obtiene un vuelo por su identificador único.
     *
     * @param id El identificador único del vuelo.
     * @return El vuelo con el identificador proporcionado.
     */
    @GetMapping("/vuelo/id/{id}")
    public Vuelo getVueloById(@PathVariable Integer id) {
        return vueloRepository.getVueloById(id);
    }

    /**
     * Obtiene un vuelo por su precio.
     *
     * @param precio El precio del vuelo.
     * @return El vuelo con el precio proporcionado.
     */
    @GetMapping("/vuelo/precio/{precio}")
    public List<Vuelo> getVuelobyPrecio(@PathVariable Double precio) {
        return vueloRepository.getVueloByPrecio(precio);
    }
    /**
     * Obtiene un vuelo por su nombre.
     *
     * @param nombre El nombre del vuelo.
     * @return El vuelo con el nombre proporcionado.
     */
    @GetMapping("/vuelo/nombre/{nombre}")
    public Vuelo getVuelobyNombre(@PathVariable String nombre) {
        return vueloRepository.getVueloByNombre(nombre);
    }

    /**
     * Obtiene un vuelo por su compañía.
     *
     * @param compañia La compañía del vuelo.
     * @return El vuelo con la compañía proporcionada.
     */
    @GetMapping("/vuelo/compañia/{compañia}")
    public Vuelo getVuelobyCompañia(@PathVariable String compañia) {
        return vueloRepository.getVueloByCompañia(compañia);
    }

    /**
     * Crea un nuevo vuelo.
     *
     * @param vuelo El vuelo a crear.
     * @param token El token de autenticación.
     * @return ResponseEntity con el resultado de la operación.
     */
    @PostMapping("/vuelo/post")
    public ResponseEntity<Vuelo> nuevo(@RequestBody Vuelo vuelo, @RequestParam String token) {
        if (securityService.tokenDeValidacion(token)) {
            return new ResponseEntity<Vuelo>(vueloRepository.save(vuelo), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Actualiza un vuelo existente.
     *
     * @param id El identificador único del vuelo a actualizar.
     * @param nuevoVuelo Los nuevos datos del vuelo.
     * @param token El token de autenticación.
     * @return ResponseEntity con el resultado de la operación.
     */
    @PutMapping("/vuelo/put/{id}")
    public ResponseEntity<Vuelo> put(@PathVariable Integer id, @RequestBody Vuelo nuevoVuelo, @RequestParam String token){
        if (!securityService.tokenDeValidacion(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            Vuelo vuelo = new Vuelo();
            var vueloOpcional = vueloRepository.findById(id);
            if (vueloOpcional.isEmpty()) {
                vuelo = nuevoVuelo;
            } else {
                vuelo = vueloOpcional.get();
                vuelo.setNombre(nuevoVuelo.getNombre());
                vuelo.setPrecio(nuevoVuelo.getPrecio());
                vuelo.setCompañia(nuevoVuelo.getCompañia());
            }
            return new ResponseEntity<Vuelo>(vueloRepository.save(vuelo), HttpStatus.OK);
        }
    }

    /**
     * Elimina un vuelo por su identificador único.
     *
     * @param id El identificador único del vuelo a eliminar.
     * @param token El token de autenticación.
     * @return ResponseEntity con el resultado de la operación.
     */
    @DeleteMapping("/vuelo/delete/{id}")
    public ResponseEntity<Vuelo> delete(@PathVariable Integer id,  @RequestParam String token){
        ResponseEntity<Vuelo> respuesta = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        if( securityService.tokenDeValidacion(token) ){
            Vuelo salida = new Vuelo();
            if (vueloRepository.existsById(id)) {
                salida = vueloRepository.findById(id).get();
                vueloRepository.deleteById(id);
                respuesta = new ResponseEntity<Vuelo>(salida, HttpStatus.OK);
            } else {
                respuesta = new ResponseEntity<Vuelo>(salida, HttpStatus.NOT_FOUND);
            }
        }
        return respuesta;
    }
}
