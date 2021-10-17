package domain.security.password;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ValidadorPassword {
    protected int cantidadMinima = 8;
    protected int cantidadMaxima = 64;
    private final List<Validador> validadoresPassword;

    public ValidadorPassword() {
        this.validadoresPassword = Arrays.asList(
                  new ValidadorPasswordComun()
                , new ValidadorPasswordNull()
                , new ValidadorPasswordTamanio(cantidadMinima, cantidadMaxima)
                , new ValidadorPasswordMismoUsuario()
                , new ValidadorPasswordMayuscula()
                , new ValidadorPasswordMinuscula()
                , new ValidadorPasswordNumero()
        );
    }

    public boolean esValida(String usuario, String password) {
        PasswordStatus passwordStatus = PasswordStatus.getInstance();
        return this.verificarPassword(usuario, password).stream().allMatch(s -> s.equals(passwordStatus.getStatusOK()));
    }

    public List<String> verificarPassword(String usuario, String password) {
        List<String> lista = validadoresPassword.stream().map(validador -> validador.esValida(usuario, password)).collect(Collectors.toList());
        return lista;
    }
}