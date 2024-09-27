package uniandes.edu.co.parranderos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.parranderos.modelo.InfoExtraVenta;
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
    public Collection<InfoExtraVenta> obtenerInfoExtraVenta() {
        return infoExtraVentaRepository.obtenerTodaLaInfoExtraVenta();
    }

    // Obtener información extra de venta por su ID
    @GetMapping("/{id}")
    public ResponseEntity<InfoExtraVenta> obtenerInfoExtraVentaPorId(@PathVariable("id") Long id) {
        InfoExtraVenta infoExtra = infoExtraVentaRepository.obtenerInfoExtraVentaPorId(id);
        if (infoExtra != null) {
            return new ResponseEntity<>(infoExtra, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Insertar una nueva información extra de venta
    @PostMapping("/new/save")
    public ResponseEntity<String> insertarInfoExtraVenta(@RequestBody InfoExtraVenta infoExtraVenta) {
        try {
            infoExtraVentaRepository.insertarInfoExtraVenta(
                infoExtraVenta.getCantidad(), 
                infoExtraVenta.getPrecioUnitario(),
                infoExtraVenta.getVenta().getIdVenta(),
                infoExtraVenta.getProducto().getCodigoBarras()
            );
            return new ResponseEntity<>("Información extra de venta creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la información extra de venta", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar información extra de venta por su ID
    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> actualizarInfoExtraVenta(@PathVariable("id") Long id, @RequestBody InfoExtraVenta infoExtraVenta) {
        try {
            infoExtraVentaRepository.actualizarInfoExtraVenta(id, 
                infoExtraVenta.getCantidad(), 
                infoExtraVenta.getPrecioUnitario(),
                infoExtraVenta.getVenta().getIdVenta(),
                infoExtraVenta.getProducto().getCodigoBarras()
            );
            return new ResponseEntity<>("Información extra de venta actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la información extra de venta", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Eliminar información extra de venta por su ID
    @PostMapping("/{id}/delete")
    public ResponseEntity<String> eliminarInfoExtraVenta(@PathVariable("id") Long id) {
        try {
            infoExtraVentaRepository.eliminarInfoExtraVenta(id);
            return new ResponseEntity<>("Información extra de venta eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la información extra de venta", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
