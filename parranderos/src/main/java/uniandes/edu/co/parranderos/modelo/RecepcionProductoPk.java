package uniandes.edu.co.parranderos.modelo;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import java.io.Serializable;

@Embeddable
public class RecepcionProductoPk implements Serializable {

    @JoinColumn(name = "ID_RECEPCION", referencedColumnName = "IDRECEPCION")
    private Long idRecepcion;

    @JoinColumn(name = "CODIGO_BARRAS", referencedColumnName = "CODIGOBARRAS")
    private String codigoBarras;

    public RecepcionProductoPk() {}

    public RecepcionProductoPk(Long idRecepcion, String codigoBarras) {
        this.idRecepcion = idRecepcion;
        this.codigoBarras = codigoBarras;
    }

    // Getters y Setters
    public Long getIdRecepcion() {
        return idRecepcion;
    }

    public void setIdRecepcion(Long idRecepcion) {
        this.idRecepcion = idRecepcion;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }
}
