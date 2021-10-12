package domain.security.password;

import excepciones.PasswordInvalidException;
import excepciones.PasswordMayuscula;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidadorPasswordMayuscula extends Validador {
    @Override
    public String esValida(String usuario, String password) {

        if(password.matches(".*[A-Z].*")) {
            return passwordStatus.getStatusOK();
        }
        else {
            return passwordStatus.getStatusMayus();
        }
    }
}