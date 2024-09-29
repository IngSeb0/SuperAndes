package uniandes.edu.co.parranderos.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "INFOEXTRABODEGA")
public class InfoExtraBodega {

    @EmbeddedId
    private InfoExtraBodegaPk pk;

    private Integer totalExistencias;
    private Float costoPromedio;
    private Integer capacidadAlmacenamiento;
    private Float nivelMinimo;

    public InfoExtraBodega() {}

    public InfoExtraBodega(InfoExtraBodegaPk pk, Integer totalExistencias, Float costoPromedio, Integer capacidadAlmacenamiento, Float nivelMinimo) {
        this.pk = pk;
        this.totalExistencias = totalExistencias;
        this.costoPromedio = costoPromedio;
        this.capacidadAlmacenamiento = capacidadAlmacenamiento;
        this.nivelMinimo = nivelMinimo;
    }

    public InfoExtraBodegaPk getPk() {
        return pk;
    }

    public void setPk(InfoExtraBodegaPk pk) {
        this.pk = pk;
    }

    public Integer getTotalExistencias() {
        return totalExistencias;
    }

    public void setTotalExistencias(Integer totalExistencias) {
        this.totalExistencias = totalExistencias;
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

    public Float getNivelMinimo() {
        return nivelMinimo;
    }

    public void setNivelMinimo(Float nivelMinimo) {
        this.nivelMinimo = nivelMinimo;
    }
}
