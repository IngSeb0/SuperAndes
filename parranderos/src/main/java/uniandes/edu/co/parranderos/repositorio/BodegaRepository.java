package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.parranderos.modelo.Bodega;
import java.util.Collection;
import java.util.List;
public interface BodegaRepository extends JpaRepository<Bodega, Long> {

    @Query(value = "SELECT * FROM BODEGA", nativeQuery = true)
    Collection<Bodega> darBodegas();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO BODEGA (IDSUCURSAL, NOMBREBODEGA, TAMANIOBODEGA) VALUES (:idSucursal, :nombre, :tamano)", nativeQuery = true)
    void insertarBodega(@Param("sucursalId") Long idSucursal, @Param("nombre") String nombreBodega, @Param("tamano") Float tamanioBodega);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM BODEGA WHERE IDSUCURSAL = :idSucursal", nativeQuery = true)
    void eliminarBodega(@Param("sucursalId") Long sucursalId);
}
