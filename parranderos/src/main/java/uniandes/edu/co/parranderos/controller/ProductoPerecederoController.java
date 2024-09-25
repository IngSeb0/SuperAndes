package uniandes.edu.co.parranderos.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.parranderos.modelo.ProductoPerecedero;
import uniandes.edu.co.parranderos.repositorio.ProductoPerecederoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

@RestController
@RequestMapping("/productos_perecederos")
public class ProductoPerecederoController {

    @Autowired
    private ProductoPerecederoRepository productoPerecederoRepository;

    // Obtener todos los productos perecederos
    @GetMapping
    public Collection<ProductoPerecedero> obtenerProductosPerecederos() {
        return productoPerecederoRepository.obtenerTodosLosProductosPerecederos();
    }

    // Obtener un producto perecedero por su código de barras
    @GetMapping("/{codigoBarras}")
    public ResponseEntity<ProductoPerecedero> obtenerProductoPerecederoPorCodigoBarras(@PathVariable("codigoBarras") Long codigoBarras) {
        ProductoPerecedero producto = productoPerecederoRepository.obtenerProductoPerecederoPorCodigoDeBarras(codigoBarras);
        if (producto != null) {
            return new ResponseEntity<>(producto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Insertar un nuevo producto perecedero
    @PostMapping("/new/save")
    public ResponseEntity<String> insertarProductoPerecedero(@RequestBody ProductoPerecedero productoPerecedero) {
        try {
            productoPerecederoRepository.insertarProductoPerecedero(
                    productoPerecedero.getCodigoBarras(), 
                    productoPerecedero.getNombre(), 
                    productoPerecedero.getPrecioUnitarioVenta(), 
                    productoPerecedero.getPresentacion(), 
                    productoPerecedero.getCantidadPresentacion(), 
                    productoPerecedero.getUnidadMedida(), 
                    productoPerecedero.getFechaExpiracion(), 
                    productoPerecedero.getFechaVencimiento()
            );
            return new ResponseEntity<>("Producto perecedero creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el producto perecedero", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar un producto perecedero por su código de barras
    @PostMapping("/{codigoBarras}/edit/save")
    public ResponseEntity<String> actualizarProductoPerecedero(@PathVariable("codigoBarras") String codigoBarras, @RequestBody ProductoPerecedero productoPerecedero) {
        try {
            productoPerecederoRepository.actualizarProductoPerecedero(
                    codigoBarras, 
                    productoPerecedero.getCodigoBarras(), 
                    productoPerecedero.getPrecioUnitarioVenta(), 
                    productoPerecedero.getPresentacion(), 
                    productoPerecedero.getCantidadPresentacion(), 
                    productoPerecedero.getUnidadMedida(), 
                    productoPerecedero.getFechaExpiracion(), 
                    productoPerecedero.getFechaVencimiento()
            );
            return new ResponseEntity<>("Producto perecedero actualizado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el producto perecedero", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Eliminar un producto perecedero por su código de barras
    @PostMapping("/{codigoBarras}/delete")
    public ResponseEntity<String> eliminarProductoPerecedero(@PathVariable("codigoBarras") String codigoBarras) {
        try {
            productoPerecederoRepository.eliminarProductoPerecedero(codigoBarras);
            return new ResponseEntity<>("Producto perecedero eliminado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el producto perecedero", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
