package uniandes.edu.co.parranderos.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.parranderos.modelo.Producto;
import uniandes.edu.co.parranderos.repositorio.ProductoRepository;
import uniandes.edu.co.parranderos.repositorio.ProductoRepository.ProductoCaracteristicasInfo;
import uniandes.edu.co.parranderos.repositorio.ProductoRepository.ProductoOrdenCompraInfo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Date;
import java.util.Collection;

@RestController
@RequestMapping("/producto")
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
    @GetMapping("/ordenes-compra")
    public ResponseEntity<Collection<ProductoOrdenCompraInfo>> obtenerProductosParaOrdenDeCompra() {
        try {
            Collection<ProductoOrdenCompraInfo> productosOrdenCompra = productoRepository.obtenerProductosParaOrdenDeCompra();
            return new ResponseEntity<>(productosOrdenCompra, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
      @GetMapping("/buscar")
    public ResponseEntity<Collection<ProductoCaracteristicasInfo>> obtenerProductosPorCaracteristicas(
            @RequestParam(required = false) Float precioMin,
            @RequestParam(required = false) Float precioMax,
            @RequestParam(required = false) Date fechaExpiracion,
            @RequestParam(required = false) Long idSucursal,
            @RequestParam(required = false) Long idCategoria) {

        try {
            Collection<ProductoCaracteristicasInfo> productos = productoRepository.obtenerProductosPorCaracteristicas(
                    precioMin, precioMax, fechaExpiracion, idSucursal, idCategoria);

            return new ResponseEntity<>(productos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
