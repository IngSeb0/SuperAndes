package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.parranderos.modelo.InfoExtraProveedor;

import java.util.List;

public interface InfoExtraProveedorRepository extends JpaRepository<InfoExtraProveedor, Long> {

    // Obtener todas las entradas de InfoExtraProveedor
    @Query("SELECT i FROM InfoExtraProveedor i")
    List<InfoExtraProveedor> obtenerTodaInfoExtraProveedor();

    // Obtener una entrada por ID
    @Query("SELECT i FROM InfoExtraProveedor i WHERE i.idInfoExtraProveedor = :id")
    InfoExtraProveedor obtenerInfoExtraProveedorPorId(@Param("id") Long id);

    // Insertar una nueva entrada
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO INFOEXTRAPROVEEDOR (CANTIDADEXISTENCIAS, PROVEEDOR_NIT, PRODUCTO_CODIGOBARRAS) VALUES (:cantidad, :nit, :codigoBarras)", nativeQuery = true)
    void insertarInfoExtraProveedor(@Param("cantidad") Integer cantidad, @Param("nit") Long nit, @Param("codigoBarras") String codigoBarras);

    // Actualizar una entrada
    @Modifying
    @Transactional
    @Query(value = "UPDATE INFOEXTRAPROVEEDOR SET CANTIDADEXISTENCIAS = :cantidad, PROVEEDOR_NIT = :nit, PRODUCTO_CODIGOBARRAS = :codigoBarras WHERE IDINFOEXTRAPROVEEDOR = :id", nativeQuery = true)
    void actualizarInfoExtraProveedor(@Param("id") Long id, @Param("cantidad") Integer cantidad, @Param("nit") Long nit, @Param("codigoBarras") String codigoBarras);

    // Eliminar una entrada
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM INFOEXTRAPROVEEDOR WHERE IDINFOEXTRAPROVEEDOR = :id", nativeQuery = true)
    void eliminarInfoExtraProveedor(@Param("id") Long id);
}
