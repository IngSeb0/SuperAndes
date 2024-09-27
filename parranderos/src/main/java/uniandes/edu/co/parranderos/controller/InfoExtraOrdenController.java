package uniandes.edu.co.parranderos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.parranderos.modelo.InfoExtraOrden;
import uniandes.edu.co.parranderos.repositorio.InfoExtraOrdenRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/infoExtraOrden")
public class InfoExtraOrdenController {

    @Autowired
    private InfoExtraOrdenRepository infoExtraOrdenRepository;

    // Obtener todos los registros
    @GetMapping
    public List<InfoExtraOrden> obtenerInfoExtraOrdenes() {
        return infoExtraOrdenRepository.findAll();
    }

    // Obtener un registro por ID
    @GetMapping("/{id}")
    public ResponseEntity<InfoExtraOrden> obtenerInfoExtraOrdenPorId(@PathVariable Long id) {
        Optional<InfoExtraOrden> infoExtraOrden = infoExtraOrdenRepository.findById(id);
        if (infoExtraOrden.isPresent()) {
            return new ResponseEntity<>(infoExtraOrden.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Crear un nuevo registro
    @PostMapping("/new/save")
    public ResponseEntity<InfoExtraOrden> crearInfoExtraOrden(@RequestBody InfoExtraOrden infoExtraOrden) {
        try {
            InfoExtraOrden nuevaInfoExtraOrden = infoExtraOrdenRepository.save(infoExtraOrden);
            return new ResponseEntity<>(nuevaInfoExtraOrden, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar un registro existente
    @PostMapping("/{id}/edit/save")
    public ResponseEntity<InfoExtraOrden> actualizarInfoExtraOrden(@PathVariable Long id, @RequestBody InfoExtraOrden infoExtraOrdenDetalles) {
        Optional<InfoExtraOrden> infoExtraOrden = infoExtraOrdenRepository.findById(id);
        if (infoExtraOrden.isPresent()) {
            InfoExtraOrden infoExtraOrdenActualizado = infoExtraOrden.get();
            infoExtraOrdenActualizado.setCantidad(infoExtraOrdenDetalles.getCantidad());
            infoExtraOrdenActualizado.setCostoUnitario(infoExtraOrdenDetalles.getCostoUnitario());
            infoExtraOrdenActualizado.setOrdenCompra(infoExtraOrdenDetalles.getOrdenCompra());
            infoExtraOrdenActualizado.setProducto(infoExtraOrdenDetalles.getProducto());

            infoExtraOrdenRepository.save(infoExtraOrdenActualizado);
            return new ResponseEntity<>(infoExtraOrdenActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar un registro
    @PostMapping("/{id}/delete")
    public ResponseEntity<HttpStatus> eliminarInfoExtraOrden(@PathVariable Long id) {
        try {
            infoExtraOrdenRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
