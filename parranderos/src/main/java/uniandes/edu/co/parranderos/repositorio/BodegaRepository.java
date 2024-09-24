package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.parranderos.modelo.Bodega;

import java.util.List;

public interface BodegaRepository extends JpaRepository<Bodega, Long> {

    // Obtener todas las bodegas
    @Query("SELECT b FROM Bodega b")
    List<Bodega> obtenerTodasLasBodegas();

    // Obtener una bodega por ID
    @Query("SELECT b FROM Bodega b WHERE b.id = :id")
    Bodega obtenerBodegaPorId(@Param("id") Long id);

    // Insertar una nueva bodega
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO bodega (nombre, tamaño, sucursal_id) VALUES (:nombre, :tamano, :sucursalId)", nativeQuery = true)
    void insertarBodega(@Param("nombre") String nombre, @Param("tamano") Float tamano, @Param("sucursalId") Long sucursalId);

    // Actualizar una bodega por ID
    @Modifying
    @Transactional
    @Query(value = "UPDATE bodega SET nombre = :nombre, tamaño = :tamano WHERE id = :id", nativeQuery = true)
    void actualizarBodega(@Param("id") Long id, @Param("nombre") String nombre, @Param("tamano") Float tamano);

    // Eliminar una bodega por ID
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM bodega WHERE id = :id", nativeQuery = true)
    void eliminarBodega(@Param("id") Long id);
}
