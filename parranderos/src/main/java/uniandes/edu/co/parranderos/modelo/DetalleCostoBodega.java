package uniandes.edu.co.parranderos.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "DETALLECOSTOBODEGA")
public class DetalleCostoBodega {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idDetalleCosto;

    private Float costoUnitarioBodega;
    private Integer cantidadExistencia;
    private Long infoExtraBodegaId;

    public DetalleCostoBodega() {
    }

    public DetalleCostoBodega(Float costoUnitarioBodega, Integer cantidadExistencia, Long infoExtraBodegaId) {
        this.costoUnitarioBodega = costoUnitarioBodega;
        this.cantidadExistencia = cantidadExistencia;
        this.infoExtraBodegaId = infoExtraBodegaId;
    }

    public Long getIdDetalleCosto() {
        return idDetalleCosto;
    }

    public void setIdDetalleCosto(Long idDetalleCosto) {
        this.idDetalleCosto = idDetalleCosto;
    }

    public Float getCostoUnitarioBodega() {
        return costoUnitarioBodega;
    }

    public void setCostoUnitarioBodega(Float costoUnitarioBodega) {
        this.costoUnitarioBodega = costoUnitarioBodega;
    }

    public Integer getCantidadExistencia() {
        return cantidadExistencia;
    }

    public void setCantidadExistencia(Integer cantidadExistencia) {
        this.cantidadExistencia = cantidadExistencia;
    }

    public Long getInfoExtraBodegaId() {
        return infoExtraBodegaId;
    }

    public void setInfoExtraBodegaId(Long infoExtraBodegaId) {
        this.infoExtraBodegaId = infoExtraBodegaId;
    }

    @Override
    public String toString() {
        return "DetalleCostoBodega{" +
                "idDetalleCosto=" + idDetalleCosto +
                ", costoUnitarioBodega=" + costoUnitarioBodega +
                ", cantidadExistencia=" + cantidadExistencia +
                ", infoExtraBodegaId=" + infoExtraBodegaId +
                '}';
    }
}
