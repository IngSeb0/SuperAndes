package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.parranderos.modelo.InfoExtraOrden;

import java.util.List;

public interface InfoExtraOrdenRepository extends JpaRepository<InfoExtraOrden, Long> {

    // Obtener toda la información extra de las órdenes
    @Query("SELECT i FROM InfoExtraOrden i")
    List<InfoExtraOrden> obtenerTodaLaInfoExtraOrden();

    // Obtener una información extra por ID
    @Query("SELECT i FROM InfoExtraOrden i WHERE i.id = :id")
    InfoExtraOrden obtenerInfoExtraOrdenPorId(@Param("id") Long id);

    // Insertar nueva información extra
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO info_extra_orden (cantidad, costo_unitario) VALUES (:cantidad, :costoUnitario)", nativeQuery = true)
    void insertarInfoExtraOrden(@Param("cantidad") Integer cantidad, @Param("costoUnitario") Float costoUnitario);

    // Actualizar información extra por ID
    @Modifying
    @Transactional
    @Query(value = "UPDATE info_extra_orden SET cantidad = :cantidad, costo_unitario = :costoUnitario WHERE id = :id", nativeQuery = true)
    void actualizarInfoExtraOrden(@Param("id") Long id, @Param("cantidad") Integer cantidad, @Param("costoUnitario") Float costoUnitario);

    // Eliminar una información extra por ID
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM info_extra_orden WHERE id = :id", nativeQuery = true)
    void eliminarInfoExtraOrden(@Param("id") Long id);
}
