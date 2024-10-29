package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.parranderos.modelo.OrdenCompra;
import java.util.Collection;
import java.util.Map;


public interface OrdenCompraRepository extends JpaRepository<OrdenCompra, Long> {

    
    @Query(value = "SELECT * FROM ORDENCOMPRA", nativeQuery = true)
    Collection<OrdenCompra> obtenerTodasLasOrdenes();

    // Obtener una orden de compra por su ID
    @Query(value = "SELECT * FROM ORDENCOMPRA WHERE IDORDEN = :id", nativeQuery = true)
    OrdenCompra obtenerOrdenPorId(@Param("id") Long id);

 
    @Query(value = "SELECT * FROM ORDENCOMPRA WHERE NIT = :nit", nativeQuery = true)
    Collection<OrdenCompra> obtenerOrdenesPorProveedor(@Param("nit") String nit);

    // Insertar una nueva orden de compra
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO ORDENCOMPRA (IDORDEN, FECHACREACION, ESTADO, FECHAENTREGA, IDSUCURSAL, NIT, CODIGOBARRAS) " +
                   "VALUES (:idOrden, TO_DATE(:fechaCreacion, 'YYYY-MM-DD HH24:MI:SS'), :estado, " +
                   "TO_DATE(:fechaEntrega, 'YYYY-MM-DD HH24:MI:SS'), :sucursalId, :nit, :codigoBarras)", 
            nativeQuery = true)
    void insertarOrden(@Param("idOrden") Long idOrden,
                       @Param("fechaCreacion") String fechaCreacion,
                       @Param("estado") String estado,
                       @Param("fechaEntrega") String fechaEntrega,
                       @Param("sucursalId") Long sucursalId,
                       @Param("codigoBarras") String codigoBarras,
                       @Param("nit") Long nit);
    

    @Modifying
    @Transactional
    @Query(value = "UPDATE ORDENCOMPRA SET FECHACREACION = :fechaCreacion, ESTADO = :estado, " +
            "FECHAENTREGA = :fechaEntrega, IDSUCURSAL = :sucursalId, NIT = :nit, CODIGOBARRAS = :codigoBarras " +
            "WHERE IDORDEN = :id", nativeQuery = true)
    void actualizarOrden(@Param("id") Long id,
                         @Param("fechaCreacion") String fechaCreacion,
                         @Param("estado") String estado,
                         @Param("fechaEntrega") String fechaEntrega,
                         @Param("sucursalId") Long sucursalId,
                         
                         @Param("codigoBarras") String codigoBarras, @Param("nit") Long nit);
@Modifying
@Transactional
                         @Query("UPDATE OrdenCompra o SET o.estado = 'ENTREGADA' WHERE o.IDORDEN = :idOrden")
                         void marcarComoEntregada(@Param("idOrden") Long idOrden);
                         
                         
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM ORDENCOMPRA WHERE IDORDEN = :id", nativeQuery = true)
    void eliminarOrden(@Param("id") Long id);
    @Query(value = "SELECT o.*, p.*, pr.*, s.* " +
                   "FROM ORDENCOMPRA o " +
                   "INNER JOIN PRODUCTO p ON o.CODIGOBARRAS = p.CODIGOBARRAS " +
                   "INNER JOIN PROVEEDOR pr ON o.NIT = pr.NIT " +
                   "INNER JOIN SUCURSAL s ON o.IDSUCURSAL = s.IDSUCURSAL " +
                   "WHERE o.IDORDEN = :idOrden", nativeQuery = true)
    Map<String, Object> obtenerOrdenCompraConDetalles(@Param("idOrden") Long idOrden);  
}
