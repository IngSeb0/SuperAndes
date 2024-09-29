package uniandes.edu.co.parranderos.modelo;

import jakarta.persistence.*;
import java.io.Serializable;


@Embeddable
public class InfoExtraProveedorPk implements Serializable {

    @Column(name = "PROVEEDOR_NIT")
    private Proveedor proveedorNit;

    @Column(name = "PRODUCTO_CODIGOBARRAS")
    private Producto productoCodigoBarras;

    public InfoExtraProveedorPk() {
        super();
    }

    public InfoExtraProveedorPk(Proveedor proveedorNit, Producto productoCodigoBarras) {
        super();
        this.proveedorNit = proveedorNit;
        this.productoCodigoBarras = productoCodigoBarras;
    }

    public Proveedor getProveedorNit() {
        return proveedorNit;
    }

    public void setProveedorNit(Proveedor proveedorNit) {
        this.proveedorNit = proveedorNit;
    }

    public Producto getProductoCodigoBarras() {
        return productoCodigoBarras;
    }

    public void setProductoCodigoBarras(Producto productoCodigoBarras) {
        this.productoCodigoBarras = productoCodigoBarras;
    }

  
}
