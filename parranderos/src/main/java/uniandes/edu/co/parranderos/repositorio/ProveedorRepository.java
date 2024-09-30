package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.parranderos.modelo.Proveedor;

import java.util.List;

public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {

    // Obtener todos los proveedores
    @Query(value = "SELECT * FROM PROVEEDOR", nativeQuery = true)
    List<Proveedor> obtenerTodosLosProveedores();

    // Obtener proveedor por NIT
    @Query(value = "SELECT * FROM PROVEEDOR WHERE NIT = :nit", nativeQuery = true)
    Proveedor obtenerProveedorPorNit(@Param("nit") Long nit);

    // Insertar un proveedor
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO PROVEEDOR (NIT, NOMBRE, DIRECCION, NOMBRECONTACTO, TELEFONOCONTACTO) " +
                   "VALUES (:nit, :nombre, :direccion, :nombreContacto, :telefonoContacto)", nativeQuery = true)
    void insertarProveedor(@Param("nit") Long nit, @Param("nombre") String nombre, 
                           @Param("direccion") String direccion, @Param("nombreContacto") String nombreContacto, 
                           @Param("telefonoContacto") String telefonoContacto);

    // Actualizar un proveedor
    @Modifying
    @Transactional
    @Query(value = "UPDATE PROVEEDOR SET NOMBRE = :nombre, DIRECCION = :direccion, " +
                   "NOMBRECONTACTO = :nombreContacto, TELEFONOCONTACTO = :telefonoContacto " +
                   "WHERE NIT = :nit", nativeQuery = true)
    void actualizarProveedor(@Param("nit") Long nit, @Param("nombre") String nombre, 
                             @Param("direccion") String direccion, @Param("nombreContacto") String nombreContacto, 
                             @Param("telefonoContacto") String telefonoContacto);

    // Eliminar un proveedor por NIT
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM PROVEEDOR WHERE NIT = :nit", nativeQuery = true)
    void eliminarProveedor(@Param("nit") Long nit);
}
