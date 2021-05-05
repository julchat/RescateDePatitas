package domain.password;

import exception.PasswordInvalidException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidadorPrimerLetra implements Validador{
    @Override
    public boolean esValido(String usuario, String password) {
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
