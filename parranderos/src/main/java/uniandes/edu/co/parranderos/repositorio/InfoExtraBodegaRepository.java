package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.parranderos.modelo.InfoExtraBodega;
import uniandes.edu.co.parranderos.modelo.InfoExtraBodegaPk;

import java.util.List;

public interface InfoExtraBodegaRepository extends JpaRepository<InfoExtraBodega, InfoExtraBodegaPk> {

    // Obtener toda la información extra de las bodegas
    @Query("SELECT i FROM InfoExtraBodega i")
    List<InfoExtraBodega> obtenerTodaLaInfoExtraBodega();

    // Obtener una información extra de bodega por su clave primaria compuesta (bodegaId y productoId)
    @Query("SELECT i FROM InfoExtraBodega i WHERE i.pk.bodega.id = :bodegaId AND i.pk.producto.id = :productoId")
    InfoExtraBodega obtenerInfoExtraBodegaPorId(@Param("bodegaId") Long bodegaId, @Param("productoId") Long productoId);

    // Insertar nueva información extra de bodega
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO INFOEXTRABODEGA (TOTALEXISTENCIAS, COSTOPROMEDIO, CAPACIDADALMACENAMIENTO, NIVELMINIMO, BODEGA_IDBODEGA, PRODUCTO_IDPRODUCTO) " +
                   "VALUES (:totalExistencias, :costoPromedio, :capacidadAlmacenamiento, :nivelMinimo, :bodegaIdBodega, :productoIdProducto)", 
           nativeQuery = true)
    void insertarInfoExtraBodega(@Param("totalExistencias") Integer totalExistencias, 
                                 @Param("costoPromedio") Float costoPromedio, 
                                 @Param("capacidadAlmacenamiento") Integer capacidadAlmacenamiento, 
                                 @Param("nivelMinimo") Float nivelMinimo,
                                 @Param("bodegaIdBodega") Long bodegaIdBodega,
                                 @Param("productoIdProducto") Long productoIdProducto);

    // Actualizar información extra de bodega por su clave primaria compuesta (bodegaId y productoId)
    @Modifying
    @Transactional
    @Query(value = "UPDATE INFOEXTRABODEGA SET TOTALEXISTENCIAS = :totalExistencias, COSTOPROMEDIO = :costoPromedio, CAPACIDADALMACENAMIENTO = :capacidadAlmacenamiento, " +
                   "NIVELMINIMO = :nivelMinimo WHERE BODEGA_IDBODEGA = :bodegaIdBodega AND PRODUCTO_IDPRODUCTO = :productoIdProducto", 
           nativeQuery = true)
    void actualizarInfoExtraBodega(@Param("totalExistencias") Integer totalExistencias, 
                                   @Param("costoPromedio") Float costoPromedio, 
                                   @Param("capacidadAlmacenamiento") Integer capacidadAlmacenamiento, 
                                   @Param("nivelMinimo") Float nivelMinimo,
                                   @Param("bodegaIdBodega") Long bodegaIdBodega,
                                   @Param("productoIdProducto") Long productoIdProducto);

    // Eliminar una información extra de bodega por su clave primaria compuesta (bodegaId y productoId)
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM INFOEXTRABODEGA WHERE BODEGA_IDBODEGA = :bodegaIdBodega AND PRODUCTO_IDPRODUCTO = :productoIdProducto", 
           nativeQuery = true)
    void eliminarInfoExtraBodega(@Param("bodegaIdBodega") Long bodegaIdBodega, @Param("productoIdProducto") Long productoIdProducto);
}
