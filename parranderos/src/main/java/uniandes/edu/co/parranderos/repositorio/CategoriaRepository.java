package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.parranderos.modelo.Categoria;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query(value = "SELECT * FROM CATEGORIA", nativeQuery = true)
    List<Categoria> obtenerTodasLasCategorias();

    @Query(value = "SELECT * FROM CATEGORIA WHERE CODIGOCATEGORIA = :id", nativeQuery = true)
    Categoria obtenerCategoriaPorId(@Param("id") Long id);

    // Insertar una nueva categoría con relación al producto
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO CATEGORIA (CARACTERISTICASALMACENAMIENTO, NOMBRECATEGORIA, DESCRIPCION, CODIGOBARRAS) VALUES (:caracteristicasAlmacenamiento, :nombreCategoria, :descripcion, :codigoBarras)", nativeQuery = true)
    void insertarCategoria(@Param("caracteristicasAlmacenamiento") String caracteristicasAlmacenamiento,
                           @Param("nombreCategoria") String nombreCategoria,
                           @Param("descripcion") String descripcion,
                           @Param("codigoBarras") String codigoBarras);

    // Actualizar una categoría existente
    @Modifying
    @Transactional
    @Query(value = "UPDATE CATEGORIA SET CARACTERISTICASALMACENAMIENTO = :caracteristicasAlmacenamiento, NOMBRECATEGORIA = :nombreCategoria, DESCRIPCION = :descripcion, CODIGOBARRAS = :codigoBarras WHERE CODIGOCATEGORIA = :id", nativeQuery = true)
    void actualizarCategoria(@Param("id") Long id,
                             @Param("caracteristicasAlmacenamiento") String caracteristicasAlmacenamiento,
                             @Param("nombreCategoria") String nombreCategoria,
                             @Param("descripcion") String descripcion,
                             @Param("codigoBarras") String codigoBarras);

    // Eliminar una categoría por su ID
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM CATEGORIA WHERE CODIGOCATEGORIA = :id", nativeQuery = true)
    void eliminarCategoria(@Param("id") Long id);
}
