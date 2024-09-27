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

    // Obtener una información extra de bodega por ID
    @Query("SELECT i FROM InfoExtraBodega i WHERE i.id = :id")
    InfoExtraBodega obtenerInfoExtraBodegaPorId(@Param("id") Long id);

    // Insertar nueva información extra de bodega
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO infoextrabodegas (TOTALEXISTENCIAS, COSTOPROMEDIO, CAPACIDADALMACENAMIENTO, NIVELMINIMO, BODEGA_IDBODEGA) VALUES (:totalExistencias, :costoPromedio, :capacidadAlmacenamiento, :nivelMinimo, :bodegaIdBodega)", nativeQuery = true)
    void insertarInfoExtraBodega(@Param("totalExistencias") Integer totalExistencias, 
                                 @Param("costoPromedio") Float costoPromedio, 
                                 @Param("capacidadAlmacenamiento") Integer capacidadAlmacenamiento, 
                                 @Param("nivelMinimo") Float nivelMinimo, 
                                 @Param("bodegaIdBodega") Long bodegaIdBodega);

    // Actualizar información extra de bodega por ID
    @Modifying
    @Transactional
    @Query(value = "UPDATE infoextrabodegas SET TOTALEXISTENCIAS = :totalExistencias, COSTOPROMEDIO = :costoPromedio, CAPACIDADALMACENAMIENTO = :capacidadAlmacenamiento, NIVELMINIMO = :nivelMinimo, BODEGA_IDBODEGA = :bodegaIdBodega WHERE IDINFOEXTRABODEGAS = :id", nativeQuery = true)
    void actualizarInfoExtraBodega(@Param("id") Long id, 
                                   @Param("totalExistencias") Integer totalExistencias, 
                                   @Param("costoPromedio") Float costoPromedio, 
                                   @Param("capacidadAlmacenamiento") Integer capacidadAlmacenamiento, 
                                   @Param("nivelMinimo") Float nivelMinimo, 
                                   @Param("bodegaIdBodega") Long bodegaIdBodega);

    // Eliminar una información extra de bodega por ID
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM infoextrabodegas WHERE IDINFOEXTRABODEGAS = :id", nativeQuery = true)
    void eliminarInfoExtraBodega(@Param("id") Long id);
}
