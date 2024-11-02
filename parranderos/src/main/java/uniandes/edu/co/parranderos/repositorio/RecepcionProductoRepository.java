package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import uniandes.edu.co.parranderos.modelo.RecepcionProducto;
import uniandes.edu.co.parranderos.modelo.RecepcionProductoPk;

@Repository
public interface RecepcionProductoRepository extends JpaRepository<RecepcionProducto, RecepcionProductoPk> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO RECEPCIONPRODUCTOS (FECHARECEPCION, IDBODEGA, IDORDEN) " +
                   "VALUES (TO_DATE(:fechaRecepcion, 'YYYY-MM-DD'), :idBodega, :idOrden)", 
           nativeQuery = true)
    void registrarRecepcionProducto(@Param("fechaRecepcion") String fechaRecepcion, 
                                    @Param("idBodega") Long idBodega, 
                                    @Param("idOrden") Long idOrden);
    @Query(value = "SELECT * FROM RECEPCIONPRODUCTOS", nativeQuery = true)
    RecepcionProducto obtenerTodasLasRecepciones();
}    
