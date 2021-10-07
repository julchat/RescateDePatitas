package domain.security.password;

public class ValidadorPasswordMismoUsuario implements Validador {
    @Override
    public boolean esValida(String usuario, String password) {
        return !usuario.equals(password);
    }
}
