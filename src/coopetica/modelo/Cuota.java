package coopetica.modelo;

import java.time.LocalDate;

/**
 * Cuota mensual de un prestamo. Solo existe dentro de su prestamo.
 */
public class Cuota {

    private int numeroCuota;
    private double monto;
    private LocalDate fechaVencimiento;
    private boolean pagada;

    /**
     * Crea una cuota sin pagar.
     *
     * @param numeroCuota numero de la cuota dentro del prestamo, empieza en 1
     * @param monto monto de la cuota
     * @param fechaVencimiento fecha en que vence la cuota
     */
    public Cuota(int numeroCuota, double monto, LocalDate fechaVencimiento) {
        this.numeroCuota = numeroCuota;
        this.monto = monto;
        this.fechaVencimiento = fechaVencimiento;
        this.pagada = false;
    }

    /**
     * Devuelve el numero de la cuota.
     *
     * @return el numero de la cuota
     */
    public int getNumeroCuota() {
        return numeroCuota;
    }

    /**
     * Devuelve el monto de la cuota.
     *
     * @return el monto de la cuota
     */
    public double getMonto() {
        return monto;
    }

    /**
     * Devuelve la fecha de vencimiento de la cuota.
     *
     * @return la fecha de vencimiento
     */
    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * Indica si la cuota ya fue pagada.
     *
     * @return true si la cuota esta pagada
     */
    public boolean estaPagada() {
        return pagada;
    }

    /**
     * Marca la cuota como pagada.
     *
     * @throws IllegalStateException si la cuota ya estaba pagada
     */
    public void marcarPagada() {
        if (pagada) {
            throw new IllegalStateException("La cuota " + numeroCuota + " ya esta pagada.");
        }
        pagada = true;
    }

    /**
     * Devuelve los datos de la cuota.
     *
     * @return el texto con el numero, monto, vencimiento y estado de la cuota
     */
    @Override
    public String toString() {
        String estado = pagada ? "Pagada" : "Pendiente";
        return "Cuota " + numeroCuota + " - Monto: " + monto
                + " - Vence: " + fechaVencimiento + " - " + estado;
    }
}
