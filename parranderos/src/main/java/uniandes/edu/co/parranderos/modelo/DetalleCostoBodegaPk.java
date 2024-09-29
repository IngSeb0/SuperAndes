package uniandes.edu.co.parranderos.modelo;
import java.io.Serializable;

import jakarta.persistence.*;

@Embeddable
public class DetalleCostoBodegaPk implements Serializable {

    @ManyToOne
    @JoinColumn(name = "INFOEXTRABODEGA_BODEGA_ID", referencedColumnName = "BODEGAID")
    @JoinColumn(name = "INFOEXTRABODEGA_PRODUCTO_ID", referencedColumnName = "IDPRODUCTO")
    private InfoExtraBodega infoExtraBodega;

    private Long idDetalleCosto;

    public DetalleCostoBodegaPk() {}

    public DetalleCostoBodegaPk(InfoExtraBodega infoExtraBodega, Long idDetalleCosto) {
        this.infoExtraBodega = infoExtraBodega;
        this.idDetalleCosto = idDetalleCosto;
    }

    public InfoExtraBodega getInfoExtraBodega() {
        return infoExtraBodega;
    }

    public void setInfoExtraBodega(InfoExtraBodega infoExtraBodega) {
        this.infoExtraBodega = infoExtraBodega;
    }

    public Long getIdDetalleCosto() {
        return idDetalleCosto;
    }

    public void setIdDetalleCosto(Long idDetalleCosto) {
        this.idDetalleCosto = idDetalleCosto;
    }
}
