package uniandes.edu.co.parranderos.modelo;

import jakarta.persistence.*;
import java.io.Serializable;


@Embeddable
public class InfoExtraVentaPk implements Serializable {

    @ManyToOne
    @JoinColumn(name = "IDVENTA", referencedColumnName = "IDVENTA")
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "CODIGOBARRAS", referencedColumnName = "CODIGOBARRAS")
    private Producto producto;

    public InfoExtraVentaPk() {
        super();
    }

    
    public InfoExtraVentaPk(Venta venta, Producto producto) {
        super();
        this.venta = venta;
        this.producto = producto;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    
}
