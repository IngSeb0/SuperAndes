package uniandes.edu.co.parranderos.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "INFOEXTRAORDEN") // Nombre de la tabla en la base de datos
public class InfoExtraOrden {

    @Id
    @JoinColumn(name = "IDINFOEXTRAORDEN") // Columna IDINFOEXTRAORDEN
    private Long idInfoExtraOrden;

    private Long cantidad; // Columna CANTIDAD

    private Float costoUnitario; // Columna COSTOUNITARIO

    @ManyToOne
    @JoinColumn(name = "ORDENCOMPRA_IDORDEN") // Columna ORDENCOMPRA_IDORDEN
    private OrdenCompra ordenCompra;

    @ManyToOne
    @JoinColumn(name = "PRODUCTO_CODIGOBARRAS") // Columna PRODUCTO_CODIGOBARRAS
    private Producto producto;

    // Constructor vacío
    public InfoExtraOrden() {}

    // Constructor con parámetros
    public InfoExtraOrden(Long idInfoExtraOrden, Long cantidad, Float costoUnitario, OrdenCompra ordenCompra, Producto producto) {
        this.idInfoExtraOrden = idInfoExtraOrden;
        this.cantidad = cantidad;
        this.costoUnitario = costoUnitario;
        this.ordenCompra = ordenCompra;
        this.producto = producto;
    }

    // Getters y setters
    public Long getIdInfoExtraOrden() {
        return idInfoExtraOrden;
    }

    public void setIdInfoExtraOrden(Long idInfoExtraOrden) {
        this.idInfoExtraOrden = idInfoExtraOrden;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public Float getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(Float costoUnitario) {
        this.costoUnitario = costoUnitario;
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

    @Override
    public String toString() {
        return "InfoExtraOrden{" +
                "idInfoExtraOrden=" + idInfoExtraOrden +
                ", cantidad=" + cantidad +
                ", costoUnitario=" + costoUnitario +
                ", ordenCompra=" + ordenCompra +
                ", producto=" + producto +
                '}';
    }
}
