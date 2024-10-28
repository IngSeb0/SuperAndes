package uniandes.edu.co.parranderos.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "ORDENCOMPRA") 
public class OrdenCompra implements Serializable {

    @Id

    private Long IDORDEN;

    private Date fechaCreacion;
    private String estado;
    private Date fechaEntrega;

    // Relación con la entidad Sucursal
    @ManyToOne
    @JoinColumn(name = "IDSUCURSAL", referencedColumnName = "IDSUCURSAL", nullable = false)
    private Sucursal IDSUCURSAL;

    // Relación con la entidad Producto
    @ManyToOne
    @JoinColumn(name = "CODIGOBARRAS", referencedColumnName = "CODIGOBARRAS", nullable = false)
    private Producto producto;

    // Relación con la entidad Proveedor (NIT)
    @ManyToOne
    @JoinColumn(name = "NIT", referencedColumnName = "nit", nullable = false)
    private Proveedor proveedor;

    public OrdenCompra() {}

    public OrdenCompra(Long IDORDEN, Date fechaCreacion, String estado, Date fechaEntrega, Sucursal IDSUCURSAL, Producto producto, Proveedor proveedor) {
        this.IDORDEN = IDORDEN;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
        this.fechaEntrega = fechaEntrega;
        this.IDSUCURSAL = IDSUCURSAL;
        this.producto = producto;
        this.proveedor = proveedor;
    }

    // Getters y Setters

    public Long getId() {
        return IDORDEN;
    }

    public void setId(Long IDORDEN) {
        this.IDORDEN = IDORDEN;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Sucursal getSucursalId() {
        return IDSUCURSAL;
    }

    public void setSucursalId(Sucursal IDSUCURSAL) {
        this.IDSUCURSAL = IDSUCURSAL;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    @Override
    public String toString() {
        return "OrdenCompra{" +
                "IDORDEN=" + IDORDEN +
                ", fechaCreacion=" + fechaCreacion +
                ", estado='" + estado + '\'' +
                ", fechaEntrega=" + fechaEntrega +
                ", IDSUCURSAL=" + IDSUCURSAL +
                ", producto=" + producto +
                ", proveedor=" + proveedor +
                '}';
    }
}
