package uniandes.edu.co.parranderos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.parranderos.modelo.InfoExtraVenta;
import uniandes.edu.co.parranderos.modelo.InfoExtraVentaPk;
import uniandes.edu.co.parranderos.repositorio.InfoExtraVentaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

@RestController
@RequestMapping("/info_extra_venta")
public class InfoExtraVentaController {

    @Autowired
    private InfoExtraVentaRepository infoExtraVentaRepository;

    // Obtener toda la información extra de ventas
    @GetMapping
    public ResponseEntity<Collection<InfoExtraVenta>> obtenerInfoExtraVenta() {
        try {
            Collection<InfoExtraVenta> infoExtraVentas = infoExtraVentaRepository.obtenerTodaLaInfoExtraVenta();
            return ResponseEntity.ok(infoExtraVentas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Obtener información extra de venta por su clave primaria compuesta (ventaId y productoCodigoBarras)
    @GetMapping("/{ventaId}/{productoCodigoBarras}")
    public ResponseEntity<InfoExtraVenta> obtenerInfoExtraVentaPorId(
            @PathVariable("ventaId") Long ventaId,
            @PathVariable("productoCodigoBarras") String productoCodigoBarras) {
        try {
            InfoExtraVenta infoExtra = infoExtraVentaRepository.obtenerInfoExtraVentaPorId(ventaId, productoCodigoBarras);
            if (infoExtra != null) {
                return new ResponseEntity<>(infoExtra, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Insertar una nueva información extra de venta
    @PostMapping("/new/save")
    public ResponseEntity<String> insertarInfoExtraVenta(@RequestBody InfoExtraVenta infoExtraVenta) {
        try {InfoExtraVentaPk pk = infoExtraVenta.getPk();
            infoExtraVentaRepository.insertarInfoExtraVenta(
                infoExtraVenta.getCantidad(),
                infoExtraVenta.getPrecioUnitario(),
                pk.getVenta().getIdVenta(),
                pk.getProducto().getCodigoBarras()
            );
            return new ResponseEntity<>("Información extra de venta creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la información extra de venta", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar información extra de venta por su clave primaria compuesta
    @PostMapping("/{ventaId}/{productoCodigoBarras}/edit/save")
    public ResponseEntity<String> actualizarInfoExtraVenta(
            @PathVariable("ventaId") Long ventaId,
            @PathVariable("productoCodigoBarras") String productoCodigoBarras,
            @RequestBody InfoExtraVenta infoExtraVenta) {
        try {
            infoExtraVentaRepository.actualizarInfoExtraVenta(
                ventaId,
                productoCodigoBarras,
                infoExtraVenta.getCantidad(),
                infoExtraVenta.getPrecioUnitario()
            );
            return new ResponseEntity<>("Información extra de venta actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la información extra de venta", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Eliminar información extra de venta por su clave primaria compuesta
    @PostMapping("/{ventaId}/{productoCodigoBarras}/delete")
    public ResponseEntity<String> eliminarInfoExtraVenta(
            @PathVariable("ventaId") Long ventaId,
            @PathVariable("productoCodigoBarras") String productoCodigoBarras) {
        try {
            infoExtraVentaRepository.eliminarInfoExtraVenta(ventaId, productoCodigoBarras);
            return new ResponseEntity<>("Información extra de venta eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la información extra de venta", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
