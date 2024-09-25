package uniandes.edu.co.parranderos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.parranderos.modelo.Venta;
import uniandes.edu.co.parranderos.repositorio.VentaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

@RestController
public class VentaController {

    @Autowired
    private VentaRepository ventaRepository;

    // Obtener todas las ventas
    @GetMapping("/ventas")
    public Collection<Venta> obtenerVentas() {
        return ventaRepository.obtenerTodasLasVentas();
    }

    // Insertar una nueva venta
    @PostMapping("/ventas/new/save")
    public ResponseEntity<String> insertarVenta(@RequestBody Venta venta) {
        try {
            ventaRepository.insertarVenta(venta.getId(), venta.getFecha(), venta.getCliente().getCedula());
            return new ResponseEntity<>("Venta creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la venta", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar una venta
    @PostMapping("/ventas/{id}/edit/save")
    public ResponseEntity<String> actualizarVenta(@PathVariable("id") Long id, @RequestBody Venta venta) {
        try {
            ventaRepository.actualizarVenta(id, venta.getFecha(), venta.getCliente().getCedula());
            return new ResponseEntity<>("Venta actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la venta", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Eliminar una venta
    @PostMapping("/ventas/{id}/delete")
    public ResponseEntity<String> eliminarVenta(@PathVariable("id") Long id) {
        try {
            ventaRepository.eliminarVenta(id);
            return new ResponseEntity<>("Venta eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la venta", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
