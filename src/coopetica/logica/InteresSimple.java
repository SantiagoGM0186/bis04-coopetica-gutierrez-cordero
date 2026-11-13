package coopetica.logica;

/**
 * Interes simple con una tasa anual. El interes depende del plazo:
 * interes = monto x tasa anual x meses / 12.
 */
public class InteresSimple implements EstrategiaInteres {

    private double tasaAnual;

    /**
     * Crea la politica de interes simple.
     *
     * @param tasaAnual tasa de interes anual, por ejemplo 0.12 para un 12 %
     * @throws IllegalArgumentException si la tasa es negativa
     */
    public InteresSimple(double tasaAnual) {
        if (tasaAnual < 0) {
            throw new IllegalArgumentException("La tasa de interes no puede ser negativa.");
        }
        this.tasaAnual = tasaAnual;
    }

    /**
     * Calcula el interes simple: monto x tasa anual x meses / 12.
     *
     * @param monto monto del prestamo
     * @param plazoMeses cantidad de meses del prestamo
     * @return el interes total del prestamo
     */
    @Override
    public double calcularInteres(double monto, int plazoMeses) {
        return monto * tasaAnual * plazoMeses / 12;
    }
}
