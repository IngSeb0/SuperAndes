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

    // Método para consultar documentos de ingreso de productos a bodega.
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public Collection<Map<String, Object>> obtenerDocumentosIngresoProductos(Long idSucursal, Long idBodega) throws InterruptedException {
        // Consultar los documentos de ingreso de productos.
        Collection<Map<String, Object>> documentos = bodegaRepository.obtenerDocumentosIngresoProductos(idSucursal, idBodega);

        // Temporizador de 30 segundos para simular operación larga.
        Thread.sleep(30000);

        // Consultar nuevamente los documentos, si es necesario.
        documentos = bodegaRepository.obtenerDocumentosIngresoProductos(idSucursal, idBodega);
        return documentos; // Devolver los documentos consultados.
    }
}
