package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.parranderos.modelo.Ciudad;

import java.util.Collection;

public interface CiudadRepository extends JpaRepository<Ciudad, String> {  

    // Consultar todas las ciudades
    @Query(value = "SELECT * FROM ciudades", nativeQuery = true)
    Collection<Ciudad> obtenerTodasLasCiudades();

    // Consultar una ciudad por su código
    @Query(value = "SELECT * FROM ciudades WHERE codigo = :codigo", nativeQuery = true)
    Ciudad obtenerCiudadPorCodigo(@Param("codigo") Long codigo);

    // Insertar una nueva ciudad
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO ciudades (codigo, nombre) VALUES (:codigo, :nombre)", nativeQuery = true)
    void insertarCiudad(@Param("codigo") Long codigo, @Param("nombre") String nombre);

    // Actualizar una ciudad por su código
    @Modifying
    @Transactional
    @Query(value = "UPDATE ciudades SET nombre = :nombre WHERE id = :codigo", nativeQuery = true)
    void actualizarCiudad(@Param("codigo") Long codigo, @Param("nombre") String nombre);

    // Eliminar una ciudad por su código
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM ciudades WHERE codigo = :codigo", nativeQuery = true)
    void eliminarCiudad(@Param("codigo") Long codigo);
}
