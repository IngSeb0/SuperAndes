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

    // Obtener una ciudad por su código
    @GetMapping("/{codigo}")
    public ResponseEntity<Ciudad> obtenerCiudadPorCodigo(@PathVariable("codigo") Long codigo) {
        Ciudad ciudad = ciudadRepository.obtenerCiudadPorCodigo(codigo);
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
            ciudadRepository.insertarCiudad(ciudad.getCodigo(), ciudad.getNombre());
            return new ResponseEntity<>("Ciudad creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la ciudad", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar una ciudad por su código
    @PostMapping("/{codigo}/edit/save")
    public ResponseEntity<String> actualizarCiudad(@PathVariable("codigo") Long codigo, @RequestBody Ciudad ciudad) {
        try {
            ciudadRepository.actualizarCiudad(codigo, ciudad.getNombre());
            return new ResponseEntity<>("Ciudad actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la ciudad", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Eliminar una ciudad por su código
    @PostMapping("/{codigo}/delete")
    public ResponseEntity<String> eliminarCiudad(@PathVariable("codigo") Long codigo) {
        try {
            ciudadRepository.eliminarCiudad(codigo);
            return new ResponseEntity<>("Ciudad eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la ciudad", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
