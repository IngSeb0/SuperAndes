package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.parranderos.modelo.Venta;

import java.util.Collection;

public interface VentaRepository extends JpaRepository<Venta, Long> {

    // Obtener todas las ventas
    @Query("SELECT v FROM Venta v")
    Collection<Venta> obtenerTodasLasVentas();

    // Obtener una venta por su ID
    @Query("SELECT v FROM Venta v WHERE v.id = :id")
    Venta obtenerVentaPorId(@Param("id") Long id);

    // Insertar una nueva venta
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO venta (id, fecha, cliente_id) VALUES (:id, :fecha, :clienteId)", nativeQuery = true)
    void insertarVenta(@Param("id") Long id, @Param("fecha") String fecha, @Param("clienteId") Long clienteId);

    // Actualizar una venta por su ID
    @Modifying
    @Transactional
    @Query(value = "UPDATE venta SET fecha = :fecha, cliente_id = :clienteId WHERE id = :id", nativeQuery = true)
    void actualizarVenta(@Param("id") Long id, @Param("fecha") String fecha, @Param("clienteId") Long clienteId);

    // Eliminar una venta por su ID
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM venta WHERE id = :id", nativeQuery = true)
    void eliminarVenta(@Param("id") Long id);
}
