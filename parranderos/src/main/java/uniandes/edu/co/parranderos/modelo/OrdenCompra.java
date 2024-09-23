package uniandes.edu.co.parranderos.modelo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Ordenes de compra")
public class OrdenCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fechaCreacion;
    private String estado;
    private String fechaEntrega;

    public OrdenCompra() {}

    public OrdenCompra(Long id, String fechaCreacion, String estado, String fechaEntrega) {
        this.id = id;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
        this.fechaEntrega = fechaEntrega;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    @Override
    public String toString() {
        return "OrdenCompra{" +
                "id=" + id +
                ", fechaCreacion='" + fechaCreacion + '\'' +
                ", estado='" + estado + '\'' +
                ", fechaEntrega='" + fechaEntrega + '\'' +
                '}';
    }
}
