package domain.security.password;

public class ValidadorPasswordTamanio implements Validador{
    @Override
    public boolean esValida(String password) {
        int tamanioPassword = password.length();

        if (tamanioPassword > 8) {
            return true;
        }
        else {
            //throw new TamanioException();
            return false;
        }
    }
}
