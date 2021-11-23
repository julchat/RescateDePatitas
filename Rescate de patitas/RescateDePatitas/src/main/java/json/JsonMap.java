package json;

public class JsonMap<T> {
    public Object contenido;

    public Object getContenido() { return contenido; }

    public void setContenido(Object contenido) { this.contenido = contenido; }

    // Metodos
    public JsonMap(Object objeto) {
        this.contenido = objeto;
    }

    public String transformar(){
        return JsonController.transformar(this);
    }
}
