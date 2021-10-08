package domain.security.password;

public interface Validador {
    boolean esValida(String usuario, String password);
}
