package uniandes.edu.co.parranderos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.parranderos.modelo.InfoExtraBodega;
import uniandes.edu.co.parranderos.repositorio.InfoExtraBodegaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

@RestController
@RequestMapping("/info_extra_bodega")
public class InfoExtraBodegaController {

    @Autowired
    private InfoExtraBodegaRepository infoExtraBodegaRepository;

    // Obtener toda la información extra de las bodegas
    @GetMapping
    public ResponseEntity<Collection<InfoExtraBodega>> obtenerInfoExtraBodega() {
        try {
            Collection<InfoExtraBodega> infoExtraBodegas = infoExtraBodegaRepository.obtenerTodaLaInfoExtraBodega();
            return new ResponseEntity<>(infoExtraBodegas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener información extra de bodega por su ID
    @GetMapping("/{id}")
    public ResponseEntity<InfoExtraBodega> obtenerInfoExtraBodegaPorId(@PathVariable("id") Long id) {
        try {
            InfoExtraBodega infoExtra = infoExtraBodegaRepository.obtenerInfoExtraBodegaPorId(id);
            if (infoExtra != null) {
                return new ResponseEntity<>(infoExtra, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Insertar nueva información extra de bodega
    @PostMapping("/new")
    public ResponseEntity<String> insertarInfoExtraBodega(@RequestBody InfoExtraBodega infoExtraBodega) {
        try {
            infoExtraBodegaRepository.insertarInfoExtraBodega(
                    infoExtraBodega.getTotalExistencias(),
                    infoExtraBodega.getCostoPromedio(),
                    infoExtraBodega.getCapacidadAlmacenamiento(),
                    infoExtraBodega.getNivelMinimo(),
                    infoExtraBodega.getPk().getIdInfoExtraBodega()
            );
            return new ResponseEntity<>("Información extra de bodega creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la información extra de bodega", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar información extra de bodega por su ID
    @PutMapping("/{id}/edit")
    public ResponseEntity<String> actualizarInfoExtraBodega(@PathVariable("id") Long id, @RequestBody InfoExtraBodega infoExtraBodega) {
        try {
            infoExtraBodegaRepository.actualizarInfoExtraBodega(
                    id,
                    infoExtraBodega.getTotalExistencias(),
                    infoExtraBodega.getCostoPromedio(),
                    infoExtraBodega.getCapacidadAlmacenamiento(),
                    infoExtraBodega.getNivelMinimo(),
                    infoExtraBodega.getPk().getIdInfoExtraBodega()
            );
            return new ResponseEntity<>("Información extra de bodega actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la información extra de bodega", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Eliminar información extra de bodega por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarInfoExtraBodega(@PathVariable("id") Long id) {
        try {
            infoExtraBodegaRepository.eliminarInfoExtraBodega(id);
            return new ResponseEntity<>("Información extra de bodega eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la información extra de bodega", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
