package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.parranderos.modelo.EspecificacionEmpacado;

import java.util.List;

public interface EspecificacionEmpacadoRepository extends JpaRepository<EspecificacionEmpacado, Long> {

    // Obtener todas las especificaciones de empacado
    @Query("SELECT e FROM EspecificacionEmpacado e")
    List<EspecificacionEmpacado> obtenerTodasLasEspecificaciones();

    // Obtener una especificación de empacado por ID
    @Query("SELECT e FROM EspecificacionEmpacado e WHERE e.idEspecificacionEmpacado = :id")
    EspecificacionEmpacado obtenerEspecificacionPorId(@Param("id") Long id);

    // Insertar nueva especificación de empacado
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO ESPECIFICACIONEMPACADO (VOLUMEN, PESOGR) VALUES (:volumen, :pesoGr)", nativeQuery = true)
    void insertarEspecificacion(@Param("volumenCm3") Float volumenCm3, @Param("pesoGr") Float pesoGr);

    // Actualizar especificación de empacado por ID
    @Modifying
    @Transactional
    @Query(value = "UPDATE ESPECIFICACIONEMPACADO SET VOLUMEN = :volumen, PESOGR = :pesoGr WHERE IDESPECIFICACIONEMPACADO = :id", nativeQuery = true)
    void actualizarEspecificacion(@Param("id") Long id, @Param("volumenCm3") Float volumen, @Param("pesoGr") Float pesoGr);

    // Eliminar una especificación de empacado por ID
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM ESPECIFICACIONEMPACADO WHERE IDESPECIFICACIONEMPACADO = :id", nativeQuery = true)
    void eliminarEspecificacion(@Param("id") Long id);
}
