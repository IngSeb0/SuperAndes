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

    // Crear una nueva bodega
    @PostMapping("/new/save")
    public ResponseEntity<?> crearBodega(@RequestBody Bodega bodega) {
        try {
            Long idBodega = bodega.getId(); // Tomar el IDBODEGA
            Long sucursalId = bodega.getSucursal().getIdSucursal();

            bodegaRepository.insertarBodega(idBodega, sucursalId, bodega.getNombreBodega(), bodega.getTamanioBodega());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la bodega");
        }
    }

    // Eliminar una bodega por IDBODEGA
    @DeleteMapping("/delete")
    public ResponseEntity<?> eliminarBodega(@RequestParam Long idBodega) {
        try {
            bodegaRepository.eliminarBodega(idBodega);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la bodega");
        }
    }
}
