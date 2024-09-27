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
    @Query("SELECT c FROM Categoria c WHERE c.codigoCategoria = :codigoCategoria")
    Categoria obtenerCategoriaPorId(@Param("codigoCategoria") Long codigoCategoria);

    // Insertar una nueva categoría
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO CATEGORIA (CARACTERISTICASALMACENAMIENTO, NOMBRECATEGORIA, DESCRIPCIÓN) VALUES (:caracteristicasAlmacenamiento, :nombreCategoria, :descripcion)", nativeQuery = true)
    void insertarCategoria(@Param("caracteristicasAlmacenamiento") String caracteristicasAlmacenamiento,
                           @Param("nombreCategoria") String nombreCategoria,
                           @Param("descripcion") String descripcion);

    // Actualizar una categoría por ID
    @Modifying
    @Transactional
    @Query(value = "UPDATE CATEGORIA SET CARACTERISTICASALMACENAMIENTO = :caracteristicasAlmacenamiento, NOMBRECATEGORIA = :nombreCategoria, DESCRIPCIÓN = :descripcion WHERE CODIGOCATEGORIA = :codigoCategoria", nativeQuery = true)
    void actualizarCategoria(@Param("codigoCategoria") Long codigoCategoria,
                             @Param("caracteristicasAlmacenamiento") String caracteristicasAlmacenamiento,
                             @Param("nombreCategoria") String nombreCategoria,
                             @Param("descripcion") String descripcion);

    // Eliminar una categoría por ID
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM CATEGORIA WHERE CODIGOCATEGORIA = :codigoCategoria", nativeQuery = true)
    void eliminarCategoria(@Param("codigoCategoria") Long codigoCategoria);
}
