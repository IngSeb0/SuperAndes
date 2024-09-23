package uniandes.edu.co.parranderos.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Sucursales")
public class Sucursal {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private Float tamaño;
    private String direccion;
    private Integer telefono;

    // Constructor sin argumentos (necesario para JPA)
    public Sucursal() {
    }

    // Constructor con todos los campos
    public Sucursal(Long id, String nombre, Float tamaño, String direccion, Integer telefono) {
        this.id = id;
        this.nombre = nombre;
        this.tamaño = tamaño;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    // Getters y Setters

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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    // Método toString para representar el objeto como String
    @Override
    public String toString() {
        return "Sucursal{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", tamaño=" + tamaño +
                ", direccion='" + direccion + '\'' +
                ", telefono=" + telefono +
                '}';
    }
}
