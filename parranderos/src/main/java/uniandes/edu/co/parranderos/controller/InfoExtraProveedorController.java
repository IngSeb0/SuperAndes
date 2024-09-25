package uniandes.edu.co.parranderos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.parranderos.modelo.InfoExtraProveedor;
import uniandes.edu.co.parranderos.repositorio.InfoExtraProveedorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/info_extra_proveedor")
public class InfoExtraProveedorController {

    @Autowired
    private InfoExtraProveedorRepository infoExtraProveedorRepository;

    // Obtener toda la información extra de proveedores
    @GetMapping
    public List<InfoExtraProveedor> obtenerInfoExtraProveedores() {
        return infoExtraProveedorRepository.obtenerTodaLaInfoExtraProveedor();
    }

    // Obtener información extra de un proveedor por su ID
    @GetMapping("/{id}")
    public ResponseEntity<InfoExtraProveedor> obtenerInfoExtraProveedorPorId(@PathVariable("id") Long id) {
        InfoExtraProveedor infoExtraProveedor = infoExtraProveedorRepository.obtenerInfoExtraProveedorPorId(id);
        if (infoExtraProveedor != null) {
            return new ResponseEntity<>(infoExtraProveedor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Insertar una nueva información extra de proveedor
    @PostMapping("/new/save")
    public ResponseEntity<String> insertarInfoExtraProveedor(@RequestBody InfoExtraProveedor infoExtraProveedor) {
        try {
            infoExtraProveedorRepository.insertarInfoExtraProveedor(infoExtraProveedor.getCantidadExistencia());
            return new ResponseEntity<>("Información extra de proveedor creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la información extra de proveedor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar información extra de un proveedor por su ID
    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> actualizarInfoExtraProveedor(@PathVariable("id") Long id, @RequestBody InfoExtraProveedor infoExtraProveedor) {
        try {
            infoExtraProveedorRepository.actualizarInfoExtraProveedor(id, infoExtraProveedor.getCantidadExistencia());
            return new ResponseEntity<>("Información extra de proveedor actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la información extra de proveedor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Eliminar información extra de un proveedor por su ID
    @PostMapping("/{id}/delete")
    public ResponseEntity<String> eliminarInfoExtraProveedor(@PathVariable("id") Long id) {
        try {
            infoExtraProveedorRepository.eliminarInfoExtraProveedor(id);
            return new ResponseEntity<>("Información extra de proveedor eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la información extra de proveedor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
