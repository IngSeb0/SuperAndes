package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.parranderos.modelo.Producto;
import java.lang.Long;
import java.util.Collection;
import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    // Consultar todos los productos
    @Query(value = "SELECT * FROM productos", nativeQuery = true)
    Collection<Producto> obtenerTodosLosProductos();

    // Consultar un producto por su código de barras
    @Query("SELECT p FROM Producto p WHERE p.codigoBarras = :codigoBarras")
    Optional<Producto> obtenerProductoPorCodigoBarras(@Param("codigoBarras") String codigoBarras);
    @Query("SELECT p FROM Producto p WHERE p.codigoBarras = :codigoBarras")
    Producto obtenerProductoPorCodigoBarras1(@Param("codigoBarras") String codigoBarras);

    // Insertar un nuevo producto
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO productos (codigo_barras, nombre, precio_unitario_venta, presentacion, cantidad_presentacion, unidad_medida, fecha_expiracion) " +
            "VALUES (:codigoBarras, :nombre, :precioUnitarioVenta, :presentacion, :cantidadPresentacion, :unidadMedida, :fechaExpiracion)", nativeQuery = true)
    void insertarProducto(@Param("codigoBarras") String codigoBarras,
                          @Param("nombre") String nombre,
                          @Param("precioUnitarioVenta") Float precioUnitarioVenta,
                          @Param("presentacion") String presentacion,
                          @Param("cantidadPresentacion") Integer cantidadPresentacion,
                          @Param("unidadMedida") String unidadMedida,
                          @Param("fechaExpiracion") String fechaExpiracion);

    // Actualizar un producto por su código de barras
    @Modifying
    @Transactional
    @Query(value = "UPDATE productos SET nombre = :nombre, precio_unitario_venta = :precioUnitarioVenta WHERE codigo_barras = :codigoBarras", nativeQuery = true)
    void actualizarProducto(@Param("codigoBarras") String codigoBarras,
                            @Param("nombre") String nombre,
                            @Param("precioUnitarioVenta") Float precioUnitarioVenta);

    // Eliminar un producto por su código de barras
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM productos WHERE codigo_barras = :codigoBarras", nativeQuery = true)
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

