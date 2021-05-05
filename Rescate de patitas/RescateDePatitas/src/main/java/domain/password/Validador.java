package domain.password;

public interface Validador {
    boolean esValido(String nombreDeUsuario, String password);
}
