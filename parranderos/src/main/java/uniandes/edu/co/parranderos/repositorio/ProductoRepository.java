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

                            public interface ProductoCaracteristicasInfo {
                                String getCodigoBarras();
                                String getNombre();
                                Float getPrecioUnitarioVenta();
                                String getPresentacion();
                                Integer getCantidadPresentacion();
                                String getUnidadMedida();
                                Date getFechaExpiracion();
                                String getCategoria();
                                String getSucursal();
                            }
                        
                            // Consulta para obtener productos por características específicas
                            @Query(value = "SELECT p.CODIGOBARRAS AS codigoBarras, p.NOMBRE AS nombre, p.PRECIOUNITARIOVENTA AS precioUnitarioVenta, " +
                                    "p.PRESENTACION AS presentacion, p.CANTIDADPRESENTACION AS cantidadPresentacion, " +
                                    "p.UNIDADMEDIDA AS unidadMedida, p.FECHAEXPIRACION AS fechaExpiracion, " +
                                    "c.NOMBRECATEGORIA AS categoria, s.NOMBRESUCURSAL AS sucursal " +
                                    "FROM PRODUCTO p " +
                                    "INNER JOIN CATEGORIA c ON p.CODIGOBARRAS = c.CODIGOBARRAS " +
                                    "INNER JOIN INFOEXTRABODEGA ie ON p.CODIGOBARRAS = ie.CODIGOBARRAS " +
                                    "INNER JOIN BODEGA b ON ie.IDBODEGA = b.IDBODEGA " +
                                    "INNER JOIN SUCURSAL s ON b.IDSUCURSAL = s.IDSUCURSAL " +
                                    "WHERE (:precioMin IS NULL OR p.PRECIOUNITARIOVENTA >= :precioMin) " +
                                    "AND (:precioMax IS NULL OR p.PRECIOUNITARIOVENTA <= :precioMax) " +
                                    "AND (:fechaExpiracion IS NULL OR p.FECHAEXPIRACION <= :fechaExpiracion) " +
                                    "AND (:idSucursal IS NULL OR s.IDSUCURSAL = :idSucursal) " +
                                    "AND (:idCategoria IS NULL OR c.CODIGOCATEGORIA = :idCategoria)", nativeQuery = true)
                            Collection<ProductoCaracteristicasInfo> obtenerProductosPorCaracteristicas(
                                    @Param("precioMin") Float precioMin,
                                    @Param("precioMax") Float precioMax,
                                    @Param("fechaExpiracion") Date fechaExpiracion,
                                    @Param("idSucursal") Long idSucursal,
                                    @Param("idCategoria") Long idCategoria
                            );
                            @Modifying
                            @Transactional
                            @Query(value = "DELETE FROM producto WHERE codigo_barras = :codigoBarras", nativeQuery = true)
                            void eliminarProducto(@Param("codigoBarras") String codigoBarras);
                            public interface ProductoOrdenCompraInfo {
                                String getNombreProducto();
                                String getCodigoBarras();
                                String getNombreBodega();
                                String getNombreSucursal();
                                String getNombreProveedor();
                                Integer getCantidadActual();
                            }
                        
                            // Consulta para obtener todos los productos que requieren una nueva orden de compra
                            @Query(value = "SELECT p.NOMBRE AS nombreProducto, p.CODIGOBARRAS AS codigoBarras, b.NOMBREBODEGA AS nombreBodega, " +
                                    "s.NOMBRESUCURSAL AS nombreSucursal, pr.NOMBREPROVEEDOR AS nombreProveedor, " +
                                    "ie.TOTALEXISTENCIAS AS cantidadActual " +
                                    "FROM PRODUCTO p " +
                                    "INNER JOIN INFOEXTRABODEGA ie ON p.CODIGOBARRAS = ie.CODIGOBARRAS " +
                                    "INNER JOIN BODEGA b ON ie.IDBODEGA = b.IDBODEGA " +
                                    "INNER JOIN SUCURSAL s ON b.IDSUCURSAL = s.IDSUCURSAL " +
                                    "INNER JOIN INFOEXTRAPROVEEDOR ip ON p.CODIGOBARRAS = ip.CODIGOBARRAS " +
                                    "INNER JOIN PROVEEDOR pr ON ip.NIT = pr.NIT " +
                                    "WHERE ie.TOTALEXISTENCIAS < ie.MINEXISTENCIAS", nativeQuery = true)
                            Collection<ProductoOrdenCompraInfo> obtenerProductosParaOrdenDeCompra();
}

