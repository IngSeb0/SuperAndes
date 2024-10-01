package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.parranderos.modelo.Sucursal;

import java.util.Collection;

public interface SucursalRepository extends JpaRepository<Sucursal, Long> {

    @Query(value = "SELECT * FROM SUCURSAL", nativeQuery = true)
    Collection<Sucursal> obtenerTodasLasSucursales();

    @Query(value = "SELECT * FROM SUCURSAL WHERE IDSUCURSAL = :id", nativeQuery = true)
    Sucursal obtenerSucursalPorId(@Param("id") Long id);

    // Insertar una nueva sucursal con relación a la ciudad
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO SUCURSAL (IDSUCURSAL, NOMBRESUCURSAL, TAMANIOINSTALACION, DIRECCION, TELEFONO, CODIGOCIUDAD) " +
                   "VALUES (:idSucursal, :nombreSucursal, :tamanioInstalacion, :direccion, :telefono, :codigoCiudad)", nativeQuery = true)
    void insertarSucursal(@Param("idSucursal") Long idSucursal,
                          @Param("nombreSucursal") String nombreSucursal,
                          @Param("tamanioInstalacion") String tamanioInstalacion,
                          @Param("direccion") String direccion,
                          @Param("telefono") String telefono,
                          @Param("codigoCiudad") Long codigoCiudad);
    

    // Actualizar una sucursal existente
    @Modifying
    @Transactional
    @Query(value = "UPDATE SUCURSAL SET NOMBRESUCURSAL = :nombreSucursal, TAMANIOINSTALACION = :tamanioInstalacion, " +
            "DIRECCION = :direccion, TELEFONO = :telefono, CODIGOCIUDAD = :codigoCiudad WHERE IDSUCURSAL = :id", nativeQuery = true)
    void actualizarSucursal(@Param("id") Long id,
                            @Param("nombreSucursal") String nombreSucursal,
                            @Param("tamanioInstalacion") String tamanioInstalacion,
                            @Param("direccion") String direccion,
                            @Param("telefono") String telefono,
                            @Param("codigoCiudad") Long codigoCiudad);
                                // Obtener sucursales que tengan un producto específico por código de barras
    @Query(value = "SELECT s.* FROM SUCURSAL s " +
    "INNER JOIN BODEGA b ON s.IDSUCURSAL = b.IDSUCURSAL " +
    "INNER JOIN INFOEXTRABODEGA ie ON b.IDBODEGA = ie.IDBODEGA " +
    "INNER JOIN PRODUCTO p ON ie.IDPRODUCTO = p.IDPRODUCTO " +
    "WHERE p.CODIGOBARRAS = :codigoBarras", nativeQuery = true)
Collection<Sucursal> obtenerSucursalesConProducto(@Param("codigoBarras") String codigoBarras);
}
