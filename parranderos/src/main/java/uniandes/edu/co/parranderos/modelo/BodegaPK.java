package uniandes.edu.co.parranderos.modelo;

import java.io.Serializable;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class BodegaPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "SUCURSAL_IDSUCURSAL", referencedColumnName = "IDSUCURSAL")
    private Sucursal sucursal;

    public BodegaPK() {}

    public BodegaPK(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }
}
