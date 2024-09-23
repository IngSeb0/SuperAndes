package uniandes.edu.co.parranderos.modelo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Detalles Costo Bodega")
public class DetalleCostoBodega {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Float costoUnitarioBodega;
    private Integer cantidadExistencia;
    

    public DetalleCostoBodega() {}

    public DetalleCostoBodega(Float costoUnitarioBodega, Integer cantidadExistencia) {
        this.costoUnitarioBodega = costoUnitarioBodega;
        this.cantidadExistencia = cantidadExistencia;
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

    @Override
    public String toString() {
        return "DetalleCostoBodega{" +
                "costoUnitarioBodega=" + costoUnitarioBodega +
                ", cantidadExistencia=" + cantidadExistencia +
                '}';
    }
}

