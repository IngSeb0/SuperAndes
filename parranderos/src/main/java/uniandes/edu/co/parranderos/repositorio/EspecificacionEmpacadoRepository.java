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

    // Obtener una especificación por ID
    @Query("SELECT e FROM EspecificacionEmpacado e WHERE e.id = :id")
    EspecificacionEmpacado obtenerEspecificacionPorId(@Param("id") Long id);

    // Insertar una nueva especificación
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO especificacion_empacado (volumen, peso) VALUES (:volumen, :peso)", nativeQuery = true)
    void insertarEspecificacion(@Param("volumen") Float volumen, @Param("peso") Float peso);

    // Actualizar una especificación por ID
    @Modifying
    @Transactional
    @Query(value = "UPDATE especificacion_empacado SET volumen = :volumen, peso = :peso WHERE id = :id", nativeQuery = true)
    void actualizarEspecificacion(@Param("id") Long id, @Param("volumen") Float volumen, @Param("peso") Float peso);

    // Eliminar una especificación por ID
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM especificacion_empacado WHERE id = :id", nativeQuery = true)
    void eliminarEspecificacion(@Param("id") Long id);
}
