package uniandes.edu.co.parranderos.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "DETALLECOSTOBODEGA")
public class DetalleCostoBodega {

    @EmbeddedId
    private DetalleCostoBodegaPk pk;

    private Float costoUnitarioBodega;
    private Integer cantidadExistencia;

    public DetalleCostoBodega() {}

    public DetalleCostoBodega(DetalleCostoBodegaPk pk, Float costoUnitarioBodega, Integer cantidadExistencia) {
        this.pk = pk;
        this.costoUnitarioBodega = costoUnitarioBodega;
        this.cantidadExistencia = cantidadExistencia;
    }

    public DetalleCostoBodegaPk getPk() {
        return pk;
    }

    public void setPk(DetalleCostoBodegaPk pk) {
        this.pk = pk;
    }

    public Float getCosto() {
        return costoUnitarioBodega;
    }

    public void setCosto(Float costoUnitarioBodega) {
        this.costoUnitarioBodega = costoUnitarioBodega;
    }

    public Integer getCantidadExistencia() {
        return cantidadExistencia;
    }

    public void setCantidadExistencia(Integer cantidadExistencia) {
        this.cantidadExistencia = cantidadExistencia;
    }
}
