package domain.security.password;

public class ValidadorPasswordNumero extends Validador {
    @Override
    public String esValida(String usuario, String password) {

        if (password.matches(".*[0-9].*")) {
            return passwordStatus.getStatusOK();
        }
        else {
            return passwordStatus.getStatusNum();
        }
    }
}
