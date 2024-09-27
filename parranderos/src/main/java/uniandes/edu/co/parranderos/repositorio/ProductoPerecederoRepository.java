package uniandes.edu.co.parranderos.repositorio;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.parranderos.modelo.ProductoPerecedero;
import java.lang.String;
import java.util.Collection;

public interface ProductoPerecederoRepository extends JpaRepository<ProductoPerecedero, Long> {

    // Consultar todos los productos perecederos
    @Query(value = "SELECT * FROM productos WHERE tipo_producto = 'PERECEDERO'", nativeQuery = true)
    Collection<ProductoPerecedero> obtenerTodosLosProductosPerecederos();

    // Consultar un producto perecedero por su ID
    @Query(value = "SELECT * FROM productos WHERE codigo_barras = :id AND tipo_producto = 'PERECEDERO'", nativeQuery = true)
    ProductoPerecedero obtenerProductoPerecederoPorCodigoDeBarras(@Param("id") Long id);

    // Insertar un nuevo producto perecedero
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO productos (codigo_barras, nombre, precio_unitario_venta, presentacion, cantidad_presentacion, unidad_medida, fecha_expiracion, tipo_producto, fecha_vencimiento) " +
            "VALUES (:codigoBarras, :nombre, :precioUnitarioVenta, :presentacion, :cantidadPresentacion, :unidadMedida, :fechaExpiracion, 'PERECEDERO', :fechaVencimiento)", nativeQuery = true)
    void insertarProductoPerecedero(@Param("codigoBarras") String codigoBarras,
                                    @Param("nombre") String nombre,
                                    @Param("precioUnitarioVenta") Float precioUnitarioVenta,
                                    @Param("presentacion") String presentacion,
                                    @Param("cantidadPresentacion") Integer cantidadPresentacion,
                                    @Param("unidadMedida") String unidadMedida,
                                    @Param("fechaExpiracion") String fechaExpiracion,
                                    @Param("fechaVencimiento") String fechaVencimiento);

    // Actualizar un producto perecedero por su ID
    @Modifying
    @Transactional
    @Query(value = "UPDATE productos SET nombre = :nombre, precio_unitario_venta = :precioUnitarioVenta, presentacion = :presentacion, cantidad_presentacion = :cantidadPresentacion, unidad_medida = :unidadMedida, fecha_expiracion = :fechaExpiracion, fecha_vencimiento = :fechaVencimiento WHERE codigo_barras = :codigoBarras", nativeQuery = true)
    void actualizarProductoPerecedero(@Param("codigoBarras") String codigoBarras,
                                      @Param("nombre") String nombre,
                                      @Param("precioUnitarioVenta") Float precioUnitarioVenta,
                                      @Param("presentacion") String presentacion,
                                      @Param("cantidadPresentacion") Integer cantidadPresentacion,
                                      @Param("unidadMedida") String unidadMedida,
                                      @Param("fechaExpiracion") String fechaExpiracion,
                                      @Param("fechaVencimiento") String fechaVencimiento);
    

    // Eliminar un producto perecedero por su ID
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM productos WHERE codigo_barras = :codigoBarras AND tipo_producto = 'PERECEDERO'", nativeQuery = true)
    void eliminarProductoPerecedero(@Param("codigoBarras") String codigoBarras);
}
