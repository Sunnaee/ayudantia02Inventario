import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class inventarioTiendaTest {

    Object[][] inventario;

    @BeforeEach
    void setUp() {
        System.out.println("Iniciando matriz para una prueba...");
        inventario = new Object[10][3];
    }

    @AfterEach
    void tearDown() {
        System.out.println("Limpiando matriz luego de una prueba...");
        inventario = null;
    }

    @Test
    void existeProducto() { //pedirID() sólo aceptará int como valor a guardar para los productos.
        assertFalse(inventarioTienda.existeProducto(inventario,12)); //producto inexistente.
        inventario[0][0] = 10;
        assertTrue(inventarioTienda.existeProducto(inventario,10)); //producto existente.
    }

    @Test
    void obtenerFila() { //función llamada solamente si ya se comprobó que existe un producto mediante ID. Además, solo se pueden guardar en orden de puestos vacíos.
        inventario[0][0] = 10;
        inventario[1][0] = 4;
        inventario[2][0] = 7;
        assertEquals(0,inventarioTienda.obtenerFila(inventario,10));
        assertEquals(1,inventarioTienda.obtenerFila(inventario,4));
        assertEquals(2,inventarioTienda.obtenerFila(inventario,7));
    }

    @Test
    void agregarUnidades() {
        inventario[0][0] = 10; inventario[0][1] = "Producto 1"; inventario[0][2] = 5;
        assertEquals(7,inventarioTienda.agregarUnidades(inventario,2,0)[0][2]);
    }

    @Test
    void nuevoProducto() {
        assertEquals(10,inventarioTienda.nuevoProducto(inventario,10,"Producto 1",5)[0][0]);
        assertEquals("Producto 1",inventarioTienda.nuevoProducto(inventario,10,"Producto 1",5)[0][1]);
        assertEquals(5,inventarioTienda.nuevoProducto(inventario,10,"Producto 1",5)[0][2]);
    }

    @Test
    void restarUnidades() {
        inventario[0][0] = 10; inventario[0][1] = "Producto 1"; inventario[0][2] = 5;
        assertEquals(2,inventarioTienda.restarUnidades(inventario,10,3,0)[0][2]);
    }

    @Test
    void eliminarProducto() {
        inventario[0][0] = 10; inventario[0][1] = "Producto 1"; inventario[0][2] = 5;
        assertEquals(null,inventarioTienda.eliminarProducto(inventario,0)[0][0]);
        assertEquals(null,inventarioTienda.eliminarProducto(inventario,0)[0][1]);
        assertEquals(null,inventarioTienda.eliminarProducto(inventario,0)[0][2]);
    }

    @Test
    void restarProductos() {
        inventario[0][0] = 10; inventario[0][1] = "Producto 1"; inventario[0][2] = 5;
        assertEquals(5,inventarioTienda.restarProductos(inventario,10,7)[0][2]); //mayor cantidad de los que hay disponibles.
        assertEquals(3,inventarioTienda.restarProductos(inventario,10,2)[0][2]); //cantidad menor a las disponibles.
        assertEquals(null,inventarioTienda.restarProductos(inventario,10,3)[0][2]); //de la prueba anterior quedaron 3, así que para probar si se eliminan hay que restar 3.
    }

    @Test
    void disponibilidadProducto() {
        inventario[0][0] = 10; inventario[0][1] = "Producto 1"; inventario[0][2] = 5;
        assertEquals(5,inventarioTienda.disponibilidadProducto(inventario,10)); //producto existente.
        assertEquals(0,inventarioTienda.disponibilidadProducto(inventario,12)); //producto inexistente.
    }

    @Test
    void continuarMenu() {
        assertTrue(inventarioTienda.continuarMenu(1));
        assertFalse(inventarioTienda.continuarMenu(2));
    }

}