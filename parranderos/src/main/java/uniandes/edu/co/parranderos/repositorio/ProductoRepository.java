package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.parranderos.modelo.Producto;
import java.lang.Long;
import java.sql.Date;
import java.util.Collection;
import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, String> {

    // Consultar todos los productos
    @Query(value = "SELECT * FROM producto", nativeQuery = true)
    Collection<Producto> obtenerTodosLosProductos();

    // Consultar un producto por su código de barras
    @Query("SELECT p FROM Producto p WHERE p.codigoBarras = :codigoBarras")
    Optional<Producto> obtenerProductoPorCodigoBarras(@Param("codigoBarras") String codigoBarras);
    @Query("SELECT p FROM Producto p WHERE p.codigoBarras = :codigoBarras")
    Producto obtenerProductoPorCodigoBarras1(@Param("codigoBarras") String codigoBarras);

    // Insertar un nuevo producto
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO producto (codigoBarras, nombre, precioUnitarioVenta, presentacion, cantidadPresentacion, unidadMedida, fechaExpiracion) " +
            "VALUES (:codigoBarras, :nombre, :precioUnitarioVenta, :presentacion, :cantidadPresentacion, :unidadMedida, :fechaExpiracion)", nativeQuery = true)
    void insertarProducto(@Param("codigoBarras") String codigoBarras,
                          @Param("nombre") String nombre,
                          @Param("precioUnitarioVenta") Float precioUnitarioVenta,
                          @Param("presentacion") String presentacion,
                          @Param("cantidadPresentacion") Integer cantidadPresentacion,
                          @Param("unidadMedida") String unidadMedida,
                          @Param("fechaExpiracion") Date fechaExpiracion);

    // Actualizar un producto por su código de barras
    @Modifying
    @Transactional
    @Query(value = "UPDATE producto SET nombre = :nombre, precioUnitarioVenta = :precioUnitarioVenta WHERE codigoBarras = :codigoBarras", nativeQuery = true)
    void actualizarProducto(@Param("codigoBarras") String codigoBarras,
                            @Param("nombre") String nombre,
                            @Param("precioUnitarioVenta") Float precioUnitarioVenta);

    // Eliminar un producto por su código de barras
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM producto WHERE codigo_barras = :codigoBarras", nativeQuery = true)
    void eliminarProducto(@Param("codigoBarras") String codigoBarras);
    @Query(value = "SELECT p.* FROM PRODUCTO p " +
    "LEFT JOIN SUCURSAL s ON p.IDSUCURSAL = s.IDSUCURSAL " +
    "LEFT JOIN CATEGORIA c ON p.IDCATEGORIA = c.IDCATEGORIA " +
    "WHERE (:precioMin IS NULL OR p.precioUnitarioVenta >= :precioMin) " +
    "AND (:precioMax IS NULL OR p.precioUnitarioVenta <= :precioMax) " +
    "AND (:fechaVencimiento IS NULL OR p.fechaExpiracion <= :fechaVencimiento) " +
    "AND (:idSucursal IS NULL OR s.IDSUCURSAL = :idSucursal) " +
    "AND (:idCategoria IS NULL OR c.IDCATEGORIA = :idCategoria)", nativeQuery = true)
Collection<Producto> filtrarProductos(@Param("precioMin") Float precioMin,
                           @Param("precioMax") Float precioMax,
                           @Param("fechaVencimiento") String fechaVencimiento,
                           @Param("idSucursal") Long idSucursal,
                           @Param("idCategoria") Long idCategoria);
}

