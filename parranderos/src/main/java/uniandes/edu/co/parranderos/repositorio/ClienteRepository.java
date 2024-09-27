package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.parranderos.modelo.Cliente;

import java.util.Collection;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {  

    @Query(value = "SELECT * FROM CLIENTE", nativeQuery = true)
    Collection<Cliente> obtenerTodosLosClientes();
    
    @Query(value = "SELECT * FROM CLIENTE WHERE cedula = :cedula", nativeQuery = true)
    Cliente obtenerClientePorCedula(@Param("cedula") Long cedula);
    
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO CLIENTE (cedula, nombreCliente) " +
            "VALUES (:cedula, :nombreCliente)", nativeQuery = true)
    void insertarCliente(@Param("cedula") Long cedula,
                         @Param("nombreCliente") String nombreCliente);
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE CLIENTE SET nombreCliente = :nombreCliente WHERE cedula = :cedula", nativeQuery = true)
    void actualizarCliente(@Param("cedula") Long cedula,  
                           @Param("nombreCliente") String nombreCliente);
    
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM CLIENTE WHERE cedula = :cedula", nativeQuery = true)
    void eliminarCliente(@Param("cedula") Long cedula);
    
}
