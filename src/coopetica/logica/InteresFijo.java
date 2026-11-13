package coopetica.logica;

/**
 * Interes fijo. Se cobra un solo porcentaje sobre el monto, sin importar el plazo:
 * interes = monto x porcentaje.
 */
public class InteresFijo implements EstrategiaInteres {

    private double porcentaje;

    /**
     * Crea la politica de interes fijo.
     *
     * @param porcentaje porcentaje que se cobra sobre el monto, por ejemplo 0.10 para un 10 %
     * @throws IllegalArgumentException si el porcentaje es negativo
     */
    public InteresFijo(double porcentaje) {
        if (porcentaje < 0) {
            throw new IllegalArgumentException("El porcentaje de interes no puede ser negativo.");
        }
        this.porcentaje = porcentaje;
    }

    /**
     * Calcula el interes fijo: monto x porcentaje.
     *
     * @param monto monto del prestamo
     * @param plazoMeses cantidad de meses del prestamo, no cambia el resultado
     * @return el interes total del prestamo
     */
    @Override
    public double calcularInteres(double monto, int plazoMeses) {
        return monto * porcentaje;
    }
}
