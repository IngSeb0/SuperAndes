package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.parranderos.modelo.DetalleCostoBodega;

import java.util.List;

public interface DetalleCostoBodegaRepository extends JpaRepository<DetalleCostoBodega, Long> {

    // Obtener todos los detalles de costos de bodega
    @Query("SELECT d FROM DetalleCostoBodega d")
    List<DetalleCostoBodega> obtenerTodosLosDetallesCostoBodega();

    // Obtener un detalle de costo por ID
    @Query("SELECT d FROM DetalleCostoBodega d WHERE d.idDetalleCosto = :idDetalleCosto")
    DetalleCostoBodega obtenerDetalleCostoBodegaPorId(@Param("idDetalleCosto") Long idDetalleCosto);

    // Insertar un nuevo detalle de costo de bodega
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO DETALLECOSTOBODEGA (COSTOUNITARIOBODEGA, CANTIDADEXISTENCIA, INFOEXTRABODEGA_INFOEXTRABODEGA_ID) " +
                   "VALUES (:costoUnitarioBodega, :cantidadExistencia, :infoExtraBodegaId)", nativeQuery = true)
    void insertarDetalleCostoBodega(@Param("costoUnitarioBodega") Float costoUnitarioBodega, 
                                    @Param("cantidadExistencia") Integer cantidadExistencia, 
                                    @Param("infoExtraBodegaId") Long infoExtraBodegaId);

    // Actualizar un detalle de costo por su ID
    @Modifying
    @Transactional
    @Query(value = "UPDATE DETALLECOSTOBODEGA SET COSTOUNITARIOBODEGA = :costoUnitarioBodega, CANTIDADEXISTENCIA = :cantidadExistencia, " +
                   "INFOEXTRABODEGA_INFOEXTRABODEGA_ID = :infoExtraBodegaId WHERE IDDETALLECOSTO = :idDetalleCosto", nativeQuery = true)
    void actualizarDetalleCostoBodega(@Param("idDetalleCosto") Long idDetalleCosto, 
                                      @Param("costoUnitarioBodega") Float costoUnitarioBodega, 
                                      @Param("cantidadExistencia") Integer cantidadExistencia, 
                                      @Param("infoExtraBodegaId") Long infoExtraBodegaId);

    // Eliminar un detalle de costo por su ID
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM DETALLECOSTOBODEGA WHERE IDDETALLECOSTO = :idDetalleCosto", nativeQuery = true)
    void eliminarDetalleCostoBodega(@Param("idDetalleCosto") Long idDetalleCosto);
}
