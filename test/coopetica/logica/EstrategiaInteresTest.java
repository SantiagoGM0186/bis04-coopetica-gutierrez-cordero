package coopetica.logica;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Pruebas de las dos politicas de interes.
 */
public class EstrategiaInteresTest {

    /**
     * El interes simple depende del plazo: 1 200 000 al 12 % anual por 12 meses da 144 000.
     */
    @Test
    public void interesSimpleDependeDelPlazo() {
        EstrategiaInteres estrategia = new InteresSimple(0.12);
        assertEquals(144000, estrategia.calcularInteres(1200000, 12), 0.01);
        assertEquals(72000, estrategia.calcularInteres(1200000, 6), 0.01);
    }

    /**
     * El interes fijo no depende del plazo: 10 % de 1 000 000 da 100 000 en cualquier plazo.
     */
    @Test
    public void interesFijoNoDependeDelPlazo() {
        EstrategiaInteres estrategia = new InteresFijo(0.10);
        assertEquals(100000, estrategia.calcularInteres(1000000, 12), 0.01);
        assertEquals(100000, estrategia.calcularInteres(1000000, 24), 0.01);
    }

    /**
     * Una tasa negativa no se acepta.
     */
    @Test(expected = IllegalArgumentException.class)
    public void tasaNegativaLanzaExcepcion() {
        new InteresSimple(-0.05);
    }
}
