package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.parranderos.modelo.InfoExtraProveedor;

import java.util.List;

public interface InfoExtraProveedorRepository extends JpaRepository<InfoExtraProveedor, Long> {

    // Obtener toda la información extra de los proveedores
    @Query("SELECT i FROM InfoExtraProveedor i")
    List<InfoExtraProveedor> obtenerTodaLaInfoExtraProveedor();

    // Obtener una información extra por ID
    @Query("SELECT i FROM InfoExtraProveedor i WHERE i.id = :id")
    InfoExtraProveedor obtenerInfoExtraProveedorPorId(@Param("id") Long id);

    // Insertar nueva información extra
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO info_extra_proveedor (cantidad_existencia) VALUES (:cantidadExistencia)", nativeQuery = true)
    void insertarInfoExtraProveedor(@Param("cantidadExistencia") Integer cantidadExistencia);

    // Actualizar información extra por ID
    @Modifying
    @Transactional
    @Query(value = "UPDATE info_extra_proveedor SET cantidad_existencia = :cantidadExistencia WHERE id = :id", nativeQuery = true)
    void actualizarInfoExtraProveedor(@Param("id") Long id, @Param("cantidadExistencia") Integer cantidadExistencia);

    // Eliminar una información extra por ID
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM info_extra_proveedor WHERE id = :id", nativeQuery = true)
    void eliminarInfoExtraProveedor(@Param("id") Long id);
}
