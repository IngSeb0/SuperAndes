package uniandes.edu.co.parranderos.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Info Extra Bodegas")
public class InfoExtraBodega {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer totalExistencia;
    private Float costoPromedio;
    private Integer capacidadAlmacenamiento;
    private Integer nivelMinimoReorden;

    public InfoExtraBodega() {}

    public InfoExtraBodega(Integer totalExistencia, Float costoPromedio, Integer capacidadAlmacenamiento, Integer nivelMinimoReorden) {
        this.totalExistencia = totalExistencia;
        this.costoPromedio = costoPromedio;
        this.capacidadAlmacenamiento = capacidadAlmacenamiento;
        this.nivelMinimoReorden = nivelMinimoReorden;
    }

    public Integer getTotalExistencia() {
        return totalExistencia;
    }

    public void setTotalExistencia(Integer totalExistencia) {
        this.totalExistencia = totalExistencia;
    }

    public Float getCostoPromedio() {
        return costoPromedio;
    }

    public void setCostoPromedio(Float costoPromedio) {
        this.costoPromedio = costoPromedio;
    }

    public Integer getCapacidadAlmacenamiento() {
        return capacidadAlmacenamiento;
    }

    public void setCapacidadAlmacenamiento(Integer capacidadAlmacenamiento) {
        this.capacidadAlmacenamiento = capacidadAlmacenamiento;
    }

    public Integer getNivelMinimoReorden() {
        return nivelMinimoReorden;
    }

    public void setNivelMinimoReorden(Integer nivelMinimoReorden) {
        this.nivelMinimoReorden = nivelMinimoReorden;
    }

    @Override
    public String toString() {
        return "InfoExtraBodega{" +
                "totalExistencia=" + totalExistencia +
                ", costoPromedio=" + costoPromedio +
                ", capacidadAlmacenamiento=" + capacidadAlmacenamiento +
                ", nivelMinimoReorden=" + nivelMinimoReorden +
                '}';
    }
}
