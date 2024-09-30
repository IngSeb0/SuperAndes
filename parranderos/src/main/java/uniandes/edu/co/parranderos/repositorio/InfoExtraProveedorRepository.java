package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.parranderos.modelo.InfoExtraProveedor;
import uniandes.edu.co.parranderos.modelo.InfoExtraProveedorPk;

import java.util.List;

public interface InfoExtraProveedorRepository extends JpaRepository<InfoExtraProveedor, InfoExtraProveedorPk> {

    // Obtener todas las entradas de InfoExtraProveedor
    @Query("SELECT i FROM InfoExtraProveedor i")
    List<InfoExtraProveedor> obtenerTodaInfoExtraProveedor();

    // Obtener una entrada por la clave primaria compuesta (proveedorNit y productoCodigoBarras)
    @Query("SELECT i FROM InfoExtraProveedor i WHERE i.pk.nit = :nit AND i.pk.CodigoBarras = :codigoBarras")
    InfoExtraProveedor obtenerInfoExtraProveedorPorId(@Param("nit") Long nit, @Param("codigoBarras") String codigoBarras);

    // Insertar una nueva entrada con clave primaria compuesta
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO INFOEXTRAPROVEEDOR (CANTIDADEXISTENCIAS, NIT, CODIGOBARRAS) VALUES (:cantidad, :nit, :codigoBarras)", nativeQuery = true)
    void insertarInfoExtraProveedor(@Param("cantidad") Integer cantidad, @Param("nit") Long nit, @Param("codigoBarras") String codigoBarras);

    // Actualizar una entrada existente por clave primaria compuesta
    @Modifying
    @Transactional
    
    @Query(value = "UPDATE INFOEXTRAPROVEEDOR SET CANTIDADEXISTENCIAS = :cantidad WHERE NIT = :nit AND CODIGOBARRAS = :codigoBarras", nativeQuery = true)
    void actualizarInfoExtraProveedor(@Param("cantidad") Integer cantidad, @Param("nit") Long nit, @Param("codigoBarras") String codigoBarras);

    // Eliminar una entrada por clave primaria compuesta
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM INFOEXTRAPROVEEDOR WHERE NIT = :nit AND CODIGOBARRAS = :codigoBarras", nativeQuery = true)
    void eliminarInfoExtraProveedor(@Param("nit") Long nit, @Param("codigoBarras") String codigoBarras);
}
