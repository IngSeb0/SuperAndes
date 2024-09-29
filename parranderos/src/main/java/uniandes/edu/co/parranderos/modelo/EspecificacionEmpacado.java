package uniandes.edu.co.parranderos.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ESPECIFICACIONEMPACADO") // Sin espacios en el nombre de la tabla
public class EspecificacionEmpacado {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idEspecificacion; 

    private Float volumen;
    private Float peso;

    public EspecificacionEmpacado() {}

    public EspecificacionEmpacado(Float volumen, Float peso) {
        this.volumen = volumen;
        this.peso = peso;
    }

    public Long getIdEspecificacionEmpacado() {
        return idEspecificacion;
    }

    public void setIdEspecificacionEmpacado(Long idEspecificacion) {
        this.idEspecificacion = idEspecificacion;
    }

    public Float getVolumen() {
        return volumen;
    }

    public void setVolumen(Float volumen) {
        this.volumen = volumen;
    }

    public Float getPesoGr() {
        return peso;
    }

    public void setPesoGr(Float peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "EspecificacionEmpacado{" +
                "idEspecificacion=" + idEspecificacion +
                ",Volumen="+volumen+
                ", pesoGr=" + peso +
                '}';
    }
}
