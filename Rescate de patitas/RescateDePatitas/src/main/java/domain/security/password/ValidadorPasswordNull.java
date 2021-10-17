package domain.security.password;

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
