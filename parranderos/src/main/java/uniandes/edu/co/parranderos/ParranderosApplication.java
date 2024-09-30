package uniandes.edu.co.parranderos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uniandes.edu.co.parranderos.modelo.*;
import uniandes.edu.co.parranderos.repositorio.*;

import java.util.Collection;

@SpringBootApplication
public class ParranderosApplication implements CommandLineRunner {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private BodegaRepository bodegaRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private DetalleCostoBodegaRepository detalleCostoBodegaRepository;

    @Autowired
    private EspecificacionEmpacadoRepository especificacionEmpacadoRepository;

    @Autowired
    private InfoExtraBodegaRepository infoExtraBodegaRepository;

    @Autowired
    private InfoExtraOrdenRepository infoExtraOrdenRepository;

    @Autowired
    private InfoExtraProveedorRepository infoExtraProveedorRepository;

    @Autowired
    private InfoExtraVentaRepository infoExtraVentaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private OrdenCompraRepository ordenCompraRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private SucursalRepository sucursalRepository;

    public static void main(String[] args) {
        SpringApplication.run(ParranderosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Clientes
        Collection<Cliente> clientes = clienteRepository.obtenerTodosLosClientes();
        System.out.println("Clientes:");
        for (Cliente c : clientes) {
            System.out.println(c);
        }

        // Ventas
        Collection<Venta> ventas = ventaRepository.obtenerTodasLasVentas();
        System.out.println("\nVentas:");
        for (Venta v : ventas) {
            System.out.println(v);
        }

        // Bodegas
        Collection<Bodega> bodegas = bodegaRepository.darBodegas();
        System.out.println("\nBodegas:");
        for (Bodega b : bodegas) {
            System.out.println(b);
        }

        // Categorías
        Collection<Categoria> categorias = categoriaRepository.obtenerTodasLasCategorias();
        System.out.println("\nCategorías:");
        for (Categoria cat : categorias) {
            System.out.println(cat);
        }

        // Detalles Costo Bodega
        Collection<DetalleCostoBodega> detallesCosto = detalleCostoBodegaRepository.obtenerTodosLosDetallesCostoBodega();
        System.out.println("\nDetalles de Costo de Bodega:");
        for (DetalleCostoBodega detalle : detallesCosto) {
            System.out.println(detalle);
        }

        // Especificaciones Empacado
        Collection<EspecificacionEmpacado> especificacionesEmpacado = especificacionEmpacadoRepository.obtenerTodasLasEspecificaciones();
        System.out.println("\nEspecificaciones de Empacado:");
        for (EspecificacionEmpacado esp : especificacionesEmpacado) {
            System.out.println(esp);
        }

        // Info Extra Bodega
        Collection<InfoExtraBodega> infoExtraBodegas = infoExtraBodegaRepository.obtenerTodaLaInfoExtraBodega();
        System.out.println("\nInfo Extra Bodegas:");
        for (InfoExtraBodega info : infoExtraBodegas) {
            System.out.println(info);
        }

        // Info Extra Orden
        Collection<InfoExtraOrden> infoExtraOrdenes = infoExtraOrdenRepository.obtenerTodaLaInfoExtraOrden();
        System.out.println("\nInfo Extra Ordenes:");
        for (InfoExtraOrden orden : infoExtraOrdenes) {
            System.out.println(orden);
        }

        // Info Extra Proveedor
        Collection<InfoExtraProveedor> infoExtraProveedores = infoExtraProveedorRepository.obtenerTodaInfoExtraProveedor();
        System.out.println("\nInfo Extra Proveedores:");
        for (InfoExtraProveedor proveedor : infoExtraProveedores) {
            System.out.println(proveedor);
        }

        // Info Extra Venta
        Collection<InfoExtraVenta> infoExtraVentas = infoExtraVentaRepository.obtenerTodaLaInfoExtraVenta();
        System.out.println("\nInfo Extra Ventas:");
        for (InfoExtraVenta venta : infoExtraVentas) {
            System.out.println(venta);
        }

        // Productos
        Collection<Producto> productos = productoRepository.obtenerTodosLosProductos();
        System.out.println("\nProductos:");
        for (Producto prod : productos) {
            System.out.println(prod);
        }

        // Órdenes de Compra
        Collection<OrdenCompra> ordenesCompra = ordenCompraRepository.obtenerTodasLasOrdenes();
        System.out.println("\nÓrdenes de Compra:");
        for (OrdenCompra ordenCompra : ordenesCompra) {
            System.out.println(ordenCompra);
        }

        // Proveedores
        Collection<Proveedor> proveedores = proveedorRepository.obtenerTodosLosProveedores();
        System.out.println("\nProveedores:");
        for (Proveedor prov : proveedores) {
            System.out.println(prov);
        }

        // Sucursales
        Collection<Sucursal> sucursales = sucursalRepository.obtenerTodasLasSucursales();
        System.out.println("\nSucursales:");
        for (Sucursal suc : sucursales) {
            System.out.println(suc);
        }
    }
}
