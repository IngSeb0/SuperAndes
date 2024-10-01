package uniandes.edu.co.parranderos.controller;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.parranderos.modelo.InfoExtraBodega;
import uniandes.edu.co.parranderos.modelo.InfoExtraBodegaPk;
import uniandes.edu.co.parranderos.repositorio.InfoExtraBodegaRepository;

@RestController
@RequestMapping("/InfoExtraBodega")
public class InfoExtraBodegaController {

    @Autowired
    private InfoExtraBodegaRepository infoExtraBodegaRepository;
   

    
    @GetMapping("/infoExtraBodega")
    public ResponseEntity<Collection<InfoExtraBodega>> obtenerInfoExtraBodega() {
        try {
            Collection<InfoExtraBodega> infoExtraBodegas = infoExtraBodegaRepository.obtenerTodaLaInfoExtraBodega();
            return ResponseEntity.ok(infoExtraBodegas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/infoExtraBodega/new/save")
    public ResponseEntity<?> createInfoExtraBodega(@RequestBody InfoExtraBodega infoExtraBodega) {
        try {
            InfoExtraBodegaPk pk = infoExtraBodega.getPk();

            if (pk == null || pk.getBodega() == null || pk.getProducto() == null) {
                throw new RuntimeException("Faltan datos en la clave primaria");
            }

            Long bodegaId = pk.getBodega().getId();
            String productoId = pk.getProducto().getCodigoBarras();
            Integer totalExistencias = infoExtraBodega.getTotalExistencias();
            Float costoPromedio = infoExtraBodega.getCostoPromedio();
            Integer capacidadAlmacenamiento = infoExtraBodega.getCapacidadAlmacenamiento();
            Float nivelMinimo = infoExtraBodega.getNivelMinimo();
            infoExtraBodegaRepository.insertarInfoExtraBodega(bodegaId, productoId, totalExistencias, costoPromedio, capacidadAlmacenamiento, nivelMinimo);

            InfoExtraBodega nuevaInfoExtraBodega = infoExtraBodegaRepository.obtenerInfoExtraBodegaPorId(bodegaId, productoId);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaInfoExtraBodega);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la información extra de bodega", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
