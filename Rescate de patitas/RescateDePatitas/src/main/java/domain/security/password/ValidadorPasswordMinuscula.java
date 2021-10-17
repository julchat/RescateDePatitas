package domain.security.password;

public class ValidadorPasswordMinuscula extends Validador {
    @Override
    public String esValida(String usuario, String password) {

        if(password.matches(".*[A-Z].*")) {
            return passwordStatus.getStatusOK();
        }
        else {
            return passwordStatus.getStatusMinus();
        }
    }
}