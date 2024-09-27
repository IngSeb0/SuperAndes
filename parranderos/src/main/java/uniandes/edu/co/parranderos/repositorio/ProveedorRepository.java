package uniandes.edu.co.parranderos.repositorio;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.parranderos.modelo.Proveedor;

import java.util.Collection;

public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {

    // Consultar todos los proveedores
    @Query(value = "SELECT * FROM proveedores", nativeQuery = true)
    Collection<Proveedor> obtenerTodosLosProveedores();

    // Consultar un proveedor por su NIT
    @Query(value = "SELECT * FROM proveedores WHERE nit = :nit", nativeQuery = true)
    Proveedor obtenerProveedorPorNit(@Param("nit") Long nit);

    // Insertar un nuevo proveedor
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO proveedores (nit, nombre, direccion, nombre_contacto, telefono_contacto) " +
            "VALUES (:nit, :nombre, :direccion, :nombreContacto, :telefonoContacto)", nativeQuery = true)
    void insertarProveedor(@Param("nit") Long nit,
                           @Param("nombre") String nombre,
                           @Param("direccion") String direccion,
                           @Param("nombreContacto") String nombreContacto,
                           @Param("telefonoContacto") String telefonoContacto);

    // Actualizar un proveedor por su NIT
    @Modifying
    @Transactional
    @Query(value = "UPDATE proveedores SET nombre = :nombre, direccion = :direccion, telefono = :telefono, nombre_contacto = :nombreContacto WHERE nit = :nit", nativeQuery = true)
    void actualizarProveedor(@Param("nit") Long nit,
                             @Param("nombre") String nombre,
                             @Param("direccion") String direccion,
                             @Param("telefono") String telefono,
                             @Param("nombreContacto") String nombreContacto);
    

    // Eliminar un proveedor por su NIT
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM proveedores WHERE nit = :nit", nativeQuery = true)
    void eliminarProveedor(@Param("nit") Long nit);
}
