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
    private Long idEspecificacionEmpacado; 

    private Float volumen;
    private Float pesoGr;

    public EspecificacionEmpacado() {}

    public EspecificacionEmpacado(Float volumen, Float pesoGr) {
        this.volumen = volumen;
        this.pesoGr = pesoGr;
    }

    public Long getIdEspecificacionEmpacado() {
        return idEspecificacionEmpacado;
    }

    public void setIdEspecificacionEmpacado(Long idEspecificacionEmpacado) {
        this.idEspecificacionEmpacado = idEspecificacionEmpacado;
    }

    public Float getVolumen() {
        return volumen;
    }

    public void setVolumen(Float volumen) {
        this.volumen = volumen;
    }

    public Float getPesoGr() {
        return pesoGr;
    }

    public void setPesoGr(Float pesoGr) {
        this.pesoGr = pesoGr;
    }

    @Override
    public String toString() {
        return "EspecificacionEmpacado{" +
                "idEspecificacionEmpacado=" + idEspecificacionEmpacado +
             
                ", pesoGr=" + pesoGr +
                '}';
    }
}
