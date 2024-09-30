package uniandes.edu.co.parranderos.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "SUCURSAL")
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long IDSUCURSAL;

    private String nombreSucursal;


    private String tamañoBloque; 

   
    private String direccion;


    private String telefono;

    public Sucursal() {}

    public Sucursal(String nombreSucursal, String tamañoBloque, String direccion, String telefono) {
        this.nombreSucursal = nombreSucursal;
        this.tamañoBloque = tamañoBloque;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Long getIdSucursal() {
        return IDSUCURSAL;
    }

    public void setIdSucursal(Long IDSUCURSAL) {
        this.IDSUCURSAL = IDSUCURSAL;
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
