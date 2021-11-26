package domain;

public class TarjetaCredito {
    float limite;
    int creditoMensualAcumulado;
    Cuenta cuenta;

    public float getLimite() {
        return limite;
    }

    public void setLimite(float limite) {
        this.limite = limite;
    }

    public int getCreditoMensualAcumulado() {
        return creditoMensualAcumulado;
    }

    public void setCreditoMensualAcumulado(int creditoMensualAcumulado) {
        this.creditoMensualAcumulado = creditoMensualAcumulado;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public TarjetaCredito(float limite, Cuenta cuenta) {
        this.limite = limite;
        this.cuenta = cuenta;
    }

    void realizarCompra(Integer valor){
        if(valor.floatValue() + creditoMensualAcumulado > limite){
            throw new LimiteTarjetaCreditoExcedidoException();
        } else{
            creditoMensualAcumulado += valor;
        }
    }

    public void aplicarCreditoMensual(){
        try{
            cuenta.deducirSaldo(creditoMensualAcumulado);
        } catch (NoHayDineroSuficienteException e){
            float cubierto = cuenta.getMontoGastable();
            cuenta.saldo = cuenta.saldo - (int) cubierto;
            cuenta.deudaTarjetaCredito += creditoMensualAcumulado - cubierto;
        } finally
        {
            creditoMensualAcumulado = 0;
        }
    }
}
