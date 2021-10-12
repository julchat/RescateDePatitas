package domain.security.password;

public class ValidadorPasswordMismoUsuario extends Validador {
    @Override
    public String esValida(String usuario, String password) {

        if ((!usuario.equalsIgnoreCase(password) && !password.equalsIgnoreCase(usuario))) {
            return passwordStatus.getStatusOK();
        }
        else {
            return passwordStatus.getStatusDiferentes();
        }
    }
}


