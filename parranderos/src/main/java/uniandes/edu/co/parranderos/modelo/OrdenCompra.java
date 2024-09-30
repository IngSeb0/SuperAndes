package uniandes.edu.co.parranderos.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import java.util.Date;

@Entity
@Table(name = "ORDENCOMPRA") // Asegúrate de que este sea el nombre correcto sin espacios
public class OrdenCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;


    private Date fechaCreacion;

    private String estado;


    private Date fechaEntrega;

    @JoinColumn(name = "IDSUCURSAL")
    private Sucursal IDSUCURSAL;

    public OrdenCompra() {}

    public OrdenCompra(Long id, Date fechaCreacion, String estado, Date fechaEntrega, Sucursal IDSUCURSAL) {
        this.id = id;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
        this.fechaEntrega = fechaEntrega;
        this.IDSUCURSAL = IDSUCURSAL;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "OrdenCompra{" +
                "id=" + id +
                ", fechaCreacion=" + fechaCreacion +
                ", estado='" + estado + '\'' +
                ", fechaEntrega=" + fechaEntrega +
                ", IDSUCURSAL=" + IDSUCURSAL +
                '}';
    }
}
