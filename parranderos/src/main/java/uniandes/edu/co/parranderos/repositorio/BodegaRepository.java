package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.parranderos.modelo.Bodega;
import uniandes.edu.co.parranderos.repositorio.InfoExtraBodegaRepository.IndiceOcupacionBodegaInfo;

import java.util.Collection;
import java.util.Map;

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
                        @Query(value = "DELETE FROM BODEGA WHERE IDBODEGA = :id", nativeQuery = true)
                        void eliminarBodega(@Param("id") Long id);

                        @Query(value = "SELECT ie.CODIGOBARRAS AS codigoBarras, " +
                        "ie.TOTALEXISTENCIAS AS totalExistencias, " +
                       
                        "ie.COSTOPROMEDIO AS costoPromedio, " +
                        "ie.CAPACIDADALMACENAMIENTO AS capacidadAlmacenamiento, " +
                        "ie.NIVELMINIMO AS nivelMinimo " +
                        "FROM INFOEXTRABODEGA ie " +
                        "WHERE ie.IDBODEGA = :idBodega", 
                nativeQuery = true)
         Collection<Map<String, Object>> obtenerInventarioProductosBodega(@Param("idBodega") Long idBodega);
    @Query(value = "SELECT b.NOMBREBODEGA AS nombreBodega, " +
                   "(SUM(p.CANTIDADPRESENTACION * ie.TOTALEXISTENCIAS) / b.TAMANIOBODEGA) * 100 AS porcentajeOcupacion " +
                   "FROM BODEGA b " +
                   "INNER JOIN INFOEXTRABODEGA ie ON b.IDBODEGA = ie.IDBODEGA " +
                   "INNER JOIN PRODUCTO p ON p.CODIGOBARRAS = ie.CODIGOBARRAS " +
                   "WHERE b.IDSUCURSAL = :idSucursal " +
                   "AND p.CODIGOBARRAS IN (:productos) " +
                   "GROUP BY b.NOMBREBODEGA, b.TAMANIOBODEGA",
           nativeQuery = true)
    Collection<IndiceOcupacionBodegaInfo> obtenerIndiceOcupacionBodega(
            @Param("idSucursal") Long idSucursal,
            @Param("productos") Collection<String> productos);
}
