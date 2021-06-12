package domain.password;

import excepciones.NullException;

public class ValidadorPasswordNull implements Validador{

    @Override
    public boolean esValida(String password) {
        if (password.isEmpty()) {
            throw new NullException();
        }
        else {
            return true;
        }
    }
}
