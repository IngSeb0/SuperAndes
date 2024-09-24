package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.parranderos.modelo.Producto;

import java.util.Collection;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    // Consultar todos los productos
    @Query(value = "SELECT * FROM productos", nativeQuery = true)
    Collection<Producto> obtenerTodosLosProductos();

    // Consultar un producto por su ID
    @Query(value = "SELECT * FROM productos WHERE codigo_barras = :id", nativeQuery = true)
    Producto obtenerProductoPorId(@Param("id") String id);

    // Insertar un nuevo producto    @Modifying
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

    // Actualizar un producto por su ID
    @Modifying
    @Transactional
    @Query(value = "UPDATE productos SET nombre = :nombre, precio_unitario_venta = :precioUnitarioVenta WHERE codigo_barras = :codigoBarras", nativeQuery = true)
    void actualizarProducto(@Param("codigoBarras") String codigoBarras,
                            @Param("nombre") String nombre,
                            @Param("precioUnitarioVenta") Float precioUnitarioVenta);

    // Eliminar un producto por su ID
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM productos WHERE codigo_barras = :codigoBarras", nativeQuery = true)
    void eliminarProducto(@Param("codigoBarras") String codigoBarras);
}
