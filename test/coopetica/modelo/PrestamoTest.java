package coopetica.modelo;

import coopetica.logica.InteresFijo;
import coopetica.logica.InteresSimple;
import java.time.LocalDate;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Pruebas de la generacion de cuotas y del manejo de un prestamo.
 */
public class PrestamoTest {

    private static final LocalDate FECHA = LocalDate.of(2026, 11, 10);

    /**
     * Un prestamo a 12 meses genera 12 cuotas numeradas del 1 al 12.
     */
    @Test
    public void generaUnaCuotaPorMes() {
        Prestamo prestamo = new Prestamo("P-001", 1200000, 12, FECHA, new InteresSimple(0.12));
        ArrayList<Cuota> cuotas = prestamo.getCuotas();
        assertEquals(12, cuotas.size());
        assertEquals(1, cuotas.get(0).getNumeroCuota());
        assertEquals(12, cuotas.get(11).getNumeroCuota());
    }

    /**
     * Con interes simple: (1 200 000 + 144 000) / 12 = 112 000 por cuota.
     */
    @Test
    public void montoDeCuotaConInteresSimple() {
        Prestamo prestamo = new Prestamo("P-002", 1200000, 12, FECHA, new InteresSimple(0.12));
        assertEquals(112000, prestamo.getCuotas().get(0).getMonto(), 0.01);
    }

    /**
     * Con interes fijo: (1 000 000 + 100 000) / 10 = 110 000 por cuota.
     * Es la misma clase Prestamo con otra estrategia.
     */
    @Test
    public void montoDeCuotaConInteresFijo() {
        Prestamo prestamo = new Prestamo("P-003", 1000000, 10, FECHA, new InteresFijo(0.10));
        assertEquals(110000, prestamo.getCuotas().get(0).getMonto(), 0.01);
    }

    /**
     * La primera cuota vence un mes despues de la solicitud y la ultima al final del plazo.
     */
    @Test
    public void fechasDeVencimientoMensuales() {
        Prestamo prestamo = new Prestamo("P-004", 600000, 6, FECHA, new InteresFijo(0.05));
        ArrayList<Cuota> cuotas = prestamo.getCuotas();
        assertEquals(LocalDate.of(2026, 12, 10), cuotas.get(0).getFechaVencimiento());
        assertEquals(LocalDate.of(2027, 5, 10), cuotas.get(5).getFechaVencimiento());
    }

    /**
     * Pagar una cuota la marca como pagada y el prestamo sigue activo.
     * Al pagar todas, el prestamo deja de estar activo.
     */
    @Test
    public void pagarCuotas() {
        Prestamo prestamo = new Prestamo("P-005", 200000, 2, FECHA, new InteresFijo(0.10));
        prestamo.pagarCuota(1);
        assertTrue(prestamo.getCuotas().get(0).estaPagada());
        assertTrue(prestamo.estaActivo());
        prestamo.pagarCuota(2);
        assertFalse(prestamo.estaActivo());
    }

    /**
     * Pagar dos veces la misma cuota no se permite.
     */
    @Test(expected = IllegalStateException.class)
    public void cuotaYaPagadaLanzaExcepcion() {
        Prestamo prestamo = new Prestamo("P-006", 300000, 3, FECHA, new InteresFijo(0.10));
        prestamo.pagarCuota(1);
        prestamo.pagarCuota(1);
    }

    /**
     * Al anular el prestamo, sus cuotas se anulan con el.
     */
    @Test
    public void anularEliminaLasCuotas() {
        Prestamo prestamo = new Prestamo("P-007", 500000, 5, FECHA, new InteresSimple(0.12));
        prestamo.anular();
        assertFalse(prestamo.estaActivo());
        assertEquals(0, prestamo.getCuotas().size());
    }

    /**
     * Un monto de cero o negativo no se acepta.
     */
    @Test(expected = IllegalArgumentException.class)
    public void montoInvalidoLanzaExcepcion() {
        new Prestamo("P-008", 0, 12, FECHA, new InteresSimple(0.12));
    }
}
