package coopetica.modelo;

/**
 * Cuenta corriente. Permite que el saldo quede en negativo hasta un limite de sobregiro.
 */
public class CuentaCorriente extends Cuenta {

    private double limiteSobregiro;

    /**
     * Crea una cuenta corriente.
     *
     * @param numeroCuenta numero que identifica la cuenta
     * @param saldoInicial saldo con el que se abre la cuenta, no puede ser negativo
     * @param limiteSobregiro monto maximo que el saldo puede quedar en negativo, no puede ser negativo
     * @throws IllegalArgumentException si algun dato no es valido
     */
    public CuentaCorriente(String numeroCuenta, double saldoInicial, double limiteSobregiro) {
        super(numeroCuenta, saldoInicial);
        if (limiteSobregiro < 0) {
            throw new IllegalArgumentException("El limite de sobregiro no puede ser negativo.");
        }
        this.limiteSobregiro = limiteSobregiro;
    }

    /**
     * Devuelve el limite de sobregiro de la cuenta.
     *
     * @return el limite de sobregiro
     */
    public double getLimiteSobregiro() {
        return limiteSobregiro;
    }

    /**
     * Retira un monto permitiendo saldo negativo hasta el limite de sobregiro.
     *
     * @param monto monto a retirar, debe ser mayor que cero
     * @throws IllegalArgumentException si el monto es cero o negativo, o si supera el limite de sobregiro
     */
    @Override
    public void retirar(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto del retiro debe ser mayor que cero.");
        }
        if (getSaldo() - monto < -limiteSobregiro) {
            throw new IllegalArgumentException("El retiro supera el limite de sobregiro.");
        }
        setSaldo(getSaldo() - monto);
    }

    /**
     * Devuelve el tipo, el numero, el saldo y el limite de sobregiro de la cuenta.
     *
     * @return el texto con los datos de la cuenta corriente
     */
    @Override
    public String toString() {
        return "Cuenta corriente " + super.toString() + " - Limite de sobregiro: " + limiteSobregiro;
    }
}
