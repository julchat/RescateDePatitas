package domain.security.password;

import java.util.Arrays;
import java.util.List;

public class ValidadorPassword {
    private final List<Validador> validadoresPassword;

    public ValidadorPassword() {
        this.validadoresPassword = Arrays.asList(new ValidadorPasswordComun(), new ValidadorPasswordNull(), new ValidadorPasswordTamanio(), new ValidadorPasswordMismoUsuario());
    }

    public boolean esValida(String usuario, String password) {
        return validadoresPassword.stream().allMatch(validadorPassword -> validadorPassword.esValida(usuario, password));
    }
}
