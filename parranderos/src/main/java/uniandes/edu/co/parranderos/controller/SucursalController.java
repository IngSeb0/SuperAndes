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

    // Obtener todas las sucursales
    @GetMapping
    public Collection<Sucursal> obtenerSucursales() {
        return sucursalRepository.obtenerTodasLasSucursales();
    }

    // Obtener una sucursal por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Sucursal> obtenerSucursalPorId(@PathVariable("id") Long id) {
        Sucursal sucursal = sucursalRepository.obtenerSucursalPorId(id);
        if (sucursal != null) {
            return new ResponseEntity<>(sucursal, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Insertar una nueva sucursal
    @PostMapping("/new/save")
    public ResponseEntity<String> insertarSucursal(@RequestBody Sucursal sucursal) {
        try {
            sucursalRepository.insertarSucursal(
                    sucursal.getNombre(),
                    sucursal.getTamaño(),
                    sucursal.getDireccion(),
                    sucursal.getTelefono()
            );
            return new ResponseEntity<>("Sucursal creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la sucursal", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar una sucursal por su ID
    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> actualizarSucursal(@PathVariable("id") Long id, @RequestBody Sucursal sucursal) {
        try {
            sucursalRepository.actualizarSucursal(id, sucursal.getNombre(), sucursal.getTamaño());
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
