package uniandes.edu.co.parranderos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.parranderos.modelo.OrdenCompra;
import uniandes.edu.co.parranderos.repositorio.OrdenCompraRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ordenes")
public class OrdenCompraController {

    @Autowired
    private OrdenCompraRepository ordenCompraRepository;

    // Obtener todas las órdenes de compra
    @GetMapping
    public List<OrdenCompra> obtenerTodasLasOrdenes() {
        return ordenCompraRepository.findAll();  // Cambiado a método estándar findAll()
    }

    // Obtener una orden de compra por su ID
    @GetMapping("/{id}")
    public ResponseEntity<OrdenCompra> obtenerOrdenPorId(@PathVariable("id") Long id) {
        Optional<OrdenCompra> ordenCompra = ordenCompraRepository.findById(id); // Cambiado a findById()
        if (ordenCompra.isPresent()) {
            return new ResponseEntity<>(ordenCompra.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Insertar una nueva orden de compra
    @PostMapping("/new/save")
    public ResponseEntity<String> insertarOrden(@RequestBody OrdenCompra ordenCompra) {
        try {
            ordenCompraRepository.save(ordenCompra); // Utilizando el método save() de JpaRepository
            return new ResponseEntity<>("Orden de compra creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la orden de compra", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar una orden de compra por su ID
    @PutMapping("/{id}/edit/save")
    public ResponseEntity<String> actualizarOrden(@PathVariable("id") Long id, @RequestBody OrdenCompra ordenCompra) {
        Optional<OrdenCompra> ordenExistente = ordenCompraRepository.findById(id); // Verificando existencia de la orden
        if (ordenExistente.isPresent()) {
            try {
                ordenCompra.setId(id);  // Asegurando que el ID no cambie durante la actualización
                ordenCompraRepository.save(ordenCompra);  // Guardar la actualización
                return new ResponseEntity<>("Orden de compra actualizada exitosamente", HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Error al actualizar la orden de compra", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Orden de compra no encontrada", HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar una orden de compra por su ID
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> eliminarOrden(@PathVariable("id") Long id) {
        try {
            if (ordenCompraRepository.existsById(id)) {  // Verificar si existe antes de eliminar
                ordenCompraRepository.deleteById(id);   // Utilizar el método deleteById()
                return new ResponseEntity<>("Orden de compra eliminada exitosamente", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Orden de compra no encontrada", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la orden de compra", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
