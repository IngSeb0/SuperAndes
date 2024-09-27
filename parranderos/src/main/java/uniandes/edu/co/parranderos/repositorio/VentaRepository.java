package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.parranderos.modelo.Venta;

import java.util.List;

public interface VentaRepository extends JpaRepository<Venta, Long> {

    // Obtener todas las ventas
    @Query(value = "SELECT * FROM VENTA", nativeQuery = true)
    List<Venta> obtenerTodasLasVentas();

    // Obtener una venta por su ID
    @Query(value = "SELECT * FROM VENTA WHERE IDVENTA = :id", nativeQuery = true)
    Venta obtenerVentaPorId(@Param("id") Long id);

    // Insertar una nueva venta
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO VENTA (FECHA, CANTIDADEXISTENCIA, SUCURSAL_IDSUCURSAL, CLIENTE_CEDULA, PROVEEDOR_NIT, VENTA_IDINFOEXTRAVENTA) VALUES (:fecha, :cantidadExistencia, :idSucursal, :idCliente, :idProveedor, :idInfoExtraVenta)", nativeQuery = true)
    void insertarVenta(@Param("fecha") String fecha, @Param("cantidadExistencia") Long cantidadExistencia, @Param("idSucursal") Long idSucursal, @Param("idCliente") Long idCliente, @Param("idProveedor") Long idProveedor, @Param("idInfoExtraVenta") Long idInfoExtraVenta);

    // Actualizar una venta por su ID
    @Modifying
    @Transactional
    @Query(value = "UPDATE VENTA SET FECHA = :fecha, CANTIDADEXISTENCIA = :cantidadExistencia, SUCURSAL_IDSUCURSAL = :idSucursal, CLIENTE_CEDULA = :idCliente, PROVEEDOR_NIT = :idProveedor, VENTA_IDINFOEXTRAVENTA = :idInfoExtraVenta WHERE IDVENTA = :id", nativeQuery = true)
    void actualizarVenta(@Param("id") Long id, @Param("fecha") String fecha, @Param("cantidadExistencia") Long cantidadExistencia, @Param("idSucursal") Long idSucursal, @Param("idCliente") Long idCliente, @Param("idProveedor") Long idProveedor, @Param("idInfoExtraVenta") Long idInfoExtraVenta);

    // Eliminar una venta por su ID
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM VENTA WHERE IDVENTA = :id", nativeQuery = true)
    void eliminarVenta(@Param("id") Long id);
}
