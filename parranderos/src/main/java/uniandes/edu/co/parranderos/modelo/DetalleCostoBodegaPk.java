package uniandes.edu.co.parranderos.modelo;

import java.io.Serializable;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class DetalleCostoBodegaPk implements Serializable {

    @ManyToOne
    @JoinColumn(name = "BODEGA_IDBODEGA", referencedColumnName = "IDBODEGA")
    private Bodega bodega;

    @ManyToOne
    @JoinColumn(name = "PRODUCTO_CODIGOBARRAS", referencedColumnName = "CODIGOBARRAS")
    private Producto producto;

    public DetalleCostoBodegaPk() {}

    public DetalleCostoBodegaPk(Bodega bodega, Producto producto) {
        this.bodega = bodega;
        this.producto = producto;
    }

    public Bodega getBodega() {
        return bodega;
    }

    public void setBodega(Bodega bodega) {
        this.bodega = bodega;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
