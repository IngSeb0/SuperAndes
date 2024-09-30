package uniandes.edu.co.parranderos.modelo;

import jakarta.persistence.*;
import java.io.Serializable;


@Embeddable
public class InfoExtraProveedorPk implements Serializable {

    @JoinColumn(name = "PROVEEDOR_NIT")
    private Proveedor proveedorNit;

    @JoinColumn(name = "PRODUCTO_CODIGOBARRAS", referencedColumnName = "CODIGOBARRAS")
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
