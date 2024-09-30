package uniandes.edu.co.parranderos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.parranderos.modelo.OrdenCompra;
import uniandes.edu.co.parranderos.modelo.Producto;
import uniandes.edu.co.parranderos.modelo.Proveedor;
import uniandes.edu.co.parranderos.modelo.Sucursal;
import uniandes.edu.co.parranderos.repositorio.OrdenCompraRepository;
import uniandes.edu.co.parranderos.repositorio.ProductoRepository;
import uniandes.edu.co.parranderos.repositorio.ProveedorRepository;
import uniandes.edu.co.parranderos.repositorio.SucursalRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.List;
import java.util.Optional;
import java.util.Date;
@RestController
@RequestMapping("/ordenes")
public class OrdenCompraController {

    @Autowired
    private OrdenCompraRepository ordenCompraRepository;
@Autowired
    private SucursalRepository sucursalRepository;
    
    @Autowired
    private ProveedorRepository proveedorRepository;
    
    @Autowired
    private ProductoRepository productoRepository;

    
    // Crear una nueva orden de compra
    @PostMapping("/new/save")
    public ResponseEntity<String> crearOrdenCompra(
            @RequestParam Long idSucursal,
            @RequestParam Long idProveedor,
            @RequestParam String fechaEntrega,
            @RequestBody List<Producto> productos) {
        
        try {
            Optional<Sucursal> sucursalOpt = sucursalRepository.findById(idSucursal);
            Optional<Proveedor> proveedorOpt = proveedorRepository.findById(idProveedor);

            if (sucursalOpt.isPresent() && proveedorOpt.isPresent()) {
                Sucursal sucursal = sucursalOpt.get();
                Proveedor proveedor = proveedorOpt.get();

                // Crear nueva orden
                OrdenCompra nuevaOrden = new OrdenCompra();
                nuevaOrden.setFechaCreacion(new Date()); 
                nuevaOrden.setEstado("vigente");
                nuevaOrden.setSucursalId(sucursal);
                nuevaOrden.setFechaEntrega(java.sql.Date.valueOf(fechaEntrega)); 

                // Guardar la nueva orden
                ordenCompraRepository.save(nuevaOrden);

                for (Producto producto : productos) {
                    Optional<Producto> productoOpt = productoRepository.obtenerProductoPorCodigoBarras(producto.getCodigoBarras());
                    if (productoOpt.isPresent()) {
                        Producto productoEnOrden = productoOpt.get();
                        productoRepository.save(productoEnOrden);
                    }
                }

                return new ResponseEntity<>("Orden de compra creada exitosamente", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Sucursal o proveedor no encontrado", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la orden de compra: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar el estado de una orden de compra por su ID
    @PutMapping("/{id}/actualizar-estado")
    public ResponseEntity<String> actualizarEstadoOrden(
            @PathVariable("id") Long id,
            @RequestParam String nuevoEstado) {
        
        try {
            Optional<OrdenCompra> ordenOpt = ordenCompraRepository.findById(id);
            if (ordenOpt.isPresent()) {
                OrdenCompra orden = ordenOpt.get();
                
                // Verificar que no esté en estado "entregada"
                if ("entregada".equals(orden.getEstado())) {
                    return new ResponseEntity<>("No se puede anular una orden ya entregada.", HttpStatus.BAD_REQUEST);
                }

                // Cambiar el estado de la orden
                orden.setEstado(nuevoEstado);
                ordenCompraRepository.save(orden);

                return new ResponseEntity<>("Estado de la orden actualizado exitosamente", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Orden no encontrada", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el estado de la orden: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener todas las órdenes de compra
    @GetMapping
    public List<OrdenCompra> obtenerTodasLasOrdenes() {
        return ordenCompraRepository.findAll();  
    }

    // Obtener una orden de compra por su ID
    @GetMapping("/{id}")
    public ResponseEntity<OrdenCompra> obtenerOrdenPorId(@PathVariable("id") Long id) {
        Optional<OrdenCompra> ordenCompra = ordenCompraRepository.findById(id);
        if (ordenCompra.isPresent()) {
            return new ResponseEntity<>(ordenCompra.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}


   

