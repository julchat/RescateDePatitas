package json;

import java.util.List;

public class JsonLists<T> {
    List<T> lista;

    public JsonLists(List<T> lista) {
        this.lista = lista;
    }

    public List<T> getLista() { return lista; }

    public void setLista(List<T> lista) { this.lista = lista; }

    public String transformar(){
        return JsonController.transformar(this);
    }
}
