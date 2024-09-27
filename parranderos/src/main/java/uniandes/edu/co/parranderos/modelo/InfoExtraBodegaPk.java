package uniandes.edu.co.parranderos.modelo;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class InfoExtraBodegaPk implements Serializable {

    @ManyToOne
    @JoinColumn(name = "BODEGA_IDBODEGA", referencedColumnName = "IDBODEGA")
    private Bodega bodega;

    @Column(name = "IDINFOEXTRABODEGA")
    private Long idInfoExtraBodega;

    public InfoExtraBodegaPk() {}

    public InfoExtraBodegaPk(Bodega bodega, Long idInfoExtraBodega) {
        this.bodega = bodega;
        this.idInfoExtraBodega = idInfoExtraBodega;
    }

    public Bodega getBodega() {
        return bodega;
    }

    public void setBodega(Bodega bodega) {
        this.bodega = bodega;
    }

    public Long getIdInfoExtraBodega() {
        return idInfoExtraBodega;
    }

    public void setIdInfoExtraBodega(Long idInfoExtraBodega) {
        this.idInfoExtraBodega = idInfoExtraBodega;
    }
}
