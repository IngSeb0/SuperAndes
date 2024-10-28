package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uniandes.edu.co.parranderos.modelo.RecepcionProducto;
import uniandes.edu.co.parranderos.modelo.RecepcionProductoPk;

@Repository
public interface RecepcionProductoRepository extends JpaRepository<RecepcionProducto, RecepcionProductoPk> {

    @Query(value = "INSERT INTO RECEPCIONPRODUCTOS (FECHARECEPCION, IDBODEGA, IDORDEN) " +
                   "VALUES (:fechaRecepcion, :idBodega, :idOrden)", nativeQuery = true)
    void registrarRecepcionProducto(
            @Param("fechaRecepcion") String fechaRecepcion,
            @Param("idBodega") Long idBodega,
            @Param("idOrden") Long idOrden);
}
