package uniandes.edu.co.parranderos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.parranderos.modelo.InfoExtraProveedor;
import uniandes.edu.co.parranderos.repositorio.InfoExtraProveedorRepository;

import java.util.List;

@RestController
@RequestMapping("/info_extra_proveedor")
public class InfoExtraProveedorController {

    @Autowired
    private InfoExtraProveedorRepository infoExtraProveedorRepository;

    // Obtener toda la información extra de proveedores
    @GetMapping
    public List<InfoExtraProveedor> obtenerInfoExtraProveedor() {
        return infoExtraProveedorRepository.obtenerTodaInfoExtraProveedor();
    }

    // Obtener información extra de proveedor por su ID
    @GetMapping("/{id}")
    public ResponseEntity<InfoExtraProveedor> obtenerInfoExtraProveedorPorId(@PathVariable("id") Long id) {
        InfoExtraProveedor infoExtra = infoExtraProveedorRepository.obtenerInfoExtraProveedorPorId(id);
        if (infoExtra != null) {
            return new ResponseEntity<>(infoExtra, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Insertar una nueva información extra de proveedor
    @PostMapping("/new/save")
    public ResponseEntity<String> insertarInfoExtraProveedor(@RequestBody InfoExtraProveedor infoExtraProveedor) {
        try {
            infoExtraProveedorRepository.insertarInfoExtraProveedor(infoExtraProveedor.getCantidadExistencias(), infoExtraProveedor.getProveedorNit(), infoExtraProveedor.getProductoCodigoBarras());
            return new ResponseEntity<>("Información extra de proveedor creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la información extra de proveedor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar información extra de proveedor
    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> actualizarInfoExtraProveedor(@PathVariable("id") Long id, @RequestBody InfoExtraProveedor infoExtraProveedor) {
        try {
            infoExtraProveedorRepository.actualizarInfoExtraProveedor(id, infoExtraProveedor.getCantidadExistencias(), infoExtraProveedor.getProveedorNit(), infoExtraProveedor.getProductoCodigoBarras());
            return new ResponseEntity<>("Información extra de proveedor actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la información extra de proveedor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Eliminar información extra de proveedor
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
