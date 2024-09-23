package uniandes.edu.co.parranderos.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Recepcion Productos")

public class RecepcionProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String fechaRecepcion;

    public RecepcionProducto() {}

    public RecepcionProducto(Long id, String fechaRecepcion) {
        this.id = id;
        this.fechaRecepcion = fechaRecepcion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
                "id=" + id +
                ", fechaRecepcion='" + fechaRecepcion + '\'' +
                '}';
    }
}
