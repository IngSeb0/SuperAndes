    package uniandes.edu.co.parranderos.modelo;

    import jakarta.persistence.*;
    import java.io.Serializable;

    @Embeddable
    public class InfoExtraBodegaPk implements Serializable {

        @ManyToOne
        @JoinColumn(name = "IDBODEGA", referencedColumnName = "IDBODEGA")
        private Bodega bodega;

        @ManyToOne
        @JoinColumn(name = "IDPRODUCTO", referencedColumnName = "IDPRODUCTO")
        private Producto producto;

        public InfoExtraBodegaPk() {
            super();
        }

        public InfoExtraBodegaPk(Bodega bodega, Producto producto) {
            super();
            this.bodega = bodega;
            this.producto = producto;
        }

        public Bodega getBodega() {
            return bodega;
        }

        public void setBodega(Bodega bodega) {
            this.bodega = bodega;
        }

        public Producto getProducto() {
            return producto;
        }

        public void setProducto(Producto producto) {
            this.producto = producto;
        }
    }
