package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.parranderos.modelo.ProductoPerecedero;

import java.util.Collection;

public interface ProductoPerecederoRepository extends JpaRepository<ProductoPerecedero, String> {

    // Obtener todos los productos perecederos
    @Query(value = "SELECT * FROM PRODUCTOPERECEDERO", nativeQuery = true)
    Collection<ProductoPerecedero> obtenerTodosLosProductosPerecederos();

    // Obtener un producto perecedero por su código de barras
    @Query(value = "SELECT * FROM PRODUCTOPERECEDERO WHERE CODIGOBARRAS = :codigoBarras", nativeQuery = true)
    ProductoPerecedero obtenerProductoPerecederoPorCodigoDeBarras(@Param("codigoBarras") String codigoBarras);

    // Insertar un nuevo producto perecedero
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO PRODUCTOPERECEDERO (CODIGOBARRAS, FECHAVENCIMIENTO) VALUES (:codigoBarras, :fechaVencimiento)", nativeQuery = true)
    void insertarProductoPerecedero(@Param("codigoBarras") String codigoBarras, 
                                    @Param("fechaVencimiento") String fechaVencimiento);

    // Actualizar un producto perecedero por su código de barras
    @Modifying
    @Transactional
    @Query(value = "UPDATE PRODUCTOPERECEDERO SET FECHAVENCIMIENTO = :fechaVencimiento WHERE CODIGOBARRAS = :codigoBarras", nativeQuery = true)
    void actualizarProductoPerecedero(@Param("codigoBarras") String codigoBarras, 
                                      @Param("fechaVencimiento") String fechaVencimiento);

    // Eliminar un producto perecedero por su código de barras
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM PRODUCTOPERECEDERO WHERE CODIGOBARRAS = :codigoBarras", nativeQuery = true)
    void eliminarProductoPerecedero(@Param("codigoBarras") String codigoBarras);
}
