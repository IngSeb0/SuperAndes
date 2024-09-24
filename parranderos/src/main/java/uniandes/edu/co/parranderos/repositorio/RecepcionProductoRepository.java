package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.parranderos.modelo.RecepcionProducto;

import java.util.Collection;

public interface RecepcionProductoRepository extends JpaRepository<RecepcionProducto, Long> {

    // Consultar todas las recepciones de productos
    @Query(value = "SELECT * FROM recepciones_producto", nativeQuery = true)
    Collection<RecepcionProducto> obtenerTodasLasRecepciones();

    // Consultar una recepción de producto por su ID
    @Query(value = "SELECT * FROM recepciones_producto WHERE id = :id", nativeQuery = true)
    RecepcionProducto obtenerRecepcionPorId(@Param("id") Long id);

    // Insertar una nueva recepción de producto
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO recepciones_producto (fecha_recepcion) " +
            "VALUES (:fechaRecepcion)", nativeQuery = true)
    void insertarRecepcionProducto(@Param("fechaRecepcion") String fechaRecepcion);

    // Actualizar una recepción de producto por su ID
    @Modifying
    @Transactional
    @Query(value = "UPDATE recepciones_producto SET fecha_recepcion = :fechaRecepcion WHERE id = :id", nativeQuery = true)
    void actualizarRecepcionProducto(@Param("id") Long id,
                                     @Param("fechaRecepcion") String fechaRecepcion);

    // Eliminar una recepción de producto por su ID
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM recepciones_producto WHERE id = :id", nativeQuery = true)
    void eliminarRecepcionProducto(@Param("id") Long id);
}
