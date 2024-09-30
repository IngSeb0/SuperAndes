package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.parranderos.modelo.Cliente;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // Obtener todos los clientes
    @Query(value = "SELECT * FROM CLIENTE", nativeQuery = true)
    List<Cliente> obtenerTodosLosClientes();

    // Obtener cliente por cédula
    @Query(value = "SELECT * FROM CLIENTE WHERE CEDULA = :cedula", nativeQuery = true)
    Cliente obtenerClientePorCedula(@Param("cedula") Long cedula);

    // Insertar un nuevo cliente
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO CLIENTE (CEDULA, NOMBRECLIENTE) VALUES (:cedula, :nombreCliente)", nativeQuery = true)
    void insertarCliente(@Param("cedula") Long cedula, @Param("nombreCliente") String nombreCliente);

    // Actualizar un cliente
    @Modifying
    @Transactional
    @Query(value = "UPDATE CLIENTE SET NOMBRECLIENTE = :nombreCliente WHERE CEDULA = :cedula", nativeQuery = true)
    void actualizarCliente(@Param("cedula") Long cedula, @Param("nombreCliente") String nombreCliente);

    // Eliminar un cliente por cédula
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM CLIENTE WHERE CEDULA = :cedula", nativeQuery = true)
    void eliminarCliente(@Param("cedula") Long cedula);
}
