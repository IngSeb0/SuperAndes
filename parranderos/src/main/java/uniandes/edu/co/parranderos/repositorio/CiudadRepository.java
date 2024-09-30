package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.parranderos.modelo.Ciudad;

import java.util.List;

public interface CiudadRepository extends JpaRepository<Ciudad, Long> {

    // Obtener todas las ciudades
    @Query("SELECT c FROM Ciudad c")
    List<Ciudad> obtenerTodasLasCiudades();

    // Obtener una ciudad por ID
    @Query("SELECT c FROM Ciudad c WHERE c.codigoCiudad = :codigoCiudad")
    Ciudad obtenerCiudadPorId(@Param("codigoCiudad") Long codigoCiudad);

    // Insertar una nueva ciudad
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO CIUDAD (CODIGOCIUDAD, NOMBRECIUDAD) VALUES (:codigoCiudad, :nombreCiudad)", nativeQuery = true)
    void insertarCiudad(@Param("codigoCiudad") Long codigoCiudad, @Param("nombreCiudad") String nombreCiudad);
    

    // Actualizar una ciudad por ID
    @Modifying
    @Transactional
    @Query(value = "UPDATE CIUDAD SET NOMBRECIUDAD = :nombreCiudad WHERE CODIGOCIUDAD = :codigoCiudad", nativeQuery = true)
    void actualizarCiudad(@Param("codigoCiudad") Long codigoCiudad, @Param("nombreCiudad") String nombreCiudad);

    // Eliminar una ciudad por ID
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM CIUDAD WHERE CODIGOCIUDAD = :codigoCiudad", nativeQuery = true)
    void eliminarCiudad(@Param("codigoCiudad") Long codigoCiudad);
}
