package uniandes.edu.co.parranderos.modelo;

import java.io.Serializable;

import jakarta.persistence.*;

@Entity
@Table(name = "SUCURSAL")
public class Sucursal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IDSUCURSAL;

    private String nombreSucursal;

    // Modificado para usar "tamanioInstalacion" en vez de "tamañoBloque"
    private String tamanioInstalacion; 

    private String direccion;

    private String telefono;

    @JoinColumn(name = "CODIGOCIUDAD", referencedColumnName = "CODIGOCIUDAD")
    Ciudad CODIGOCIUDAD;

    public Sucursal() {}

    public Sucursal(String nombreSucursal, String tamanioInstalacion, String direccion, String telefono, Ciudad CODIGOCIUDAD) {
        this.nombreSucursal = nombreSucursal;
        this.tamanioInstalacion = tamanioInstalacion;
        this.direccion = direccion;
        this.telefono = telefono;
        this.CODIGOCIUDAD = CODIGOCIUDAD;
    }

    public Long getIdSucursal() {
        return IDSUCURSAL;
    }

    public void setIdSucursal(Long IDSUCURSAL) {
        this.IDSUCURSAL = IDSUCURSAL;
    }

    public Ciudad getCodigoCiudad() {
        return CODIGOCIUDAD;
    }

    public void setCodigoCiudad(Ciudad CODIGOCIUDAD) {
        this.CODIGOCIUDAD = CODIGOCIUDAD;
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }

    public String getTamanioInstalacion() {
        return tamanioInstalacion;
    }

    public void setTamanioInstalacion(String tamanioInstalacion) {
        this.tamanioInstalacion = tamanioInstalacion;
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
