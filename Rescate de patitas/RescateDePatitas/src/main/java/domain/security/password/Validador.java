package domain.security.password;

public abstract class Validador {
    PasswordStatus passwordStatus = PasswordStatus.getInstance();
    abstract String esValida(String usuario, String password);
}
