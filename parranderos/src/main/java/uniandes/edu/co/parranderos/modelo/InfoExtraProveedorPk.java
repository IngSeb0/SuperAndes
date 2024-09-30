package uniandes.edu.co.parranderos.modelo;

import jakarta.persistence.*;
import java.io.Serializable;


@Embeddable
public class InfoExtraProveedorPk implements Serializable {

    @JoinColumn(name = "NIT", referencedColumnName = "NIT")
    private Proveedor nit;

    @JoinColumn(name = "CODIGOBARRAS", referencedColumnName = "CODIGOBARRAS")
    private Producto CodigoBarras;

    public InfoExtraProveedorPk() {
        super();
    }

    public InfoExtraProveedorPk(Proveedor nit, Producto CodigoBarras) {
        super();
        this.nit = nit;
        this.CodigoBarras = CodigoBarras;
    }

    public Proveedor getProveedorNit() {
        return nit;
    }

    public void setProveedorNit(Proveedor nit) {
        this.nit = nit;
    }

    public Producto getProductoCodigoBarras() {
        return CodigoBarras;
    }

    public void setProductoCodigoBarras(Producto CodigoBarras) {
        this.CodigoBarras = CodigoBarras;
    }

  
}
