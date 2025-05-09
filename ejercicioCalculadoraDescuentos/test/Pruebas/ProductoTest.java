package Pruebas;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import coberturaCodigo.CalculadoraDescuentosUtils;
import coberturaCodigo.Producto;

public class ProductoTest {

    @Test
    public void testProductoValido() {
        Producto p = new Producto("ropa", 50.0, 3);
        assertEquals("ropa", p.categoria);
        assertEquals(50.0, p.precio);
        assertEquals(3, p.cantidad);
    }

    @Test
    public void testProductoConPrecioCero() {
        Producto p = new Producto("ropa", 0.0, 5);
        assertEquals(0.0, p.precio);
    }

    @Test
    public void testProductoConCantidadCero() {
        Producto p = new Producto("ropa", 50.0, 0);
        assertEquals(0, p.cantidad);
    }

    @Test
    public void testProductoConPrecioNegativo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Producto("ropa", -10.0, 2);
        });
    }

    @Test
    public void testProductoConCantidadNegativa() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Producto("ropa", 10.0, -5);
        });
    }

    @Test
    public void testCategoriaConMayusculas() {
        Producto p = new Producto("ELECTRONICA", 100.0, 1);
        double total = CalculadoraDescuentosUtils.calcularTotalConDescuento(Collections.singletonList(p), false);
        assertEquals(90.0, total); // 10% de descuento
    }

    @Test
    public void testCategoriaInvalida() {
        Producto p = new Producto("videojuegos", 100.0, 2);
        assertThrows(IllegalArgumentException.class, () -> {
            CalculadoraDescuentosUtils.calcularTotalConDescuento(Collections.singletonList(p), false);
        });
    }
}
