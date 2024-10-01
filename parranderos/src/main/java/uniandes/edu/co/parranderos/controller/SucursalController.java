package uniandes.edu.co.parranderos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.parranderos.modelo.Sucursal;
import uniandes.edu.co.parranderos.repositorio.SucursalRepository;

import java.util.Collection;

@RestController
@RequestMapping("/sucursales")
public class SucursalController {

    @Autowired
    private SucursalRepository sucursalRepository;

    @GetMapping
    public ResponseEntity<Collection<Sucursal>> obtenerSucursales() {
        try {
            Collection<Sucursal> sucursales = sucursalRepository.obtenerTodasLasSucursales();
            return ResponseEntity.ok(sucursales);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sucursal> obtenerSucursalPorId(@PathVariable("id") Long id) {
        try {
            Sucursal sucursal = sucursalRepository.obtenerSucursalPorId(id);
            if (sucursal != null) {
                return new ResponseEntity<>(sucursal, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/disponibilidad-producto")
    public ResponseEntity<Collection<Sucursal>> obtenerSucursalesConProductoDisponible(
            @RequestParam(required = false) String productoId,
            @RequestParam(required = false) String nombreProducto) {
        try {
            if (productoId == null && nombreProducto == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            // Llamada al repositorio para obtener las sucursales con disponibilidad del producto
            Collection<Sucursal> sucursales = sucursalRepository.obtenerSucursalesConProductoDisponible(productoId, nombreProducto);

            if (sucursales.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(sucursales, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/new/save")
    public ResponseEntity<String> insertarSucursal(@RequestBody Sucursal sucursal) {
        try {
            sucursalRepository.insertarSucursal(
                    sucursal.getIdSucursal(),
                    sucursal.getNombreSucursal(),
                    sucursal.getTamanioInstalacion(),
                    sucursal.getDireccion(),
                    sucursal.getTelefono(),
                    sucursal.getCiudad().getCodigoCiudad() // Obtener el código de la ciudad
            );
            return new ResponseEntity<>("Sucursal creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la sucursal", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> actualizarSucursal(@PathVariable("id") Long id, @RequestBody Sucursal sucursal) {
        try {
            sucursalRepository.actualizarSucursal(
                    id,
                    sucursal.getNombreSucursal(),
                    sucursal.getTamanioInstalacion(),
                    sucursal.getDireccion(),
                    sucursal.getTelefono(),
                    sucursal.getCiudad().getCodigoCiudad() // Actualizar la ciudad
            );
            return new ResponseEntity<>("Sucursal actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la sucursal", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
