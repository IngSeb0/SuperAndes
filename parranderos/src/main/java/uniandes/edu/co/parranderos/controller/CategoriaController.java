package uniandes.edu.co.parranderos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.parranderos.modelo.Categoria;
import uniandes.edu.co.parranderos.repositorio.CategoriaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

@RestController
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Obtener todas las categorías
    @GetMapping("/categorias")
    public Collection<Categoria> obtenerCategorias() {
        return categoriaRepository.obtenerTodasLasCategorias();
    }

    // Insertar una nueva categoría
    @PostMapping("/categorias/new/save")
    public ResponseEntity<String> insertarCategoria(@RequestBody Categoria categoria) {
        try {
            categoriaRepository.insertarCategoria(categoria.getCodigo(),categoria.getNombre(), categoria.getDescripcion(), categoria.getCaracteristica());
            return new ResponseEntity<>("Categoría creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la categoría", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar una categoría
    @PostMapping("/categorias/{id}/edit/save")
    public ResponseEntity<String> actualizarCategoria(@PathVariable("id") Long id, @RequestBody Categoria categoria) {
        try {
            categoriaRepository.actualizarCategoria(categoria.getCodigo(), categoria.getNombre(), categoria.getDescripcion(), categoria.getCaracteristica());
            return new ResponseEntity<>("Categoría actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la categoría", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Eliminar una categoría
    @PostMapping("/categorias/{id}/delete")
    public ResponseEntity<String> eliminarCategoria(@PathVariable("id") Long id) {
        try {
            categoriaRepository.eliminarCategoria(id);
            return new ResponseEntity<>("Categoría eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la categoría", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
