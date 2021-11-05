package json;

public class FormUser {
    String nombre;
    String apellido;
    String fechaDeNacimiento;
    String email;
    String tipoDoc;
    String nroDocumento;
    String telefono;
    String userName;
    String password;
    String passConf;

    public FormUser(String nombre, String apellido, String fechaDeNacimiento, String email, String tipoDoc, String nroDocumento, String telefono, String userName, String password, String passConfg) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.email = email;
        this.tipoDoc = tipoDoc;
        this.nroDocumento = nroDocumento;
        this.telefono = telefono;
        this.userName = userName;
        this.password = password;
        this.passConf = passConfg;
    }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }

    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getFechaDeNacimiento() { return fechaDeNacimiento; }

    public void setFechaDeNacimiento(String fechaDeNacimiento) { this.fechaDeNacimiento = fechaDeNacimiento; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getTipoDoc() { return tipoDoc; }

    public void setTipoDoc(String tipoDoc) { this.tipoDoc = tipoDoc; }

    public String getNroDocumento() { return nroDocumento; }

    public void setNroDocumento(String nroDocumento) { this.nroDocumento = nroDocumento; }

    public String getTelefono() { return telefono; }

    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getUserName() { return userName; }

    public void setUserName(String userName) { this.userName = userName; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getPassConf() { return passConf; }

    public void setPassConf(String passConf) { this.passConf = passConf; }
}
