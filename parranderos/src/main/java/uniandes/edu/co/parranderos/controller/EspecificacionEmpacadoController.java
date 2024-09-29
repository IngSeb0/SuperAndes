package uniandes.edu.co.parranderos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.parranderos.modelo.EspecificacionEmpacado;
import uniandes.edu.co.parranderos.repositorio.EspecificacionEmpacadoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

@RestController
@RequestMapping("/especificaciones_empacado")
public class EspecificacionEmpacadoController {

    @Autowired
    private EspecificacionEmpacadoRepository especificacionEmpacadoRepository;

    // Obtener todas las especificaciones de empacado
    @GetMapping
    public Collection<EspecificacionEmpacado> obtenerEspecificaciones() {
        return especificacionEmpacadoRepository.obtenerTodasLasEspecificaciones();
    }

    // Obtener una especificación de empacado por ID
    @GetMapping("/{id}")
    public ResponseEntity<EspecificacionEmpacado> obtenerEspecificacionPorId(@PathVariable("id") Long id) {
        EspecificacionEmpacado especificacion = especificacionEmpacadoRepository.obtenerEspecificacionPorId(id);
        if (especificacion != null) {
            return new ResponseEntity<>(especificacion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Insertar una nueva especificación de empacado
    @PostMapping("/new/save")
    public ResponseEntity<String> insertarEspecificacion(@RequestBody EspecificacionEmpacado especificacionEmpacado) {
        try {
            especificacionEmpacadoRepository.insertarEspecificacion(
                    especificacionEmpacado.getVolumen(),
                    especificacionEmpacado.getPesoGr()
            );
            return new ResponseEntity<>("Especificación de empacado creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la especificación de empacado", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar una especificación de empacado por su ID
    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> actualizarEspecificacion(@PathVariable("id") Long id, @RequestBody EspecificacionEmpacado especificacionEmpacado) {
        try {
            especificacionEmpacadoRepository.actualizarEspecificacion(
                    id,
                    especificacionEmpacado.getVolumen(),
                    especificacionEmpacado.getPesoGr()
            );
            return new ResponseEntity<>("Especificación de empacado actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la especificación de empacado", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Eliminar una especificación de empacado por su ID
    @PostMapping("/{id}/delete")
    public ResponseEntity<String> eliminarEspecificacion(@PathVariable("id") Long id) {
        try {
            especificacionEmpacadoRepository.eliminarEspecificacion(id);
            return new ResponseEntity<>("Especificación de empacado eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la especificación de empacado", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
