package json;

public class FormUsuarioRol {
    String id;
    String nombreUsuario;
    String tipoRol;



    public FormUsuarioRol(String id, String nombreUsuario, String tipoRol) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.tipoRol = tipoRol;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getTipoRol() {
        return tipoRol;
    }

    public void setTipoRol(String tipoRol) {
        this.tipoRol = tipoRol;
    }
}
