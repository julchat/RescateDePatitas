package domain.password;

import java.util.Arrays;
import  java.util.List;

public class ValidadorPassword {
    private List<Validador> validadoresPassword;

    public void ValidadorPassword() {
        this.validadoresPassword = Arrays.asList( new ValidadorPrimerLetra(),
                                                  new ValidadorPasswordComun(),
                                                  new ValidadorPasswordTamanio());
    }

    public boolean esValido(String usuario, String password) {
        return validadoresPassword.stream().allMatch(validadorPassword -> validadorPassword.esValido(usuario, password));
    }
}
