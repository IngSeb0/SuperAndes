package uniandes.edu.co.parranderos.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name="VENTA")
public class Venta {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long idVenta; 

    private Date fecha; 

    private Long cantidadExistencia; // Corresponde a CANTIDADEXISTENCIA en la base de datos

    @ManyToOne
    @JoinColumn(name = "SUCURSAL_IDSUCURSAL") // Relación con Sucursal
    private Sucursal sucursal; // Corresponde a SUCURSAL_IDSUCURSAL en la base de datos

    @ManyToOne
    @JoinColumn(name = "CLIENTE_CEDULA") // Relación con Cliente
    private Cliente cliente; // Corresponde a CLIENTE_CEDULA en la base de datos

    @ManyToOne
    @JoinColumn(name = "PROVEEDOR_NIT") // Relación con Proveedor
    private Proveedor proveedor; // Corresponde a PROVEEDOR_NIT en la base de datos

    @ManyToOne
    @JoinColumn(name = "VENTA_IDINFOEXTRAVENTA") // Relación con InfoExtraVenta
    private InfoExtraVenta infoExtraVenta; // Corresponde a VENTA_IDINFOEXTRAVENTA en la base de datos

    // Constructor por defecto
    public Venta() {;}

    // Constructor con parámetros
    public Venta(Date fecha, Long cantidadExistencia, Sucursal sucursal, Cliente cliente, Proveedor proveedor, InfoExtraVenta infoExtraVenta) {
        this.fecha = fecha;
        this.cantidadExistencia = cantidadExistencia;
        this.sucursal = sucursal;
        this.cliente = cliente;
        this.proveedor = proveedor;
        this.infoExtraVenta = infoExtraVenta;
    }

    // Getters y Setters
    public Long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Long idVenta) {
        this.idVenta = idVenta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Long getCantidadExistencia() {
        return cantidadExistencia;
    }

    public void setCantidadExistencia(Long cantidadExistencia) {
        this.cantidadExistencia = cantidadExistencia;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public InfoExtraVenta getInfoExtraVenta() {
        return infoExtraVenta;
    }

    public void setInfoExtraVenta(InfoExtraVenta infoExtraVenta) {
        this.infoExtraVenta = infoExtraVenta;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "idVenta=" + idVenta +
                ", fecha=" + fecha +
                ", cantidadExistencia=" + cantidadExistencia +
                ", sucursal=" + sucursal +
                ", cliente=" + cliente +
                ", proveedor=" + proveedor +
                ", infoExtraVenta=" + infoExtraVenta +
                '}';
    }
}
