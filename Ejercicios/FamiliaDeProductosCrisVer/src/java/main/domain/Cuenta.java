package java.main.domain;

public class Cuenta {
    TipoCuenta tipo;
    Cliente dueño;
    int saldo;
    int deudaTarjetaCredito;

    public Cuenta() {

    }

    public TipoCuenta getTipo() {
        return tipo;
    }

    public Cliente getDueño() {
        return dueño;
    }

    public void setDueño(Cliente dueño) {
        this.dueño = dueño;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public int getDeudaTarjetaCredito() {
        return deudaTarjetaCredito;
    }

    public void setDeudaTarjetaCredito(int deudaTarjetaCredito) {
        this.deudaTarjetaCredito = deudaTarjetaCredito;
    }

    public void setTipo(TipoCuenta tipo) {
        this.tipo = tipo;
    }

    public void deducirSaldo(int valorADeducir){
        if (this.saldo > valorADeducir) {
            saldo = saldo - valorADeducir;
        } else {
            if (this.saldo + tipo.getDescubiertoRestante() > valorADeducir) {
                tipo.descubiertoPermitido = tipo.descubiertoPermitido - (valorADeducir - saldo);
                saldo = saldo - valorADeducir; // Me voy a negativo (descubierto)
            } else {
                throw new NoHayDineroSuficienteException();
            }
        }
    }
    public void depositarSaldo(int valorDepositado){
        if(valorDepositado > deudaTarjetaCredito){
            int sobrante = valorDepositado - deudaTarjetaCredito;
            saldo = saldo + sobrante;
            deudaTarjetaCredito = 0;
        } else{
            deudaTarjetaCredito = deudaTarjetaCredito - valorDepositado;
        }
    }
    public float getMontoGastable(){
        if(saldo < 0){
            return tipo.getDescubiertoRestante();
        } else {
            return saldo + tipo.getDescubiertoRestante();
        }
    }

}
