package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.parranderos.modelo.InfoExtraVenta;

import java.util.List;

public interface InfoExtraVentaRepository extends JpaRepository<InfoExtraVenta, Long> {

    // Obtener toda la información extra de las ventas
    @Query("SELECT i FROM InfoExtraVenta i")
    List<InfoExtraVenta> obtenerTodaLaInfoExtraVenta();

    // Obtener una información extra por ID
    @Query("SELECT i FROM InfoExtraVenta i WHERE i.idInfoExtraVenta = :id")
    InfoExtraVenta obtenerInfoExtraVentaPorId(@Param("id") Long id);

    // Insertar nueva información extra
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO info_extra_venta (cantidad, precio_unitario, venta_idventa, producto_codigobarras) " +
            "VALUES (:cantidad, :precioUnitario, :ventaId, :productoCodigoBarras)", nativeQuery = true)
    void insertarInfoExtraVenta(@Param("cantidad") Integer cantidad, 
                                @Param("precioUnitario") Float precioUnitario, 
                                @Param("ventaId") Long ventaId,
                                @Param("productoCodigoBarras") String productoCodigoBarras);

    // Actualizar información extra por ID
    @Modifying
    @Transactional
    @Query(value = "UPDATE info_extra_venta SET cantidad = :cantidad, precio_unitario = :precioUnitario, " +
            "venta_idventa = :ventaId, producto_codigobarras = :productoCodigoBarras " +
            "WHERE idinfoextraventa = :id", nativeQuery = true)
    void actualizarInfoExtraVenta(@Param("id") Long id, 
                                  @Param("cantidad") Integer cantidad, 
                                  @Param("precioUnitario") Float precioUnitario,
                                  @Param("ventaId") Long ventaId,
                                  @Param("productoCodigoBarras") String productoCodigoBarras);

    // Eliminar una información extra por ID
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM info_extra_venta WHERE idinfoextraventa = :id", nativeQuery = true)
    void eliminarInfoExtraVenta(@Param("id") Long id);
}
