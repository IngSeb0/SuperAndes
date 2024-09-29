package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.parranderos.modelo.InfoExtraVenta;
import uniandes.edu.co.parranderos.modelo.InfoExtraVentaPk;

import java.util.List;

public interface InfoExtraVentaRepository extends JpaRepository<InfoExtraVenta, InfoExtraVentaPk> {

    // Obtener toda la información extra de las ventas
    @Query("SELECT i FROM InfoExtraVenta i")
    List<InfoExtraVenta> obtenerTodaLaInfoExtraVenta();

    // Obtener una información extra por clave primaria compuesta (ventaId y productoCodigoBarras)
    @Query("SELECT i FROM InfoExtraVenta i WHERE i.pk.venta.id = :ventaId AND i.pk.producto.codigoBarras = :productoCodigoBarras")
    InfoExtraVenta obtenerInfoExtraVentaPorId(@Param("ventaId") Long ventaId, @Param("productoCodigoBarras") String productoCodigoBarras);

    // Insertar nueva información extra de venta
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO INFOEXTRAVENTA (CANTIDAD, PRECIO_UNITARIO, VENTA_IDVENTA, PRODUCTO_CODIGOBARRAS) " +
            "VALUES (:cantidad, :precioUnitario, :ventaId, :productoCodigoBarras)", 
            nativeQuery = true)
    void insertarInfoExtraVenta(@Param("cantidad") Integer cantidad, 
                                @Param("precioUnitario") Float precioUnitario, 
                                @Param("ventaId") Long ventaId,
                                @Param("productoCodigoBarras") String productoCodigoBarras);

    // Actualizar información extra por clave primaria compuesta (ventaId y productoCodigoBarras)
    @Modifying
    @Transactional
    @Query(value = "UPDATE INFOEXTRAVENTA SET CANTIDAD = :cantidad, PRECIO_UNITARIO = :precioUnitario " +
            "WHERE VENTA_IDVENTA = :ventaId AND PRODUCTO_CODIGOBARRAS = :productoCodigoBarras", 
            nativeQuery = true)
    void actualizarInfoExtraVenta(@Param("ventaId") Long ventaId, 
                                  @Param("productoCodigoBarras") String productoCodigoBarras,
                                  @Param("cantidad") Integer cantidad, 
                                  @Param("precioUnitario") Float precioUnitario);

    // Eliminar información extra por clave primaria compuesta (ventaId y productoCodigoBarras)
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM INFOEXTRAVENTA WHERE VENTA_IDVENTA = :ventaId AND PRODUCTO_CODIGOBARRAS = :productoCodigoBarras", 
            nativeQuery = true)
    void eliminarInfoExtraVenta(@Param("ventaId") Long ventaId, @Param("productoCodigoBarras") String productoCodigoBarras);
}
