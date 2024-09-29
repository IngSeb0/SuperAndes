package uniandes.edu.co.parranderos.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "DETALLECOSTOBODEGA")
public class DetalleCostoBodega {

    @EmbeddedId
    private DetalleCostoBodegaPk pk;

    private Float costo;
    private Integer cantidadExistencia;

    public DetalleCostoBodega() {}

    public DetalleCostoBodega(DetalleCostoBodegaPk pk, Float costo, Integer cantidadExistencia) {
        this.pk = pk;
        this.costo = costo;
        this.cantidadExistencia = cantidadExistencia;
    }

    public DetalleCostoBodegaPk getPk() {
        return pk;
    }

    public void setPk(DetalleCostoBodegaPk pk) {
        this.pk = pk;
    }

    public Float getCosto() {
        return costo;
    }

    public void setCosto(Float costo) {
        this.costo = costo;
    }

    public Integer getCantidadExistencia() {
        return cantidadExistencia;
    }

    public void setCantidadExistencia(Integer cantidadExistencia) {
        this.cantidadExistencia = cantidadExistencia;
    }
}
