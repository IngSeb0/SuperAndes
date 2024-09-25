package uniandes.edu.co.parranderos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.parranderos.modelo.RecepcionProducto;
import uniandes.edu.co.parranderos.repositorio.RecepcionProductoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

@RestController
@RequestMapping("/recepciones")
public class RecepcionProductosController {

    @Autowired
    private RecepcionProductoRepository recepcionProductoRepository;

    // Obtener todas las recepciones de productos
    @GetMapping
    public Collection<RecepcionProducto> obtenerRecepciones() {
        return recepcionProductoRepository.obtenerTodasLasRecepciones();
    }

    // Obtener una recepción por su ID
    @GetMapping("/{id}")
    public ResponseEntity<RecepcionProducto> obtenerRecepcionPorId(@PathVariable("id") Long id) {
        RecepcionProducto recepcionProducto = recepcionProductoRepository.obtenerRecepcionPorId(id);
        if (recepcionProducto != null) {
            return new ResponseEntity<>(recepcionProducto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Insertar una nueva recepción de producto
    @PostMapping("/new/save")
    public ResponseEntity<String> insertarRecepcion(@RequestBody RecepcionProducto recepcionProducto) {
        try {
            recepcionProductoRepository.insertarRecepcionProducto( recepcionProducto.getFechaRecepcion());
            return new ResponseEntity<>("Recepción de producto creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la recepción", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar una recepción de producto por su ID
    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> actualizarRecepcion(@PathVariable("id") Long id, @RequestBody RecepcionProducto recepcionProducto) {
        try {
            recepcionProductoRepository.actualizarRecepcionProducto(id, recepcionProducto.getFechaRecepcion());
            return new ResponseEntity<>("Recepción de producto actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la recepción", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Eliminar una recepción de producto por su ID
    @PostMapping("/{id}/delete")
    public ResponseEntity<String> eliminarRecepcion(@PathVariable("id") Long id) {
        try {
            recepcionProductoRepository.eliminarRecepcionProducto(id);
            return new ResponseEntity<>("Recepción de producto eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la recepción", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
