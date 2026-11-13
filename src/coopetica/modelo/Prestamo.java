package coopetica.modelo;

import coopetica.logica.EstrategiaInteres;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Prestamo de un asociado. Al crearse genera automaticamente una cuota por
 * cada mes del plazo. Las cuotas pertenecen al prestamo: si el prestamo se
 * anula, sus cuotas se anulan con el.
 */
public class Prestamo {

    private String numeroPrestamo;
    private double monto;
    private int plazoMeses;
    private LocalDate fechaSolicitud;
    private boolean activo;
    private ArrayList<Cuota> cuotas;

    /**
     * Crea un prestamo activo y genera sus cuotas. El interes total se calcula
     * con la estrategia recibida y se reparte en partes iguales entre las cuotas.
     * La primera cuota vence un mes despues de la fecha de solicitud.
     *
     * @param numeroPrestamo numero que identifica el prestamo
     * @param monto monto solicitado, debe ser mayor que cero
     * @param plazoMeses cantidad de meses, debe ser mayor que cero
     * @param fechaSolicitud fecha en que se solicita el prestamo
     * @param estrategia politica de interes con la que se calculan las cuotas
     * @throws IllegalArgumentException si algun dato no es valido
     */
    public Prestamo(String numeroPrestamo, double monto, int plazoMeses,
            LocalDate fechaSolicitud, EstrategiaInteres estrategia) {
        if (numeroPrestamo == null || numeroPrestamo.trim().isEmpty()) {
            throw new IllegalArgumentException("El numero de prestamo es obligatorio.");
        }
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto del prestamo debe ser mayor que cero.");
        }
        if (plazoMeses <= 0) {
            throw new IllegalArgumentException("El plazo debe ser de al menos un mes.");
        }
        if (fechaSolicitud == null) {
            throw new IllegalArgumentException("La fecha de solicitud es obligatoria.");
        }
        if (estrategia == null) {
            throw new IllegalArgumentException("Debe indicar la politica de interes.");
        }
        this.numeroPrestamo = numeroPrestamo.trim();
        this.monto = monto;
        this.plazoMeses = plazoMeses;
        this.fechaSolicitud = fechaSolicitud;
        this.activo = true;
        this.cuotas = new ArrayList<>();
        generarCuotas(estrategia);
    }

    private void generarCuotas(EstrategiaInteres estrategia) {
        double total = monto + estrategia.calcularInteres(monto, plazoMeses);
        double montoCuota = total / plazoMeses;
        for (int i = 1; i <= plazoMeses; i++) {
            cuotas.add(new Cuota(i, montoCuota, fechaSolicitud.plusMonths(i)));
        }
    }

    /**
     * Devuelve el numero del prestamo.
     *
     * @return el numero del prestamo
     */
    public String getNumeroPrestamo() {
        return numeroPrestamo;
    }

    /**
     * Devuelve el monto solicitado.
     *
     * @return el monto del prestamo, sin intereses
     */
    public double getMonto() {
        return monto;
    }

    /**
     * Devuelve el plazo del prestamo.
     *
     * @return la cantidad de meses
     */
    public int getPlazoMeses() {
        return plazoMeses;
    }

    /**
     * Devuelve la fecha en que se solicito el prestamo.
     *
     * @return la fecha de solicitud
     */
    public LocalDate getFechaSolicitud() {
        return fechaSolicitud;
    }

    /**
     * Indica si el prestamo sigue activo.
     *
     * @return true si el prestamo esta activo
     */
    public boolean estaActivo() {
        return activo;
    }

    /**
     * Devuelve una copia de la lista de cuotas, para que no se puedan agregar
     * ni quitar cuotas desde fuera del prestamo.
     *
     * @return la lista de cuotas del prestamo
     */
    public ArrayList<Cuota> getCuotas() {
        return new ArrayList<>(cuotas);
    }

    /**
     * Marca una cuota como pagada. Cuando todas las cuotas estan pagadas,
     * el prestamo deja de estar activo.
     *
     * @param numeroCuota numero de la cuota que se paga
     * @throws IllegalStateException si el prestamo no esta activo o la cuota ya estaba pagada
     * @throws IllegalArgumentException si la cuota no existe
     */
    public void pagarCuota(int numeroCuota) {
        if (!activo) {
            throw new IllegalStateException("El prestamo no esta activo.");
        }
        Cuota encontrada = null;
        for (Cuota c : cuotas) {
            if (c.getNumeroCuota() == numeroCuota) {
                encontrada = c;
            }
        }
        if (encontrada == null) {
            throw new IllegalArgumentException("No existe la cuota " + numeroCuota + ".");
        }
        encontrada.marcarPagada();

        boolean todasPagadas = true;
        for (Cuota c : cuotas) {
            if (!c.estaPagada()) {
                todasPagadas = false;
            }
        }
        if (todasPagadas) {
            activo = false;
        }
    }

    /**
     * Anula el prestamo. Al anularlo se eliminan tambien sus cuotas.
     */
    public void anular() {
        activo = false;
        cuotas.clear();
    }

    /**
     * Devuelve los datos principales del prestamo.
     *
     * @return el texto con el numero, monto, plazo, fecha y estado del prestamo
     */
    @Override
    public String toString() {
        String estado = activo ? "Activo" : "Inactivo";
        return "Prestamo " + numeroPrestamo + " - Monto: " + monto + " - Plazo: "
                + plazoMeses + " meses - Solicitado: " + fechaSolicitud + " - " + estado;
    }
}
