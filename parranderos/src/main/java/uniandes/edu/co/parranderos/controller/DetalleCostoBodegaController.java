package uniandes.edu.co.parranderos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.parranderos.modelo.DetalleCostoBodega;
import uniandes.edu.co.parranderos.repositorio.DetalleCostoBodegaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

@RestController
@RequestMapping("/detalles_costo_bodega")
public class DetalleCostoBodegaController {

    @Autowired
    private DetalleCostoBodegaRepository detalleCostoBodegaRepository;

    // Obtener todos los detalles de costo de la bodega
    @GetMapping
    public Collection<DetalleCostoBodega> obtenerDetallesCostoBodega() {
        return detalleCostoBodegaRepository.obtenerTodosLosDetallesCostoBodega();
    }

    // Obtener un detalle de costo por ID
    @GetMapping("/{id}")
    public ResponseEntity<DetalleCostoBodega> obtenerDetalleCostoBodegaPorId(@PathVariable("id") Long id) {
        DetalleCostoBodega detalle = detalleCostoBodegaRepository.obtenerDetalleCostoBodegaPorId(id);
        if (detalle != null) {
            return new ResponseEntity<>(detalle, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Insertar un nuevo detalle de costo de bodega
    @PostMapping("/new/save")
    public ResponseEntity<String> insertarDetalleCostoBodega(@RequestBody DetalleCostoBodega detalleCostoBodega) {
        try {
            detalleCostoBodegaRepository.insertarDetalleCostoBodega(
                detalleCostoBodega.getCostoUnitarioBodega(),
                detalleCostoBodega.getCantidadExistencia(),
                detalleCostoBodega.getInfoExtraBodegaId()
            );
            return new ResponseEntity<>("Detalle de costo de bodega creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el detalle de costo de bodega", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar un detalle de costo de bodega por su ID
    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> actualizarDetalleCostoBodega(@PathVariable("id") Long id, @RequestBody DetalleCostoBodega detalleCostoBodega) {
        try {
            detalleCostoBodegaRepository.actualizarDetalleCostoBodega(
                id,
                detalleCostoBodega.getCostoUnitarioBodega(),
                detalleCostoBodega.getCantidadExistencia(),
                detalleCostoBodega.getInfoExtraBodegaId()
            );
            return new ResponseEntity<>("Detalle de costo de bodega actualizado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el detalle de costo de bodega", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Eliminar un detalle de costo de bodega por su ID
    @PostMapping("/{id}/delete")
    public ResponseEntity<String> eliminarDetalleCostoBodega(@PathVariable("id") Long id) {
        try {
            detalleCostoBodegaRepository.eliminarDetalleCostoBodega(id);
            return new ResponseEntity<>("Detalle de costo de bodega eliminado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el detalle de costo de bodega", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
