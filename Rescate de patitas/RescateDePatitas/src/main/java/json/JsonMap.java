package json;

public class JsonMap<T> {
    public Object contenido;
    public String mensaje;

    public Object getContenido() { return contenido; }

    public void setContenido(Object contenido) { this.contenido = contenido; }

    public String getMensaje() { return mensaje; }

    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    // Metodos
    public JsonMap(Object objeto, String mensaje) {
        this.contenido = objeto;
        this.mensaje = mensaje;
    }

    public String transformar(){
        return JsonController.transformar(this);
    }
}
