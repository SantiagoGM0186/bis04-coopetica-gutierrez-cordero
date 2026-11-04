package coopetica.modelo;

/**
 * Cuenta de un asociado de CoopeTica. Es la clase base de la jerarquia de
 * cuentas: guarda el numero y el saldo, y define las operaciones comunes.
 * Cada tipo de cuenta decide como se hace un retiro.
 */
public abstract class Cuenta {

    private String numeroCuenta;
    private double saldo;

    /**
     * Crea una cuenta con su numero y su saldo inicial.
     *
     * @param numeroCuenta numero que identifica la cuenta
     * @param saldoInicial saldo con el que se abre la cuenta, no puede ser negativo
     * @throws IllegalArgumentException si el numero esta vacio o el saldo inicial es negativo
     */
    public Cuenta(String numeroCuenta, double saldoInicial) {
        if (numeroCuenta == null || numeroCuenta.trim().isEmpty()) {
            throw new IllegalArgumentException("El numero de cuenta es obligatorio.");
        }
        if (saldoInicial < 0) {
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo.");
        }
        this.numeroCuenta = numeroCuenta.trim();
        this.saldo = saldoInicial;
    }

    /**
     * Devuelve el numero de la cuenta.
     *
     * @return el numero de la cuenta
     */
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    /**
     * Devuelve el saldo actual de la cuenta.
     *
     * @return el saldo actual
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     * Cambia el saldo de la cuenta. Solo lo usan las clases hijas al hacer un retiro.
     *
     * @param saldo nuevo saldo de la cuenta
     */
    protected void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    /**
     * Deposita un monto en la cuenta.
     *
     * @param monto monto a depositar, debe ser mayor que cero
     * @throws IllegalArgumentException si el monto es cero o negativo
     */
    public void depositar(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto del deposito debe ser mayor que cero.");
        }
        saldo += monto;
    }

    /**
     * Retira un monto de la cuenta. Cada tipo de cuenta define su propia regla.
     *
     * @param monto monto a retirar, debe ser mayor que cero
     * @throws IllegalArgumentException si el monto no es valido o el retiro no se permite
     */
    public abstract void retirar(double monto);

    /**
     * Devuelve el numero y el saldo de la cuenta.
     *
     * @return el texto con los datos de la cuenta
     */
    @Override
    public String toString() {
        return numeroCuenta + " - Saldo: " + saldo;
    }
}
