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
import java.util.Collections;
import java.util.List;
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

    // Método para obtener el índice de ocupación de las bodegas de una sucursal
    @GetMapping("/{idSucursal}/ocupacion")
    public ResponseEntity<Collection<IndiceOcupacionBodegaInfo>> obtenerIndiceOcupacionBodegas(
        @PathVariable Long idSucursal,
        @RequestBody List<String> productos) {
    if (productos == null || productos.isEmpty()) {
        return ResponseEntity.badRequest().body(Collections.emptyList());
    }
    try {
        Collection<IndiceOcupacionBodegaInfo> ocupacionBodegas =
                bodegaRepository.obtenerIndiceOcupacionBodega(idSucursal, productos);
        return ResponseEntity.ok(ocupacionBodegas);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
   
    @GetMapping("/{idBodega}/inventario")
    public ResponseEntity<Collection<Map<String, Object>>> obtenerInventarioBodega(
            @PathVariable("idBodega") Long idBodega) {
        try {


            Collection<Map<String, Object>> inventario = bodegaRepository.obtenerInventarioProductosBodega(idBodega);



            if (inventario.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(inventario, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
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

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarBodega(@PathVariable("id") Long id) {
        try {
            bodegaRepository.eliminarBodega(id);
            return ResponseEntity.ok("Bodega eliminada exitosamente");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la bodega");
        }
    }
    
}