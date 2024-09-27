package uniandes.edu.co.parranderos.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "SUCURSAL")
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDSUCURSAL")
    private Long idSucursal;

    @Column(name = "NOMBRE")
    private String nombreSucursal;

    @Column(name = "TAMAÑOBLOSTABLACION")
    private String tamañoBloque; // Tamaño almacenado como String por convención en este caso.

    @Column(name = "DIRECCION")
    private String direccion;

    @Column(name = "TELEFONO")
    private String telefono;

    public Sucursal() {}

    public Sucursal(String nombreSucursal, String tamañoBloque, String direccion, String telefono) {
        this.nombreSucursal = nombreSucursal;
        this.tamañoBloque = tamañoBloque;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Long getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Long idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }

    public String getTamañoBloque() {
        return tamañoBloque;
    }

    public void setTamañoBloque(String tamañoBloque) {
        this.tamañoBloque = tamañoBloque;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
