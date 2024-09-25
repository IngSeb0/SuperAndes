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

    // Insertar una nueva categoría (CORREGIDO)
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO categoria (codigo, nombre, descripcion, caracteristica) VALUES (:codigo, :nombre, :descripcion, :caracteristica)", nativeQuery = true)
    void insertarCategoria(@Param("codigo") Long codigo, 
                           @Param("nombre") String nombre, 
                           @Param("descripcion") String descripcion, 
                           @Param("caracteristica") String caracteristica);

    // Actualizar una categoría por ID (CORREGIDO)
    @Modifying
    @Transactional
    @Query(value = "UPDATE categoria SET nombre = :nombre, descripcion = :descripcion, caracteristica = :caracteristica WHERE id = :codigo", nativeQuery = true)
    void actualizarCategoria(@Param("codigo") Long codigo, 
                             @Param("nombre") String nombre, 
                             @Param("descripcion") String descripcion, 
                             @Param("caracteristica") String caracteristica);

    // Eliminar una categoría por ID
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM categoria WHERE id = :id", nativeQuery = true)
    void eliminarCategoria(@Param("id") Long id);
}
