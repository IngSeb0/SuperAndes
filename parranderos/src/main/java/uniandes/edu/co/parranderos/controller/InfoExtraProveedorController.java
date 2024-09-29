package uniandes.edu.co.parranderos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.parranderos.modelo.InfoExtraProveedor;
import uniandes.edu.co.parranderos.modelo.InfoExtraProveedorPk;
import uniandes.edu.co.parranderos.repositorio.InfoExtraProveedorRepository;

import java.util.Collection;

@RestController
public class InfoExtraProveedorController {

    @Autowired
    private InfoExtraProveedorRepository infoExtraProveedorRepository;

    // Obtener toda la información extra de proveedores
    @GetMapping("/infoExtraProveedor")
    public ResponseEntity<Collection<InfoExtraProveedor>> obtenerInfoExtraProveedor() {
        try {
            Collection<InfoExtraProveedor> infoExtraProveedores = infoExtraProveedorRepository.obtenerTodaInfoExtraProveedor();
            return ResponseEntity.ok(infoExtraProveedores);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Crear una nueva información extra de proveedor
    @PostMapping("/infoExtraProveedor/new/save")
    public ResponseEntity<?> createInfoExtraProveedor(@RequestBody InfoExtraProveedor infoExtraProveedor) {
        try {
             InfoExtraProveedorPk pk = infoExtraProveedor.getPk();

            if (pk == null || pk.getProveedorNit() == null || pk.getProductoCodigoBarras() == null) {
                throw new RuntimeException("Faltan datos en la clave primaria");
            }

            Long proveedorNit = pk.getProveedorNit();
            String productoCodigoBarras = pk.getProductoCodigoBarras();

            if (proveedorNit == null || productoCodigoBarras == null) {
                throw new RuntimeException("Faltan datos en la clave primaria");
            }

            Integer cantidadExistencias = infoExtraProveedor.getCantidadExistencias();
            infoExtraProveedorRepository.insertarInfoExtraProveedor(cantidadExistencias, proveedorNit, productoCodigoBarras);

            InfoExtraProveedor nuevaInfoExtraProveedor = infoExtraProveedorRepository.obtenerInfoExtraProveedorPorId(proveedorNit, productoCodigoBarras);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaInfoExtraProveedor);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la información extra del proveedor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar una información extra de proveedor
    @PostMapping("/infoExtraProveedor/{proveedorNit}/{productoCodigoBarras}/edit/save")
    public ResponseEntity<?> actualizarInfoExtraProveedor(
            @PathVariable("proveedorNit") Long proveedorNit,
            @PathVariable("productoCodigoBarras") String productoCodigoBarras,
            @RequestBody InfoExtraProveedor infoExtraProveedorDetalles) {
        try {
            if (proveedorNit == null || productoCodigoBarras == null) {
                throw new RuntimeException("Faltan datos en la clave primaria");
            }

            Integer cantidadExistencias = infoExtraProveedorDetalles.getCantidadExistencias();
            infoExtraProveedorRepository.actualizarInfoExtraProveedor(cantidadExistencias, proveedorNit, productoCodigoBarras);
            
            InfoExtraProveedor actualizadaInfoExtraProveedor = infoExtraProveedorRepository.obtenerInfoExtraProveedorPorId(proveedorNit, productoCodigoBarras);
            return new ResponseEntity<>(actualizadaInfoExtraProveedor, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la información extra del proveedor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Eliminar una información extra de proveedor
    @PostMapping("/infoExtraProveedor/{proveedorNit}/{productoCodigoBarras}/delete")
    public ResponseEntity<?> eliminarInfoExtraProveedor(
            @PathVariable("proveedorNit") Long proveedorNit,
            @PathVariable("productoCodigoBarras") String productoCodigoBarras) {
        try {
            infoExtraProveedorRepository.eliminarInfoExtraProveedor(proveedorNit, productoCodigoBarras);
            return new ResponseEntity<>("Información extra del proveedor eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la información extra del proveedor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
