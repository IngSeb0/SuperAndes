package uniandes.edu.servicios;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.parranderos.repositorio.BodegaRepository;

import java.util.Collection;
import java.util.Map;

@Service
public class BodegaServicio {

    @Autowired
    private BodegaRepository bodegaRepository;

   
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public Collection<Map<String, Object>> obtenerDocumentosIngresoProductos(Long idSucursal, Long idBodega) throws InterruptedException {
        
        Collection<Map<String, Object>> documentos = bodegaRepository.obtenerDocumentosIngresoProductos(idSucursal, idBodega);

        Thread.sleep(30000);

        documentos = bodegaRepository.obtenerDocumentosIngresoProductos(idSucursal, idBodega);
        return documentos; 
    }
}
