package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.parranderos.modelo.Categoria;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    // Obtener todas las categorías
    @Query("SELECT c FROM Categoria c")
    List<Categoria> obtenerTodasLasCategorias();

    // Obtener una categoría por ID
    @Query("SELECT c FROM Categoria c WHERE c.id = :id")
    Categoria obtenerCategoriaPorId(@Param("id") Long id);

    // Insertar una nueva categoría
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO categoria (nombre, descripcion) VALUES (:nombre, :descripcion)", nativeQuery = true)
    void insertarCategoria(@Param("nombre") String nombre, @Param("descripcion") String descripcion);

    // Actualizar una categoría por ID
    @Modifying
    @Transactional
    @Query(value = "UPDATE categoria SET nombre = :nombre, descripcion = :descripcion WHERE id = :id", nativeQuery = true)
    void actualizarCategoria(@Param("id") Long id, @Param("nombre") String nombre, @Param("descripcion") String descripcion);

    // Eliminar una categoría por ID
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM categoria WHERE id = :id", nativeQuery = true)
    void eliminarCategoria(@Param("id") Long id);
}
