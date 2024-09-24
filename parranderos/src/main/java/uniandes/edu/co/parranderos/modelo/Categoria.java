package uniandes.edu.co.parranderos.modelo;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="Categorias")

public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigo;
    private String nombre;
    private String descripcion;
    private String caracteristicaAlmacenamiento;

    public Categoria() {}

    public Categoria(Long codigo, String nombre, String descripcion, String caracteristicaAlmacenamiento) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.caracteristicaAlmacenamiento = caracteristicaAlmacenamiento;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCaracteristicaAlmacenamiento() {
        return caracteristicaAlmacenamiento;
    }

    public void setCaracteristicaAlmacenamiento(String caracteristicaAlmacenamiento) {
        this.caracteristicaAlmacenamiento = caracteristicaAlmacenamiento;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", caracteristicaAlmacenamiento='" + caracteristicaAlmacenamiento + '\'' +
                '}';
    }
}
