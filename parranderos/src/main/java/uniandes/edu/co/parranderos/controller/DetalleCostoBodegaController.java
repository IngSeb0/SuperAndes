package uniandes.edu.co.parranderos.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.parranderos.modelo.DetalleCostoBodega;
import uniandes.edu.co.parranderos.modelo.DetalleCostoBodegaPk;
import uniandes.edu.co.parranderos.repositorio.DetalleCostoBodegaRepository;

@RestController
public class DetalleCostoBodegaController {

    @Autowired
    private DetalleCostoBodegaRepository detalleCostoBodegaRepository;

    // Obtener todos los detalles de costo de bodega
    @GetMapping("/detalles_costo_bodega")
    public ResponseEntity<Collection<DetalleCostoBodega>> obtenerDetallesCostoBodega() {
        try {
            Collection<DetalleCostoBodega> detalles = detalleCostoBodegaRepository.obtenerTodosLosDetallesCostoBodega();
            return ResponseEntity.ok(detalles);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Crear un nuevo detalle de costo de bodega
    @PostMapping("/detalles_costo_bodega/new/save")
    public ResponseEntity<?> crearDetalleCostoBodega(@RequestBody DetalleCostoBodega detalleCostoBodega) {
        try {
            DetalleCostoBodegaPk pk = detalleCostoBodega.getPk();

            if (pk == null || pk.getInfoExtraBodega() == null || pk.getIdDetalleCosto() == null) {
                throw new RuntimeException("Faltan datos en la clave primaria");
            }

            Long idBodega = pk.getInfoExtraBodega().getPk().getBodega().getId();
            String idProducto = pk.getInfoExtraBodega().getPk().getProducto().getCodigoBarras();
            Long idDetalleCosto = pk.getIdDetalleCosto();

            detalleCostoBodegaRepository.insertarDetalleCostoBodega(
                detalleCostoBodega.getCosto(),
                detalleCostoBodega.getCantidadExistencia(),
                idBodega,
                idProducto,
                idDetalleCosto
            );

            DetalleCostoBodega nuevoDetalle = detalleCostoBodegaRepository.obtenerDetalleCostoBodegaPorId(idBodega, idProducto, idDetalleCosto);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoDetalle);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el detalle de costo de bodega", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener un detalle de costo por su clave primaria compuesta
    @GetMapping("/detalles_costo_bodega/{bodegaId}/{productoId}/{idDetalle}")
    public ResponseEntity<DetalleCostoBodega> obtenerDetalleCostoBodegaPorId(
            @PathVariable("bodegaId") Long bodegaId,
            @PathVariable("productoId") String productoId,
            @PathVariable("idDetalle") Long idDetalle) {
        try {
            DetalleCostoBodega detalle = detalleCostoBodegaRepository.obtenerDetalleCostoBodegaPorId(bodegaId, productoId, idDetalle);
            if (detalle != null) {
                return new ResponseEntity<>(detalle, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar un detalle de costo de bodega
    @PostMapping("/detalles_costo_bodega/{bodegaId}/{productoId}/{idDetalle}/edit/save")
    public ResponseEntity<String> actualizarDetalleCostoBodega(
            @PathVariable("bodegaId") Long bodegaId,
            @PathVariable("productoId") String productoId,
            @PathVariable("idDetalle") Long idDetalle,
            @RequestBody DetalleCostoBodega detalleCostoBodega) {
        try {
            detalleCostoBodegaRepository.actualizarDetalleCostoBodega(
                idDetalle,
                detalleCostoBodega.getCosto(),
                detalleCostoBodega.getCantidadExistencia(),
                bodegaId,
                productoId
            );
            return new ResponseEntity<>("Detalle de costo de bodega actualizado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el detalle de costo de bodega", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Eliminar un detalle de costo de bodega
    @PostMapping("/detalles_costo_bodega/{bodegaId}/{productoId}/{idDetalle}/delete")
    public ResponseEntity<String> eliminarDetalleCostoBodega(
            @PathVariable("bodegaId") Long bodegaId,
            @PathVariable("productoId") String productoId,
            @PathVariable("idDetalle") Long idDetalle) {
        try {
            detalleCostoBodegaRepository.eliminarDetalleCostoBodega(idDetalle, bodegaId, productoId);
            return new ResponseEntity<>("Detalle de costo de bodega eliminado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el detalle de costo de bodega", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
