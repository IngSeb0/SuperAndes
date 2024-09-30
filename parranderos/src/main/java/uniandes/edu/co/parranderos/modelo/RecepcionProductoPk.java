package uniandes.edu.co.parranderos.modelo;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import java.io.Serializable;

@Embeddable
public class RecepcionProductoPk implements Serializable {

    @JoinColumn(name = "IDRECEPCION", referencedColumnName = "IDRECEPCION")
    private OrdenCompra ordenCompra;

    @JoinColumn(name = "IDBODEGA", referencedColumnName = "IDBODEGA")
    private Bodega bodega;

    public RecepcionProductoPk() {
        super();
    }

    public RecepcionProductoPk(OrdenCompra ordenCompra, Bodega bodega) {
        super();
        this.ordenCompra = ordenCompra;
        this.bodega = bodega;
    }

    // Getters y Setters
    public OrdenCompra getIdRecepcion() {
        return ordenCompra;
    }

    public void setIdRecepcion(OrdenCompra ordenCompra) {
        this.ordenCompra = ordenCompra;
    }

    public Bodega getBodega() {
        return bodega;
    }

    public void setBodega(Bodega bodega) {
        this.bodega = bodega;
    }
}
