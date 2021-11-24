package json;

public class FormFoundPet {
    // Persona
    String nombre;
    String apellido;
    String fechaDeNacimiento;
    String email;
    String tipoDoc;
    String nroDocumento;
    String telefono;
    // Notificaciones
    String notificacionSms;
    String notificacionEmail;
    String notificacionWpp;
    // Contacto
    String contactoNombre;
    String contactoApellido;
    String contactoEmail;
    String contactoTelefono;
    String contactoNotificacionSms;
    String contactoNotificacionEmail;
    String contactoNotificacionWpp;
    // Mascota
    String tipoAnimal;
    String sexoMascota;
    String tamanioMascota;
    String descripcionMascota;
    // Domicilio
    String provincia;
    String localidad;
    String codigoPostal;
    String calle;
    String numeracion;
    String departamento;
    String piso;

    // verifica si puede albergar a la mascota
    String transito;
    String radioKM;

    public FormFoundPet(String nombre, String apellido, String fechaDeNacimiento, String email, String tipoDoc, String nroDocumento, String telefono, String notificacionSms, String notificacionEmail, String notificacionWpp, String contactoNombre, String contactoApellido, String contactoEmail, String contactoTelefono, String contactoNotificacionSms, String contactoNotificacionEmail, String contactoNotificacionWpp, String tipoAnimal, String sexoMascota, String tamanioMascota, String descripcionMascota, String radioKM, String provincia, String localidad, String codigoPostal, String calle, String numeracion, String departamento, String piso, String transito) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.email = email;
        this.tipoDoc = tipoDoc;
        this.nroDocumento = nroDocumento;
        this.telefono = telefono;
        this.notificacionSms = notificacionSms;
        this.notificacionEmail = notificacionEmail;
        this.notificacionWpp = notificacionWpp;
        this.contactoNombre = contactoNombre;
        this.contactoApellido = contactoApellido;
        this.contactoEmail = contactoEmail;
        this.contactoTelefono = contactoTelefono;
        this.contactoNotificacionSms = contactoNotificacionSms;
        this.contactoNotificacionEmail = contactoNotificacionEmail;
        this.contactoNotificacionWpp = contactoNotificacionWpp;
        this.tipoAnimal = tipoAnimal;
        this.sexoMascota = sexoMascota;
        this.tamanioMascota = tamanioMascota;
        this.descripcionMascota = descripcionMascota;
        this.radioKM = radioKM;
        this.provincia = provincia;
        this.localidad = localidad;
        this.codigoPostal = codigoPostal;
        this.calle = calle;
        this.numeracion = numeracion;
        this.departamento = departamento;
        this.piso = piso;
        this.transito = transito;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public String getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNotificacionSms() {
        return notificacionSms;
    }

    public void setNotificacionSms(String notificacionSms) {
        this.notificacionSms = notificacionSms;
    }

    public String getNotificacionEmail() {
        return notificacionEmail;
    }

    public void setNotificacionEmail(String notificacionEmail) {
        this.notificacionEmail = notificacionEmail;
    }

    public String getNotificacionWpp() {
        return notificacionWpp;
    }

    public void setNotificacionWpp(String notificacionWpp) {
        this.notificacionWpp = notificacionWpp;
    }

    public String getContactoNombre() {
        return contactoNombre;
    }

    public void setContactoNombre(String contactoNombre) {
        this.contactoNombre = contactoNombre;
    }

    public String getContactoApellido() {
        return contactoApellido;
    }

    public void setContactoApellido(String contactoApellido) {
        this.contactoApellido = contactoApellido;
    }

    public String getContactoEmail() {
        return contactoEmail;
    }

    public void setContactoEmail(String contactoEmail) {
        this.contactoEmail = contactoEmail;
    }

    public String getContactoTelefono() {
        return contactoTelefono;
    }

    public void setContactoTelefono(String contactoTelefono) {
        this.contactoTelefono = contactoTelefono;
    }

    public String getContactoNotificacionSms() {
        return contactoNotificacionSms;
    }

    public void setContactoNotificacionSms(String contactoNotificacionSms) {
        this.contactoNotificacionSms = contactoNotificacionSms;
    }

    public String getContactoNotificacionEmail() {
        return contactoNotificacionEmail;
    }

    public void setContactoNotificacionEmail(String contactoNotificacionEmail) {
        this.contactoNotificacionEmail = contactoNotificacionEmail;
    }

    public String getContactoNotificacionWpp() {
        return contactoNotificacionWpp;
    }

    public void setContactoNotificacionWpp(String contactoNotificacionWpp) {
        this.contactoNotificacionWpp = contactoNotificacionWpp;
    }

    public String getTipoAnimal() {
        return tipoAnimal;
    }

    public void setTipoAnimal(String tipoAnimal) {
        this.tipoAnimal = tipoAnimal;
    }

    public String getSexoMascota() {
        return sexoMascota;
    }

    public void setSexoMascota(String sexoMascota) {
        this.sexoMascota = sexoMascota;
    }

    public String getTamanioMascota() { return tamanioMascota; }

    public void setTamanioMascota(String tamanioMascota) {
        this.tamanioMascota = tamanioMascota;
    }

    public String getDescripcionMascota() {
        return descripcionMascota;
    }

    public void setDescripcionMascota(String descripcionMascota) {
        this.descripcionMascota = descripcionMascota;
    }

    public String getRadioKM() {
        return radioKM;
    }

    public void setRadioKM(String radioKM) {
        this.radioKM = radioKM;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumeracion() {
        return numeracion;
    }

    public void setNumeracion(String numeracion) {
        this.numeracion = numeracion;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getTransito() {
        return transito;
    }

    public void setTransito(String transito) {
        this.transito = transito;
    }
}
