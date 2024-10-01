package uniandes.edu.co.parranderos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.parranderos.modelo.Categoria;
import uniandes.edu.co.parranderos.repositorio.CategoriaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public ResponseEntity<List<Categoria>> obtenerCategorias() {
        try {
            List<Categoria> categorias = categoriaRepository.obtenerTodasLasCategorias();
            return new ResponseEntity<>(categorias, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> obtenerCategoriaPorId(@PathVariable("id") Long id) {
        try {
            Categoria categoria = categoriaRepository.obtenerCategoriaPorId(id);
            if (categoria != null) {
                return new ResponseEntity<>(categoria, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/new/save")
    public ResponseEntity<String> insertarCategoria(@RequestBody Categoria categoria) {
        try {
            categoriaRepository.insertarCategoria(
                categoria.getCodigoCategoria(),
                    categoria.getCaracteristicasAlmacenamiento(),
                    categoria.getNombreCategoria(),
                    categoria.getDescripcion(),
                    categoria.getProducto().getCodigoBarras()
            );
            return new ResponseEntity<>("Categoría creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la categoría", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> actualizarCategoria(@PathVariable("id") Long id, @RequestBody Categoria categoria) {
        try {
            categoriaRepository.actualizarCategoria(
                    id,
                    categoria.getCaracteristicasAlmacenamiento(),
                    categoria.getNombreCategoria(),
                    categoria.getDescripcion(),
                    categoria.getProducto().getCodigoBarras()
            );
            return new ResponseEntity<>("Categoría actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la categoría", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}/delete")
    public ResponseEntity<String> eliminarCategoria(@PathVariable("id") Long id) {
        try {
            categoriaRepository.eliminarCategoria(id);
            return new ResponseEntity<>("Categoría eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la categoría", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
