package uniandes.edu.co.parranderos.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.parranderos.modelo.Proveedor;
import uniandes.edu.co.parranderos.repositorio.ProveedorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

@RestController
@RequestMapping("/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @GetMapping
    public Collection<Proveedor> obtenerProveedores() {
        return proveedorRepository.obtenerTodosLosProveedores();
    }

    // Obtener un proveedor por NIT
    @GetMapping("/{nit}")
    public ResponseEntity<Proveedor> obtenerProveedorPorNit(@PathVariable("nit") Long nit) {
        Proveedor proveedor = proveedorRepository.obtenerProveedorPorNit(nit);
        if (proveedor != null) {
            return new ResponseEntity<>(proveedor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Insertar un nuevo proveedor
    @PostMapping("/new/save")
    public ResponseEntity<String> insertarProveedor(@RequestBody Proveedor proveedor) {
        try {
            proveedorRepository.insertarProveedor(proveedor.getNit(), proveedor.getNombre(), proveedor.getDireccion(), proveedor.getNombreContacto(), proveedor.getTelefonoContacto());
            return new ResponseEntity<>("Proveedor creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el proveedor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

// Actualizar un proveedor por NIT
@PutMapping("/{nit}/edit")
public ResponseEntity<String> actualizarProveedor(@PathVariable("nit") Long nit, @RequestBody Proveedor proveedor) {
    try {
        proveedorRepository.actualizarProveedor(nit, proveedor.getNombre(), proveedor.getDireccion(), proveedor.getNombreContacto(), proveedor.getTelefonoContacto());
        return new ResponseEntity<>("Proveedor actualizado exitosamente", HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<>("Error al actualizar el proveedor", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

    // Eliminar un proveedor por NIT
    @PostMapping("/{nit}/delete")
    public ResponseEntity<String> eliminarProveedor(@PathVariable("nit") Long nit) {
        try {
            proveedorRepository.eliminarProveedor(nit);
            return new ResponseEntity<>("Proveedor eliminado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el proveedor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
