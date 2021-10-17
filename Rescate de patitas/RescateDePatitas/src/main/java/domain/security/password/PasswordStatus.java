package domain.security.password;

public class PasswordStatus {
    private static final String StatusOK = "OK";
    private static final String StatusNull = "No puede ingresar una Contraseña nula.";
    private static final String StatusSimple = "La Contraseña es muy simple e insegura.";
    private static final String StatusMayus = "La Contraseña necesita al menos una mayúscula.";
    private static final String StatusMinus = "La Contraseña necesita al menos una minúscula.";
    private static final String StatusNum = "La Contraseña necesita al menos un número.";
    private static final String StatusTamanio = "La Contraseña debe tener un largo entre 8 y 64 caracteres.";
    private static final String StatusDiferentes = "El Usuario y la Contraseña tienen que ser distintos.";
    private static PasswordStatus instance = null;

    public static PasswordStatus getInstance() {
        if(instance == null) {
            instance = new PasswordStatus();
        }
        return instance;
    }

    public String getStatusOK() { return StatusOK; }

    public String getStatusNull() { return StatusNull; }

    public String getStatusSimple() { return StatusSimple; }

    public String getStatusMayus() { return StatusMayus; }

    public String getStatusMinus() { return StatusMinus; }

    public String getStatusNum() { return StatusNum; }

    public String getStatusTamanio() { return StatusTamanio; }

    public String getStatusDiferentes() { return StatusDiferentes; }
}
