package uniandes.edu.co.parranderos.modelo;

import java.io.Serializable;

import jakarta.persistence.*;

@Entity
@Table(name = "CIUDAD")
public class Ciudad implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long codigoCiudad;

    private String nombreCiudad;

    public Ciudad() {}

    public Ciudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    public Long getCodigoCiudad() {
        return codigoCiudad;
    }

    public void setCodigoCiudad(Long codigoCiudad) {
        this.codigoCiudad = codigoCiudad;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }
}
