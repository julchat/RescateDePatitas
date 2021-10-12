package domain.security.password;

import excepciones.NullException;

public class ValidadorPasswordNull extends Validador {

    @Override
    public String esValida(String usuario, String password) {

        if(password.isEmpty()) {
            return passwordStatus.getStatusNull();
        }
        else {
            return passwordStatus.getStatusOK();
        }
    }
}
