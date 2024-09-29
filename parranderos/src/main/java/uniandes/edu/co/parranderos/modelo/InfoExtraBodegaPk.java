package uniandes.edu.co.parranderos.modelo;

import jakarta.persistence.*;
import java.io.Serializable;

@Embeddable
public class InfoExtraBodegaPk implements Serializable {

    @ManyToOne
    @JoinColumn(name = "BODEGAID", referencedColumnName = "BODEGAID")
    private Bodega bodega;

    @ManyToOne
    @JoinColumn(name = "IDPRODUCTO", referencedColumnName = "IDPRODUCTO")
    private Producto producto;

    public InfoExtraBodegaPk() {}

    public InfoExtraBodegaPk(Bodega bodega, Producto producto) {
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
