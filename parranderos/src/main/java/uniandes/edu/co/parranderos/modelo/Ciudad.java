package uniandes.edu.co.parranderos.modelo;

import java.io.Serializable;

import jakarta.persistence.*;

@Entity
@Table(name = "CIUDAD")
public class Ciudad implements Serializable {

    @Id
  

    private Integer codigoCiudad;

    private String nombreCiudad;

    public Ciudad() {}

    public Ciudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    public Integer getCodigoCiudad() {
        return codigoCiudad;
    }

    public void setCodigoCiudad(Integer codigoCiudad) {
        this.codigoCiudad = codigoCiudad;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }
}
