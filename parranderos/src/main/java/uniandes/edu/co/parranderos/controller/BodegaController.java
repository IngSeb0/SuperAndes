package uniandes.edu.co.parranderos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.parranderos.modelo.Bodega;

import uniandes.edu.co.parranderos.repositorio.BodegaRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/bodegas")
public class BodegaController {

    @Autowired
    private BodegaRepository bodegaRepository;

    @GetMapping
    public ResponseEntity<Collection<Bodega>> obtenerBodegas() {
        try {
            Collection<Bodega> bodegas = bodegaRepository.darBodegas();
            return ResponseEntity.ok(bodegas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
 @GetMapping("/consulta")
    public ResponseEntity<?> obtenerIndiceOcupacionBodegas(@RequestParam Long idSucursal, 
                                                           @RequestParam(required = false) Collection<String> productos) {
        try {
            // Obtener el índice de ocupación para los productos y la sucursal
            Collection<Object[]> ocupacionBodegas = bodegaRepository.obtenerIndiceOcupacionBodega(idSucursal, productos);

            // Crear un mapa para almacenar los resultados y otros datos
            Map<String, Object> response = new HashMap<>();
            response.put("OcupacionBodegas", ocupacionBodegas);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping("/new/save")
    public ResponseEntity<?> crearBodega(@RequestBody Bodega bodega) {
        try {
            Long sucursalId = bodega.getSucursal().getIdSucursal();

            bodegaRepository.insertarBodega(sucursalId, bodega.getNombreBodega(), bodega.getTamanioBodega());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la bodega");
        }
    }

    // Método para eliminar una bodega
    @DeleteMapping("/delete")
    public ResponseEntity<?> eliminarBodega(@RequestParam Long sucursalId) {
        try {
            bodegaRepository.eliminarBodega(sucursalId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la bodega");
        }
    }
}
