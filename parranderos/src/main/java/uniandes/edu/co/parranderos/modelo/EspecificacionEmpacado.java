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
    private Long idEspecificacionEmpacado;  // Agregamos el campo id

    private Float volumenCm3;
    private Float pesoGr;

    public EspecificacionEmpacado() {}

    public EspecificacionEmpacado(Float volumenCm3, Float pesoGr) {
        this.volumenCm3 = volumenCm3;
        this.pesoGr = pesoGr;
    }

    public Long getIdEspecificacionEmpacado() {
        return idEspecificacionEmpacado;
    }

    public void setIdEspecificacionEmpacado(Long idEspecificacionEmpacado) {
        this.idEspecificacionEmpacado = idEspecificacionEmpacado;
    }

    public Float getVolumenCm3() {
        return volumenCm3;
    }

    public void setVolumenCm3(Float volumenCm3) {
        this.volumenCm3 = volumenCm3;
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
                ", volumenCm3=" + volumenCm3 +
                ", pesoGr=" + pesoGr +
                '}';
    }
}
