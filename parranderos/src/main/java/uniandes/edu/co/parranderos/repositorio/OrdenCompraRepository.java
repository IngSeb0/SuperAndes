package uniandes.edu.co.parranderos.repositorio;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.parranderos.modelo.OrdenCompra;

import java.util.Collection;

public interface OrdenCompraRepository extends JpaRepository<OrdenCompra, Long> {

    // Consultar todas las órdenes de compra
    @Query(value = "SELECT * FROM ordenes_compra", nativeQuery = true)
    Collection<OrdenCompra> obtenerTodasLasOrdenesCompra();

    // Consultar una orden de compra por su ID
    @Query(value = "SELECT * FROM ordenes_compra WHERE id = :id", nativeQuery = true)
    OrdenCompra obtenerOrdenCompraPorId(@Param("id") Long id);

    // Insertar una nueva orden de compra
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO ordenes_compra (fecha_creacion, estado, fecha_entrega) " +
            "VALUES (:fechaCreacion, :estado, :fechaEntrega)", nativeQuery = true)
    void insertarOrdenCompra(@Param("fechaCreacion") String fechaCreacion,
                             @Param("estado") String estado,
                             @Param("fechaEntrega") String fechaEntrega);

    // Actualizar una orden de compra por su ID
    @Modifying
    @Transactional
    @Query(value = "UPDATE ordenes_compra SET estado = :estado, fecha_entrega = :fechaEntrega WHERE id = :id", nativeQuery = true)
    void actualizarOrdenCompra(@Param("id") Long id,
                               @Param("estado") String estado,
                               @Param("fechaEntrega") String fechaEntrega);

    // Eliminar una orden de compra por su ID
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM ordenes_compra WHERE id = :id", nativeQuery = true)
    void eliminarOrdenCompra(@Param("id") Long id);
}
