package uniandes.edu.co.parranderos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.parranderos.modelo.Venta;
import uniandes.edu.co.parranderos.repositorio.VentaRepository;

import java.util.List;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    private VentaRepository ventaRepository;

    // Obtener todas las ventas
    @GetMapping
    public ResponseEntity<List<Venta>> obtenerTodasLasVentas() {
        List<Venta> ventas = ventaRepository.obtenerTodasLasVentas();
        return new ResponseEntity<>(ventas, HttpStatus.OK);
    }

    // Obtener una venta por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Venta> obtenerVentaPorId(@PathVariable("id") Long id) {
        Venta venta = ventaRepository.obtenerVentaPorId(id);
        if (venta != null) {
            return new ResponseEntity<>(venta, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Insertar una nueva venta
    @PostMapping("/new/save")
    public ResponseEntity<String> insertarVenta(@RequestBody Venta venta) {
        try {
            ventaRepository.insertarVenta(
                    venta.getFecha().toString(),
                    venta.getCantidadExistencia(),
                    venta.getSucursal().getIdSucursal(),
                    venta.getCliente().getCedula(),
                    venta.getProveedor().getNit(),
                    venta.getInfoExtraVenta().getIdInfoExtraVenta()
            );
            return new ResponseEntity<>("Venta creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la venta", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar una venta por su ID
    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> actualizarVenta(@PathVariable("id") Long id, @RequestBody Venta venta) {
        try {
            ventaRepository.actualizarVenta(
                    id,
                    venta.getFecha().toString(),
                    venta.getCantidadExistencia(),
                    venta.getSucursal().getIdSucursal(),
                    venta.getCliente().getCedula(),
                    venta.getProveedor().getNit(),
                    venta.getInfoExtraVenta().getIdInfoExtraVenta()
            );
            return new ResponseEntity<>("Venta actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la venta", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Eliminar una venta por su ID
    @PostMapping("/{id}/delete")
    public ResponseEntity<String> eliminarVenta(@PathVariable("id") Long id) {
        try {
            ventaRepository.eliminarVenta(id);
            return new ResponseEntity<>("Venta eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la venta", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
