package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.parranderos.modelo.Bodega;
import uniandes.edu.co.parranderos.modelo.BodegaPK;

import java.util.Collection;

public interface BodegaRepository extends JpaRepository<Bodega, BodegaPK> {

    @Query(value = "SELECT * FROM BODEGA", nativeQuery = true)
    Collection<Bodega> darBodegas();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO BODEGA (SUCURSAL_IDSUCURSAL, INFOEXTRABODEGA_ID, NOMBREBODEGA, TAMANIOBODEGA) VALUES (:sucursalId, :infoExtraId, :nombre, :tamano)", nativeQuery = true)
    void insertarBodega(@Param("sucursalId") Long sucursalId, @Param("nombre") String nombreBodega, @Param("tamano") Float tamanioBodega);

    @Modifying
    @Transactional
    @Query(value = "UPDATE BODEGA SET NOMBREBODEGA = :nombreBodega, TAMANIOBODEGA = :tamano WHERE SUCURSAL_IDSUCURSAL = :sucursalId AND INFOEXTRABODEGA_ID = :infoExtraId", nativeQuery = true)
    void actualizarBodega(@Param("sucursalId") Long sucursalId, @Param("nombreBodega") String nombreBodega, @Param("tamano") Float tamanioBodega);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM BODEGA WHERE SUCURSAL_IDSUCURSAL = :sucursalId AND INFOEXTRABODEGA_ID = :infoExtraId", nativeQuery = true)
    void eliminarBodega(@Param("sucursalId") Long sucursalId);
}
