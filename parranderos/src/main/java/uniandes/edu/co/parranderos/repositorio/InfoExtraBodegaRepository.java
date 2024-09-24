package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.parranderos.modelo.InfoExtraBodega;

import java.util.List;

public interface InfoExtraBodegaRepository extends JpaRepository<InfoExtraBodega, Long> {

    // Obtener toda la información extra de las bodegas
    @Query("SELECT i FROM InfoExtraBodega i")
    List<InfoExtraBodega> obtenerTodaLaInfoExtraBodega();

    // Obtener una información extra por ID
    @Query("SELECT i FROM InfoExtraBodega i WHERE i.id = :id")
    InfoExtraBodega obtenerInfoExtraBodegaPorId(@Param("id") Long id);

    // Insertar nueva información extra
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO info_extra_bodega (total_existencia, costo_promedio, capacidad_almacenamiento) VALUES (:totalExistencia, :costoPromedio, :capacidadAlmacenamiento)", nativeQuery = true)
    void insertarInfoExtraBodega(@Param("totalExistencia") Integer totalExistencia, @Param("costoPromedio") Float costoPromedio, @Param("capacidadAlmacenamiento") Integer capacidadAlmacenamiento);

    // Actualizar información extra por ID
    @Modifying
    @Transactional
    @Query(value = "UPDATE info_extra_bodega SET total_existencia = :totalExistencia, costo_promedio = :costoPromedio, capacidad_almacenamiento = :capacidadAlmacenamiento WHERE id = :id", nativeQuery = true)
    void actualizarInfoExtraBodega(@Param("id") Long id, @Param("totalExistencia") Integer totalExistencia, @Param("costoPromedio") Float costoPromedio, @Param("capacidadAlmacenamiento") Integer capacidadAlmacenamiento);

    // Eliminar una información extra por ID
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM info_extra_bodega WHERE id = :id", nativeQuery = true)
    void eliminarInfoExtraBodega(@Param("id") Long id);
}
