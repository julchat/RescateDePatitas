package domain.password;

import exception.NullException;

public class ValidadorPasswordNull implements Validador{

    @Override
    public boolean esValido(String nombreDeUsuario, String password) {
        if (password.isEmpty()) {
            throw new NullException();
        }
        else {
            return true;
        }
    }
}
