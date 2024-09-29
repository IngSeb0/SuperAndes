package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.parranderos.modelo.DetalleCostoBodega;
import uniandes.edu.co.parranderos.modelo.DetalleCostoBodegaPk;
import java.util.List;

public interface DetalleCostoBodegaRepository extends JpaRepository<DetalleCostoBodega, DetalleCostoBodegaPk> {
    
    // Obtener todos los detalles de costos de bodega
    @Query("SELECT d FROM DetalleCostoBodega d")
    List<DetalleCostoBodega> obtenerTodosLosDetallesCostoBodega();

    // Obtener un detalle de costo por clave primaria compuesta (idInfoExtraBodega + idDetalleCosto)
    @Query("SELECT d FROM DetalleCostoBodega d WHERE d.pk.infoExtraBodega.pk.bodega.id = :idBodega AND d.pk.infoExtraBodega.pk.producto.id = :idProducto AND d.pk.idDetalleCosto = :idDetalleCosto")
    DetalleCostoBodega obtenerDetalleCostoBodegaPorId(
        @Param("idBodega") Long idBodega, 
        @Param("idProducto") Long idProducto, 
        @Param("idDetalleCosto") Long idDetalleCosto);

    // Insertar un nuevo detalle de costo de bodega
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO DETALLECOSTOBODEGA (COSTOUNITARIOBODEGA, CANTIDADEXISTENCIA, IDBODEGA, IDPRODUCTO, IDDETALLECOSTO) " +
                   "VALUES (:costoUnitarioBodega, :cantidadExistencia, :bodegaId, :productoId, :idDetalleCosto)", nativeQuery = true)
    void insertarDetalleCostoBodega(@Param("costoUnitarioBodega") Float costoUnitarioBodega, 
                                    @Param("cantidadExistencia") Integer cantidadExistencia, 
                                    @Param("bodegaId") Long idBodega,
                                    @Param("productoId") Long idProducto,
                                    @Param("idDetalleCosto") Long idDetalleCosto);

    // Actualizar un detalle de costo por su clave primaria compuesta
    @Modifying
    @Transactional
    @Query(value = "UPDATE DETALLECOSTOBODEGA SET COSTOUNITARIOBODEGA = :costoUnitarioBodega, CANTIDADEXISTENCIA = :cantidadExistencia " +
                   "WHERE IDBODEGA = :bodegaId AND IDPRODUCTO = :productoId AND IDDETALLECOSTO = :idDetalleCosto", nativeQuery = true)
    void actualizarDetalleCostoBodega(@Param("idDetalleCosto") Long idDetalleCosto, 
                                      @Param("costoUnitarioBodega") Float costoUnitarioBodega, 
                                      @Param("cantidadExistencia") Integer cantidadExistencia, 
                                      @Param("bodegaId") Long idBodega,
                                    @Param("productoId") Long idProducto);

    // Eliminar un detalle de costo por su clave primaria compuesta
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM DETALLECOSTOBODEGA WHERE INFOEXTRABODEGA_BODEGA_ID = :bodegaId AND INFOEXTRABODEGA_PRODUCTO_ID = :productoId AND IDDETALLECOSTO = :idDetalleCosto", nativeQuery = true)
    void eliminarDetalleCostoBodega(@Param("idDetalleCosto") Long idDetalleCosto, 
                                    @Param("bodegaId") Long bodegaId,
                                    @Param("productoId") Long productoId);
}
