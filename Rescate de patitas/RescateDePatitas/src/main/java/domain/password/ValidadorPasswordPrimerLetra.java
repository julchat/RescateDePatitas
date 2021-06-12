package domain.password;

import excepciones.PasswordInvalidException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidadorPasswordPrimerLetra implements Validador{
    @Override
    public boolean esValida(String password) {
        Pattern pattern = Pattern.compile("[A-Z]");
        Matcher matcher = pattern.matcher(password);
        if(pattern.equals(password.charAt(0))){
            return true;
        }
        else {
            throw new PasswordInvalidException();
        }
    }
}
