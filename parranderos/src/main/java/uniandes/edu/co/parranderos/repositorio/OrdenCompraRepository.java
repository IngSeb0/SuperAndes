package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.parranderos.modelo.OrdenCompra;

import java.util.List;

public interface OrdenCompraRepository extends JpaRepository<OrdenCompra, Long> {

    // Obtener todas las órdenes de compra
    @Query(value = "SELECT * FROM ORDENCOMPRA", nativeQuery = true)
    List<OrdenCompra> obtenerTodasLasOrdenes();

    // Obtener una orden de compra por su ID
    @Query(value = "SELECT * FROM ORDENCOMPRA WHERE IDORDEN = :id", nativeQuery = true)
    OrdenCompra obtenerOrdenPorId(@Param("id") Long id);

    // Insertar una nueva orden de compra
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO ORDENCOMPRA (FECHACREACION, ESTADO, FECHAENTREGA, SUCURSAL_IDSUCURSAL) VALUES (:fechaCreacion, :estado, :fechaEntrega, :sucursalId)", nativeQuery = true)
    void insertarOrden(@Param("fechaCreacion") String fechaCreacion,
                       @Param("estado") String estado,
                       @Param("fechaEntrega") String fechaEntrega,
                       @Param("sucursalId") Long sucursalId);

    // Actualizar una orden de compra por su ID
    @Modifying
    @Transactional
    @Query(value = "UPDATE ORDENCOMPRA SET FECHACREACION = :fechaCreacion, ESTADO = :estado, FECHAENTREGA = :fechaEntrega, SUCURSAL_IDSUCURSAL = :sucursalId WHERE IDORDEN = :id", nativeQuery = true)
    void actualizarOrden(@Param("id") Long id,
                         @Param("fechaCreacion") String fechaCreacion,
                         @Param("estado") String estado,
                         @Param("fechaEntrega") String fechaEntrega,
                         @Param("sucursalId") Long sucursalId);

    // Eliminar una orden de compra por su ID
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM ORDENCOMPRA WHERE IDORDEN = :id", nativeQuery = true)
    void eliminarOrden(@Param("id") Long id);
}
