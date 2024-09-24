package uniandes.edu.co.parranderos.repositorio;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.parranderos.modelo.Sucursal;

import java.util.Collection;

public interface SucursalRepository extends JpaRepository<Sucursal, Long> {

    // Consultar todas las sucursales
    @Query(value = "SELECT * FROM sucursales", nativeQuery = true)
    Collection<Sucursal> obtenerTodasLasSucursales();

    // Consultar una sucursal por su ID
    @Query(value = "SELECT * FROM sucursales WHERE id = :id", nativeQuery = true)
    Sucursal obtenerSucursalPorId(@Param("id") Long id);

    // Insertar una nueva sucursal
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO sucursales (nombre, tamaño, direccion, telefono) " +
            "VALUES (:nombre, :tamaño, :direccion, :telefono)", nativeQuery = true)
    void insertarSucursal(@Param("nombre") String nombre,
                          @Param("tamaño") Float tamaño,
                          @Param("direccion") String direccion,
                          @Param("telefono") Integer telefono);

    // Actualizar una sucursal por su ID
    @Modifying
    @Transactional
    @Query(value = "UPDATE sucursales SET nombre = :nombre, tamaño = :tamaño WHERE id = :id", nativeQuery = true)
    void actualizarSucursal(@Param("id") Long id,
                            @Param("nombre") String nombre,
                            @Param("tamaño") Float tamaño);

    // Eliminar una sucursal por su ID
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM sucursales WHERE id = :id", nativeQuery = true)
    void eliminarSucursal(@Param("id") Long id);
}
