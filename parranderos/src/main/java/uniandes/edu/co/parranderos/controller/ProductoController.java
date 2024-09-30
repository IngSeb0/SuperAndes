package uniandes.edu.co.parranderos.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.parranderos.modelo.Producto;
import uniandes.edu.co.parranderos.repositorio.ProductoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    // Obtener todos los productos
    @GetMapping
    public Collection<Producto> obtenerProductos() {
        return productoRepository.obtenerTodosLosProductos();
    }

    // Obtener un producto por su código de barras
    @GetMapping("/{codigoBarras}")
    public ResponseEntity<Producto> obtenerProductoPorCodigoBarras(@PathVariable("codigoBarras") String codigoBarras) {
        Producto producto = productoRepository.obtenerProductoPorCodigoBarras1(codigoBarras);
        if (producto != null) {
            return new ResponseEntity<>(producto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Insertar un nuevo producto
    @PostMapping("/new/save")
    public ResponseEntity<String> insertarProducto(@RequestBody Producto producto) {
        try {
            productoRepository.insertarProducto(
                    producto.getCodigoBarras(),
                    producto.getNombre(),
                    producto.getPrecioUnitarioVenta(),
                    producto.getPresentacion(),
                    producto.getCantidadPresentacion(),
                    producto.getUnidadMedida(),
                    producto.getFechaExpiracion()
            );
            return new ResponseEntity<>("Producto creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el producto", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar un producto por su código de barras
    @PostMapping("/{codigoBarras}/edit/save")
    public ResponseEntity<String> actualizarProducto(@PathVariable("codigoBarras") String codigoBarras, @RequestBody Producto producto) {
        try {
            productoRepository.actualizarProducto(
                    codigoBarras,
                    producto.getNombre(),
                    producto.getPrecioUnitarioVenta()
            );
            return new ResponseEntity<>("Producto actualizado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el producto", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Eliminar un producto por su código de barras
    @PostMapping("/{codigoBarras}/delete")
    public ResponseEntity<String> eliminarProducto(@PathVariable("codigoBarras") String codigoBarras) {
        try {
            productoRepository.eliminarProducto(codigoBarras);
            return new ResponseEntity<>("Producto eliminado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el producto", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/filtrar")
    public ResponseEntity<?> filtrarProductos(
            @RequestParam(required = false) Float precioMin,
            @RequestParam(required = false) Float precioMax,
            @RequestParam(required = false) String fechaVencimiento,
            @RequestParam(required = false) Long idSucursal,
            @RequestParam(required = false) Long idCategoria) {

        try {
            Collection<Producto> productosFiltrados = productoRepository.filtrarProductos(precioMin, precioMax, fechaVencimiento, idSucursal, idCategoria);
            return new ResponseEntity<>(productosFiltrados, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al filtrar productos: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
