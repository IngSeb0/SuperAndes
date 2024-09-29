package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.parranderos.modelo.InfoExtraOrden;
import uniandes.edu.co.parranderos.modelo.InfoExtraOrdenPk;

import java.util.Collection;

public interface InfoExtraOrdenRepository extends JpaRepository<InfoExtraOrden, InfoExtraOrdenPk> {

    @Query(value = "SELECT * FROM infoextraorden", nativeQuery = true)
    Collection<InfoExtraOrden> obtenerTodaLaInfoExtraOrden();

    @Query(value = "SELECT * FROM infoextraorden WHERE ordencompra_idorden = :ordenId AND producto_codigobarras = :productoId", nativeQuery = true)
    InfoExtraOrden obtenerInfoExtraOrdenPorId(@Param("ordenId") Long ordenId, @Param("productoId") Long productoId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM infoextraorden WHERE ordencompra_idorden = :ordenId AND producto_codigobarras = :productoId", nativeQuery = true)
    void eliminarInfoExtraOrden(@Param("ordenId") Long ordenId, @Param("productoId") Long productoId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE infoextraorden SET cantidad = :cantidad, costounitario = :costoUnitario WHERE ordencompra_idorden = :ordenId AND producto_codigobarras = :productoId", nativeQuery = true)
    void actualizarInfoExtraOrden(@Param("ordenId") Long ordenId,
                                  @Param("productoId") Long productoId,
                                  @Param("cantidad") Long cantidad,
                                  @Param("costoUnitario") Float costoUnitario);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO infoextraorden (ordencompra_idorden, producto_codigobarras, cantidad, costounitario) VALUES (:ordenId, :productoId, :cantidad, :costoUnitario)", nativeQuery = true)
    void insertarInfoExtraOrden(@Param("ordenId") Long ordenId,
                                @Param("productoId") Long productoId,
                                @Param("cantidad") Long cantidad,
                                @Param("costoUnitario") Float costoUnitario);
}
