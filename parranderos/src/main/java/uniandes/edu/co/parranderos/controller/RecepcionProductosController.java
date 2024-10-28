package uniandes.edu.co.parranderos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.parranderos.repositorio.RecepcionProductoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

@RestController
@RequestMapping("/recepciones")
public class RecepcionProductosController {

    @Autowired
    private RecepcionProductoRepository recepcionProductoRepository;

    @PostMapping("/registrar")
    public ResponseEntity<String> registrarRecepcionProducto(
            @RequestParam Long idBodega,
            @RequestParam Long idOrden) {
        try {
            // Obtener la fecha actual para la recepción.
            String fechaRecepcion = LocalDate.now().toString();

            // Llamar al método del repositorio para registrar la recepción del producto.
            recepcionProductoRepository.registrarRecepcionProducto(fechaRecepcion, idBodega, idOrden);

            return ResponseEntity.ok("Recepción de producto registrada exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al registrar la recepción del producto: " + e.getMessage());
        }
    }
}
