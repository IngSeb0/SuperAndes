package uniandes.edu.co.parranderos.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Info extra ordenes")

public class InfoExtraOrden {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cantidad;
    private Float costoUnitarioCompra;

    public InfoExtraOrden() {}

    public InfoExtraOrden(Integer cantidad, Float costoUnitarioCompra) {
        this.cantidad = cantidad;
        this.costoUnitarioCompra = costoUnitarioCompra;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Float getCostoUnitarioCompra() {
        return costoUnitarioCompra;
    }

    public void setCostoUnitarioCompra(Float costoUnitarioCompra) {
        this.costoUnitarioCompra = costoUnitarioCompra;
    }

    @Override
    public String toString() {
        return "InfoExtraOrden{" +
                "cantidad=" + cantidad +
                ", costoUnitarioCompra=" + costoUnitarioCompra +
                '}';
    }
}
