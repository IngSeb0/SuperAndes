package uniandes.edu.co.parranderos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.parranderos.modelo.InfoExtraBodega;
import uniandes.edu.co.parranderos.repositorio.InfoExtraBodegaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

@RestController
@RequestMapping("/info_extra_bodegas")
public class InfoExtraBodegaController {

    @Autowired
    private InfoExtraBodegaRepository infoExtraBodegaRepository;

    // Obtener toda la información extra de las bodegas
    @GetMapping
    public Collection<InfoExtraBodega> obtenerInfoExtraBodegas() {
        return infoExtraBodegaRepository.obtenerTodaLaInfoExtraBodega();
    }

    // Obtener una información extra de bodega por su ID
    @GetMapping("/{id}")
    public ResponseEntity<InfoExtraBodega> obtenerInfoExtraBodegaPorId(@PathVariable("id") Long id) {
        InfoExtraBodega infoExtra = infoExtraBodegaRepository.obtenerInfoExtraBodegaPorId(id);
        if (infoExtra != null) {
            return new ResponseEntity<>(infoExtra, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Insertar una nueva información extra de bodega
    @PostMapping("/new/save")
    public ResponseEntity<String> insertarInfoExtraBodega(@RequestBody InfoExtraBodega infoExtra) {
        try {
            infoExtraBodegaRepository.insertarInfoExtraBodega(
                infoExtra.getTotalExistencia(),
                infoExtra.getCostoPromedio(),
                infoExtra.getCapacidadAlmacenamiento(),
                infoExtra.getNivelMinimoReorden()
            );
            return new ResponseEntity<>("Información extra de bodega creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la información extra de bodega", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar una información extra de bodega por su ID
    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> actualizarInfoExtraBodega(@PathVariable("id") Long id, @RequestBody InfoExtraBodega infoExtra) {
        try {
            infoExtraBodegaRepository.actualizarInfoExtraBodega(
                id, 
                infoExtra.getTotalExistencia(), 
                infoExtra.getCostoPromedio(), 
                infoExtra.getCapacidadAlmacenamiento(),
                infoExtra.getNivelMinimoReorden()
            );
            return new ResponseEntity<>("Información extra de bodega actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la información extra de bodega", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Eliminar una información extra de bodega por su ID
    @PostMapping("/{id}/delete")
    public ResponseEntity<String> eliminarInfoExtraBodega(@PathVariable("id") Long id) {
        try {
            infoExtraBodegaRepository.eliminarInfoExtraBodega(id);
            return new ResponseEntity<>("Información extra de bodega eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la información extra de bodega", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
