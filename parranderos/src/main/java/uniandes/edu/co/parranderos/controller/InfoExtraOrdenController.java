package uniandes.edu.co.parranderos.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.parranderos.modelo.InfoExtraOrden;
import uniandes.edu.co.parranderos.repositorio.InfoExtraOrdenRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

@RestController
@RequestMapping("/info_extra_ordenes")
public class InfoExtraOrdenController {

    @Autowired
    private InfoExtraOrdenRepository infoExtraOrdenRepository;

    // Obtener toda la información extra de órdenes
    @GetMapping
    public Collection<InfoExtraOrden> obtenerInfoExtraOrdenes() {
        return infoExtraOrdenRepository.obtenerTodaLaInfoExtraOrden();
    }

    // Obtener una información extra de orden por su ID
    @GetMapping("/{id}")
    public ResponseEntity<InfoExtraOrden> obtenerInfoExtraOrdenPorId(@PathVariable("id") Long id) {
        InfoExtraOrden infoExtra = infoExtraOrdenRepository.obtenerInfoExtraOrdenPorId(id);
        if (infoExtra != null) {
            return new ResponseEntity<>(infoExtra, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Insertar una nueva información extra de orden
    @PostMapping("/new/save")
    public ResponseEntity<String> insertarInfoExtraOrden(@RequestBody InfoExtraOrden infoExtra) {
        try {
            infoExtraOrdenRepository.insertarInfoExtraOrden(infoExtra.getCantidad(), infoExtra.getCostoUnitarioCompra());
            return new ResponseEntity<>("Información extra de orden creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la información extra de orden", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar una información extra de orden por su ID
    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> actualizarInfoExtraOrden(@PathVariable("id") Long id, @RequestBody InfoExtraOrden infoExtra) {
        try {
            infoExtraOrdenRepository.actualizarInfoExtraOrden(id, infoExtra.getCantidad(), infoExtra.getCostoUnitarioCompra());
            return new ResponseEntity<>("Información extra de orden actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la información extra de orden", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Eliminar una información extra de orden por su ID
    @PostMapping("/{id}/delete")
    public ResponseEntity<String> eliminarInfoExtraOrden(@PathVariable("id") Long id) {
        try {
            infoExtraOrdenRepository.eliminarInfoExtraOrden(id);
            return new ResponseEntity<>("Información extra de orden eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la información extra de orden", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

