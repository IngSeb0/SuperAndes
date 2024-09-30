package uniandes.edu.co.parranderos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.parranderos.modelo.Sucursal;
import uniandes.edu.co.parranderos.repositorio.SucursalRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
            return new ResponseEntity<>(sucursales, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener una sucursal por su ID
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

    // Insertar una nueva sucursal con ciudad
    @PostMapping("/new/save")
    public ResponseEntity<String> insertarSucursal(@RequestBody Sucursal sucursal) {
        try {
            sucursalRepository.insertarSucursal(
                sucursal.getIdSucursal(),
                    sucursal.getNombreSucursal(),

                    sucursal.getTamanioInstalacion(),
                    sucursal.getDireccion(),
                    sucursal.getTelefono(),
                    sucursal.getCodigoCiudad().getCodigoCiudad()
            );
            return new ResponseEntity<>("Sucursal creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la sucursal", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener sucursales con un producto específico
    @GetMapping("/consulta")
    public ResponseEntity<?> obtenerSucursalesConProducto(@PathVariable String codigoBarras) {
        try {
            Collection<Sucursal> sucursales = sucursalRepository.obtenerSucursalesConProducto(codigoBarras);
            return new ResponseEntity<>(sucursales, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al obtener sucursales: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar una sucursal por su ID con ciudad
    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> actualizarSucursal(@PathVariable("id") Long id, @RequestBody Sucursal sucursal) {
        try {
            sucursalRepository.actualizarSucursal(
                    id,
                    sucursal.getNombreSucursal(),
                    sucursal.getTamanioInstalacion(),
                    sucursal.getDireccion(),
                    sucursal.getTelefono(),
                    sucursal.getCodigoCiudad().getCodigoCiudad()
            );
            return new ResponseEntity<>("Sucursal actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la sucursal", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Eliminar una sucursal por su ID
    @PostMapping("/{id}/delete")
    public ResponseEntity<String> eliminarSucursal(@PathVariable("id") Long id) {
        try {
            sucursalRepository.eliminarSucursal(id);
            return new ResponseEntity<>("Sucursal eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la sucursal", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
