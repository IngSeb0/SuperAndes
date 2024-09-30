package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.parranderos.modelo.Bodega;

import java.util.Collection;

public interface BodegaRepository extends JpaRepository<Bodega, Long> {

    @Query(value = "SELECT * FROM BODEGA", nativeQuery = true)
    Collection<Bodega> darBodegas();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO BODEGA (IDSUCURSAL, NOMBREBODEGA, TAMANIOBODEGA) VALUES (:idSucursal, :nombre, :tamano)", nativeQuery = true)
    void insertarBodega(@Param("idSucursal") Long idSucursal, @Param("nombre") String nombreBodega, @Param("tamano") Float tamanioBodega);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM BODEGA WHERE IDSUCURSAL = :idSucursal", nativeQuery = true)
    void eliminarBodega(@Param("idSucursal") Long idSucursal);
    @Query(value = "SELECT B.NOMBREBODEGA, " +
            "       (SUM(IE.TOTALEXISTENCIAS * P.CANTIDADPRESENTACION) / B.TAMANIOBODEGA) * 100 AS porcentajeOcupacion " +
            "FROM BODEGA B " +
            "INNER JOIN INFOEXTRABODEGA IE ON B.IDBODEGA = IE.IDBODEGA " +
            "INNER JOIN PRODUCTO P ON IE.CODIGOBARRAS = P.CODIGOBARRAS " +
            "WHERE B.IDSUCURSAL = :idSucursal " +
            "AND P.CODIGOBARRAS IN :productos " +
            "GROUP BY B.NOMBREBODEGA, B.TAMANIOBODEGA", nativeQuery = true)
    Collection<Object[]> obtenerIndiceOcupacionBodega(@Param("idSucursal") Long idSucursal, @Param("productos") Collection<String> productos);
}


