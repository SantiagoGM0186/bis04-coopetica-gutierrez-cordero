package coopetica.modelo;

/**
 * Cuenta de ahorro. No permite que el saldo quede en negativo.
 */
public class CuentaAhorro extends Cuenta {

    /**
     * Crea una cuenta de ahorro.
     *
     * @param numeroCuenta numero que identifica la cuenta
     * @param saldoInicial saldo con el que se abre la cuenta, no puede ser negativo
     * @throws IllegalArgumentException si el numero esta vacio o el saldo inicial es negativo
     */
    public CuentaAhorro(String numeroCuenta, double saldoInicial) {
        super(numeroCuenta, saldoInicial);
    }

    /**
     * Retira un monto sin dejar el saldo en negativo.
     *
     * @param monto monto a retirar, debe ser mayor que cero
     * @throws IllegalArgumentException si el monto es cero o negativo, o si es mayor que el saldo
     */
    @Override
    public void retirar(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto del retiro debe ser mayor que cero.");
        }
        if (monto > getSaldo()) {
            throw new IllegalArgumentException("Saldo insuficiente para el retiro.");
        }
        setSaldo(getSaldo() - monto);
    }

    /**
     * Devuelve el tipo, el numero y el saldo de la cuenta.
     *
     * @return el texto con los datos de la cuenta de ahorro
     */
    @Override
    public String toString() {
        return "Cuenta de ahorro " + super.toString();
    }
}
