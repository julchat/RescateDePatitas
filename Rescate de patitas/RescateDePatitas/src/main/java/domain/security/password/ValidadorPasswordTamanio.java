package domain.security.password;

public class ValidadorPasswordTamanio extends Validador {
    private int cantidadMinima;
    private int cantidadMaxima;

    public ValidadorPasswordTamanio(int cantidadMinima, int cantidadMaxima) {
        this.cantidadMinima = cantidadMinima;
        this.cantidadMaxima = cantidadMaxima;
    }

    @Override
    public String esValida(String usuario, String password) {
        int tamanioPassword = password.length();

        if(tamanioPassword >= cantidadMinima && tamanioPassword <= cantidadMaxima){
            return passwordStatus.getStatusOK();
        }
        else {
            return passwordStatus.getStatusTamanio();
        }
    }
}
