package uniandes.edu.co.parranderos.repositorio;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uniandes.edu.co.parranderos.modelo.Ciudad;

import java.util.Collection;

public interface CiudadRepository extends JpaRepository<Ciudad, String> {

    // Consultar todas las ciudades
    @Query(value = "SELECT * FROM ciudades", nativeQuery = true)
    Collection<Ciudad> obtenerTodasLasCiudades();

    // Consultar una ciudad por su código
    @Query(value = "SELECT * FROM ciudades WHERE codigo = :codigo", nativeQuery = true)
    Ciudad obtenerCiudadPorCodigo(@Param("codigo") String codigo);
}
