package coopetica.logica;

/**
 * Politica para calcular el interes de un prestamo. Cada clase que la
 * implementa es una forma distinta de calcularlo, y se pueden intercambiar
 * sin cambiar la clase Prestamo.
 */
public interface EstrategiaInteres {

    /**
     * Calcula el interes total de un prestamo.
     *
     * @param monto monto del prestamo
     * @param plazoMeses cantidad de meses del prestamo
     * @return el interes total que se suma al monto
     */
    double calcularInteres(double monto, int plazoMeses);
}
