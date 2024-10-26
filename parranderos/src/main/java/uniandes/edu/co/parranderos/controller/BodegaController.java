package uniandes.edu.co.parranderos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.parranderos.modelo.Bodega;
import uniandes.edu.co.parranderos.repositorio.BodegaRepository;
import uniandes.edu.co.parranderos.repositorio.BodegaRepository.InventarioBodegaInfo;
import uniandes.edu.co.parranderos.repositorio.BodegaRepository.IndiceOcupacionBodegaInfo;

import java.util.Collection;

@RestController
@RequestMapping("/bodegas")
public class BodegaController {

    @Autowired
    private BodegaRepository bodegaRepository;

    // Obtener todas las bodegas
    @GetMapping
    public ResponseEntity<Collection<Bodega>> obtenerBodegas() {
        try {
            Collection<Bodega> bodegas = bodegaRepository.darBodegas();
            return ResponseEntity.ok(bodegas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Método para obtener el índice de ocupación de las bodegas de una sucursal
    @GetMapping("/{idSucursal}/ocupacion")
    public ResponseEntity<Collection<IndiceOcupacionBodegaInfo>> obtenerIndiceOcupacionBodegas(@PathVariable("idSucursal") Long idSucursal,
                                                                                               @RequestParam Collection<String> productos) {
        try {
            // Obtener el índice de ocupación para los productos y la sucursal
            Collection<IndiceOcupacionBodegaInfo> ocupacionBodegas = bodegaRepository.obtenerIndiceOcupacionBodega(idSucursal, productos);
            return new ResponseEntity<>(ocupacionBodegas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener el inventario de productos de una bodega
    @GetMapping("/{idBodega}/inventario")
    public ResponseEntity<Collection<InventarioBodegaInfo>> obtenerInventarioBodega(@PathVariable("idBodega") Long idBodega) {
        try {
            Collection<InventarioBodegaInfo> inventario = bodegaRepository.obtenerInventarioProductosBodega(idBodega);
            return new ResponseEntity<>(inventario, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/new/save")
    public ResponseEntity<String> crearBodega(@RequestBody Bodega bodega) {
        try {
            // Verificar si la sucursal asociada es válida
            if (bodega.getSucursal() == null || bodega.getSucursal().getIdSucursal() == null) {
                return new ResponseEntity<>("ID de sucursal inválido", HttpStatus.BAD_REQUEST);
            }

            // Obtener los datos de la bodega y la sucursal
            Long idBodega = bodega.getId();
            Long idSucursal = bodega.getSucursal().getIdSucursal();
            String nombreBodega = bodega.getNombreBodega();
            Float tamanioBodega = bodega.getTamanioBodega();

            // Llamar al repositorio para insertar la bodega
            bodegaRepository.insertarBodega(idBodega, idSucursal, nombreBodega, tamanioBodega);

            return new ResponseEntity<>("Bodega creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error al crear la bodega", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{id}/delete")
    public ResponseEntity<?> eliminarBodega(@PathVariable Long id) {
        try {
            bodegaRepository.eliminarBodega(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la bodega");
        }
    }
    
}