package uniandes.edu.co.parranderos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.parranderos.modelo.Ciudad;
import uniandes.edu.co.parranderos.repositorio.CiudadRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

@RestController
@RequestMapping("/ciudades")
public class CiudadController {

    @Autowired
    private CiudadRepository ciudadRepository;

    // Obtener todas las ciudades
    @GetMapping
    public Collection<Ciudad> obtenerCiudades() {
        return ciudadRepository.obtenerTodasLasCiudades();
    }

    // Obtener una ciudad por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Ciudad> obtenerCiudadPorId(@PathVariable("id") Long id) {
        Ciudad ciudad = ciudadRepository.obtenerCiudadPorId(id);
        if (ciudad != null) {
            return new ResponseEntity<>(ciudad, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Insertar una nueva ciudad
    @PostMapping("/new/save")
    public ResponseEntity<String> insertarCiudad(@RequestBody Ciudad ciudad) {
        try {
            ciudadRepository.insertarCiudad(ciudad.getNombreCiudad());
            return new ResponseEntity<>("Ciudad creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la ciudad", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar una ciudad por su ID
    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> actualizarCiudad(@PathVariable("id") Long id, @RequestBody Ciudad ciudad) {
        try {
            ciudadRepository.actualizarCiudad(id, ciudad.getNombreCiudad());
            return new ResponseEntity<>("Ciudad actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la ciudad", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Eliminar una ciudad por su ID
    @PostMapping("/{id}/delete")
    public ResponseEntity<String> eliminarCiudad(@PathVariable("id") Long id) {
        try {
            ciudadRepository.eliminarCiudad(id);
            return new ResponseEntity<>("Ciudad eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la ciudad", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
