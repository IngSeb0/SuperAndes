package uniandes.edu.co.parranderos.modelo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Info extra proveedores")

public class InfoExtraProveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cantidadExistencia;

    public InfoExtraProveedor() {}

    public InfoExtraProveedor(Integer cantidadExistencia) {
        this.cantidadExistencia = cantidadExistencia;
    }

    public Integer getCantidadExistencia() {
        return cantidadExistencia;
    }

    public void setCantidadExistencia(Integer cantidadExistencia) {
        this.cantidadExistencia = cantidadExistencia;
    }

    @Override
    public String toString() {
        return "InfoExtraProveedor{" +
                "cantidadExistencia=" + cantidadExistencia +
                '}';
    }
}
