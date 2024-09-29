package uniandes.edu.co.parranderos.modelo;

import jakarta.persistence.*;
import java.io.Serializable;

@Embeddable
public class InfoExtraOrdenPk implements Serializable {

    @ManyToOne
    @JoinColumn(name = "ORDENCOMPRA_IDORDEN", referencedColumnName = "IDORDEN")
    private OrdenCompra ordenCompra;

    @ManyToOne
    @JoinColumn(name = "PRODUCTO_CODIGOBARRAS", referencedColumnName = "CODIGOBARRAS")
    private Producto producto;

    public InfoExtraOrdenPk() {
        super();
    }

    public InfoExtraOrdenPk(OrdenCompra ordenCompra, Producto producto) {
        this.ordenCompra = ordenCompra;
        this.producto = producto;
    }

    public OrdenCompra getOrdenCompra() {
        return ordenCompra;
    }

    public void setOrdenCompra(OrdenCompra ordenCompra) {
        this.ordenCompra = ordenCompra;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    
}
