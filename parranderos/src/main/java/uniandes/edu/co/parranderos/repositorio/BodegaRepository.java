package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.parranderos.modelo.Bodega;
import uniandes.edu.co.parranderos.repositorio.InfoExtraBodegaRepository.IndiceOcupacionBodegaInfo;

import java.util.Collection;

public interface BodegaRepository extends JpaRepository<Bodega, Long> {

    @Query(value = "SELECT * FROM BODEGA", nativeQuery = true)
    Collection<Bodega> darBodegas();
    public interface InventarioBodegaInfo {
        String getNombreProducto();
        int getCantidadActual();
        int getCantidadMinima();
        double getCostoPromedio();
    }
    public interface IndiceOcupacionBodegaInfo {
        String getNombreBodega();
        double getPorcentajeOcupacion();
    }
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO BODEGA (IDBODEGA, IDSUCURSAL, NOMBREBODEGA, TAMANIOBODEGA) " +
                   "VALUES (:idBodega, :idSucursal, :nombre, :tamano)", nativeQuery = true)
    void insertarBodega(@Param("idBodega") Long idBodega, 
                        @Param("idSucursal") Long idSucursal, 
                        @Param("nombre") String nombreBodega, 
                        @Param("tamano") Float tamanioBodega);

                
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM BODEGA WHERE IDBODEGA = :idBodega", nativeQuery = true)
    void eliminarBodega(@Param("idBodega") Long idBodega);

    @Query(value = "SELECT p.NOMBRE AS nombreProducto, " +
                   "ie.TOTALEXISTENCIAS AS cantidadActual, " +
                   "ie.CANTIDADMINIMA AS cantidadMinima, " +
                   "AVG(ie.COSTOUNITARIO) AS costoPromedio " +
                   "FROM INFOEXTRABODEGA ie " +
                   "INNER JOIN PRODUCTO p ON ie.CODIGOBARRAS = p.CODIGOBARRAS " +
                   "WHERE ie.IDBODEGA = :idBodega " +
                   "GROUP BY p.NOMBRE, ie.TOTALEXISTENCIAS, ie.CANTIDADMINIMA", nativeQuery = true)
    Collection<InventarioBodegaInfo> obtenerInventarioProductosBodega(@Param("idBodega") Long idBodega);
  @Query(value = "SELECT b.NOMBREBODEGA AS nombreBodega, " +
                   "(SUM(p.CANTIDADPRESENTACION * ie.TOTALEXISTENCIAS) / b.TAMANIOBODEGA) * 100 AS porcentajeOcupacion " +
                   "FROM BODEGA b " +
                   "INNER JOIN INFOEXTRABODEGA ie ON b.IDBODEGA = ie.IDBODEGA " +
                   "INNER JOIN PRODUCTO p ON ie.CODIGOBARRAS = p.CODIGOBARRAS " +
                   "WHERE b.IDSUCURSAL = :idSucursal " +
                   "AND p.CODIGOBARRAS IN :productos " +
                   "GROUP BY b.NOMBREBODEGA, b.TAMANIOBODEGA", nativeQuery = true)
    Collection<IndiceOcupacionBodegaInfo> obtenerIndiceOcupacionBodega(@Param("idSucursal") Long idSucursal, @Param("productos") Collection<String> productos);
}
