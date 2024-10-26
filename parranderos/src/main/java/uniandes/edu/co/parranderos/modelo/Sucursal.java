package uniandes.edu.co.parranderos.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "SUCURSAL")
public class Sucursal {

    @Id
    private Long idSucursal;

    private String nombreSucursal;
    private Float tamanioInstalacion;
    private String direccion;
    private String telefono;

    // Relación ManyToOne con Ciudad a través del código de ciudad
    @ManyToOne
    @JoinColumn(name = "CODIGOCIUDAD", referencedColumnName = "CODIGOCIUDAD")
    private Ciudad ciudad;

    public Sucursal() {}

    public Sucursal(String nombreSucursal, Float tamanioInstalacion, String direccion, String telefono, Ciudad ciudad) {
        this.nombreSucursal = nombreSucursal;
        this.tamanioInstalacion = tamanioInstalacion;
        this.direccion = direccion;
        this.telefono = telefono;
        this.ciudad = ciudad;
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

    public Float getTamanioInstalacion() {
        return tamanioInstalacion;
    }

    public void setTamanioInstalacion(Float tamanioInstalacion) {
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

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public String toString() {
        return "Sucursal{" +
                "idSucursal=" + idSucursal +
                ", nombreSucursal='" + nombreSucursal + '\'' +
                ", tamanioInstalacion='" + tamanioInstalacion + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", ciudad=" + ciudad.getCodigoCiudad() +
                '}';
    }
}
