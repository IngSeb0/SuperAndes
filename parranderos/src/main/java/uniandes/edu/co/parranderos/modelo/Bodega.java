package uniandes.edu.co.parranderos.modelo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Bodegas")
public class Bodega {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private Float tamaño;

    public Bodega() {}

    public Bodega(Long id, String nombre, Float tamaño) {
        this.id = id;
        this.nombre = nombre;
        this.tamaño = tamaño;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Float getTamaño() {
        return tamaño;
    }

    public void setTamaño(Float tamaño) {
        this.tamaño = tamaño;
    }

    @Override
    public String toString() {
        return "Bodega{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", tamaño=" + tamaño +
                '}';
    }
}
