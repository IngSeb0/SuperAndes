package uniandes.edu.co.parranderos.modelo;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "RecepcionProductos")
public class RecepcionProducto {

    @EmbeddedId
    private RecepcionProductoPk pk;

    private String fechaRecepcion;

    public RecepcionProducto() {}

    public RecepcionProducto(RecepcionProductoPk pk, String fechaRecepcion) {
        this.pk = pk;
        this.fechaRecepcion = fechaRecepcion;
    }

    // Getters y Setters
    public RecepcionProductoPk getPk() {
        return pk;
    }

    public void setPk(RecepcionProductoPk pk) {
        this.pk = pk;
    }

    public String getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(String fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    @Override
    public String toString() {
        return "RecepcionProducto{" +
                "pk=" + pk +
                ", fechaRecepcion='" + fechaRecepcion + '\'' +
                '}';
    }
}
