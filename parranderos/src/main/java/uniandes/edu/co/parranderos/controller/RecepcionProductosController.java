package uniandes.edu.co.parranderos.controller;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.transaction.Transactional;
import uniandes.edu.co.parranderos.modelo.OrdenCompra;
import uniandes.edu.co.parranderos.modelo.Producto;
import uniandes.edu.co.parranderos.repositorio.OrdenCompraRepository;
import uniandes.edu.co.parranderos.repositorio.ProductoRepository;
import uniandes.edu.co.parranderos.repositorio.RecepcionProductoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
@RestController
@RequestMapping("/recepciones")
public class RecepcionProductosController {

    @Autowired
    private RecepcionProductoRepository recepcionProductoRepository;

    @Autowired
    private OrdenCompraRepository ordenCompraRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @PostMapping("/registrar")
    @Transactional
    public ResponseEntity<String> registrarRecepcionProducto(@RequestBody Map<String, Object> request) {
        try {
            // Extraer los parámetros del cuerpo de la solicitud.
            Long idOrden = Long.valueOf(request.get("idOrden").toString());
            Long idBodega = Long.valueOf(request.get("idBodega").toString());

            // Obtener la orden de compra con detalles
            Map<String, Object> ordenCompraDetalles = ordenCompraRepository
                    .obtenerOrdenCompraConDetalles(idOrden);

            if (ordenCompraDetalles == null || ordenCompraDetalles.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Orden de compra no encontrada.");
            }

            String estado = (String) ordenCompraDetalles.get("estado");
            if ("ENTREGADA".equals(estado)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("La Orden de Compra ya ha sido entregada.");
            }

            // Registrar la recepción del producto
            String fechaRecepcion = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            recepcionProductoRepository.registrarRecepcionProducto(fechaRecepcion, idBodega, idOrden);

            // Actualizar inventario y costos
            String codigoBarras = String.valueOf(ordenCompraDetalles.get("CODIGOBARRAS").toString());
            Integer cantidad = Integer.valueOf(request.get("cantidad").toString());
            Float precio = Float.valueOf(ordenCompraDetalles.get("precioUnitarioVenta").toString());

            productoRepository.actualizarInventarioYCostos(idBodega, codigoBarras, cantidad, precio);

            ordenCompraRepository.marcarComoEntregada(idOrden);

            return ResponseEntity.ok("Recepción registrada y orden marcada como ENTREGADA.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al registrar la recepción: " + e.getMessage());
        }
    }
     @GetMapping
    public Collection<OrdenCompra> obtenerTodasLasOrdenes() {
        return ordenCompraRepository.obtenerTodasLasOrdenes();
    }

}
