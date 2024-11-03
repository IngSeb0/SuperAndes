package uniandes.edu.co.parranderos.Servicios;

import java.util.Collection;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.parranderos.repositorio.BodegaRepository;

@Service
public class BodegaServicio {

    private BodegaRepository bodegaRepository;

    public BodegaServicio(BodegaRepository bodegaRepository){
        this.bodegaRepository = bodegaRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, readOnly = true)
    public Collection<Map<String, Object>> obtenerDocumentosIngresoProductos(Long idSucursal, Long idBodegaLong) throws InterruptedException {
        Collection<Map<String, Object>> obj = bodegaRepository.obtenerDocumentosIngresoProductos(idSucursal, idBodegaLong);
        System.out.println(obj.size());
        Thread.sleep(30000);
        obj = bodegaRepository.obtenerDocumentosIngresoProductos(idSucursal, idBodegaLong);
        return obj;
    }

}
