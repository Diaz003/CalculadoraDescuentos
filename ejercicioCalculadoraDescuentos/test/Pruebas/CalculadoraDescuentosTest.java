package Pruebas;

import coberturaCodigo.Producto;
import coberturaCodigo.CalculadoraDescuentosUtils;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraDescuentosTest {

    @Test
    public void testTotalConDescuentoElectronicaVip() {
        Producto p = new Producto("electronica", 100.0, 5); // debería aplicar 20% + 5% = 25%
        double total = CalculadoraDescuentosUtils.calcularTotalConDescuento(Collections.singletonList(p), true);
        assertEquals(75.0, total);
    }

    @Test
    public void testTotalConDescuentoRopaNoVip() {
        Producto p = new Producto("ropa", 50.0, 2); // 2 unidades, ropa = 5%
        double total = CalculadoraDescuentosUtils.calcularTotalConDescuento(Collections.singletonList(p), false);
        assertEquals(95.0, total); // 100 - 5%
    }

    @Test
    public void testLimiteMaximoDescuento() {
        // 30% es el máximo permitido incluso siendo VIP
        Producto p = new Producto("electronica", 100.0, 10); // 20% + 5% = 25%, pero >=5 → ya tiene 20% base
        double total = CalculadoraDescuentosUtils.calcularTotalConDescuento(Collections.singletonList(p), true);
        assertEquals(75.0, total); 
    }

    @Test
    public void testCategoriaInvalida() {
        Producto p = new Producto("juguetes", 30.0, 1);
        assertThrows(IllegalArgumentException.class, () -> {
            CalculadoraDescuentosUtils.calcularTotalConDescuento(Collections.singletonList(p), false);
        });
    }

    @Test
    public void testVariosProductos() {
        Producto p1 = new Producto("ropa", 20.0, 3);         
        Producto p2 = new Producto("hogar", 100.0, 2);       
        Producto p3 = new Producto("alimentacion", 10.0, 5); 
        double total = CalculadoraDescuentosUtils.calcularTotalConDescuento(
                Arrays.asList(p1, p2, p3), false
        );
        double esperado = (20 * 3 * 0.85) + (100 * 2 * 0.90) + (10 * 5 * 0.95);
        esperado = Math.round(esperado * 100.0) / 100.0;
        assertEquals(esperado, total);
    }
}
