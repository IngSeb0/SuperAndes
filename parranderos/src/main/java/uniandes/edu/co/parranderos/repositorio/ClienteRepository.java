package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.parranderos.modelo.Cliente;

import java.util.Collection;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {  

    // Consultar todos los clientes
    @Query(value = "SELECT * FROM clientes", nativeQuery = true)
    Collection<Cliente> obtenerTodosLosClientes();

    // Consultar un cliente por su cédula
    @Query(value = "SELECT * FROM clientes WHERE cedula = :cedula", nativeQuery = true)
    Cliente obtenerClientePorCedula(@Param("cedula") Long cedula);  
    // Insertar un nuevo cliente
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO clientes (cedula, nombre) " +
            "VALUES (:cedula, :nombre)", nativeQuery = true)
    void insertarCliente(@Param("cedula") Long cedula,
                         @Param("nombre") String nombre);

    // Actualizar un cliente por su cédula
    @Modifying
    @Transactional
    @Query(value = "UPDATE clientes SET nombre = :nombre WHERE cedula = :cedula", nativeQuery = true)
    void actualizarCliente(@Param("cedula") Long cedula,  
                           @Param("nombre") String nombre);

    // Eliminar un cliente por su cédula
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM clientes WHERE cedula = :cedula", nativeQuery = true)
    void eliminarCliente(@Param("cedula") Long cedula);  
}
