package domain.password;

import java.util.Arrays;
import java.util.List;

public class ValidadorPassword {
    private final List<Validador> validadoresPassword;

    public ValidadorPassword() {
        this.validadoresPassword = Arrays.asList( /*new ValidadorPasswordPrimerLetra(),*/
                                                  new ValidadorPasswordComun(),
                                                  new ValidadorPasswordNull(),
                                                  new ValidadorPasswordTamanio());
    }

    public boolean esValida(String password) {
        return validadoresPassword.stream().allMatch(validadorPassword -> validadorPassword.esValida(password));
    }
}