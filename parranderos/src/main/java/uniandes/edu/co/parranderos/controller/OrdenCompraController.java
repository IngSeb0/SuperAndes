package uniandes.edu.co.parranderos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.parranderos.modelo.OrdenCompra;
import uniandes.edu.co.parranderos.repositorio.OrdenCompraRepository;

import java.util.List;

@RestController
@RequestMapping("/ordenes-compra")
public class OrdenCompraController {

    @Autowired
    private OrdenCompraRepository ordenCompraRepository;

    // Obtener todas las órdenes de compra
    @GetMapping
    public List<OrdenCompra> obtenerTodasLasOrdenes() {
        return ordenCompraRepository.obtenerTodasLasOrdenes();
    }

    // Obtener una orden de compra por su ID
    @GetMapping("/{id}")
    public ResponseEntity<OrdenCompra> obtenerOrdenPorId(@PathVariable Long id) {
        OrdenCompra orden = ordenCompraRepository.obtenerOrdenPorId(id);
        if (orden != null) {
            return ResponseEntity.ok(orden);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Obtener órdenes de compra por NIT del proveedor
    @GetMapping("/proveedor/{nit}")
    public List<OrdenCompra> obtenerOrdenesPorProveedor(@PathVariable String nit) {
        return ordenCompraRepository.obtenerOrdenesPorProveedor(nit);
    }

    // Insertar una nueva orden de compra
    @PostMapping
    public ResponseEntity<String> insertarOrden(@RequestBody OrdenCompra ordenCompra) {
        try {
            ordenCompraRepository.insertarOrden(
                    ordenCompra.getFechaCreacion().toString(),
                    ordenCompra.getEstado(),
                    ordenCompra.getFechaEntrega().toString(),
                    ordenCompra.getSucursalId().getIdSucursal(),
                    ordenCompra.getProveedor().getNit(),
                    ordenCompra.getProducto().getCodigoBarras()
            );
            return ResponseEntity.ok("Orden de compra creada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al crear la orden de compra");
        }
    }

    // Actualizar una orden de compra por su ID
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarOrden(@PathVariable Long id, @RequestBody OrdenCompra ordenCompra) {
        try {
            ordenCompraRepository.actualizarOrden(
                    id,
                    ordenCompra.getFechaCreacion().toString(),
                    ordenCompra.getEstado(),
                    ordenCompra.getFechaEntrega().toString(),
                    ordenCompra.getSucursalId().getIdSucursal(),
                    ordenCompra.getProveedor().getNit(),
                    ordenCompra.getProducto().getCodigoBarras()
            );
            return ResponseEntity.ok("Orden de compra actualizada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al actualizar la orden de compra");
        }
    }

    // Eliminar una orden de compra por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOrden(@PathVariable Long id) {
        try {
            ordenCompraRepository.eliminarOrden(id);
            return ResponseEntity.ok("Orden de compra eliminada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al eliminar la orden de compra");
        }
    }
}
