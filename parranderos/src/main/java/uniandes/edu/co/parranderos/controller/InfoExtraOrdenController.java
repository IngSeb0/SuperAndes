package uniandes.edu.co.parranderos.controller;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.parranderos.modelo.InfoExtraOrden;
import uniandes.edu.co.parranderos.modelo.InfoExtraOrdenPk;
import uniandes.edu.co.parranderos.repositorio.InfoExtraOrdenRepository;

@RestController
public class InfoExtraOrdenController {

    @Autowired
    private InfoExtraOrdenRepository infoExtraOrdenRepository;

    @GetMapping("/infoExtraOrden")
    public ResponseEntity<Collection<InfoExtraOrden>> obtenerInfoExtraOrden() {
        try {
            Collection<InfoExtraOrden> infoExtraOrdenes = infoExtraOrdenRepository.obtenerTodaLaInfoExtraOrden();
            return ResponseEntity.ok(infoExtraOrdenes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/infoExtraOrden/new/save")
    public ResponseEntity<?> createInfoExtraOrden(@RequestBody InfoExtraOrden infoExtraOrden) {
        try {
            InfoExtraOrdenPk pk = infoExtraOrden.getPk();

            if (pk == null || pk.getOrdenCompra() == null || pk.getProducto() == null) {
                throw new RuntimeException("Faltan datos en la clave primaria");
            }

            Long ordenId = pk.getOrdenCompra().getId();
            String productoId = pk.getProducto().getCodigoBarras();
            Long cantidad = infoExtraOrden.getCantidad();
            Float costoUnitario = infoExtraOrden.getCostoUnitario();

            infoExtraOrdenRepository.insertarInfoExtraOrden(ordenId, productoId, cantidad, costoUnitario);

            InfoExtraOrden nuevaInfoExtraOrden = infoExtraOrdenRepository.obtenerInfoExtraOrdenPorId(ordenId, productoId);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaInfoExtraOrden);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la información extra de orden", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
