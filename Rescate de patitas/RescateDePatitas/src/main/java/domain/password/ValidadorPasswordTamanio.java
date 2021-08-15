package domain.password;

import excepciones.TamanioException;
import java.util.stream.Stream;

public class ValidadorPasswordTamanio implements Validador{
    @Override
    public boolean esValida(String password) {
        int tamanioPassword = password.length();

        if (tamanioPassword > 8) {
            return true;
        }
        else {
            throw new TamanioException();
        }
    }
}
