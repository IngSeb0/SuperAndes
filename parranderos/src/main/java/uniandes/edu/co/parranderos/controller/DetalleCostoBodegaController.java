package uniandes.edu.co.parranderos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.parranderos.modelo.DetalleCostoBodega;
import uniandes.edu.co.parranderos.repositorio.DetalleCostoBodegaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

@RestController
public class DetalleCostoBodegaController {

    @Autowired
    private DetalleCostoBodegaRepository detalleCostoBodegaRepository;

    // Obtener todos los detalles de costo de bodega
    @GetMapping("/detalles_costo_bodega")
    public Collection<DetalleCostoBodega> obtenerDetallesCostoBodega() {
        return detalleCostoBodegaRepository.findAll();
    }

    // Insertar un nuevo detalle de costo de bodega
    @PostMapping("/detalles_costo_bodega/new/save")
    public ResponseEntity<String> insertarDetalleCostoBodega(@RequestBody DetalleCostoBodega detalle) {
        try {
            detalleCostoBodegaRepository.insertarDetalle(detalle.getCostoUnitarioBodega(), detalle.getCantidadExistencia());
            return new ResponseEntity<>("Detalle de costo de bodega creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el detalle", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar un detalle de costo de bodega
    @PostMapping("/detalles_costo_bodega/{id}/edit/save")
    public ResponseEntity<String> actualizarDetalleCostoBodega(@PathVariable("id") Long id, @RequestBody DetalleCostoBodega detalle) {
        try {
            detalleCostoBodegaRepository.actualizarDetalle(id, detalle.getCostoUnitarioBodega(),detalle.getCantidadExistencia() );
            detalleCostoBodegaRepository.save(detalle);
            return new ResponseEntity<>("Detalle de costo de bodega actualizado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el detalle", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Eliminar un detalle de costo de bodega
    @PostMapping("/detalles_costo_bodega/{id}/delete")
    public ResponseEntity<String> eliminarDetalleCostoBodega(@PathVariable("id") Long id) {
        try {
            detalleCostoBodegaRepository.deleteById(id);
            return new ResponseEntity<>("Detalle de costo de bodega eliminado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el detalle", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
