package uniandes.edu.co.parranderos.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "INFOEXTRAVENTA") // Nombre de la tabla en la base de datos

public class InfoExtraVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInfoExtraVenta; // Clave primaria

    private Integer cantidad; // Campo CANTIDAD en la BD

    private Float precioUnitario; // Campo PRECIOUNITARIO en la BD

    @ManyToOne
    @JoinColumn(name = "VENTA_IDVENTA") // Relación con la tabla VENTA
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "PRODUCTO_CODIGOBARRAS") // Relación con la tabla PRODUCTO
    private Producto producto;

    // Constructor vacío
    public InfoExtraVenta() {}

    // Constructor con parámetros
    public InfoExtraVenta(Integer cantidad, Float precioUnitario, Venta venta, Producto producto) {
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.venta = venta;
        this.producto = producto;
    }

    // Getters y Setters
    public Long getIdInfoExtraVenta() {
        return idInfoExtraVenta;
    }

    public void setIdInfoExtraVenta(Long idInfoExtraVenta) {
        this.idInfoExtraVenta = idInfoExtraVenta;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Float getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Float precioUnitario) {
        this.precioUnitario = precioUnitario;
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

    @Override
    public String toString() {
        return "InfoExtraVenta{" +
                "idInfoExtraVenta=" + idInfoExtraVenta +
                ", cantidad=" + cantidad +
                ", precioUnitario=" + precioUnitario +
                ", venta=" + venta +
                ", producto=" + producto +
                '}';
    }
}
