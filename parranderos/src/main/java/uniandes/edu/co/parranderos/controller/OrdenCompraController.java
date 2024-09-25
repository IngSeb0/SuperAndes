package uniandes.edu.co.parranderos.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.parranderos.modelo.OrdenCompra;
import uniandes.edu.co.parranderos.repositorio.OrdenCompraRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

@RestController
@RequestMapping("/ordenes_compra")
public class OrdenCompraController {

    @Autowired
    private OrdenCompraRepository ordenCompraRepository;

    // Obtener todas las órdenes de compra
    @GetMapping
    public Collection<OrdenCompra> obtenerOrdenesCompra() {
        return ordenCompraRepository.obtenerTodasLasOrdenesCompra();
    }

    // Obtener una orden de compra por su ID
    @GetMapping("/{id}")
    public ResponseEntity<OrdenCompra> obtenerOrdenCompraPorId(@PathVariable("id") Long id) {
        OrdenCompra ordenCompra = ordenCompraRepository.obtenerOrdenCompraPorId(id);
        if (ordenCompra != null) {
            return new ResponseEntity<>(ordenCompra, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Insertar una nueva orden de compra
    @PostMapping("/new/save")
    public ResponseEntity<String> insertarOrdenCompra(@RequestBody OrdenCompra ordenCompra) {
        try {
            ordenCompraRepository.insertarOrdenCompra(
                    ordenCompra.getId(), 
                    ordenCompra.getFechaCreacion(), 
                    ordenCompra.getFechaEntrega()
            );
            return new ResponseEntity<>("Orden de compra creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la orden de compra", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar una orden de compra por su ID
    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> actualizarOrdenCompra(@PathVariable("id") Long id, @RequestBody OrdenCompra ordenCompra) {
        try {
            ordenCompraRepository.actualizarOrdenCompra(id, ordenCompra.getFechaCreacion(), ordenCompra.getFechaEntrega());
            return new ResponseEntity<>("Orden de compra actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la orden de compra", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Eliminar una orden de compra por su ID
    @PostMapping("/{id}/delete")
    public ResponseEntity<String> eliminarOrdenCompra(@PathVariable("id") Long id) {
        try {
            ordenCompraRepository.eliminarOrdenCompra(id);
            return new ResponseEntity<>("Orden de compra eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la orden de compra", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
