package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.parranderos.modelo.DetalleCostoBodega;

import java.util.List;

public interface DetalleCostoBodegaRepository extends JpaRepository<DetalleCostoBodega, Long> {

    // Obtener todos los detalles de costo de bodegas
    @Query("SELECT d FROM DetalleCostoBodega d")
    List<DetalleCostoBodega> obtenerTodosLosDetallesCosto();

    // Obtener un detalle por ID
    @Query("SELECT d FROM DetalleCostoBodega d WHERE d.id = :id")
    DetalleCostoBodega obtenerDetallePorId(@Param("id") Long id);

    // Insertar un nuevo detalle
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO detalle_costo_bodega (costo, cantidad) VALUES (:costo, :cantidad)", nativeQuery = true)
    void insertarDetalle(@Param("costo") Float costo, @Param("cantidad") Integer cantidad);

    // Actualizar un detalle por ID
    @Modifying
    @Transactional
    @Query(value = "UPDATE detalle_costo_bodega SET costo = :costo, cantidad = :cantidad WHERE id = :id", nativeQuery = true)
    void actualizarDetalle(@Param("id") Long id, @Param("costo") Float costo, @Param("cantidad") Integer cantidad);

    // Eliminar un detalle por ID
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM detalle_costo_bodega WHERE id = :id", nativeQuery = true)
    void eliminarDetalle(@Param("id") Long id);
}
