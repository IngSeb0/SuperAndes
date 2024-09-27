package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.parranderos.modelo.Sucursal;

import java.util.Collection;

public interface SucursalRepository extends JpaRepository<Sucursal, Long> {

    @Query(value = "SELECT * FROM SUCURSAL", nativeQuery = true)
    Collection<Sucursal> obtenerTodasLasSucursales();

    @Query(value = "SELECT * FROM SUCURSAL WHERE IDSUCURSAL = :id", nativeQuery = true)
    Sucursal obtenerSucursalPorId(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO SUCURSAL (NOMBRE, TAMAÑOBLOSTABLACION, DIRECCION, TELEFONO) VALUES (:nombre, :tamañoBloque, :direccion, :telefono)", nativeQuery = true)
    void insertarSucursal(@Param("nombre") String nombre, @Param("tamañoBloque") String tamañoBloque, @Param("direccion") String direccion, @Param("telefono") String telefono);

    @Modifying
    @Transactional
    @Query(value = "UPDATE SUCURSAL SET NOMBRE = :nombre, TAMAÑOBLOSTABLACION = :tamañoBloque, DIRECCION = :direccion, TELEFONO = :telefono WHERE IDSUCURSAL = :id", nativeQuery = true)
    void actualizarSucursal(@Param("id") Long id, @Param("nombre") String nombre, @Param("tamañoBloque") String tamañoBloque, @Param("direccion") String direccion, @Param("telefono") String telefono);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM SUCURSAL WHERE IDSUCURSAL = :id", nativeQuery = true)
    void eliminarSucursal(@Param("id") Long id);
}
