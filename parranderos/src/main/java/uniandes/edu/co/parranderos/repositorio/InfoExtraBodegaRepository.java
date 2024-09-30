package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.parranderos.modelo.InfoExtraBodega;
import uniandes.edu.co.parranderos.modelo.InfoExtraBodegaPk;

import java.util.Collection;
import java.util.List;

public interface InfoExtraBodegaRepository extends JpaRepository<InfoExtraBodega, InfoExtraBodegaPk> {

    @Query(value = "SELECT * FROM infoextrabodega", nativeQuery = true)
    Collection<InfoExtraBodega> obtenerTodaLaInfoExtraBodega();

    @Query(value = "SELECT * FROM infoextrabodega WHERE bodega_idbodega = :bodegaId AND producto_idproducto = :productoId", nativeQuery = true)
    InfoExtraBodega obtenerInfoExtraBodegaPorId(@Param("bodegaId") Long bodegaId, @Param("productoId") String productoId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM infoextrabodega WHERE bodega_idbodega = :bodegaId AND producto_idproducto = :productoId", nativeQuery = true)
    void eliminarInfoExtraBodega(@Param("bodegaId") Long bodegaId, @Param("productoId") String productoId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE infoextrabodega SET totalexistencias = :totalExistencias, costopromedio = :costoPromedio, capacidadalmacenamiento = :capacidadAlmacenamiento, nivelminimo = :nivelMinimo WHERE bodega_idbodega = :bodegaId AND producto_idproducto = :productoId", nativeQuery = true)
    void actualizarInfoExtraBodega(@Param("bodegaId") Long bodegaId,
                                   @Param("productoId") String productoId,
                                   @Param("totalExistencias") Integer totalExistencias,
                                   @Param("costoPromedio") Float costoPromedio,
                                   @Param("capacidadAlmacenamiento") Integer capacidadAlmacenamiento,
                                   @Param("nivelMinimo") Float nivelMinimo);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO infoextrabodega (bodega_idbodega, producto_idproducto, totalexistencias, costopromedio, capacidadalmacenamiento, nivelminimo) VALUES (:bodegaId, :productoId, :totalExistencias, :costoPromedio, :capacidadAlmacenamiento, :nivelMinimo)", nativeQuery = true)
    void insertarInfoExtraBodega(@Param("bodegaId") Long bodegaId,
                                 @Param("productoId") String productoId,
                                 @Param("totalExistencias") Integer totalExistencias,
                                 @Param("costoPromedio") Float costoPromedio,
                                 @Param("capacidadAlmacenamiento") Integer capacidadAlmacenamiento,
                                 @Param("nivelMinimo") Float nivelMinimo);

   
}
