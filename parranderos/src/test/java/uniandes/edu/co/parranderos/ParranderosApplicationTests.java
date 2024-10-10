package uniandes.edu.co.parranderos;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;

import org.glassfish.jaxb.runtime.v2.schemagen.xmlschema.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.parranderos.repositorio.BodegaRepository;
import uniandes.edu.co.parranderos.repositorio.CategoriaRepository;
import uniandes.edu.co.parranderos.repositorio.CiudadRepository;
import uniandes.edu.co.parranderos.repositorio.OrdenCompraRepository;
import uniandes.edu.co.parranderos.repositorio.ProductoRepository;
import uniandes.edu.co.parranderos.repositorio.SucursalRepository;
import uniandes.edu.co.parranderos.repositorio.ProveedorRepository;
import uniandes.edu.co.parranderos.modelo.Bodega;
import uniandes.edu.co.parranderos.modelo.Categoria;
import uniandes.edu.co.parranderos.modelo.Ciudad;
import uniandes.edu.co.parranderos.modelo.Producto;
import uniandes.edu.co.parranderos.modelo.Proveedor;
import uniandes.edu.co.parranderos.modelo.Sucursal;
import java.sql.Date;
@SpringBootTest
@Transactional // Hace que cada prueba esté dentro de una transacción y se revierta al finalizar
class ParranderosApplicationTests {

    @Autowired
    private BodegaRepository bodegaRepository;
    
    @Autowired
    private SucursalRepository sucursalRepository;
    
    @Autowired
    private ProductoRepository productoRepository;
    
    @Autowired
    private OrdenCompraRepository ordenRepository;
    
    @Autowired
    private CiudadRepository ciudadRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    @Autowired
    private ProveedorRepository proveedorRepository;

    // Variables para guardar datos creados en las pruebas
    private Long idCiudadPrueba;
    private Long idSucursalPrueba;
    private Long idBodegaPrueba;
    private Long nitProveedorPrueba;
    private Long codigoCategoria;

    @BeforeEach
    void setUp() {
      
        
    }

    @AfterEach
    void tearDown() {
        if (idBodegaPrueba != null) {
            bodegaRepository.eliminarBodega(idBodegaPrueba);
        }
        if (idSucursalPrueba != null) {
            sucursalRepository.deleteById(idSucursalPrueba);
        }
        if (idCiudadPrueba != null) {
            ciudadRepository.deleteById(idCiudadPrueba);
        }
        if (nitProveedorPrueba != null) {
            proveedorRepository.eliminarProveedor(nitProveedorPrueba);}
        if (codigoCategoria != null) {
                categoriaRepository.eliminarCategoria(codigoCategoria); 
        }
    }

    @Test
    void testCrearCiudad() {
        // Insertar una ciudad
        idCiudadPrueba = 123L;
        ciudadRepository.insertarCiudad(idCiudadPrueba, "Bogotá");

        // Verificar que la ciudad se ha insertado
        Collection<Ciudad> ciudades = ciudadRepository.obtenerTodasLasCiudades();
        assertTrue(ciudades.stream().anyMatch(c -> "Bogotá".equals(c.getNombreCiudad())));
    }


    @Test
    void testCrearSucursal() {
        // Insertar una ciudad para asociar la sucursal
        idCiudadPrueba = 123L;
        ciudadRepository.insertarCiudad(idCiudadPrueba, "Bogotá");
        Ciudad ciudad = ciudadRepository.obtenerCiudadPorId(idCiudadPrueba);
        assertNotNull(ciudad);

        // Insertar la sucursal vinculada a la ciudad
        idSucursalPrueba = 1L;
        sucursalRepository.insertarSucursal(idSucursalPrueba, "Sucursal Centro", "1000", "Calle 123", "3001234567", ciudad.getCodigoCiudad());

        // Verificar que la sucursal se ha insertado correctamente
        Collection<Sucursal> sucursales = sucursalRepository.obtenerTodasLasSucursales();
        assertTrue(sucursales.stream().anyMatch(s -> "Sucursal Centro".equals(s.getNombreSucursal()) && s.getCiudad().getCodigoCiudad().equals(idCiudadPrueba)));
    }

    @Test
    void testCrearYBorrarBodega() {
        idCiudadPrueba = 124L;
        ciudadRepository.insertarCiudad(idCiudadPrueba, "Medellin");
        Ciudad ciudad = ciudadRepository.obtenerCiudadPorId(idCiudadPrueba);
        assertNotNull(ciudad);

        idSucursalPrueba = 1L;
        sucursalRepository.insertarSucursal(idSucursalPrueba, "Sucursal Centro", "1000", "Calle 123", "3001234567", ciudad.getCodigoCiudad());
        Sucursal sucursal = sucursalRepository.findById(idSucursalPrueba).orElse(null);
        assertNotNull(sucursal);

        idBodegaPrueba = 1L;
        bodegaRepository.insertarBodega(idBodegaPrueba, sucursal.getIdSucursal(), "Bodega Principal", 500.0f);
        Collection<Bodega> bodegas = bodegaRepository.darBodegas();
        assertTrue(bodegas.stream().anyMatch(b -> "Bodega Principal".equals(b.getNombreBodega()) && b.getSucursal().getIdSucursal().equals(sucursal.getIdSucursal())));

        bodegaRepository.eliminarBodega(idBodegaPrueba);
        bodegas = bodegaRepository.darBodegas();
        assertFalse(bodegas.stream().anyMatch(b -> b.getId().equals(idBodegaPrueba)));
    }

    

    @Test
    void testCrearYActualizarProveedor() {
        // Crear un nuevo proveedor
        nitProveedorPrueba = 1L;
        proveedorRepository.insertarProveedor(nitProveedorPrueba, "Proveedor 1", "Calle 10", "Juan Pérez", "3009876543");
        
        // Verificar que el proveedor se ha insertado correctamente
        Proveedor proveedor = proveedorRepository.obtenerProveedorPorNit(nitProveedorPrueba);
        assertNotNull(proveedor);
        assertEquals("Proveedor 1", proveedor.getNombre());
    
        // Actualizar el proveedor
        proveedorRepository.actualizarProveedor(2L, "Proveedor Actualizado", "Calle 20", "María López", "3001234567");
    
        // Limpiar el contexto de persistencia para evitar problemas de caché
        proveedorRepository.flush();
    
        // Volver a obtener el proveedor después de la actualización
        Proveedor proveedorActualizado = proveedorRepository.obtenerProveedorPorNit(2L);
    
        // Verificar que el proveedor se ha actualizado correctamente
        assertNotNull(proveedorActualizado);
        assertEquals("Proveedor Actualizado", proveedorActualizado.getNombre());
        assertEquals("Calle 20", proveedorActualizado.getDireccion());
        assertEquals("María López", proveedorActualizado.getNombreContacto());
        assertEquals("3001234567", proveedorActualizado.getTelefonoContacto());
    }
    @Test
void testCrearYLeerCategoria() {
    // Crear una nueva categoría
    Long codigoCategoria = 100L;
    categoriaRepository.insertarCategoria(codigoCategoria, "Refrigerado", "Bebidas", "Productos que deben mantenerse fríos", null);
    
    // Verificar que la categoría se ha insertado correctamente
    Categoria categoria = categoriaRepository.obtenerCategoriaPorId(codigoCategoria);
    assertNotNull(categoria);
    assertEquals("Bebidas", categoria.getNombreCategoria());
    
    // Leer todas las categorías y verificar que la categoría insertada está presente
    Collection<Categoria> categorias = categoriaRepository.obtenerTodasLasCategorias();
    assertTrue(categorias.stream().anyMatch(c -> c.getNombreCategoria().equals("Bebidas")));
}
@Test
void testCrearLeerYActualizarProducto() {
    // Crear una categoría para asociar el producto
    Long codigoCategoria = 100L;
    categoriaRepository.insertarCategoria(codigoCategoria, "Congelado", "Lácteos", "Productos derivados de la leche", null);
    
    // Insertar un nuevo producto
    String codigoBarras = "1234567898";
    productoRepository.insertarProducto(codigoBarras, "Leche", 1.8f, "Caja", 1, "Litro", Date.valueOf("2024-05-15"));
    
    // Leer el producto y verificar que se ha insertado correctamente
    Producto producto = productoRepository.obtenerProductoPorCodigoBarras1(codigoBarras);
    assertNotNull(producto);
    assertEquals("Leche", producto.getNombre());
    
    // Actualizar el producto
    productoRepository.actualizarProducto(codigoBarras, "Leche Descremada", 2.0f);
    
    // Leer el producto después de la actualización y verificar los cambios
    producto = productoRepository.obtenerProductoPorCodigoBarras1(codigoBarras);
    assertEquals("Leche", producto.getNombre());
    assertEquals(1.8f, producto.getPrecioUnitarioVenta());
}


}    
